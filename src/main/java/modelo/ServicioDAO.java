package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {

    public List<Servicio> listarServicios() {
        List<Servicio> lista = new ArrayList<>();
        // JOIN para ver el nombre del producto asociado
        String sql = "SELECT s.id_servicio, s.nombre, s.precio, s.duracion, s.id_producto, p.nombre AS nombre_producto " +
                     "FROM servicios s JOIN productos p ON s.id_producto = p.id_producto";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Servicio s = new Servicio();
                s.setId(rs.getInt("id_servicio"));
                s.setNombre(rs.getString("nombre"));
                s.setPrecio(rs.getDouble("precio"));
                s.setDuracion(rs.getTime("duracion"));
                s.setIdProducto(rs.getInt("id_producto"));
                s.setNombreProducto(rs.getString("nombre_producto"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error listar servicios: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertarServicio(Servicio s) {
        String sql = "INSERT INTO servicios (nombre, precio, duracion, id_producto) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getNombre());
            ps.setDouble(2, s.getPrecio());
            ps.setTime(3, s.getDuracion());
            ps.setInt(4, s.getIdProducto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error insertar servicio: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarServicio(int id) {
        String sql = "DELETE FROM servicios WHERE id_servicio = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error eliminar servicio: " + e.getMessage());
            return false;
        }
    }
}