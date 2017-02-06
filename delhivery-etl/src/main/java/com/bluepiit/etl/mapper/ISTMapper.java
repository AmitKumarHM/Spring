package com.bluepiit.etl.mapper;

import java.util.HashMap;

public interface ISTMapper<K,V,D> extends BaseMapper<K,V,D> {
	public HashMap<K,V> istInsertMapper(HashMap<K,V> insertISTMap);
	public String istInsertStringMapper(HashMap<K,V> insertISTMap);
}
