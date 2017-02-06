package com.bluepiit.etl.mapper.service.dispatch;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface DispatchCurrencyCountInsertMapper<K, V, D> extends RowMapper<K, V, D> {
	public void dispatchCurrencyCountInsertMapper(HashMap<K,V> insertDispatchMap,HashMap<K,V> arrayList);
	public void dispatchCurrencyCountInsertStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString);
}
