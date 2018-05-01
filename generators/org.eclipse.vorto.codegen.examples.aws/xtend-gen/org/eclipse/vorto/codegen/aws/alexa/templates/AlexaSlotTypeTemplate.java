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

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.datatype.EnumLiteral;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class AlexaSlotTypeTemplate implements IFileTemplate<org.eclipse.vorto.core.api.model.datatype.Enum> {
  @Override
  public String getFileName(final org.eclipse.vorto.core.api.model.datatype.Enum context) {
    String _name = context.getName();
    return (_name + "_CustomSlotType.txt");
  }
  
  @Override
  public String getPath(final org.eclipse.vorto.core.api.model.datatype.Enum context) {
    return "aws/alexa";
  }
  
  @Override
  public String getContent(final org.eclipse.vorto.core.api.model.datatype.Enum element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<EnumLiteral> _enums = element.getEnums();
      for(final EnumLiteral literal : _enums) {
        String _name = literal.getName();
        _builder.append(_name, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder.toString();
  }
}
