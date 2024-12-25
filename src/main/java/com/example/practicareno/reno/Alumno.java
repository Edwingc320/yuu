package com.example.practicareno.reno;

public class Alumno {
    private int id; // Suponiendo que tienes un ID autoincremental en la base de datos
    private String nombre;
    private String matricula;

    // Constructor
    public Alumno(int id, String nombre, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
