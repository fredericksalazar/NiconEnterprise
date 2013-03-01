/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Empresa;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.EmpresaDAO;
import nicon.enterprise.libCore.api.obj.Almacen;
import nicon.enterprise.libCore.api.obj.Empresa;
import nicon.enterprise.libCore.dao.ClienteDAO;

public class VisorEmpresa
{
  private JPanel panelAdmin;
  private JLabel jlRazonSocial;
  private JLabel jlSlogan;
  private Empresa empresa;
  private EmpresaDAO empresaDAO;
  private ClienteDAO clienteDAO;
  private Almacen almacen;
  private JLabel jlNit;
  private JLabel nit;
  private JLabel jlRepresentante;
  private JLabel jlIcon;
  private String Icons;
  private JLabel representante;
  private JLabel jlDireccion;
  private JLabel direccion;
  private JLabel jlTelfijo;
  private JLabel telfijo;
  private JLabel jlTelMovil;
  private JLabel telMovil;
  private JLabel jlPBX;
  private JLabel pbx;
  private JLabel jlEmail;
  private JLabel email;
  private JLabel jlWebPage;
  private JLabel webPage;
  private JLabel jlFacePage;
  private JLabel facePage;
  private JLabel jlSeparator;
  private JLabel jlInformacion;
  private JLabel jlAlmacenes;
  private JLabel jlClientes;
  private JButton jbAceptar;
  private JDialog visor;

  public VisorEmpresa()
  {
    this.empresaDAO = new EmpresaDAO();
    this.clienteDAO = new ClienteDAO();
    this.almacen = new Almacen();
    this.Icons = GlobalConfigSystem.getIconsPath();
    init();
  }

  private void init() {
      try {
          this.empresa = this.empresaDAO.detallesEmpresa();

          this.panelAdmin = new JPanel();
          this.panelAdmin.setBackground(GlobalConfigSystem.getBackgroundAplication());
          this.panelAdmin.setSize(700, 500);
          this.panelAdmin.setLayout(null);

          this.jlRazonSocial = new JLabel(this.empresa.getRazon_Social());
          this.jlRazonSocial.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlRazonSocial.setFont(GlobalConfigSystem.getFontAplicationTitle());
          this.jlRazonSocial.setBounds(10, 20, 600, 42);

          this.jlSlogan = new JLabel(this.empresa.getSlogan());
          this.jlSlogan.setBounds(10, 65, 300, 20);
          this.jlSlogan.setForeground(GlobalConfigSystem.getForegrounAplicationText());
          this.jlSlogan.setFont(GlobalConfigSystem.getFontAplicationText());

          this.jlIcon = new JLabel(new ImageIcon(getClass().getResource(this.Icons + "NiconInformation.png")));
          this.jlIcon.setBounds(50, 120, 130, 130);

          this.jlNit = new JLabel("- Nit:");
          this.jlNit.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlNit.setFont(GlobalConfigSystem.getFontAplicationText());
          this.jlNit.setBounds(260, 110, 180, 20);

          this.nit = new JLabel(this.empresa.getNit());
          this.nit.setForeground(GlobalConfigSystem.getForegrounAplicationText());
          this.nit.setFont(GlobalConfigSystem.getFontAplicationText());
          this.nit.setBounds(450, 110, 300, 20);

          this.jlRepresentante = new JLabel("- Representante Legal:");
          this.jlRepresentante.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlRepresentante.setFont(GlobalConfigSystem.getFontAplicationText());
          this.jlRepresentante.setBounds(260, 140, 180, 20);

          this.representante = new JLabel(this.empresa.getRepresentante_legal());
          this.representante.setForeground(GlobalConfigSystem.getForegroundAplicationText());
          this.representante.setFont(GlobalConfigSystem.getFontAplicationText());
          this.representante.setBounds(450, 140, 200, 20);

          this.jlDireccion = new JLabel("- Dirección:");
          this.jlDireccion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
          this.jlDireccion.setBounds(260, 170, 180, 20);

          this.direccion = new JLabel(this.empresa.getCiudad() + " / " + this.empresa.getDireccion());
          this.direccion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
          this.direccion.setFont(GlobalConfigSystem.getFontAplicationText());
          this.direccion.setBounds(450, 170, 300, 20);

          this.jlTelfijo = new JLabel("- Teléfono Fijo:");
          this.jlTelfijo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlTelfijo.setFont(GlobalConfigSystem.getFontAplicationText());
          this.jlTelfijo.setBounds(260, 200, 300, 20);

          this.telfijo = new JLabel(this.empresa.getTelefono_fijo());
          this.telfijo.setForeground(GlobalConfigSystem.getForegroundAplicationText());
          this.telfijo.setFont(GlobalConfigSystem.getFontAplicationText());
          this.telfijo.setBounds(450, 200, 300, 20);

          this.jlTelMovil = new JLabel("- Teléfono Móvil:");
          this.jlTelMovil.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlTelMovil.setFont(GlobalConfigSystem.getFontAplicationText());
          this.jlTelMovil.setBounds(260, 230, 180, 20);

          this.telMovil = new JLabel(this.empresa.getTelefono_movil());
          this.telMovil.setForeground(GlobalConfigSystem.getForegroundAplicationText());
          this.telMovil.setFont(GlobalConfigSystem.getFontAplicationText());
          this.telMovil.setBounds(450, 230, 300, 20);

          this.jlPBX = new JLabel("- PBX:");
          this.jlPBX.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlPBX.setFont(GlobalConfigSystem.getFontAplicationText());
          this.jlPBX.setBounds(260, 260, 300, 20);

          this.pbx = new JLabel(this.empresa.getPBX());
          this.pbx.setForeground(GlobalConfigSystem.getForegrounAplicationText());
          this.pbx.setFont(GlobalConfigSystem.getFontAplicationText());
          this.pbx.setBounds(450, 260, 300, 20);

          this.jlEmail = new JLabel("-Email:");
          this.jlEmail.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlEmail.setFont(GlobalConfigSystem.getFontAplicationText());
          this.jlEmail.setBounds(260, 290, 300, 20);

          this.email = new JLabel(this.empresa.getEmail());
          this.email.setForeground(GlobalConfigSystem.getForegroundAplicationText());
          this.email.setFont(GlobalConfigSystem.getFontAplicationText());
          this.email.setBounds(450, 290, 600, 30);

          this.jlWebPage = new JLabel("- Página web:");
          this.jlWebPage.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlWebPage.setFont(GlobalConfigSystem.getFontAplicationText());
          this.jlWebPage.setBounds(260, 320, 700, 20);

          this.webPage = new JLabel(this.empresa.getWeb_page());
          this.webPage.setForeground(GlobalConfigSystem.getForegroundAplicationText());
          this.webPage.setFont(GlobalConfigSystem.getFontAplicationText());
          this.webPage.setBounds(450, 320, 600, 20);

          this.jlFacePage = new JLabel("- Facebook Page:");
          this.jlFacePage.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlFacePage.setFont(GlobalConfigSystem.getFontAplicationText());
          this.jlFacePage.setBounds(260, 350, 600, 20);

          this.facePage = new JLabel(this.empresa.getFace_Page());
          this.facePage.setFont(GlobalConfigSystem.getFontAplicationText());
          this.facePage.setForeground(GlobalConfigSystem.getForegrounAplicationText());
          this.facePage.setBounds(450, 350, 600, 20);

          this.jlSeparator = new JLabel("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
          this.jlSeparator.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlSeparator.setBounds(5, 380, 700, 15);

          this.jlInformacion = new JLabel("Datos Adicionales:");
          this.jlInformacion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlInformacion.setBounds(30, 400, 300, 20);
          this.jlInformacion.setFont(GlobalConfigSystem.getFontAplicationTextBold());

          this.jlAlmacenes = new JLabel("- Total almacenes registrados:         " + this.almacen.obtenerTotalRegistros());
          this.jlAlmacenes.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlAlmacenes.setFont(GlobalConfigSystem.getFontAplicationTextBold());
          this.jlAlmacenes.setBounds(50, 430, 700, 20);

          this.jlClientes = new JLabel("- Total Clientes Registrados:          " + this.clienteDAO.obtenerTotalRegistros());
          this.jlClientes.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
          this.jlClientes.setFont(GlobalConfigSystem.getFontAplicationTextBold());
          this.jlClientes.setBounds(50, 460, 700, 20);

          this.jbAceptar = new JButton("Aceptar");
          this.jbAceptar.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconOK.png")));
          this.jbAceptar.setFont(GlobalConfigSystem.getFontAplicationButton());
          this.jbAceptar.setBounds(550, 460, 130, 30);
          this.jbAceptar.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) {
              VisorEmpresa.this.visor.dispose();
            }
          });
          this.panelAdmin.add(this.jlIcon);
          this.panelAdmin.add(this.jlRazonSocial);
          this.panelAdmin.add(this.jlSlogan);
          this.panelAdmin.add(this.jlNit);
          this.panelAdmin.add(this.nit);
          this.panelAdmin.add(this.jlRepresentante);
          this.panelAdmin.add(this.representante);
          this.panelAdmin.add(this.jlDireccion);
          this.panelAdmin.add(this.direccion);
          this.panelAdmin.add(this.jlTelfijo);
          this.panelAdmin.add(this.telfijo);
          this.panelAdmin.add(this.jlTelMovil);
          this.panelAdmin.add(this.telMovil);
          this.panelAdmin.add(this.jlPBX);
          this.panelAdmin.add(this.pbx);
          this.panelAdmin.add(this.jlEmail);
          this.panelAdmin.add(this.email);
          this.panelAdmin.add(this.jlWebPage);
          this.panelAdmin.add(this.webPage);
          this.panelAdmin.add(this.jlFacePage);
          this.panelAdmin.add(this.facePage);
          this.panelAdmin.add(this.jlSeparator);
          this.panelAdmin.add(this.jlInformacion);
          this.panelAdmin.add(this.jlAlmacenes);
          this.panelAdmin.add(this.jlClientes);
          this.panelAdmin.add(this.jbAceptar);
      } catch (SQLException ex) {
          Logger.getLogger(VisorEmpresa.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  public JPanel obtenerVisor() {
    return this.panelAdmin;
  }

  public void mostrarVisor() {
    this.visor = new JDialog();
    this.visor.setTitle(GlobalConfigSystem.getAplicationTitle());
    this.visor.setSize(700, 530);
    this.visor.setModal(true);
    this.visor.setLocationRelativeTo(null);
    this.visor.setResizable(false);
    this.visor.add(this.panelAdmin);
    this.visor.setVisible(true);
  }
}
