/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Proveedores;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
import net.sf.jasperreports.engine.JRException;
import nicon.enterprise.gui.ModuloPrincipal;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.NiconAdminReport;
import nicon.enterprise.libCore.dao.ProveedorDAO;
import nicon.enterprise.libCore.obj.Proveedor;

public class ModuloProveedores
  implements ActionListener, MouseListener
{
  private JPanel moduloProveedores;
  private JPanel panelInformacion;
  private TitledBorder bordeInformacion;
  private static DefaultTableModel modelo;
  private JTable tablaProveedores;
  private JScrollPane jsScroll;
  private JMenuBar menuBar;
  private JMenu menuArchivo;
  private JMenu menuProveedor;
  private JMenu reportes;
  private JMenu ver;
  private JMenuItem cerrarModulo;
  private JMenuItem crearProveedor;
  private JMenuItem eliminarProveedor;
  private JMenuItem actualizarProveedor;
  private JMenuItem exportarTodos;
  private JMenuItem verListaAsc;
  private JMenuItem verListaDesc;
  private String Icons;
  private static String[] vectorDatos;
  private static Proveedor proveedor;
  private static ProveedorDAO proveedorDAO;
  private static ArrayList listaProveedores;
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
  private int indice;
  private String response;
  private boolean estate;
  private NiconAdminReport adminReport;

  public ModuloProveedores()
  {
    this.Icons = GlobalConfigSystem.getIconsPath();
    crearModulo();
    listarProveedores();
  }

  private void crearModulo()
  {
    this.moduloProveedores = new JPanel();
    this.moduloProveedores.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.moduloProveedores.setLayout(null);

    this.bordeInformacion = BorderFactory.createTitledBorder("Detalles Proveedor");
    this.bordeInformacion.setTitleFont(GlobalConfigSystem.getFontAplicationText());
    this.bordeInformacion.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());

    this.panelInformacion = new JPanel();
    this.panelInformacion.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.panelInformacion.setLayout(null);
    this.panelInformacion.setBorder(this.bordeInformacion);
    this.panelInformacion.setBounds(410, 50, 600, 510);

    modelo = new DefaultTableModel();
    modelo.addColumn("Nit");
    modelo.addColumn("Razon Social");

    this.tablaProveedores = new JTable(modelo);
    this.tablaProveedores.setRowHeight(29);
    this.tablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(15);
    this.tablaProveedores.setFont(GlobalConfigSystem.getFontAplicationText());
    this.tablaProveedores.setSelectionBackground(GlobalConfigSystem.getrowSelectedTable());
    this.tablaProveedores.addMouseListener(this);

    this.jsScroll = new JScrollPane(this.tablaProveedores);
    this.jsScroll.setBounds(0, 0, 390, 595);

    this.menuBar = new JMenuBar();
    this.menuBar.setBounds(390, 0, 890, 20);

    this.menuArchivo = new JMenu("Archivo");
    this.menuArchivo.setMnemonic('A');

    this.menuProveedor = new JMenu("Proveedor");
    this.menuProveedor.setMnemonic('P');

    this.reportes = new JMenu("Reportes");
    this.reportes.setMnemonic('r');

    this.ver = new JMenu("Ver");
    this.ver.setMnemonic('v');

    this.cerrarModulo = new JMenuItem("- Cerrar Módulo");
    this.cerrarModulo.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconMenuExit.png")));
    this.cerrarModulo.addActionListener(this);

    this.crearProveedor = new JMenuItem("- Crear Proveedor");
    this.crearProveedor.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconAdd.png")));
    this.crearProveedor.addActionListener(this);

    this.eliminarProveedor = new JMenuItem("- Eliminar Proveedor");
    this.eliminarProveedor.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconRemove.png")));
    this.eliminarProveedor.addActionListener(this);

    this.actualizarProveedor = new JMenuItem("- Actualizar datos");
    this.actualizarProveedor.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconUpdateMenu.png")));
    this.actualizarProveedor.addActionListener(this);

    this.exportarTodos = new JMenuItem(" - Listar Todos");
    this.exportarTodos.setToolTipText("Imprime el listado de todos los proveedores registrados en el sistema");
    this.exportarTodos.addActionListener(this);

    this.verListaAsc = new JMenuItem("Ordenar Lista Asc");
    this.verListaAsc.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconMenuUp.png")));
    this.verListaAsc.setToolTipText("Ordena el listado de proveedores de forma ascendente");
    this.verListaAsc.addActionListener(this);

    this.verListaDesc = new JMenuItem("Ordenar Lista Desc");
    this.verListaDesc.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconMenuDown.png")));
    this.verListaDesc.setToolTipText("Ordena la lista de forma descendente");
    this.verListaDesc.addActionListener(this);

    this.menuArchivo.add(this.cerrarModulo);

    this.menuProveedor.add(this.crearProveedor);
    this.menuProveedor.add(this.actualizarProveedor);
    this.menuProveedor.add(this.eliminarProveedor);

    this.reportes.add(this.exportarTodos);

    this.ver.add(this.verListaAsc);
    this.ver.add(this.verListaDesc);

    this.menuBar.add(this.menuArchivo);
    this.menuBar.add(this.menuProveedor);
    this.menuBar.add(this.reportes);
    this.menuBar.add(this.ver);

    this.jlNit = new JLabel("- Nit:");
    this.jlNit.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlNit.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNit.setBounds(10, 50, 200, 20);

    this.jtNit = new JTextField();
    this.jtNit.setEditable(false);
    this.jtNit.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtNit.setBounds(200, 50, 350, 30);

    this.jlRazonSocial = new JLabel("- Razón Social:");
    this.jlRazonSocial.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlRazonSocial.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlRazonSocial.setBounds(10, 90, 200, 20);

    this.jtRazonSocial = new JTextField();
    this.jtRazonSocial.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtRazonSocial.setEditable(false);
    this.jtRazonSocial.setBounds(200, 90, 350, 30);

    this.jlDireccion = new JLabel("- Dirección: ");
    this.jlDireccion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlDireccion.setBounds(10, 130, 200, 20);

    this.jtDireccion = new JTextField();
    this.jtDireccion.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtDireccion.setEditable(false);
    this.jtDireccion.setBounds(200, 130, 350, 30);

    this.jlCiudad = new JLabel("- Ciudad:");
    this.jlCiudad.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlCiudad.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlCiudad.setBounds(10, 170, 350, 20);

    this.jtCiudad = new JTextField();
    this.jtCiudad.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtCiudad.setEditable(false);
    this.jtCiudad.setBounds(200, 170, 350, 30);

    this.jlTelFijo = new JLabel("- Teléfono Fijo:");
    this.jlTelFijo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlTelFijo.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlTelFijo.setBounds(10, 210, 150, 20);

    this.jtTelFijo = new JTextField();
    this.jtTelFijo.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtTelFijo.setEditable(false);
    this.jtTelFijo.setBounds(200, 210, 350, 30);

    this.jlTelMovil = new JLabel("- Telefono Movil:");
    this.jlTelMovil.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlTelMovil.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlTelMovil.setBounds(10, 250, 200, 20);

    this.jtTelMovil = new JTextField();
    this.jtTelMovil.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtTelMovil.setEditable(false);
    this.jtTelMovil.setBounds(200, 250, 350, 30);

    this.jlFax = new JLabel("- Fax:");
    this.jlFax.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlFax.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlFax.setBounds(10, 290, 200, 20);

    this.jtFax = new JTextField();
    this.jtFax.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtFax.setEditable(false);
    this.jtFax.setBounds(200, 290, 350, 30);

    this.jlEmail = new JLabel("-Email:");
    this.jlEmail.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlEmail.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlEmail.setBounds(10, 330, 200, 20);

    this.jtEmail = new JTextField();
    this.jtEmail.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtEmail.setEditable(false);
    this.jtEmail.setBounds(200, 330, 350, 30);

    this.jlWebPage = new JLabel("- Página web:");
    this.jlWebPage.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlWebPage.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlWebPage.setBounds(10, 370, 200, 20);

    this.jtWebPage = new JTextField();
    this.jtWebPage.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtWebPage.setEditable(false);
    this.jtWebPage.setBounds(200, 370, 350, 30);

    this.jlBanco = new JLabel("- Banco:");
    this.jlBanco.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlBanco.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlBanco.setBounds(10, 410, 200, 20);

    this.jtBanco = new JTextField();
    this.jtBanco.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtBanco.setEditable(false);
    this.jtBanco.setBounds(200, 410, 350, 30);

    this.jlNumeroCuenta = new JLabel("- Número Cuenta:");
    this.jlNumeroCuenta.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlNumeroCuenta.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNumeroCuenta.setBounds(10, 450, 200, 20);

    this.jtNumeroCuenta = new JTextField();
    this.jtNumeroCuenta.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jtNumeroCuenta.setEditable(false);
    this.jtNumeroCuenta.setBounds(200, 450, 350, 30);

    this.panelInformacion.add(this.jlNit);
    this.panelInformacion.add(this.jtNit);
    this.panelInformacion.add(this.jlRazonSocial);
    this.panelInformacion.add(this.jtRazonSocial);
    this.panelInformacion.add(this.jlDireccion);
    this.panelInformacion.add(this.jtDireccion);
    this.panelInformacion.add(this.jlCiudad);
    this.panelInformacion.add(this.jtCiudad);
    this.panelInformacion.add(this.jlTelFijo);
    this.panelInformacion.add(this.jtTelFijo);
    this.panelInformacion.add(this.jlTelMovil);
    this.panelInformacion.add(this.jtTelMovil);
    this.panelInformacion.add(this.jlFax);
    this.panelInformacion.add(this.jtFax);
    this.panelInformacion.add(this.jlEmail);
    this.panelInformacion.add(this.jtEmail);
    this.panelInformacion.add(this.jlWebPage);
    this.panelInformacion.add(this.jtWebPage);
    this.panelInformacion.add(this.jlBanco);
    this.panelInformacion.add(this.jtBanco);
    this.panelInformacion.add(this.jlNumeroCuenta);
    this.panelInformacion.add(this.jtNumeroCuenta);

    this.moduloProveedores.add(this.menuBar);
    this.moduloProveedores.add(this.jsScroll);
    this.moduloProveedores.add(this.panelInformacion);
  }

  public JPanel obtenerModulo()
  {
    return this.moduloProveedores;
  }

  private static void cargarListaProveedores(ArrayList listado)
  {
    try
    {
      System.out.println("Iniciando carga de datos al Modulo de Proveedores");
      vectorDatos = new String[2];
      for (int i = 0; i < listado.size(); i++) {
        proveedor = (Proveedor)listado.get(i);
        vectorDatos[0] = proveedor.getNit();
        vectorDatos[1] = proveedor.getRazonSocial();
        modelo.addRow(vectorDatos);
      }
      System.out.println("Todos los Proveedores han sido cargados al modulo. total proveedores registrados: " + modelo.getRowCount());
    } catch (Exception e) {
      System.err.println("Ocurrio un error cargando datos de modulo: Clientes\n" + e.getMessage());
    }
  }

  private static void listarProveedores()
  {
    proveedorDAO = new ProveedorDAO();
    listaProveedores = proveedorDAO.listarProveedores();
    cargarListaProveedores(listaProveedores);
  }

  private void listarProveedoresOrderASC() {
    listaProveedores.clear();
    modelo.getDataVector().removeAllElements();
    listaProveedores = proveedorDAO.listarProveedoresOrdenados("asc");
    cargarListaProveedores(listaProveedores);
  }

  private void listarProveedoresOrderDESC() {
    listaProveedores.clear();
    modelo.getDataVector().removeAllElements();
    listaProveedores = proveedorDAO.listarProveedoresOrdenados("desc");
    cargarListaProveedores(listaProveedores);
  }

  public static void recargarModeloDatos()
  {
    modelo.getDataVector().removeAllElements();
    listaProveedores.clear();
    listarProveedores();
  }

  public void mostrarProveedor(Proveedor proveedor)
  {
    if (proveedor != null) {
      this.jtNit.setText(proveedor.getNit());
      this.jtRazonSocial.setText(proveedor.getRazonSocial());
      this.jtDireccion.setText(proveedor.getDireccion());
      this.jtCiudad.setText(proveedor.getCiudad());
      this.jtTelFijo.setText(proveedor.getTelefonoFijo());
      this.jtTelMovil.setText(proveedor.getTelefonoMovil());
      this.jtFax.setText(proveedor.getFax());
      this.jtEmail.setText(proveedor.getEmail());
      this.jtWebPage.setText(proveedor.getWebPage());
      this.jtBanco.setText(proveedor.getBanco());
      this.jtNumeroCuenta.setText(proveedor.getNumeroCuenta());
    }
  }

  public int obtenerIndiceSeleccionado()
  {
    this.indice = this.tablaProveedores.getSelectedRow();
    if (this.indice < 0) {
      JOptionPane.showMessageDialog(null, "No ha seleccionado nigún Proveedor en la lista", GlobalConfigSystem.getAplicationTitle(), 0);
    }
    return this.indice;
  }

  public Proveedor obtenerProveedorSeleccionado()
  {
    proveedor = (Proveedor)listaProveedores.get(obtenerIndiceSeleccionado());
    return proveedor;
  }

  private void eliminarProveedor()
  {
    proveedor = obtenerProveedorSeleccionado();
    this.response = JOptionPane.showInputDialog(null, "Esta a punto de eliminar el proveedor :" + proveedor.getRazonSocial() + "\n¿esta seguro que desea hacerlo?\n Eliminar = S cancelar = N", GlobalConfigSystem.getAplicationTitle(), 2);
    if ((this.response.equals("S")) || (this.response.equals("s"))) {
      proveedorDAO = new ProveedorDAO(proveedor);
      this.estate = proveedorDAO.eliminarProveedor();
      if (this.estate == true) {
        JOptionPane.showMessageDialog(this.panelInformacion, "El proveedor ha sido eliminado del sistema exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
        recargarModeloDatos();
      } else {
        JOptionPane.showMessageDialog(this.panelInformacion, "El proveedor no pudo ser eliminado del sistema, sus registros esta enlazados y no se permite eliminacion de PrimaryKey", GlobalConfigSystem.getAplicationTitle(), 0);
      }
    }
  }

  private void listarTodosLosProveedores() {
    try {
      proveedorDAO.exportarTodosPDF();
    } catch (JRException ex) {
      Logger.getLogger(ModuloProveedores.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void actionPerformed(ActionEvent ae)
  {
    if (ae.getSource() == this.cerrarModulo) {
      ModuloPrincipal.removeTab("Proveedores");
    }

    if (ae.getSource() == this.crearProveedor) {
      IngresoProveedor ingreso = new IngresoProveedor();
      ingreso.abrirVentana();
    }

    if (ae.getSource() == this.eliminarProveedor) {
      eliminarProveedor();
    }

    if (ae.getSource() == this.actualizarProveedor) {
      EditorProveedor editor = new EditorProveedor(obtenerProveedorSeleccionado());
      editor.setVisible(true);
    }

    if (ae.getSource() == this.exportarTodos) {
      listarTodosLosProveedores();
    }

    if (ae.getSource() == this.verListaAsc) {
      listarProveedoresOrderASC();
    }

    if (ae.getSource() == this.verListaDesc)
      listarProveedoresOrderDESC();
  }

  @Override
  public void mouseClicked(MouseEvent me)
  {
    if (me.getSource() == this.tablaProveedores)
      mostrarProveedor(obtenerProveedorSeleccionado());
  }

  @Override
  public void mousePressed(MouseEvent me)
  {
  }

  @Override
  public void mouseReleased(MouseEvent me)
  {
  }

  @Override
  public void mouseEntered(MouseEvent me)
  {
  }

  @Override
  public void mouseExited(MouseEvent me)
  {
  }
}
