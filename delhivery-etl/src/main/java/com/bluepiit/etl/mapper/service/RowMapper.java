package com.bluepiit.etl.mapper.service;

import java.util.HashMap;

public interface RowMapper<K,V,D> {

    public boolean isISTCollections(HashMap<K, V> insertDispatchMap);
	public boolean isDispatchCollections(HashMap<K, V> insertDispatchMap);
	public boolean isBagCollections(HashMap<K, V> insertDispatchMap);
	public boolean isInsertCollections(HashMap<K, V> insertDispatchMap);
    public boolean isUpdateCollections(HashMap<K, V> insertDispatchMap);
	public void setOperation(int operation);
    
}
