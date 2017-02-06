package com.bluepiit.etl.utils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClient;
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest;
import com.amazonaws.services.kinesisfirehose.model.Record;
import com.bluepiit.etl.DelhiveryETLConstants;

/**
 * Created by Potential on 15/01/16.
 */
public class DelhiveryUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DelhiveryUtils.class);

    static String[] PACKAGE_ARRAY = {"cl", "wbn", "zn", "rgn", "pt", "oc", "cn", "occ", "cnc", "od", "od_time", "pd", "pd_time", "ivd", "ivd_time", "fpd", "fpd_time",
            "ftd", "ftd_time", "ldd", "ldd_time", "fbd", "fbd_time", "rd", "rd_time", "fdd", "fdd_time", "ed", "ed_time", "pdd", "pdd_time", "cd", "cd_time", "mnd", "mnd_time", "cpd", "cpd_time",
            "ntd", "ntd_time", "lu", "lu_time", "lsd", "lsd_time", "adt", "adt_time", "frd", "frd_time", "frpd", "frpd_time", "frbd", "frbd_time", "frdd", "frdd_time"};

    static String[] SCAN_ARRAY = {"client_name", "waybillnum","zone","region","package_pickup_types_code","origin_center","destination_center",
            "origin_center_city","dest_center_city","consignee_pincode","lost_package"};

    @SuppressWarnings("unchecked")
    public static void parseDate(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof ArrayList) {
                for (int i = 0; i < ((ArrayList) entry.getValue()).size(); i++) {
                    if (((ArrayList) entry.getValue()).get(i) instanceof Map) {
                        parseDate(((Map) ((ArrayList) entry.getValue()).get(i)));
                    }
                }
            } else if (entry.getValue() instanceof Map) {
                if (((Map) entry.getValue()).containsKey("$date") && ((Map) entry.getValue()).get("$date").toString().contains("T")) {
                    ((Map) entry.getValue()).put("$date", new DateTime(((Map) entry.getValue()).get("$date")).getMillis());
                }
                parseDate((Map<String, Object>) entry.getValue());
            }
        }
    }

    public static String concatData(int counter, ArrayList<String> data) {
        if (!data.isEmpty()) {
            String result = "";
            for (int i = counter; i < data.size(); i++) {
                result = result + data.get(i);
            }
            return result;
        }
        return DelhiveryETLConstants.EMPTY_STRING;
    }

    @SuppressWarnings("unchecked")
    public static String extractMongoObjectId(HashMap<String, Object> map) {
        String mongoId;
        HashMap<String, Object> incomingObject;
        if (map.containsKey(DelhiveryETLConstants.INCOMING_UPDATE_OBJECT)) {
            incomingObject = (HashMap) map.get(DelhiveryETLConstants.INCOMING_UPDATE_OBJECT);
        } else {
            incomingObject = (HashMap) map.get(DelhiveryETLConstants.INCOMING_OBJECT);
        }
        incomingObject = (HashMap) incomingObject.get(DelhiveryETLConstants.ID);
        mongoId = (String) incomingObject.get(DelhiveryETLConstants.$_OID);
        return mongoId;
    }

    public static HashMap<String, Object> enrichMap(HashMap<String, Object> incomingMap, HashMap<String, Object> scanMap, boolean pkg) {
        if (pkg) {
            for (String aPACKAGE_ARRAY : PACKAGE_ARRAY) {
                incomingMap.put(aPACKAGE_ARRAY, scanMap.get(aPACKAGE_ARRAY));
            }
        } else {
            for (String aSCAN_ARRAY : SCAN_ARRAY) {
                incomingMap.put(aSCAN_ARRAY, scanMap.get(aSCAN_ARRAY));
            }
        }
        return incomingMap;
    }

    public static void populateDateTypes(HashMap<String, String> dateTypes) {
        dateTypes.put("od", "order_datetime");
        dateTypes.put("pd", "pickup_ship_datetime");
        dateTypes.put("ivd", "incoming_verified_datetime");
        dateTypes.put("fpd", "first_pending_datetime");
        dateTypes.put("ftd", "first_trial_datetime");
        dateTypes.put("ldd", "last_dispatch_datetime");
        dateTypes.put("fbd", "first_bagging_date");
        dateTypes.put("rd", "returned_datetime");
        dateTypes.put("fdd", "first_dispatch_datetime");
        dateTypes.put("pdd", "promised_datetime");
        dateTypes.put("mnd", "package_manifest_datetime");
        dateTypes.put("cpd", "package_pickup_at_clientwarehouse_datetime");
        dateTypes.put("ntd", "next_trial_datetime");
        dateTypes.put("lu", "last_updated_datetime");
        dateTypes.put("lsd", "last_scan_datetime");
        dateTypes.put("adt", "action_datetime");
        dateTypes.put("idt", "invoice_datetime");
        dateTypes.put("rdt", "remittance_datetime");
        dateTypes.put("frd", "first_datetime_return_shipment");
        dateTypes.put("frpd", "first_pending_datetime_return_shipment");
        dateTypes.put("frbd", "first_bagging_datetime_return_shipment");
        dateTypes.put("frdd", "first_dispatch_datetime_return_shipment");
        dateTypes.put("pud", "pkg_last_updated_datetime");
        dateTypes.put("ud", "updated_datetime");
        dateTypes.put("cd", "creation_datetime");
        dateTypes.put("ed", "expected_datetime");
    }

    public static void populatePackageSurroundingTables(HashMap<String, Object> packageMap, HashMap<String, String> dateTypes, HashMap<String, ArrayList<HashMap>> dateHashMapList) {

        // populate the surrounding table for Date Fields.
        for (String key : dateTypes.keySet()) {
            HashMap date_value = (HashMap) packageMap.get(key);
            DateTimeFormatter dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
            DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

            if (date_value != null) {
                DateTime $date = new DateTime(date_value.get("$date"));
                HashMap<String, Object> parameter = new HashMap<>();
                parameter.put("wbn", packageMap.get("wbn"));
                parameter.put("date_type", dateTypes.get(key));
                parameter.put("date_value", dateFormat.print($date.withZone(DateTimeZone.UTC).getMillis()));
                parameter.put("datetime_value", dateTimeFormat.print($date.withZone(DateTimeZone.UTC).getMillis()));
                ArrayList<HashMap> hashMaps = dateHashMapList.get(key);
                ArrayList<HashMap> hashMapList;
                if (hashMaps == null) {
                    hashMapList = new ArrayList<>();
                    hashMapList.add(parameter);
                    dateHashMapList.put(key, hashMapList);
                } else {
                    hashMaps.add(parameter);
                    dateHashMapList.put(key, hashMaps);
                }

            } else {
                HashMap packageDateObject = (HashMap) packageMap.get("date");
                if (packageDateObject == null) return;
                HashMap x1 = (HashMap) packageDateObject.get(key);
                // Format for input
                if (x1 != null) {
                    DateTime $date = new DateTime(x1.get("$date"));

                    HashMap<String, Object> parameter = new HashMap<>();
                    parameter.put("wbn", packageMap.get("wbn"));
                    parameter.put("date_type", dateTypes.get(key));
                    parameter.put("date_value", dateFormat.print($date.withZone(DateTimeZone.UTC).getMillis()));
                    parameter.put("datetime_value", dateTimeFormat.print($date.withZone(DateTimeZone.UTC).getMillis()));
                    ArrayList<HashMap> hashMaps = dateHashMapList.get(key);
                    ArrayList<HashMap> hashMapList;
                    if (hashMaps == null) {
                        hashMapList = new ArrayList<>();
                        hashMapList.add(parameter);
                        dateHashMapList.put(key, hashMapList);
                    } else {
                        hashMaps.add(parameter);
                        dateHashMapList.put(key, hashMaps);
                    }

                }
            }
        }
    }

    public static void putRecordToKinesisFirehose(String deliveryStreamName, ByteBuffer wrap) {
        AmazonKinesisFirehoseClient fireHoseClient = new AmazonKinesisFirehoseClient(new DefaultAWSCredentialsProviderChain());
        fireHoseClient.setRegion(Region.getRegion(Regions.EU_WEST_1));
        PutRecordRequest putRecordRequest = new PutRecordRequest();
        Record record = new Record();
        putRecordRequest.setDeliveryStreamName(deliveryStreamName);
        record.setData(wrap);
        putRecordRequest.setRecord(record);
        fireHoseClient.putRecord(putRecordRequest);
    }

    public static String stripPipeAndNewLine(String elementToBeStripped) {
        if (elementToBeStripped != null) {
            if (elementToBeStripped.contains("|")) {
                elementToBeStripped = elementToBeStripped.replaceAll("\\|", "");
            }
            if (elementToBeStripped.contains("\\n")) {
                elementToBeStripped = elementToBeStripped.replaceAll("\\\\n", "");
                stripPipeAndNewLine(elementToBeStripped);
            }
        }
        return elementToBeStripped;
    }

    @SuppressWarnings("unchecked")
    public static void populateAddressAndPhoneDetails(HashMap<String, Object> packageMap) {
        ArrayList<String> addressList;
        ArrayList<String> phoneList;
        addressList = ((ArrayList) packageMap.get("add"));
        if (addressList != null && !addressList.isEmpty()) {
            String addressFirstElement = stripPipeAndNewLine(addressList.get(0));
            packageMap.put("add_1", addressFirstElement);
            String add_2 = "";
            for (int i = 1; i < addressList.size(); i++) {
                add_2 = add_2 + addressList.get(i);
            }
            add_2 = stripPipeAndNewLine(add_2);
            packageMap.put("add_2", add_2);
        }
        phoneList = ((ArrayList) packageMap.get("ph"));
        if (phoneList != null && !phoneList.isEmpty()) {
            packageMap.put("ph_1", stripPipeAndNewLine(phoneList.get(0)));
            String ph_3 = "";
            if (phoneList.size() > 1) {
                packageMap.put("ph_2", stripPipeAndNewLine(phoneList.get(1)));
                if (phoneList.size() > 2) {
                    for (int i = 2; i < phoneList.size(); i++) {
                        ph_3 = ph_3 + phoneList.get(i);
                    }
                    packageMap.put("ph_3", stripPipeAndNewLine(ph_3));
                }
            }
        }
    }

    public static String prepareDataForKinesis(HashMap<String, Object> packageMap, String[] columnArray) {
        String entries = "";
        String date = "", datetime="";
        for (String key : columnArray) {
            int length = key.split("\\.").length;
            String[] nestedKey = key.split("\\.");
            if(length>0 && length==1){
                if (!packageMap.containsKey(nestedKey[0]))
                    entries = entries.concat("|");
                else {
                    if (packageMap.get(key) != null) {
                        entries = entries.concat(packageMap.get(key).toString()).concat("|");
                    } else {
                        entries = entries.concat("|");
                    }
                }
            }
            else if(length>0){
                Object test = nestedMap(packageMap, nestedKey, 0);
                if(test!= null){
                    if(nestedKey[length-1].equalsIgnoreCase("$date")){
                        date = convertLongToDate(test.toString());
                        entries = entries.concat(date).concat("|");
                    }
                    else if(nestedKey[length-1].equalsIgnoreCase("$datetime")){
                        datetime = convertLongToDatetime(test.toString());
                        entries = entries.concat(datetime).concat("|");
                    }
                    else
                        entries=entries.concat(test.toString()).concat("|");
                }
                else
                    entries=entries.concat("|");
            }
        }
        // Removing last pipe.
        entries = entries.substring(0, entries.length() - 1).concat("\n");
        return entries;
    }

    @SuppressWarnings("unchecked")
    public static Object nestedMap(Map<String,Object>map,String[] keyTest,int key){
        Object object = null;
        if(map.containsKey(keyTest[key]) && (map.get(keyTest[key]) instanceof Map)){
            Map<String,Object> innerMap= (Map<String, Object>)map.get(keyTest[key]);
            object = nestedMap(innerMap, keyTest, ++key);
        }
        else if(map.containsKey(keyTest[key]))
            object = map.get(keyTest[key]);
        else if(!map.containsKey(keyTest[key]) && keyTest[key].equalsIgnoreCase("$datetime"))
            object = map.get("$date");
        return object;
    }

    public static String convertLongToDate(String value){

        DateTime date = new DateTime(Long.parseLong(value)).withZone(DateTimeZone.UTC);
        DateTimeFormatter dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dateFormat.print(date);
    }

    public static String convertLongToDatetime(String value){

        DateTime datetime = new DateTime(Long.parseLong(value)).withZone(DateTimeZone.UTC);
        DateTimeFormatter dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        return dateFormat.print(datetime);
    }
    
    public static boolean isNULL(Object obj){
		return null==obj?true:false;
	}
}
