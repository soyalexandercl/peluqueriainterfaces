package modelo;

import java.sql.Time;

public class Servicio {
    private int id;
    private String nombre;
    private double precio;
    private Time duracion;
    private int idProducto;
    private String nombreProducto;

    public Servicio() {}

    public Servicio(int id, String nombre, double precio, Time duracion, int idProducto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.duracion = duracion;
        this.idProducto = idProducto;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public Time getDuracion() { return duracion; }
    public void setDuracion(Time duracion) { this.duracion = duracion; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    // NUEVO
    @Override
    public String toString() {
        return nombre + " (" + precio + "€ - " + duracion + ")";
    }
}