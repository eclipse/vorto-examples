/**
 * Copyright (c) 2016 Bosch Software Innovations GmbH and others.
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
package org.eclipse.vorto.codegen.prosystfi.templates;

import java.util.Set;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;

@SuppressWarnings("all")
public class FIManifestFileTemplate implements IFileTemplate<InformationModel> {
  private Set<String> exports;
  
  public FIManifestFileTemplate(final Set<String> exports) {
    this.exports = exports;
  }
  
  @Override
  public String getContent(final InformationModel context, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Manifest-Version: 1.0");
    _builder.newLine();
    _builder.append("Bundle-ManifestVersion: 2");
    _builder.newLine();
    _builder.append("Bundle-Name: ");
    String _displayname = context.getDisplayname();
    _builder.append(_displayname, "");
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-SymbolicName: ");
    String _name = context.getName();
    _builder.append(_name, "");
    _builder.append(";singleton:=true");
    _builder.newLineIfNotEmpty();
    _builder.append("Bundle-Version: ");
    String _version = context.getVersion();
    _builder.append(_version, "");
    _builder.newLineIfNotEmpty();
    _builder.append("Import-Package: com.prosyst.mbs.services.fim,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("com.prosyst.mbs.services.fim.annotations,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("com.prosyst.mbs.services.fim.spi,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("com.prosyst.util.security");
    _builder.newLine();
    _builder.append("Bundle-RequiredExecutionEnvironment: JavaSE-1.5");
    _builder.newLine();
    _builder.append("Export-Package: ");
    String _printExports = this.printExports();
    return (_builder.toString() + _printExports);
  }
  
  @Override
  public String getFileName(final InformationModel context) {
    return "MANIFEST.MF";
  }
  
  @Override
  public String getPath(final InformationModel context) {
    return "META-INF";
  }
  
  public String printExports() {
    StringBuilder res = new StringBuilder();
    String _get = ((String[])Conversions.unwrapArray(this.exports, String.class))[0];
    res.append(_get);
    for (int i = 1; (i < this.exports.size()); i++) {
      StringBuilder _append = res.append(",");
      StringBuilder _append_1 = _append.append("\n ");
      String _get_1 = ((String[])Conversions.unwrapArray(this.exports, String.class))[i];
      _append_1.append(_get_1);
    }
    res.append("\n");
    return res.toString();
  }
}
