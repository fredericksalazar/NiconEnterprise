/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.api.dao;

import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import nicon.enterprise.libCore.api.util.AdminConector;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.util.NiconLibTools;
import nicon.enterprise.libCore.obj.ContratoEmpleado;

public class ContratoEmpleadoDAO
{
  private ContratoEmpleado contrato;
  private EmpleadoDAO empleadoDAO;
  private String sentence;
  private boolean stateOP = true;
  private ArrayList contratos;
  private int codigoContrato;
  private ResultSet dataSentence;
  private int counter;
  private int ExecuteSentence;
  private int idContrato;
  private String cargo;
  private AdminConector coneccion;

  public ContratoEmpleadoDAO()
  {
    this.contrato = null;
    this.coneccion = AdminConector.getInstance();
  }

  public ContratoEmpleadoDAO(ContratoEmpleado contrato)
  {
    this.contrato = contrato;
    this.coneccion = AdminConector.getInstance();
  }

  public boolean registrarContrato()
    throws SQLException
  {
    if (this.contrato != null) {
      this.sentence = ("insert into ContratosEmpleados (fecha_contratacion,cargo,Salario,tiempo_contratado,Tipo_contrato,Inicio_Funciones,EstadoContrato,Observaciones,IDempleado) values ('" + this.contrato.getFechaContratacion() + "','" + this.contrato.getCargo() + "'," + this.contrato.getSalario() + "," + this.contrato.getTiempoContratado() + ",'" + this.contrato.getTipoContrato() + "','" + this.contrato.getInicioFunciones() + "',true,'" + this.contrato.getObservacion() + "','" + this.contrato.getIdEmpleado() + "');");
      System.out.println(this.sentence);
      this.ExecuteSentence = this.coneccion.runSentence(this.sentence);
      if (this.ExecuteSentence == 0) {
        System.out.println("El contrato ha sido registrado exitosamente ...");
        this.stateOP = true;
      } else {
        System.out.println("Ocurrio un Error y el contrato no se registro en el sistema+");
        this.stateOP = false;
      }
    } else {
      JOptionPane.showMessageDialog(null, "El objeto contratoEmpleado tiene valor null, no puede acceder al metodo registroContrato()", GlobalConfigSystem.getAplicationTitle(), 0);
    }
    return this.stateOP;
  }

  public ContratoEmpleado buscarContrato(String IDempleado)
  {
    try
    {
      System.out.println("Buscando contrato Activo para ID:  " + IDempleado);
      this.sentence = ("SELECT * FROM  ContratosEmpleados WHERE IDempleado='" + IDempleado + "' AND EstadoContrato=true;");
      this.dataSentence = this.coneccion.queryData(this.sentence);
      if (this.dataSentence.next()) {
        this.contrato = new ContratoEmpleado(this.dataSentence.getInt(1), this.dataSentence.getString(2), this.dataSentence.getString(3), this.dataSentence.getDouble(4), this.dataSentence.getInt(5), this.dataSentence.getString(6), NiconLibTools.parseToMysqlStringDate(this.dataSentence.getDate(7)), this.dataSentence.getBoolean(8), IDempleado, this.dataSentence.getString(9));
        System.out.println("Contrato Activo Encontrado Nº : " + this.contrato.getIdContrato());
      } else {
        System.out.println("No se Encontró ningun contrato para el ID: " + IDempleado);
        this.contrato = new ContratoEmpleado(-1, "No hay Contrato Activo", "No hay Contrato Activo", 0.0D, -1, "No hay Contrato Activo", "No hay Contrato Activo", false, "No hay Contrato Activo", "No hay Contrato Activo");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this.contrato;
  }

  public int obtenerContratoActivo(String Identificacion)
  {
    try
    {
      this.sentence = ("select idContrato from ContratosEmpleados where IDempleado=" + Identificacion + " and EstadoContrato=true;");
      this.dataSentence = this.coneccion.queryData(this.sentence);
      if (this.dataSentence.next())
        this.idContrato = this.dataSentence.getInt(1);
      else
        this.idContrato = -1;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return this.idContrato;
  }

  public String obtenerCargoContratoActivo(String id) {
    try {
      this.sentence = ("select cargo from ContratosEmpleados where IDempleado=" + id + " and EstadoContrato=true");
      this.dataSentence = this.coneccion.queryData(this.sentence);
      if (this.dataSentence.next())
        this.cargo = this.dataSentence.getString(1);
      else
        this.cargo = "No hay Cargo activo";
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return this.cargo;
  }

  public ArrayList listarContratosEmpleado(String IdEmpleado)
  {
    try
    {
      System.out.println("Iniciando busqueda de contratos para el empleado=" + IdEmpleado);
      this.contratos = new ArrayList();
      this.counter = 0;
      this.sentence = ("select * from ContratosEmpleados where IDempleado=" + IdEmpleado + ";");
      this.dataSentence = this.coneccion.queryData(this.sentence);
      if (this.dataSentence.next()) {
        this.dataSentence.beforeFirst();
        while (this.dataSentence.next()) {
          this.contrato = new ContratoEmpleado(this.dataSentence.getInt(1), this.dataSentence.getString(2), this.dataSentence.getString(3), this.dataSentence.getDouble(4), this.dataSentence.getInt(5), this.dataSentence.getString(6), NiconLibTools.parseToMysqlStringDate(this.dataSentence.getDate(7)), this.dataSentence.getBoolean(8), IdEmpleado, this.dataSentence.getString(9));
          this.contratos.add(this.counter, this.contrato);
          this.counter += 1;
        }
      }
      this.contratos.clear();

      System.out.println("Lista de contratos cargados exitosamente, total contratos: " + this.contratos.size());
    } catch (Exception e) {
      System.err.println("Un error ocurrio cuando se estaba cargando los contratos del empleado: " + IdEmpleado + " \n" + e.getMessage());
    }
    return this.contratos;
  }

  public ArrayList obtenerTodos()
  {
    try
    {
      this.sentence = "select * from ContratosEmpleados;";
      this.dataSentence = this.coneccion.queryData(this.sentence);
      while (this.dataSentence.next()) {
        this.contrato = new ContratoEmpleado(this.dataSentence.getInt(1), this.dataSentence.getString(2), this.dataSentence.getString(3), this.dataSentence.getDouble(4), this.dataSentence.getInt(5), this.dataSentence.getString(6), NiconLibTools.parseToMysqlStringDate(this.dataSentence.getDate(7)), this.dataSentence.getBoolean(8), this.dataSentence.getString(9), this.dataSentence.getString(10));
        this.contratos.add(this.counter, this.contrato);
        this.counter += 1;
      }
    } catch (Exception e) {
      System.err.println("Ocurrio un ERROR cargando la lista de contratos registrados.\n" + e.getMessage());
    }
    return this.contratos;
  }

  public boolean verificarEstado(int codigo)
  {
    try
    {
      this.sentence = ("select EstadoContrato from ContratosEmpleados where idContrato=" + codigo + ";");
      this.dataSentence = this.coneccion.queryData(this.sentence);
      this.dataSentence.next();
      this.stateOP = this.dataSentence.getBoolean(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this.stateOP;
  }

  public boolean cancelarContratoActivo(String IDempleado)
  {
    try
    {
      if (IDempleado != null) {
        this.codigoContrato = obtenerContratoActivo(IDempleado);
        this.sentence = ("UPDATE ContratosEmpleados set EstadoContrato=false where idContrato=" + this.codigoContrato + ";");
        this.ExecuteSentence = this.coneccion.runSentence(this.sentence);
        this.empleadoDAO = new EmpleadoDAO();
        this.stateOP = this.empleadoDAO.cambiarEstadoEmpleado(IDempleado, false);
        if ((this.ExecuteSentence == 0) && (this.stateOP))
          this.stateOP = true;
        else
          this.stateOP = false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return this.stateOP;
  }

  public String generarCodigoContrato()
  {
    try
    {
      this.sentence = "select count(idContrato) from ContratosEmpleados;";
      this.dataSentence = this.coneccion.queryData(this.sentence);
      this.dataSentence.next();
      this.codigoContrato = this.dataSentence.getInt(1);
      this.codigoContrato += 1;
      System.out.println("Codigo de contrato generado = " + this.codigoContrato);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return String.valueOf(this.codigoContrato);
  }
}