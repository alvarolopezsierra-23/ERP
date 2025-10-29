package com.erp.erp.service;

import com.erp.erp.model.Producto;
import com.erp.erp.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto agregarProducto(Producto p){
        return productoRepository.save(p);
    }

    public Producto buscarProducto(int id){
       return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto con id " + id + " no encontrado"));
    }

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public Producto actualizarStock(int id, int cantidad) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto con id " + id + " no encontrado"));
        if (producto != null) {
            int nuevoStock = producto.getStock() - cantidad;
            if (nuevoStock < 0) {
                throw new IllegalArgumentException("El stock no puede ser negativo");
            }
            producto.setStock(nuevoStock);
            return productoRepository.save(producto);
        }
        return null;
    }

    public void eliminarProducto(int id){
        productoRepository.deleteById(id);
    }


}
