package com.bluepiit.etl.mapper;

import java.util.HashMap;

public interface ISTStageMapper<K,V> {
	public HashMap<K,V> istInsertMapper(HashMap<K,V> insertISTMap);
}
