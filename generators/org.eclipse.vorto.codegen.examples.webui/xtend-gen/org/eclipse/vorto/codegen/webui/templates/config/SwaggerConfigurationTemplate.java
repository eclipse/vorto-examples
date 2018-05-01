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
package org.eclipse.vorto.codegen.webui.templates.config;

import java.util.Map;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class SwaggerConfigurationTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("SwaggerConfiguration.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(context);
    _builder.append(_baseApplicationPath, "");
    _builder.append("/config");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".config;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import static com.google.common.base.Predicates.or;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.springframework.context.annotation.Bean;");
    _builder.newLine();
    _builder.append("import org.springframework.context.annotation.Configuration;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.google.common.base.Predicate;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import springfox.documentation.builders.PathSelectors;");
    _builder.newLine();
    _builder.append("import springfox.documentation.service.ApiInfo;");
    _builder.newLine();
    _builder.append("import springfox.documentation.spi.DocumentationType;");
    _builder.newLine();
    _builder.append("import springfox.documentation.spring.web.plugins.Docket;");
    _builder.newLine();
    _builder.append("import springfox.documentation.swagger2.annotations.EnableSwagger2;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@EnableSwagger2");
    _builder.newLine();
    _builder.append("@Configuration");
    _builder.newLine();
    _builder.append("public class SwaggerConfiguration {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Bean");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Docket vortoApi() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).useDefaultResponseMessages(false)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append(".select().paths(paths()).build();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@SuppressWarnings(\"unchecked\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private Predicate<String> paths() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return or(\tPathSelectors.regex(\"/rest/devices.*\")");
    {
      Map<String, String> _configurationProperties = context.getConfigurationProperties();
      String _orDefault = _configurationProperties.getOrDefault("history", "true");
      boolean _equalsIgnoreCase = _orDefault.equalsIgnoreCase("true");
      if (_equalsIgnoreCase) {
        _builder.append(",PathSelectors.regex(\"/rest/history/devices.*\")");
      }
    }
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ApiInfo apiInfo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new ApiInfo(\"");
    String _name_1 = element.getName();
    _builder.append(_name_1, "\t\t");
    _builder.append("\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("\"");
    String _name_2 = element.getName();
    _builder.append(_name_2, "\t\t\t\t");
    _builder.append(" Solution\",");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t\t");
    _builder.append("\"1.0.0\", \"\", \"Generated by Vorto from ");
    String _namespace = element.getNamespace();
    _builder.append(_namespace, "\t\t\t\t");
    _builder.append(".");
    String _name_3 = element.getName();
    _builder.append(_name_3, "\t\t\t\t");
    _builder.append(":");
    String _version = element.getVersion();
    _builder.append(_version, "\t\t\t\t");
    _builder.append("\", \"Bosch-SI\", \"\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
