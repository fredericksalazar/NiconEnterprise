/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Clientes.activities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.TipoActividadDAO;
import nicon.enterprise.libCore.obj.TipoActividad;


public class AdministradorTipoActividad extends JDialog {
    
  private JPanel panelAdmin;
  private JLabel jlid;
  private JLabel jlNombre;
  private JLabel jlDescripcion;
  private JTextField jtId;
  private JTextField jtNombre;
  private JTextArea jtDescripcion;
  private DefaultTableModel modelo;
  private JTable tabla;
  private JScrollPane scroll;
  private JScrollPane scrollDescripcion;
  private ArrayList listaTipos;
  private TipoActividad tipoActividad;
  private TipoActividadDAO tipoActividadDAO;
  private JButton aceptar;

  public AdministradorTipoActividad(){
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setSize(590,380);
    setModal(true);
    setLocationRelativeTo(null);
    setResizable(false);
    crearInterfaz();
    cargarLista();
  }

  private void crearInterfaz() {
    this.listaTipos = new ArrayList();

    this.panelAdmin = new JPanel();
    this.panelAdmin.setLayout(null);
    this.panelAdmin.setBackground(GlobalConfigSystem.getBackgroundAplication());

    this.modelo = new DefaultTableModel();
    this.modelo.addColumn("Tipo Actividad");

    this.tabla = new JTable(this.modelo);
    this.tabla.setFont(GlobalConfigSystem.getFontAplicationText());
    this.tabla.setRowHeight(30);
    this.tabla.setTableHeader(null);
    this.tabla.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mousePressed(MouseEvent me) {
        AdministradorTipoActividad.this.mostrarSeleccionado();
      }
    });
    this.scroll = new JScrollPane(this.tabla);
    this.scroll.setBounds(0, 0, 230, 400);

    this.jlid = new JLabel("ID Tipo Actividad:");
    this.jlid.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlid.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlid.setBounds(250, 20, 220, 20);

    this.jtId = new JTextField();
    this.jtId.setEditable(false);
    this.jtId.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtId.setBounds(250, 50, 300, 30);

    this.jlNombre = new JLabel("Nombre Actividad:");
    this.jlNombre.setForeground(GlobalConfigSystem.getForegroundAplicationText());
    this.jlNombre.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNombre.setBounds(250, 90, 220, 20);

    this.jtNombre = new JTextField();
    this.jtNombre.setEditable(false);
    this.jtNombre.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtNombre.setBounds(250, 120, 300, 30);

    this.jlDescripcion = new JLabel("Descripción:");
    this.jlDescripcion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlDescripcion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlDescripcion.setBounds(250, 160, 220, 20);

    this.jtDescripcion = new JTextArea();
    this.jtDescripcion.setLineWrap(true);
    this.jtDescripcion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtDescripcion.setEditable(false);

    this.scrollDescripcion = new JScrollPane(this.jtDescripcion);
    this.scrollDescripcion.setBounds(250, 190, 300, 100);

    this.aceptar = new JButton("Aceptar");
    this.aceptar.setBounds(430, 310, 120, 30);
    this.aceptar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
    this.aceptar.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ae) {
        AdministradorTipoActividad.this.dispose();
      }
    });
    this.panelAdmin.add(this.scroll);
    this.panelAdmin.add(this.jlid);
    this.panelAdmin.add(this.jtId);
    this.panelAdmin.add(this.jlNombre);
    this.panelAdmin.add(this.jtNombre);
    this.panelAdmin.add(this.jlDescripcion);
    this.panelAdmin.add(this.scrollDescripcion);
    this.panelAdmin.add(this.aceptar);

    getContentPane().add(this.panelAdmin);
  }

  private void cargarLista() {
    String[] vector = new String[1];
    this.tipoActividadDAO = new TipoActividadDAO();
    this.listaTipos = this.tipoActividadDAO.listarTipoActividad();

    for (int i = 0; i < this.listaTipos.size(); i++) {
      this.tipoActividad = ((TipoActividad)this.listaTipos.get(i));
      vector[0] = this.tipoActividad.getNombreActividad();
      this.modelo.addRow(vector);
    }
  }

  private void mostrarSeleccionado() {
    int index = this.tabla.getSelectedRow();
    this.tipoActividad = ((TipoActividad)this.listaTipos.get(index));
    this.jtId.setText(String.valueOf(this.tipoActividad.getCodigoActividad()));
    this.jtNombre.setText(this.tipoActividad.getNombreActividad());
    this.jtDescripcion.setText(this.tipoActividad.getDescripcion());
  }
}