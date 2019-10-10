package org.eclipse.vorto.middleware.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.vorto.middleware.plugins.IPlugin;
import org.eclipse.vorto.middleware.plugins.config.TextConfigurationItem;
import org.eclipse.vorto.middleware.service.IPluginService;
import org.eclipse.vorto.middleware.web.model.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/plugins")
public class PluginResource {

	@Autowired
	private IPluginService pluginService;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Plugin> getPlugins() {
		List<Plugin> plugins = new ArrayList<Plugin>();
		pluginService.listPlugins().stream().forEach(p -> {
			plugins.add(Plugin.of(p));
		});
		return plugins;
	}

	@RequestMapping(value = "/{pluginId}", method = RequestMethod.GET)
	public ResponseEntity<Plugin> getPlugin(@PathVariable String pluginId) {
		Optional<IPlugin> plugin = this.pluginService.getPlugin(pluginId);
		if (!plugin.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Plugin>(Plugin.of(plugin.get()), HttpStatus.ACCEPTED);
		}
	}

	@RequestMapping(value = "/{pluginId}", method = RequestMethod.PUT)
	public ResponseEntity<Plugin> applyConfiguration(@PathVariable String pluginId,
			@RequestBody Map<String, TextConfigurationItem> configuration) {

		Optional<IPlugin> plugin = this.pluginService.getPlugin(pluginId);
		if (!plugin.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			this.pluginService.applyConfiguration(pluginId, configuration);
			return new ResponseEntity<>(Plugin.of(this.pluginService.getPlugin(pluginId).get()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{pluginId}/start", method = RequestMethod.PUT)
	public ResponseEntity<Plugin> startPlugin(@PathVariable String pluginId) {
		try {
			return new ResponseEntity<>(Plugin.of(this.pluginService.start(pluginId)), HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/{pluginId}/stop", method = RequestMethod.PUT)
	public ResponseEntity<Plugin> disablePlugin(@PathVariable String pluginId) {
		try {
			return new ResponseEntity<>(Plugin.of(this.pluginService.stop(pluginId)), HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
