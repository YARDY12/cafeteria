package com.example.cafeteriaspring.controller;

import com.example.cafeteriaspring.model.Empleado;
import com.example.cafeteriaspring.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@Validated
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // Permitir acceso a ADMIN y USER para obtener todos los empleados
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable int id) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Empleado> addEmpleado(@Valid @RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.addEmpleado(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable int id, @Valid @RequestBody Empleado empleado) {
        Empleado updatedEmpleado = empleadoService.updateEmpleado(id, empleado);
        if (updatedEmpleado != null) {
            return ResponseEntity.ok(updatedEmpleado);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable int id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}