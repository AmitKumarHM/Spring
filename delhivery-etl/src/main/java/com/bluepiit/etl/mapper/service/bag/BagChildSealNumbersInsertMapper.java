package com.bluepiit.etl.mapper.service.bag;

import java.util.ArrayList;
import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface BagChildSealNumbersInsertMapper<K, V, D> extends RowMapper<K, V, D> {

	public void bagChildSealNumbersInsertMapper(HashMap<K,V> insertDispatchMap,ArrayList<HashMap<K, V>> arrayList);
	public void bagChildSealNumbersInsertStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString);

	public void bagChildSealNumbersUpdateMapper(HashMap<K,V> insertDispatchMap,ArrayList<HashMap<K, V>> arrayList,HashMap<K,V> redisDispatchMap);
	public void bagChildSealNumbersUpdateStringMapper(HashMap<K,V> insertDispatchMap,StringBuilder insertString,HashMap<K,V> redisDispatchMap);

	
}
