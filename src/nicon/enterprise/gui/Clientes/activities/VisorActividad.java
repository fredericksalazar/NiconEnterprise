/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Clientes.activities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.ActividadDAO;
import nicon.enterprise.libCore.obj.Actividad;


public class VisorActividad extends JDialog {
    
  private Actividad actividad;
  private ActividadDAO actividadDAO;
  private JPanel panel;
  private JLabel jlIdActividad;
  private JLabel jlTitulo;
  private JLabel idActividad;
  private JLabel jlTipoActividad;
  private JLabel tipoActividad;
  private JLabel jlCliente;
  private JLabel cliente;
  private JLabel jlFechaAsignacion;
  private JLabel fechaAsignacion;
  private JLabel jlDescripcion;
  private JTextArea descripcion;
  private JLabel jlidentificacion;
  private JLabel identificacion;
  private JLabel jldireccion;
  private JLabel direccion;
  private JButton aceptar;
  private JButton terminarActividad;
  private JButton imprimir;

  public VisorActividad(Actividad actividad)
  {
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setSize(500, 540);
    setModal(true);
    setLocationRelativeTo(null);
    setResizable(false);
    this.actividad = actividad;
    crearInterfaz();
  }

  private void crearInterfaz() {
    this.panel = new JPanel();
    this.panel.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.panel.setLayout(null);

    this.jlTitulo = new JLabel(this.actividad.getTituloActividad());
    this.jlTitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlTitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
    this.jlTitulo.setBounds(20, 20, 600, 40);

    this.jlIdActividad = new JLabel("ID Actividad:");
    this.jlIdActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.jlIdActividad.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlIdActividad.setBounds(40, 90, 200, 20);

    this.idActividad = new JLabel(String.valueOf(this.actividad.getIdActividad()));
    this.idActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.idActividad.setFont(GlobalConfigSystem.getFontAplicationText());
    this.idActividad.setBounds(220, 90, 220, 20);

    this.jlTipoActividad = new JLabel("Tipo Actividad:");
    this.jlTipoActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.jlTipoActividad.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlTipoActividad.setBounds(40, 130, 200, 20);

    this.tipoActividad = new JLabel(this.actividad.getNombreActividad());
    this.tipoActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.tipoActividad.setFont(GlobalConfigSystem.getFontAplicationText());
    this.tipoActividad.setBounds(220, 130, 400, 20);

    this.jlidentificacion = new JLabel("Id Cliente:");
    this.jlidentificacion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.jlidentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlidentificacion.setBounds(40, 170, 220, 20);

    this.identificacion = new JLabel(this.actividad.getIdCliente());
    this.identificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.identificacion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.identificacion.setBounds(220, 170, 220, 20);

    this.jlCliente = new JLabel("Nombre Cliente:");
    this.jlCliente.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlCliente.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlCliente.setBounds(40, 210, 200, 20);

    this.cliente = new JLabel(this.actividad.getNombreCliente() + " " + this.actividad.getApellidosCliente());
    this.cliente.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.cliente.setFont(GlobalConfigSystem.getFontAplicationText());
    this.cliente.setBounds(220, 210, 220, 20);

    this.jldireccion = new JLabel("Dirección Cliente:");
    this.jldireccion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.jldireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jldireccion.setBounds(40, 250, 200, 20);

    this.direccion = new JLabel(this.actividad.getDireccionCliente());
    this.direccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.direccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.direccion.setBounds(220, 250, 220, 20);

    this.jlFechaAsignacion = new JLabel("Fecha Asignación: ");
    this.jlFechaAsignacion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.jlFechaAsignacion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlFechaAsignacion.setBounds(40, 290, 220, 20);

    this.fechaAsignacion = new JLabel(this.actividad.getFechaAsignacion());
    this.fechaAsignacion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.fechaAsignacion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.fechaAsignacion.setBounds(220, 290, 220, 20);

    this.jlDescripcion = new JLabel("Descripción:");
    this.jlDescripcion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.jlDescripcion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlDescripcion.setBounds(40, 330, 220, 20);

    this.descripcion = new JTextArea(this.actividad.getDescripcionActividad());
    this.descripcion.setLineWrap(true);
    this.descripcion.setEditable(false);
    this.descripcion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.descripcion.setBounds(40, 350, 440, 130);

    this.aceptar = new JButton("Salir");
    this.aceptar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
    this.aceptar.setBounds(370, 490, 120, 35);
    this.aceptar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ae) {
        VisorActividad.this.dispose();
      }
    });
    this.terminarActividad = new JButton("Terminar actividad");
    this.terminarActividad.setToolTipText("Permite ajustar una actividad en terminada");
    this.terminarActividad.setBounds(200, 490, 150, 35);
    this.terminarActividad.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ae) {
        actividadDAO=new ActividadDAO();
        boolean cambiarEstado =actividadDAO.cambiarEstado(VisorActividad.this.actividad.getIdActividad(), true);
        if (cambiarEstado)
          JOptionPane.showMessageDialog(VisorActividad.this.rootPane, "La actividad se ha cambiado a terminada.", GlobalConfigSystem.getAplicationTitle(), 1);
      }
    });
    this.imprimir = new JButton("Imprimir");
    this.imprimir.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPrinter.png")));
    this.imprimir.setBounds(1, 1, 1, 1);

    this.panel.add(this.jlTitulo);
    this.panel.add(this.jlIdActividad);
    this.panel.add(this.idActividad);
    this.panel.add(this.jlTipoActividad);
    this.panel.add(this.tipoActividad);
    this.panel.add(this.jlidentificacion);
    this.panel.add(this.identificacion);
    this.panel.add(this.jlCliente);
    this.panel.add(this.cliente);
    this.panel.add(this.jldireccion);
    this.panel.add(this.direccion);
    this.panel.add(this.jlFechaAsignacion);
    this.panel.add(this.fechaAsignacion);
    this.panel.add(this.jlDescripcion);
    this.panel.add(this.descripcion);
    this.panel.add(this.aceptar);
    this.panel.add(this.terminarActividad);
    getContentPane().add(this.panel);
  }
}
