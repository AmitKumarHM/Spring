package com.bluepiit.etl.mapper;

import java.util.HashMap;

import com.bluepiit.etl.mapper.service.RowMapper;
import com.bluepiit.etl.mapper.service.impl.BaseMapperImpl;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;

public class DelhiveryBasicOperation<K, V, D> implements BaseMapper<K,V,D> {
	private RowMapper<K, V, D> rowMapper;
	public DelhiveryBasicOperation() {
		  init();	
	}
	
	private void init(){
		rowMapper=new BaseMapperImpl<K, V, D>();
	}
	
	@Override
	public boolean isISTCollections(HashMap<K, V> insertDispatchMap) {
		return rowMapper.isISTCollections(insertDispatchMap);
	}

	@Override
	public boolean isDispatchCollections(HashMap<K, V> insertDispatchMap) {
		return rowMapper.isDispatchCollections(insertDispatchMap);
	}

	@Override
	public boolean isBagCollections(HashMap<K, V> insertDispatchMap) {
		return rowMapper.isBagCollections(insertDispatchMap);
	}

	@Override
	public boolean isInsertCollections(HashMap<K, V> insertDispatchMap) {
		return rowMapper.isInsertCollections(insertDispatchMap);
	}

	@Override
	public boolean isUpdateCollections(HashMap<K, V> insertDispatchMap) {
		return rowMapper.isUpdateCollections(insertDispatchMap);
	}

	@Override
	public void setOperation(int operation) {
		rowMapper.setOperation(operation);
	}

	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public HashMap<K, V> getMap(K k,HashMap<K, V> insertISTMap){   
		return (!DelhiveryUtils.isNULL(insertISTMap))?(HashMap<K, V>)insertISTMap.get(k):null;	 	
	}
	
	public V getValue(K k,HashMap<K, V> insertISTMap){
	    return (!DelhiveryUtils.isNULL(insertISTMap))?(V)insertISTMap.get(k):(V)null;	 	
    }
	
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public V getValueMongoId(HashMap<K, V> insertISTMap){
		 HashMap<K, V> mongoIdMap=getMap((K)DelhiveryEtlKeys.UNDERSCORE_ID, insertISTMap);
		 return getValue((K)DelhiveryEtlKeys.MONGO_DB_ID, mongoIdMap);
    }
	
}
