/**
 * Copyright (c) 2016 Bosch Software Innovations GmbH and others.
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
import org.eclipse.vorto.codegen.kura.templates.cloud.TypeMapper;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class FunctionblockTemplate implements IFileTemplate<FunctionblockModel> {
  private InformationModel informationModelContext;
  
  public FunctionblockTemplate(final InformationModel context) {
    this.informationModelContext = context;
  }
  
  @Override
  public String getFileName(final FunctionblockModel model) {
    String _name = model.getName();
    return (_name + ".java");
  }
  
  @Override
  public String getPath(final FunctionblockModel model) {
    StringConcatenation _builder = new StringConcatenation();
    String _javaPackageBasePath = Utils.getJavaPackageBasePath(this.informationModelContext);
    _builder.append(_javaPackageBasePath, "");
    _builder.append("/model");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final FunctionblockModel model, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _javaPackage = Utils.getJavaPackage(this.informationModelContext);
    _builder.append(_javaPackage, "");
    _builder.append(".model;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class ");
    String _name = model.getName();
    _builder.append(_name, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    {
      FunctionBlock _functionblock = model.getFunctionblock();
      Status _status = _functionblock.getStatus();
      EList<Property> _properties = _status.getProperties();
      for(final Property statusProperty : _properties) {
        _builder.append("    ");
        _builder.append("private ");
        PropertyType _type = statusProperty.getType();
        String _mapSimpleDatatype = TypeMapper.mapSimpleDatatype(_type);
        _builder.append(_mapSimpleDatatype, "    ");
        _builder.append(" ");
        String _name_1 = statusProperty.getName();
        String _checkKeyword = TypeMapper.checkKeyword(_name_1);
        _builder.append(_checkKeyword, "    ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("    ");
    _builder.newLine();
    {
      FunctionBlock _functionblock_1 = model.getFunctionblock();
      Status _status_1 = _functionblock_1.getStatus();
      EList<Property> _properties_1 = _status_1.getProperties();
      for(final Property statusProperty_1 : _properties_1) {
        _builder.append("    ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("* Getter for ");
        String _name_2 = statusProperty_1.getName();
        _builder.append(_name_2, "     ");
        _builder.append(".");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("public ");
        PropertyType _type_1 = statusProperty_1.getType();
        String _mapSimpleDatatype_1 = TypeMapper.mapSimpleDatatype(_type_1);
        _builder.append(_mapSimpleDatatype_1, "    ");
        _builder.append(" get");
        String _name_3 = statusProperty_1.getName();
        String _checkKeyword_1 = TypeMapper.checkKeyword(_name_3);
        String _firstUpper = StringExtensions.toFirstUpper(_checkKeyword_1);
        _builder.append(_firstUpper, "    ");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("    ");
        _builder.append("return ");
        String _name_4 = statusProperty_1.getName();
        String _checkKeyword_2 = TypeMapper.checkKeyword(_name_4);
        _builder.append(_checkKeyword_2, "        ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("    ");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("* Setter for ");
        String _name_5 = statusProperty_1.getName();
        _builder.append(_name_5, "     ");
        _builder.append(".");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("public void set");
        String _name_6 = statusProperty_1.getName();
        String _checkKeyword_3 = TypeMapper.checkKeyword(_name_6);
        String _firstUpper_1 = StringExtensions.toFirstUpper(_checkKeyword_3);
        _builder.append(_firstUpper_1, "    ");
        _builder.append("(");
        PropertyType _type_2 = statusProperty_1.getType();
        String _mapSimpleDatatype_2 = TypeMapper.mapSimpleDatatype(_type_2);
        _builder.append(_mapSimpleDatatype_2, "    ");
        _builder.append(" ");
        String _name_7 = statusProperty_1.getName();
        String _checkKeyword_4 = TypeMapper.checkKeyword(_name_7);
        _builder.append(_checkKeyword_4, "    ");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("    ");
        _builder.append("this.");
        String _name_8 = statusProperty_1.getName();
        String _checkKeyword_5 = TypeMapper.checkKeyword(_name_8);
        _builder.append(_checkKeyword_5, "        ");
        _builder.append(" = ");
        String _name_9 = statusProperty_1.getName();
        String _checkKeyword_6 = TypeMapper.checkKeyword(_name_9);
        _builder.append(_checkKeyword_6, "        ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("    ");
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
