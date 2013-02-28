/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.ConfigConectorDAO;
import nicon.enterprise.libCore.obj.ConfigConector;

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

    public ModuleConector() {
        crearPanel();
    }

    public ModuleConector(ConfigConector configConector) {
        this.config = configConector;
        crearPanel();
        ajustarInterfaz();
    }

    private void crearPanel() {
        setTitle(GlobalConfigSystem.getAplicationTitle());
        setSize(600, 390);
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(null);

        this.panelConfig = new JPanel();
        this.panelConfig.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.panelConfig.setLayout(null);

        this.jlTitulo = new JLabel("Nueva Conección");
        this.jlTitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlTitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
        this.jlTitulo.setBounds(20, 20, 500, 30);

        this.jlNombre = new JLabel("- Nombre de la conección:");
        this.jlNombre.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlNombre.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlNombre.setBounds(50, 90, 250, 20);

        this.jtNombre = new JTextField();
        this.jtNombre.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        this.jtNombre.setBounds(260, 85, 300, 30);

        this.jlIPServer = new JLabel("- Dirección IP a conectar:");
        this.jlIPServer.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlIPServer.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        this.jlIPServer.setBounds(50, 140, 220, 20);

        this.jtIPServer = new JTextField();
        this.jtIPServer.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        this.jtIPServer.setBounds(260, 135, 300, 30);

        this.jlPort = new JLabel("- Puerto de conección:");
        this.jlPort.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlPort.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlPort.setBounds(50, 190, 220, 20);

        this.jtPort = new JTextField();
        this.jtPort.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        this.jtPort.setBounds(260, 185, 300, 30);

        this.jlUsuario = new JLabel("- Ingrese el Usuario:");
        this.jlUsuario.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlUsuario.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlUsuario.setBounds(50, 240, 220, 20);

        this.jtUsuario = new JTextField();
        this.jtUsuario.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        this.jtUsuario.setBounds(260, 235, 300, 30);

        this.jlPassword = new JLabel("- Password para el usuario");
        this.jlPassword.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlPassword.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlPassword.setBounds(50, 290, 220, 20);

        this.jtPassword = new JPasswordField();
        this.jtPassword.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        this.jtPassword.setBounds(260, 285, 300, 30);

        this.jbCancelar = new JButton("Cancelar");
        this.jbCancelar.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jbCancelar.setBounds(330, 340, 120, 30);
        this.jbCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
        this.jbCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ModuleConector.this.dispose();
            }
        });
        this.jbAceptar = new JButton("Aceptar");
        this.jbAceptar.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jbAceptar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
        this.jbAceptar.setToolTipText("Crear un nuevo archivo de coneccion a la fuente de datos con los parametros ingresados");
        this.jbAceptar.setBounds(460, 340, 120, 30);
        this.jbAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ModuleConector.this.guardarConfiguracion();
            }
        });
        this.jlInformacion = new JLabel();
        this.jlInformacion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlInformacion.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlInformacion.setBounds(10, 340, 400, 20);

        this.panelConfig.add(this.jlTitulo);
        this.panelConfig.add(this.jlNombre);
        this.panelConfig.add(this.jtNombre);
        this.panelConfig.add(this.jlIPServer);
        this.panelConfig.add(this.jtIPServer);
        this.panelConfig.add(this.jlPort);
        this.panelConfig.add(this.jtPort);
        this.panelConfig.add(this.jlUsuario);
        this.panelConfig.add(this.jtUsuario);
        this.panelConfig.add(this.jlPassword);
        this.panelConfig.add(this.jtPassword);
        this.panelConfig.add(this.jbCancelar);
        this.panelConfig.add(this.jbAceptar);
        this.panelConfig.add(this.jlInformacion);
        getContentPane().add(this.panelConfig);
    }

    private void ajustarInterfaz() {
        this.jlTitulo.setText("Detalles de Conección:");
        this.jtNombre.setEditable(false);
        this.jtIPServer.setEditable(false);
        this.jtPort.setEditable(false);
        this.jtUsuario.setEditable(false);
        this.jtPassword.setEditable(false);
        this.jtNombre.setText(this.config.getNAMECONECTION());
        this.jtIPServer.setText(this.config.getIPSERVER());
        this.jtPort.setText(this.config.getPORT());
        this.jtUsuario.setText(this.config.getUSUARIO());
        this.panelConfig.remove(this.jbAceptar);
        this.jbCancelar.setText("Salir");
        this.jbCancelar.setBounds(460, 340, 120, 30);
        repaint();
    }

    private void guardarConfiguracion() {
        String nombre = this.jtNombre.getText();
        String ip = this.jtIPServer.getText();
        String Port = this.jtPort.getText();
        String user = this.jtUsuario.getText();
        String pass = String.valueOf(this.jtPassword.getPassword());

        if ((nombre.equals("")) || (ip.equals("")) || (Port.equals("")) || (user.equals("")) || (pass.equals(""))) {
            JOptionPane.showMessageDialog(null, "Hay campos in ingresar", GlobalConfigSystem.getAplicationTitle(), 0);
        } else {
            this.jlInformacion.setText("Obteniendo nuevos parametros ...");
            this.config = new ConfigConector(nombre, ip, Port, "NiconEnterprise", user, pass);
            this.configDAO = new ConfigConectorDAO(this.config);
            this.jlInformacion.setText("Eliminando configuración actual ...");
            this.configDAO.deleteConfigFile();
            this.state = this.configDAO.createConfigFile();
            if (this.state) {
                this.jlInformacion.setText("Nueva configuracion terminada ...");
                JOptionPane.showMessageDialog(this.panelConfig, "La nueva configuración ha sido establecida en Conector.conf exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
                dispose();
            }
        }
    }
}