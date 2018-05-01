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
package org.eclipse.vorto.codegen.protobuf.templates;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.core.api.model.datatype.DictionaryPropertyType;
import org.eclipse.vorto.core.api.model.datatype.ObjectPropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.datatype.Type;
import org.eclipse.vorto.core.api.model.model.Model;

@SuppressWarnings("all")
public abstract class ProtobufTemplate<T extends Model> implements IFileTemplate<T> {
  @Override
  public String getFileName(final T element) {
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    return (_lowerCase + ".proto");
  }
  
  @Override
  public String getPath(final T element) {
    String _namespace = element.getNamespace();
    return _namespace.replace(".", "/");
  }
  
  public String type(final PropertyType type) {
    if ((type instanceof PrimitivePropertyType)) {
      PrimitiveType _type = ((PrimitivePropertyType) type).getType();
      return this.toProtoPrimitive(_type);
    } else {
      if ((type instanceof DictionaryPropertyType)) {
        PropertyType _keyType = ((DictionaryPropertyType) type).getKeyType();
        String _type_1 = this.type(_keyType);
        String _plus = ("map<" + _type_1);
        String _plus_1 = (_plus + ",");
        PropertyType _valueType = ((DictionaryPropertyType) type).getValueType();
        String _type_2 = this.type(_valueType);
        String _plus_2 = (_plus_1 + _type_2);
        return (_plus_2 + ">");
      } else {
        Type _type_3 = ((ObjectPropertyType) type).getType();
        return _type_3.getName();
      }
    }
  }
  
  public String toProtoPrimitive(final PrimitiveType primitiveType) {
    if (primitiveType != null) {
      switch (primitiveType) {
        case DOUBLE:
          return "double";
        case FLOAT:
          return "float";
        case INT:
          return "int32";
        case LONG:
          return "int64";
        case BOOLEAN:
          return "bool";
        case BASE64_BINARY:
          return "bytes";
        case STRING:
          return "string";
        default:
          return "string";
      }
    } else {
      return "string";
    }
  }
}
