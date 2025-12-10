package modelo;

public class Cliente {
    private int id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private boolean esVip;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con datos
    public Cliente(int id, String nombre, String apellidos, String telefono, boolean esVip) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.esVip = esVip;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public boolean isEsVip() { return esVip; }
    public void setEsVip(boolean esVip) { this.esVip = esVip; }
}