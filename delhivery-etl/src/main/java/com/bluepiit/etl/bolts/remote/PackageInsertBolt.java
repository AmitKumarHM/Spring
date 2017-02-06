package com.bluepiit.etl.bolts.remote;


import static com.bluepiit.etl.utils.DelhiveryUtils.populateAddressAndPhoneDetails;
import static com.bluepiit.etl.utils.DelhiveryUtils.stripPipeAndNewLine;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluepiit.etl.DelhiveryETLConstants;
import com.bluepiit.etl.models.KinesisInputModel;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Suabhagya
 * @description Class is used to put packages into Kinesis Firehose.
 */
public class PackageInsertBolt extends BaseBasicBolt {


    private static final Logger LOG = LoggerFactory.getLogger(PackageInsertBolt.class);
    HashMap<String, String> dateTypes = new HashMap<>();
    private JedisPool pool = null;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
        DelhiveryUtils.populateDateTypes(dateTypes);
        LOG.info("----------------------- Inside PackageInsertBolt prepare populating Date Types ----------------------- ");
        pool = new JedisPool(new JedisPoolConfig(), "delhivery-dw.4w13el.ng.0001.euw1.cache.amazonaws.com", 6379);
        LOG.info("----------------------- Created the Jedis Pool ----------------------- ");
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer fieldsDeclarer) {
        // No Output.
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Tuple tuple,
                        BasicOutputCollector outputCollector) {


        byte[] payload = (byte[]) tuple.getValueByField(KinesisInputModel.FIELD_RECORD_DATA);
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, ArrayList<HashMap>> dateHashMapList = new HashMap<>();

        try {
            JsonNode jsonNode = mapper.readTree(payload);
            //  stripping the | and \n.
            String packageInput = stripPipeAndNewLine(jsonNode.toString());
            LOG.info(" >>>>>>>>>>>>>>>>>> Inside PackageInsertBolt data received >>>>>>>>>>>>>>>>>>> " + packageInput);

            // putting backup JSON data into firehose.
            HashMap<String, Object> s3JSONMap = new HashMap<>();
            s3JSONMap.put(DelhiveryETLConstants.JSON_KEY, packageInput);
            LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt PACKAGE S3 Backup record START <<<<<<<<<<<<<<<<<<<<<<< ");
            String s3JsonEntry = DelhiveryUtils.prepareDataForKinesis(s3JSONMap, DelhiveryETLConstants.S3_BACKUP_JSON);
            DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryETLConstants.DW_FIREHOSE_JSON_BACKUP, ByteBuffer.wrap(s3JsonEntry.getBytes()));
            LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt PACKAGE S3 Backup record END <<<<<<<<<<<<<<<<<<<<<<< ");

            HashMap<String, Object> readValue = mapper.readValue(packageInput, HashMap.class);

            boolean isPackage = readValue.get(DelhiveryETLConstants.COLLECTION_NAME_KEY).toString().equalsIgnoreCase(DelhiveryETLConstants.COLLECTION_PACKAGE);
            boolean isInsert = readValue.get(DelhiveryETLConstants.OPERATION).toString().equalsIgnoreCase(DelhiveryETLConstants.INSERT_OPERATION);
            String mongoId = DelhiveryUtils.extractMongoObjectId(readValue);


            if (isPackage && isInsert) {
                LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt payload received >>>>>>>>>>>>>>>>> " + mapper.readTree(payload).toString());
                HashMap<String, Object> packageMap = (HashMap<String, Object>) readValue.get(DelhiveryETLConstants.INCOMING_OBJECT);
                //TODO please log all empty mnogoids - waybillnum

                if(packageMap.containsKey("sku") && packageMap.get("sku") != null && packageMap.get("sku").toString().length() > 2048){
                    packageMap.put("sku",packageMap.get("sku").toString().substring(0,2048));
                }
                // operation added in map to check if it is insert or update
                packageMap.put(DelhiveryETLConstants.OP,DelhiveryETLConstants.OP_INSERT_VALUE);

                // adding mongo id to the map to persist it in the redshift.
                packageMap.put(DelhiveryETLConstants.ID, mongoId);

                // convert all the dates to milliseconds.
                DelhiveryUtils.parseDate(packageMap);

                // populating all the surrounding tables.
                DelhiveryUtils.populatePackageSurroundingTables(packageMap, dateTypes, dateHashMapList);

                // populating address and phone number after stripping the | and \n.
                populateAddressAndPhoneDetails(packageMap);

                // fetching scans from
                ArrayList<HashMap<String, Object>> scanList = (ArrayList<HashMap<String, Object>>) packageMap.get(DelhiveryETLConstants.SCAN_KEY);

                // putting package data into firehose.
                LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt PACKAGE record START <<<<<<<<<<<<<<<<<<<<<<< ");
                String packageEntry = DelhiveryUtils.prepareDataForKinesis(packageMap, DelhiveryETLConstants.PACKAGE_JSON_ARRAY);
                DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryETLConstants.DW_FIREHOSE_PKG, ByteBuffer.wrap(packageEntry.getBytes()));
                LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt PACKAGE record END <<<<<<<<<<<<<<<<<<<<<<< ");

                // putting data into REDIS.
                try (Jedis jedis = pool.getResource()) {
                    jedis.set(mongoId, (String) packageMap.get(DelhiveryETLConstants.WAY_BILL_NUM));
                    jedis.set((String) packageMap.get(DelhiveryETLConstants.WAY_BILL_NUM), packageEntry);
                    jedis.expire(DelhiveryETLConstants.ID, DelhiveryETLConstants.EXPIRY_SECONDS);
                }

                LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt SCAN record START <<<<<<<<<<<<<<<<<<<<<<< ");
                ArrayList<HashMap<String, Object>> scanMapList = scanInsertTransformation(scanList, packageMap);
                for (HashMap<String, Object> scanMap : scanMapList) {
                    DelhiveryUtils.enrichMap(scanMap, packageMap, true);
                    //todo log scanlist map
                    // putting scan map into firehose.

                    // operation added in map to check if it is insert or update
                    scanMap.put(DelhiveryETLConstants.OP,DelhiveryETLConstants.OP_INSERT_VALUE);

                    String scanEntry = DelhiveryUtils.prepareDataForKinesis(scanMap, DelhiveryETLConstants.SCAN_JSON_ARRAY);
                    DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryETLConstants.DW_FIREHOSE_SCAN_FACTS, ByteBuffer.wrap(scanEntry.getBytes()));
                }
                LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt SCAN record END <<<<<<<<<<<<<<<<<<<<<<< ");

                LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt DATE record START <<<<<<<<<<<<<<<<<<<<<<< ");
                for (String key : dateHashMapList.keySet()) {
                    ArrayList<HashMap> o = (ArrayList) dateHashMapList.get(key);
                    if (o == null) return;
                    for (HashMap dateMap : o) {

                        // operation added in map to check if it is insert or update
                        dateMap.put(DelhiveryETLConstants.OP,DelhiveryETLConstants.OP_INSERT_VALUE);

                        String dateEntry = DelhiveryUtils.prepareDataForKinesis(dateMap, DelhiveryETLConstants.PACKAGE_DATE_JSON_ARRAY);
                        DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryETLConstants.DW_FIREHOSE_PACKAGE_DATES, ByteBuffer.wrap(dateEntry.getBytes()));
                    }
                }
                LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt DATE record END <<<<<<<<<<<<<<<<<<<<<<< ");

                LOG.info(">>>>>>>>>>>>>>>>>>>>>>> Push Completed to Kinesis FireHose <<<<<<<<<<<<<<<<<<<");
            }


        } catch (Exception e) {
            LOG.error("------------------- PackageInsertBolt encountered an ERROR START------------------");
            e.printStackTrace();
            LOG.error("------------------- PackageInsertBolt encountered an ERROR END------------------");
        }
    }

    public ArrayList<HashMap<String, Object>> scanInsertTransformation(ArrayList<HashMap<String, Object>> scanList, HashMap<String, Object> packageMap) {


        ArrayList<HashMap<String, Object>> scanMapList = new ArrayList<>();
        HashMap<String, Object> prevScanMap = null;
        HashMap<String, Object> firstScanMap = null;
        for (HashMap<String, Object> scanMap : scanList) {
            // Decorating scanMap with packageMap Data.
            DateTime scanTime = null;
            if (scanMap.containsKey("sd") && ((Map) scanMap.get("sd")).get("$date") != null) {
                scanTime = new DateTime(((Map) scanMap.get("sd")).get("$date"));
            }

            DateTime pdTime = null;
            DateTime ivdTime = null;
            DateTime lddTime = null;
            DateTime fbdTime = null;
            DateTime fpdTime = null;
            DateTime frbdTime = null;
            DateTime mndTime = null;
            DateTime cpdTime = null;
            DateTime frdTime = null;
            DateTime pddTime = null;
            DateTime fddTime = null;
            DateTime sdTime = null;
            DateTime frpdTime = null;
            DateTime frddTime = null;

            DelhiveryUtils.enrichMap(scanMap, packageMap, true);
            scanMap.put("scanned_datetime_millis", ((Long) scanTime.toDate().getTime()).intValue());


            if (!packageMap.containsKey("pd") && (packageMap.get("pd")) != null) {
                pdTime = new DateTime(((Map) packageMap.get("pd")).get("$date"));
            }
            if (packageMap.containsKey("ivd") && (packageMap.get("ivd")) != null) {
                ivdTime = new DateTime(((Map) packageMap.get("ivd")).get("$date"));
            }
            if (packageMap.containsKey("ldd") && (packageMap.get("ldd")) != null) {
                lddTime = new DateTime(((Map) packageMap.get("ldd")).get("$date"));
            }
            if (packageMap.containsKey("fbd") && (packageMap.get("fbd")) != null) {
                fbdTime = new DateTime(((Map) packageMap.get("fbd")).get("$date"));
            }
            if (packageMap.containsKey("fpd") && (packageMap.get("fpd")) != null) {
                fpdTime = new DateTime(((Map) packageMap.get("fpd")).get("$date"));
            }
            if (packageMap.containsKey("pdd") && packageMap.get("pdd") != null) {
                pddTime = new DateTime(((Map) packageMap.get("pdd")).get("$date"));
            }

            if (packageMap.containsKey("date") && ((Map) packageMap.get("date")).containsKey("frbd") && ((Map) packageMap.get("date")).get("frbd") != null) {
                frbdTime = new DateTime((((Map) (((Map) packageMap.get("date")).get("frbd"))).get("$date")));
            }
            if (packageMap.containsKey("date") && ((Map) packageMap.get("date")).containsKey("mnd") && (((Map) packageMap.get("date")).get("mnd")) != null) {
                mndTime = new DateTime((((Map) (((Map) packageMap.get("date")).get("mnd"))).get("$date")));
            }
            if (packageMap.containsKey("date") && ((Map) packageMap.get("date")).containsKey("cpd") && (((Map) packageMap.get("date")).get("cpd")) != null) {
                cpdTime = new DateTime((((Map) (((Map) packageMap.get("date")).get("cpd"))).get("$date")));
            }
            if (packageMap.containsKey("date") && ((Map) packageMap.get("date")).containsKey("frd") && ((Map) packageMap.get("date")).get("frd") != null) {
                frdTime = new DateTime((((Map) (((Map) packageMap.get("date")).get("frd"))).get("$date")));
            }
            if (packageMap.containsKey("date") && ((Map) packageMap.get("date")).containsKey("frpd") && ((Map) packageMap.get("date")).get("frpd") != null) {
                frpdTime = new DateTime((((Map) (((Map) packageMap.get("date")).get("frpd"))).get("$date")));
            }
            if (packageMap.containsKey("date") && ((Map) packageMap.get("date")).containsKey("frdd") && ((Map) packageMap.get("date")).get("frdd") != null) {
                frddTime = new DateTime((((Map) (((Map) packageMap.get("date")).get("frdd"))).get("$date")));
            }

            if (packageMap.containsKey("dd") && ((Map) packageMap.get("dd")).containsKey("fdd") && ((Map) packageMap.get("dd")).get("fdd") != null) {
                fddTime = new DateTime((((Map) (((Map) packageMap.get("dd")).get("fdd"))).get("$date")));
            }
            if (packageMap.containsKey("cs") && ((Map) packageMap.get("cs")).containsKey("sd") && ((Map) packageMap.get("cs")).get("sd") != null) {
                sdTime = new DateTime((((Map) (((Map) packageMap.get("cs")).get("sd"))).get("$date")));
            }


            if (pdTime != null) {
                scanMap.put("now_pickup", new DateTime().getMillis() - pdTime.withZone(DateTimeZone.UTC).getMillis());
                if (mndTime != null) {
                    scanMap.put("pickup_manifestreceived", pdTime.getMillis() - mndTime.getMillis());
                }
                if (cpdTime != null) {
                    scanMap.put("pickup_clientpickup", pdTime.getMillis() - cpdTime.getMillis());
                }
                if (ivdTime != null) {
                    scanMap.put("incomingverified_pickup", ivdTime.getMillis() - pdTime.getMillis());
                }
                if (frdTime != null) {
                    scanMap.put("firstreturn_pickup", frdTime.getMillis() - pdTime.getMillis());
                }
                if (pddTime != null) {
                    scanMap.put("promised_pickup", pddTime.getMillis() - pdTime.getMillis());
                }
                if (fddTime != null) {
                    scanMap.put("firstdispatch_pickup", fddTime.getMillis() - pdTime.getMillis());
                }
                if (sdTime != null) {
                    scanMap.put("currentscanned_pickup", sdTime.getMillis() - pdTime.getMillis());
                }
            }

            if (fddTime != null) {
                if (fpdTime != null) {
                    scanMap.put("firstdispatch_firstpending", fddTime.getMillis() - fpdTime.getMillis());
                }
                if (lddTime != null) {
                    scanMap.put("lastdispatch_firstdispatch", lddTime.getMillis() - fddTime.getMillis());
                }
                if (ivdTime != null) {
                    scanMap.put("firstdispatch_incomingverified", fddTime.getMillis() - ivdTime.getMillis());
                }
            }

            if (frbdTime != null) {
                if (ivdTime != null) {
                    scanMap.put("firstbagging_incomingverified", fbdTime.getMillis() - ivdTime.getMillis());
                }
                if (mndTime != null) {
                    scanMap.put("firstbaggingforreturn_firstreturn", frbdTime.getMillis() - frdTime.getMillis());
                }
            }

            if (frpdTime != null && frddTime != null) {
                scanMap.put("firstpendingforreturn_firstdispatchforreturn", frpdTime.getMillis() - frddTime.getMillis());
            }
            if (sdTime != null) {
                scanMap.put("now_currentscanned", new DateTime().getMillis() - sdTime.withZone(DateTimeZone.UTC).getMillis());
            }

            if (prevScanMap == null) {
                scanMap.put("previous_scan_location", null);
                scanMap.put("time_in_system", 0);
                scanMap.put("time_since_last_scan", 0);
                scanMap.put("time_spent_in_facility", 0);
                scanMap.put("time_on_road", 0);
                if (packageMap.containsKey("pt") && packageMap.get("pt") != null && (packageMap.get("pt").toString().equalsIgnoreCase("COD") || packageMap.get("pt").toString().equalsIgnoreCase("Pre-paid"))) {
                    if (scanMap.containsKey("ss") && scanMap.get("ss") != null && scanMap.containsKey("st") && scanMap.get("st") != null) {
                        if (scanMap.get("ss").toString().equalsIgnoreCase("In Transit") && scanMap.get("st").toString().equalsIgnoreCase("RT")) {
                            scanMap.put("returns_pickedup_date", scanMap.get("sd"));
                            scanMap.put("returns_closure_time", 0);
                        }
                        if (scanMap.get("ss").toString().equalsIgnoreCase("RTO") && scanMap.get("st").toString().equalsIgnoreCase("DL")) {
                            scanMap.put("returns_closed_date", scanMap.get("sd"));
                        }

                    }
                }

                if (scanMap.containsKey("vh") && scanMap.get("vh") != null && scanMap.get("vh") != "null") {
                    scanMap.put("field_user_user_id", scanMap.get("vh").toString().substring(scanMap.get("vh").toString().indexOf("(") + 1, scanMap.get("vh").toString().indexOf(")")));
                }

                firstScanMap = scanMap;
            } else {
                scanMap.put("previous_scan_location", prevScanMap.get("sl").toString());
                scanMap.put("time_in_system", (Integer) scanMap.get("scanned_datetime_millis") - (Integer) firstScanMap.get("scanned_datetime_millis"));
                scanMap.put("time_since_last_scan", (Integer) scanMap.get("scanned_datetime_millis") - (Integer) prevScanMap.get("scanned_datetime_millis"));

                if (prevScanMap.get("sl").toString().equals(scanMap.get("sl").toString())) {
                    scanMap.put("time_spent_in_facility", (Integer) prevScanMap.get("time_spent_in_facility") + ((Integer) scanMap.get("scanned_datetime_millis") - (Integer) prevScanMap.get("scanned_datetime_millis")));
                    scanMap.put("time_on_road", prevScanMap.get("time_on_road"));
                    if (scanMap.containsKey("vh") && scanMap.get("vh") != null && scanMap.get("vh") != "null") {
                        scanMap.put("field_user_user_id", scanMap.get("vh").toString().substring(scanMap.get("vh").toString().indexOf("(") + 1, scanMap.get("vh").toString().indexOf(")")));
                    } else if (prevScanMap.containsKey("field_user_user_id") && prevScanMap.get("field_user_user_id") != null) {
                        scanMap.put("field_user_user_id", prevScanMap.get("field_user_user_id"));
                    }
                    if (packageMap.containsKey("pt") && packageMap.get("pt") != null && (packageMap.get("pt").toString().equalsIgnoreCase("COD") || packageMap.get("pt").toString().equalsIgnoreCase("Pre-paid"))) {
                        if (scanMap.containsKey("ss") && scanMap.get("ss") != null && scanMap.containsKey("st") && scanMap.get("st") != null) {
                            if (scanMap.get("st").toString().equalsIgnoreCase("RT")) {
                                DateTime firstScanTime = new DateTime(((Map) firstScanMap.get("sd")).get("$date"));
                                scanMap.put("returns_closure_time", scanTime.getMillis() - firstScanTime.getMillis());
                            }
                            if (scanMap.get("ss").toString().equalsIgnoreCase("RTO") && scanMap.get("st").toString().equalsIgnoreCase("DL")) {
                                scanMap.put("returns_closed_date", scanMap.get("sd"));
                            }

                        }
                    }
                } else {
                    scanMap.put("time_spent_in_facility", 0);
                    scanMap.put("time_on_road", (Integer) scanMap.get("scanned_datetime_millis") - (Integer) prevScanMap.get("scanned_datetime_millis"));
                    if (scanMap.containsKey("vh") && scanMap.get("vh") != null && scanMap.get("vh") != "null") {
                        scanMap.put("field_user_user_id", scanMap.get("vh").toString().substring(scanMap.get("vh").toString().indexOf("(") + 1, scanMap.get("vh").toString().indexOf(")")));
                    } else if (prevScanMap.containsKey("field_user_user_id") && prevScanMap.get("field_user_user_id") != null) {
                        scanMap.put("field_user_user_id", prevScanMap.get("field_user_user_id"));
                    }
                    if (packageMap.containsKey("pt") && packageMap.get("pt") != null && (packageMap.get("pt").toString().equalsIgnoreCase("COD") || packageMap.get("pt").toString().equalsIgnoreCase("Pre-paid"))) {
                        if (scanMap.containsKey("ss") && scanMap.get("ss") != null && scanMap.containsKey("st") && scanMap.get("st") != null) {
                            if (scanMap.get("st").toString().equalsIgnoreCase("RT")) {
                                scanMap.put("returns_pickedup_date", scanMap.get("sd"));
                            }
                            if (scanMap.get("ss").toString().equalsIgnoreCase("RTO") && scanMap.get("st").toString().equalsIgnoreCase("DL")) {
                                scanMap.put("returns_closed_date", scanMap.get("sd"));
                            }
                            DateTime previousScanTime = new DateTime(((Map) prevScanMap.get("sd")).get("$date"));
                                scanMap.put("returns_closure_time", new DateTime().getMillis() - previousScanTime.withZone(DateTimeZone.UTC).getMillis());
                        }
                    }
                }
            }
            prevScanMap = scanMap;
            scanMapList.add(scanMap);
        }
        return scanMapList;
    }

    @Override
    public void cleanup() {
        super.cleanup();
        pool.destroy();
    }
}
