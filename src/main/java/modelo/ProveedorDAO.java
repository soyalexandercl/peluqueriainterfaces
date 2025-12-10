package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    
    // Obtener todos los proveedores
    public List<Proveedor> listarProveedores() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Proveedor(rs.getInt("id_proveedor"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.out.println("Error listar proveedores: " + e.getMessage());
        }
        return lista;
    }

    // Método auxiliar inteligente: Si no hay proveedores, crea uno por defecto para que no falle la inserción de productos
    public int obtenerOInsertarProveedorDefecto() {
        List<Proveedor> lista = listarProveedores();
        if (!lista.isEmpty()) {
            return lista.get(0).getId(); // Devuelve el primero que encuentre
        }
        // Si no hay ninguno, creamos uno automático
        String sql = "INSERT INTO proveedores (nombre) VALUES ('Proveedor General') RETURNING id_proveedor";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
             System.out.println("Error creando proveedor defecto: " + e.getMessage());
        }
        return -1; // Error
    }
}