package com.bluepiit.etl.bolts.remote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ExecutorType;
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


public class PackageDeleteBolt extends BaseBasicBolt {


    private static final Logger LOG = LoggerFactory.getLogger(PackageDeleteBolt.class);
    private ArrayList<HashMap<String, Object>> packageDeleteList = new ArrayList<HashMap<String, Object>>();

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer fieldsDeclarer) {
        // No Output.
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        Config conf = new Config();
        conf.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, 60);
        return conf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Tuple tuple,
                        BasicOutputCollector outputCollector) {

        if (isTickTuple(tuple)) {
            long currentTime;
            SqlSession session = DBUtils.getSessionFactory().openSession(ExecutorType.BATCH);
            try {
                if (!packageDeleteList.isEmpty()) {

                    for (HashMap<String, Object> packageMap : packageDeleteList) {
                        String id = DelhiveryUtils.extractMongoObjectId(packageMap);
                        LOG.info("MONGO ID ---------- " + id);

                        String waybillnum = session.selectOne(DelhiveryETLConstants.PACKAGE_SELECT_WAYBILLNUM, id);
                        if (StringUtils.isNotBlank(waybillnum)) {
                            session.delete(DelhiveryETLConstants.PACKAGE_DELETE_PACKAGE, waybillnum);
                            session.delete(DelhiveryETLConstants.PACKAGE_SCANS_DELETE_SCAN_FACTS, waybillnum);
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

                    if (readValue != null) {
                        if (readValue.get(DelhiveryETLConstants.COLLECTION_NAME_KEY).toString().equalsIgnoreCase(DelhiveryETLConstants.COLLECTION_PACKAGE)
                                && readValue.get(DelhiveryETLConstants.OPERATION).toString().equalsIgnoreCase(DelhiveryETLConstants.DELETE_OPERATION)) {
                            DelhiveryUtils.parseDate(readValue);
                            LOG.info("Delete ----------- " + jsonNode.toString() + "----------------------");
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
