/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */
package nicon.enterprise.libCore.api.dao;

import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;

import nicon.enterprise.libCore.AdminConector;
import nicon.enterprise.libCore.api.obj.Empresa;

/**
 * Un creado a partir de la clase EmpresaDAO define un api de metodos que serán
 * usados en el modulo de empresa del sistema define los metodos que el sistema
 * puede ofrecer para la gestion de la informacion de la empresa que esta
 * usanado el sistema.
 *
 * @author frederick
 */
public class EmpresaDAO {

    private Empresa empresa;
    private ResultSet datosEmpresa;    
    private AdminConector coneccion;
    
    private int ejecucion;
    private boolean stateOP;
    private String sentencia;
    private String nitEmpresa;   
    private String nit;
    

    /**
     * Este constructor se usa para accesar a metodos genericos que no requieren
     * de un objeto de tipo empresa por lo general los metodos retornan datos
     * del tipo empresa u otro tipo primitivo de java.
     */
    public EmpresaDAO() {
        this.coneccion = AdminConector.getInstance();
    }

    /**
     * Esteo metodo permite instancia un nuevo objeto de EmpresaDAO pero
     * recibiendo un obejto del tipo empresa que serán usados dentro de los
     * metodos de creacion o eliminacion a los que puede acceder.
     *
     * @param empresa
     */
    public EmpresaDAO(Empresa empresa) {
        this.empresa = empresa;
        this.coneccion = AdminConector.getInstance();
    }

    /**
     * Permite registrar uuna empresa dentro de la base de datos de
     * NiconEnterprise con todos los datos recibidos desde el objeto Empresa,
     * intenta hacer el registro de la empresa y retorna un boolean con el
     * estado de la ejecución en caso de poder registrar los datos retorna true
     * en caso contrario retornara false.
     *
     * @return boolean stateOP
     */
    public boolean registrarEmpresa() throws SQLException {
        if (empresa != null) {
            sentencia = "INSERT INTO Empresa (Nit,Razon_social,Slogan,Representante_legal,Direccion,Ciudad,Departamento,Telefono_fijo,Telefono_movil,PBX,email,Web_page,Face_page,fecha_registro) Values('" + this.empresa.getNit() + "','" + this.empresa.getRazon_Social() + "','" + this.empresa.getSlogan() + "','" + this.empresa.getRepresentante_legal() + "','" + this.empresa.getDireccion() + "','" + this.empresa.getCiudad() + "','" + this.empresa.getDepartamento() + "','" + this.empresa.getTelefono_fijo() + "','" + this.empresa.getTelefono_movil() + "','" + this.empresa.getPBX() + "','" + this.empresa.getEmail() + "','" + this.empresa.getWeb_page() + "','" + this.empresa.getFace_Page() + "',current_date);";
            ejecucion = coneccion.runSentence(sentencia);            
                if (ejecucion == 0) {
                    stateOP = true;
                } else {
                    stateOP = false;
                }                
        } else {
            stateOP = false;
        }
        return this.stateOP;
    }

    /**
     * Este metodo permite obetener toda la informacion de la empresa que esta registrada en la base de datos, hace la
     * consulta y crea un Objeto del Tipo Empresa que es retornado al solicitante.
     * @return
     */
    public Empresa detallesEmpresa() throws SQLException {
        sentencia = "SELECT * FROM Empresa";
        datosEmpresa = coneccion.queryData(sentencia);
        
            if (datosEmpresa.next()) {
                empresa = new Empresa(datosEmpresa.getString(1),datosEmpresa.getString(2),datosEmpresa.getString(3),datosEmpresa.getString(4),datosEmpresa.getString(5),datosEmpresa.getString(6), datosEmpresa.getString(7),datosEmpresa.getString(8),datosEmpresa.getString(9),datosEmpresa.getString(10),datosEmpresa.getString(11),datosEmpresa.getString(12),datosEmpresa.getString(13));
                datosEmpresa.close();
            }
        return empresa;
    }

    /**
     * Este metodo permite retornar el numero Nit de la empresa registrada en el sistema.
     * @return String Nit
     */
    public String obtenerNit() throws SQLException {        
            sentencia = "SELECT nit FROM Empresa;";
            datosEmpresa = coneccion.queryData(sentencia);
            
                if (datosEmpresa.next()) {
                    nitEmpresa =datosEmpresa.getString("Nit");
                    datosEmpresa.close();
                } else {
                    nitEmpresa = null;
                }       
        return nitEmpresa;
    }

    /**
     * Este metodo permite verificar el estado de las bases de datos en cuanto ha si han sido registrada una empresa
     * o no, hace uso del metodo obtenerNit(), si recibe un Nit es que una empresa esta usando el software en cuyo caso 
     * retornará un stateOP=true 
     * @return 
     */
    public boolean verificarEstadoSistema() throws SQLException {        
             nitEmpresa = obtenerNit();            
                if (nitEmpresa != null) {
                    stateOP = true;
                } else {
                    stateOP = false;
                }        
        return this.stateOP;
    }

    /**
     * Este metodo permite actualizar la informacion almacenada de la empresa, recibe como parametros el campo
     * a  actualizar y el nuevo dato que será insertado.al intentar ejecutar la sentencia retorna un boolean con
     * el estado de la operacion true en caso de ser exitosa y false en caso de haber alguna falla.
     * 
     * @param campo
     * @param dato
     * @return boolean stateOP
     */
    public boolean actualizarInformacion(String campo, String dato) throws SQLException {
        sentencia = "UPDATE Empresa SET " + campo + " ='" + dato + "' WHERE Codigo_empresa=1;";
        ejecucion = coneccion.runSentence(sentencia);
            if (ejecucion == 0) {
                stateOP = true;
            } else {
                stateOP = false;
            }              
        return this.stateOP;
    }
}
