package modelo;

public class Producto {
    private int id;
    private int idProveedor;        // La clave foránea (FK)
    private String nombreProveedor; // Campo auxiliar para mostrar en la tabla (no está en la tabla productos, viene del JOIN)
    private String nombre;
    private int stock;
    private int stockMaximo;
    private double precio;

    public Producto() {}

    public Producto(int id, int idProveedor, String nombre, int stock, int stockMaximo, double precio) {
        this.id = id;
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.stock = stock;
        this.stockMaximo = stockMaximo;
        this.precio = precio;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    public String getNombreProveedor() { return nombreProveedor; }
    public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public int getStockMaximo() { return stockMaximo; }
    public void setStockMaximo(int stockMaximo) { this.stockMaximo = stockMaximo; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}