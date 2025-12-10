package vistas;

import java.awt.Dimension;
import javax.swing.JFrame;

public class VistaSimulacion extends JFrame {
    public VistaSimulacion() {
        setTitle("Simulación Peluquería");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        
        setVisible(true);
    }
}
