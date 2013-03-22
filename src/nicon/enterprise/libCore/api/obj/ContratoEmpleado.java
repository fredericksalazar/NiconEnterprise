/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */
package nicon.enterprise.libCore.api.obj;

/**
 * Esta clase define el objeto o modelo de datos que representará y definirá un
 * modelo de contrato que servirá para tener un control de empleados de la
 * empresa, cada Objeto de tipo contrato debe estar asignado a un empleado a
 * traves de su Id (numero de cedula) a traves del cual se le ingresará al
 * sistema de nomina, y por medio del cual se puede saber todos los terminos
 * establecidos en la contratacion del mismo por parte del departamento de
 * recursos humanos.
 *
 * @author Frederick Adolfo Salazar Sanchez
 */
public class ContratoEmpleado {

    private int codigoContrato;
    private String fechaContratacion;
    private int tiempoContratado;
    private String fechaFinContrato;
    private String tipoJornadaLaboral;
    private String fechaInicioFunciones;
    private String cargo;
    private double salario;
    private String funcionesLaborales;
    private String observaciones;
    private boolean estadoContrato;
    private String idEmpleado;

    public ContratoEmpleado(int codigoContrato, String fechaContratacion, int tiempoContratado, String fechaFinContrato, String tipoJornadaLaboral, String fechaInicioFunciones, String cargo, double salario, String funcionesLaborales, String observaciones, boolean estadoContrato, String idEmpleado) {
        this.codigoContrato = codigoContrato;
        this.fechaContratacion = fechaContratacion;
        this.tiempoContratado = tiempoContratado;
        this.fechaFinContrato = fechaFinContrato;
        this.tipoJornadaLaboral = tipoJornadaLaboral;
        this.fechaInicioFunciones = fechaInicioFunciones;
        this.cargo = cargo;
        this.salario = salario;
        this.funcionesLaborales = funcionesLaborales;
        this.observaciones = observaciones;
        this.estadoContrato = estadoContrato;
        this.idEmpleado = idEmpleado;
    }

    public ContratoEmpleado(String fechaContratacion, int tiempoContratado, String fechaFinContrato, String tipoJornadaLaboral, String fechaInicioFunciones, String cargo, double salario, String funcionesLaborales, String observaciones, boolean estadoContrato, String idEmpleado) {
        this.fechaContratacion = fechaContratacion;
        this.tiempoContratado = tiempoContratado;
        this.fechaFinContrato = fechaFinContrato;
        this.tipoJornadaLaboral = tipoJornadaLaboral;
        this.fechaInicioFunciones = fechaInicioFunciones;
        this.cargo = cargo;
        this.salario = salario;
        this.funcionesLaborales = funcionesLaborales;
        this.observaciones = observaciones;
        this.estadoContrato = estadoContrato;
        this.idEmpleado = idEmpleado;
    }

    public int getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(int codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public int getTiempoContratado() {
        return tiempoContratado;
    }

    public void setTiempoContratado(int tiempoContratado) {
        this.tiempoContratado = tiempoContratado;
    }

    public String getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(String fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    public String getTipoJornadaLaboral() {
        return tipoJornadaLaboral;
    }

    public void setTipoJornadaLaboral(String tipoJornadaLaboral) {
        this.tipoJornadaLaboral = tipoJornadaLaboral;
    }

    public String getFechaInicioFunciones() {
        return fechaInicioFunciones;
    }

    public void setFechaInicioFunciones(String fechaInicioFunciones) {
        this.fechaInicioFunciones = fechaInicioFunciones;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFuncionesLaborales() {
        return funcionesLaborales;
    }

    public void setFuncionesLaborales(String funcionesLaborales) {
        this.funcionesLaborales = funcionesLaborales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean getEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(boolean estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "ContratoEmpleado{" + "codigoContrato=" + codigoContrato + ", fechaContratacion=" + fechaContratacion + ", tiempoContratado=" + tiempoContratado + ", fechaFinContrato=" + fechaFinContrato + ", tipoJornadaLaboral=" + tipoJornadaLaboral + ", fechaInicioFunciones=" + fechaInicioFunciones + ", cargo=" + cargo + ", salario=" + salario + ", funcionesLaborales=" + funcionesLaborales + ", observaciones=" + observaciones + ", estadoContrato=" + estadoContrato + ", idEmpleado=" + idEmpleado + '}';
    }
}