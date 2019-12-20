package org.eclipse.vorto.middleware.plugins.kinesis;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.vorto.middleware.plugins.AbstractPlugin;
import org.eclipse.vorto.middleware.plugins.ExecutionContext;
import org.eclipse.vorto.middleware.plugins.TextConfigurationItem;
import org.eclipse.vorto.model.runtime.InfomodelValue;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;

public class AWSKinesisPlugin extends AbstractPlugin {
	
	private AmazonKinesis kinesisClient;
	
	private String accessKey;
	
	private String secretKey;
	
	private String streamName;

	@Override
	public String getId() {
		return "AWS_KINESIS";
	}

	@Override
	public String getName() {
		return "AWS Kinesis Data Stream";
	}

	@Override
	public String getDescription() {
		return "Publishes to AWS Kinesis Data Stream.";
	}

	@Override
	public String getImageUrl() {
		return "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Amazon_Web_Services_Logo.svg/2880px-Amazon_Web_Services_Logo.svg.png";
	}

	@Override
	public Map<String, TextConfigurationItem> getConfiguration() {
		Map<String, TextConfigurationItem> configuration = new HashMap<>();
		configuration.put("accessKey", new TextConfigurationItem("accessKey", "accessKey", this.accessKey));
		configuration.put("streamName", new TextConfigurationItem("streamName", "streamName", this.streamName));
		return configuration;
	}

	public void setConfiguration(Map<String, TextConfigurationItem> configuration) {
		//NOOP
	}

	@Override
	protected void doExecute(InfomodelValue value, ExecutionContext context) {
		List<PutRecordsRequestEntry> entries = value.getProperties().keySet().stream().map(property -> {
		    PutRecordsRequestEntry entry = new PutRecordsRequestEntry(); 
		    entry.setData(ByteBuffer.wrap((new StreamData(value.get(property), property, context.getDeviceId())).serializeToString().getBytes())); 
		    entry.setPartitionKey(Long.toString(new Date().getTime()));
		    return entry;
		}).collect(Collectors.toList());
		
		PutRecordsRequest createRecordsRequest = new PutRecordsRequest();
		createRecordsRequest.setStreamName(this.streamName);
		createRecordsRequest.setRecords(entries);
		
		kinesisClient.putRecords(createRecordsRequest);
	}

	@Override
	public void start() {
		logger.info("Trying to start plugin: "+getId());
		if (this.accessKey == null) {
			throw new CannotStartPluginException("Access key is missing");
		} else if(this.secretKey == null) {
			throw new CannotStartPluginException("Secret key is missing");
		} else if(this.streamName == null) {
			throw new CannotStartPluginException("Stream Name is missing");
		}
		
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
	    kinesisClient = AmazonKinesisClientBuilder.standard()
	      .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
	      .withRegion(Regions.EU_CENTRAL_1)
	      .build();
	    
	    this.setIsStarted(true);
		logger.info("Started successfully");

	}

	@Override
	public void stop() {
		logger.info("Stopping plugin: "+getId());
		this.kinesisClient = null;
		this.setIsStarted(false);
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
}

