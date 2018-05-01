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
package org.eclipse.vorto.codegen.webui.templates.resources.partials;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class LoginTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("login.html");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("-solution/src/main/resources/static/partials");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<section class=\"content\">");
    _builder.newLine();
    _builder.append("<div class=\"login-box\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<!-- /.login-logo -->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<div class=\"login-box-body\">");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<div ng-show=\"error\" class=\"alert alert-danger alert-dismissable\">");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">�</button>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<h4>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<i class=\"icon fa fa-ban\"></i>Incorrect credentials");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</h4>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<p class=\"login-box-msg\">Sign in to start your session</p>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<a href=\"google/login\">");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<i class=\"fa fa-google fa-2x\" aria-hidden=\"true\"></i> Login with Google");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</a>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("</section>");
    _builder.newLine();
    return _builder.toString();
  }
}
