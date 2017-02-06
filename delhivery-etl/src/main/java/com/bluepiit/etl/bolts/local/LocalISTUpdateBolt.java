package com.bluepiit.etl.bolts.local;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluepiit.etl.DelhiveryETLConstants;
import com.bluepiit.etl.diskstorage.DiskOperation;
import com.bluepiit.etl.diskstorage.RedisOperation;
import com.bluepiit.etl.mapper.DelhiveryEtlIstRowMapper;
import com.bluepiit.etl.models.KinesisInputModel;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.common.DelhiveryCollection;
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

public class LocalISTUpdateBolt extends BaseBasicBolt{
	private static final long serialVersionUID = 2143090009079575904L;
	private static final Logger LOG = LoggerFactory.getLogger(LocalISTUpdateBolt.class);

	private DiskOperation redisOperation;
	private DelhiveryCollection<String ,Object> delhiveryCollection;
	@Override
	@SuppressWarnings(DelhiveryEtlConstants.RAW_TYPES)
	public void prepare(Map stormConf, TopologyContext context) {
		super.prepare(stormConf, context);
		redisOperation=RedisOperation.getInstance();
		delhiveryCollection=new DelhiveryCollection<String ,Object>();
	}

	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void execute(Tuple input, BasicOutputCollector collector) {
		byte[] payload = (byte[]) input.getValueByField(KinesisInputModel.FIELD_RECORD_DATA);
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			if(!DelhiveryUtils.isNULL(payload)) {
				JsonNode jsonNode = mapper.readTree(payload);
				DelhiveryEtlIstRowMapper<String,Object,Double> delhiveryETLRowMapper=new DelhiveryEtlIstRowMapper<String,Object,Double>();
				LOG.info("Payload converted into json and is "+jsonNode.toString());
                HashMap<String, Object> readValue =mapper.readValue(jsonNode.toString(), HashMap.class);
				if(delhiveryETLRowMapper.isISTCollections(readValue) && delhiveryETLRowMapper.isUpdateCollections(readValue) && !DelhiveryUtils.isNULL(readValue.get(DelhiveryEtlKeys.OUTPUT)) && persistWithoutMapper((HashMap<String, Object>)readValue.get(DelhiveryEtlKeys.OUTPUT),delhiveryETLRowMapper))
					LOG.info("IST and IST Scan is persist successfully");
			}
		} catch (JsonProcessingException e) {
			LOG.error("Conversion to JSON not successfully "+e.getOriginalMessage());
		}catch (IOException e) {
			LOG.error("Stream not successfully converted "+e.getMessage());
		}		


	}

	@SuppressWarnings({DelhiveryEtlConstants.UNUSED,DelhiveryEtlConstants.UNCHECKED})
	private boolean persist(HashMap<String, Object> readValue, DelhiveryEtlIstRowMapper<String, Object, Double> delhiveryETLRowMapper) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		delhiveryETLRowMapper.setOperation(DelhiveryEtlConstants.FIRST_INDEX);
		String dispatchWayBillNum=(String)redisOperation.get(readValue.get(DelhiveryEtlKeys.DB_MONGO_ID).toString());
		String json=(String)redisOperation.get(dispatchWayBillNum);

		HashMap<String,Object> redisMap=mapper.readValue(json.toString(), HashMap.class);
		for (String key : readValue.keySet()){
			redisMap.put(key, readValue.get(key));
		}
		readValue=redisMap;

		HashMap<String, Object> persistData=delhiveryETLRowMapper.istInsertMapper(readValue);
		String istScanPersistData=delhiveryETLRowMapper.istScanInsertStringMapper(readValue);
		String istPersistData=delhiveryETLRowMapper.istInsertStringMapper(readValue);

		redisOperation.set(persistData.get(DelhiveryEtlKeys.DB_MONGO_ID).toString(), persistData.get(DelhiveryEtlKeys.DB_IST_NUMBER));
		redisOperation.set(persistData.get(DelhiveryEtlKeys.DB_IST_NUMBER).toString(), mapper.writeValueAsString(readValue));
		redisOperation.expiry(DelhiveryETLConstants.ID, DelhiveryETLConstants.EXPIRY_SECONDS);

		LOG.info("Firehose data with pipe for IST "+istPersistData);
		LOG.info("Firehose data with pipe for IST Scan "+istScanPersistData);
		DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryEtlConstants.DISPATCH_KINESIS_FIREHOSE, ByteBuffer.wrap(istPersistData.getBytes()));
		DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryEtlConstants.DISPATCH_KINESIS_FIREHOSE, ByteBuffer.wrap(istScanPersistData.getBytes()));
		return true;
	}


	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}

	@SuppressWarnings({DelhiveryEtlConstants.UNCHECKED})
	private boolean persistWithoutMapper(HashMap<String, Object> readValue, DelhiveryEtlIstRowMapper<String, Object, Double> delhiveryETLRowMapper) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		delhiveryETLRowMapper.setOperation(DelhiveryEtlConstants.START_INDEX);
        readValue.put(DelhiveryEtlKeys.DB_OPERATION, DelhiveryEtlConstants.START_INDEX);
		String dispatchWayBillNum=(String)redisOperation.get(readValue.get(DelhiveryEtlKeys.DB_MONGO_ID).toString());
		String json=(String)redisOperation.get(dispatchWayBillNum);

		HashMap<String,Object> redisMap=mapper.readValue(json.toString(), HashMap.class);
		for (String key : readValue.keySet()){
			redisMap.put(key, readValue.get(key));
		}
		readValue=redisMap;

		HashMap<String, Object> persistData=delhiveryETLRowMapper.istInsertMapper(readValue);
		String istScanPersistData=getISTScanFacts(readValue,persistData);
		String istPersistData=DelhiveryUtils.prepareDataForKinesis(readValue, DelhiveryETLConstants.IST_JSON);
		redisOperation.set(persistData.get(DelhiveryEtlKeys.DB_MONGO_ID).toString(), persistData.get(DelhiveryEtlKeys.DB_IST_NUMBER));
		redisOperation.set(persistData.get(DelhiveryEtlKeys.DB_IST_NUMBER).toString(), mapper.writeValueAsString(readValue));
		redisOperation.expiry(DelhiveryETLConstants.ID, DelhiveryETLConstants.EXPIRY_SECONDS);

		LOG.info("Firehose data with pipe for IST "+istPersistData);
		LOG.info("Firehose data with pipe for IST Scan "+istScanPersistData);
		DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryEtlConstants.DISPATCH_KINESIS_FIREHOSE, ByteBuffer.wrap(istPersistData.getBytes()));
		DelhiveryUtils.putRecordToKinesisFirehose(DelhiveryEtlConstants.DISPATCH_KINESIS_FIREHOSE, ByteBuffer.wrap(istScanPersistData.getBytes()));
		return true;
	}

	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	private String getISTScanFacts(HashMap<String, Object> readValue, HashMap<String, Object> persistData) {

		HashMap<String, Object> connectionMap=delhiveryCollection.getMap(DelhiveryEtlKeys.CONNECTION, readValue);
		ArrayList<Object> istList=delhiveryCollection.getList(DelhiveryEtlKeys.SCAN_ARRAY, readValue);
		Iterator<Object> itr=istList.iterator();
		StringBuilder persistString=new StringBuilder();
		ArrayList<HashMap<String, Object>> bagIst=new ArrayList<HashMap<String, Object>>();
		while(itr.hasNext()){
			HashMap<String, Object> scanMap=(HashMap<String, Object>)itr.next();
			HashMap<String, Object> istMapLatest=new HashMap<String, Object>();
			istMapLatest.put(DelhiveryEtlKeys.SCAN_ARRAY, scanMap);
			istMapLatest.put(DelhiveryEtlKeys.CONNECTION, connectionMap);
			istMapLatest.put(DelhiveryEtlKeys.DB_OPERATION, DelhiveryEtlConstants.FIRST_INDEX);
			bagIst.add(istMapLatest);
		}

		Iterator<HashMap<String, Object>> itr1=bagIst.iterator();
		while(itr1.hasNext()){
			HashMap<String, Object> istMap=(HashMap<String, Object>)itr1.next();
			persistString.append(DelhiveryUtils.prepareDataForKinesis(istMap,DelhiveryETLConstants.IST_SCAN_JSON));
			persistString.append(DelhiveryEtlConstants.NEW_LINE);
		}
		return persistString.toString();
	}

	 @Override
	 public void cleanup() {
	        super.cleanup();
	        redisOperation.destroyPool();
	    }

}
