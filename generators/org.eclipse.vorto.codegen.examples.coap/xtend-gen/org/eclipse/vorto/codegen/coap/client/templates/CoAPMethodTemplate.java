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
package org.eclipse.vorto.codegen.coap.client.templates;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class CoAPMethodTemplate implements IFileTemplate<InformationModel> {
  private String targetPath;
  
  private String classPackage;
  
  public CoAPMethodTemplate(final String targetPath, final String classPackage) {
    this.targetPath = targetPath;
    this.classPackage = classPackage;
  }
  
  @Override
  public String getFileName(final InformationModel context) {
    return "CoapMethod.java";
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
    _builder.append("public enum CoapMethod {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("GET(1),");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("POST(2),");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("PUT(3),");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("DELETE(4),");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("OBSERVE(5),");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("DISCOVER(6);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public final int value;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("CoapMethod(int value) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("this.value = value;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public static CoapMethod valueOf(int value) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("switch (value) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("case 1:");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("return GET;");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("case 2:");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("return POST;");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("case 3:");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("return PUT;");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("case 4:");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("return DELETE;");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("case 5:");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("return OBSERVE;");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("case 6:");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("return DISCOVER;");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("default:");
    _builder.newLine();
    _builder.append("                ");
    _builder.append("throw new IllegalArgumentException(\"Unknwon Method value \" + value);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder.toString();
  }
}
