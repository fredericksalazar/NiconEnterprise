/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.memData;

import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import nicon.enterprise.libCore.Conection;

public class BasicDataAplication {

    private static String sentence;
    private static ResultSet DataSentence;
    private static ArrayList ListData;
    private static int counter;
    private static String[] VectorCiudades;
    private static String[] VectorDpto;
    private static int init;
    private static Conection coneccion;

    public BasicDataAplication() {
        ListData = new ArrayList();
        counter = 0;
        init = 0;
        coneccion = Conection.obtenerInstancia();
    }

    public static String[] getListCity() {
        try {
            if (counter == 0) {
                sentence = "select Distinct ciudad from Clientes";
                DataSentence = coneccion.consultarDatos(sentence);

                while (DataSentence.next()) {
                    ListData.add(DataSentence.getString("ciudad"));
                }
                VectorCiudades = new String[ListData.size()];
                VectorCiudades = (String[]) (String[]) ListData.toArray(VectorCiudades);
                DataSentence.close();
                ListData.clear();
                counter += 1;
            }
        } catch (Exception ex) {
            Logger.getLogger(BasicDataAplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return VectorCiudades;
    }

    public static String[] getListDepartament() {
        try {
            if (init == 0) {
                sentence = "select distinct Departamento from Clientes";
                DataSentence = coneccion.consultarDatos(sentence);

                while (DataSentence.next()) {
                    ListData.add(DataSentence.getString("Departamento"));
                }
                VectorDpto = new String[ListData.size()];
                VectorDpto = (String[]) (String[]) ListData.toArray(VectorDpto);
                DataSentence.close();
                init += 1;
            }
        } catch (Exception e) {
            System.out.println("Error en getListDepartament() detail error:\n" + e);
        }
        return VectorDpto;
    }

    public static int searchCityVector(String city) {
        int index = -1;
        for (int i = 0; i < VectorCiudades.length; i++) {
            if (VectorCiudades[i].contains(city)) {
                index = i;
                break;
            }
        }
        return index;
    }
}