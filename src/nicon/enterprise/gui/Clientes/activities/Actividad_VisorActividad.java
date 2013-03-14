/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */

package nicon.enterprise.gui.Clientes.activities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import nicon.enterprise.libCore.api.dao.ActividadDAO;
import nicon.enterprise.libCore.api.obj.Actividad;

import nicon.enterprise.libCore.api.util.GlobalConfigSystem;

/**
 * Clase que representa el Dialogo para el visor de las actividades del sistema, hereda de <b>JDialog</b> 
 * e incopora todos los elemntos gráficos de  la vista, hace uso de la libreria LibCore.ActividadDAO para
 * obtener datos de la actividad y mostrarlos en pantalla.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class Actividad_VisorActividad extends JDialog {
    
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

  /**
   * Este constructor recibe como parametro un objeto de tipo Actividad que será mostrada en la vista
   * @param actividad 
   */
  public Actividad_VisorActividad(Actividad actividad) {
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setSize(560,560);
    setModal(true);
    setLocationRelativeTo(null);
    setResizable(false);
    this.actividad = actividad;
    crearInterfaz();
  }

  private void crearInterfaz() {
      
    panel = new JPanel();
    panel.setBackground(GlobalConfigSystem.getBackgroundAplication());
    panel.setLayout(null);

    jlTitulo = new JLabel(actividad.getTituloActividad());
    jlTitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    jlTitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
    jlTitulo.setBounds(20, 20, 600, 40);

    jlIdActividad = new JLabel("ID Actividad:");
    jlIdActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    jlIdActividad.setFont(GlobalConfigSystem.getFontAplicationText());
    jlIdActividad.setBounds(40, 90, 200, 20);

    idActividad = new JLabel(String.valueOf(actividad.getIdActividad()));
    idActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    idActividad.setFont(GlobalConfigSystem.getFontAplicationText());
    idActividad.setBounds(220, 90, 220, 20);

    jlTipoActividad = new JLabel("Tipo Actividad:");
    jlTipoActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    jlTipoActividad.setFont(GlobalConfigSystem.getFontAplicationText());
    jlTipoActividad.setBounds(40, 130, 200, 20);

    tipoActividad = new JLabel(actividad.getNombreActividad());
    tipoActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    tipoActividad.setFont(GlobalConfigSystem.getFontAplicationText());
    tipoActividad.setBounds(220, 130, 400, 20);

    jlidentificacion = new JLabel("Id Cliente:");
    jlidentificacion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    jlidentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
    jlidentificacion.setBounds(40, 170, 220, 20);

    identificacion = new JLabel(actividad.getIdCliente());
    identificacion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    identificacion.setFont(GlobalConfigSystem.getFontAplicationText());
    identificacion.setBounds(220, 170, 220, 20);

    jlCliente = new JLabel("Nombre Cliente:");
    jlCliente.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    jlCliente.setFont(GlobalConfigSystem.getFontAplicationText());
    jlCliente.setBounds(40, 210, 200, 20);

    cliente = new JLabel(actividad.getNombreCliente() + " " + this.actividad.getApellidosCliente());
    cliente.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    cliente.setFont(GlobalConfigSystem.getFontAplicationText());
    cliente.setBounds(220, 210, 220, 20);

    jldireccion = new JLabel("Dirección Cliente:");
    jldireccion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    jldireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    jldireccion.setBounds(40, 250, 200, 20);

    direccion = new JLabel(actividad.getDireccionCliente());
    direccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    direccion.setFont(GlobalConfigSystem.getFontAplicationText());
    direccion.setBounds(220, 250, 220, 20);

    jlFechaAsignacion = new JLabel("Fecha Asignación: ");
    jlFechaAsignacion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    jlFechaAsignacion.setFont(GlobalConfigSystem.getFontAplicationText());
    jlFechaAsignacion.setBounds(40, 290, 220, 20);

    fechaAsignacion = new JLabel(actividad.getFechaAsignacion());
    fechaAsignacion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    fechaAsignacion.setFont(GlobalConfigSystem.getFontAplicationText());
    fechaAsignacion.setBounds(220, 290, 220, 20);

    jlDescripcion = new JLabel("Descripción:");
    jlDescripcion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    jlDescripcion.setFont(GlobalConfigSystem.getFontAplicationText());
    jlDescripcion.setBounds(40, 330, 220, 20);

    descripcion = new JTextArea(actividad.getDescripcionActividad());
    descripcion.setLineWrap(true);
    descripcion.setEditable(false);
    descripcion.setFont(GlobalConfigSystem.getFontAplicationText());
    descripcion.setBounds(40, 350, 490, 130);

    aceptar = new JButton("Salir");
    aceptar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
    aceptar.setBounds(380, 490, 150, 35);
    aceptar.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent ae) {
        dispose();
      }
    });
    
    terminarActividad = new JButton("Terminar actividad");
    terminarActividad.setToolTipText("Permite ajustar una actividad en terminada");
    terminarActividad.setBounds(210, 490, 150, 35);
    terminarActividad.addActionListener(new ActionListener()    {
      @Override
      public void actionPerformed(ActionEvent ae) {
          try {
              actividadDAO=new ActividadDAO();        
                  if (actividadDAO.cambiarEstado(actividad.getIdActividad(), true))
                          JOptionPane.showMessageDialog(rootPane, "La actividad se ha cambiado a terminada.", GlobalConfigSystem.getAplicationTitle(),JOptionPane.INFORMATION_MESSAGE,new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconPositive.png")));
          } catch (SQLException ex) {
              Logger.getLogger(Actividad_VisorActividad.class.getName()).log(Level.SEVERE, null, ex);
          }
            }
    });
    
    imprimir = new JButton("Imprimir");
    imprimir.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconPrinter.png")));
    imprimir.setBounds(1, 1, 1, 1);

    panel.add(jlTitulo);
    panel.add(jlIdActividad);
    panel.add(idActividad);
    panel.add(jlTipoActividad);
    panel.add(tipoActividad);
    panel.add(jlidentificacion);
    panel.add(identificacion);
    panel.add(jlCliente);
    panel.add(cliente);
    panel.add(jldireccion);
    panel.add(direccion);
    panel.add(jlFechaAsignacion);
    panel.add(fechaAsignacion);
    panel.add(jlDescripcion);
    panel.add(descripcion);
    panel.add(aceptar);
    panel.add(terminarActividad);
    getContentPane().add(panel);
  }
}
