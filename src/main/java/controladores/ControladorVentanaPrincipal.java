package controladores;

import vistas.VistaAdministracion;
import vistas.VistaSimulacion;

public class ControladorVentanaPrincipal {
    public ControladorVentanaPrincipal(String modulo) {
        switch(modulo) {
            case "administrarPeluqueria":
                new ControladorAdministracion(new VistaAdministracion());
                break;
            case "simularPeluqueria":
                new ControladorSimulacion(new VistaSimulacion());
                break;
        }
    }
}