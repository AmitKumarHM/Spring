package com.bluepiit.etl.mapper.service.bag;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface BagDispatchDetailsInsertMapper<K, V, D>  extends RowMapper<K, V, D> {

	public void bagDispatchDetailsInsertMapper(HashMap<K,V> insertDispatchMap,HashMap<K, V> arrayList);
	public void bagDispatchDetailsInsertStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString);

	public void bagDispatchDetailsUpdateMapper(HashMap<K,V> insertDispatchMap,HashMap<K, V> arrayList,HashMap<K,V> redisDispatchMap);
	public void bagDispatchDetailsUpdateStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString,HashMap<K,V> redisDispatchMap);
}
