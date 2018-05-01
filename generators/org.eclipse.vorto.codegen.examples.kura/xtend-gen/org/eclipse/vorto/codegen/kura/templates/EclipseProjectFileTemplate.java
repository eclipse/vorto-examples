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

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class EclipseProjectFileTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    return ".project";
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _basePath = Utils.getBasePath(context);
    _builder.append(_basePath, "");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel context, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<projectDescription>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<name>");
    String _javaPackage = Utils.getJavaPackage(context);
    _builder.append(_javaPackage, "    ");
    _builder.append("</name>");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("<comment></comment>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<projects>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("</projects>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<buildSpec>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<buildCommand>");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("<name>org.eclipse.jdt.core.javabuilder</name>");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("<arguments>");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("</arguments>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("</buildCommand>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<buildCommand>");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("<name>org.eclipse.pde.ManifestBuilder</name>");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("<arguments>");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("</arguments>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("</buildCommand>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("</buildSpec>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("<natures>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<nature>org.eclipse.pde.PluginNature</nature>");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("<nature>org.eclipse.jdt.core.javanature</nature>");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("</natures>");
    _builder.newLine();
    _builder.append("</projectDescription>");
    _builder.newLine();
    return _builder.toString();
  }
}
