/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.dao.ClienteDAO;
import nicon.enterprise.libCore.obj.Cliente;

public class Actualizar extends JDialog implements ActionListener {

    private JPanel JPUpdate;
    private JLabel JLTitulo;
    private JLabel JLid;
    private JLabel JLnombres;
    private JLabel JLresidencia;
    private JLabel JLTelefonos;
    private JLabel JLoptions;
    private JLabel JLinput;
    private JComboBox JCOptions;
    private JTextField JTinput;
    private JButton JBcancelar;
    private JButton JBaceptar;
    private TitledBorder Border;
    private Cliente cliente;
    private ClienteDAO clienteDAO;

    public Actualizar(Cliente cliente) {
        setSize(700, 500);
        setTitle(GlobalConfigSystem.getAplicationTitle());
        setModal(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        this.cliente = cliente;
        initComponents();
    }

    private void initComponents() {
        this.clienteDAO = new ClienteDAO();
        String[] options = {"identificacion", "nombres", "apellidos", "ciudad", "direccion", "Departamento", "telefono_fijo", "telefono_movil", "telefono_alternativo", "email"};

        this.Border = BorderFactory.createTitledBorder(GlobalConfigSystem.getAplicationTitle());

        this.JPUpdate = new JPanel();
        this.JPUpdate.setBackground(GlobalConfigSystem.getBackgroundAplication());
        this.JPUpdate.setLayout(null);
        this.JPUpdate.setBorder(this.Border);

        this.JLTitulo = new JLabel("Actualización de Datos");
        this.JLTitulo.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.JLTitulo.setBounds(150, 15, 600, 50);
        this.JLTitulo.setFont(GlobalConfigSystem.getFontAplicationTitle());

        this.JLid = new JLabel("Identificación:          " + this.cliente.getIdentificacion());
        this.JLid.setBounds(25, 80, 300, 25);
        this.JLid.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLid.setFont(GlobalConfigSystem.getFontAplicationText());

        this.JLnombres = new JLabel("Nombre:                      " + this.cliente.getNombres() + " " + this.cliente.getApellidos());
        this.JLnombres.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLnombres.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLnombres.setBounds(25, 110, 500, 25);

        this.JLresidencia = new JLabel("Residencia:                  " + this.cliente.getDireccion() + " / " + this.cliente.getCiudad() + " / " + this.cliente.getProvincia());
        this.JLresidencia.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLresidencia.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLresidencia.setBounds(25, 140, 500, 25);

        this.JLTelefonos = new JLabel("Telefonos:                   " + this.cliente.getTelefono_fijo() + " / " + this.cliente.getTelefono_movil() + "/ " + this.cliente.getTelefono_alternativo());
        this.JLTelefonos.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLTelefonos.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLTelefonos.setBounds(25, 170, 600, 25);

        this.JLoptions = new JLabel("Seleccione el campo a actualizar:");
        this.JLoptions.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLoptions.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLoptions.setBounds(25, 280, 250, 25);

        this.JCOptions = new JComboBox(options);
        this.JCOptions.setBounds(300, 280, 300, 25);
        this.JCOptions.setToolTipText("Seleccione un campo para actualizar");

        this.JLinput = new JLabel("Ingrese el nuevo Dato:");
        this.JLinput.setForeground(GlobalConfigSystem.getForegrounAplicationText());
        this.JLinput.setFont(GlobalConfigSystem.getFontAplicationText());
        this.JLinput.setBounds(25, 350, 300, 25);

        this.JTinput = new JTextField();
        this.JTinput.setBounds(300, 345, 300, 25);

        this.JBcancelar = new JButton("Cancelar");
        this.JBcancelar.setBounds(300, 440, 150, 35);
        this.JBcancelar.setToolTipText("Cancelar la actualizacion de datos");
        this.JBcancelar.addActionListener(this);

        this.JBaceptar = new JButton("Actualizar");
        this.JBaceptar.setBounds(470, 440, 150, 35);
        this.JBaceptar.setToolTipText("Actualizar la informacion");
        this.JBaceptar.addActionListener(this);

        this.JPUpdate.add(this.JLTitulo);
        this.JPUpdate.add(this.JLid);
        this.JPUpdate.add(this.JLnombres);
        this.JPUpdate.add(this.JLresidencia);
        this.JPUpdate.add(this.JLTelefonos);
        this.JPUpdate.add(this.JLoptions);
        this.JPUpdate.add(this.JCOptions);
        this.JPUpdate.add(this.JLinput);
        this.JPUpdate.add(this.JTinput);
        this.JPUpdate.add(this.JBcancelar);
        this.JPUpdate.add(this.JBaceptar);

        getContentPane().add(this.JPUpdate);
    }

    private void getInputData() {
        String campo = (String) this.JCOptions.getSelectedItem();
        if (campo.equals("identificacion")) {
            String cedula = this.JTinput.getText();
            String cedulaActual = this.cliente.getIdentificacion();
            this.clienteDAO.actualizarIdentificacion(cedula, cedulaActual);
            dispose();
        } else {
            String dato = this.JTinput.getText();
            boolean ActualizarDatos = this.clienteDAO.actualizarDatoCliente(this.cliente.getIdentificacion(), campo, dato);
            if (ActualizarDatos) {
                JOptionPane.showMessageDialog(this.rootPane, "El cliente ha sido actualizado exitosamente", GlobalConfigSystem.getAplicationTitle(), 1);
                dispose();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.JBcancelar) {
            dispose();
        }
        if (ae.getSource() == this.JBaceptar) {
            getInputData();
        }
    }
}