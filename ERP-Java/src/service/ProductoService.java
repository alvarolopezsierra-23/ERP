package service;

import model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private int nextId = 1;
    private List<Producto> productos;

    public ProductoService() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto p){
        p.setId(nextId++);
        productos.add(p);
    }

    public Producto buscarProducto(int id){
        return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Producto> listarProductos(){
        return productos;
    }

    public void actualizarStock(int id, int cantidad){
        productos.stream().filter(p -> p.getId() == id).forEach(p -> {p.setStock(cantidad);});
    }

    public void eliminarProducto(int id){
        productos.removeIf(p -> p.getId() == id);
    }


}
