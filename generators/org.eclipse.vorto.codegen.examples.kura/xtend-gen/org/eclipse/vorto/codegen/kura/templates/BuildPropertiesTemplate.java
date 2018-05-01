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
package org.eclipse.vorto.codegen.kura.templates;

import java.util.Map;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class BuildPropertiesTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("build.properties");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _basePath = Utils.getBasePath(context);
    _builder.append(_basePath, "");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("output.. = bin/");
    _builder.newLine();
    _builder.append("bin.includes = META-INF/,\\");
    _builder.newLine();
    _builder.append("               ");
    _builder.append(".,\\");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("OSGI-INF/,\\");
    _builder.newLine();
    {
      boolean _or = false;
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _or = true;
      } else {
        Map<String, String> _configurationProperties_1 = context.getConfigurationProperties();
        String _orDefault_1 = _configurationProperties_1.getOrDefault("boschhub", "false");
        boolean _equalsIgnoreCase_1 = _orDefault_1.equalsIgnoreCase("true");
        _or = _equalsIgnoreCase_1;
      }
      if (_or) {
        _builder.append("               ");
        _builder.append("lib/,\\");
        _builder.newLine();
        _builder.append("               ");
        _builder.append("secret/,\\");
        _builder.newLine();
      }
    }
    _builder.append("               ");
    _builder.append("build.properties");
    _builder.newLine();
    _builder.append("source.. = src/");
    _builder.newLine();
    _builder.append("src.includes = bin/,\\");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("OSGI-INF/,\\");
    _builder.newLine();
    _builder.append("               ");
    _builder.append("META-INF/,\\");
    _builder.newLine();
    {
      boolean _or_1 = false;
      Map<String, String> _configurationProperties_2 = context.getConfigurationProperties();
      String _orDefault_2 = _configurationProperties_2.getOrDefault("boschcloud", "false");
      boolean _equalsIgnoreCase_2 = _orDefault_2.equalsIgnoreCase("true");
      if (_equalsIgnoreCase_2) {
        _or_1 = true;
      } else {
        Map<String, String> _configurationProperties_3 = context.getConfigurationProperties();
        String _orDefault_3 = _configurationProperties_3.getOrDefault("boschhub", "false");
        boolean _equalsIgnoreCase_3 = _orDefault_3.equalsIgnoreCase("true");
        _or_1 = _equalsIgnoreCase_3;
      }
      if (_or_1) {
        _builder.append("               ");
        _builder.append("lib/,\\");
        _builder.newLine();
        _builder.append("               ");
        _builder.append("secret/,\\");
        _builder.newLine();
      }
    }
    _builder.append("               ");
    _builder.append("build.properties");
    _builder.newLine();
    return _builder.toString();
  }
}
