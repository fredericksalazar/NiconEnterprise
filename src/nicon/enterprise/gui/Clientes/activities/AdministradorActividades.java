/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.ActividadDAO;
import nicon.enterprise.libCore.obj.Actividad;

public class AdministradorActividades extends JDialog implements ActionListener {

    private JPanel moduloActividades;
    private JLabel jlCantidad;
    private DefaultTableModel modelo;
    private JTable tablaActividades;
    private JScrollPane scrollTabla;
    private TitledBorder borde;
    private JMenuBar menuBar;
    private JMenuItem tipoActividades;
    private JButton verTodas;
    private JButton verTerminadas;
    private JButton verPendientes;
    private JButton crearActividad;
    private JButton verActividad;
    private JButton imprimirActividad;
    private JButton crearPdf;
    private JButton buscar;
    private JComboBox jcBusqueda;
    private String[] vectorActividad;
    private Actividad actividad;
    private ActividadDAO actividadDAO;
    private ArrayList listaActividades;
    private boolean Estado;
    private int index;
    private JTextField jtBusqueda;
    private JMenu ajustes;

    public AdministradorActividades() {
        setTitle("Adminisrador de Actividades - " + GlobalConfigSystem.getAplicationTitle());
        setSize(1200, 570);
        setLocationRelativeTo(null);
        setResizable(false);
        this.actividadDAO = new ActividadDAO();
        this.listaActividades = new ArrayList();
        crear_Interfaz();
        listarTodasActividades();
    }

    private void crear_Interfaz() {
        this.borde = BorderFactory.createTitledBorder("");
        this.borde.setTitlePosition(4);
        this.borde.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());

        this.moduloActividades = new JPanel();
        this.moduloActividades.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.moduloActividades.setBorder(this.borde);
        this.moduloActividades.setLayout(null);

        this.menuBar = new JMenuBar();
        this.menuBar.setBounds(0, 0, 1200, 20);

        this.ajustes = new JMenu("Ajustes");
        this.ajustes.setFont(GlobalConfigSystem.getFontAplicationText());

        this.tipoActividades = new JMenuItem("Tipo Actividades");
        this.tipoActividades.addActionListener(this);

        this.ajustes.add(this.tipoActividades);

        this.menuBar.add(this.ajustes);

        this.verTodas = new JButton(" Ver todas");
        this.verTodas.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconList.png")));
        this.verTodas.setToolTipText("Permite ver un listado con todas las actividades registradas en el sistema");
        this.verTodas.addActionListener(this);
        this.verTodas.setBounds(650, 490, 170, 35);

        this.verTerminadas = new JButton("Ver Terminadas");
        this.verTerminadas.setToolTipText("Permite listar todas las actividades que han sido terminadas...");
        this.verTerminadas.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconComplete.png")));
        this.verTerminadas.addActionListener(this);
        this.verTerminadas.setBounds(830, 490, 170, 35);

        this.verPendientes = new JButton("Ver Pendientes");
        this.verPendientes.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconReject.png")));
        this.verPendientes.setToolTipText("Permite listar todas las actividades que aun estan pendientes por realizar");
        this.verPendientes.addActionListener(this);
        this.verPendientes.setBounds(1010, 490, 170, 35);

        this.crearActividad = new JButton("Nueva");
        this.crearActividad.setToolTipText("Permite crear una nueva actividad");
        this.crearActividad.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconAdd.png")));
        this.crearActividad.addActionListener(this);
        this.crearActividad.setBounds(10, 40, 120, 30);

        this.verActividad = new JButton("Ver");
        this.verActividad.setToolTipText("Permite abrir el visor de actividades");
        this.verActividad.addActionListener(this);
        this.verActividad.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconViewer.png")));
        this.verActividad.setBounds(140, 40, 120, 30);

        this.imprimirActividad = new JButton("Imprimir");
        this.imprimirActividad.setToolTipText("Permite imprimir un actividad seleccionada");
        this.imprimirActividad.addActionListener(this);
        this.imprimirActividad.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPrinter.png")));
        this.imprimirActividad.setBounds(270, 40, 120, 30);

        this.crearPdf = new JButton("Exportar");
        this.crearPdf.addActionListener(this);
        this.crearPdf.setToolTipText("Permite generar un reporte en formato PDF con la actividad seleccionada");
        this.crearPdf.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPdf.png")));
        this.crearPdf.setBounds(400, 40, 120, 30);

        this.jlCantidad = new JLabel("- Total actividades registradas: ");
        this.jlCantidad.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlCantidad.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlCantidad.setBounds(10, 530, 600, 20);

        this.modelo = new DefaultTableModel();
        this.modelo.addColumn("ID Actividad");
        this.modelo.addColumn("Nombre");
        this.modelo.addColumn("Descripcion");
        this.modelo.addColumn("Tipo Actividad");
        this.modelo.addColumn("Nombre Cliente");
        this.modelo.addColumn("Fecha Asignación");
        this.modelo.addColumn("Estado Actividad");
        this.modelo.addColumn("Fecha Registro");

        this.tablaActividades = new JTable(this.modelo);
        this.tablaActividades.setRowHeight(29);
        this.tablaActividades.getColumn("ID Actividad").setPreferredWidth(15);
        this.tablaActividades.getColumn("Fecha Asignación").setPreferredWidth(20);
        this.tablaActividades.getColumn("Nombre Cliente").setPreferredWidth(20);
        this.tablaActividades.getColumn("Estado Actividad").setPreferredWidth(20);
        this.tablaActividades.getColumn("Fecha Registro").setPreferredWidth(20);
        this.tablaActividades.setFont(GlobalConfigSystem.getFontAplicationText());

        this.scrollTabla = new JScrollPane(this.tablaActividades);
        this.scrollTabla.setBounds(15, 80, 1170, 400);

        this.jtBusqueda = new JTextField("Ingrese la Identificación de un cliente:");
        this.jtBusqueda.setForeground(Color.gray);
        this.jtBusqueda.setFont(GlobalConfigSystem.getFontAplicationTextItalic());
        this.jtBusqueda.setBounds(15, 490, 350, 30);
        this.jtBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == 10) {
                    String id = AdministradorActividades.this.jtBusqueda.getText();
                    AdministradorActividades.this.buscarActividad(id);
                }
            }
        });
        this.jtBusqueda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                AdministradorActividades.this.jtBusqueda.setText("");
            }
        });
        this.moduloActividades.add(this.menuBar);
        this.moduloActividades.add(this.scrollTabla);
        this.moduloActividades.add(this.crearActividad);
        this.moduloActividades.add(this.verActividad);
        this.moduloActividades.add(this.imprimirActividad);
        this.moduloActividades.add(this.crearPdf);
        this.moduloActividades.add(this.verTodas);
        this.moduloActividades.add(this.verTerminadas);
        this.moduloActividades.add(this.verPendientes);
        this.moduloActividades.add(this.jlCantidad);
        this.moduloActividades.add(this.jtBusqueda);
        getContentPane().add(this.moduloActividades);
    }

    private void cargarActividades(ArrayList listaActividades) {
        this.vectorActividad = new String[8];
        System.out.println("Total tamaño de vector: " + listaActividades.size());
        for (int i = 0; i < listaActividades.size(); i++) {
            this.actividad = ((Actividad) listaActividades.get(i));
            this.vectorActividad[0] = String.valueOf(this.actividad.getIdActividad());
            this.vectorActividad[1] = this.actividad.getTituloActividad();
            this.vectorActividad[2] = this.actividad.getDescripcionActividad();
            this.vectorActividad[3] = this.actividad.getNombreActividad();
            this.vectorActividad[4] = this.actividad.getNombreCliente();
            this.vectorActividad[5] = this.actividad.getFechaAsignacion();
            this.vectorActividad[6] = String.valueOf(this.actividad.getEstadoActividad());
            this.vectorActividad[7] = String.valueOf(this.actividad.getFechaRegistro());
            this.modelo.addRow(this.vectorActividad);
        }
        this.jlCantidad.setText("- Total actividades registradas: " + this.modelo.getRowCount());
    }

    private void limpiarTablaActividades() {
        this.modelo.getDataVector().removeAllElements();
        this.listaActividades.clear();
        this.tablaActividades.removeAll();
        repaint();
    }

    private void listarTodasActividades() {
        limpiarTablaActividades();
        this.listaActividades = this.actividadDAO.listarTodas();
        cargarActividades(this.listaActividades);
    }

    private void listarActividadesPendientes() {
        limpiarTablaActividades();
        this.listaActividades = this.actividadDAO.listarPendientes();
        cargarActividades(this.listaActividades);
    }

    private void listarActividadesTerminadas() {
        limpiarTablaActividades();
        this.listaActividades = this.actividadDAO.listarRealizadas();
        cargarActividades(this.listaActividades);
    }

    private int obtenerFilaSeleccionada() {
        return this.tablaActividades.getSelectedRow();
    }

    private void mostrarActividadSeleccionada() {
        this.index = obtenerFilaSeleccionada();
        if (this.index < 0) {
            JOptionPane.showMessageDialog(this.rootPane, "No ha seleccionado una actividad para ver", GlobalConfigSystem.getAplicationTitle(), 0);
        } else {
            this.actividad = ((Actividad) this.listaActividades.get(this.index));
            VisorActividad visor = new VisorActividad(this.actividad);
            visor.setVisible(true);
        }
    }

    private void imprimirActividad() {
        this.index = this.tablaActividades.getSelectedRow();
        if (this.index < 0) {
            JOptionPane.showMessageDialog(this.rootPane, "No ha seleccionado una actividad para Imprimir", GlobalConfigSystem.getAplicationTitle(), 0);
        } else {
            try {
                this.actividad = ((Actividad) this.listaActividades.get(this.index));
                this.actividadDAO.imprimirActividadPorID(this.actividad.getIdActividad());
            } catch (JRException ex) {
                Logger.getLogger(AdministradorActividades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void generarPDF() {
        this.index = obtenerFilaSeleccionada();
        if (this.index < 0) {
            JOptionPane.showMessageDialog(this.rootPane, "No ha seleccionado una actividad para generar Reporte", GlobalConfigSystem.getAplicationTitle(), 0);
        } else {
            this.actividad = ((Actividad) this.listaActividades.get(this.index));
            this.actividadDAO.verReportePDF(this.actividad.getIdActividad());
        }
    }

    private void buscarActividad(String criterio) {
        limpiarTablaActividades();
        this.listaActividades = this.actividadDAO.buscarActividadesProIDCliente(criterio);
        cargarActividades(this.listaActividades);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.crearActividad) {
            AsignarActividad nueva = new AsignarActividad();
            nueva.setVisible(true);
        }

        if (ae.getSource() == this.verPendientes) {
            listarActividadesPendientes();
        }

        if (ae.getSource() == this.verTerminadas) {
            listarActividadesTerminadas();
        }

        if (ae.getSource() == this.verTodas) {
            listarTodasActividades();
        }

        if (ae.getSource() == this.verActividad) {
            mostrarActividadSeleccionada();
        }

        if (ae.getSource() == this.imprimirActividad) {
            imprimirActividad();
        }

        if (ae.getSource() == this.crearPdf) {
            generarPDF();
        }

        if (ae.getSource() == this.tipoActividades) {
            AdministradorTipoActividad adminTipo = new AdministradorTipoActividad();
            adminTipo.setVisible(true);
        }
    }
}