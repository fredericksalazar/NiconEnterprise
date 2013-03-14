/**
 * CopyRigth (C) 2013 NiconSystem Incorporated.
 *
 * NiconSystem Inc. Cll 9a#6a-09 Florida Valle del cauca Colombia 318 437 4382
 * fredefass01@gmail.com desarrollador-mantenedor: Frederick Adolfo Salazar
 * Sanchez.
 */

package nicon.enterprise.gui.Clientes.activities;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import nicon.enterprise.libCore.api.dao.ActividadDAO;
import nicon.enterprise.libCore.api.util.GlobalConfigSystem;
import nicon.enterprise.libCore.api.util.NiconLibTools;
import nicon.enterprise.libCore.api.dao.ClienteDAO;
import nicon.enterprise.libCore.api.obj.Actividad;
import nicon.enterprise.libCore.dao.TipoActividadDAO;
import nicon.enterprise.libCore.api.obj.Cliente;
import nicon.enterprise.libCore.obj.TipoActividad;

/**
 * esta ventana de JDialog permitirá la creacion de una nueva actividad dentro de la fuente de datos.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */
public class Actividad_Crear extends JDialog implements ActionListener {

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
    private String nombreActividad;
    private String idCliente;
    private String descripcion;
    private boolean estadoActividad;
    private int indiceActividad;
    private int indiceEstado;
    
    private JScrollPane scrollDescripcion;
    private JComboBox jcEstado;
        
    private Date fecha;
    
    private JButton jbAddTipo;
    private JButton jbGrabar;
    private JButton jbCancelar;
    private Cliente cliente;
    private ClienteDAO customerAPI;
    
    /**
     * metodo constructor que prepara la interfaz grafica para crear una nuevai
     * actividad, en esta interfaz no se recibe el ID del cliente por lo que
     * se permitirá buscar al cliente de forma manual.
     */
    public Actividad_Crear() {
        crearInterfaz();
        cargarListaTipoActividad();
    }

    
    /**
     * metodo constructor que prepara la interfaz grafica para crear una nueva
     * actividad, en esta interfaz se recibe el ID del cliente al que se le 
     * asignará la nueva actividad.
     * 
     * @param String  idCliente 
     */
    public Actividad_Crear(String idCliente) {
        this.idCliente = idCliente;
        crearInterfaz();
        cargarListaTipoActividad();
    }

    /**
     * el metodo crear interfaz se encarga de inicializar y posisionar todos
     * los componentes graficos que permitirán la configuracion de dicha
     * actividad.
     */
    private void crearInterfaz() {
        try{
            setTitle(GlobalConfigSystem.getAplicationTitle());
            setSize(600, 520);
            setModal(true);
            setLocationRelativeTo(null);
            setResizable(false);
        
            actividadDAO = new ActividadDAO();
            customerAPI = new ClienteDAO();

            panel = new JPanel();
            panel.setBackground(GlobalConfigSystem.getBackgroundAplication());
            panel.setLayout(null);

            jlTitulo = new JLabel("Asignación de Actividades");
            jlTitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
            jlTitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());
            jlTitulo.setBounds(20, 20, 600, 45);

            jlCodigo = new JLabel("- Código actividad:");
            jlCodigo.setForeground(GlobalConfigSystem.getForegroundAplicationText());
            jlCodigo.setFont(GlobalConfigSystem.getFontAplicationText());
            jlCodigo.setBounds(40, 100, 220, 20);

            jtCodigo = new JTextField(String.valueOf(actividadDAO.generarCodigoActividad()));
            jtCodigo.setEditable(false);
            jtCodigo.setFont(GlobalConfigSystem.getFontAplicationText());
            jtCodigo.setBounds(250, 95, 300, 30);

            jlNombre = new JLabel("- Nombre Actividad:");
            jlNombre.setForeground(GlobalConfigSystem.getForegroundAplicationText());
            jlNombre.setFont(GlobalConfigSystem.getFontAplicationText());
            jlNombre.setBounds(40, 140, 200, 20);

            jtNombre = new JTextField();
            jtNombre.setFont(GlobalConfigSystem.getFontAplicationText());
            jtNombre.setBounds(250, 135, 300, 30);

            jlTipoActividad = new JLabel("- Tipo Actividad:");
            jlTipoActividad.setForeground(GlobalConfigSystem.getForegroundAplicationText());
            jlTipoActividad.setFont(GlobalConfigSystem.getFontAplicationText());
            jlTipoActividad.setBounds(40, 180, 200, 20);

            jbAddTipo = new JButton();
            jbAddTipo.setToolTipText("Agregar un nuevo tipo de actvidad");
            jbAddTipo.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconAdd.png")));
            jbAddTipo.setBounds(560, 175, 30, 30);
            jbAddTipo.addActionListener(this);

            jcTipoActividad = new JComboBox(cargarListaTipoActividad());
            jcTipoActividad.setFont(GlobalConfigSystem.getFontAplicationText());
            jcTipoActividad.setToolTipText("seleccionar un tipo de actividad para la nueva actividad a registrar");
            jcTipoActividad.setBounds(250, 175, 300, 30);

            jlIdCliente = new JLabel("- Seleccione un Cliente:");
            jlIdCliente.setForeground(GlobalConfigSystem.getForegroundAplicationText());
            jlIdCliente.setFont(GlobalConfigSystem.getFontAplicationText());
            jlIdCliente.setBounds(40, 220, 200, 20);

            jtIdCliente = new JTextField(idCliente);
            jtIdCliente.setBounds(250, 215, 300, 30);
            jtIdCliente.setFont(GlobalConfigSystem.getFontAplicationText());
            jtIdCliente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                try {
                    cliente=customerAPI.buscarPorIdentificacion(jtIdCliente.getText());
                        if (cliente == null) {
                            JOptionPane.showMessageDialog(rootPane, "La identificación "
                                + "Ingresada no existe, no se puede cargar el Cliente"
                                , GlobalConfigSystem.getAplicationTitle(), 
                                JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource(
                                    GlobalConfigSystem.getIconsPath()+"NiconError.png")));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Actividad_Crear.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            });
        
            jlDescripcion = new JLabel("- Describa la actividad:");
            jlDescripcion.setForeground(GlobalConfigSystem.getForegroundAplicationText());
            jlDescripcion.setFont(GlobalConfigSystem.getFontAplicationText());
            jlDescripcion.setBounds(40, 260, 200, 20);

            jtDescripcion = new JTextArea();
            jtDescripcion.setLineWrap(true);
            jtDescripcion.setFont(GlobalConfigSystem.getFontAplicationText());

            scrollDescripcion = new JScrollPane(jtDescripcion);
            scrollDescripcion.setBounds(250, 255, 300, 100);

            jlFecha = new JLabel("- Fecha de Asignación:");
            jlFecha.setForeground(GlobalConfigSystem.getForegroundAplicationText());
            jlFecha.setFont(GlobalConfigSystem.getFontAplicationText());
            jlFecha.setBounds(40, 370, 200, 20);

            jdFechaAsignacion = new JDateChooser();
            jdFechaAsignacion.setBounds(250, 365, 300, 30);

            jlEstado = new JLabel("-Estado de la actividad:");
            jlEstado.setForeground(GlobalConfigSystem.getForegroundAplicationText());
            jlEstado.setFont(GlobalConfigSystem.getFontAplicationText());
            jlEstado.setBounds(40, 410, 200, 20);

            String[] estado = {"En proceso", "Terminada"};
        
            jcEstado = new JComboBox(estado);
            jcEstado.setFont(GlobalConfigSystem.getFontAplicationText());
            jcEstado.setBounds(250, 405, 300,30);

            jbGrabar = new JButton("Crear Actividad");
            jbGrabar.addActionListener(this);
            jbGrabar.setFont(GlobalConfigSystem.getFontAplicationText());
            jbGrabar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
            jbGrabar.setBounds(390, 450, 160, 35);

            jbCancelar = new JButton("Cancelar");
            jbCancelar.addActionListener(this);
            jbCancelar.setFont(GlobalConfigSystem.getFontAplicationText());
            jbCancelar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconCancelButton.png")));
            jbCancelar.addActionListener(this);
            jbCancelar.setBounds(220, 450,160, 35);

            panel.add(jlTitulo);
            panel.add(jlTitulo);
            panel.add(jlCodigo);
            panel.add(jtCodigo);
            panel.add(jlNombre);
            panel.add(jtNombre);
            panel.add(jlTipoActividad);
            panel.add(jbAddTipo);
            panel.add(jcTipoActividad);
            panel.add(jlIdCliente);
            panel.add(jtIdCliente);
            panel.add(jlDescripcion);
            panel.add(scrollDescripcion);
            panel.add(jlFecha);
            panel.add(jdFechaAsignacion);
            panel.add(jlEstado);
            panel.add(jcEstado);
            panel.add(jbGrabar);
            panel.add(jbCancelar);
        
            getContentPane().add(panel);
        }catch(Exception jre){
            System.out.println(jre);
        }        
    }
    
    /**
     * este metodo permite cargar los tipos de actividades a la lista que se mostrará al usuario
     * @return 
     */
    private String[] cargarListaTipoActividad() {        
            tipoActividadDAO = new TipoActividadDAO();
            listaTipoActividad = tipoActividadDAO.listarTipoActividad();
            lista = new String[listaTipoActividad.size()];
                for (int i = 0; i < this.listaTipoActividad.size(); i++) {
                        tipoActividad = ((TipoActividad)listaTipoActividad.get(i));
                        lista[i] = tipoActividad.getNombreActividad();
                }        
        return lista;
    }

    private void crearActividad() {
        nombreActividad = jtNombre.getText();
        indiceActividad = jcTipoActividad.getSelectedIndex();
        idCliente = jtIdCliente.getText();
        descripcion = jtDescripcion.getText();
        fecha = jdFechaAsignacion.getDate();
        indiceEstado = jcEstado.getSelectedIndex();
        
            if (indiceEstado == 0) {
                estadoActividad = false;
            } else {
                estadoActividad = true;
            }

        if ((nombreActividad.equals("")) || (idCliente.equals("")) || (descripcion.equals("")) || (fecha.toString().equals(""))) {
            JOptionPane.showMessageDialog(rootPane, "Hay datos importantes sin ingresar, verifique e intente de nuevo", GlobalConfigSystem.getAplicationTitle(), 0);
            jbGrabar.setEnabled(false);
        } else {
            try {
                jbGrabar.setEnabled(true);
                indiceActividad += 1;
                actividad = new Actividad(nombreActividad,descripcion,indiceActividad,idCliente, NiconLibTools.parseToMysqlStringDate(fecha),estadoActividad, null);
                actividadDAO = new ActividadDAO(actividad);
                
                        if (actividadDAO.crearActividad()) {
                            JOptionPane.showMessageDialog(rootPane, "La nueva actividad ha sido asignada al cliente exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "La actividad no pudo ser asignada ocurrio un error en el proceso", GlobalConfigSystem.getAplicationTitle(), 0);
                            dispose();
                        }
            } catch (ParseException ex) {
                Logger.getLogger(Actividad_Crear.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Actividad_Crear.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jbAddTipo) {
            CrearTipoActividad agregar = new CrearTipoActividad();
            agregar.setVisible(true);
        }

        if (ae.getSource() == jbCancelar) {
            dispose();
        }

        if (ae.getSource() == jbGrabar) {
            crearActividad();
        }
    }
}