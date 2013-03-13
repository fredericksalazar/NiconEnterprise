/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Proveedores;

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
import nicon.enterprise.libCore.api.dao.ProveedorDAO;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.obj.Proveedor;

public class EditorProveedor extends JDialog
{
  private JPanel panelEditor;
  private JLabel jlTitulo;
  private JLabel jlNit;
  private JLabel nit;
  private JLabel jlRazonSocial;
  private JLabel razonSocial;
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
  private Proveedor proveedor;
  private ProveedorDAO proveedorDAO;

  public EditorProveedor(Proveedor proveedor)
  {
    setSize(650, 450);
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setLocationRelativeTo(null);
    setResizable(false);
    setModal(true);
    this.proveedor = proveedor;
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

    this.jlNit = new JLabel("- Nit: ");
    this.jlNit.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlNit.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNit.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNit.setBounds(60, 100, 200, 20);

    this.nit = new JLabel(this.proveedor.getNit());
    this.nit.setForeground(Color.white);
    this.nit.setFont(GlobalConfigSystem.getFontAplicationText());
    this.nit.setBounds(200, 100, 600, 20);

    this.jlRazonSocial = new JLabel("- Razón Social:");
    this.jlRazonSocial.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlRazonSocial.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlRazonSocial.setBounds(60, 130, 200, 20);

    this.razonSocial = new JLabel(this.proveedor.getRazonSocial());
    this.razonSocial.setForeground(Color.white);
    this.razonSocial.setFont(GlobalConfigSystem.getFontAplicationText());
    this.razonSocial.setBounds(200, 130, 600, 20);

    this.jlDireccion = new JLabel("- Dirección:");
    this.jlDireccion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlDireccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlDireccion.setBounds(60, 160, 200, 20);

    this.direccion = new JLabel(this.proveedor.getCiudad() + " / " + this.proveedor.getDireccion());
    this.direccion.setForeground(Color.white);
    this.direccion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.direccion.setBounds(200, 160, 600, 20);

    this.jlTelefonos = new JLabel("- Telefonos:");
    this.jlTelefonos.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlTelefonos.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlTelefonos.setBounds(60, 190, 200, 20);

    this.telefonos = new JLabel(this.proveedor.getTelefonoFijo() + " / " + this.proveedor.getTelefonoMovil());
    this.telefonos.setForeground(Color.white);
    this.telefonos.setFont(GlobalConfigSystem.getFontAplicationText());
    this.telefonos.setBounds(200, 190, 600, 20);

    this.jlOpciones = new JLabel("- Seleccione un campo a actualizar:");
    this.jlOpciones.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlOpciones.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlOpciones.setBounds(60, 220, 600, 20);

    String[] vectorOpciones = { "Direccion", "ciudad", "Telefono_fijo", "Telefono_movil", "fax", "email", "Web_Page", "Banco", "numeroCuenta" };

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
      public void actionPerformed(ActionEvent ae) {
        EditorProveedor.this.actualizar();
      }
    });
    this.jbCancelar = new JButton("Cancelar");
    this.jbCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
    this.jbCancelar.setBounds(350, 400, 120, 30);
    this.jbCancelar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent ae) {
        EditorProveedor.this.dispose();
      }
    });
    this.panelEditor.add(this.jlTitulo);
    this.panelEditor.add(this.jlNit);
    this.panelEditor.add(this.nit);
    this.panelEditor.add(this.jlRazonSocial);
    this.panelEditor.add(this.razonSocial);
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
      this.proveedorDAO = new ProveedorDAO();
      boolean actualizarDatos = this.proveedorDAO.actualizarProveedor(this.proveedor.getNit(), campo, dato);
      if (actualizarDatos) {
        JOptionPane.showMessageDialog(this.rootPane, "Los datos del empleado han sido actualizados exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
        dispose();
      }
    }
  }
}