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
package org.eclipse.vorto.codegen.protobuf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.GenerationResultZip;
import org.eclipse.vorto.codegen.api.GeneratorTaskFromFileTemplate;
import org.eclipse.vorto.codegen.api.IGenerationResult;
import org.eclipse.vorto.codegen.api.IVortoCodeGenProgressMonitor;
import org.eclipse.vorto.codegen.api.IVortoCodeGenerator;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.VortoCodeGeneratorException;
import org.eclipse.vorto.codegen.protobuf.templates.ProtobufEntityTemplate;
import org.eclipse.vorto.codegen.protobuf.templates.ProtobufEnumTemplate;
import org.eclipse.vorto.codegen.protobuf.templates.ProtobufFBTemplate;
import org.eclipse.vorto.codegen.protobuf.templates.ProtobufIMTemplate;
import org.eclipse.vorto.codegen.protobuf.templates.ProtobufMetaTemplate;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.datatype.Entity;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.vorto.core.api.model.model.Model;

@SuppressWarnings("all")
public class ProtobufGenerator implements IVortoCodeGenerator {
  @Override
  public IGenerationResult generate(final InformationModel infomodel, final InvocationContext context, final IVortoCodeGenProgressMonitor monitor) throws VortoCodeGeneratorException {
    String _serviceKey = this.getServiceKey();
    GenerationResultZip output = new GenerationResultZip(infomodel, _serviceKey);
    ProtobufMetaTemplate _protobufMetaTemplate = new ProtobufMetaTemplate();
    GeneratorTaskFromFileTemplate<Model> metaTemplate = new GeneratorTaskFromFileTemplate<Model>(_protobufMetaTemplate);
    metaTemplate.generate(infomodel, context, output);
    ProtobufIMTemplate _protobufIMTemplate = new ProtobufIMTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> imTemplate = new GeneratorTaskFromFileTemplate<InformationModel>(_protobufIMTemplate);
    imTemplate.generate(infomodel, context, output);
    EList<FunctionblockProperty> _properties = infomodel.getProperties();
    for (final FunctionblockProperty fbProperty : _properties) {
      {
        ProtobufFBTemplate _protobufFBTemplate = new ProtobufFBTemplate();
        GeneratorTaskFromFileTemplate<FunctionblockModel> fbTemplate = new GeneratorTaskFromFileTemplate<FunctionblockModel>(_protobufFBTemplate);
        FunctionblockModel _type = fbProperty.getType();
        fbTemplate.generate(_type, context, output);
        FunctionblockModel _type_1 = fbProperty.getType();
        FunctionBlock _functionblock = _type_1.getFunctionblock();
        EList<Entity> _referencedEntities = Utils.getReferencedEntities(_functionblock);
        for (final Entity entity : _referencedEntities) {
          {
            ProtobufEntityTemplate _protobufEntityTemplate = new ProtobufEntityTemplate();
            GeneratorTaskFromFileTemplate<Entity> entityTemplate = new GeneratorTaskFromFileTemplate<Entity>(_protobufEntityTemplate);
            entityTemplate.generate(entity, context, output);
          }
        }
        FunctionblockModel _type_2 = fbProperty.getType();
        FunctionBlock _functionblock_1 = _type_2.getFunctionblock();
        EList<org.eclipse.vorto.core.api.model.datatype.Enum> _referencedEnums = Utils.getReferencedEnums(_functionblock_1);
        for (final org.eclipse.vorto.core.api.model.datatype.Enum en : _referencedEnums) {
          {
            ProtobufEnumTemplate _protobufEnumTemplate = new ProtobufEnumTemplate();
            GeneratorTaskFromFileTemplate<org.eclipse.vorto.core.api.model.datatype.Enum> enumTemplate = new GeneratorTaskFromFileTemplate<org.eclipse.vorto.core.api.model.datatype.Enum>(_protobufEnumTemplate);
            enumTemplate.generate(en, context, output);
          }
        }
      }
    }
    return output;
  }
  
  @Override
  public String getServiceKey() {
    return "protobuf";
  }
}
