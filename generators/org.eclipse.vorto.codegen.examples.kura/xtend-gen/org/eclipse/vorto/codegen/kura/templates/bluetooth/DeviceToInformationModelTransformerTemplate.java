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
package org.eclipse.vorto.codegen.kura.templates.bluetooth;

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
public class DeviceToInformationModelTransformerTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("DeviceTo");
    String _name = context.getName();
    _builder.append(_name, "");
    _builder.append("Transformer.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _javaPackageBasePath = Utils.getJavaPackageBasePath(context);
    _builder.append(_javaPackageBasePath, "");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _javaPackage = Utils.getJavaPackage(element);
    _builder.append(_javaPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.util.Optional;");
    _builder.newLine();
    _builder.append("import java.util.function.Function;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.eclipse.kura.KuraException;");
    _builder.newLine();
    _builder.append("import org.eclipse.kura.bluetooth.BluetoothDevice;");
    _builder.newLine();
    _builder.append("import org.eclipse.kura.bluetooth.BluetoothGatt;");
    _builder.newLine();
    _builder.append("import org.slf4j.Logger;");
    _builder.newLine();
    _builder.append("import org.slf4j.LoggerFactory;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import ");
    String _javaPackage_1 = Utils.getJavaPackage(element);
    _builder.append(_javaPackage_1, "");
    _builder.append(".model.");
    String _name = element.getName();
    _builder.append(_name, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("import ");
        String _javaPackage_2 = Utils.getJavaPackage(element);
        _builder.append(_javaPackage_2, "");
        _builder.append(".model.");
        FunctionblockModel _type = fbProperty.getType();
        String _name_1 = _type.getName();
        _builder.append(_name_1, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("public class DeviceTo");
    String _name_2 = element.getName();
    _builder.append(_name_2, "");
    _builder.append("Transformer implements Function<BluetoothDevice, Optional<");
    String _name_3 = element.getName();
    _builder.append(_name_3, "");
    _builder.append(">> {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static final Logger logger = LoggerFactory.getLogger(DeviceTo");
    String _name_4 = element.getName();
    _builder.append(_name_4, "\t");
    _builder.append("Transformer.class);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private ");
    String _name_5 = element.getName();
    _builder.append(_name_5, "\t");
    _builder.append("Configuration configuration;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public DeviceTo");
    String _name_6 = element.getName();
    _builder.append(_name_6, "\t");
    _builder.append("Transformer(");
    String _name_7 = element.getName();
    _builder.append(_name_7, "\t");
    _builder.append("Configuration configuration) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.configuration = configuration;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Optional<");
    String _name_8 = element.getName();
    _builder.append(_name_8, "\t");
    _builder.append("> apply(BluetoothDevice device) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("BluetoothGatt gatt = device.getBluetoothGatt();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (gatt.connect()) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    String _name_9 = element.getName();
    _builder.append(_name_9, "\t\t\t\t");
    _builder.append(" ");
    String _name_10 = element.getName();
    String _lowerCase = _name_10.toLowerCase();
    _builder.append(_lowerCase, "\t\t\t\t");
    _builder.append(" = new ");
    String _name_11 = element.getName();
    _builder.append(_name_11, "\t\t\t\t");
    _builder.append("(getResourceId(device));");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_1 = element.getProperties();
      for(final FunctionblockProperty fbProperty_1 : _properties_1) {
        _builder.append("\t\t\t\t");
        _builder.append("if (configuration.enable");
        String _name_12 = fbProperty_1.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_12);
        _builder.append(_firstUpper, "\t\t\t\t");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("\t");
        _builder.append("enable");
        String _name_13 = fbProperty_1.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_13);
        _builder.append(_firstUpper_1, "\t\t\t\t\t");
        _builder.append("(gatt);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("\t");
        String _name_14 = element.getName();
        String _lowerCase_1 = _name_14.toLowerCase();
        _builder.append(_lowerCase_1, "\t\t\t\t\t");
        _builder.append(".set");
        String _name_15 = fbProperty_1.getName();
        String _firstUpper_2 = StringExtensions.toFirstUpper(_name_15);
        _builder.append(_firstUpper_2, "\t\t\t\t\t");
        _builder.append("(get");
        String _name_16 = fbProperty_1.getName();
        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_16);
        _builder.append(_firstUpper_3, "\t\t\t\t\t");
        _builder.append("(gatt));");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.newLine();
      }
    }
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("gatt.disconnect();");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return Optional.of(");
    String _name_17 = element.getName();
    String _lowerCase_2 = _name_17.toLowerCase();
    _builder.append(_lowerCase_2, "\t\t\t\t");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (KuraException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("logger.error(\"Error in getting device data\", e);");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return Optional.empty();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return Optional.empty();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Modify this to change how your resourceId is generated");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String getResourceId(BluetoothDevice device) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return \"");
    String _name_18 = element.getName();
    _builder.append(_name_18, "\t\t");
    _builder.append(":\" + device.getAdress().replace(\":\", \"\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_2 = element.getProperties();
      for(final FunctionblockProperty fbProperty_2 : _properties_2) {
        _builder.append("\t");
        _builder.append("/*------------------ Implement method for ");
        String _name_19 = fbProperty_2.getName();
        String _firstUpper_4 = StringExtensions.toFirstUpper(_name_19);
        _builder.append(_firstUpper_4, "\t");
        _builder.append(" here! (start) -----------------*/");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("/*");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("* Implement here the actual code to enable getting of ");
        FunctionblockModel _type_1 = fbProperty_2.getType();
        String _name_20 = _type_1.getName();
        _builder.append(_name_20, "\t ");
        _builder.append(" value from bluetooth device");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private void enable");
        String _name_21 = fbProperty_2.getName();
        String _firstUpper_5 = StringExtensions.toFirstUpper(_name_21);
        _builder.append(_firstUpper_5, "\t");
        _builder.append("(BluetoothGatt gatt) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("gatt.writeCharacteristicValue(\"\", \"\");");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("/*");
        _builder.newLine();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("* Implement the actual code to get ");
        FunctionblockModel _type_2 = fbProperty_2.getType();
        String _name_22 = _type_2.getName();
        _builder.append(_name_22, "\t ");
        _builder.append(" value from bluetooth device");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private ");
        FunctionblockModel _type_3 = fbProperty_2.getType();
        String _name_23 = _type_3.getName();
        _builder.append(_name_23, "\t");
        _builder.append(" get");
        String _name_24 = fbProperty_2.getName();
        String _firstUpper_6 = StringExtensions.toFirstUpper(_name_24);
        _builder.append(_firstUpper_6, "\t");
        _builder.append("(BluetoothGatt gatt) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        FunctionblockModel _type_4 = fbProperty_2.getType();
        String _name_25 = _type_4.getName();
        _builder.append(_name_25, "\t\t");
        _builder.append(" ");
        FunctionblockModel _type_5 = fbProperty_2.getType();
        String _name_26 = _type_5.getName();
        String _lowerCase_3 = _name_26.toLowerCase();
        _builder.append(_lowerCase_3, "\t\t");
        _builder.append(" = new ");
        FunctionblockModel _type_6 = fbProperty_2.getType();
        String _name_27 = _type_6.getName();
        _builder.append(_name_27, "\t\t");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("try {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("//TODO: insert code that reads ");
        FunctionblockModel _type_7 = fbProperty_2.getType();
        String _name_28 = _type_7.getName();
        _builder.append(_name_28, "\t\t\t");
        _builder.append(" and converts into ");
        FunctionblockModel _type_8 = fbProperty_2.getType();
        String _name_29 = _type_8.getName();
        _builder.append(_name_29, "\t\t\t");
        _builder.append(" object");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.append("String value = gatt.readCharacteristicValue(\"\");");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("} catch (KuraException e) {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t\t ");
        _builder.append("logger.error(e.toString());");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return ");
        FunctionblockModel _type_9 = fbProperty_2.getType();
        String _name_30 = _type_9.getName();
        String _lowerCase_4 = _name_30.toLowerCase();
        _builder.append(_lowerCase_4, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
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
