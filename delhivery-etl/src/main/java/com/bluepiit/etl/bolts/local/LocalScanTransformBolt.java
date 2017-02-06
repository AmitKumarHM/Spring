	package com.bluepiit.etl.bolts.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluepiit.etl.utils.DBUtils;
import com.bluepiit.etl.utils.DelhiveryUtils;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class LocalScanTransformBolt extends BaseBasicBolt {

    private static final Logger LOG = LoggerFactory.getLogger(LocalScanTransformBolt.class);
    private List<HashMap<String, Object>> resultantScanList;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
        resultantScanList = new ArrayList<HashMap<String, Object>>();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer fieldsDeclarer) {
        fieldsDeclarer.declare(new Fields("scanList"));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Tuple tuple, BasicOutputCollector outputCollector) {

        SqlSession session = DBUtils.getSessionFactory().openSession();

        List<HashMap<String, Object>> packageList = (ArrayList<HashMap<String, Object>>) tuple.getValueByField("packageList");
        List<HashMap<String, Object>> scanList;

        if (tuple.contains("status") && !packageList.isEmpty()) {
            for (HashMap<String, Object> pkgMap : packageList) {
                String id;
                HashMap<String, Object> packageMap;


                if (pkgMap.get("_id").toString().contains("$oid")) {
                    id = DelhiveryUtils.extractMongoObjectId(pkgMap);
                } else {
                    id = pkgMap.get("_id").toString();
                }

                packageMap = session.selectOne("selectPackageData", id);
                scanList = session.selectList("selectScans", packageMap.get("waybillnum").toString());
                if (scanList != null && !scanList.isEmpty()) {
                    scanTransformation(scanList, packageMap);
                }
            }

            long currentTimeMillis = System.currentTimeMillis();
            session.update("updateScan", resultantScanList);
            session.commit();

            LOG.info("Updated "+resultantScanList.size()+" --------------- in " + (System.currentTimeMillis() - currentTimeMillis)/1000);
        }
    }


    public List<HashMap<String, Object>> scanTransformation(List<HashMap<String, Object>> scanList, HashMap<String, Object> packageMap) {

        HashMap<String, Object> prevScanMap = null;
        HashMap<String, Object> firstScanMap = null;
        for (HashMap<String, Object> scanMap : scanList) {


            DateTime scanTime = null;
            if (scanMap.containsKey("scanned_date") && (scanMap.get("scanned_date") != null)) {
                scanTime = new DateTime(scanMap.get("scanned_date"));
            }

            scanMap.put("wbn", packageMap.get("waybillnum"));
            scanMap.put("is_current", false);
            scanMap.put("sdMilli", ((Long) scanTime.toDate().getTime()).intValue());
            DateTime currentTime = new DateTime();
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
            DateTime sdTime = scanTime;
            DateTime frpdTime = null;
            DateTime frddTime = null;

            if (packageMap.containsKey("pickup_ship_date") && packageMap.get("pickup_ship_date") != null) {
                pdTime = new DateTime(packageMap.get("pickup_ship_date"));
            }
            if (packageMap.containsKey("incoming_verified_date") && packageMap.get("incoming_verified_date") != null) {
                ivdTime = new DateTime(packageMap.get("incoming_verified_date"));
            }
            if (packageMap.containsKey("last_dispatch_date") && packageMap.get("last_dispatch_date") != null) {
                lddTime = new DateTime(packageMap.get("last_dispatch_date"));
            }
            if (packageMap.containsKey("first_bagging_date") && packageMap.get("first_bagging_date") != null) {
                fbdTime = new DateTime(packageMap.get("first_bagging_date"));
            }
            if (packageMap.containsKey("first_pending_date") && packageMap.get("first_pending_date") != null) {
                fpdTime = new DateTime(packageMap.get("first_pending_date"));
            }
            if (packageMap.containsKey("first_bagging_datetime_return_shipment_date") && packageMap.get("first_bagging_datetime_return_shipment_date") != null) {
                frbdTime = new DateTime(packageMap.get("first_bagging_datetime_return_shipment_date"));
            }
            if (packageMap.containsKey("package_manifest_date") && packageMap.get("package_manifest_date") != null) {
                mndTime = new DateTime(packageMap.get("package_manifest_date"));
            }
            if (packageMap.containsKey("package_pickup_at_clientwarehouse_date") && packageMap.get("package_pickup_at_clientwarehouse_date") != null) {
                cpdTime = new DateTime(packageMap.get("package_pickup_at_clientwarehouse_date"));
            }
            if (packageMap.containsKey("first_datetime_return_shipment_date") && packageMap.get("first_datetime_return_shipment_date") != null) {
                frdTime = new DateTime(packageMap.get("first_datetime_return_shipment_date"));
            }
            if (packageMap.containsKey("promised_date") && packageMap.get("promised_date") != null) {
                pddTime = new DateTime(packageMap.get("promised_date"));
            }
            if (packageMap.containsKey("first_dispatch_date") && packageMap.get("first_dispatch_date") != null) {
                fddTime = new DateTime(packageMap.get("first_dispatch_date"));
            }
            if (packageMap.containsKey("first_pending_datetime_return_shipment_date") && packageMap.get("first_pending_datetime_return_shipment_date") != null) {
                frpdTime = new DateTime(packageMap.get("first_pending_datetime_return_shipment_date"));
            }
            if (packageMap.containsKey("first_dispatch_datetime_return_shipment_date") && packageMap.get("first_dispatch_datetime_return_shipment_date") != null) {
                frpdTime = new DateTime(packageMap.get("first_dispatch_datetime_return_shipment_date"));
            }

            if (pdTime != null && mndTime != null) {
                scanMap.put("pickup_manifestreceived", pdTime.getMillis() - mndTime.getMillis());
            }
            if (pdTime != null && cpdTime != null) {
                scanMap.put("pickup_clientpickup", pdTime.getMillis() - cpdTime.getMillis());
            }
            if (pdTime != null && ivdTime != null) {
                scanMap.put("incomingverified_pickup", ivdTime.getMillis() - pdTime.getMillis());
            }
            if (fbdTime != null && ivdTime != null) {
                scanMap.put("firstbagging_incomingverified", fbdTime.getMillis() - ivdTime.getMillis());
            }
            if (fddTime != null && fpdTime != null) {
                scanMap.put("firstdispatch_firstpending", fddTime.getMillis() - fpdTime.getMillis());
            }
            if (lddTime != null && fddTime != null) {
                scanMap.put("lastdispatch_firstdispatch", lddTime.getMillis() - fddTime.getMillis());
            }
            if (frdTime != null && pdTime != null) {
                scanMap.put("firstreturn_pickup", frdTime.getMillis() - pdTime.getMillis());
            }
            if (frbdTime != null && mndTime != null) {
                scanMap.put("firstbaggingforreturn_firstreturn", frbdTime.getMillis() - frdTime.getMillis());
            }
            if (frpdTime != null && frddTime != null) {
                scanMap.put("firstpendingforreturn_firstdispatchforreturn", frpdTime.getMillis() - frddTime.getMillis());
            }
            if (pddTime != null && pdTime != null) {
                scanMap.put("promised_pickup", pddTime.getMillis() - pdTime.getMillis());
            }
            if (fddTime != null && pdTime != null) {
                scanMap.put("firstdispatch_pickup", fddTime.getMillis() - pdTime.getMillis());
            }
            if (fddTime != null && ivdTime != null) {
                scanMap.put("firstdispatch_incomingverified", fddTime.getMillis() - ivdTime.getMillis());
            }
            if (sdTime != null) {
                scanMap.put("now_currentscanned", currentTime.getMillis() - sdTime.getMillis());
            }
            if (sdTime != null && pdTime != null) {
                scanMap.put("currentscanned_pickup", sdTime.getMillis() - pdTime.getMillis());
            }
            if (pdTime != null) {
                scanMap.put("now_pickup", currentTime.getMillis() - pdTime.getMillis());
            }

            if (prevScanMap == null) {
                scanMap.put("time_in_system", 0);
                scanMap.put("time_since_last_scan", 0);
                scanMap.put("time_spent_in_facility", 0);
                scanMap.put("time_on_road", 0);
                if (packageMap.containsKey("package_pickup_types_code") && packageMap.get("package_pickup_types_code") != null && (packageMap.get("package_pickup_types_code").toString().equalsIgnoreCase("COD") || packageMap.get("package_pickup_types_code").toString().equalsIgnoreCase("Pre-paid"))) {
                    if (scanMap.containsKey("status_code") && scanMap.get("status_code") != null && scanMap.containsKey("status_type") && scanMap.get("status_type") != null) {
                        if (scanMap.get("status_code").toString().equalsIgnoreCase("In Transit") && scanMap.get("status_type").toString().equalsIgnoreCase("RT")) {
                            scanMap.put("returns_pickedup_date", scanMap.get("scanned_date"));
                            scanMap.put("returns_closure_time", 0);
                        }
                        if (scanMap.get("status_code").toString().equalsIgnoreCase("RTO") && scanMap.get("status_type").toString().equalsIgnoreCase("DL")) {
                            scanMap.put("returns_closed_date", scanMap.get("scanned_date"));
                        }

                    }
                }

                if (scanMap.containsKey("istsc_vehicle_registration_number") && scanMap.get("istsc_vehicle_registration_number") != null && scanMap.get("istsc_vehicle_registration_number") != "null") {
                    scanMap.put("field_user_user_id", scanMap.get("istsc_vehicle_registration_number").toString());
                }

                firstScanMap = scanMap;

            } else if (prevScanMap.get("scan_location_center").toString().equals(scanMap.get("scan_location_center").toString())) {
                scanMap.put("time_in_system", (Integer) scanMap.get("sdMilli") - (Integer) firstScanMap.get("sdMilli"));
                scanMap.put("time_since_last_scan", (Integer) scanMap.get("sdMilli") - (Integer) prevScanMap.get("sdMilli"));
                scanMap.put("time_spent_in_facility", (Integer) prevScanMap.get("time_spent_in_facility") + ((Integer) scanMap.get("sdMilli") - (Integer) prevScanMap.get("sdMilli")));
                scanMap.put("time_on_road", prevScanMap.get("time_on_road"));
                if (scanMap.containsKey("istsc_vehicle_registration_number") && scanMap.get("istsc_vehicle_registration_number") != null && scanMap.get("istsc_vehicle_registration_number") != "null") {
                    scanMap.put("field_user_user_id", scanMap.get("istsc_vehicle_registration_number").toString());
                } else if (prevScanMap.containsKey("field_user_user_id") && prevScanMap.get("field_user_user_id") != null) {
                    scanMap.put("field_user_user_id", prevScanMap.get("field_user_user_id"));
                }
                if (packageMap.containsKey("package_pickup_types_code") && packageMap.get("package_pickup_types_code") != null && (packageMap.get("package_pickup_types_code").toString().equalsIgnoreCase("COD") || packageMap.get("package_pickup_types_code").toString().equalsIgnoreCase("Pre-paid"))) {
                    if (scanMap.containsKey("status_code") && scanMap.get("status_code") != null && scanMap.containsKey("status_type") && scanMap.get("status_type") != null) {
                        if (scanMap.get("status_type").toString().equalsIgnoreCase("RT")) {
                            DateTime firstScanTime = new DateTime(firstScanMap.get("scanned_date"));
                            scanMap.put("returns_closure_time", scanTime.getMillis() - firstScanTime.getMillis());
                        }
                        if (scanMap.get("status_code").toString().equalsIgnoreCase("RTO") && scanMap.get("status_type").toString().equalsIgnoreCase("DL")) {
                            scanMap.put("returns_closed_date", scanMap.get("scanned_date"));
                        }

                    }
                }
            } else if (!prevScanMap.get("scan_location_center").toString().equals(scanMap.get("scan_location_center").toString())) {
                scanMap.put("time_in_system", (Integer) scanMap.get("sdMilli") - (Integer) firstScanMap.get("sdMilli"));
                scanMap.put("time_since_last_scan", (Integer) scanMap.get("sdMilli") - (Integer) prevScanMap.get("sdMilli"));
                scanMap.put("time_spent_in_facility", 0);
                scanMap.put("time_on_road", (Integer) scanMap.get("sdMilli") - (Integer) prevScanMap.get("sdMilli"));
                if (scanMap.containsKey("istsc_vehicle_registration_number") && scanMap.get("istsc_vehicle_registration_number") != null && scanMap.get("istsc_vehicle_registration_number") != "null") {
                    scanMap.put("field_user_user_id", scanMap.get("istsc_vehicle_registration_number").toString());
                } else if (prevScanMap.containsKey("field_user_user_id") && prevScanMap.get("field_user_user_id") != null) {
                    scanMap.put("field_user_user_id", prevScanMap.get("field_user_user_id"));
                }
                if (packageMap.containsKey("package_pickup_types_code") && packageMap.get("package_pickup_types_code") != null && (packageMap.get("package_pickup_types_code").toString().equalsIgnoreCase("COD") || packageMap.get("package_pickup_types_code").toString().equalsIgnoreCase("Pre-paid"))) {
                    if (scanMap.containsKey("status_code") && scanMap.get("status_code") != null && scanMap.containsKey("status_type") && scanMap.get("status_type") != null) {
                        if (scanMap.get("status_type").toString().equalsIgnoreCase("RT")) {
                            scanMap.put("returns_pickedup_date", scanMap.get("scanned_date"));
                        }
                        if (scanMap.get("status_code").toString().equalsIgnoreCase("RTO") && scanMap.get("status_type").toString().equalsIgnoreCase("DL")) {
                            scanMap.put("returns_closed_date", scanMap.get("scanned_date"));
                        }
                        DateTime previousScanTime = new DateTime(prevScanMap.get("scanned_date"));
                        scanMap.put("returns_closure_time", currentTime.getMillis() - previousScanTime.getMillis());
                    }
                }
            }
            prevScanMap = scanMap;
            resultantScanList.add(scanMap);
        }
        return resultantScanList;
    }

}