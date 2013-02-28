/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui;

import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import nicon.enterprise.gui.Clientes.ModuloClientes;
import nicon.enterprise.gui.Clientes.activities.ActividadesPendientes;
import nicon.enterprise.gui.Empleados.ModuloEmpleados;
import nicon.enterprise.gui.Empresa.VisorEmpresa;
import nicon.enterprise.gui.Proveedores.ModuloProveedores;
import nicon.enterprise.libCore.Conection;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.ConfigConectorDAO;
import nicon.enterprise.libCore.obj.ConfigConector;
import nicon.enterprise.libCore.obj.Empresa;

public class ModuloPrincipal extends JFrame implements WindowListener, ActionListener, KeyListener {

    private static final long serialVersionUID = 3L;
    private static int TotalOpenTabs;
    private static String Icons;
    private int index;
    private boolean operacion;
    private String salida;
    private ArrayList listadoActividades;
    private JMenuBar MenuBar;
    private JMenu menuArchivo;
    private JMenu menuConf;
    private JMenu menuEmpresa;
    private JMenu menuAlmacen;
    private JMenuItem JMIConfig_almacen;
    private JMenuItem jmVerEmpresa;
    private JMenuItem jmSalir;
    private JMenuItem jmConectar;
    private JMenuItem jmDesconectar;
    private JMenuItem jmCrearConfConector;
    private JMenuItem jmDelConfConector;
    private JMenuItem jmVerConector;
    private JPanel panelDash;
    private JPanel mainPanel;
    private JPanel panelActividades;
    private JPanel panelAcciones;
    private JPanel JPTemp;
    private JLabel jlRazonSocial;
    private JLabel jlSlogan;
    private JLabel jlNit;
    private JLabel jlDireccion;
    private JLabel JLTelefono;
    private JLabel JLEmail;
    private JLabel JLTitulo;
    private JLabel JLClientes;
    private JLabel JLempleados;
    private JLabel jlProveedores;
    private JLabel estadoServidor;
    private JLabel jlInfActividad;
    private JButton moduloClientes;
    private JButton moduloEmpleados;
    private JButton moduloProveedores;
    private static JTabbedPane JTabsNiconEnterprise;
    private JTextField guiEstadoServidor;
    private JScrollPane scrollLista;
    private Empresa datosEmpresa;
    private Conection coneccion;
    private ConfigConector conector;
    private ConfigConectorDAO conectorDAO;
    private JScrollPane scrollPanelAcciones;
    private TitledBorder borderAcciones;
    private ActividadesPendientes panelPendientes;
    private ConfigConectorDAO config;
    private ModuleConector module;

    public ModuloPrincipal(Empresa Informacion) {
        setTitle(GlobalConfigSystem.getAplicationTitle());
        setSize(1320, 730);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setJMenuBar(this.MenuBar);

        this.datosEmpresa = Informacion;
        Icons = GlobalConfigSystem.getIconsPath();
        this.coneccion = Conection.obtenerInstancia();

        crearModulo();
        createDash();
    }

    private void crearModulo() {
        this.MenuBar = new JMenuBar();
        this.MenuBar.setFont(GlobalConfigSystem.getFontAplicationText());
        this.MenuBar.setBounds(0, 0, 1320, 25);

        this.menuArchivo = new JMenu("Archivo");
        this.menuArchivo.setFont(GlobalConfigSystem.getFontAplicationText());
        this.menuArchivo.setToolTipText("Abre herramientas de NiconEnterprise");
        this.menuArchivo.setMnemonic('A');

        this.menuConf = new JMenu("Configuración");
        this.menuConf.setFont(GlobalConfigSystem.getFontAplicationText());
        this.menuConf.setToolTipText("Accede a las configuraciones básicas de la aplicación");
        this.menuConf.setMnemonic('C');

        this.menuEmpresa = new JMenu("Mi Empresa");
        this.menuEmpresa.setFont(GlobalConfigSystem.getFontAplicationText());
        this.menuEmpresa.setToolTipText("Muestra todas las herramientas de configuracion de la empresa");
        this.menuEmpresa.setMnemonic('E');

        this.menuAlmacen = new JMenu("Almacenes");
        this.menuAlmacen.setFont(GlobalConfigSystem.getFontAplicationText());

        this.JMIConfig_almacen = new JMenuItem("Configurar");
        this.JMIConfig_almacen.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JMIConfig_almacen.setToolTipText("Configurar sistema de almacenes");
        this.JMIConfig_almacen.setMnemonic('C');
        this.JMIConfig_almacen.addActionListener(this);

        this.jmVerEmpresa = new JMenuItem("- Ver mi Empresa");
        this.jmVerEmpresa.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmVerEmpresa.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuInformation.png")));
        this.jmVerEmpresa.setToolTipText("Abre el visor de datos mostrando todos los datos actuales de la empresa");
        this.jmVerEmpresa.addActionListener(this);

        this.jmCrearConfConector = new JMenuItem("- Nuevo Conector");
        this.jmCrearConfConector.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmCrearConfConector.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconAdd.png")));
        this.jmCrearConfConector.setToolTipText("Permite conectar el sistema a un nuevo servidor de datos NiconEnterprise");
        this.jmCrearConfConector.addActionListener(this);

        this.jmDelConfConector = new JMenuItem("- Eliminar conector");
        this.jmDelConfConector.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmDelConfConector.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconRemove.png")));
        this.jmDelConfConector.setToolTipText("Elimina el archivo conector.conf que permite configurar la coneccion a la fuente de datos");
        this.jmDelConfConector.addActionListener(this);

        this.jmVerConector = new JMenuItem("- Ver Conector");
        this.jmVerConector.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmVerConector.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuInformation.png")));
        this.jmVerConector.setToolTipText("Muestra detalles de la coneccion actual a la fuente de datos");
        this.jmVerConector.addActionListener(this);

        this.jmConectar = new JMenuItem("- Conectar al servidor");
        this.jmConectar.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmConectar.setToolTipText("Permite conectar el sistema al servidor de datos");
        this.jmConectar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconServerOnline.png")));
        this.jmConectar.addActionListener(this);

        this.jmDesconectar = new JMenuItem("- Desconectar el servidor");
        this.jmDesconectar.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmDesconectar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconServerOffline.png")));
        this.jmDesconectar.setToolTipText("Cierrra la conección entre el servidor de datos y el NiconEnterprise");
        this.jmDesconectar.addActionListener(this);

        this.jmSalir = new JMenuItem("- Salir de NiconEnterprise");
        this.jmSalir.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmSalir.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconShutdown.png")));
        this.jmSalir.setToolTipText("Salir de " + GlobalConfigSystem.getAplicationTitle());
        this.jmSalir.addActionListener(this);

        this.menuArchivo.add(this.jmCrearConfConector);
        this.menuArchivo.add(this.jmVerConector);
        this.menuArchivo.add(this.jmDelConfConector);
        this.menuArchivo.addSeparator();
        this.menuArchivo.add(this.jmConectar);
        this.menuArchivo.add(this.jmDesconectar);
        this.menuArchivo.addSeparator();
        this.menuArchivo.add(this.jmSalir);

        this.menuAlmacen.add(this.JMIConfig_almacen);

        this.menuEmpresa.add(this.jmVerEmpresa);

        this.menuConf.add(this.menuAlmacen);

        this.MenuBar.add(this.menuArchivo);
        this.MenuBar.add(this.menuConf);
        this.MenuBar.add(this.menuEmpresa);

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(null);
        this.mainPanel.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.mainPanel.setBounds(0, 25, 1320, 720);
        this.mainPanel.add(this.MenuBar);

        this.jlRazonSocial = new JLabel(this.datosEmpresa.getRazon_Social());
        this.jlRazonSocial.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlRazonSocial.setBounds(10, 10, 700, 45);
        this.jlRazonSocial.setFont(GlobalConfigSystem.getFontAplicationTitle());

        this.jlSlogan = new JLabel(this.datosEmpresa.getSlogan());
        this.jlSlogan.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlSlogan.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlSlogan.setBounds(10, 50, 300, 19);

        this.jlNit = new JLabel("Nit:  " + this.datosEmpresa.getNit());
        this.jlNit.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlNit.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlNit.setBounds(10, 80, 300, 19);

        this.jlDireccion = new JLabel(this.datosEmpresa.getDireccion() + " / " + this.datosEmpresa.getCiudad() + "  / " + this.datosEmpresa.getDepartamento());
        this.jlDireccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlDireccion.setBounds(10, 100, 300, 19);

        this.JLTelefono = new JLabel(this.datosEmpresa.getTelefono_fijo() + " / " + this.datosEmpresa.getTelefono_movil());
        this.JLTelefono.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLTelefono.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLTelefono.setBounds(10, 120, 200, 20);

        this.panelDash = new JPanel();
        this.panelDash.setLayout(null);
        this.panelDash.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.panelDash.setBounds(0, 0, 1280, 680);

        JTabsNiconEnterprise = new JTabbedPane();
        JTabsNiconEnterprise.setFont(GlobalConfigSystem.getFontAplicationText());
        JTabsNiconEnterprise.setBounds(10, 20, 1280, 650);
        JTabsNiconEnterprise.addTab(GlobalConfigSystem.getAplicationTitle(), this.panelDash);

        this.mainPanel.add(JTabsNiconEnterprise);

        addWindowListener(this);
        getContentPane().add(this.MenuBar);
        getContentPane().add(this.mainPanel);
    }

    private void createDash() {
        this.panelPendientes = new ActividadesPendientes();

        this.borderAcciones = BorderFactory.createTitledBorder("");
        this.borderAcciones.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());
        this.borderAcciones.setTitleFont(GlobalConfigSystem.getFontAplicationText());

        this.panelAcciones = new JPanel();
        this.panelAcciones.setBorder(this.borderAcciones);
        this.panelAcciones.setLayout(null);
        this.panelAcciones.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.panelAcciones.setBounds(10, 160, 700, 420);

        this.moduloClientes = new JButton();
        this.moduloClientes.setBounds(60, 50, 100, 100);
        this.moduloClientes.addActionListener(this);
        this.moduloClientes.setMnemonic('1');
        this.moduloClientes.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconClient.png")));

        this.moduloEmpleados = new JButton();
        this.moduloEmpleados.setBounds(180, 50, 100, 100);
        this.moduloEmpleados.setMnemonic('2');
        this.moduloEmpleados.addActionListener(this);
        this.moduloEmpleados.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconEmpleado.png")));

        this.moduloProveedores = new JButton();
        this.moduloProveedores.setBounds(300, 50, 100, 100);
        this.moduloProveedores.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconProveedor.png")));
        this.moduloProveedores.setToolTipText("Permite acceder al módulo de administracion de proveedores");
        this.moduloProveedores.addActionListener(this);

        this.JLClientes = new JLabel("Clientes");
        this.JLClientes.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.JLClientes.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLClientes.setBounds(80, 160, 200, 25);

        this.JLempleados = new JLabel("Empleados");
        this.JLempleados.setBounds(190, 160, 150, 25);
        this.JLempleados.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.JLempleados.setFont(GlobalConfigSystem.getFontAplicationText());

        this.jlProveedores = new JLabel("Proveedores");
        this.jlProveedores.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlProveedores.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlProveedores.setBounds(307, 160, 150, 25);

        this.panelAcciones.add(this.moduloClientes);
        this.panelAcciones.add(this.JLClientes);
        this.panelAcciones.add(this.moduloEmpleados);
        this.panelAcciones.add(this.JLempleados);
        this.panelAcciones.add(this.moduloProveedores);
        this.panelAcciones.add(this.jlProveedores);

        this.estadoServidor = new JLabel("Estado del Servidor");
        this.estadoServidor.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.estadoServidor.setFont(GlobalConfigSystem.getFontAplicationText());
        this.estadoServidor.setBounds(1070, 568, 300, 20);

        this.guiEstadoServidor = new JTextField();
        this.guiEstadoServidor.setEditable(false);
        this.guiEstadoServidor.setBackground(GlobalConfigSystem.getColorActiveStatus());
        this.guiEstadoServidor.setBounds(1210, 562, 30, 30);

        this.panelDash.add(this.jlRazonSocial);
        this.panelDash.add(this.jlSlogan);
        this.panelDash.add(this.jlNit);
        this.panelDash.add(this.jlDireccion);
        this.panelDash.add(this.JLTelefono);
        this.panelDash.add(this.panelAcciones);
        this.panelDash.add(this.estadoServidor);
        this.panelDash.add(this.guiEstadoServidor);
        this.panelDash.add(this.panelPendientes.obetenerPanelActividades());
    }

    private void addTabbedPane(JPanel panel, String name) {
        TotalOpenTabs = JTabsNiconEnterprise.getTabCount();
        JTabsNiconEnterprise.addTab(name, panel);
        JTabsNiconEnterprise.setSelectedIndex(TotalOpenTabs);
    }

    public static void removeTab(String nombre) {
        JTabsNiconEnterprise.remove(getIndexOfTab(nombre));
    }

    public static int getIndexOfTab(String Name) {
        return JTabsNiconEnterprise.indexOfTab(Name);
    }

    private void salir() {
        this.salida = JOptionPane.showInputDialog(this.rootPane, "Esta a punto de cerrar " + GlobalConfigSystem.getAplicationTitle() + ", ¿Esta seguro que desea salir?\n" + "                      Presione S para salir o N para cancelar", GlobalConfigSystem.getAplicationTitle(), 2);

        if (this.salida.equals("")) {
            JOptionPane.showMessageDialog(this.rootPane, "No ha Ingresado un parametro correcto, verifique e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0, new ImageIcon(getClass().getResource(Icons + "NiconError.png")));
        }
        if ((this.salida.equals("S")) || (this.salida.equals("s"))) {
            dispose();
            System.exit(0);
        }
    }

    private void desconectar() {
        this.operacion = this.coneccion.desconectar();
        if (this.operacion) {
            this.guiEstadoServidor.setBackground(GlobalConfigSystem.getColorInactiveStatus());
            JOptionPane.showMessageDialog(this.rootPane, "El sistema se ha desconectado del servidor exitosamente.", GlobalConfigSystem.getAplicationTitle(), 2, new ImageIcon(getClass().getResource(Icons + "NiconWarning.png")));
        }
    }

    private void conectar()
            throws SQLException {
        this.operacion = this.coneccion.conectar();
        if (this.operacion) {
            this.guiEstadoServidor.setBackground(GlobalConfigSystem.getColorActiveStatus());
            JOptionPane.showMessageDialog(this.rootPane, "El sistema se ha conectado exitosamente al servidor.", GlobalConfigSystem.getAplicationTitle(), 1, new ImageIcon(getClass().getResource(Icons + "NiconPossitive.png")));
        }
    }

    private void eliminarArchivoConeccion() {
        this.salida = JOptionPane.showInputDialog(this.rootPane, "¿ Esta a punto de ELIMINAR el archivo ./config/Conector. conf que define la conección\n con la fuente de datos, ¿ Esta seguro que desea continuar?:\n Presione S para eliminar N para cancelar.", GlobalConfigSystem.getAplicationTitle(), 2);
        if ((this.salida.equals("S")) || (this.salida.equals("s"))) {
            this.config = new ConfigConectorDAO();
            if (this.config.deleteConfigFile()) {
                JOptionPane.showMessageDialog(this.rootPane, "El archivo Conector.conf ha sido eliminado exitosamente, por favor configure nuevos parametros de coneccion\no espere a que el sistema configure un automaticamente", GlobalConfigSystem.getAplicationTitle(), 1);
            }
        }
    }

    private void verDetallesConector() {
        try {
            this.conectorDAO = new ConfigConectorDAO();
            this.conector = this.conectorDAO.loadConfig();
            this.module = new ModuleConector(this.conector);
            this.module.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ModuloPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModuloPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        salir();
    }

    /**
     *
     * @param we
     */
    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    /**
     *
     * @param we
     */
    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.JMIConfig_almacen) {
            GuiAlmacen ConfigAlmacen = new GuiAlmacen();
            ConfigAlmacen.setVisible(true);
        }

        if (ae.getSource() == this.moduloClientes) {
            this.index = getIndexOfTab("Clientes");

            if (this.index == -1) {
                ModuloClientes panelClientes = new ModuloClientes();
                this.JPTemp = panelClientes.obtenerModulo();
                addTabbedPane(this.JPTemp, "Clientes");
            } else {
                JTabsNiconEnterprise.setSelectedIndex(this.index);
            }
        }

        if (ae.getSource() == this.moduloEmpleados) {
            this.index = getIndexOfTab("Empleados");
            if (this.index == -1) {
                ModuloEmpleados adminEmpleados = new ModuloEmpleados();
                this.JPTemp = adminEmpleados.getGuiPanel();
                addTabbedPane(this.JPTemp, "Empleados");
            } else {
                JTabsNiconEnterprise.setSelectedIndex(this.index);
            }
        }

        if (ae.getSource() == this.moduloProveedores) {
            this.index = getIndexOfTab("Proveedores");
            if (this.index == -1) {
                ModuloProveedores adminProveedores = new ModuloProveedores();
                this.JPTemp = adminProveedores.obtenerModulo();
                addTabbedPane(this.JPTemp, "Proveedores");
            } else {
                JTabsNiconEnterprise.setSelectedIndex(this.index);
            }
        }

        if (ae.getSource() == this.jmVerEmpresa) {
            VisorEmpresa visor = new VisorEmpresa();
            visor.mostrarVisor();
        }

        if (ae.getSource() == this.jmSalir) {
            salir();
        }

        if (ae.getSource() == this.jmDesconectar) {
            desconectar();
        }

        if (ae.getSource() == this.jmConectar) {
            try {
                conectar();
            } catch (SQLException ex) {
                Logger.getLogger(ModuloPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ae.getSource() == this.jmDelConfConector) {
            eliminarArchivoConeccion();
        }

        if (ae.getSource() == this.jmCrearConfConector) {
            ModuleConector addConfig = new ModuleConector();
            addConfig.setVisible(true);
        }

        if (ae.getSource() == this.jmVerConector) {
            verDetallesConector();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}