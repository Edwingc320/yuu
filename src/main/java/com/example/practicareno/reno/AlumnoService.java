package com.example.practicareno.reno;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnoService {

    // Cadena de conexión a Supabase
    String cadena = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:5432/postgres?user=postgres.uomtenscdtkmstakdjyw&password=DataBase320EGC";

    // Método para establecer la conexión
    public Connection establecerConexion() throws SQLException {
        return DriverManager.getConnection(cadena);
    }



    public void agregarAlumno(String nombre, String matricula) {
        String sql = "INSERT INTO alumno (nombre, matricula) VALUES (?, ?)";
        try (Connection conn = establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al agregar alumno: " + e.getMessage(), e);
        }
    }
    


    // Método para obtener los alumnos de la base de datos
    public List<Alumno> obtenerAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno"; // Asume que tienes una tabla "alumno"

        try (Connection conn = establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id"); // Asegúrate de que la columna en la base de datos se llame "id"
                String nombre = rs.getString("nombre");
                String matricula = rs.getString("matricula");

                // Crear un nuevo objeto Alumno y agregarlo a la lista
                Alumno alumno = new Alumno(id, nombre, matricula);
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Mostrar detalles del error en consola
        }
        return alumnos;
    }
}
