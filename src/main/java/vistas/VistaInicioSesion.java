package vistas;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class VistaInicioSesion extends JFrame {
    public JTextField campoUsuario;
    public JPasswordField campoPassword;
    public JButton botonContinuar;
    
    public VistaInicioSesion() {
        setTitle("Inicio de Sesión");
        setSize(300, 180);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Inicializar los objetos
        campoUsuario = new JTextField();
        campoPassword = new JPasswordField();
        botonContinuar = new JButton("Continuar");

        // Panel usuario
        JPanel panelUsuario = new JPanel(new GridLayout(1, 2));
        panelUsuario.add(new JLabel("Usuario:"));
        panelUsuario.add(campoUsuario);

        // Panel contraseña
        JPanel panelPasswordPanel = new JPanel(new GridLayout(1, 2));
        panelPasswordPanel.add(new JLabel("Contraseña:"));
        panelPasswordPanel.add(campoPassword);

        // Agregar al panel
        panel.add(panelUsuario);
        panel.add(panelPasswordPanel);
        panel.add(botonContinuar);

        add(panel);
        setVisible(true);
    }
}