package com.bluepiit.etl.mapper.service.dispatch;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface DispatchInsertMapper<K,V,D> extends RowMapper<K,V,D>{
	public void dispatchInsertMapper(HashMap<K,V> insertDispatchMap,HashMap<K,V> arrayList);
	public void dispatchInsertStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString);
	
}
