package com.example.cafeteriaspring.service;

import com.example.cafeteriaspring.model.Detalle_Pedido;
import com.example.cafeteriaspring.repository.Detalle_PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Detalle_PedidoService {

    @Autowired
    private Detalle_PedidoRepository detallePedidoRepository;

    // Get all order details
    public List<Detalle_Pedido> getAllOrderDetails() {
        return detallePedidoRepository.findAll();
    }

    // Get order detail by ID
    public Detalle_Pedido getOrderDetailById(int id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    // Add new order detail
    public Detalle_Pedido addOrderDetail(Detalle_Pedido orderDetail) {
        return detallePedidoRepository.save(orderDetail);
    }

    // Update order detail by ID
    public Detalle_Pedido updateOrderDetail(int id, Detalle_Pedido orderDetail) {
        Detalle_Pedido existingOrderDetail = detallePedidoRepository.findById(id).orElse(null);
        if (existingOrderDetail != null) {
            existingOrderDetail.setCantidad(orderDetail.getCantidad());
            existingOrderDetail.setSubtotal(orderDetail.getSubtotal());
            existingOrderDetail.setNota_detalle(orderDetail.getNota_detalle());
            existingOrderDetail.setFecha_detalle(orderDetail.getFecha_detalle());
            existingOrderDetail.setEstado_detalle(orderDetail.getEstado_detalle());
            existingOrderDetail.setDescuento(orderDetail.getDescuento());
            return detallePedidoRepository.save(existingOrderDetail);
        }
        return null;
    }

    // Delete order detail by ID
    public void deleteOrderDetail(int id) {
        detallePedidoRepository.deleteById(id);
    }
}
