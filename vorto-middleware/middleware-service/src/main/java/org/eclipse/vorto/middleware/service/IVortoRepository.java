package org.eclipse.vorto.middleware.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.vorto.mapping.engine.model.spec.MappingSpecification;
import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.repository.client.ModelInfo;

public interface IVortoRepository {

	Optional<MappingSpecification> getById(String modelId);
	
	Collection<ModelInfo> discoverUserInfomodels(List<ModelId> excludes, String author);
}
