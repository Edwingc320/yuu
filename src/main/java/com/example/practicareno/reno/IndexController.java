package com.example.practicareno.reno;




import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {


    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ProfesorService profesorService;


    @GetMapping("/")
    public String showIndex() {
        return "index"; // Retorna la vista "index.html"
    }

    @GetMapping("/indexADDAlumno")
    public String showAddAlumno() {
        return "indexADDAlumno"; // Retorna la vista "indexADDAlumno.html"
    }


    @GetMapping("/indexADDProfesores")
    public String showAddProfesor() {
        return "indexADDProfesores"; // Retorna la vista "indexADDAlumno.html"
    }
    


    @PostMapping("/ADDProfesor")
    public ResponseEntity<String> agregarProfesor(@RequestParam String nombre, @RequestParam String clave) {
        boolean agregado = profesorService.agregarProfesor(nombre, clave);
    
        if (agregado) {
            return ResponseEntity.ok("Profesor agregado exitosamente.");
        } else {
            return ResponseEntity.status(500).body("Error al agregar profesor.");
        }
    }

    @GetMapping("/profesores")
    public List<Profesor> obtenerProfesores() throws SQLException {
        return profesorService.obtenerProfesores();
    }

    @PostMapping("/ADDAlumno")
    public ResponseEntity<String> agregarAlumno(@RequestParam String nombre, @RequestParam String matricula) {
    alumnoService.agregarAlumno(nombre, matricula);
    return ResponseEntity.ok("Alumno agregado exitosamente");
    }

   
    @GetMapping("/alumnos")
    public List<Alumno> obtenerAlumnos() {
        return alumnoService.obtenerAlumnos();
    }

    

   
}
