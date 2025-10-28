package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int id;
    private Cliente cliente;
    private Empleado empleado;
    private LocalDate fecha;
    private List<DetalleVenta> detalles;
    private double total;

    public Venta(Cliente c, Empleado e, List<DetalleVenta> detalles) {
        this.cliente = c;
        this.empleado = e;
        this.detalles = new ArrayList<>();
        fecha = LocalDate.now();
        total = 0;
    }

    public void addDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
        this.total += detalle.getSubtotal();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public double getTotal() {
        return detalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum();
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", fecha=" + fecha +
                ", detalles=" + detalles +
                '}';
    }
}
