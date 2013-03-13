/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.ConfigConectorDAO;
import nicon.enterprise.libCore.obj.ConfigConector;

/**
 * Module Conector es la interfaz mediante la cual se puede ver la información de configuracion de la coneccion 
 * con la fuente de datos y permite de igual forma ingresar los datos para configurar una nueva configuracion 
 * define dos metodos constructores que ajustan la interfaz para el caso en el que se necesite.
 * 
 * @author frederick
 */
public class ModuleConector extends JDialog {

    private JPanel panelConfig;
    private JLabel jlTitulo;
    private JLabel jlNombre;
    private JLabel jlIPServer;
    private JTextField jtNombre;
    private JTextField jtIPServer;
    private JLabel jlPort;
    private JTextField jtPort;
    private JLabel jlUsuario;
    private JTextField jtUsuario;
    private JLabel jlPassword;
    private JPasswordField jtPassword;
    private JButton jbCancelar;
    private JButton jbAceptar;
    private JLabel jlInformacion;
    private ConfigConector config;
    private ConfigConectorDAO configDAO;
    private boolean state;

    /**
     * Este constructor permite activar la vista del modulo de coneccion para recibir nuevos datos de configuracion
     * y configurar el nuevo archivo, no recibe parametros.
     */
    public ModuleConector() {
        crearPanel();
    }
 
    /**
     * Este metodo permite ajustar la vista en modo de visualizacion de datos, recibe el ConfigConector a visualizar
     * 
     * @param configConector 
     */
    public ModuleConector(ConfigConector configConector) {
        this.config = configConector;
        crearPanel();
        modoLectura();
    }
    
    /**
     * Crear u ajusta el panel principal de la vista
     */
    private void crearPanel() {
        setTitle(GlobalConfigSystem.getAplicationTitle());
        setSize(600,400);
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(null);

        panelConfig = new JPanel();
        panelConfig.setBackground(GlobalConfigSystem.getBackgroundAplication());
        panelConfig.setLayout(null);

        jlTitulo = new JLabel("Nueva Conección");
        jlTitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlTitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
        jlTitulo.setBounds(20, 20, 500, 30);

        jlNombre = new JLabel("- Nombre de la conección:");
        jlNombre.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlNombre.setFont(GlobalConfigSystem.getFontAplicationText());
        jlNombre.setBounds(50, 90, 250, 20);

        jtNombre = new JTextField();
        jtNombre.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        jtNombre.setBounds(260, 85, 300, 30);

        jlIPServer = new JLabel("- Dirección IP a conectar:");
        jlIPServer.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlIPServer.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        jlIPServer.setBounds(50, 140, 220, 20);

        jtIPServer = new JTextField();
        jtIPServer.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        jtIPServer.setBounds(260, 135, 300, 30);

        jlPort = new JLabel("- Puerto de conección:");
        jlPort.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlPort.setFont(GlobalConfigSystem.getFontAplicationText());
        jlPort.setBounds(50, 190, 220, 20);

        jtPort = new JTextField();
        jtPort.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        jtPort.setBounds(260, 185, 300, 30);

        jlUsuario = new JLabel("- Ingrese el Usuario:");
        jlUsuario.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlUsuario.setFont(GlobalConfigSystem.getFontAplicationText());
        jlUsuario.setBounds(50, 240, 220, 20);

        jtUsuario = new JTextField();
        jtUsuario.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        jtUsuario.setBounds(260, 235, 300, 30);

        jlPassword = new JLabel("- Password para el usuario");
        jlPassword.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlPassword.setFont(GlobalConfigSystem.getFontAplicationText());
        jlPassword.setBounds(50, 290, 220, 20);

        jtPassword = new JPasswordField();
        jtPassword.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        jtPassword.setBounds(260, 285, 300, 30);

        jbCancelar = new JButton("Cancelar");
        jbCancelar.setFont(GlobalConfigSystem.getFontAplicationText());
        jbCancelar.setBounds(330, 340, 120, 30);
        jbCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        
        jbAceptar = new JButton("Aceptar");
        jbAceptar.setFont(GlobalConfigSystem.getFontAplicationText());
        jbAceptar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
        jbAceptar.setToolTipText("Crear un nuevo archivo de coneccion a la fuente de datos con los parametros ingresados");
        jbAceptar.setBounds(460, 340, 120, 30);
        jbAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    guardarConfiguracion();
                } catch (IOException ex) {
                    Logger.getLogger(ModuleConector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        jlInformacion = new JLabel();
        jlInformacion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlInformacion.setFont(GlobalConfigSystem.getFontAplicationText());
        jlInformacion.setBounds(10, 340, 400, 20);

        panelConfig.add(this.jlTitulo);
        panelConfig.add(this.jlNombre);
        panelConfig.add(this.jtNombre);
        panelConfig.add(this.jlIPServer);
        panelConfig.add(this.jtIPServer);
        panelConfig.add(this.jlPort);
        panelConfig.add(this.jtPort);
        panelConfig.add(this.jlUsuario);
        panelConfig.add(this.jtUsuario);
        panelConfig.add(this.jlPassword);
        panelConfig.add(this.jtPassword);
        panelConfig.add(this.jbCancelar);
        panelConfig.add(this.jbAceptar);
        panelConfig.add(this.jlInformacion);
        getContentPane().add(this.panelConfig);
    }

    private void modoLectura() {
        jlTitulo.setText("Detalles de Conección:");
        jtNombre.setEditable(false);
        jtIPServer.setEditable(false);
        jtPort.setEditable(false);
        jtUsuario.setEditable(false);
        jtPassword.setEditable(false);
        jtNombre.setText(config.getNAMECONECTION());
        jtIPServer.setText(config.getIPSERVER());
        jtPort.setText(config.getPORT());
        jtUsuario.setText(config.getUSUARIO());
        panelConfig.remove(jbAceptar);
        jbCancelar.setText("Salir");
        jbCancelar.setBounds(460, 340, 120, 30);
        repaint();
    }

    private void guardarConfiguracion() throws IOException {
        String nombre = jtNombre.getText();
        String ip = jtIPServer.getText();
        String Port = jtPort.getText();
        String user = jtUsuario.getText();
        String pass = String.valueOf(jtPassword.getPassword());

        if ((nombre.equals("")) || (ip.equals("")) || (Port.equals("")) || (user.equals("")) || (pass.equals(""))) {
            JOptionPane.showMessageDialog(null, "Hay campos sin ingresar por favor verifique e intente de nuevo", GlobalConfigSystem.getAplicationTitle(),JOptionPane.ERROR_MESSAGE,new javax.swing.ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconError.png")));
        } else {            
            config = new ConfigConector(nombre, ip, Port, "NiconEnterprise", user, pass);
            configDAO = new ConfigConectorDAO(config);
            state = configDAO.createConfigFile();
            if (state) {
                jlInformacion.setText("Nueva configuracion terminada ...");
                panelConfig.repaint();
                JOptionPane.showMessageDialog(panelConfig, "La nueva configuración ha sido establecida en Conector.conf exitosamente", GlobalConfigSystem.getAplicationTitle(),JOptionPane.INFORMATION_MESSAGE,new javax.swing.ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconPositive.png")));
                dispose();
            }
        }
    }
}