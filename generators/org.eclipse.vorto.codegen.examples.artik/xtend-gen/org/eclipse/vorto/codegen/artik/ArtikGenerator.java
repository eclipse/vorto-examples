/**
 * Copyright (c) 2015, 2016 Bosch Software Innovations GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * Contributors:
 * Bosch Software Innovations GmbH - Please refer to git log
 */
package org.eclipse.vorto.codegen.artik;

import org.eclipse.vorto.codegen.api.GenerationResultZip;
import org.eclipse.vorto.codegen.api.GeneratorTaskFromFileTemplate;
import org.eclipse.vorto.codegen.api.IGenerationResult;
import org.eclipse.vorto.codegen.api.IVortoCodeGenProgressMonitor;
import org.eclipse.vorto.codegen.api.IVortoCodeGenerator;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.VortoCodeGeneratorException;
import org.eclipse.vorto.codegen.artik.templates.DeviceManifestTemplate;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;

@SuppressWarnings("all")
public class ArtikGenerator implements IVortoCodeGenerator {
  @Override
  public IGenerationResult generate(final InformationModel infomodel, final InvocationContext context, final IVortoCodeGenProgressMonitor monitor) throws VortoCodeGeneratorException {
    String _serviceKey = this.getServiceKey();
    GenerationResultZip output = new GenerationResultZip(infomodel, _serviceKey);
    DeviceManifestTemplate _deviceManifestTemplate = new DeviceManifestTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> manifestTemplate = new GeneratorTaskFromFileTemplate<InformationModel>(_deviceManifestTemplate);
    manifestTemplate.generate(infomodel, context, output);
    return output;
  }
  
  @Override
  public String getServiceKey() {
    return "artik";
  }
}
