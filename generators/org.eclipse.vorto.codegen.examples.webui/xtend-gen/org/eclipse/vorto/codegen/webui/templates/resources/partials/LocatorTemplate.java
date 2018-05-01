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
public class LocatorTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("locator.html");
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
    _builder.newLine();
    _builder.append("<!-- Content Header (Page header) -->");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("<section class=\"content-header\">");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<h1>");
    _builder.newLine();
    _builder.append("         ");
    String _name = element.getName();
    _builder.append(_name, "         ");
    _builder.append(" Locator");
    _builder.newLineIfNotEmpty();
    _builder.append("         ");
    _builder.append("<small>Locate your connected ");
    String _name_1 = element.getName();
    _builder.append(_name_1, "         ");
    _builder.append(" devices</small>");
    _builder.newLineIfNotEmpty();
    _builder.append("       ");
    _builder.append("</h1>");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<ol class=\"breadcrumb\">");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i> Home</a></li>");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("<li class=\"active\">");
    String _name_2 = element.getName();
    _builder.append(_name_2, "         ");
    _builder.append(" Locator</li>");
    _builder.newLineIfNotEmpty();
    _builder.append("       ");
    _builder.append("</ol>");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("</section>");
    _builder.newLine();
    _builder.append("     ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<!-- Main content -->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<section class=\"content\">");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<div ng-show=\"isLoading\" class=\"box box-default\">");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<div  class=\"box-header with-border\">");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<h3><i class=\"fa fa-refresh fa-spin\"></i>&nbsp;&nbsp; Locating devices </h3>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<div ng-hide=\"isLoading\" class=\"box box-default\">\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<div class=\"box-header with-border\">");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<h3 ng-show=\"things.length-defaultThings!=1\" class=\"box-title\">Located {{things.length - defaultThings}} Things</h3> ");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<h3 ng-show=\"things.length-defaultThings==1\" class=\"box-title\">Located {{things.length - defaultThings}} Thing</h3> ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<i>Hint: Click on the marker for details</i>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<leaflet markers=\"things\" lf-center=\"singapore\" width=\"100%\" height=\"800px\"></leaflet>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</section>");
    _builder.newLine();
    return _builder.toString();
  }
}
