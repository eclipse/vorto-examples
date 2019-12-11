package org.eclipse.vorto.middleware.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.vorto.mapping.engine.model.spec.MappingSpecification;
import org.eclipse.vorto.middleware.service.IVortoRepository;
import org.eclipse.vorto.repository.client.ModelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VortoRepositoryAdapter implements IVortoRepository {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final String BASE_URL = "https://vorto.eclipse.org";
	
	@Override
	public Optional<MappingSpecification> getById(String modelId) {
		ResponseEntity<MappingSpecification> spec = restTemplate.getForEntity(BASE_URL+"/rest/mappings/specifications/{modelId}", MappingSpecification.class,modelId);
		if (spec.getStatusCode().is2xxSuccessful()) {
			return Optional.of(spec.getBody());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Collection<MappingSpecification> list() {
		List<MappingSpecification> allSpecs = new ArrayList<MappingSpecification>();
		ResponseEntity<ModelInfo[]> informationModels = restTemplate.getForEntity(BASE_URL+"/api/v1/search/models?expression=InformationModel", ModelInfo[].class);
		Arrays.asList(informationModels.getBody()).stream().forEach(infomodel -> {
			@SuppressWarnings("rawtypes")
			ResponseEntity<Map> exists = restTemplate.getForEntity(BASE_URL+"/rest/mappings/{modelId}/exists", Map.class,infomodel.getId().getPrettyFormat());
			if (Boolean.parseBoolean((String)exists.getBody().get("exists"))) {
				allSpecs.add(getById(infomodel.getId().getPrettyFormat()).get());
			}
		});
		return allSpecs;
	}

}
