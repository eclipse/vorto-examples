package org.eclipse.vorto.middleware.plugins;

import java.util.Map;

import org.eclipse.vorto.middleware.plugins.config.TextConfigurationItem;
import org.eclipse.vorto.model.runtime.InfomodelValue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractPlugin implements IPlugin {

	protected boolean started = false;
	
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public boolean isStarted() {
		return this.started;
	}

	public void setIsStarted(boolean started) {
		this.started = started;
	}

	public void execute(InfomodelValue value, ExecutionContext context) {
		context.getLogger().info(context.getDeviceId(),"Normalized telemetry payload: "+gson.toJson(value.serialize()));
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
