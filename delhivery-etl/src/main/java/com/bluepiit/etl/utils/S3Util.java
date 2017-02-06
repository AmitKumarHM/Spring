package com.bluepiit.etl.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.bluepiit.etl.utils.constant.DelhiveryEtlConstants;



/**
 * The Class S3Util
 * Provide basic feature for uploading,reading,removing Object from S3 Bucket.
 */
public final class S3Util {


	private AmazonS3 awsS3 = null;
	private Region region = null;

	private static final Logger LOG =LoggerFactory.getLogger(S3Util.class);

	/**
	 * Instantiates a new S3Util.
	 *
	 * @param strCredentialFilePath the str credential file path
	 * @param rgRegion the rg region
	 */
	public S3Util(String strCredentialFilePath, Regions rgRegion) {

		try {	
			ClientConfiguration cc = new ClientConfiguration();
			cc.setMaxErrorRetry(5);
			cc.setConnectionTimeout(300000);

			awsS3 = new AmazonS3Client(
					new ClasspathPropertiesFileCredentialsProvider(strCredentialFilePath), cc);

			region = Region.getRegion(rgRegion);
			awsS3.setRegion(region);
		} catch (IllegalArgumentException e) {
			LOG.error("S3 connection error " + e.getMessage());
		}

	}

	/**
	 * Instantiates a new S3Util .
	 */
	public S3Util() {

		try {
			awsS3 = new AmazonS3Client(
					new DefaultAWSCredentialsProviderChain());
			region = Region.getRegion(Regions.US_EAST_1);
			awsS3.setRegion(region);
		} catch (IllegalArgumentException e) {
			LOG.error("S3 connection error " + e.getMessage());
		}

	}

	/**
	 * emptyBucket.
	 * This will empty bucket using bucket name in S3
	 *
	 * @param bucketName the name of bucket
	 */
	public boolean emptyBucket(String bucketName){
		if(awsS3.doesBucketExist(bucketName)){
			try{
			DeleteObjectsResult deleteObjectsResult=awsS3.deleteObjects(new DeleteObjectsRequest(bucketName));
			return (DelhiveryUtils.isNULL(deleteObjectsResult));
			}catch (AmazonServiceException e) {
				LOG.error("AmazonServiceException Error Message: " +e.getMessage()+"\nHTTP Status Code: "+e.getStatusCode()+"\nAWS Error Code: "+e.getErrorCode()+"\nError Type: "+e.getErrorType()+"\nRequest ID: "+e.getRequestId());
				return false;
			} catch (AmazonClientException e) {
				LOG.error("AmazonClientException Error Message: " +e.getMessage());
				return false;
			}
		}else{
			LOG.info("Bucket is not there with name "+bucketName);
			return false;
		}
		
	}
	
	/**
	 * delete Object
	 * This will delete object of specified key from bucket in S3
	 *
	 * @param bucketName the name of bucket
	 */
	public boolean deleteObject(String bucketName,String keyName){
		if(awsS3.doesBucketExist(bucketName)){
			try{
			awsS3.deleteObject(new DeleteObjectRequest(bucketName,keyName));
			LOG.info("Specified is delete from bucket");
			return true;
			}catch (AmazonServiceException e) {
				LOG.error("AmazonServiceException Error Message: " +e.getMessage()+"\nHTTP Status Code: "+e.getStatusCode()+"\nAWS Error Code: "+e.getErrorCode()+"\nError Type: "+e.getErrorType()+"\nRequest ID: "+e.getRequestId());
				return false;
			} catch (AmazonClientException e) {
				LOG.error("AmazonClientException Error Message: " +e.getMessage());
				return false;
			}
		}else{
			LOG.info("Bucket is not there with name "+bucketName);
			return false;
		}
	}
	
	/**
	 * createBucket.
	 * This will create bucket in specified bucket name in S3
	 *
	 * @param bucketName the name of bucket
	 */
	public boolean createBucket(String bucketName){
		if(!awsS3.doesBucketExist(bucketName)){
			Bucket bucket=awsS3.createBucket(new CreateBucketRequest(
					bucketName));
			LOG.info("Bucket is created successfully with name "+bucket.getName());
			return (DelhiveryUtils.isNULL(bucket));
		}
		LOG.info("Bucket is already there with name "+bucketName);
		return false;
	}

	/**
	 * listOfKeys.
	 * List of files in the particular bucket
	 *
	 * @param bucketName the name of bucket
	 */
	public void listOfKeys(String bucketName,String keyName){
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
				.withBucketName(bucketName).withDelimiter(DelhiveryEtlConstants.BACKSLASH).withPrefix(keyName);
		ObjectListing objectListing;
		do {
			objectListing = awsS3.listObjects(listObjectsRequest);
			for (S3ObjectSummary objectSummary : 
				objectListing.getObjectSummaries()) {
				LOG.info( " - " + objectSummary.getKey() + "  " +
						"(size = " + objectSummary.getSize() + ")");
			}
			listObjectsRequest.setMarker(objectListing.getNextMarker());
		} while (objectListing.isTruncated());
	}


	/**
	 * listOfObject.
	 * List of Object in the particular bucket and key name
	 *
	 * @param bucketName the name of bucket
	 */
	public void listOfObject(String bucketName,String keyName){
		S3Object object = awsS3.getObject(
				new GetObjectRequest(bucketName, keyName));
		InputStream objectData = object.getObjectContent();
		//Process the objectData stream.
		try {
			displayTextInputStream(objectData);
			objectData.close();
		} catch (IOException e) {
		 LOG.error("S3 Caught an IOException " + e.getMessage());		
		}
	}


	/**
	 * save.
	 *
	 * @param strBucket the string bucket
	 * @param strKey the sting key
	 * @param flUpload the file upload
	 * @return true, if successful
	 */
	public boolean save(String strBucket, String strKey, File flUpload) {

		boolean blRet=false;
		try {
			PutObjectRequest putObj = new PutObjectRequest(strBucket, strKey,flUpload);
			putObj.setCannedAcl(CannedAccessControlList.PublicRead);
			awsS3.putObject(putObj);
			blRet=true;
			LOG.info(flUpload.getName() + " Uploaded successfully on S3");
		} catch (AmazonServiceException e) {
			LOG.error("AmazonServiceException Error Message: " +e.getMessage()+"\nHTTP Status Code: "+e.getStatusCode()+"\nAWS Error Code: "+e.getErrorCode()+"\nError Type: "+e.getErrorType()+"\nRequest ID: "+e.getRequestId());
		} catch (AmazonClientException e) {
			LOG.error("AmazonClientException Error Message: " +e.getMessage());
		}
		return blRet;
	}

	/**
	 * save.
	 *
	 * @param strBucket the string bucket
	 * @param strKey the sting key
	 * @param inputStream the Input Stream
	 * @param objectMetadata the metadata of object 
	 * @return true, if successful
	 */
	public boolean save(String strBucket, String strKey, InputStream inputStream,ObjectMetadata objectMetadata) {

		boolean blRet=false;
		try {
			PutObjectRequest putObj = new PutObjectRequest(strBucket, strKey,inputStream,objectMetadata);
			putObj.setCannedAcl(CannedAccessControlList.PublicRead);
			PutObjectResult putObjectResult=awsS3.putObject(putObj);
			blRet=true;
			LOG.info("Data Written sucessfully on S3 and Etag:"+putObjectResult.getETag());
		} catch (AmazonServiceException e) {
			LOG.error("AmazonServiceException Error Message: " +e.getMessage()+"\nHTTP Status Code: "+e.getStatusCode()+"\nAWS Error Code: "+e.getErrorCode()+"\nError Type: "+e.getErrorType()+"\nRequest ID: "+e.getRequestId());
		} catch (AmazonClientException e) {
			LOG.error("S3 Caught an AmazonClientException " + e.getMessage());
		}

		return blRet;
	}
	
	

	/**
	 * listOfKeys.
	 * Display the list of Object using InputStream
	 *
	 * @param input the InputStream
	 */
	private void displayTextInputStream(InputStream input)
		    throws IOException {
		    	// Read one text line at a time and display.
		        BufferedReader reader = new BufferedReader(new 
		        		InputStreamReader(input));
		        while (true) {
		            String line = reader.readLine();
		            if (line == null) break;
		            System.out.println("    " + line);
		        }
		        System.out.println();
		    }
}
