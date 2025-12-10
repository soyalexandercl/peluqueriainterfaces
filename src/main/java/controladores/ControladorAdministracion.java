package controladores;

import vistas.VistaAdministracion;
import vistas.VistaClientes;
import vistas.VistaPeluqueras; // Importar nueva vista
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAdministracion implements ActionListener {
    
    private VistaAdministracion vistaPrincipal;
    private VistaClientes vistaClientes;
    private VistaPeluqueras vistaPeluqueras; // Declarar nueva vista

    public ControladorAdministracion(VistaAdministracion vista) {
        this.vistaPrincipal = vista;
        
        // 1. Inicializar paneles
        this.vistaClientes = new VistaClientes();
        this.vistaPeluqueras = new VistaPeluqueras(); // Nuevo panel
        
        // 2. Añadir al CardLayout
        this.vistaPrincipal.panelCentral.add(vistaClientes, "PANEL_CLIENTES");
        this.vistaPrincipal.panelCentral.add(vistaPeluqueras, "PANEL_PELUQUERAS"); // Nuevo panel
        
        // 3. Inicializar Controladores de submódulos
        new ControladorClientes(vistaClientes);
        new ControladorPeluqueras(vistaPeluqueras); // Lógica de peluqueras
        
        // 4. Configurar botones del menú
        this.vistaPrincipal.botonMenuClientes.addActionListener(this);
        this.vistaPrincipal.botonMenuPeluqueras.addActionListener(this);
        
        this.vistaPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.botonMenuClientes) {
            vistaPrincipal.cardLayout.show(vistaPrincipal.panelCentral, "PANEL_CLIENTES");
        } 
        else if (e.getSource() == vistaPrincipal.botonMenuPeluqueras) {
            vistaPrincipal.cardLayout.show(vistaPrincipal.panelCentral, "PANEL_PELUQUERAS");
        }
    }
}