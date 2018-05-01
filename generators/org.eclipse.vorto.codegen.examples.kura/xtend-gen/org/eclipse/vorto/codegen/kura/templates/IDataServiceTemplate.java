/**
 * Copyright (c) 2015-2016 Bosch Software Innovations GmbH and others.
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
package org.eclipse.vorto.codegen.kura.templates;

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.vorto.core.api.model.model.ModelId;
import org.eclipse.vorto.core.api.model.model.ModelIdFactory;
import org.eclipse.vorto.core.api.model.model.ModelReference;
import org.eclipse.vorto.core.api.model.model.ModelType;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * @author Alexander Edelmann
 */
@SuppressWarnings("all")
public class IDataServiceTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("IDataService.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _javaPackageBasePath = Utils.getJavaPackageBasePath(context);
    _builder.append(_javaPackageBasePath, "");
    _builder.append("/cloud");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _javaPackage = Utils.getJavaPackage(element);
    _builder.append(_javaPackage, "");
    _builder.append(".cloud;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      EList<ModelReference> _references = element.getReferences();
      for(final ModelReference reference : _references) {
        ModelId modelId = ModelIdFactory.newInstance(ModelType.Functionblock, reference);
        _builder.newLineIfNotEmpty();
        _builder.append("import ");
        String _javaPackage_1 = Utils.getJavaPackage(element);
        _builder.append(_javaPackage_1, "");
        _builder.append(".model.");
        String _name = modelId.getName();
        _builder.append(_name, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("public interface IDataService {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("\t");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("* publishes ");
        String _name_1 = fbProperty.getName();
        _builder.append(_name_1, "\t ");
        _builder.append(" data to the IoT Cloud backend");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("* @param resourceId ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("* @param data");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("void publish");
        String _name_2 = fbProperty.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_2);
        _builder.append(_firstUpper, "\t");
        _builder.append("(String resourceId, ");
        FunctionblockModel _type = fbProperty.getType();
        String _name_3 = _type.getName();
        _builder.append(_name_3, "\t");
        _builder.append(" data);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
}
