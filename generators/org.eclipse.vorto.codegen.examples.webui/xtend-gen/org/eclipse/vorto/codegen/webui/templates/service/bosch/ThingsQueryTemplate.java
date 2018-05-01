package org.eclipse.vorto.codegen.webui.templates.service.bosch;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ThingsQueryTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ThingsQuery.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(context);
    _builder.append(_baseApplicationPath, "");
    _builder.append("/service/bosch");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".service.bosch;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.io.UnsupportedEncodingException;");
    _builder.newLine();
    _builder.append("import java.net.URLEncoder;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.example.iot.");
    String _name_1 = element.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "");
    _builder.append(".service.Query;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class ThingsQuery implements Query {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private StringBuilder builder = new StringBuilder();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ThingsQuery() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("withFilter(\"eq(attributes/_modelId,\\\"");
    String _namespace = element.getNamespace();
    _builder.append(_namespace, "\t\t");
    _builder.append(".");
    String _name_2 = element.getName();
    _builder.append(_name_2, "\t\t");
    _builder.append(":");
    String _version = element.getVersion();
    _builder.append(_version, "\t\t");
    _builder.append("\\\")\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void withFilter(String filter) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (builder.length() > 0) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("builder.append(\"&\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("builder.append(\"filter=\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("builder.append(encode(filter));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String encode(String param) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return URLEncoder.encode(param,\"utf-8\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (UnsupportedEncodingException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("throw new IllegalArgumentException(\"invalid url\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void withOptions(String options) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (builder.length() > 0) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("builder.append(\"&\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("builder.append(\"option=\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("builder.append(encode(options));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String getValue() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return builder.toString();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} ");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
}
