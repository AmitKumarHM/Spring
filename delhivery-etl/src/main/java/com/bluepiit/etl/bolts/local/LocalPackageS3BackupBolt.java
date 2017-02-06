package com.bluepiit.etl.bolts.local;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.bluepiit.etl.bucket.DelhiveryEtlBucketImpl;
import com.bluepiit.etl.models.KinesisInputModel;
import com.bluepiit.etl.utils.DelhiveryUtils;
import com.bluepiit.etl.utils.S3Util;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class LocalPackageS3BackupBolt extends BaseBasicBolt {

	private static final long serialVersionUID = -4943353042845766381L;
	private static final Logger LOG = LoggerFactory.getLogger(LocalPackageS3BackupBolt.class);

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		 byte[] payload = (byte[]) input.getValueByField(KinesisInputModel.FIELD_RECORD_DATA);
		 ObjectMetadata metadata = new ObjectMetadata();
		 metadata.setContentLength(payload.length);
             if (!DelhiveryUtils.isNULL(payload)) {
            	LOG.info("Tuple is read from KinesisInputModel");
            	InputStream emptyContent = new ByteArrayInputStream(payload);
            	S3Util s3Util=new S3Util();
            	s3Util.createBucket(DelhiveryEtlConstants.BUCKET_NAME);
            	DelhiveryEtlBucketImpl delhiveryEtlBucketImpl=new DelhiveryEtlBucketImpl();
            	s3Util.save(DelhiveryEtlConstants.BUCKET_NAME, delhiveryEtlBucketImpl.keyName()+DelhiveryEtlConstants.BACKSLASH+delhiveryEtlBucketImpl.fileName(), emptyContent, metadata);
             }
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("status", "packageList"));
	}

	
	
	public static void main(String...strings){
		 byte[] payload = "{'name':'amit'}".getBytes();
		 ObjectMetadata metadata = new ObjectMetadata();
		 metadata.setContentLength(payload.length);
             if (!DelhiveryUtils.isNULL(payload)) {
            	LOG.info("Tuple is read from KinesisInputModel");
            	InputStream emptyContent = new ByteArrayInputStream(payload);
            	S3Util s3Util=new S3Util();
            	s3Util.createBucket(DelhiveryEtlConstants.BUCKET_NAME);
            	DelhiveryEtlBucketImpl delhiveryEtlBucketImpl=new DelhiveryEtlBucketImpl();
            	System.out.println(delhiveryEtlBucketImpl.keyName());
            	s3Util.listOfKeys(DelhiveryEtlConstants.BUCKET_NAME, delhiveryEtlBucketImpl.keyName()+DelhiveryEtlConstants.BACKSLASH);
            	s3Util.save(DelhiveryEtlConstants.BUCKET_NAME, delhiveryEtlBucketImpl.keyName()+DelhiveryEtlConstants.BACKSLASH+delhiveryEtlBucketImpl.fileName(), emptyContent, metadata);
            	s3Util.listOfObject(DelhiveryEtlConstants.BUCKET_NAME, delhiveryEtlBucketImpl.keyName()+DelhiveryEtlConstants.BACKSLASH);
             }	
	}
	
	
}
