/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */

package nicon.enterprise.libCore.api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.HashMap;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import nicon.enterprise.libCore.api.util.AdminConector;
import nicon.enterprise.libCore.api.util.NiconAdminReport;
import nicon.enterprise.libCore.api.util.NiconLibTools;
import nicon.enterprise.libCore.api.obj.Actividad;

/**
 * Actividad DAO define el API de acceso a datos del sistema de actividades del
 * que hace uso el sistema, con este api se implementan todos los metodos
 * funciones y sistemas de control del sistema de actividades del modulo de
 * clientes.
 *
 * @author Frederick Adolfo Salazar Sanchez
 */
public class ActividadDAO {

    /**
     * define el url donde se almacena el archivo .jasper que permite generar un
     * reporte de una actividad almacenada en el sistema.
     */
    private static final String JASPER_TEMPLATE_URL = "/nicon/enterprise/libCore/rsc/ImpActividad.jasper";
    
    private int ejecucion;
    private int codigo;
    private boolean state;
    
    private Actividad actividad;
    private AdminConector coneccion;
    private NiconAdminReport apiReporter;
    
    private JasperPrint reporte;
    private ArrayList listaActividades;
    private String sentencia;
    private ResultSet datosConsulta;
    private HashMap parametro;

    /**
     * crea un nuevo API de actividadDAO sin recibir un objeto Actividad, por
     * medio de este acceso se permite el ingreso a ciertos metodos que no
     * requieren un objeto Actividad para obtener datos.
     */
    public ActividadDAO() {
        coneccion = AdminConector.getInstance();
        apiReporter = new NiconAdminReport();
        parametro = new HashMap();
        listaActividades = new ArrayList();
    }

    /**
     * crea un nuevo objeto API de actividadDAO que permite recibir un objeto
     * Actividad por medio de este permite acceder a metodos como creacion o
     * eliminacion de registros.
     *
     * @param actividad
     */
    public ActividadDAO(Actividad actividad) {
        this.actividad = actividad;
        this.coneccion = AdminConector.getInstance();
        this.apiReporter = new NiconAdminReport();
        this.parametro = new HashMap();
    }

    /**
     * este metodo permite crear una actividad dentro delfuente de datos, los
     * datos a enviar la fuente de datos son obtenido desde el objeto Actividad
     * que recibe el metodo constructor 1.
     *
     * @return boolean state
     * @throws SQLException
     */
    public boolean crearActividad() throws SQLException {
        sentencia = "INSERT INTO Actividades (TituloActividad,DescripcionActividad,TipoActividad_codigo,Clientes_identificacion,FechaAsignacion,EstadoActividad,FechaRegistro) VALUES ('" + actividad.getTituloActividad() + "','" + actividad.getDescripcionActividad() + "'," + actividad.getTipoActividad() + "," + actividad.getIdCliente() + ",'" + actividad.getFechaAsignacion() + "'," + actividad.getEstadoActividad() + ",current_timestamp);";
        ejecucion = coneccion.runSentence(sentencia);
        if (ejecucion == 0) {
            state = true;
        } else {
            state = false;
        }
        actividad = null;
        return state;
    }

    /**
     * este metodo permite buscar una actividad por el idActividad, recibe como
     * parametro el id de la actividad a buscar y retorna la actividad en caso
     * de encontrarla con los datos obtenidos, en caso contrario el objeto es
     * null.
     *
     * @param ID
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    public Actividad buscarActividadPorID(String ID) throws SQLException, ParseException {
        sentencia = "SELECT TituloActividad,DescripcionActividad,TipoActividad.Titulo,identificacion,nombres,apellidos,direccion,FechaAsignacion,EstadoActividad,FechaRegistro FROM Actividades,TipoActividad,Clientes WHERE TipoActividad.codigo=TipoActividad_codigo AND Clientes.identificacion=Clientes_identificacion AND idActividad=" + ID + "; ";
        datosConsulta = coneccion.queryData(sentencia);
        if (datosConsulta.next()) {
            actividad = new Actividad(datosConsulta.getInt(1), datosConsulta.getString(2), datosConsulta.getString(3), datosConsulta.getInt(4), datosConsulta.getString(5), NiconLibTools.parseToMysqlStringDate(datosConsulta.getDate(6)), datosConsulta.getBoolean(7), String.valueOf(datosConsulta.getDate(8)));
            datosConsulta.close();
        } else {
            actividad = null;
        }
        return actividad;
    }

    /**
     * este metodo permite buscar dentro de la fuente de datos, todas las
     * actividades que un cliente tenga registradas dentro del sistema pueden
     * ser pendientes o terminadas, son cargadas a un ArrayList y retornadas
     * para ser mostradas en el sistema.
     *
     * @param idCliente
     * @return ArrayList listaActividades
     * @throws SQLException
     */
    public ArrayList listarActividadesPorCliente(String idCliente) throws SQLException {
        listaActividades.clear();
        sentencia = "select idActividad,TituloActividad,DescripcionActividad,TipoActividad.Titulo,Clientes.nombres,Clientes.apellidos,Clientes.direccion,FechaAsignacion,EstadoActividad,FechaRegistro from Actividades,Clientes,TipoActividad where Actividades.TipoActividad_codigo=TipoActividad.codigo and Actividades.Clientes_identificacion=Clientes.identificacion and Actividades.Clientes_identificacion=" + idCliente + ";";
        datosConsulta = coneccion.queryData(sentencia);
        while (datosConsulta.next()) {
            actividad = new Actividad(datosConsulta.getInt("idActividad"), datosConsulta.getString("TituloActividad"), datosConsulta.getString("DescripcionActividad"), String.valueOf(datosConsulta.getDate("FechaAsignacion")), datosConsulta.getBoolean("EstadoActividad"), String.valueOf(datosConsulta.getDate("FechaRegistro")), idCliente, datosConsulta.getString("nombres"), datosConsulta.getString("apellidos"), datosConsulta.getString("direccion"), datosConsulta.getString("Titulo"));
            listaActividades.add(actividad);
        }
        datosConsulta.close();
        return listaActividades;
    }

    public Actividad buscarActividadPorTitulo(String titulo) throws SQLException, ParseException {
        sentencia = ("select * from Actividades where idActividad='" + titulo + "';");
        datosConsulta = coneccion.queryData(sentencia);
        if (datosConsulta.next()) {
            actividad = new Actividad(datosConsulta.getInt(1), datosConsulta.getString(2), datosConsulta.getString(3), datosConsulta.getInt(4), datosConsulta.getString(5), NiconLibTools.parseToMysqlStringDate(datosConsulta.getDate(6)), datosConsulta.getBoolean(7), String.valueOf(datosConsulta.getDate(8)));
            datosConsulta.close();
        }
        return actividad;
    }

    /**
     * este metodo permite cambiar el estado de una actividad registrada en el
     * sistema, recibe el idActividad y el nuevo estado al que se pasará
     *
     * @param ID
     * @param estado
     * @return
     */
    public boolean cambiarEstado(int ID, boolean estado) throws SQLException {
        sentencia = ("UPDATE Actividades SET EstadoActividad=" + estado + " WHERE idActividad=" + ID + ";");
        ejecucion = coneccion.runSentence(sentencia);
        if (ejecucion == 0) {
            state = true;
        } else {
            state = false;
        }
        return state;
    }

    /**
     * este metodo permite consultar el estado de de una actividad registrada en
     * el sistema, recibe como parametro el id de la actividad, ejecuta la
     * consulta contra l fuente de datos y retorna el boolean con el estado
     * actual de la actividad.
     *
     * @param idActividad
     * @return
     */
    public boolean consultarEstado(int idActividad) throws SQLException {
        sentencia = "SELECT EstadoActividad FROM Actividades WHERE idActividad=" + idActividad + ";";
        datosConsulta = coneccion.queryData(sentencia);
        if (datosConsulta.next()) {
            state = datosConsulta.getBoolean(1);
        }
        datosConsulta.close();
        return state;
    }

    /**
     * obtiene un listado con todas las actividades registradas en el sistema
     * cuyo estado de actividad es de pendientes, todas las actividades
     * pendientes son almacenadas en un ArrayList que es retornado a las vistas
     * para ser mostradas al usuario.
     *
     * @return
     */
    public ArrayList listarPendientes() throws SQLException {
        sentencia = "SELECT idActividad,TituloActividad,DescripcionActividad,TipoActividad.Titulo,identificacion,nombres,apellidos,direccion,FechaAsignacion,EstadoActividad,FechaRegistro FROM Actividades,TipoActividad,Clientes where TipoActividad.codigo=TipoActividad_codigo and Clientes.identificacion=Clientes_identificacion and EstadoActividad=false order By idActividad; ";
        datosConsulta = coneccion.queryData(sentencia);
        while (datosConsulta.next()) {
            actividad = new Actividad(datosConsulta.getInt("idActividad"),
                    datosConsulta.getString("TituloActividad"),
                    datosConsulta.getString("DescripcionActividad"),
                    String.valueOf(datosConsulta.getDate("FechaAsignacion")),
                    datosConsulta.getBoolean("EstadoActividad"),
                    String.valueOf(datosConsulta.getDate("FechaRegistro")),
                    datosConsulta.getString("identificacion"),
                    datosConsulta.getString("nombres"),
                    datosConsulta.getString("apellidos"),
                    datosConsulta.getString("direccion"),
                    datosConsulta.getString("Titulo"));
            listaActividades.add(actividad);
        }
        datosConsulta.close();
        return listaActividades;
    }

    /**
     * este metodo permite obtener un listado de todas las actividades que estan
     * pendientes para la fecha actual, hace la consulta de los datos a la
     * fuente de datos y crea todos los objetos de tipo Actividad a un ArrayList
     * que es retornado.
     *
     * @return ArrayList listaActividades
     * @throws SQLException
     */
    public ArrayList listarPendientesHoy() throws SQLException {
        listaActividades = new ArrayList();
        sentencia = "SELECT idActividad,TituloActividad,DescripcionActividad,TipoActividad.Titulo,Clientes.identificacion,Clientes.nombres,Clientes.apellidos,Clientes.direccion,FechaAsignacion,EstadoActividad,FechaRegistro FROM Actividades,TipoActividad,Clientes WHERE TipoActividad.codigo=TipoActividad_codigo AND Clientes.identificacion=Clientes_identificacion AND EstadoActividad=false AND Actividades.FechaAsignacion=current_date order By idActividad; ";
        datosConsulta = coneccion.queryData(sentencia);
        while (datosConsulta.next()) {
            actividad = new Actividad(datosConsulta.getInt("idActividad"), datosConsulta.getString("TituloActividad"),
                    datosConsulta.getString("DescripcionActividad"),
                    String.valueOf(datosConsulta.getDate("FechaAsignacion")),
                    datosConsulta.getBoolean("EstadoActividad"),
                    String.valueOf(datosConsulta.getDate("FechaRegistro")),
                    datosConsulta.getString("identificacion"),
                    datosConsulta.getString("nombres"), datosConsulta.getString("apellidos"),
                    datosConsulta.getString("direccion"), datosConsulta.getString("Titulo"));
            listaActividades.add(actividad);
        }
        datosConsulta.close();
        return listaActividades;
    }

    /**
     * este metodo permite obtener un listado de actividades registradass en el
     * sistema y cuyo estado es terminado o true, esta lista de actividades es
     * cargada al ArrayList que es retornado al solicitante
     *
     * @return ArrayList listaActividades
     */
    public ArrayList listarRealizadas() throws SQLException {
        listaActividades = new ArrayList();
        sentencia = "select idActividad,TituloActividad,DescripcionActividad,TipoActividad.Titulo,identificacion,nombres,apellidos,identificacion,direccion,FechaAsignacion,EstadoActividad,FechaRegistro from Actividades,TipoActividad,Clientes where TipoActividad.codigo=TipoActividad_codigo and Clientes.identificacion=Clientes_identificacion and EstadoActividad=true order By idActividad; ";
        datosConsulta = coneccion.queryData(sentencia);
        while (datosConsulta.next()) {
            actividad = new Actividad(datosConsulta.getInt("idActividad"),
                    datosConsulta.getString("TituloActividad"),
                    datosConsulta.getString("DescripcionActividad"),
                    String.valueOf(datosConsulta.getDate("FechaAsignacion")),
                    datosConsulta.getBoolean("EstadoActividad"),
                    String.valueOf(datosConsulta.getDate("FechaRegistro")),
                    datosConsulta.getString("identificacion"),
                    datosConsulta.getString("nombres"),
                    datosConsulta.getString("apellidos"),
                    datosConsulta.getString("direccion"),
                    datosConsulta.getString("Titulo"));
            listaActividades.add(actividad);
        }
        datosConsulta.close();
        return listaActividades;
    }

    /**
     * este metodo permite listar todas las actividades registradas en el
     * sistema, todas las actividades terminadas o pendientes son cargadas al
     * listado, este es almacenado en un ArrayList que s retornado al sistema.
     *
     * @return ArrayList listaActividades
     */
    public ArrayList listarTodas() throws SQLException {
        sentencia = "SELECT idActividad,TituloActividad,DescripcionActividad,TipoActividad.Titulo,identificacion,nombres,apellidos,direccion,FechaAsignacion,EstadoActividad,FechaRegistro FROM Actividades,TipoActividad,Clientes WHERE TipoActividad.codigo=TipoActividad_codigo AND Clientes.identificacion=Clientes_identificacion order By idActividad; ";
        datosConsulta = coneccion.queryData(sentencia);
        while (datosConsulta.next()) {
            actividad = new Actividad(datosConsulta.getInt("idActividad"),
                    datosConsulta.getString("TituloActividad"),
                    datosConsulta.getString("DescripcionActividad"),
                    String.valueOf(datosConsulta.getDate("FechaAsignacion")),
                    datosConsulta.getBoolean("EstadoActividad"),
                    String.valueOf(datosConsulta.getDate("FechaRegistro")),
                    datosConsulta.getString("identificacion"),
                    datosConsulta.getString("nombres"),
                    datosConsulta.getString("apellidos"),
                    datosConsulta.getString("direccion"),
                    datosConsulta.getString("Titulo"));
            listaActividades.add(actividad);
        }
        datosConsulta.close();
        return listaActividades;
    }

    /**
     *este metodo permite generar el codigo de la siguiente Actividad
     *solicita el conteo de todas las actividades dentro de la fuente de datos
     * a la cual le sume 1 para obtener el proximo idActividad
     * 
     * @return int codigoActividad
     */
    public int generarCodigoActividad() throws SQLException {
        sentencia = "SELECT COUNT(idActividad) FROM Actividades;";
        datosConsulta = coneccion.queryData(sentencia);
            if (datosConsulta.next()) {
                codigo = datosConsulta.getInt(1);
            } else {
                codigo = 0;
            }
            datosConsulta.close();
            codigo ++;        
        return codigo;
    }

    /**
     * Este metodo permite imprimir una actividad registrada en el sistema, 
     * recibe como parametro el idActividad que se desea imprimir y hace
     * la compilacion del reporte haciendo uso del API apiReporter para generar
     * el archivo y enviarlo a imprimir.
     * 
     * @param idActividad
     * @throws JRException 
     */
    public void imprimirActividadPorID(int idActividad) throws JRException {        
            parametro.put("idActividad",idActividad);
            reporte = apiReporter.buildReportParameter(JASPER_TEMPLATE_URL,parametro);
            apiReporter.servicioImpresion(reporte);
            parametro.clear();        
    }

    /**
     * 
     * @throws JRException
     * @throws SQLException 
     */
    public void imprimirListaActividades(ArrayList lista) throws JRException, SQLException {   
        
        for (int i = 0; i < lista.size(); i++) {
                actividad = ((Actividad) lista.get(i));
                parametro.put("idActividad",actividad.getIdActividad());
                reporte = apiReporter.buildReportParameter(JASPER_TEMPLATE_URL,parametro);
                apiReporter.servicioImpresion(reporte);
                parametro.clear();
        }
    }
    
    /**
     * Este metodo permite que una actividad sea exportada y vista en un archivo
     * pdf, hace uso del JasperViewer para mostrar el archivo pdf al usuario.
     * 
     * @param idActividad
     * @return 
     */
    public boolean verActividadPDF(int idActividad) throws JRException {
            parametro.put("idActividad", Integer.valueOf(idActividad));
            reporte = apiReporter.buildReportParameter(JASPER_TEMPLATE_URL,parametro);
            apiReporter.viewerReport(reporte);
            parametro.clear();        
        return state;
    }
}