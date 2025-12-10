package vistas;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaEspacioTrabajo extends JFrame {
    public JButton botonAdministrar;
    public JButton botonSimular;

    public VistaEspacioTrabajo() {
        setTitle("Espacio de trabajo");
        setSize(300, 180);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        // Inicializar los objetos
        botonAdministrar = new JButton("Administrar Peluquería");
        botonSimular = new JButton("Simular Peluquería");

        panel.add(botonAdministrar);
        panel.add(botonSimular);

        add(panel);
        setVisible(true);
    }
}