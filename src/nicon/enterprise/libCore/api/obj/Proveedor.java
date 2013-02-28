/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.obj;


public class Proveedor
{
  private String nit;
  private String razonSocial;
  private String direccion;
  private String ciudad;
  private String telefonoFijo;
  private String telefonoMovil;
  private String fax;
  private String email;
  private String webPage;
  private String banco;
  private String numeroCuenta;
  private String Descripcion;

  public Proveedor(String nit, String razonSocial, String direccion, String ciudad, String telefonoFijo, String telefonoMovil, String fax, String email, String webPage, String banco, String numeroCuenta, String descripcion)
  {
    this.nit = nit;
    this.razonSocial = razonSocial;
    this.direccion = direccion;
    this.ciudad = ciudad;
    this.telefonoFijo = telefonoFijo;
    this.telefonoMovil = telefonoMovil;
    this.fax = fax;
    this.email = email;
    this.webPage = webPage;
    this.banco = banco;
    this.numeroCuenta = numeroCuenta;
    this.Descripcion = descripcion;
  }

  public String getCiudad() {
    return this.ciudad;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public String getDireccion() {
    return this.direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFax() {
    return this.fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getNit() {
    return this.nit;
  }

  public void setNit(String nit) {
    this.nit = nit;
  }

  public void setBanco(String banco) {
    this.banco = banco;
  }

  public void setNumeroCuenta(String numeroCuenta) {
    this.numeroCuenta = numeroCuenta;
  }

  public String getRazonSocial() {
    return this.razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public String getTelefonoFijo() {
    return this.telefonoFijo;
  }

  public void setTelefonoFijo(String telefonoFijo) {
    this.telefonoFijo = telefonoFijo;
  }

  public String getTelefonoMovil() {
    return this.telefonoMovil;
  }

  public void setTelefonoMovil(String telefonoMovil) {
    this.telefonoMovil = telefonoMovil;
  }

  public String getWebPage() {
    return this.webPage;
  }

  public void setWebPage(String webPage) {
    this.webPage = webPage;
  }

  public String getBanco() {
    return this.banco;
  }

  public String getNumeroCuenta() {
    return this.numeroCuenta;
  }

  public String getDescripcion() {
    return this.Descripcion;
  }

  public void setDescripcion(String Descripcion) {
    this.Descripcion = Descripcion;
  }

  public String toString()
  {
    return "Proveedor{nit=" + this.nit + ", razonSocial=" + this.razonSocial + ", direccion=" + this.direccion + ", ciudad=" + this.ciudad + ", telefonoFijo=" + this.telefonoFijo + ", telefonoMovil=" + this.telefonoMovil + ", fax=" + this.fax + ", email=" + this.email + ", webPage=" + this.webPage + ", banco=" + this.banco + ", numeroCuenta=" + this.numeroCuenta + ", Descripcion=" + this.Descripcion + '}';
  }
}