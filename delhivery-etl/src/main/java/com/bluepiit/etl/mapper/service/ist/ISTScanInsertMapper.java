package com.bluepiit.etl.mapper.service.ist;

import java.util.ArrayList;
import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface ISTScanInsertMapper<K,V,D> extends RowMapper<K,V,D>{
	public void insertISTScanMapper(HashMap<K,V> insertISTScanMap,ArrayList<HashMap<K,V>> persistData);
	public void insertISTScanStringMapper(HashMap<K,V> insertISTScanMap,StringBuilder persistData);
}
