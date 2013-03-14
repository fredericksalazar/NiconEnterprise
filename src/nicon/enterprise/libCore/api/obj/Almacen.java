/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.libCore.api.obj;

import com.mysql.jdbc.ResultSet;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import nicon.enterprise.libCore.api.util.AdminConector;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.EmpresaDAO;

/**
 * la clase Almacen define el modelo de datos que representan el objeto de tipo
 * almacen de una empresa, esta clase almacen será usada por el APi de acceso
 * a datos del AlmacenDAO, 
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class Almacen {

    private int CodeStore;
    
    private String Nombre;
    private String Direccion;
    private String Barrio;
    private String Ciudad;
    private String Provincia;
    private String Telefono_fijo;
    private String email;
    
    private Almacen Almacen;
    private EmpresaDAO empresaDAO;
    private AdminConector coneccion;
    private String sentence;
    private ResultSet Data;
    private int counter;
    private int ExecuteSentence;

    public Almacen(String Nombre, String Direccion, String Barrio, String Ciudad, String Provincia, String Telefono_fijo, String email) {
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Barrio = Barrio;
        this.Ciudad = Ciudad;
        this.Provincia = Provincia;
        this.Telefono_fijo = Telefono_fijo;
        this.email = email;
        this.coneccion = AdminConector.getInstance();
    }

    public Almacen(int CodeStore, String Nombre, String Direccion, String Barrio, String Ciudad, String Provincia, String Telefono_fijo, String email) {
        this.CodeStore = CodeStore;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Barrio = Barrio;
        this.Ciudad = Ciudad;
        this.Provincia = Provincia;
        this.Telefono_fijo = Telefono_fijo;
        this.email = email;
        this.coneccion = AdminConector.getInstance();
    }

    public Almacen(int CodeStore, String Name) {
        this.CodeStore = CodeStore;
        this.Nombre = Name;
        this.coneccion = AdminConector.getInstance();
    }

    public Almacen(Almacen almacen) {
        this.Almacen = almacen;
    }

    public Almacen() {
    }

    public void setBarrio(String Barrio) {
        this.Barrio = Barrio;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }

    public void setTelefono_fijo(String Telefono_fijo) {
        this.Telefono_fijo = Telefono_fijo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCodeStore(int CodeStore) {
        this.CodeStore = CodeStore;
    }

    public String getBarrio() {
        return this.Barrio;
    }

    public String getCiudad() {
        return this.Ciudad;
    }

    public String getDireccion() {
        return this.Direccion;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public String getProvincia() {
        return this.Provincia;
    }

    public String getTelefono_fijo() {
        return this.Telefono_fijo;
    }

    public String getEmail() {
        return this.email;
    }

    public int getCodeStore() {
        return this.CodeStore;
    }

    public void createStore() {
        try {
            empresaDAO = new EmpresaDAO();
            sentence = ("Insert Into Almacenes (Nombre,direccion,barrio,ciudad,Departamento,telefono_fijo,email,fecha_registro,Empresa_Nit) Values ('" + getNombre() + "','" + getDireccion() + "','" + getBarrio() + "','" + getCiudad() + "','" + getProvincia() + "','" + getTelefono_fijo() + "','" + getEmail() + "',current_timestamp,'" +empresaDAO.obtenerNit() + "');");
            ExecuteSentence = coneccion.runSentence(sentence);

             if (ExecuteSentence == 0) {
                JOptionPane.showMessageDialog(null, "El almacen ha sido creado detalles:\n\n" + toString());
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un Error y no se pudo crear el almacen.", GlobalConfigSystem.getAplicationTitle(), 0);
            }
        } catch (Exception e) {
            System.out.println("Un Error ocurrió Strore.createStore() detail error:\n" + e);
        }
    }

    public void updateStore(String campo, String Data, int Code) {
        try {
            sentence = ("Update Almacenes set " + campo + " ='" + Data + "' where idAlmacenes=" + Code + ";");
            ExecuteSentence = coneccion.runSentence(sentence);
            if (ExecuteSentence == 0) {
                JOptionPane.showMessageDialog(null, "La actualizacion de datos ha sido exitosa");
            } else {
                JOptionPane.showMessageDialog(null, "La actualizacion de datos no se efectuo Ocurrio un ERROR. Intente de nuevo");
            }
        } catch (Exception e) {
            System.err.println("A Error ocurred when trying UpdateDataStore() detail Error" + e);
        }
    }

    public Almacen getDataStore(int code) {
        sentence = ("select * from Almacenes where Codigo_almacen=" + code + ";");
        Almacen store = new Almacen();
        try {
            Data = coneccion.queryData(sentence);
            Data.next();
            store.setNombre(Data.getString(1));
            store.setDireccion(Data.getString(2));
            store.setBarrio(Data.getString(3));
            store.setCiudad(Data.getString(4));
            store.setProvincia(Data.getString(5));
            store.setTelefono_fijo(Data.getString(6));
            store.setEmail(Data.getString(7));
            Data.close();
            coneccion.disconect();
        } catch (Exception e) {
            System.err.println("A Error ocurred when trying Store.getDataStore() detail Error" + e);
        }
        return store;
    }

    public Almacen[] getDataBasicStore() {
        Almacen[] basicData = new Almacen[10];
        counter = 0;
        try {
            sentence = "select idAlmacenes, Nombre from Almacenes;";
            Data = coneccion.queryData(sentence);

            while (Data.next()) {
                Almacen = new Almacen(Data.getInt("idAlmacen"), Data.getString("Nombre"));
                basicData[counter] = Almacen;
                counter += 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return basicData;
    }

    public int obtenerTotalRegistros() {
        try {
            sentence = "select count(idAlmacenes) from Almacenes";
            Data = coneccion.queryData(sentence);
            if (Data.next()) {
                counter = Data.getInt(1);
            } else {
                counter = 0;
            }
        } catch (Exception e) {
        }
        return this.counter;
    }

    @Override
    public String toString() {
        return "Store Information:\nNombre=" + this.Nombre + "\nDireccion=" + this.Direccion + "\nBarrio=" + this.Barrio + "\nCiudad=" + this.Ciudad + "\nProvincia=" + this.Provincia + "\nTelefono_fijo=" + this.Telefono_fijo + "\nemail=" + this.email;
    }
}