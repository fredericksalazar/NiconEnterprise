/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.obj;

public class ContratoEmpleado
{
  private int idContrato;
  private String fechaContratacion;
  private String cargo;
  private double salario;
  private int tiempoContratado;
  private String tipoContrato;
  private String inicioFunciones;
  private String idEmpleado;
  private String observacion;
  private boolean estadoContrato;

  public ContratoEmpleado(String fechaContratacion, String cargo, double salario, int tiempoContratado, String tipoContrato, String inicioFunciones, String idEmpleado, String Observaciones)
  {
    this.cargo = cargo;
    this.fechaContratacion = fechaContratacion;
    this.salario = salario;
    this.tiempoContratado = tiempoContratado;
    this.tipoContrato = tipoContrato;
    this.inicioFunciones = inicioFunciones;
    this.idEmpleado = idEmpleado;
    this.observacion = Observaciones;
  }

  public ContratoEmpleado(int idContrato, String fechaContratacion, String cargo, double salario, int tiempoContratado, String tipoContrato, String inicioFunciones, boolean estadoContrato, String idEmpleado, String Observaciones)
  {
    this.idContrato = idContrato;
    this.fechaContratacion = fechaContratacion;
    this.cargo = cargo;
    this.salario = salario;
    this.tiempoContratado = tiempoContratado;
    this.tipoContrato = tipoContrato;
    this.inicioFunciones = inicioFunciones;
    this.estadoContrato = estadoContrato;
    this.idEmpleado = idEmpleado;
    this.observacion = Observaciones;
  }

  public String getObservacion()
  {
    return this.observacion;
  }

  public String getFechaContratacion()
  {
    return this.fechaContratacion;
  }

  public String getCargo()
  {
    return this.cargo;
  }

  public void setCargo(String cargo)
  {
    this.cargo = cargo;
  }

  public int getIdContrato()
  {
    return this.idContrato;
  }

  public String getIdEmpleado()
  {
    return this.idEmpleado;
  }

  public String getInicioFunciones()
  {
    return this.inicioFunciones;
  }

  public double getSalario()
  {
    return this.salario;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }

  public int getTiempoContratado()
  {
    return this.tiempoContratado;
  }

  public void setTiempoContratado(int tiempoContratado) {
    this.tiempoContratado = tiempoContratado;
  }

  public String getTipoContrato()
  {
    return this.tipoContrato;
  }

  public void setTipoContrato(String tipoContrato) {
    this.tipoContrato = tipoContrato;
  }

  public boolean getEstadoContrato()
  {
    return this.estadoContrato;
  }

  public void setEstadoContrato(boolean estadoContrato)
  {
    this.estadoContrato = estadoContrato;
  }

  public String toString()
  {
    return "ContratoEmpleado{idContrato=" + this.idContrato + ", cargo=" + this.cargo + ", salario=" + this.salario + ", tiempoContratado=" + this.tiempoContratado + ", tipoContrato=" + this.tipoContrato + ", inicioFunciones=" + this.inicioFunciones + ", idEmpleado=" + this.idEmpleado + '}';
  }
}