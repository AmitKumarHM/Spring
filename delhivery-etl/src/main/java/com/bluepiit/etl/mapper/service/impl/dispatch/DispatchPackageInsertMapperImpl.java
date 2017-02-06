package com.bluepiit.etl.mapper.service.impl.dispatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bluepiit.etl.mapper.service.dispatch.DispatchPackageInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class DispatchPackageInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D>
		implements DispatchPackageInsertMapper<K, V, D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchPackageInsertMapper(HashMap<K, V> insertDispatchMap, ArrayList<HashMap<K, V>> persistList) {
		V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		 
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.WAYBILLNUM, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	 HashMap<K, V> wayBillMap=new HashMap<K, V>();
	    	 V wayBillNumber=itr.next();
	    	 wayBillMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
	    	 wayBillMap.put((K)DelhiveryEtlKeys.DB_WAYBILLNUM, wayBillNumber);
	    	 wayBillMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
	    	 persistList.add(wayBillMap);
	    }
		
		
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchPackageInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, insertDispatchMap);
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.WAYBILLNUM, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	 V wayBillNumber=delhiveryCollection.getValueFromArrayWithPipe(itr);
	    	 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(wayBillNumber.toString());
			 insertString.append(operation);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
	    }
		
	}

}
