/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.dao;

import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import nicon.enterprise.libCore.AdminConector;
import nicon.enterprise.libCore.obj.TipoActividad;

public class TipoActividadDAO {

    private TipoActividad tipoActividad;
    private boolean stateOP;
    private ArrayList listaActividades;
    private String sentencia;
    private AdminConector coneccion;
    private int ejecucion;
    private ResultSet datosConsulta;

    public TipoActividadDAO() {
        this.coneccion = AdminConector.getInstance();
    }

    public TipoActividadDAO(TipoActividad actividad) {
        this.tipoActividad = actividad;
        this.coneccion = AdminConector.getInstance();
    }

    public boolean crearTipoActividad() {
        try {
            this.sentencia = (" insert into TipoActividad (Titulo,Descripcion) values('" + this.tipoActividad.getNombreActividad() + "','" + this.tipoActividad.getDescripcion() + "');");
            this.ejecucion = this.coneccion.runSentence(this.sentencia);
            if (this.ejecucion == 0) {
                this.stateOP = true;
            } else {
                this.stateOP = false;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en TipoActividadDAO:crearTipoActividad():\n" + e.getMessage());
        }
        return this.stateOP;
    }

    public boolean editarTipoActividad(int id, String campo, String dato) {
        try {
            this.sentencia = ("update TipoActividad set " + campo + " ='" + dato + "' where codigo=" + id + ";");
            this.ejecucion = this.coneccion.runSentence(this.sentencia);
            if (this.ejecucion == 0) {
                this.stateOP = true;
            } else {
                this.stateOP = false;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en TipActividadDAO:editarTipoActividad():\n" + e.getMessage());
        }
        return this.stateOP;
    }

    public TipoActividad buscarActividad(int id) {
        try {
            this.sentencia = ("select * from TipoActividad where codigo=" + id + ";");
            this.datosConsulta = ((ResultSet) this.coneccion.queryData(this.sentencia));
            if (this.datosConsulta.next()) {
                this.tipoActividad = new TipoActividad(this.datosConsulta.getInt(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3));
                this.datosConsulta.close();
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en TipoActividadDAO:buscarActividad():\n" + e.getMessage());
        }
        return this.tipoActividad;
    }

    public boolean eliminarTipoActividad() {
        return this.stateOP;
    }

    public ArrayList listarTipoActividad() {
        try {
            this.listaActividades = new ArrayList();
            this.sentencia = "select * from TipoActividad;";
            this.datosConsulta = ((ResultSet) this.coneccion.queryData(this.sentencia));
            while (this.datosConsulta.next()) {
                this.tipoActividad = new TipoActividad(this.datosConsulta.getInt(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3));
                this.listaActividades.add(this.tipoActividad);
            }
            this.datosConsulta.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error en TipoActividadDAO:listarTipoActividad():\n" + e.getMessage());
        }
        return this.listaActividades;
    }
}