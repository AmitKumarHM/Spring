package com.bluepiit.etl.mapper.service.impl.bag;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.bag.BagDispatchInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.constant.DelhiveryBagKeyAndColumn;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class BagDispatchInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D> implements BagDispatchInsertMapper<K, V, D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagDispatchInsertMapper(HashMap<K, V> insertDispatchMap, HashMap<K, V> persistList) {
		
		V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap);
		HashMap<K, V> dispatchDetailsMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.DISPATCH_DETAILS, insertDispatchMap);
		V dispatchWayBillNum=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, dispatchDetailsMap);
		persistList.put((K)DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER, bagSealNumber);
		persistList.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
		persistList.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation)); 
		
		
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagDispatchInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		
		V bagSealNumber=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap);
		HashMap<K, V> dispatchDetailsMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.DISPATCH_DETAILS, insertDispatchMap);
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, dispatchDetailsMap);
		 insertString.append(dispatchWayBillNum.toString());
		 insertString.append(bagSealNumber.toString());
		 insertString.append(operation);
		 insertString.append(DelhiveryEtlConstants.NEW_LINE);
		
		
	}

	
	
	
	
	
	
	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagDispatchUpdateMapper(HashMap<K, V> insertDispatchMap, HashMap<K, V> persistList,
			HashMap<K, V> redisMap) {
		V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap,redisMap);
		HashMap<K, V> dispatchDetailsMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.DISPATCH_DETAILS, insertDispatchMap,redisMap);
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, dispatchDetailsMap);
		persistList.put((K)DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER, bagSealNumber);
		persistList.put((K)DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM, dispatchWayBillNum);
		persistList.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation+DelhiveryEtlConstants.PIPE)); 
		
		
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void bagDispatchUpdateStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString,
			HashMap<K, V> redisMap) {
		V bagSealNumber=delhiveryCollection.getValue((K)DelhiveryEtlKeys.BAG_SEAL_NUMBER, insertDispatchMap,redisMap);
		HashMap<K, V> dispatchDetailsMap=delhiveryCollection.getMap((K)DelhiveryBagKeyAndColumn.DISPATCH_DETAILS, insertDispatchMap,redisMap);
		V dispatchWayBillNum=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, dispatchDetailsMap);
		 insertString.append(dispatchWayBillNum.toString());
		 insertString.append(bagSealNumber.toString());
		 insertString.append(operation+DelhiveryEtlConstants.PIPE);
		 insertString.append(DelhiveryEtlConstants.NEW_LINE);
		
	}

}
