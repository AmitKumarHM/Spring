package com.bluepiit.etl.mapper;

import java.util.ArrayList;
import java.util.HashMap;

public interface DispatchMapper<K,V,D>  extends BaseMapper<K,V,D>{
	public HashMap<K,V> dispatchInsertMapper(HashMap<K,V> insertISTMap);
	public String dispatchInsertStringMapper(HashMap<K,V> insertISTMap);
	
	
	public ArrayList<HashMap<K, V>> dispatchBagInsertMapper(HashMap<K,V> insertDispatchMap);
	public String dispatchbagInsertStringMapper(HashMap<K,V> insertDispatchMap);
	
	
	public HashMap<K,V> dispatchCurrencyCountInsertMapper(HashMap<K,V> insertDispatchMap);
	public String dispatchCurrencyCountInsertStringMapper(HashMap<K,V> insertDispatchMap);

	
	public ArrayList<HashMap<K, V>> dispatchPackageInsertMapper(HashMap<K,V> insertDispatchMap);
	public String dispatchPackageInsertStringMapper(HashMap<K,V> insertDispatchMap);
	
	
	public ArrayList<HashMap<K, V>> dispatchPickUpInsertMapper(HashMap<K,V> insertDispatchMap);
	public String dispatchPickUpInsertStringMapper(HashMap<K,V> insertDispatchMap);
	

	public ArrayList<HashMap<K, V>> dispatchPodslipsInsertMapper(HashMap<K,V> insertDispatchMap);
	public String dispatchPodslipsInsertStringMapper(HashMap<K,V> insertDispatchMap);
	
	
	public HashMap<K, V> dispatchTypeInsertMapper(HashMap<K,V> insertDispatchMap);
	public String dispatchTypeInsertStringMapper(HashMap<K,V> insertDispatchMap);
	
	
	public ArrayList<HashMap<K, V>> incomingDispatchInsertMapper(HashMap<K,V> insertDispatchMap);
	public String incomingDispatchInsertStringMapper(HashMap<K,V> insertDispatchMap);
	
	public ArrayList<HashMap<K, V>> routeDispatchInsertMapper(HashMap<K,V> insertDispatchMap);
	public String routeDispatchInsertStringMapper(HashMap<K,V> insertDispatchMap);
		
}
