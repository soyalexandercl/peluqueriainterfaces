package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VistaProductos extends JPanel {
    public JTable tablaProductos;
    public DefaultTableModel modeloTabla;
    
    public JButton botonAgregar;
    public JButton botonEliminar;
    public JButton botonRefrescar;

    public VistaProductos() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Proveedor"); // Mostraremos nombre, no ID
        modeloTabla.addColumn("Stock");
        modeloTabla.addColumn("Precio");
        
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        botonAgregar = new JButton("Nuevo Producto");
        botonEliminar = new JButton("Eliminar");
        botonRefrescar = new JButton("Recargar");

        panelBotones.add(botonAgregar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonRefrescar);
        
        add(panelBotones, BorderLayout.SOUTH);
    }
}