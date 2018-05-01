package org.eclipse.vorto.codegen.webui.templates.service.sample;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.TemplateUtils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class SampleDataServiceTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("SampleDataService.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _baseApplicationPath = TemplateUtils.getBaseApplicationPath(context);
    _builder.append(_baseApplicationPath, "");
    _builder.append("/service/sample");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.example.iot.");
    String _name = element.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(".service.sample;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.util.ArrayList;");
    _builder.newLine();
    _builder.append("import java.util.Collections;");
    _builder.newLine();
    _builder.append("import java.util.Date;");
    _builder.newLine();
    _builder.append("import java.util.List;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.eclipse.vorto.repository.api.ModelId;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.example.iot.");
    String _name_1 = element.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    _builder.append(_lowerCase_1, "");
    _builder.append(".model.*;");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.example.iot.");
    String _name_2 = element.getName();
    String _lowerCase_2 = _name_2.toLowerCase();
    _builder.append(_lowerCase_2, "");
    _builder.append(".service.DataService;");
    _builder.newLineIfNotEmpty();
    _builder.append("import com.example.iot.");
    String _name_3 = element.getName();
    String _lowerCase_3 = _name_3.toLowerCase();
    _builder.append(_lowerCase_3, "");
    _builder.append(".service.Query;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Implement this Service to fetch thing data ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class SampleDataService implements DataService {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Query newQuery() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return new Query() {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("public String getValue() {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return \"*\";");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}};");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public List<");
    String _name_4 = element.getName();
    _builder.append(_name_4, "\t");
    _builder.append("> queryThings(Query query) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return Collections.emptyList();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_5 = element.getName();
    _builder.append(_name_5, "\t");
    _builder.append(" getThing(String thingId) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void registerAsyncCallback(String thingId, DataCallback callback) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void deregisterAsyncCallback(String thingId) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
}
