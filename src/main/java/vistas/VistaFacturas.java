package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VistaFacturas extends JPanel {
    public JTable tablaFacturas;
    public DefaultTableModel modeloTabla;
    
    public JButton botonGenerar;
    public JButton botonRefrescar;

    public VistaFacturas() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nº Factura");
        modeloTabla.addColumn("Cita ID");
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("Importe");
        modeloTabla.addColumn("Método Pago");
        modeloTabla.addColumn("Fecha Emisión");
        
        tablaFacturas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaFacturas);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        botonGenerar = new JButton("Generar Factura de Cita");
        botonRefrescar = new JButton("Recargar Historial");

        panelBotones.add(botonGenerar);
        panelBotones.add(botonRefrescar);
        
        add(panelBotones, BorderLayout.SOUTH);
    }
}