package org.eclipse.vorto.middleware.internal.plugins;

import org.springframework.data.repository.CrudRepository;

public interface PluginRepository extends CrudRepository<PluginConfig, String>  {

}
