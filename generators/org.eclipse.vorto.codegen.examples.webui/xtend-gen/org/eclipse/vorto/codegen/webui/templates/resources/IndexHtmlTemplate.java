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
package org.eclipse.vorto.codegen.webui.templates.resources;

import java.util.Map;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class IndexHtmlTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("index.html");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("-solution/src/main/resources/static");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<!DOCTYPE html>");
    _builder.newLine();
    _builder.append("<html class=\"full\" lang=\"en\" ng-app=\'");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("App\'>");
    _builder.newLineIfNotEmpty();
    _builder.append("<head>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<meta charset=\"utf-8\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<meta name=\"description\" content=\"\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<meta name=\"author\" content=\"\">");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<title>");
    String _name_1 = element.getName();
    _builder.append(_name_1, "    ");
    _builder.append(" Solution</title>");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<link rel=\"stylesheet\" href=\"webjars/bootstrap/css/bootstrap.min.css\" type=\"text/css\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<link rel=\"stylesheet\" href=\"webjars/font-awesome/css/font-awesome.min.css\" type=\"text/css\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<link rel=\"stylesheet\" href=\"webjars/adminlte/dist/css/skins/_all-skins.min.css\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<link rel=\"stylesheet\" href=\"webjars/adminlte/dist/css/AdminLTE.min.css\">");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<link rel=\"stylesheet\" href=\"css/style.css\">");
    _builder.newLine();
    _builder.append("    \t\t    ");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("swagger", "true");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append("    ");
        _builder.append("<link rel=\"stylesheet\" href=\"webjars/angular-swagger-ui/0.2.7/dist/css/swagger-ui.min.css\">");
        _builder.newLine();
      }
    }
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<script src=\"webjars/jquery/jquery.min.js\"></script>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<script src=\"webjars/bootstrap/js/bootstrap.min.js\"></script>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<script src=\"webjars/angularjs/angular.min.js\"></script>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<script src=\"webjars/angular-ui-bootstrap/ui-bootstrap-tpls.min.js\"></script>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<script src=\"webjars/angular-ui-router/release/angular-ui-router.min.js\"></script>");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<!-- OpenStreetMap UI component stuff -->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<link rel=\"stylesheet\" href=\"webjars/leaflet/leaflet.css\" />");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<script src=\"webjars/leaflet/leaflet.js\"></script>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<script src=\"webjars/angular-leaflet-directive/angular-leaflet-directive.js\"></script>");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<!-- Barchart UI component stuff -->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<script src=\"webjars/d3js/d3.min.js\"></script>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<script src=\"webjars/nvd3/nv.d3.min.js\"></script>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<script src=\"webjars/angular-nvd3/angular-nvd3.min.js\"></script>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<link rel=\"stylesheet\" href=\"webjars/nvd3/nv.d3.css\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<!-- Gauge UI component stuff -->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<script src=\"webjars/justgage/justgage.js\"></script>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<script src=\"webjars/raphaeljs/raphael-min.js\"></script>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<script src=\"dist/js/angular-gage.min.js\"></script>");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<!-- SockJS for websockets  -->");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<script src=\"webjars/sockjs-client/sockjs.min.js\"></script>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<script src=\"webjars/stomp-websocket/stomp.min.js\"></script>");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_1 = context.getConfigurationProperties();
      String _orDefault_1 = _configurationProperties_1.getOrDefault("swagger", "true");
      boolean _equalsIgnoreCase_1 = _orDefault_1.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_1) {
        _builder.append("\t");
        _builder.append("<script type=\"text/javascript\" src=\"webjars/angular-swagger-ui/0.2.7/dist/scripts/swagger-ui.min.js\"></script>");
        _builder.newLine();
      }
    }
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<script src=\"js/app.js\"></script>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<script src=\"js/browser-controller.js\"></script>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<script src=\"js/location-controller.js\"></script>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<script src=\"js/login-controller.js\"></script>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<script src=\"js/details-controller.js\"></script>");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_2 = context.getConfigurationProperties();
      String _orDefault_2 = _configurationProperties_2.getOrDefault("swagger", "true");
      boolean _equalsIgnoreCase_2 = _orDefault_2.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_2) {
        _builder.append("    ");
        _builder.append("<script src=\"js/api-controller.js\"></script>");
        _builder.newLine();
      }
    }
    _builder.newLine();
    _builder.append("</head>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<body class=\"skin-blue\">");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("<div class=\"wrapper\">");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("<!-- Main Header -->");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("<header class=\"main-header\">");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("<!-- Logo -->");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("<a href=\"#\" class=\"logo\">");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<!-- mini logo for sidebar mini 50x50 pixels -->");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<span class=\"logo-mini\"><b>IoT</b></span>");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<!-- logo for regular state and mobile devices -->");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<span class=\"logo-lg\"><b>");
    String _name_2 = element.getName();
    _builder.append(_name_2, "       ");
    _builder.append("</b>App</span>");
    _builder.newLineIfNotEmpty();
    _builder.append("     ");
    _builder.append("</a>");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("<!-- Header Navbar -->");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("<nav class=\"navbar navbar-static-top\" role=\"navigation\">");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<!-- Sidebar toggle button-->");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<a href=\"#\" class=\"sidebar-toggle\" data-toggle=\"push-menu\" role=\"button\">");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("<span class=\"sr-only\">Toggle navigation</span>");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("</a>");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<!-- Navbar Right Menu -->");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<div class=\"navbar-custom-menu\">");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("<ul class=\"nav navbar-nav\">");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("</ul>");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("</nav>");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("</header>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("   ");
    _builder.append("<!-- Left side column. contains the logo and sidebar -->");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("<aside class=\"main-sidebar\">");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("<section class=\"sidebar\">");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<ul class=\"sidebar-menu user-panel\" ng-show=\"authenticated\">");
    _builder.newLine();
    _builder.append("\t\t\t            ");
    _builder.append("<li class=\"user-menu treeview image open active\">");
    _builder.newLine();
    _builder.append("\t\t\t                ");
    _builder.append("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" aria-expanded=\"true\">");
    _builder.newLine();
    _builder.append("\t\t\t                  ");
    _builder.append("<span class=\"hidden-xs\"><strong>{{currentUser}}</strong></span>");
    _builder.newLine();
    _builder.append("\t\t\t                  ");
    _builder.append("<i class=\"fa fa-angle-left pull-right\"></i>");
    _builder.newLine();
    _builder.append("\t\t\t                ");
    _builder.append("</a>");
    _builder.newLine();
    _builder.append("\t\t\t                ");
    _builder.append("<ul class=\"treeview-menu menu-open\" style=\"display: block;\">");
    _builder.newLine();
    _builder.append("\t\t\t                    ");
    _builder.append("<li><a href=\"#\"><strong>Subject: </strong></a><span style=\"color: white\">{{ currentUserSub }}</span></li>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t\t");
    _builder.append("<li><a href=\"#\" ng-click=\"logout();\"><i class=\"fa fa-sign-out\"></i> Sign out</a></li>");
    _builder.newLine();
    _builder.append("\t\t\t\t\t\t\t");
    _builder.append("</ul>");
    _builder.newLine();
    _builder.append("\t\t              ");
    _builder.append("</li>");
    _builder.newLine();
    _builder.append("\t\t          ");
    _builder.append("</ul>");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<!-- Sidebar Menu -->");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("<ul class=\"sidebar-menu\">");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("<li class=\"header\">Home</li>");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("<li><a href=\"#/browser\"><i class=\"fa fa-th\"></i> <span>");
    String _name_3 = element.getName();
    _builder.append(_name_3, "         ");
    _builder.append(" Browser</span></a></li>");
    _builder.newLineIfNotEmpty();
    _builder.append("         ");
    _builder.append("<li><a href=\"#/api\"><i class=\"fa fa-th\"></i> <span>Swagger</span></a></li>");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("</ul><!-- /.sidebar-menu -->");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("</section>");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("<!-- /.sidebar -->");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("</aside>");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("<!-- Content Wrapper. Contains page content -->");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("<div class=\"content-wrapper\" style=\"min-height:988px;\" ui-view> </div>");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("<!-- Main Footer -->");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("<footer class=\"main-footer\">");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("<!-- To the right -->");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("<div class=\"pull-right hidden-xs\">");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("Generated by Eclipse Vorto");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("</div>");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("</footer>");
    _builder.newLine();
    _builder.newLine();
    _builder.append(" ");
    _builder.append("</div><!-- ./wrapper -->");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("</body>");
    _builder.newLine();
    _builder.newLine();
    _builder.newLine();
    _builder.append("</html>");
    _builder.newLine();
    return _builder.toString();
  }
}
