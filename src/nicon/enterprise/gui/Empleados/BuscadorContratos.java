/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Empleados;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.ContratoEmpleadoDAO;
import nicon.enterprise.libCore.dao.EmpleadoDAO;
import nicon.enterprise.libCore.obj.ContratoEmpleado;
import nicon.enterprise.libCore.obj.Empleado;

public class BuscadorContratos extends JDialog
  implements ActionListener, MouseListener
{
  private JPanel panelAdmin;
  private JLabel jlIdentificacion;
  private JLabel jlNombres;
  private JLabel jlCargo;
  private JLabel jlCantidad;
  private JTextField jtIdentificacion;
  private JTable jtContratos;
  private DefaultTableModel modelo;
  private JScrollPane jScroll;
  private JButton jbBuscar;
  private JButton jbVisor;
  private String iconsPath;
  private ArrayList contratos;
  private ContratoEmpleadoDAO contratoDAO;
  private ContratoEmpleado contrato;
  private int index;
  private VisorContrato visor;
  private EmpleadoDAO empleadoDAO;
  private Empleado empleado;
  private JLabel jlNumContratos;

  public BuscadorContratos()
  {
    setTitle(GlobalConfigSystem.getAplicationTitle());
    setSize(800, 600);
    setModal(true);
    setLocationRelativeTo(null);
    this.iconsPath = GlobalConfigSystem.getIconsPath();
    this.contratoDAO = new ContratoEmpleadoDAO();
    this.empleadoDAO = new EmpleadoDAO();
    this.contratos = new ArrayList();
    initComponents();
  }

  private void initComponents() {
    this.panelAdmin = new JPanel();
    this.panelAdmin.setBackground(GlobalConfigSystem.getBackgroundAplication());
    this.panelAdmin.setLayout(null);

    this.jlIdentificacion = new JLabel("- Ingrese la Identificación:");
    this.jlIdentificacion.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlIdentificacion.setFont(GlobalConfigSystem.getFontAplicationTextBold());
    this.jlIdentificacion.setBounds(20, 50, 200, 16);

    this.jtIdentificacion = new JTextField();
    this.jtIdentificacion.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jtIdentificacion.addMouseListener(this);
    this.jtIdentificacion.setToolTipText("Ingresar el número de identificación del empleado");
    this.jtIdentificacion.setBounds(230, 42, 300, 30);

    this.jbBuscar = new JButton();
    this.jbBuscar.setIcon(new ImageIcon(getClass().getResource(this.iconsPath + "NiconSearch.png")));
    this.jbBuscar.setBounds(540, 40, 35, 35);
    this.jbBuscar.addActionListener(this);

    this.jbVisor = new JButton();
    this.jbVisor.setToolTipText("Ver detalles del contrato Seleccionado");
    this.jbVisor.addActionListener(this);
    this.jbVisor.setIcon(new ImageIcon(getClass().getResource(this.iconsPath + "NiconView.png")));
    this.jbVisor.setBounds(620, 40, 35, 35);

    this.jlNombres = new JLabel();
    this.jlNombres.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
    this.jlNombres.setFont(GlobalConfigSystem.getFontAplicationTitle());
    this.jlNombres.setBounds(20, 90, 600, 40);

    this.jlCargo = new JLabel();
    this.jlCargo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlCargo.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlCargo.setBounds(20, 135, 300, 16);

    this.jlNumContratos = new JLabel();
    this.jlNumContratos.setForeground(GlobalConfigSystem.getForegrounAplicationText());
    this.jlNumContratos.setFont(GlobalConfigSystem.getFontAplicationText());
    this.jlNumContratos.setBounds(20, 155, 300, 16);

    this.modelo = new DefaultTableModel();
    this.modelo.addColumn("Código");
    this.modelo.addColumn("Fecha Contratación");
    this.modelo.addColumn("Cargo");
    this.modelo.addColumn("Salario");
    this.modelo.addColumn("Tiempo Contratado");
    this.modelo.addColumn("Estado Contrato");

    this.jtContratos = new JTable(this.modelo);
    this.jtContratos.setGridColor(GlobalConfigSystem.getColorInactiveStatus());
    this.jtContratos.addMouseListener(this);

    this.jScroll = new JScrollPane(this.jtContratos);
    this.jScroll.setBounds(20, 260, 750, 300);

    this.panelAdmin.add(this.jlIdentificacion);
    this.panelAdmin.add(this.jtIdentificacion);
    this.panelAdmin.add(this.jbBuscar);
    this.panelAdmin.add(this.jbVisor);
    this.panelAdmin.add(this.jlNombres);
    this.panelAdmin.add(this.jlCargo);
    this.panelAdmin.add(this.jScroll);
    this.panelAdmin.add(this.jlNumContratos);

    getContentPane().add(this.panelAdmin);
  }

  private void buscarInformacion()
  {
    String id = this.jtIdentificacion.getText();
    if (id.equals("")) {
      JOptionPane.showMessageDialog(null, "No ha Ingresado un número de identificacion correcto", GlobalConfigSystem.getAplicationTitle(), 0);
    } else {
      this.contratos = this.contratoDAO.listarContratosEmpleado(id);
      this.empleado = this.empleadoDAO.buscarEmpleadoPorID(id);
      this.jlNombres.setText(this.empleado.getNombres() + " " + this.empleado.getApellidos());
      this.jlCargo.setText(this.contratoDAO.obtenerCargoContratoActivo(id));
      if (!this.contratos.isEmpty()) {
        String[] data = new String[6];

        for (int i = 0; i < this.contratos.size(); i++) {
          this.contrato = ((ContratoEmpleado)this.contratos.get(i));
          data[0] = String.valueOf(this.contrato.getIdContrato());
          data[1] = this.contrato.getFechaContratacion();
          data[2] = this.contrato.getCargo();
          data[3] = String.valueOf(this.contrato.getSalario());
          data[4] = String.valueOf(this.contrato.getTiempoContratado());
          data[5] = String.valueOf(this.contrato.getEstadoContrato());
          this.modelo.addRow(data);
        }
        this.jlNumContratos.setText(String.valueOf("Total Contratos: " + this.modelo.getRowCount()));
      } else {
        JOptionPane.showMessageDialog(null, "No se encontraron contratos firmados con el empleado.", GlobalConfigSystem.getAplicationTitle(), 1);
      }
    }
  }

  private void verDetallesContrato()
  {
    this.index = this.jtContratos.getSelectedRow();
    if (this.index < 0) {
      JOptionPane.showMessageDialog(null, "No ha seleccionado un contrato para mostrar", GlobalConfigSystem.getAplicationTitle(), 0);
    } else {
      this.contrato = ((ContratoEmpleado)this.contratos.get(this.index));
      this.visor = new VisorContrato(this.contrato, this.empleado.getNombres() + " " + this.empleado.getApellidos());
      this.visor.setVisible(true);
    }
  }

  private void limpiarTabla()
  {
    this.modelo.getDataVector().removeAllElements();
    repaint();
  }

  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.jbBuscar) {
      buscarInformacion();
    }

    if (e.getSource() == this.jbVisor)
      verDetallesContrato();
  }

  public void mouseClicked(MouseEvent e)
  {
  }

  public void mousePressed(MouseEvent e)
  {
    if (e.getSource() == this.jtIdentificacion) {
      this.jtIdentificacion.setText("");
      limpiarTabla();
    }
  }

  public void mouseReleased(MouseEvent e)
  {
  }

  public void mouseEntered(MouseEvent e)
  {
  }

  public void mouseExited(MouseEvent e)
  {
  }
}
