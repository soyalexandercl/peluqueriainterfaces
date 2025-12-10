package controladores;

import vistas.VistaAdministracion;
import vistas.VistaClientes;
import vistas.VistaPeluqueras;
import vistas.VistaProductos;
import vistas.VistaServicios; // Importar
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAdministracion implements ActionListener {
    
    private VistaAdministracion vistaPrincipal;
    
    // Vistas secundarias
    private VistaClientes vistaClientes;
    private VistaPeluqueras vistaPeluqueras;
    private VistaProductos vistaProductos;
    private VistaServicios vistaServicios; // Nueva vista

    public ControladorAdministracion(VistaAdministracion vista) {
        this.vistaPrincipal = vista;
        
        // 1. Instancias
        this.vistaClientes = new VistaClientes();
        this.vistaPeluqueras = new VistaPeluqueras();
        this.vistaProductos = new VistaProductos();
        this.vistaServicios = new VistaServicios(); // Instancia
        
        // 2. CardLayout
        this.vistaPrincipal.panelCentral.add(vistaClientes, "PANEL_CLIENTES");
        this.vistaPrincipal.panelCentral.add(vistaPeluqueras, "PANEL_PELUQUERAS");
        this.vistaPrincipal.panelCentral.add(vistaProductos, "PANEL_PRODUCTOS");
        this.vistaPrincipal.panelCentral.add(vistaServicios, "PANEL_SERVICIOS"); // Añadir
        
        // 3. Controladores
        new ControladorClientes(vistaClientes);
        new ControladorPeluqueras(vistaPeluqueras);
        new ControladorProductos(vistaProductos);
        new ControladorServicios(vistaServicios); // Iniciar controlador
        
        // 4. Listeners Menú
        this.vistaPrincipal.botonMenuClientes.addActionListener(this);
        this.vistaPrincipal.botonMenuPeluqueras.addActionListener(this);
        this.vistaPrincipal.botonMenuProductos.addActionListener(this);
        this.vistaPrincipal.botonMenuServicios.addActionListener(this); // Listener
        
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
        else if (e.getSource() == vistaPrincipal.botonMenuProductos) {
            vistaPrincipal.cardLayout.show(vistaPrincipal.panelCentral, "PANEL_PRODUCTOS");
        }
        else if (e.getSource() == vistaPrincipal.botonMenuServicios) { // Navegación
            vistaPrincipal.cardLayout.show(vistaPrincipal.panelCentral, "PANEL_SERVICIOS");
        }
    }
}