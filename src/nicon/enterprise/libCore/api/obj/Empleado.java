/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.obj;

import java.util.Date;

public class Empleado
{
  private String Identificacion;
  private String Nombres;
  private String Apellidos;
  private String FechaNacimiento;
  private String LugarNacimiento;
  private String EstadoCivil;
  private String Direccion;
  private String Barrio;
  private String Ciudad;
  private String TelefonoFijo;
  private String TelefonoMovil;
  private String Email;
  private boolean Estado;
  private Date FechaRegistro;
  private Almacen codigo;

  public Empleado(String Identificacion, String Nombres, String Apellidos, String FechaNacimiento, String LugarNacimiento, String EstadoCivil, String Direccion, String Barrio, String Ciudad, String TelefonoFijo, String TelefonoMovil, String Email, boolean Estado)
  {
    this.Identificacion = Identificacion;
    this.Nombres = Nombres;
    this.Apellidos = Apellidos;
    this.FechaNacimiento = FechaNacimiento;
    this.LugarNacimiento = LugarNacimiento;
    this.EstadoCivil = EstadoCivil;
    this.Direccion = Direccion;
    this.Barrio = Barrio;
    this.Ciudad = Ciudad;
    this.TelefonoFijo = TelefonoFijo;
    this.TelefonoMovil = TelefonoMovil;
    this.Email = Email;
    this.Estado = Estado;
  }

  public Empleado(String Identificacion, String Nombres, String Apellidos, String FechaNacimiento, String LugarNacimiento, String EstadoCivil, String Direccion, String Barrio, String Ciudad, String TelefonoFijo, String TelefonoMovil, String Email, boolean Estado, Date FechaRegistro, String codigo)
  {
    this.Identificacion = Identificacion;
    this.Nombres = Nombres;
    this.Apellidos = Apellidos;
    this.FechaNacimiento = FechaNacimiento;
    this.LugarNacimiento = LugarNacimiento;
    this.EstadoCivil = EstadoCivil;
    this.Direccion = Direccion;
    this.Barrio = Barrio;
    this.Ciudad = Ciudad;
    this.TelefonoFijo = TelefonoFijo;
    this.TelefonoMovil = TelefonoMovil;
    this.Email = Email;
    this.Estado = Estado;
    this.FechaRegistro = FechaRegistro;
  }

  public String getApellidos() {
    return this.Apellidos;
  }

  public void setApellidos(String Apellidos) {
    this.Apellidos = Apellidos;
  }

  public String getBarrio() {
    return this.Barrio;
  }

  public void setBarrio(String Barrio) {
    this.Barrio = Barrio;
  }

  public String getCiudad() {
    return this.Ciudad;
  }

  public void setCiudad(String Ciudad) {
    this.Ciudad = Ciudad;
  }

  public String getDireccion() {
    return this.Direccion;
  }

  public void setDireccion(String Direccion) {
    this.Direccion = Direccion;
  }

  public String getEmail() {
    return this.Email;
  }

  public void setEmail(String Email) {
    this.Email = Email;
  }

  public boolean getEstado() {
    return this.Estado;
  }

  public void setEstado(boolean Estado) {
    this.Estado = Estado;
  }

  public String getEstadoCivil() {
    return this.EstadoCivil;
  }

  public void setEstadoCivil(String EstadoCivil) {
    this.EstadoCivil = EstadoCivil;
  }

  public String getFechaNacimiento() {
    return this.FechaNacimiento;
  }

  public void setFechaNacimiento(String FechaNacimiento) {
    this.FechaNacimiento = FechaNacimiento;
  }

  public Date getFechaRegistro() {
    return this.FechaRegistro;
  }

  public void setFechaRegistro(Date FechaRegistro) {
    this.FechaRegistro = FechaRegistro;
  }

  public String getIdentificacion() {
    return this.Identificacion;
  }

  public void setIdentificacion(String Identificacion) {
    this.Identificacion = Identificacion;
  }

  public String getLugarNacimiento() {
    return this.LugarNacimiento;
  }

  public void setLugarNacimiento(String LugarNacimiento) {
    this.LugarNacimiento = LugarNacimiento;
  }

  public String getNombres() {
    return this.Nombres;
  }

  public void setNombres(String Nombres) {
    this.Nombres = Nombres;
  }

  public String getTelefonoFijo() {
    return this.TelefonoFijo;
  }

  public void setTelefonoFijo(String TelefonoFijo) {
    this.TelefonoFijo = TelefonoFijo;
  }

  public String getTelefonoMovil() {
    return this.TelefonoMovil;
  }

  public void setTelefonoMovil(String TelefonoMovil) {
    this.TelefonoMovil = TelefonoMovil;
  }

  public Almacen getCodigo() {
    return this.codigo;
  }

  public void setCodigo(Almacen codigo) {
    this.codigo = codigo;
  }

  public String toString()
  {
    return "Empleado{Identificacion=" + this.Identificacion + ", Nombres=" + this.Nombres + ", Apellidos=" + this.Apellidos + ", FechaNacimiento=" + this.FechaNacimiento + ", LugarNacimiento=" + this.LugarNacimiento + ", EstadoCivil=" + this.EstadoCivil + ", Direccion=" + this.Direccion + ", Barrio=" + this.Barrio + ", Ciudad=" + this.Ciudad + ", TelefonoFijo=" + this.TelefonoFijo + ", TelefonoMovil=" + this.TelefonoMovil + ", Email=" + this.Email + ", Estado=" + this.Estado + ", FechaRegistro=" + this.FechaRegistro + ", codigo=" + this.codigo + '}';
  }
}