/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.api.dao;

import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import nicon.enterprise.libCore.api.util.AdminConector;
import nicon.enterprise.libCore.api.obj.Empleado;

public class EmpleadoDAO {

    private Empleado empleado;
    private boolean stateOP;
    private String sentence;
    private int ExecuteSentence;
    private ArrayList ListaEmpleados;
    private ResultSet DataSentence;
    private int contador;
    private AdminConector coneccion;

    public EmpleadoDAO(Empleado empleado) {
        this.empleado = empleado;
        this.coneccion = AdminConector.getInstance();
    }

    public EmpleadoDAO() {
        this.coneccion = AdminConector.getInstance();
    }

    public boolean crearEmpleado() {
        try {
            this.sentence = ("INSERT INTO Empleados values('" + this.empleado.getIdentificacion() + "','" + this.empleado.getNombres() + "','" + this.empleado.getApellidos() + "','" + this.empleado.getFechaNacimiento() + "','" + this.empleado.getLugarNacimiento() + "','" + this.empleado.getEstadoCivil() + "','" + this.empleado.getDireccion() + "','" + this.empleado.getBarrio() + "','" + this.empleado.getCiudad() + "','" + this.empleado.getTelefonoFijo() + "','" + this.empleado.getTelefonoMovil() + "','" + this.empleado.getEmail() + "'," + this.empleado.getEstado() + ",current_timestamp,1);");
            System.out.println("Sentencia de ejecucion:\n" + this.sentence);
            this.ExecuteSentence = this.coneccion.runSentence(this.sentence);
            if (this.ExecuteSentence == 0) {
                System.out.println("La informacion del empleado se ha ingresado exitosamente ...");
                this.stateOP = true;
            } else {
                System.out.println("La sentencia no se ejecuto y la informacion no se almaceno ...");
                this.stateOP = false;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en EmpleadoDAO.crearEmpleado():\n" + e);
            this.stateOP = false;
        }
        return this.stateOP;
    }

    public boolean eliminarEmpleado() {
        try {
            System.out.println("Inciando eliminacion de empleado ...");
            this.sentence = ("DELETE FROM Empleados WHERE empleados.Identificacion='" + this.empleado.getIdentificacion() + "' AND empleados.nombres='" + this.empleado.getNombres() + ";");
            this.ExecuteSentence = this.coneccion.runSentence(this.sentence);
            if (this.ExecuteSentence == 0) {
                this.stateOP = true;
                System.out.println("El empleado ha sido eliminado");
            } else {
                this.stateOP = false;
                System.out.println("El empleado no se elimino ...");
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error y el empleado no se elimino ...");
            e.printStackTrace();
        }
        return this.stateOP;
    }

    public boolean cambiarEstadoEmpleado(String IDempleado, boolean Estado) {
        try {
            this.sentence = ("UPDATE Empleados set estado=" + Estado + " where identificacion=" + IDempleado + ";");
            this.ExecuteSentence = this.coneccion.runSentence(this.sentence);
            if (this.ExecuteSentence == 0) {
                this.stateOP = true;
            } else {
                this.stateOP = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.stateOP;
    }

    public boolean actualizarDatos(String cedula, String campo, String dato) {
        try {
            System.out.println("Iniciando actualizacion de datos de empleado ...");
            this.sentence = ("UPDATE Empleados SET " + campo + " ='" + dato + "' WHERE Empleados.identificacion='" + cedula + "';");
            System.out.println(this.sentence);
            this.ExecuteSentence = this.coneccion.runSentence(this.sentence);
            if (this.ExecuteSentence == 0) {
                this.stateOP = true;
                System.out.println("Actualizacion de datos Terminada ...");
            } else {
                this.stateOP = false;
                System.out.println("La actualizacion de datos no pudo ser terminada ...");
            }
        } catch (Exception e) {
            System.out.println("Ocurrio el siguiente error en la actualizacion de datos ");
            e.printStackTrace();
        }
        return this.stateOP;
    }

    public ArrayList listarEmpleados() {
        try {
            System.out.println("Inciando carga de informacion de Empleados ...");
            this.ListaEmpleados = new ArrayList();
            this.sentence = "select * from Empleados;";
            this.DataSentence = this.coneccion.queryData(this.sentence);
            while (this.DataSentence.next()) {
                this.empleado = new Empleado(this.DataSentence.getString(1), this.DataSentence.getString(2), this.DataSentence.getString(3), this.DataSentence.getString(4), this.DataSentence.getString(5), this.DataSentence.getString(6), this.DataSentence.getString(7), this.DataSentence.getString(8), this.DataSentence.getString(9), this.DataSentence.getString(10), this.DataSentence.getString(11), this.DataSentence.getString(12), this.DataSentence.getBoolean(13), this.DataSentence.getDate(14), this.DataSentence.getString(15));
                this.ListaEmpleados.add(this.empleado);
            }
            System.out.println("Lista de Empleados cargada exitosamente total registros econtrados: " + this.ListaEmpleados.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.ListaEmpleados;
    }

    public Empleado buscarEmpleadoPorID(String cedula) {
        try {
            System.out.println("Iniciando la carga de datos del empleado de identificacion: " + cedula);
            this.sentence = ("select * from Empleados where Empleados.identificacion='" + cedula + "';");
            this.DataSentence = this.coneccion.queryData(this.sentence);
            if (this.DataSentence.next()) {
                System.out.println("Creando Objeto Empleado con la informacion obtenida ...");
                this.empleado = new Empleado(this.DataSentence.getString(1), this.DataSentence.getString(2), this.DataSentence.getString(3), this.DataSentence.getString(4), this.DataSentence.getString(5), this.DataSentence.getString(6), this.DataSentence.getString(7), this.DataSentence.getString(8), this.DataSentence.getString(9), this.DataSentence.getString(10), this.DataSentence.getString(11), this.DataSentence.getString(12), this.DataSentence.getBoolean(13), this.DataSentence.getDate(14), this.DataSentence.getString(15));
                this.DataSentence.close();
            } else {
                System.out.println("No se encontro informacion de empleado relacionada con la identificacion: " + cedula);
                this.empleado = null;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error mientras se buscaba la informacion: \n");
            e.printStackTrace();
        }
        return this.empleado;
    }

    public Empleado buscarEmpleadoPorNombres(String Nombres) {
        try {
            System.out.println("Iniciando carga de datos de empleado de Nombres: " + Nombres);
            this.sentence = ("select * from Empleados where nombres='" + Nombres + "';");
            this.DataSentence = this.coneccion.queryData(this.sentence);
            if (this.DataSentence.next()) {
                System.out.println("Creando Objeto Empleado con la informacion obtenida ...");
                this.empleado = new Empleado(this.DataSentence.getString(1), this.DataSentence.getString(2), this.DataSentence.getString(3), this.DataSentence.getString(4), this.DataSentence.getString(5), this.DataSentence.getString(6), this.DataSentence.getString(7), this.DataSentence.getString(8), this.DataSentence.getString(9), this.DataSentence.getString(10), this.DataSentence.getString(11), this.DataSentence.getString(12), this.DataSentence.getBoolean(13), this.DataSentence.getDate(14), this.DataSentence.getString(15));
                this.DataSentence.close();
            } else {
                System.out.println("No se encontro informacion de empleado relacionada con la identificacion: " + Nombres);
                this.empleado = null;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error mientras se buscaba la informacion: \n");
            e.printStackTrace();
        }
        return this.empleado;
    }

    public boolean verificarExistencia(String identificacion) {
        try {
            this.sentence = ("select * from Empleados where identificacion='" + identificacion + "';");
            this.DataSentence = this.coneccion.queryData(this.sentence);
            if (this.DataSentence.next()) {
                this.stateOP = true;
            } else {
                this.stateOP = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.stateOP;
    }

    public int obtenerTotalRegistros() {
        try {
            this.sentence = "select count(identificacion) from Empleados;";
            this.DataSentence = this.coneccion.queryData(this.sentence);
            if (this.DataSentence.next()) {
                this.contador = this.DataSentence.getInt(1);
            } else {
                this.contador = 0;
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un error en EmpleadoDAO.obtenerTotalRegistros():\n" + e);
        }
        return this.contador;
    }
}