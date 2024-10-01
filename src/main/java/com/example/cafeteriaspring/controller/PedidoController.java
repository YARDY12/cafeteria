package com.example.cafeteriaspring.controller;

import com.example.cafeteriaspring.model.Pedido;
import com.example.cafeteriaspring.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Pedido>> listaDePedidos() {
        List<Pedido> listaPedidos = pedidoService.getAllOrders();
        return ResponseEntity.ok(listaPedidos);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Pedido> addOrder(@RequestBody Pedido pedido) {
        try {
            Pedido newOrder = pedidoService.addOrder(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
        } catch (Exception e) {
            // Log del error para diagnóstico
            System.out.println("Error al agregar el pedido: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Pedido> getOrderById(@PathVariable int id) {
        Pedido pedido = pedidoService.getOrderById(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Pedido> updateOrder(@PathVariable int id, @RequestBody Pedido pedido) {
        Pedido updatedOrder = pedidoService.updateOrder(id, pedido);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder); // Retorna el pedido actualizado
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
    }

    //Eliminar un pedido por su id
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteOrder(@PathVariable int id){
        pedidoService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

