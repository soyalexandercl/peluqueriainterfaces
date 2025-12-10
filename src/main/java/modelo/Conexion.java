package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Clase para gestionar la conexión a la base de datos PostgreSQL
public class Conexion {
    // Ajusta estos datos a tu configuración local de PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/peluqueria_db";
    private static final String USUARIO = "postgres";
    private static final String PASSWORD = "admin"; // Tu contraseña aquí

    // Método estático para obtener la conexión
    public static Connection getConexion() {
        try {
            Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            return con;
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
}