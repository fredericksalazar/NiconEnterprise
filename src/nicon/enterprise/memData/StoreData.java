/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.memData;

import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import nicon.enterprise.libCore.AdminConector;
import nicon.enterprise.libCore.api.obj.Almacen;

public class StoreData {

    private static ArrayList ListStore;
    private static String sentence;
    private static ResultSet DataSentence;
    private static Almacen Store;
    private static AdminConector coneccion;

    public static void storeInit() {
        try {
            coneccion = AdminConector.getInstance();
            ListStore = new ArrayList();
            sentence = "select * from Almacenes;";
            DataSentence = coneccion.queryData(sentence);

            while (DataSentence.next()) {
                Store = new Almacen(DataSentence.getInt("idAlmacenes"), DataSentence.getString("Nombre"), DataSentence.getString("direccion"), DataSentence.getString("barrio"), DataSentence.getString("ciudad"), DataSentence.getString("Departamento"), DataSentence.getString("telefono_fijo"), DataSentence.getString("email"));
                ListStore.add(Store);
            }
            DataSentence.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un Error al ejecutar el proceso: storeInit() detalles del error:\n" + e.getStackTrace());
            e.printStackTrace();
        }
    }

    public static void addStore(Almacen almacen) {
        ListStore.add(almacen);
        almacen.createStore();
    }

    public static ArrayList cloneStoreData() {
        return ListStore;
    }

    public static Almacen getStore(int CodeStore) {
        return Store;
    }
}