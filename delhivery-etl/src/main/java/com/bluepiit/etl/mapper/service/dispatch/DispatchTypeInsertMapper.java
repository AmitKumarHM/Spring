package com.bluepiit.etl.mapper.service.dispatch;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface DispatchTypeInsertMapper<K, V, D> extends RowMapper<K, V, D> {
	public void dispatchTypeInsertMapper(HashMap<K,V> insertDispatchMap,HashMap<K, V> arrayList);
	public void dispatchTypeInsertStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString);

}
