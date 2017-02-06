package com.bluepiit.etl.mapper.service.impl.dispatch;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.dispatch.DispatchInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;


public class DispatcherInsertMapperImpl<K, V,D> extends BaseMapperImpl<K, V, D> implements DispatchInsertMapper<K, V,D> {
	
	
	@Override
	@SuppressWarnings({DelhiveryEtlConstants.UNCHECKED})
	public void dispatchInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		 /******* Dispatch Table Column values retrieve from JSON Map *********/
		 HashMap<K, V> mongoIdMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UNDERSCORE_ID, insertDispatchMap);
		 V mongoId=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.MONGO_DB_ID, mongoIdMap);
		 
		 V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		 V cu=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.COMPLETED_BY, insertDispatchMap);
		 V originCenter=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_ORIGIN_CENTER, insertDispatchMap);
		 V driverName=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DRIVER_NAME, insertDispatchMap);
		 V remarks=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.REMARK, insertDispatchMap);
		 V mileage=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.MILEAGE, insertDispatchMap);
		 V vehicleRegistrationNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.VEHICLE_REGISTRATION_NUMBER, insertDispatchMap);
		 V dimBookedWeightKg=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DIM_BOOKED_WEIGHT_KG, insertDispatchMap);
		 V imei=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.IMEI, insertDispatchMap);
		 V receiptDateTime=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.RECEIPT_DATETIME, insertDispatchMap);
		 V dispatchedByFeildUser=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCHED_BY_FEILD_USER, insertDispatchMap);
		 V bagCount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.BAG_COUNT, insertDispatchMap);
		 V destinationCenter=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DESTINATION_CENTER, insertDispatchMap);
		 
		 HashMap<K, V> eodMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.EOD, insertDispatchMap);
		 V returnedPackageCount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.RETURNED_PACKAGE_COUNT, eodMap);
		 V pickedUpPackageCount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.PICKEDUP_PACKAGE_COUNT, eodMap);
		 V collectedPackageCount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.COLLECTED_PACKAGE_COUNT, eodMap);
		 V cancelledPackageCount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.CANCELLED_PACKAGE_COUNT, eodMap);
		 V pendingPackageCount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.PENDING_PACKAGE_COUNT, eodMap);
		 V deliveredPackageCount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DELIVERED_PACKAGE_COUNT, eodMap);
		 V amountCollected=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.AMOUNT_COLLECTED, eodMap);
		 V startKM=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.START_KM, eodMap);
		 V endKM=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.END_KM, eodMap);
		 V expectedPackageCount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.EXPECTED_PACKAGE_COUNT, eodMap);
		 V vehicleId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.VEHICLE_ID, insertDispatchMap);
		 V codAmount=null;
		 V prepaidAmount=null;
		 V pickUpAmount=null;
		 if(!DelhiveryUtils.isNULL((delhiveryCollection.getValue((K)DelhiveryEtlKeys.AMOUNT, insertDispatchMap))) && (delhiveryCollection.getValue((K)DelhiveryEtlKeys.AMOUNT, insertDispatchMap)).getClass().equals(Double.class)){
			 codAmount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.AMOUNT, insertDispatchMap);
		 }else{
		 HashMap<K, V> amountMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.AMOUNT, insertDispatchMap);
		 codAmount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.COD_AMOUNT, amountMap);
		 prepaidAmount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.PREPAID_AMOUNT, amountMap);
		 pickUpAmount=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.PICKUP_AMOUNT, amountMap);
		 } 
		 
		 HashMap<K, V> updatedDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.COMPLETE_DATE, insertDispatchMap);
		 V completeDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, updatedDateMap);
		 
		 HashMap<K, V> dispatchDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.DISPATCH_DATE, insertDispatchMap);
		 V dispatchDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, dispatchDateMap);
		 
		 HashMap<K, V> updateDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, insertDispatchMap);
		 V updateDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, updateDateMap);
		 
		 /******* Dispatch Table Column wise value is put in a StringBuilder *********/
		 insertString.append(mongoId.toString());
		 
		 insertString.append(returnedPackageCount.toString());
		 insertString.append(pickedUpPackageCount.toString());
		 insertString.append(collectedPackageCount.toString());
		 insertString.append(pendingPackageCount.toString());
		 insertString.append(cancelledPackageCount.toString());
		 insertString.append(deliveredPackageCount.toString());
		 insertString.append(amountCollected.toString());
		 insertString.append(startKM.toString());
		 insertString.append(endKM.toString());
		 insertString.append(expectedPackageCount.toString());
		 
		 insertString.append(originCenter.toString());
		 
		 insertString.append(mileage.toString());
		 insertString.append(dimBookedWeightKg.toString());
		 
		 insertString.append(imei.toString());
		 insertString.append(receiptDateTime.toString().equals(DelhiveryEtlConstants.PIPE)?receiptDateTime.toString():receiptDateTime.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX));
		 insertString.append(receiptDateTime.toString());
		 insertString.append(dispatchedByFeildUser.toString());
		 insertString.append(bagCount.toString());
		 
		 insertString.append((dispatchDate.toString().length()>DelhiveryEtlConstants.FIRST_INDEX)?dispatchDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
		 insertString.append(dispatchDate.toString());
		 
		 insertString.append((completeDate.toString().length()>DelhiveryEtlConstants.FIRST_INDEX)?completeDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
		 insertString.append(completeDate.toString());
		 
		 insertString.append((updateDate.toString().length()>DelhiveryEtlConstants.FIRST_INDEX)?updateDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
		 insertString.append(updateDate.toString());
		 insertString.append(vehicleId.toString());
		 
		 insertString.append((!DelhiveryUtils.isNULL(codAmount))?codAmount.toString():DelhiveryEtlConstants.PIPE);
		 insertString.append((!DelhiveryUtils.isNULL(prepaidAmount))?prepaidAmount.toString():DelhiveryEtlConstants.PIPE);
		 insertString.append((!DelhiveryUtils.isNULL(pickUpAmount))?pickUpAmount.toString():DelhiveryEtlConstants.PIPE);
	
		 insertString.append(vehicleRegistrationNumber.toString());
		 insertString.append(dispatchWayBillNum.toString());
		 
		 insertString.append(cu.toString());
		 insertString.append(remarks.toString());
		 insertString.append(driverName.toString());
		 insertString.append(destinationCenter.toString());
		 insertString.append(operation);
		 insertString.append(DelhiveryEtlConstants.NEW_LINE);
	}

	
	

	
	
	
	
	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchInsertMapper(final HashMap<K, V> insertDispatchMap, final HashMap<K, V> persistData) { 
		 /******* Dispatch Table Column values retrieve from JSON Map *********/
		 HashMap<K, V> mongoIdMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UNDERSCORE_ID, insertDispatchMap);
		 V mongoId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.MONGO_DB_ID, mongoIdMap);
		 
		 V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		 V vehicleId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.VEHICLE_ID, insertDispatchMap);
		 V cu=delhiveryCollection.getValue((K)DelhiveryEtlKeys.COMPLETED_BY, insertDispatchMap);
		 V originCenter=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_ORIGIN_CENTER, insertDispatchMap);
		 V driverName=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DRIVER_NAME, insertDispatchMap);
		 V remarks=delhiveryCollection.getValue((K)DelhiveryEtlKeys.REMARK, insertDispatchMap);
		 V mileage=delhiveryCollection.getValue((K)DelhiveryEtlKeys.MILEAGE, insertDispatchMap);
		 V vehicleRegistrationNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.VEHICLE_REGISTRATION_NUMBER, insertDispatchMap);
		 V dimBookedWeightKg=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DIM_BOOKED_WEIGHT_KG, insertDispatchMap);
		 V imei=delhiveryCollection.getValue((K)DelhiveryEtlKeys.IMEI, insertDispatchMap);
		 V receiptDateTime=delhiveryCollection.getValue((K)DelhiveryEtlKeys.RECEIPT_DATETIME, insertDispatchMap);
		 V dispatchedByFeildUser=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCHED_BY_FEILD_USER, insertDispatchMap);
		 V bagCount=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_COUNT, insertDispatchMap);
		 V destinationCenter=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DESTINATION_CENTER, insertDispatchMap);
		 V type=delhiveryCollection.getValue((K)DelhiveryEtlKeys.TYPE, insertDispatchMap);
		 V dispatchStatus=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_STATUS, insertDispatchMap);
		 
		 HashMap<K, V> eodMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.EOD, insertDispatchMap);
		 V returnedPackageCount=delhiveryCollection.getValue((K)DelhiveryEtlKeys.RETURNED_PACKAGE_COUNT, eodMap);
		 V pickedUpPackageCount=delhiveryCollection.getValue((K)DelhiveryEtlKeys.PICKEDUP_PACKAGE_COUNT, eodMap);
		 V collectedPackageCount=delhiveryCollection.getValue((K)DelhiveryEtlKeys.COLLECTED_PACKAGE_COUNT, eodMap);
		 V cancelledPackageCount=delhiveryCollection.getValue((K)DelhiveryEtlKeys.CANCELLED_PACKAGE_COUNT, eodMap);
		 V pendingPackageCount=delhiveryCollection.getValue((K)DelhiveryEtlKeys.PENDING_PACKAGE_COUNT, eodMap);
		 V deliveredPackageCount=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DELIVERED_PACKAGE_COUNT, eodMap);
		 V amountCollected=delhiveryCollection.getValue((K)DelhiveryEtlKeys.AMOUNT_COLLECTED, eodMap);
		 V startKM=delhiveryCollection.getValue((K)DelhiveryEtlKeys.START_KM, eodMap);
		 V endKM=delhiveryCollection.getValue((K)DelhiveryEtlKeys.END_KM, eodMap);
		 V expectedPackageCount=delhiveryCollection.getValue((K)DelhiveryEtlKeys.EXPECTED_PACKAGE_COUNT, eodMap);
		 Double codAmount=null;
		 Double prepaidAmount=null;
		 Double pickUpAmount=null;
		 if((delhiveryCollection.getValue((K)DelhiveryEtlKeys.AMOUNT, insertDispatchMap)).getClass().equals(Double.class)){
			 codAmount=(Double)delhiveryCollection.getValue((K)DelhiveryEtlKeys.AMOUNT, insertDispatchMap);
		 }else{
		 HashMap<K, V> amountMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.AMOUNT, insertDispatchMap);
		 codAmount=(Double)delhiveryCollection.getValue((K)DelhiveryEtlKeys.COD_AMOUNT, amountMap);
		 prepaidAmount=(Double)delhiveryCollection.getValue((K)DelhiveryEtlKeys.PREPAID_AMOUNT, amountMap);
		 pickUpAmount=(Double)delhiveryCollection.getValue((K)DelhiveryEtlKeys.PICKUP_AMOUNT, amountMap);
		 } 
		 
		 
		 HashMap<K, V> updatedDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.COMPLETE_DATE, insertDispatchMap);
		 V completeDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, updatedDateMap);
		 

		 HashMap<K, V> dispatchDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.DISPATCH_DATE, insertDispatchMap);
		 V dispatchDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, dispatchDateMap);
		 
		 HashMap<K, V> updateDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, insertDispatchMap);
		 V updateDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, updateDateMap);
        
		 HashMap<K, V> fieldUserMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.FIELD_USER, insertDispatchMap);
		 V fieldUserName=delhiveryCollection.getValue((K)DelhiveryEtlKeys.USERNAME, fieldUserMap);
		 V fieldUserId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.USER_ID, fieldUserMap);
		 V fieldEmpId=delhiveryCollection.getValue((K)DelhiveryEtlKeys.EMP_ID, fieldUserMap);
		 V fieldEmpType=delhiveryCollection.getValue((K)DelhiveryEtlKeys.EMP_TYPE, fieldUserMap);
		 
		 HashMap<K,V> podMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.PODSLIIPS, insertDispatchMap);
		 V podStatus=delhiveryCollection.getValue((K)DelhiveryEtlKeys.POD_STATUS, podMap);
		 /******* Dispatch Table Column wise value is put in a Map *********/
		 persistData.put((K)DelhiveryEtlKeys.DB_MONGO_ID, mongoId);
		 persistData.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
		 
		 persistData.put((K)DelhiveryEtlKeys.DB_RETURNED_PACKAGE_COUNT, returnedPackageCount);
		 persistData.put((K)DelhiveryEtlKeys.DB_PICKEDUP_PACKAGE_COUNT, pickedUpPackageCount);
		 persistData.put((K)DelhiveryEtlKeys.DB_COLLECTED_PACKAGE_COUNT, collectedPackageCount);
		 persistData.put((K)DelhiveryEtlKeys.DB_PENDING_PACKAGE_COUNT, pendingPackageCount);
		
		 persistData.put((K)DelhiveryEtlKeys.DB_CANCELLED_PACKAGE_COUNT, cancelledPackageCount);
		 persistData.put((K)DelhiveryEtlKeys.DB_DELIVERED_PACKAGE_COUNT, deliveredPackageCount);
		 persistData.put((K)DelhiveryEtlKeys.DB_AMOUNT_COLLECTED, amountCollected);
		 persistData.put((K)DelhiveryEtlKeys.DB_EXPECTED_CODE_COUNT, expectedPackageCount);
		 
		 persistData.put((K)DelhiveryEtlKeys.DB_START_KM, startKM);
		 persistData.put((K)DelhiveryEtlKeys.DB_END_KM, endKM);
		 
		 persistData.put((K)DelhiveryEtlKeys.DB_ORIGIN_CENTER, originCenter);
		 persistData.put((K)DelhiveryEtlKeys.DB_MILEAGE, mileage);
		 
		 
		 persistData.put((K)DelhiveryEtlKeys.DB_DIM_BOOKED_WEIGHT_KG, dimBookedWeightKg);
		 persistData.put((K)DelhiveryEtlKeys.DB_IMEI, imei);
		 
		 persistData.put((K)DelhiveryEtlKeys.DB_RECEIPT_DATETIME, !(DelhiveryUtils.isNULL(receiptDateTime))?(V)receiptDateTime.toString():null);
		 persistData.put((K)DelhiveryEtlKeys.DB_RECEIPT_DATE, !(DelhiveryUtils.isNULL(receiptDateTime))?(V)receiptDateTime.toString():null);
		 persistData.put((K)DelhiveryEtlKeys.DB_COMPLETE_DATE, !(DelhiveryUtils.isNULL(completeDate))?(V)completeDate.toString():null);
		 persistData.put((K)DelhiveryEtlKeys.DB_COMPLETE_DATETIME,!(DelhiveryUtils.isNULL(completeDate))?(V)completeDate.toString():null);
		 persistData.put((K)DelhiveryEtlKeys.DB_DISPATCH_DATE, !(DelhiveryUtils.isNULL(dispatchDate))?(V)dispatchDate.toString():null);
		 persistData.put((K)DelhiveryEtlKeys.DB_DISPATCH_DATETIME, !(DelhiveryUtils.isNULL(dispatchDate))?(V)dispatchDate.toString():null);
		 persistData.put((K)DelhiveryEtlKeys.DB_UPDATED_DATE, !(DelhiveryUtils.isNULL(updateDate))?(V)updateDate.toString():null);
		 persistData.put((K)DelhiveryEtlKeys.DB_UPDATED_DATETIME, !(DelhiveryUtils.isNULL(updateDate))?(V)updateDate.toString():null);
		 persistData.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME, !(DelhiveryUtils.isNULL(updateDate))?(V)updateDate.toString():null);
		 persistData.put((K)DelhiveryEtlKeys.DB_DISPATCHED_BY_FEILD_USER, dispatchedByFeildUser);
		 persistData.put((K)DelhiveryEtlKeys.DB_BAG_COUNT, bagCount);
		 
		 persistData.put((K)DelhiveryEtlKeys.DB_COD_AMOUNT, (V)codAmount);
		 persistData.put((K)DelhiveryEtlKeys.DB_PREPAID_AMOUNT, (V)prepaidAmount);
		 persistData.put((K)DelhiveryEtlKeys.DB_PICKUP_AMOUNT, (V)pickUpAmount);
		 
	     persistData.put((K)DelhiveryEtlKeys.DB_REGISTRATION_NUMBER, vehicleRegistrationNumber);
	     persistData.put((K)DelhiveryEtlKeys.DB_DISPATCH_STATUS, dispatchStatus);
		 persistData.put((K)DelhiveryEtlKeys.DB_COMPLETED_BY,cu);
		 persistData.put((K)DelhiveryEtlKeys.DB_DISPATCH_ORIGIN_CENTER,originCenter);
		 persistData.put((K)DelhiveryEtlKeys.DB_REMARK,remarks);
		 persistData.put((K)DelhiveryEtlKeys.DB_DRIVER_NAME,driverName);
		 persistData.put((K)DelhiveryEtlKeys.DB_DESTINAION_CENTER, destinationCenter);
		 persistData.put((K)DelhiveryEtlKeys.DB_VEHICLE_ID, vehicleId);
		 persistData.put((K)DelhiveryEtlKeys.DB_POD_STATUS, podStatus);
		 persistData.put((K)DelhiveryEtlKeys.DB_TYPE, type);
		 persistData.put((K)DelhiveryEtlKeys.DB_FIELD_USERNAME, fieldUserName);
		 persistData.put((K)DelhiveryEtlKeys.DB_USER_ID, fieldUserId);
		 persistData.put((K)DelhiveryEtlKeys.DB_EMP_ID, fieldEmpId);
		 persistData.put((K)DelhiveryEtlKeys.DB_EMP_TYPE, fieldEmpType);
		 persistData.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)(new Integer(operation)));
	}


}
