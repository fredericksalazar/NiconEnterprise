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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import nicon.enterprise.libCore.api.util.AdminConector;
import nicon.enterprise.libCore.api.util.NiconAdminReport;
import nicon.enterprise.libCore.obj.Proveedor;

/**
 * ProveedorDAO define el API de metodos que pueden ser ejecutados desde el 
 * modulo de proveedores.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class ProveedorDAO {
    
    /**
     *define la URL donde esta almacenada la plantilla de reporte de la lista de
     *proveedores que será exportada en archivo pdf.
     */
    public static final String URL_REPORT_LISTA_PROVEEDORES="/nicon/enterprise/libCore/rsc/ListaProveedores.jasper";

    private Proveedor proveedor;
    private ArrayList listaProveedores;
    private ArrayList listaCiudades;
    private ResultSet datosConsulta;
    
    private String sentencia;
    private boolean stateOP;
    private int response;
    
    private AdminConector coneccion;
    private NiconAdminReport adminReport;
    private JasperPrint reporte;

    /**
     * constructor que recibe un objeto de tipo Proveedor para ser almacenado
     * en la fuente de datos, este constructor brinda acceso a metodos que el 
     * API define como de escritura.
     * @param proveedor 
     */
    public ProveedorDAO(Proveedor proveedor) {
        this.proveedor = proveedor;
        this.coneccion = AdminConector.getInstance();
    }

    /**
     * Este metodo permite 
     */
    public ProveedorDAO() {
        this.proveedor = null;
        this.coneccion = AdminConector.getInstance();
        listaProveedores = new ArrayList();
    }

    /**
     * este metodo permite crear un nuevo proveedor dentro de la fuente de datos
     * los datos del proveedor serán tomados del objeto Proveedor que recibe el
     * constructor definido para escritura de datos, al ejecutar la sentencia
     * retorna un boolean con el valor de la operacion, true en caso de haber sido
     * creado exitosamente y false en caso de no haber creado el proveedor.
     * 
     * @return
     * @throws SQLException 
     */
    public boolean crearProveedor() throws SQLException {
                sentencia = "INSERT INTO Proveedores VALUES('" +proveedor.getNit() + "','" + proveedor.getRazonSocial() + "','" +proveedor.getDireccion() + "','" +proveedor.getCiudad() + "','" +proveedor.getTelefonoFijo() + "','" +proveedor.getTelefonoMovil() + "','" +proveedor.getFax() + "','" +proveedor.getEmail() + "','" + proveedor.getWebPage() + "','" +proveedor.getBanco() + "','" + proveedor.getNumeroCuenta() + "','" + proveedor.getDescripcion() + "',1);";
                response = coneccion.runSentence(sentencia);
                    if (response == 0) {
                        stateOP = true;
                    } else {
                        stateOP = false;
                    }       
        return stateOP;
    }

    /**
     * este metodo permite eliminar un proveedor registrado dentro de la fuente
     * de datos, los datos para la eliminacion son tomados del objeto Proveedor
     * recibido en el constructor de escritura.
     * 
     * @return boolean stateOP
     * @throws SQLException 
     */
    public boolean eliminarProveedor() throws SQLException {
              sentencia = "DELETE FROM Proveedores WHERE Nit='" +proveedor.getNit()+ "';";
              response = coneccion.runSentence(sentencia);
                if (response == 0) {
                    stateOP = true;
                } else {
                    stateOP = false;
                }       
        return stateOP;
    }

    /**
     * este metodo permite actualizar un dato de un proveedor, la actualizacion
     * de datos permite que pueda actualizar cualquier registro del sistema, 
     * recibe como parametro el Nit del proveedor, el campo a actualizar y el
     * nuevo dato que será ingresado en la base de datos.
     * @param Nit
     * @param Campo
     * @param dato
     * @return boolean stateOP
     * @throws SQLException 
     */
    public boolean actualizarProveedor(String Nit, String Campo, String dato) throws SQLException {       
           sentencia = "UPDATE Proveedores SET " + Campo + " ='" + dato + "' WHERE Nit='" + Nit + "';";
           response = coneccion.runSentence(sentencia);
                if (response == 0) {
                    stateOP = true;
                } else {
                    stateOP = false;
                }
        return stateOP;
    }

    /**
     * este metodo permite obtener un listado de todos los proveedores registrados
     * dentro del sistema, al consultar todos los proveedores estos son almacenados
     * dentro de un ArrayList que será retornado al solicitante
     * 
     * @return ArrayList listaProveedores
     * @throws SQLException 
     */
    public ArrayList listarProveedores() throws SQLException {
            sentencia = "SELECT * FROM Proveedores;";
            datosConsulta = (ResultSet) coneccion.queryData(sentencia);
                while (datosConsulta.next()) {
                    proveedor = new Proveedor(datosConsulta.getString(1),datosConsulta.getString(2),datosConsulta.getString(3),datosConsulta.getString(4),datosConsulta.getString(5),datosConsulta.getString(6),datosConsulta.getString(7),datosConsulta.getString(8),datosConsulta.getString(9),datosConsulta.getString(10),datosConsulta.getString(11),datosConsulta.getString(12));
                    listaProveedores.add(proveedor);
                }
            datosConsulta.close();        
        return listaProveedores;
    }

    /**
     * este metodo permite obtener un listado de proveedores pero ordenados
     * segun parametros alfabeticamente definidos, recibe como parametro
     * el cdigo de ordenamiento asc en caso de ser ascedente o desc en caso de 
     * ser descendente, estos objetos de tipo proveedor son almacenados en una
     * lista y retornados 
     * @param opcion
     * @return ArrayList listaProveedores
     * @throws SQLException 
     */
    public ArrayList listarProveedoresOrdenados(String opcion) throws SQLException {
            if (opcion.equals("asc")) {
                sentencia = "SELECT * FROM Proveedores ORDER BY Razon_social ASC;";
            }
            if (opcion.equals("desc")) {
                sentencia = "SELECT * FROM Proveedores ORDER BY Razon_social DESC;";
            }
            datosConsulta = (ResultSet) coneccion.queryData(sentencia);
                while (datosConsulta.next()) {
                    proveedor = new Proveedor(datosConsulta.getString(1),datosConsulta.getString(2),datosConsulta.getString(3),datosConsulta.getString(4),datosConsulta.getString(5),datosConsulta.getString(6),datosConsulta.getString(7),datosConsulta.getString(8),datosConsulta.getString(9),datosConsulta.getString(10), datosConsulta.getString(11),datosConsulta.getString(12));
                    listaProveedores.add(proveedor);
                }
            datosConsulta.close();
        return listaProveedores;
    }

    /**
     * este metodo permite obtener un listado de todos los proveedores registrados
     * ubicados dentro de la misma ciudad, recibe como parametro el nobmre de la
     * ciudad en la cual se buscarán los proveedores-
     * 
     * @param ciudad
     * @return ArrayList listaProveedores
     * @throws SQLException 
     */
    public ArrayList listarProveedoresPorCiudad(String ciudad) throws SQLException {
            sentencia = "SELECT * FROM  Proveedores WHERE ciudad='" + ciudad + "';";
            datosConsulta = (ResultSet) coneccion.queryData(sentencia);
                while (datosConsulta.next()) {
                    proveedor = new Proveedor(datosConsulta.getString(1),datosConsulta.getString(2),datosConsulta.getString(3),datosConsulta.getString(4),datosConsulta.getString(5),datosConsulta.getString(6),datosConsulta.getString(7),datosConsulta.getString(8),datosConsulta.getString(9),datosConsulta.getString(10),datosConsulta.getString(11),datosConsulta.getString(12));
                    listaProveedores.add(proveedor);
                }
            datosConsulta.close();
        return listaProveedores;
    }

    public ArrayList obtenerListaCiudades() {
        try {
            this.listaCiudades = new ArrayList();
            this.sentencia = "select ciudad from Proveedores;";
            this.datosConsulta = ((ResultSet) this.coneccion.queryData(this.sentencia));
            while (this.datosConsulta.next()) {
                this.listaCiudades.add(this.datosConsulta.getString(1));
            }
        } catch (Exception e) {
        }
        return this.listaCiudades;
    }

    /**
     * este metodo permite buscar la informacion  de un proveedor dentro de la
     * fuente de datos, recibe como parametro el nit del proveedor a buscar,
     * en caso de ser encontrado el objeto proveedor es retornado con sus valores
     * en caso contrario el objeto proveedor es ajustado a null.
     * 
     * @param Nit
     * @return Proveedor proveedor
     * @throws SQLException 
     */
    public Proveedor buscarProveedorPorNit(String Nit) throws SQLException {
            sentencia = "SELECT * FROM Proveedores WHERE Nit='" + Nit + "';";
            datosConsulta = (ResultSet) coneccion.queryData(sentencia);
                if (datosConsulta.next()) {
                    proveedor = new Proveedor(datosConsulta.getString(1), datosConsulta.getString(2),datosConsulta.getString(3),datosConsulta.getString(4),datosConsulta.getString(5),datosConsulta.getString(6),datosConsulta.getString(7),datosConsulta.getString(8),datosConsulta.getString(9),datosConsulta.getString(10),datosConsulta.getString(11),datosConsulta.getString(12));
                } else {
                    proveedor = null;
                }        
        return proveedor;
    }

    /**
     * premite buscar datos de un proveedor dentro de la fuente de datos, recibe
     * como parametros un String con la razon social del proveedor a buscar,
     * en caso de encontrar registros retorna un objeto proveedor, en caso contrario
     * el objeto proveedor toma valor de null.
     * 
     * @param razonSocial
     * @return Proveedor proveedor
     * @throws SQLException 
     */
    public Proveedor buscarProveedorPorRazonSocial(String razonSocial) throws SQLException {
            sentencia = "SELECT * FROM Proveedores WHERE Razon_social='" + razonSocial + "';";
            datosConsulta = (ResultSet) coneccion.queryData(sentencia);
                if (datosConsulta.next()) {
                    proveedor = new Proveedor(datosConsulta.getString(1),datosConsulta.getString(2),datosConsulta.getString(3),datosConsulta.getString(4),datosConsulta.getString(5),datosConsulta.getString(6),datosConsulta.getString(7),datosConsulta.getString(8),datosConsulta.getString(9),datosConsulta.getString(10),datosConsulta.getString(11),datosConsulta.getString(12));
                } else {
                    proveedor = null;
                }        
        return proveedor;
    }

    public void exportarTodosPDF()throws JRException {
        adminReport = new NiconAdminReport();
        reporte = adminReport.buildReport(URL_REPORT_LISTA_PROVEEDORES);
        adminReport.viewerReport(reporte);
    }
}
