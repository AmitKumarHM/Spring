package com.bluepiit.etl.mapper.service.bag;

import java.util.ArrayList;
import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;

public interface BagScanFactsInsertMapper<K, V, D>  extends RowMapper<K, V, D> {

	public void insertBagScanFactsMapper(HashMap<K,V> insertISTScanMap,ArrayList<HashMap<K,V>> persistData);
	public void insertBagScanFactsStringMapper(HashMap<K,V> insertISTScanMap,StringBuilder persistData);

}
