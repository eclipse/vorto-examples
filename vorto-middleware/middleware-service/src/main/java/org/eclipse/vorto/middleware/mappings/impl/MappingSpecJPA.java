package org.eclipse.vorto.middleware.mappings.impl;

import org.eclipse.vorto.middleware.mappings.MappingConfig;
import org.springframework.data.repository.CrudRepository;

public interface MappingSpecJPA extends CrudRepository<MappingConfig, String> {

}
