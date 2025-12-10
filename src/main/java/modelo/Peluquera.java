package modelo;

public class Peluquera {
    private int id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private int aniosExperiencia;
    private String especialidad; // Ej: Corte, Color, Peinado
    private String estado;       // Ej: Disponible, Ocupada, Descanso

    public Peluquera() {
    }

    public Peluquera(int id, String nombre, String apellidos, String telefono, int aniosExperiencia, String especialidad, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.aniosExperiencia = aniosExperiencia;
        this.especialidad = especialidad;
        this.estado = estado;
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
    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}