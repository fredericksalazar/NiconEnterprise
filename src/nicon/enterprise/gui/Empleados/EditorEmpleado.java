/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Empleados;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.EmpleadoDAO;
import nicon.enterprise.libCore.api.obj.Empleado;

public class EditorEmpleado extends JDialog
{
  private JPanel panelEditor;
  private JLabel jlTitulo;
  private JLabel jlIdentificacion;
  private JLabel identificacion;
  private JLabel jlNombres;
  private JLabel nombres;
  private Empleado empleado;
  private JLabel jlDireccion;
  private JLabel direccion;
  private JLabel jlTelefonos;
  private JLabel telefonos;
  private JLabel jlOpciones;
  private JLabel jlDato;
  private JComboBox jcOpciones;
  private JTextField jtDatos;
  private JButton jbAceptar;
  private JButton jbCancelar;
  private EmpleadoDAO actualizar;

  public EditorEmpleado(Empleado empleado){
    setSize(650, 450);
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setLocationRelativeTo(null);
    setResizable(false);
    setModal(true);
    this.empleado = empleado;
    crearComponentes();
  }

  private void crearComponentes() {
    this.panelEditor = new JPanel();
    this.panelEditor.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.panelEditor.setLayout(null);

    this.jlTitulo = new JLabel("Actualización de Datos");
    this.jlTitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
    this.jlTitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlTitulo.setBounds(10, 10, 600, 30);

    this.jlIdentificacion = new JLabel("- Identificación: ");
    this.jlIdentificacion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlIdentificacion.setBounds(60, 100, 200, 20);

    this.identificacion = new JLabel(this.empleado.getIdentificacion());
    this.identificacion.setForeground(Color.white);
    this.identificacion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.identificacion.setBounds(200, 100, 600, 20);

    this.jlNombres = new JLabel("- Nombres:");
    this.jlNombres.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlNombres.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNombres.setBounds(60, 130, 200, 20);

    this.nombres = new JLabel(this.empleado.getNombres() + " " + this.empleado.getApellidos());
    this.nombres.setForeground(Color.white);
    this.nombres.setFont(GlobalConfigSystem.getFontAplicationText());
    this.nombres.setBounds(200, 130, 600, 20);

    this.jlDireccion = new JLabel("- Viven en:");
    this.jlDireccion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlDireccion.setBounds(60, 160, 200, 20);

    this.direccion = new JLabel(this.empleado.getCiudad() + " / " + this.empleado.getDireccion());
    this.direccion.setForeground(Color.white);
    this.direccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.direccion.setBounds(200, 160, 600, 20);

    this.jlTelefonos = new JLabel("- Telefonos:");
    this.jlTelefonos.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlTelefonos.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlTelefonos.setBounds(60, 190, 200, 20);

    this.telefonos = new JLabel(this.empleado.getTelefonoFijo() + " / " + this.empleado.getTelefonoMovil());
    this.telefonos.setForeground(Color.white);
    this.telefonos.setFont(GlobalConfigSystem.getFontAplicationText());
    this.telefonos.setBounds(200, 190, 600, 20);

    this.jlOpciones = new JLabel("- Seleccione un campo a actualizar:");
    this.jlOpciones.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlOpciones.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlOpciones.setBounds(60, 220, 600, 20);

    String[] vectorOpciones = { "identificacion", "estado_civil", "direccion", "barrio", "ciudad", "telefono_fijo", "telefono_movil", "email" };

    this.jcOpciones = new JComboBox(vectorOpciones);
    this.jcOpciones.setBounds(200, 250, 400, 30);

    this.jlDato = new JLabel("- Ingrese el nuevo valor:");
    this.jlDato.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlDato.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlDato.setBounds(60, 290, 200, 20);

    this.jtDatos = new JTextField();
    this.jtDatos.setBounds(200, 320, 400, 30);

    this.jbAceptar = new JButton("Actualizar");
    this.jbAceptar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
    this.jbAceptar.setBounds(480, 400, 120, 30);
    this.jbAceptar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ae) {
        EditorEmpleado.this.actualizar();
      }
    });
    this.jbCancelar = new JButton("Cancelar");
    this.jbCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
    this.jbCancelar.setBounds(350, 400, 120, 30);
    this.jbCancelar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ae) {
        EditorEmpleado.this.dispose();
      }
    });
    this.panelEditor.add(this.jlTitulo);
    this.panelEditor.add(this.jlIdentificacion);
    this.panelEditor.add(this.identificacion);
    this.panelEditor.add(this.jlNombres);
    this.panelEditor.add(this.nombres);
    this.panelEditor.add(this.jlDireccion);
    this.panelEditor.add(this.direccion);
    this.panelEditor.add(this.jlTelefonos);
    this.panelEditor.add(this.telefonos);
    this.panelEditor.add(this.jlOpciones);
    this.panelEditor.add(this.jcOpciones);
    this.panelEditor.add(this.jlDato);
    this.panelEditor.add(this.jtDatos);
    this.panelEditor.add(this.jbAceptar);
    this.panelEditor.add(this.jbCancelar);

    getContentPane().add(this.panelEditor);
  }

  private void actualizar() {
    String campo = (String)this.jcOpciones.getSelectedItem();
    String dato = this.jtDatos.getText();
    if (dato.equals("")) {
      JOptionPane.showMessageDialog(this.rootPane, "No ha Ingresado un dato a actualizar.", GlobalConfigSystem.getAplicationTitle(), 0);
    } else {
      this.actualizar = new EmpleadoDAO();
      boolean actualizarDatos = this.actualizar.actualizarDatos(this.empleado.getIdentificacion(), campo, dato);
      if (actualizarDatos) {
        JOptionPane.showMessageDialog(this.rootPane, "Los datos del empleado han sido actualizados exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
        ModuloEmpleados.recargarListaEmpleados();
        dispose();
        this.empleado = null;
        this.actualizar = null;
      }
    }
  }
}
