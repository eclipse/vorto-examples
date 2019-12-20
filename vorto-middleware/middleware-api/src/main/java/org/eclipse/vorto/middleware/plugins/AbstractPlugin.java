package org.eclipse.vorto.middleware.plugins;

import java.util.Map;

import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPlugin implements IPlugin {

	protected boolean started = false;
	
	protected static final Logger logger = LoggerFactory.getLogger(AbstractPlugin.class);

	
	@Override
	public boolean isStarted() {
		return this.started;
	}

	public void setIsStarted(boolean started) {
		this.started = started;
	}

	public void execute(InfomodelValue value, ExecutionContext context) {
		doExecute(value, context);
		
	}
	
	protected abstract void doExecute(InfomodelValue value, ExecutionContext context);


	public abstract void setConfiguration(Map<String, TextConfigurationItem> configuration);
	
}
