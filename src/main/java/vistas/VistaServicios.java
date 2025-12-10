package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VistaServicios extends JPanel {
    public JTable tablaServicios;
    public DefaultTableModel modeloTabla;
    
    public JButton botonAgregar;
    public JButton botonEliminar;
    public JButton botonRefrescar;

    public VistaServicios() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Servicio");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Duración");
        modeloTabla.addColumn("Producto Usado");
        
        tablaServicios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaServicios);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        botonAgregar = new JButton("Nuevo Servicio");
        botonEliminar = new JButton("Eliminar");
        botonRefrescar = new JButton("Recargar");

        panelBotones.add(botonAgregar);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonRefrescar);
        
        add(panelBotones, BorderLayout.SOUTH);
    }
}