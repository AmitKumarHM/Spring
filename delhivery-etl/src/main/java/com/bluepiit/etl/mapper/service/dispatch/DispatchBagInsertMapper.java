package com.bluepiit.etl.mapper.service.dispatch;

import java.util.ArrayList;
import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface DispatchBagInsertMapper<K, V, D> extends RowMapper<K, V, D> {
	public void dispatchBagInsertMapper(HashMap<K,V> insertDispatchMap,ArrayList<HashMap<K, V>> arrayList);
	public void dispatchbagInsertStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString);
	
}
