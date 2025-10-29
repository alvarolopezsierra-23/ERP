package model;

public class DetalleVenta {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        subtotal = producto.getPrecio() * cantidad;
    }

    public double calcularSubtotal(){
        return (producto.getPrecio() *cantidad);
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Double getSubtotal(){
        return subtotal;
    }
}
