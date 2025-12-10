package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    public List<Factura> listarFacturas() {
        List<Factura> lista = new ArrayList<>();
        // Hacemos JOIN con Citas y Personas para mostrar quién pagó
        String sql = "SELECT f.id_factura, f.id_cita, f.importe, f.metodo_pago, f.registro, p.nombre, p.apellidos " +
                     "FROM facturas f " +
                     "JOIN citas c ON f.id_cita = c.id_cita " +
                     "JOIN personas p ON c.id_cliente = p.id_persona " +
                     "ORDER BY f.id_factura DESC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Factura f = new Factura();
                f.setId(rs.getInt("id_factura"));
                f.setIdCita(rs.getInt("id_cita"));
                f.setImporte(rs.getDouble("importe"));
                f.setMetodoPago(rs.getString("metodo_pago"));
                f.setFecha(rs.getTimestamp("registro"));
                f.setNombreCliente(rs.getString("nombre") + " " + rs.getString("apellidos"));
                lista.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Error listar facturas: " + e.getMessage());
        }
        return lista;
    }

    // Generar factura a partir de una cita existente
    public boolean generarFactura(int idCita, String metodoPago) {
        Connection con = Conexion.getConexion();
        String sqlVerificar = "SELECT precio FROM citas WHERE id_cita = ?";
        String sqlInsertar = "INSERT INTO facturas (id_cita, importe, metodo_pago) VALUES (?, ?, ?)";
        // Opcional: Actualizar estado de cita a 'Completada' si no lo estaba
        String sqlActualizarCita = "UPDATE citas SET estado = 'Completada' WHERE id_cita = ?";

        try {
            con.setAutoCommit(false); // Transacción

            // 1. Obtener el precio de la cita original
            double importe = 0;
            try (PreparedStatement ps = con.prepareStatement(sqlVerificar)) {
                ps.setInt(1, idCita);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    importe = rs.getDouble("precio");
                } else {
                    return false; // La cita no existe
                }
            }

            // 2. Crear la factura
            try (PreparedStatement ps = con.prepareStatement(sqlInsertar)) {
                ps.setInt(1, idCita);
                ps.setDouble(2, importe);
                ps.setString(3, metodoPago);
                ps.executeUpdate();
            }
            
            // 3. Asegurar que la cita conste como completada
            try (PreparedStatement ps = con.prepareStatement(sqlActualizarCita)) {
                ps.setInt(1, idCita);
                ps.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println("Error generando factura: " + e.getMessage());
            return false;
        }
    }
}