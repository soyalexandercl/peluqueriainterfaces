package controladores;

import vistas.VistaAdministracion;
import vistas.VistaClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ControladorAdministracion implements ActionListener {
    
    private VistaAdministracion vistaPrincipal;
    
    // Instancias de las vistas secundarias (Paneles)
    private VistaClientes vistaClientes;
    
    public ControladorAdministracion(VistaAdministracion vista) {
        this.vistaPrincipal = vista;
        
        // 1. Inicializar las vistas secundarias (Los paneles internos)
        this.vistaClientes = new VistaClientes();
        // this.vistaPeluqueras = new VistaPeluqueras(); // Cuando la crees...
        
        // 2. Añadir los paneles al "Mazo de cartas" (CardLayout)
        // El String "PANEL_CLIENTES" es la clave para llamar a este panel luego
        this.vistaPrincipal.panelCentral.add(vistaClientes, "PANEL_CLIENTES");
        // this.vistaPrincipal.panelCentral.add(vistaPeluqueras, "PANEL_PELUQUERAS");
        
        // 3. Inicializar los Controladores de cada módulo
        // Esto le da vida a la tabla y botones de la vista clientes
        new ControladorClientes(vistaClientes); 
        
        // 4. Configurar los botones del menú lateral
        this.vistaPrincipal.botonMenuClientes.addActionListener(this);
        this.vistaPrincipal.botonMenuPeluqueras.addActionListener(this);
        // ... añadir los demás listeners
        
        // 5. Mostrar la ventana finalmente
        this.vistaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Detectar qué botón del menú se pulsó y cambiar el panel central
        if (e.getSource() == vistaPrincipal.botonMenuClientes) {
            vistaPrincipal.cardLayout.show(vistaPrincipal.panelCentral, "PANEL_CLIENTES");
        } 
        else if (e.getSource() == vistaPrincipal.botonMenuPeluqueras) {
            // vistaPrincipal.cardLayout.show(vistaPrincipal.panelCentral, "PANEL_PELUQUERAS");
        }
    }
}