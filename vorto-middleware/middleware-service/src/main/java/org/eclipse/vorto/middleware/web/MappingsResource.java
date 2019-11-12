package org.eclipse.vorto.middleware.web;

import java.util.Collection;

import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.middleware.service.IMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/mappings")
public class MappingsResource {

	@Autowired
	private IMappingService mappingService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<IMappingSpecification> getMappings() {
		return mappingService.listMappings();
	}
}
