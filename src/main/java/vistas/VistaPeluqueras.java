package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VistaPeluqueras extends JPanel {
    
    public JTable tablaPeluqueras;
    public DefaultTableModel modeloTabla;
    
    public JButton botonAgregar;
    public JButton botonEditar;
    public JButton botonEliminar;
    public JButton botonRefrescar;

    public VistaPeluqueras() {
        setLayout(new BorderLayout());

        // --- Configuración Tabla ---
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Experiencia"); // Nueva columna
        modeloTabla.addColumn("Especialidad"); // Nueva columna
        modeloTabla.addColumn("Estado");       // Nueva columna
        
        tablaPeluqueras = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPeluqueras);
        add(scrollPane, BorderLayout.CENTER);

        // --- Panel Botones ---
        JPanel panelBotones = new JPanel(new FlowLayout());
        
        botonAgregar = new JButton("Nueva Peluquera");
        botonEditar = new JButton("Editar");
        botonEliminar = new JButton("Eliminar");
        botonRefrescar = new JButton("Recargar");

        panelBotones.add(botonAgregar);
        panelBotones.add(botonEditar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonRefrescar);
        
        add(panelBotones, BorderLayout.SOUTH);
    }
}