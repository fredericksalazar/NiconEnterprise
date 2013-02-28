/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.api.obj.Almacen;
import nicon.enterprise.libCore.dao.ClienteDAO;
import nicon.enterprise.libCore.obj.*;
import nicon.enterprise.memData.BasicDataAplication;

public class Ingreso extends JDialog implements ActionListener {
    
  private static final long serialVersionUID = 4L;
  private JPanel JPGui;
  private JPanel JPInput;
  private TitledBorder BorderInput;
  private JButton JBgrabar;
  private JButton JBCancelar;
  private JButton JBLimpiar;
  private JLabel JLTitulo;
  private JLabel JLIdentificacion;
  private JLabel JLNombres;
  private JLabel JLApellidos;
  private JLabel JLCiudad;
  private JLabel JLDireccion;
  private JLabel JLDepartamento;
  private JLabel JLTel_fijo;
  private JLabel JLTel_movil;
  private JLabel JLTel_alternativo;
  private JLabel JLEmail;
  private JTextField JTIdentificacion;
  private JTextField JTNombres;
  private JTextField JTApellidos;
  private JTextField JTDireccion;
  private JTextField JTTel_fijo;
  private JTextField JTTel_movil;
  private JTextField JTTel_alternativo;
  private JTextField JTEmail;
  private String id;
  private String nombres;
  private String apellidos;
  private String ciudad;
  private String direccion;
  private String departamento;
  private String tel_fijo;
  private String tel_movil;
  private String tel_aternativo;
  private String email;
  private JComboBox JCciudades;
  private JComboBox JCDpto;
  private Almacen almacen;
  private Cliente cliente;
  private ClienteDAO clienteDAO;
  private Cliente validacion;

  public Ingreso()
  {
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setSize(800, 600);
    setLocationRelativeTo(null);
    setUndecorated(true);
    setModal(true);
    loadComponents();
    this.clienteDAO = new ClienteDAO();
    this.almacen = new Almacen();
  }

  private void loadComponents()
  {
    this.JPGui = new JPanel();
    this.JPGui.setBackground(GlobalConfigSystem.getBackgroundAplicationPanel());
    this.JPGui.setBounds(0, 0, 500, 500);
    this.JPGui.setLayout(null);

    this.BorderInput = BorderFactory.createTitledBorder("");

    this.JPInput = new JPanel();
    this.JPInput.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.JPInput.setBounds(50, 85, 700, 470);
    this.JPInput.setBorder(this.BorderInput);
    this.JPInput.setLayout(null);

    this.JLTitulo = new JLabel("Creación de Nuevo Cliente");
    this.JLTitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.JLTitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
    this.JLTitulo.setBounds(200, 15, 600, 40);

    this.JLIdentificacion = new JLabel("Ingrese la cédula:");
    this.JLIdentificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLIdentificacion.setBounds(25, 20, 160, 20);
    this.JLIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());

    this.JLNombres = new JLabel("Ingrese los Nombres:");
    this.JLNombres.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLNombres.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLNombres.setBounds(25, 60, 160, 20);

    this.JLApellidos = new JLabel("Ingrese los apellidos:");
    this.JLApellidos.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLApellidos.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLApellidos.setBounds(25, 100, 160, 20);

    this.JLCiudad = new JLabel("Ingrese la ciudad de residencia:");
    this.JLCiudad.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLCiudad.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLCiudad.setBounds(25, 140, 300, 20);

    this.JLDireccion = new JLabel("Ingrese la dirección:");
    this.JLDireccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLDireccion.setBounds(25, 180, 300, 20);

    this.JLDepartamento = new JLabel("Ingrese el Departamento:");
    this.JLDepartamento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLDepartamento.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLDepartamento.setBounds(25, 220, 300, 20);

    this.JLTel_fijo = new JLabel("Ingrese el número de teléfono fijo:");
    this.JLTel_fijo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLTel_fijo.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLTel_fijo.setBounds(25, 260, 300, 20);

    this.JLTel_movil = new JLabel("Ingrese el número de celular:");
    this.JLTel_movil.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLTel_movil.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLTel_movil.setBounds(25, 300, 300, 20);

    this.JLTel_alternativo = new JLabel("Ingrese un Telefono alternativo:");
    this.JLTel_alternativo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLTel_alternativo.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLTel_alternativo.setBounds(25, 340, 300, 20);

    this.JLEmail = new JLabel("Ingrese una direccón de email:");
    this.JLEmail.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.JLEmail.setFont(GlobalConfigSystem.getFontAplicationText());
    this.JLEmail.setBounds(25, 380, 300, 20);

    this.JTIdentificacion = new JTextField();
    this.JTIdentificacion.setBounds(300, 15, 300, 25);
    this.JTIdentificacion.setToolTipText("Número de identificación");
    this.JTIdentificacion.addFocusListener(new FocusAdapter()
    {
      @Override
      public void focusLost(FocusEvent fe) {
        Ingreso.this.verificarCliente();
      }
    });
    this.JTNombres = new JTextField();
    this.JTNombres.setBounds(300, 55, 300, 25);
    this.JTNombres.setToolTipText("Nombres del cliente");

    this.JTApellidos = new JTextField();
    this.JTApellidos.setBounds(300, 95, 300, 25);
    this.JTApellidos.setToolTipText("Ingrese los apellidos del cliente");

    this.JCciudades = new JComboBox(BasicDataAplication.getListCity());
    this.JCciudades.setBounds(300, 135, 300, 25);
    this.JCciudades.setEditable(true);

    this.JTDireccion = new JTextField();
    this.JTDireccion.setBounds(300, 175, 300, 25);
    this.JTDireccion.setToolTipText("Ingrese la dirección de residencia del cliente");

    this.JCDpto = new JComboBox(BasicDataAplication.getListDepartament());
    this.JCDpto.setBounds(300, 215, 300, 25);
    this.JCDpto.setEditable(true);
    this.JCDpto.setToolTipText("Ingrese el departamento o estado");

    this.JTTel_fijo = new JTextField();
    this.JTTel_fijo.setBounds(300, 255, 300, 25);
    this.JTTel_fijo.setToolTipText("Ingrese el numero de telefono fijo");

    this.JTTel_movil = new JTextField();
    this.JTTel_movil.setBounds(300, 295, 300, 25);
    this.JTTel_movil.setToolTipText("Ingrese el numero de celular");

    this.JTTel_alternativo = new JTextField();
    this.JTTel_alternativo.setBounds(300, 335, 300, 25);
    this.JTTel_alternativo.setToolTipText("Ingrese un numero de teléfono adicional");

    this.JTEmail = new JTextField();
    this.JTEmail.setBounds(300, 375, 300, 25);
    this.JTEmail.setToolTipText("Ingrese una direcciónd de correo electrónico");

    this.JPInput.add(this.JLIdentificacion);
    this.JPInput.add(this.JLNombres);
    this.JPInput.add(this.JLApellidos);
    this.JPInput.add(this.JTIdentificacion);
    this.JPInput.add(this.JTNombres);
    this.JPInput.add(this.JTApellidos);
    this.JPInput.add(this.JLCiudad);
    this.JPInput.add(this.JCciudades);
    this.JPInput.add(this.JLDireccion);
    this.JPInput.add(this.JTDireccion);
    this.JPInput.add(this.JLDepartamento);
    this.JPInput.add(this.JCDpto);
    this.JPInput.add(this.JLTel_fijo);
    this.JPInput.add(this.JTTel_fijo);
    this.JPInput.add(this.JLTel_movil);
    this.JPInput.add(this.JTTel_movil);
    this.JPInput.add(this.JLTel_alternativo);
    this.JPInput.add(this.JTTel_alternativo);
    this.JPInput.add(this.JLEmail);
    this.JPInput.add(this.JTEmail);

    this.JBCancelar = new JButton("Cancelar");
    this.JBCancelar.setBounds(420, 533, 150, 35);
    this.JBCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
    this.JBCancelar.setToolTipText("Cancelar el registro de el cliente");
    this.JBCancelar.addActionListener(this);

    this.JBgrabar = new JButton("Crear Cliente");
    this.JBgrabar.setBounds(570, 533, 150, 35);
    this.JBgrabar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
    this.JBgrabar.setToolTipText("Ingresar la información de cliente al sistema");
    this.JBgrabar.addActionListener(this);

    this.JPGui.add(this.JLTitulo);
    this.JPGui.add(this.JBCancelar);
    this.JPGui.add(this.JBgrabar);
    this.JPGui.add(this.JPInput);
    getContentPane().add(this.JPGui);
  }

  private void verificarCliente() {
    this.id = this.JTIdentificacion.getText();
    this.cliente = this.clienteDAO.buscarPorIdentificacion(this.id);
    if (this.cliente != null) {
      JOptionPane.showMessageDialog(this.rootPane, "El cliente ya esta regisrado en el sistema, verifique e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0);
      dispose();
    }
  }

  private boolean getAndVerifiyDataClient()
  {
    boolean state = true;
    try {
      this.id = this.JTIdentificacion.getText();
      this.nombres = this.JTNombres.getText();
      this.apellidos = this.JTApellidos.getText();
      this.ciudad = ((String)this.JCciudades.getSelectedItem());
      this.direccion = this.JTDireccion.getText();
      this.departamento = ((String)this.JCDpto.getSelectedItem());
      this.tel_fijo = this.JTTel_fijo.getText();
      this.tel_movil = this.JTTel_movil.getText();
      this.tel_aternativo = this.JTTel_alternativo.getText();
      this.email = this.JTEmail.getText();

      if ((this.nombres.equals("")) || (this.apellidos.equals("")) || (this.ciudad.equals("")) || (this.direccion.equals("")) || (this.departamento.equals("")) || (this.tel_movil.equals(""))) {
        state = false;
        JOptionPane.showMessageDialog(this.rootPane, "Hay datos sin ingresar por favor verifique e intente de nuevo", "NiconEnterprise", 0);
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this.rootPane, "Ocurrio este error\n" + e, "NiconEnterprise", 0);
      state = false;
    }
    return state;
  }

  private void guardarDatos()
  {
    this.cliente = new Cliente(this.id, this.nombres, this.apellidos, this.ciudad, this.direccion, this.departamento, this.tel_fijo, this.tel_movil, this.tel_aternativo, this.email, 1);
    this.clienteDAO = new ClienteDAO();
    this.validacion = this.clienteDAO.validarCliente(this.cliente.getNombres(), this.cliente.getApellidos());
    if (this.validacion != null) {
      this.id = this.validacion.getIdentificacion();
      this.nombres = this.validacion.getNombres();
      this.apellidos = this.validacion.getApellidos();
      JOptionPane.showMessageDialog(this.rootPane, "Actualmente hay registrado un cliente en la base de datos con informacion similar:\n\nIdentificación: " + this.validacion.getIdentificacion().toUpperCase() + "\n Nombres: " + this.validacion.getNombres().toUpperCase() + "\n Apellidos:" + this.validacion.getApellidos().toUpperCase() + "\n\nNo puede registrar el nuevo Cliente.", GlobalConfigSystem.getAplicationTitle(), 0, new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconWarning.png")));

      dispose();
      this.cliente = null;
      this.validacion = null;
      this.clienteDAO = null;
    } else {
      try {
        this.clienteDAO = new ClienteDAO(this.cliente);
        if (this.clienteDAO.crearCliente()) {
          ModuloClientes.recargarDatos();
          JOptionPane.showMessageDialog(this.rootPane, "El cliente ha sido registrado exitosamente en el sistema", GlobalConfigSystem.getAplicationTitle(), 1, new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPositive.png")));
          this.cliente = null;
          this.clienteDAO = null;
          dispose();
        }
      } catch (SQLException ex) {
        Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent ae)
  {
    if (ae.getSource() == this.JBCancelar) {
      dispose();
    }
    if (ae.getSource() == this.JBgrabar) {
      boolean state = getAndVerifiyDataClient();
      if (state == true)
        guardarDatos();
    }
  }
}