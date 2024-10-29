package com.example.cafeteriaspring.controller;

import com.example.cafeteriaspring.model.Empleado;
import com.example.cafeteriaspring.repository.EmpleadoRepository;
import com.example.cafeteriaspring.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Validated // Permite la validación de los parámetros del controlador
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // Obtener la lista de empleados
    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    // Obtener un empleado por su id
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable int id) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        return ResponseEntity.notFound().build();
    }

    // Registrar un nuevo empleado
    @PostMapping
    public Empleado addEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.addEmpleado(empleado);
    }

    // Actualizar un empleado por su ID
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable int id, @RequestBody Empleado empleado) {
        Empleado updatedEmpleado = empleadoService.updateEmpleado(id, empleado);
        if (updatedEmpleado != null) {
            return ResponseEntity.ok(updatedEmpleado);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable int id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}

