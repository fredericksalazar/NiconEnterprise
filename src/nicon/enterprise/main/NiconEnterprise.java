/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.main;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import nicon.enterprise.gui.Empresa.RegistroEmpresa;
import nicon.enterprise.gui.ModuloPrincipal;
import nicon.enterprise.libCore.Init;
import nicon.enterprise.libCore.api.dao.EmpresaDAO;
import nicon.enterprise.libCore.api.obj.Empresa;

/**
 * NiconEnterprise es la puerta de entrada para todo el sistema grafico, hace las inicializaciones especiales
 * y las configuraciones basicas del sistema.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 * @version 0.1.50
 */
public class NiconEnterprise {
    
    /**
     * Se define el objeto empresa que representa toda la informacion de la empresa que hace uso del sistema.
     */
    private static Empresa empresa;
    private static EmpresaDAO ApiEmpresa;
    private static ModuloPrincipal FrontEnd;
    private static RegistroEmpresa activation;

    public static void main(String[] args) {
        try {
            System.out.println("Iniciando NiconEnterprise ...");
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            Init.init_Process();
            ApiEmpresa = new EmpresaDAO();
                if (ApiEmpresa.verificarEstadoSistema()) {
                        FrontEnd = new ModuloPrincipal(ApiEmpresa.detallesEmpresa());
                        FrontEnd.setVisible(true);
                } else {
                        activation = new RegistroEmpresa();
                        activation.setVisible(true);
                }
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(NiconEnterprise.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException sql){            
            System.out.println("Oucrrio un error en main.main(): "+sql);
            System.exit(0);
        }
    }
}
