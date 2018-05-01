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
package org.eclipse.vorto.codegen.artik.templates;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.api.mapping.IMapped;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Operation;
import org.eclipse.vorto.core.api.model.functionblock.Status;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class DeviceManifestTemplate implements IFileTemplate<InformationModel> {
  private final static String STEREOTYPE = "TYPE";
  
  private final static String ATTRIBUTE_KEY = "name";
  
  @Override
  public String getFileName(final InformationModel model) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = model.getName();
    _builder.append(_name, "");
    _builder.append("Manifest.groovy");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel model) {
    return null;
  }
  
  @Override
  public String getContent(final InformationModel model, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import groovy.json.JsonSlurper");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.samsung.sami.manifest.Manifest");
    _builder.newLine();
    _builder.append("import com.samsung.sami.manifest.fields.*");
    _builder.newLine();
    _builder.append("import com.samsung.sami.manifest.actions.Action");
    _builder.newLine();
    _builder.append("import com.samsung.sami.manifest.actions.Actionable");
    _builder.newLine();
    _builder.append("import static com.samsung.sami.manifest.groovy.JsonUtil.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    String _name = model.getName();
    _builder.append(_name, "");
    _builder.append("Manifest implements Manifest, Actionable {");
    _builder.newLineIfNotEmpty();
    {
      EList<FunctionblockProperty> _properties = model.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        {
          FunctionblockModel _type = fbProperty.getType();
          FunctionBlock _functionblock = _type.getFunctionblock();
          Status _status = _functionblock.getStatus();
          boolean _notEquals = (!Objects.equal(_status, null));
          if (_notEquals) {
            {
              FunctionblockModel _type_1 = fbProperty.getType();
              FunctionBlock _functionblock_1 = _type_1.getFunctionblock();
              Status _status_1 = _functionblock_1.getStatus();
              EList<Property> _properties_1 = _status_1.getProperties();
              for(final Property statusProperty : _properties_1) {
                {
                  boolean _and = false;
                  PropertyType _type_2 = statusProperty.getType();
                  if (!(_type_2 instanceof PrimitivePropertyType)) {
                    _and = false;
                  } else {
                    IMapped<Property> _mappedElement = context.getMappedElement(statusProperty, DeviceManifestTemplate.STEREOTYPE);
                    boolean _hasAttribute = _mappedElement.hasAttribute(DeviceManifestTemplate.ATTRIBUTE_KEY);
                    boolean _not = (!_hasAttribute);
                    _and = _not;
                  }
                  if (_and) {
                    _builder.append("  ");
                    _builder.append("static final ");
                    String _name_1 = fbProperty.getName();
                    String _upperCase = _name_1.toUpperCase();
                    _builder.append(_upperCase, "  ");
                    _builder.append("_");
                    String _name_2 = statusProperty.getName();
                    String _upperCase_1 = _name_2.toUpperCase();
                    _builder.append(_upperCase_1, "  ");
                    _builder.append(" = new FieldDescriptor(\"");
                    String _name_3 = fbProperty.getName();
                    _builder.append(_name_3, "  ");
                    String _name_4 = statusProperty.getName();
                    String _firstUpper = StringExtensions.toFirstUpper(_name_4);
                    _builder.append(_firstUpper, "  ");
                    _builder.append("\", \"");
                    {
                      String _description = statusProperty.getDescription();
                      boolean _notEquals_1 = (!Objects.equal(_description, null));
                      if (_notEquals_1) {
                        String _description_1 = statusProperty.getDescription();
                        _builder.append(_description_1, "  ");
                      }
                    }
                    _builder.append("\", ");
                    PropertyType _type_3 = statusProperty.getType();
                    String _type_4 = this.toType(((PrimitivePropertyType) _type_3));
                    _builder.append(_type_4, "  ");
                    _builder.append(")");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    _builder.append("  ");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("List<Field> normalize(String input) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def slurper = new JsonSlurper()");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def json = slurper.parseText(input)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def fields = []");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_2 = model.getProperties();
      for(final FunctionblockProperty fbProperty_1 : _properties_2) {
        {
          FunctionblockModel _type_5 = fbProperty_1.getType();
          FunctionBlock _functionblock_2 = _type_5.getFunctionblock();
          Status _status_2 = _functionblock_2.getStatus();
          boolean _notEquals_2 = (!Objects.equal(_status_2, null));
          if (_notEquals_2) {
            {
              FunctionblockModel _type_6 = fbProperty_1.getType();
              FunctionBlock _functionblock_3 = _type_6.getFunctionblock();
              Status _status_3 = _functionblock_3.getStatus();
              EList<Property> _properties_3 = _status_3.getProperties();
              for(final Property statusProperty_1 : _properties_3) {
                {
                  boolean _and_1 = false;
                  PropertyType _type_7 = statusProperty_1.getType();
                  if (!(_type_7 instanceof PrimitivePropertyType)) {
                    _and_1 = false;
                  } else {
                    IMapped<Property> _mappedElement_1 = context.getMappedElement(statusProperty_1, DeviceManifestTemplate.STEREOTYPE);
                    boolean _hasAttribute_1 = _mappedElement_1.hasAttribute(DeviceManifestTemplate.ATTRIBUTE_KEY);
                    boolean _not_1 = (!_hasAttribute_1);
                    _and_1 = _not_1;
                  }
                  if (_and_1) {
                    _builder.append("\t");
                    _builder.append("addToList(fields, json,");
                    String _name_5 = fbProperty_1.getName();
                    String _upperCase_2 = _name_5.toUpperCase();
                    _builder.append(_upperCase_2, "\t");
                    _builder.append("_");
                    String _name_6 = statusProperty_1.getName();
                    String _upperCase_3 = _name_6.toUpperCase();
                    _builder.append(_upperCase_3, "\t");
                    _builder.append(")");
                    _builder.newLineIfNotEmpty();
                  } else {
                    boolean _and_2 = false;
                    PropertyType _type_8 = statusProperty_1.getType();
                    if (!(_type_8 instanceof PrimitivePropertyType)) {
                      _and_2 = false;
                    } else {
                      IMapped<Property> _mappedElement_2 = context.getMappedElement(statusProperty_1, DeviceManifestTemplate.STEREOTYPE);
                      boolean _hasAttribute_2 = _mappedElement_2.hasAttribute(DeviceManifestTemplate.ATTRIBUTE_KEY);
                      _and_2 = _hasAttribute_2;
                    }
                    if (_and_2) {
                      _builder.append("\t");
                      _builder.append("addToList(fields, json,");
                      IMapped<Property> _mappedElement_3 = context.getMappedElement(statusProperty_1, DeviceManifestTemplate.STEREOTYPE);
                      String _attributeValue = _mappedElement_3.getAttributeValue(DeviceManifestTemplate.ATTRIBUTE_KEY, "[---->INSERT TYPE HERE]");
                      _builder.append(_attributeValue, "\t");
                      _builder.append(")");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return fields");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("List<FieldDescriptor> getFieldDescriptors() {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return [");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_4 = model.getProperties();
      for(final FunctionblockProperty fbProperty_2 : _properties_4) {
        {
          FunctionblockModel _type_9 = fbProperty_2.getType();
          FunctionBlock _functionblock_4 = _type_9.getFunctionblock();
          Status _status_4 = _functionblock_4.getStatus();
          boolean _notEquals_3 = (!Objects.equal(_status_4, null));
          if (_notEquals_3) {
            {
              FunctionblockModel _type_10 = fbProperty_2.getType();
              FunctionBlock _functionblock_5 = _type_10.getFunctionblock();
              Status _status_5 = _functionblock_5.getStatus();
              EList<Property> _properties_5 = _status_5.getProperties();
              boolean _hasElements = false;
              for(final Property statusProperty_2 : _properties_5) {
                if (!_hasElements) {
                  _hasElements = true;
                } else {
                  _builder.appendImmediate(",", "    ");
                }
                {
                  boolean _and_3 = false;
                  PropertyType _type_11 = statusProperty_2.getType();
                  if (!(_type_11 instanceof PrimitivePropertyType)) {
                    _and_3 = false;
                  } else {
                    IMapped<Property> _mappedElement_4 = context.getMappedElement(statusProperty_2, DeviceManifestTemplate.STEREOTYPE);
                    boolean _hasAttribute_3 = _mappedElement_4.hasAttribute(DeviceManifestTemplate.ATTRIBUTE_KEY);
                    boolean _not_2 = (!_hasAttribute_3);
                    _and_3 = _not_2;
                  }
                  if (_and_3) {
                    _builder.append("    ");
                    String _name_7 = fbProperty_2.getName();
                    String _upperCase_4 = _name_7.toUpperCase();
                    _builder.append(_upperCase_4, "    ");
                    _builder.append("_");
                    String _name_8 = statusProperty_2.getName();
                    String _upperCase_5 = _name_8.toUpperCase();
                    _builder.append(_upperCase_5, "    ");
                    _builder.newLineIfNotEmpty();
                  } else {
                    boolean _and_4 = false;
                    PropertyType _type_12 = statusProperty_2.getType();
                    if (!(_type_12 instanceof PrimitivePropertyType)) {
                      _and_4 = false;
                    } else {
                      IMapped<Property> _mappedElement_5 = context.getMappedElement(statusProperty_2, DeviceManifestTemplate.STEREOTYPE);
                      boolean _hasAttribute_4 = _mappedElement_5.hasAttribute(DeviceManifestTemplate.ATTRIBUTE_KEY);
                      _and_4 = _hasAttribute_4;
                    }
                    if (_and_4) {
                      _builder.append("    ");
                      IMapped<Property> _mappedElement_6 = context.getMappedElement(statusProperty_2, DeviceManifestTemplate.STEREOTYPE);
                      String _attributeValue_1 = _mappedElement_6.getAttributeValue(DeviceManifestTemplate.ATTRIBUTE_KEY, "[---->INSERT TYPE HERE]");
                      _builder.append(_attributeValue_1, "    ");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    _builder.append("    ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("List<Action> getActions() {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return [");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_6 = model.getProperties();
      for(final FunctionblockProperty fbProperty_3 : _properties_6) {
        {
          FunctionblockModel _type_13 = fbProperty_3.getType();
          FunctionBlock _functionblock_6 = _type_13.getFunctionblock();
          EList<Operation> _operations = _functionblock_6.getOperations();
          boolean _hasElements_1 = false;
          for(final Operation operation : _operations) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(",", "      ");
            }
            _builder.append("      ");
            _builder.append("new Action(\"");
            String _name_9 = fbProperty_3.getName();
            _builder.append(_name_9, "      ");
            _builder.append("_");
            String _name_10 = operation.getName();
            _builder.append(_name_10, "      ");
            _builder.append("\",");
            {
              String _description_2 = operation.getDescription();
              boolean _notEquals_4 = (!Objects.equal(_description_2, null));
              if (_notEquals_4) {
                _builder.append("\"");
                String _description_3 = operation.getDescription();
                _builder.append(_description_3, "      ");
                _builder.append("\"");
              } else {
                _builder.append("\"\"");
              }
            }
            _builder.append(")");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("    ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
  
  public String toType(final PrimitivePropertyType propertyType) {
    PrimitiveType _type = propertyType.getType();
    boolean _equals = Objects.equal(_type, PrimitiveType.BOOLEAN);
    if (_equals) {
      return "Boolean.class";
    } else {
      PrimitiveType _type_1 = propertyType.getType();
      boolean _equals_1 = Objects.equal(_type_1, PrimitiveType.STRING);
      if (_equals_1) {
        return "String.class";
      } else {
        PrimitiveType _type_2 = propertyType.getType();
        boolean _equals_2 = Objects.equal(_type_2, PrimitiveType.INT);
        if (_equals_2) {
          return "Integer.class";
        } else {
          PrimitiveType _type_3 = propertyType.getType();
          boolean _equals_3 = Objects.equal(_type_3, PrimitiveType.LONG);
          if (_equals_3) {
            return "Long.class";
          } else {
            PrimitiveType _type_4 = propertyType.getType();
            boolean _equals_4 = Objects.equal(_type_4, PrimitiveType.DOUBLE);
            if (_equals_4) {
              return "Double.class";
            } else {
              PrimitiveType _type_5 = propertyType.getType();
              boolean _equals_5 = Objects.equal(_type_5, PrimitiveType.FLOAT);
              if (_equals_5) {
                return "Float.class";
              } else {
                return "String.class";
              }
            }
          }
        }
      }
    }
  }
}
