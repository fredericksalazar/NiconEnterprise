/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */

package nicon.enterprise.gui;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
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
import nicon.enterprise.libCore.api.obj.Empresa;
import nicon.enterprise.libCore.dao.ConfigConectorDAO;
import nicon.enterprise.libCore.obj.ConfigConector;

/**
 * Moudlo Principal es JFrame que perite la administracion de todos los modulos
 * del sistema, se encarga de posisionar accesos a los diferentes modulos del
 * sistema Hereda de <b> JFrame </b>
 *
 * @author frederick
 */
public class ModuloPrincipal extends JFrame implements WindowListener, ActionListener, KeyListener {

    private static final long serialVersionUID = 3L;
    private static int TotalOpenTabs;
    private static String Icons;
    private int index;
    private boolean operacion;
    private String opcionSalida;
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
    private JPanel panelAcciones;
    private JLabel jlRazonSocial;
    private JLabel jlSlogan;
    private JLabel jlNit;
    private JLabel jlDireccion;
    private JLabel JLTelefono;
    private JLabel JLClientes;
    private JLabel JLempleados;
    private JLabel jlProveedores;
    private JLabel estadoServidor;
    private JButton moduloClientes;
    private JButton moduloEmpleados;
    private JButton moduloProveedores;
    private static JTabbedPane JTabsNiconEnterprise;
    private JTextField guiEstadoServidor;
    private TitledBorder borderAcciones;
    private Empresa datosEmpresa;
    private Conection coneccion;
    private ConfigConector conector;
    private ConfigConectorDAO conectorDAO;
    private ActividadesPendientes panelPendientes;
    private ModuleConector module;
    private JScrollPane scrollAcciones;

    /**
     * Este metodo constructor se encarga de inicializar todos los componentes
     * de la interfaz grafica y de la ventana principal, crea el Dash y ajusta
     * el sistema grafico, recibe como parametro un objeto del Tipo <b> Empresa
     * </b>
     * que se utilizará para mostrar información básica de la empresa en la
     * interfaz principal.
     *
     * @param Empresa Informacion
     */
    public ModuloPrincipal(Empresa Informacion) {

        setTitle(GlobalConfigSystem.getAplicationTitle());
        setSize(1320, 730);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setJMenuBar(MenuBar);
        this.datosEmpresa = Informacion;
        Icons = GlobalConfigSystem.getIconsPath();
        coneccion = Conection.obtenerInstancia();
        crearModulo();
        createDash();
    }

    /**
     * Iniciliza, configura y posisiona todos los elementos graficos que
     * componen el Gui de NiconEnterprise.
     */
    private void crearModulo() {

        MenuBar = new JMenuBar();
        MenuBar.setFont(GlobalConfigSystem.getFontAplicationText());
        MenuBar.setBounds(0, 0, 1320, 25);

        menuArchivo = new JMenu("Archivo");
        menuArchivo.setFont(GlobalConfigSystem.getFontAplicationText());
        menuArchivo.setToolTipText("Abre herramientas de NiconEnterprise");
        menuArchivo.setMnemonic('A');

        menuConf = new JMenu("Configuración");
        menuConf.setFont(GlobalConfigSystem.getFontAplicationText());
        menuConf.setToolTipText("Accede a las configuraciones básicas de la aplicación");
        menuConf.setMnemonic('C');

        menuEmpresa = new JMenu("Mi Empresa");
        menuEmpresa.setFont(GlobalConfigSystem.getFontAplicationText());
        menuEmpresa.setToolTipText("Muestra todas las herramientas de configuracion de la empresa");
        menuEmpresa.setMnemonic('E');

        menuAlmacen = new JMenu("Almacenes");
        menuAlmacen.setFont(GlobalConfigSystem.getFontAplicationText());

        JMIConfig_almacen = new JMenuItem("Configurar");
        JMIConfig_almacen.setFont(GlobalConfigSystem.getFontAplicationText());
        JMIConfig_almacen.setToolTipText("Configurar sistema de almacenes");
        JMIConfig_almacen.setMnemonic('C');
        JMIConfig_almacen.addActionListener(this);

        jmVerEmpresa = new JMenuItem("- Ver mi Empresa");
        jmVerEmpresa.setFont(GlobalConfigSystem.getFontAplicationText());
        jmVerEmpresa.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuInformation.png")));
        jmVerEmpresa.setToolTipText("Abre el visor de datos mostrando todos los datos actuales de la empresa");
        jmVerEmpresa.addActionListener(this);

        jmCrearConfConector = new JMenuItem("- Nuevo Conector");
        jmCrearConfConector.setFont(GlobalConfigSystem.getFontAplicationText());
        jmCrearConfConector.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconAdd.png")));
        jmCrearConfConector.setToolTipText("Permite conectar el sistema a un nuevo servidor de datos NiconEnterprise");
        jmCrearConfConector.addActionListener(this);

        jmDelConfConector = new JMenuItem("- Eliminar conector");
        jmDelConfConector.setFont(GlobalConfigSystem.getFontAplicationText());
        jmDelConfConector.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconRemove.png")));
        jmDelConfConector.setToolTipText("Elimina el archivo conector.conf que permite configurar la coneccion a la fuente de datos");
        jmDelConfConector.addActionListener(this);

        jmVerConector = new JMenuItem("- Ver Conector");
        jmVerConector.setFont(GlobalConfigSystem.getFontAplicationText());
        jmVerConector.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuInformation.png")));
        jmVerConector.setToolTipText("Muestra detalles de la coneccion actual a la fuente de datos");
        jmVerConector.addActionListener(this);

        jmConectar = new JMenuItem("- Conectar al servidor");
        jmConectar.setFont(GlobalConfigSystem.getFontAplicationText());
        jmConectar.setToolTipText("Permite conectar el sistema al servidor de datos");
        jmConectar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconServerOnline.png")));
        jmConectar.addActionListener(this);

        jmDesconectar = new JMenuItem("- Desconectar el servidor");
        jmDesconectar.setFont(GlobalConfigSystem.getFontAplicationText());
        jmDesconectar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconServerOffline.png")));
        jmDesconectar.setToolTipText("Cierrra la conección entre el servidor de datos y el NiconEnterprise");
        jmDesconectar.addActionListener(this);

        jmSalir = new JMenuItem("- Salir de NiconEnterprise");
        jmSalir.setFont(GlobalConfigSystem.getFontAplicationText());
        jmSalir.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconShutdown.png")));
        jmSalir.setToolTipText("Salir de " + GlobalConfigSystem.getAplicationTitle());
        jmSalir.addActionListener(this);

        menuArchivo.add(jmCrearConfConector);
        menuArchivo.add(jmVerConector);
        menuArchivo.add(jmDelConfConector);
        menuArchivo.addSeparator();
        menuArchivo.add(jmConectar);
        menuArchivo.add(jmDesconectar);
        menuArchivo.addSeparator();
        menuArchivo.add(jmSalir);

        menuAlmacen.add(JMIConfig_almacen);

        menuEmpresa.add(jmVerEmpresa);
        menuConf.add(menuAlmacen);

        MenuBar.add(menuArchivo);
        MenuBar.add(menuConf);
        MenuBar.add(menuEmpresa);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(GlobalConfigSystem.getBackgroundAplication());
        mainPanel.setBounds(0, 25, 1320, 720);
        mainPanel.add(MenuBar);

        jlRazonSocial = new JLabel(datosEmpresa.getRazon_Social());
        jlRazonSocial.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlRazonSocial.setBounds(10, 10, 700, 45);
        jlRazonSocial.setFont(GlobalConfigSystem.getFontAplicationTitle());

        jlSlogan = new JLabel(datosEmpresa.getSlogan());
        jlSlogan.setFont(GlobalConfigSystem.getFontAplicationText());
        jlSlogan.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlSlogan.setBounds(10, 50, 300, 19);

        jlNit = new JLabel("Nit:  " + datosEmpresa.getNit());
        jlNit.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlNit.setFont(GlobalConfigSystem.getFontAplicationText());
        jlNit.setBounds(10, 80, 300, 19);

        jlDireccion = new JLabel(datosEmpresa.getDireccion() + " / " + datosEmpresa.getCiudad() + "  / " + datosEmpresa.getDepartamento());
        jlDireccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
        jlDireccion.setBounds(10, 100, 300, 19);

        JLTelefono = new JLabel(datosEmpresa.getTelefono_fijo() + " / " + datosEmpresa.getTelefono_movil());
        JLTelefono.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        JLTelefono.setFont(GlobalConfigSystem.getFontAplicationText());
        JLTelefono.setBounds(10, 120, 200, 20);

        panelDash = new JPanel();
        panelDash.setLayout(null);
        panelDash.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelDash.setBounds(0, 0, 1280, 680);

        JTabsNiconEnterprise = new JTabbedPane();
        JTabsNiconEnterprise.setFont(GlobalConfigSystem.getFontAplicationText());
        JTabsNiconEnterprise.setBounds(10, 20, 1280, 650);
        JTabsNiconEnterprise.addTab("Home", panelDash);

        mainPanel.add(JTabsNiconEnterprise);

        addWindowListener(this);
        getContentPane().add(MenuBar);
        getContentPane().add(mainPanel);
    }

    /**
     * este metodo se encarga de crear el panel del Dash, este panel es usado
     * para brindar un rápido acceso al usuari a componentes comunes del
     * sistema.
     */
    private void createDash() {
        panelPendientes = new ActividadesPendientes();

        borderAcciones = BorderFactory.createTitledBorder("Módulos");
        borderAcciones.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());
        borderAcciones.setTitleFont(GlobalConfigSystem.getFontAplicationText());

        panelAcciones = new JPanel();
        panelAcciones.setLayout(null);
        panelAcciones.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelAcciones.setPreferredSize(new Dimension(700, 700));

        scrollAcciones = new JScrollPane(panelAcciones, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollAcciones.setBounds(10, 160, 700, 420);
        scrollAcciones.getViewport().setView(panelAcciones);
        scrollAcciones.setBorder(borderAcciones);

        moduloClientes = new JButton();
        moduloClientes.setBounds(60, 50, 100, 100);
        moduloClientes.addActionListener(this);
        moduloClientes.setMnemonic('1');
        moduloClientes.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconClient.png")));
        moduloClientes.setToolTipText("Acceder al módulo de clientes");

        moduloEmpleados = new JButton();
        moduloEmpleados.setBounds(180, 50, 100, 100);
        moduloEmpleados.setMnemonic('2');
        moduloEmpleados.addActionListener(this);
        moduloEmpleados.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconEmpleado.png")));
        moduloEmpleados.setToolTipText("Acceder al módulo de empleados");

        moduloProveedores = new JButton();
        moduloProveedores.setBounds(300, 50, 100, 100);
        moduloProveedores.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconProveedor.png")));
        moduloProveedores.setToolTipText("Permite acceder al módulo de administracion de proveedores");
        moduloProveedores.addActionListener(this);

        JLClientes = new JLabel("Clientes");
        JLClientes.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        JLClientes.setFont(GlobalConfigSystem.getFontAplicationText());
        JLClientes.setBounds(80, 160, 200, 25);

        JLempleados = new JLabel("Empleados");
        JLempleados.setBounds(190, 160, 150, 25);
        JLempleados.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        JLempleados.setFont(GlobalConfigSystem.getFontAplicationText());

        jlProveedores = new JLabel("Proveedores");
        jlProveedores.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlProveedores.setFont(GlobalConfigSystem.getFontAplicationText());
        jlProveedores.setBounds(307, 160, 150, 25);

        panelAcciones.add(moduloClientes);
        panelAcciones.add(JLClientes);
        panelAcciones.add(moduloEmpleados);
        panelAcciones.add(JLempleados);
        panelAcciones.add(moduloProveedores);
        panelAcciones.add(jlProveedores);

        estadoServidor = new JLabel("Estado del Servidor");
        estadoServidor.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        estadoServidor.setFont(GlobalConfigSystem.getFontAplicationText());
        estadoServidor.setBounds(1070, 568, 300, 20);

        guiEstadoServidor = new JTextField();
        guiEstadoServidor.setEditable(false);
        guiEstadoServidor.setBackground(GlobalConfigSystem.getColorActiveStatus());
        guiEstadoServidor.setBounds(1210, 562, 30, 30);

        panelDash.add(jlRazonSocial);
        panelDash.add(jlSlogan);
        panelDash.add(jlNit);
        panelDash.add(jlDireccion);
        panelDash.add(JLTelefono);
        panelDash.add(scrollAcciones);
        panelDash.add(estadoServidor);
        panelDash.add(guiEstadoServidor);
        panelDash.add(panelPendientes.obetenerPanelActividades());
    }

    /**
     * Este metodo permite agregar una nueva pestaña al gestor de pestañas del
     * Dash, recibe como parametros esenciales el Panel ue se va a agregar y el
     * nombre que rescibirá.
     *
     * @param panel
     * @param name
     */
    private void addTabbedPane(JPanel panel, String name) {
        TotalOpenTabs = JTabsNiconEnterprise.getTabCount();
        JTabsNiconEnterprise.addTab(name, panel);
        JTabsNiconEnterprise.setSelectedIndex(TotalOpenTabs);
    }

    /**
     * Este metodo permite eliminar una pestaña del gestor de pestañas del dash,
     * recibe como parametros el nombre de la pestaña que se desea eliminar.
     *
     * @param nombre
     */
    public static void removeTab(String nombre) {
        JTabsNiconEnterprise.remove(getIndexOfTab(nombre));
    }

    /**
     * Este metodo obtiene el indice de una pestaña que se encuentre dentro del
     * Tab, recibe como parametro el nombre de la pestaña a buscar.
     *
     * @param Name
     * @return int index
     */
    public static int getIndexOfTab(String Name) {
        return JTabsNiconEnterprise.indexOfTab(Name);
    }

    /**
     * Este metodo brinda un metodo de salida del sistema limpio y eficiente, en
     * el cual el usuario debe decidir si esta seguro de salir o no.
     */
    private void salir() {
        opcionSalida = (String) JOptionPane.showInputDialog(rootPane, "Esta a punto de cerrar " + GlobalConfigSystem.getAplicationTitle() + ", ¿Esta seguro que desea salir?\n" + "                      Presione S para salir o N para cancelar", GlobalConfigSystem.getAplicationTitle(),JOptionPane.QUESTION_MESSAGE,new ImageIcon(getClass().getResource(Icons+"NiconHelpOption.png")),null,null);
            if (opcionSalida.equals("")) {
                    JOptionPane.showMessageDialog(this.rootPane, "No ha Ingresado un parametro correcto, verifique e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0, new ImageIcon(getClass().getResource(Icons + "NiconError.png")));
            }
            if ((opcionSalida.equals("S")) || (opcionSalida.equals("s"))) {
                System.exit(0);
            }
    }

    /**
     * Este metodo permite desconectar el fronEnd de la fuente de datos, esta operacion de desconeccion debe ser
     * realizada solo en casos necesarios pues inhabilitaria las transacciones de datos entre le front end y el backend.
     */
    private void desconectar() {
        operacion = coneccion.desconectar();
        if (operacion) {
            guiEstadoServidor.setBackground(GlobalConfigSystem.getColorInactiveStatus());
            JOptionPane.showMessageDialog(this.rootPane, "El sistema se ha desconectado del servidor exitosamente.", GlobalConfigSystem.getAplicationTitle(), 2, new ImageIcon(getClass().getResource(Icons + "NiconWarning.png")));
        }
    }

    /**
     * Este metodo permite reestablecer la coneccion del fronEnd con la fuente de datos.
     * @throws SQLException 
     */
    private void conectar() {
        try {
            operacion = coneccion.conectar();
                if (operacion) {
                    guiEstadoServidor.setBackground(GlobalConfigSystem.getColorActiveStatus());
                    JOptionPane.showMessageDialog(rootPane, "El sistema se ha conectado exitosamente al servidor.", GlobalConfigSystem.getAplicationTitle(),JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Icons+"NiconPositive.png")));
                }
        } catch (SQLException ex) {
            Logger.getLogger(ModuloPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Oucrrió un ERROR y no se puede establecer la conección con la fuente de datos.", GlobalConfigSystem.getAplicationTitle(), JOptionPane.INFORMATION_MESSAGE, new javax.swing.ImageIcon(getClass().getResource(Icons+"NiconError.png")));
        }
    }
    
    /**
     * Este metodo permite que el usuario pueda eliminar el archivo de configuracion de coneccion con la fuente de da
     * datos, 
     */
    private void eliminarArchivoConeccion() {
        opcionSalida = (String) JOptionPane.showInputDialog(rootPane, "¿ Esta a punto de ELIMINAR el archivo ./config/Conector.conf que define la conección\n con la fuente de datos, ¿ Esta seguro que desea continuar?:\n Presione S para eliminar o N para cancelar.", GlobalConfigSystem.getAplicationTitle(),JOptionPane.QUESTION_MESSAGE,new ImageIcon(getClass().getResource(Icons+"NiconWarning.png")),null,null);
        if ((opcionSalida.equals("S")) || (opcionSalida.equals("s"))) {
            conectorDAO = new ConfigConectorDAO();
            if (conectorDAO.deleteConfigFile()) {
                JOptionPane.showMessageDialog(rootPane, "El archivo Conector.conf ha sido eliminado exitosamente, por favor configure nuevos parametros de coneccion\no espere a que el sistema configure un automaticamente", GlobalConfigSystem.getAplicationTitle(),JOptionPane.INFORMATION_MESSAGE,new javax.swing.ImageIcon(getClass().getResource(Icons+"NiconPositive.png")));
            }
        }
    }
    
    /**
     * Este metodo permite ver informacion técnica de la coneccion del frontEnd con la fuente de datos, activa la
     * vista de ModuleConector la cual permite visualizar dicha información.
     */
    private void verDetallesConector() {
        try {
            conectorDAO = new ConfigConectorDAO();
            conector = conectorDAO.loadConfig();
            module = new ModuleConector(conector);
            module.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ModuloPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "NO se pudo cargar Conector.conf ocurrio un error:\n\n"+ex, GlobalConfigSystem.getAplicationTitle(),JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource(Icons+"NiconError.png")));
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
        
        if (ae.getSource() == JMIConfig_almacen) {
            GuiAlmacen ConfigAlmacen = new GuiAlmacen();
            ConfigAlmacen.setVisible(true);
        }

        if (ae.getSource() == moduloClientes) {
            index = getIndexOfTab("Clientes");
            if (index == -1) {
                ModuloClientes panelClientes = new ModuloClientes();
                addTabbedPane(panelClientes.obtenerModulo(),"Clientes");
            } else {
                JTabsNiconEnterprise.setSelectedIndex(index);
            }
        }

        if (ae.getSource() == moduloEmpleados) {
            index = getIndexOfTab("Empleados");
            if (index == -1) {
                ModuloEmpleados adminEmpleados = new ModuloEmpleados();
                addTabbedPane(adminEmpleados.getGuiPanel(), "Empleados");
            } else {
                JTabsNiconEnterprise.setSelectedIndex(this.index);
            }
        }

        if (ae.getSource() == moduloProveedores) {
            index = getIndexOfTab("Proveedores");
            if (index == -1) {
                ModuloProveedores adminProveedores = new ModuloProveedores();
                addTabbedPane(adminProveedores.obtenerModulo(), "Proveedores");
            } else {
                JTabsNiconEnterprise.setSelectedIndex(index);
            }
        }

        if (ae.getSource() == jmVerEmpresa) {
            VisorEmpresa visor = new VisorEmpresa();
            visor.mostrarVisor();
        }

        if (ae.getSource() == jmSalir) {
            salir();
        }

        if (ae.getSource() == jmDesconectar) {
            desconectar();
        }

        if (ae.getSource() == jmConectar) {
                conectar();            
        }

        if (ae.getSource() == jmDelConfConector) {
            eliminarArchivoConeccion();
        }

        if (ae.getSource() == jmCrearConfConector) {
            ModuleConector addConfig = new ModuleConector();
            addConfig.setVisible(true);
        }

        if (ae.getSource() == jmVerConector) {
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