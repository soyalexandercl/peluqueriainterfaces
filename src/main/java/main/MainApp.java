package main;

import controladores.ControladorInicioSesion;
import vistas.VistaInicioSesion;

public class MainApp {
    public static void main(String[] args) {
        new ControladorInicioSesion(new VistaInicioSesion());
    }
}