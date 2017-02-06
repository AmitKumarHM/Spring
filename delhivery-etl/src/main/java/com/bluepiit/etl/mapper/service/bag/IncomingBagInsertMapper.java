package com.bluepiit.etl.mapper.service.bag;

import java.util.ArrayList;
import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface IncomingBagInsertMapper<K, V, D> extends RowMapper<K, V, D>  {
	public void incomingBagInsertMapper(HashMap<K,V> insertDispatchMap,ArrayList<HashMap<K, V>> arrayList);
	public void incomingBagInsertStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString);
}
