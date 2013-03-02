/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.gui.Clientes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.api.dao.ClienteDAO;
import nicon.enterprise.libCore.obj.Cliente;

public class CajaBusqueda extends JDialog {

    private JPanel panelBusqueda;
    private JTextField jtBusqueda;
    private JLabel jlIngreso;
    private JButton jbBuscar;
    private String ID;
    private Cliente cliente;
    private ClienteDAO clienteDAO;

    public CajaBusqueda() {
        this.clienteDAO = new ClienteDAO();
        setUndecorated(true);
        setSize(450, 110);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        this.panelBusqueda = new JPanel();
        this.panelBusqueda.setLayout(null);
        this.panelBusqueda.setBackground(GlobalConfigSystem.getrowSelectedTable());

        this.jlIngreso = new JLabel("- Ingrese el número de Indeitificación a buscar:");
        this.jlIngreso.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        this.jlIngreso.setFont(GlobalConfigSystem.getFontAplicationText());
        this.jlIngreso.setBounds(10, 10, 600, 20);

        this.jtBusqueda = new JTextField();
        this.jtBusqueda.setBounds(50, 50, 350, 30);

        this.jbBuscar = new JButton();
        this.jbBuscar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconFind.png")));
        this.jbBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CajaBusqueda.this.buscarCliente();
            }
        });
        this.jbBuscar.setBounds(405, 48, 35, 35);

        this.panelBusqueda.add(this.jlIngreso);
        this.panelBusqueda.add(this.jtBusqueda);
        this.panelBusqueda.add(this.jbBuscar);

        getContentPane().add(this.panelBusqueda);
    }

    private void buscarCliente() {
        this.ID = this.jtBusqueda.getText();
        if (this.ID.equals("")) {
            JOptionPane.showMessageDialog(this.rootPane, "No ha Ingresado parámetros a buscar", GlobalConfigSystem.getAplicationTitle(), 0);
        } else {
            try {
                this.cliente = this.clienteDAO.buscarPorIdentificacion(this.ID);
                if (this.cliente != null) {
                    ModuloClientes.mostrarDatos(this.cliente);
                    ModuloClientes.seleccionarCliente(this.ID);
                } else {
                    JOptionPane.showMessageDialog(this.rootPane, "No se encontraron registros.", GlobalConfigSystem.getAplicationTitle(), 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CajaBusqueda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dispose();
    }
}
