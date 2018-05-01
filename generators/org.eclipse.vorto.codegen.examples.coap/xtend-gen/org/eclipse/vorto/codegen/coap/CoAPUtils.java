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
package org.eclipse.vorto.codegen.coap;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.vorto.codegen.templates.java.utils.ValueMapper;
import org.eclipse.vorto.core.api.model.datatype.Entity;
import org.eclipse.vorto.core.api.model.datatype.ObjectPropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitivePropertyType;
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.datatype.PropertyType;
import org.eclipse.vorto.core.api.model.datatype.Type;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.functionblock.Operation;
import org.eclipse.vorto.core.api.model.functionblock.Param;
import org.eclipse.vorto.core.api.model.functionblock.PrimitiveParam;
import org.eclipse.vorto.core.api.model.functionblock.RefParam;
import org.eclipse.vorto.core.api.model.functionblock.ReturnObjectType;
import org.eclipse.vorto.core.api.model.functionblock.ReturnPrimitiveType;
import org.eclipse.vorto.core.api.model.functionblock.ReturnType;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CoAPUtils {
  /**
   * Returns the parameter as string
   */
  public static String getParameterTypeAsString(final Param parameter) {
    if ((parameter instanceof PrimitiveParam)) {
      PrimitiveType _type = ((PrimitiveParam) parameter).getType();
      return ValueMapper.mapSimpleDatatype(((PrimitiveType) _type));
    } else {
      if ((parameter instanceof RefParam)) {
        RefParam object = ((RefParam) parameter);
        Type _type_1 = object.getType();
        if ((_type_1 instanceof Entity)) {
          Type _type_2 = object.getType();
          String _name = ((Entity) _type_2).getName();
          return StringExtensions.toFirstUpper(_name);
        } else {
          Type _type_3 = object.getType();
          if ((_type_3 instanceof org.eclipse.vorto.core.api.model.datatype.Enum)) {
            Type _type_4 = object.getType();
            String _name_1 = ((org.eclipse.vorto.core.api.model.datatype.Enum) _type_4).getName();
            return StringExtensions.toFirstUpper(_name_1);
          }
        }
      }
    }
    return null;
  }
  
  /**
   * Returns the type of the property as string
   */
  public static String getPropertyTypeAsString(final Property property) {
    PropertyType _type = property.getType();
    if ((_type instanceof PrimitivePropertyType)) {
      PropertyType _type_1 = property.getType();
      PrimitiveType _type_2 = ((PrimitivePropertyType) _type_1).getType();
      return ValueMapper.mapSimpleDatatype(((PrimitiveType) _type_2));
    } else {
      PropertyType _type_3 = property.getType();
      if ((_type_3 instanceof ObjectPropertyType)) {
        PropertyType _type_4 = property.getType();
        ObjectPropertyType object = ((ObjectPropertyType) _type_4);
        Type _type_5 = object.getType();
        if ((_type_5 instanceof Entity)) {
          Type _type_6 = object.getType();
          String _name = ((Entity) _type_6).getName();
          return StringExtensions.toFirstUpper(_name);
        } else {
          Type _type_7 = object.getType();
          if ((_type_7 instanceof org.eclipse.vorto.core.api.model.datatype.Enum)) {
            Type _type_8 = object.getType();
            String _name_1 = ((org.eclipse.vorto.core.api.model.datatype.Enum) _type_8).getName();
            return StringExtensions.toFirstUpper(_name_1);
          }
        }
      }
    }
    return null;
  }
  
  /**
   * Returns the return type as string
   */
  public static String getReturnTypeAsString(final Operation op) {
    ReturnType _returnType = op.getReturnType();
    boolean _notEquals = (!Objects.equal(_returnType, null));
    if (_notEquals) {
      ReturnType returnType = op.getReturnType();
      if ((returnType instanceof ReturnObjectType)) {
        Type _returnType_1 = ((ReturnObjectType) returnType).getReturnType();
        return _returnType_1.getName();
      } else {
        if ((returnType instanceof ReturnPrimitiveType)) {
          PrimitiveType _returnType_2 = ((ReturnPrimitiveType) returnType).getReturnType();
          return _returnType_2.getName();
        }
      }
    } else {
      return "void";
    }
    return null;
  }
  
  /**
   * Returns the name of the return type or 'returnValue' as string
   */
  public static String getReturnNameAsString(final ReturnType returnType) {
    if ((returnType instanceof ReturnObjectType)) {
      Type _returnType = ((ReturnObjectType) returnType).getReturnType();
      String _name = _returnType.getName();
      return StringExtensions.toFirstLower(_name);
    } else {
      if ((returnType instanceof ReturnPrimitiveType)) {
        return "returnValue";
      }
    }
    return null;
  }
  
  /**
   * Sorts all functionblock properties according to their functionblock model
   */
  public static EMap<FunctionblockModel, EList<FunctionblockProperty>> sortByPropertyType(final EList<FunctionblockProperty> properties) {
    EMap<FunctionblockModel, EList<FunctionblockProperty>> map = new BasicEMap<FunctionblockModel, EList<FunctionblockProperty>>();
    for (final FunctionblockProperty property : properties) {
      FunctionblockModel _type = property.getType();
      boolean _containsKey = map.containsKey(_type);
      if (_containsKey) {
        FunctionblockModel _type_1 = property.getType();
        EList<FunctionblockProperty> eList = map.get(_type_1);
        eList.add(property);
        FunctionblockModel _type_2 = property.getType();
        map.put(_type_2, eList);
      } else {
        EList<FunctionblockProperty> eList_1 = new BasicEList<FunctionblockProperty>();
        eList_1.add(property);
        FunctionblockModel _type_3 = property.getType();
        map.put(_type_3, eList_1);
      }
    }
    return map;
  }
}
