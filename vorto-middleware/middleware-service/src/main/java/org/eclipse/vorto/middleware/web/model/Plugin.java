package org.eclipse.vorto.middleware.web.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.vorto.middleware.plugins.ConfigurationItem;
import org.eclipse.vorto.middleware.plugins.IPlugin;
import org.eclipse.vorto.middleware.plugins.TextConfigurationItem;

public class Plugin {

	public String id;
	public String name;
	public String description;
	public String imageUrl;
	public boolean started;
	
	public Map<String, TextConfigurationItem> configuration = new HashMap<String, TextConfigurationItem>();
	
	
	public static Plugin of(IPlugin refPlugin) {
		Plugin plugin = new Plugin();
		plugin.id = refPlugin.getId();
		plugin.name = refPlugin.getName();
		plugin.description = refPlugin.getDescription();
		plugin.imageUrl = refPlugin.getImageUrl();
		plugin.started = refPlugin.isStarted();
		
		for (String key : refPlugin.getConfiguration().keySet()) {
			ConfigurationItem item = refPlugin.getConfiguration().get(key);
			plugin.configuration.put(key,(TextConfigurationItem)item);
		}
		
		return plugin;
	}
	
	private Plugin() {
		
	}
}
