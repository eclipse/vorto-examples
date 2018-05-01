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
package org.eclipse.vorto.codegen.aws;

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.ChainedCodeGeneratorTask;
import org.eclipse.vorto.codegen.api.GenerationResultZip;
import org.eclipse.vorto.codegen.api.GeneratorTaskFromFileTemplate;
import org.eclipse.vorto.codegen.api.IGeneratedWriter;
import org.eclipse.vorto.codegen.api.IGenerationResult;
import org.eclipse.vorto.codegen.api.IVortoCodeGenProgressMonitor;
import org.eclipse.vorto.codegen.api.IVortoCodeGenerator;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.VortoCodeGeneratorException;
import org.eclipse.vorto.codegen.aws.alexa.templates.AlexaIndentSchemaTemplate;
import org.eclipse.vorto.codegen.aws.alexa.templates.AlexaSkillLambdaTemplate;
import org.eclipse.vorto.codegen.aws.alexa.templates.AlexaSlotTypeTemplate;
import org.eclipse.vorto.codegen.aws.alexa.templates.AlexaUtterancesTemplate;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;

/**
 * @author Alexander Edelmann (Robert Bosch (SEA) Pte. Ltd)
 */
@SuppressWarnings("all")
public class AWSGenerator implements IVortoCodeGenerator {
  @Override
  public IGenerationResult generate(final InformationModel infomodel, final InvocationContext context, final IVortoCodeGenProgressMonitor monitor) throws VortoCodeGeneratorException {
    String _serviceKey = this.getServiceKey();
    GenerationResultZip output = new GenerationResultZip(infomodel, _serviceKey);
    ChainedCodeGeneratorTask<InformationModel> chainedGenerators = new ChainedCodeGeneratorTask<InformationModel>();
    AlexaIndentSchemaTemplate _alexaIndentSchemaTemplate = new AlexaIndentSchemaTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> _generatorTaskFromFileTemplate = new GeneratorTaskFromFileTemplate<InformationModel>(_alexaIndentSchemaTemplate);
    chainedGenerators.addTask(_generatorTaskFromFileTemplate);
    AlexaSkillLambdaTemplate _alexaSkillLambdaTemplate = new AlexaSkillLambdaTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> _generatorTaskFromFileTemplate_1 = new GeneratorTaskFromFileTemplate<InformationModel>(_alexaSkillLambdaTemplate);
    chainedGenerators.addTask(_generatorTaskFromFileTemplate_1);
    AlexaUtterancesTemplate _alexaUtterancesTemplate = new AlexaUtterancesTemplate();
    GeneratorTaskFromFileTemplate<InformationModel> _generatorTaskFromFileTemplate_2 = new GeneratorTaskFromFileTemplate<InformationModel>(_alexaUtterancesTemplate);
    chainedGenerators.addTask(_generatorTaskFromFileTemplate_2);
    chainedGenerators.generate(infomodel, context, output);
    this.generateCustomSlotTypes(infomodel, context, output);
    return output;
  }
  
  /**
   * Generates Alexa Custom Slot Types for every Infomodel Enumeration
   */
  public void generateCustomSlotTypes(final InformationModel infomodel, final InvocationContext context, final IGeneratedWriter output) {
    EList<FunctionblockProperty> _properties = infomodel.getProperties();
    for (final FunctionblockProperty fbModel : _properties) {
      {
        FunctionblockModel _type = fbModel.getType();
        FunctionBlock _functionblock = _type.getFunctionblock();
        EList<org.eclipse.vorto.core.api.model.datatype.Enum> enums = Utils.getReferencedEnums(_functionblock);
        for (final org.eclipse.vorto.core.api.model.datatype.Enum enumeration : enums) {
          {
            AlexaSlotTypeTemplate _alexaSlotTypeTemplate = new AlexaSlotTypeTemplate();
            GeneratorTaskFromFileTemplate<org.eclipse.vorto.core.api.model.datatype.Enum> _template = new GeneratorTaskFromFileTemplate<org.eclipse.vorto.core.api.model.datatype.Enum>(_alexaSlotTypeTemplate);
            _template.generate(enumeration, context, output);
          }
        }
      }
    }
  }
  
  @Override
  public String getServiceKey() {
    return "aws";
  }
}
