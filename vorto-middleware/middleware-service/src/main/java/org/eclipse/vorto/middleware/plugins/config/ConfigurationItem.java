package org.eclipse.vorto.middleware.plugins.config;

public abstract class ConfigurationItem {
	
	private String key;
	private String label;
	
	public ConfigurationItem(String key, String label) {
		super();
		this.key = key;
		this.label = label;
	}
	
	public ConfigurationItem() {}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	
}
