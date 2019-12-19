package org.eclipse.vorto.middleware.internal.service;

import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.model.runtime.InfomodelValue;

public interface IMappingService {
	
	InfomodelValue map(ModelId modelId, Object payload);
}
