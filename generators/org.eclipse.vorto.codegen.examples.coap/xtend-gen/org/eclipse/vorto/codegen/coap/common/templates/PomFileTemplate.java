/**
 * Copyright (c) 2014 Bosch Software Innovations GmbH and others.
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
package org.eclipse.vorto.codegen.coap.common.templates;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class PomFileTemplate implements IFileTemplate<InformationModel> {
  private String artefactId;
  
  private String mainClass;
  
  private String projectName;
  
  public PomFileTemplate(final String artefactId, final String mainClass, final String projectName) {
    this.artefactId = artefactId;
    this.mainClass = mainClass;
    this.projectName = projectName;
  }
  
  @Override
  public String getContent(final InformationModel model, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<modelVersion>4.0.0</modelVersion>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<groupId>org.eclipse.vorto.examples</groupId>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<artifactId>");
    _builder.append(this.artefactId, "\t");
    _builder.append("</artifactId>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<version>");
    String _version = model.getVersion();
    _builder.append(_version, "\t");
    _builder.append("</version>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<name>Sample project generated from ");
    String _name = model.getName();
    _builder.append(_name, "\t");
    _builder.append("</name>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<packaging>jar</packaging>");
    _builder.newLine();
    _builder.append("\t ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<properties>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<app.main.class>");
    _builder.append(this.mainClass, "\t\t");
    _builder.append("</app.main.class>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("<!-- Use the latest version whenever possible. -->");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<jackson.version>2.5.0</jackson.version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<californium.version>1.0.1</californium.version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<shade.version>2.4.3</shade.version>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</properties>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<dependencies>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<groupId>org.eclipse.californium</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<artifactId>californium-core</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>${californium.version}</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<dependency>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<groupId>com.fasterxml.jackson.core</groupId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<artifactId>jackson-databind</artifactId>");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("<version>${jackson.version}</version>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("</dependency>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("</dependencies>");
    _builder.newLine();
    _builder.append("</project>");
    _builder.newLine();
    return _builder.toString();
  }
  
  @Override
  public String getFileName(final InformationModel context) {
    return (this.projectName + "/pom.xml");
  }
  
  @Override
  public String getPath(final InformationModel context) {
    return null;
  }
}
