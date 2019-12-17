package org.eclipse.vorto.middleware.internal.service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.eclipse.vorto.middleware.plugins.IPlugin;
import org.eclipse.vorto.middleware.plugins.TextConfigurationItem;


public interface IPluginService {

	/**
	 * lists all plugins that are available in the system
	 * @return
	 */
	Collection<IPlugin> listPlugins();
	
	/**
	 * Gets all plugins that are currently started
	 * @return
	 */
	Collection<IPlugin> startedPlugins();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<IPlugin> getPlugin(String id);
	
	/**
	 * Applies the configuration for the given plugin.
	 * 
	 * @PostCondition configuration set on the plugin as well as saved to persistence
	 * @param pluginId
	 * @param configuration
	 */
	void applyConfiguration(String pluginId, Map<String,TextConfigurationItem> configuration);
	
	
	/**
	 * starts the plugin
	 * @param pluginId
	 */
	IPlugin start(String pluginId);
	
	/**
	 * stops the plugin
	 * @param pluginId
	 */
	IPlugin stop(String pluginId);

}
