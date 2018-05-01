package org.eclipse.vorto.codegen.protobuf.templates;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.model.Model;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class ProtobufMetaTemplate implements IFileTemplate<Model> {
  @Override
  public String getFileName(final Model context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("meta.proto");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final Model context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("org/eclipse/vorto");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final Model element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("syntax = \"proto3\";");
    _builder.newLine();
    _builder.newLine();
    _builder.append("package org.eclipse.vorto;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("message Meta {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("enum Type {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("UNDEFINED = 0;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("DATATYPE = 1;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("FUNCTION_BLOCK = 2;");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("INFORMATION_MODEL = 3;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("string namespace = 1;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("string name = 2;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("string version = 3;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("Type type = 4;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("string display_name = 5;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("string description = 6;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("string category = 7;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
