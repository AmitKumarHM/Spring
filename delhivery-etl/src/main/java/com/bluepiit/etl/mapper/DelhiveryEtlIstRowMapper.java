package com.bluepiit.etl.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.bluepiit.etl.mapper.service.dispatch.DispatchBagInsertMapper;
import com.bluepiit.etl.mapper.service.dispatch.DispatchCurrencyCountInsertMapper;
import com.bluepiit.etl.mapper.service.dispatch.DispatchInsertMapper;
import com.bluepiit.etl.mapper.service.dispatch.DispatchPackageInsertMapper;
import com.bluepiit.etl.mapper.service.dispatch.DispatchPickUpInsertMapper;
import com.bluepiit.etl.mapper.service.dispatch.DispatchPodSlipsInsertMapper;
import com.bluepiit.etl.mapper.service.dispatch.DispatchTypeInsertMapper;
import com.bluepiit.etl.mapper.service.dispatch.IncomingDispatchInsertMapper;
import com.bluepiit.etl.mapper.service.dispatch.RouteDispatchInsertMapper;
import com.bluepiit.etl.mapper.service.impl.dispatch.DispatchBagInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.dispatch.DispatchCurrencyCountInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.dispatch.DispatchPackageInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.dispatch.DispatchPickUpInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.dispatch.DispatchPodSlipsInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.dispatch.DispatchTypeInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.dispatch.DispatcherInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.dispatch.IncomingDispatchInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.dispatch.RouteDispatchInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.ist.ISTInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.ist.ISTInsertScanMapperImpl;

public class DelhiveryEtlIstRowMapper<K,V,D> implements ISTMapper<K, V,D>,ISTScanMapper<K, V,D>,DispatchMapper<K, V,D>{
	
	private ISTInsertMapperImpl<K,V,D> iSTMapperImpl;
	private HashMap<K,V> persistData;
	private ISTInsertScanMapperImpl<K,V> iSTScanMapperImpl;
	private ArrayList<HashMap<K,V>> iSTScanPersistData;
	private HashMap<K,V> dispatchPersistData;
	
	private DispatchInsertMapper<K,V,D> dispatchInsertMapper;
	private DispatchBagInsertMapper<K, V, D> dispatchBagInsertMapper;
	private DispatchCurrencyCountInsertMapper<K, V, D> dispatchCurrencyCountInsertMapper;
	private DispatchPackageInsertMapper<K, V, D> dispatchPackageInsertMapper;
	private DispatchPickUpInsertMapper<K, V, D> dispatchPickUpInsertMapper;
	private DispatchPodSlipsInsertMapper<K, V, D> dispatchPodSlipsInsertMapper;
	private DispatchTypeInsertMapper<K, V, D> dispatchTypeInsertMapper;
	private IncomingDispatchInsertMapper<K, V, D> incomingDispatchInsertMapper;
	private RouteDispatchInsertMapper<K, V, D> routeDispatchInsertMapper;
	
	public DelhiveryEtlIstRowMapper() {
	  init();	
	}
	
	public void setOperation(int operation) {
		dispatchInsertMapper.setOperation(operation);
		iSTMapperImpl.setOperation(operation);
		iSTScanMapperImpl.setOperation(operation);
		dispatchBagInsertMapper.setOperation(operation);
		dispatchCurrencyCountInsertMapper.setOperation(operation);
		dispatchPackageInsertMapper.setOperation(operation);
		dispatchPickUpInsertMapper.setOperation(operation);
		dispatchPodSlipsInsertMapper.setOperation(operation);
		dispatchTypeInsertMapper.setOperation(operation);
		incomingDispatchInsertMapper.setOperation(operation);
		routeDispatchInsertMapper.setOperation(operation);
	}
	
	
	private void init(){
		dispatchPersistData=new HashMap<K,V>();
		dispatchInsertMapper=new DispatcherInsertMapperImpl<K,V,D>();
		iSTMapperImpl=new ISTInsertMapperImpl<K,V,D>();
		iSTScanMapperImpl=new ISTInsertScanMapperImpl<K,V>();
		persistData=new HashMap<K,V>();
		iSTScanPersistData=new ArrayList<HashMap<K,V>>();
		dispatchBagInsertMapper=new DispatchBagInsertMapperImpl<K, V, D>();
		dispatchCurrencyCountInsertMapper=new DispatchCurrencyCountInsertMapperImpl<K, V, D>();
		dispatchPackageInsertMapper=new DispatchPackageInsertMapperImpl<K, V, D>();
		dispatchPickUpInsertMapper=new DispatchPickUpInsertMapperImpl<K, V, D>();
		dispatchPodSlipsInsertMapper=new DispatchPodSlipsInsertMapperImpl<K, V, D>();
		dispatchTypeInsertMapper=new DispatchTypeInsertMapperImpl<K, V, D>();
		incomingDispatchInsertMapper=new IncomingDispatchInsertMapperImpl<K, V, D>();
		routeDispatchInsertMapper=new RouteDispatchInsertMapperImpl<K, V, D>();
		
	}
		
	public DispatchInsertMapper<K,V,D> getDispatchInsertMapper() {
		return dispatchInsertMapper;
	}

	public void setDispatchInsertMapper(DispatchInsertMapper<K,V,D> dispatchInsertMapper) {
		this.dispatchInsertMapper = dispatchInsertMapper;
	}


	public ArrayList<HashMap<K, V>> getiSTScanPersistData() {
		return iSTScanPersistData;
	}

	public void setiSTScanPersistData(ArrayList<HashMap<K, V>> iSTScanPersistData) {
		this.iSTScanPersistData = iSTScanPersistData;
	}

	public HashMap<K, V> getDispatchPersistData() {
		return dispatchPersistData;
	}

	public void setDispatchPersistData(HashMap<K, V> dispatchPersistData) {
		this.dispatchPersistData = dispatchPersistData;
	}

	public ISTInsertScanMapperImpl<K, V> getiSTScanMapperImpl() {
		return iSTScanMapperImpl;
	}

	public void setiSTScanMapperImpl(ISTInsertScanMapperImpl<K, V> iSTScanMapperImpl) {
		this.iSTScanMapperImpl = iSTScanMapperImpl;
	}

	public ISTInsertMapperImpl<K, V,D> getiSTMapperImpl() {
		return iSTMapperImpl;
	}

	public void setiSTMapperImpl(ISTInsertMapperImpl<K, V,D> iSTMapperImpl) {
		this.iSTMapperImpl = iSTMapperImpl;
	}

	public HashMap<K, V> getPersistData() {
		return persistData;
	}

	public void setPersistData(HashMap<K, V> persistData) {
		this.persistData = persistData;
	}

	@Override
	public HashMap<K, V> istInsertMapper(HashMap<K, V> insertISTMap) {
		iSTMapperImpl.insertISTMapper(insertISTMap, persistData);
		return persistData;
	}

	@Override
	public ArrayList<HashMap<K, V>> istScanInsertMapper(HashMap<K, V> insertISTMap) {
		iSTScanMapperImpl.insertISTScanMapper(insertISTMap, iSTScanPersistData);
		return iSTScanPersistData;
	}

	@Override
	public HashMap<K, V> dispatchInsertMapper(HashMap<K, V> insertDispatchMap) {
		dispatchInsertMapper.dispatchInsertMapper(insertDispatchMap,dispatchPersistData);
		return dispatchPersistData;
	}

	@Override
	public String dispatchInsertStringMapper(HashMap<K, V> insertISTMap) {
		StringBuilder dispatchFirehose=new StringBuilder();
		dispatchInsertMapper.dispatchInsertStringMapper(insertISTMap, dispatchFirehose);
		return dispatchFirehose.toString();
	}

	@Override
	public String istInsertStringMapper(HashMap<K, V> insertISTMap) {
		StringBuilder istFirehose=new StringBuilder();
		iSTMapperImpl.insertISTStringMapper(insertISTMap, istFirehose);
		return istFirehose.toString();
	}

	@Override
	public String istScanInsertStringMapper(HashMap<K, V> insertISTMap) {
		StringBuilder istScanFirehose=new StringBuilder();
		iSTScanMapperImpl.insertISTScanStringMapper(insertISTMap, istScanFirehose);
		return istScanFirehose.toString();
	}

	@Override
	public boolean isISTCollections(HashMap<K, V> insertDispatchMap) {
		return iSTMapperImpl.isISTCollections(insertDispatchMap);
	}

	@Override
	public boolean isDispatchCollections(HashMap<K, V> insertDispatchMap) {
		return iSTMapperImpl.isDispatchCollections(insertDispatchMap);
	}

	@Override
	public boolean isBagCollections(HashMap<K, V> insertDispatchMap) {
		return iSTMapperImpl.isBagCollections(insertDispatchMap);
	}

	@Override
	public boolean isInsertCollections(HashMap<K, V> insertDispatchMap) {
	    return iSTMapperImpl.isInsertCollections(insertDispatchMap);
	}

	@Override
	public boolean isUpdateCollections(HashMap<K, V> insertDispatchMap) {
	    return iSTMapperImpl.isUpdateCollections(insertDispatchMap);
	}

	
	
	
	
	
	@Override
	public ArrayList<HashMap<K, V>> dispatchBagInsertMapper(HashMap<K, V> insertDispatchMap) {
		ArrayList<HashMap<K, V>> persistdata=new ArrayList<HashMap<K, V>>();
		dispatchBagInsertMapper.dispatchBagInsertMapper(insertDispatchMap, persistdata);
	    return persistdata;
	}

	@Override
	public String dispatchbagInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		dispatchBagInsertMapper.dispatchbagInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public HashMap<K, V> dispatchCurrencyCountInsertMapper(HashMap<K, V> insertDispatchMap) {
		HashMap<K, V> persistdata=new HashMap<K, V>();
		dispatchCurrencyCountInsertMapper.dispatchCurrencyCountInsertMapper(insertDispatchMap, persistdata);
	    return persistdata;

	}

	@Override
	public String dispatchCurrencyCountInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		dispatchCurrencyCountInsertMapper.dispatchCurrencyCountInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public ArrayList<HashMap<K, V>> dispatchPackageInsertMapper(HashMap<K, V> insertDispatchMap) {
		ArrayList<HashMap<K, V>> persistdata=new ArrayList<HashMap<K, V>>();
		dispatchPackageInsertMapper.dispatchPackageInsertMapper(insertDispatchMap, persistdata);
	    return persistdata;
	}

	@Override
	public String dispatchPackageInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		dispatchPackageInsertMapper.dispatchPackageInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public ArrayList<HashMap<K, V>> dispatchPickUpInsertMapper(HashMap<K, V> insertDispatchMap) {
		ArrayList<HashMap<K, V>> persistdata=new ArrayList<HashMap<K, V>>();
		dispatchPickUpInsertMapper.dispatchPickUpInsertMapper(insertDispatchMap, persistdata);
	    return persistdata;	
	    }

	@Override
	public String dispatchPickUpInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		dispatchPickUpInsertMapper.dispatchPickUpInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public ArrayList<HashMap<K, V>> dispatchPodslipsInsertMapper(HashMap<K, V> insertDispatchMap) {
		ArrayList<HashMap<K, V>> persistdata=new ArrayList<HashMap<K, V>>();
		dispatchPodSlipsInsertMapper.dispatchPodslipsInsertMapper(insertDispatchMap, persistdata);
	    return persistdata;
	    }

	@Override
	public String dispatchPodslipsInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		dispatchPodSlipsInsertMapper.dispatchPodslipsInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public HashMap<K, V> dispatchTypeInsertMapper(HashMap<K, V> insertDispatchMap) {
		HashMap<K, V> persistdata=new HashMap<K, V>();
		dispatchTypeInsertMapper.dispatchTypeInsertMapper(insertDispatchMap, persistdata);
	    return persistdata;
	}

	@Override
	public String dispatchTypeInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		dispatchTypeInsertMapper.dispatchTypeInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public ArrayList<HashMap<K, V>> incomingDispatchInsertMapper(HashMap<K, V> insertDispatchMap) {
		ArrayList<HashMap<K, V>> persistdata=new ArrayList<HashMap<K, V>>();
		incomingDispatchInsertMapper.incomingDispatchInsertMapper(insertDispatchMap, persistdata);
	    return persistdata;
	    }

	@Override
	public String incomingDispatchInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		incomingDispatchInsertMapper.incomingDispatchInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public String routeDispatchInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		routeDispatchInsertMapper.routeDispatchInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public ArrayList<HashMap<K, V>> routeDispatchInsertMapper(HashMap<K, V> insertDispatchMap) {
		ArrayList<HashMap<K, V>> persistdata=new ArrayList<HashMap<K, V>>();
		routeDispatchInsertMapper.routeDispatchInsertMapper(insertDispatchMap, persistdata);
	    return persistdata;
	    }

}
