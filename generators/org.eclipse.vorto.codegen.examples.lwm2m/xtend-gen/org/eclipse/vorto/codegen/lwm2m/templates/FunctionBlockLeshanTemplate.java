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
package org.eclipse.vorto.codegen.lwm2m.templates;

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.mapping.IMapped;
import org.eclipse.vorto.codegen.lwm2m.templates.LWM2MConstants;
import org.eclipse.vorto.codegen.lwm2m.utils.TypeMapper;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Operation;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * @author Shaodong Ying (Robert Bosch (SEA) Pte. Ltd)
 */
@SuppressWarnings("all")
public class FunctionBlockLeshanTemplate extends LWM2MConstants implements ITemplate<FunctionblockModel> {
  @Override
  public String getContent(final FunctionblockModel model, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package examples.leshan.client;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.eclipse.leshan.client.resource.BaseInstanceEnabler;");
    _builder.newLine();
    _builder.append("import org.eclipse.leshan.core.response.ExecuteResponse;");
    _builder.newLine();
    _builder.append("import org.eclipse.leshan.core.response.ReadResponse;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    String _name = model.getName();
    _builder.append(_name, "");
    _builder.append(" extends BaseInstanceEnabler {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      FunctionBlock _functionblock = model.getFunctionblock();
      Status _status = _functionblock.getStatus();
      EList<Property> _properties = _status.getProperties();
      for(final Property statusProperty : _properties) {
        _builder.append("    ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("* ");
        String _description = statusProperty.getDescription();
        _builder.append(_description, "     ");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("private ");
        PropertyType _type = statusProperty.getType();
        PrimitiveType _type_1 = ((PrimitivePropertyType) _type).getType();
        String _mapSimpleDatatype = TypeMapper.mapSimpleDatatype(((PrimitiveType) _type_1));
        _builder.append(_mapSimpleDatatype, "    ");
        _builder.append(" ");
        String _name_1 = statusProperty.getName();
        _builder.append(_name_1, "    ");
        _builder.append(" = ");
        PropertyType _type_2 = statusProperty.getType();
        PrimitiveType _type_3 = ((PrimitivePropertyType) _type_2).getType();
        String _initialValue = TypeMapper.getInitialValue(((PrimitiveType) _type_3));
        _builder.append(_initialValue, "    ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.newLine();
      }
    }
    _builder.append("        ");
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
        PropertyType _type_4 = statusProperty_1.getType();
        PrimitiveType _type_5 = ((PrimitivePropertyType) _type_4).getType();
        String _mapSimpleDatatype_1 = TypeMapper.mapSimpleDatatype(((PrimitiveType) _type_5));
        _builder.append(_mapSimpleDatatype_1, "    ");
        _builder.append(" get");
        String _name_3 = statusProperty_1.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_3);
        _builder.append(_firstUpper, "    ");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("    ");
        _builder.append("return this.");
        String _name_4 = statusProperty_1.getName();
        _builder.append(_name_4, "        ");
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
    {
      FunctionBlock _functionblock_2 = model.getFunctionblock();
      Status _status_2 = _functionblock_2.getStatus();
      EList<Property> _properties_2 = _status_2.getProperties();
      for(final Property statusProperty_2 : _properties_2) {
        _builder.append("    ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("* Setter for ");
        String _name_5 = statusProperty_2.getName();
        _builder.append(_name_5, "     ");
        _builder.append(".");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("public void set");
        String _name_6 = statusProperty_2.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_6);
        _builder.append(_firstUpper_1, "    ");
        _builder.append("(");
        PropertyType _type_6 = statusProperty_2.getType();
        PrimitiveType _type_7 = ((PrimitivePropertyType) _type_6).getType();
        String _mapSimpleDatatype_2 = TypeMapper.mapSimpleDatatype(((PrimitiveType) _type_7));
        _builder.append(_mapSimpleDatatype_2, "    ");
        _builder.append(" ");
        String _name_7 = statusProperty_2.getName();
        _builder.append(_name_7, "    ");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("    ");
        _builder.append("this.");
        String _name_8 = statusProperty_2.getName();
        _builder.append(_name_8, "        ");
        _builder.append(" = ");
        String _name_9 = statusProperty_2.getName();
        _builder.append(_name_9, "        ");
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
    _builder.append("    ");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("* Gets the current value of one of this LWM2M object instance\'s resources.");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public ReadResponse read(int resourceid) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("switch (resourceid) {");
    _builder.newLine();
    {
      FunctionBlock _functionblock_3 = model.getFunctionblock();
      Status _status_3 = _functionblock_3.getStatus();
      EList<Property> _properties_3 = _status_3.getProperties();
      for(final Property statusProperty_3 : _properties_3) {
        _builder.append("        ");
        IMapped<Property> mappedElement = context.getMappedElement(statusProperty_3, LWM2MConstants.STEREOTYPE_RESOURCE);
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        String obj_id = mappedElement.getAttributeValue(LWM2MConstants.ATTRIBUTE_ID, null);
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("case ");
        _builder.append(obj_id, "        ");
        _builder.append(":");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("    ");
        _builder.append("return ReadResponse.success(resourceid, get");
        String _name_10 = statusProperty_3.getName();
        String _firstUpper_2 = StringExtensions.toFirstUpper(_name_10);
        _builder.append(_firstUpper_2, "            ");
        _builder.append("());");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("        ");
    _builder.append("default:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return super.read(resourceid);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("* Executes the operation represented by one of this LWM2M object instance\'s resources.");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public ExecuteResponse execute(int resourceid, String params) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("switch (resourceid) {");
    _builder.newLine();
    {
      FunctionBlock _functionblock_4 = model.getFunctionblock();
      EList<Operation> _operations = _functionblock_4.getOperations();
      for(final Operation operation : _operations) {
        _builder.append("        ");
        IMapped<Operation> mappedElement_1 = context.getMappedElement(operation, LWM2MConstants.STEREOTYPE_RESOURCE);
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        String obj_id_1 = mappedElement_1.getAttributeValue(LWM2MConstants.ATTRIBUTE_ID, null);
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("case ");
        _builder.append(obj_id_1, "        ");
        _builder.append(":");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("    ");
        _builder.append("// TODO: Implement execution code here");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("    ");
        _builder.append("// ...");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("    ");
        _builder.append("return ExecuteResponse.success();");
        _builder.newLine();
      }
    }
    _builder.append("        ");
    _builder.append("default:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return ExecuteResponse.success();");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
}
