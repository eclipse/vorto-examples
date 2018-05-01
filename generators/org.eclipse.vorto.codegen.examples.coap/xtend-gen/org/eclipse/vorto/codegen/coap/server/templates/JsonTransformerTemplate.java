/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
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
package org.eclipse.vorto.codegen.coap.server.templates;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class JsonTransformerTemplate implements IFileTemplate<InformationModel> {
  private String targetPath;
  
  private String classPackage;
  
  public JsonTransformerTemplate(final String targetPath, final String classPackage) {
    this.targetPath = targetPath;
    this.classPackage = classPackage;
  }
  
  @Override
  public String getFileName(final InformationModel context) {
    return "JsonTransformer.java";
  }
  
  @Override
  public String getPath(final InformationModel context) {
    return this.targetPath;
  }
  
  @Override
  public String getContent(final InformationModel context, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.classPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import com.fasterxml.jackson.core.JsonProcessingException;");
    _builder.newLine();
    _builder.append("import com.fasterxml.jackson.databind.ObjectMapper;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import java.io.IOException;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* This implements an Transformer for JSON (Java Simple Object Notation) Created by laj7rng on 15.12.15.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class JsonTransformer {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("ObjectMapper mapper;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public JsonTransformer() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("mapper = new ObjectMapper();");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public byte[] serialize(Object value) {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("byte[] serialized = null;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("serialized = mapper.writeValueAsBytes(value);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("} catch (JsonProcessingException e) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return serialized;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public Object deserialize(Class<?> valueType, byte[] src) {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Object value = null;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("value = mapper.readValue(src, valueType);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("} catch (IOException e) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("return value;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
