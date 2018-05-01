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
public class URIAnalyzerTemplate implements IFileTemplate<InformationModel> {
  private String targetPath;
  
  private String classPackage;
  
  public URIAnalyzerTemplate(final String targetPath, final String classPackage) {
    this.targetPath = targetPath;
    this.classPackage = classPackage;
  }
  
  @Override
  public String getFileName(final InformationModel context) {
    return "URIAnalyzer.java";
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
    _builder.append("public class URIAnalyzer {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static String getService(String uri) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("String tokens[] = uri.split(\"/\");");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("if (tokens.length > 2) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return tokens[tokens.length - 3];");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return \"\";");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public static String getInstance(String uri) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("String tokens[] = uri.split(\"/\");");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("if (tokens.length > 1) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return tokens[tokens.length - 2];");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return \"\";");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public static String getOperation(String uri) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("String tokens[] = uri.split(\"/\");");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("if (tokens.length > 0) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return tokens[tokens.length - 1];");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("return \"\";");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
