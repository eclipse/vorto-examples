/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.vorto.example.mapping.service;

import java.io.IOException;
import org.eclipse.vorto.mapping.engine.MappingEngine;
import org.eclipse.vorto.model.ModelId;
import org.eclipse.vorto.model.runtime.InfomodelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class MappingService {

  private static Logger logger = LoggerFactory.getLogger(MappingService.class);


  public InfomodelValue map(ModelId modelId, Object payload) {
    logger.debug("Mapping device data for model ID " + modelId.getPrettyFormat());

    final String fileName = "specs/"+modelId.getNamespace() + "_" + modelId.getName() + "_"
        + modelId.getVersion() + "-mappingspec.json";
    
    logger.debug("Looking up file from classpath: "+fileName);

    MappingEngine engine;
    try {
      engine = MappingEngine
          .createFromInputStream(new ClassPathResource(fileName).getInputStream());
    } catch (IOException e) {
      throw new RuntimeException(
          "Cannot find mapping spec for modelID '" + modelId.getPrettyFormat()
              + "' in classpath under specs/. Did you forget to download from Vorto Repository ? ");
    }
    return engine.mapSource(payload);
  }



}
