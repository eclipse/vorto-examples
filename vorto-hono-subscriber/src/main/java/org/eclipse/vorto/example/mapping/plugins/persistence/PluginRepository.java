package org.eclipse.vorto.example.mapping.plugins.persistence;

import org.springframework.data.repository.CrudRepository;

public interface PluginRepository extends CrudRepository<PluginConfig, String>  {

}
