/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore;

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
import nicon.enterprise.libCore.dao.ConfigConectorDAO;
import nicon.enterprise.libCore.obj.ConfigConector;

public class Conection {

    private static Conection instancia;
    private static ConfigConector conector;
    private static ConfigConectorDAO configDAO;
    
    private ResultSet Data;
    private Statement Execution;
    private static Connection SGBD;
    
    private int stateOP;
    private int coneccion;
    private boolean execute;
    private boolean estadoConeccion;

    private Conection(ConfigConector conector) {
        Conection.conector = conector;
    }

    public boolean conectar()
            throws SQLException {
        try {
            if (this.coneccion == 0) {
                Class.forName("com.mysql.jdbc.Driver");
                SGBD = (Connection) DriverManager.getConnection(conector.getURL(), conector.getUSUARIO(), conector.getCONTRASEÑA());
                this.Execution = (Statement) SGBD.createStatement();
                this.estadoConeccion = true;
                this.coneccion += 1;
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe una conección activa con el sistema. no se puede conectar", GlobalConfigSystem.getAplicationTitle(), 0, new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPossitive.png")));
            }
        } catch (ClassNotFoundException ce) {
            JOptionPane.showMessageDialog(null, "El controlador JDBC no ha sido encontrado, por favor verique que todas las librerias estan completas.", GlobalConfigSystem.getAplicationTitle(), 0);
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ce);
            this.execute = false;
        } catch (CommunicationsException Cex) {
            JOptionPane.showMessageDialog(null, "No se puede conectar con el servidor central de datos:\n\nHost Central Server: \n\nVerifique su conexion a la red Local\nVerifique que el que el servidor este activo y funcionando.\n\nEn caso de no poder conectarse por favor informe a Ingeniería\ny Reporte su problema.\nNo se encuentra el servidor:\n" + Cex.getMessage(), "NiconEnterprise Error de Conexión", 0);

            System.out.println("Ocurrio un error en la conexion a  Detail error:\n" + Cex);
            System.exit(0);
        }
        return this.estadoConeccion;
    }

    public boolean desconectar() {
        try {
            System.out.println("Desconectando del servidor: ");
            this.Data.close();
            this.Execution.close();
            SGBD.close();
            this.estadoConeccion = false;
            this.coneccion = 0;
            this.execute = true;
            System.out.println("La conexión se cerró exitosamente ...");
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
            this.execute = false;
        }
        return this.execute;
    }

    public int ejecutarSentencia(String sql)
            throws SQLException {
        this.stateOP = -1;
        if (verificarEstado()) {
            this.execute = this.Execution.execute(sql);
            if (!this.execute) {
                this.stateOP = 0;
                this.Execution.execute("commit;");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay coneción con la fuente de datos, verifique el estado del servidor e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0);
        }

        return this.stateOP;
    }

    public ResultSet consultarDatos(String sql)
            throws SQLException {
        if (verificarEstado()) {
            Data = (ResultSet) Execution.executeQuery(sql);
        } else {
            JOptionPane.showMessageDialog(null, "No hay coneción con la fuente de datos, verifique el estado del servidor e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0);
        }
        return this.Data;
    }

    public static Conection obtenerInstancia() {
        if (instancia == null) {
            instancia = new Conection(cargarConfigConector());
        }
        return instancia;
    }

    public Connection obtenerConeccion() {
        return SGBD;
    }

    private static ConfigConector cargarConfigConector() {
        try {
            configDAO = new ConfigConectorDAO();
            if (configDAO.verifyConfigFile()) {
                conector = configDAO.loadConfig();
            } else {
                configDAO.createConfigDefault();
                conector = configDAO.loadConfig();
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un error en Conection.cargarConfigConector():\n" + e);
        }
        return conector;
    }

    public boolean verificarEstado() {
        return this.estadoConeccion;
    }
}