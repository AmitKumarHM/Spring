package com.bluepiit.etl.mapper.service.impl.ist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.mapper.service.ist.ISTScanInsertMapper;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

import clojure.lang.IFn.D;

public class ISTInsertScanMapperImpl<K,V> extends BaseMapperImpl<K, V, D> implements ISTScanInsertMapper<K,V,D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void insertISTScanMapper(final HashMap<K, V> insertISTScanMap, final ArrayList<HashMap<K, V>> persistData) {
				
		/******* IST Scan Table Column values retrieve from JSON Map *********/
		HashMap<K, V> connectionMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.CONNECTION, insertISTScanMap);
		V istNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.IST_NUMBER, connectionMap);
		V originCenter=delhiveryCollection.getValue((K)DelhiveryEtlKeys.ORIGIN_CENTER, connectionMap);
		V destination=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DESTINATION, connectionMap);
		V vehicleType=delhiveryCollection.getValue((K)DelhiveryEtlKeys.VEHICLE_TYPE, connectionMap);
	    V connectionId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.ID, connectionMap);
		V vehicleMode=delhiveryCollection.getValue((K)DelhiveryEtlKeys.VEHICLE_MODE, connectionMap);
		V vehicleRegistrationNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.REGISTRATION_NUMBER, connectionMap);

		HashMap<K, V> connectionScanMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.COLLECTION_SCAN, insertISTScanMap);
		HashMap<K, V> updatedDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, connectionScanMap);
		V date=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, updatedDateMap);
		
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
			V nslCode=delhiveryCollection.getValue((K)DelhiveryEtlKeys.NSL_CODE, scanIST);
			V statusRemarks=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.STATUS_REMARKS, scanIST);
			V additionalRemarks=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ADDITIONAL_REMARKS, scanIST);
			
			/******* IST Scan Table Column wise value is put in a Map *********/
			scanIST.put((K)DelhiveryEtlKeys.DB_SCAN_DATE, (V)scanDate);
			scanIST.put((K)DelhiveryEtlKeys.DB_UPDATED_DATE, (V)updatedDate);
			
			scanIST.put((K)DelhiveryEtlKeys.DB_SCAN_DATETIME, (V)scanDate);
			scanIST.put((K)DelhiveryEtlKeys.DB_UPDATED_DATETIME, (V)updatedDate);
			
			scanIST.put((K)DelhiveryEtlKeys.DB_STATUS_CODE, scanStatus);
			scanIST.put((K)DelhiveryEtlKeys.DB_SCAN_DESTINATION, scanDestination);
			scanIST.put((K)DelhiveryEtlKeys.DB_SCAN_LOCATION, scanLocation);
			scanIST.put((K)DelhiveryEtlKeys.DB_IST_NUMBER, istNumber);
			scanIST.put((K)DelhiveryEtlKeys.DB_UPDATED_BY, updatedBy);
			scanIST.put((K)DelhiveryEtlKeys.DB_ACTION_CODE, actionCode);
			scanIST.put((K)DelhiveryEtlKeys.DB_ORIGIN_CENTER, originCenter);
			
			scanIST.put((K)DelhiveryEtlKeys.DB_REGISTRATION_NUMBER, vehicleRegistrationNumber);
			scanIST.put((K)DelhiveryEtlKeys.DB_DESTINAION_CENTER, destination);
			scanIST.put((K)DelhiveryEtlKeys.DB_COLOADERS, vehicleType);
			scanIST.put((K)DelhiveryEtlKeys.DB_VEHICLE_MODE, vehicleMode);
			scanIST.put((K)DelhiveryEtlKeys.DB_CONNECTION, connectionId);
			scanIST.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME, (V)date);
			scanIST.put((K)DelhiveryEtlKeys.DB_STATUS_TYPE, statusType);
			scanIST.put((K)DelhiveryEtlKeys.DB_STATUS_REMARKS, statusRemarks);
			scanIST.put((K)DelhiveryEtlKeys.DB_ADDITIONAL_REMARKS, additionalRemarks);
			scanIST.put((K)DelhiveryEtlKeys.DB_NSL_CODE, nslCode);
			scanIST.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)(new Integer(operation)));
			persistData.add(scanIST);
	    } 
	}
	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void insertISTScanStringMapper(HashMap<K, V> insertISTScanMap, StringBuilder persistData) {
        
		/******* IST Scan Table Column values retrieve from JSON Map *********/
        HashMap<K, V> connectionMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.CONNECTION, insertISTScanMap);
		V istNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.IST_NUMBER, connectionMap);
		V originCenter=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ORIGIN_CENTER, connectionMap);
		V destination=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DESTINATION, connectionMap);
		V vehicleType=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.VEHICLE_TYPE, connectionMap);
	    V connectionId=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ID, connectionMap);
		V vehicleMode=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.VEHICLE_MODE, connectionMap);
		V vehicleRegistrationNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.REGISTRATION_NUMBER, connectionMap);

		HashMap<K, V> connectionScanMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.COLLECTION_SCAN, insertISTScanMap);
		HashMap<K, V> updatedDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, connectionScanMap);
		V date=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, updatedDateMap);
		
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
			
			/******* IST Scan Table Column wise value is put in a StringBuilder *********/
			persistData.append(scanDate.toString());
			persistData.append(updatedDate.toString());
			
			persistData.append((scanDate.toString().length()>DelhiveryEtlConstants.FIRST_INDEX)?scanDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			persistData.append((updatedDate.toString().length()>DelhiveryEtlConstants.FIRST_INDEX)?updatedDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			
			persistData.append(scanStatus.toString());
			persistData.append(scanDestination.toString());
			persistData.append(scanLocation.toString());
			persistData.append(istNumber.toString());
			persistData.append(updatedBy.toString());
			persistData.append(actionCode.toString());
			persistData.append(originCenter.toString());
			
			persistData.append(vehicleRegistrationNumber.toString());
			persistData.append(destination.toString());
			persistData.append(vehicleType.toString());
			persistData.append(vehicleMode.toString());
			persistData.append(connectionId.toString());
			persistData.append(date.toString());
			persistData.append(statusType.toString());
			persistData.append(statusRemarks.toString());
			persistData.append(additionalRemarks.toString());
			persistData.append(operation);
			persistData.append(DelhiveryEtlConstants.NEW_LINE);
	    } 
	}

}
