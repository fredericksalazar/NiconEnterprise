/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.api.obj.Almacen;
import nicon.enterprise.memData.StoreData;

public class GuiAlmacen extends JDialog implements ActionListener, MouseListener {

    private int PanelWidth;
    private int PanelHeight;
    private JPanel JPAlmacen;
    private JList ListaAlmacenes;
    private DefaultListModel ModelList;
    private JScrollPane JScroll;
    private JLabel JLCode;
    private JLabel JLNombre;
    private JLabel JLdireccion;
    private JLabel JLBarrio;
    private JLabel JLciudad;
    private JLabel JLDepartamento;
    private JLabel JLTel_fijo;
    private JLabel JLemail;
    private JTextField JTCodigo;
    private JTextField JTNombre;
    private JTextField JTDireccion;
    private JTextField JTBarrio;
    private JTextField JTCiudad;
    private JTextField JTDepartamento;
    private JTextField JTTel_fijo;
    private JTextField JTEmail;
    private JButton JBnew;
    private JButton JBEdit;
    private JButton JBStatics;
    private JButton JBsave;
    private JButton JBCancel;
    private ArrayList allStore;
    private Almacen Store;
    private Iterator iterator;
    private int Counter;
    private int Index;
    private Almacen TmpStore;

    public GuiAlmacen() {
        this.PanelWidth = 750;
        this.PanelHeight = 480;
        setTitle(GlobalConfigSystem.getAplicationTitle());
        setSize(this.PanelWidth, this.PanelHeight);
        setModal(true);
        setLocationRelativeTo(null);
        setResizable(false);
        this.Store = new Almacen();
        this.TmpStore = new Almacen();
        Init();
    }

    private void Init() {
        this.JPAlmacen = new JPanel();
        this.JPAlmacen.setBackground(GlobalConfigSystem.getBackgroundAplicationPanel());
        this.JPAlmacen.setLayout(null);
        this.JPAlmacen.setBounds(0, 0, this.PanelWidth, this.PanelHeight);

        this.allStore = StoreData.cloneStoreData();
        this.iterator = this.allStore.iterator();

        this.ModelList = new DefaultListModel();
        this.ListaAlmacenes = new JList(this.ModelList);
        this.ListaAlmacenes.addMouseListener(this);
        this.JScroll = new JScrollPane(this.ListaAlmacenes);
        this.JScroll.setBounds(0, 0, 250, this.PanelHeight);

        loadArrayList();

        this.JLCode = new JLabel("Código del Almacen:");
        this.JLCode.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLCode.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLCode.setBounds(270, 20, 250, 20);

        this.JLNombre = new JLabel("Nombre del Almacen:");
        this.JLNombre.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLNombre.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLNombre.setBounds(270, 60, 250, 20);

        this.JLdireccion = new JLabel("Ingrese la Dirección:");
        this.JLdireccion.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLdireccion.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLdireccion.setBounds(270, 100, 250, 20);

        this.JLBarrio = new JLabel("Ingrese el Barrio:");
        this.JLBarrio.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLBarrio.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLBarrio.setBounds(270, 140, 250, 20);

        this.JLciudad = new JLabel("Ingrese la ciudad:");
        this.JLciudad.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLciudad.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLciudad.setBounds(270, 180, 250, 20);

        this.JLDepartamento = new JLabel("Ingrese el departamento:");
        this.JLDepartamento.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLDepartamento.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLDepartamento.setBounds(270, 220, 250, 20);

        this.JLTel_fijo = new JLabel("Ingrese el Telefono fijo:");
        this.JLTel_fijo.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLTel_fijo.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLTel_fijo.setBounds(270, 260, 250, 20);

        this.JLemail = new JLabel("Ingrese el Email:");
        this.JLemail.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLemail.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLemail.setBounds(270, 300, 250, 20);

        this.JTCodigo = new JTextField();
        this.JTCodigo.setBounds(450, 15, 280, 25);
        this.JTCodigo.setEditable(false);
        this.JTCodigo.setToolTipText("Código de el almacen");

        this.JTNombre = new JTextField();
        this.JTNombre.setBounds(450, 55, 280, 25);
        this.JTNombre.setToolTipText("Nombre de el almacen");
        this.JTNombre.setEditable(false);

        this.JTDireccion = new JTextField();
        this.JTDireccion.setBounds(450, 95, 280, 25);
        this.JTDireccion.setToolTipText("Direccion de el almacen");
        this.JTDireccion.setEditable(false);

        this.JTBarrio = new JTextField();
        this.JTBarrio.setBounds(450, 135, 280, 25);
        this.JTBarrio.setEditable(false);
        this.JTBarrio.setToolTipText("Ingrese el barrio de el almacen");

        this.JTCiudad = new JTextField();
        this.JTCiudad.setBounds(450, 175, 280, 25);
        this.JTCiudad.setToolTipText("Ingrese la ciudad de el almacen");
        this.JTCiudad.setEditable(false);

        this.JTDepartamento = new JTextField();
        this.JTDepartamento.setBounds(450, 215, 280, 25);
        this.JTDepartamento.setEditable(false);
        this.JTDepartamento.setToolTipText("Ingrese el departamento del almacen");

        this.JTTel_fijo = new JTextField();
        this.JTTel_fijo.setBounds(450, 255, 280, 25);
        this.JTTel_fijo.setEditable(false);
        this.JTTel_fijo.setToolTipText("Ingrese el numero de telefono fijo del almacen");

        this.JTEmail = new JTextField();
        this.JTEmail.setBounds(450, 295, 280, 25);
        this.JTEmail.setEditable(false);
        this.JTEmail.setToolTipText("Ingrese la direccion de correo electronico");

        this.JBnew = new JButton("Crear Nuevo");
        this.JBnew.setToolTipText("Ingresar un nuevo almacen al sistema");
        this.JBnew.setBounds(310, 420, 120, 32);
        this.JBnew.addActionListener(this);

        this.JBEdit = new JButton("Editar datos");
        this.JBEdit.setToolTipText("Editar los datos de un almacen seleccionado");
        this.JBEdit.setBounds(440, 420, 120, 32);
        this.JBEdit.addActionListener(this);

        this.JBStatics = new JButton("estadísticas");
        this.JBStatics.setToolTipText("Ver todas las estadisticas del almacen seleccionado");
        this.JBStatics.setBounds(570, 420, 120, 32);
        this.JBStatics.addActionListener(this);

        this.JBsave = new JButton("Guardar");
        this.JBsave.setToolTipText("Guardar la informacion ingresada en el sistema");
        this.JBsave.setBounds(480, 360, 120, 32);
        this.JBsave.addActionListener(this);

        this.JBCancel = new JButton("Cancelar");
        this.JBCancel.setToolTipText("Cancelar el registro del almace");
        this.JBCancel.setBounds(610, 360, 120, 32);
        this.JBCancel.addActionListener(this);

        this.JPAlmacen.add(this.JScroll);
        this.JPAlmacen.add(this.JLCode);
        this.JPAlmacen.add(this.JLNombre);
        this.JPAlmacen.add(this.JLdireccion);
        this.JPAlmacen.add(this.JLBarrio);
        this.JPAlmacen.add(this.JLDepartamento);
        this.JPAlmacen.add(this.JLTel_fijo);
        this.JPAlmacen.add(this.JLemail);
        this.JPAlmacen.add(this.JTCodigo);
        this.JPAlmacen.add(this.JTNombre);
        this.JPAlmacen.add(this.JTDireccion);
        this.JPAlmacen.add(this.JTBarrio);
        this.JPAlmacen.add(this.JLciudad);
        this.JPAlmacen.add(this.JTCiudad);
        this.JPAlmacen.add(this.JTDepartamento);
        this.JPAlmacen.add(this.JTTel_fijo);
        this.JPAlmacen.add(this.JTEmail);
        this.JPAlmacen.add(this.JBnew);
        this.JPAlmacen.add(this.JBEdit);
        this.JPAlmacen.add(this.JBStatics);

        getContentPane().add(this.JPAlmacen);
    }

    private void SaveDataToDB() {
        try {
            String Nombre = this.JTNombre.getText();
            String Direccion = this.JTDireccion.getText();
            String Barrio = this.JTBarrio.getText();
            String Ciudad = this.JTCiudad.getText();
            String Departamento = this.JTDepartamento.getText();
            String Tel_fijo = this.JTTel_fijo.getText();
            String Email = this.JTEmail.getText();

            if ((Nombre.equals("")) || (Direccion.equals("")) || (Barrio.equals("")) || (Ciudad.equals("")) || (Departamento.equals("")) || (Tel_fijo.equals("")) || (Email.equals(""))) {
                JOptionPane.showMessageDialog(this.rootPane, "Hay datos que aún no ha ingresado, por favor verifique e intente de nuevo", "NiconEnterprise", 0);
            } else {
                this.Store = new Almacen(Nombre, Direccion, Barrio, Ciudad, Departamento, Tel_fijo, Email);
//                StoreData.addStore(this.Store);
                this.ModelList.addElement(this.Store.getNombre());
                setToReadDataPanel();
            }
        } catch (Exception e) {
            Logger.getLogger(GuiAlmacen.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Ocurrio este error en GuiAlmacen.SaveDataToDB() DetailError:\n" + e);
        }
    }

    private void loadArrayList() {
        this.Counter = 0;
        try {
            while (this.iterator.hasNext()) {
                this.TmpStore = ((Almacen) this.allStore.get(this.Counter));
                this.ModelList.addElement(this.TmpStore.getNombre());
                this.Counter += 1;
            }
        } catch (Exception e) {
            System.out.println("ERROR loadArrayList() In GuiAlmacen detail:\n" + e);
        }
    }

    private void setToInputDataPanel() {
        this.JTNombre.setEditable(true);
        this.JTNombre.setText("");
        this.JTDireccion.setEditable(true);
        this.JTDireccion.setText("");
        this.JTBarrio.setEditable(true);
        this.JTBarrio.setText("");
        this.JTCiudad.setEditable(true);
        this.JTCiudad.setText("");
        this.JTDepartamento.setEditable(true);
        this.JTDepartamento.setText("");
        this.JTTel_fijo.setEditable(true);
        this.JTTel_fijo.setText("");
        this.JTEmail.setEditable(true);
        this.JTEmail.setText("");
        this.JBnew.setEnabled(false);
        this.JBEdit.setEnabled(false);
        this.JBStatics.setEnabled(false);
        this.JPAlmacen.add(this.JBsave);
        this.JPAlmacen.add(this.JBCancel);
        repaint();
    }

    private void setToReadDataPanel() {
        this.JTNombre.setEditable(false);
        this.JTNombre.setText("");
        this.JTDireccion.setEditable(false);
        this.JTDireccion.setText("");
        this.JTCiudad.setEditable(false);
        this.JTCiudad.setText("");
        this.JTBarrio.setEditable(false);
        this.JTBarrio.setText("");
        this.JTDepartamento.setEditable(false);
        this.JTDepartamento.setText("");
        this.JTEmail.setEditable(false);
        this.JTEmail.setText("");
        this.JTTel_fijo.setEditable(false);
        this.JTTel_fijo.setText("");
        this.JPAlmacen.remove(this.JBsave);
        this.JPAlmacen.remove(this.JBCancel);
        this.JBnew.setEnabled(true);
        this.JBEdit.setEnabled(true);
        this.JBStatics.setEnabled(true);
        repaint();
    }

    private void setInformationStore(int index) {
        try {
            this.Store = ((Almacen) this.allStore.get(index));

            this.JTCodigo.setText(String.valueOf(this.Store.getCodeStore()));
            this.JTNombre.setText(this.Store.getNombre());
            this.JTDireccion.setText(this.Store.getDireccion());
            this.JTBarrio.setText(this.Store.getBarrio());
            this.JTCiudad.setText(this.Store.getCiudad());
            this.JTDepartamento.setText(this.Store.getProvincia());
            this.JTTel_fijo.setText(this.Store.getTelefono_fijo());
            this.JTEmail.setText(this.Store.getEmail());
        } catch (Exception e) {
            System.out.println("ERROR setInformationStore() In GuiAlmacen detail:\n" + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.JBnew) {
            setToInputDataPanel();
        }
        if (ae.getSource() == this.JBsave) {
            SaveDataToDB();
        }
        if (ae.getSource() == this.JBCancel) {
            setToReadDataPanel();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() == this.ListaAlmacenes) {
            this.Index = this.ListaAlmacenes.getSelectedIndex();
            setInformationStore(this.Index);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    
}