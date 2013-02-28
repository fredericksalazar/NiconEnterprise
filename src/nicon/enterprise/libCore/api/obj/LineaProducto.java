/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.obj;


public class LineaProducto
{
  private String codeLine;
  private String nameLine;
  private String DescriptionLine;
  private String CodeFamily;

  public LineaProducto(String codeLine, String nameLine, String DescriptionLine, String CodeFamily)
  {
    this.codeLine = codeLine;
    this.nameLine = nameLine;
    this.DescriptionLine = DescriptionLine;
    this.CodeFamily = CodeFamily;
  }

  public String getCodeFamily() {
    return this.CodeFamily;
  }

  public void setCodeFamily(String CodeFamily) {
    this.CodeFamily = CodeFamily;
  }

  public String getDescriptionLine() {
    return this.DescriptionLine;
  }

  public void setDescriptionLine(String DescriptionLine) {
    this.DescriptionLine = DescriptionLine;
  }

  public String getCodeLine() {
    return this.codeLine;
  }

  public void setCodeLine(String codeLine) {
    this.codeLine = codeLine;
  }

  public String getNameLine() {
    return this.nameLine;
  }

  public void setNameLine(String nameLine) {
    this.nameLine = nameLine;
  }

  public String toString()
  {
    return "Product_Line{codeLine=" + this.codeLine + ", nameLine=" + this.nameLine + ", DescriptionLine=" + this.DescriptionLine + ", CodeFamily=" + this.CodeFamily + '}';
  }
}