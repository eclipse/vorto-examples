/**
 * Copyright (c) 2017 Oliver Meili
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  The Eclipse Distribution License is available at
 *  http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 *  Contributors:
 *  Oliver Meili <omi@ieee.org>
 */
package org.eclipse.vorto.codegen.ble.alpwise;

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.GenerationResultZip;
import org.eclipse.vorto.codegen.api.GeneratorTaskFromFileTemplate;
import org.eclipse.vorto.codegen.api.IGenerationResult;
import org.eclipse.vorto.codegen.api.IVortoCodeGenProgressMonitor;
import org.eclipse.vorto.codegen.api.IVortoCodeGenerator;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.VortoCodeGeneratorException;
import org.eclipse.vorto.codegen.api.mapping.IMapped;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseAppCbkHeaderTemplate;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseAppHeaderTemplate;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseAppSourceTemplate;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseFbHeaderTemplate;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseFbSourceTemplate;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseImHeaderTemplate;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseImSourceTemplate;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseServiceHeaderTemplate;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseServiceSourceTemplate;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseUtilsHeaderTemplate;
import org.eclipse.vorto.codegen.ble.alpwise.templates.AlpwiseUtilsSourceTemplate;
import org.eclipse.vorto.codegen.ble.model.BleInvocationContext;
import org.eclipse.vorto.codegen.ble.model.ModelTransformer;
import org.eclipse.vorto.codegen.ble.model.blegatt.Device;
import org.eclipse.vorto.codegen.ble.model.blegatt.Service;
import org.eclipse.vorto.codegen.ble.templates.BleGattTemplate;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;

@SuppressWarnings("all")
public class AlpwiseBtStackGenerator implements IVortoCodeGenerator {
  @Override
  public IGenerationResult generate(final InformationModel infomodel, final InvocationContext context, final IVortoCodeGenProgressMonitor monitor) throws VortoCodeGeneratorException {
    ModelTransformer _modelTransformer = new ModelTransformer(infomodel, context);
    Device device = _modelTransformer.transform();
    BleInvocationContext bleContext = new BleInvocationContext(context, device);
    String _serviceKey = this.getServiceKey();
    GenerationResultZip output = new GenerationResultZip(infomodel, _serviceKey);
    BleGattTemplate.rootPath = "ble";
    AlpwiseImHeaderTemplate _alpwiseImHeaderTemplate = new AlpwiseImHeaderTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> imHeaderTemplateGen = new GeneratorTaskFromFileTemplate<InformationModel>(_alpwiseImHeaderTemplate);
    imHeaderTemplateGen.generate(infomodel, bleContext, output);
    AlpwiseImSourceTemplate _alpwiseImSourceTemplate = new AlpwiseImSourceTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> imSourceTemplateGen = new GeneratorTaskFromFileTemplate<InformationModel>(_alpwiseImSourceTemplate);
    imSourceTemplateGen.generate(infomodel, bleContext, output);
    AlpwiseAppHeaderTemplate _alpwiseAppHeaderTemplate = new AlpwiseAppHeaderTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> appHeaderTemplateGen = new GeneratorTaskFromFileTemplate<InformationModel>(_alpwiseAppHeaderTemplate);
    appHeaderTemplateGen.generate(infomodel, bleContext, output);
    AlpwiseAppCbkHeaderTemplate _alpwiseAppCbkHeaderTemplate = new AlpwiseAppCbkHeaderTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> appCbkHeaderTemplateGen = new GeneratorTaskFromFileTemplate<InformationModel>(_alpwiseAppCbkHeaderTemplate);
    appCbkHeaderTemplateGen.generate(infomodel, bleContext, output);
    AlpwiseAppSourceTemplate _alpwiseAppSourceTemplate = new AlpwiseAppSourceTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> appSourceTemplateGen = new GeneratorTaskFromFileTemplate<InformationModel>(_alpwiseAppSourceTemplate);
    appSourceTemplateGen.generate(infomodel, bleContext, output);
    AlpwiseUtilsHeaderTemplate _alpwiseUtilsHeaderTemplate = new AlpwiseUtilsHeaderTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> utilsHeaderTemplateGen = new GeneratorTaskFromFileTemplate<InformationModel>(_alpwiseUtilsHeaderTemplate);
    utilsHeaderTemplateGen.generate(infomodel, bleContext, output);
    AlpwiseUtilsSourceTemplate _alpwiseUtilsSourceTemplate = new AlpwiseUtilsSourceTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> utilsSourceTemplateGen = new GeneratorTaskFromFileTemplate<InformationModel>(_alpwiseUtilsSourceTemplate);
    utilsSourceTemplateGen.generate(infomodel, bleContext, output);
    EList<Service> _services = device.getServices();
    for (final Service service : _services) {
      {
        AlpwiseServiceHeaderTemplate _alpwiseServiceHeaderTemplate = new AlpwiseServiceHeaderTemplate();
        GeneratorTaskFromFileTemplate<Service> serviceHeaderTemplateGen = new GeneratorTaskFromFileTemplate<Service>(_alpwiseServiceHeaderTemplate);
        serviceHeaderTemplateGen.generate(service, bleContext, output);
        AlpwiseServiceSourceTemplate _alpwiseServiceSourceTemplate = new AlpwiseServiceSourceTemplate();
        GeneratorTaskFromFileTemplate<Service> serviceSourceTemplateGen = new GeneratorTaskFromFileTemplate<Service>(_alpwiseServiceSourceTemplate);
        serviceSourceTemplateGen.generate(service, bleContext, output);
      }
    }
    EList<FunctionblockProperty> _properties = infomodel.getProperties();
    for (final FunctionblockProperty fbProperty : _properties) {
      FunctionblockModel _type = fbProperty.getType();
      IMapped<FunctionblockModel> _mappedElement = context.getMappedElement(_type, "Service");
      boolean _hasAttribute = _mappedElement.hasAttribute("uuid");
      if (_hasAttribute) {
        AlpwiseFbHeaderTemplate _alpwiseFbHeaderTemplate = new AlpwiseFbHeaderTemplate();
        GeneratorTaskFromFileTemplate<FunctionblockModel> fbHeaderTemplateGen = new GeneratorTaskFromFileTemplate<FunctionblockModel>(_alpwiseFbHeaderTemplate);
        FunctionblockModel _type_1 = fbProperty.getType();
        fbHeaderTemplateGen.generate(_type_1, bleContext, output);
        AlpwiseFbSourceTemplate _alpwiseFbSourceTemplate = new AlpwiseFbSourceTemplate();
        GeneratorTaskFromFileTemplate<FunctionblockModel> fbSourceTemplateGen = new GeneratorTaskFromFileTemplate<FunctionblockModel>(_alpwiseFbSourceTemplate);
        FunctionblockModel _type_2 = fbProperty.getType();
        fbSourceTemplateGen.generate(_type_2, bleContext, output);
      }
    }
    return output;
  }
  
  @Override
  public String getServiceKey() {
    return "blegatt";
  }
}
