package org.eclipse.vorto.middleware.plugins;

import java.util.Map;

import org.eclipse.vorto.model.runtime.InfomodelValue;

public abstract class AbstractPlugin implements IPlugin {

	protected boolean started = false;
	
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

	/**
	 * Initializes the plugin, for example setting up a connection 
	 */
	public abstract void init();

	/**
	 * Cleans up the plugin, for example any connections to a DB 
	 */
	public abstract void destroy();
	
}
