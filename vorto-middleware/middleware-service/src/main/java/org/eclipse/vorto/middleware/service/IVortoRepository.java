package org.eclipse.vorto.middleware.service;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.vorto.mapping.engine.model.spec.MappingSpecification;

public interface IVortoRepository {

	Optional<MappingSpecification> getById(String modelId);
	
	Collection<MappingSpecification> list();
}
