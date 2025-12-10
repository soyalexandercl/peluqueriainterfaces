package vistas;

import controladores.ControladorClientes;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaAdministracion extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelCentral;
    
    public VistaAdministracion() {
        setTitle("Administrar Peluquería");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        
        JPanel panelMenu = new JPanel(new GridLayout(6, 1, 10, 10));
        
        JButton botonClientes = new JButton("Clientes");
        JButton botonCitas = new JButton("Citas");
        JButton botonPeluqueras = new JButton("Peluqueras");
        JButton botonServicios = new JButton("Servicios");
        JButton botonProductos = new JButton("Productos");
        JButton botonFacturas = new JButton("Facturas");
        
        panelMenu.add(botonClientes);
        panelMenu.add(botonCitas);
        panelMenu.add(botonPeluqueras);
        panelMenu.add(botonServicios);
        panelMenu.add(botonProductos);
        panelMenu.add(botonFacturas);

        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);
        
        // panelCentral.add(new VistaClientes(), "clientes");

        // botonClientes.addActionListener(e -> cardLayout.show(panelCentral, "clientes"));
        
        add(panelMenu, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
        
        setVisible(true);
    }
}
