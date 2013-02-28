/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.obj;

public class Actividad
{
  private int idActividad;
  private String tituloActividad;
  private String descripcionActividad;
  private int tipoActividad;
  private String idCliente;
  private String fechaAsignacion;
  private boolean estadoActividad;
  private String fechaRegistro;
  private String nombreCliente;
  private String apellidosCliente;
  private String direccionCliente;
  private String nombreActividad;

  public Actividad(int idActividad, String tituloActividad, String descripcionActividad, int tipoActividad, String idCliente, String fechaAsignacion, boolean estadoActividad, String fechaRegistro)
  {
    this.idActividad = idActividad;
    this.tituloActividad = tituloActividad;
    this.descripcionActividad = descripcionActividad;
    this.tipoActividad = tipoActividad;
    this.idCliente = idCliente;
    this.fechaAsignacion = fechaAsignacion;
    this.estadoActividad = estadoActividad;
    this.fechaRegistro = fechaRegistro;
  }

  public Actividad(String tituloActividad, String descripcionActividad, int tipoActividad, String idCliente, String fechaAsignacion, boolean estadoActividad, String fechaRegistro) {
    this.tituloActividad = tituloActividad;
    this.descripcionActividad = descripcionActividad;
    this.tipoActividad = tipoActividad;
    this.idCliente = idCliente;
    this.fechaAsignacion = fechaAsignacion;
    this.estadoActividad = estadoActividad;
    this.fechaRegistro = fechaRegistro;
  }

  public Actividad(int idActividad, String tituloActividad, String descripcionActividad, String fechaAsignacion, boolean estadoActividad, String fechaRegistro, String idCliente, String nombreCliente, String apellidosCliente, String direccionCliente, String nombreActividad)
  {
    this.idActividad = idActividad;
    this.tituloActividad = tituloActividad;
    this.descripcionActividad = descripcionActividad;
    this.fechaAsignacion = fechaAsignacion;
    this.estadoActividad = estadoActividad;
    this.fechaRegistro = fechaRegistro;
    this.idCliente = idCliente;
    this.nombreCliente = nombreCliente;
    this.apellidosCliente = apellidosCliente;
    this.direccionCliente = direccionCliente;
    this.nombreActividad = nombreActividad;
  }

  public String getNombreActividad() {
    return this.nombreActividad;
  }

  public void setNombreActividad(String nombreActividad) {
    this.nombreActividad = nombreActividad;
  }

  public String getNombreCliente() {
    return this.nombreCliente;
  }

  public void setNombreCliente(String nombreCliente) {
    this.nombreCliente = nombreCliente;
  }

  public String getDescripcionActividad() {
    return this.descripcionActividad;
  }

  public void setDescripcionActividad(String descripcionActividad) {
    this.descripcionActividad = descripcionActividad;
  }

  public boolean getEstadoActividad() {
    return this.estadoActividad;
  }

  public void setEstadoActividad(boolean estadoActividad) {
    this.estadoActividad = estadoActividad;
  }

  public String getFechaAsignacion() {
    return this.fechaAsignacion;
  }

  public void setFechaAsignacion(String fechaAsignacion) {
    this.fechaAsignacion = fechaAsignacion;
  }

  public String getFechaRegistro() {
    return this.fechaRegistro;
  }

  public void setFechaRegistro(String fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  public int getIdActividad() {
    return this.idActividad;
  }

  public void setIdActividad(int idActividad) {
    this.idActividad = idActividad;
  }

  public String getIdCliente() {
    return this.idCliente;
  }

  public void setIdCliente(String idCliente) {
    this.idCliente = idCliente;
  }

  public int getTipoActividad() {
    return this.tipoActividad;
  }

  public void setTipoActividad(int tipoActividad) {
    this.tipoActividad = tipoActividad;
  }

  public String getTituloActividad() {
    return this.tituloActividad;
  }

  public void setTituloActividad(String tituloActividad) {
    this.tituloActividad = tituloActividad;
  }

  public String getApellidosCliente() {
    return this.apellidosCliente;
  }

  public void setApellidosCliente(String apellidosCliente) {
    this.apellidosCliente = apellidosCliente;
  }

  public String getDireccionCliente() {
    return this.direccionCliente;
  }

  public void setDireccionCliente(String direccionCliente) {
    this.direccionCliente = direccionCliente;
  }
}