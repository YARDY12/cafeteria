package com.example.cafeteriaspring.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;

    @Column(name = "nombre_producto", nullable = false, length = 100)
    private String nombre_producto;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "precio_unitario", nullable = false)
    private double precio_unitario;

    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    @Column(name = "stock_actual", nullable = false)
    private int stock_actual;

    @Column(name = "unidad_medida", nullable = false, length = 50)
    private String unidad_medida;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = false)
    private Date fecha_registro;

    @Column(name = "estado_producto", nullable = false, length = 50)
    private String estado_producto;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Detalle_Pedido> detalles;

    // Constructor vacío
    public Producto() {}

    // Constructor con todos los parámetros
    public Producto(String nombre_producto, String descripcion, double precio_unitario, String categoria,
                    int stock_actual, String unidad_medida, Date fecha_registro, String estado_producto) {
        this.nombre_producto = nombre_producto;
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
        this.categoria = categoria;
        this.stock_actual = stock_actual;
        this.unidad_medida = unidad_medida;
        this.fecha_registro = fecha_registro;
        this.estado_producto = estado_producto;
    }

    // Getters y Setters


    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(String estado_producto) {
        this.estado_producto = estado_producto;
    }
}