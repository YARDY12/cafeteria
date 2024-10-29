package com.example.cafeteriaspring.repository;

import com.example.cafeteriaspring.model.Empleado;
import com.example.cafeteriaspring.model.Pedido;
import com.example.cafeteriaspring.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByEmpleado(Empleado empleado);
    List<Pedido> findByProducto(Producto producto);
}