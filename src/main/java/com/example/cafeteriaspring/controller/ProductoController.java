package com.example.cafeteriaspring.controller;

import com.example.cafeteriaspring.model.Producto;
import com.example.cafeteriaspring.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Cambiado de @RestController a @Controller para manejar vistas Thymeleaf
@RequestMapping("/api/products") // Cambiado el mapeo para el controlador de producto
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Producto>> listaDeProductos() {
        List<Producto> listaProductos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(listaProductos);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Producto> addProduct(@RequestBody Producto producto) {
        try {
            Producto newProduct = productoService.addProduct(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } catch (Exception e) {
            // Log del error para diagnóstico
            System.out.println("Error al agregar el producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Producto> getProductById(@PathVariable int id) {
        Producto producto = productoService.getProductById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Producto> updateProduct(@PathVariable int id, @RequestBody Producto producto) {
        Producto updatedProduct = productoService.updateProduct(id, producto);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct); // Retorna el producto actualizado
        }
        return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
    }

    //Eliminar un producto por su id
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        productoService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}



