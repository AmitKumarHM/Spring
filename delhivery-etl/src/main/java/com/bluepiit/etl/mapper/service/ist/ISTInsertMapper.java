package com.bluepiit.etl.mapper.service.ist;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface ISTInsertMapper<K,V,D> extends RowMapper<K,V,D>{
	public void insertISTMapper(HashMap<K,V> insertISTMap,HashMap<K,V> persistData);
	public void insertISTStringMapper(HashMap<K,V> insertISTMap,StringBuilder persistData);
}
