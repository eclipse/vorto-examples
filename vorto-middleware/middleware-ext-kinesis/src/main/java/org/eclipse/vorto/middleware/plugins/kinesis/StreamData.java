package org.eclipse.vorto.middleware.plugins.kinesis;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.vorto.model.runtime.FunctionblockValue;

import com.google.gson.Gson;

public class StreamData {

	private FunctionblockValue value;
	private String metaProperty;
	private String deviceId;
	
	public StreamData(FunctionblockValue value, String metaProperty, String deviceId) {
		super();
		this.value = value;
		this.metaProperty = metaProperty;
		this.deviceId = deviceId;
	}
	
	public String serializeToString() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("payload", value.serialize());
		result.put("modelId", value.getMeta().getId().getPrettyFormat());
		result.put("tag", metaProperty);
		result.put("deviceId", deviceId);
		return new Gson().toJson(result);
	}
	
}
