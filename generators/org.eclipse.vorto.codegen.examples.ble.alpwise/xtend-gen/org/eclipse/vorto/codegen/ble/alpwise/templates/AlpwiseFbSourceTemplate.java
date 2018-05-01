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
package org.eclipse.vorto.codegen.ble.alpwise.templates;

import java.io.File;
import java.util.UUID;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.mapping.IMapped;
import org.eclipse.vorto.codegen.ble.model.BleInvocationContext;
import org.eclipse.vorto.codegen.ble.model.blegatt.Characteristic;
import org.eclipse.vorto.codegen.ble.model.blegatt.Device;
import org.eclipse.vorto.codegen.ble.model.blegatt.Service;
import org.eclipse.vorto.codegen.ble.templates.BleGattTemplate;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class AlpwiseFbSourceTemplate extends BleGattTemplate<FunctionblockModel> {
  private UUID uuid;
  
  @Override
  public String getFileName(final FunctionblockModel fb) {
    String _name = fb.getName();
    return (_name + ".c");
  }
  
  @Override
  public String getPath(final FunctionblockModel fb) {
    String _name = fb.getName();
    return ((BleGattTemplate.rootPath + File.separator) + _name);
  }
  
  @Override
  public String getContent(final FunctionblockModel fb, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/* ");
    String _name = fb.getName();
    _builder.append(_name, "");
    _builder.append(" generated by Vorto */");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("#include <stdint.h>");
    _builder.newLine();
    _builder.append("#include <stdio.h>");
    _builder.newLine();
    _builder.append("#include <assert.h>");
    _builder.newLine();
    _builder.append("#include <BleTypes.h>");
    _builder.newLine();
    _builder.append("#include <attserver.h>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"BleUtils.h\"");
    _builder.newLine();
    _builder.append("#include \"BleApp_Cbk.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#include \"");
    String _name_1 = fb.getName();
    _builder.append(_name_1, "");
    _builder.append(".h\"");
    _builder.newLineIfNotEmpty();
    _builder.append("#include \"services/");
    Device _device = ((BleInvocationContext) context).getDevice();
    Service _findService = this.findService(_device, fb);
    String _name_2 = _findService.getName();
    _builder.append(_name_2, "");
    _builder.append("Service.h\"");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("const ");
    String _name_3 = fb.getName();
    _builder.append(_name_3, "");
    _builder.append("_t ");
    String _name_4 = fb.getName();
    _builder.append(_name_4, "");
    _builder.append("_Instances[NUM_");
    String _name_5 = fb.getName();
    String _upperCase = _name_5.toUpperCase();
    _builder.append(_upperCase, "");
    _builder.append("_SERVICES] = {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("&");
    Device _device_1 = ((BleInvocationContext) context).getDevice();
    Service _findService_1 = this.findService(_device_1, fb);
    String _name_6 = _findService_1.getName();
    _builder.append(_name_6, "\t\t");
    _builder.append("Service_Instances[0],");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      FunctionBlock _functionblock = fb.getFunctionblock();
      Status _status = _functionblock.getStatus();
      EList<Property> _properties = _status.getProperties();
      for(final Property status : _properties) {
        {
          IMapped<Property> _mappedElement = context.getMappedElement(status, "source");
          boolean _hasAttribute = _mappedElement.hasAttribute("uuid");
          if (_hasAttribute) {
            _builder.append("\t\t");
            _builder.append("&");
            Device _device_2 = ((BleInvocationContext) context).getDevice();
            Service _findService_2 = this.findService(_device_2, fb);
            String _name_7 = _findService_2.getName();
            _builder.append(_name_7, "\t\t");
            _builder.append("Service_Instances[0].");
            Device _device_3 = ((BleInvocationContext) context).getDevice();
            Characteristic _findCharacteristic = this.findCharacteristic(_device_3, status);
            String _name_8 = _findCharacteristic.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name_8);
            _builder.append(_firstUpper, "\t\t");
            _builder.append("Value,");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    _builder.newLine();
    {
      FunctionBlock _functionblock_1 = fb.getFunctionblock();
      Status _status_1 = _functionblock_1.getStatus();
      EList<Property> _properties_1 = _status_1.getProperties();
      for(final Property status_1 : _properties_1) {
        {
          IMapped<Property> _mappedElement_1 = context.getMappedElement(status_1, "source");
          boolean _hasAttribute_1 = _mappedElement_1.hasAttribute("uuid");
          if (_hasAttribute_1) {
            _builder.append("void ");
            String _name_9 = fb.getName();
            _builder.append(_name_9, "");
            _builder.append("_Set");
            String _name_10 = status_1.getName();
            String _firstUpper_1 = StringExtensions.toFirstUpper(_name_10);
            _builder.append(_firstUpper_1, "");
            _builder.append("Value(");
            String _name_11 = fb.getName();
            _builder.append(_name_11, "");
            _builder.append("_t *");
            String _name_12 = fb.getName();
            String _lowerCase = _name_12.toLowerCase();
            _builder.append(_lowerCase, "");
            _builder.append(", ");
            IMapped<Property> _mappedElement_2 = context.getMappedElement(status_1, "source");
            String _attributeValue = _mappedElement_2.getAttributeValue("datatype", "uint16");
            _builder.append(_attributeValue, "");
            _builder.append("_t value)");
            _builder.newLineIfNotEmpty();
            _builder.append("{");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("/* Data conversion based on little endian byte order */");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("for (int i = 0; i < ");
            IMapped<Property> _mappedElement_3 = context.getMappedElement(status_1, "source");
            String _attributeValue_1 = _mappedElement_3.getAttributeValue("length", "2");
            _builder.append(_attributeValue_1, "\t");
            _builder.append("; i++)");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("{");
            _builder.newLine();
            _builder.append("\t\t");
            String _name_13 = fb.getName();
            String _lowerCase_1 = _name_13.toLowerCase();
            _builder.append(_lowerCase_1, "\t\t");
            _builder.append("->");
            String _name_14 = status_1.getName();
            String _firstUpper_2 = StringExtensions.toFirstUpper(_name_14);
            _builder.append(_firstUpper_2, "\t\t");
            _builder.append("Value[i+");
            IMapped<Property> _mappedElement_4 = context.getMappedElement(status_1, "source");
            String _attributeValue_2 = _mappedElement_4.getAttributeValue("offset", "0");
            _builder.append(_attributeValue_2, "\t\t");
            _builder.append("] = (uint8_t) (value >> (8*i));");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
            _builder.newLine();
            _builder.append("\t");
            Device _device_4 = ((BleInvocationContext) context).getDevice();
            Service _findService_3 = this.findService(_device_4, fb);
            String _name_15 = _findService_3.getName();
            _builder.append(_name_15, "\t");
            _builder.append("Service_WriteAndNotifyValue(&");
            String _name_16 = fb.getName();
            String _lowerCase_2 = _name_16.toLowerCase();
            _builder.append(_lowerCase_2, "\t");
            _builder.append("->service->");
            Device _device_5 = ((BleInvocationContext) context).getDevice();
            Characteristic _findCharacteristic_1 = this.findCharacteristic(_device_5, status_1);
            String _name_17 = _findCharacteristic_1.getName();
            String _firstUpper_3 = StringExtensions.toFirstUpper(_name_17);
            _builder.append(_firstUpper_3, "\t");
            _builder.append(", ");
            String _name_18 = fb.getName();
            String _lowerCase_3 = _name_18.toLowerCase();
            _builder.append(_lowerCase_3, "\t");
            _builder.append("->");
            String _name_19 = status_1.getName();
            String _firstUpper_4 = StringExtensions.toFirstUpper(_name_19);
            _builder.append(_firstUpper_4, "\t");
            _builder.append("Value, ");
            Device _device_6 = ((BleInvocationContext) context).getDevice();
            Characteristic _findCharacteristic_2 = this.findCharacteristic(_device_6, status_1);
            int _length = _findCharacteristic_2.getLength();
            _builder.append(_length, "\t");
            _builder.append(", 1);");
            _builder.newLineIfNotEmpty();
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    _builder.newLine();
    return _builder.toString();
  }
}