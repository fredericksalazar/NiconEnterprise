/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nicon.enterprise.memData.BasicDataAplication;
import nicon.enterprise.memData.StoreData;

public class Init {

    private static BasicDataAplication basicData;
    private static GlobalConfigSystem ConfigGlobal;
    private static Conection coneccion;

    public static void init_Process() {
        try {
            System.out.println("Starting init().");
            basicData = new BasicDataAplication();
            ConfigGlobal = new GlobalConfigSystem();
            coneccion = Conection.obtenerInstancia();
            coneccion.conectar();
            StoreData.storeInit();
        } catch (SQLException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}