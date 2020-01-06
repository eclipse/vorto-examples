package org.eclipse.vorto.middleware.mappings.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.eclipse.vorto.mapping.engine.model.spec.IMappingSpecification;
import org.eclipse.vorto.middleware.mappings.IMappingConfigDao;
import org.eclipse.vorto.middleware.mappings.MappingConfig;
import org.eclipse.vorto.model.ModelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;

@Service("mappingConfigPersistence")
public class MappingSpecDB implements IMappingConfigDao {
	
	@Autowired
	private MappingSpecJPA persistence;
	
	@Autowired
	private MappingSpecsConfiguration localConfiguration;

	@Override
	public void save(IMappingSpecification specification) {
		XStream xstream = new XStream();
		MappingConfig configuration = persistence.findOne(specification.getInfoModel().getId().getPrettyFormat());
		if (configuration != null) {
			configuration.setContent(xstream.toXML(specification));
		} else {
			configuration = new MappingConfig(specification.getInfoModel().getId().getPrettyFormat(), xstream.toXML(specification));
		}
		persistence.save(configuration);
	}

	@Override
	public void remove(ModelId specificationId) {
		persistence.delete(specificationId.getPrettyFormat());
	}

	@Override
	public Collection<IMappingSpecification> list() {
		List<IMappingSpecification> specs = new ArrayList<IMappingSpecification>();
		persistence.findAll().forEach(config -> {
			XStream xstream = new XStream();
			specs.add((IMappingSpecification)xstream.fromXML(config.getContent()));
		});
		return specs;
	}
	
	@PostConstruct
	public void addMappingSpecs() throws Exception {
		localConfiguration.list().stream().forEach(spec -> {
			if (!get(spec.getInfoModel().getId()).isPresent()) {
				save(spec);
			}
		});
			
			
	}

	@Override
	public Optional<IMappingSpecification> get(ModelId modelId) {
		MappingConfig config =  persistence.findOne(modelId.getPrettyFormat());
		if (config != null) {
			XStream xstream = new XStream();
			return Optional.of((IMappingSpecification)xstream.fromXML(config.getContent()));
		} else {
			return Optional.empty();
		}
	}
	
}
