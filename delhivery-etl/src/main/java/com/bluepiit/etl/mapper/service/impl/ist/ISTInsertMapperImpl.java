package com.bluepiit.etl.mapper.service.impl.ist;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.mapper.service.ist.ISTInsertMapper;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class ISTInsertMapperImpl<K,V,D> extends BaseMapperImpl<K, V, D> implements ISTInsertMapper<K,V,D>{
	
	
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void insertISTMapper(final HashMap<K,V> insertISTMap,final HashMap<K,V> persistData) {
	
		 
		 /******* IST Table Column values retrieve from JSON Map *********/
		 HashMap<K, V> mongoIdMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UNDERSCORE_ID, insertISTMap);
		 V mongoId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.MONGO_DB_ID, mongoIdMap);
		 
		 HashMap<K, V> connectionMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.CONNECTION, insertISTMap);
		 V destination=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DESTINATION, connectionMap);
		 V vehicleType=delhiveryCollection.getValue((K)DelhiveryEtlKeys.VEHICLE_TYPE, connectionMap);
		 V istNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.IST_NUMBER, connectionMap);
		 V originCenter=delhiveryCollection.getValue((K)DelhiveryEtlKeys.ORIGIN_CENTER, connectionMap);
		 V connectionId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.ID, connectionMap);
		 V vehicleMode=delhiveryCollection.getValue((K)DelhiveryEtlKeys.VEHICLE_MODE, connectionMap);
		 V vehicleRegistrationNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.REGISTRATION_NUMBER, connectionMap);
		 
		 HashMap<K, V> connectionScanMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.COLLECTION_SCAN, insertISTMap);
		 HashMap<K, V> updatedDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, connectionScanMap);
		 V date=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, updatedDateMap);
		 
		 HashMap<K, V> extraMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.EXTRA, insertISTMap);
		 V bagCount=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_COUNTS, extraMap);
		 D bagWeight=(D)delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_WEIGHT, extraMap);
		 D bagVolumetricWeight=(D)delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_VOLUMETRIC_WEIGHT, extraMap);
		 bagCount=(V)new Integer((!DelhiveryUtils.isNULL(bagCount))?(Integer.parseInt((bagCount.toString()))):null);
		 
		 /******* IST Table Column wise value is put in a Map *********/
		 persistData.put((K)DelhiveryEtlKeys.DB_MONGO_ID, mongoId);
		 persistData.put((K)DelhiveryEtlKeys.DB_DESTINAION_CENTER, destination);
		 persistData.put((K)DelhiveryEtlKeys.DB_COLOADERS, vehicleType);
		 persistData.put((K)DelhiveryEtlKeys.DB_VEHICLE_MODE, vehicleMode);
		 persistData.put((K)DelhiveryEtlKeys.DB_IST_NUMBER, istNumber);
		 persistData.put((K)DelhiveryEtlKeys.DB_BAG_COUNTS, bagCount);
		 persistData.put((K)DelhiveryEtlKeys.DB_BAG_WEIGHT, (V)bagWeight);
		 persistData.put((K)DelhiveryEtlKeys.DB_ORIGIN_CENTER, originCenter);
		 persistData.put((K)DelhiveryEtlKeys.DB_CONNECTION, connectionId);
		 persistData.put((K)DelhiveryEtlKeys.DB_BAG_VOLUMTERIC_WEIGHT, (V)bagVolumetricWeight);
		 persistData.put((K)DelhiveryEtlKeys.DB_REGISTRATION_NUMBER, vehicleRegistrationNumber);
		 persistData.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME, (V)date);
		 persistData.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)(new Integer(operation)));
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void insertISTStringMapper(HashMap<K, V> insertISTMap, StringBuilder persistData) { 
		 /******* IST Table Column values retrieve from JSON Map *********/
		 HashMap<K, V> mongoIdMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UNDERSCORE_ID, insertISTMap);
		 V mongoId=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.MONGO_DB_ID, mongoIdMap);
		 
		 HashMap<K, V> connectionMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.CONNECTION, insertISTMap);
		 V destination=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DESTINATION, connectionMap);
		 V vehicleType=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.VEHICLE_TYPE, connectionMap);
		 V istNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.IST_NUMBER, connectionMap);
		 V originCenter=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ORIGIN_CENTER, connectionMap);
		 V connectionId=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.ID, connectionMap);
		 V vehicleMode=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.VEHICLE_MODE, connectionMap);
		 V vehicleRegistrationNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.REGISTRATION_NUMBER, connectionMap);
		 
		 HashMap<K, V> connectionScanMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.COLLECTION_SCAN, insertISTMap);
		 HashMap<K, V> updatedDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, connectionScanMap);
		 V date=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, updatedDateMap);
		 
		 HashMap<K, V> extraMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.EXTRA, insertISTMap);
		 V bagCount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.BAG_COUNTS, extraMap);
		 D bagWeight=(D)delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.BAG_WEIGHT, extraMap);
		 D bagVolumetricWeight=(D)delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.BAG_VOLUMETRIC_WEIGHT, extraMap);
	
		 /******* IST Table Column wise value is put in a StringBuilder *********/
		 persistData.append(mongoId.toString());
		 persistData.append(destination.toString());
		 persistData.append(vehicleType.toString());
		 persistData.append(istNumber.toString());
		 
		 persistData.append(originCenter.toString());
		 persistData.append(connectionId.toString());
		 persistData.append(vehicleMode.toString());
		 
		 persistData.append(vehicleRegistrationNumber.toString());
		 persistData.append(date.toString());
		 persistData.append(bagCount.toString());
		 
		 persistData.append(bagWeight.toString());
		 persistData.append(bagVolumetricWeight.toString());
		 persistData.append(operation);
		 persistData.append(DelhiveryEtlConstants.NEW_LINE);
	}
}
