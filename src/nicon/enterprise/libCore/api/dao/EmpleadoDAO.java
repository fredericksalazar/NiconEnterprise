/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.libCore.api.dao;

import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import nicon.enterprise.libCore.api.util.AdminConector;
import nicon.enterprise.libCore.api.obj.Empleado;

public class EmpleadoDAO {

    private Empleado empleado;
    
    private boolean stateOP;
    private String sentence;
    private int ExecuteSentence;
    private int contador;
    
    private ArrayList ListaEmpleados;
    private ResultSet DataSentence;
    private AdminConector coneccion;

    /**
     * 
     * @param empleado 
     */
    public EmpleadoDAO(Empleado empleado) {
        this.empleado = empleado;
        this.coneccion = AdminConector.getInstance();
    }

    /**
     * 
     */
    public EmpleadoDAO() {
        coneccion = AdminConector.getInstance();
        ListaEmpleados = new ArrayList();
    }

    /**
     * este metodo permite crear n nuevo empleado dentro de la fuente de datos, 
     * la informacion del empleado a crear se obtiene del objeto empleado que
     * se recibe dentro del constructor
     * 
     * @return 
     */
    public boolean createEmployee() throws SQLException {
            sentence = "INSERT INTO Empleados values('" + empleado.getIdentificacion() + "','" + empleado.getNombres() + "','" + empleado.getApellidos() + "','" + empleado.getFechaNacimiento() + "','" + empleado.getLugarNacimiento() + "','" + empleado.getEstadoCivil() + "','" + empleado.getDireccion() + "','" + empleado.getBarrio() + "','" + empleado.getCiudad() + "','" +empleado.getTelefonoFijo() + "','" + empleado.getTelefonoMovil() + "','" +empleado.getEmail() + "'," +empleado.getEstado() + ",current_timestamp,1);";
            ExecuteSentence = coneccion.runSentence(sentence);
                if (ExecuteSentence == 0) {
                    stateOP = true;
                } else {
                    stateOP = false;
                }        
        return stateOP;
    }

    /**
     * 
     * @return
     * @throws SQLException 
     */
    public boolean eliminarEmpleado() throws SQLException {
            sentence = "DELETE FROM Empleados WHERE empleados.Identificacion='" +empleado.getIdentificacion() + "' AND empleados.nombres='" +empleado.getNombres() + ";";
            ExecuteSentence = coneccion.runSentence(sentence);
                if (ExecuteSentence == 0) {
                    stateOP = true;
                } else {
                    stateOP = false;
                }        
        return stateOP;
    }

    /**
     * el estado de un empleado hace referencia al estado dentro de la organizacion
     * que puede ser activo o inactivo, el hecho de que un empleado tenga como
     * estado inactivo refiere de que no se le permite ni el ingreso ni el pago
     * de nomina.
     * 
     * @param IDempleado
     * @param Estado
     * @return boolean stateOP;
     * @throws SQLException 
     */
    public boolean cambiarEstadoEmpleado(String IDempleado, boolean Estado) throws SQLException {
            sentence = "UPDATE Empleados set estado=" + Estado + " where identificacion=" + IDempleado + ";";
            ExecuteSentence =coneccion.runSentence(sentence);
                if (ExecuteSentence == 0) {
                    stateOP = true;
                } else {
                    stateOP = false;
                }
            return stateOP;
    }

    /**
     * este metodo permite actualizar los datos de un empleado, registrado dentro
     * de la fuente de datos, recibe como parametros la cedula del empleado, el
     * campo a actulizar y el nuevo dato.
     * 
     * @param cedula
     * @param campo
     * @param dato
     * @return
     * @throws SQLException 
     */
    public boolean actualizarDatos(String cedula, String campo, String dato) throws SQLException {
            sentence = ("UPDATE Empleados SET " + campo + " ='" + dato + "' WHERE Empleados.identificacion='" + cedula + "';");
            ExecuteSentence = coneccion.runSentence(sentence);
                if (ExecuteSentence == 0) {
                    stateOP = true;
                } else {
                    stateOP = false;
                }
                return stateOP;
    }

    /**
     * este metodo permite obtener un listado de todos los empleados registrados
     * en la fuente de datos, 
     * @return ArrayList listEmpleados
     * @throws SQLException 
     */
    public ArrayList listarEmpleados() throws SQLException {            
            sentence = "SELECT * FROM Empleados;";
            DataSentence = coneccion.queryData(sentence);
                while (DataSentence.next()) {
                    empleado = new Empleado(DataSentence.getString(1),DataSentence.getString(2),DataSentence.getString(3),DataSentence.getString(4),DataSentence.getString(5),DataSentence.getString(6),DataSentence.getString(7),DataSentence.getString(8),DataSentence.getString(9),DataSentence.getString(10),DataSentence.getString(11),DataSentence.getString(12),DataSentence.getBoolean(13),DataSentence.getDate(14),DataSentence.getString(15));
                    ListaEmpleados.add(empleado);
                }
            return ListaEmpleados;
    }

    /**
     * 
     * @param cedula
     * @return
     * @throws SQLException 
     */
    public Empleado buscarEmpleadoPorID(String cedula) throws SQLException {
            sentence = ("select * from Empleados where Empleados.identificacion='" + cedula + "';");
            DataSentence = coneccion.queryData(sentence);
            if (DataSentence.next()) {
                empleado = new Empleado(this.DataSentence.getString(1), this.DataSentence.getString(2), this.DataSentence.getString(3), this.DataSentence.getString(4), this.DataSentence.getString(5), this.DataSentence.getString(6), this.DataSentence.getString(7), this.DataSentence.getString(8), this.DataSentence.getString(9), this.DataSentence.getString(10), this.DataSentence.getString(11), this.DataSentence.getString(12), this.DataSentence.getBoolean(13), this.DataSentence.getDate(14), this.DataSentence.getString(15));
                DataSentence.close();
            } else {
                empleado = null;
            }
            return empleado;
    }

    /**
     * 
     * @param Nombres
     * @return
     * @throws SQLException 
     */
    public Empleado buscarEmpleadoPorNombres(String Nombres) throws SQLException {
            sentence = ("select * from Empleados where nombres='" + Nombres + "';");
            DataSentence = coneccion.queryData(sentence);
                if (DataSentence.next()) {
                    empleado = new Empleado(DataSentence.getString(1),DataSentence.getString(2),DataSentence.getString(3),DataSentence.getString(4),DataSentence.getString(5),DataSentence.getString(6),DataSentence.getString(7),DataSentence.getString(8),DataSentence.getString(9),DataSentence.getString(10),DataSentence.getString(11),DataSentence.getString(12),DataSentence.getBoolean(13),DataSentence.getDate(14),DataSentence.getString(15));
                    DataSentence.close();
                } else {
                    empleado = null;
                }        
        return empleado;
    }

    /**
     * 
     * @param identificacion
     * @return
     * @throws SQLException 
     */
    public boolean verificarExistencia(String identificacion) throws SQLException {
            sentence = "select * from Empleados where identificacion='" + identificacion + "';";
            DataSentence = coneccion.queryData(sentence);
                if (DataSentence.next()) {
                    stateOP = true;
                } else {
                    stateOP = false;
                }        
        return stateOP;
    }

    /**
     * 
     * @return
     * @throws SQLException 
     */
    public int obtenerTotalRegistros() throws SQLException {
            sentence = "select count(identificacion) from Empleados;";
            DataSentence = coneccion.queryData(sentence);
            if (DataSentence.next()) {
                contador = DataSentence.getInt(1);
            } else {
                contador = 0;
            }return contador;
    }
}