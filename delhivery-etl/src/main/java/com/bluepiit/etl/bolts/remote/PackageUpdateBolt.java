package com.bluepiit.etl.bolts.remote;

import static com.bluepiit.etl.utils.DelhiveryUtils.populateAddressAndPhoneDetails;
import static com.bluepiit.etl.utils.DelhiveryUtils.stripPipeAndNewLine;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluepiit.etl.DelhiveryETLConstants;
import com.bluepiit.etl.models.KinesisInputModel;
import com.bluepiit.etl.models.PackageDate;
import com.bluepiit.etl.utils.DBUtils;
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


public class PackageUpdateBolt extends BaseBasicBolt {


    private static final Logger LOG = LoggerFactory.getLogger(PackageUpdateBolt.class);

    private HashMap<String, String> dateTypes = new HashMap<>();
    private JedisPool pool = null;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
        DelhiveryUtils.populateDateTypes(dateTypes);
        pool = new JedisPool(new JedisPoolConfig(), "delhivery-dw.4w13el.ng.0001.euw1.cache.amazonaws.com", 6379);
        LOG.info("------------------ All the Date Types fetched successfully -------------------");
    }


    @Override
    public void declareOutputFields(OutputFieldsDeclarer fieldsDeclarer) {
        // No Output.
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Tuple tuple,
                        BasicOutputCollector outputCollector) {
        HashMap<String, ArrayList<HashMap>> dateHashMapList = new HashMap<>();

        byte[] payload = (byte[]) tuple.getValueByField(KinesisInputModel.FIELD_RECORD_DATA);
        ObjectMapper mapper = new ObjectMapper();


        SqlSession session = DBUtils.getSessionFactory().openSession();

        try {
            JsonNode jsonNode = mapper.readTree(payload);
            //  stripping the | and \n.
            String packageUpdate = stripPipeAndNewLine(jsonNode.toString());
            LOG.info(" >>>>>>>>>>>>>>>>>> Inside PackageUpdateBolt data received >>>>>>>>>>>>>>>>>>> " + packageUpdate);

            // putting backup JSON data into firehose.
            HashMap<String, Object> s3JSONMap = new HashMap<>();
            s3JSONMap.put(DelhiveryETLConstants.JSON_KEY, packageUpdate);
            LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt PACKAGE S3 Backup record START <<<<<<<<<<<<<<<<<<<<<<< ");
            String s3JsonEntry = DelhiveryUtils.prepareDataForKinesis(s3JSONMap, DelhiveryETLConstants.S3_BACKUP_JSON);
            DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryETLConstants.DW_FIREHOSE_JSON_BACKUP, ByteBuffer.wrap(s3JsonEntry.getBytes()));
            LOG.info(">>>>>>>>>>>>>>>>> Inside PackageInsertBolt PACKAGE S3 Backup record END <<<<<<<<<<<<<<<<<<<<<<< ");

            HashMap<String, Object> updatePackageMap = mapper.readValue(packageUpdate, HashMap.class);

            String mongoId = DelhiveryUtils.extractMongoObjectId(updatePackageMap);

            if (updatePackageMap.containsKey("sku") && updatePackageMap.get("sku") != null && updatePackageMap.get("sku").toString().length() > 2048) {
                updatePackageMap.put("sku", updatePackageMap.get("sku").toString().substring(0, 2048));
            }

            // operation added in map to check if it is insert or update
            updatePackageMap.put(DelhiveryETLConstants.OP, DelhiveryETLConstants.OP_UPDATE_VALUE);

            boolean isPackage = updatePackageMap.get(DelhiveryETLConstants.COLLECTION_NAME_KEY).toString().equalsIgnoreCase(DelhiveryETLConstants.COLLECTION_PACKAGE);
            boolean isUpdate = updatePackageMap.get(DelhiveryETLConstants.OPERATION).toString().equalsIgnoreCase(DelhiveryETLConstants.UPDATE_OPERATION);


            if (!(isPackage && isUpdate)) return;

            updatePackageMap = ((HashMap<String, Object>) updatePackageMap.get(DelhiveryETLConstants.INCOMING_OBJECT));
            if (updatePackageMap.containsKey(DelhiveryETLConstants.UPDATE_ACTION_SET))
                updatePackageMap = (HashMap<String, Object>) updatePackageMap.get(DelhiveryETLConstants.UPDATE_ACTION_SET);

            String waybill;


            try (Jedis jedis = pool.getResource()) {

                waybill = jedis.get(mongoId);

                if (waybill == null) {
                    waybill = session.selectOne(DelhiveryETLConstants.PACKAGE_SELECT_WAYBILLNUM, mongoId);

                }

                if (StringUtils.isBlank(waybill)) return;

                jedis.set(mongoId, waybill);
                //set the cache timeout to 24 hours
                jedis.expire(mongoId, DelhiveryETLConstants.EXPIRY_SECONDS);

                //Start processing for any scan that may have come as part of the package
                DelhiveryUtils.parseDate(updatePackageMap); //convert times into ms
                updatePackageMap.put(DelhiveryETLConstants.WAY_BILL_NUM, waybill);

                // populating all the surrounding tables.
                DelhiveryUtils.populatePackageSurroundingTables(updatePackageMap, dateTypes, dateHashMapList);

                // populating the address and phone number.
                populateAddressAndPhoneDetails(updatePackageMap);

                LOG.info(">>>>>>>>>>>>>>>>> Inside PackageUpdateBolt PACKAGE out record START <<<<<<<<<<<<<<<<<<<<<<< ");
                //put the package entry into the Kinesis Stream
                String packageEntry = DelhiveryUtils.prepareDataForKinesis(updatePackageMap, DelhiveryETLConstants.PACKAGE_JSON_ARRAY);
                DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryETLConstants.DW_FIREHOSE_PKG, ByteBuffer.wrap(packageEntry.getBytes()));
                //put the packageEntry against the waybill so that I look it up there before looking up redshift
                jedis.set(waybill, packageEntry);
            }

            List<HashMap<String, Object>> incomingScanList = new ArrayList<>();

            for (String key : updatePackageMap.keySet()) {
                if (key.startsWith("s.") || key.equalsIgnoreCase("s")) {
                    if (updatePackageMap.get(key) instanceof Map) {
                        incomingScanList.add((HashMap<String, Object>) updatePackageMap.get(key));

                    } else {
                        incomingScanList = (ArrayList<HashMap<String, Object>>) updatePackageMap.get(key);
                    }
                }
            }

            HashMap<String, Object> lastScanObject;

            try (Jedis jedis = pool.getResource()) {

                lastScanObject = getScanObjectFromTabSeparatedString(jedis.get("lastscan-" + waybill));

                if (lastScanObject == null) {
                    lastScanObject = session.selectOne(DelhiveryETLConstants
                            .PACKAGE_SCANS_SELECT_SCAN, waybill);
                }

                List<PackageDate> packageDates = session.selectList(DelhiveryETLConstants.PACKAGE_DATES_LOOKUP, waybill);

                if (lastScanObject == null) {

                    //TODO push undecorated objectsproceed with all scans without decorations
                } else {

                    for (PackageDate pd : packageDates) {
                        if (pd == null) continue;
                        lastScanObject.put(pd.getDateCode(), pd.getDateValue());
                    }

                    String scanString = null;
                    //proceed with decorations
                    boolean isDb = true;

                    for (int i = 0; i < incomingScanList.size(); i++) {
                        if (i != 0) isDb = false;

                        lastScanObject = decorateScan(incomingScanList.get(i), lastScanObject, isDb);
                        //TODO need to add operation in lastScanObject instead of incomingMap
                        // operation added in map to check if it is insert or update
                        lastScanObject.put(DelhiveryETLConstants.OP, DelhiveryETLConstants.OP_UPDATE_VALUE);

                        scanString = DelhiveryUtils.prepareDataForKinesis(lastScanObject, DelhiveryETLConstants.SCAN_JSON_ARRAY);
                        DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryETLConstants.DW_FIREHOSE_SCAN_FACTS, ByteBuffer
                                .wrap(scanString.getBytes()));
                    }

                    if (scanString != null) jedis.set("lastscan-" + waybill, scanString);
                }
            }
            LOG.info(">>>>>>>>>>>>>>>>> Inside PackageUpdateBolt DATE out record START <<<<<<<<<<<<<<<<<<<<<<< ");
            for (String key : dateHashMapList.keySet()) {
                ArrayList<HashMap> o = (ArrayList) dateHashMapList.get(key);
                if (o == null) return;
                for (HashMap dateMap : o) {

                    // operation added in map to check if it is insert or update
                    dateMap.put(DelhiveryETLConstants.OP, DelhiveryETLConstants.OP_UPDATE_VALUE);

                    String dateEntry = DelhiveryUtils.prepareDataForKinesis(dateMap, DelhiveryETLConstants.PACKAGE_DATE_JSON_ARRAY);
                    DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryETLConstants.DW_FIREHOSE_PACKAGE_DATES, ByteBuffer.wrap(dateEntry.getBytes()));
                }
            }
            LOG.info(">>>>>>>>>>>>>>>>> Inside PackageUpdateBolt DATE out record END <<<<<<<<<<<<<<<<<<<<<<< ");


        } catch (Exception e) {
            LOG.error("------- Error occurred while adding data to Package Update List -------");
            e.printStackTrace();
            session.rollback();

        } finally {
            session.close();
        }

    }

    private HashMap<String, Object> decorateScan(HashMap<String, Object> currentScan,
                                                 HashMap<String, Object> lastScan,
                                                 boolean isDb) {

        DelhiveryUtils.enrichMap(currentScan, lastScan, false);
//        calculateScans(currentScan, lastScan, isDb);

        return currentScan;


    }

    private void calculateScans(HashMap<String, Object> currentScan, HashMap<String, Object> lastScan, boolean isDb) {
        calculatePickUpManifestDate(currentScan);
        calculateClientPickUp(currentScan);
        calculateIncomingVerifiedPickUp(currentScan);
        calculateFirstbaggingIncVerified(currentScan);
        calculateFirstDispatchFirstPending(currentScan);
        calculateLastDispatchFirstDisptach(currentScan);
        calculateFirstReturnPickup(currentScan);
        calculateFirstBaggingOfFirstReturn(currentScan);
        calculateFirstPendingOfFirstReturn(currentScan);
        calculatePromisedPickup(currentScan);
        calculateFirstDispatchPickup(currentScan);
        calculateFirstDispatchIncomingVerified(currentScan);
        calculateCurrentScanned(currentScan);
        calculateCurrentScannedPickup(currentScan);
        calculateNowPickup(currentScan);

        if (isDb) {
            calculateTimeInSystemLastScanOrTimeRoad(currentScan, lastScan, true);
            calculateTimeSpentInFacility(currentScan, lastScan, true);
            calculateFieldUserId(currentScan, lastScan);
            calculateReturnsDate(currentScan, lastScan, true);
        } else {
            calculateTimeInSystemLastScanOrTimeRoad(currentScan, lastScan, false);
            calculateTimeSpentInFacility(currentScan, lastScan, false);
            calculateFieldUserId(currentScan, lastScan);
            calculateReturnsDate(currentScan, lastScan, false);
        }
    }

    private HashMap<String, Object> getScanObjectFromTabSeparatedString(String lastScanObject) {

        //TODO  @SaubhagyaB Write the code to generate the scan object from the tab separated scan string retrieved
        // from Redis

        return null;
    }


    private void calculatePickUpManifestDate(HashMap<String, Object> incomingScanMap) {

        if (incomingScanMap.get("package_pickup_datetime") != null && incomingScanMap.get("package_manifest_datetime") != null) {
            long value = new DateTime(incomingScanMap.get("package_pickup_datetime")).getMillis() - new DateTime(incomingScanMap.get("package_manifest_datetime")).getMillis();
            incomingScanMap.put("pickup_manifestreceived", Math.abs(value));
        }
    }

    private void calculateClientPickUp(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("package_pickup_datetime") != null && incomingScanMap.get("package_pickup_at_clientwarehouse_datetime") != null) {
            long value = new DateTime(incomingScanMap.get("package_pickup_datetime")).getMillis() - new DateTime(incomingScanMap.get("package_pickup_at_clientwarehouse_datetime")).getMillis();
            incomingScanMap.put("pickup_clientpickup", Math.abs(value));
        }
    }

    private void calculateIncomingVerifiedPickUp(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("incoming_verified_datetime") != null && incomingScanMap.get("package_pickup_datetime") != null) {
            long value = new DateTime(incomingScanMap.get("incoming_verified_datetime")).getMillis() - new DateTime(incomingScanMap.get("package_pickup_datetime")).getMillis();
            incomingScanMap.put("incomingverified_pickup", Math.abs(value));
        }

    }

    private void calculateFirstbaggingIncVerified(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("first_bagging_datetime") != null && incomingScanMap.get("incoming_verified_datetime") != null) {
            long value = new DateTime(incomingScanMap.get("first_bagging_datetime")).getMillis() - new DateTime(incomingScanMap.get("incoming_verified_datetime")).getMillis();
            incomingScanMap.put("firstbagging_incomingverified", Math.abs(value));
        }

    }

    private void calculateFirstDispatchFirstPending(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("first_dispatch_datetime") != null && incomingScanMap.get("first_pending_datetime") != null) {
            long value = new DateTime(incomingScanMap.get("first_dispatch_datetime")).getMillis() - new DateTime(incomingScanMap.get("first_pending_datetime")).getMillis();
            incomingScanMap.put("firstdispatch_firstpending", Math.abs(value));
        }

    }

    private void calculateLastDispatchFirstDisptach(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("last_dispatch_datetime") != null && incomingScanMap.get("first_dispatch_datetime") != null) {
            long value = new DateTime(incomingScanMap.get("last_dispatch_datetime")).getMillis() - new DateTime(incomingScanMap.get("first_dispatch_datetime")).getMillis();
            incomingScanMap.put("lastdispatch_firstdispatch", Math.abs(value));
        }

    }

    private void calculateFirstReturnPickup(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("first_datetime_return_shipment") != null && incomingScanMap.get("package_pickup_datetime") != null) {
            long value = new DateTime(incomingScanMap.get("first_datetime_return_shipment")).getMillis() - new DateTime(incomingScanMap.get("package_pickup_datetime")).getMillis();
            incomingScanMap.put("firstreturn_pickup", Math.abs(value));
        }

    }

    private void calculateFirstBaggingOfFirstReturn(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("first_bagging_datetime_return_shipment") != null && incomingScanMap.get("first_datetime_return_shipment") != null) {
            long value = new DateTime(incomingScanMap.get("first_bagging_datetime_return_shipment")).getMillis() - new DateTime(incomingScanMap.get("first_datetime_return_shipment")).getMillis();
            incomingScanMap.put("firstbaggingforreturn_firstreturn", Math.abs(value));
        }

    }

    private void calculateFirstPendingOfFirstReturn(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("first_pending_datetime_return_shipment") != null && incomingScanMap.get("first_dispatch_datetime_return_shipment") != null) {
            long value = new DateTime(incomingScanMap.get("first_pending_datetime_return_shipment")).getMillis() - new DateTime(incomingScanMap.get("first_dispatch_datetime_return_shipment")).getMillis();
            incomingScanMap.put("firstpendingforreturn_firstdispatchforreturn", Math.abs(value));
        }

    }

    private void calculatePromisedPickup(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("promised_datetime") != null && incomingScanMap.get("package_pickup_datetime") != null) {
            long value = new DateTime(incomingScanMap.get("promised_datetime")).getMillis() - new DateTime(incomingScanMap.get("package_pickup_datetime")).getMillis();
            incomingScanMap.put("promised_pickup", Math.abs(value));
        }
    }

    private void calculateFirstDispatchPickup(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("first_dispatch_datetime") != null && incomingScanMap.get("package_pickup_datetime") != null) {
            long value = new DateTime(incomingScanMap.get("first_dispatch_datetime")).getMillis() - new DateTime(incomingScanMap.get("package_pickup_datetime")).getMillis();
            incomingScanMap.put("firstdispatch_pickup", Math.abs(value));
        }

    }

    private void calculateFirstDispatchIncomingVerified(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("first_dispatch_datetime") != null && incomingScanMap.get("incoming_verified_datetime") != null) {
            long value = new DateTime(incomingScanMap.get("first_dispatch_datetime")).getMillis() - new DateTime(incomingScanMap.get("incoming_verified_datetime")).getMillis();
            incomingScanMap.put("firstdispatch_incomingverified", Math.abs(value));
        }

    }

    private void calculateCurrentScanned(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("sd") != null && ((Map) incomingScanMap.get("sd")).get("$date") != null) {
            incomingScanMap.put("now_currentscanned", new DateTime().getMillis() - new DateTime(((Map) incomingScanMap.get("sd")).get("$date")).withZone(DateTimeZone.UTC).getMillis());
        }

    }

    private void calculateCurrentScannedPickup(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("package_pickup_datetime") != null && incomingScanMap.get("sd") != null) {
            incomingScanMap.put("currentscanned_pickup", new DateTime(((Map) incomingScanMap.get("sd")).get("$date")).getMillis() - new DateTime(incomingScanMap.get("package_pickup_datetime")).getMillis());
        }

    }

    private void calculateNowPickup(HashMap<String, Object> incomingScanMap) {
        if (incomingScanMap.get("package_pickup_datetime") != null) {
            incomingScanMap.put("now_pickup", new DateTime().getMillis() - new DateTime(incomingScanMap.get("package_pickup_datetime")).withZone(DateTimeZone.UTC).getMillis());
        }

    }

    private void calculateTimeInSystemLastScanOrTimeRoad(HashMap<String, Object> incomingScanMap, HashMap<String, Object> prevScanMap, boolean dbflag) {
        DateTime sddTime = new DateTime(((Map) incomingScanMap.get("sd")).get("$date"));
        long sddMilli = sddTime.toDate().getTime();
        long tisMilli = 0;
        if (prevScanMap.get("time_in_system") != null) {
            DateTime spdTime = new DateTime(Long.parseLong(prevScanMap.get("time_in_system").toString()));
            tisMilli = spdTime.toDate().getTime();
        }

        if (dbflag) {
            if (prevScanMap.containsKey("scanned_datetime") && prevScanMap.get("scanned_datetime") != null) {

                long sdMilli = new DateTime(prevScanMap.get("scanned_datetime")).toDate().getTime();
                incomingScanMap.put("time_in_system", tisMilli + (sddMilli - sdMilli));
                incomingScanMap.put("time_since_last_scan", sddMilli - sdMilli);
                if (incomingScanMap.get("sl").toString().equals(prevScanMap.get("scan_location_center").toString())) {
                    incomingScanMap.put("time_on_road", prevScanMap.get("time_on_road"));
                } else if (!incomingScanMap.get("sl").toString().equals(prevScanMap.get("scan_location_center").toString())) {
                    incomingScanMap.put("time_on_road", sddMilli - sdMilli);
                }
            }
            incomingScanMap.put("previous_scan_location", prevScanMap.get("scan_location_center").toString());
        }

        if (!dbflag) {
            if (prevScanMap.containsKey("sd") && prevScanMap.get("sd") != null) {

                long sdMilli = new DateTime(((Map) prevScanMap.get("sd")).get("$date")).toDate().getTime();
                incomingScanMap.put("time_in_system", tisMilli + (sddMilli - sdMilli));
                incomingScanMap.put("time_since_last_scan", sddMilli - sdMilli);
                if (incomingScanMap.get("sl").toString().equals(prevScanMap.get("sl").toString())) {
                    incomingScanMap.put("time_on_road", prevScanMap.get("time_on_road"));
                } else if (!incomingScanMap.get("sl").toString().equals(prevScanMap.get("sl").toString())) {
                    incomingScanMap.put("time_on_road", sddMilli - sdMilli);
                }
            }
            incomingScanMap.put("previous_scan_location", prevScanMap.get("sl").toString());
        }

    }

    private void calculateTimeSpentInFacility(HashMap<String, Object> incomingScanMap, HashMap<String, Object> prevScanMap, boolean dbflag) {

        long sddMilli = new DateTime(((Map) incomingScanMap.get("sd")).get("$date")).toDate().getTime();

        long spdMilli = 0;
        if (prevScanMap.get("time_spent_in_facility") != null) {
            DateTime spdTime = new DateTime(Long.parseLong(prevScanMap.get("time_spent_in_facility").toString()));
            spdMilli = spdTime.toDate().getTime();
        }

        if (dbflag) {
            if (prevScanMap.get("scan_location_center") != null && incomingScanMap.get("sl").toString().equals(prevScanMap.get("scan_location_center").toString())) {
                if (prevScanMap.get("scanned_datetime") != null) {
                    long sdMilli = new DateTime(prevScanMap.get("scanned_datetime")).toDate().getTime();
                    incomingScanMap.put("time_spent_in_facility", spdMilli + sddMilli - sdMilli);
                }
            } else if (prevScanMap.get("scan_location_center") != null && !incomingScanMap.get("sl").toString().equals(prevScanMap.get("scan_location_center").toString())) {
                incomingScanMap.put("time_spent_in_facility", 0);
            }
        }

        if (!dbflag) {
            if (prevScanMap.get("sl") != null && incomingScanMap.get("sl").toString().equals(prevScanMap.get("sl").toString())) {
                if (prevScanMap.get("time_spent_in_facility") != null && prevScanMap.get("sd") != null) {

                    long sdMilli = new DateTime(((Map) prevScanMap.get("sd")).get("$date")).toDate().getTime();
                    incomingScanMap.put("time_spent_in_facility", spdMilli + sddMilli - sdMilli);
                }
            } else if (prevScanMap.get("sl") != null && !incomingScanMap.get("sl").toString().equals(prevScanMap.get("sl").toString())) {
                incomingScanMap.put("time_spent_in_facility", 0);
            }
        }
    }

    private void calculateFieldUserId(HashMap<String, Object> incomingScanMap, HashMap<String, Object> prevScanMap) {
        if (incomingScanMap.containsKey("vh") && incomingScanMap.get("vh") != null && incomingScanMap.get("vh") != "null") {
            incomingScanMap.put("field_user_user_id", incomingScanMap.get("vh").toString().substring(incomingScanMap.get("vh").toString().indexOf("(") + 1, incomingScanMap.get("vh").toString().indexOf(")")));
        } else {
            incomingScanMap.put("field_user_user_id", prevScanMap.get("field_user_user_id"));
        }
    }

    private void calculateReturnsDate(HashMap<String, Object> incomingScanMap, HashMap<String, Object> prevScanMap, boolean dbflag) {
        if (incomingScanMap.get("package_pickup_types_code") != null && (incomingScanMap.get("package_pickup_types_code").toString().equalsIgnoreCase("COD") || incomingScanMap.get("package_pickup_types_code").toString().equalsIgnoreCase("Pre-paid"))) {
            if (incomingScanMap.get("ss") != null && incomingScanMap.get("st") != null) {
                DateTime previousScanTime = null;

                if (dbflag) {
                    previousScanTime = new DateTime(prevScanMap.get("scanned_datetime"));
                }
                if (!dbflag) {
                    previousScanTime = new DateTime(((Map) prevScanMap.get("sd")).get("$date"));
                }

                if (incomingScanMap.get("st").toString().equalsIgnoreCase("RT") && incomingScanMap.get("ss").toString().equalsIgnoreCase("In Transit")) {
                    incomingScanMap.put("returns_pickedup_date", incomingScanMap.get("sd"));
                    incomingScanMap.put("returns_closure_time", 0);
                }

                if (incomingScanMap.get("ss").toString().equalsIgnoreCase("RTO") && incomingScanMap.get("st").toString().equalsIgnoreCase("DL")) {
                    incomingScanMap.put("returns_closure_time", new DateTime(((Map) incomingScanMap.get("sd")).get("$date")).getMillis() - previousScanTime.getMillis());
                    incomingScanMap.put("returns_closed_date", incomingScanMap.get("sd"));
                }
            }
        }
    }


    @Override
    public void cleanup() {
        super.cleanup();
        pool.destroy();
    }
}
