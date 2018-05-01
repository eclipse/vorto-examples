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
package org.eclipse.vorto.codegen.webui.templates.resources.ui.components;

import java.util.Optional;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.webui.templates.resources.ui.IFunctionBlockUITemplate;
import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class GaugeUITemplate implements IFunctionBlockUITemplate {
  private Optional<String> symbol;
  
  private Optional<String> minValue;
  
  private Optional<String> maxValue;
  
  private String value;
  
  public GaugeUITemplate(final String symbol, final String minValue, final String maxValue, final String value) {
    Optional<String> _ofNullable = Optional.<String>ofNullable(symbol);
    this.symbol = _ofNullable;
    Optional<String> _ofNullable_1 = Optional.<String>ofNullable(minValue);
    this.minValue = _ofNullable_1;
    Optional<String> _ofNullable_2 = Optional.<String>ofNullable(maxValue);
    this.maxValue = _ofNullable_2;
    this.value = value;
  }
  
  @Override
  public String renderHtml(final FunctionblockProperty fbProperty, final InvocationContext ctx) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<div justgage");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("titleFontColor=black");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("decimals=\"2\"");
    _builder.newLine();
    {
      boolean _and = false;
      boolean _isPresent = this.symbol.isPresent();
      if (!_isPresent) {
        _and = false;
      } else {
        boolean _equals = this.symbol.equals("");
        boolean _not = (!_equals);
        _and = _not;
      }
      if (_and) {
        _builder.append("\t");
        _builder.append("symbol={{thing.");
        String _name = fbProperty.getName();
        String _lowerCase = _name.toLowerCase();
        _builder.append(_lowerCase, "\t");
        _builder.append(".");
        String _get = this.symbol.get();
        _builder.append(_get, "\t");
        _builder.append("}}");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      boolean _and_1 = false;
      boolean _isPresent_1 = this.minValue.isPresent();
      if (!_isPresent_1) {
        _and_1 = false;
      } else {
        boolean _equals_1 = this.minValue.equals("");
        boolean _not_1 = (!_equals_1);
        _and_1 = _not_1;
      }
      if (_and_1) {
        _builder.append("\t");
        _builder.append("min={{thing.");
        String _name_1 = fbProperty.getName();
        String _lowerCase_1 = _name_1.toLowerCase();
        _builder.append(_lowerCase_1, "\t");
        _builder.append(".");
        String _get_1 = this.minValue.get();
        _builder.append(_get_1, "\t");
        _builder.append("}}");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      boolean _and_2 = false;
      boolean _isPresent_2 = this.maxValue.isPresent();
      if (!_isPresent_2) {
        _and_2 = false;
      } else {
        boolean _equals_2 = this.maxValue.equals("");
        boolean _not_2 = (!_equals_2);
        _and_2 = _not_2;
      }
      if (_and_2) {
        _builder.append("\t");
        _builder.append("max={{thing.");
        String _name_2 = fbProperty.getName();
        String _lowerCase_2 = _name_2.toLowerCase();
        _builder.append(_lowerCase_2, "\t");
        _builder.append(".");
        String _get_2 = this.maxValue.get();
        _builder.append(_get_2, "\t");
        _builder.append("}}");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("value={{thing.");
    String _name_3 = fbProperty.getName();
    String _lowerCase_3 = _name_3.toLowerCase();
    _builder.append(_lowerCase_3, "\t");
    _builder.append(".");
    _builder.append(this.value, "\t");
    _builder.append("}}>");
    _builder.newLineIfNotEmpty();
    _builder.append("</div>");
    _builder.newLine();
    return _builder.toString();
  }
  
  @Override
  public String renderJavascript(final FunctionblockProperty fbProperty, final InvocationContext ctx) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$scope.set");
    String _name = fbProperty.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append(" = function() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    return _builder.toString();
  }
}
