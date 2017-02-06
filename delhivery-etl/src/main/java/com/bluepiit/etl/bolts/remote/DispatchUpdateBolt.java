package com.bluepiit.etl.bolts.remote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluepiit.etl.DelhiveryETLConstants;
import com.bluepiit.etl.bolts.local.LocalDispatchUpdateBolt;
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

public class DispatchUpdateBolt extends BaseBasicBolt{
	private static final long serialVersionUID = 2273206934207281834L;
	private static final Logger LOG = LoggerFactory.getLogger(LocalDispatchUpdateBolt.class);
	
	private DiskOperation diskOperation;
	private DelhiveryCollection<String ,Object> delhiveryCollection;
	@Override
	@SuppressWarnings(DelhiveryEtlConstants.RAW_TYPES)
	public void prepare(Map stormConf, TopologyContext context) {
	        super.prepare(stormConf, context);
	        diskOperation=RedisOperation.getInstance();
	        delhiveryCollection=new DelhiveryCollection<String ,Object>();
	}
	
	@Override
	@SuppressWarnings(DelhiveryEtlConstants.UNCHECKED)
	public void execute(Tuple input, BasicOutputCollector collector) {
		byte[] payload = (byte[]) input.getValueByField(KinesisInputModel.FIELD_RECORD_DATA);
        ObjectMapper mapper = new ObjectMapper();
        DelhiveryEtlIstRowMapper<String,Object,Double> delhiveryETLRowMapper=new DelhiveryEtlIstRowMapper<String,Object,Double>();
        try {
            if(!DelhiveryUtils.isNULL(payload)) {
                JsonNode jsonNode = mapper.readTree(payload);
                LOG.info("Payload converted into json and is "+jsonNode.toString());
                HashMap<String, Object> readValue =mapper.readValue(jsonNode.toString(), HashMap.class);
                if(delhiveryETLRowMapper.isDispatchCollections(readValue) && delhiveryETLRowMapper.isUpdateCollections(readValue) && !DelhiveryUtils.isNULL(readValue.get(DelhiveryEtlKeys.OUTPUT)) && withoutMapperPersisting((HashMap<String, Object>)readValue.get(DelhiveryEtlKeys.OUTPUT),delhiveryETLRowMapper))
               	 LOG.info("Dispatch is persist successfully");
             }
        } catch (JsonProcessingException e) {
       	 LOG.error("Conversion to JSON not successfully "+e.getOriginalMessage());
        }catch (IOException e) {
       	 LOG.error("Stream not successfully converted "+e.getMessage());
        }		
        
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		
	}

	@SuppressWarnings({DelhiveryEtlConstants.UNCHECKED,DelhiveryEtlConstants.UNUSED})
	private boolean persist(HashMap<String, Object> readValue, DelhiveryEtlIstRowMapper<String, Object, Double> delhiveryETLRowMapper) throws IOException {
		LOG.info("read Map from JSON "+readValue);
		ObjectMapper mapper = new ObjectMapper();
        delhiveryETLRowMapper.setOperation(DelhiveryEtlConstants.START_INDEX);
		String dispatchWayBillNum=(String)diskOperation.get(readValue.get(DelhiveryEtlKeys.DB_MONGO_ID).toString());
		String json=(String)diskOperation.get(dispatchWayBillNum);
		HashMap<String,Object> redisMap=mapper.readValue(json.toString(), HashMap.class);
		for (String key : readValue.keySet()){
			redisMap.put(key, readValue.get(key));
		}
		readValue=redisMap;
		
		HashMap<String,Object> persistData=delhiveryETLRowMapper.dispatchInsertMapper(readValue);
		String persistData1=delhiveryETLRowMapper.dispatchInsertStringMapper(readValue);
		String dispatchBagList1=delhiveryETLRowMapper.dispatchbagInsertStringMapper(readValue);
		String dispatchPackageList1=delhiveryETLRowMapper.dispatchPackageInsertStringMapper(readValue);
		String dispatchPickUpRequestList1=delhiveryETLRowMapper.dispatchPickUpInsertStringMapper(readValue);
		String  dispatchPodSlipList1=delhiveryETLRowMapper.dispatchPodslipsInsertStringMapper(readValue);
		String dispatchIncomingList1=delhiveryETLRowMapper.incomingDispatchInsertStringMapper(readValue);
		String  dispatchRouteList1=delhiveryETLRowMapper.routeDispatchInsertStringMapper(readValue);
		String  dispatchCurrencyList1=delhiveryETLRowMapper.dispatchCurrencyCountInsertStringMapper(readValue);
		
		diskOperation.set(persistData.get(DelhiveryEtlKeys.DB_MONGO_ID).toString(), persistData.get(DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM));
		diskOperation.set(persistData.get(DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM).toString(), mapper.writeValueAsString(readValue));
		diskOperation.expiry(DelhiveryETLConstants.ID, DelhiveryETLConstants.EXPIRY_SECONDS);
		
	    return true;
	}
	
	@SuppressWarnings({DelhiveryEtlConstants.UNUSED,DelhiveryEtlConstants.UNCHECKED})
	private boolean withoutMapperPersisting(HashMap<String, Object> readValue, DelhiveryEtlIstRowMapper<String, Object, Double> delhiveryETLRowMapper)  throws IOException{
        
		LOG.info("read Map from JSON "+readValue);
		ObjectMapper mapper = new ObjectMapper();
		delhiveryETLRowMapper.setOperation(DelhiveryEtlConstants.START_INDEX);
        readValue.put(DelhiveryEtlKeys.DB_OPERATION, DelhiveryEtlConstants.START_INDEX);
		String dispatchWayBillNum=(String)diskOperation.get(readValue.get(DelhiveryEtlKeys.DB_MONGO_ID).toString());
		String json=(String)diskOperation.get(dispatchWayBillNum);
		HashMap<String,Object> redisMap=mapper.readValue(json.toString(), HashMap.class);
		for (String key : readValue.keySet()){
			redisMap.put(key, readValue.get(key));
		}
		readValue=redisMap;
		
		DelhiveryUtils.parseDate(readValue);
		HashMap<String,Object> persistData=delhiveryETLRowMapper.dispatchInsertMapper(readValue);
		String dispatch=DelhiveryUtils.prepareDataForKinesis(readValue, DelhiveryETLConstants.DISPATCH_JSON);
		String dispatchCurrency=DelhiveryUtils.prepareDataForKinesis(readValue, DelhiveryETLConstants.CURRENCY_JSON);
        String dispatchType=DelhiveryUtils.prepareDataForKinesis(readValue, DelhiveryETLConstants.DISPATCH_TYPE_JSON);
        String dispatchBag=getDispatchBag(readValue,persistData);
        String dispatchPackage=getDispatchPackage(readValue,persistData);
        String dispatchRouteList1=delhiveryETLRowMapper.routeDispatchInsertStringMapper(readValue);
        String dispatchIncomingList1=delhiveryETLRowMapper.incomingDispatchInsertStringMapper(readValue);
        String dispatchPickUpRequestList1=delhiveryETLRowMapper.dispatchPickUpInsertStringMapper(readValue);
		String dispatchPodSlipList1=delhiveryETLRowMapper.dispatchPodslipsInsertStringMapper(readValue);

        
        diskOperation.set(persistData.get(DelhiveryEtlKeys.DB_MONGO_ID).toString(), persistData.get(DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM));
		diskOperation.set(persistData.get(DelhiveryEtlKeys.DB_DISPATCH_WAY_BILL_NUM).toString(), mapper.writeValueAsString(readValue));
		diskOperation.expiry(DelhiveryETLConstants.ID, DelhiveryETLConstants.EXPIRY_SECONDS);
		return true;
	}

	
	private String getDispatchPackage(HashMap<String, Object> readValue, HashMap<String, Object> persistData) {
		
		ArrayList<Object> istList=delhiveryCollection.getList(DelhiveryEtlKeys.WAYBILLNUM, readValue);
		Iterator<Object> itr=istList.iterator();
		String dispatchWayBillNum=(String)delhiveryCollection.getValue(DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, readValue);
		StringBuilder persistString=new StringBuilder();
		ArrayList<HashMap<String, Object>> bagIst=new ArrayList<HashMap<String, Object>>();
		while(itr.hasNext()){
			String wayBillNum=(String)itr.next();
			HashMap<String, Object> istMapLatest=new HashMap<String, Object>();
	    	istMapLatest.put(DelhiveryEtlKeys.WAYBILLNUM, wayBillNum);
	    	istMapLatest.put(DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, dispatchWayBillNum);
	    	istMapLatest.put(DelhiveryEtlKeys.DB_OPERATION, DelhiveryEtlConstants.FIRST_INDEX);
	    	bagIst.add(istMapLatest);
	    }
		
		Iterator<HashMap<String, Object>> itr1=bagIst.iterator();
		while(itr1.hasNext()){
			HashMap<String, Object> istMap=(HashMap<String, Object>)itr1.next();
			persistString.append(DelhiveryUtils.prepareDataForKinesis(istMap,DelhiveryETLConstants.DISPATCH_BAG_JSON));
			persistString.append(DelhiveryEtlConstants.NEW_LINE);
		}
		return persistString.toString();
		
	}

	private String getDispatchBag(HashMap<String, Object> readValue, HashMap<String, Object> persistData) {
		
		ArrayList<Object> istList=delhiveryCollection.getList(DelhiveryEtlKeys.INCOMING_BAGS, readValue);
		Iterator<Object> itr=istList.iterator();
		String dispatchWayBillNum=(String)delhiveryCollection.getValue(DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, readValue);
		StringBuilder persistString=new StringBuilder();
		ArrayList<HashMap<String, Object>> bagIst=new ArrayList<HashMap<String, Object>>();
		while(itr.hasNext()){
			Integer bss=(Integer)itr.next();
			HashMap<String, Object> istMapLatest=new HashMap<String, Object>();
	    	istMapLatest.put(DelhiveryEtlKeys.BAG_SEAL_NUMBER, bss);
	    	istMapLatest.put(DelhiveryEtlKeys.DISPATCH_WAYBILLNUM, dispatchWayBillNum);
	    	istMapLatest.put(DelhiveryEtlKeys.DB_OPERATION, DelhiveryEtlConstants.FIRST_INDEX);
	    	bagIst.add(istMapLatest);
	    }
		
		Iterator<HashMap<String, Object>> itr1=bagIst.iterator();
		while(itr1.hasNext()){
			HashMap<String, Object> istMap=(HashMap<String, Object>)itr1.next();
			persistString.append(DelhiveryUtils.prepareDataForKinesis(istMap,DelhiveryETLConstants.DISPATCH_BAG_JSON));
			persistString.append(DelhiveryEtlConstants.NEW_LINE);
		}
		return persistString.toString();
	}
	

	 @Override
	 public void cleanup() {
	        super.cleanup();
	        diskOperation.destroyPool();
	    }
	
}
