package com.bluepiit.etl.mapper.service.impl;

import java.util.HashMap;

import com.bluepiit.etl.DelhiveryETLConstants;
import com.bluepiit.etl.mapper.service.RowMapper;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.common.DelhiveryCollection;



public class BaseMapperImpl<K, V,D> implements RowMapper<K,V,D>{
	
	protected int operation=0;
	protected DelhiveryCollection<K ,V> delhiveryCollection;

	public BaseMapperImpl() {
		this.delhiveryCollection = new DelhiveryCollection<K ,V>();
	}

	public DelhiveryCollection<K, V> getDelhiveryCollection() {
		return delhiveryCollection;
	}

	public void setDelhiveryCollection(DelhiveryCollection<K, V> delhiveryCollection) {
		this.delhiveryCollection = delhiveryCollection;
	}
	
	public boolean isISTCollections(HashMap<K, V> map){
		V v=map.get(DelhiveryETLConstants.COLLECTION_NAME_KEY);
		return !DelhiveryUtils.isNULL(v)?v.toString().equals(DelhiveryETLConstants.IST_PACKAGE):false;
	}
	
	public boolean isDispatchCollections(HashMap<K, V> map){
		V v=map.get(DelhiveryETLConstants.COLLECTION_NAME_KEY);
		return !DelhiveryUtils.isNULL(v)?v.toString().equals(DelhiveryETLConstants.DISPATCH_PACKAGE):false;	
		}

	public boolean isBagCollections(HashMap<K, V> map){
		V v=map.get(DelhiveryETLConstants.COLLECTION_NAME_KEY);
		return !DelhiveryUtils.isNULL(v)?v.toString().equals(DelhiveryETLConstants.BAGS_PACKAGE):false;	
	}
	
	public boolean isInsertCollections(HashMap<K, V> collectionsMap){
		V v=collectionsMap.get(DelhiveryETLConstants.OPERATION);
		return !DelhiveryUtils.isNULL(v)?v.toString().equals(DelhiveryETLConstants.INSERT_OPERATION):false;	
	}

   public boolean isUpdateCollections(HashMap<K, V> collectionsMap){
	   V v=collectionsMap.get(DelhiveryETLConstants.OPERATION);
		return !DelhiveryUtils.isNULL(v)?v.toString().equals(DelhiveryETLConstants.UPDATE_OPERATION):false;	
    }

	public int getOperation() {
		return operation;
	}
	
	public void setOperation(int operation) {
		this.operation = operation;
	}
   
}
