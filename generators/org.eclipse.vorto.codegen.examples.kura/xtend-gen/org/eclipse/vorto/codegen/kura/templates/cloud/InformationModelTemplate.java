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
package org.eclipse.vorto.codegen.kura.templates.cloud;

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * @author Erle Czar Mantos - Robert Bosch (SEA) Pte. Ltd.
 */
@SuppressWarnings("all")
public class InformationModelTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    _builder.append(_name, "");
    _builder.append(".java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _javaPackageBasePath = Utils.getJavaPackageBasePath(context);
    _builder.append(_javaPackageBasePath, "");
    _builder.append("/model");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _javaPackage = Utils.getJavaPackage(element);
    _builder.append(_javaPackage, "");
    _builder.append(".model;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class ");
    String _name = element.getName();
    _builder.append(_name, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("\t");
        _builder.append("private ");
        FunctionblockModel _type = fbProperty.getType();
        String _name_1 = _type.getName();
        _builder.append(_name_1, "\t");
        _builder.append(" ");
        String _name_2 = fbProperty.getName();
        _builder.append(_name_2, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String resourceId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_3 = element.getName();
    _builder.append(_name_3, "\t");
    _builder.append("(String resourceId) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.resourceId = resourceId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_1 = element.getProperties();
      for(final FunctionblockProperty fbProperty_1 : _properties_1) {
        _builder.append("\t");
        _builder.append("public ");
        FunctionblockModel _type_1 = fbProperty_1.getType();
        String _name_4 = _type_1.getName();
        _builder.append(_name_4, "\t");
        _builder.append(" get");
        String _name_5 = fbProperty_1.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_5);
        _builder.append(_firstUpper, "\t");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return ");
        String _name_6 = fbProperty_1.getName();
        _builder.append(_name_6, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void set");
        String _name_7 = fbProperty_1.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_7);
        _builder.append(_firstUpper_1, "\t");
        _builder.append("(");
        FunctionblockModel _type_2 = fbProperty_1.getType();
        String _name_8 = _type_2.getName();
        _builder.append(_name_8, "\t");
        _builder.append(" ");
        String _name_9 = fbProperty_1.getName();
        _builder.append(_name_9, "\t");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("this.");
        String _name_10 = fbProperty_1.getName();
        _builder.append(_name_10, "\t\t");
        _builder.append(" = ");
        String _name_11 = fbProperty_1.getName();
        _builder.append(_name_11, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("public void setResourceId(String resourceId) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.resourceId = resourceId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String getResourceId() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return resourceId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
