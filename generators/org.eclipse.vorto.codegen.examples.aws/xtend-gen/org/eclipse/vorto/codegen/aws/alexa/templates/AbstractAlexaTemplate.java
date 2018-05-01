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
package org.eclipse.vorto.codegen.aws.alexa.templates;

import com.google.common.base.Objects;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.core.api.model.datatype.PrimitiveType;
import org.eclipse.vorto.core.api.model.datatype.Type;
import org.eclipse.vorto.core.api.model.functionblock.Param;
import org.eclipse.vorto.core.api.model.functionblock.PrimitiveParam;
import org.eclipse.vorto.core.api.model.functionblock.RefParam;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;

@SuppressWarnings("all")
public abstract class AbstractAlexaTemplate implements IFileTemplate<InformationModel> {
  protected final static String STEREOTYPE_ALEXA = "alexa";
  
  @Override
  public String getPath(final InformationModel context) {
    return "aws/alexa";
  }
  
  protected boolean isAlexaSupportedParamType(final Param param) {
    if ((param instanceof PrimitiveParam)) {
      PrimitiveType primitiveType = ((PrimitiveParam) param).getType();
      boolean _or = false;
      boolean _or_1 = false;
      boolean _equals = Objects.equal(primitiveType, PrimitiveType.INT);
      if (_equals) {
        _or_1 = true;
      } else {
        boolean _equals_1 = Objects.equal(primitiveType, PrimitiveType.DATETIME);
        _or_1 = _equals_1;
      }
      if (_or_1) {
        _or = true;
      } else {
        boolean _equals_2 = Objects.equal(primitiveType, PrimitiveType.LONG);
        _or = _equals_2;
      }
      if (_or) {
        return true;
      } else {
        return false;
      }
    } else {
      boolean _and = false;
      if (!(param instanceof RefParam)) {
        _and = false;
      } else {
        Type _type = ((RefParam) param).getType();
        _and = (_type instanceof org.eclipse.vorto.core.api.model.datatype.Enum);
      }
      if (_and) {
        return true;
      } else {
        return false;
      }
    }
  }
  
  protected String mapToAlexaSupportedType(final Param param) {
    if ((param instanceof PrimitiveParam)) {
      PrimitiveType primitiveType = ((PrimitiveParam) param).getType();
      boolean _or = false;
      boolean _equals = Objects.equal(primitiveType, PrimitiveType.INT);
      if (_equals) {
        _or = true;
      } else {
        boolean _equals_1 = Objects.equal(primitiveType, PrimitiveType.LONG);
        _or = _equals_1;
      }
      if (_or) {
        return "AMAZON.NUMBER";
      } else {
        boolean _equals_2 = Objects.equal(primitiveType, PrimitiveType.DATETIME);
        if (_equals_2) {
          return "AMAZON.DATE";
        }
      }
    } else {
      boolean _and = false;
      if (!(param instanceof RefParam)) {
        _and = false;
      } else {
        Type _type = ((RefParam) param).getType();
        _and = (_type instanceof org.eclipse.vorto.core.api.model.datatype.Enum);
      }
      if (_and) {
        Type _type_1 = ((RefParam) param).getType();
        return _type_1.getName();
      }
    }
    return null;
  }
}
