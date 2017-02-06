package com.bluepiit.etl.mapper.service.impl.bag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bluepiit.etl.mapper.service.bag.BagScanFactsInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryBagKeyAndColumn;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class BagScanFactsInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D> implements BagScanFactsInsertMapper<K, V, D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void insertBagScanFactsMapper(HashMap<K, V> insertISTScanMap, ArrayList<HashMap<K, V>> persistData) {
		/******* IST Scan Table Column values retrieve from JSON Map *********/
		V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertISTScanMap);
		V originCenter=delhiveryCollection.getValue((K)DelhiveryEtlKeys.ORIGIN_CENTER, insertISTScanMap);
		V destination=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DESTINATION, insertISTScanMap);
		V vehicleType=delhiveryCollection.getValue((K)DelhiveryEtlKeys.VEHICLE_TYPE, insertISTScanMap);
	    V connectionId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.ID, insertISTScanMap);
		V vehicleMode=delhiveryCollection.getValue((K)DelhiveryEtlKeys.VEHICLE_MODE, insertISTScanMap);
		V vehicleRegistrationNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.REGISTRATION_NUMBER, insertISTScanMap);
		V zone=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.ZONE, insertISTScanMap);
		 
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.SCAN_ARRAY, insertISTScanMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	HashMap<K, V> scanIST=(HashMap<K, V>)itr.next();
	    	HashMap<K, V> updatedDMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, scanIST);
	    	HashMap<K, V> scannedDMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.SCAN_DATE, scanIST);
	    	V scanDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, scannedDMap);
			V updatedDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, updatedDMap);
			V scanStatus=delhiveryCollection.getValue((K)DelhiveryEtlKeys.SCAN_STATUS, scanIST);
			V scanDestination=delhiveryCollection.getValue((K)DelhiveryEtlKeys.SCAN_DESTINATION, scanIST);
			V scanLocation=delhiveryCollection.getValue((K)DelhiveryEtlKeys.SCAN_LOCATION, scanIST);
			V updatedBy=delhiveryCollection.getValue((K)DelhiveryEtlKeys.UPDATED_BY, scanIST);
			V actionCode=delhiveryCollection.getValue((K)DelhiveryEtlKeys.ACTION_CODE, scanIST);
			V statusType=delhiveryCollection.getValue((K)DelhiveryEtlKeys.STATUS_TYPE, scanIST);
			
			V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, scanIST);
			V psIstNumber=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.PS_IST_NUMBER, scanIST);
			V pidIstNumber=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.PID_IST_NUMBER, scanIST);
			
			V nslCode=delhiveryCollection.getValue((K)DelhiveryEtlKeys.NSL_CODE, scanIST);
			V statusRemarks=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.STATUS_REMARKS, scanIST);
			V additionalRemarks=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ADDITIONAL_REMARKS, scanIST);
			
			/******* IST Scan Table Column wise value is put in a Map *********/
			scanIST.put((K)DelhiveryEtlKeys.DB_SCAN_DATE, (V)(!DelhiveryUtils.isNULL(scanDate)?scanDate.toString().substring(DelhiveryEtlConstants.FIRST_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE));
			scanIST.put((K)DelhiveryEtlKeys.DB_UPDATED_DATE, (V)(!DelhiveryUtils.isNULL(updatedDate)?updatedDate.toString().substring(DelhiveryEtlConstants.FIRST_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE));
			
			scanIST.put((K)DelhiveryEtlKeys.DB_SCAN_DATETIME, (V)scanDate);
			scanIST.put((K)DelhiveryEtlKeys.DB_UPDATED_DATETIME, (V)updatedDate);
			
			scanIST.put((K)DelhiveryEtlKeys.DB_STATUS_CODE, scanStatus);
			scanIST.put((K)DelhiveryEtlKeys.DB_SCAN_DESTINATION, scanDestination);
			scanIST.put((K)DelhiveryEtlKeys.DB_SCAN_LOCATION, scanLocation);
			scanIST.put((K)DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER, bagSealNumber);
			scanIST.put((K)DelhiveryEtlKeys.DB_UPDATED_BY, updatedBy);
			scanIST.put((K)DelhiveryEtlKeys.DB_ACTION_CODE, actionCode);
			scanIST.put((K)DelhiveryEtlKeys.DB_ORIGIN_CENTER, originCenter);
			
			scanIST.put((K)DelhiveryEtlKeys.DB_REGISTRATION_NUMBER, vehicleRegistrationNumber);
			scanIST.put((K)DelhiveryEtlKeys.DB_DESTINAION_CENTER, destination);
			scanIST.put((K)DelhiveryEtlKeys.DB_COLOADERS, vehicleType);
			scanIST.put((K)DelhiveryEtlKeys.DB_VEHICLE_MODE, vehicleMode);
			scanIST.put((K)DelhiveryEtlKeys.DB_CONNECTION, connectionId);
			
			scanIST.put((K)DelhiveryEtlKeys.DB_STATUS_TYPE, statusType);
			scanIST.put((K)DelhiveryEtlKeys.DB_STATUS_REMARKS, statusRemarks);
			scanIST.put((K)DelhiveryEtlKeys.DB_ADDITIONAL_REMARKS, additionalRemarks);
			scanIST.put((K)DelhiveryEtlKeys.DB_NSL_CODE, nslCode);
			scanIST.put((K)DelhiveryBagKeyAndColumn.DB_ZONE, zone);
			scanIST.put((K)DelhiveryBagKeyAndColumn.DB_PS_IST_NUMBER, psIstNumber);
			scanIST.put((K)DelhiveryBagKeyAndColumn.DB_PID_IST_NUMBER, pidIstNumber);
			scanIST.put((K)DelhiveryBagKeyAndColumn.DB_BAG_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
			scanIST.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
			persistData.add(scanIST);
	    } 
		
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void insertBagScanFactsStringMapper(HashMap<K, V> insertISTScanMap, StringBuilder persistData) {
		/******* IST Scan Table Column values retrieve from JSON Map *********/
		
		V bagSealNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertISTScanMap);
		V originCenter=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ORIGIN_CENTER, insertISTScanMap);
		V destination=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DESTINATION, insertISTScanMap);
		V zone=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.ZONE, insertISTScanMap);
		
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.SCAN_ARRAY, insertISTScanMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	HashMap<K, V> scanIST=(HashMap<K, V>)itr.next();
	    	HashMap<K, V> updatedDMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, scanIST);
	    	HashMap<K, V> scannedDMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.SCAN_DATE, scanIST);
	    	V scanDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, scannedDMap);
			V updatedDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, updatedDMap);
			V scanStatus=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.SCAN_STATUS, scanIST);
			V scanDestination=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.SCAN_DESTINATION, scanIST);
			V scanLocation=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.SCAN_LOCATION, scanIST);
			V updatedBy=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.UPDATED_BY, scanIST);
			V actionCode=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ACTION_CODE, scanIST);
			V statusType=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.STATUS_TYPE, scanIST);
			V statusRemarks=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.STATUS_REMARKS, scanIST);
			V additionalRemarks=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ADDITIONAL_REMARKS, scanIST);
			V psIstNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.PS_IST_NUMBER, scanIST);
			V pidIstNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.PID_IST_NUMBER, scanIST);
			V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, scanIST);
			/******* IST Scan Table Column wise value is put in a StringBuilder *********/
			persistData.append(scanDate.toString());
			persistData.append(updatedDate.toString());
			
			persistData.append((scanDate.toString().length()>DelhiveryEtlConstants.FIRST_INDEX)?scanDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			persistData.append((updatedDate.toString().length()>DelhiveryEtlConstants.FIRST_INDEX)?updatedDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			
			persistData.append(scanStatus.toString());
			persistData.append(scanDestination.toString());
			persistData.append(scanLocation.toString());
			persistData.append(bagSealNumber.toString());
			persistData.append(updatedBy.toString());
			persistData.append(actionCode.toString());
			persistData.append(originCenter.toString());
			persistData.append(destination.toString());
			persistData.append(statusType.toString());
			persistData.append(statusRemarks.toString());
			persistData.append(additionalRemarks.toString());
			persistData.append(zone.toString());
			persistData.append(psIstNumber.toString());
			persistData.append(pidIstNumber.toString());
			persistData.append(dispatchWayBillNum.toString());
			persistData.append(operation);
			persistData.append(DelhiveryEtlConstants.NEW_LINE);
	    } 
		
	}

}
