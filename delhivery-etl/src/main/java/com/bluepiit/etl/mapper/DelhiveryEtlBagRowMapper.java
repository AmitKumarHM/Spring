package com.bluepiit.etl.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.bluepiit.etl.mapper.service.bag.BagChildSealNumbersInsertMapper;
import com.bluepiit.etl.mapper.service.bag.BagDispatchDetailsInsertMapper;
import com.bluepiit.etl.mapper.service.bag.BagDispatchInsertMapper;
import com.bluepiit.etl.mapper.service.bag.BagInsertMapper;
import com.bluepiit.etl.mapper.service.bag.BagIstInsertMapper;
import com.bluepiit.etl.mapper.service.bag.BagScanFactsInsertMapper;
import com.bluepiit.etl.mapper.service.bag.IncomingBagInsertMapper;
import com.bluepiit.etl.mapper.service.impl.bag.BagChildSealNumbersInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.bag.BagDispatchDetailsInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.bag.BagDispatchInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.bag.BagInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.bag.BagIstInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.bag.BagScanFactsInsertMapperImpl;
import com.bluepiit.etl.mapper.service.impl.bag.IncomingBagInsertMapperImpl;

public class DelhiveryEtlBagRowMapper<K, V, D> implements BagMapper<K, V, D> {
	
	private BagChildSealNumbersInsertMapper<K, V, D> bagChildSealNumbersInsertMapper;
	private BagDispatchDetailsInsertMapper<K, V, D> bagDispatchDetailsInsertMapper;
	private BagDispatchInsertMapper<K, V, D> bagDispatchInsertMapper;
	private BagInsertMapper<K, V, D> bagInsertMapper;
	private BagIstInsertMapper<K, V, D> bagIstInsertMapper;
	private BagScanFactsInsertMapper<K, V, D> bagScanFactsInsertMapper;
	private IncomingBagInsertMapper<K, V, D> incomingBagInsertMapper;
	

	public void setOperation(int operation) {
		bagChildSealNumbersInsertMapper.setOperation(operation);
		bagDispatchDetailsInsertMapper.setOperation(operation);
		bagDispatchInsertMapper.setOperation(operation);
		bagInsertMapper.setOperation(operation);
		bagIstInsertMapper.setOperation(operation);
		bagScanFactsInsertMapper.setOperation(operation);
		incomingBagInsertMapper.setOperation(operation);
	}

	public DelhiveryEtlBagRowMapper() {
		  init();	
	}
	
	private void init(){
		bagChildSealNumbersInsertMapper=new BagChildSealNumbersInsertMapperImpl<K,V,D>();
		bagDispatchDetailsInsertMapper=new BagDispatchDetailsInsertMapperImpl<K,V,D>();
		bagDispatchInsertMapper=new BagDispatchInsertMapperImpl<K,V,D>();
		bagInsertMapper=new BagInsertMapperImpl<K, V, D>();
		bagIstInsertMapper=new BagIstInsertMapperImpl<K, V, D>();
		bagScanFactsInsertMapper=new BagScanFactsInsertMapperImpl<K, V, D>();
		incomingBagInsertMapper=new IncomingBagInsertMapperImpl<K, V, D>();
	}
	
	@Override
	public boolean isISTCollections(HashMap<K, V> insertDispatchMap) {
		return bagChildSealNumbersInsertMapper.isISTCollections(insertDispatchMap);
	}

	@Override
	public boolean isDispatchCollections(HashMap<K, V> insertDispatchMap) {
		return bagChildSealNumbersInsertMapper.isDispatchCollections(insertDispatchMap);
	}

	@Override
	public boolean isBagCollections(HashMap<K, V> insertDispatchMap) {
		return bagChildSealNumbersInsertMapper.isBagCollections(insertDispatchMap);
	}

	@Override
	public boolean isInsertCollections(HashMap<K, V> insertDispatchMap) {
		return bagChildSealNumbersInsertMapper.isInsertCollections(insertDispatchMap);
	}

	@Override
	public boolean isUpdateCollections(HashMap<K, V> insertDispatchMap) {
		return bagChildSealNumbersInsertMapper.isUpdateCollections(insertDispatchMap);
	}

	
	@Override
	public String bagInsertStringMapper(HashMap<K, V> insertbagMap) {
		StringBuilder strBuilder=new StringBuilder();
		bagInsertMapper.bagInsertStringMapper(insertbagMap, strBuilder);
		return strBuilder.toString();
	}

	@Override
	public HashMap<K, V> bagInsertMapper(HashMap<K, V> insertbagMap) {
		HashMap<K, V> persistData=new HashMap<K, V>();
		bagInsertMapper.bagInsertMapper(insertbagMap, persistData);
		return persistData;
	}


	@Override
	public ArrayList<HashMap<K, V>> bagChildSealNumbersInsertMapper(HashMap<K, V> insertDispatchMap) {
		ArrayList<HashMap<K, V>> persistData=new ArrayList<HashMap<K, V>>();
		bagChildSealNumbersInsertMapper.bagChildSealNumbersInsertMapper(insertDispatchMap, persistData);
		return persistData;
	}

	@Override
	public String bagChildSealNumbersInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		bagChildSealNumbersInsertMapper.bagChildSealNumbersInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public HashMap<K, V> bagDispatchDetailsInsertMapper(HashMap<K, V> insertDispatchMap) {
		HashMap<K, V> persistData=new HashMap<K, V>();
		bagDispatchDetailsInsertMapper.bagDispatchDetailsInsertMapper(insertDispatchMap, persistData);
		return persistData;
	}

	@Override
	public String bagDispatchDetailsInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		bagDispatchDetailsInsertMapper.bagDispatchDetailsInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public HashMap<K, V> bagDispatchInsertMapper(HashMap<K, V> insertDispatchMap) {
		HashMap<K, V> persistData=new HashMap<K, V>();
		bagDispatchInsertMapper.bagDispatchInsertMapper(insertDispatchMap, persistData);
		return persistData;
	}

	@Override
	public String bagDispatchInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		bagDispatchInsertMapper.bagDispatchInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public ArrayList<HashMap<K, V>> bagIstInsertMapper(HashMap<K, V> insertDispatchMap) {
		ArrayList<HashMap<K, V>> persistData=new ArrayList<HashMap<K, V>>();
		bagIstInsertMapper.bagIstInsertMapper(insertDispatchMap, persistData);
		return persistData;
	}

	@Override
	public String bagIstInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		bagIstInsertMapper.bagIstInsertStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

	@Override
	public ArrayList<HashMap<K, V>> insertBagScanFactsMapper(HashMap<K, V> insertISTScanMap) {
		ArrayList<HashMap<K, V>> persistData=new ArrayList<HashMap<K, V>>();
		bagScanFactsInsertMapper.insertBagScanFactsMapper(insertISTScanMap, persistData);
		return persistData;

	}

	@Override
	public String insertBagScanFactsStringMapper(HashMap<K, V> insertISTScanMap) {
		StringBuilder persistData=new StringBuilder();
		bagScanFactsInsertMapper.insertBagScanFactsStringMapper(insertISTScanMap, persistData);
		return persistData.toString();
	}

	@Override
	public ArrayList<HashMap<K, V>> incomingBagInsertMapper(HashMap<K, V> insertDispatchMap) {
		ArrayList<HashMap<K, V>> persistData=new ArrayList<HashMap<K, V>>();
		incomingBagInsertMapper.incomingBagInsertMapper(insertDispatchMap, persistData);
		return persistData;
	}

	@Override
	public String incomingBagInsertStringMapper(HashMap<K, V> insertDispatchMap) {
		StringBuilder persistData=new StringBuilder();
		bagScanFactsInsertMapper.insertBagScanFactsStringMapper(insertDispatchMap, persistData);
		return persistData.toString();
	}

}
