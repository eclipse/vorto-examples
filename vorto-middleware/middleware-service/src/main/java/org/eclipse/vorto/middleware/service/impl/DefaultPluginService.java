package org.eclipse.vorto.middleware.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.eclipse.vorto.middleware.plugins.AbstractPlugin;
import org.eclipse.vorto.middleware.plugins.IPlugin;
import org.eclipse.vorto.middleware.plugins.config.TextConfigurationItem;
import org.eclipse.vorto.middleware.plugins.persistence.PluginConfig;
import org.eclipse.vorto.middleware.plugins.persistence.PluginRepository;
import org.eclipse.vorto.middleware.service.IPluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;

@Service
public class DefaultPluginService implements IPluginService {

	@Autowired
	private List<IPlugin> plugins = new ArrayList<IPlugin>();

	@Autowired
	private PluginRepository pluginRepository;

	@Override
	public Collection<IPlugin> listPlugins() {
		return plugins;
	}

	@Override
	public Collection<IPlugin> startedPlugins() {
		return plugins.stream().filter(plugin -> plugin.isStarted()).collect(Collectors.toList());
	}

	@Override
	public Optional<IPlugin> getPlugin(String id) {
		return plugins.stream().filter(plugin -> plugin.getId().equalsIgnoreCase(id)).findAny();
	}

	@Override
	public void applyConfiguration(String pluginId, Map<String, TextConfigurationItem> configuration) {
		Optional<IPlugin> plugin = getPlugin(pluginId);

		if (!plugin.isPresent()) {
			throw new IllegalArgumentException("Plugin with specified ID does not exist");
		}

		if (plugin.get().isStarted()) {
			stop(pluginId);
			((AbstractPlugin) plugin.get()).setConfiguration(configuration);
			start(pluginId);
		} else {
			((AbstractPlugin) plugin.get()).setConfiguration(configuration);
		}

		XStream xstream = new XStream();

		// saving configuration to DB
		pluginRepository.save(new PluginConfig(pluginId, xstream.toXML(configuration), plugin.get().isStarted()));
	}

	@PostConstruct
	public void restartPlugins() {
		XStream xstream = new XStream();

		this.listPlugins().stream().forEach(plugin -> {
			PluginConfig configuration = pluginRepository.findOne(plugin.getId());
			if (configuration != null) {
				@SuppressWarnings("unchecked")
				Map<String, TextConfigurationItem> configurationItems = (Map<String, TextConfigurationItem>) xstream
						.fromXML(configuration.getConfigurationXml());
				((AbstractPlugin) plugin).setConfiguration(configurationItems);
				((AbstractPlugin) plugin).setIsStarted(configuration.isStarted());
			}

			if (plugin.isStarted()) {
				start(plugin.getId());
			}
		});
	}

	@Override
	public IPlugin start(String pluginId) {
		Optional<IPlugin> _plugin = getPlugin(pluginId);

		if (!_plugin.isPresent()) {
			throw new IllegalArgumentException("Plugin with specified ID does not exist");
		}
		
		AbstractPlugin plugin = (AbstractPlugin)_plugin.get();
		
		if (plugin.isStarted()) { // restart plugin by re-initializing configuration
			plugin.destroy();
			plugin.init();
		} else {
			plugin.init();
		}
		
		return plugin;
	}

	@Override
	public IPlugin stop(String pluginId) {
		Optional<IPlugin> _plugin = getPlugin(pluginId);

		if (!_plugin.isPresent()) {
			throw new IllegalArgumentException("Plugin with specified ID does not exist");
		}
		
		AbstractPlugin plugin = (AbstractPlugin)_plugin.get();
		
		if (plugin.isStarted()) {
			plugin.destroy();
		}
		
		return plugin;
	}

}
