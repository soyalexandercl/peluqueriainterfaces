package modelo;

import java.sql.Timestamp;

public class Cita {
    private int id;
    private int idCliente;
    private String nombreCliente; // Para mostrar
    private double precioTotal;
    private String estado; // Programada, En curso, Completada, Cancelada
    private Timestamp fecha;

    public Cita() {}

    public Cita(int id, int idCliente, String nombreCliente, double precioTotal, String estado, Timestamp fecha) {
        this.id = id;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
}