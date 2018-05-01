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
package org.eclipse.vorto.codegen.kura.templates.osgiinf;

import java.util.Map;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ComponentXmlTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("component.xml");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _basePath = Utils.getBasePath(context);
    _builder.append(_basePath, "");
    _builder.append("/OSGI-INF");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.newLine();
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("bluetooth", "false");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append("<scr:component xmlns:scr=\"http://www.osgi.org/xmlns/scr/v1.1.0\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("activate=\"activate\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("configuration-policy=\"require\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("deactivate=\"deactivate\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("enabled=\"true\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("immediate=\"true\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("modified=\"updated\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("name=\"");
        String _javaPackage = Utils.getJavaPackage(element);
        _builder.append(_javaPackage, "\t");
        _builder.append(".");
        String _name = element.getName();
        _builder.append(_name, "\t");
        _builder.append("BluetoothFinder\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("<implementation class=\"");
        String _javaPackage_1 = Utils.getJavaPackage(element);
        _builder.append(_javaPackage_1, "   ");
        _builder.append(".");
        String _name_1 = element.getName();
        _builder.append(_name_1, "   ");
        _builder.append("BluetoothFinder\"/>");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("<service>");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("<provide interface=\"org.eclipse.kura.configuration.ConfigurableComponent\"/>");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("</service>");
        _builder.newLine();
        _builder.append("   ");
        _builder.newLine();
        _builder.append("   ");
        _builder.append("<property name=\"service.pid\" value=\"");
        String _javaPackage_2 = Utils.getJavaPackage(element);
        _builder.append(_javaPackage_2, "   ");
        _builder.append(".");
        String _name_2 = element.getName();
        _builder.append(_name_2, "   ");
        _builder.append("BluetoothFinder\"/>");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.newLine();
        {
          Map<String, String> _configurationProperties_1 = context.getConfigurationProperties();
          String _orDefault_1 = _configurationProperties_1.getOrDefault("boschcloud", "false");
          boolean _equalsIgnoreCase_1 = _orDefault_1.equalsIgnoreCase("true");
          boolean _not = (!_equalsIgnoreCase_1);
          if (_not) {
            _builder.append("   ");
            _builder.append("<reference bind=\"setCloudService\" ");
            _builder.newLine();
            _builder.append("   ");
            _builder.append(" \t\t  ");
            _builder.append("cardinality=\"1..1\" ");
            _builder.newLine();
            _builder.append("   ");
            _builder.append(" \t\t  ");
            _builder.append("interface=\"org.eclipse.kura.cloud.CloudService\"");
            _builder.newLine();
            _builder.append("   ");
            _builder.append(" \t\t  ");
            _builder.append("unbind=\"unsetCloudService\"/>");
            _builder.newLine();
            _builder.append("   ");
            _builder.append(" \t\t  ");
            _builder.newLine();
          }
        }
        _builder.append("   ");
        _builder.append("<reference bind=\"setBluetoothService\" ");
        _builder.newLine();
        _builder.append("              ");
        _builder.append("cardinality=\"1..1\" ");
        _builder.newLine();
        _builder.append("              ");
        _builder.append("interface=\"org.eclipse.kura.bluetooth.BluetoothService\" ");
        _builder.newLine();
        _builder.append("              ");
        _builder.append("name=\"BluetoothService\" ");
        _builder.newLine();
        _builder.append("              ");
        _builder.append("policy=\"static\" ");
        _builder.newLine();
        _builder.append("              ");
        _builder.append("unbind=\"unsetBluetoothService\"/>");
        _builder.newLine();
        _builder.append("              ");
        _builder.newLine();
        _builder.append("</scr:component>");
        _builder.newLine();
      } else {
        _builder.append("<scr:component xmlns:scr=\"http://www.osgi.org/xmlns/scr/v1.1.0\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("activate=\"activate\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("configuration-policy=\"require\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("deactivate=\"deactivate\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("enabled=\"true\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("immediate=\"true\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("modified=\"updated\" ");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("name=\"");
        String _javaPackage_3 = Utils.getJavaPackage(element);
        _builder.append(_javaPackage_3, "\t");
        _builder.append(".");
        String _name_3 = element.getName();
        _builder.append(_name_3, "\t");
        _builder.append("App\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("<implementation class=\"");
        String _javaPackage_4 = Utils.getJavaPackage(element);
        _builder.append(_javaPackage_4, "\t");
        _builder.append(".");
        String _name_4 = element.getName();
        _builder.append(_name_4, "\t");
        _builder.append("App\"/>");
        _builder.newLineIfNotEmpty();
        _builder.append("</scr:component>");
        _builder.newLine();
      }
    }
    return _builder.toString();
  }
}
