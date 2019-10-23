package org.eclipse.vorto.middleware.config;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.middleware.service.impl.DefaultMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class MappingSpecsConfiguration {

	@Autowired
	private DefaultMappingService mappingService;
	
	
	@PostConstruct
	public void addMappingSpecs() throws Exception {
		mappingService.addMappingSpec(loadMappingFromFile(new ClassPathResource("specs/org.eclipse.vorto.tutorial_PMSMotor_1.0.0-mappingspec.json").getInputStream()));
		mappingService.addMappingSpec(loadMappingFromFile(new ClassPathResource("specs/vorto.private.somesh_ACMEWaterSensor_1.0.0-mappingspec.json").getInputStream()));
		mappingService.addMappingSpec(loadMappingFromFile(new ClassPathResource("specs/vorto.private.timgrossmann_Plantect_1.0.0-mappingspec.json").getInputStream()));

	}
	
	private IMappingSpecification loadMappingFromFile(InputStream is) {
		return IMappingSpecification.newBuilder().fromInputStream(is).build();
	}
}
