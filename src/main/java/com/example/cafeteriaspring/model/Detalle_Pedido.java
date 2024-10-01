package com.example.cafeteriaspring.model;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Detalle_Pedido")
public class Detalle_Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "subtotal", nullable = false)
    private double subtotal;

    @Column(name = "nota_detalle")
    private String nota_detalle;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_detalle", nullable = false)
    private Date fecha_detalle;

    @Column(name = "estado_detalle", nullable = false, length = 50)
    private String estado_detalle;

    @Column(name = "descuento")
    private double descuento;


    // Constructor vacío
    public Detalle_Pedido() {}

    // Constructor con todos los parámetros
    public Detalle_Pedido(Pedido pedido, Producto producto, int cantidad, double subtotal, String nota_detalle, Date fecha_detalle, String estado_detalle, double descuento) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.nota_detalle = nota_detalle;
        this.fecha_detalle = fecha_detalle;
        this.estado_detalle = estado_detalle;
        this.descuento = descuento;
    }

    // Getters y Setters


    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getNota_detalle() {
        return nota_detalle;
    }

    public void setNota_detalle(String nota_detalle) {
        this.nota_detalle = nota_detalle;
    }

    public Date getFecha_detalle() {
        return fecha_detalle;
    }

    public void setFecha_detalle(Date fecha_detalle) {
        this.fecha_detalle = fecha_detalle;
    }

    public String getEstado_detalle() {
        return estado_detalle;
    }

    public void setEstado_detalle(String estado_detalle) {
        this.estado_detalle = estado_detalle;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}
