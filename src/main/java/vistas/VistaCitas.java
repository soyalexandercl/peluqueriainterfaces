package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
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

        modeloTabla = new DefaultTableModel() {
            @Override // Hacemos que las celdas no sean editables al hacer doble click
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Fecha Registro");
        
        tablaCitas = new JTable(modeloTabla);
        
        // --- Renderizador de Colores ---
        tablaCitas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Obtenemos el estado (Columna 3)
                String estado = (String) table.getModel().getValueAt(row, 3);
                
                if ("Completada".equalsIgnoreCase(estado)) {
                    c.setBackground(new Color(200, 255, 200)); // Verde claro
                    c.setForeground(Color.BLACK);
                } else if ("Cancelada".equalsIgnoreCase(estado)) {
                    c.setBackground(new Color(255, 200, 200)); // Rojo claro
                    c.setForeground(Color.RED);
                } else if ("En curso".equalsIgnoreCase(estado)) {
                    c.setBackground(new Color(255, 255, 200)); // Amarillo claro
                    c.setForeground(Color.BLACK);
                } else {
                    // Programada u otro
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                
                // Si está seleccionada, mantenemos el azul por defecto del sistema pero más oscuro
                if (isSelected) {
                    c.setBackground(new Color(184, 207, 229));
                    c.setForeground(Color.BLACK);
                }
                
                return c;
            }
        });
        
        // Ajustes visuales de tabla
        tablaCitas.setRowHeight(25);
        tablaCitas.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(tablaCitas);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        botonNuevaCita = new JButton("Nueva Cita");
        botonCompletar = new JButton("Marcar Completada");
        botonCancelar = new JButton("Cancelar Cita");
        botonRefrescar = new JButton("Recargar");

        // Iconos visuales (Unicode simple)
        botonNuevaCita.setText("➕ Nueva Cita");
        botonCompletar.setText("✅ Completar");
        botonCancelar.setText("❌ Cancelar");
        botonRefrescar.setText("🔄 Recargar");

        panelBotones.add(botonNuevaCita);
        panelBotones.add(botonCompletar);
        panelBotones.add(botonCancelar);
        panelBotones.add(botonRefrescar);
        
        add(panelBotones, BorderLayout.SOUTH);
    }
}