/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Proveedores;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.ProveedorDAO;
import nicon.enterprise.libCore.obj.Proveedor;

public class IngresoProveedor implements ActionListener {
    
  private JPanel panelIngreso;
  private JLabel jlNit;
  private JLabel jlRazonSocial;
  private JLabel jlDireccion;
  private JLabel jlCiudad;
  private JLabel jlTelFijo;
  private JLabel jlTelMovil;
  private JLabel jlFax;
  private JLabel jlEmail;
  private JLabel jlWebPage;
  private JLabel jlBanco;
  private JLabel jlNumeroCuenta;
  private JLabel jlDescripcion;
  private JTextField jtNit;
  private JTextField jtRazonSocial;
  private JTextField jtDireccion;
  private JTextField jtCiudad;
  private JTextField jtTelFijo;
  private JTextField jtTelMovil;
  private JTextField jtFax;
  private JTextField jtEmail;
  private JTextField jtWebPage;
  private JTextField jtBanco;
  private JTextField jtNumeroCuenta;
  private JTextArea jtDescripcion;
  private JButton jbAceptar;
  private JButton jbCancelar;
  private String Icons;
  private String nit;
  private String razonSocial;
  private String direccion;
  private String ciudad;
  private String telFijo;
  private String telMovil;
  private String fax;
  private String email;
  private String webPage;
  private String banco;
  private String numeroCuenta;
  private String descripcion;
  private boolean validacion;
  private Proveedor proveedor;
  private ProveedorDAO proveedorDAO;
  private JDialog ventana;

  public IngresoProveedor()
  {
    this.Icons = GlobalConfigSystem.getIconsPath();
    crearInterfaz();
  }

  private void crearInterfaz()
  {
    this.panelIngreso = new JPanel();
    this.panelIngreso.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.panelIngreso.setSize(500, 500);
    this.panelIngreso.setLayout(null);

    this.jlNit = new JLabel("- Ingrese el Nit:");
    this.jlNit.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlNit.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNit.setBounds(10, 20, 180, 20);

    this.jtNit = new JTextField();
    this.jtNit.setBounds(250, 20, 300, 30);

    this.jlRazonSocial = new JLabel("- Ingrese la Razón Social:");
    this.jlRazonSocial.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlRazonSocial.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlRazonSocial.setBounds(10, 60, 200, 20);

    this.jtRazonSocial = new JTextField();
    this.jtRazonSocial.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtRazonSocial.setBounds(250, 60, 300, 30);

    this.jlDireccion = new JLabel("- Ingrese la Dirección:");
    this.jlDireccion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlDireccion.setBounds(10, 100, 200, 20);

    this.jtDireccion = new JTextField();
    this.jtDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtDireccion.setBounds(250, 100, 300, 30);

    this.jlCiudad = new JLabel("- Ingrese la ciudad:");
    this.jlCiudad.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlCiudad.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlCiudad.setBounds(10, 140, 200, 20);

    this.jtCiudad = new JTextField();
    this.jtCiudad.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtCiudad.setBounds(250, 140, 300, 30);

    this.jlTelFijo = new JLabel("- Ingrese el Teléfono fijo:");
    this.jlTelFijo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlTelFijo.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlTelFijo.setBounds(10, 180, 200, 20);

    this.jtTelFijo = new JTextField();
    this.jtTelFijo.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtTelFijo.setBounds(250, 180, 300, 30);

    this.jlTelMovil = new JLabel("- Ingrese el número celular:");
    this.jlTelMovil.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlTelMovil.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlTelMovil.setBounds(10, 220, 200, 20);

    this.jtTelMovil = new JTextField();
    this.jtTelMovil.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtTelMovil.setBounds(250, 220, 300, 30);

    this.jlFax = new JLabel("- Ingrese el FAX:");
    this.jlFax.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlFax.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlFax.setBounds(10, 260, 200, 20);

    this.jtFax = new JTextField();
    this.jtFax.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtFax.setBounds(250, 260, 300, 30);

    this.jlEmail = new JLabel("- Ingrese el Email:");
    this.jlEmail.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlEmail.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlEmail.setBounds(10, 300, 200, 20);

    this.jtEmail = new JTextField();
    this.jtEmail.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtEmail.setBounds(250, 300, 300, 30);

    this.jlWebPage = new JLabel("- Ingrese la página web:");
    this.jlWebPage.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlWebPage.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlWebPage.setBounds(10, 340, 200, 20);

    this.jtWebPage = new JTextField();
    this.jtWebPage.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtWebPage.setBounds(250, 340, 300, 30);

    this.jlBanco = new JLabel("-Ingrese el Banco de depósito:");
    this.jlBanco.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlBanco.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlBanco.setBounds(10, 380, 220, 20);

    this.jtBanco = new JTextField();
    this.jtBanco.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtBanco.setBounds(250, 380, 300, 30);

    this.jlNumeroCuenta = new JLabel("- Ingrese el Número de cuenta:");
    this.jlNumeroCuenta.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlNumeroCuenta.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNumeroCuenta.setBounds(10, 420, 230, 20);

    this.jtNumeroCuenta = new JTextField();
    this.jtNumeroCuenta.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtNumeroCuenta.setBounds(250, 420, 300, 30);

    this.jlDescripcion = new JLabel("- Ingrese una descripción del producto o servicio que la empresa prestará:");
    this.jlDescripcion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlDescripcion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlDescripcion.setBounds(10, 460, 600, 20);

    this.jtDescripcion = new JTextArea();
    this.jtDescripcion.setLineWrap(true);
    this.jtDescripcion.setBounds(10, 485, 538, 140);

    this.jbAceptar = new JButton("Crear Proveedor");
    this.jbAceptar.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconOK.png")));
    this.jbAceptar.setBounds(402, 630, 160, 30);
    this.jbAceptar.addActionListener(this);

    this.jbCancelar = new JButton("Cancelar");
    this.jbCancelar.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconCancelButton.png")));
    this.jbCancelar.setBounds(227, 630, 160, 30);
    this.jbCancelar.addActionListener(this);

    this.panelIngreso.add(this.jlNit);
    this.panelIngreso.add(this.jtNit);
    this.panelIngreso.add(this.jlRazonSocial);
    this.panelIngreso.add(this.jtRazonSocial);
    this.panelIngreso.add(this.jlDireccion);
    this.panelIngreso.add(this.jtDireccion);
    this.panelIngreso.add(this.jlCiudad);
    this.panelIngreso.add(this.jtCiudad);
    this.panelIngreso.add(this.jlTelFijo);
    this.panelIngreso.add(this.jtTelFijo);
    this.panelIngreso.add(this.jlTelMovil);
    this.panelIngreso.add(this.jtTelMovil);
    this.panelIngreso.add(this.jlFax);
    this.panelIngreso.add(this.jtFax);
    this.panelIngreso.add(this.jlEmail);
    this.panelIngreso.add(this.jtEmail);
    this.panelIngreso.add(this.jlWebPage);
    this.panelIngreso.add(this.jtWebPage);
    this.panelIngreso.add(this.jlBanco);
    this.panelIngreso.add(this.jtBanco);
    this.panelIngreso.add(this.jlNumeroCuenta);
    this.panelIngreso.add(this.jtNumeroCuenta);
    this.panelIngreso.add(this.jbAceptar);
    this.panelIngreso.add(this.jbCancelar);
    this.panelIngreso.add(this.jlDescripcion);
    this.panelIngreso.add(this.jtDescripcion);
  }

  public void abrirVentana()
  {
    this.ventana = new JDialog();
    this.ventana.setTitle(GlobalConfigSystem.getAplicationTitle());
    this.ventana.setSize(600, 690);
    this.ventana.setModal(true);
    this.ventana.setResizable(false);
    this.ventana.setLocationRelativeTo(null);
    this.ventana.add(this.panelIngreso);
    this.ventana.setVisible(true);
  }

  public JPanel obtenerPanelIngreso() {
    return this.panelIngreso;
  }

  private boolean validarDatos()
  {
    this.nit = this.jtNit.getText();
    this.razonSocial = this.jtRazonSocial.getText();
    this.direccion = this.jtDireccion.getText();
    this.ciudad = this.jtCiudad.getText();
    this.telFijo = this.jtTelFijo.getText();
    this.telMovil = this.jtTelMovil.getText();
    this.fax = this.jtFax.getText();
    this.email = this.jtEmail.getText();
    this.webPage = this.jtWebPage.getText();
    this.banco = this.jtBanco.getText();
    this.numeroCuenta = this.jtNumeroCuenta.getText();
    this.descripcion = this.jtDescripcion.getText();

    if ((this.nit.equals("")) || (this.razonSocial.equals("")) || (this.direccion.equals("")) || (this.ciudad.equals("")) || (this.telFijo.equals(""))) {
      JOptionPane.showMessageDialog(null, "Hay campos en los que no ha ingresado Información, verifique e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0);
      this.validacion = false;
    } else {
      this.validacion = true;
    }
    return this.validacion;
  }

  private void registrarProveedor()
  {
    this.proveedor = new Proveedor(this.nit, this.razonSocial, this.direccion, this.ciudad, this.telFijo, this.telMovil, this.fax, this.email, this.webPage, this.banco, this.numeroCuenta, this.descripcion);
    this.proveedorDAO = new ProveedorDAO(this.proveedor);
    this.validacion = this.proveedorDAO.crearProveedor();
    if (this.validacion == true) {
      JOptionPane.showMessageDialog(null, "El proveedor ha sido registrado exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
      this.ventana.setVisible(false);
      this.proveedorDAO = null;
      this.proveedor = null;
    } else {
      JOptionPane.showMessageDialog(null, "Ocurrio un error y el proveedor no se pudo registrar\nintente de nuevo si el error continua, por favor\ncomuníquese con el fabricante", GlobalConfigSystem.getAplicationTitle(), 0);
      this.ventana.setVisible(false);
      this.proveedorDAO = null;
      this.proveedor = null;
    }
  }

  @Override
  public void actionPerformed(ActionEvent ae)
  {
    if ((ae.getSource() == this.jbAceptar) && 
      ((this.validacion = validarDatos()))) {
      registrarProveedor();
      ModuloProveedores.recargarModeloDatos();
    }

    if (ae.getSource() == this.jbCancelar)
      this.ventana.dispose();
  }
}

