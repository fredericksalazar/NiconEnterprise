/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Empleados;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.NiconLibTools;
import nicon.enterprise.libCore.api.dao.ContratoEmpleadoDAO;
import nicon.enterprise.libCore.api.dao.EmpleadoDAO;
import nicon.enterprise.libCore.obj.ContratoEmpleado;

public class CrearContrato {

    private JPanel panelContrato;
    private JPanel panelAdmin;
    private Border borderContrato;
    private JLabel jlCodigoContrato;
    private JLabel jlFechaContratacion;
    private JLabel jlCargo;
    private JLabel jlSalario;
    private JLabel jlTiempoContrato;
    private JLabel jlInicioFunciones;
    private JLabel jlTipoContrato;
    private JLabel jlObservaciones;
    private JTextField jtCodigoContrato;
    private JDateChooser jtFechaContratacion;
    private JTextField jtCargo;
    private JTextField jtSalario;
    private JTextField jtTiempoContrato;
    private JDateChooser jtInicioFunciones;
    private JComboBox jcTipoContrato;
    private JTextArea jtObservaciones;
    private JScrollPane jsObservaciones;
    private ContratoEmpleadoDAO registroContrato;
    private JDialog ventana;
    private JButton jbAceptar;
    private JButton jbCancelar;
    private JLabel jlIdentificacion;
    private JTextField jtIdentificacion;
    private EmpleadoDAO empleadoDAO;
    private ContratoEmpleadoDAO contratoDAO;
    private ContratoEmpleado contratoEmpleado;
    private boolean registro;
    private boolean estado;

    public CrearContrato() {
        initComponents();
        this.empleadoDAO = new EmpleadoDAO();
    }

    private void initComponents() {
        this.panelAdmin = new JPanel();
        this.panelAdmin.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.panelAdmin.setBounds(0, 0, 690, 590);
        this.panelAdmin.setLayout(null);

        this.registroContrato = new ContratoEmpleadoDAO();
        this.panelContrato = new JPanel();
        this.panelContrato.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.panelContrato.setLayout(null);
        this.panelContrato.setBorder(this.borderContrato);
        this.panelContrato.setSize(500, 580);

        this.jlCodigoContrato = new JLabel("- Contrato N°:");
        this.jlCodigoContrato.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlCodigoContrato.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlCodigoContrato.setBounds(10, 60, 180, 30);

        this.jtCodigoContrato = new JTextField(this.registroContrato.generarCodigoContrato());
        this.jtCodigoContrato.setBackground(Color.lightGray);
        this.jtCodigoContrato.setEditable(false);
        this.jtCodigoContrato.setBounds(190, 60, 300, 30);

        this.jlFechaContratacion = new JLabel("- Fecha Contratacion:");
        this.jlFechaContratacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlFechaContratacion.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlFechaContratacion.setBounds(10, 100, 180, 30);

        this.jtFechaContratacion = new JDateChooser();
        this.jtFechaContratacion.setBounds(190, 100, 300, 30);

        this.jlCargo = new JLabel("- Cargo:");
        this.jlCargo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlCargo.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlCargo.setBounds(10, 140, 180, 30);

        this.jtCargo = new JTextField();
        this.jtCargo.setBounds(190, 140, 300, 30);

        this.jlSalario = new JLabel("- Salario:");
        this.jlSalario.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlSalario.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlSalario.setBounds(10, 180, 180, 30);

        this.jtSalario = new JTextField();
        this.jtSalario.setBounds(190, 180, 300, 30);

        this.jlTiempoContrato = new JLabel("- Tiempo Contratado:");
        this.jlTiempoContrato.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlTiempoContrato.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlTiempoContrato.setBounds(10, 220, 180, 30);

        this.jtTiempoContrato = new JTextField();
        this.jtTiempoContrato.setBounds(190, 220, 300, 30);

        this.jlInicioFunciones = new JLabel("- Inicio Funciones:");
        this.jlInicioFunciones.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlInicioFunciones.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlInicioFunciones.setBounds(10, 300, 180, 30);

        this.jtInicioFunciones = new JDateChooser();
        this.jtInicioFunciones.setBounds(190, 300, 300, 30);

        this.jlTipoContrato = new JLabel("- Tipo de Contratacion:");
        this.jlTipoContrato.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlTipoContrato.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlTipoContrato.setBounds(10, 260, 180, 30);

        String[] TipoContratos = {"Seleccione un campo", "Tiempo Completo", "Medio Tiempo", "A Contrato"};

        this.jcTipoContrato = new JComboBox(TipoContratos);
        this.jcTipoContrato.setBounds(190, 260, 300, 30);

        this.jlObservaciones = new JLabel("- Observaciones adicionales:");
        this.jlObservaciones.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlObservaciones.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlObservaciones.setBounds(10, 340, 300, 30);

        this.jtObservaciones = new JTextArea();
        this.jtObservaciones.setLineWrap(true);
        this.jtObservaciones.setBounds(50, 370, 400, 200);

        this.jsObservaciones = new JScrollPane(this.jtObservaciones);
        this.jsObservaciones.setBounds(50, 370, 450, 200);

        this.panelContrato.add(this.jlCodigoContrato);
        this.panelContrato.add(this.jtCodigoContrato);
        this.panelContrato.add(this.jlFechaContratacion);
        this.panelContrato.add(this.jtFechaContratacion);
        this.panelContrato.add(this.jlCargo);
        this.panelContrato.add(this.jtCargo);
        this.panelContrato.add(this.jlSalario);
        this.panelContrato.add(this.jtSalario);
        this.panelContrato.add(this.jlTiempoContrato);
        this.panelContrato.add(this.jtTiempoContrato);
        this.panelContrato.add(this.jlInicioFunciones);
        this.panelContrato.add(this.jtInicioFunciones);
        this.panelContrato.add(this.jlTipoContrato);
        this.panelContrato.add(this.jcTipoContrato);
        this.panelContrato.add(this.jlObservaciones);
        this.panelContrato.add(this.jsObservaciones);
    }

    public void abrirDialogo() {
        this.ventana = new JDialog();
        this.ventana.setSize(690, 600);
        this.ventana.setModal(true);
        this.ventana.setLayout(null);
        this.ventana.setTitle(GlobalConfigSystem.getAplicationTitle());
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setResizable(false);

        this.jlIdentificacion = new JLabel("- Identificación:");
        this.jlIdentificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.jlIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlIdentificacion.setBounds(10, 20, 200, 16);
        this.panelContrato.add(this.jlIdentificacion);

        this.jtIdentificacion = new JTextField();
        this.jtIdentificacion.setBounds(190, 20, 300, 30);
        this.jtIdentificacion.addFocusListener(new FocusAdapter() {
            private boolean verificacion;

            @Override
            public void focusLost(FocusEvent e) {
                this.verificacion = CrearContrato.this.empleadoDAO.verificarExistencia(CrearContrato.this.jtIdentificacion.getText());
                if (!this.verificacion) {
                    JOptionPane.showMessageDialog(CrearContrato.this.panelContrato, "El número de identificacion Ingresado NO esta Registrado.", GlobalConfigSystem.getAplicationTitle(), 0);
                    CrearContrato.this.jbAceptar.setEnabled(false);
                } else {
                    int id = CrearContrato.this.registroContrato.obtenerContratoActivo(CrearContrato.this.jtIdentificacion.getText());
                    if (id > 0) {
                        JOptionPane.showMessageDialog(null, "El Empleado con identificación: " + CrearContrato.this.jtIdentificacion.getText() + " actualmente tiene un contrato activo.\n" + "Código de Conttrao registrado actualmente = " + id + "\nNo puede registrar mas de un contrato activo por empleado.", GlobalConfigSystem.getAplicationTitle(), 0);

                        CrearContrato.this.ventana.dispose();
                    } else {
                        CrearContrato.this.jbAceptar.setEnabled(true);
                    }
                }
            }
        });
        this.panelContrato.add(this.jtIdentificacion);

        this.jbAceptar = new JButton("Registrar");
        this.jbAceptar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
        this.jbAceptar.setToolTipText("Registrar un contrato con los datos Ingresados previamente");
        this.jbAceptar.setBounds(525, 370, 150, 30);
        this.jbAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                CrearContrato.access$602(CrearContrato.this, CrearContrato.this.registrarContrato());
//                if (CrearContrato.this.estado) {
//                    CrearContrato.this.ventana.dispose();
//                }
            }
        });
        this.jbCancelar = new JButton("Cancelar");
        this.jbCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
        this.jbCancelar.setBounds(525, 410, 150, 30);
        this.jbCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CrearContrato.this.ventana.dispose();
            }
        });
        this.panelAdmin.add(this.panelContrato);
        this.panelAdmin.add(this.jbAceptar);
        this.panelAdmin.add(this.jbCancelar);
        this.ventana.add(this.panelAdmin);
        this.ventana.setVisible(true);
    }

    private boolean registrarContrato() {
        try {
            String identificacion = this.jtIdentificacion.getText();
            Date fechaContratacion = this.jtFechaContratacion.getDate();
            String cargo = this.jtCargo.getText();
            Double salario = Double.valueOf(Double.parseDouble(this.jtSalario.getText()));
            int tiempoContratado = Integer.parseInt(this.jtTiempoContrato.getText());
            String tipoContrato = (String) this.jcTipoContrato.getSelectedItem();
            Date inicioFunciones = this.jtInicioFunciones.getDate();
            String observaciones = this.jtObservaciones.getText();

            this.contratoEmpleado = new ContratoEmpleado(NiconLibTools.dateFormatSimple(fechaContratacion), cargo, salario.doubleValue(), tiempoContratado, tipoContrato, NiconLibTools.dateFormatSimple(inicioFunciones), identificacion, observaciones);
            this.contratoDAO = new ContratoEmpleadoDAO(this.contratoEmpleado);
            this.registro = this.contratoDAO.registrarContrato();
            if (this.registro == true) {
                JOptionPane.showMessageDialog(null, "El contrato ha sido registrado exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
                this.registro = this.empleadoDAO.cambiarEstadoEmpleado(identificacion, true);
                if (this.registro) {
                    JOptionPane.showMessageDialog(null, "El empleado con identificación:  " + identificacion + ":\n" + "ha sido reactivao nuevamente, su Ingreso ha sido permitido a la planta.", GlobalConfigSystem.getAplicationTitle(), 1);

                    this.estado = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El contrato No se registro, por favor verifique e intente de nuevo\nen caso de que el error sea repetitivo\nInforme al fabricante del Software", GlobalConfigSystem.getAplicationTitle(), 0);
                this.estado = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrearContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.estado;
    }

    public JComboBox getJcTipoContrato() {
        return this.jcTipoContrato;
    }

    public JTextField getJtCargo() {
        return this.jtCargo;
    }

    public JTextField getJtCodigoContrato() {
        return this.jtCodigoContrato;
    }

    public JDateChooser getJtFechaContratacion() {
        return this.jtFechaContratacion;
    }

    public JDateChooser getJtInicioFunciones() {
        return this.jtInicioFunciones;
    }

    public JTextArea getJtObservaciones() {
        return this.jtObservaciones;
    }

    public JTextField getJtSalario() {
        return this.jtSalario;
    }

    public JTextField getJtTiempoContrato() {
        return this.jtTiempoContrato;
    }

    public JPanel clonarPanel() {
        return this.panelContrato;
    }
}
