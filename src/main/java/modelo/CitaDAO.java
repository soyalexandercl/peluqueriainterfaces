package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    // Listar citas con el nombre del cliente
    public List<Cita> listarCitas() {
        List<Cita> lista = new ArrayList<>();
        // JOIN entre citas y personas (a través de clientes) para ver nombres
        String sql = "SELECT c.id_cita, c.id_cliente, p.nombre, p.apellidos, c.precio, c.estado, c.registro " +
                     "FROM citas c JOIN personas p ON c.id_cliente = p.id_persona " +
                     "ORDER BY c.id_cita DESC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("id_cita"));
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombreCliente(rs.getString("nombre") + " " + rs.getString("apellidos"));
                c.setPrecioTotal(rs.getDouble("precio"));
                c.setEstado(rs.getString("estado"));
                c.setFecha(rs.getTimestamp("registro"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar citas: " + e.getMessage());
        }
        return lista;
    }

    // Método transaccional: Crea Cita y su detalle en Servicios_Citas
    public boolean crearCita(int idCliente, int idServicio, int idPeluquera) {
        Connection con = Conexion.getConexion();
        
        // 1. Necesitamos saber el precio del servicio primero
        String sqlPrecio = "SELECT precio FROM servicios WHERE id_servicio = ?";
        String sqlInsertCita = "INSERT INTO citas (id_cliente, precio, estado) VALUES (?, ?, 'Programada') RETURNING id_cita";
        String sqlInsertDetalle = "INSERT INTO servicios_citas (id_cita, id_servicio, id_peluquera) VALUES (?, ?, ?)";

        try {
            con.setAutoCommit(false); // INICIO TRANSACCIÓN

            // Paso A: Obtener precio
            double precio = 0;
            try (PreparedStatement psPrecio = con.prepareStatement(sqlPrecio)) {
                psPrecio.setInt(1, idServicio);
                ResultSet rs = psPrecio.executeQuery();
                if (rs.next()) {
                    precio = rs.getDouble("precio");
                } else {
                    throw new SQLException("Servicio no encontrado");
                }
            }

            // Paso B: Insertar Cita (Cabecera)
            int idCitaGenerada = 0;
            try (PreparedStatement psCita = con.prepareStatement(sqlInsertCita)) {
                psCita.setInt(1, idCliente);
                psCita.setDouble(2, precio);
                ResultSet rs = psCita.executeQuery();
                if (rs.next()) {
                    idCitaGenerada = rs.getInt(1);
                }
            }

            // Paso C: Insertar Detalle (Relación N:M)
            try (PreparedStatement psDetalle = con.prepareStatement(sqlInsertDetalle)) {
                psDetalle.setInt(1, idCitaGenerada);
                psDetalle.setInt(2, idServicio);
                psDetalle.setInt(3, idPeluquera);
                psDetalle.executeUpdate();
            }

            con.commit(); // FIN TRANSACCIÓN EXITOSA
            return true;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {} // Deshacer cambios si falla
            System.out.println("Error creando cita: " + e.getMessage());
            return false;
        }
    }

    // Cambiar estado (para cancelar o completar)
    public boolean actualizarEstado(int idCita, String nuevoEstado) {
        String sql = "UPDATE citas SET estado = ? WHERE id_cita = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idCita);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error actualizando estado: " + e.getMessage());
            return false;
        }
    }
}