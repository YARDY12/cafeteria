package com.example.cafeteriaspring.service;

import com.example.cafeteriaspring.model.Producto;
import com.example.cafeteriaspring.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Get product by ID
    public Producto getProductById(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Add new product
    public Producto addProduct(Producto product) {
        return productoRepository.save(product);
    }

    // Update product by ID
    public Producto updateProduct(int id, Producto product) {
        Producto existingProduct = productoRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setNombre_producto(product.getNombre_producto());
            existingProduct.setDescripcion(product.getDescripcion());
            existingProduct.setPrecio_unitario(product.getPrecio_unitario());
            existingProduct.setCategoria(product.getCategoria());
            existingProduct.setStock_actual(product.getStock_actual());
            existingProduct.setUnidad_medida(product.getUnidad_medida());
            existingProduct.setEstado_producto(product.getEstado_producto());
            return productoRepository.save(existingProduct);
        }
        return null;
    }

    // Delete product by ID
    public void deleteProduct(int id) {
        productoRepository.deleteById(id);
    }

}
