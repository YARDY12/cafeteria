package com.example.cafeteriaspring.controller;

import com.example.cafeteriaspring.model.Producto;
import com.example.cafeteriaspring.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener la lista de productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    // Obtener un producto por su id
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable int id) {
        Producto producto = productoService.getProductoById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }

    // Registrar un nuevo producto
    @PostMapping
    public Producto addProducto(@RequestBody Producto producto) {
        return productoService.addProducto(producto);
    }

    // Actualizar un producto por su ID
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
        Producto updatedProducto = productoService.updateProducto(id, producto);
        if (updatedProducto != null) {
            return ResponseEntity.ok(updatedProducto);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable int id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}


