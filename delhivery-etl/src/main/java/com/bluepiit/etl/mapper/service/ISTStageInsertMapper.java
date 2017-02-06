package com.bluepiit.etl.mapper.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface ISTStageInsertMapper<K,V> {
	public void insertISTStageMapper(HashMap<K,V> insertISTScanMap,ArrayList<HashMap<K,V>> persistData);
}
