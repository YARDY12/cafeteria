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


    // Método para obtener todos los productos
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Método para obtener un producto por su ID
    public Producto getProductoById(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Método para agregar un nuevo producto
    public Producto addProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Método para actualizar un producto existente
    public Producto updateProducto(int id, Producto productoDetails) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.setNombre(productoDetails.getNombre());
            producto.setDescripcion(productoDetails.getDescripcion());
            producto.setPrecio(productoDetails.getPrecio());
            producto.setCategoria(productoDetails.getCategoria());
            producto.setEstado_producto(productoDetails.getEstado_producto());
            producto.setTamaño(productoDetails.getTamaño());
            producto.setAlergenos(productoDetails.getAlergenos());
            return productoRepository.save(producto);
        }
        return null;
    }

    // Método para eliminar un producto
    public void deleteProducto(int id) {
        productoRepository.deleteById(id);
    }

}
