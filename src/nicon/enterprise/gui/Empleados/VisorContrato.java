/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Empleados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.obj.ContratoEmpleado;

public class VisorContrato extends JDialog{
    
  private ContratoEmpleado contrato;
  private String Nombres;
  private JPanel panelVisor;
  private JLabel jlFechaContratacion;
  private JLabel jlNumeroContrato;
  private JLabel numeroContrato;
  private JLabel fechaContratacion;
  private JLabel jlInicioFunciones;
  private JLabel inicioFunciones;
  private JLabel jlCargo;
  private JLabel cargo;
  private JLabel jlTiempoContratado;
  private JLabel tiempoContratado;
  private JLabel jlTipoContrato;
  private JLabel jlNombres;
  private JLabel tipoContrato;
  private JLabel jlSalario;
  private JLabel salario;
  private JLabel jlEstado;
  private JTextArea jtObservaciones;
  private JTextField jtEstado;
  private JButton jbAceptar;
  private String iconsPath;

  public VisorContrato(ContratoEmpleado contrato, String Nombres)
  {
    setSize(750, 440);
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setResizable(false);
    setLocationRelativeTo(null);
    setModal(true);
    this.contrato = contrato;
    this.Nombres = Nombres;
    this.iconsPath = GlobalConfigSystem.getIconsPath();
    initComponents();
    verificarEstado();
  }

  private void initComponents()
  {
    this.panelVisor = new JPanel();
    this.panelVisor.setLayout(null);
    this.panelVisor.setBackground(GlobalConfigSystem.getBackgroundAplication());

    this.jlNombres = new JLabel(this.Nombres);
    this.jlNombres.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlNombres.setFont(GlobalConfigSystem.getFontAplicationTitle());
    this.jlNombres.setBounds(5, 5, 700, 40);

    this.jlNumeroContrato = new JLabel("- Número Contrato:");
    this.jlNumeroContrato.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlNumeroContrato.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jlNumeroContrato.setBounds(20, 70, 180, 16);

    this.numeroContrato = new JLabel(String.valueOf(this.contrato.getIdContrato()));
    this.numeroContrato.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.numeroContrato.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.numeroContrato.setBounds(200, 70, 300, 16);

    this.jlFechaContratacion = new JLabel("- Fecha Cotratación:");
    this.jlFechaContratacion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlFechaContratacion.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jlFechaContratacion.setBounds(20, 100, 250, 16);

    this.fechaContratacion = new JLabel(this.contrato.getFechaContratacion());
    this.fechaContratacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.fechaContratacion.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.fechaContratacion.setBounds(200, 100, 250, 16);

    this.jlInicioFunciones = new JLabel("- Inicio Funciones:");
    this.jlInicioFunciones.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlInicioFunciones.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jlInicioFunciones.setBounds(320, 100, 250, 16);

    this.inicioFunciones = new JLabel(this.contrato.getInicioFunciones());
    this.inicioFunciones.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.inicioFunciones.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.inicioFunciones.setBounds(470, 100, 200, 16);

    this.jlCargo = new JLabel("- Cargo:");
    this.jlCargo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlCargo.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jlCargo.setBounds(20, 130, 150, 16);

    this.cargo = new JLabel(this.contrato.getCargo());
    this.cargo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.cargo.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.cargo.setBounds(120, 130, 200, 16);

    this.jlTiempoContratado = new JLabel("- Tiempo Contratado:");
    this.jlTiempoContratado.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlTiempoContratado.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jlTiempoContratado.setBounds(350, 130, 180, 16);

    this.tiempoContratado = new JLabel(String.valueOf(this.contrato.getTiempoContratado()) + " Meses.");
    this.tiempoContratado.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.tiempoContratado.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.tiempoContratado.setBounds(510, 130, 120, 16);

    this.jlTipoContrato = new JLabel("- Tipo Contrato:");
    this.jlTipoContrato.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlTipoContrato.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jlTipoContrato.setBounds(20, 160, 180, 16);

    this.tipoContrato = new JLabel(this.contrato.getTipoContrato());
    this.tipoContrato.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.tipoContrato.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.tipoContrato.setBounds(200, 160, 180, 16);

    this.jlSalario = new JLabel("- Salario Mensual:");
    this.jlSalario.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlSalario.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jlSalario.setBounds(20, 190, 180, 16);

    this.salario = new JLabel(String.valueOf(this.contrato.getSalario()));
    this.salario.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.salario.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.salario.setBounds(200, 190, 250, 16);

    this.jlEstado = new JLabel("- Estado Contrato:");
    this.jlEstado.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlEstado.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jlEstado.setBounds(350, 190, 180, 20);

    this.jtEstado = new JTextField();
    this.jtEstado.setEditable(false);
    this.jtEstado.setBounds(500, 190, 77, 25);

    this.jtObservaciones = new JTextArea(this.contrato.getObservacion());
    this.jtObservaciones.setLineWrap(true);
    this.jtObservaciones.setEditable(false);
    this.jtObservaciones.setBounds(20, 220, 560, 170);

    this.jbAceptar = new JButton("Aceptar");
    this.jbAceptar.setIcon(new ImageIcon(getClass().getResource(this.iconsPath + "NiconOK.png")));
    this.jbAceptar.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jbAceptar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          VisorContrato.this.dispose();
        } catch (Throwable ex) {
          Logger.getLogger(VisorContrato.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
    this.jbAceptar.setBounds(600, 360, 130, 30);

    this.panelVisor.add(this.jlNombres);
    this.panelVisor.add(this.jlNumeroContrato);
    this.panelVisor.add(this.numeroContrato);
    this.panelVisor.add(this.jlFechaContratacion);
    this.panelVisor.add(this.fechaContratacion);
    this.panelVisor.add(this.jlInicioFunciones);
    this.panelVisor.add(this.inicioFunciones);
    this.panelVisor.add(this.jlCargo);
    this.panelVisor.add(this.cargo);
    this.panelVisor.add(this.jlTiempoContratado);
    this.panelVisor.add(this.tiempoContratado);
    this.panelVisor.add(this.jlTipoContrato);
    this.panelVisor.add(this.tipoContrato);
    this.panelVisor.add(this.jlSalario);
    this.panelVisor.add(this.salario);
    this.panelVisor.add(this.jtObservaciones);
    this.panelVisor.add(this.jbAceptar);
    this.panelVisor.add(this.jlEstado);
    this.panelVisor.add(this.jtEstado);

    getContentPane().add(this.panelVisor);
  }

  private void verificarEstado() {
    if (this.contrato.getEstadoContrato())
      this.jtEstado.setBackground(GlobalConfigSystem.getColorActiveStatus());
    else
      this.jtEstado.setBackground(GlobalConfigSystem.getColorInactiveStatus());
  }
}
