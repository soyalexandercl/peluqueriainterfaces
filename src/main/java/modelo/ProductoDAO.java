package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        // JOIN para obtener el nombre del proveedor (tabla proveedores) cruzando con productos
        String sql = "SELECT p.id_producto, p.nombre, p.stock, p.stock_maximo, p.precio, p.id_proveedor, pro.nombre AS nombre_proveedor " +
                     "FROM productos p JOIN proveedores pro ON p.id_proveedor = pro.id_proveedor";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setStock(rs.getInt("stock"));
                p.setStockMaximo(rs.getInt("stock_maximo"));
                p.setPrecio(rs.getDouble("precio"));
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setNombreProveedor(rs.getString("nombre_proveedor")); // Dato extraído del JOIN
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error listar productos: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertarProducto(Producto p) {
        String sql = "INSERT INTO productos (id_proveedor, nombre, stock, stock_maximo, precio) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getIdProveedor());
            ps.setString(2, p.getNombre());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getStockMaximo());
            ps.setDouble(5, p.getPrecio());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error insertar producto: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error eliminar producto: " + e.getMessage());
            return false;
        }
    }
}