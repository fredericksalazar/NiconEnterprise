/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.dao;

import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import nicon.enterprise.libCore.api.util.AdminConector;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.util.NiconAdminReport;
import nicon.enterprise.libCore.obj.Proveedor;

public class ProveedorDAO {

    private Proveedor proveedor;
    private String sentencia;
    private ArrayList listaProveedores;
    private ArrayList listaCiudades;
    private ResultSet datosConsulta;
    private boolean stateOP;
    private int response;
    private AdminConector coneccion;
    private NiconAdminReport adminReport;
    private JasperPrint reporte;

    public ProveedorDAO(Proveedor proveedor) {
        this.proveedor = proveedor;
        this.coneccion = AdminConector.getInstance();
    }

    public ProveedorDAO() {
        this.proveedor = null;
        this.coneccion = AdminConector.getInstance();
    }

    public boolean crearProveedor() {
        System.out.println("Iniciando creación de nuevo Proveedor, verificando Objeto ...");
        try {
            if (this.proveedor != null) {
                this.sentencia = ("Insert Into Proveedores values('" + this.proveedor.getNit() + "','" + this.proveedor.getRazonSocial() + "','" + this.proveedor.getDireccion() + "','" + this.proveedor.getCiudad() + "','" + this.proveedor.getTelefonoFijo() + "','" + this.proveedor.getTelefonoMovil() + "','" + this.proveedor.getFax() + "','" + this.proveedor.getEmail() + "','" + this.proveedor.getWebPage() + "','" + this.proveedor.getBanco() + "','" + this.proveedor.getNumeroCuenta() + "','" + this.proveedor.getDescripcion() + "',1);");
                this.response = this.coneccion.runSentence(this.sentencia);
                if (this.response == 0) {
                    System.out.println("El objeto proveedor ha sido creado exitosamente ...");
                    this.stateOP = true;
                } else {
                    System.out.println("El objeto proveedor no pudo ser creado, un error ocurrio al ejecutar la sentencia ...");
                    this.stateOP = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El Objeto Proveedor es null, por favor verifique e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0);
            }
        } catch (Exception e) {
            System.err.println("Ocurrió el siguiente error en ProveedorDAO.crearProveedor():\n" + e);
            this.stateOP = false;
        }
        return this.stateOP;
    }

    public boolean eliminarProveedor() {
        System.out.println("Iniciando la eliminación del proveedor : " + this.proveedor.getRazonSocial());
        if (this.proveedor != null) {
            try {
                this.sentencia = ("DELETE FROM Proveedores WHERE Nit='" + this.proveedor.getNit() + "';");
                this.response = this.coneccion.runSentence(this.sentencia);
                if (this.response == 0) {
                    System.out.println("El proveedor  fue eliminado correctamente ...");
                    this.stateOP = true;
                } else {
                    this.stateOP = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El Objeto Proveedor es null, por favor verifique e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0);
        }
        return this.stateOP;
    }

    public boolean actualizarProveedor(String Nit, String Campo, String dato) {
        try {
            System.out.println("Iniciando actualización de Proveedor");
            this.sentencia = ("UPDATE Proveedores SET " + Campo + " ='" + dato + "' where Nit='" + Nit + "';");
            this.response = this.coneccion.runSentence(this.sentencia);
            if (this.response == 0) {
                this.stateOP = true;
            } else {
                this.stateOP = false;
            }
        } catch (Exception e) {
            System.err.println("Ocurrio el siguiente error en ProveedorDAO.actualizarProveedor():\n" + e);
        }
        return this.stateOP;
    }

    public ArrayList listarProveedores() {
        try {
            this.listaProveedores = new ArrayList();
            this.sentencia = "select * from Proveedores;";
            this.datosConsulta = ((ResultSet) this.coneccion.queryData(this.sentencia));
            while (this.datosConsulta.next()) {
                this.proveedor = new Proveedor(this.datosConsulta.getString(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3), this.datosConsulta.getString(4), this.datosConsulta.getString(5), this.datosConsulta.getString(6), this.datosConsulta.getString(7), this.datosConsulta.getString(8), this.datosConsulta.getString(9), this.datosConsulta.getString(10), this.datosConsulta.getString(11), this.datosConsulta.getString(12));
                this.listaProveedores.add(this.proveedor);
            }
            this.datosConsulta.close();
        } catch (Exception e) {
            System.err.println("Un error ocurrió cuando se intentaba obtener la lista de Proveedores:\n" + e);
        }
        return this.listaProveedores;
    }

    public ArrayList listarProveedoresOrdenados(String opcion) {
        try {
            if (opcion.equals("asc")) {
                this.sentencia = "select * from Proveedores Order By Razon_social asc;";
            }
            if (opcion.equals("desc")) {
                this.sentencia = "select * from Proveedores order by Razon_social desc;";
            }
            this.datosConsulta = ((ResultSet) this.coneccion.queryData(this.sentencia));
            while (this.datosConsulta.next()) {
                this.proveedor = new Proveedor(this.datosConsulta.getString(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3), this.datosConsulta.getString(4), this.datosConsulta.getString(5), this.datosConsulta.getString(6), this.datosConsulta.getString(7), this.datosConsulta.getString(8), this.datosConsulta.getString(9), this.datosConsulta.getString(10), this.datosConsulta.getString(11), this.datosConsulta.getString(12));
                this.listaProveedores.add(this.proveedor);
            }
            this.datosConsulta.close();
        } catch (Exception e) {
            System.err.println("Ocurrio un error en ProveedorDAO.listarProveeddoresOrdenados():\n" + e.getMessage());
        }
        return this.listaProveedores;
    }

    public ArrayList listarProveedoresPorCiudad(String ciudad) {
        try {
            this.sentencia = ("select * from Proveedores where ciudad='" + ciudad + "';");
            this.datosConsulta = ((ResultSet) this.coneccion.queryData(this.sentencia));
            while (this.datosConsulta.next()) {
                this.proveedor = new Proveedor(this.datosConsulta.getString(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3), this.datosConsulta.getString(4), this.datosConsulta.getString(5), this.datosConsulta.getString(6), this.datosConsulta.getString(7), this.datosConsulta.getString(8), this.datosConsulta.getString(9), this.datosConsulta.getString(10), this.datosConsulta.getString(11), this.datosConsulta.getString(12));
                this.listaProveedores.add(this.proveedor);
            }
            this.datosConsulta.close();
        } catch (Exception e) {
        }
        return this.listaProveedores;
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

    public Proveedor buscarProveedorPorNit(String Nit) {
        try {
            this.sentencia = ("SELECT * FROM Proveedores where Nit='" + Nit + "';");
            this.datosConsulta = ((ResultSet) this.coneccion.queryData(this.sentencia));
            if (this.datosConsulta.next()) {
                this.proveedor = new Proveedor(this.datosConsulta.getString(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3), this.datosConsulta.getString(4), this.datosConsulta.getString(5), this.datosConsulta.getString(6), this.datosConsulta.getString(7), this.datosConsulta.getString(8), this.datosConsulta.getString(9), this.datosConsulta.getString(10), this.datosConsulta.getString(11), this.datosConsulta.getString(12));
            } else {
                this.proveedor = null;
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un mientras se intentaba buscar Información de un proveedor (ProveedorDAO.buscarProveedorProNit(String NIt):\n" + e);
        }
        return this.proveedor;
    }

    public Proveedor buscarProveedorPorRazonSocial(String razonSocial) {
        try {
            this.sentencia = ("SELECT * FROM Proveedores where Razon_social='" + razonSocial + "';");
            this.datosConsulta = ((ResultSet) this.coneccion.queryData(this.sentencia));
            if (this.datosConsulta.next()) {
                this.proveedor = new Proveedor(this.datosConsulta.getString(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3), this.datosConsulta.getString(4), this.datosConsulta.getString(5), this.datosConsulta.getString(6), this.datosConsulta.getString(7), this.datosConsulta.getString(8), this.datosConsulta.getString(9), this.datosConsulta.getString(10), this.datosConsulta.getString(11), this.datosConsulta.getString(12));
            } else {
                this.proveedor = null;
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un mientras se intentaba buscar Información de un proveedor (ProveedorDAO.buscarProveedorPorRazonSocial(String razonSocial):\n" + e);
        }
        return this.proveedor;
    }

    public void exportarTodosPDF()
            throws JRException {
        this.adminReport = new NiconAdminReport();
        this.reporte = this.adminReport.compilarReporte("/Nicon/Enterprise/LibCore/rsc/ListaProveedores.jasper");
        this.adminReport.verReporte(this.reporte);
    }

    private void limpiarInterfaz() {
        this.datosConsulta = null;
        this.listaProveedores = null;
        this.proveedor = null;
        this.response = 0;
        this.sentencia = null;
    }
}
