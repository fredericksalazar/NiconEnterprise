/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package nicon.enterprise.gui.Clientes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import nicon.enterprise.libCore.GlobalConfigSystem;
import nicon.enterprise.libCore.NiconLibTools;
import nicon.enterprise.libCore.api.dao.ClienteDAO;
import nicon.enterprise.libCore.api.obj.Cliente;

/**
 * La caja de Busqueda es un diálogo que permite al usuario hacer una búsqeuda simple de un cliente dentro de la
 * fuente de datos, el parametro que debe ingresar debe ser el ID del cliente y presiona enter para inciar la 
 * busqeuda de registros usando el API ClienteDAO.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 */

public class CajaBusqueda extends JDialog{

    private JPanel panelBusqueda;
    private JTextField jtBusqueda;
    private JLabel jlIngreso;
    private JButton buscar;
    
    private String ID;
    private Cliente cliente;
    private ClienteDAO clienteDAO;

    public CajaBusqueda() {
        clienteDAO = new ClienteDAO();
        setUndecorated(true);
        setSize(450,110);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {        
        NiconLibTools.addEscapeListenerWindowDialog(this);
        
        panelBusqueda = new JPanel();
        panelBusqueda.setLayout(null);
        panelBusqueda.setBackground(GlobalConfigSystem.getBackgroundAplicationPanel());

        jlIngreso = new JLabel("- Ingrese un número de identificación a buscar:");
        jlIngreso.setForeground(GlobalConfigSystem.getForegroundAplicationTitle());
        jlIngreso.setFont(GlobalConfigSystem.getFontAplicationTextBold());
        jlIngreso.setBounds(10, 10, 600, 20);

        jtBusqueda = new JTextField("Ingrese datos a Buscar");
        jtBusqueda.setForeground(Color.gray);
        jtBusqueda.setFont(GlobalConfigSystem.getFontAplicationTextItalic());
        jtBusqueda.setBounds(50, 50, 350, 30);
        jtBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                jtBusqueda.setText("");
            }
        });
        
        buscar=new JButton();
        buscar.setBounds(410, 50, 30, 30);
        buscar.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconFind.png")));
        buscar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                buscarCliente();
           }
        });

        panelBusqueda.add(jlIngreso);
        panelBusqueda.add(jtBusqueda);
        panelBusqueda.add(buscar);
                
        getContentPane().add(panelBusqueda);
        getRootPane().setDefaultButton(buscar);
    }

    /**
     * 
     */
    private void buscarCliente() {
        ID =jtBusqueda.getText();
        if (ID.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No ha Ingresado parámetros a buscar", GlobalConfigSystem.getAplicationTitle(),0,new javax.swing.ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconError.png")));
        } else {
            try {
                cliente = clienteDAO.buscarPorIdentificacion(ID);
                    if (cliente != null) {
                        ModuloClientes.mostrarDatos(cliente);
                        ModuloClientes.seleccionarCliente(ID);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this.rootPane, "No se encontraron registros.", GlobalConfigSystem.getAplicationTitle(), 0);
                    }
            } catch (SQLException ex) {
                Logger.getLogger(CajaBusqueda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
