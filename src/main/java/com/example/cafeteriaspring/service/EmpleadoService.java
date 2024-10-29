package com.example.cafeteriaspring.service;


import com.example.cafeteriaspring.model.Empleado;
import com.example.cafeteriaspring.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {


    @Autowired
    private EmpleadoRepository empleadoRepository;


    // Método para obtener todos los empleados
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    // Método para obtener un empleado por su ID
    public Empleado getEmpleadoById(int id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    // Método para agregar un nuevo empleado
    public Empleado addEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Método para actualizar un empleado existente
    public Empleado updateEmpleado(int id, Empleado empleadoDetails) {
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        if (empleado != null) {
            empleado.setNombre(empleadoDetails.getNombre());
            empleado.setApellido(empleadoDetails.getApellido());
            empleado.setGenero(empleadoDetails.getGenero());
            empleado.setEmail(empleadoDetails.getEmail());
            empleado.setTelefono(empleadoDetails.getTelefono());
            empleado.setPuesto(empleadoDetails.getPuesto());
            empleado.setSalario(empleadoDetails.getSalario());
            return empleadoRepository.save(empleado);
        }
        return null;
    }

    // Método para eliminar un empleado
    public void deleteEmpleado(int id) {
        empleadoRepository.deleteById(id);
    }
}
