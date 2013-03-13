/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com 
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.gui.Clientes.activities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.ActividadDAO;
import nicon.enterprise.libCore.obj.Actividad;

/**
 * Esta panel es el encargado de visualizar actividades registradas en el sistema cuyo estado actual es de
 * pendientes, hace uso de <b> JPanel </b>, esta clase se encarga de posisionar todos los elementos graficos
 * que se encargan de mostrar el listado de actividades.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class Actividad_ListadoPendientes implements ActionListener {

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
    private Actividad_VisorActividad visor;

    public Actividad_ListadoPendientes() {
        Icons = GlobalConfigSystem.getIconsPath();
        crearInterfaz();
        cargarTablaActividades();
        actualizarListado();
    }

    private void crearInterfaz() {
        bordeActividades = BorderFactory.createTitledBorder("Actividades Pendientes");
        bordeActividades.setTitleColor(GlobalConfigSystem.getForegroundAplicationTitle());
        bordeActividades.setTitleFont(GlobalConfigSystem.getFontAplicationText());

        panelActividades = new JPanel();
        panelActividades.setLayout(null);
        panelActividades.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelActividades.setBorder(bordeActividades);
        panelActividades.setBounds(850, 20, 400, 270);

        jlInfActividad = new JLabel("No hay actividades pendientes para hoy...");
        jlInfActividad.setBounds(50, 50, 300, 20);
        jlInfActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        jlInfActividad.setFont(GlobalConfigSystem.getFontAplicationText());

        modelo = new DefaultTableModel();
        modelo.addColumn("Actvididades");

        tablaActividades = new JTable(modelo);
        tablaActividades.setTableHeader(null);
        tablaActividades.setFont(GlobalConfigSystem.getFontAplicationText());
        tablaActividades.setToolTipText("Listado de actividades pendientes para hoy");
        tablaActividades.setRowHeight(35);

        scrollTabla = new JScrollPane(tablaActividades);
        scrollTabla.setBounds(12, 30, 380, 200);

        jlCantidad = new JLabel("Total Actividades:");
        jlCantidad.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlCantidad.setFont(GlobalConfigSystem.getFontAplicationText());
        jlCantidad.setBounds(240, 232, 200, 20);

        verActividad = new JButton();
        verActividad.setToolTipText("Abrir el visor de actividades para detalles de actividad...");
        verActividad.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconViewer.png")));
        verActividad.addActionListener(this);
        verActividad.setBounds(10, 232, 30, 30);

        imprimir = new JButton();
        imprimir.addActionListener(this);
        imprimir.setToolTipText("Imprimir todo el listado de actividades pendientes para hoy");
        imprimir.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconPrinter.png")));
        imprimir.setBounds(50, 232, 30, 30);

        panelActividades.add(verActividad);
        panelActividades.add(scrollTabla);
        panelActividades.add(jlCantidad);
        panelActividades.add(imprimir);
    }

    /**
     * Retorna un panel de actividades pendientes creado previamente
     * @return JPanel panelActividades
     */
    public JPanel obetenerPanelActividades() {
        return panelActividades;
    }

    /**
     * Este metodo permite cargar el listado de actividades pendientes al modelo de datos que será mostrado 
     * en la tabla de actividades.
     */
    private void cargarTablaActividades() {
        String[] add = new String[1];
        actividadDAO = new ActividadDAO();
        listadoActividades = actividadDAO.listarActividadesPendientesHoy();

        if (listadoActividades.isEmpty()) {
            panelActividades.remove(scrollTabla);
            panelActividades.add(jlInfActividad);
            panelActividades.repaint();
        } else {
            for (int i = 0; i < listadoActividades.size(); i++) {
                actividad = ((Actividad)listadoActividades.get(i));
                add[0] = (actividad.getTituloActividad() + ":  " +actividad.getNombreCliente());
                modelo.addRow(add);
            }
        }
        jlCantidad.setText("Total Actividades: " +modelo.getRowCount());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == verActividad) {
            if (obtenerIndiceSeleccionado() < 0) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado una actividad para ver", GlobalConfigSystem.getAplicationTitle(), 0);
            } else {
                actividad = ((Actividad) listadoActividades.get(obtenerIndiceSeleccionado()));
                visor = new Actividad_VisorActividad(actividad);
                visor.setVisible(true);
            }
        }

        if (ae.getSource() == imprimir) {
            try {
                actividadDAO = new ActividadDAO();
                actividadDAO.imprimirActividadesParaHoy();
            } catch (JRException ex) {
                Logger.getLogger(Actividad_ListadoPendientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Retorna el entero del indice de la fila seleccionada en la JTable 
     * @return 
     */
    private int obtenerIndiceSeleccionado() {
        return tablaActividades.getSelectedRow();
    }

    /**
     * Este metodo permite limpiar todos los registros de las actividades pendientes que han sido cargadas
     * al array y al modelo de datos de la tabla.
     */
    private void limpiartablaActividades() {
        listadoActividades.clear();
        modelo.getDataVector().removeAllElements();
    }

    /**
     * este metodo recarga de cero todos los registros de la tabla de actividades pendientes.
     */
    private void recargarListaActividades() {
        limpiartablaActividades();
        cargarTablaActividades();
    }

    /**
     * este metodo es na especie de temporizador cuyo objetivo es actualizar cada cierto tiempo el listado
     * de actividades pendientes.
     */
    private void actualizarListado() {
        try {
            Temporizator = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    recargarListaActividades();
                }
            });
            Temporizator.start();
            Temporizator.setRepeats(true);
        } catch (Exception e) {
            System.out.println("Error en InitUpdateTime() detail:  \n" + e);
            JOptionPane.showMessageDialog(panelActividades, "Ocurrio un error al actualizar la lista de actividades", GlobalConfigSystem.getAplicationTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }
}