package org.eclipse.vorto.middleware.plugins.impl;

import java.util.Collections;
import java.util.Map;

import org.eclipse.vorto.middleware.plugins.AbstractPlugin;
import org.eclipse.vorto.middleware.plugins.ExecutionContext;
import org.eclipse.vorto.middleware.plugins.config.TextConfigurationItem;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.springframework.stereotype.Component;

@Component
public class AzureIoTPlugin extends AbstractPlugin {

	@Override
	public String getId() {
		return "Azure_IoT";
	}

	@Override
	public String getName() {
		return "Azure IoT - Digital Twin API";
	}

	@Override
	public String getDescription() {
		return "Publishes harmonized device telemetry data to an Azure IoT Digital Twin Service.";
	}

	@Override
	public String getImageUrl() {
		return "https://mspoweruser.com/wp-content/uploads/2017/09/azure-1.png";
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


