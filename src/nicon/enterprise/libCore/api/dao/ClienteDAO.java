/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */

package nicon.enterprise.libCore.api.dao;

import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import nicon.enterprise.libCore.AdminConector;
import nicon.enterprise.libCore.NiconAdminReport;
import nicon.enterprise.libCore.api.obj.Cliente;

/**
 * ClienteDAO es la interfaz de metodos de todo el modulo de clientes, define
 * herramientas que el usuario puede usar para registrar, actualizar y o
 * eliminar registros de la fuente de datos, este Interfaz ademas permite
 * mediante los metodos definidos buscar informacion estadistica de los
 * clientes.
 *
 * @author frederick
 */
public class ClienteDAO {
    
    private final String URL_REPORT="/Nicon/Enterprise/LibCore/rsc/ListaClientes.jasper";

    private Cliente cliente;
    private boolean state = false;
    private String sentencia;
    private int ExecuteSentence;
    private int totalRegistros;
    private ArrayList listaClientes;
    private ResultSet datosConsulta;
    private String impresion;
    private AdminConector coneccion;
    private NiconAdminReport adminReport;
    private JasperPrint reporte;

    /**
     * Metodo constructor que recibe como parametro un objeto de tipo Cliente
     * que será usado para el registro o e eliminacion de registros de la fuente
     * de datos.
     *
     * @param cliente
     */
    public ClienteDAO(Cliente cliente) {
        this.cliente = cliente;
        this.coneccion = AdminConector.getInstance();
    }

    /**
     * Este metodo constructor permite acceder a los metodos que define el api
     * como metodos de consulta de informacion y busqueda.
     */
    public ClienteDAO() {
        listaClientes = new ArrayList();
        coneccion = AdminConector.getInstance();
        adminReport = new NiconAdminReport();
    }

    /**
     * este metodo permite ingresar a la entidad de clientes un nuevo registro
     * con el objeto de tipo cliente que recibe del constructor, se evalua el
     * estado del objeto cliente en caso de ser null el estado de la operacion
     * pasa a ser false en caso contrario intenta ejecutar la sentencia mediante
     * la interfaz de coneccion NiconDB.
     *
     * @return boolean stateOP
     * @throws SQLException
     */
    public boolean crearCliente() throws SQLException {
        if (cliente != null) {
            sentencia = "INSERT INTO Clientes VALUES(" + this.cliente.getIdentificacion() + ",'" + this.cliente.getNombres() + "','" + this.cliente.getApellidos() + "','" + this.cliente.getCiudad() + "','" + this.cliente.getDireccion() + "','" + this.cliente.getProvincia() + "','" + this.cliente.getTelefono_fijo() + "','" + this.cliente.getTelefono_movil() + "','" + this.cliente.getTelefono_alternativo() + "','" + this.cliente.getEmail() + "',current_timestamp," + this.cliente.getCodigoAlmacen() + ");";
            ExecuteSentence = coneccion.runSentence(sentencia);
            if (ExecuteSentence == 0) {
                state = true;
            } else {
                state = false;
            }
        } else {
            state = false;
        }
        cliente = null;
        return state;
    }

    /**
     * Este metodo permite elimnar los registros de un cliente dentro de la
     * fuente de datos, en caso de que el cliente no posea dependencias de
     * informacion puede ser eliminado en caso de que posea dependencias el
     * sistema no permitira la eliminacion de ningun tipo de registros.
     *
     * @return stateOP
     * @throws SQLException
     */
    public boolean eliminarCliente() throws SQLException {
        sentencia = "DELETE FROM Clientes WHERE Clientes.identificacion=" + cliente.getIdentificacion() + ";";
        ExecuteSentence = coneccion.runSentence(sentencia);
        if (ExecuteSentence == 0) {
            state = true;
        } else {
            state = false;
        }
        return state;
    }

    /**
     * Metodo que permite la actualizacion de el numero de identificacion de un
     * usuario registrado dentro de la
     *
     * @param oldID
     * @param NewID
     * @return
     */
    public boolean actualizarIdentificacion(String oldID, String NewID) throws SQLException {
        sentencia = "UPDATE Clientes SET identificacion = " + NewID + " where identificacion=" + oldID + ";";
        ExecuteSentence = this.coneccion.runSentence(this.sentencia);
        if (this.ExecuteSentence == 0) {
            state = true;
        } else {
            state = false;
        }
        return state;
    }

    /**
     * Este metodo permite actualizar la informacion de un cliente, recibe como
     * parametros la cedula, el campo a actualizar y el nuevo dato a ingresar.
     *
     * @param cedula
     * @param campo
     * @param dato
     * @return boolean stateOP
     */
    public boolean actualizarDatoCliente(String cedula, String campo, String dato) throws SQLException {
        sentencia = "UPDATE Clientes SET " + campo + " = '" + dato + "' WHERE identificacion=" + cedula + ";";
        ExecuteSentence = coneccion.runSentence(sentencia);
        if (ExecuteSentence == 0) {
            state = true;
        } else {
            state = false;
        }
        return state;
    }

    /**
     * Este metodo permite obtener un listado con toda la informacion de todos
     * los clientes registrados en la base de datos, los ordenado por el nombre
     * de forma ascendente
     *
     * @return listaClientes
     * @throws SQLException
     */
    public ArrayList listarClientes() throws SQLException {
        sentencia = "SELECT  * FROM Clientes ORDER BY nombres ASC;";
        datosConsulta = coneccion.queryData(sentencia);
        while (datosConsulta.next()) {
            cliente = new Cliente(datosConsulta.getString("identificacion"), datosConsulta.getString("nombres"), datosConsulta.getString("apellidos"), datosConsulta.getString("ciudad"), datosConsulta.getString("direccion"), datosConsulta.getString("Departamento"), datosConsulta.getString("telefono_fijo"), datosConsulta.getString("telefono_movil"), datosConsulta.getString("telefono_alternativo"), datosConsulta.getString("email"), String.valueOf(datosConsulta.getDate("fecha_registro")), datosConsulta.getInt("Almacenes_idAlmacenes"));
            listaClientes.add(cliente);
        }
        cliente = null;
        datosConsulta.close();
        return listaClientes;
    }

    /**
     * este metodo permite obtener un listado de todos los clientes ordenados
     * por nombres ascendennte o de forma descendente, recibe como parametro la
     * opcion de ordenamiento.
     */
    public ArrayList listarClientesOrdenadosPorNombre(String opcion) throws SQLException {

        if (opcion.equals("asc")) {
            sentencia = "SELECT  * FROM Clientes ORDER BY nombres ASC;";
        }
        if (opcion.equals("desc")) {
            sentencia = "SELECT * FROM Clientes ORDER BY nombres DESC;";
        }
        datosConsulta = coneccion.queryData(sentencia);        
            while (datosConsulta.next()) {
                cliente = new Cliente(datosConsulta.getString("identificacion"), datosConsulta.getString("nombres"), datosConsulta.getString("apellidos"), datosConsulta.getString("ciudad"), datosConsulta.getString("direccion"), datosConsulta.getString("Departamento"), datosConsulta.getString("telefono_fijo"), datosConsulta.getString("telefono_movil"), datosConsulta.getString("telefono_alternativo"), datosConsulta.getString("email"), String.valueOf(datosConsulta.getDate("fecha_registro")), datosConsulta.getInt("Almacenes_idAlmacenes"));
                listaClientes.add(cliente);
            }
        cliente = null;
        datosConsulta.close();
        return listaClientes;
    }

    /**
     * este metodo permite listar todos los clientes que residan en ala misma ciudad, recibe como parametros el
     * nombre de la ciudad con la que se agruparán los registros obtenidos.
     * @param ciudad
     * @return listaClientes
     */
    public ArrayList listarClientesPorCiudad(String ciudad) throws SQLException {        
            sentencia = "SELECT  * FROM Clientes WHERE  ciudad='" + ciudad + "' ORDER BY nombres ASC;";
            datosConsulta = coneccion.queryData(sentencia);
                while (datosConsulta.next()) {
                    cliente = new Cliente(datosConsulta.getString("identificacion"),datosConsulta.getString("nombres"),datosConsulta.getString("apellidos"),datosConsulta.getString("ciudad"),datosConsulta.getString("direccion"), datosConsulta.getString("Departamento"),datosConsulta.getString("telefono_fijo"),datosConsulta.getString("telefono_movil"),datosConsulta.getString("telefono_alternativo"),datosConsulta.getString("email"), String.valueOf(datosConsulta.getDate("fecha_registro")),datosConsulta.getInt("Almacenes_idAlmacenes"));
                    listaClientes.add(cliente);
                }
            cliente=null;
            datosConsulta.close();
        return listaClientes;
    }

    /**
     * este metodo permite buscar los datos de un cliente por el numero de identificacion registrado como primey Key
     * dentro de la fuente de datos.
     * 
     * @param identificacion
     * @return Cliente cliente
     */
    public Cliente buscarPorIdentificacion(String identificacion) throws SQLException {        
            sentencia = "SELECT  `nombres`, `apellidos`, `ciudad`, `direccion`, `Departamento`, `telefono_fijo`, `telefono_movil`, `Telefono_alternativo`, `email`, `fecha_registro`, `Almacenes_idAlmacenes` FROM `Clientes` WHERE identificacion=" + identificacion + ";";
            datosConsulta = coneccion.queryData(sentencia);            
                if (datosConsulta.next()) {
                    cliente = new Cliente(identificacion,datosConsulta.getString("nombres"), datosConsulta.getString("apellidos"),datosConsulta.getString("ciudad"),datosConsulta.getString("direccion"), datosConsulta.getString("Departamento"), datosConsulta.getString("telefono_fijo"), datosConsulta.getString("telefono_movil"), datosConsulta.getString("Telefono_alternativo"),datosConsulta.getString("email"),datosConsulta.getString("fecha_registro"),datosConsulta.getInt("Almacenes_idAlmacenes"));
                } else {
                    cliente = null;
                } 
                datosConsulta.close();
        return cliente;
    }

    /**
     * Este metodo permite bucar registros de un cliente usando el nombre como parametro de busqeuda de datos.
     * @param Nombres
     * @return 
     */
    public Cliente buscarPorNombres(String Nombres) throws SQLException {
            sentencia = "SELECT distinct * FROM Clientes WHERE nombres='" + Nombres + "';";
            datosConsulta = coneccion.queryData(sentencia);
                if (datosConsulta.next()) {
                    cliente = new Cliente(datosConsulta.getString(1),datosConsulta.getString(2),datosConsulta.getString(3),datosConsulta.getString(4),datosConsulta.getString(5),datosConsulta.getString(6),datosConsulta.getString(7),datosConsulta.getString(8),datosConsulta.getString(9),datosConsulta.getString(10),datosConsulta.getInt(11));
                } else {
                    cliente = null;
                }
            datosConsulta.close();        
        return cliente;
    }

    /**
     * valida la existencia de un cliente dentro de la base de datos recibiendo como parametros el nombre y los apellidos
     * del cliente a registrar, en caso de encontrar registros retorna un objeto de tipo Cliente con la informacion recibida
     * en caso de no encontrar ningun registro el dato a retornar es null.
     * 
     * @param nombres
     * @param apellidos
     * @return 
     */
    public Cliente validarCliente(String nombres, String apellidos,String ciudad) throws SQLException {
            sentencia = "SELECT DISTINCT * FROM Clientes WHERE nombres='" + nombres + "' AND apellidos='" + apellidos + "' AND ciudad='"+ciudad+"';";
            datosConsulta = coneccion.queryData(sentencia);
                if (datosConsulta.next()) {
                        cliente = new Cliente(datosConsulta.getString(1),datosConsulta.getString(2),datosConsulta.getString(3),datosConsulta.getString(4),datosConsulta.getString(5),datosConsulta.getString(6), this.datosConsulta.getString(7),datosConsulta.getString(8),datosConsulta.getString(9),datosConsulta.getString(10),datosConsulta.getInt(11));
                } else {
                        cliente = null;
                }
            datosConsulta.close();       
        return cliente;
    }

    /**
     * este metodo retorna el total de registros en la entidad de clientes de la base de datos, el conteo se hace en
     * la columna de Clientes.identificacion, en caso de poder ejecutar la sentencia y obtener resultados castea a 
     * Integer el resultado y lo retorna, en caso de no haber registros en el resultSet retorna 0
     * 
     * @return Int totalRegistros
     */
    public int obtenerTotalRegistros() throws SQLException {
            sentencia = "SELETC COUNT(identificacion) FROM Clientes;";
            datosConsulta = coneccion.queryData(sentencia);
                if (datosConsulta.next()) {
                        totalRegistros = datosConsulta.getInt(1);
                } else {
                        totalRegistros = 0;
                }
                datosConsulta.close();        
        return  totalRegistros;
    }

    /**
     * Este metodo permite generar un listado en pdf de todos los cliente registrados en la basse de datos, ese archiv
     * por lo general este archivo pdf solo sera visualizado usando el JasperViewer que ofrece el API JasperReport,
     * 
     * @throws JRException 
     */
    public void exportarTodosPDF() throws JRException {
            if(adminReport!=null){            
                    reporte = adminReport.compilarReporte(URL_REPORT);
                    adminReport.verReporte(reporte);
            }
            reporte=null;
            adminReport=null;
    }
}