package com.bluepiit.etl.bolts.remote;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bluepiit.etl.DelhiveryETLConstants;
import com.bluepiit.etl.models.KinesisInputModel;

import backtype.storm.Constants;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class InputJSONBackupBolt extends BaseBasicBolt implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(InputJSONBackupBolt.class);
    private AmazonS3 s3client = null;
    File file = new File("backup.json");
    int counter = 0;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
        s3client = new AmazonS3Client(new DefaultAWSCredentialsProviderChain());
    }

    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {

        FileWriter fw;
        if (isTickTuple(tuple)) {
            try {

                if (!file.exists()) return;
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(DelhiveryETLConstants.TD_DATE_TIME_FORMAT_NO_SECONDS);

                String fileName = sdf.format(date);

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(0);
                InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
                if (!s3client.doesBucketExist(DelhiveryETLConstants.BUCKET_NAME)) return;

                Bucket bucket = s3client.createBucket(new CreateBucketRequest(DelhiveryETLConstants.BUCKET_NAME));
                LOG.info("Bucket is created successfully with name " + bucket.getName());

                ObjectListing objectListing = s3client.listObjects(new ListObjectsRequest().withBucketName(DelhiveryETLConstants.BUCKET_NAME).withPrefix(fileName));
                if (objectListing.getObjectSummaries().isEmpty()) {
                    PutObjectRequest putObjectRequest = new PutObjectRequest(DelhiveryETLConstants.BUCKET_NAME, fileName, emptyContent, metadata);
                    s3client.putObject(putObjectRequest);
                    fw = new FileWriter(file.getAbsoluteFile(), false);
                }
                s3client.putObject(new PutObjectRequest(DelhiveryETLConstants.BUCKET_NAME, fileName, new File("backup.json")));


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            byte[] payload = (byte[]) tuple.getValueByField(KinesisInputModel.FIELD_RECORD_DATA);
            try {
                file.createNewFile();
                fw = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(counter + new String(payload).concat("\n"));
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            counter++;
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // No Output
    }

    private boolean isTickTuple(Tuple tuple) {
        String sourceComponent = tuple.getSourceComponent();
        String sourceStreamId = tuple.getSourceStreamId();
        return sourceComponent.equals(Constants.SYSTEM_COMPONENT_ID) && sourceStreamId.equals(Constants.SYSTEM_TICK_STREAM_ID);
    }

}
