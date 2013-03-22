/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Empleados;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import nicon.enterprise.libCore.api.dao.EmpleadoDAO;
import nicon.enterprise.libCore.api.obj.Empleado;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.util.NiconLibTools;

/**
 * un objeto de esta clase representa un Jpanel con la vista que permite
 * ingresar los datos de un nuevo empleado dentro de la fuente de datos, este
 * objeto JPanel puede ser retornado a una vista mayoor o puede ser visualizado
 * desde el mismo JDialog que provee esta clase
 *
 * @author frederick
 */
public class PanelCrearEmpleado {

    private JDialog ventana;
    private JPanel panelEmpleado;
    private JPanel panelVentana;
    private TitledBorder border;
    private JLabel titulo;
    private JLabel jlIdentificacion;
    private JLabel jlNombres;
    private JLabel jlApellidos;
    private JLabel jlFechaNacimiento;
    private JLabel jlLugarNacimiento;
    private JLabel jlEstadoCivil;
    private JLabel jlDireccion;
    private JLabel jlBarrio;
    private JLabel jlCiudad;
    private JLabel jlTelFijo;
    private JLabel jlTelMovil;
    private JLabel jlEmail;
    private JTextField jtIdentificacion;
    private JTextField jtNombres;
    private JTextField jtApellidos;
    private JDateChooser jtFechaNacimiento;
    private JTextField jtLugarNacimiento;
    private JTextField jtEstadoCivil;
    private JTextField jtDireccion;
    private JTextField jtBarrio;
    private JTextField jtCiudad;
    private JTextField jtTelFijo;
    private JTextField jtTelMovil;
    private JTextField jtEmail;
    private Empleado empleado;
    private EmpleadoDAO apiEmpleado;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String lugarNacimiento;
    private String estadoCivil;
    private String direccion;
    private String ciudad;
    private String barrio;
    private String telefonoFijo;
    private String telefonoMovil;
    private String email;
    private JButton aceptar;
    private JButton limpiar;
    private JButton cancelar;
    private boolean state;

    public PanelCrearEmpleado() {
        initComponents();
    }

    private void initComponents() {

        border = BorderFactory.createTitledBorder("");

        panelEmpleado = new JPanel();
        panelEmpleado.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelEmpleado.setBorder(border);
        panelEmpleado.setSize(600, 630);
        panelEmpleado.setLayout(null);

        titulo = new JLabel("Información Personal");
        titulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
        titulo.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        titulo.setBounds(5, 20, 400, 40);

        jlIdentificacion = new JLabel("- Ingrese la Identificación:");
        jlIdentificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
        jlIdentificacion.setBounds(40, 120, 180, 25);

        jtIdentificacion = new JTextField();
        jtIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
        jtIdentificacion.setForeground(Color.darkGray);
        jtIdentificacion.setToolTipText("Ingrese el numero de identificacion del empleado");
        jtIdentificacion.setBounds(260, 120, 300, 30);

        jlNombres = new JLabel("- Ingrese los Nombres:");
        jlNombres.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlNombres.setFont(GlobalConfigSystem.getFontAplicationText());
        jlNombres.setBounds(40, 160, 180, 30);

        jtNombres = new JTextField();
        jtNombres.setFont(GlobalConfigSystem.getFontAplicationText());
        jtNombres.setForeground(Color.darkGray);
        jtNombres.setToolTipText("Ingrese los Nombres del empleado");
        jtNombres.setBounds(260, 160, 300, 30);

        jlApellidos = new JLabel("- Ingrese los Apellidos:");
        jlApellidos.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlApellidos.setFont(GlobalConfigSystem.getFontAplicationText());
        jlApellidos.setBounds(40, 200, 180, 30);

        jtApellidos = new JTextField();
        jtApellidos.setFont(GlobalConfigSystem.getFontAplicationText());
        jtApellidos.setForeground(Color.darkGray);
        jtApellidos.setToolTipText("Ingrese los apellidos del empleado");
        jtApellidos.setBounds(260, 200, 300, 30);

        jlFechaNacimiento = new JLabel("- Fecha de Nacimiento:");
        jlFechaNacimiento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlFechaNacimiento.setFont(GlobalConfigSystem.getFontAplicationText());
        jlFechaNacimiento.setBounds(40, 240, 180, 30);

        jtFechaNacimiento = new JDateChooser();
        jtFechaNacimiento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jtFechaNacimiento.setToolTipText("Ingrese la fecha de nacimiento");
        jtFechaNacimiento.setBounds(260, 240, 300, 30);

        jlLugarNacimiento = new JLabel("- Lugar Nacimiento:");
        jlLugarNacimiento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlLugarNacimiento.setFont(GlobalConfigSystem.getFontAplicationText());
        jlLugarNacimiento.setBounds(40, 280, 180, 30);

        jtLugarNacimiento = new JTextField();
        jtLugarNacimiento.setFont(GlobalConfigSystem.getFontAplicationText());
        jtLugarNacimiento.setForeground(Color.darkGray);
        jtLugarNacimiento.setToolTipText("Ingrese la ciudad de nacimiento");
        jtLugarNacimiento.setBounds(260, 280, 300, 30);

        jlEstadoCivil = new JLabel("- Estado Civil:");
        jlEstadoCivil.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlEstadoCivil.setFont(GlobalConfigSystem.getFontAplicationText());
        jlEstadoCivil.setBounds(40, 320, 180, 30);

        jtEstadoCivil = new JTextField();
        jtEstadoCivil.setFont(GlobalConfigSystem.getFontAplicationText());
        jtEstadoCivil.setForeground(Color.darkGray);
        jtEstadoCivil.setToolTipText("Ingrese el estado Civil del empleado");
        jtEstadoCivil.setBounds(260, 320, 300, 30);

        jlDireccion = new JLabel("- Direccion:");
        jlDireccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
        jlDireccion.setBounds(40, 360, 180, 30);

        jtDireccion = new JTextField();
        jtDireccion.setForeground(Color.darkGray);
        jtDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
        jtDireccion.setToolTipText("Ingrese la Direccion de residencia del empleado");
        jtDireccion.setBounds(260, 360, 300, 30);

        jlBarrio = new JLabel("- Barrio");
        jlBarrio.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlBarrio.setFont(GlobalConfigSystem.getFontAplicationText());
        jlBarrio.setBounds(40, 400, 180, 30);

        jtBarrio = new JTextField();
        jtBarrio.setFont(GlobalConfigSystem.getFontAplicationText());
        jtBarrio.setForeground(Color.darkGray);
        jtBarrio.setToolTipText("Ingrese el Barrio ");
        jtBarrio.setBounds(260, 400, 300, 30);

        jlCiudad = new JLabel("- Ciudad:");
        jlCiudad.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlCiudad.setFont(GlobalConfigSystem.getFontAplicationText());
        jlCiudad.setBounds(40, 440, 180, 30);

        jtCiudad = new JTextField();
        jtCiudad.setFont(GlobalConfigSystem.getFontAplicationText());
        jtCiudad.setForeground(Color.darkGray);
        jtCiudad.setBounds(260, 440, 300, 30);

        jlTelFijo = new JLabel("- Telefono Fijo:");
        jlTelFijo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlTelFijo.setFont(GlobalConfigSystem.getFontAplicationText());
        jlTelFijo.setBounds(40, 480, 180, 30);

        jtTelFijo = new JTextField();
        jtTelFijo.setForeground(Color.darkGray);
        jtTelFijo.setFont(GlobalConfigSystem.getFontAplicationText());
        jtTelFijo.setBounds(260, 480, 300, 30);

        jlTelMovil = new JLabel("- Telefono movil:");
        jlTelMovil.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlTelMovil.setFont(GlobalConfigSystem.getFontAplicationText());
        jlTelMovil.setBounds(40, 520, 180, 30);

        jtTelMovil = new JTextField();
        jtTelMovil.setFont(GlobalConfigSystem.getFontAplicationText());
        jtTelMovil.setForeground(Color.darkGray);
        jtTelMovil.setBounds(260, 520, 300, 30);

        jlEmail = new JLabel("- Correo Electónico:");
        jlEmail.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        jlEmail.setFont(GlobalConfigSystem.getFontAplicationText());
        jlEmail.setBounds(40, 560, 180, 30);

        jtEmail = new JTextField();
        jtEmail.setFont(GlobalConfigSystem.getFontAplicationText());
        jtEmail.setForeground(Color.darkGray);
        jtEmail.setBounds(260, 560, 300, 30);

        panelEmpleado.add(titulo);
        panelEmpleado.add(jlIdentificacion);
        panelEmpleado.add(jtIdentificacion);
        panelEmpleado.add(jlNombres);
        panelEmpleado.add(jtNombres);
        panelEmpleado.add(jlApellidos);
        panelEmpleado.add(jtApellidos);
        panelEmpleado.add(jlFechaNacimiento);
        panelEmpleado.add(jtFechaNacimiento);
        panelEmpleado.add(jlLugarNacimiento);
        panelEmpleado.add(jtLugarNacimiento);
        panelEmpleado.add(jlEstadoCivil);
        panelEmpleado.add(jtEstadoCivil);
        panelEmpleado.add(jlDireccion);
        panelEmpleado.add(jtDireccion);
        panelEmpleado.add(jlBarrio);
        panelEmpleado.add(jtBarrio);
        panelEmpleado.add(jlCiudad);
        panelEmpleado.add(jtCiudad);
        panelEmpleado.add(jlTelFijo);
        panelEmpleado.add(jtTelFijo);
        panelEmpleado.add(jlTelMovil);
        panelEmpleado.add(jtTelMovil);
        panelEmpleado.add(jlEmail);
        panelEmpleado.add(jtEmail);
    }

    /**
     * este metodo permite obtener un clon exacto del panel de creacion y registro
     * de datos para el ingreso de un empleado.
     * @return JPanel panelEmpleado
     */
    public JPanel obtenerPanel() {
        return panelEmpleado;
    }

    /**
     * Este metodo permite obtener un objeto de tipo Empleado que se crea
     * con los datos ingresados por el usuario en la interfaz y que se convierten
     * en los atributos del empleado que será registrado en la fuente de datos,
     * este metodo valida los valores de los campos ingresados por el usuario
     * dentro de la vista y retorna el objeto.
     * 
     * @return Empleado empleado
     */
    public Empleado obtenerEmpleado() {
        try {
            identificacion = jtIdentificacion.getText();
            nombres = jtNombres.getText();
            apellidos = jtApellidos.getText();
            fechaNacimiento = NiconLibTools.parseToMysqlStringDate(jtFechaNacimiento.getDate());
            lugarNacimiento = jtLugarNacimiento.getText();
            estadoCivil = jtEstadoCivil.getText();
            direccion = jtDireccion.getText();
            barrio = jtBarrio.getText();
            ciudad = jtCiudad.getText();
            telefonoFijo = jtTelFijo.getText();
            telefonoMovil = jtTelMovil.getText();
            email = jtEmail.getText();

            if (identificacion.equals("") || nombres.equals("") || apellidos.equals("") || fechaNacimiento.equals("") || lugarNacimiento.equals("") || estadoCivil.equals("") || direccion.equals("") || barrio.equals("") || ciudad.equals("") || telefonoMovil.equals("")) {
                JOptionPane.showMessageDialog(null, "Hay datos importantes sin ingresar, verifique e intente de nuevo.", GlobalConfigSystem.getAplicationTitle(), JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconError.png")));
            } else {
                empleado = new Empleado(identificacion, nombres, apellidos, fechaNacimiento, lugarNacimiento, estadoCivil, direccion, barrio, ciudad, telefonoFijo, telefonoMovil, email, false);
            }
        } catch (ParseException ex) {
            Logger.getLogger(PanelCrearEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }

    /**
     * este metodo permite mostrar en una ventana de JDialog un panel para el
     * registro de los datos de un nuevo empleado, este registro ser hará y se 
     * ingresará el empleado dentro de la fuente de datos pero su estado será
     * false con lo cual no podrá ser activado hasta registrar un contrato a su
     * nombre.
     */
    public void mostrarVentana() {
        ventana = new JDialog();
        ventana.setSize(770, 650);
        ventana.setLocationRelativeTo(null);
        ventana.setModal(true);
        ventana.setTitle(GlobalConfigSystem.getAplicationTitle());
        ventana.setBackground(GlobalConfigSystem.getBackgroundAplicationFrame());
        ventana.setResizable(false);

        aceptar = new JButton("Guardar");
        aceptar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
        aceptar.setBounds(610, 480, 140, 30);
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                registrarEmpleado(obtenerEmpleado());
            }
        });

        limpiar = new JButton("Limpiar");
        limpiar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconUpdateMenu.png")));
        limpiar.setBounds(610, 520, 140, 30);
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limpiarCampos();
            }
        });

        cancelar = new JButton("Cancelar");
        cancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
        cancelar.setBounds(610, 560, 140, 30);
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ventana.dispose();
            }
        });

        panelVentana = new JPanel();
        panelVentana.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelVentana.setLayout(null);

        panelVentana.add(panelEmpleado);
        panelVentana.add(aceptar);
        panelVentana.add(limpiar);
        panelVentana.add(cancelar);

        ventana.add(panelVentana);
        ventana.setVisible(true);
    }

    /**
     * Este metodo permite limpiar todos los campos de texto de la vista preparandolos
     * para un nuevo servicio de ingreso de datos.
     */
    public void limpiarCampos() {
        this.jtIdentificacion.setText("");
        this.jtNombres.setText("");
        this.jtApellidos.setText("");
        this.jtBarrio.setText("");
        this.jtCiudad.setText("");
        this.jtDireccion.setText("");
        this.jtEmail.setText("");
        this.jtEstadoCivil.setText("");
        this.jtLugarNacimiento.setText("");
        this.jtTelFijo.setText("");
        this.jtTelMovil.setText("");
    }

    /**
     * este metodo permite guadar un objeto de tipo empleado dentro de la fuente
     * de datos, el objeto empleado es armado con los datos ingresados por el 
     * usuario dentro de la vista de ingreso, hace uso del API EmpleadoDAO y retorna
     * un boolean con el estado de la oepracion.
     * 
     * @param empleado 
     */
    public boolean registrarEmpleado(Empleado empleado) {
        try {
            apiEmpleado = new EmpleadoDAO(empleado);
            if (apiEmpleado.createEmployee()) {
                JOptionPane.showMessageDialog(null, "El empleado se registró correctamente.", GlobalConfigSystem.getAplicationTitle(), JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPositive.png")));
//                ventana.dispose();
                state=true;
            }else{
                state=false;
            }
        }catch (SQLException ex) {
            Logger.getLogger(PanelCrearEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return state;
    }
}
