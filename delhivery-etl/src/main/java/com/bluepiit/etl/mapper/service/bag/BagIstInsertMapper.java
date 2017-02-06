package com.bluepiit.etl.mapper.service.bag;

import java.util.ArrayList;
import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface BagIstInsertMapper<K, V, D> extends RowMapper<K, V, D> {

	public void bagIstInsertMapper(HashMap<K,V> insertDispatchMap,ArrayList<HashMap<K, V>> arrayList);
	public void bagIstInsertStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString);

}
