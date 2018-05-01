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

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.resources.ui.IFunctionBlockUITemplate;
import org.eclipse.vorto.codegen.webui.templates.resources.ui.UIComponentFactory;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class DetailsTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("details.html");
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
    _builder.append(" Details");
    _builder.newLineIfNotEmpty();
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
    _builder.append(" Details</li>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("</ol>");
    _builder.newLine();
    _builder.append("</section>");
    _builder.newLine();
    _builder.append("     ");
    _builder.newLine();
    _builder.append("<!-- Main content -->");
    _builder.newLine();
    _builder.append("<section class=\"content\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<div class=\"row\">");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("<div class=\"col-md-5 col-sm-6 col-xs-12\">");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("<div class=\"info-box\">");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("<span class=\"info-box-icon\" style=\"background-color:white\"><img ng-src=\"http://vorto.eclipse.org/rest/model/image/{{thing.thingType.namespace}}/{{thing.thingType.name}}/{{thing.thingType.version}}\"/></span>");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("<div class=\"info-box-content\">");
    _builder.newLine();
    _builder.append("                  ");
    _builder.append("<span class=\"info-box-text\">{{thing.name}}</span>");
    _builder.newLine();
    _builder.append("                  ");
    _builder.append("<div class=\"tiny-box-number\">");
    _builder.newLine();
    _builder.append("                  \t");
    _builder.append("<span href=\"#\" data-toggle=\"tooltip\" title=\"\">{{thing.thingId}}</span>");
    _builder.newLine();
    _builder.append("                  ");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("                  ");
    _builder.append("<a href=\"http://vorto.eclipse.org/#/details/{{thing.thingType.namespace}}/{{thing.thingType.name}}/{{thing.thingType.version}}\" target=\"_blank\" class=\"small-box-footer\">More info <i class=\"fa fa-arrow-circle-right\"></i></a>");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("</div><!-- /.info-box-content -->");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("</div><!-- /.info-box -->");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("</div><!-- /.col -->");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<!-- Small boxes (Stat box) -->");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<!-- Main row -->");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("<div class=\"row\">");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("\t\t\t\t\t\t");
        FunctionblockModel _type = fbProperty.getType();
        IFunctionBlockUITemplate template = UIComponentFactory.getByModelId(_type, context);
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("<section class=\"col-lg-4 connectedSortable ui-sortable\">");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("\t");
        _builder.append("<div class=\"box box-solid\">");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("\t\t");
        _builder.append("<div class=\"box-header\">");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("\t\t\t");
        _builder.append("<h3>");
        String _name_2 = fbProperty.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_2);
        _builder.append(_firstUpper, "\t\t\t\t\t\t\t\t\t");
        _builder.append("</h3>");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("\t\t");
        _builder.append("</div>");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("\t\t");
        _builder.append("<div class=\"box-body\">");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("\t\t\t");
        String _renderHtml = template.renderHtml(fbProperty, context);
        _builder.append(_renderHtml, "\t\t\t\t\t\t\t\t\t");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("\t\t");
        _builder.append("</div>");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("\t");
        _builder.append("</div>");
        _builder.newLine();
        _builder.append("\t\t\t\t\t\t");
        _builder.append("</section><!-- /.Left col -->");
        _builder.newLine();
      }
    }
    _builder.append("\t\t\t\t\t");
    _builder.append("</div><!-- /.row (main row) -->");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("</section>");
    _builder.newLine();
    _builder.append("<!-- /.content -->");
    _builder.newLine();
    return _builder.toString();
  }
}
