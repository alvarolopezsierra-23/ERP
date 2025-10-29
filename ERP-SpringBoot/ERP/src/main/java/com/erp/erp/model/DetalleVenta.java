package com.erp.erp.model;

import jakarta.persistence.*;

@Entity
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetalleVenta() {
    }

    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        subtotal = producto.getPrecio() * cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double calcularSubtotal(){
        return (producto.getPrecio() *cantidad);
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "id=" + getId() +
                ", producto=" + getProducto() +
                ", cantidad=" + getCantidad() +
                ", subtotal=" + getSubtotal() +
                '}';
    }
}
