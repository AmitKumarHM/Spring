package com.bluepiit.etl.mapper.service.ist;

import java.util.ArrayList;
import java.util.HashMap;

public interface ISTScansStageInsertMapper <K,V>{
	public void insertISTScansStageMapper(HashMap<K,V> insertISTScanMap,ArrayList<HashMap<K,V>> persistData);
}
