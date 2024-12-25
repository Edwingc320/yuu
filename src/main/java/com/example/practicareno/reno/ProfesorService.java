package com.example.practicareno.reno;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



@Service
public class ProfesorService {

    
    // Cadena de conexión a Supabase
    String cadena = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:5432/postgres?user=postgres.uomtenscdtkmstakdjyw&password=DataBase320EGC";

    // Método para establecer la conexión
    public Connection establecerConexion() throws SQLException {
        return DriverManager.getConnection(cadena);
    }

     



    // Método para agregar un nuevo profesor
    public boolean agregarProfesor(String nombre, String clave) {
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = establecerConexion();
        String sql = "INSERT INTO profesor (nombre, clave) VALUES (?, ?)";
        stmt = conn.prepareStatement(sql);

        stmt.setString(1, nombre);
        stmt.setString(2, clave);

        int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0; // Retorna true si se insertó con éxito
    } catch (SQLException e) {
        System.out.println("Error al agregar profesor: " + e.getMessage());
        return false; // Retorna false si ocurrió un error
    } finally {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar los recursos: " + e.getMessage());
        }
    }
}

    // Método para obtener los profesores de la base de datos
public List<Profesor> obtenerProfesores() {
    List<Profesor> profesores = new ArrayList<>();
    String sql = "SELECT * FROM profesor"; // Asume que tienes una tabla "profesor"

    try (Connection conn = establecerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id_profesor"); // Asegúrate de que la columna en la base de datos se llame "id"
            String nombre = rs.getString("nombre");
            String clave = rs.getString("clave");

            // Crear un nuevo objeto Profesor y agregarlo a la lista
            Profesor profesor = new Profesor(id, nombre, clave);
            profesores.add(profesor);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Mostrar detalles del error en consola
    }
    return profesores;
}

}