package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VistaCitas extends JPanel {
    public JTable tablaCitas;
    public DefaultTableModel modeloTabla;
    
    public JButton botonNuevaCita;
    public JButton botonCancelar;
    public JButton botonCompletar;
    public JButton botonRefrescar;

    public VistaCitas() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Fecha Registro");
        
        tablaCitas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaCitas);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        botonNuevaCita = new JButton("Nueva Cita Rápida");
        botonCompletar = new JButton("Marcar Completada");
        botonCancelar = new JButton("Cancelar Cita");
        botonRefrescar = new JButton("Recargar");

        panelBotones.add(botonNuevaCita);
        panelBotones.add(botonCompletar);
        panelBotones.add(botonCancelar);
        panelBotones.add(botonRefrescar);
        
        add(panelBotones, BorderLayout.SOUTH);
    }
}