/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.dao;

import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import nicon.enterprise.libCore.Conection;
import nicon.enterprise.libCore.NiconAdminReport;
import nicon.enterprise.libCore.obj.Cliente;

public class ClienteDAO {

    private Cliente cliente;
    private boolean state = false;
    private String sentencia;
    private int ExecuteSentence;
    private int contador;
    private ArrayList listaClientes;
    private ResultSet datosConsulta;
    private String impresion;
    private Conection coneccion;
    private NiconAdminReport adminReport;
    private JasperPrint reporte;

    public ClienteDAO(Cliente cliente) {
        this.cliente = cliente;
        this.coneccion = Conection.obtenerInstancia();
    }

    public ClienteDAO() {
        this.coneccion = Conection.obtenerInstancia();
    }

    public boolean crearCliente()
            throws SQLException {
        if (this.cliente != null) {
            this.sentencia = ("Insert Into Clientes Values(" + this.cliente.getIdentificacion() + ",'" + this.cliente.getNombres() + "','" + this.cliente.getApellidos() + "','" + this.cliente.getCiudad() + "','" + this.cliente.getDireccion() + "','" + this.cliente.getProvincia() + "','" + this.cliente.getTelefono_fijo() + "','" + this.cliente.getTelefono_movil() + "','" + this.cliente.getTelefono_alternativo() + "','" + this.cliente.getEmail() + "',current_timestamp," + this.cliente.getCodigoAlmacen() + ");");
            this.ExecuteSentence = this.coneccion.ejecutarSentencia(this.sentencia);
            if (this.ExecuteSentence == 0) {
                this.state = true;
                this.cliente = null;
            } else {
                this.state = false;
            }
        } else {
            this.state = false;
        }
        return this.state;
    }

    public boolean eliminarCliente()
            throws SQLException {
        this.sentencia = ("delete from Clientes where identificacion=" + this.cliente.getIdentificacion() + ";");
        this.ExecuteSentence = this.coneccion.ejecutarSentencia(this.sentencia);
        if (this.ExecuteSentence == 0) {
            this.state = true;
        } else {
            this.state = false;
        }

        return this.state;
    }

    public boolean actualizarIdentificacion(String oldID, String NewID) {
        try {
            this.sentencia = ("update Clientes set identificacion = " + NewID + " where identificacion=" + oldID + ";");
            this.ExecuteSentence = this.coneccion.ejecutarSentencia(this.sentencia);

            if (this.ExecuteSentence == 0) {
                this.state = true;
            } else {
                this.state = false;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en Cliente.updateIdentificationClient() detail:\n" + e);
        }
        return this.state;
    }

    public boolean actualizarDatoCliente(String cedula, String campo, String dato) {
        try {
            this.sentencia = ("update Clientes set " + campo + " = '" + dato + "' where identificacion=" + cedula + ";");
            this.ExecuteSentence = this.coneccion.ejecutarSentencia(this.sentencia);
            if (this.ExecuteSentence == 0) {
                this.state = true;
            } else {
                this.state = false;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en Cliente.updateDataClient() \n:" + e);
        }
        return this.state;
    }

    public ArrayList listarClientes()
            throws SQLException {
        this.listaClientes = new ArrayList();
        this.sentencia = "select * from Clientes;";
        this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
        while (this.datosConsulta.next()) {
            this.cliente = new Cliente(this.datosConsulta.getString("identificacion"), this.datosConsulta.getString("nombres"), this.datosConsulta.getString("apellidos"), this.datosConsulta.getString("ciudad"), this.datosConsulta.getString("direccion"), this.datosConsulta.getString("Departamento"), this.datosConsulta.getString("telefono_fijo"), this.datosConsulta.getString("telefono_movil"), this.datosConsulta.getString("telefono_alternativo"), this.datosConsulta.getString("email"), String.valueOf(this.datosConsulta.getDate("fecha_registro")), this.datosConsulta.getInt("Almacenes_idAlmacenes"));
            this.listaClientes.add(this.cliente);
        }
        this.datosConsulta.close();
        return this.listaClientes;
    }

    public ArrayList listarClientesOrdenadosPorNombre(String opcion) {
        try {
            this.listaClientes = new ArrayList();
            if (opcion.equals("asc")) {
                this.sentencia = "select * from Clientes Order By nombres asc;";
            }
            if (opcion.equals("desc")) {
                this.sentencia = "select * from Clientes Order By nombres Desc;";
            }
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            while (this.datosConsulta.next()) {
                this.cliente = new Cliente(this.datosConsulta.getString("identificacion"), this.datosConsulta.getString("nombres"), this.datosConsulta.getString("apellidos"), this.datosConsulta.getString("ciudad"), this.datosConsulta.getString("direccion"), this.datosConsulta.getString("Departamento"), this.datosConsulta.getString("telefono_fijo"), this.datosConsulta.getString("telefono_movil"), this.datosConsulta.getString("telefono_alternativo"), this.datosConsulta.getString("email"), String.valueOf(this.datosConsulta.getDate("fecha_registro")), this.datosConsulta.getInt("Almacenes_idAlmacenes"));
                this.listaClientes.add(this.cliente);
            }
            this.datosConsulta.close();
        } catch (Exception e) {
            System.err.println("Ocurrio un error al obtener listado de clientes ordenados (API ClienteDAO):\n" + e.getMessage());
        }
        return this.listaClientes;
    }

    public ArrayList listarClientesPorCiudad(String ciudad) {
        try {
            this.listaClientes = new ArrayList();
            this.sentencia = ("select * from Clientes where ciudad='" + ciudad + "';");
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            while (this.datosConsulta.next()) {
                this.cliente = new Cliente(this.datosConsulta.getString("identificacion"), this.datosConsulta.getString("nombres"), this.datosConsulta.getString("apellidos"), this.datosConsulta.getString("ciudad"), this.datosConsulta.getString("direccion"), this.datosConsulta.getString("Departamento"), this.datosConsulta.getString("telefono_fijo"), this.datosConsulta.getString("telefono_movil"), this.datosConsulta.getString("telefono_alternativo"), this.datosConsulta.getString("email"), String.valueOf(this.datosConsulta.getDate("fecha_registro")), this.datosConsulta.getInt("Almacenes_idAlmacenes"));
                this.listaClientes.add(this.cliente);
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un error al obtener el listado de clientes (API ClienteDAO):\n" + e.getMessage());
        }
        return this.listaClientes;
    }

    public Cliente buscarPorIdentificacion(String identificacion) {
        try {
            this.sentencia = ("select * from Clientes where identificacion=" + identificacion + ";");
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            if (this.datosConsulta.next()) {
                this.cliente = new Cliente(this.datosConsulta.getString(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3), this.datosConsulta.getString(4), this.datosConsulta.getString(5), this.datosConsulta.getString(6), this.datosConsulta.getString(7), this.datosConsulta.getString(8), this.datosConsulta.getString(9), this.datosConsulta.getString(10), this.datosConsulta.getInt(11));
            } else {
                this.cliente = null;
            }
        } catch (Exception e) {
            System.err.println(getClass().getPackage() + getClass().getName() + "Ocurrio un error buscando datos del cliente con ID: " + identificacion);
        }
        return this.cliente;
    }

    public Cliente buscarPorNombres(String Nombres) {
        try {
            this.sentencia = ("select distinct * from Clientes where nombres='" + Nombres + "';");
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            if (this.datosConsulta.next()) {
                this.cliente = new Cliente(this.datosConsulta.getString(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3), this.datosConsulta.getString(4), this.datosConsulta.getString(5), this.datosConsulta.getString(6), this.datosConsulta.getString(7), this.datosConsulta.getString(8), this.datosConsulta.getString(9), this.datosConsulta.getString(10), this.datosConsulta.getInt(11));
            } else {
                this.cliente = null;
            }
            this.datosConsulta.close();
        } catch (Exception e) {
            System.err.println(getClass().getPackage() + getClass().getName() + "Ocurrio un error buscando datos del cliente con Nombres: " + Nombres);
        }
        return this.cliente;
    }

    public Cliente validarCliente(String nombres, String apellidos) {
        try {
            this.sentencia = ("select distinct * from Clientes where nombres='" + nombres + "' and apellidos='" + apellidos + "';");
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            if (this.datosConsulta.next()) {
                this.cliente = new Cliente(this.datosConsulta.getString(1), this.datosConsulta.getString(2), this.datosConsulta.getString(3), this.datosConsulta.getString(4), this.datosConsulta.getString(5), this.datosConsulta.getString(6), this.datosConsulta.getString(7), this.datosConsulta.getString(8), this.datosConsulta.getString(9), this.datosConsulta.getString(10), this.datosConsulta.getInt(11));
            } else {
                this.cliente = null;
            }
            this.datosConsulta.close();
        } catch (SQLException e) {
            System.err.println("ocurrio un error en ClienteDAO.validarCliente():\n" + e);
        }
        return this.cliente;
    }

    public int obtenerTotalRegistros() {
        try {
            this.sentencia = "select count(identificacion) from Clientes;";
            this.datosConsulta = this.coneccion.consultarDatos(this.sentencia);
            if (this.datosConsulta.next()) {
                this.contador = this.datosConsulta.getInt(1);
            } else {
                this.contador = 0;
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un error en ClienteDAO.obtenerTotalRegistros():\n" + e);
        }
        return this.contador;
    }

    public void exportarTodosPDF()
        throws JRException {
        this.adminReport = new NiconAdminReport();
        this.reporte = this.adminReport.compilarReporte("/Nicon/Enterprise/LibCore/rsc/ListaClientes.jasper");
        this.adminReport.verReporte(this.reporte);
    }

    public void imprimirTodos() {
        try {
            this.listaClientes = listarClientes();
            for (int i = 0; i < this.listaClientes.size(); i++) {
                this.cliente = ((Cliente) this.listaClientes.get(i));
                this.impresion = (this.impresion + this.cliente.toString() + "\n");
            }
            System.out.println(this.impresion);
        } catch (Exception e) {
        }
    }
}