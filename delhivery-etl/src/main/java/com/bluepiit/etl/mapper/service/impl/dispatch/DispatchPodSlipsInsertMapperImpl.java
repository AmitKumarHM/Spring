package com.bluepiit.etl.mapper.service.impl.dispatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bluepiit.etl.mapper.service.dispatch.DispatchPodSlipsInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class DispatchPodSlipsInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D>
		implements DispatchPodSlipsInsertMapper<K, V, D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchPodslipsInsertMapper(HashMap<K, V> insertDispatchMap, ArrayList<HashMap<K, V>> persistList) {
	
		HashMap<K, V> lastUpdateDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, insertDispatchMap);
		V lastUpdateDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, lastUpdateDateMap);
		V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		HashMap<K,V> podMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.PODSLIIPS, insertDispatchMap);
	
		ArrayList<V> userArray=delhiveryCollection.getList((K)DelhiveryEtlKeys.USER, podMap);
		Iterator<V> itr1=userArray.iterator();
		
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.UPDATE_TIME, podMap);
		Iterator<V> itr2=arrayList.iterator();
		V podStatus=delhiveryCollection.getValue((K)DelhiveryEtlKeys.POD_STATUS, podMap);
		int indexCount=1;
		while(itr1.hasNext() && itr2.hasNext()){
		 HashMap<K, V> dispatchPodslipsMap=new HashMap<K, V>();
		 V user=itr1.next();
		 HashMap<K, V> updateDateMap=(HashMap<K, V>)itr2.next();
		 V updateDateTime=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, updateDateMap);
		 V updateDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, updateDateMap);
		 dispatchPodslipsMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
		 dispatchPodslipsMap.put((K)DelhiveryEtlKeys.DB_USERNAME, user);
		 dispatchPodslipsMap.put((K)DelhiveryEtlKeys.DB_UPLOADED_DATETIME, updateDateTime);
		 dispatchPodslipsMap.put((K)DelhiveryEtlKeys.DB_UPLOADED_DATE, (V)(!DelhiveryUtils.isNULL(updateDate)?updateDate.toString().substring(DelhiveryEtlConstants.START_INDEX, DelhiveryEtlConstants.TEN_INDEX):null));
		 dispatchPodslipsMap.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME, lastUpdateDate);
		 dispatchPodslipsMap.put((K)DelhiveryEtlKeys.DB_POD_STATUS, podStatus);
		 dispatchPodslipsMap.put((K)DelhiveryEtlKeys.DB_INDEX_COUNT, (V)new Integer(indexCount));
		 dispatchPodslipsMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
		 indexCount++;
		 persistList.add(dispatchPodslipsMap);
		}
		
	}


	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchPodslipsInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		
		HashMap<K, V> lastUpdateDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, insertDispatchMap);
		V lastUpdateDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, lastUpdateDateMap);
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		HashMap<K,V> podMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.PODSLIIPS, insertDispatchMap);
		
		ArrayList<V> userArray=delhiveryCollection.getList((K)DelhiveryEtlKeys.USER, podMap);
		Iterator<V> itr1=userArray.iterator();
		
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.UPDATE_TIME, podMap);
		Iterator<V> itr2=arrayList.iterator();
		//V podStatus=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.POD_STATUS, podMap);
		
		while(itr1.hasNext() && itr2.hasNext()){
		 V user=delhiveryCollection.getValueFromArrayWithPipe(itr1);
		 HashMap<K, V> updateDateMap=(HashMap<K, V>)itr2.next();
		 V updateDateTime=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, updateDateMap);
		 V updateDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, updateDateMap);
		 
		 insertString.append(dispatchWayBillNum.toString());
		 insertString.append(user.toString());
		 insertString.append(updateDateTime.toString());
		 insertString.append(!DelhiveryUtils.isNULL(updateDate)?updateDate.toString().substring(DelhiveryEtlConstants.FIRST_INDEX, DelhiveryEtlConstants.TEN_INDEX)+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
		 insertString.append(lastUpdateDate.toString());
		 insertString.append(operation);
		 insertString.append(DelhiveryEtlConstants.NEW_LINE);
		}
		
	}	

}
