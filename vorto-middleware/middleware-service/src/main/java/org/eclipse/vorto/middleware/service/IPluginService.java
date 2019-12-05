
/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional information regarding copyright
 * ownership.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which is available at https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */package org.eclipse.vorto.middleware.service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.eclipse.vorto.middleware.plugins.IPlugin;
import org.eclipse.vorto.middleware.plugins.config.TextConfigurationItem;


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
