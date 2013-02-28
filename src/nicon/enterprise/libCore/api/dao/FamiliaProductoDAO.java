/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.api.dao;

import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import nicon.enterprise.libCore.Conection;
import nicon.enterprise.libCore.api.obj.FamiliaProducto;


public class FamiliaProductoDAO
{
  private FamiliaProducto ProductFamily;
  private boolean stateOP;
  private String sentence;
  private int ExecuteSentence;
  private ArrayList ListData;
  private ResultSet DataSentence;
  private Conection coneccion;

  public FamiliaProductoDAO()
  {
    this.coneccion = Conection.obtenerInstancia();
  }

  public FamiliaProductoDAO(FamiliaProducto familia) {
    this.ProductFamily = familia;
    this.coneccion = Conection.obtenerInstancia();
  }

  public boolean crearFamilia()
  {
    try
    {
      System.out.println("Iniciando la creacion de la nueva familia de productos: " + this.ProductFamily.getName() + " ...");
      this.sentence = ("Insert Into Familias_Productos values('" + this.ProductFamily.getCode() + "','" + this.ProductFamily.getName() + "','" + this.ProductFamily.getDescription() + "',current_timestamp,'" + this.ProductFamily.getCodeStore() + "');");
      this.ExecuteSentence = this.coneccion.ejecutarSentencia(this.sentence);
      if (this.ExecuteSentence == 0) {
        System.out.println("La nueva familia de productos se ha creado exitosamente ...");
        this.ProductFamily.toString();
        this.stateOP = true;
      } else {
        System.out.println("La nueva familia de productos no se creo, ocurrio un error en el proceso ...");
        this.stateOP = false;
      }
      this.ProductFamily = null;
    } catch (Exception e) {
      System.out.println("Ocurrio un error al ejecutar la sentencia: \n");
      e.printStackTrace();
    }
    return this.stateOP;
  }

  public boolean eliminarFamilia()
  {
    System.out.println("Iniciando eliminacion de datos : Familias de Productos ... ");
    try {
      this.sentence = ("delete from Familias_Productos where codigo_famila='" + this.ProductFamily.getCode() + "' and nombre_familia='" + this.ProductFamily.getName() + "');");
      this.ExecuteSentence = this.coneccion.ejecutarSentencia(this.sentence);
      if (this.ExecuteSentence == 0) {
        System.out.println("La sentencia de eliminacion se ha ejecutado exitosamente ...");
        this.stateOP = true;
      } else {
        System.out.println("La sentencia de eliminacion no se pudo ejecutar, intente de nuevo ...");
        this.stateOP = false;
      }
    } catch (Exception e) {
      System.out.println("Ocurrio un error grave en Family_Product.eliminarFamilia():\n");
      e.printStackTrace();
    }
    return this.stateOP;
  }

  public boolean actualizarDatos(String codigo, String campo, String dato)
  {
    try
    {
      System.out.println("Iniciando actualizacion de datos ...");
      this.sentence = ("UPDATE FROM Familias_Productos SET " + campo + " ='" + dato + "' where Codigo_Familia='" + codigo + "';");
      this.ExecuteSentence = this.coneccion.ejecutarSentencia(this.sentence);
      if (this.ExecuteSentence == 0)
        this.stateOP = true;
      else
        this.stateOP = false;
    }
    catch (Exception e) {
      System.out.println("Ocurrio un error en la actualizacion de datos ...");
      e.printStackTrace();
    }
    return this.stateOP;
  }

  public ArrayList cargarFamilias()
  {
    try
    {
      System.out.println("Inciando carga de datos de : Familias de productos ...");
      this.sentence = "select * from Familias_Productos;";
      this.DataSentence = this.coneccion.consultarDatos(this.sentence);
      while (this.DataSentence.next()) {
        this.ProductFamily = new FamiliaProducto(this.DataSentence.getString(1), this.DataSentence.getString(2), this.DataSentence.getString(3), this.DataSentence.getString(5));
        this.ListData.add(this.ProductFamily);
      }
    } catch (SQLException e) {
      System.out.println("Ocurrio un error en FamiliaProductoDAO.listarFamilias():\n");
      e.printStackTrace();
    }
    return this.ListData;
  }
}