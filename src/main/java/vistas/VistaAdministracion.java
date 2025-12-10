package controladores;

import vistas.VistaAdministracion;
import vistas.VistaCitas; // Importar
import vistas.VistaClientes;
import vistas.VistaPeluqueras;
import vistas.VistaProductos;
import vistas.VistaServicios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAdministracion implements ActionListener {
    
    private VistaAdministracion vistaPrincipal;
    
    // Vistas secundarias
    private VistaClientes vistaClientes;
    private VistaPeluqueras vistaPeluqueras;
    private VistaProductos vistaProductos;
    private VistaServicios vistaServicios;
    private VistaCitas vistaCitas; // Declarar

    public ControladorAdministracion(VistaAdministracion vista) {
        this.vistaPrincipal = vista;
        
        // 1. Instancias
        this.vistaClientes = new VistaClientes();
        this.vistaPeluqueras = new VistaPeluqueras();
        this.vistaProductos = new VistaProductos();
        this.vistaServicios = new VistaServicios();
        this.vistaCitas = new VistaCitas(); // Instancia
        
        // 2. CardLayout
        this.vistaPrincipal.panelCentral.add(vistaClientes, "PANEL_CLIENTES");
        this.vistaPrincipal.panelCentral.add(vistaPeluqueras, "PANEL_PELUQUERAS");
        this.vistaPrincipal.panelCentral.add(vistaProductos, "PANEL_PRODUCTOS");
        this.vistaPrincipal.panelCentral.add(vistaServicios, "PANEL_SERVICIOS");
        this.vistaPrincipal.panelCentral.add(vistaCitas, "PANEL_CITAS"); // Añadir
        
        // 3. Controladores
        new ControladorClientes(vistaClientes);
        new ControladorPeluqueras(vistaPeluqueras);
        new ControladorProductos(vistaProductos);
        new ControladorServicios(vistaServicios);
        new ControladorCitas(vistaCitas); // Iniciar lógica
        
        // 4. Listeners Menú
        this.vistaPrincipal.botonMenuClientes.addActionListener(this);
        this.vistaPrincipal.botonMenuPeluqueras.addActionListener(this);
        this.vistaPrincipal.botonMenuProductos.addActionListener(this);
        this.vistaPrincipal.botonMenuServicios.addActionListener(this);
        this.vistaPrincipal.botonMenuCitas.addActionListener(this); // Activar botón
        
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
        else if (e.getSource() == vistaPrincipal.botonMenuServicios) {
            vistaPrincipal.cardLayout.show(vistaPrincipal.panelCentral, "PANEL_SERVICIOS");
        }
        else if (e.getSource() == vistaPrincipal.botonMenuCitas) { // Navegación
            vistaPrincipal.cardLayout.show(vistaPrincipal.panelCentral, "PANEL_CITAS");
        }
    }
}