package vistas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaAdministracion extends JFrame {
    
    // Layout que permite intercambiar paneles (como cartas de una baraja)
    public CardLayout cardLayout; 
    public JPanel panelCentral;   // Aquí se mostrarán las vistas (Clientes, Citas, etc.)

    // Botones del menú lateral
    public JButton botonMenuClientes;
    public JButton botonMenuCitas;
    public JButton botonMenuPeluqueras;
    public JButton botonMenuServicios;
    public JButton botonMenuProductos;
    public JButton botonMenuFacturas;

    public VistaAdministracion() {
        setTitle("Administrar Peluquería - Sistema de Gestión");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Diseño principal

        // --- 1. Menú Lateral (Izquierda) ---
        JPanel panelMenu = new JPanel(new GridLayout(7, 1, 10, 10)); // 7 filas, 1 columna
        
        // Inicializamos botones
        botonMenuClientes = new JButton("Gestión Clientes");
        botonMenuCitas = new JButton("Gestión Citas");
        botonMenuPeluqueras = new JButton("Gestión Peluqueras");
        botonMenuServicios = new JButton("Servicios");
        botonMenuProductos = new JButton("Inventario Productos");
        botonMenuFacturas = new JButton("Facturación");
        JButton botonSalir = new JButton("Salir"); // Opcional

        // Añadimos al panel lateral
        panelMenu.add(botonMenuClientes);
        panelMenu.add(botonMenuCitas);
        panelMenu.add(botonMenuPeluqueras);
        panelMenu.add(botonMenuServicios);
        panelMenu.add(botonMenuProductos);
        panelMenu.add(botonMenuFacturas);
        panelMenu.add(botonSalir);

        add(panelMenu, BorderLayout.WEST); // Ponemos el menú a la izquierda

        // --- 2. Panel Central (Contenido Cambiante) ---
        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);
        
        // Aquí añadiremos los paneles desde el Controlador
        
        add(panelCentral, BorderLayout.CENTER);
        
        // No hacemos setVisible(true) aquí, lo hará el controlador
    }
}