package org.eclipse.vorto.middleware.internal.service;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.model.runtime.InfomodelValue;

public interface IMappingService {
	
	Collection<IMappingSpecification> listMappings();
	
	Optional<IMappingSpecification> getMappingForModelId(ModelId modelId);

	InfomodelValue map(ModelId modelId, Object payload);
}
