package com.bluepiit.etl.mapper.service.impl.bag;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.bag.BagDispatchDetailsInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.constant.DelhiveryBagKeyAndColumn;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class BagDispatchDetailsInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D>
		implements BagDispatchDetailsInsertMapper<K, V, D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagDispatchDetailsInsertMapper(HashMap<K, V> insertDispatchMap, HashMap<K, V> persistList) {
		/*V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap); 
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.SCAN_ARRAY, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	 HashMap<K, V> wayBillMap=new HashMap<K, V>();
	    	 HashMap<K, V> bagDispatchMap=(HashMap<K, V>)itr.next();
	    	 V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, bagDispatchMap);
	    	 V dispatchType=delhiveryCollection.getValue((K)DelhiveryEtlKeys.TYPE, bagDispatchMap);
	    	 wayBillMap.put((K)DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER, bagSealNumber);
	    	 wayBillMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
	    	 wayBillMap.put((K)DelhiveryEtlKeys.DB_DISPATCH_TYPE, dispatchType);
	    	 wayBillMap.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation+DelhiveryEtlConstants.PIPE));
	    	 persistList.add(wayBillMap);
	    }*/
		
		V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap);
		HashMap<K, V> dispatchDetailsMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.DISPATCH_DETAILS, insertDispatchMap);
		V type=delhiveryCollection.getValue((K)DelhiveryBagKeyAndColumn.TYPE, dispatchDetailsMap);
		V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, dispatchDetailsMap);
		persistList.put((K)DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER, bagSealNumber);
		persistList.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
		persistList.put((K)DelhiveryEtlKeys.DB_DISPATCH_TYPE, type);
		persistList.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation)); 
		
		
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagDispatchDetailsInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		/*V bagSealNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap);
		ArrayList<V> arrayList=delhiveryCollection.getList((K)DelhiveryEtlKeys.SCAN_ARRAY, insertDispatchMap);
		Iterator<V> itr=arrayList.iterator();
	    while(itr.hasNext()){
	    	 HashMap<K, V> bagDispatchMap=(HashMap<K, V>)itr.next();
	    	 V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, bagDispatchMap);
	    	 V dispatchType=delhiveryCollection.getValue((K)DelhiveryEtlKeys.TYPE, bagDispatchMap);
	    	 insertString.append(dispatchWayBillNum.toString());
			 insertString.append(bagSealNumber.toString());
			 insertString.append(dispatchType.toString());
			 insertString.append(DelhiveryEtlKeys.DB_OPERATION);
			 insertString.append(DelhiveryEtlConstants.NEW_LINE);
	    }*/
		
		V bagSealNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap);
		HashMap<K, V> dispatchDetailsMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.DISPATCH_DETAILS, insertDispatchMap);
		V type=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.TYPE, dispatchDetailsMap);
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, dispatchDetailsMap);
		 insertString.append(dispatchWayBillNum.toString());
		 insertString.append(bagSealNumber.toString());
		 insertString.append(type.toString());
		 insertString.append(operation);
	     insertString.append(DelhiveryEtlConstants.NEW_LINE);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagDispatchDetailsUpdateMapper(HashMap<K, V> insertDispatchMap, HashMap<K, V> persistList,
			HashMap<K, V> redisDispatchMap) {
		V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap,redisDispatchMap);
		HashMap<K, V> dispatchDetailsMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.DISPATCH_DETAILS, insertDispatchMap,redisDispatchMap);
		V type=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.TYPE, dispatchDetailsMap);
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, dispatchDetailsMap);
		persistList.put((K)DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER, bagSealNumber);
		persistList.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
		persistList.put((K)DelhiveryEtlKeys.DB_DISPATCH_TYPE, type);
		persistList.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation+DelhiveryEtlConstants.PIPE)); 
		
		
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagDispatchDetailsUpdateStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString,
			HashMap<K, V> redisDispatchMap) {
		V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap,redisDispatchMap);
		HashMap<K, V> dispatchDetailsMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.DISPATCH_DETAILS, insertDispatchMap,redisDispatchMap);
		V type=delhiveryCollection.getValueWithPipe((K)DelhiveryBagKeyAndColumn.TYPE, dispatchDetailsMap);
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, dispatchDetailsMap);
		 insertString.append(dispatchWayBillNum.toString());
		 insertString.append(bagSealNumber.toString());
		 insertString.append(type.toString());
		 insertString.append(DelhiveryEtlKeys.DB_OPERATION);
		 insertString.append(DelhiveryEtlConstants.NEW_LINE);
		
	}

}
