/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.libCore;

import java.sql.SQLException;
import nicon.enterprise.memData.BasicDataAplication;
import nicon.enterprise.memData.StoreData;

/**
 * Init se encarga de inicializar servicios, procesos y fuentes de informacion basicos para el uso en todo el sistema
 * esta clase solo puede ser accedida 1 ves desde el sistema.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 * @version 0.2
 */
public class Init {

    private static BasicDataAplication basicData;
    private static GlobalConfigSystem ConfigGlobal;
    private static Conection coneccion;
    private static int acces=0;

    /**
     * Este metodo estatico permite arrancar los procesos y servicios basicos de operacion del sistema solo puede ser
     * accedido 1 sola vez para no causar redundancia de datos en memoria.
     * 
     * @author Frederick Adolfo Salazar Sanchez.
     * @exception SQLException causada por un problema con el gestor de coneccion a la base de datos.
     */
    public static void init_Process() throws SQLException {
       
            if(acces==0){
                basicData = new BasicDataAplication();
                ConfigGlobal = new GlobalConfigSystem();
                coneccion = Conection.obtenerInstancia();
                coneccion.conectar();
                StoreData.storeInit();
                acces++;
            }       
    }
}