package com.erp.erp.service;

import com.erp.erp.model.DetalleVenta;
import com.erp.erp.model.Producto;
import com.erp.erp.model.Venta;
import com.erp.erp.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public void crearVenta(Venta v){
        ventaRepository.save(v);
    }

    public Venta buscarVenta(int id){
        return ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta con id " + id + " no encontrada"));
    }

    public List<Venta> listarVentas(){
        return ventaRepository.findAll();
    }

    public double calcularTotalVentas(){
        return ventaRepository.findAll()
                .stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }

    @Transactional
    public void agregarDetalle(int idVenta, Producto producto, int cantidad) {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new RuntimeException("Venta con id " + idVenta + " no encontrada"));

        DetalleVenta detalle = new DetalleVenta(producto, cantidad);
        venta.addDetalle(detalle);

        producto.setStock(producto.getStock() - cantidad);
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("No hay suficiente stock del producto: " + producto.getNombre());
        }

        ventaRepository.save(venta);
    }


    public void eliminarVenta(int id){
        ventaRepository.deleteById(id);
    }
}
