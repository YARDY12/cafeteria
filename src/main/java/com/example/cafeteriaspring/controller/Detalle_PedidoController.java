package com.example.cafeteriaspring.controller;

import com.example.cafeteriaspring.model.Detalle_Pedido;
import com.example.cafeteriaspring.service.Detalle_PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalle-pedidos")
public class Detalle_PedidoController {

    private final Detalle_PedidoService detallePedidoService;

    @Autowired
    public Detalle_PedidoController(Detalle_PedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Detalle_Pedido>> getAllDetallePedidos() {
        List<Detalle_Pedido> detallePedidos = detallePedidoService.getAllDetallePedidos();
        return ResponseEntity.ok(detallePedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detalle_Pedido> getDetallePedidoById(@PathVariable int id) {
        Optional<Detalle_Pedido> detallePedido = detallePedidoService.getDetallePedidoById(id);
        return detallePedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Detalle_Pedido> addDetallePedido(@RequestBody Detalle_Pedido detallePedido) {
        Detalle_Pedido newDetallePedido = detallePedidoService.addDetallePedido(detallePedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDetallePedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detalle_Pedido> updateDetallePedido(@PathVariable int id, @RequestBody Detalle_Pedido detallePedidoDetails) {
        try {
            Detalle_Pedido updatedDetallePedido = detallePedidoService.updateDetallePedido(id, detallePedidoDetails);
            return ResponseEntity.ok(updatedDetallePedido);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetallePedido(@PathVariable int id) {
        try {
            detallePedidoService.deleteDetallePedido(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}



