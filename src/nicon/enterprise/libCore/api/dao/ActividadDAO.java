/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import nicon.enterprise.libCore.Conection;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.NiconAdminReport;
import nicon.enterprise.libCore.NiconLibTools;
import nicon.enterprise.libCore.obj.Actividad;

public class ActividadDAO {

    private Actividad actividad;
    private Conection coneccion;
    private NiconAdminReport adminReport;
    private JasperPrint reporte;
    private int ejecucion;
    private int codigo;
    private boolean state;
    private ArrayList listaActividades;
    private String sentencia;
    private String urlReport = "/Nicon/Enterprise/LibCore/rsc/ImpActividad.jasper";
    private ResultSet datosConsulta;
    private HashMap parametro;

    public ActividadDAO() {
        this.coneccion = Conection.obtenerInstancia();
        this.adminReport = new NiconAdminReport();
        this.parametro = new HashMap();
    }

    public ActividadDAO(Actividad actividad) {
        this.actividad = actividad;
        this.coneccion = Conection.obtenerInstancia();
        this.adminReport = new NiconAdminReport();
        this.parametro = new HashMap();
    }

    public boolean crearActividad() {
        if (this.actividad != null) {
            try {
                this.sentencia = ("insert into Actividades (TituloActividad,DescripcionActividad,TipoActividad_codigo,Clientes_identificacion,FechaAsignacion,EstadoActividad,FechaRegistro) values ('" + this.actividad.getTituloActividad() + "','" + this.actividad.getDescripcionActividad() + "'," + this.actividad.getTipoActividad() + "," + this.actividad.getIdCliente() + ",'" + this.actividad.getFechaAsignacion() + "'," + this.actividad.getEstadoActividad() + ",current_timestamp);");
                this.ejecucion = this.coneccion.ejecutarSentencia(this.sentencia);
                if (this.ejecucion == 0) {
                    this.state = true;
                } else {
                    this.state = false;
                }
            } catch (Exception e) {
                System.err.println("Ocurrioun error en ActividadDAO.crearActividad():\n" + e.getMessage());
                this.state = false;
            }
        }
        return this.state;
    }

    public Actividad buscarActividadPorID(String ID) {
        try {
            this.sentencia = ("select TituloActividad,DescripcionActividad,TipoActividad.Titulo,identificacion,nombres,apellidos,direccion,FechaAsignacion,EstadoActividad,FechaRegistro from Actividades,TipoActividad,Clientes where TipoActividad.codigo=TipoActividad_codigo and Clientes.identificacion=Clientes_identificacion and idActividad=" + ID + "; ");
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            if (this.datosConsulta.next()) {
                this.actividad = new Actividad(this.datosConsulta.getInt(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3), this.datosConsulta.getInt(4), this.datosConsulta.getString(5), NiconLibTools.parseToMysqlStringDate(this.datosConsulta.getDate(6)), this.datosConsulta.getBoolean(7), String.valueOf(this.datosConsulta.getDate(8)));
                this.datosConsulta.close();
            } else {
                this.actividad = null;
            }
        } catch (Exception e) {
            System.err.println("Un error ocurrio en ActividadDAO.buscarActividad(String ID):\n" + e.getMessage());
        }
        return this.actividad;
    }

    public ArrayList buscarActividadesProIDCliente(String idCliente) {
        try {
            this.listaActividades.clear();
            this.sentencia = ("select idActividad,TituloActividad,DescripcionActividad,TipoActividad.Titulo,Clientes.nombres,Clientes.apellidos,Clientes.direccion,FechaAsignacion,EstadoActividad,FechaRegistro from Actividades,Clientes,TipoActividad where Actividades.TipoActividad_codigo=TipoActividad.codigo and Actividades.Clientes_identificacion=Clientes.identificacion and Actividades.Clientes_identificacion=" + idCliente + ";");
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            while (this.datosConsulta.next()) {
                this.actividad = new Actividad(this.datosConsulta.getInt("idActividad"), this.datosConsulta.getString("TituloActividad"), this.datosConsulta.getString("DescripcionActividad"), String.valueOf(this.datosConsulta.getDate("FechaAsignacion")), this.datosConsulta.getBoolean("EstadoActividad"), String.valueOf(this.datosConsulta.getDate("FechaRegistro")), idCliente, this.datosConsulta.getString("nombres"), this.datosConsulta.getString("apellidos"), this.datosConsulta.getString("direccion"), this.datosConsulta.getString("Titulo"));
                this.listaActividades.add(this.actividad);
            }
            this.datosConsulta.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.listaActividades;
    }

    public ArrayList buscarActividadesPendientesPorID(String idCliente) {
        return this.listaActividades;
    }

    public Actividad buscarActividadPorTitulo(String titulo) {
        try {
            this.sentencia = ("select * from Actividades where idActividad='" + titulo + "';");
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            if (this.datosConsulta.next()) {
                this.actividad = new Actividad(this.datosConsulta.getInt(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3), this.datosConsulta.getInt(4), this.datosConsulta.getString(5), NiconLibTools.parseToMysqlStringDate(this.datosConsulta.getDate(6)), this.datosConsulta.getBoolean(7), String.valueOf(this.datosConsulta.getDate(8)));
                this.datosConsulta.close();
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un error en ActividadDAO.buscarActividadPorTitul(String titulo):\n" + e.getMessage());
        }
        return this.actividad;
    }

    public boolean cambiarEstado(int ID, boolean estado) {
        try {
            this.sentencia = ("update Actividades set EstadoActividad=" + estado + " where idActividad=" + ID + ";");
            this.ejecucion = this.coneccion.ejecutarSentencia(this.sentencia);
            if (this.ejecucion == 0) {
                this.state = true;
            } else {
                this.state = false;
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un error en ActividadDAO.cambiarEstad(boolean estado):\n" + e.getMessage());
        }
        return this.state;
    }

    public boolean consultarEstado(int ID) {
        try {
            this.sentencia = ("select EstadoActividad from Actividades where idActividad=" + ID + ";");
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            if (this.datosConsulta.next()) {
                this.state = this.datosConsulta.getBoolean(1);
            }
            this.datosConsulta.close();
        } catch (SQLException ex) {
            Logger.getLogger(ActividadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.state;
    }

    public ArrayList listarPendientes() {
        try {
            this.listaActividades = new ArrayList();
            this.sentencia = "select idActividad,TituloActividad,DescripcionActividad,TipoActividad.Titulo,identificacion,nombres,apellidos,direccion,FechaAsignacion,EstadoActividad,FechaRegistro from Actividades,TipoActividad,Clientes where TipoActividad.codigo=TipoActividad_codigo and Clientes.identificacion=Clientes_identificacion and EstadoActividad=false order By idActividad; ";
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            while (this.datosConsulta.next()) {
                this.actividad = new Actividad(this.datosConsulta.getInt("idActividad"), this.datosConsulta.getString("TituloActividad"), this.datosConsulta.getString("DescripcionActividad"), String.valueOf(this.datosConsulta.getDate("FechaAsignacion")), this.datosConsulta.getBoolean("EstadoActividad"), String.valueOf(this.datosConsulta.getDate("FechaRegistro")), this.datosConsulta.getString("identificacion"), this.datosConsulta.getString("nombres"), this.datosConsulta.getString("apellidos"), this.datosConsulta.getString("direccion"), this.datosConsulta.getString("Titulo"));
                this.listaActividades.add(this.actividad);
            }
            System.out.println("Carga de actividades terminada, total actividades registradas: " + this.listaActividades.size());
            this.datosConsulta.close();
        } catch (Exception e) {
            System.err.println("Ocurrio un error en ActividadDAO.listarPendientes():\n" + e.getMessage());
        }
        return this.listaActividades;
    }

    public ArrayList listarActividadesPendientesHoy() {
        try {
            this.listaActividades = new ArrayList();
            this.sentencia = "select idActividad,TituloActividad,DescripcionActividad,TipoActividad.Titulo,Clientes.identificacion,Clientes.nombres,Clientes.apellidos,Clientes.direccion,FechaAsignacion,EstadoActividad,FechaRegistro from Actividades,TipoActividad,Clientes where TipoActividad.codigo=TipoActividad_codigo and Clientes.identificacion=Clientes_identificacion and EstadoActividad=false and Actividades.FechaAsignacion=current_date order By idActividad; ";
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            while (this.datosConsulta.next()) {
                this.actividad = new Actividad(this.datosConsulta.getInt("idActividad"), this.datosConsulta.getString("TituloActividad"), this.datosConsulta.getString("DescripcionActividad"), String.valueOf(this.datosConsulta.getDate("FechaAsignacion")), this.datosConsulta.getBoolean("EstadoActividad"), String.valueOf(this.datosConsulta.getDate("FechaRegistro")), this.datosConsulta.getString("identificacion"), this.datosConsulta.getString("nombres"), this.datosConsulta.getString("apellidos"), this.datosConsulta.getString("direccion"), this.datosConsulta.getString("Titulo"));
                this.listaActividades.add(this.actividad);
            }
            System.out.println("Carga de actividades terminada, total actividades registradas: " + this.listaActividades.size());
            this.datosConsulta.close();
        } catch (Exception e) {
            System.err.println("Ocurrio un error en ActividadDAO.listarPendientes():\n" + e.getMessage());
        }
        return this.listaActividades;
    }

    public ArrayList listarRealizadas() {
        try {
            this.listaActividades = new ArrayList();
            this.sentencia = "select idActividad,TituloActividad,DescripcionActividad,TipoActividad.Titulo,identificacion,nombres,apellidos,identificacion,direccion,FechaAsignacion,EstadoActividad,FechaRegistro from Actividades,TipoActividad,Clientes where TipoActividad.codigo=TipoActividad_codigo and Clientes.identificacion=Clientes_identificacion and EstadoActividad=true order By idActividad; ";
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            while (this.datosConsulta.next()) {
                this.actividad = new Actividad(this.datosConsulta.getInt("idActividad"), this.datosConsulta.getString("TituloActividad"), this.datosConsulta.getString("DescripcionActividad"), String.valueOf(this.datosConsulta.getDate("FechaAsignacion")), this.datosConsulta.getBoolean("EstadoActividad"), String.valueOf(this.datosConsulta.getDate("FechaRegistro")), this.datosConsulta.getString("identificacion"), this.datosConsulta.getString("nombres"), this.datosConsulta.getString("apellidos"), this.datosConsulta.getString("direccion"), this.datosConsulta.getString("Titulo"));
                this.listaActividades.add(this.actividad);
            }
            System.out.println("Carga de actividades terminada, total actividades registradas: " + this.listaActividades.size());
            this.datosConsulta.close();
        } catch (Exception e) {
            System.err.println("Ocurrio un error en ActividadDAO.listarPendientes():\n" + e.getMessage());
        }
        return this.listaActividades;
    }

    public ArrayList listarTodas() {
        try {
            this.listaActividades = new ArrayList();
            this.sentencia = "select idActividad,TituloActividad,DescripcionActividad,TipoActividad.Titulo,identificacion,nombres,apellidos,direccion,FechaAsignacion,EstadoActividad,FechaRegistro from Actividades,TipoActividad,Clientes where TipoActividad.codigo=TipoActividad_codigo and Clientes.identificacion=Clientes_identificacion order By idActividad; ";
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            while (this.datosConsulta.next()) {
                this.actividad = new Actividad(this.datosConsulta.getInt("idActividad"), this.datosConsulta.getString("TituloActividad"), this.datosConsulta.getString("DescripcionActividad"), String.valueOf(this.datosConsulta.getDate("FechaAsignacion")), this.datosConsulta.getBoolean("EstadoActividad"), String.valueOf(this.datosConsulta.getDate("FechaRegistro")), this.datosConsulta.getString("identificacion"), this.datosConsulta.getString("nombres"), this.datosConsulta.getString("apellidos"), this.datosConsulta.getString("direccion"), this.datosConsulta.getString("Titulo"));
                this.listaActividades.add(this.actividad);
            }
            System.out.println("Carga de actividades terminada, total actividades registradas: " + this.listaActividades.size());
            this.datosConsulta.close();
        } catch (Exception e) {
            System.err.println("Ocurrió un error en ActividadDAO.listarTodas():\n" + e.getMessage());
        }
        return this.listaActividades;
    }

    public int generarCodigoActividad() {
        try {
            this.sentencia = " select count(idActividad) from Actividades;";
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            if (this.datosConsulta.next()) {
                this.codigo = this.datosConsulta.getInt(1);
            } else {
                this.codigo = 0;
            }
            this.datosConsulta.close();
            this.codigo += 1;
        } catch (Exception e) {
            System.err.println("Ocurrio un error en ActividadDAO.generarCodigoActividad():\n" + e.getMessage());
        }
        return this.codigo;
    }

    public void imprimirActividadPorID(int idActividad)
            throws JRException {
        if (idActividad <= 0) {
            JOptionPane.showMessageDialog(null, "El id de la actividad no es válido, verifique e intente de nuevo.", GlobalConfigSystem.getAplicationTitle(), 1);
        } else {
            this.parametro.put("idActividad", Integer.valueOf(idActividad));
            this.reporte = this.adminReport.compilarReporteConParametros(this.urlReport, this.parametro);
            this.adminReport.servicioImpresion(this.reporte);
            this.parametro.clear();
        }
    }

    public void imprimirActividadesParaHoy()
            throws JRException {
        this.listaActividades = listarActividadesPendientesHoy();

        for (int i = 0; i < this.listaActividades.size(); i++) {
            this.actividad = ((Actividad) this.listaActividades.get(i));
            this.parametro.put("idActividad", Integer.valueOf(this.actividad.getIdActividad()));
            this.reporte = this.adminReport.compilarReporteConParametros(this.urlReport, this.parametro);
            this.adminReport.servicioImpresion(this.reporte);
            this.parametro.clear();
            System.out.println("La Actividad: " + this.actividad.getIdActividad() + " ha sido impresa ...");
        }
    }

    public boolean verReportePDF(int idActividad) {
        try {
            this.parametro.put("idActividad", Integer.valueOf(idActividad));
            this.reporte = this.adminReport.compilarReporteConParametros(this.urlReport, this.parametro);
            this.adminReport.verReporte(this.reporte);
            this.parametro.clear();
        } catch (Exception e) {
            System.err.println("Ocurrio un error en ActividadDAO.verReportePDF():\n" + e.getMessage());
        }
        return this.state;
    }
}