package org.eclipse.vorto.middleware.internal.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.vorto.mapping.engine.model.spec.MappingSpecification;
import org.eclipse.vorto.middleware.internal.service.IVortoRepository;
import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.repository.client.ModelInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class VortoRepositoryAdapter implements IVortoRepository {

	@Autowired
	@Qualifier("repositoryTemplate")
	private RestTemplate restTemplate;

	private static final String BASE_URL = "https://vorto.eclipse.org";
	
	private static final Logger LOG = LoggerFactory.getLogger(VortoRepositoryAdapter.class);

	@Override
	public Optional<MappingSpecification> getById(String modelId) {
		LOG.info("Trying to resolve mapping for model Id "+modelId); 
		try {
			@SuppressWarnings("rawtypes")
			ResponseEntity<Map> exists = restTemplate.getForEntity(BASE_URL + "/rest/mappings/specifications/{modelId}/exists",
					Map.class, modelId);
			if ((Boolean) exists.getBody().get("exists")) {
				LOG.info("Mapping exists for model "+modelId+". Downloading mapping specification"); 
				ResponseEntity<MappingSpecification> spec = restTemplate.getForEntity(
						BASE_URL + "/rest/mappings/specifications/{modelId}", MappingSpecification.class, modelId);
				if (spec.getStatusCode().is2xxSuccessful()) {
					LOG.info("Mapping successfully downloaded."); 
					return Optional.of(spec.getBody());
				}
			} else {
				LOG.info("Mapping does not exist."); 
			}
		} catch (HttpClientErrorException ex) {
			LOG.error("Fatal problem occured when downloading mapping spec",ex); 
		}
		
		return Optional.empty();
	}
	
	@Override
	public Collection<ModelInfo> discoverUserInfomodels(List<ModelId> excludes, String author) {
		ResponseEntity<ModelInfo[]> informationModels = restTemplate
				.getForEntity(BASE_URL + "/api/v1/search/models?expression=InformationModel author:{author}", ModelInfo[].class,author);
		
		LOG.info("Found "+informationModels.getBody().length+ " information models for user "+author);
		return Arrays.asList(informationModels.getBody()).stream().filter(infomodel -> !excludes.contains(infomodel.getId()) || !infomodel.getState().equalsIgnoreCase("deprecated")).collect(Collectors.toList());
	}
}
