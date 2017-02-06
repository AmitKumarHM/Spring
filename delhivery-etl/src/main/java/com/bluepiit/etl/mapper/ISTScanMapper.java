package com.bluepiit.etl.mapper;

import java.util.ArrayList;
import java.util.HashMap;

public interface ISTScanMapper<K,V,D> extends BaseMapper<K,V,D> {
	public ArrayList<HashMap<K,V>> istScanInsertMapper(HashMap<K,V> insertISTMap);
	public String istScanInsertStringMapper(HashMap<K,V> insertISTMap);
}
