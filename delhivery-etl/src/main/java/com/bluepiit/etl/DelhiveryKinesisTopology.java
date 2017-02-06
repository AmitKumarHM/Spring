package com.bluepiit.etl;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.stormspout.InitialPositionInStream;
import com.amazonaws.services.kinesis.stormspout.KinesisSpout;
import com.amazonaws.services.kinesis.stormspout.KinesisSpoutConfig;
import com.bluepiit.etl.bolts.local.LocalBagInsertBolt;
import com.bluepiit.etl.bolts.local.LocalBagUpdateBolt;
import com.bluepiit.etl.bolts.local.LocalDispatchInsertBolt;
import com.bluepiit.etl.bolts.local.LocalDispatchUpdateBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTUpdateBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.local.LocalISTInsertBolt;
import com.bluepiit.etl.bolts.remote.BagInsertBolt;
import com.bluepiit.etl.bolts.remote.BagUpdateBolt;
import com.bluepiit.etl.bolts.remote.DispatchInsertBolt;
import com.bluepiit.etl.bolts.remote.DispatchUpdateBolt;
import com.bluepiit.etl.bolts.remote.ISTInsertBolt;
import com.bluepiit.etl.bolts.remote.ISTUpdateBolt;
import com.bluepiit.etl.bolts.remote.PackageInsertBolt;
import com.bluepiit.etl.models.KinesisInputModel;
import com.bluepiit.etl.spouts.InputSpout;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

public class DelhiveryKinesisTopology {
    private static final Logger LOG = LoggerFactory.getLogger(DelhiveryKinesisTopology.class);
    private static final String STREAM_NAME = "STREAM_NAME";
    private static final String INITIAL_POSITION_IN_STREAM_KEY = "INITIAL_POSITION_IN_STREAM_KEY";
    private static final String RECORD_RETRY_LIMIT = "RECORD_RETRY_LIMIT";
    private static final String REGION_KEY = "REGION_KEY";
    private static final String ZOOKEEPER_ENDPOINT_KEY = "ZOOKEEPER_ENDPOINT_KEY";
    private static final String ZOOKEEPER_PREFIX_EVENT_KEY = "ZOOKEEPER_PREFIX_EVENT_KEY";
    private static final String TOPOLOGY_NAME = "TOPOLOGY_NAME";

    private static String topologyName;
    private static String streamName;
    private static InitialPositionInStream initialPositionInStream;
    private static int recordRetryLimit;
    private static Regions region;
    private static String zookeeperEndpoint;
    private static String zookeeperEventPrefix;


    public static void main(String[] args) throws IllegalArgumentException, KeeperException, InterruptedException, AlreadyAliveException, InvalidTopologyException, IOException {

        String propertiesFile = null;
        String mode;

        if (args.length != 2) {
            mode = "LocalMode";
        } else {
            propertiesFile = args[0];
            mode = args[1];
        }
        TopologyBuilder inputBuilder = new TopologyBuilder();
        if (mode.equals("RemoteMode")) {
            configure(propertiesFile);
            final KinesisSpoutConfig eventConfig =
                    new KinesisSpoutConfig(streamName, zookeeperEndpoint).withZookeeperPrefix(zookeeperEventPrefix)
                            .withKinesisRecordScheme(new KinesisInputModel())
                            .withInitialPositionInStream(initialPositionInStream)
                            .withRecordRetryLimit(recordRetryLimit)
                            .withRegion(region)
                            .withMaxRecordsPerCall(500);



            final KinesisSpout eventSpout = new KinesisSpout(eventConfig, new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
            inputBuilder.setSpout(DelhiveryETLConstants.INPUT_SPOUT, eventSpout);
            inputBuilder.setBolt(DelhiveryETLConstants.PACKAGE_INSERT_PACKAGE, new PackageInsertBolt());
            inputBuilder.setBolt(DelhiveryETLConstants.IST_INSERT, new ISTInsertBolt());
            inputBuilder.setBolt(DelhiveryETLConstants.DISPATCH_INSERT, new DispatchInsertBolt());
            inputBuilder.setBolt(DelhiveryETLConstants.BAG_INSERT, new BagInsertBolt());
            
            /*Configuration for Update bolt of ist, dispatch and bag*/
           /*inputBuilder.setBolt(DelhiveryETLConstants.IST_UPDATE, new ISTUpdateBolt());
            inputBuilder.setBolt(DelhiveryETLConstants.DISPATCH_UPDATE, new DispatchUpdateBolt());
            inputBuilder.setBolt(DelhiveryETLConstants.BAG_UPDATE, new BagUpdateBolt());*/
          

        } else if (mode.equals("LocalMode")) {

            InputSpout eventSpout = new InputSpout();

            inputBuilder.setSpout(DelhiveryETLConstants.INPUT_SPOUT, eventSpout);         
      
            /*inputBuilder.setBolt(DelhiveryETLConstants.LOCAL_IST_INSERT_BOLT, new LocalISTInsertBolt())
            .globalGrouping(DelhiveryETLConstants.INPUT_SPOUT);*/
            
            inputBuilder.setBolt(DelhiveryETLConstants.LOCAL_BAG_INSERT_BOLT, new LocalBagInsertBolt())
            .globalGrouping(DelhiveryETLConstants.INPUT_SPOUT);
            inputBuilder.setBolt(DelhiveryETLConstants.LOCAL_DISPATCH_INSERT_BOLT, new LocalDispatchInsertBolt())
            .globalGrouping(DelhiveryETLConstants.INPUT_SPOUT);
            inputBuilder.setBolt(DelhiveryETLConstants.LOCAL_IST_INSERT_BOLT, new LocalISTInsertBolt())
            .globalGrouping(DelhiveryETLConstants.INPUT_SPOUT);
            
            /*Configuration for Update bolt of ist, dispatch and bag*/
           /* inputBuilder.setBolt(DelhiveryETLConstants.LOCAL_BAG_UPDATE_BOLT, new LocalBagUpdateBolt())
            .globalGrouping(DelhiveryETLConstants.INPUT_SPOUT);
            inputBuilder.setBolt(DelhiveryETLConstants.LOCAL_DISPATCH_UPDATE_BOLT, new LocalDispatchUpdateBolt())
            .globalGrouping(DelhiveryETLConstants.INPUT_SPOUT);
            inputBuilder.setBolt(DelhiveryETLConstants.LOCAL_IST_UPDATE_BOLT, new LocalISTUpdateBolt())
            .globalGrouping(DelhiveryETLConstants.INPUT_SPOUT);*/
            
        }


        Config topoConf = new Config();
        topoConf.setFallBackOnJavaSerialization(true);
        topoConf.setDebug(false);
        topoConf.put("accessMode", mode);
        topoConf.setMessageTimeoutSecs(60);
        topoConf.setMaxSpoutPending(500);

        if (mode.equals("LocalMode")) {
            LOG.info("Starting sample storm topology in LocalMode ...");
            new LocalCluster().submitTopology("test_spout", topoConf, inputBuilder.createTopology());
        } else if (mode.equals("RemoteMode")) {
            topoConf.setNumWorkers(7);
            LOG.info("Submitting sample topology " + topologyName + " to remote cluster.");
            LOG.info("Connecting to " + topoConf.toString() + "zookeper endpoint.");

            StormSubmitter.submitTopology(topologyName, topoConf, inputBuilder.createTopology());
        } else {
            LOG.info("Usage: " + DelhiveryKinesisTopology.class.getName() + " <propertiesFile> <LocalMode or RemoteMode>");
            System.exit(-1);
        }

    }

    private static void configure(String propertiesFile) throws IOException {
        FileInputStream inputStream = new FileInputStream(propertiesFile);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } finally {
            inputStream.close();
        }

        String topologyName1Override = properties.getProperty(TOPOLOGY_NAME);
        if (topologyName1Override != null) {
            topologyName = topologyName1Override;
        }
        LOG.info("Using topology name " + topologyName);

        String streamNameEvent = properties.getProperty(STREAM_NAME);
        if (streamNameEvent != null) {
            streamName = streamNameEvent;
        }
        LOG.info("Using stream name " + streamName);

        String initialPositionOverride = properties.getProperty(INITIAL_POSITION_IN_STREAM_KEY);
        if (initialPositionOverride != null) {
            initialPositionInStream = InitialPositionInStream.valueOf(initialPositionOverride);
        }
        LOG.info("Using initial position " + initialPositionInStream.toString() + " (if a checkpoint is not found).");

        String recordRetryLimitOverride = properties.getProperty(RECORD_RETRY_LIMIT);
        if (recordRetryLimitOverride != null) {
            recordRetryLimit = Integer.parseInt(recordRetryLimitOverride.trim());
        }
        LOG.info("Using recordRetryLimit " + recordRetryLimit);

        String regionOverride = properties.getProperty(REGION_KEY);
        if (regionOverride != null) {
            region = Regions.fromName(regionOverride);
        }
        LOG.info("Using region " + region.getName());

        String zookeeperEndpointOverride = properties.getProperty(ZOOKEEPER_ENDPOINT_KEY);
        if (zookeeperEndpointOverride != null) {
            zookeeperEndpoint = zookeeperEndpointOverride;
        }
        LOG.info("Using zookeeper endpoint " + zookeeperEndpoint);

        String zookeeperPrefixEventOverride = properties.getProperty(ZOOKEEPER_PREFIX_EVENT_KEY);

        if (zookeeperPrefixEventOverride != null) {
            zookeeperEventPrefix = zookeeperPrefixEventOverride;
        }

    }
}
