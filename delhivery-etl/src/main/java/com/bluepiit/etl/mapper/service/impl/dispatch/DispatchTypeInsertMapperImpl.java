package com.bluepiit.etl.mapper.service.impl.dispatch;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.dispatch.DispatchTypeInsertMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class DispatchTypeInsertMapperImpl<K, V, D> extends BaseMapperImpl<K, V, D> implements DispatchTypeInsertMapper<K, V, D> {

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchTypeInsertMapper(HashMap<K, V> insertDispatchMap, HashMap<K, V> arrayList) {
		 V type=delhiveryCollection.getValue((K)DelhiveryEtlKeys.TYPE, insertDispatchMap);
		 V dispatchStatus=delhiveryCollection.getValue((K)DelhiveryEtlKeys.DISPATCH_STATUS, insertDispatchMap);
		 arrayList.put((K)DelhiveryEtlKeys.DB_DISPATCH_STATUS, dispatchStatus);
		 arrayList.put((K)DelhiveryEtlKeys.DB_TYPE, type);
		 arrayList.put((K)DelhiveryEtlKeys.DB_OPERATION, (V)new Integer(operation));
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void dispatchTypeInsertStringMapper(HashMap<K, V> insertDispatchMap, StringBuilder insertString) {
		 V type=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.TYPE, insertDispatchMap);
		 V dispatchStatus=delhiveryCollection.getValueWithPipe((K)DelhiveryEtlKeys.DISPATCH_STATUS, insertDispatchMap);
		 insertString.append(type.toString());
		 insertString.append(dispatchStatus.toString());
		 insertString.append(operation);
		 insertString.append(DelhiveryEtlConstants.NEW_LINE);
	}

}
