package com.bluepiit.etl.mapper.service.impl.dispatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bluepiit.etl.mapper.service.dispatch.DispatchBagInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class DispatchBagInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D> implements DispatchBagInsertMapper<K, V, D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchBagInsertMapper(HashMap<K, V> insertDispatchMap, ArrayList<HashMap<K, V>> persistList) {
		V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
				 
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.INCOMING_BAGS, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	 HashMap<K, V> bagMap=new HashMap<K, V>();
	    	 V bagSealNumber=itr.next();
	    	 bagMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
	    	 bagMap.put((K)DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER, bagSealNumber);
	    	 bagMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)(new Integer(operation)));
	    	 persistList.add(bagMap);
	    }
		
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchbagInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.INCOMING_BAGS, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	 V bagSealNumber=delhiveryCollection.getValueFromArrayWithPipe(itr);
	    	 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(bagSealNumber.toString());
			 insertString.append(operation);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
	    }		
		
	}

}
