/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import nicon.enterprise.libCore.dao.EmpresaDAO;
import nicon.enterprise.libCore.obj.Empresa;

public class NiconEnterprise {

    private static Empresa empresa;
    private static EmpresaDAO ApiEmpresa;
    private static ModuloPrincipal FrontEnd;

    public static void main(String[] args)throws SQLException {
        try {
            System.out.println("Iniciando NiconEnterprise ...");
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            Init.init_Process();
            ApiEmpresa = new EmpresaDAO();
            if (ApiEmpresa.verificarEstadoSistema()) {
                empresa = ApiEmpresa.detallesEmpresa();
                FrontEnd = new ModuloPrincipal(empresa);
                FrontEnd.setVisible(true);
                ApiEmpresa = null;
            } else {
                RegistroEmpresa activation = new RegistroEmpresa();
                activation.setVisible(true);
            }
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(NiconEnterprise.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
