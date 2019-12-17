package org.eclipse.vorto.middleware.plugins.kinesis;

import java.util.Collections;
import java.util.Map;

import org.eclipse.vorto.middleware.plugins.AbstractPlugin;
import org.eclipse.vorto.middleware.plugins.ExecutionContext;
import org.eclipse.vorto.middleware.plugins.TextConfigurationItem;
import org.eclipse.vorto.model.runtime.InfomodelValue;

public class AWSKinesisPlugin extends AbstractPlugin {

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
		return Collections.emptyMap();
	}

	public void setConfiguration(Map<String, TextConfigurationItem> configuration) {
		//NOOP

	}

	@Override
	protected void doExecute(InfomodelValue value, ExecutionContext context) {
		//FIXME
	}

	@Override
	public void init() {
		//
	}

	@Override
	public void destroy() {

	}

}

