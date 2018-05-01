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
public class BrowserTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("browser.html");
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
    _builder.append("<section class=\"content-header\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<h1>");
    _builder.newLine();
    _builder.append("\t\t");
    String _name = element.getName();
    _builder.append(_name, "\t\t");
    _builder.append(" Browser");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("<small>Browse your connected devices</small>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</h1>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<ol class=\"breadcrumb\">");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("<li><a href=\"#\"><i class=\"fa fa-dashboard\"></i> Home</a></li>");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("<li class=\"active\">");
    String _name_1 = element.getName();
    _builder.append(_name_1, "    \t");
    _builder.append(" Browser</li>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("</ol>");
    _builder.newLine();
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
    _builder.append("<div class=\"box box-default\">");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<div class=\"box-header with-border\">");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<div class=\"row\">");
    _builder.newLine();
    _builder.append("\t\t\t \t\t");
    _builder.append("<div class=\"col-xs-6\">\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<h3 ng-show=\"isLoading\" class=\"box-title\"><i class=\"fa fa-refresh fa-spin\"></i>&nbsp;&nbsp; Searching for devices </h3>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t");
    _builder.append("<h3 ng-show=\"!isLoading\" class=\"box-title\">{{things.length}} devices found</h3>\t\t\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</div>\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</div>\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<div class=\"box-body\" ng-show=\"things.length == 0\">");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("You have no connected devices yet.");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<div class=\"box-body\" ng-show=\"things.length > 0\">");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("<div>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<ul class=\"pagination pagination-sm no-margin pull-right\">");
    _builder.newLine();
    _builder.append("\t\t\t\t       \t");
    _builder.append("<li><a href=\"\" ng-show=\"!listView\" ng-click=\"toggleView()\"><span class=\"fa fa-list-ul\"></span></a></li>");
    _builder.newLine();
    _builder.append("\t\t\t\t       \t");
    _builder.append("<li><a href=\"\" ng-show=\"listView\" ng-click=\"toggleView()\"><span class=\"fa fa-th\"></span></a></li>");
    _builder.newLine();
    _builder.append("\t\t\t\t    ");
    _builder.append("</ul>\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<div ng-show=\"!listView\">");
    _builder.newLine();
    _builder.append("\t\t\t \t\t\t");
    _builder.append("<div class=\"row\" ng-repeat=\"row in thingsMatrix\">");
    _builder.newLine();
    _builder.append("\t\t\t \t \t\t\t");
    _builder.append("<div class=\"col-md-4\" ng-repeat=\"thing in row\">");
    _builder.newLine();
    _builder.append("\t\t\t \t \t\t\t\t");
    _builder.append("<!--box-->");
    _builder.newLine();
    _builder.append("\t\t                \t\t");
    _builder.append("<a ui-sref=\"details({ thingId: thing.thingId })\">");
    _builder.newLine();
    _builder.append("\t\t\t            \t\t\t");
    _builder.append("<div class=\"tiny-box\" id=\"tinyBox:{{thing.thingId}}\" ng-click=\"toggleBox(thing.thingId)\">");
    _builder.newLine();
    _builder.append("\t\t\t\t                \t\t");
    _builder.append("<span ng-show=\"thing.thingType\" class=\"tiny-box-icon\"><img width=\"64px\" height=\"64px\" ng-src=\"http://vorto.eclipse.org/rest/model/image/{{thing.thingType.namespace}}/{{thing.thingType.name}}/{{thing.thingType.version}}\"/></span>");
    _builder.newLine();
    _builder.append("\t\t\t\t               \t\t\t");
    _builder.append("<span ng-show=\"!thing.thingType\" class=\"tiny-box-icon\"><img width=\"64px\" height=\"64px\" ng-src=\"img/noImageIcon.png\"/></span>");
    _builder.newLine();
    _builder.append("\t\t\t\t               \t\t\t");
    _builder.append("<div class=\"tiny-box-name\"><span>{{thing.name}}</span></div>");
    _builder.newLine();
    _builder.append("\t\t\t\t               \t\t\t");
    _builder.append("<div class=\"tiny-box-number\">");
    _builder.newLine();
    _builder.append("\t\t\t\t               \t\t\t\t");
    _builder.append("<span href=\"#\" data-toggle=\"tooltip\" title=\"\"><p class=\"breakeWordWithDots ng-binding\" style=\"width: 130px\">{{thing.thingId}}</p></span>");
    _builder.newLine();
    _builder.append("\t\t\t\t               \t\t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t                 \t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t                \t");
    _builder.append("</a>");
    _builder.newLine();
    _builder.append("\t\t            \t\t");
    _builder.append("<!--box end-->");
    _builder.newLine();
    _builder.append("\t\t            \t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t \t \t\t");
    _builder.append("</div>                        ");
    _builder.newLine();
    _builder.append("\t\t       \t\t");
    _builder.append("</div>\t\t   \t\t\t   \t");
    _builder.newLine();
    _builder.append("\t\t\t   \t\t");
    _builder.append("<div ng-show=\"listView\">\t\t\t   \t\t\t\t   \t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t \t\t");
    _builder.append("<div ng-repeat=\"row in thingsMatrix\">");
    _builder.newLine();
    _builder.append("\t\t\t\t \t \t\t");
    _builder.append("<div ng-repeat=\"thing in row\">");
    _builder.newLine();
    _builder.append("\t\t\t\t \t \t\t\t");
    _builder.append("<div class=\"row\">\t\t\t\t \t \t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t \t \t\t\t\t");
    _builder.append("<div class=\"col-xs-1\">");
    _builder.newLine();
    _builder.append("\t\t\t\t \t \t\t\t\t\t");
    _builder.append("<span ng-show=\"thing.thingType\"><img width=\"16px\" height=\"16px\" ng-src=\"http://vorto.eclipse.org/rest/model/image/{{thing.thingType.namespace}}/{{thing.thingType.name}}/{{thing.thingType.version}}\"/></span>");
    _builder.newLine();
    _builder.append("\t\t\t\t               \t\t\t");
    _builder.append("<span ng-show=\"!thing.thingType\"><img width=\"16px\" height=\"16px\" ng-src=\"img/noImageIcon.png\"/></span>");
    _builder.newLine();
    _builder.append("\t\t\t\t               \t\t");
    _builder.append("</div>\t\t\t \t \t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t\t \t \t\t\t");
    _builder.append("<div class=\"col-xs-8\">");
    _builder.newLine();
    _builder.append("\t\t\t\t            \t\t\t");
    _builder.append("<a ui-sref=\"details({ thingId: thing.thingId })\"><p class=\"breakWord\">{{thing.name}}</p></a>");
    _builder.newLine();
    _builder.append("\t\t\t\t            \t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t\t            \t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t            \t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t            ");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t      \t");
    _builder.append("</div>\t");
    _builder.newLine();
    _builder.append("\t\t\t \t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<!-- /.box-body -->");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<!-- /.box -->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</section>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<!-- /.content -->");
    _builder.newLine();
    return _builder.toString();
  }
}
