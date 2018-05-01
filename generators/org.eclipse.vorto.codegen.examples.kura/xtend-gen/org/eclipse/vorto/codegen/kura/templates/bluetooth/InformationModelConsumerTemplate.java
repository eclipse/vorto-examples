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

import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.kura.templates.Utils;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * @author Erle Czar Mantos - Robert Bosch (SEA) Pte. Ltd.
 */
@SuppressWarnings("all")
public class InformationModelConsumerTemplate implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    _builder.append(_name, "");
    _builder.append("Consumer.java");
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
    _builder.append("import java.util.Optional;");
    _builder.newLine();
    _builder.append("import java.util.function.Consumer;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.slf4j.Logger;");
    _builder.newLine();
    _builder.append("import org.slf4j.LoggerFactory;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import ");
    String _javaPackage_1 = Utils.getJavaPackage(element);
    _builder.append(_javaPackage_1, "");
    _builder.append(".cloud.IDataService;");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    String _javaPackage_2 = Utils.getJavaPackage(element);
    _builder.append(_javaPackage_2, "");
    _builder.append(".model.");
    String _name = element.getName();
    _builder.append(_name, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class ");
    String _name_1 = element.getName();
    _builder.append(_name_1, "");
    _builder.append("Consumer implements Consumer<Optional<");
    String _name_2 = element.getName();
    _builder.append(_name_2, "");
    _builder.append(">> {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static final Logger logger = LoggerFactory.getLogger(");
    String _name_3 = element.getName();
    _builder.append(_name_3, "\t");
    _builder.append("Consumer.class);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ");
    String _name_4 = element.getName();
    _builder.append(_name_4, "\t");
    _builder.append("Configuration configuration;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("private IDataService dataService;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_5 = element.getName();
    _builder.append(_name_5, "\t");
    _builder.append("Consumer(");
    String _name_6 = element.getName();
    _builder.append(_name_6, "\t");
    _builder.append("Configuration configuration, IDataService dataService) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.configuration = configuration;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.dataService = dataService;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void accept(Optional<");
    String _name_7 = element.getName();
    _builder.append(_name_7, "\t");
    _builder.append("> ");
    String _name_8 = element.getName();
    String _lowerCase = _name_8.toLowerCase();
    _builder.append(_lowerCase, "\t");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("if (");
    String _name_9 = element.getName();
    String _lowerCase_1 = _name_9.toLowerCase();
    _builder.append(_lowerCase_1, "\t\t");
    _builder.append(".isPresent()) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    String _name_10 = element.getName();
    _builder.append(_name_10, "\t\t\t");
    _builder.append(" my");
    String _name_11 = element.getName();
    _builder.append(_name_11, "\t\t\t");
    _builder.append(" = ");
    String _name_12 = element.getName();
    String _lowerCase_2 = _name_12.toLowerCase();
    _builder.append(_lowerCase_2, "\t\t\t");
    _builder.append(".get();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.newLine();
    {
      EList<FunctionblockProperty> _properties = element.getProperties();
      for(final FunctionblockProperty fbProperty : _properties) {
        _builder.append("\t\t\t");
        _builder.append("if (configuration.enable");
        String _name_13 = fbProperty.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_13);
        _builder.append(_firstUpper, "\t\t\t");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t");
        _builder.append("\t");
        _builder.append("dataService.publish");
        String _name_14 = fbProperty.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_14);
        _builder.append(_firstUpper_1, "\t\t\t\t");
        _builder.append("(my");
        String _name_15 = element.getName();
        _builder.append(_name_15, "\t\t\t\t");
        _builder.append(".getResourceId(), my");
        String _name_16 = element.getName();
        _builder.append(_name_16, "\t\t\t\t");
        _builder.append(".get");
        String _name_17 = fbProperty.getName();
        String _firstUpper_2 = StringExtensions.toFirstUpper(_name_17);
        _builder.append(_firstUpper_2, "\t\t\t\t");
        _builder.append("());");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.newLine();
      }
    }
    _builder.append("\t\t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("logger.info(\"No ");
    String _name_18 = element.getName();
    _builder.append(_name_18, "\t\t\t");
    _builder.append(" information available.\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
