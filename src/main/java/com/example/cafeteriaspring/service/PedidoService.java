package com.example.cafeteriaspring.service;

import com.example.cafeteriaspring.model.Empleado;
import com.example.cafeteriaspring.model.Pedido;
import com.example.cafeteriaspring.model.Producto;
import com.example.cafeteriaspring.repository.EmpleadoRepository;
import com.example.cafeteriaspring.repository.PedidoRepository;
import com.example.cafeteriaspring.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, EmpleadoRepository empleadoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.empleadoRepository = empleadoRepository;
        this.productoRepository = productoRepository;
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedidoById(int id) {
        return pedidoRepository.findById(id);
    }

    public Pedido addPedido(Pedido pedido) {
        Empleado empleado = empleadoRepository.findById(pedido.getEmpleado().getId_empleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        Producto producto = productoRepository.findById(pedido.getProducto().getId_producto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        pedido.setEmpleado(empleado);
        pedido.setProducto(producto);
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(int id, Pedido pedidoDetails) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Empleado empleado = empleadoRepository.findById(pedidoDetails.getEmpleado().getId_empleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        Producto producto = productoRepository.findById(pedidoDetails.getProducto().getId_producto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        pedido.setNum_mesa(pedidoDetails.getNum_mesa());
        pedido.setNom_cliente(pedidoDetails.getNom_cliente());
        pedido.setNota_especial(pedidoDetails.getNota_especial());
        pedido.setTotal(pedidoDetails.getTotal());
        pedido.setEmpleado(empleado);
        pedido.setProducto(producto);

        return pedidoRepository.save(pedido);
    }

    public void deletePedido(int id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedidoRepository.delete(pedido);
    }

}
