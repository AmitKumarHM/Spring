package com.bluepiit.etl.bolts.local;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluepiit.etl.DelhiveryETLConstants;
import com.bluepiit.etl.diskstorage.DiskOperation;
import com.bluepiit.etl.diskstorage.RedisOperation;
import com.bluepiit.etl.mapper.DelhiveryEtlBagRowMapper;
import com.bluepiit.etl.models.KinesisInputModel;
import com.bluepiit.etl.utils.DBUtils;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.common.DelhiveryCollection;
import com.bluepiit.etl.utils.constant.DelhiveryBagKeyAndColumn;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;
import com.bluepiit.etl.utils.constant.DelhiveryEtlKeys;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class LocalBagInsertBolt extends BaseBasicBolt{

	private static final long serialVersionUID = -2620298894307487535L;
	private static final Logger LOG = LoggerFactory.getLogger(LocalBagInsertBolt.class);

	private DiskOperation diskOperation;
	private DelhiveryCollection<String ,Object> delhiveryCollection;

	public void prepare(@SuppressWarnings(DelhiveryEtlConstants.RAW_TYPES) Map stormConf, TopologyContext context) {
		super.prepare(stormConf, context);
		diskOperation=RedisOperation.getInstance();
		delhiveryCollection=new DelhiveryCollection<String ,Object>();
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void execute(Tuple input, BasicOutputCollector collector) {
		byte[] payload = (byte[]) input.getValueByField(KinesisInputModel.FIELD_RECORD_DATA);
		ObjectMapper mapper = new ObjectMapper();
		DelhiveryEtlBagRowMapper<String,Object,Double> delhiveryETLRowMapper=new DelhiveryEtlBagRowMapper<String,Object,Double>();
		try {
			if(!DelhiveryUtils.isNULL(payload)) {
				JsonNode jsonNode = mapper.readTree(payload);
				LOG.info("Payload converted into json and is "+jsonNode.toString());
				HashMap<String, Object> readValue =mapper.readValue(jsonNode.toString(), HashMap.class);
				if(delhiveryETLRowMapper.isBagCollections(readValue) &&  delhiveryETLRowMapper.isInsertCollections(readValue) && !DelhiveryUtils.isNULL(readValue.get(DelhiveryEtlKeys.OUTPUT)) && withoutMapperPersisting((HashMap<String, Object>)readValue.get(DelhiveryEtlKeys.OUTPUT),delhiveryETLRowMapper))
					LOG.info("Bag is persist successfully");
			}
		} catch (JsonProcessingException e) {
			LOG.error("Conversion to JSON not successfully "+e.getOriginalMessage());
		}catch (IOException e) {
			LOG.error("Stream not successfully converted "+e.getMessage());
		}		

	}

	@SuppressWarnings({DelhiveryEtlConstants.UNUSED})
	private boolean persist(HashMap<String, Object> readValue, DelhiveryEtlBagRowMapper<String, Object, Double> delhiveryETLRowMapper) throws JsonProcessingException {
		LOG.info("read Map from JSON "+readValue);
		ObjectMapper mapper = new ObjectMapper();
		delhiveryETLRowMapper.setOperation(DelhiveryEtlConstants.FIRST_INDEX);
		DelhiveryUtils.parseDate(readValue);
		DelhiveryUtils.prepareDataForKinesis(readValue, DelhiveryETLConstants.BAG_JSON_ARRAY);
		HashMap<String,Object> persistData=delhiveryETLRowMapper.bagInsertMapper(readValue);
		String persistData1=delhiveryETLRowMapper.bagInsertStringMapper(readValue);
		String bagChildSealNumbers1=delhiveryETLRowMapper.bagChildSealNumbersInsertStringMapper(readValue);
		String bagIst1=delhiveryETLRowMapper.bagIstInsertStringMapper(readValue);
		String incomingBag1=delhiveryETLRowMapper.incomingBagInsertStringMapper(readValue);
		String  bagScanFacts1=delhiveryETLRowMapper.insertBagScanFactsStringMapper(readValue);
		String bagDispatch1=delhiveryETLRowMapper.bagDispatchInsertStringMapper(readValue);
		String bagDispatchDetails1=delhiveryETLRowMapper.bagDispatchDetailsInsertStringMapper(readValue);

		diskOperation.set(persistData.get(DelhiveryEtlKeys.DB_MONGO_ID).toString(), persistData.get(DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER));
		diskOperation.set(persistData.get(DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER).toString(), mapper.writeValueAsString(readValue));
		diskOperation.expiry(DelhiveryETLConstants.ID, DelhiveryETLConstants.EXPIRY_SECONDS);		
		return true;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}

	
	private boolean withoutMapperPersisting(HashMap<String, Object> readValue, DelhiveryEtlBagRowMapper<String, Object, Double> delhiveryETLRowMapper)  throws JsonProcessingException{
        
		ObjectMapper mapper = new ObjectMapper();
		DelhiveryUtils.parseDate(readValue);
		readValue.put(DelhiveryEtlKeys.DB_OPERATION, DelhiveryEtlConstants.FIRST_INDEX);
		delhiveryETLRowMapper.setOperation(DelhiveryEtlConstants.FIRST_INDEX);
		HashMap<String,Object> persistData=delhiveryETLRowMapper.bagInsertMapper(readValue);
		
		String bag=DelhiveryUtils.prepareDataForKinesis(readValue, DelhiveryETLConstants.BAG_JSON_ARRAY);
		String bagDispatchDetails=DelhiveryUtils.prepareDataForKinesis(readValue, DelhiveryETLConstants.BAG_DISPATCH_DETAILS);
        String bagDispatch=DelhiveryUtils.prepareDataForKinesis(readValue, DelhiveryETLConstants.BAG_DISPATCH);
        String bagIst=getBagIst(readValue,persistData);
        String bagChildSealNumbers=getBagChildSealNumbers(readValue,persistData);
        String bagScansfacts=getBagScanFacts(readValue,persistData);
        String incomingBag1=delhiveryETLRowMapper.incomingBagInsertStringMapper(readValue);
        System.out.println(bag+"\nbagDispatchDetails****"+bagDispatchDetails+"\nbagDispatch****"+bagDispatch+"\nbagIst*******"+bagIst+"\nbagChildSealNumbers********"+bagChildSealNumbers+"\nbagScansfacts******"+bagScansfacts+"\nincomingBag1*********"+incomingBag1);
		diskOperation.set(persistData.get(DelhiveryEtlKeys.DB_MONGO_ID).toString(), persistData.get(DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER));
		diskOperation.set(persistData.get(DelhiveryEtlKeys.DB_BAG_SEAL_NUMBER).toString(), mapper.writeValueAsString(readValue));
		diskOperation.expiry(DelhiveryETLConstants.ID, DelhiveryETLConstants.EXPIRY_SECONDS);		
		return true;
	}
	
	
	
	@SuppressWarnings({DelhiveryEtlConstants.UNCHECKED})
	private String getBagScanFacts(HashMap<String, Object> readValue, HashMap<String, Object> persistData) {
		ArrayList<Object> scanList=delhiveryCollection.getList(DelhiveryEtlKeys.SCAN_ARRAY, readValue);
		Iterator<Object> itr=scanList.iterator();
		String bagSealNumber=(String)delhiveryCollection.getValue(DelhiveryEtlKeys.BAG_SEAL_NUMBER, readValue);
		String originCenter=(String)delhiveryCollection.getValueWithPipe(DelhiveryEtlKeys.ORIGIN_CENTER, readValue);
		String destination=(String)delhiveryCollection.getValueWithPipe(DelhiveryEtlKeys.DESTINATION, readValue);
		String zone=(String)delhiveryCollection.getValueWithPipe(DelhiveryBagKeyAndColumn.ZONE, readValue);
		
		StringBuilder persistString=new StringBuilder();
		ArrayList<HashMap<String, Object>> scanFacts=new ArrayList<HashMap<String, Object>>();
		while(itr.hasNext()){
	    	HashMap<String, Object> scanFactsMap=(HashMap<String, Object>)itr.next();
	    	
	    	HashMap<String, Object> scanFactsLatest=new HashMap<String, Object>();
	    	scanFactsLatest.put(DelhiveryEtlKeys.SCAN_ARRAY, scanFactsMap);
	    	scanFactsLatest.put(DelhiveryEtlKeys.BAG_SEAL_NUMBER, bagSealNumber);
	    	scanFactsLatest.put(DelhiveryEtlKeys.ORIGIN_CENTER, originCenter);
	    	scanFactsLatest.put(DelhiveryEtlKeys.DESTINATION, destination);
	    	scanFactsLatest.put(DelhiveryBagKeyAndColumn.ZONE, zone);
	    	scanFactsLatest.put(DelhiveryEtlKeys.DB_OPERATION, DelhiveryEtlConstants.FIRST_INDEX);
	    	scanFacts.add(scanFactsLatest);
	    }
		
		Iterator<HashMap<String, Object>> itr1=scanFacts.iterator();
		while(itr1.hasNext()){
			HashMap<String, Object> istMap=(HashMap<String, Object>)itr1.next();
			persistString.append(DelhiveryUtils.prepareDataForKinesis(istMap,DelhiveryETLConstants.BAG_SCAN_FACTS));
			persistString.append(DelhiveryEtlConstants.NEW_LINE);
		}
		return persistString.toString();
	}
	
	
	
	@SuppressWarnings({DelhiveryEtlConstants.UNCHECKED})
	private String getBagIst(HashMap<String, Object> readValue, HashMap<String, Object> persistData) {
		ArrayList<Object> istList=delhiveryCollection.getList(DelhiveryBagKeyAndColumn.IST, readValue);
		Iterator<Object> itr=istList.iterator();
		String bagSealNumber=(String)delhiveryCollection.getValue(DelhiveryEtlKeys.BAG_SEAL_NUMBER, readValue);
		StringBuilder persistString=new StringBuilder();
		ArrayList<HashMap<String, Object>> bagIst=new ArrayList<HashMap<String, Object>>();
		while(itr.hasNext()){
	    	HashMap<String, Object> istMap=(HashMap<String, Object>)itr.next();
	    	HashMap<String, Object> mongoIdMap=delhiveryCollection.getMap(DelhiveryBagKeyAndColumn.ID, istMap);
	    	String mongoId=(String)delhiveryCollection.getValue(DelhiveryEtlKeys.MONGO_DB_ID, mongoIdMap);
	    	String istNumber=getISTNumber(mongoId);
	    	HashMap<String, Object> istMapLatest=new HashMap<String, Object>();
	    	istMapLatest.put(DelhiveryEtlKeys.IST_NUMBER, istNumber);
	    	istMapLatest.put(DelhiveryEtlKeys.BAG_SEAL_NUMBER, bagSealNumber);
	    	istMapLatest.put(DelhiveryEtlKeys.DB_OPERATION, DelhiveryEtlConstants.FIRST_INDEX);
	    	bagIst.add(istMapLatest);
	    }
		
		Iterator<HashMap<String, Object>> itr1=bagIst.iterator();
		while(itr1.hasNext()){
			HashMap<String, Object> istMap=(HashMap<String, Object>)itr1.next();
			persistString.append(DelhiveryUtils.prepareDataForKinesis(istMap,DelhiveryETLConstants.BAG_IST));
			persistString.append(DelhiveryEtlConstants.NEW_LINE);
		}
		return persistString.toString();
	}

	
	
	private String getBagChildSealNumbers(HashMap<String, Object> readValue, HashMap<String, Object> persistData) {
		ArrayList<Object> istList=delhiveryCollection.getList(DelhiveryBagKeyAndColumn.CHILD_BAG_SEAL_NUMBER, readValue);
		Iterator<Object> itr=istList.iterator();
		String bagSealNumber=(String)delhiveryCollection.getValue(DelhiveryEtlKeys.BAG_SEAL_NUMBER, readValue);
		StringBuilder persistString=new StringBuilder();
		ArrayList<HashMap<String, Object>> bagIst=new ArrayList<HashMap<String, Object>>();
		while(itr.hasNext()){
			Integer bss=(Integer)itr.next();
			HashMap<String, Object> istMapLatest=new HashMap<String, Object>();
	    	istMapLatest.put(DelhiveryEtlKeys.DB_CHILED_BAG_SEAL_NUMBER, bss);
	    	istMapLatest.put(DelhiveryEtlKeys.BAG_SEAL_NUMBER, bagSealNumber);
	    	istMapLatest.put(DelhiveryEtlKeys.DB_OPERATION, DelhiveryEtlConstants.FIRST_INDEX);
	    	bagIst.add(istMapLatest);
	    }
		
		Iterator<HashMap<String, Object>> itr1=bagIst.iterator();
		while(itr1.hasNext()){
			HashMap<String, Object> istMap=(HashMap<String, Object>)itr1.next();
			persistString.append(DelhiveryUtils.prepareDataForKinesis(istMap,DelhiveryETLConstants.CHILD_BAG_NUMBERS));
			persistString.append(DelhiveryEtlConstants.NEW_LINE);
		}
		return persistString.toString();
	}
	
	
	
	
	private String getISTNumber(String mongoId){
		String istNumber=null;
    	SqlSession session =null;
		try{
			session = DBUtils.getSessionFactory().openSession(ExecutorType.SIMPLE);
			istNumber=(String)session.selectOne("selectWbnFromIST",mongoId);
		}catch(Exception ex){
			LOG.error("Exception occur in insert DispatchRoute "+ex.getMessage());
		}finally {
			session.close();
		}
		return istNumber;
	}

	 @Override
	 public void cleanup() {
	        super.cleanup();
	        diskOperation.destroyPool();
	    }
	
}
