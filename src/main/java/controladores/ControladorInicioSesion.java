package controladores;

import vistas.VistaEspacioTrabajo;
import vistas.VistaInicioSesion;

public class ControladorInicioSesion {
    private VistaInicioSesion vista;

    public ControladorInicioSesion(VistaInicioSesion vista) {
        this.vista = vista;

        this.vista.botonContinuar.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        vista.dispose();
        
        new ControladorEspacioTrabajo(new VistaEspacioTrabajo());
    }
}