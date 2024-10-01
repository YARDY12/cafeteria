package com.example.cafeteriaspring.controller;

import com.example.cafeteriaspring.model.Detalle_Pedido;
import com.example.cafeteriaspring.service.Detalle_PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-pedidos")
public class Detalle_PedidoController {

    @Autowired
    private Detalle_PedidoService detallePedidoService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Detalle_Pedido>> listaDeDetalles() {
        List<Detalle_Pedido> listaDetalles = detallePedidoService.getAllOrderDetails();
        return ResponseEntity.ok(listaDetalles);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Detalle_Pedido> addOrderDetail(@RequestBody Detalle_Pedido detallePedido) {
        try {
            Detalle_Pedido newOrderDetail = detallePedidoService.addOrderDetail(detallePedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(newOrderDetail);
        } catch (Exception e) {
            // Log del error para diagnóstico
            System.out.println("Error al agregar el detalle de pedido: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Detalle_Pedido> getOrderDetailById(@PathVariable int id) {
        Detalle_Pedido detallePedido = detallePedidoService.getOrderDetailById(id);
        if (detallePedido != null) {
            return ResponseEntity.ok(detallePedido);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Detalle_Pedido> updateOrderDetail(@PathVariable int id, @RequestBody Detalle_Pedido detallePedido) {
        Detalle_Pedido updatedOrderDetail = detallePedidoService.updateOrderDetail(id, detallePedido);
        if (updatedOrderDetail != null) {
            return ResponseEntity.ok(updatedOrderDetail); // Retorna el detalle de pedido actualizado
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
    }

    //Eliminar un detalle de pedido por su id
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable int id){
        detallePedidoService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
}

