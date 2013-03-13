/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Empleados;


import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.util.NiconLibTools;
import nicon.enterprise.libCore.api.dao.ContratoEmpleadoDAO;
import nicon.enterprise.libCore.api.dao.EmpleadoDAO;
import nicon.enterprise.libCore.api.obj.Empleado;
import nicon.enterprise.libCore.obj.ContratoEmpleado;



public class GuiIngreso extends JDialog  implements ActionListener{
    
  private JPanel panelAdmin;
  private JPanel panelEmpleado;
  private JPanel panelContrato;
  private TitledBorder borderEmpleado;
  private TitledBorder borderContrato;
  private JLabel jlIdentificacion;
  private JLabel jlNombres;
  private JLabel jlApellidos;
  private JLabel jlFechaNacimiento;
  private JLabel jlLugarNacimiento;
  private JLabel jlEstadoCivil;
  private JLabel jlDireccion;
  private JLabel jlBarrio;
  private JLabel jlCiudad;
  private JLabel jlTelFijo;
  private JLabel jlCodigoContrato;
  private JLabel jlFechaContratacion;
  private JLabel jlCargo;
  private JLabel jlSalario;
  private JLabel jlTiempoContrato;
  private JLabel jlTipoContrato;
  private JLabel jlInicioFunciones;
  private JLabel jlObservaciones;
  private JLabel jlTelMovil;
  private JLabel jlEmail;
  private JTextField jtIdentificacion;
  private JTextField jtNombres;
  private JTextField jtApellidos;
  private JTextField jtLugarNacimiento;
  private JTextField jtEstadoCivil;
  private JTextField jtDireccion;
  private JTextField jtBarrio;
  private JTextField jtCiudad;
  private JTextField jtTelFijo;
  private JTextField jtCargo;
  private JTextField jtSalario;
  private JTextField jtTiempoContrato;
  private JTextField jtCodigoContrato;
  private JTextField jtTelMovil;
  private JTextField jtEmail;
  private JDateChooser jtFechaContratacion;
  private JDateChooser jtFechaNacimiento;
  private JDateChooser jtInicioFunciones;
  private JComboBox jcTipoContrato;
  private JTextArea jtObservaciones;
  private JScrollPane jsObservaciones;
  private JButton jbRegistrar;
  private JButton jbLimpiar;
  private JButton jbCancelar;
  private int response;
  private boolean estado;
  private String observaciones;
  private String lugarNacimiento;
  private String tipoContrato;
  private String identificacion;
  private String nombres;
  private String apellidos;
  private Date fechaNacimiento;
  private String estadoCivil;
  private String direccion;
  private String barrio;
  private String ciudad;
  private String email;
  private String telMovil;
  private String telFijo;
  private Date fechaContratacion;
  private String cargo;
  private double salario;
  private int tiempoContrato;
  private Date inicioFunciones;
  private Empleado empleado;
  private EmpleadoDAO registro;
  private ContratoEmpleado contratoEmpleado;
  private ContratoEmpleadoDAO registroContrato;
  private CrearContrato guiContrato;

  public GuiIngreso(){
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setSize(1200, 650);
    setBackground(GlobalConfigSystem.getBackgroundAplication());
    setModal(true);
    setResizable(false);
    setLocationRelativeTo(null);
    this.registroContrato = new ContratoEmpleadoDAO();
    this.guiContrato = new CrearContrato();
    initComponents();
  }

  private void initComponents()
  {
    this.borderEmpleado = BorderFactory.createTitledBorder("Informacion Personal:");
    this.borderEmpleado.setTitleColor(GlobalConfigSystem.getForegrounAplicationText());
    this.borderEmpleado.setTitleFont(GlobalConfigSystem.getFontAplicationText());

    this.borderContrato = BorderFactory.createTitledBorder("Informacion contractual:");
    this.borderContrato.setTitleColor(GlobalConfigSystem.getForegrounAplicationText());
    this.borderContrato.setTitleFont(GlobalConfigSystem.getFontAplicationText());

    this.panelAdmin = new JPanel();
    this.panelAdmin.setLayout(null);
    this.panelAdmin.setBackground(GlobalConfigSystem.getBackgroundAplication());

    this.panelEmpleado = new JPanel();
    this.panelEmpleado.setLayout(null);
    this.panelEmpleado.setBorder(this.borderEmpleado);
    this.panelEmpleado.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.panelEmpleado.setBounds(8, 30, 500, 580);

    this.jlIdentificacion = new JLabel("- Identificacion:");
    this.jlIdentificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlIdentificacion.setBounds(10, 60, 180, 25);

    this.jtIdentificacion = new JTextField();
    this.jtIdentificacion.setToolTipText("Ingrese el numero de identificacion del empleado");
    this.jtIdentificacion.setBounds(190, 60, 300, 30);

    this.jlNombres = new JLabel("- Nombres:");
    this.jlNombres.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlNombres.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNombres.setBounds(10, 100, 180, 30);

    this.jtNombres = new JTextField();
    this.jtNombres.setToolTipText("Ingrese los Nombres del empleado");
    this.jtNombres.setBounds(190, 100, 300, 30);

    this.jlApellidos = new JLabel("- Apellidos:");
    this.jlApellidos.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlApellidos.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlApellidos.setBounds(10, 140, 180, 30);

    this.jtApellidos = new JTextField();
    this.jtApellidos.setToolTipText("Ingrese los apellidos del empleado");
    this.jtApellidos.setBounds(190, 140, 300, 30);

    this.jlFechaNacimiento = new JLabel("- Fecha de Nacimiento:");
    this.jlFechaNacimiento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlFechaNacimiento.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlFechaNacimiento.setBounds(10, 180, 180, 30);

    this.jtFechaNacimiento = new JDateChooser();
    this.jtFechaNacimiento.setToolTipText("Ingrese la fecha de nacimiento");
    this.jtFechaNacimiento.setBounds(190, 180, 300, 30);

    this.jlLugarNacimiento = new JLabel("- Lugar Nacimiento:");
    this.jlLugarNacimiento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlLugarNacimiento.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlLugarNacimiento.setBounds(10, 220, 180, 30);

    this.jtLugarNacimiento = new JTextField();
    this.jtLugarNacimiento.setToolTipText("Ingrese la ciudad de nacimiento");
    this.jtLugarNacimiento.setBounds(190, 220, 300, 30);

    this.jlEstadoCivil = new JLabel("- Estado Civil:");
    this.jlEstadoCivil.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlEstadoCivil.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlEstadoCivil.setBounds(10, 260, 180, 30);

    this.jtEstadoCivil = new JTextField();
    this.jtEstadoCivil.setToolTipText("Ingrese el estado Civil del empleado");
    this.jtEstadoCivil.setBounds(190, 260, 300, 30);

    this.jlDireccion = new JLabel("- Direccion:");
    this.jlDireccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlDireccion.setBounds(10, 300, 180, 30);

    this.jtDireccion = new JTextField();
    this.jtDireccion.setToolTipText("Ingrese la Direccion de residencia del empleado");
    this.jtDireccion.setBounds(190, 300, 300, 30);

    this.jlBarrio = new JLabel("- Barrio");
    this.jlBarrio.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlBarrio.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlBarrio.setBounds(10, 340, 180, 30);

    this.jtBarrio = new JTextField();
    this.jtBarrio.setToolTipText("Ingrese el Barrio ");
    this.jtBarrio.setBounds(190, 340, 300, 30);

    this.jlCiudad = new JLabel("- Ciudad:");
    this.jlCiudad.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlCiudad.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlCiudad.setBounds(10, 380, 180, 30);

    this.jtCiudad = new JTextField();
    this.jtCiudad.setBounds(190, 380, 300, 30);

    this.jlTelFijo = new JLabel("- Telefono Fijo:");
    this.jlTelFijo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlTelFijo.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlTelFijo.setBounds(10, 420, 180, 30);

    this.jtTelFijo = new JTextField();
    this.jtTelFijo.setBounds(190, 420, 300, 30);

    this.jlTelMovil = new JLabel("- Telefono movil:");
    this.jlTelMovil.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlTelMovil.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlTelMovil.setBounds(10, 460, 180, 30);

    this.jtTelMovil = new JTextField();
    this.jtTelMovil.setBounds(190, 460, 300, 30);

    this.jlEmail = new JLabel("- Correo Electónico:");
    this.jlEmail.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlEmail.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlEmail.setBounds(10, 500, 180, 30);

    this.jtEmail = new JTextField();
    this.jtEmail.setBounds(190, 500, 300, 30);

    this.panelEmpleado.add(this.jlIdentificacion);
    this.panelEmpleado.add(this.jtIdentificacion);
    this.panelEmpleado.add(this.jlNombres);
    this.panelEmpleado.add(this.jtNombres);
    this.panelEmpleado.add(this.jlApellidos);
    this.panelEmpleado.add(this.jtApellidos);
    this.panelEmpleado.add(this.jlFechaNacimiento);
    this.panelEmpleado.add(this.jtFechaNacimiento);
    this.panelEmpleado.add(this.jlLugarNacimiento);
    this.panelEmpleado.add(this.jtLugarNacimiento);
    this.panelEmpleado.add(this.jlEstadoCivil);
    this.panelEmpleado.add(this.jtEstadoCivil);
    this.panelEmpleado.add(this.jlDireccion);
    this.panelEmpleado.add(this.jtDireccion);
    this.panelEmpleado.add(this.jlBarrio);
    this.panelEmpleado.add(this.jtBarrio);
    this.panelEmpleado.add(this.jlCiudad);
    this.panelEmpleado.add(this.jtCiudad);
    this.panelEmpleado.add(this.jlTelFijo);
    this.panelEmpleado.add(this.jtTelFijo);
    this.panelEmpleado.add(this.jlTelMovil);
    this.panelEmpleado.add(this.jtTelMovil);
    this.panelEmpleado.add(this.jlEmail);
    this.panelEmpleado.add(this.jtEmail);

    this.panelContrato = this.guiContrato.clonarPanel();
    this.panelContrato.setBounds(510, 30, 500, 580);

    this.jbRegistrar = new JButton("Registrar");
    this.jbRegistrar.addActionListener(this);
    this.jbRegistrar.setBounds(1020, 70, 160, 30);

    this.jbLimpiar = new JButton("Limpiar campos");
    this.jbLimpiar.addActionListener(this);
    this.jbLimpiar.setToolTipText("Limpia todos los componentes de ingreso de datos.");
    this.jbLimpiar.setBounds(1020, 110, 160, 30);

    this.jbCancelar = new JButton("Cancelar");
    this.jbCancelar.addActionListener(this);
    this.jbCancelar.setToolTipText("Cancela el registro de datos y cierra la ventana");
    this.jbCancelar.setBounds(1020, 150, 160, 30);

    this.panelAdmin.add(this.panelEmpleado);
    this.panelAdmin.add(this.panelContrato);
    this.panelAdmin.add(this.jbRegistrar);
    this.panelAdmin.add(this.jbLimpiar);
    this.panelAdmin.add(this.jbCancelar);
    getContentPane().add(this.panelAdmin);
  }

  private void cleanInterface()
  {
    this.jtIdentificacion.setText("");
    this.jtNombres.setText("");
    this.jtApellidos.setText("");
    this.jtEstadoCivil.setText("");
    this.jtDireccion.setText("");
    this.jtBarrio.setText("");
    this.jtCiudad.setText("");
    this.jtTelFijo.setText("");
    this.jtTelMovil.setText("");
    this.jtEmail.setText("");
    this.jtCargo.setText("");
    this.jtSalario.setText("");
    this.jtTiempoContrato.setText("");
    this.jtObservaciones.setText("");
    this.jtLugarNacimiento.setText("");
  }

  private void obtenerDatos()
  {
    try
    {
      System.out.println("Obteniendo informacion ...");
      this.identificacion = this.jtIdentificacion.getText();
      this.nombres = this.jtNombres.getText();
      this.apellidos = this.jtApellidos.getText();
      this.fechaNacimiento = this.jtFechaNacimiento.getDate();
      this.estadoCivil = this.jtEstadoCivil.getText();
      this.direccion = this.jtDireccion.getText();
      this.barrio = this.jtBarrio.getText();
      this.ciudad = this.jtCiudad.getText();
      this.telFijo = this.jtTelFijo.getText();
      this.telMovil = this.jtTelMovil.getText();
      this.email = this.jtEmail.getText();

      this.fechaContratacion = this.guiContrato.getJtFechaContratacion().getDate();
      this.cargo = this.guiContrato.getJtCargo().getText();
      this.salario = Double.parseDouble(this.guiContrato.getJtSalario().getText());
      this.tiempoContrato = Integer.parseInt(this.guiContrato.getJtTiempoContrato().getText());
      this.tipoContrato = ((String)this.guiContrato.getJcTipoContrato().getSelectedItem());
      this.inicioFunciones = this.guiContrato.getJtInicioFunciones().getDate();
      this.observaciones = this.guiContrato.getJtObservaciones().getText();
      this.lugarNacimiento = this.jtLugarNacimiento.getText();
      System.out.println("Informacion recuperada ...");
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this.panelAdmin, "Los campos numericos no tienen valores correctos, verifique e intente de nuevo.", GlobalConfigSystem.getAplicationTitle(), 0);
    }
  }

  private boolean verificarDatos()
  {
    if ((this.identificacion.equals("")) || (this.nombres.equals("")) || (this.apellidos.equals("")) || (this.estadoCivil.equals("")) || (this.direccion.equals("")) || (this.ciudad.equals("")) || (this.telMovil.equals("")) || (this.email.equals("")) || (this.lugarNacimiento.equals("")) || (this.cargo.equals("")) || (this.salario <= 0.0D) || (this.tiempoContrato <= 0) || (this.tipoContrato.equals("Seleccione un campo"))) {
      JOptionPane.showMessageDialog(this.panelAdmin, "Usted no ha Ingresado todos los datos o son erroneos, verifique e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0);
      this.estado = false;
    } else {
      this.estado = true;
    }
    return this.estado;
  }

  private void registrarContratacion() {
    try {
      this.registroContrato = new ContratoEmpleadoDAO();
      this.empleado = new Empleado(this.identificacion, this.nombres, this.apellidos, NiconLibTools.parseToMysqlStringDate(this.fechaNacimiento), this.lugarNacimiento, this.estadoCivil, this.direccion, this.barrio, this.ciudad, this.telFijo, this.telMovil, this.email, true);
      this.contratoEmpleado = new ContratoEmpleado(NiconLibTools.parseToMysqlStringDate(this.fechaContratacion), this.cargo, this.salario, this.tiempoContrato, this.tipoContrato, NiconLibTools.parseToMysqlStringDate(this.inicioFunciones), this.empleado.getIdentificacion(), this.observaciones);
      this.registro = new EmpleadoDAO(this.empleado);
      this.registroContrato = new ContratoEmpleadoDAO(this.contratoEmpleado);
      boolean crearEmpleado = this.registro.crearEmpleado();
      boolean registrarContrato = this.registroContrato.registrarContrato();
      if ((crearEmpleado == true) && (registrarContrato == true)) {
        JOptionPane.showMessageDialog(this.panelAdmin, "La Informaicon del empleado ha sido almacenada exitosamente.", GlobalConfigSystem.getAplicationTitle(), 1);
        ModuloEmpleados.recargarListaEmpleados();
      } else {
        if (!registrarContrato) {
          JOptionPane.showMessageDialog(this.panelAdmin, " El contrato NO se ha registrado en el sistema, Ocurrio un error en el sistema y la informacion no fue almacenada\n verifique e intente de nuevo o comuniquese con soporte tecnico para recibir ayuda.", GlobalConfigSystem.getAplicationTitle(), 0);
        }
        if (!crearEmpleado)
          JOptionPane.showMessageDialog(this.panelAdmin, "El Empleado no se registro, Ocurrio un error en el sistema y la informacion no fue almacenada\n verifique e intente de nuevo o comuniquese con soporte tecnico para recibir ayuda.", GlobalConfigSystem.getAplicationTitle(), 0);
      }
    }
    catch (Exception e) {
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.jbCancelar) {
      this.response = JOptionPane.showConfirmDialog(this.panelAdmin, "¿Esta seguro que desea cancelar el registro?");
      if (this.response == 0) {
        dispose();
      }
    }

    if (e.getSource() == this.jbLimpiar) {
      this.response = JOptionPane.showConfirmDialog(this.panelAdmin, "¿Esta seguro que desea Limpiar todos los componentes?");
      if (this.response == 0) {
        cleanInterface();
      }
    }

    if (e.getSource() == this.jbRegistrar) {
      obtenerDatos();
      boolean verificacion = verificarDatos();
      if (verificacion)
        registrarContratacion();
    }
  }
}