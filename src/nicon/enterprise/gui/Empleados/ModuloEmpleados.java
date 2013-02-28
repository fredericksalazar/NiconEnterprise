/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Empleados;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import nicon.enterprise.gui.ModuloPrincipal;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.ContratoEmpleadoDAO;
import nicon.enterprise.libCore.api.dao.EmpleadoDAO;
import nicon.enterprise.libCore.api.obj.Empleado;
import nicon.enterprise.libCore.obj.ContratoEmpleado;


public class ModuloEmpleados  implements ActionListener, MouseListener{
    
  private JPanel panelAdmin;
  private JPanel panelInformacion;
  private JLabel jlNombres;
  private JLabel jlIdentificacion;
  private JLabel jlFechaNacimiento;
  private JLabel jlLugarNacimiento;
  private JLabel jlEstadoCivil;
  private JLabel jlDireccion;
  private JLabel jlBarrio;
  private JLabel jlTelFijo;
  private JLabel jlTelMovil;
  private JLabel jlEmail;
  private JLabel jlEstado;
  private JLabel jlFechaContratacion;
  private JTextField jtIdentificacion;
  private JTextField jtFechaNacimiento;
  private JTextField jtLugarNacimiento;
  private JTextField jtEstadoCivil;
  private JTextField jtDireccion;
  private JTextField jtBarrio;
  private JTextField jtTelFijo;
  private JTextField jtTelMovil;
  private JTextField jtEmail;
  private JTextField jtEstado;
  private JTextField jtFechaContratacion;
  private static JScrollPane jsEmpleados;
  private static DefaultTableModel modeloDatos;
  private static JTable jtEmpleados;
  private static TitledBorder bordeInf;
  private JMenuBar menuBar;
  private JMenu menuEmpleado;
  private JMenu menuArchivo;
  private JMenu menuContrato;
  private JMenuItem cerrarModulo;
  private JMenuItem crearEmpleado;
  private JMenuItem eliminarEmpleado;
  private JMenuItem editarEmpleado;
  private JMenuItem crearContrato;
  private JMenuItem listarContratos;
  private JMenuItem cancelarContrato;
  private static Empleado empleado;
  private static EmpleadoDAO empleadoDAO;
  private ContratoEmpleado contrato;
  private ContratoEmpleadoDAO contratoDAO;
  private static ArrayList listaEmpleados;
  private int selectedRow;
  private JLabel jlCargo;
  private VisorContrato visor;
  private String Icons;
  private JButton jbVisor;
  private boolean state;

  public ModuloEmpleados()
  {
    this.Icons = GlobalConfigSystem.getIconsPath();
    this.contratoDAO = new ContratoEmpleadoDAO();
    initComponents();
    cargarDatos();
  }

  private void initComponents()
  {
    this.panelAdmin = new JPanel();
    this.panelAdmin.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.panelAdmin.setLayout(null);

    bordeInf = BorderFactory.createTitledBorder("Información");
    bordeInf.setTitleColor(GlobalConfigSystem.getForegrounAplicationText());
    bordeInf.setTitleFont(GlobalConfigSystem.getFontAplicationText());

    this.panelInformacion = new JPanel();
    this.panelInformacion.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.panelInformacion.setBorder(bordeInf);
    this.panelInformacion.setBounds(470, 50, 515, 495);
    this.panelInformacion.setLayout(null);

    modeloDatos = new DefaultTableModel();
    modeloDatos.addColumn("Identificacion");
    modeloDatos.addColumn("Nombres");
    modeloDatos.addColumn("Apellidos");

    jtEmpleados = new JTable(modeloDatos);
    jtEmpleados.setRowHeight(30);
    jtEmpleados.setFont(GlobalConfigSystem.getFontAplicationText());
    jtEmpleados.setSelectionBackground(GlobalConfigSystem.getrowSelectedTable());
    jtEmpleados.addMouseListener(this);

    jsEmpleados = new JScrollPane(jtEmpleados);
    jsEmpleados.setBounds(0, 0, 450, 580);

    this.menuBar = new JMenuBar();
    this.menuBar.setBounds(450, 0, 830, 20);

    this.menuEmpleado = new JMenu("Empleado");
    this.menuArchivo = new JMenu("Archivo");
    this.menuContrato = new JMenu("Contrato");

    this.cerrarModulo = new JMenuItem("Cerrar este Módulo");
    this.cerrarModulo.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconMenuExit.png")));
    this.cerrarModulo.setToolTipText("Cierra el actual Módulo de empleados.");
    this.cerrarModulo.addActionListener(this);

    this.crearEmpleado = new JMenuItem("Crear Empleado");
    this.crearEmpleado.setToolTipText("Abre la ventana de ingreso de datos para crear un nuevo empleado");
    this.crearEmpleado.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconMenuPersonAdd.png")));
    this.crearEmpleado.addActionListener(this);

    this.eliminarEmpleado = new JMenuItem("Eliminar Empleado");
    this.eliminarEmpleado.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconRemove.png")));
    this.eliminarEmpleado.setToolTipText("Eliminar un empleado seleccionado de la table de empleados");
    this.eliminarEmpleado.addActionListener(this);

    this.editarEmpleado = new JMenuItem("Editar Empleado");
    this.editarEmpleado.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconUpdateMenu.png")));
    this.editarEmpleado.setToolTipText("Actualizar la información de un empleado seleccionado en la tabla");
    this.editarEmpleado.addActionListener(this);

    this.crearContrato = new JMenuItem("Crear Contrato");
    this.crearContrato.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconMenuAddContrat.png")));
    this.crearContrato.setToolTipText("Abre la ventana de registro de contratos");
    this.crearContrato.addActionListener(this);

    this.cancelarContrato = new JMenuItem("Cancelar Contrato");
    this.cancelarContrato.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconMenuRemContrat.png")));
    this.cancelarContrato.setToolTipText("Cancela un Contrato firmado con un empleado");
    this.cancelarContrato.addActionListener(this);

    this.listarContratos = new JMenuItem("buscar contratos de:");
    this.listarContratos.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconMenuListContrat.png")));
    this.listarContratos.setToolTipText("Busca todos los contratos pactados con un empleado");
    this.listarContratos.addActionListener(this);

    this.menuArchivo.add(this.cerrarModulo);

    this.menuEmpleado.add(this.crearEmpleado);
    this.menuEmpleado.add(this.eliminarEmpleado);
    this.menuEmpleado.add(this.editarEmpleado);

    this.menuContrato.add(this.crearContrato);
    this.menuContrato.add(this.cancelarContrato);
    this.menuContrato.add(this.listarContratos);

    this.menuBar.add(this.menuArchivo);
    this.menuBar.add(this.menuEmpleado);
    this.menuBar.add(this.menuContrato);

    this.jlNombres = new JLabel();
    this.jlNombres.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlNombres.setBounds(20, 30, 500, 26);
    this.jlNombres.setFont(new Font("Ubuntu", 0, 25));

    this.jlCargo = new JLabel();
    this.jlCargo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlCargo.setBounds(20, 70, 500, 15);
    this.jlCargo.setFont(GlobalConfigSystem.getFontAplicationText());

    this.jlIdentificacion = new JLabel("Identificación:");
    this.jlIdentificacion.setBounds(32, 100, 180, 25);
    this.jlIdentificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());

    this.jtIdentificacion = new JTextField();
    this.jtIdentificacion.setEditable(false);
    this.jtIdentificacion.setBackground(Color.lightGray);
    this.jtIdentificacion.setBounds(200, 100, 260, 25);

    this.jlFechaNacimiento = new JLabel("Fecha Nacimiento: ");
    this.jlFechaNacimiento.setBounds(32, 135, 180, 25);
    this.jlFechaNacimiento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlFechaNacimiento.setFont(GlobalConfigSystem.getFontAplicationText());

    this.jtFechaNacimiento = new JTextField();
    this.jtFechaNacimiento.setEditable(false);
    this.jtFechaNacimiento.setBackground(Color.lightGray);
    this.jtFechaNacimiento.setBounds(200, 135, 260, 25);

    this.jlLugarNacimiento = new JLabel("Lugar Nacimiento");
    this.jlLugarNacimiento.setBounds(32, 170, 180, 25);
    this.jlLugarNacimiento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlLugarNacimiento.setFont(GlobalConfigSystem.getFontAplicationText());

    this.jtLugarNacimiento = new JTextField();
    this.jtLugarNacimiento.setBounds(200, 170, 260, 25);
    this.jtLugarNacimiento.setEditable(false);
    this.jtLugarNacimiento.setBackground(Color.lightGray);

    this.jlEstadoCivil = new JLabel("Estado Civil");
    this.jlEstadoCivil.setBounds(32, 205, 180, 25);
    this.jlEstadoCivil.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlEstadoCivil.setFont(GlobalConfigSystem.getFontAplicationText());

    this.jtEstadoCivil = new JTextField();
    this.jtEstadoCivil.setBounds(200, 205, 260, 25);
    this.jtEstadoCivil.setEditable(false);
    this.jtEstadoCivil.setBackground(Color.lightGray);

    this.jlDireccion = new JLabel("Direccion");
    this.jlDireccion.setBounds(32, 240, 180, 25);
    this.jlDireccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());

    this.jtDireccion = new JTextField();
    this.jtDireccion.setEditable(false);
    this.jtDireccion.setBackground(Color.lightGray);
    this.jtDireccion.setBounds(200, 240, 260, 25);

    this.jlBarrio = new JLabel("Barrio:");
    this.jlBarrio.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlBarrio.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlBarrio.setBounds(32, 275, 180, 25);

    this.jtBarrio = new JTextField();
    this.jtBarrio.setBackground(Color.lightGray);
    this.jtBarrio.setEditable(false);
    this.jtBarrio.setBounds(200, 275, 260, 25);

    this.jlTelFijo = new JLabel("Telefono Fijo:");
    this.jlTelFijo.setBounds(32, 310, 180, 25);
    this.jlTelFijo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlTelFijo.setFont(GlobalConfigSystem.getFontAplicationText());

    this.jtTelFijo = new JTextField();
    this.jtTelFijo.setEditable(false);
    this.jtTelFijo.setBackground(Color.lightGray);
    this.jtTelFijo.setBounds(200, 310, 260, 25);

    this.jlTelMovil = new JLabel("Telefono Movil:");
    this.jlTelMovil.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlTelMovil.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlTelMovil.setBounds(32, 345, 180, 25);

    this.jtTelMovil = new JTextField();
    this.jtTelMovil.setEditable(false);
    this.jtTelMovil.setBackground(Color.lightGray);
    this.jtTelMovil.setBounds(200, 345, 260, 25);

    this.jlEmail = new JLabel("Email:");
    this.jlEmail.setBounds(32, 380, 180, 25);
    this.jlEmail.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlEmail.setFont(GlobalConfigSystem.getFontAplicationText());

    this.jtEmail = new JTextField();
    this.jtEmail.setEditable(false);
    this.jtEmail.setBackground(Color.lightGray);
    this.jtEmail.setBounds(200, 380, 260, 25);

    this.jlEstado = new JLabel("Estado:");
    this.jlEstado.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlEstado.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlEstado.setBounds(32, 415, 180, 25);

    this.jtEstado = new JTextField("Actualmente Activo");
    this.jtEstado.setEditable(false);
    this.jtEstado.setBounds(200, 415, 260, 25);
    this.jtEstado.setBackground(GlobalConfigSystem.getColorInactiveStatus());

    this.jlFechaContratacion = new JLabel("Fecha Contratacion:");
    this.jlFechaContratacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlFechaContratacion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlFechaContratacion.setBounds(32, 450, 180, 25);

    this.jtFechaContratacion = new JTextField();
    this.jtFechaContratacion.setBackground(Color.lightGray);
    this.jtFechaContratacion.setEditable(false);
    this.jtFechaContratacion.setBounds(200, 450, 260, 25);

    this.jbVisor = new JButton("Ver Detalles de Contrato");
    this.jbVisor.addActionListener(this);
    this.jbVisor.setBounds(470, 550, 200, 30);

    this.panelInformacion.add(this.jlNombres);
    this.panelInformacion.add(this.jlCargo);
    this.panelInformacion.add(this.jlIdentificacion);
    this.panelInformacion.add(this.jtIdentificacion);
    this.panelInformacion.add(this.jlFechaNacimiento);
    this.panelInformacion.add(this.jtFechaNacimiento);
    this.panelInformacion.add(this.jlLugarNacimiento);
    this.panelInformacion.add(this.jtLugarNacimiento);
    this.panelInformacion.add(this.jlEstadoCivil);
    this.panelInformacion.add(this.jtEstadoCivil);
    this.panelInformacion.add(this.jlDireccion);
    this.panelInformacion.add(this.jtDireccion);
    this.panelInformacion.add(this.jlBarrio);
    this.panelInformacion.add(this.jtBarrio);
    this.panelInformacion.add(this.jlTelFijo);
    this.panelInformacion.add(this.jtTelFijo);
    this.panelInformacion.add(this.jlTelMovil);
    this.panelInformacion.add(this.jtTelMovil);
    this.panelInformacion.add(this.jlEmail);
    this.panelInformacion.add(this.jtEmail);
    this.panelInformacion.add(this.jlEstado);
    this.panelInformacion.add(this.jtEstado);
    this.panelInformacion.add(this.jlFechaContratacion);
    this.panelInformacion.add(this.jtFechaContratacion);

    this.panelAdmin.add(jsEmpleados);
    this.panelAdmin.add(this.menuBar);
    this.panelAdmin.add(this.panelInformacion);
    this.panelAdmin.add(this.jbVisor);
  }

  public JPanel getGuiPanel()
  {
    return this.panelAdmin;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.crearEmpleado) {
      GuiIngreso ingreso = new GuiIngreso();
      ingreso.setVisible(true);
    }

    if (e.getSource() == this.editarEmpleado) {
      EditorEmpleado editor = new EditorEmpleado(obtenerEmpleadoSeleccionado());
      editor.setVisible(true);
    }

    if (e.getSource() == this.cerrarModulo) {
      ModuloPrincipal.removeTab("Empleados");
    }

    if (e.getSource() == this.jbVisor) {
      visorContrato(this.jtIdentificacion.getText(), this.jlNombres.getText());
    }

    if (e.getSource() == this.crearContrato) {
      CrearContrato crear = new CrearContrato();
      crear.abrirDialogo();
    }

    if (e.getSource() == this.listarContratos) {
      BuscadorContratos buscador = new BuscadorContratos();
      buscador.setVisible(true);
    }

    if (e.getSource() == this.cancelarContrato)
      cancelarContratoSeleccionado();
  }

  private static void cargarDatos()
  {
    try
    {
      empleadoDAO = new EmpleadoDAO();
      listaEmpleados = empleadoDAO.listarEmpleados();
      String[] data = new String[3];
      for (int i = 0; i < listaEmpleados.size(); i++) {
        empleado = (Empleado)listaEmpleados.get(i);
        data[0] = empleado.getIdentificacion();
        data[1] = empleado.getNombres();
        data[2] = empleado.getApellidos();
        modeloDatos.addRow(data);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void recargarListaEmpleados()
  {
    modeloDatos.getDataVector().removeAllElements();
    cargarDatos();
  }

  private void mostrarIndiceSeleccionado()
  {
    try
    {
      empleado = obtenerEmpleadoSeleccionado();
      this.contrato = this.contratoDAO.buscarContrato(empleado.getIdentificacion());
      this.jlNombres.setText(empleado.getNombres() + " " + empleado.getApellidos());
      this.jlCargo.setText(this.contrato.getCargo());
      this.jtIdentificacion.setText(empleado.getIdentificacion());
      this.jtFechaNacimiento.setText(empleado.getFechaNacimiento());
      this.jtLugarNacimiento.setText(empleado.getLugarNacimiento());
      this.jtEstadoCivil.setText(empleado.getEstadoCivil());
      this.jtDireccion.setText(empleado.getDireccion());
      this.jtBarrio.setText(empleado.getBarrio());
      this.jtTelFijo.setText(empleado.getTelefonoFijo());
      this.jtTelMovil.setText(empleado.getTelefonoMovil());
      this.jtEmail.setText(empleado.getEmail());
      boolean estado = empleado.getEstado();
      if (estado == true) {
        this.jtEstado.setBackground(GlobalConfigSystem.getColorActiveStatus());
        this.jtEstado.setText("Actualmente ACTIVO");
      } else {
        this.jtEstado.setBackground(GlobalConfigSystem.getColorInactiveStatus());
        this.jtEstado.setText("Actualmente INACTIVO");
      }
      this.jtFechaContratacion.setText(this.contrato.getFechaContratacion());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Empleado obtenerEmpleadoSeleccionado()
  {
    this.selectedRow = jtEmpleados.getSelectedRow();
    if (this.selectedRow < 0)
      JOptionPane.showMessageDialog(this.panelAdmin, "No se ha Seleccionado Ningun empleado", GlobalConfigSystem.getAplicationTitle(), 0);
    else {
      empleado = (Empleado)listaEmpleados.get(this.selectedRow);
    }
    return empleado;
  }

  private void visorContrato(String Identificacion, String Nombres)
  {
    try
    {
      System.out.println("Mostrando informacion de ID: " + Identificacion);
      this.contrato = this.contratoDAO.buscarContrato(Identificacion);
      this.visor = new VisorContrato(this.contrato, Nombres);
      this.visor.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void cancelarContratoSeleccionado()
  {
    empleado = obtenerEmpleadoSeleccionado();
    String cancel = JOptionPane.showInputDialog(null, "Esta seguro que deseea cancelar el actual contrato para el empleado:\n\n" + empleado.getNombres() + " " + empleado.getApellidos() + "\n\n" + " SI Cancelar = S      No Cancelar = N", GlobalConfigSystem.getAplicationTitle(), 2);

    if ((cancel.equals("S")) || (cancel.equals("s"))) {
      this.state = this.contratoDAO.cancelarContratoActivo(empleado.getIdentificacion());
      if (this.state == true) {
        JOptionPane.showMessageDialog(this.panelAdmin, "El contrato actual con el empleado ha sido cancelado exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
        JOptionPane.showMessageDialog(this.panelAdmin, "El empleado con identificación= " + empleado.getIdentificacion() + "\n" + "" + empleado.getNombres() + " " + empleado.getApellidos() + " \n" + "Ha sido desactivado, su Ingreso a Producción o cualquier área adicional\n" + "esta totalmente retringida.", GlobalConfigSystem.getAplicationTitle(), 1);
      }

    }
    else if ((cancel.equals("N")) || (cancel.equals("n"))) {
      empleado = null;
      this.contratoDAO = null;
    }
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    mostrarIndiceSeleccionado();
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
  }

  public void mouseExited(MouseEvent e)
  {
  }
}