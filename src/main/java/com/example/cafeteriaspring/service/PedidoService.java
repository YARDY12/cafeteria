package com.example.cafeteriaspring.service;

import com.example.cafeteriaspring.model.Pedido;
import com.example.cafeteriaspring.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // Get all orders
    public List<Pedido> getAllOrders() {
        return pedidoRepository.findAll();
    }

    // Get order by ID
    public Pedido getOrderById(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    // Add new order
    public Pedido addOrder(Pedido order) {
        return pedidoRepository.save(order);
    }

    // Update order by ID
    public Pedido updateOrder(int id, Pedido order) {
        Pedido existingOrder = pedidoRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            existingOrder.setNum_mesa(order.getNum_mesa());
            existingOrder.setNom_cliente(order.getNom_cliente());
            existingOrder.setFecha(order.getFecha());
            existingOrder.setMetodo_pago(order.getMetodo_pago());
            existingOrder.setEstado(order.getEstado());
            existingOrder.setTotal(order.getTotal());
            existingOrder.setNota_especial(order.getNota_especial());
            existingOrder.setNom_mesero(order.getNom_mesero());
            return pedidoRepository.save(existingOrder);
        }
        return null;
    }

    // Delete order by ID
    public void deleteOrder(int id) {
        pedidoRepository.deleteById(id);
    }

}
