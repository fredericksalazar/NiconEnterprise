/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */

package nicon.enterprise.gui.Clientes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.ClienteDAO;
import nicon.enterprise.libCore.api.obj.Cliente;
import nicon.enterprise.memData.BasicDataAplication;

/**
 * Esta clase representa una vista para la creacion de nuevos clientees, le permite al usuario poder 
 * ingresar los datos de la informacion basica del usuario.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class Clientes_Ingreso extends JDialog implements ActionListener {
    
  private static final long serialVersionUID = 4L;
  
  private JPanel panelPrincipal;
  private JPanel panelContenedor;
  
  private TitledBorder BorderInput;
  
  private JButton JBgrabar;
  private JButton JBCancelar;
  
  private JLabel titulo;
  private JLabel identificacion;
  private JLabel jlNombres;
  private JLabel jlapellidos;
  private JLabel jlCiudad;
  private JLabel jlDireccion;
  private JLabel jlDepartamento;
  private JLabel jlTelefono_fijo;
  private JLabel jlTelefono_movil;
  private JLabel jlTelefono_alternativo;
  private JLabel jlEmail;
  
  private JTextField jtIdentificacion;
  private JTextField jtNombres;
  private JTextField jtApellidos;
  private JTextField jtDireccion;
  private JTextField jtTelefono_fijo;
  private JTextField jtTelefono_movil;
  private JTextField jtTelefono_alternativo;
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
  
  private JComboBox jcCiudades;
  private JComboBox jcDepartamento;
  
  private Cliente cliente;
  private ClienteDAO clienteDAO;
  private Cliente validacion;
  private boolean estadoValidacion;

  public Clientes_Ingreso(){
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setSize(800, 600);
    setLocationRelativeTo(null);
    setUndecorated(true);
    setModal(true);
    loadComponents();
    clienteDAO = new ClienteDAO();
  }

  private void loadComponents(){
    panelPrincipal = new JPanel();
    panelPrincipal.setBackground(GlobalConfigSystem.getBackgroundAplicationPanel());
    panelPrincipal.setBounds(0, 0, 500, 500);
    panelPrincipal.setLayout(null);

    BorderInput = BorderFactory.createTitledBorder("");

    panelContenedor = new JPanel();
    panelContenedor.setBackground(GlobalConfigSystem.getBackgroundAplication());
    panelContenedor.setBounds(50, 85, 700, 470);
    panelContenedor.setBorder(BorderInput);
    panelContenedor.setLayout(null);

    titulo = new JLabel("Ingrese los datos del Cliente:");
    titulo.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    titulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
    titulo.setBounds(50,40, 600,45);

    identificacion = new JLabel("Número de Identificación:");
    identificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    identificacion.setBounds(25, 20, 160, 20);
    identificacion.setFont(GlobalConfigSystem.getFontAplicationText());

    jlNombres = new JLabel("Ingrese los Nombres:");
    jlNombres.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    jlNombres.setFont(GlobalConfigSystem.getFontAplicationText());
    jlNombres.setBounds(25, 60, 160, 20);

    jlapellidos = new JLabel("Ingrese los apellidos:");
    jlapellidos.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    jlapellidos.setFont(GlobalConfigSystem.getFontAplicationText());
    jlapellidos.setBounds(25, 100, 160, 20);

    jlCiudad = new JLabel("Ingrese la ciudad de residencia:");
    jlCiudad.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    jlCiudad.setFont(GlobalConfigSystem.getFontAplicationText());
    jlCiudad.setBounds(25, 140, 300, 20);

    jlDireccion = new JLabel("Ingrese la dirección:");
    jlDireccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    jlDireccion.setBounds(25, 180, 300, 20);

    jlDepartamento = new JLabel("Ingrese el Departamento:");
    jlDepartamento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    jlDepartamento.setFont(GlobalConfigSystem.getFontAplicationText());
    jlDepartamento.setBounds(25, 220, 300, 20);

    jlTelefono_fijo = new JLabel("Ingrese el número de teléfono fijo:");
    jlTelefono_fijo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    jlTelefono_fijo.setFont(GlobalConfigSystem.getFontAplicationText());
    jlTelefono_fijo.setBounds(25, 260, 300, 20);

    jlTelefono_movil = new JLabel("Ingrese el número de celular:");
    jlTelefono_movil.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    jlTelefono_movil.setFont(GlobalConfigSystem.getFontAplicationText());
    jlTelefono_movil.setBounds(25, 300, 300, 20);

    jlTelefono_alternativo = new JLabel("Ingrese un Telefono alternativo:");
    jlTelefono_alternativo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    jlTelefono_alternativo.setFont(GlobalConfigSystem.getFontAplicationText());
    jlTelefono_alternativo.setBounds(25, 340, 300, 20);

    jlEmail = new JLabel("Ingrese una direccón de email:");
    jlEmail.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    jlEmail.setFont(GlobalConfigSystem.getFontAplicationText());
    jlEmail.setBounds(25, 380, 300, 20);

    jtIdentificacion = new JTextField();
    jtIdentificacion.setBounds(300, 15, 300,30);
    jtIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
    jtIdentificacion.setToolTipText("Número de identificación");
    jtIdentificacion.addFocusListener(new FocusAdapter(){
      @Override
      /**
       * Este evento sucede cuando el campo de texto de la indentificacion pierde el foco en ese momento se
       * verifica si el numero de identificacion ingresado ya existe dentro de la fuente de datos, en caso de 
       * ya estar registrado no permitira el ingreso del nuevo usuario con ese numero de indentificacion.
       */
        public void focusLost(FocusEvent fe) {
            validarID();
        }
    });
    
    jtNombres = new JTextField();
    jtNombres.setFont(GlobalConfigSystem.getFontAplicationText());
    jtNombres.setForeground(Color.darkGray);
    jtNombres.setBounds(300, 55, 300,30);
    jtNombres.setToolTipText("Ingrese los nombres del cliente");

    jtApellidos = new JTextField();
    jtApellidos.setFont(GlobalConfigSystem.getFontAplicationText());
    jtApellidos.setForeground(Color.darkGray);
    jtApellidos.setBounds(300, 95, 300,30);
    jtApellidos.setToolTipText("Ingrese los apellidos del cliente");

    jcCiudades = new JComboBox(BasicDataAplication.getListCity());
    jcCiudades.setFont(GlobalConfigSystem.getFontAplicationText());
    jcCiudades.setBounds(300, 135, 300,30);
    jcCiudades.setEditable(true);

    jtDireccion = new JTextField();
    jtDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    jtDireccion.setForeground(Color.darkGray);
    jtDireccion.setBounds(300, 175, 300,30);
    jtDireccion.setToolTipText("Ingrese la dirección de residencia del cliente");

    jcDepartamento = new JComboBox(BasicDataAplication.getListDepartament());
    jcDepartamento.setBounds(300, 215, 300,30);
    jcDepartamento.setFont(GlobalConfigSystem.getFontAplicationText());
    jcDepartamento.setEditable(true);
    jcDepartamento.setToolTipText("Ingrese el departamento o estado");

    jtTelefono_fijo = new JTextField();
    jtTelefono_fijo.setFont(GlobalConfigSystem.getFontAplicationText());
    jtTelefono_fijo.setForeground(Color.darkGray);
    jtTelefono_fijo.setBounds(300, 255, 300,30);
    jtTelefono_fijo.setToolTipText("Ingrese el numero de telefono fijo");

    jtTelefono_movil = new JTextField();
    jtTelefono_movil.setFont(GlobalConfigSystem.getFontAplicationText());
    jtTelefono_movil.setForeground(Color.darkGray);
    jtTelefono_movil.setBounds(300, 295, 300,30);
    jtTelefono_movil.setToolTipText("Ingrese el numero de celular");

    jtTelefono_alternativo = new JTextField();
    jtTelefono_alternativo.setFont(GlobalConfigSystem.getFontAplicationText());
    jtTelefono_alternativo.setForeground(Color.darkGray);
    jtTelefono_alternativo.setBounds(300, 335, 300,30);
    jtTelefono_alternativo.setToolTipText("Ingrese un numero de teléfono adicional");

    JTEmail = new JTextField();
    JTEmail.setFont(GlobalConfigSystem.getFontAplicationText());
    JTEmail.setForeground(Color.darkGray);
    JTEmail.setBounds(300, 375, 300,30);
    JTEmail.setToolTipText("Ingrese una direcciónd de correo electrónico");

    panelContenedor.add(identificacion);
    panelContenedor.add(jlNombres);
    panelContenedor.add(jlapellidos);
    panelContenedor.add(jtIdentificacion);
    panelContenedor.add(jtNombres);
    panelContenedor.add(jtApellidos);
    panelContenedor.add(jlCiudad);
    panelContenedor.add(jcCiudades);
    panelContenedor.add(jlDireccion);
    panelContenedor.add(jtDireccion);
    panelContenedor.add(jlDepartamento);
    panelContenedor.add(jcDepartamento);
    panelContenedor.add(jlTelefono_fijo);
    panelContenedor.add(jtTelefono_fijo);
    panelContenedor.add(jlTelefono_movil);
    panelContenedor.add(jtTelefono_movil);
    panelContenedor.add(jlTelefono_alternativo);
    panelContenedor.add(jtTelefono_alternativo);
    panelContenedor.add(jlEmail);
    panelContenedor.add(JTEmail);

    JBCancelar = new JButton("Cancelar");
    JBCancelar.setBounds(420, 533, 150, 35);
    JBCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
    JBCancelar.setToolTipText("Cancelar el registro de el cliente");
    JBCancelar.addActionListener(this);

    JBgrabar = new JButton("Crear Cliente");
    JBgrabar.setBounds(570, 533, 150, 35);
    JBgrabar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
    JBgrabar.setToolTipText("Ingresar la información de cliente al sistema");
    JBgrabar.addActionListener(this);

    panelPrincipal.add(titulo);
    panelPrincipal.add(JBCancelar);
    panelPrincipal.add(JBgrabar);
    panelPrincipal.add(panelContenedor);
    getContentPane().add(panelPrincipal);
  }

  /**
   * Este metodo se encarga de verificar los registros de un cliente que esta intentando registrar, por lo general
   * solo se verifica si el numero ID ya existe en la base de datos en cuyo caso no permitirá registrar un nuevo
   * cliente con ese ID.
   */
  private void validarID() {
      try {
          id = jtIdentificacion.getText();
          cliente = clienteDAO.buscarPorIdentificacion(id);          
                if (cliente != null) {
                    JOptionPane.showMessageDialog(rootPane, "El número de identificación Ingresado ya existe en el sistema, verifique e intente de nuevo", GlobalConfigSystem.getAplicationTitle(),JOptionPane.ERROR_MESSAGE,new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconError.png")));
                    jtIdentificacion.setText("");
                }
      } catch (SQLException ex) {
          Logger.getLogger(Clientes_Ingreso.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  /**
   * Este metodo permite obtener todos los dats ingresados por el usuario y almacenarlos en variables que 
   * serán usadas al momento de crear el objeto <b>Cliente</b> que será registrado en el sistema.
   * @return 
   */
  private boolean obtenerDatosIngresados(){
    estadoValidacion = true;
    
      id = jtIdentificacion.getText();
      nombres= jtNombres.getText();
      apellidos = jtApellidos.getText();
      ciudad = (String) jcCiudades.getSelectedItem();
      direccion = jtDireccion.getText();
      departamento = ((String)jcDepartamento.getSelectedItem());
      tel_fijo = jtTelefono_fijo.getText();
      tel_movil = jtTelefono_movil.getText();
      tel_aternativo = jtTelefono_alternativo.getText();
      email = JTEmail.getText();

      if ((nombres.equals("")) || (apellidos.equals("")) || (ciudad.equals("")) || (direccion.equals("")) || (departamento.equals("")) || (tel_movil.equals(""))) {
        estadoValidacion = false;
        JOptionPane.showMessageDialog(rootPane, "Hay datos sin ingresar por favor verifique e intente de nuevo", "NiconEnterprise",JOptionPane.ERROR_MESSAGE,new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconError.png")));
      }
    
    return estadoValidacion;
  }

  /**
   * este metodo permite tomar los datos ya ingresados y previamente validados crear un objeto del tipo Cliente
   * que será registrado dentro de la fuente de datos, este metodo crea el objeto cliente por razones de seguridad
   * no se pemritirá la creación de un cliente con diferente numero de cedula pero con datos similares dado que 
   * esto puede permitir la duplicidad de informacion, por lo tanto se hace la verificacion de identidades completa
   * para garantizar informacion verdadera.
   */
  private void guardarDatos(){
      try {
          cliente = new Cliente(id, nombres,apellidos,ciudad,direccion,departamento,tel_fijo,tel_movil,tel_aternativo,email, 1);
          clienteDAO = new ClienteDAO();
          validacion = clienteDAO.validarCliente(cliente.getNombres(),cliente.getApellidos(),cliente.getCiudad());
                if (validacion != null) {
                    id = validacion.getIdentificacion();
                    nombres = validacion.getNombres();
                    apellidos = validacion.getApellidos();
                    JOptionPane.showMessageDialog(rootPane, "Actualmente hay registrado un cliente en la base de datos con informacion similar:\n\nIdentificación: " + validacion.getIdentificacion().toUpperCase() + "\n Nombres: " + validacion.getNombres().toUpperCase() + "\n Apellidos:" + validacion.getApellidos().toUpperCase() + "\n\nNo puede registrar el nuevo Cliente.", GlobalConfigSystem.getAplicationTitle(), 0, new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconWarning.png")));
                    dispose();
                    cliente = null;
                    validacion = null;
                    clienteDAO = null;
                } else {
                    clienteDAO = new ClienteDAO(this.cliente);
                        if (clienteDAO.crearCliente()) {
                                Clientes_Module.recargarDatos();
                                JOptionPane.showMessageDialog(this.rootPane, "El cliente ha sido registrado exitosamente en el sistema", GlobalConfigSystem.getAplicationTitle(), 1, new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPositive.png")));
                                cliente = null;
                                clienteDAO = null;
                                dispose();
                        }            
                }
      } catch (SQLException ex) {
          Logger.getLogger(Clientes_Ingreso.class.getName()).log(Level.SEVERE, null, ex);
          JOptionPane.showMessageDialog(rootPane, "Una exepcion ocurrio en el sistema:\n"+ex, GlobalConfigSystem.getAplicationTitle(), JOptionPane.ERROR_MESSAGE);
      }
  }

  @Override
  public void actionPerformed(ActionEvent ae){
      
    if (ae.getSource() ==JBCancelar) {
      dispose();
    }
    
    if (ae.getSource() == JBgrabar) {
      
            if (obtenerDatosIngresados()){
                guardarDatos();
            }        
    }
  }
}