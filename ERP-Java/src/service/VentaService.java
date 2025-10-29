package service;

import model.DetalleVenta;
import model.Producto;
import model.Venta;

import java.util.ArrayList;
import java.util.List;

public class VentaService {
    private int nextId = 1;
    private List<Venta> ventas;

    public VentaService() {
        this.ventas = new ArrayList<>();
    }

    public void crearVenta(Venta v){
        v.setId(nextId++);
        ventas.add(v);
    }

    public void agregarDetalle(Venta venta, Producto producto, int cantidad) {
        DetalleVenta detalle = new DetalleVenta(producto, cantidad);
        venta.addDetalle(detalle);
        producto.setStock(producto.getStock() - cantidad);
    }

    public Venta buscarVenta(int id){
        return ventas.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
    }

    public List<Venta> listarVentas(){
        return ventas;
    }

    public double calcularTotalVentas(){
        return ventas.stream().mapToDouble(Venta::getTotal).sum();
    }

    public void eliminarVenta(int id){
        ventas.removeIf(v -> v.getId() == id);
    }
}
