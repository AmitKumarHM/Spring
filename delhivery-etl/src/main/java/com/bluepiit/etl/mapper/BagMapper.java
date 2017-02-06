package com.bluepiit.etl.mapper;

import java.util.ArrayList;
import java.util.HashMap;

public interface BagMapper<K, V, D>  extends BaseMapper<K, V, D> {

	public ArrayList<HashMap<K, V>> bagChildSealNumbersInsertMapper(HashMap<K,V> insertDispatchMap);
	public String bagChildSealNumbersInsertStringMapper(HashMap<K,V> insertDispatchMap);
	
	public HashMap<K, V> bagDispatchDetailsInsertMapper(HashMap<K,V> insertDispatchMap);
	public String bagDispatchDetailsInsertStringMapper(HashMap<K,V> insertDispatchMap);
	
	public HashMap<K, V> bagDispatchInsertMapper(HashMap<K,V> insertDispatchMap);
	public String bagDispatchInsertStringMapper(HashMap<K,V> insertDispatchMap);

	public String bagInsertStringMapper(HashMap<K,V> insertbagMap);
	public HashMap<K,V> bagInsertMapper(HashMap<K,V> insertbagMap);
	
	public ArrayList<HashMap<K, V>> bagIstInsertMapper(HashMap<K,V> insertDispatchMap);
	public String bagIstInsertStringMapper(HashMap<K,V> insertDispatchMap);
	
	public ArrayList<HashMap<K,V>> insertBagScanFactsMapper(HashMap<K,V> insertISTScanMap);
	public String insertBagScanFactsStringMapper(HashMap<K,V> insertISTScanMap);
	
	public ArrayList<HashMap<K, V>> incomingBagInsertMapper(HashMap<K,V> insertDispatchMap);
	public String incomingBagInsertStringMapper(HashMap<K,V> insertDispatchMap);

	
}
