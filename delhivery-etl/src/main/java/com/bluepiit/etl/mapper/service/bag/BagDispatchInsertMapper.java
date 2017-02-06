package com.bluepiit.etl.mapper.service.bag;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface BagDispatchInsertMapper<K, V, D>  extends RowMapper<K, V, D> {

	public void bagDispatchInsertMapper(HashMap<K,V> insertDispatchMap,HashMap<K, V> arrayList);
	public void bagDispatchInsertStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString);

	
	public void bagDispatchUpdateMapper(HashMap<K,V> insertDispatchMap,HashMap<K, V> arrayList, HashMap<K,V> redisMap);
	public void bagDispatchUpdateStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString, HashMap<K,V> redisMap);
}
