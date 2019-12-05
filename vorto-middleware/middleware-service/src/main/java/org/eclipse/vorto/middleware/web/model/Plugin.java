/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.middleware.web.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.vorto.middleware.plugins.IPlugin;
import org.eclipse.vorto.middleware.plugins.config.ConfigurationItem;
import org.eclipse.vorto.middleware.plugins.config.TextConfigurationItem;

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
