/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Empresa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import nicon.enterprise.gui.ModuloPrincipal;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.EmpresaDAO;
import nicon.enterprise.libCore.api.obj.Empresa;

public class RegistroEmpresa extends JDialog
  implements WindowListener
{
  private JPanel JPActivation;
  private JPanel JPComponents;
  private TitledBorder border;
  private JLabel JLTitulo;
  private JLabel JLInformacion;
  private JLabel JLNit;
  private JLabel JLRazon_Social;
  private JLabel JLSlogan;
  private JLabel JLRpLegal;
  private JLabel JLDireccion;
  private JLabel JLciudad;
  private JLabel JLdepartamento;
  private JLabel JLTel_fijo;
  private JLabel JLTel_movil;
  private JLabel JLPBX;
  private JLabel JLemail;
  private JLabel JLWeb_page;
  private JLabel JLFace_page;
  private JTextField JTNit;
  private JTextField JTRazon_Social;
  private JTextField JTSlogan;
  private JTextField JTRpLegal;
  private JTextField JTDireccion;
  private JTextField JTCiudad;
  private JTextField JTDepartamento;
  private JTextField JTTel_fijo;
  private JTextField JTTel_movil;
  private JTextField JTPBX;
  private JTextField JTemail;
  private JTextField JTWeb_page;
  private JTextField JTFace_page;
  private JButton JBRegistrar;
  private JButton JBcancelar;

  public RegistroEmpresa(){
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setSize(900, 720);
    setLocationRelativeTo(null);
    setLayout(null);
    Init();
  }

  private void Init()
  {
    this.JPActivation = new JPanel();
    this.JPActivation.setLayout(null);
    this.JPActivation.setBounds(0, 0, 900, 720);
    this.JPActivation.setBackground(GlobalConfigSystem.getBackgroundAplication());

    this.JLTitulo = new JLabel("Bienvenido a NiconEnterprise");
    this.JLTitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
    this.JLTitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.JLTitulo.setBounds(180, 18, 700, 40);

    this.JLInformacion = new JLabel("Bienvido a " + GlobalConfigSystem.getAplicationTitle() + " por favor Ingrese los datos a continuación solicitados para registrar su Empresa");
    this.JLInformacion.setBounds(20, 115, 850, 16);
    this.JLInformacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLInformacion.setFont(GlobalConfigSystem.getFontAplicationText());

    this.border = BorderFactory.createTitledBorder("NiconEnterprise Developed By NiconSystem Inc.");
    this.border.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());
    this.border.setTitleFont(GlobalConfigSystem.getFontAplicationText());
    this.border.setTitlePosition(5);

    this.JPComponents = new JPanel();
    this.JPComponents.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.JPComponents.setLayout(null);
    this.JPComponents.setBorder(this.border);
    this.JPComponents.setBounds(20, 160, 850, 540);

    this.JLNit = new JLabel(" Ingrese el Nit:");
    this.JLNit.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLNit.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLNit.setBounds(20, 20, 300, 25);

    this.JLRazon_Social = new JLabel(" Ingrese la razón Social:");
    this.JLRazon_Social.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLRazon_Social.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLRazon_Social.setBounds(20, 55, 300, 25);

    this.JLSlogan = new JLabel(" Ingrese el eslogan:");
    this.JLSlogan.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLSlogan.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLSlogan.setBounds(20, 90, 300, 25);

    this.JLRpLegal = new JLabel(" Ingrese Representante Legal:");
    this.JLRpLegal.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLRpLegal.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLRpLegal.setBounds(20, 125, 300, 25);

    this.JLDireccion = new JLabel(" Ingrese la Dirección:");
    this.JLDireccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLDireccion.setBounds(20, 160, 300, 25);

    this.JLciudad = new JLabel(" Ingrese la Ciudad:");
    this.JLciudad.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLciudad.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLciudad.setBounds(20, 195, 300, 25);

    this.JLdepartamento = new JLabel(" Ingrese el departamento:");
    this.JLdepartamento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLdepartamento.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLdepartamento.setBounds(20, 230, 300, 25);

    this.JLTel_fijo = new JLabel(" Ingrese el Teléfono fijo:");
    this.JLTel_fijo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLTel_fijo.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLTel_fijo.setBounds(20, 264, 300, 25);

    this.JLTel_movil = new JLabel(" Ingrese un Teléfono móvil:");
    this.JLTel_movil.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLTel_movil.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLTel_movil.setBounds(20, 299, 300, 25);

    this.JLPBX = new JLabel(" Ingrese el número del PBX");
    this.JLPBX.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLPBX.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLPBX.setBounds(20, 334, 300, 25);

    this.JLemail = new JLabel(" Ingrese su Email:");
    this.JLemail.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLemail.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLemail.setBounds(20, 369, 300, 25);

    this.JLWeb_page = new JLabel(" Ingrese la URL de su pagina web:");
    this.JLWeb_page.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLWeb_page.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLWeb_page.setBounds(20, 404, 300, 25);

    this.JLFace_page = new JLabel(" Ingrese su página e Facebook:");
    this.JLFace_page.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLFace_page.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLFace_page.setBounds(20, 439, 300, 25);

    this.JTNit = new JTextField();
    this.JTNit.setBounds(370, 15, 320, 25);

    this.JTRazon_Social = new JTextField();
    this.JTRazon_Social.setBounds(370, 50, 320, 25);

    this.JTSlogan = new JTextField();
    this.JTSlogan.setBounds(370, 85, 320, 25);

    this.JTRpLegal = new JTextField();
    this.JTRpLegal.setBounds(370, 120, 320, 25);

    this.JTDireccion = new JTextField();
    this.JTDireccion.setBounds(370, 155, 320, 25);

    this.JTCiudad = new JTextField();
    this.JTCiudad.setBounds(370, 190, 320, 25);

    this.JTDepartamento = new JTextField();
    this.JTDepartamento.setBounds(370, 225, 320, 25);

    this.JTTel_fijo = new JTextField();
    this.JTTel_fijo.setBounds(370, 259, 320, 25);

    this.JTTel_movil = new JTextField();
    this.JTTel_movil.setBounds(370, 294, 320, 25);

    this.JTPBX = new JTextField();
    this.JTPBX.setBounds(370, 329, 320, 25);

    this.JTemail = new JTextField();
    this.JTemail.setBounds(370, 364, 320, 25);

    this.JTWeb_page = new JTextField();
    this.JTWeb_page.setBounds(370, 399, 320, 25);

    this.JTFace_page = new JTextField();
    this.JTFace_page.setBounds(370, 434, 320, 25);

    this.JBRegistrar = new JButton("Registrar Empresa");
    this.JBRegistrar.setBounds(690, 490, 150, 30);
    this.JBRegistrar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ae) {
        RegistroEmpresa.this.getAndSaveDataInput();
      }
    });
    this.JBcancelar = new JButton("Cancelar Registro");
    this.JBcancelar.setBounds(530, 490, 150, 30);
    this.JBcancelar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ae) {
        RegistroEmpresa.this.cancelRegist();
      }
    });
    this.JPComponents.add(this.JLNit);
    this.JPComponents.add(this.JLRazon_Social);
    this.JPComponents.add(this.JLSlogan);
    this.JPComponents.add(this.JLRpLegal);
    this.JPComponents.add(this.JLDireccion);
    this.JPComponents.add(this.JLciudad);
    this.JPComponents.add(this.JLdepartamento);
    this.JPComponents.add(this.JLTel_fijo);
    this.JPComponents.add(this.JLTel_movil);
    this.JPComponents.add(this.JLPBX);
    this.JPComponents.add(this.JLemail);
    this.JPComponents.add(this.JLWeb_page);
    this.JPComponents.add(this.JLFace_page);
    this.JPComponents.add(this.JTNit);
    this.JPComponents.add(this.JTRazon_Social);
    this.JPComponents.add(this.JTSlogan);
    this.JPComponents.add(this.JTRpLegal);
    this.JPComponents.add(this.JTDireccion);
    this.JPComponents.add(this.JTCiudad);
    this.JPComponents.add(this.JTDepartamento);
    this.JPComponents.add(this.JTTel_fijo);
    this.JPComponents.add(this.JTTel_movil);
    this.JPComponents.add(this.JTPBX);
    this.JPComponents.add(this.JTemail);
    this.JPComponents.add(this.JTFace_page);
    this.JPComponents.add(this.JTWeb_page);
    this.JPComponents.add(this.JBRegistrar);
    this.JPComponents.add(this.JBcancelar);

    this.JPActivation.add(this.JLTitulo);
    this.JPActivation.add(this.JLInformacion);
    this.JPActivation.add(this.JPComponents);

    getContentPane().add(this.JPActivation);
    addWindowListener(this);
  }

  private void cancelRegist()
  {
    int response = JOptionPane.showConfirmDialog(null, "¿ La activación se cancelará, esta seguro que desea salir?", "Activación NiconEnterprse", 0);
    if (response == 0)
      System.exit(0);
  }

  private void getAndSaveDataInput()
  {
    String Nit = this.JTNit.getText();
    String razon_social = this.JTRazon_Social.getText();
    String Slogan = this.JTSlogan.getText();
    String representante = this.JTRpLegal.getText();
    String Direccion = this.JTDireccion.getText();
    String Ciudad = this.JTCiudad.getText();
    String Dpto = this.JTDepartamento.getText();
    String tel_fijo = this.JTTel_fijo.getText();
    String Tel_movil = this.JTTel_movil.getText();
    String pbx = this.JTPBX.getText();
    String email = this.JTemail.getText();
    String Web_page = this.JTWeb_page.getText();
    String face_page = this.JTFace_page.getText();

    if ((Nit.equals("")) || (razon_social.equals("")) || (representante.equals("")) || (Direccion.equals("")) || (Ciudad.equals("")) || (Dpto.equals("")) || (tel_fijo.equals(""))) {
      JOptionPane.showMessageDialog(null, "Hay campos que no han sido ingresado, por favor verifique e intente de nuevo", "NiconEnnterprise v 0.1", 0);
    } else {
        try {
            Empresa empresa = new Empresa(Nit, razon_social, Slogan, representante, Direccion, Ciudad, Dpto, tel_fijo, Tel_movil, pbx, email, Web_page, face_page);
            EmpresaDAO empresaDAO = new EmpresaDAO(empresa);
            empresaDAO.registrarEmpresa();
            dispose();
            ModuloPrincipal Gui = new ModuloPrincipal(empresa);
            Gui.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(RegistroEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }

  @Override
  public void windowOpened(WindowEvent we)
  {
  }

  @Override
  public void windowClosing(WindowEvent we)
  {
    cancelRegist();
  }

  @Override
  public void windowClosed(WindowEvent we)
  {
  }

  @Override
  public void windowIconified(WindowEvent we)
  {
  }

  @Override
  public void windowDeiconified(WindowEvent we)
  {
  }

  @Override
  public void windowActivated(WindowEvent we)
  {
  }

  @Override
  public void windowDeactivated(WindowEvent we)
  {
  }
}