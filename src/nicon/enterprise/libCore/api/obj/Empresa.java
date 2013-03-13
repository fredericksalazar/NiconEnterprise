/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.api.obj;

public class Empresa
{
  private String Nit;
  private String Razon_Social;
  private String Slogan;
  private String Representante_legal;
  private String telefono_fijo;
  private String telefono_movil;
  private String PBX;
  private String email;
  private String web_page;
  private String Face_Page;
  private String departamento;
  private String ciudad;
  private String direccion;

  public Empresa(String Nit, String Razon_Social, String Slogan, String Representante_legal, String direccion, String ciudad, String departamento, String telefono_fijo, String telefono_movil, String PBX, String email, String web_page, String Face_Page)
  {
    this.Nit = Nit;
    this.Razon_Social = Razon_Social;
    this.Slogan = Slogan;
    this.Representante_legal = Representante_legal;
    this.direccion = direccion;
    this.ciudad = ciudad;
    this.departamento = departamento;
    this.telefono_fijo = telefono_fijo;
    this.telefono_movil = telefono_movil;
    this.PBX = PBX;
    this.email = email;
    this.web_page = web_page;
    this.Face_Page = Face_Page;
  }

  public Empresa()
  {
  }

  public void setFace_Page(String Face_Page) {
    this.Face_Page = Face_Page;
  }

  public void setNit(String Nit) {
    this.Nit = Nit;
  }

  public void setPBX(String PBX) {
    this.PBX = PBX;
  }

  public void setRazon_Social(String Razon_Social) {
    this.Razon_Social = Razon_Social;
  }

  public void setSlogan(String Slogan) {
    this.Slogan = Slogan;
  }

  public void setRepresentante_legal(String Representante_legal) {
    this.Representante_legal = Representante_legal;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setDepartamento(String provincia) {
    this.departamento = provincia;
  }

  public void setTelefono_fijo(String telefono_fijo) {
    this.telefono_fijo = telefono_fijo;
  }

  public void setTelefono_movil(String telefono_movil) {
    this.telefono_movil = telefono_movil;
  }

  public void setWeb_page(String web_page) {
    this.web_page = web_page;
  }

  public String getFace_Page() {
    return this.Face_Page;
  }

  public String getNit() {
    return this.Nit;
  }

  public String getPBX() {
    return this.PBX;
  }

  public String getRazon_Social() {
    return this.Razon_Social;
  }

  public String getSlogan() {
    return this.Slogan;
  }
  public String getRepresentante_legal() {
    return this.Representante_legal;
  }

  public String getCiudad() {
    return this.ciudad;
  }

  public String getDireccion() {
    return this.direccion;
  }

  public String getEmail() {
    return this.email;
  }

  public String getDepartamento() {
    return this.departamento;
  }

  public String getTelefono_fijo() {
    return this.telefono_fijo;
  }

  public String getTelefono_movil() {
    return this.telefono_movil;
  }

  public String getWeb_page() {
    return this.web_page;
  }
}