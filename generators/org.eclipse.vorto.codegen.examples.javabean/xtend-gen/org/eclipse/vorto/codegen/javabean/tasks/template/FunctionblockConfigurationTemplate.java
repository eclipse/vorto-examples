package org.eclipse.vorto.codegen.javabean.tasks.template;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.vorto.codegen.api.ITemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.codegen.templates.java.JavaClassFieldGetterTemplate;
import org.eclipse.vorto.codegen.templates.java.JavaClassFieldSetterTemplate;
import org.eclipse.vorto.codegen.templates.java.JavaClassFieldTemplate;
import org.eclipse.vorto.core.api.model.datatype.Property;
import org.eclipse.vorto.core.api.model.functionblock.Configuration;
import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class FunctionblockConfigurationTemplate implements ITemplate<FunctionblockModel> {
  private JavaClassFieldTemplate propertyTemplate;
  
  private JavaClassFieldSetterTemplate propertySetterTemplate;
  
  private JavaClassFieldGetterTemplate propertyGetterTemplate;
  
  private String[] imports;
  
  private String packageName;
  
  private String implSuffix;
  
  public FunctionblockConfigurationTemplate(final String[] imports, final String implSuffix, final String packageName) {
    this.imports = imports;
    this.packageName = packageName;
    this.implSuffix = implSuffix;
    JavaClassFieldTemplate _javaClassFieldTemplate = new JavaClassFieldTemplate();
    this.propertyTemplate = _javaClassFieldTemplate;
    JavaClassFieldSetterTemplate _javaClassFieldSetterTemplate = new JavaClassFieldSetterTemplate("set");
    this.propertySetterTemplate = _javaClassFieldSetterTemplate;
    JavaClassFieldGetterTemplate _javaClassFieldGetterTemplate = new JavaClassFieldGetterTemplate("get");
    this.propertyGetterTemplate = _javaClassFieldGetterTemplate;
  }
  
  @Override
  public String getContent(final FunctionblockModel context, final InvocationContext invocationContext) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.packageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      for(final String _import : this.imports) {
        _builder.append("import ");
        _builder.append(_import, "");
        _builder.append(".*;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("public class ");
    String _name = context.getName();
    _builder.append(_name, "");
    _builder.append(this.implSuffix, "");
    _builder.append("{");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    {
      FunctionBlock _functionblock = context.getFunctionblock();
      Configuration _configuration = _functionblock.getConfiguration();
      boolean _notEquals = (!Objects.equal(_configuration, null));
      if (_notEquals) {
        {
          FunctionBlock _functionblock_1 = context.getFunctionblock();
          Configuration _configuration_1 = _functionblock_1.getConfiguration();
          EList<Property> _properties = _configuration_1.getProperties();
          for(final Property property : _properties) {
            _builder.append("\t");
            String _content = this.propertyTemplate.getContent(property, invocationContext);
            _builder.append(_content, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.newLine();
        {
          FunctionBlock _functionblock_2 = context.getFunctionblock();
          Configuration _configuration_2 = _functionblock_2.getConfiguration();
          EList<Property> _properties_1 = _configuration_2.getProperties();
          for(final Property property_1 : _properties_1) {
            _builder.append("\t");
            String _content_1 = this.propertySetterTemplate.getContent(property_1, invocationContext);
            _builder.append(_content_1, "\t");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            String _content_2 = this.propertyGetterTemplate.getContent(property_1, invocationContext);
            _builder.append(_content_2, "\t");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
