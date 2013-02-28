/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Clientes.activities;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.*;
import nicon.enterprise.gui.Clientes.CrearTipoActividad;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.NiconLibTools;
import nicon.enterprise.libCore.dao.ActividadDAO;
import nicon.enterprise.libCore.dao.ClienteDAO;
import nicon.enterprise.libCore.dao.TipoActividadDAO;
import nicon.enterprise.libCore.obj.Actividad;
import nicon.enterprise.libCore.obj.Cliente;
import nicon.enterprise.libCore.obj.TipoActividad;

public class AsignarActividad extends JDialog implements ActionListener {

    private JPanel panel;
    private JLabel jlTitulo;
    private JLabel jlCodigo;
    private JLabel jlNombre;
    private JLabel jlTipoActividad;
    private JLabel jlIdCliente;
    private JLabel jlDescripcion;
    private JLabel jlFecha;
    private JLabel jlEstado;
    private JTextField jtCodigo;
    private JTextField jtNombre;
    private JTextField jtIdCliente;
    private JTextArea jtDescripcion;
    private JDateChooser jdFechaAsignacion;
    private Actividad actividad;
    private ActividadDAO actividadDAO;
    private TipoActividad tipoActividad;
    private TipoActividadDAO tipoActividadDAO;
    private JComboBox jcTipoActividad;
    private ArrayList listaTipoActividad;
    private String[] lista;
    private JScrollPane scrollDescripcion;
    private JComboBox jcEstado;
    private int indiceActividad;
    private int indiceEstado;
    private String nombreActividad;
    private String idCliente;
    private String descripcion;
    private Date fecha;
    private boolean estadoActividad;
    private JButton jbAddTipo;
    private JButton jbAddCliente;
    private JButton jbGrabar;
    private JButton jbCancelar;
    private Cliente cliente;
    private ClienteDAO clienteDAO;

    public AsignarActividad() {
        crearInterfaz();
        cargarListaTipoActividad();
    }

    public AsignarActividad(String idCliente) {
        this.idCliente = idCliente;
        crearInterfaz();
        cargarListaTipoActividad();
    }

    private void crearInterfaz() {
        setTitle(GlobalConfigSystem.getAplicationTitle());
        setSize(600, 520);
        setModal(true);
        setLocationRelativeTo(null);
        setResizable(false);
        this.actividadDAO = new ActividadDAO();
        this.clienteDAO = new ClienteDAO();

        this.panel = new JPanel();
        this.panel.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.panel.setLayout(null);

        this.jlTitulo = new JLabel("Asignación de Actividades");
        this.jlTitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlTitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
        this.jlTitulo.setBounds(20, 20, 600, 45);

        this.jlCodigo = new JLabel("- Codigo actividad:");
        this.jlCodigo.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        this.jlCodigo.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlCodigo.setBounds(40, 100, 220, 20);

        this.jtCodigo = new JTextField(String.valueOf(this.actividadDAO.generarCodigoActividad()));
        this.jtCodigo.setEditable(false);
        this.jtCodigo.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jtCodigo.setBounds(250, 95, 300, 30);

        this.jlNombre = new JLabel("- Nombre Actividad:");
        this.jlNombre.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        this.jlNombre.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlNombre.setBounds(40, 140, 200, 20);

        this.jtNombre = new JTextField();
        this.jtNombre.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jtNombre.setBounds(250, 135, 300, 30);

        this.jlTipoActividad = new JLabel("- Tipo Actividad:");
        this.jlTipoActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        this.jlTipoActividad.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlTipoActividad.setBounds(40, 180, 200, 20);

        this.jbAddTipo = new JButton();
        this.jbAddTipo.setToolTipText("Agregar un nuevo tipo de actvidad");
        this.jbAddTipo.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconAdd.png")));
        this.jbAddTipo.setBounds(560, 175, 30, 30);
        this.jbAddTipo.addActionListener(this);

        this.jcTipoActividad = new JComboBox(cargarListaTipoActividad());
        this.jcTipoActividad.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jcTipoActividad.setToolTipText("seleccionar un tipo de actividad para la nueva actividad a registrar");
        this.jcTipoActividad.setBounds(250, 175, 300, 30);

        this.jlIdCliente = new JLabel("- Seleccione un Cliente:");
        this.jlIdCliente.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        this.jlIdCliente.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlIdCliente.setBounds(40, 220, 200, 20);

        this.jtIdCliente = new JTextField(this.idCliente);
        this.jtIdCliente.setBounds(250, 215, 300, 30);
        this.jtIdCliente.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jtIdCliente.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent fe) {
//                clienteDAO.
//                if (AsignarActividad.this.cliente == null) {
//                    JOptionPane.showMessageDialog(AsignarActividad.this.rootPane, "La identificacion Ingresada no existe, no se puede cargar el Cliente", GlobalConfigSystem.getAplicationTitle(), 0);
//                }
            }
        });
        this.jlDescripcion = new JLabel("- Desriba la actividad:");
        this.jlDescripcion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        this.jlDescripcion.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlDescripcion.setBounds(40, 260, 200, 20);

        this.jtDescripcion = new JTextArea();
        this.jtDescripcion.setLineWrap(true);
        this.jtDescripcion.setFont(GlobalConfigSystem.getFontAplicationText());

        this.scrollDescripcion = new JScrollPane(this.jtDescripcion);
        this.scrollDescripcion.setBounds(250, 255, 300, 100);

        this.jlFecha = new JLabel("- Fecha de Asignación:");
        this.jlFecha.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        this.jlFecha.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlFecha.setBounds(40, 370, 200, 20);

        this.jdFechaAsignacion = new JDateChooser();
        this.jdFechaAsignacion.setBounds(250, 365, 300, 30);

        this.jlEstado = new JLabel("-Estado de la actividad:");
        this.jlEstado.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        this.jlEstado.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlEstado.setBounds(40, 410, 200, 20);

        String[] estado = {"En proceso", "Terminada"};
        this.jcEstado = new JComboBox(estado);
        this.jcEstado.setBounds(250, 405, 300, 30);

        this.jbGrabar = new JButton("Crear Actividad");
        this.jbGrabar.addActionListener(this);
        this.jbGrabar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
        this.jbGrabar.setBounds(400, 450, 150, 30);

        this.jbCancelar = new JButton("Cancelar");
        this.jbCancelar.addActionListener(this);
        this.jbCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
        this.jbCancelar.addActionListener(this);
        this.jbCancelar.setBounds(240, 450, 150, 30);

        this.panel.add(this.jlTitulo);
        this.panel.add(this.jlTitulo);
        this.panel.add(this.jlCodigo);
        this.panel.add(this.jtCodigo);
        this.panel.add(this.jlNombre);
        this.panel.add(this.jtNombre);
        this.panel.add(this.jlTipoActividad);
        this.panel.add(this.jbAddTipo);
        this.panel.add(this.jcTipoActividad);
        this.panel.add(this.jlIdCliente);
        this.panel.add(this.jtIdCliente);
        this.panel.add(this.jlDescripcion);
        this.panel.add(this.scrollDescripcion);
        this.panel.add(this.jlFecha);
        this.panel.add(this.jdFechaAsignacion);
        this.panel.add(this.jlEstado);
        this.panel.add(this.jcEstado);
        this.panel.add(this.jbGrabar);
        this.panel.add(this.jbCancelar);
        getContentPane().add(this.panel);
    }

    private String[] cargarListaTipoActividad() {
        try {
            this.tipoActividadDAO = new TipoActividadDAO();
            this.listaTipoActividad = this.tipoActividadDAO.listarTipoActividad();
            this.lista = new String[this.listaTipoActividad.size()];
            for (int i = 0; i < this.listaTipoActividad.size(); i++) {
                this.tipoActividad = ((TipoActividad) this.listaTipoActividad.get(i));
                this.lista[i] = this.tipoActividad.getNombreActividad();
            }
        } catch (Exception e) {
        }
        return this.lista;
    }

    private void crearActividad() {
        this.nombreActividad = this.jtNombre.getText();
        this.indiceActividad = this.jcTipoActividad.getSelectedIndex();
        this.idCliente = this.jtIdCliente.getText();
        this.descripcion = this.jtDescripcion.getText();
        this.fecha = (Date) this.jdFechaAsignacion.getDate();
        this.indiceEstado = this.jcEstado.getSelectedIndex();
        if (this.indiceEstado == 0) {
            this.estadoActividad = false;
        } else {
            this.estadoActividad = true;
        }

        if ((this.nombreActividad.equals("")) || (this.idCliente.equals("")) || (this.descripcion.equals("")) || (this.fecha.equals(""))) {
            JOptionPane.showMessageDialog(this.rootPane, "Hay datos importantes sin ingresar, verifiqeu e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0);
            this.jbGrabar.setEnabled(false);
        } else {
            this.jbGrabar.setEnabled(true);
            this.indiceActividad += 1;
            this.actividad = new Actividad(this.nombreActividad, this.descripcion, this.indiceActividad, this.idCliente, NiconLibTools.dateFormatSimple(this.fecha), this.estadoActividad, null);
            this.actividadDAO = new ActividadDAO(this.actividad);
            boolean crearActividad = this.actividadDAO.crearActividad();
            if (crearActividad) {
                JOptionPane.showMessageDialog(this.rootPane, "La nueva actividad ha sido asignada al cliente exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this.rootPane, "La actividad no pudo ser asignada ocurrio un error en el proceso", GlobalConfigSystem.getAplicationTitle(), 0);
                dispose();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.jbAddTipo) {
            CrearTipoActividad agregar = new CrearTipoActividad();
            agregar.setVisible(true);
        }

        if (ae.getSource() == this.jbCancelar) {
            dispose();
        }

        if (ae.getSource() == this.jbGrabar) {
            crearActividad();
        }
    }
}