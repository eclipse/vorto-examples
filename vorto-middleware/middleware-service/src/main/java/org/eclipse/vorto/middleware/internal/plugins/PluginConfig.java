package org.eclipse.vorto.middleware.internal.plugins;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Entity
public class PluginConfig {

	@Id
	private String id;
	
	private boolean started;

	@NotNull
	@Lob
	private String configurationXml;
	
	public PluginConfig() {
		
	}
	
	public PluginConfig(String id, String configurationXml, boolean started) {
		this.id = id;
		this.configurationXml = configurationXml;
		this.started = started;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigurationXml() {
		return configurationXml;
	}

	public void setConfigurationXml(String configurationXml) {
		this.configurationXml = configurationXml;
	}

	public boolean isStarted() {
		return started;
	}

	public void setIsStarted(boolean started) {
		this.started = started;
	}
	
	
	
	
}
