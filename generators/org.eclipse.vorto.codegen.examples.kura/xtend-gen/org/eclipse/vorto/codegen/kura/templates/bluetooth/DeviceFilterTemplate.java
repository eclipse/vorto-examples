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
package org.eclipse.vorto.codegen.kura.templates.bluetooth;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * @author Erle Czar Mantos - Robert Bosch (SEA) Pte. Ltd.
 */
@SuppressWarnings("all")
public class DeviceFilterTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    _builder.append(_name, "");
    _builder.append("DeviceFilter.java");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _javaPackageBasePath = Utils.getJavaPackageBasePath(context);
    _builder.append(_javaPackageBasePath, "");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _javaPackage = Utils.getJavaPackage(element);
    _builder.append(_javaPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.util.function.Predicate;");
    _builder.newLine();
    _builder.append("import java.util.Objects;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.eclipse.kura.bluetooth.BluetoothDevice;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    String _name = element.getName();
    _builder.append(_name, "");
    _builder.append("DeviceFilter implements Predicate<BluetoothDevice> {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private String filter;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_1 = element.getName();
    _builder.append(_name_1, "\t");
    _builder.append("DeviceFilter(");
    String _name_2 = element.getName();
    _builder.append(_name_2, "\t");
    _builder.append("Configuration configuration) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.filter = Objects.requireNonNull(configuration.deviceFilter);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean test(BluetoothDevice bluetoothDevice) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return bluetoothDevice.getAdress().contains(filter);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
