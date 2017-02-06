package com.bluepiit.etl.mapper.service.impl.dispatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bluepiit.etl.mapper.service.dispatch.DispatchPickUpInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class DispatchPickUpInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D>
		implements DispatchPickUpInsertMapper<K, V, D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchPickUpInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		HashMap<K, V> updateDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, insertDispatchMap);
		V updateDate=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DATE, updateDateMap);
		 
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.PICK_UP_REQUEST, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	 V pickUpRequest=delhiveryCollection.getValueFromArrayWithPipe(itr);
	    	 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(updateDate.toString());
			 insertString.append(pickUpRequest.toString());
			 insertString.append(operation);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
	    }
		
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchPickUpInsertMapper(HashMap<K, V> insertDispatchMap, ArrayList<HashMap<K, V>> persistList) {
		V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		HashMap<K, V> updateDateMap=delhiveryCollection.getMap((K)DelhiveryEtlKeys.UPDATED_DATE, insertDispatchMap);
		V updateDate=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DATE, updateDateMap);
		 
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.PICK_UP_REQUEST, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	 HashMap<K, V> pickUpRequestMap=new HashMap<K, V>();
	    	 V pickUpRequest=itr.next();
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_LAST_UPDATED_TIME, updateDate);
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_PICKUP_REQUEST_ID, pickUpRequest);
	    	 pickUpRequestMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
	    	 persistList.add(pickUpRequestMap);
	    }
		
	}


	

}
