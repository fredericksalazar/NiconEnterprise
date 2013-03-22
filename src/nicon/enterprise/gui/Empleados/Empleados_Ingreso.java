/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */
package nicon.enterprise.gui.Empleados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.ContratoEmpleadoDAO;
import nicon.enterprise.libCore.api.obj.ContratoEmpleado;
import nicon.enterprise.libCore.api.obj.Empleado;

/**
 * Esta clase hereda directamente de JDialog, reune dentro de una sola interfaz
 * los modulos de ingreso de datos Empleado y contratos, esto permite que al
 * registrar un nuevo empleado pueda asignarsele inmediatamente su contrato de
 * trabajo y pueda activar su participacion en el sistema de nomina,
 *
 * @author Frederick Adolfo Salazar Sanchez
 */
public class Empleados_Ingreso extends JDialog implements ActionListener {

    private JPanel panelAdmin;
    private JPanel panelEmpleado;
    private JPanel panelContrato;
    private JButton jbRegistrar;
    private JButton jbLimpiar;
    private JButton jbCancelar;
    private int response;
    private String Icons;
    private PanelCrearContrato guiContrato;
    private PanelCrearEmpleado guiEmpleado;
    private JScrollPane scrollPaneContrato;
    private TitledBorder border;
    private ContratoEmpleadoDAO registroContrato;
    private Empleado empleado;
    private ContratoEmpleado contrato;

    public Empleados_Ingreso() {
        setTitle(GlobalConfigSystem.getAplicationTitle());
        setSize(1200, 740);
        setBackground(GlobalConfigSystem.getBackgroundAplication());
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {

        Icons = GlobalConfigSystem.getIconsPath();

        registroContrato = new ContratoEmpleadoDAO();
        guiContrato = new PanelCrearContrato();
        guiEmpleado = new PanelCrearEmpleado();

        /**
         * este panel es el contenedor principal de toda la interfaz y albergará
         * tanto los otros paneles como los objetos de ajuste de datos.
         */
        panelAdmin = new JPanel();
        panelAdmin.setLayout(null);
        panelAdmin.setBackground(GlobalConfigSystem.getBackgroundAplication());

        panelContrato = guiContrato.clonarPanel();

        scrollPaneContrato = new JScrollPane(panelContrato, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneContrato.setBounds(600, 70, 600, 630);
        scrollPaneContrato.getViewport().setView(panelContrato);

        panelEmpleado = guiEmpleado.obtenerPanel();
        panelEmpleado.setBounds(0, 70, 600, 630);

        jbRegistrar = new JButton();
        jbRegistrar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconSave.png")));
        jbRegistrar.setToolTipText("Guardar todos los datos y registrar el contrato");
        jbRegistrar.addActionListener(this);
        jbRegistrar.setBounds(1050, 20, 40, 40);

        jbLimpiar = new JButton();
        jbLimpiar.addActionListener(this);
        jbLimpiar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconClear.png")));
        jbLimpiar.setToolTipText("Limpia todos los componentes de ingreso de datos.");
        jbLimpiar.setBounds(1100, 20, 40, 40);

        jbCancelar = new JButton();
        jbCancelar.setIcon(new ImageIcon(getClass().getResource(Icons + "NiconClose.png")));
        jbCancelar.addActionListener(this);
        jbCancelar.setToolTipText("Cancela el registro de datos y cierra la ventana");
        jbCancelar.setBounds(1150, 20, 40, 40);

        panelAdmin.add(panelEmpleado);
        panelAdmin.add(scrollPaneContrato);
        panelAdmin.add(jbRegistrar);
        panelAdmin.add(jbLimpiar);
        panelAdmin.add(jbCancelar);
        getContentPane().add(panelAdmin);
    }

    private void registrarContratacion() {
        boolean registrarEmpleado = guiEmpleado.registrarEmpleado(guiEmpleado.obtenerEmpleado());
        boolean registrarContrato = guiContrato.registrarContrato(guiContrato.obtenerContrato());
            
            if(registrarEmpleado && registrarContrato){
                JOptionPane.showMessageDialog(null, "El empleado ha sido registrado exitosamente y se le ha asignado el contrato.", GlobalConfigSystem.getAplicationTitle(), JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Icons+"NiconPositive.png"));
                dispose();
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbCancelar) {
            this.response = JOptionPane.showConfirmDialog(this.panelAdmin, "¿Esta seguro que desea cancelar el registro?");
            if (this.response == 0) {
                dispose();
            }
        }

        if (e.getSource() == jbLimpiar) {
            guiEmpleado.limpiarCampos();
            guiContrato.limparCampos();
        }

        if (e.getSource() == this.jbRegistrar) {
            registrarContratacion();
        }
    }
}