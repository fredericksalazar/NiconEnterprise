/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.TipoActividadDAO;
import nicon.enterprise.libCore.obj.TipoActividad;


public class CrearTipoActividad extends JDialog{
    
  private TipoActividad tipoActividad;
  private TipoActividadDAO tipoActividadDAO;
  private JPanel panel;
  private JLabel jltitulo;
  private JLabel jlNombre;
  private JLabel jlDescripcion;
  private JTextField jtTitulo;
  private JTextArea jtDescripcion;
  private JButton jbAceptar;
  private JButton jbCancelar;

  public CrearTipoActividad() {      
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setSize(450, 340);
    setModal(true);
    setResizable(false);
    setLocationRelativeTo(null);
    crear_Interfaz();
  }

  private void crear_Interfaz(){
      
    this.panel = new JPanel();
    this.panel.setLayout(null);
    this.panel.setBackground(GlobalConfigSystem.getBackgroundAplication());

    this.jltitulo = new JLabel("Tipo Actividad");
    this.jltitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jltitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
    this.jltitulo.setBounds(20, 20, 300, 45);

    this.jlNombre = new JLabel("- Ingrese un Nombre:");
    this.jlNombre.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.jlNombre.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNombre.setBounds(20, 20, 180, 20);

    this.jtTitulo = new JTextField();
    this.jtTitulo.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtTitulo.setBounds(50, 60, 350, 30);

    this.jlDescripcion = new JLabel("- Ingrese la Descripción:");
    this.jlDescripcion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlDescripcion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlDescripcion.setBounds(20, 110, 200, 20);

    this.jtDescripcion = new JTextArea();
    this.jtDescripcion.setToolTipText("Ingrese la descripcion del tipo de actividad");
    this.jtDescripcion.setBounds(50, 140, 350, 120);

    this.jbAceptar = new JButton("Guardar");
    this.jbAceptar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
    this.jbAceptar.setBounds(270, 268, 130, 30);
    this.jbAceptar.addActionListener(new ActionListener()    {
      @Override
      public void actionPerformed(ActionEvent ae) {
        CrearTipoActividad.this.guardarTipoActividad();
      }
    });
    this.jbCancelar = new JButton("Cancelar");
    this.jbCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
    this.jbCancelar.setBounds(120, 268, 130, 30);
    this.jbCancelar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ae) {
        CrearTipoActividad.this.dispose();
      }
    });
    this.panel.add(this.jlNombre);
    this.panel.add(this.jtTitulo);
    this.panel.add(this.jlDescripcion);
    this.panel.add(this.jtDescripcion);
    this.panel.add(this.jbAceptar);
    this.panel.add(this.jbCancelar);
    getContentPane().add(this.panel);
  }

  private void guardarTipoActividad() {
    String titulo = this.jtTitulo.getText();
    String descripcion = this.jtDescripcion.getText();

    if ((titulo.equals("")) || (descripcion.equals(""))) {
      JOptionPane.showMessageDialog(this.rootPane, "Hay datos sin Ingresar, verifique e intente de nuevo.", GlobalConfigSystem.getAplicationTitle(), 0);
    } else {
      this.tipoActividad = new TipoActividad(titulo, descripcion);
      this.tipoActividadDAO = new TipoActividadDAO(this.tipoActividad);
      boolean response = this.tipoActividadDAO.crearTipoActividad();
      if (response)
        JOptionPane.showMessageDialog(this.rootPane, "El nuevo tipo de actividad ha sido creado.", GlobalConfigSystem.getAplicationTitle(), 1);
      else {
        JOptionPane.showMessageDialog(this.rootPane, "El nuevo tipo de actividad no pudo ser creado.", GlobalConfigSystem.getAplicationTitle(), 0);
      }
      this.tipoActividad = null;
      this.tipoActividadDAO = null;
      dispose();
    }
  }
}
