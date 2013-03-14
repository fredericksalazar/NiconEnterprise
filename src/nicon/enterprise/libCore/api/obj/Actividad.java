/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.libCore.api.obj;

import java.util.logging.Logger;

/**
 * La classe actividad esta definida como un objeto de ejecucion de tarea, una
 * actividad con un cliente es definida como una acción a ejecutar con un
 * determinado cliente, el Objeto Actividad representa esa accion y todos
 * los objeto de tipo Actividad son usados como modelos de datos por el API
 * ActividadDAO que es a ciencia cierta quien le da el uso necesario, todas las
 * actividades deben tener un idActividad único y un número de identificacion
 * de un cliente al que será asignada la actividad.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class Actividad{
    
  private int idActividad;
  private int tipoActividad;
  
  private String tituloActividad;
  private String descripcionActividad;  
  private String idCliente;
  private String fechaAsignacion;  
  private String fechaRegistro;
  private String nombreCliente;
  private String apellidosCliente;
  private String direccionCliente;
  private String nombreActividad;
  
  private boolean estadoActividad;

  /**
   * este constructor crea una instancia de una Actividad de forma básica, 
   * recibe los parametros de ingreso en la vista y se prepará para se almacenada
   * dentro de la fuente de datos de Mysql.
   * 
   * @param idActividad
   * @param tituloActividad
   * @param descripcionActividad
   * @param tipoActividad
   * @param idCliente
   * @param fechaAsignacion
   * @param estadoActividad
   * @param fechaRegistro 
   */
  public Actividad(int idActividad, String tituloActividad, String descripcionActividad, int tipoActividad, String idCliente, String fechaAsignacion, boolean estadoActividad, String fechaRegistro){
    this.idActividad = idActividad;
    this.tituloActividad = tituloActividad;
    this.descripcionActividad = descripcionActividad;
    this.tipoActividad = tipoActividad;
    this.idCliente = idCliente;
    this.fechaAsignacion = fechaAsignacion;
    this.estadoActividad = estadoActividad;
    this.fechaRegistro = fechaRegistro;
  }

  /**
   * Este contructor de Objetos Actividad crea una nueva actividad pero esta
   * vez no necesita recibir el id de actividad, es usado para el rgitro
   * de actividades dentro de la fuente de datos.
   * 
   * @param tituloActividad
   * @param descripcionActividad
   * @param tipoActividad
   * @param idCliente
   * @param fechaAsignacion
   * @param estadoActividad
   * @param fechaRegistro 
   */
  public Actividad(String tituloActividad, String descripcionActividad, int tipoActividad, String idCliente, String fechaAsignacion, boolean estadoActividad, String fechaRegistro) {
    this.tituloActividad = tituloActividad;
    this.descripcionActividad = descripcionActividad;
    this.tipoActividad = tipoActividad;
    this.idCliente = idCliente;
    this.fechaAsignacion = fechaAsignacion;
    this.estadoActividad = estadoActividad;
    this.fechaRegistro = fechaRegistro;
  }

  /**
   * este contructor de la clase actividad es la mas completa de todas, permite
   * crear una nueva actividad con informacion adicional que le permitirá al
   * usuario final ver información detallada de la actividad que se ha 
   * recuperado de la fuente de datos.
   * 
   * @param idActividad
   * @param tituloActividad
   * @param descripcionActividad
   * @param fechaAsignacion
   * @param estadoActividad
   * @param fechaRegistro
   * @param idCliente
   * @param nombreCliente
   * @param apellidosCliente
   * @param direccionCliente
   * @param nombreActividad 
   */
  public Actividad(int idActividad, String tituloActividad, String descripcionActividad, String fechaAsignacion, boolean estadoActividad, String fechaRegistro, String idCliente, String nombreCliente, String apellidosCliente, String direccionCliente, String nombreActividad){
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

  /**
   * retorna el nombre de la actividad.
   * 
   * @return String nombre Actividad
   */
  public String getNombreActividad() {
    return this.nombreActividad;
  }

  /**
   * retorna el nombre del cliente al que han asignado la actividad
   * 
   * @return String nombreCliente
   */
  public String getNombreCliente() {
    return this.nombreCliente;
  }


  /**
   * retorna la descripcion de la actividad
   * 
   * @return String descripcionActividad
   */
  public String getDescripcionActividad() {
    return this.descripcionActividad;
  }

  /**
   * ajusta la descripcion de la actividad
   * @param descripcionActividad 
   */
  public void setDescripcionActividad(String descripcionActividad) {
    this.descripcionActividad = descripcionActividad;
  }

  /**
   * retorna el estado boolean de la actividad
   * @return boolean estadoActividad
   */
  public boolean getEstadoActividad() {
    return this.estadoActividad;
  }

  /**
   * ajusta el estado boolean de la actividad
   * @param estadoActividad 
   */
  public void setEstadoActividad(boolean estadoActividad) {
    this.estadoActividad = estadoActividad;
  }

  /**
   * retorna la fecha de asignacion de la actividad
   * @return String fechaAsignacion
   */
  public String getFechaAsignacion() {
    return this.fechaAsignacion;
  }

  /**
   * ajusta la fecha de asignacion de la actividad
   * @param fechaAsignacion 
   */
  public void setFechaAsignacion(String fechaAsignacion) {
    this.fechaAsignacion = fechaAsignacion;
  }

  /**
   * retorna la fecha de registro de la actividad
   * @return String fechaRegistro
   */
  public String getFechaRegistro() {
    return this.fechaRegistro;
  }

  /**
   * ajusta la fecha de registro de la actividad
   * @param fechaRegistro 
   */
  public void setFechaRegistro(String fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  /**
   * retorna el Id de la actividad
   * @return int idActividad
   */
  public int getIdActividad() {
    return this.idActividad;
  }

  /**
   * ajusta el id de la actividad
   * @param idActividad 
   */
  public void setIdActividad(int idActividad) {
    this.idActividad = idActividad;
  }

  /**
   * retorna el id del cliente
   * @return String idCliente
   */
  public String getIdCliente() {
    return this.idCliente;
  }

  /**
   * ajusta el id del cliente
   * @param idCliente 
   */
  public void setIdCliente(String idCliente) {
    this.idCliente = idCliente;
  }

  /**
   * retorna el codigo del tipo de actividad ue se debe desarrollar
   * @return int tipoActividad
   */
  public int getTipoActividad() {
    return this.tipoActividad;
  }

  /**
   * ajusta el id del tipo de actividad  a desarrollar
   * @param tipoActividad 
   */
  public void setTipoActividad(int tipoActividad) {
    this.tipoActividad = tipoActividad;
  }

  /**
   * retorna el titulo principal de la actividad
   * @return String tituloActividad
   */
  public String getTituloActividad() {
    return this.tituloActividad;
  }

  /**
   * ajusta el titulo de al actividad
   * @param tituloActividad 
   */
  public void setTituloActividad(String tituloActividad) {
    this.tituloActividad = tituloActividad;
  }

  /**
   * retorna los apellidos del cliente al que se le ha asignado la actividad
   * @return String apellidosCliente
   */
  public String getApellidosCliente() {
    return this.apellidosCliente;
  }

  /**
   * ajusta los apellidos del cliente
   * @param apellidosCliente 
   */
  public void setApellidosCliente(String apellidosCliente) {
    this.apellidosCliente = apellidosCliente;
  }

  /**
   * retora la direccion de residencia del cliente
   * @return String direccionCliente
   */
  public String getDireccionCliente() {
    return this.direccionCliente;
  }

  /**
   * ajusta la direccion de residencia del cliente
   * @param direccionCliente 
   */
  public void setDireccionCliente(String direccionCliente) {
    this.direccionCliente = direccionCliente;
  }

    @Override
    public String toString() {
        return "Actividad{" + "idActividad=" + idActividad + ", tipoActividad=" + tipoActividad + ", tituloActividad=" + tituloActividad + ", descripcionActividad=" + descripcionActividad + ", idCliente=" + idCliente + ", fechaAsignacion=" + fechaAsignacion + ", fechaRegistro=" + fechaRegistro + ", nombreCliente=" + nombreCliente + ", apellidosCliente=" + apellidosCliente + ", direccionCliente=" + direccionCliente + ", nombreActividad=" + nombreActividad + ", estadoActividad=" + estadoActividad + '}';
    }
    
}