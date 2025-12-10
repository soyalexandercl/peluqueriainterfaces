package controladores;

import vistas.VistaEspacioTrabajo;

public class ControladorEspacioTrabajo {
    private VistaEspacioTrabajo vista;

    public ControladorEspacioTrabajo(VistaEspacioTrabajo vista) {
        this.vista = vista;
        
        this.vista.botonAdministrar.addActionListener(e -> controlarVentana("administrarPeluqueria"));
        this.vista.botonSimular.addActionListener(e -> controlarVentana("simularPeluqueria"));
    }

    private void controlarVentana(String modulo) {
        vista.dispose();
        
        new ControladorVentanaPrincipal(modulo);
    }
}