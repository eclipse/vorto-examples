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
public class ApiTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("api.html");
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
    _builder.append("<link href=\'css/swagger_custom.css\' media=\'screen\' rel=\'stylesheet\' type=\'text/css\'/>");
    _builder.newLine();
    _builder.append("<div class=\"swagger-section\">");
    _builder.newLine();
    _builder.append("<div id=\"message-bar\" class=\"swagger-ui-wrap\" data-sw-translate>&nbsp;</div>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<div id=\"swagger-ui-container\" class=\"swagger-ui-wrap\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("<h3 ng-show=\"isLoading\">Loading... Please wait...</h3>");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("<div swagger-ui url=\"swaggerUrl\" validator-url=\"false\" loading=\"isLoading\" parser=\"json\" api-explorer=\"true\" trusted-sources=\"true\"");
    _builder.newLine();
    _builder.append("\t         ");
    _builder.append("error-handler=\"defaultErrorHandler\">");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("</div>");
    _builder.newLine();
    return _builder.toString();
  }
}
