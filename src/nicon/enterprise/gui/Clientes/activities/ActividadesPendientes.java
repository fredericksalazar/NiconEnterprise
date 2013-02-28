/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Clientes.activities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ActividadesPendientes extends JPanel implements ActionListener {

    private static final long serialVersionUID = 2L;
    private JPanel panelActividades;
    private DefaultTableModel modelo;
    private JTable tablaActividades;
    private JScrollPane scrollTabla;
    private JLabel jlInfActividad;
    private JLabel jlCantidad;
    private TitledBorder bordeActividades;
    private JButton verActividad;
    private JButton imprimir;
    private Timer Temporizator;
    private String Icons;
    private ActividadDAO actividadDAO;
    private ArrayList listadoActividades;
    private Actividad actividad;

    public ActividadesPendientes() {
        this.Icons = GlobalConfigSystem.getIconsPath();
        crearInterfaz();
        cargarTablaActividades();
        actualizarListado();
    }

    private void crearInterfaz() {
        this.bordeActividades = BorderFactory.createTitledBorder("Lista Actividades Pendientes");
        this.bordeActividades.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());
        this.bordeActividades.setTitleFont(GlobalConfigSystem.getFontAplicationText());

        this.panelActividades = new JPanel();
        this.panelActividades.setLayout(null);
        this.panelActividades.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.panelActividades.setBorder(this.bordeActividades);
        this.panelActividades.setBounds(850, 20, 400, 270);

        this.jlInfActividad = new JLabel("No hay actividades pendientes para hoy...");
        this.jlInfActividad.setBounds(50, 50, 300, 20);
        this.jlInfActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        this.jlInfActividad.setFont(GlobalConfigSystem.getFontAplicationText());

        this.modelo = new DefaultTableModel();
        this.modelo.addColumn("Actvididades");

        this.tablaActividades = new JTable(this.modelo);
        this.tablaActividades.setTableHeader(null);
        this.tablaActividades.setFont(GlobalConfigSystem.getFontAplicationText());
        this.tablaActividades.setToolTipText("Listado de actividades pendientes para hoy");
        this.tablaActividades.setRowHeight(35);

        this.scrollTabla = new JScrollPane(this.tablaActividades);
        this.scrollTabla.setBounds(12, 30, 380, 200);

        this.jlCantidad = new JLabel("Total Actividades:");
        this.jlCantidad.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlCantidad.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlCantidad.setBounds(240, 232, 200, 20);

        this.verActividad = new JButton();
        this.verActividad.setToolTipText("Abrir el visor de actividades para detalles de actividad...");
        this.verActividad.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconViewer.png")));
        this.verActividad.addActionListener(this);
        this.verActividad.setBounds(10, 232, 30, 30);

        this.imprimir = new JButton();
        this.imprimir.addActionListener(this);
        this.imprimir.setToolTipText("Imprimir todo el listado de actividades pendientes para hoy");
        this.imprimir.setIcon(new ImageIcon(getClass().getResource(this.Icons + "NiconPrinter.png")));
        this.imprimir.setBounds(50, 232, 30, 30);

        this.panelActividades.add(this.verActividad);
        this.panelActividades.add(this.scrollTabla);
        this.panelActividades.add(this.jlCantidad);
        this.panelActividades.add(this.imprimir);
    }

    public JPanel obetenerPanelActividades() {
        return this.panelActividades;
    }

    private void cargarTablaActividades() {
        String[] add = new String[1];
        this.actividadDAO = new ActividadDAO();
        this.listadoActividades = this.actividadDAO.listarActividadesPendientesHoy();

        if (this.listadoActividades.isEmpty()) {
            this.panelActividades.remove(this.scrollTabla);
            this.jlInfActividad.setText("No hay Actividades pendientes para hoy ...");
            this.panelActividades.add(this.jlInfActividad);
            this.panelActividades.repaint();
        } else {
            for (int i = 0; i < this.listadoActividades.size(); i++) {
                this.actividad = ((Actividad) this.listadoActividades.get(i));
                add[0] = (this.actividad.getTituloActividad() + ":  " + this.actividad.getNombreCliente());
                this.modelo.addRow(add);
            }
        }
        this.jlCantidad.setText("Total Actividades: " + this.modelo.getRowCount());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.verActividad) {
            if (obtenerIndiceSeleccionado() < 0) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado una actividad para ver", GlobalConfigSystem.getAplicationTitle(), 0);
            } else {
                this.actividad = ((Actividad) this.listadoActividades.get(obtenerIndiceSeleccionado()));
                VisorActividad visor = new VisorActividad(this.actividad);
                visor.setVisible(true);
            }
        }

        if (ae.getSource() == this.imprimir) {
            try {
                this.actividadDAO = new ActividadDAO();
                this.actividadDAO.imprimirActividadesParaHoy();
            } catch (JRException ex) {
                Logger.getLogger(ActividadesPendientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private int obtenerIndiceSeleccionado() {
        return this.tablaActividades.getSelectedRow();
    }

    private void limpiartablaActividades() {
        this.listadoActividades.clear();
        this.modelo.getDataVector().removeAllElements();
    }

    private void recargarListaActividades() {
        limpiartablaActividades();
        cargarTablaActividades();
    }

    private void actualizarListado() {
        try {
            this.Temporizator = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ActividadesPendientes.this.recargarListaActividades();
                }
            });
            this.Temporizator.start();
            this.Temporizator.setRepeats(true);
        } catch (Exception e) {
            System.out.println("Error en InitUpdateTime() detail:  \n" + e);
        }
    }
}