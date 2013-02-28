/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Clientes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import nicon.enterprise.gui.Clientes.activities.AdministradorActividades;
import nicon.enterprise.gui.Clientes.activities.AsignarActividad;
import nicon.enterprise.gui.ModuloPrincipal;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.ClienteDAO;
import nicon.enterprise.libCore.obj.Cliente;
import nicon.enterprise.memData.BasicDataAplication;

public class ModuloClientes extends JPanel implements ActionListener, MouseListener {

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
    private JButton jbExportar;
    private JButton jbCrearActividad;
    private ButtonGroup JGBOptions;
    private JRadioButton JRBNombres;
    private JRadioButton JRBIdentificacion;
    private JSeparator SeparatorTools;
    private JComboBox JCListCity;
    private int selectedSearch;
    private static ArrayList listaClientes;
    private static Iterator iterator;
    private static Cliente cliente;
    private static ClienteDAO clienteDAO;
    private static String[] vectorDatos;
    private static String Icons;
    private int index;
    private boolean stateOP;
    private final int counter;
    private AsignarActividad asignacion;
    private JSeparator separator1;
    private JSeparator separator2;

    public ModuloClientes() {
        cliente = new Cliente();
        this.selectedSearch = 0;
        Icons = GlobalConfigSystem.getIconsPath();
        clienteDAO = new ClienteDAO();
        listaClientes = new ArrayList();
        crearInterfaz();
        listarClientes();
        seleccionarPrimerRegistro();
        this.counter = 0;
    }

    private void crearInterfaz() {
        this.BorderInf = BorderFactory.createTitledBorder("");
        this.BorderInf.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());

        this.moduloClientes = new JPanel();
        this.moduloClientes.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.moduloClientes.setLayout(null);

        this.menuClientes = new JMenuBar();
        this.menuClientes.setBounds(390, 0, 890, 20);

        this.jmArchivo = new JMenu("Archivo");
        this.jmArchivo.setFont(GlobalConfigSystem.getFontAplicationText());

        this.jmClientes = new JMenu("Clientes");
        this.jmClientes.setFont(GlobalConfigSystem.getFontAplicationText());

        this.jmExportar = new JMenu("Reportes");
        this.jmExportar.setFont(GlobalConfigSystem.getFontAplicationText());

        this.jmVer = new JMenu("Ver");
        this.jmVer.setFont(GlobalConfigSystem.getFontAplicationText());

        this.jmActividades = new JMenu("Actividades");
        this.jmActividades.setFont(GlobalConfigSystem.getFontAplicationText());

        this.jmCerrar = new JMenuItem("- Cerrar Módulo");
        this.jmCerrar.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmCerrar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuExit.png")));
        this.jmCerrar.addActionListener(this);

        this.crearCliente = new JMenuItem("- Crear Cliente");
        this.crearCliente.setToolTipText("Permite crear un nuevo cliente");
        this.crearCliente.setFont(GlobalConfigSystem.getFontAplicationText());
        this.crearCliente.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuPersonAdd.png")));
        this.crearCliente.addActionListener(this);

        this.editarCliente = new JMenuItem("- Editar Cliente");
        this.editarCliente.setFont(GlobalConfigSystem.getFontAplicationText());
        this.editarCliente.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconUpdateMenu.png")));
        this.editarCliente.setToolTipText("Permite editar los datos de un cliente");
        this.editarCliente.addActionListener(this);

        this.eliminarCliente = new JMenuItem("- Eliminar Cliente");
        this.eliminarCliente.setFont(GlobalConfigSystem.getFontAplicationText());
        this.eliminarCliente.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconRemove.png")));
        this.eliminarCliente.setToolTipText("Permite eliminar un cliente del sistema");
        this.eliminarCliente.addActionListener(this);

        this.busquedaID = new JMenuItem("- Buscar Identificación");
        this.busquedaID.setFont(GlobalConfigSystem.getFontAplicationText());
        this.busquedaID.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconFind.png")));
        this.busquedaID.setToolTipText("Permite buscar la información de un cliente desde el desktop");
        this.busquedaID.addActionListener(this);

        this.jmListarTodo = new JMenuItem("- Listar Todos los clientes");
        this.jmListarTodo.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmListarTodo.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconPdf.png")));
        this.jmListarTodo.setToolTipText("Genera un reporte con todos los clientes registrados y los exporta a PDF");
        this.jmListarTodo.addActionListener(this);

        this.jmListarPorID = new JMenuItem("Listar Por Identificación");
        this.jmListarPorID.setToolTipText("Genera un reporte en pdf ingresando el numero de cedula de un cliente");
        this.jmListarPorID.addActionListener(this);

        this.jmOrdenarAsc = new JMenuItem("- Ordenar Lista Asc");
        this.jmOrdenarAsc.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmOrdenarAsc.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuUp.png")));
        this.jmOrdenarAsc.setToolTipText("Permite ordenar la lista de clientes por referencia a nombres de modo ascendente");
        this.jmOrdenarAsc.addActionListener(this);

        this.jmOrdenarDesc = new JMenuItem("- Ordenar Lista Desc");
        this.jmOrdenarDesc.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmOrdenarDesc.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconMenuDown.png")));
        this.jmOrdenarDesc.addActionListener(this);

        this.jmVerPrimero = new JMenuItem("- Ver Primer Registro");
        this.jmVerPrimero.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmVerPrimero.setToolTipText("Muestra el primer registro de la tabla");
        this.jmVerPrimero.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconFirstMenu.png")));
        this.jmVerPrimero.addActionListener(this);

        this.jmVerUltimo = new JMenuItem("- Ver último registro");
        this.jmVerUltimo.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmVerUltimo.setToolTipText("Muestra el último registro de la tabla");
        this.jmVerUltimo.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconLastMenu.png")));
        this.jmVerUltimo.addActionListener(this);

        this.jmAbrirActividades = new JMenuItem("- Administrador de Actividades");
        this.jmAbrirActividades.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconTimer.png")));
        this.jmAbrirActividades.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jmAbrirActividades.addActionListener(this);

        this.jmCrearTipoActividad = new JMenuItem("Crear Tipo Actividad");
        this.jmCrearTipoActividad.setToolTipText("Permite crear un nuevo tipo de actividad en el sistema");
        this.jmCrearTipoActividad.addActionListener(this);

        this.jmActividades.add(this.jmAbrirActividades);
        this.jmActividades.add(this.jmCrearTipoActividad);

        this.jmClientes.add(this.crearCliente);
        this.jmClientes.add(this.editarCliente);
        this.jmClientes.add(this.eliminarCliente);
        this.jmClientes.addSeparator();
        this.jmClientes.add(this.busquedaID);

        this.jmExportar.add(this.jmListarTodo);
        this.jmArchivo.add(this.jmCerrar);

        this.jmVer.add(this.jmOrdenarAsc);
        this.jmVer.add(this.jmOrdenarDesc);
        this.jmVer.addSeparator();
        this.jmVer.add(this.jmVerPrimero);
        this.jmVer.add(this.jmVerUltimo);

        this.menuClientes.add(this.jmArchivo);
        this.menuClientes.add(this.jmClientes);
        this.menuClientes.add(this.jmExportar);
        this.menuClientes.add(this.jmVer);
        this.menuClientes.add(this.jmActividades);

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

        this.ScrollTable = new JScrollPane(tablaClientes);
        this.ScrollTable.setBounds(0, 0, 390, 570);

        this.panelInformacion = new JPanel();
        this.panelInformacion.setLayout(null);
        this.panelInformacion.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.panelInformacion.setBounds(420, 30, 600, 495);
        this.panelInformacion.setBorder(this.BorderInf);

        nombres = new JLabel();
        nombres.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        nombres.setBounds(10, 10, 700, 40);
        nombres.setFont(GlobalConfigSystem.getFontAplicationName());

        identificacion = new JLabel();
        identificacion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        identificacion.setFont(GlobalConfigSystem.getFontAplicationSubTitle());
        identificacion.setBounds(10, 60, 150, 16);

        this.jlContacto = new JLabel("CONTACTO");
        this.jlContacto.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlContacto.setFont(GlobalConfigSystem.getFontAplicationSubTitle());
        this.jlContacto.setBounds(40, 130, 220, 15);

        this.separator1 = new JSeparator();
        this.separator1.setBounds(40, 155, 500, 5);

        this.JLTel_fijo = new JLabel("Fijo:");
        this.JLTel_fijo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.JLTel_fijo.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLTel_fijo.setBounds(153, 175, 150, 15);

        telFijo = new JLabel();
        telFijo.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        telFijo.setFont(GlobalConfigSystem.getFontAplicationText());
        telFijo.setBounds(220, 175, 300, 15);

        this.JLTel_movil = new JLabel("Móvil:");
        this.JLTel_movil.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.JLTel_movil.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLTel_movil.setBounds(142, 200, 150, 15);

        telMovil = new JLabel();
        telMovil.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        telMovil.setFont(GlobalConfigSystem.getFontAplicationText());
        telMovil.setBounds(220, 200, 300, 15);

        this.JLTel_alternativo = new JLabel("Alternativo:");
        this.JLTel_alternativo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.JLTel_alternativo.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLTel_alternativo.setBounds(100, 225, 150, 15);

        telAlternativo = new JLabel();
        telAlternativo.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        telAlternativo.setFont(GlobalConfigSystem.getFontAplicationText());
        telAlternativo.setBounds(220, 225, 300, 15);

        this.JLEmail = new JLabel("Email:");
        this.JLEmail.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.JLEmail.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLEmail.setBounds(140, 250, 150, 15);

        email = new JLabel();
        email.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        email.setFont(GlobalConfigSystem.getFontAplicationText());
        email.setBounds(220, 250, 600, 15);

        this.jlUbicacion = new JLabel("VIVE EN");
        this.jlUbicacion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        this.jlUbicacion.setFont(GlobalConfigSystem.getFontAplicationSubTitle());
        this.jlUbicacion.setBounds(40, 290, 220, 20);

        this.separator2 = new JSeparator();
        this.separator2.setBounds(40, 315, 500, 5);

        this.JLDireccion = new JLabel("Dirección:");
        this.JLDireccion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.JLDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLDireccion.setBounds(110, 330, 150, 15);

        direccion = new JLabel();
        direccion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        direccion.setFont(GlobalConfigSystem.getFontAplicationText());
        direccion.setBounds(220, 330, 300, 15);

        this.JLCiudad = new JLabel("Ciudad:");
        this.JLCiudad.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.JLCiudad.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLCiudad.setBounds(127, 355, 150, 15);

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

        this.panelInformacion.add(nombres);
        this.panelInformacion.add(identificacion);
        this.panelInformacion.add(this.jlContacto);
        this.panelInformacion.add(this.separator1);
        this.panelInformacion.add(this.JLTel_fijo);
        this.panelInformacion.add(telFijo);
        this.panelInformacion.add(this.JLTel_movil);
        this.panelInformacion.add(telMovil);
        this.panelInformacion.add(this.JLTel_alternativo);
        this.panelInformacion.add(telAlternativo);
        this.panelInformacion.add(this.JLEmail);
        this.panelInformacion.add(email);

        this.panelInformacion.add(this.jlUbicacion);
        this.panelInformacion.add(this.separator2);
        this.panelInformacion.add(this.JLDireccion);
        this.panelInformacion.add(direccion);
        this.panelInformacion.add(this.JLCiudad);
        this.panelInformacion.add(ciudad);
        this.panelInformacion.add(JLfecha_registro);
        this.panelInformacion.add(fechaRegistro);

        this.BorderTool = BorderFactory.createTitledBorder("");
        this.BorderTool.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());

        this.panelHerramientas = new JPanel();
        this.panelHerramientas.setLayout(null);
        this.panelHerramientas.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.panelHerramientas.setBounds(1020, 30, 250, 580);
        this.panelHerramientas.setBorder(this.BorderTool);

        this.JBCrear = new JButton("Nuevo");
        this.JBCrear.setBounds(10, 10, 115, 30);
        this.JBCrear.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconAdd.png")));
        this.JBCrear.setToolTipText("Crear un nuevo cliente en el sistema");
        this.JBCrear.addActionListener(this);

        this.JBEditar = new JButton("Editar");
        this.JBEditar.setBounds(128, 10, 115, 30);
        this.JBEditar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconUpdateMenu.png")));
        this.JBEditar.setToolTipText("Editar los datos del cliente seleccionado");
        this.JBEditar.addActionListener(this);

        this.JBDelete = new JButton("Eliminar");
        this.JBDelete.setBounds(10, 50, 115, 30);
        this.JBDelete.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconRemove.png")));
        this.JBDelete.setToolTipText("Eliminar un cliente de la base de datos");
        this.JBDelete.addActionListener(this);

        this.jbCrearActividad = new JButton("Actividad");
        this.jbCrearActividad.setToolTipText("Permite asiganar una Nueva actividad al cliente seleccionado...");
        this.jbCrearActividad.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconAdd.png")));
        this.jbCrearActividad.addActionListener(this);
        this.jbCrearActividad.setBounds(128, 50, 115, 30);

        this.SeparatorTools = new JSeparator();
        this.SeparatorTools.setBounds(15, 105, 220, 5);
        this.SeparatorTools.setBackground(Color.lightGray);

        this.JLListCity = new JLabel("Mostrar Clientes de:");
        this.JLListCity.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLListCity.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLListCity.setBounds(15, 115, 200, 25);

        this.JCListCity = new JComboBox(BasicDataAplication.getListCity());
        this.JCListCity.addItem("Mostrar todas");
        this.JCListCity.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JCListCity.setBounds(15, 140, 218, 30);
        this.JCListCity.setToolTipText("Mostrar los clientes de una ciudad seleccionada en la lista");
        this.JCListCity.addActionListener(this);

        this.panelHerramientas.add(this.JBCrear);
        this.panelHerramientas.add(this.JBEditar);
        this.panelHerramientas.add(this.JBDelete);
        this.panelHerramientas.add(this.SeparatorTools);
        this.panelHerramientas.add(this.JLListCity);
        this.panelHerramientas.add(this.JCListCity);
        this.panelHerramientas.add(this.jbCrearActividad);

        this.BorderSearch = BorderFactory.createTitledBorder("Busqueda:");
        this.BorderSearch.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());

        this.panelBusqueda = new JPanel();
        this.panelBusqueda.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.panelBusqueda.setLayout(null);
        this.panelBusqueda.setBounds(420, 523, 600, 100);
        this.panelBusqueda.setBorder(this.BorderSearch);

        this.JGBOptions = new ButtonGroup();

        this.JRBNombres = new JRadioButton("Por Nombres");
        this.JRBNombres.setBounds(10, 30, 260, 30);
        this.JRBNombres.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JRBNombres.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JRBNombres.addActionListener(this);

        this.JRBIdentificacion = new JRadioButton("Por Cédula");
        this.JRBIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JRBIdentificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JRBIdentificacion.setBounds(10, 55, 260, 30);
        this.JRBIdentificacion.addActionListener(this);

        this.JGBOptions.add(this.JRBNombres);
        this.JGBOptions.add(this.JRBIdentificacion);

        this.JTSearchData = new JTextField("Ingrese los datos a Buscar:");
        this.JTSearchData.setBounds(190, 45, 400, 28);
        this.JTSearchData.setToolTipText("Ingrese el Nombre  la cédula para buscar información.");
        this.JTSearchData.setFont(GlobalConfigSystem.getFontAplicationTextItalic());
        this.JTSearchData.setForeground(Color.darkGray);
        this.JTSearchData.addMouseListener(this);
        this.JTSearchData.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == 10) {
                    if (ModuloClientes.this.selectedSearch == 0) {
                        JOptionPane.showMessageDialog(null, "Por favor seleccione un criterio de busqueda\n      Por nombres / por cédula", GlobalConfigSystem.getAplicationTitle(), 0);
                    } else {
                        if (ModuloClientes.this.selectedSearch == 1) {
                            ModuloClientes.this.buscar(1, ModuloClientes.this.JTSearchData.getText());
                        }
                        if (ModuloClientes.this.selectedSearch == 2) {
                            ModuloClientes.this.buscar(2, ModuloClientes.this.JTSearchData.getText());
                        }
                    }
                }
            }
        });
        this.panelBusqueda.add(this.JTSearchData);
        this.panelBusqueda.add(this.JRBNombres);
        this.panelBusqueda.add(this.JRBIdentificacion);

        this.moduloClientes.add(this.menuClientes);
        this.moduloClientes.add(this.ScrollTable);
        this.moduloClientes.add(this.panelInformacion);
        this.moduloClientes.add(this.panelHerramientas);
        this.moduloClientes.add(this.panelBusqueda);
        this.moduloClientes.add(jlTotalRegistros);
    }

    public JPanel obtenerModulo() {
        return this.moduloClientes;
    }

    private static void cargarDatos(ArrayList listado) {
        try {
            System.out.println("Iniciando carga de datos al Modulo de clientes");
            vectorDatos = new String[3];
            for (int i = 0; i < listado.size(); i++) {
                cliente = (Cliente) listado.get(i);
                vectorDatos[0] = cliente.getIdentificacion();
                vectorDatos[1] = cliente.getNombres();
                vectorDatos[2] = cliente.getApellidos();
                modelo.addRow(vectorDatos);
            }
            actualizarContador();
            System.out.println("Todos los clientes han sido cargados al modulo. total clientes registrados: " + modelo.getRowCount());
        } catch (Exception e) {
            System.err.println("Ocurrio un error cargando datos de modulo: Clientes\n" + e.getMessage());
        }
    }

    private static void actualizarContador() {
        jlTotalRegistros.setText("Total registros:" + String.valueOf(modelo.getRowCount()));
    }

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

    public Cliente obtenerSeleccionado() {
        try {
            this.index = obtenerIndiceSeleccionado();
            if (this.index >= 0) {
                cliente = (Cliente) listaClientes.get(this.index);
            } else {
                cliente = null;
            }
        } catch (Exception e) {
        }
        return cliente;
    }

    private int obtenerIndiceSeleccionado() {
        this.index = tablaClientes.getSelectedRow();
        if (this.index < 0) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado nigún cliente en la lista", GlobalConfigSystem.getAplicationTitle(), 0);
        }
        return this.index;
    }

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

    public void buscar(int opcionBusqueda, String datos) {
        if (opcionBusqueda == 1) {
            buscarInformacionPorID(datos);
        }
        if (opcionBusqueda == 2) {
            buscarInformacionPorNombres(datos);
        }
    }

    private void buscarInformacionPorID(String ID) {
        cliente = clienteDAO.buscarPorIdentificacion(ID);
        if (cliente != null) {
            mostrarDatos(cliente);
            seleccionarCliente(cliente.getIdentificacion());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron registros con los parametros Ingresados", GlobalConfigSystem.getAplicationTitle(), 2, new ImageIcon(getClass().getResource(Icons + "NiconWarning.png")));
        }
    }

    private void buscarInformacionPorNombres(String Nombres) {
        cliente = clienteDAO.buscarPorNombres(Nombres);
        if (cliente != null) {
            mostrarDatos(cliente);
            seleccionarCliente(cliente.getIdentificacion());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron registros con los parametros Ingresados", GlobalConfigSystem.getAplicationTitle(), 2);
        }
    }

    private void seleccionarPrimerRegistro() {
        tablaClientes.changeSelection(0, 0, false, false);
        mostrarDatos(obtenerSeleccionado());
    }

    private void seleccionarUltimoRegistro() {
        tablaClientes.changeSelection(modelo.getRowCount() - 1, 0, false, false);
        mostrarDatos(obtenerSeleccionado());
    }

    private void listaClientesPorCiudad(String ciudad) {
        listaClientes.clear();
        modelo.getDataVector().removeAllElements();
        listaClientes = clienteDAO.listarClientesPorCiudad(ciudad);
        cargarDatos(listaClientes);
    }

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

    private void listarClientesOrderAsc() {
        listaClientes.clear();
        modelo.getDataVector().removeAllElements();
        listaClientes = clienteDAO.listarClientesOrdenadosPorNombre("asc");
        cargarDatos(listaClientes);
    }

    private void listarClientesOrderDesc() {
        listaClientes.clear();
        modelo.getDataVector().removeAllElements();
        listaClientes = clienteDAO.listarClientesOrdenadosPorNombre("desc");
        cargarDatos(listaClientes);
    }

    private void eliminarCliente() {
        cliente = obtenerSeleccionado();
        try {
            clienteDAO = new ClienteDAO(cliente);
            this.stateOP = clienteDAO.eliminarCliente();
            if (this.stateOP) {
                JOptionPane.showMessageDialog(null, "El cliente ha sido eliminado exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
                recargarDatos();
            } else {
                JOptionPane.showMessageDialog(null, "El cliente no se puede eliminar del sistema, hay datos dependientes de su ID", GlobalConfigSystem.getAplicationTitle(), 0);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El cliente no se puede eliminar del sistema, Ocurrio un error:\n\n" + ex, GlobalConfigSystem.getAplicationTitle(), 0, new ImageIcon(getClass().getResource(Icons + "NiconError.png")));
            Logger.getLogger(ModuloClientes.class.getName()).log(Level.SEVERE, null, ex);
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
            this.asignacion = new AsignarActividad(cliente.getIdentificacion());
            this.asignacion.setVisible(true);
        } else {
            this.asignacion = new AsignarActividad();
            this.asignacion.setVisible(true);
        }
        cliente = null;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ((ae.getSource() == this.JBCrear) || (ae.getSource() == this.crearCliente)) {
            Ingreso Regist = new Ingreso();
            Regist.setVisible(true);
        }

        if ((ae.getSource() == this.JBEditar) || (ae.getSource() == this.editarCliente)) {
            Actualizar update = new Actualizar(obtenerSeleccionado());
            update.setVisible(true);
        }

        if ((ae.getSource() == this.JBDelete) || (ae.getSource() == this.eliminarCliente)) {
            eliminarCliente();
        }

        if (ae.getSource() == this.busquedaID) {
            CajaBusqueda buscar = new CajaBusqueda();
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
            AdministradorActividades activitiesAdmin = new AdministradorActividades();
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

        if (ae.getSource() == this.jmListarTodo) {
            try {
                clienteDAO.exportarTodosPDF();
            } catch (JRException ex) {
                Logger.getLogger(ModuloClientes.class.getName()).log(Level.SEVERE, null, ex);
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