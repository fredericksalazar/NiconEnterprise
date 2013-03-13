/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */

package nicon.enterprise.gui.Clientes;

import nicon.enterprise.gui.Clientes.activities.CrearTipoActividad;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

import nicon.enterprise.gui.Clientes.activities.Actividad_Administrador;
import nicon.enterprise.gui.Clientes.activities.Actividad_Crear;
import nicon.enterprise.gui.ModuloPrincipal;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.ClienteDAO;
import nicon.enterprise.libCore.api.obj.Cliente;
import nicon.enterprise.memData.BasicDataAplication;

/**
 * Este es modulo de administracion de clientes que permite administrar todos
 * los componentes y acciones que el usuario pueda ejecutar en relacion con la
 * información de todos los clientes,
 *
 * @author frederick
 */
public class Clientes_Module extends JPanel implements ActionListener, MouseListener {

    private static final long serialVersionUID = 3L;
    
    private JPanel moduloClientes;
    private JPanel panelInformacion;
    private JPanel panelHerramientas;
    private JPanel panelBusqueda;
    
    private JMenuBar menuClientes;
    private JMenu jmArchivo;
    private JMenu jmClientes;
    private JMenu jmExportar;
    private JMenu jmVer;
    private JMenu jmActividades;
    
    private JMenuItem jmCerrar;
    private JMenuItem crearCliente;
    private JMenuItem eliminarCliente;
    private JMenuItem editarCliente;
    private JMenuItem busquedaID;
    private JMenuItem jmListarTodo;
    private JMenuItem jmListarPorID;
    private JMenuItem jmOrdenarAsc;
    private JMenuItem jmOrdenarDesc;
    private JMenuItem jmVerPrimero;
    private JMenuItem jmVerUltimo;
    private JMenuItem jmAbrirActividades;
    private JMenuItem jmCrearTipoActividad;
    
    private static DefaultTableModel modelo;
    private static JTable tablaClientes;
    private JScrollPane ScrollTable;
    private TitledBorder BorderInf;
    private TitledBorder BorderTool;
    private TitledBorder BorderSearch;
    
    private JLabel JLTel_fijo;
    private JLabel JLTel_movil;
    private JLabel JLTel_alternativo;
    private JLabel JLDireccion;
    private JLabel JLCiudad;
    private JLabel JLEmail;
    private JLabel JLListCity;
    private JLabel jlContacto;
    private JLabel jlUbicacion;
    
    private static JLabel nombres;
    private static JLabel identificacion;
    private static JLabel JLfecha_registro;
    private static JLabel jlTotalRegistros;
    private static JLabel telFijo;
    private static JLabel telMovil;
    private static JLabel telAlternativo;
    private static JLabel direccion;
    private static JLabel ciudad;
    private static JLabel email;
    private static JLabel fechaRegistro;
    
    private JTextField JTSearchData;
    private JButton JBCrear;
    private JButton JBEditar;
    private JButton JBDelete;
    private JButton jbCrearActividad;
    private ButtonGroup JGBOptions;
    private JRadioButton JRBNombres;
    private JRadioButton JRBIdentificacion;
    private JSeparator SeparatorTools;
    private JComboBox JCListCity;    
    
    private static ArrayList listaClientes;
    private static Cliente cliente;
    private static ClienteDAO clienteDAO;
    private static String[] vectorDatos;
    private static String Icons;
    
    private int selectedSearch;
    private int index;
    private boolean stateOP;
    
    private Actividad_Crear asignacion;
    private JSeparator separator1;
    private JSeparator separator2;

    public Clientes_Module() {
        cliente = new Cliente();
        this.selectedSearch = 0;
        Icons = GlobalConfigSystem.getIconsPath();
        clienteDAO = new ClienteDAO();
        listaClientes = new ArrayList();
        vectorDatos = new String[3];
        crearInterfaz();
        listarClientes();
        seleccionarPrimerRegistro();
    }

    private void crearInterfaz() {
        BorderInf = BorderFactory.createTitledBorder("");
        BorderInf.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());

        moduloClientes = new JPanel();
        moduloClientes.setBackground(GlobalConfigSystem.getBackgroundAplication());
        moduloClientes.setLayout(null);

        menuClientes = new JMenuBar();
        menuClientes.setBounds(390, 0, 890, 20);

        jmArchivo = new JMenu("Archivo");
        jmArchivo.setMnemonic('A');
        jmArchivo.setFont(GlobalConfigSystem.getFontAplicationText());

        jmClientes = new JMenu("Clientes");
        jmClientes.setMnemonic('C');
        jmClientes.setFont(GlobalConfigSystem.getFontAplicationText());

        jmExportar = new JMenu("Reportes");
        jmExportar.setMnemonic('R');
        jmExportar.setFont(GlobalConfigSystem.getFontAplicationText());

        jmVer = new JMenu("Ver");
        jmVer.setMnemonic('V');
        jmVer.setFont(GlobalConfigSystem.getFontAplicationText());

        jmActividades = new JMenu("Actividades");
        jmActividades.setMnemonic('t');
        jmActividades.setFont(GlobalConfigSystem.getFontAplicationText());

        jmCerrar = new JMenuItem("- Cerrar Módulo");
        jmCerrar.setFont(GlobalConfigSystem.getFontAplicationText());
        jmCerrar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuExit.png")));
        jmCerrar.addActionListener(this);

        crearCliente = new JMenuItem("- Crear Cliente");
        crearCliente.setToolTipText("Permite crear un nuevo cliente");
        crearCliente.setFont(GlobalConfigSystem.getFontAplicationText());
        crearCliente.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuPersonAdd.png")));
        crearCliente.addActionListener(this);

        editarCliente = new JMenuItem("- Editar Cliente");
        editarCliente.setFont(GlobalConfigSystem.getFontAplicationText());
        editarCliente.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconUpdateMenu.png")));
        editarCliente.setToolTipText("Permite editar los datos de un cliente");
        editarCliente.addActionListener(this);

        eliminarCliente = new JMenuItem("- Eliminar Cliente");
        eliminarCliente.setFont(GlobalConfigSystem.getFontAplicationText());
        eliminarCliente.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconRemove.png")));
        eliminarCliente.setToolTipText("Permite eliminar un cliente del sistema");
        eliminarCliente.addActionListener(this);

        busquedaID = new JMenuItem("- Buscar Identificación");
        busquedaID.setFont(GlobalConfigSystem.getFontAplicationText());
        busquedaID.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconFind.png")));
        busquedaID.setToolTipText("Permite buscar la información de un cliente desde el desktop");
        busquedaID.addActionListener(this);

        jmListarTodo = new JMenuItem("- Listar Todos los clientes");
        jmListarTodo.setFont(GlobalConfigSystem.getFontAplicationText());
        jmListarTodo.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconPdf.png")));
        jmListarTodo.setToolTipText("Genera un reporte con todos los clientes registrados y los exporta a PDF");
        jmListarTodo.addActionListener(this);

        jmListarPorID = new JMenuItem("Listar Por Identificación");
        jmListarPorID.setToolTipText("Genera un reporte en pdf ingresando el numero de cedula de un cliente");
        jmListarPorID.addActionListener(this);

        jmOrdenarAsc = new JMenuItem("- Ordenar Lista Asc");
        jmOrdenarAsc.setFont(GlobalConfigSystem.getFontAplicationText());
        jmOrdenarAsc.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuUp.png")));
        jmOrdenarAsc.setToolTipText("Permite ordenar la lista de clientes por referencia a nombres de modo ascendente");
        jmOrdenarAsc.addActionListener(this);

        jmOrdenarDesc = new JMenuItem("- Ordenar Lista Desc");
        jmOrdenarDesc.setFont(GlobalConfigSystem.getFontAplicationText());
        jmOrdenarDesc.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuDown.png")));
        jmOrdenarDesc.addActionListener(this);

        jmVerPrimero = new JMenuItem("- Ver Primer Registro");
        jmVerPrimero.setFont(GlobalConfigSystem.getFontAplicationText());
        jmVerPrimero.setToolTipText("Muestra el primer registro de la tabla");
        jmVerPrimero.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconFirstMenu.png")));
        jmVerPrimero.addActionListener(this);

        jmVerUltimo = new JMenuItem("- Ver último registro");
        jmVerUltimo.setFont(GlobalConfigSystem.getFontAplicationText());
        jmVerUltimo.setToolTipText("Muestra el último registro de la tabla");
        jmVerUltimo.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconLastMenu.png")));
        jmVerUltimo.addActionListener(this);

        jmAbrirActividades = new JMenuItem("- Administrador de Actividades");
        jmAbrirActividades.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconTimer.png")));
        jmAbrirActividades.setFont(GlobalConfigSystem.getFontAplicationText());
        jmAbrirActividades.addActionListener(this);

        jmCrearTipoActividad = new JMenuItem("Crear Tipo Actividad");
        jmCrearTipoActividad.setToolTipText("Permite crear un nuevo tipo de actividad en el sistema");
        jmCrearTipoActividad.addActionListener(this);

        jmActividades.add(jmAbrirActividades);
        jmActividades.add(jmCrearTipoActividad);

        jmClientes.add(crearCliente);
        jmClientes.add(editarCliente);
        jmClientes.add(eliminarCliente);
        jmClientes.addSeparator();
        jmClientes.add(busquedaID);

        jmExportar.add(jmListarTodo);
        jmArchivo.add(jmCerrar);

        jmVer.add(jmOrdenarAsc);
        jmVer.add(jmOrdenarDesc);
        jmVer.addSeparator();
        jmVer.add(jmVerPrimero);
        jmVer.add(jmVerUltimo);

        menuClientes.add(jmArchivo);
        menuClientes.add(jmClientes);
        menuClientes.add(jmVer);
        menuClientes.add(jmActividades);
        menuClientes.add(jmExportar);

        modelo = new DefaultTableModel();
        modelo.addColumn("Identificación");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");

        tablaClientes = new JTable(modelo);
        tablaClientes.setRowHeight(29);
        tablaClientes.setSelectionBackground(GlobalConfigSystem.getrowSelectedTable());
        tablaClientes.setGridColor(GlobalConfigSystem.getrowSelectedTable());
        tablaClientes.setFont(GlobalConfigSystem.getFontAplicationText());
        tablaClientes.addMouseListener(this);

        ScrollTable = new JScrollPane(tablaClientes);
        ScrollTable.setToolTipText("Listado de Clientes registrados");
        ScrollTable.setBounds(0, 0, 390, 570);

        panelInformacion = new JPanel();
        panelInformacion.setLayout(null);
        panelInformacion.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelInformacion.setBounds(420, 30, 600, 495);
        panelInformacion.setBorder(BorderInf);

        nombres = new JLabel();
        nombres.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        nombres.setBounds(10, 10, 700, 40);
        nombres.setFont(GlobalConfigSystem.getFontAplicationName());

        identificacion = new JLabel();
        identificacion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        identificacion.setFont(GlobalConfigSystem.getFontAplicationSubTitle());
        identificacion.setBounds(10, 60, 150, 16);

        jlContacto = new JLabel("CONTACTO");
        jlContacto.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlContacto.setFont(GlobalConfigSystem.getFontAplicationSubTitle());
        jlContacto.setBounds(40, 130, 220, 15);

        separator1 = new JSeparator();
        separator1.setBounds(40, 155, 500, 5);

        JLTel_fijo = new JLabel("Fijo:");
        JLTel_fijo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        JLTel_fijo.setFont(GlobalConfigSystem.getFontAplicationText());
        JLTel_fijo.setBounds(153, 175, 150, 15);

        telFijo = new JLabel();
        telFijo.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        telFijo.setFont(GlobalConfigSystem.getFontAplicationText());
        telFijo.setBounds(220, 175, 300, 15);

        JLTel_movil = new JLabel("Móvil:");
        JLTel_movil.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        JLTel_movil.setFont(GlobalConfigSystem.getFontAplicationText());
        JLTel_movil.setBounds(142, 200, 150, 15);

        telMovil = new JLabel();
        telMovil.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        telMovil.setFont(GlobalConfigSystem.getFontAplicationText());
        telMovil.setBounds(220, 200, 300, 15);

        JLTel_alternativo = new JLabel("Alternativo:");
        JLTel_alternativo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        JLTel_alternativo.setFont(GlobalConfigSystem.getFontAplicationText());
        JLTel_alternativo.setBounds(100, 225, 150, 15);

        telAlternativo = new JLabel();
        telAlternativo.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        telAlternativo.setFont(GlobalConfigSystem.getFontAplicationText());
        telAlternativo.setBounds(220, 225, 300, 15);

        JLEmail = new JLabel("Email:");
        JLEmail.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        JLEmail.setFont(GlobalConfigSystem.getFontAplicationText());
        JLEmail.setBounds(140, 250, 150, 15);

        email = new JLabel();
        email.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        email.setFont(GlobalConfigSystem.getFontAplicationText());
        email.setBounds(220, 250, 600, 15);

        jlUbicacion = new JLabel("VIVE EN");
        jlUbicacion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        jlUbicacion.setFont(GlobalConfigSystem.getFontAplicationSubTitle());
        jlUbicacion.setBounds(40, 290, 220, 20);

        separator2 = new JSeparator();
        separator2.setBounds(40, 315, 500, 5);

        JLDireccion = new JLabel("Dirección:");
        JLDireccion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        JLDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
        JLDireccion.setBounds(110, 330, 150, 15);

        direccion = new JLabel();
        direccion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        direccion.setFont(GlobalConfigSystem.getFontAplicationText());
        direccion.setBounds(220, 330, 300, 15);

        JLCiudad = new JLabel("Ciudad:");
        JLCiudad.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        JLCiudad.setFont(GlobalConfigSystem.getFontAplicationText());
        JLCiudad.setBounds(127, 355, 150, 15);

        ciudad = new JLabel();
        ciudad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        ciudad.setFont(GlobalConfigSystem.getFontAplicationText());
        ciudad.setBounds(220, 355, 600, 15);

        JLfecha_registro = new JLabel("Fecha Registro:");
        JLfecha_registro.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        JLfecha_registro.setFont(GlobalConfigSystem.getFontAplicationText());
        JLfecha_registro.setBounds(40, 460, 150, 15);

        fechaRegistro = new JLabel("fecha registro");
        fechaRegistro.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        fechaRegistro.setFont(GlobalConfigSystem.getFontAplicationText());
        fechaRegistro.setBounds(160, 460, 600, 15);

        jlTotalRegistros = new JLabel();
        jlTotalRegistros.setBounds(100, 565, 800, 30);
        jlTotalRegistros.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlTotalRegistros.setFont(GlobalConfigSystem.getFontAplicationText());

        panelInformacion.add(nombres);
        panelInformacion.add(identificacion);
        panelInformacion.add(jlContacto);
        panelInformacion.add(separator1);
        panelInformacion.add(JLTel_fijo);
        panelInformacion.add(telFijo);
        panelInformacion.add(JLTel_movil);
        panelInformacion.add(telMovil);
        panelInformacion.add(JLTel_alternativo);
        panelInformacion.add(telAlternativo);
        panelInformacion.add(JLEmail);
        panelInformacion.add(email);

        panelInformacion.add(jlUbicacion);
        panelInformacion.add(separator2);
        panelInformacion.add(JLDireccion);
        panelInformacion.add(direccion);
        panelInformacion.add(JLCiudad);
        panelInformacion.add(ciudad);
        panelInformacion.add(JLfecha_registro);
        panelInformacion.add(fechaRegistro);

        BorderTool = BorderFactory.createTitledBorder("");
        BorderTool.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());

        panelHerramientas = new JPanel();
        panelHerramientas.setLayout(null);
        panelHerramientas.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelHerramientas.setBounds(1020, 30, 250, 580);
        panelHerramientas.setBorder(this.BorderTool);

        JBCrear = new JButton("Nuevo");
        JBCrear.setBounds(10, 10, 115, 30);
        JBCrear.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconAdd.png")));
        JBCrear.setToolTipText("Crear un nuevo cliente en el sistema");
        JBCrear.addActionListener(this);

        JBEditar = new JButton("Editar");
        JBEditar.setBounds(128, 10, 115, 30);
        JBEditar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconUpdateMenu.png")));
        JBEditar.setToolTipText("Editar los datos del cliente seleccionado");
        JBEditar.addActionListener(this);

        JBDelete = new JButton("Eliminar");
        JBDelete.setBounds(10, 50, 115, 30);
        JBDelete.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconRemove.png")));
        JBDelete.setToolTipText("Eliminar un cliente de la base de datos");
        JBDelete.addActionListener(this);

        jbCrearActividad = new JButton("Actividad");
        jbCrearActividad.setToolTipText("Permite asignar una Nueva actividad al cliente seleccionado...");
        jbCrearActividad.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconAdd.png")));
        jbCrearActividad.addActionListener(this);
        jbCrearActividad.setBounds(128, 50, 115, 30);

        SeparatorTools = new JSeparator();
        SeparatorTools.setBounds(15, 505, 220, 5);
        SeparatorTools.setBackground(Color.lightGray);

        JLListCity = new JLabel("Mostrar Clientes de:");
        JLListCity.setFont(GlobalConfigSystem.getFontAplicationText());
        JLListCity.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        JLListCity.setBounds(15, 510, 200, 25);

        JCListCity = new JComboBox(BasicDataAplication.getListCity());
        JCListCity.addItem("Mostrar todas");
        JCListCity.setFont(GlobalConfigSystem.getFontAplicationText());
        JCListCity.setBounds(15, 535, 218, 30);
        JCListCity.setToolTipText("Mostrar los clientes de una ciudad seleccionada en la lista");
        JCListCity.addActionListener(this);

        panelHerramientas.add(JBCrear);
        panelHerramientas.add(JBEditar);
        panelHerramientas.add(JBDelete);
        panelHerramientas.add(SeparatorTools);
        panelHerramientas.add(JLListCity);
        panelHerramientas.add(JCListCity);
        panelHerramientas.add(jbCrearActividad);

        BorderSearch = BorderFactory.createTitledBorder("Busqueda:");
        BorderSearch.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());

        panelBusqueda = new JPanel();
        panelBusqueda.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelBusqueda.setLayout(null);
        panelBusqueda.setBounds(420, 523, 600, 100);
        panelBusqueda.setBorder(BorderSearch);

        JGBOptions = new ButtonGroup();

        JRBNombres = new JRadioButton("Por Nombres");
        JRBNombres.setBounds(10, 30, 260, 30);
        JRBNombres.setFont(GlobalConfigSystem.getFontAplicationText());
        JRBNombres.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        JRBNombres.addActionListener(this);

        JRBIdentificacion = new JRadioButton("Por Cédula");
        JRBIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
        JRBIdentificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        JRBIdentificacion.setBounds(10, 55, 260, 30);
        JRBIdentificacion.addActionListener(this);

        JGBOptions.add(JRBNombres);
        JGBOptions.add(JRBIdentificacion);

        JTSearchData = new JTextField("Ingrese los datos a Buscar:");
        JTSearchData.setBounds(190, 45, 400, 28);
        JTSearchData.setToolTipText("Ingrese el Nombre o la cédula para buscar información.");
        JTSearchData.setFont(GlobalConfigSystem.getFontAplicationTextItalic());
        JTSearchData.setForeground(Color.darkGray);
        JTSearchData.addMouseListener(this);
        JTSearchData.addKeyListener(new KeyAdapter() {
            /**
             * en esta caja de busqueda el usuario ingresará la informacion a
             * buscar de un cliente recibiendo parametros como los nombre o el
             * numero de identificacion de una persona, la casa de busqueda
             * posee un KeyListener que le permite escuchar cuando el usuario
             * presiona la tecla ENTE(VK_ENTER) en cuyo casi indica el incio de
             * la busqueda de informacion.
             */
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (selectedSearch == 0) {
                        JOptionPane.showMessageDialog(null, "Por favor seleccione un criterio de busqueda\n      -1 Por nombres     -2 por cédula", GlobalConfigSystem.getAplicationTitle(), JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource(Icons + "NiconError.png")));
                    } else {
                        /**
                         * el Usuaurio ha seleccionado la busqueda por la
                         * identificacion del cliente.
                         */
                        if (selectedSearch == 1) {
                            buscar(1, JTSearchData.getText());
                        }
                        /**
                         * el Usuario ha seleccionado la buqueda por nombres.
                         */
                        if (selectedSearch == 2) {
                            buscar(2, JTSearchData.getText());
                        }
                    }
                }
            }
        });

        panelBusqueda.add(JTSearchData);
        panelBusqueda.add(JRBNombres);
        panelBusqueda.add(JRBIdentificacion);

        moduloClientes.add(menuClientes);
        moduloClientes.add(ScrollTable);
        moduloClientes.add(panelInformacion);
        moduloClientes.add(panelHerramientas);
        moduloClientes.add(panelBusqueda);
        moduloClientes.add(jlTotalRegistros);
    }

    /**
     * Este metodo retorna el panel del modulo de clientes ya creado
     * anteriormente, para ser usado sobre un Jframe un JDialog u otro tipo de
     * Contenedor
     *
     * @return JPanel moudloClientes
     */
    public JPanel obtenerModulo() {
        return moduloClientes;
    }

    /**
     * Este metodo permite cargar los datos almacenados en la tabla de clientes
     * en la fuente de datos en la entidad de Clientes, recibe un ArrayList con
     * objetos de tipo clientes que serán cargados al modelo de datos de la
     * tabla y que posteriormente serán cargado a la vista tablaClientes.
     *
     * @param listaClientes
     */
    private static void cargarDatos(ArrayList listaClientes) {
        for (int i = 0; i < listaClientes.size(); i++) {
            cliente = (Cliente) listaClientes.get(i);
            vectorDatos[0] = cliente.getIdentificacion();
            vectorDatos[1] = cliente.getNombres();
            vectorDatos[2] = cliente.getApellidos();
            modelo.addRow(vectorDatos);
        }
        actualizarContador();
    }

    /**
     * Este metodo permite actualizar los datos del contador de registros de la
     * tabla de cliente.
     */
    private static void actualizarContador() {
        jlTotalRegistros.setText("Total registros:" + String.valueOf(modelo.getRowCount()));
    }

    /**
     * Este metodo permite recargar la tabla de clientes nuevamente con datos
     * nuevos desde la base de datos, limpia el modelo de datos de todos los
     * registros de igual forma que limpia el ArrayList.
     */
    public static void recargarDatos() {
        try {
            modelo.getDataVector().removeAllElements();
            listaClientes.clear();
            listaClientes = clienteDAO.listarClientes();
            cargarDatos(listaClientes);
        } catch (Exception e) {
            System.err.println("Error en GuiCliente.ReloadDataClient() detail:\n" + e);
        }
    }

    /**
     * retorna el cliente seleccionado en la tabla de clientes, si hay fila selccionada en la tabla obtiene el objeto
     * de tipo Cliente del ArrayList de clientes obtenido del API ClienteDAO, sino hay fila selccionada el cliente es
     * null..
     *
     * @return Cliente cliente
     */
    public Cliente obtenerSeleccionado() {
        index = obtenerIndiceSeleccionado();
            if (index >= 0) {
                cliente = (Cliente) listaClientes.get(index);
            } else {
                cliente = null;
            }
        return cliente;
    }

    /**
     * obtiene un entero con el indice apuntando a la fila actualmente seleccionada en la tabla de clientes
     * @return 
     */
    private int obtenerIndiceSeleccionado() {
        index = tablaClientes.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(null, "NO ha seleccionado ninguna fila en la tabla de clientes", GlobalConfigSystem.getAplicationTitle(),JOptionPane.ERROR_MESSAGE,new javax.swing.ImageIcon(getClass().getResource(Icons+"NiconError.png")));
            }
        return index;
    }

    /**
     * este metodo es el encargado de ajustar las variables de tipo JLabel mostrando la informacion del objeto de
     * tipo Cliente recibido, es invocado al momento de buscar datos, o cuando el usuario selecciona un objeto de la
     * lsta de la tabla de clientes.
     * 
     * @param cliente 
     */
    protected static void mostrarDatos(Cliente cliente) {
        nombres.setText(cliente.getNombres() + " " + cliente.getApellidos());
        identificacion.setText(cliente.getIdentificacion());
        telFijo.setText(cliente.getTelefono_fijo());
        telMovil.setText(cliente.getTelefono_movil());
        telAlternativo.setText(cliente.getTelefono_alternativo());
        direccion.setText(cliente.getDireccion());
        ciudad.setText(cliente.getCiudad() + " - " + cliente.getProvincia());
        email.setText(cliente.getEmail());
        fechaRegistro.setText(cliente.getFecha_registro());
    }

    /**
     * Interfaz para la busqueda de datos de un cliente contra la fuente de datos, recibe como parametros la opcion de
     * busqueda y los datos a buscar dentro de la fuente de datos.
     * 
     * @param opcionBusqueda
     * @param datos 
     */
    public void buscar(int opcionBusqueda, String datos) {
        if (opcionBusqueda == 1) {
            buscarInformacionPorID(datos);
        }
        if (opcionBusqueda == 2) {
            buscarInformacionPorNombres(datos);
        }
    }

    private void buscarInformacionPorID(String ID) {
        try {
            cliente = clienteDAO.buscarPorIdentificacion(ID);
            if (cliente != null) {
                mostrarDatos(cliente);
                seleccionarCliente(cliente.getIdentificacion());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron registros con los parametros Ingresados", GlobalConfigSystem.getAplicationTitle(),JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Icons + "NiconError.png")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes_Module.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarInformacionPorNombres(String Nombres) {
        try {
            cliente = clienteDAO.buscarPorNombres(Nombres);
            if (cliente != null) {
                mostrarDatos(cliente);
                seleccionarCliente(cliente.getIdentificacion());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron registros con los parametros Ingresados", GlobalConfigSystem.getAplicationTitle(),JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Icons + "NiconError.png")));
              }
        } catch (SQLException ex) {
            Logger.getLogger(Clientes_Module.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * metodo que permite seleciconar y mostrar el primer registro de tabla de la lista de clientes.
     */
    private void seleccionarPrimerRegistro() {
        tablaClientes.changeSelection(0, 0, false, false);
        mostrarDatos(obtenerSeleccionado());
    }

    /**
     * metodo que permite seleccionar y mostrar el utlimo registro de la tabla de la lista de clientes.
     */
    private void seleccionarUltimoRegistro() {
        tablaClientes.changeSelection(modelo.getRowCount() - 1, 0, false, false);
        mostrarDatos(obtenerSeleccionado());
    }

    /**
     * Este metodo permite cargar la tabla de clientes con el listado filtrado por clientes identificados por 
     * ciudad.
     * @param ciudad 
     */
    private void listaClientesPorCiudad(String ciudad) {
        try {
            listaClientes.clear();
            modelo.getDataVector().removeAllElements();
            listaClientes = clienteDAO.listarClientesPorCiudad(ciudad);
            cargarDatos(listaClientes);
        } catch (SQLException ex) {
            Logger.getLogger(Clientes_Module.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * este metodo permite cargar el listado de todos los clientes almacenados en la fuente de datos en la tabla de
     * clientes, hce uso del API ClienteDAO.
     */
    private void listarClientes() {
        try {
            listaClientes.clear();
            modelo.getDataVector().removeAllElements();
            listaClientes = clienteDAO.listarClientes();
            cargarDatos(listaClientes);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar la lista de Clientes, Ocurrio un Error.", GlobalConfigSystem.getAplicationTitle(), 0, new ImageIcon(getClass().getResource(Icons + "NiconError.png")));
        }
    }

    /**
     * metodo que permite listar los clientes por orden alfabetico ascendente.
     */
    private void listarClientesOrderAsc() {
        try {
            listaClientes.clear();
            modelo.getDataVector().removeAllElements();
            listaClientes = clienteDAO.listarClientesOrdenadosPorNombre("asc");
            cargarDatos(listaClientes);
        } catch (SQLException ex) {
            Logger.getLogger(Clientes_Module.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * metodo que permite listar los clientes por orden alfabetico descendente.
     */
    private void listarClientesOrderDesc() {
        try {
            listaClientes.clear();
            modelo.getDataVector().removeAllElements();
            listaClientes = clienteDAO.listarClientesOrdenadosPorNombre("desc");
            cargarDatos(listaClientes);
        } catch (SQLException ex) {
            Logger.getLogger(Clientes_Module.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     */
    private void eliminarCliente() {
        cliente = obtenerSeleccionado();
        try {
            clienteDAO = new ClienteDAO(cliente);
            stateOP = clienteDAO.eliminarCliente();
            if (stateOP) {
                JOptionPane.showMessageDialog(null, "El cliente ha sido eliminado exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
                recargarDatos();
            } else {
                JOptionPane.showMessageDialog(null, "El cliente no se puede eliminar del sistema, hay datos dependientes de su ID", GlobalConfigSystem.getAplicationTitle(), 0);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El cliente no se puede eliminar del sistema, Ocurrio un error:\n\n" + ex, GlobalConfigSystem.getAplicationTitle(), 0, new ImageIcon(getClass().getResource(Icons + "NiconError.png")));
            Logger.getLogger(Clientes_Module.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected static void seleccionarCliente(String ID) {
        for (int i = 0; i < tablaClientes.getRowCount(); i++) {
            if (modelo.getValueAt(i, 0).equals(ID)) {
                tablaClientes.changeSelection(i, 0, false, false);
            }
        }
    }

    private void crearActividad() {
        cliente = obtenerSeleccionado();
        if (cliente != null) {
            asignacion = new Actividad_Crear(cliente.getIdentificacion());
            asignacion.setVisible(true);
        } else {
            asignacion = new Actividad_Crear();
            asignacion.setVisible(true);
        }
        cliente = null;
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ((ae.getSource() == this.JBCrear) || (ae.getSource() == this.crearCliente)) {
            Clientes_Ingreso Regist = new Clientes_Ingreso();
            Regist.setVisible(true);
        }

        if ((ae.getSource() == this.JBEditar) || (ae.getSource() == this.editarCliente)) {
            Clientes_Actualizar update = new Clientes_Actualizar(obtenerSeleccionado());
            update.setVisible(true);
        }

        if ((ae.getSource() == this.JBDelete) || (ae.getSource() == this.eliminarCliente)) {
            eliminarCliente();
        }

        if (ae.getSource() == this.busquedaID) {
            Clientes_EasySearch buscar = new Clientes_EasySearch();
            buscar.setVisible(true);
        }

        if (ae.getSource() == this.jmCerrar) {
            ModuloPrincipal.removeTab("Clientes");
        }

        if (ae.getSource() == this.JRBIdentificacion) {
            this.selectedSearch = 1;
        }

        if (ae.getSource() == this.JRBNombres) {
            this.selectedSearch = 2;
        }

        if (ae.getSource() == this.jmOrdenarAsc) {
            listarClientesOrderAsc();
        }

        if (ae.getSource() == this.jmOrdenarDesc) {
            listarClientesOrderDesc();
        }

        if (ae.getSource() == this.jmVerPrimero) {
            seleccionarPrimerRegistro();
        }

        if (ae.getSource() == this.jmVerUltimo) {
            seleccionarUltimoRegistro();
        }

        if (ae.getSource() == this.jmAbrirActividades) {
            Actividad_Administrador activitiesAdmin = new Actividad_Administrador();
            activitiesAdmin.setVisible(true);
        }

        if (ae.getSource() == this.jmCrearTipoActividad) {
            CrearTipoActividad nueva = new CrearTipoActividad();
            nueva.setVisible(true);
        }

        if (ae.getSource() == this.jbCrearActividad) {
            crearActividad();
        }

        if (ae.getSource() == this.JCListCity) {
            String citi = (String) this.JCListCity.getSelectedItem();
            if (citi.equals("Mostrar todas")) {
                listarClientes();
            } else {
                listaClientesPorCiudad(citi);
            }
        }

        if (ae.getSource() == jmListarTodo) {
            try {
                clienteDAO.exportarTodosPDF();
            } catch (JRException ex) {
                Logger.getLogger(Clientes_Module.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == tablaClientes) {
            mostrarDatos(obtenerSeleccionado());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() == this.JTSearchData) {
            this.JTSearchData.setText("");
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}