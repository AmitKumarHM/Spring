package com.bluepiit.etl.mapper.service.bag;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface BagInsertMapper<K,V,D>  extends RowMapper<K,V,D>{
	public void bagInsertStringMapper(HashMap<K,V> insertbagMap,StringBuilder insertString);
	public void bagInsertMapper(HashMap<K,V> insertbagMap,HashMap<K,V> arrayList);
}
