/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.dao;

import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import nicon.enterprise.libCore.Conection;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.obj.Empresa;


public class EmpresaDAO
{
  private Empresa empresa;
  private String sentencia;
  private ResultSet datosEmpresa;
  private int ejecucion;
  private boolean stateOP;
  private Conection coneccion;

  public EmpresaDAO()
  {
    this.coneccion = Conection.obtenerInstancia();
  }

  public EmpresaDAO(Empresa empresa)
  {
    this.empresa = empresa;
    this.coneccion = Conection.obtenerInstancia();
  }

  public boolean registrarEmpresa()
  {
    try
    {
      this.sentencia = ("INSERT INTO Empresa (Nit,Razon_social,Slogan,Representante_legal,Direccion,Ciudad,Departamento,Telefono_fijo,Telefono_movil,PBX,email,Web_page,Face_page,fecha_registro) Values('" + this.empresa.getNit() + "','" + this.empresa.getRazon_Social() + "','" + this.empresa.getSlogan() + "','" + this.empresa.getRepresentante_legal() + "','" + this.empresa.getDireccion() + "','" + this.empresa.getCiudad() + "','" + this.empresa.getDepartamento() + "','" + this.empresa.getTelefono_fijo() + "','" + this.empresa.getTelefono_movil() + "','" + this.empresa.getPBX() + "','" + this.empresa.getEmail() + "','" + this.empresa.getWeb_page() + "','" + this.empresa.getFace_Page() + "',current_date);");
      this.ejecucion = this.coneccion.ejecutarSentencia(this.sentencia);
      if (this.ejecucion == 0) {
        JOptionPane.showMessageDialog(null, "Los Datos de la empresa han sido registrados exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
        this.stateOP = true;
      } else {
        JOptionPane.showMessageDialog(null, "Ocurrio un ERROR y la empresa no se pudo registrar", GlobalConfigSystem.getAplicationTitle(), 0);
        this.stateOP = false;
      }
    } catch (Exception e) {
      System.out.println("A Error had Ocurred when trying Insert sentence SQL In Enterprise.createEnterprise() Detail Error:\n" + e);
    }
    return this.stateOP;
  }

  public Empresa detallesEmpresa()
  {
    try
    {
      this.sentencia = "select * from Empresa";
      this.datosEmpresa = this.coneccion.consultarDatos(this.sentencia);
      if (this.datosEmpresa.next())
        this.empresa = new Empresa(this.datosEmpresa.getString(1), this.datosEmpresa.getString(2), this.datosEmpresa.getString(3), this.datosEmpresa.getString(4), this.datosEmpresa.getString(5), this.datosEmpresa.getString(6), this.datosEmpresa.getString(7), this.datosEmpresa.getString(8), this.datosEmpresa.getString(9), this.datosEmpresa.getString(10), this.datosEmpresa.getString(11), this.datosEmpresa.getString(12), this.datosEmpresa.getString(13));
    }
    catch (Exception e) {
      System.err.println("Ocurrio un error en EmpresaDAO.obtenerInformacion()\n" + e.getMessage());
    }
    return this.empresa;
  }

  public String obtenerNit()
  {
    String Nit = "";
    try {
      this.sentencia = "select nit from Empresa;";
      this.datosEmpresa = this.coneccion.consultarDatos(this.sentencia);
      if (this.datosEmpresa.next()) {
        Nit = this.datosEmpresa.getString("Nit");
        this.datosEmpresa.close();
      } else {
        Nit = null;
      }
    } catch (Exception ex) {
      Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Nit;
  }

  public boolean verificarEstadoSistema()
  {
    try
    {
      String nit = obtenerNit();
      if (nit != null)
        this.stateOP = true;
      else
        this.stateOP = false;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return this.stateOP;
  }

  public boolean actualizarInformacion(String campo, String dato)
  {
    try
    {
      this.sentencia = ("Update Empresa set " + campo + " ='" + dato + "' where Codigo_empresa=1;");
      int update = this.coneccion.ejecutarSentencia(this.sentencia);
      if (update == 0)
        this.stateOP = true;
      else
        this.stateOP = false;
    }
    catch (SQLException ex)
    {
      Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return this.stateOP;
  }
}
