/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */

package nicon.enterprise.gui.Empleados;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.ContratoEmpleadoDAO;
import nicon.enterprise.libCore.api.dao.EmpleadoDAO;
import nicon.enterprise.libCore.api.obj.ContratoEmpleado;
import nicon.enterprise.libCore.api.util.NiconLibTools;

public class PanelCrearContrato {

    private JPanel panelContrato;
    private JPanel panelAdmin;
        
    private JLabel titulo;
    private JLabel jlIdentificacion;
    private JLabel jlCodigoContrato;
    private JLabel jlFechaContratacion;
    private JLabel jlCargo;
    private JLabel jlSalario;
    private JLabel jlTiempoContrato;
    private JLabel jlInicioFunciones;
    private JLabel jlTipoContrato;
    private JLabel jlFunciones;
    private JLabel jlObservaciones;
    
    private JTextField jtIdentificacion;
    private JTextField jtCodigoContrato;
    private JDateChooser jtFechaContratacion;
    private JTextField jtCargo;
    private JTextField jtSalario;
    private JTextField jtTiempoContrato;
    private JDateChooser jtInicioFunciones;
    private JComboBox jcTipoContrato;
    private JTextArea jtObservaciones;
    private JTextArea jtFunciones;
    private JScrollPane jsObservaciones;
    private JScrollPane jsFunciones;
    
    private ContratoEmpleadoDAO registroContrato;
    
    private JDialog ventana;
    private JButton jbAceptar;
    private JButton jbCancelar;   
    
    private EmpleadoDAO empleadoDAO;
    private ContratoEmpleadoDAO contratoDAO;
    private ContratoEmpleado contratoEmpleado;
    
    private boolean registro;
    private boolean estado;
    
    private String identificacion;
    private String fechaContratacion;
    private String cargo;
    private Double salario;
    private int tiempoContratado;
    private String tipoContrato;
    private String inicioFunciones;
    private String observaciones;
    private String fechaFinContrato;
    private JScrollPane scrollPane;
    private String funciones;
    private String Icons;
    

    public PanelCrearContrato() {
        initComponents();
    }

    private void initComponents() {
        
        Icons=GlobalConfigSystem.getIconsPath();
        registroContrato = new ContratoEmpleadoDAO();
        empleadoDAO = new EmpleadoDAO();

        panelAdmin = new JPanel();
        panelAdmin.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelAdmin.setBounds(0, 0, 690, 630);
        panelAdmin.setLayout(null);

        panelContrato = new JPanel();
        panelContrato.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelContrato.setLayout(null);
        panelContrato.setPreferredSize(new Dimension(600,700));
        
        titulo = new JLabel(" Información de contratación");
        titulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
        titulo.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        titulo.setBounds(5, 20, 600, 40);

        jlCodigoContrato = new JLabel("- Contrato N°:");
        jlCodigoContrato.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlCodigoContrato.setFont(GlobalConfigSystem.getFontAplicationText());
        jlCodigoContrato.setBounds(40, 120, 180, 20);

        jtCodigoContrato = new JTextField();
        jtCodigoContrato.setBackground(Color.lightGray);
        jtCodigoContrato.setEditable(false);
        jtCodigoContrato.setBounds(260, 120, 300, 30);

        jlFechaContratacion = new JLabel("- Fecha Contratacion:");
        jlFechaContratacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlFechaContratacion.setFont(GlobalConfigSystem.getFontAplicationText());
        jlFechaContratacion.setBounds(40, 160, 180, 20);

        jtFechaContratacion = new JDateChooser();
        jtFechaContratacion.setBounds(260, 160, 300, 30);

        jlCargo = new JLabel("- Cargo:");
        jlCargo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlCargo.setFont(GlobalConfigSystem.getFontAplicationText());
        jlCargo.setBounds(40, 200, 180, 20);

        jtCargo = new JTextField();
        jtCargo.setFont(GlobalConfigSystem.getFontAplicationText());
        jtCargo.setForeground(Color.darkGray);
        jtCargo.setBounds(260, 200, 300, 30);

        jlSalario = new JLabel("- Salario:");
        jlSalario.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlSalario.setFont(GlobalConfigSystem.getFontAplicationText());
        jlSalario.setBounds(40, 240, 180, 20);

        jtSalario = new JTextField();
        jtSalario.setFont(GlobalConfigSystem.getFontAplicationText());
        jtSalario.setForeground(Color.darkGray);
        jtSalario.setBounds(260, 240, 300, 30);

        jlTiempoContrato = new JLabel("- Tiempo Contratado:");
        jlTiempoContrato.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlTiempoContrato.setFont(GlobalConfigSystem.getFontAplicationText());
        jlTiempoContrato.setBounds(40, 280, 180, 30);

        jtTiempoContrato = new JTextField();
        jtTiempoContrato.setFont(GlobalConfigSystem.getFontAplicationText());
        jtTiempoContrato.setForeground(Color.darkGray);
        jtTiempoContrato.setBounds(260, 280, 300, 30);

        jlInicioFunciones = new JLabel("- Inicio Funciones:");
        jlInicioFunciones.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlInicioFunciones.setFont(GlobalConfigSystem.getFontAplicationText());
        jlInicioFunciones.setBounds(40, 320, 180, 20);

        jtInicioFunciones = new JDateChooser();
        jtInicioFunciones.setBounds(260, 320, 300, 30);

        jlTipoContrato = new JLabel("- Tipo de Contratacion:");
        jlTipoContrato.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlTipoContrato.setFont(GlobalConfigSystem.getFontAplicationText());
        jlTipoContrato.setBounds(40, 360, 180, 20);

        String[] TipoContratos = {"Seleccione un campo", "Tiempo Completo", "Medio Tiempo", "A Contrato"};

        jcTipoContrato = new JComboBox(TipoContratos);
        jcTipoContrato.setBounds(260, 360, 300, 30);
        
        jlFunciones=new JLabel("Ingrese una descripción de las funciones del empleado:");
        jlFunciones.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlFunciones.setFont(GlobalConfigSystem.getFontAplicationText());
        jlFunciones.setBounds(40, 400, 600, 20);
        
        jtFunciones=new JTextArea();
        jtFunciones.setLineWrap(true);
        jtFunciones.setForeground(Color.darkGray);
        jtFunciones.setFont(GlobalConfigSystem.getFontAplicationText());
        
        jsFunciones=new JScrollPane(jtFunciones);
        jsFunciones.setBounds(40,430,515,100);

        jlObservaciones = new JLabel("- Observaciones adicionales:");
        jlObservaciones.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlObservaciones.setFont(GlobalConfigSystem.getFontAplicationText());
        jlObservaciones.setBounds(40,540, 300,20);

        jtObservaciones = new JTextArea();
        jtObservaciones.setLineWrap(true);

        jsObservaciones = new JScrollPane(jtObservaciones);
        jsObservaciones.setBounds(40,580,515,100);

        panelContrato.add(titulo);
        panelContrato.add(jlCodigoContrato);
        panelContrato.add(jtCodigoContrato);
        panelContrato.add(jlFechaContratacion);
        panelContrato.add(jtFechaContratacion);
        panelContrato.add(jlCargo);
        panelContrato.add(jtCargo);
        panelContrato.add(jlSalario);
        panelContrato.add(jtSalario);
        panelContrato.add(jlTiempoContrato);
        panelContrato.add(jtTiempoContrato);
        panelContrato.add(jlInicioFunciones);
        panelContrato.add(jtInicioFunciones);
        panelContrato.add(jlTipoContrato);
        panelContrato.add(jcTipoContrato);
        panelContrato.add(jlFunciones);
        panelContrato.add(jsFunciones);
        panelContrato.add(jlObservaciones);
        panelContrato.add(jsObservaciones);
    }

    /**
     * Este metodo permite clonar el panel de ingreso de datos para el sistema
     * de contratos de empleados, esto da la facilidad de ser usado en cualquier
     * contenedor mayor.
     *
     * @return JPanel panelContrato.
     */
    public JPanel clonarPanel() {
        return this.panelContrato;
    }

    /**
     * Este metodo permite limpiar todos los campos de ingreso de datos
     * ajustando sus valores a null, es invocado cuando el usuario desea hacer
     * la limpieza de la interfaz para ingresar nuevos valores.
     */
    public void limparCampos() {
        this.jtFechaContratacion.setDate(null);
        this.jtCargo.setText("");
        this.jtIdentificacion.setText("");
        this.jtInicioFunciones.setDate(null);
        this.jtObservaciones.setText("");
        this.jtSalario.setText("");
        this.jtTiempoContrato.setText("");
    }

    public ContratoEmpleado obtenerContrato() {
        try {
            identificacion = jtIdentificacion.getText();
            fechaContratacion = NiconLibTools.parseToMysqlStringDate(jtFechaContratacion.getDate());
            cargo = jtCargo.getText();
            salario = Double.parseDouble(jtSalario.getText());
            tiempoContratado = Integer.parseInt(jtTiempoContrato.getText());
            tipoContrato =(String) jcTipoContrato.getSelectedItem();
            inicioFunciones = NiconLibTools.parseToMysqlStringDate(jtInicioFunciones.getDate());            
            fechaFinContrato=NiconLibTools.sumMonthsToDate(tiempoContratado, jtFechaContratacion.getDate());
            funciones=jtFunciones.getText();
            observaciones = jtObservaciones.getText();            
            contratoEmpleado = new ContratoEmpleado(fechaContratacion,tiempoContratado,fechaFinContrato,tipoContrato,inicioFunciones,cargo,salario,funciones,observaciones,true, identificacion);
        } catch (ParseException ex) {
            Logger.getLogger(PanelCrearContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contratoEmpleado;
    }

    public void abrirDialogo() {
        ventana = new JDialog();
        ventana.setSize(740, 650);
        ventana.setModal(true);
        ventana.setLayout(null);
        ventana.setTitle(GlobalConfigSystem.getAplicationTitle());
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);

        jlIdentificacion = new JLabel("- Identificación:");
        jlIdentificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
        jlIdentificacion.setBounds(40, 80, 200, 16);
        panelContrato.add(jlIdentificacion);

        jtIdentificacion = new JTextField();
        jtIdentificacion.setBounds(260, 80, 300, 30);
        jtIdentificacion.addFocusListener(new FocusAdapter() {
            private boolean verificacion;

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    this.verificacion = PanelCrearContrato.this.empleadoDAO.verificarExistencia(PanelCrearContrato.this.jtIdentificacion.getText());
                    if (!this.verificacion) {
                        JOptionPane.showMessageDialog(PanelCrearContrato.this.panelContrato, "El número de identificacion Ingresado NO esta Registrado.", GlobalConfigSystem.getAplicationTitle(), 0);
                        PanelCrearContrato.this.jbAceptar.setEnabled(false);
                    } else {
                        int id = PanelCrearContrato.this.registroContrato.obtenerIDContratoActivo(PanelCrearContrato.this.jtIdentificacion.getText());
                        if (id > 0) {
                            JOptionPane.showMessageDialog(null, "El Empleado con identificación: " + PanelCrearContrato.this.jtIdentificacion.getText() + " actualmente tiene un contrato activo.\n" + "Código de Conttrao registrado actualmente = " + id + "\nNo puede registrar mas de un contrato activo por empleado.", GlobalConfigSystem.getAplicationTitle(), 0);

                            PanelCrearContrato.this.ventana.dispose();
                        } else {
                            PanelCrearContrato.this.jbAceptar.setEnabled(true);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PanelCrearContrato.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panelContrato.add(jtIdentificacion);
        
        scrollPane=new JScrollPane(panelContrato,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 600, 630);
        scrollPane.getViewport().setView(panelContrato);
        
        this.jbAceptar = new JButton("Registrar");
        this.jbAceptar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
        this.jbAceptar.setToolTipText("Registrar un contrato con los datos Ingresados previamente");
        this.jbAceptar.setBounds(525, 370, 150, 30);
        this.jbAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarContrato(obtenerContrato());
            }
        });
        this.jbCancelar = new JButton("Cancelar");
        this.jbCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
        this.jbCancelar.setBounds(525, 410, 150, 30);
        this.jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelCrearContrato.this.ventana.dispose();
            }
        });
        this.panelAdmin.add(scrollPane);
        this.panelAdmin.add(this.jbAceptar);
        this.panelAdmin.add(this.jbCancelar);
        this.ventana.add(this.panelAdmin);
        this.ventana.setVisible(true);
    }

    public boolean registrarContrato(ContratoEmpleado contrato) {
        try {
            contratoDAO = new ContratoEmpleadoDAO(contrato);
            if (contratoDAO.crearContrato()) {
                JOptionPane.showMessageDialog(null, "El contrato ha sido registrado exitosamente", GlobalConfigSystem.getAplicationTitle(),JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource(Icons+"NiconPositive.png")));
                registro = empleadoDAO.cambiarEstadoEmpleado(identificacion, true);                
            } else {
                JOptionPane.showMessageDialog(null, "El contrato No se registro, por favor verifique e intente de nuevo\nen caso de que el error sea repetitivo\nInforme al fabricante del Software", GlobalConfigSystem.getAplicationTitle(), 0);
                this.estado = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PanelCrearContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
}
