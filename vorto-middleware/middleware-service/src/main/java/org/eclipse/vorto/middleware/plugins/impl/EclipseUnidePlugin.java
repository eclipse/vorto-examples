package org.eclipse.vorto.middleware.plugins.impl;

import java.util.Collections;
import java.util.Map;

import org.eclipse.vorto.middleware.plugins.AbstractPlugin;
import org.eclipse.vorto.middleware.plugins.ExecutionContext;
import org.eclipse.vorto.middleware.plugins.config.TextConfigurationItem;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.springframework.stereotype.Component;

@Component
public class EclipseUnidePlugin extends AbstractPlugin {

	@Override
	public String getId() {
		return "PPM";
	}

	@Override
	public String getName() {
		return "Eclipse Unide - Device Measurement API";
	}

	@Override
	public String getDescription() {
		return "Publishes harmonized device measurements to an Eclipse Unide server.";
	}

	@Override
	public String getImageUrl() {
		return "https://www.eclipse.org/unide/logo.svg";
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
