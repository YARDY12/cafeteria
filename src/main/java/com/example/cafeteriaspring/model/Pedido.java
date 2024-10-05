package com.example.cafeteriaspring.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pedido;

    private int num_mesa;

    @Column(name = "nom_cliente", nullable = false, length = 100)
    private String nom_cliente;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "metodo_pago", nullable = false, length = 50)
    private String metodo_pago;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "nota_especial")
    private String nota_especial;

    @Column(name = "nom_mesero", length = 100)
    private String nom_mesero;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Detalle_Pedido> detalles;

    // Constructor vacío
    public Pedido() {}

    // Constructor con todos los parámetros
    public Pedido(int num_mesa, String nom_cliente, Date fecha, String metodo_pago, String estado, double total, String nota_especial, String nom_mesero) {
        this.num_mesa = num_mesa;
        this.nom_cliente = nom_cliente;
        this.fecha = fecha;
        this.metodo_pago = metodo_pago;
        this.estado = estado;
        this.total = total;
        this.nota_especial = nota_especial;
        this.nom_mesero = nom_mesero;
    }

    // Getters y Setters


    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNota_especial() {
        return nota_especial;
    }

    public void setNota_especial(String nota_especial) {
        this.nota_especial = nota_especial;
    }

    public String getNom_mesero() {
        return nom_mesero;
    }

    public void setNom_mesero(String nom_mesero) {
        this.nom_mesero = nom_mesero;
    }
}