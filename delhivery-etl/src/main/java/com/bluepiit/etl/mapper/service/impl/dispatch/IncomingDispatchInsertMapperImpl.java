package com.bluepiit.etl.mapper.service.impl.dispatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bluepiit.etl.mapper.service.dispatch.IncomingDispatchInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class IncomingDispatchInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D>
		implements IncomingDispatchInsertMapper<K, V, D> {



	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void incomingDispatchInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
        
		HashMap<K, V> lastUpdateDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, insertDispatchMap);
		V lastUpdateDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, lastUpdateDateMap);
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		HashMap<K,V> incomingDispatchMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.INCOMING_DISPATCH, insertDispatchMap);
		
		V otp=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.OTP, incomingDispatchMap);
		V user=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.INCOMING_USER, incomingDispatchMap);
		V remark=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.REMARK, incomingDispatchMap);
		HashMap<K, V> incomingDateDate=delhiveryCollection.getMap((K)DelhiveryEtlKeys.INCOMING_DATE, insertDispatchMap);
		V incomingDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, incomingDateDate);
		
		
		HashMap<K,V> wayBillNumMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.INCOMING_WAYBILLNUM, incomingDispatchMap);
		ArrayList<V> successList=delhiveryCollection.getList((K)DelhiveryEtlKeys.SUCCESS, wayBillNumMap);
		Iterator<V> successItr= successList.iterator();
		while(successItr.hasNext()){
			 V v=successItr.next();
			 insertString.append(DelhiveryEtlKeys.IS_WAYBILL+DelhiveryEtlConstants.PIPE);
			 insertString.append(v.toString()+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlKeys.DB_SUCCESS+DelhiveryEtlConstants.PIPE);
			 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(otp.toString());
			 insertString.append(user.toString());
			 insertString.append(remark.toString());
			 insertString.append(incomingDate.toString());
			 insertString.append(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			 insertString.append(lastUpdateDate.toString());
			 insertString.append(operation+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
		}
		
		ArrayList<V>  failedList=delhiveryCollection.getList((K)DelhiveryEtlKeys.FAILED, wayBillNumMap);
		Iterator<V> failedItr= failedList.iterator();
		while(failedItr.hasNext()){
			 V v=failedItr.next();
			 insertString.append(DelhiveryEtlKeys.IS_WAYBILL+DelhiveryEtlConstants.PIPE);
			 insertString.append(v.toString()+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlKeys.DB_FAILED+DelhiveryEtlConstants.PIPE);
			 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(otp.toString());
			 insertString.append(user.toString());
			 insertString.append(remark.toString());
			 insertString.append(incomingDate.toString());
			 insertString.append(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			 insertString.append(lastUpdateDate.toString());
			 insertString.append(operation+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
		}
		
		
		ArrayList<V>  unexpetedList=delhiveryCollection.getList((K)DelhiveryEtlKeys.UNEXPECTED, wayBillNumMap);
		Iterator<V> unexpectedItr= unexpetedList.iterator();
		while(unexpectedItr.hasNext()){
			 V v=unexpectedItr.next();
			 insertString.append(DelhiveryEtlKeys.IS_WAYBILL+DelhiveryEtlConstants.PIPE);
			 insertString.append(v.toString()+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlKeys.DB_UNEXPECTED+DelhiveryEtlConstants.PIPE);
			 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(otp.toString());
			 insertString.append(user.toString());
			 insertString.append(remark.toString());
			 insertString.append(incomingDate.toString());
			 insertString.append(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			 insertString.append(lastUpdateDate.toString());
			 insertString.append(operation+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);		
			 
		}
		
		HashMap<K,V> bagsMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.INCOMING_BAGS, incomingDispatchMap);
		ArrayList<V>  successbagList=delhiveryCollection.getList((K)DelhiveryEtlKeys.SUCCESS, bagsMap);
		Iterator<V> bagsItr= successbagList.iterator();
		while(bagsItr.hasNext()){
			 V v=bagsItr.next();
			 insertString.append(DelhiveryEtlKeys.IS_BAGS+DelhiveryEtlConstants.PIPE);
			 insertString.append(v.toString()+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlKeys.DB_SUCCESS+DelhiveryEtlConstants.PIPE);
			 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(otp.toString());
			 insertString.append(user.toString());
			 insertString.append(remark.toString());
			 insertString.append(incomingDate.toString());
			 insertString.append(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			 insertString.append(lastUpdateDate.toString());
			 insertString.append(operation+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);		
			 
		}
			
		ArrayList<V>  failedbagsList=delhiveryCollection.getList((K)DelhiveryEtlKeys.FAILED, wayBillNumMap);
		Iterator<V> failedbagsItr= failedbagsList.iterator();
		while(failedbagsItr.hasNext()){
			V v=failedbagsItr.next();
			
			 insertString.append(DelhiveryEtlKeys.IS_BAGS+DelhiveryEtlConstants.PIPE);
			 insertString.append(v.toString()+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlKeys.DB_FAILED+DelhiveryEtlConstants.PIPE);
			 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(otp.toString());
			 insertString.append(user.toString());
			 insertString.append(remark.toString());
			 insertString.append(incomingDate.toString());
			 insertString.append(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			 insertString.append(lastUpdateDate.toString());
			 insertString.append(operation);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
			
			
		}
	
		ArrayList<V>  unexpetedbagsList=delhiveryCollection.getList((K)DelhiveryEtlKeys.UNEXPECTED, wayBillNumMap);
		Iterator<V> unexpectedbagsItr= unexpetedbagsList.iterator();
		while(unexpectedbagsItr.hasNext()){
			V v=unexpectedbagsItr.next();
			 insertString.append(DelhiveryEtlKeys.IS_BAGS+DelhiveryEtlConstants.PIPE);
			 insertString.append(v.toString()+DelhiveryEtlConstants.PIPE);
			 insertString.append(DelhiveryEtlKeys.DB_UNEXPECTED+DelhiveryEtlConstants.PIPE);
			 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(otp.toString());
			 insertString.append(user.toString());
			 insertString.append(remark.toString());
			 insertString.append(incomingDate.toString());
			 insertString.append(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
			 insertString.append(lastUpdateDate.toString());
			 insertString.append(operation);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
		}
		
	}

	
	

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void incomingDispatchInsertMapper(HashMap<K, V> insertDispatchMap, ArrayList<HashMap<K, V>> arrayList) {
		
		HashMap<K, V> lastUpdateDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, insertDispatchMap);
		V lastUpdateDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, lastUpdateDateMap);
		V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		HashMap<K,V> incomingDispatchMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.INCOMING_DISPATCH, insertDispatchMap);
		
		V otp=delhiveryCollection.getValue((K)DelhiveryEtlKeys.OTP, incomingDispatchMap);
		V user=delhiveryCollection.getValue((K)DelhiveryEtlKeys.INCOMING_USER, incomingDispatchMap);
		V remark=delhiveryCollection.getValue((K)DelhiveryEtlKeys.REMARK, incomingDispatchMap);
		HashMap<K, V> incomingDateDate=delhiveryCollection.getMap((K)DelhiveryEtlKeys.INCOMING_DATE, insertDispatchMap);
		V incomingDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, incomingDateDate);
		
		
		HashMap<K,V> wayBillNumMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.INCOMING_WAYBILLNUM, incomingDispatchMap);
		ArrayList<V> successList=delhiveryCollection.getList((K)DelhiveryEtlKeys.SUCCESS, wayBillNumMap);
		Iterator<V> successItr= successList.iterator();
		while(successItr.hasNext()){
			 HashMap<K, V> successMap=new HashMap<K, V>();
			 V v=successItr.next();
			 
			 successMap.put((K)DelhiveryEtlKeys.DB_IS_WAYBILL_BAG,(V) DelhiveryEtlKeys.IS_WAYBILL);
			 successMap.put((K)DelhiveryEtlKeys.DB_IDENTIFIER_VALUE,v);
			 successMap.put((K)DelhiveryEtlKeys.DB_INCOMING_TYPE,(V)DelhiveryEtlKeys.DB_SUCCESS);
			 successMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM,dispatchWayBillNum);
			 successMap.put((K)DelhiveryEtlKeys.DB_OTP,otp);
			 successMap.put((K)DelhiveryEtlKeys.DB_LAST_INCOMING_PERFORMED_BY,user);
			 successMap.put((K)DelhiveryEtlKeys.DB_REMARK,remark);
			 successMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATETIME,incomingDate);
			 successMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATE,(V)(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX):null));
			 successMap.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME,lastUpdateDate);
			 successMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
			 arrayList.add(successMap);
		}
		
		ArrayList<V>  failedList=delhiveryCollection.getList((K)DelhiveryEtlKeys.FAILED, wayBillNumMap);
		Iterator<V> failedItr= failedList.iterator();
		while(failedItr.hasNext()){
			 HashMap<K, V> failedMap=new HashMap<K, V>();
			 V v=failedItr.next();
			 
			 failedMap.put((K)DelhiveryEtlKeys.DB_IS_WAYBILL_BAG,(V) DelhiveryEtlKeys.IS_WAYBILL);
			 failedMap.put((K)DelhiveryEtlKeys.DB_IDENTIFIER_VALUE,v);
			 failedMap.put((K)DelhiveryEtlKeys.DB_INCOMING_TYPE,(V)DelhiveryEtlKeys.DB_FAILED);
			 failedMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM,dispatchWayBillNum);
			 failedMap.put((K)DelhiveryEtlKeys.DB_OTP,otp);
			 failedMap.put((K)DelhiveryEtlKeys.DB_LAST_INCOMING_PERFORMED_BY,user);
			 failedMap.put((K)DelhiveryEtlKeys.DB_REMARK,remark);
			 failedMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATETIME,incomingDate);
			 failedMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATE,(V)(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX):null));
			 failedMap.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME,lastUpdateDate);
			 failedMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
			 arrayList.add(failedMap);
		}
		
		
		ArrayList<V>  unexpetedList=delhiveryCollection.getList((K)DelhiveryEtlKeys.UNEXPECTED, wayBillNumMap);
		Iterator<V> unexpectedItr= unexpetedList.iterator();
		while(unexpectedItr.hasNext()){
			 V v=unexpectedItr.next();
			 HashMap<K, V> unexpectedMap=new HashMap<K, V>();
			 
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_IS_WAYBILL_BAG,(V) DelhiveryEtlKeys.IS_WAYBILL);
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_IDENTIFIER_VALUE,v);
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_INCOMING_TYPE,(V)DelhiveryEtlKeys.DB_UNEXPECTED);
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM,dispatchWayBillNum);
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_OTP,otp);
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_LAST_INCOMING_PERFORMED_BY,user);
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_REMARK,remark);
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATETIME,incomingDate);
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATE,(V)(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX):null));
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME,lastUpdateDate);		
			 unexpectedMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
			 arrayList.add(unexpectedMap);
		}
		
		HashMap<K,V> bagsMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.INCOMING_BAGS, incomingDispatchMap);
		ArrayList<V>  successbagList=delhiveryCollection.getList((K)DelhiveryEtlKeys.SUCCESS, bagsMap);
		Iterator<V> bagsItr= successbagList.iterator();
		while(bagsItr.hasNext()){
			 V v=bagsItr.next();
			 HashMap<K, V> successBagMap=new HashMap<K, V>();
			 successBagMap.put((K)DelhiveryEtlKeys.DB_IS_WAYBILL_BAG,(V) DelhiveryEtlKeys.IS_BAGS);
			 successBagMap.put((K)DelhiveryEtlKeys.DB_IDENTIFIER_VALUE,v);
			 successBagMap.put((K)DelhiveryEtlKeys.DB_INCOMING_TYPE,(V)DelhiveryEtlKeys.DB_SUCCESS);
			 successBagMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM,dispatchWayBillNum);
			 successBagMap.put((K)DelhiveryEtlKeys.DB_OTP,otp);
			 successBagMap.put((K)DelhiveryEtlKeys.DB_LAST_INCOMING_PERFORMED_BY,user);
			 successBagMap.put((K)DelhiveryEtlKeys.DB_REMARK,remark);
			 successBagMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATETIME,incomingDate);
			 successBagMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATE,(V)(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX):null));
			 successBagMap.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME,lastUpdateDate);
			 successBagMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
			 arrayList.add(successBagMap);		 
		}
			
		ArrayList<V>  failedbagsList=delhiveryCollection.getList((K)DelhiveryEtlKeys.FAILED, wayBillNumMap);
		Iterator<V> failedbagsItr= failedbagsList.iterator();
		while(failedbagsItr.hasNext()){
			 V v=failedbagsItr.next();
			
			 HashMap<K, V> failedBagMap=new HashMap<K, V>();
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_IS_WAYBILL_BAG,(V) DelhiveryEtlKeys.IS_BAGS);
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_IDENTIFIER_VALUE,v);
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_INCOMING_TYPE,(V)DelhiveryEtlKeys.DB_FAILED);
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM,dispatchWayBillNum);
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_OTP,otp);
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_LAST_INCOMING_PERFORMED_BY,user);
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_REMARK,remark);
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATETIME,incomingDate);
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATE,(V)(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX):null));
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME,lastUpdateDate);	
			 failedBagMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
			 arrayList.add(failedBagMap);
		}
	
		ArrayList<V>  unexpetedbagsList=delhiveryCollection.getList((K)DelhiveryEtlKeys.UNEXPECTED, wayBillNumMap);
		Iterator<V> unexpectedbagsItr= unexpetedbagsList.iterator();
		while(unexpectedbagsItr.hasNext()){
       		 V v=unexpectedbagsItr.next();
			 
       		HashMap<K, V> unexpectedBagMap=new HashMap<K, V>();
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_IS_WAYBILL_BAG,(V) DelhiveryEtlKeys.IS_BAGS);
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_IDENTIFIER_VALUE,v);
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_INCOMING_TYPE,(V)DelhiveryEtlKeys.DB_UNEXPECTED);
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM,dispatchWayBillNum);
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_OTP,otp);
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_LAST_INCOMING_PERFORMED_BY,user);
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_REMARK,remark);
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATETIME,incomingDate);
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_INCOMING_DATE,(V)(!DelhiveryUtils.isNULL(incomingDate)?incomingDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX):null));
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME,lastUpdateDate);	
			 unexpectedBagMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
			 arrayList.add(unexpectedBagMap);
		}
		
	}


	

}
