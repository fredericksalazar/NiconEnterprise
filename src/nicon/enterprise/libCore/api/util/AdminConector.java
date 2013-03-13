/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.libCore.api.util;

import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import nicon.enterprise.libCore.api.dao.ConfigConectorDAO;
import nicon.enterprise.libCore.obj.ConfigConector;

/**
 * la clase AdminConector es la encargada de gestionar la coneccion con la fuente de datos, se convierte en 
 * si misma en la capa de comunicacion entre el NiconEnterprise FrontEnd y la fuente de datos en MySql, 
 * AdminConector define los metodos para la consulta de datos y ejecucion de operaciones de datos almacenados
 * en la fuente, se considera como de nvel bajo de la aplicacion
 * 
 * AdminConector hace uso del API de MysqlConector asi mismo como del API de configuracion de Coneccion
 * ConfigConector y ConfigConectorDAO usados para establecer los parametros de configuracion de coneccion
 * al servidor de datos, bien sea remoto o local.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 * 
 */
public class AdminConector {

    private static AdminConector instance;
    private static ConfigConector conector;
    private static ConfigConectorDAO conectorAPI;
    
    private ResultSet result;
    private Statement sentence;
    private static Connection SGBD;
    
    private int stateOP;
    private int countConection;
    private boolean execute;
    private boolean stateConection;

    /**
     * este constructor privado permite que solo se cree una instancia de AdminConector, recibe como parametros 
     * el objeto ConfigConector desde donde se obtendrán los parametros de configuracion para la coneccion con la
     * fuente de datos.
     * 
     * @param conector 
     */
    private AdminConector(ConfigConector conector) {
        AdminConector.conector = conector;
    }

    /**
     * este metodo permite conectar el sistema grafico con la fuente de datos MySql, esta coneccion solo pued
     * realizarse una sola vez desde un frontend por lo cual se establece un control de conecciones de 
     * AdminConector, este control esta dado por el valor Int coneccion si coneccion es mayo que cero dice
     * que actualmente ya esta abierta una coneccion por lo cual no dejará implementar una nueva.
     * 
     * @return boolean estadoConeccion
     * 
     * @throws SQLException 
     */
    public boolean conect() throws SQLException {
        try {
            if (countConection == 0) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                SGBD = (Connection) DriverManager.getConnection(conector.getURL(), conector.getUSUARIO(), conector.getCONTRASEÑA());
                sentence = (Statement) SGBD.createStatement();
                stateConection = true;
                countConection ++;
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe una conección activa con el sistema. no se puede conectar", GlobalConfigSystem.getAplicationTitle(), 0, new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPositive.png")));
            }
        } catch (ClassNotFoundException ce) {
            JOptionPane.showMessageDialog(null, "El controlador JDBC no ha sido encontrado, por favor verique que todas las librerias estan completas.", GlobalConfigSystem.getAplicationTitle(), 0);
            execute = false;
        } catch (CommunicationsException Cex) {
            JOptionPane.showMessageDialog(null, "No se puede conectar con el servidor central de datos:\n\nHost Central Server: \n\nVerifique su conexion a la red Local\nVerifique que el que el servidor este activo y funcionando.\n\nEn caso de no poder conectarse por favor informe a Ingeniería\ny Reporte su problema.\nNo se encuentra el servidor:\n",GlobalConfigSystem.getAplicationTitle(),JOptionPane.ERROR_MESSAGE,new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconError.png")));
            System.exit(0);
        } catch (InstantiationException ex) {
            Logger.getLogger(AdminConector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AdminConector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stateConection;
    }

    /**
     * Este metodo permite cerrar la coneccion activa con la fuente de datos, ademas de poner el valor de la
     * variable estadoConeccion=false y coneccion=0;
     * 
     * @return 
     */
    public boolean disconect() {
        try {
            result.close();
            sentence.close();
            SGBD.close();
            stateConection = false;
            countConection = 0;
            execute = true;
        } catch (SQLException ex) {
            Logger.getLogger(AdminConector.class.getName()).log(Level.SEVERE, null, ex);
            execute = false;
        }
        return execute;
    }

    /**
     * Este metodo permite ejecutar una sentencia reciba desde 
     * @param sqlSentence
     * @return
     * @throws SQLException 
     */
    public int runSentence(String sqlSentence) throws SQLException {
        stateOP = -1;
            if (isConected()) {
                    execute = sentence.execute(sqlSentence);
                        if (!execute) {
                            stateOP = 0;
                            sentence.execute("commit;");
                        }
            } else {
                JOptionPane.showMessageDialog(null, "No hay coneción con la fuente de datos, verifique el estado del servidor e intente de nuevo", GlobalConfigSystem.getAplicationTitle(),JOptionPane.ERROR_MESSAGE,new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconError.png")));
            }
        return stateOP;
    }

    /**
     * este metodo permite consultar datos en la fuente de datos Mysql de NiconEnterprise, al consultar estos 
     * datos son almacenados dentro de un <b> ResultSet</b> que es retornado con toda la informacion de la consul
     * ta.
     * 
     * @param sql
     * @return result
     * @throws SQLException 
     */
    public ResultSet queryData(String sql) throws SQLException {
        if (isConected()) {
            result = (ResultSet) sentence.executeQuery(sql);
        } else {
            JOptionPane.showMessageDialog(null, "No hay coneción con la fuente de datos, verifique el estado del servidor e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0);
        }
        return result;
    }

    /**
     * este metodo se encarga de servir de interface de incializacion del sistema adminConector en el patron
     * singelton, retorna una instancia de AdminConector con la cual el usuario podrá interactuar posteriormente
     * en caso de que ya haya una instancia corriendo pues solo retorna la misma, en caso contrario lo que hace
     * es crear una instancia para poder retornarla al sistema.
     * 
     * @return AdminConector instance
     */
    public static AdminConector getInstance() {
        if (instance == null) {
            instance = new AdminConector(loadConfigConector());
        }
        return instance;
    }

    /**
     * retorna un objeo de Coneccion con la fuente de datos Connection.
     * 
     * @return Connection SGBD
     */
    public Connection getConnectionSGBD() {
        return SGBD;
    }

    /**
     * este metodo permite cargar el archivo de configuracion de coneccion  <b> ConfigConector </b>, el objeto
     * ConfigConector define los parametros de configuracion de coneccion entre el FrontEnd de NiconEnterprise
     * y el backend de la fuente de datos, sin este archivo de coneccion es practicamente imposible poder conectar
     * el sistema a la fuente de datos.
     * 
     * @return ConfigConector
     */
    private static ConfigConector loadConfigConector() {
        try {
            conectorAPI = new ConfigConectorDAO();
                if (conectorAPI.verifyConfigFile()) {
                        conector = conectorAPI.loadConfig();
                } else {
                        conectorAPI.createConfigDefault();
                        conector = conectorAPI.loadConfig();
                }
        } catch (Exception e) {
            System.err.println("Ocurrio un error en Conection.cargarConfigConector():\n" + e);
        }
        return conector;
    }

    /**
     * este metodo retorna el valor booleano que representa el estado de la coneccion con la fuente de datos 
     * Mysql, true en caso de estar conectado y false en caso de estar desconectado.
     * 
     * @return boolean stateConection
     */
    public boolean isConected() {
        return stateConection;
    }
}