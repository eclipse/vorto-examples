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
package org.eclipse.vorto.codegen.kura.templates.bluetooth;

import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * @author Erle Czar Mantos - Robert Bosch (SEA) Pte. Ltd.
 */
@SuppressWarnings("all")
public class ConfigurationTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    _builder.append(_name, "");
    _builder.append("Configuration.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _javaPackageBasePath = Utils.getJavaPackageBasePath(context);
    _builder.append(_javaPackageBasePath, "");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _javaPackage = Utils.getJavaPackage(element);
    _builder.append(_javaPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.util.Map;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    String _name = element.getName();
    _builder.append(_name, "");
    _builder.append("Configuration {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private final static String PROPERTY_INAME = \"iname\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private final static String PROPERTY_SCAN = \"scan_enable\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private final static String PROPERTY_SCANINTERVAL = \"scan_interval\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private final static String PROPERTY_SCANTIME = \"scan_time\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private final static String PROPERTY_UPDATEINTERVAL = \"update_interval\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private final static String PROPERTY_DEVICEFILTER = \"device_filter\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append("\t");
        _builder.append("private final static String PROPERTY_BOSCHCLOUD_ENDPOINT = \"boschcloud_endpoint\";");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("private final static String PROPERTY_BOSCHCLOUD_SOLUTIONID = \"boschcloud_solutionid\";");
        _builder.newLine();
      } else {
        {
          Map<String, String> _configurationProperties_1 = context.getConfigurationProperties();
          String _orDefault_1 = _configurationProperties_1.getOrDefault("boschhub", "false");
          boolean _equalsIgnoreCase_1 = _orDefault_1.equalsIgnoreCase("true");
          if (_equalsIgnoreCase_1) {
            _builder.append("\t");
            _builder.append("private final static String PROPERTY_HUB_URL = \"mqttHostUrl\";");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("private final static String PROPERTY_HUB_TENANT = \"hubTenant\";");
            _builder.newLine();
          } else {
            _builder.append("\t");
            _builder.append("private final static String PROPERTY_PUBLISHTOPIC = \"publishTopic\";");
            _builder.newLine();
          }
        }
      }
    }
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("\t");
        _builder.append("private final static String PROPERTY_");
        String _name_1 = fbProperty.getName();
        String _upperCase = _name_1.toUpperCase();
        _builder.append(_upperCase, "\t");
        _builder.append(" = \"enable");
        String _name_2 = fbProperty.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_2);
        _builder.append(_firstUpper, "\t");
        _builder.append("\";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public int scantime = 10;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public int scaninterval = 60;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public int updateInterval = 10;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String iname = \"hci0\";");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean enableScan = false;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String deviceFilter;");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_2 = context.getConfigurationProperties();
      String _orDefault_2 = _configurationProperties_2.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase_2 = _orDefault_2.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_2) {
        _builder.append("\t");
        _builder.append("public String endpoint;");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public String solutionId;");
        _builder.newLine();
      } else {
        {
          Map<String, String> _configurationProperties_3 = context.getConfigurationProperties();
          String _orDefault_3 = _configurationProperties_3.getOrDefault("boschhub", "false");
          boolean _equalsIgnoreCase_3 = _orDefault_3.equalsIgnoreCase("true");
          if (_equalsIgnoreCase_3) {
            _builder.append("\t");
            _builder.append("public String hubUrl;");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("public String hubTenant;");
            _builder.newLine();
          } else {
            _builder.append("\t");
            _builder.append("public String publishTopic;");
            _builder.newLine();
          }
        }
      }
    }
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties_1 = element.getProperties();
      for(final FunctionblockProperty fbProperty_1 : _properties_1) {
        _builder.append("\t");
        _builder.append("public boolean enable");
        String _name_3 = fbProperty_1.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_3);
        _builder.append(_firstUpper_1, "\t");
        _builder.append(" = false;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static ");
    String _name_4 = element.getName();
    _builder.append(_name_4, "\t");
    _builder.append("Configuration newConfiguration() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return new ");
    String _name_5 = element.getName();
    _builder.append(_name_5, "\t\t");
    _builder.append("Configuration();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static ");
    String _name_6 = element.getName();
    _builder.append(_name_6, "\t");
    _builder.append("Configuration configurationFrom(Map<String, Object> properties) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    String _name_7 = element.getName();
    _builder.append(_name_7, "\t\t\t");
    _builder.append("Configuration ");
    String _name_8 = element.getName();
    String _lowerCase = _name_8.toLowerCase();
    _builder.append(_lowerCase, "\t\t\t");
    _builder.append("Configuration = new ");
    String _name_9 = element.getName();
    _builder.append(_name_9, "\t\t\t");
    _builder.append("Configuration();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (properties != null) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("if (properties.get(");
    String _name_10 = element.getName();
    _builder.append(_name_10, "\t\t\t\t");
    _builder.append("Configuration.PROPERTY_SCAN) != null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t");
    String _name_11 = element.getName();
    String _lowerCase_1 = _name_11.toLowerCase();
    _builder.append(_lowerCase_1, "\t\t\t\t\t");
    _builder.append("Configuration.enableScan = (Boolean) properties.get(");
    String _name_12 = element.getName();
    _builder.append(_name_12, "\t\t\t\t\t");
    _builder.append("Configuration.PROPERTY_SCAN);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("if (properties.get(");
    String _name_13 = element.getName();
    _builder.append(_name_13, "\t\t\t\t");
    _builder.append("Configuration.PROPERTY_SCANTIME) != null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t");
    String _name_14 = element.getName();
    String _lowerCase_2 = _name_14.toLowerCase();
    _builder.append(_lowerCase_2, "\t\t\t\t\t");
    _builder.append("Configuration.scantime = (Integer) properties.get(");
    String _name_15 = element.getName();
    _builder.append(_name_15, "\t\t\t\t\t");
    _builder.append("Configuration.PROPERTY_SCANTIME);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("if (properties.get(");
    String _name_16 = element.getName();
    _builder.append(_name_16, "\t\t\t\t");
    _builder.append("Configuration.PROPERTY_SCANINTERVAL) != null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t");
    String _name_17 = element.getName();
    String _lowerCase_3 = _name_17.toLowerCase();
    _builder.append(_lowerCase_3, "\t\t\t\t\t");
    _builder.append("Configuration.scaninterval = (Integer) properties.get(");
    String _name_18 = element.getName();
    _builder.append(_name_18, "\t\t\t\t\t");
    _builder.append("Configuration.PROPERTY_SCANINTERVAL);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("if (properties.get(");
    String _name_19 = element.getName();
    _builder.append(_name_19, "\t\t\t\t");
    _builder.append("Configuration.PROPERTY_UPDATEINTERVAL) != null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t");
    String _name_20 = element.getName();
    String _lowerCase_4 = _name_20.toLowerCase();
    _builder.append(_lowerCase_4, "\t\t\t\t\t");
    _builder.append("Configuration.updateInterval = (Integer) properties.get(");
    String _name_21 = element.getName();
    _builder.append(_name_21, "\t\t\t\t\t");
    _builder.append("Configuration.PROPERTY_UPDATEINTERVAL);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("if (properties.get(");
    String _name_22 = element.getName();
    _builder.append(_name_22, "\t\t\t\t");
    _builder.append("Configuration.PROPERTY_DEVICEFILTER) != null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t");
    String _name_23 = element.getName();
    String _lowerCase_5 = _name_23.toLowerCase();
    _builder.append(_lowerCase_5, "\t\t\t\t\t");
    _builder.append("Configuration.deviceFilter = (String) properties.get(");
    String _name_24 = element.getName();
    _builder.append(_name_24, "\t\t\t\t\t");
    _builder.append("Configuration.PROPERTY_DEVICEFILTER);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("if (properties.get(");
    String _name_25 = element.getName();
    _builder.append(_name_25, "\t\t\t\t");
    _builder.append("Configuration.PROPERTY_INAME) != null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t\t");
    String _name_26 = element.getName();
    String _lowerCase_6 = _name_26.toLowerCase();
    _builder.append(_lowerCase_6, "\t\t\t\t\t");
    _builder.append("Configuration.iname = (String) properties.get(");
    String _name_27 = element.getName();
    _builder.append(_name_27, "\t\t\t\t\t");
    _builder.append("Configuration.PROPERTY_INAME);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    {
      Map<String, String> _configurationProperties_4 = context.getConfigurationProperties();
      String _orDefault_4 = _configurationProperties_4.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase_4 = _orDefault_4.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_4) {
        _builder.append("\t\t\t\t");
        _builder.append("if (properties.get(");
        String _name_28 = element.getName();
        _builder.append(_name_28, "\t\t\t\t");
        _builder.append("Configuration.PROPERTY_BOSCHCLOUD_ENDPOINT) != null) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("\t");
        String _name_29 = element.getName();
        String _lowerCase_7 = _name_29.toLowerCase();
        _builder.append(_lowerCase_7, "\t\t\t\t\t");
        _builder.append("Configuration.endpoint = (String) properties.get(");
        String _name_30 = element.getName();
        _builder.append(_name_30, "\t\t\t\t\t");
        _builder.append("Configuration.PROPERTY_BOSCHCLOUD_ENDPOINT);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.append("if (properties.get(");
        String _name_31 = element.getName();
        _builder.append(_name_31, "\t\t\t\t");
        _builder.append("Configuration.PROPERTY_BOSCHCLOUD_SOLUTIONID) != null) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("\t");
        String _name_32 = element.getName();
        String _lowerCase_8 = _name_32.toLowerCase();
        _builder.append(_lowerCase_8, "\t\t\t\t\t");
        _builder.append("Configuration.solutionId = (String) properties.get(");
        String _name_33 = element.getName();
        _builder.append(_name_33, "\t\t\t\t\t");
        _builder.append("Configuration.PROPERTY_BOSCHCLOUD_SOLUTIONID);");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.newLine();
      } else {
        {
          Map<String, String> _configurationProperties_5 = context.getConfigurationProperties();
          String _orDefault_5 = _configurationProperties_5.getOrDefault("boschhub", "false");
          boolean _equalsIgnoreCase_5 = _orDefault_5.equalsIgnoreCase("true");
          if (_equalsIgnoreCase_5) {
            _builder.append("\t\t\t\t");
            _builder.append("if (properties.get(");
            String _name_34 = element.getName();
            _builder.append(_name_34, "\t\t\t\t");
            _builder.append("Configuration.PROPERTY_HUB_URL) != null) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t");
            _builder.append("\t");
            String _name_35 = element.getName();
            String _lowerCase_9 = _name_35.toLowerCase();
            _builder.append(_lowerCase_9, "\t\t\t\t\t");
            _builder.append("Configuration.hubUrl = (String) properties.get(");
            String _name_36 = element.getName();
            _builder.append(_name_36, "\t\t\t\t\t");
            _builder.append("Configuration.PROPERTY_HUB_URL);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t\t\t\t");
            _builder.newLine();
            _builder.append("\t\t\t\t");
            _builder.append("if (properties.get(");
            String _name_37 = element.getName();
            _builder.append(_name_37, "\t\t\t\t");
            _builder.append("Configuration.PROPERTY_HUB_TENANT) != null) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t");
            _builder.append("\t");
            String _name_38 = element.getName();
            String _lowerCase_10 = _name_38.toLowerCase();
            _builder.append(_lowerCase_10, "\t\t\t\t\t");
            _builder.append("Configuration.hubTenant = (String) properties.get(");
            String _name_39 = element.getName();
            _builder.append(_name_39, "\t\t\t\t\t");
            _builder.append("Configuration.PROPERTY_HUB_TENANT);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t\t\t\t");
            _builder.newLine();
          } else {
            _builder.append("\t\t\t\t");
            _builder.append("if (properties.get(");
            String _name_40 = element.getName();
            _builder.append(_name_40, "\t\t\t\t");
            _builder.append("Configuration.PROPERTY_PUBLISHTOPIC) != null) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t");
            _builder.append("\t");
            String _name_41 = element.getName();
            String _lowerCase_11 = _name_41.toLowerCase();
            _builder.append(_lowerCase_11, "\t\t\t\t\t");
            _builder.append("Configuration.publishTopic = (String) properties.get(");
            String _name_42 = element.getName();
            _builder.append(_name_42, "\t\t\t\t\t");
            _builder.append("Configuration.PROPERTY_PUBLISHTOPIC);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t\t\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t\t\t\t");
            _builder.newLine();
          }
        }
      }
    }
    {
      EList<FunctionblockProperty> _properties_2 = element.getProperties();
      for(final FunctionblockProperty fbProperty_2 : _properties_2) {
        _builder.append("\t\t\t\t");
        _builder.append("if (properties.get(");
        String _name_43 = element.getName();
        _builder.append(_name_43, "\t\t\t\t");
        _builder.append("Configuration.PROPERTY_");
        String _name_44 = fbProperty_2.getName();
        String _upperCase_1 = _name_44.toUpperCase();
        _builder.append(_upperCase_1, "\t\t\t\t");
        _builder.append(") != null) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("\t");
        String _name_45 = element.getName();
        String _lowerCase_12 = _name_45.toLowerCase();
        _builder.append(_lowerCase_12, "\t\t\t\t\t");
        _builder.append("Configuration.enable");
        String _name_46 = fbProperty_2.getName();
        String _firstUpper_2 = StringExtensions.toFirstUpper(_name_46);
        _builder.append(_firstUpper_2, "\t\t\t\t\t");
        _builder.append(" = (Boolean) properties.get(");
        String _name_47 = element.getName();
        _builder.append(_name_47, "\t\t\t\t\t");
        _builder.append("Configuration.PROPERTY_");
        String _name_48 = fbProperty_2.getName();
        String _upperCase_2 = _name_48.toUpperCase();
        _builder.append(_upperCase_2, "\t\t\t\t\t");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t\t");
        _builder.newLine();
      }
    }
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return ");
    String _name_49 = element.getName();
    String _lowerCase_13 = _name_49.toLowerCase();
    _builder.append(_lowerCase_13, "\t\t\t");
    _builder.append("Configuration;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
