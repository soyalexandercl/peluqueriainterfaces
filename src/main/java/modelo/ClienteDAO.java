package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Data Access Object para Clientes (Maneja las consultas SQL)
public class ClienteDAO {
    
    // Listar todos los clientes (Uniendo tabla personas y clientes)
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT p.id_persona, p.nombre, p.apellidos, p.telefono, c.tipo_cliente " +
                     "FROM personas p JOIN clientes c ON p.id_persona = c.id_cliente";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id_persona"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setEsVip(rs.getBoolean("tipo_cliente"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // Insertar un nuevo cliente (Requiere transacción: primero Persona, luego Cliente)
    public boolean insertarCliente(Cliente cliente) {
        String sqlPersona = "INSERT INTO personas (nombre, apellidos, telefono) VALUES (?, ?, ?) RETURNING id_persona";
        String sqlCliente = "INSERT INTO clientes (id_cliente, tipo_cliente) VALUES (?, ?)";
        
        Connection con = Conexion.getConexion();
        try {
            con.setAutoCommit(false); // Iniciamos transacción manual

            // 1. Insertar en Personas
            PreparedStatement psPersona = con.prepareStatement(sqlPersona);
            psPersona.setString(1, cliente.getNombre());
            psPersona.setString(2, cliente.getApellidos());
            psPersona.setString(3, cliente.getTelefono());
            
            ResultSet rs = psPersona.executeQuery();
            int idGenerado = 0;
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

            // 2. Insertar en Clientes usando el ID generado
            PreparedStatement psCliente = con.prepareStatement(sqlCliente);
            psCliente.setInt(1, idGenerado);
            psCliente.setBoolean(2, cliente.isEsVip());
            psCliente.executeUpdate();

            con.commit(); // Confirmamos cambios
            return true;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {} // Deshacer si hay error
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }
    
    // Aquí añadirías los métodos eliminarCliente y actualizarCliente siguiendo la misma lógica...
}
