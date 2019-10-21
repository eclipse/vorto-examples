package org.eclipse.vorto.middleware.plugins.persistence;

import org.springframework.data.repository.CrudRepository;

public interface PluginRepository extends CrudRepository<PluginConfig, String>  {

}
