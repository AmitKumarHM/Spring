package com.bluepiit.etl.mapper;

import java.util.HashMap;

public interface ISTScansStageMapper<K, V> {
	public HashMap<K,V> istInsertMapper(HashMap<K,V> insertISTMap);
}
