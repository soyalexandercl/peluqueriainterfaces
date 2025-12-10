package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Hereda de JPanel, NO de JFrame, para poder meterlo dentro de la ventana principal
public class VistaClientes extends JPanel {
    
    // Componentes públicos para que el controlador pueda acceder a ellos
    public JTable tablaClientes;
    public DefaultTableModel modeloTabla;
    
    public JButton botonAgregar;
    public JButton botonEditar;
    public JButton botonEliminar;
    public JButton botonRefrescar;

    public VistaClientes() {
        // Usamos BorderLayout para organizar el panel
        setLayout(new BorderLayout());

        // --- 1. Configuración de la Tabla ---
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Es VIP");
        
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        add(scrollPane, BorderLayout.CENTER); // La tabla ocupa el centro

        // --- 2. Panel de Botones (Inferior) ---
        JPanel panelBotones = new JPanel(new FlowLayout());
        
        botonAgregar = new JButton("Nuevo Cliente");
        botonEditar = new JButton("Editar Seleccionado");
        botonEliminar = new JButton("Eliminar Seleccionado");
        botonRefrescar = new JButton("Recargar Tabla");

        panelBotones.add(botonAgregar);
        panelBotones.add(botonEditar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonRefrescar);
        
        add(panelBotones, BorderLayout.SOUTH); // Los botones van abajo
    }
}