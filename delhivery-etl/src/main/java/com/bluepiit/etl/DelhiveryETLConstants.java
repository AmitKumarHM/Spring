package com.bluepiit.etl;

import java.util.HashMap;

public class DelhiveryETLConstants {

    public static final int EXPIRY_SECONDS = 86400;

    public static final String INPUT_SPOUT = "INPUT_SPOUT";

    public static final String INPUT_JSON_BACKUP_BOLT = "INPUT_JSON_BACKUP_BOLT";
    public static final String PACKAGE_INSERT_BOLT = "PACKAGE_INSERT_BOLT";
    public static final String PACKAGE_UPDATE_BOLT = "PACKAGE_UPDATE_BOLT";
    public static final String PACKAGE_DELETE_BOLT = "PACKAGE_DELETE_BOLT";

    public static final String LOCAL_PACKAGE_UPDATE_BOLT = "LOCAL_PACKAGE_UPDATE_BOLT";
    public static final String LOCAL_SCAN_TRANSFORM_BOLT = "LOCAL_SCAN_TRANSFORM_BOLT";
    public static final String LOCAL_PACKAGE_INSERT_BOLT = "LOCAL_PACKAGE_INSERT_BOLT";
    public static final String LOCAL_SCAN_INSERT_BOLT = "LOCAL_SCAN_INSERT_BOLT";
    public static final String LOCAL_PACKAGE_DELETE_BOLT = "LOCAL_PACKAGE_DELETE_BOLT";
    
    /*****************Local IST Insert Bolt**********************/
    public static final String LOCAL_IST_INSERT_BOLT = "LOCAL_IST_INSERT_BOLT";
    public static final String LOCAL_IST_UPDATE_BOLT = "LOCAL_IST_UPDATE_BOLT";
    
    /*****************Local Package Insert Bolt**********************/
    public static final String LOCAL_DISPATCH_INSERT_BOLT = "LOCAL_DISPATCH_INSERT_BOLT";
    public static final String LOCAL_DISPATCH_UPDATE_BOLT = "LOCAL_DISPATCH_UPDATE_BOLT";
    
    /*****************Local Bag Insert Bolt**********************/
    public static final String LOCAL_BAG_INSERT_BOLT = "LOCAL_BAG_INSERT_BOLT";
    public static final String LOCAL_BAG_UPDATE_BOLT = "LOCAL_BAG_UPDATE_BOLT";

    public static final String OPERATION = "op";
    public static final String INCOMING_OBJECT = "o";
    public static final String INSERT_OPERATION = "i";
    public static final String UPDATE_OPERATION = "u";
    public static final String DELETE_OPERATION = "d";
    public static final String INCOMING_UPDATE_OBJECT = "o2";
    public static final String UPDATE_ACTION_SET = "$set";
    public static final String ID = "_id";
    public static final String COLLECTION_NAME_KEY = "ns";
    public static final String COLLECTION_PACKAGE = "delhivery_db.packages";
    public static final String DISPATCH_PACKAGE = "delhivery_db.dispatches";
    public static final String IST_PACKAGE = "delhivery_db.ists";
    public static final String BAGS_PACKAGE = "delhivery_db.bags";
    public static final String EMPTY_STRING = "";
    public static final String SCAN_KEY = "s";
    public static final String $_OID = "$oid";

    public static final String OP = "operation";
    public static final int OP_INSERT_VALUE = 0;
    public static final int OP_UPDATE_VALUE = 1;

    public static final String PACKAGE_SCANS_INSERT_SCAN_FACT = "package_scans.insertScanFact";
    public static final String PACKAGE_SCANS_UPDATE_SCAN = "package_scans.updateScan";
    public static final String PACKAGE_SCANS_SELECT_SCAN = "package_scans.selectScan";
    public static final String PACKAGE_INSERT_PACKAGE = "package.insertPackage";
  
    public static final String PACKAGE_SELECT_WAYBILLNUM = "package.selectWaybillnum";
    public static final String PACKAGE_DELETE_PACKAGE = "package.deletePackage";
    public static final String PACKAGE_SCANS_DELETE_SCAN_FACTS = "package_scans.deleteScanFacts";
    public static final String VIEW_SELECT = "package_scans.selectViewDate";
    public static final String WAY_BILL_NUM = "wbn";
    public static final String PACKAGE_DATES_LOOKUP = "package_scans.selectPackageDates";
    public static final String JSON_KEY = "json";

    public static final String BUCKET_NAME = "delhivery-staging-backup";
    public static final String TD_DATE_TIME_FORMAT_NO_SECONDS = "yyyy-MM-dd-HH";

    public static final HashMap<String, String> COLUMN_QUERY_MAPPING = new HashMap<>();
    public static final HashMap<String, HashMap<String, String>> SCAN_PACKAGE = new HashMap<>();

    public static final String DW_FIREHOSE_JSON_BACKUP = "dw-json-backup";
    public static final String DW_FIREHOSE_SCAN_FACTS = "dw-scan-03";
    public static final String DW_FIREHOSE_PKG = "dw-package-04";
    public static final String DW_FIREHOSE_PACKAGE_DATES = "dw-package-date-05";
  
    /**** Bag, IST and Dispatch Remote Bolts name for insert ****/
    public static final String IST_INSERT= "ist.insertIST";
    public static final String BAG_INSERT = "bag.insertBag";
    public static final String DISPATCH_INSERT= "dispatch.insertDispatch";
    
    /**** Bag, IST and Dispatch Remote Bolts name for update ****/
    public static final String IST_UPDATE="ist.updateIST";
    public static final String BAG_UPDATE = "bag.updateBag";
    public static final String DISPATCH_UPDATE= "dispatch.updateDispatch";
    
    
    static {
        COLUMN_QUERY_MAPPING.put("pid","package.insertPackageBag");
        COLUMN_QUERY_MAPPING.put("dispatch","package.insertPackageDispatch");
        COLUMN_QUERY_MAPPING.put("dsc","package.insertPackageItems");
        // COLUMN_QUERY_MAPPING.put("ncc","package.insertPackageNcc");
        // COLUMN_QUERY_MAPPING.put("nsl","package.insertPackageNsl");
        COLUMN_QUERY_MAPPING.put("category","package.insertPackageProductSegment");
        COLUMN_QUERY_MAPPING.put("acd","package.insertPackageResponse");
        COLUMN_QUERY_MAPPING.put("r","package.insertPackageReverseLogistics");
        //COLUMN_QUERY_MAPPING.put("snm","package.insertPackageSeller");
        COLUMN_QUERY_MAPPING.put("sbt","package.insertPackageStateBasedTax");
        COLUMN_QUERY_MAPPING.put("lp","package.insertPackageLost");
        COLUMN_QUERY_MAPPING.put("lock","package.insertPackageLocked");
        COLUMN_QUERY_MAPPING.put("rs","package.insertPackageInvoiceAmount");
        COLUMN_QUERY_MAPPING.put("cod","package.insertPackageCodAmount");
        COLUMN_QUERY_MAPPING.put("rgn","package.insertPackageRegion");
        COLUMN_QUERY_MAPPING.put("zn","package.insertPackageZone");
        COLUMN_QUERY_MAPPING.put("oc","package.insertPackageOriginCentre");
        COLUMN_QUERY_MAPPING.put("occ","package.insertPackageOriginCentreCity");
        COLUMN_QUERY_MAPPING.put("cn","package.insertPackageDestinationCentre");
        COLUMN_QUERY_MAPPING.put("cnc","package.insertPackageDestinationCentreCity");

        COLUMN_QUERY_MAPPING.put("dd","package.insertPackageDispatchDetails");
        //COLUMN_QUERY_MAPPING.put("inv","package.insertPackageInvoice") done from list;
    }

    public static final String[] S3_BACKUP_JSON = {"json"};
    public static final String[] PACKAGE_JSON_ARRAY = {"wbn", "oid", "cl", "pt", "dws.l", "dws.b", "dws.h", "dws.wt", "dws.v", "dws.mwt", "dws.u", "dws.ud.$date", "dws.ud.$datetime", "pl",
            "zn", "rgn", "itc","rs","cod","lp", "md", "u", "dws.ud.$date", "dws.ud.$datetime", "pmode", "ptype", "pupid", "sku", "prd", "pclh", "bw", "gw", "swt", "oc", "occ", "cn", "dpc", "cnc", "rcn",
            "rdpc", "rcty", "lock", "lu.$date", "lu.$datetime", "nm", "add_1", "add_2", "cty", "st", "cnt", "pin", "em", "ph_1", "ph_2", "ph_3", "aseg.lon", "aseg.lat", "aseg.pin", "aseg.mismatch",
            "aseg.loc", "aseg.sloc", "aseg.aloc", "_id", "operation"};
    public static final String[] SCAN_JSON_ARRAY = {"cl", "wbn", "zn", "rgn", "pt", "oc", "cn", "occ", "cnc", "pin", "lp", "cd.$date", "cd.$datetime",
            "od.$date", "od.$datetime", "pd.$date", "pd.$datetime", "ivd.$date", "ivd.$datetime", "fpd.$date", "fpd.$datetime", "ftd.$date", "ftd.$datetime", "ldd.$date", "ldd.$datetime", "fbd.$date", "fbd.$datetime", "rd.$date", "rd.$datetime", "fdd.$date", "fdd.$datetime", "ed.$date", "ed.$datetime", "pdd.$date", "pdd.$datetime", "cd.$date", "cd.$datetime", "mnd.$date", "mnd.$datetime", "cpd.$date", "cpd.$datetime", "ntd.$date", "ntd.$datetime", "lu.$date", "lu.$datetime", "lsd.$date", "lsd.$datetime", "adt.$date", "adt.$datetime", "idt.$date", "idt.$datetime","rdt.$date", "rdt.$datetime", "frd.$date", "frd.$datetime", "frpd.$date", "frpd.$datetime",
            "frbd.$date", "frbd.$datetime", "frdd.$date", "frdd.$datetime", "act", "nsl", "ss", "st", "sl", "pid", "ps", "dwbn", "cid", "sd.$date", "sd.$datetime", "dws.ud.$date", "dws.ud.$datetime", "u", "scan_location_hub", "scan_location_dc", "scan_location_pc", "scan_location_dpc", "scan_location_cc", "dest", "field_user_user_id", "vehicle_registration_number", "pupid", "pbs", "cld", "vh", "derived_scan_type","time_in_system", "time_since_last_scan", "time_spent_in_facility", "time_on_road", "pickup_manifestreceived", "pickup_clientpickup", "incomingverified_pickup", "firstbagging_incomingverified", "pcout_firstbagging",
            "originhubin_pcout", "originhubout_originhubin", "desthubin_originhubout", "desthubout_desthubin", "dpcin_desthubout", "dpcout_dpcin", "firstpending_dpcout", "firstdispatch_firstpending", "lastdispatch_firstdispatch", "firstreturn_pickup", "firstbaggingforreturn_firstreturn", "firstpendingforreturn_firstdispatchforreturn", "promised_pickup", "firstdispatch_pickup", "firstdispatch_incomingverified", "now_currentscanned", "currentscanned_pickup", "now_pickup",
            "returns_pickedup_date.$date", "returns_pickedup_date.$datime", "returns_closed_date.$date", "returns_closed_date.$datime", "returns_closure_time", "previous_scan_location","status_remarks","status_additional_remarks","operation"};
    public static final String[] PACKAGE_DATE_JSON_ARRAY = {"wbn", "date_type","date_value","datetime_value","operation"};
    
   
    
    
    /* Bag JSON Array*/
    public static final String[] BAG_JSON_ARRAY = {"_id.$id", "bs","cod","cn","vb","gm","vh",
            "vl","gmtot","vgm","fpd","cm","u","oc","pid","stn","rs","dd.t","zn","cd.$date","ud.$date","ud.$datetime","cd.$datetime","operation"};
    public static final String[] BAG_SCAN_FACTS = {"bs","o","d","cld","zn","s.ud.$date","s.ud.$datetime","s.sd.$date","s.sd.$datetime","s.ss","s.dest","s.sl","s.u","s.act","s.st","s.sr","s.asr","s.ps","s.pid","s.dwbn","operation"};
    public static final String[] BAG_DISPATCH_DETAILS = {"bs","dd.t","dd.dwbn","operation"};
    public static final String[] BAG_DISPATCH = {"bs","dd.dwbn","operation"};
    public static final String[] BAG_IST = {"bs","cdn","operation"};
    public static final String[] CHILD_BAG_NUMBERS = {"bs","bss","operation"};
    
    /* Dispatch JSON Array*/
    public static final String[] DISPATCH_JSON = {"_id.$id","dwbn","cu","cn","dn","rmk","mage","vn","kg","imei","rdt","du","bc","dc","eod.rt","eod.pu","eod.col","eod.can","eod.pd","eod.dv","eod.amt","eod.skm","eod.ekm","eod.exp_cod","vid","rs.cod","rs.pp","rs.pu","cd.$date","dpd.$date","ud.$date","cd.$datetime","dpd.$datetime","ud.$datetime","fu.username","fu.user_id","fu.emp_id","fu.emp_type","pod.st","operation"};
    public static final String[] CURRENCY_JSON = {"dwbn","curr.one","curr.two","curr.five","curr.ten","curr.twenty","curr.fifty","curr.hundred","curr.five_hundred","curr.thousand","operation"};
    public static final String[] DISPATCH_TYPE_JSON = {"ds","t","operation"};
    public static final String[] DISPATCH_BAG_JSON = {"bs","dwbn","operation"};
    public static final String[] DISPATCH_PACKAGE_JSON = {"wdn","dwbn","operation"};
    
    /* IST JSON Array*/
    public static final String[] IST_JSON = {"_id.$id","conn.d","conn.cld","conn.cdn","conn.o","conn.id","conn.md","conn.vh","extra.bag_counts","extra.bag_wt","extra.bag_vwt","operation"};
    public static final String[] IST_SCAN_JSON = {"_id.$id","conn.d","conn.cld","conn.cdn","conn.o","conn.id","conn.md","conn.vh","s.ud.date","s.sd.date","s.ud.datetime","s.sd.datetime","s.ss","s.dest","s.sl","s.u","s.act","s.st","s.nsl","s.sr","s.asr","operation"};
}
