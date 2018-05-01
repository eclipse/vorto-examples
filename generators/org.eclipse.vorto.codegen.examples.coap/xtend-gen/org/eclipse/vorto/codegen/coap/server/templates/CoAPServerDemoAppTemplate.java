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
public class CoAPServerDemoAppTemplate implements IFileTemplate<InformationModel> {
  private String targetPath;
  
  private String classPackage;
  
  private String reqHndlImport;
  
  private String serverImport;
  
  public CoAPServerDemoAppTemplate(final String targetPath, final String classPackage, final String reqHndlImport, final String serverImport) {
    this.targetPath = targetPath;
    this.classPackage = classPackage;
    this.reqHndlImport = reqHndlImport;
    this.serverImport = serverImport;
  }
  
  @Override
  public String getFileName(final InformationModel context) {
    return "ServerDemoApp.java";
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
    _builder.append("import ");
    _builder.append(this.reqHndlImport, "");
    _builder.append(".*;");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    _builder.append(this.serverImport, "");
    _builder.append(".*;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class ServerDemoApp {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static void main (String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("CoAPRequestHandler handler = new CoAPRequestHandler();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Server server = new Server(handler);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("server.start();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("System.out.println(\"CoAP-Server started ...\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("System.out.println(\"Now browse to \'coap://localhost:5683/\' to discover your server!\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
