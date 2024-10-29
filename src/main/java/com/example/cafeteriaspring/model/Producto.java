package com.example.cafeteriaspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Date;


@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;
    @NotBlank(message = "El nombre del producto es requerido")
    @Size(min = 2, max = 100, message = "El nombre del producto debe tener entre 2 y 100 caracteres")
    private String nombre;
    @NotBlank(message = "La descripción es requerida")
    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;
    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio debe ser un número positivo")
    private double precio;
    @NotBlank(message = "La categoría es requerida")
    private String categoria;
    @NotBlank(message = "El estado del producto es requerido")
    private String estado_producto;
    @NotBlank(message = "El tamaño es requerido")
    private String tamaño;
    @NotNull(message = "La fecha de registro es requerida")
    @Column(name = "fecha_registro")
    private Date fecha_registro;
    private String alergenos;

    public Producto() {
    }

    public Producto(int id_producto, String nombre, String descripcion, double precio, String categoria, String estado_producto, String tamaño, Date fecha_registro, String alergenos) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.estado_producto = estado_producto;
        this.tamaño = tamaño;
        this.fecha_registro = fecha_registro;
        this.alergenos = alergenos;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(String estado_producto) {
        this.estado_producto = estado_producto;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(String alergenos) {
        this.alergenos = alergenos;
    }
}