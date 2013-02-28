/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.api.obj;

public class FamiliaProducto
{
  private String Code;
  private String Name;
  private String Description;
  private Almacen store;
  private String codeStore;

  public FamiliaProducto()
  {
  }

  public FamiliaProducto(String Code, String Name, String Description, Almacen store)
  {
    this.Code = Code;
    this.Name = Name;
    this.Description = Description;
    this.store = store;
  }

  public FamiliaProducto(String Code, String Name, String Description, String codeStore) {
    this.Code = Code;
    this.Name = Name;
    this.Description = Description;
    this.codeStore = codeStore;
  }

  public String getCode() {
    return this.Code;
  }

  public void setCode(String Code) {
    this.Code = Code;
  }

  public String getDescription() {
    return this.Description;
  }

  public void setDescription(String Description) {
    this.Description = Description;
  }

  public String getName() {
    return this.Name;
  }

  public void setName(String Name) {
    this.Name = Name;
  }

  public String getCodeStore() {
    return this.codeStore;
  }

  public void setCodeStore(String codeStore) {
    this.codeStore = codeStore;
  }

  public String toString()
  {
    return "Product_Family{Code=" + this.Code + ", Name=" + this.Name + ", Description=" + this.Description + ", codeStore=" + this.codeStore + '}';
  }
}
