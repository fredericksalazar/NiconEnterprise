/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.obj;

public class TipoActividad
{
  private int codigoActividad;
  private String nombreActividad;
  private String descripcion;

  public TipoActividad(int codigoActividad, String nombreActividad, String descripcion)
  {
    this.codigoActividad = codigoActividad;
    this.nombreActividad = nombreActividad;
    this.descripcion = descripcion;
  }

  public TipoActividad(String nombreActividad, String descripcion) {
    this.nombreActividad = nombreActividad;
    this.descripcion = descripcion;
  }

  public int getCodigoActividad() {
    return this.codigoActividad;
  }

  public void setCodigoActividad(int codigoActividad) {
    this.codigoActividad = codigoActividad;
  }

  public String getDescripcion() {
    return this.descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getNombreActividad() {
    return this.nombreActividad;
  }

  public void setNombreActividad(String nombreActividad) {
    this.nombreActividad = nombreActividad;
  }

  public String toString()
  {
    return "TipoActividad{codigoActividad=" + this.codigoActividad + ", nombreActividad=" + this.nombreActividad + ", descripcion=" + this.descripcion + '}';
  }
}