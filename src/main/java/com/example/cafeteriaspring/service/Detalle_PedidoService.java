package com.example.cafeteriaspring.service;

import com.example.cafeteriaspring.model.Detalle_Pedido;
import com.example.cafeteriaspring.model.Pedido;
import com.example.cafeteriaspring.model.Producto;
import com.example.cafeteriaspring.repository.Detalle_PedidoRepository;
import com.example.cafeteriaspring.repository.PedidoRepository;
import com.example.cafeteriaspring.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Detalle_PedidoService {

    private final Detalle_PedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public Detalle_PedidoService(Detalle_PedidoRepository detallePedidoRepository, PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    public List<Detalle_Pedido> getAllDetallePedidos() {
        return detallePedidoRepository.findAll();
    }

    public Optional<Detalle_Pedido> getDetallePedidoById(int id) {
        return detallePedidoRepository.findById(id);
    }

    public Detalle_Pedido addDetallePedido(Detalle_Pedido detallePedido) {
        Pedido pedido = pedidoRepository.findById(detallePedido.getPedido().getId_pedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Producto producto = productoRepository.findById(detallePedido.getProducto().getId_producto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
        return detallePedidoRepository.save(detallePedido);
    }

    public Detalle_Pedido updateDetallePedido(int id, Detalle_Pedido detallePedidoDetails) {
        Detalle_Pedido detallePedido = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de Pedido no encontrado"));
        Pedido pedido = pedidoRepository.findById(detallePedidoDetails.getPedido().getId_pedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Producto producto = productoRepository.findById(detallePedidoDetails.getProducto().getId_producto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        detallePedido.setCantidad(detallePedidoDetails.getCantidad());
        detallePedido.setSubtotal(detallePedidoDetails.getSubtotal());
        detallePedido.setNota_detalle(detallePedidoDetails.getNota_detalle());
        detallePedido.setFecha_detalle(detallePedidoDetails.getFecha_detalle());
        detallePedido.setEstado_detalle(detallePedidoDetails.getEstado_detalle());
        detallePedido.setDescuento(detallePedidoDetails.getDescuento());
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);

        return detallePedidoRepository.save(detallePedido);
    }

    public void deleteDetallePedido(int id) {
        Detalle_Pedido detallePedido = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de Pedido no encontrado"));
        detallePedidoRepository.delete(detallePedido);
    }
}
