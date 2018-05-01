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
public class LinkTemplate implements IFileTemplate<InformationModel> {
  private String targetPath;
  
  private String classPackage;
  
  public LinkTemplate(final String targetPath, final String classPackage) {
    this.targetPath = targetPath;
    this.classPackage = classPackage;
  }
  
  @Override
  public String getFileName(final InformationModel context) {
    return "Link.java";
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
    _builder.append("import org.eclipse.californium.core.coap.CoAP;");
    _builder.newLine();
    _builder.append("import org.eclipse.californium.core.coap.LinkFormat;");
    _builder.newLine();
    _builder.append("import org.eclipse.californium.core.coap.MediaTypeRegistry;");
    _builder.newLine();
    _builder.append("import org.eclipse.californium.core.server.resources.CoapExchange;");
    _builder.newLine();
    _builder.append("import org.eclipse.californium.core.server.resources.ConcurrentCoapResource;");
    _builder.newLine();
    _builder.append("import org.eclipse.californium.core.server.resources.Resource;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import java.util.Collection;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import static org.eclipse.californium.core.coap.MediaTypeRegistry.APPLICATION_LINK_FORMAT;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class Link extends ConcurrentCoapResource {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public Link(String name) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("super(name, SINGLE_THREADED);");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("getAttributes().addContentType(MediaTypeRegistry.APPLICATION_LINK_FORMAT);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public void handleGET(CoapExchange exchange) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("final Collection<Resource> children = this.getChildren();");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("StringBuilder sb = new StringBuilder();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("        ");
    _builder.append("for (Resource child : children) {");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("sb.append(LinkFormat.serializeResource(child));");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("exchange.respond(CoAP.ResponseCode.CONTENT, sb.toString(), APPLICATION_LINK_FORMAT);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
