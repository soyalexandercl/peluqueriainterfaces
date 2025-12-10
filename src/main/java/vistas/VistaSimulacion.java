package vistas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VistaSimulacion extends JFrame {
    
    public VistaSimulacion() {
        setTitle("Simulación de la Peluquería (PSP)");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose para no cerrar toda la app
        setLocationRelativeTo(null);
        
        add(new JLabel("AQUÍ IRÁ LA SIMULACIÓN VISUAL", SwingConstants.CENTER));
        
        setVisible(true);
    }
}