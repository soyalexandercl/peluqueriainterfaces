package modelo;

import java.sql.Timestamp;

public class Factura {
    private int id;
    private int idCita;
    private String nombreCliente; // Dato útil para mostrar
    private double importe;
    private String metodoPago; // Efectivo, Tarjeta, etc.
    private Timestamp fecha;

    public Factura() {}

    public Factura(int id, int idCita, String nombreCliente, double importe, String metodoPago, Timestamp fecha) {
        this.id = id;
        this.idCita = idCita;
        this.nombreCliente = nombreCliente;
        this.importe = importe;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public double getImporte() { return importe; }
    public void setImporte(double importe) { this.importe = importe; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
}