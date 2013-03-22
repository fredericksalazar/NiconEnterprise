/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.libCore.api.dao;

import com.mysql.jdbc.ResultSet;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import nicon.enterprise.libCore.api.obj.ContratoEmpleado;
import nicon.enterprise.libCore.api.util.AdminConector;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.util.NiconLibTools;

public class ContratoEmpleadoDAO{
    
  private ContratoEmpleado contrato;  
  private EmpleadoDAO empleadoDAO;
  private AdminConector coneccion;
  
  private String sentence;
  private String cargo;
  private boolean stateOP = true;
  
  private ArrayList listaContratos;
  private ResultSet dataSentence;
  
  private int ExecuteSentence;
  private int codigoContrato;
  
  /**
   * 
   */
  public ContratoEmpleadoDAO(){
    this.contrato = null;
    this.coneccion = AdminConector.getInstance();
  }

  /**
   * 
   * @param contrato 
   */
  public ContratoEmpleadoDAO(ContratoEmpleado contrato){
    this.contrato = contrato;
    this.coneccion = AdminConector.getInstance();
  }

  /**
   * este metodo permite registrar un contrato a un empleado y ponerlo en estado de
   * activo, este contrato es la representacion digital de la informacion de un
   * contrato fisico que se ha firmado, en caso de que el contrato sea creado se
   * retorna un boolean con el estado true en caso contrario retorna false.
   * 
   * @return boolean stateOP
   * @throws SQLException 
   */
  public boolean crearContrato()throws SQLException {
      sentence = "INSERT INTO ContratosEmpleados (Fecha_Contratacion,Tiempo_Contratado,Fecha_Fin_Contrato,Tipo_Jornada_Laboral,Fecha_Inicio_Funciones,Cargo,Salario,Funciones_Laborales,Observaciones,EstadoContrato,IDempleado) VALUES ('" +contrato.getFechaContratacion() + "'," + contrato.getTiempoContratado() + ",'" +contrato.getFechaFinContrato() + "','" +contrato.getTipoJornadaLaboral() + "','" + contrato.getFechaInicioFunciones() + "','" +contrato.getCargo()+"',"+contrato.getSalario()+",'"+contrato.getFuncionesLaborales()+"','"+contrato.getObservaciones()+"',true,'"+contrato.getIdEmpleado()+"');";
      System.out.println(sentence);
      ExecuteSentence = coneccion.runSentence(sentence);
        if (ExecuteSentence == 0) {
                stateOP = true;
        } else {
                stateOP = false;
        }    
    return stateOP;
  }
  
  /**
   * este metodo permite al usuario poder cancelar un contrato activo registrado
   * para un empleado, el hecho de cancelar el contrato refiere que el usuario de igual
   * forma cambiará su estado de empleado a inactivo, por lo cual el sistema de nomina
   * ya no podrá tenerlo en cuenta para pagos de salario, prestamos o demas, para
   * cancelar el contrato de un empleado se requiere el id del empleado y retorna
   * un boolean con la representacion de la operacion.
   * @param IDempleado
   * @return
   * @throws SQLException 
   */
  public boolean cancelarContratoActivo(String IDempleado) throws SQLException{
        empleadoDAO = new EmpleadoDAO();
        codigoContrato = obtenerIDContratoActivo(IDempleado);
            if(codigoContrato==-1){
                JOptionPane.showMessageDialog(null, "No se encontró un contrato activo para el empleado", GlobalConfigSystem.getAplicationTitle(), JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconError.png")));
            }else{
                sentence = "UPDATE ContratosEmpleados set EstadoContrato=false WHERE idContrato=" +codigoContrato + ";";
                ExecuteSentence = coneccion.runSentence(sentence);                
                stateOP = empleadoDAO.cambiarEstadoEmpleado(IDempleado, false);        
                    if ((ExecuteSentence == 0) && (stateOP)){
                        stateOP = true;
                        coneccion.commit();
                    }else{
                        coneccion.rollBack();
                        stateOP = false;
                    }
           }
    return stateOP;
  }

  /**
   * este metodo retorna el contrato activo que posee un empleado dentro de la 
   * fuente de datos, este objeto de contrato empleado es buscado usando como
   * llave el id del empleado y cuya condicion debe ser que el contrato tenga como
   * estado de contrato true, en caso tal es creado el objeto y retornado al usuario
   * para ser mostrado en la vista de datos.
   * 
   * @param IDempleado
   * @return ContratoEmpleado contrato
   */
  public ContratoEmpleado buscarContratoActivo(String IDempleado) throws SQLException, ParseException{
      sentence = "SELECT * FROM  ContratosEmpleados WHERE IDempleado='" + IDempleado + "' AND EstadoContrato=true;";
      dataSentence = coneccion.queryData(sentence);
        if (dataSentence.next()) {
             contrato=new ContratoEmpleado(dataSentence.getInt("Codigo_Contrato"),NiconLibTools.parseToMysqlStringDate(dataSentence.getDate("Fecha_Contratacion")),dataSentence.getInt("Tiempo_Contratado"),NiconLibTools.parseToMysqlStringDate(dataSentence.getDate("Fecha_Fin_Contrato")),dataSentence.getString("Tipo_Jornada_Laboral"),NiconLibTools.parseToMysqlStringDate(dataSentence.getDate("Fecha_Inicio_Funciones")),dataSentence.getString("Cargo"),dataSentence.getDouble("Salario"),dataSentence.getString("Funciones_Laborales"),dataSentence.getString("Observaciones"),dataSentence.getBoolean("EstadoContrato"),dataSentence.getString("IDempleado"));
        } else {
            contrato = new ContratoEmpleado(-1,"No hay Contrato Activo",0,"No hay Contrato Activo","No hay Contrato Activo", "No hay Contrato Activo", "No hay Contrato Activo",0.0, "No hay Contrato Activo", "No hay Contrato Activo",false,"No hay Contrato Activo");
        }
        dataSentence.close();
    return contrato;
  }

  /**
   * este metodo retorna el codigo de contrato que se ha firmado con un empleaod
   * y cuyo estado actual es activo, se obtiene el Codigo_Contrato  y se retorna
   * al usuario
   * @param Identificacion
   * @return int CodigoContrato
   */
  public int obtenerIDContratoActivo(String Identificacion) throws SQLException{
        sentence ="SELECT Codigo_Contrato FROM ContratosEmpleados WHERE IDempleado=" + Identificacion + " and EstadoContrato=true;";
        dataSentence = coneccion.queryData(sentence);        
            if (dataSentence.next()){
               codigoContrato = dataSentence.getInt(1); 
            }                
            else{
                codigoContrato = -1;
            }  
    return codigoContrato;
  }

  /**
   * este metodo permite obtener el cargo actual del contrato registrado para 
   * un empleado y cuyo estado de contrato es contrato activo, 
   * @param idEmpleado
   * @return 
   */
  public String obtenerCargoContratoActivo(String idEmpleado) throws SQLException {
      sentence = "SELECT cargo FROM ContratosEmpleados WHERE IDempleado='" + idEmpleado + "' AND EstadoContrato=true";
      dataSentence = coneccion.queryData(sentence);
        if (dataSentence.next()){
            cargo = dataSentence.getString(1);
        }            
        else{
           cargo = "No hay Cargo activo"; 
        }
        dataSentence.close();
        return cargo;
  }

  /**
   * este metodo permite obtener un listado de todos los contratos que posea
   * un empleado dentro de la fuente de datos, esta lista almacena todos los contratos
   * sin importar el estado del contrato.
   * 
   * @param IdEmpleado
   * @return ArrayList listaContratos
   */
  public ArrayList listarContratosEmpleado(String IdEmpleado) throws SQLException, ParseException{    
      listaContratos=new ArrayList();
      sentence = "SELECT * FROM ContratosEmpleados WHERE IDempleado=" + IdEmpleado + ";";
      dataSentence=coneccion.queryData(sentence);
        while(dataSentence.next()){
            contrato=new ContratoEmpleado(dataSentence.getInt("Codigo_Contrato"),NiconLibTools.parseToMysqlStringDate(dataSentence.getDate("Fecha_Contratacion")),dataSentence.getInt("Tiempo_Contratado"),NiconLibTools.parseToMysqlStringDate(dataSentence.getDate("Fecha_Fin_Contrato")),dataSentence.getString("Tipo_Jornada_Laboral"),NiconLibTools.parseToMysqlStringDate(dataSentence.getDate("Fecha_Inicio_Funciones")),dataSentence.getString("Cargo"),dataSentence.getDouble("Salario"),dataSentence.getString("Funciones_Laborales"),dataSentence.getString("Observaciones"),dataSentence.getBoolean("EstadoContrato"),dataSentence.getString("IDempleado"));
            listaContratos.add(contrato);
        }
        dataSentence.close();
    return listaContratos;
  }

  /**
   * este metodo permite obtener un listado con todos los contratos registrados
   * dentro de la fuente  de datos, este listado incluye contratos de todos los
   * empleados y con los diferentes estado, este listado se genera para poder 
   * permitir visualizar todos los contratos en una  vista grafica
   * 
   * @return ArrayList listaContratos
   * @throws SQLException
   * @throws ParseException 
   */
  public ArrayList obtenerTodos() throws SQLException, ParseException{    
      sentence = "SELECT * FROM ContratosEmpleados;";
      dataSentence = coneccion.queryData(sentence);
        while (dataSentence.next()) {
            contrato=new ContratoEmpleado(dataSentence.getInt("Codigo_Contrato"),NiconLibTools.parseToMysqlStringDate(dataSentence.getDate("Fecha_Contratacion")),dataSentence.getInt("Tiempo_Contratado"),NiconLibTools.parseToMysqlStringDate(dataSentence.getDate("Fecha_Fin_Contrato")),dataSentence.getString("Jornada_Laboral"),NiconLibTools.parseToMysqlStringDate(dataSentence.getDate("Fecha_Inicio_Funciones")),dataSentence.getString("Cargo"),dataSentence.getDouble("Salario"),dataSentence.getString("Funciones_Laborales"),dataSentence.getString("Observaciones"),dataSentence.getBoolean("EstadoContrato"),dataSentence.getString("IDempleadp"));
            listaContratos.add(contrato);
        }    
        dataSentence.close();
    return listaContratos;
  }

  /**
   * Este metodo permite obtener el estado de contrato registrado en la fuente
   * de datos, recibe como parametros el codigo del contrato que desea buscar
   * y retorna un boolean representando el estado del contrato true en caso de
   * estar activo y false en caso de estar cancelado.
   * 
   * @param codigo
   * @return boolean estadoContrato
   * @throws SQLException 
   */
  public boolean verificarEstado(int codigo) throws SQLException{    
      sentence = "SELECT EstadoContrato FROM ContratosEmpleados WHERE idContrato=" + codigo + ";";
      dataSentence = coneccion.queryData(sentence);
      dataSentence.next();
      stateOP = dataSentence.getBoolean(1);
      dataSentence.close();    
    return stateOP;
  }

  /**
   * este metodo permite obtener el nuevo codigo para el siguiente contrato
   * a firmar y registrar dentro de la fuente de datos.
   * 
   * @return int codigoContrato
   * @throws SQLException 
   */
  public String generarCodigoContrato() throws SQLException{   
      sentence = "select count(Codigo_Contrato) from ContratosEmpleados;";
      dataSentence = coneccion.queryData(sentence);
            if(dataSentence.next()){
                codigoContrato=dataSentence.getInt(1);
                codigoContrato++;
            }else{
                codigoContrato=1;
            }
            dataSentence.close();    
    return String.valueOf(codigoContrato);
  }
}