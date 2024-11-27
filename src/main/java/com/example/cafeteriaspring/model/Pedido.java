package com.example.cafeteriaspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pedido;
    @ManyToOne
    @JoinColumn(name="id_empleado")
    @NotNull(message = "El empleado es requerido")
    private Empleado empleado;
    @ManyToOne
    @JoinColumn(name="id_producto")
    @NotNull(message = "El producto es requerido")
    private Producto producto;
    @NotNull(message = "El número de mesa es requerido")
    @Positive(message = "El número de mesa debe ser un número positivo")
    private int num_mesa;
    @NotBlank(message = "El nombre del cliente es requerido")
    private String nom_cliente;
    private String nota_especial;
    @NotNull(message = "El total es requerido")
    @Positive(message = "El total debe ser un número positivo")
    private double total;


    public Pedido() {
    }


    public Pedido(int id_pedido, Empleado empleado, Producto producto, int num_mesa, String nom_cliente, String nota_especial, double total) {
        this.id_pedido = id_pedido;
        this.empleado = empleado;
        this.producto = producto;
        this.num_mesa = num_mesa;
        this.nom_cliente = nom_cliente;
        this.nota_especial = nota_especial;
        this.total = total;
    }


    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }

    public String getNom_cliente() {
        return nom_cliente;
    }

    public void setNom_cliente(String nom_cliente) {
        this.nom_cliente = nom_cliente;
    }

    public String getNota_especial() {
        return nota_especial;
    }

    public void setNota_especial(String nota_especial) {
        this.nota_especial = nota_especial;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}