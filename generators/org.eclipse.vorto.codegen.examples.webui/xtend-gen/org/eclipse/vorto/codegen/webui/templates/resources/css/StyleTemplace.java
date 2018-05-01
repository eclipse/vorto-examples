package org.eclipse.vorto.codegen.webui.templates.resources.css;

import org.eclipse.vorto.codegen.api.IFileTemplate;
import org.eclipse.vorto.codegen.api.InvocationContext;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModel;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class StyleTemplace implements IFileTemplate<InformationModel> {
  @Override
  public String getFileName(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("style.css");
    return _builder.toString();
  }
  
  @Override
  public String getPath(final InformationModel context) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = context.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("-solution/src/main/resources/static/css");
    return _builder.toString();
  }
  
  @Override
  public String getContent(final InformationModel element, final InvocationContext context) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(".tiny-box {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("box-shadow:1px 1px 1px 2px rgba(0,0,0,.1);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("padding:0 5px;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("color:#333;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("margin:5px auto;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("min-height:114px;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("background:#fff;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("height:130px;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("width:154px;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("text-align:center");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append(".tiny-box-icon {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("height:80px;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("width:90px;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("text-align:center;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("font-size:45px;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("line-height:70px;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("background-color:transparent!important");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append(".tiny-box.selected,.tiny-box:hover {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("border:4px solid #c2e1f5;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("cursor:pointer");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append(".tiny-box-name,.tiny-box-number {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("margin:0 5px;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("height:30px");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append(".tiny-box-name span,.tiny-box-number span {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("display:inline-block;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("vertical-align:middle;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("line-height:.9em");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append(".tiny-box-name span {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("font-weight:600");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append(".tiny-box-number span {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("color:#005691");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("p.breakeWordWithDots {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("overflow: hidden;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("display: inline-block;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("text-overflow: ellipsis;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("white-space: nowrap;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("direction: rtl;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
