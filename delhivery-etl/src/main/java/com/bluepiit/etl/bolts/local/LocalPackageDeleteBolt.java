package com.bluepiit.etl.bolts.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluepiit.etl.DelhiveryETLConstants;
import com.bluepiit.etl.models.KinesisInputModel;
import com.bluepiit.etl.utils.DBUtils;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import backtype.storm.Config;
import backtype.storm.Constants;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;


public class LocalPackageDeleteBolt extends BaseBasicBolt {
	
	private static final long serialVersionUID = -7845601930845275478L;
	private static final Logger LOG = LoggerFactory.getLogger(LocalPackageDeleteBolt.class);
    private ArrayList<HashMap<String, Object>> packageDeleteList;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
        packageDeleteList = new ArrayList<HashMap<String, Object>>();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer fieldsDeclarer) {
        // No Output.
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        Config conf = new Config();
        conf.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, .01);
        return conf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Tuple tuple,
                        BasicOutputCollector outputCollector) {

        if (isTickTuple(tuple)) {
            long currentTime;
            SqlSession session = DBUtils.getSessionFactory().openSession();
            try {
                if (!packageDeleteList.isEmpty()) {

                    for (HashMap<String, Object> packageMap : packageDeleteList) {
                        String id = DelhiveryUtils.extractMongoObjectId(packageMap);
                        LOG.info("MONGO ID ---------- " + id);
                        String waybillnum = session.selectOne("selectWaybillnum", id);
                        if(StringUtils.isNotBlank(waybillnum)) {
                            session.delete("deletePackage", waybillnum);
                            session.delete("deleteScanFacts", waybillnum);
                        }
                    }

                    LOG.info("Begin Deleting " + packageDeleteList.size() + " Packages ------------------- ");
                    currentTime = System.currentTimeMillis();
                    session.commit();
                    LOG.info("Deleted " + packageDeleteList.size() + " packages in ------------------------ " + (System.currentTimeMillis() - currentTime) / 1000 + " seconds.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                session.rollback();
                session.close();
            } finally {
                packageDeleteList.clear();
                session.close();
            }

        } else {
            byte[] payload = (byte[]) tuple.getValueByField(KinesisInputModel.FIELD_RECORD_DATA);
            ObjectMapper mapper = new ObjectMapper();
            try {
                if (payload != null) {
                    JsonNode jsonNode = mapper.readTree(payload);
                    HashMap<String, Object> readValue = mapper.readValue(jsonNode.toString(), HashMap.class);
                    DelhiveryUtils.parseDate(readValue);

                    if (readValue != null) {
                        if (readValue.get(DelhiveryETLConstants.COLLECTION_NAME_KEY).toString().equalsIgnoreCase(DelhiveryETLConstants.COLLECTION_PACKAGE)
                                && readValue.get(DelhiveryETLConstants.OPERATION).toString().equalsIgnoreCase(DelhiveryETLConstants.DELETE_OPERATION)) {
                            LOG.error("Delete ----------- " + jsonNode.toString() +"----------------------");
                            packageDeleteList.add((HashMap<String, Object>) readValue.get(DelhiveryETLConstants.INCOMING_OBJECT));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isTickTuple(Tuple tuple) {
        String sourceComponent = tuple.getSourceComponent();
        String sourceStreamId = tuple.getSourceStreamId();
        return sourceComponent.equals(Constants.SYSTEM_COMPONENT_ID) && sourceStreamId.equals(Constants.SYSTEM_TICK_STREAM_ID);
    }
}
