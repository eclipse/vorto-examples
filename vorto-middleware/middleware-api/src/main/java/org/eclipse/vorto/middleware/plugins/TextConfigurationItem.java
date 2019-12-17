package org.eclipse.vorto.middleware.plugins;

public class TextConfigurationItem extends ConfigurationItem {
	
	private String value = null;
	
	public TextConfigurationItem(String key, String label, String value) {
		super(key, label);
		this.value = value;
	}
	
	public TextConfigurationItem() {}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}
