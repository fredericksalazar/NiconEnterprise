/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */

package nicon.enterprise.gui.Clientes.activities;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import nicon.enterprise.libCore.api.dao.ActividadDAO;
import nicon.enterprise.libCore.api.obj.Actividad;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;


/**
 * Esta clase construye una vista que es destinada a ser el administrador de el sistema de actividades de clientes
 * esta interfaz permite gestionar de forma visual todos las acciones del sistema de actividades hereda de JDialog
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class Actividad_Administrador extends JDialog implements ActionListener {

    private JPanel actividad_Administrador;
    
    private JLabel jlCantidad;
    
    private DefaultTableModel modelo;
    private JTable tablaActividades;
    private JScrollPane scrollTabla;
    
    private TitledBorder borde;
    
    private JMenuBar menuBar;
    private JMenu ajustes;
    private JMenuItem tipoActividades;
    
    private JButton verTodas;
    private JButton verTerminadas;
    private JButton verPendientes;
    private JButton crearActividad;
    private JButton verActividad;
    private JButton imprimirActividad;
    private JButton crearPdf;
    
    private String[] filaTabla;
    
    private Actividad actividad;
    private ActividadDAO actividadDAO;
    private ArrayList listaActividades;
    
    private int index;
    private JTextField jtBusqueda;
    private Actividad_VisorActividad visor;
    

    public Actividad_Administrador() {
        setTitle("Adminisrador de Actividades - " + GlobalConfigSystem.getAplicationTitle());
        setSize(1200, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        actividadDAO = new ActividadDAO();
        listaActividades = new ArrayList();
        crear_Interfaz();
        listarTodasActividades();
    }

    private void crear_Interfaz() {
        
        borde = BorderFactory.createTitledBorder("");
        borde.setTitlePosition(4);
        borde.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());

        actividad_Administrador = new JPanel();
        actividad_Administrador.setBackground(GlobalConfigSystem.getBackgroundAplication());
        actividad_Administrador.setBorder(borde);
        actividad_Administrador.setLayout(null);

        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1200, 20);

        ajustes = new JMenu("Ajustes");
        ajustes.setFont(GlobalConfigSystem.getFontAplicationText());

        tipoActividades = new JMenuItem("Tipo Actividades");
        tipoActividades.setFont(GlobalConfigSystem.getFontAplicationText());
        tipoActividades.addActionListener(this);

        ajustes.add(tipoActividades);

        menuBar.add(ajustes);

        verTodas = new JButton(" Ver todas");
        verTodas.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconList.png")));
        verTodas.setToolTipText("Permite ver un listado con todas las actividades registradas en el sistema");
        verTodas.addActionListener(this);
        verTodas.setBounds(650, 490, 170, 35);

        verTerminadas = new JButton("Ver Terminadas");
        verTerminadas.setToolTipText("Permite listar todas las actividades que han sido terminadas...");
        verTerminadas.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconComplete.png")));
        verTerminadas.addActionListener(this);
        verTerminadas.setBounds(830, 490, 170, 35);

        verPendientes = new JButton("Ver Pendientes");
        verPendientes.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconReject.png")));
        verPendientes.setToolTipText("Permite listar todas las actividades que aun estan pendientes por realizar");
        verPendientes.addActionListener(this);
        verPendientes.setBounds(1010, 490, 170, 35);

        crearActividad = new JButton("Nueva");
        crearActividad.setToolTipText("Permite crear una nueva actividad");
        crearActividad.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconAdd.png")));
        crearActividad.addActionListener(this);
        crearActividad.setBounds(15, 40, 120, 33);

        verActividad = new JButton("Ver");
        verActividad.setToolTipText("Permite abrir el visor de actividades");
        verActividad.addActionListener(this);
        verActividad.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconViewer.png")));
        verActividad.setBounds(145, 40, 120, 33);

        imprimirActividad = new JButton("Imprimir");
        imprimirActividad.setToolTipText("Permite imprimir un actividad seleccionada");
        imprimirActividad.addActionListener(this);
        imprimirActividad.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPrinter.png")));
        imprimirActividad.setBounds(275, 40, 120, 33);

        crearPdf = new JButton("Exportar");
        crearPdf.addActionListener(this);
        crearPdf.setToolTipText("Permite generar un reporte en formato PDF con la actividad seleccionada");
        crearPdf.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPdf.png")));
        crearPdf.setBounds(405, 40, 120, 33);

        jlCantidad = new JLabel("- Total actividades registradas: ");
        jlCantidad.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlCantidad.setFont(GlobalConfigSystem.getFontAplicationText());
        jlCantidad.setBounds(10, 530, 600, 20);

        modelo = new DefaultTableModel();
        modelo.addColumn("ID Actividad");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Tipo Actividad");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Fecha Asignación");
        modelo.addColumn("Estado Actividad");
        modelo.addColumn("Fecha Registro");

        tablaActividades = new JTable(modelo);
        tablaActividades.setRowHeight(29);
        tablaActividades.getColumn("ID Actividad").setPreferredWidth(15);
        tablaActividades.getColumn("Fecha Asignación").setPreferredWidth(20);
        tablaActividades.getColumn("Nombre Cliente").setPreferredWidth(20);
        tablaActividades.getColumn("Estado Actividad").setPreferredWidth(20);
        tablaActividades.getColumn("Fecha Registro").setPreferredWidth(20);
        tablaActividades.setFont(GlobalConfigSystem.getFontAplicationText());

        scrollTabla = new JScrollPane(tablaActividades);
        scrollTabla.setBounds(15, 80, 1170, 400);

        jtBusqueda = new JTextField("Ingrese la Identificación de un cliente para buscar actividades:");
        jtBusqueda.setForeground(Color.gray);
        jtBusqueda.setFont(GlobalConfigSystem.getFontAplicationTextItalic());
        jtBusqueda.setBounds(15, 490,500, 30);
        jtBusqueda.addKeyListener(new KeyAdapter() {
            private String idCliente;
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    idCliente = jtBusqueda.getText();
                    buscarActividad(idCliente);
                }
            }
        });
        jtBusqueda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                jtBusqueda.setText("");
            }
        });
        
        actividad_Administrador.add(menuBar);
        actividad_Administrador.add(scrollTabla);
        actividad_Administrador.add(crearActividad);
        actividad_Administrador.add(verActividad);
        actividad_Administrador.add(imprimirActividad);
        actividad_Administrador.add(crearPdf);
        actividad_Administrador.add(verTodas);
        actividad_Administrador.add(verTerminadas);
        actividad_Administrador.add(verPendientes);
        actividad_Administrador.add(jlCantidad);
        actividad_Administrador.add(jtBusqueda);
        
        getContentPane().add(actividad_Administrador);
    }
    
    /**
     * este metodo permite cargar un listado de actividades al modelo de datos de la tabla de actividades, este m
     * metodo recibe como parametro el ArrayList de actividades que serán cargadas al modelo.
     * 
     * @param listaActividades 
     */
    private void cargarActividades(ArrayList listaActividades) {
        filaTabla = new String[8];
        
            for (int i = 0; i < listaActividades.size(); i++) {
                actividad = ((Actividad) listaActividades.get(i));
                filaTabla[0] = String.valueOf(actividad.getIdActividad());
                filaTabla[1] = actividad.getTituloActividad();
                filaTabla[2] = actividad.getDescripcionActividad();
                filaTabla[3] = actividad.getNombreActividad();
                filaTabla[4] = actividad.getNombreCliente();
                filaTabla[5] = actividad.getFechaAsignacion();
                filaTabla[6] = String.valueOf(actividad.getEstadoActividad());
                filaTabla[7] = String.valueOf(actividad.getFechaRegistro());
                modelo.addRow(filaTabla);
        }
        jlCantidad.setText("- Total actividades registradas: " + this.modelo.getRowCount());
    }

    /**
     * Este metodo permite limpiar de datos el modelo de datosy la lista de actividades.
     */
    private void limpiarTablaActividades() {
        modelo.getDataVector().removeAllElements();
        listaActividades.clear();
        tablaActividades.removeAll();
        repaint();
    }

    /**
     * Este metodo permite cargar todas las actividades tanto terminadas como pendientes al modelo de datos de la
     * tabla de actividades.
     */
    private void listarTodasActividades() {
        limpiarTablaActividades();
        listaActividades = actividadDAO.listarTodas();
        cargarActividades(listaActividades);
    }

    /**
     * este metodo permite cargar todas las actividades pendientes por terminar al modelo de datos de la 
     * tabla de actividades, y son mostradas al usuario.
     */
    private void listarActividadesPendientes() {
        limpiarTablaActividades();
        listaActividades = actividadDAO.listarPendientes();
        cargarActividades(listaActividades);
    }

    /**
     * 
     */
    private void listarActividadesTerminadas() {
        limpiarTablaActividades();
        this.listaActividades = this.actividadDAO.listarRealizadas();
        cargarActividades(this.listaActividades);
    }

    private int obtenerFilaSeleccionada() {
        return tablaActividades.getSelectedRow();
    }

    private void mostrarActividadSeleccionada() {
        index = obtenerFilaSeleccionada();
        if (index < 0) {
            JOptionPane.showMessageDialog(rootPane, "No ha seleccionado una actividad para ver", GlobalConfigSystem.getAplicationTitle(), 0);
        } else {
            actividad = ((Actividad) listaActividades.get(index));
            visor = new Actividad_VisorActividad(actividad);
            visor.setVisible(true);
        }
    }

    private void imprimirActividad() {
        index = tablaActividades.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(rootPane, "No ha seleccionado una actividad para Imprimir", GlobalConfigSystem.getAplicationTitle(), 0);
        } else {
            try {
                actividad = ((Actividad) listaActividades.get(index));
                actividadDAO.imprimirActividadPorID(actividad.getIdActividad());
            } catch (JRException ex) {
                Logger.getLogger(Actividad_Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void generarPDF() {
        index = obtenerFilaSeleccionada();
        if (index < 0) {
            JOptionPane.showMessageDialog(this.rootPane, "No ha seleccionado una actividad para generar Reporte", GlobalConfigSystem.getAplicationTitle(), 0);
        } else {
            actividad = ((Actividad)listaActividades.get(this.index));
            actividadDAO.verReportePDF(actividad.getIdActividad());
        }
    }

    private void buscarActividad(String criterio) {
        limpiarTablaActividades();
        listaActividades = actividadDAO.buscarActividadesProIDCliente(criterio);
        cargarActividades(listaActividades);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == crearActividad) {
            Actividad_Crear nueva = new Actividad_Crear();
            nueva.setVisible(true);
        }

        if (ae.getSource() == verPendientes) {
            listarActividadesPendientes();
        }

        if (ae.getSource() == verTerminadas) {
            listarActividadesTerminadas();
        }

        if (ae.getSource() == verTodas) {
            listarTodasActividades();
        }

        if (ae.getSource() == verActividad) {
            mostrarActividadSeleccionada();
        }

        if (ae.getSource() == imprimirActividad) {
            imprimirActividad();
        }

        if (ae.getSource() == crearPdf) {
            generarPDF();
        }

        if (ae.getSource() == tipoActividades) {
            AdministradorTipoActividad adminTipo = new AdministradorTipoActividad();
            adminTipo.setVisible(true);
        }
    }
}