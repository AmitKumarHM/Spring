package com.bluepiit.etl.utils.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;

public class DelhiveryCollection<K, V>{

	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public ArrayList<V> getList(K k,HashMap<K, V> map){
		    return (!DelhiveryUtils.isNULL(map))?(ArrayList<V>)map.get(k):null;	 	
	}

	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public HashMap<K, V> getMap(K k,HashMap<K, V> map){   
		return (!DelhiveryUtils.isNULL(map))?(HashMap<K, V>)map.get(k):null;	 	
	}
	
	public V getValue(K k,HashMap<K, V> map){
		    return (!DelhiveryUtils.isNULL(map))?(V)map.get(k):(V)null;	 	
	}
	
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public V getValueWithPipe(K k,HashMap<K, V> map){
	    return (!DelhiveryUtils.isNULL(map))?((!DelhiveryUtils.isNULL((V)map.get(k)))?(V)(map.get(k).toString()+(DelhiveryEtlConstants.PIPE)):(V)(DelhiveryEtlConstants.PIPE)):(V)(DelhiveryEtlConstants.PIPE);	 	
    }
	
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public V getValueFromArrayWithPipe(Iterator<V> itr){
		if(!DelhiveryUtils.isNULL(itr)){
			V v= (V)itr.next();
			return (V) ((!DelhiveryUtils.isNULL(v))?v.toString()+DelhiveryEtlConstants.PIPE:DelhiveryEtlConstants.PIPE);
		}
	    return (V)DelhiveryEtlConstants.PIPE;	 	
    }
	
	
	
	
	public HashMap<K, V> getMap(K k,HashMap<K, V> map,HashMap<K, V> redisMap){  
		return (!DelhiveryUtils.isNULL(getMap(k,map)))?getMap(k,map):getMap(k,redisMap);	 	
	}
	
	public V getValue(K k,HashMap<K, V> map,HashMap<K, V> redisMap){
	    return (!DelhiveryUtils.isNULL(getValue(k,map)))?(V)getValue(k,map):(V)(V)getValue(k,redisMap);	 	
    }
	
	public V getValueWithPipe(K k,HashMap<K, V> map,HashMap<K, V> redisMap){
		return (!DelhiveryUtils.isNULL(getValue(k,map)))?(V)getValueWithPipe(k,map):(V)getValueWithPipe(k,redisMap);	 	
    }

	public ArrayList<V> getList(K k,HashMap<K, V> map,HashMap<K, V> redisMap){
		    return (!DelhiveryUtils.isNULL(getList(k,map)))?getList(k,map):getList(k,redisMap);	 	
	}
	
	public V getValueFromArrayWithPipe(Iterator<V> itr,Iterator<V> redisItr){
			 	return (itr.hasNext())?getValueFromArrayWithPipe(itr):getValueFromArrayWithPipe(redisItr);
    }
	
}
