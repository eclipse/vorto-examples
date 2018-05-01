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
package org.eclipse.vorto.codegen.latex.tasks.template;

import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.utils.Utils;
import org.eclipse.vorto.core.api.model.functionblock.Operation;
import org.eclipse.vorto.core.api.model.functionblock.ReturnType;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class LatexOperationTemplate implements ITemplate<Operation> {
  @Override
  public String getContent(final Operation operation, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\\begin{tabular}{|p{4.5cm}|p{4cm}|p{7cm}|}");
    _builder.newLine();
    _builder.append("\\hline");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\\multicolumn{3}{|p{15.5cm}|}{\\textbf{Operation}}\\\\");
    _builder.newLine();
    _builder.append("\\hline");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Name &  ReturnType & Description\\\\");
    _builder.newLine();
    _builder.append("\\hline\\hline");
    _builder.newLine();
    _builder.append("\t");
    String _name = operation.getName();
    String _replace = _name.replace("_", "\\_");
    _builder.append(_replace, "\t");
    _builder.append(" & ");
    ReturnType _returnType = operation.getReturnType();
    String _returnType_1 = Utils.getReturnType(_returnType);
    _builder.append(_returnType_1, "\t");
    _builder.append(" & ");
    String _description = operation.getDescription();
    _builder.append(_description, "\t");
    _builder.append("\\\\");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("\\hline");
    _builder.newLine();
    _builder.append("\\end{tabular}\\\\\\\\\\\\");
    _builder.newLine();
    return _builder.toString();
  }
}
