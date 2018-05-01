/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
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
package org.eclipse.vorto.codegen.markdown.utils;

import org.eclipse.vorto.core.api.model.datatype.ObjectPropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.datatype.Type;
import org.eclipse.vorto.core.api.model.functionblock.ReturnObjectType;
import org.eclipse.vorto.core.api.model.functionblock.ReturnPrimitiveType;
import org.eclipse.vorto.core.api.model.functionblock.ReturnType;

@SuppressWarnings("all")
public class Utils {
  public static String getReturnType(final ReturnType type) {
    if ((type instanceof ReturnPrimitiveType)) {
      PrimitiveType _returnType = ((ReturnPrimitiveType) type).getReturnType();
      return _returnType.getName();
    } else {
      if ((type instanceof ReturnObjectType)) {
        Type _returnType_1 = ((ReturnObjectType) type).getReturnType();
        return _returnType_1.getName();
      }
    }
    return null;
  }
  
  public static String getPropertyType(final Property property) {
    PropertyType _type = property.getType();
    if ((_type instanceof PrimitivePropertyType)) {
      PropertyType _type_1 = property.getType();
      PrimitiveType _type_2 = ((PrimitivePropertyType) _type_1).getType();
      return _type_2.getName();
    } else {
      PropertyType _type_3 = property.getType();
      if ((_type_3 instanceof ObjectPropertyType)) {
        PropertyType _type_4 = property.getType();
        Type _type_5 = ((ObjectPropertyType) _type_4).getType();
        return _type_5.getName();
      }
    }
    return null;
  }
}
