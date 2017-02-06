package com.bluepiit.etl.utils.constant;

public class DelhiveryEtlKeys {

	private DelhiveryEtlKeys() {
		
	}

	/*-------------------------------------------
    |                   JSON Key Name            |
    ===========================================*/
	
	/** The Constant.*/
    public static final String MONGO_DB_ID = "$oid";
    
    /** The Constant.*/
    public static final String OUTPUT = "o";
    
	/** The Constant. */
    public static final String SCAN_ARRAY = "s";
    
    /** The Constant. */
    public static final String PICK_UP_REQUEST = "pup";
    
    /** The Constant. */
    public static final String PODSLIIPS = "pod";
    
    /** The Constant. */
    public static final String INCOMING_DISPATCH = "inc";
    
    /** The Constant. */
    public static final String INCOMING_WAYBILLNUM = "wbn";
    
    /** The Constant. */
    public static final String INCOMING_BAGS = "bs";
    
    /** The Constant. */
    public static final String UNDERSCORE_ID = "_id";
    
    /** The Constant. */
    public static final String COLLECTION_SCAN = "cs";
    
    /** The Constant. */
    public static final String OTP = "otp";
    
    /** The Constant. */
    public static final String ID = "id";

    /** The Constant. */
    public static final String CONNECTION = "conn";
    
    /** The Constant. */
    public static final String EXTRA = "extra";
    
    /** The Constant. */
    public static final String IST_NUMBER = "cdn";
    
    /** The Constant. */
    public static final String VEHICLE_TYPE = "cld";
    
    /** The Constant. */
    public static final String DESTINATION = "d";
    
    /** The Constant. */
    public static final String BAG_COUNTS = "bag_counts";
    
    /** The Constant. */
    public static final String BAG_WEIGHT = "bag_wt";
    
    /** The Constant. */
    public static final String BAG_VOLUMETRIC_WEIGHT= "bag_vwt";
    
    /** The Constant. */
    public static final String UPDATED_BY = "u";
    
    /** The Constant. */
    public static final String UPDATED_DATE = "ud";
    
    /** The Constant. */
    public static final String SCAN_DATE = "sd";
    
    /** The Constant. */
    public static final String SCAN_DESTINATION= "dest";
    
    /** The Constant. */
    public static final String SCAN_STATUS= "ss";
    
    /** The Constant. */
    public static final String SCAN_LOCATION= "sl";
    
    /** The Constant. */
    public static final String SCAN_RESULT= "rs";
    
    /** The Constant. */
    public static final String ACTION_CODE = "act";
    
    /** The Constant. */
    public static final String STATUS_TYPE = "st";
    
    /** The Constant. */
    public static final String NSL_CODE = "nsl";
    
    /** The Constant. */
    public static final String ORIGIN_CENTER = "o";
    
    /** The Constant. */
    public static final String VEHICLE_MODE = "md";
    
    /** The Constant. */
    public static final String REGISTRATION_NUMBER= "vh";
    
    /** The Constant. */
    public static final String DATE= "$date";
    
    /** The Constant. */
    public static final String INCOMING_DATE= "dt";
    
    /** The Constant. */
    public static final String SUCCESS= "s";
    
    /** The Constant. */
    public static final String FAILED= "f";
    
    /** The Constant. */
    public static final String UNEXPECTED= "u";
    
    /** The Constant. */
    public static final String ROUTE= "rou";
    
    /** The Constant. */
    public static final String LOCATION= "loc";
    
    /** The Constant. */
    public static final String ACCURANCY= "acc";    
    
    /** The Constant. */
    public static final String SPEED= "spd"; 
    
    /** The Constant. */
    public static final String ROUTE_DATETIME= "dt"; 
    
    
	/*-------------------------------------------
    |                   DB Column Name          |
    ===========================================*/
    
    
    /** The Constant. */
    public static final String DB_SUCCESS= "success";
    
    /** The Constant. */
    public static final String IS_WAYBILL= "waybill";
    
    /** The Constant. */
    public static final String IS_BAGS= "bags";
    
    /** The Constant. */
    public static final String DB_FAILED= "failed";
    
    /** The Constant. */
    public static final String DB_UNEXPECTED= "unexpected";
    
    /** The Constant. */
    public static final String DB_NSL_CODE = "nsl_code";    
    
    /** The Constant. */
    public static final String DB_IST_NUMBER = "ist_number";
    
    /** The Constant. */
    public static final String DB_VEHICLE_MODE = "vehicle_mode";

    /** The Constant. */
    public static final String DB_MONGO_ID = "mongo_id";
    
    /** The Constant. */
    public static final String DB_DESTINAION_CENTER = "destination_center";
    
    /** The Constant. */
    public static final String DB_BAG_COUNTS = "bag_count";
    
    /** The Constant. */
    public static final String DB_BAG_WEIGHT = "bag_weight";
    
    /** The Constant. */
    public static final String DB_BAG_VOLUMTERIC_WEIGHT= "bag_volumetric_weight";
    
    /** The Constant. */
    public static final String DB_UPDATED_BY = "updated_by";
    
    /** The Constant. */
    public static final String DB_ACTION_CODE = "action_code";
    
    /** The Constant. */
    public static final String DB_UPDATED_DATE = "updated_date";
    
    /** The Constant. */
    public static final String DB_UPDATED_DATETIME = "updated_datetime";
    
    /** The Constant. */
    public static final String DB_UPLOADED_DATE = "upload_date";
    
    /** The Constant. */
    public static final String DB_UPLOADED_DATETIME = "upload_datetime";
    
    /** The Constant. */
    public static final String DB_SCAN_DATE = "scanned_date";
    
    /** The Constant. */
    public static final String DB_SCAN_DATETIME = "scanned_datetime";
    
    /** The Constant. */
    public static final String DB_SCAN_DESTINATION= "scan_destination_center";
    
    /** The Constant. */
    public static final String DB_STATUS_CODE= "status_code";
    
    /** The Constant. */
    public static final String DB_SCAN_RESULT= "rs";
    
    /** The Constant. */
    public static final String DB_SCAN_LOCATION= "scan_location_center";
    
    /** The Constant. */
    public static final String DB_ORIGIN_CENTER= "origin_center";
    
    /** The Constant. */
    public static final String DB_CONNECTION= "connection";
    
    /** The Constant. */
    public static final String DB_COLOADERS= "coloaders";
    
    /** The Constant. */
    public static final String DB_REGISTRATION_NUMBER= "vehicle_registration_number";
    
    /** The Constant. */
    public static final String DB_DISPATCH_STATUS= "dispatch_status";
    
    /** The Constant. */
    public static final String DB_LAST_UPDATED_TIME= "last_updated_time";
    
    /** The Constant. */
    public static final String DB_STATUS_TYPE = "status_type";
    
    /** The Constant. */
    public static final String DB_OPERATION = "operation";
    
    
    
			    /*-------------------------------------------
			    |         Dispatch JSON Key Name            |
			    ===========================================*/
    
    /** The Constant. */
    public static final String VEHICLE_ID = "vid";
    
    /** The Constant. */
    public static final String DRIVER_NAME = "dn";
    
    /** The Constant. */
    public static final String VEHICLE_REGISTRATION_NUMBER="vn";
    
    /** The Constant. */
    public static final String DIM_BOOKED_WEIGHT_KG="kg";
    
    /** The Constant. */
    public static final String DISPATCH_ORIGIN_CENTER="cn";
    
    /** The Constant. */
    public static final String DISPATCH_DESTINATION_CENTER="dc";
    
    /** The Constant. */
    public static final String CODE_AMOUNT="cod";
    
    /** The Constant. */
    public static final String DISPATCH_WAYBILLNUM= "dwbn";
    
    /** The Constant. */
    public static final String COMPLETED_BY= "cu";
    
    /** The Constant. */
    public static final String REMARK= "rmk";
    
    /** The Constant. */
    public static final String MILEAGE= "mage";
    
    /** The Constant. */
    public static final String EOD="eod";
    
    /** The Constant. */
    public static final String COMPLETE_DATE="cd";
    
    /** The Constant. */
    public static final String DISPATCH_DATE="dpd";
    
    /** The Constant. */
    public static final String EXPECTED_PACKAGE_COUNT="exp_cod";
    
    /** The Constant. */
    public static final String RETURNED_PACKAGE_COUNT="rt";
    
    /** The Constant. */
    public static final String PICKEDUP_PACKAGE_COUNT="pu";
    
    /** The Constant. */
    public static final String COLLECTED_PACKAGE_COUNT="col";
    
    /** The Constant. */
    public static final String CANCELLED_PACKAGE_COUNT="can";
    
    /** The Constant. */
    public static final String PENDING_PACKAGE_COUNT="pd";
    
    /** The Constant. */
    public static final String DELIVERED_PACKAGE_COUNT="dv";
    
    /** The Constant. */
    public static final String AMOUNT_COLLECTED="amt";
    
    /** The Constant. */
    public static final String START_KM="skm";
    
    /** The Constant. */
    public static final String END_KM="ekm";
    
    /** The Constant. */
    public static final String IMEI="imei";
    
    /** The Constant. */
    public static final String RECEIPT_DATETIME="rdt";
    
    /** The Constant. */
    public static final String DISPATCHED_BY_FEILD_USER="du";
    
    /** The Constant. */
	public static final String BAG_COUNT = "bc";
	
	/** The Constant. */
	public static final String AMOUNT = "rs";
	
	/** The Constant. */
	public static final String COD_AMOUNT = "cod";
	
	/** The Constant. */
	public static final String PREPAID_AMOUNT = "pp";
	
	/** The Constant. */
	public static final String PICKUP_AMOUNT = "pu";
    
	 /** The Constant. */
    public static final String DESTINATION_CENTER = "dc";
    
    /** The Constant. */
    public static final String TYPE = "t";
    
    /** The Constant. */
    public static final String DISPATCH_STATUS = "ds";
    
    /** The Constant. */
    public static final String FIELD_USER = "fu";
    
    /** The Constant. */
    public static final String USERNAME = "username";
    
    /** The Constant. */
    public static final String USER_ID = "user_id";
    
    /** The Constant. */
    public static final String EMP_ID = "emp_id";
    
    /** The Constant. */
    public static final String EMP_TYPE = "emp_type";
    
    /** The Constant. */
	public static final String STATUS_REMARKS = "sr";
	
	/** The Constant. */
	public static final String ADDITIONAL_REMARKS = "asr";
   
    /** The Constant. */
	public static final String CURRENCY = "curr";
	
	/** The Constant. */
	public static final String ONE = "one";
    
	/** The Constant. */
	public static final String TWO = "two";
	
	/** The Constant. */
	public static final String FIVE = "five";
	
	/** The Constant. */
	public static final String TEN = "ten";
		
	/** The Constant. */
	public static final String TWENTY = "twenty";
	    
    /** The Constant. */
	public static final String THOUSAND = "thousand";
	
	/** The Constant. */
	public static final String FIFTY = "fifty";
	
	/** The Constant. */
	public static final String FIVE_HUNDRED = "five_hundred";
	
	/** The Constant. */
	public static final String HUNDRED = "hundred";
	
	/** The Constant. */
	public static final String USER = "u";
	
	/** The Constant. */
	public static final String INCOMING_USER = "user";
	
	
	/** The Constant. */
	public static final String UPDATE_TIME = "pt";
	
	/** The Constant. */
	public static final String POD_STATUS = "st";
	    
	
	/** The Constant. */
    public static final String WAYBILLNUM= "wbn"; 
    
    /** The Constant. */
    public static final String BAG_SEAL_NUMBER= "bs";
			/*-------------------------------------------
			|                   Dispatch DB Columns     |
			===========================================*/
    
 
    
    /** The Constant. */
    public static final String DB_FIELD_USERNAME = "field_username";
    
    /** The Constant. */
    public static final String DB_USER_ID = "field_user_user_id";
    
    /** The Constant. */
    public static final String DB_EMP_ID = "field_user_employee_id";
    
    /** The Constant. */
    public static final String DB_EMP_TYPE = "field_user_employee_type";
	
	
    /** The Constant. */
    public static final String DB_VEHICLE_ID = "vehicle_id";
    
    /** The Constant. */
    public static final String DB_PICKUP_REQUEST_ID = "pickuprequest_id";
    
    /** The Constant. */
    public static final String DB_DRIVER_NAME = "drivername";
    
    /** The Constant. */
    public static final String DB_VEHICLE_REGISTRATION_NUMBER="vehicle_registration_number";
    
    /** The Constant. */
    public static final String DB_DISPATCH_ORIGIN_CENTER="origin_center";
    
    /** The Constant. */
    public static final String DB_DISPATCH_DESTINATION_CENTER="destination_center";
    
    /** The Constant. */
    public static final String DB_CODE_AMOUNT="cod_amount";
   
    /** The Constant. */
    public static final String DB_DISPATCH_WAY_BILL_NUM = "dispatch_waybillnum";
    
    /** The Constant. */
    public static final String DB_DISPATCH_TYPE = "dispatch_type";
    
    /** The Constant. */
    public static final String DB_BAG_SEAL_NUMBER= "bag_seal_number";
    
    /** The Constant. */
    public static final String DB_CHILED_BAG_SEAL_NUMBER= "child_bag_seal_number";
    
    /** The Constant. */
    public static final String DB_COMPLETED_BY = "completed_by";
    
    /** The Constant. */
    public static final String DB_REMARK= "remarks";
    
    /** The Constant. */
    public static final String DB_USERNAME= "user_name";
    
    /** The Constant. */
    public static final String DB_INCOMING_DATE= "incoming_date";  
    
    /** The Constant. */
    public static final String DB_INCOMING_DATETIME= "incoming_datetime"; 
    
    /** The Constant. */
    public static final String DB_IS_WAYBILL_BAG= "is_waybill_bag"; 
    
    /** The Constant. */
    public static final String DB_INCOMING_TYPE= "incoming_type"; 
    
    /** The Constant. */
    public static final String DB_IDENTIFIER_VALUE= "identifier_value"; 
    
    /** The Constant. */
    public static final String DB_OTP= "opt"; 
    
    /** The Constant. */
    public static final String DB_LAST_INCOMING_PERFORMED_BY= "last_incoming_performed_by"; 
    
    /** The Constant. */
    public static final String DB_EXPECTED_CODE_COUNT="eod_expected_cod_amount";
    
    /** The Constant. */
    public static final String DB_RETURNED_PACKAGE_COUNT="eod_returned_package_count";
    
    /** The Constant. */
    public static final String DB_PICKEDUP_PACKAGE_COUNT="eod_pickedup_package_count";
    
    /** The Constant. */
    public static final String DB_COLLECTED_PACKAGE_COUNT="eod_collected_package_count";
    
    /** The Constant. */
    public static final String DB_CANCELLED_PACKAGE_COUNT="eod_cancelled_package_count";
    
    /** The Constant. */
    public static final String DB_PENDING_PACKAGE_COUNT="eod_pending_package_count";
    
    /** The Constant. */
    public static final String DB_DELIVERED_PACKAGE_COUNT="eod_delivered_package_count";
    
    /** The Constant. */
    public static final String DB_AMOUNT_COLLECTED="eod_amount_collected";
    
    /** The Constant. */
    public static final String DB_START_KM="eod_start_km";
    
    /** The Constant. */
    public static final String DB_END_KM="eod_end_km";
    
    /** The Constant. */
    public static final String DB_COMPLETE_DATE="complete_date";
    
    /** The Constant. */
    public static final String DB_COMPLETE_DATETIME="complete_datetime";
    
    /** The Constant. */
    public static final String DB_DISPATCH_DATE="dispatch_date";
    
    /** The Constant. */
    public static final String DB_DISPATCH_DATETIME="dispatch_datetime";
    
    /** The Constant. */
    public static final String DB_MILEAGE= "mileage";
    
    /** The Constant. */
    public static final String DB_DIM_BOOKED_WEIGHT_KG="dim_booked_weight_kg";
    
    /** The Constant. */
    public static final String DB_IMEI="field_user_imei_code";
    
    /** The Constant. */
    public static final String DB_RECEIPT_DATETIME="receipt_datetime";
    
    /** The Constant. */
    public static final String DB_RECEIPT_DATE="receipt_date";
    
    /** The Constant. */
    public static final String DB_DISPATCHED_BY_FEILD_USER="dispatched_by_field_user";

    /** The Constant. */
	public static final String DB_BAG_COUNT = "bagcount";
	
	/** The Constant. */
	public static final String DB_COD_AMOUNT = "cod_amount";
	
	/** The Constant. */
	public static final String DB_PREPAID_AMOUNT = "prepaid_amount";
	
	/** The Constant. */
	public static final String DB_PICKUP_AMOUNT = "pickup_amount";
    
	/** The Constant. */
	public static final String DB_STATUS_REMARKS = "status_remarks";
	
	/** The Constant. */
	public static final String DB_ADDITIONAL_REMARKS = "status_additional_remarks";
	
	/** The Constant. */
	public static final String DB_ONE = "dispatch_curr_count_one";
    
	/** The Constant. */
	public static final String DB_TWO = "dispatch_curr_count_two";
	
	/** The Constant. */
	public static final String DB_FIVE = "dispatch_curr_count_five";
	
	/** The Constant. */
	public static final String DB_TEN = "dispatch_curr_count_ten";
		
	/** The Constant. */
	public static final String DB_TWENTY = "dispatch_curr_count_twenty";
	    
    /** The Constant. */
	public static final String DB_THOUSAND = "dispatch_curr_count_thousand";
	
	/** The Constant. */
	public static final String DB_FIFTY = "dispatch_curr_count_fifty";
	
	/** The Constant. */
	public static final String DB_FIVE_HUNDRED = "dispatch_curr_count_five_hundred";
	
	/** The Constant. */
	public static final String DB_HUNDRED = "dispatch_curr_count_hundred";     
	
	/** The Constant. */
	public static final String DB_POD_STATUS = "pod_status";
	
	/** The Constant. */
	public static final String DB_TYPE = "type";
	    
	/** The Constant. */
    public static final String DB_LOCATION_LONGITUTE= "longitude";
    
    /** The Constant. */
    public static final String DB_LOCATION_LATITUTE= "latitude";
    
    /** The Constant. */
    public static final String DB_ACCURANCY= "accuracy";    
    
    /** The Constant. */
    public static final String DB_SPEED= "speed"; 
    
    /** The Constant. */
    public static final String DB_WAYBILLNUM= "waybillnum"; 
    
    /** The Constant. */
    public static final String DB_ROUTE_DATE= "route_date";
    
    /** The Constant. */
    public static final String DB_ROUTE_DATETIME= "route_datetime";
    
    /** The Constant. */
    public static final String DB_INDEX_COUNT= "index_cnt";
}
