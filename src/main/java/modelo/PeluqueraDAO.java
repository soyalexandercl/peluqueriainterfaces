package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeluqueraDAO {

    // Listar peluqueras uniendo tablas
    public List<Peluquera> listarPeluqueras() {
        List<Peluquera> lista = new ArrayList<>();
        // Hacemos JOIN entre personas y peluqueras
        String sql = "SELECT p.id_persona, p.nombre, p.apellidos, p.telefono, pe.experiencia, pe.especialidad, pe.estado " +
                     "FROM personas p JOIN peluqueras pe ON p.id_persona = pe.id_peluquera";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Peluquera p = new Peluquera();
                p.setId(rs.getInt("id_persona"));
                p.setNombre(rs.getString("nombre"));
                p.setApellidos(rs.getString("apellidos"));
                p.setTelefono(rs.getString("telefono"));
                p.setAniosExperiencia(rs.getInt("experiencia"));
                p.setEspecialidad(rs.getString("especialidad"));
                p.setEstado(rs.getString("estado"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar peluqueras: " + e.getMessage());
        }
        return lista;
    }

    // Insertar (Transacción: Persona -> Peluquera)
    public boolean insertarPeluquera(Peluquera peluquera) {
        String sqlPersona = "INSERT INTO personas (nombre, apellidos, telefono) VALUES (?, ?, ?) RETURNING id_persona";
        String sqlPeluquera = "INSERT INTO peluqueras (id_peluquera, experiencia, especialidad, estado) VALUES (?, ?, ?, ?)";

        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false); // Inicio transacción

            // 1. Insertar Persona
            PreparedStatement psPersona = con.prepareStatement(sqlPersona);
            psPersona.setString(1, peluquera.getNombre());
            psPersona.setString(2, peluquera.getApellidos());
            psPersona.setString(3, peluquera.getTelefono());
            
            ResultSet rs = psPersona.executeQuery();
            int idGenerado = 0;
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

            // 2. Insertar Peluquera
            PreparedStatement psPelu = con.prepareStatement(sqlPeluquera);
            psPelu.setInt(1, idGenerado);
            psPelu.setInt(2, peluquera.getAniosExperiencia());
            psPelu.setString(3, peluquera.getEspecialidad());
            psPelu.setString(4, "Disponible"); // Estado por defecto
            psPelu.executeUpdate();

            con.commit(); // Todo bien
            return true;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println("Error al insertar peluquera: " + e.getMessage());
            return false;
        }
    }

    // Eliminar (Transacción: Peluquera -> Persona)
    // IMPORTANTE: Primero borramos de la tabla hija (peluqueras), luego de la padre (personas)
    public boolean eliminarPeluquera(int id) {
        String sqlPeluquera = "DELETE FROM peluqueras WHERE id_peluquera = ?";
        String sqlPersona = "DELETE FROM personas WHERE id_persona = ?";
        
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false);
            
            // 1. Borrar de tabla hija
            PreparedStatement psPelu = con.prepareStatement(sqlPeluquera);
            psPelu.setInt(1, id);
            psPelu.executeUpdate();
            
            // 2. Borrar de tabla padre
            PreparedStatement psPers = con.prepareStatement(sqlPersona);
            psPers.setInt(1, id);
            psPers.executeUpdate();
            
            con.commit();
            return true;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}
