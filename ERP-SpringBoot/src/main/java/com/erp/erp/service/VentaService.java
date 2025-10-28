package com.erp.erp.service;

import com.erp.erp.model.DetalleVenta;
import com.erp.erp.model.Producto;
import com.erp.erp.model.Venta;
import com.erp.erp.repository.VentaRepository;
import org.springframework.stereotype.Service;

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

    public void agregarDetalle(Venta venta, Producto producto, int cantidad) {
        DetalleVenta detalle = new DetalleVenta(producto, cantidad);
        venta.addDetalle(detalle);
        ventaRepository.save(venta);
        producto.setStock(producto.getStock() - cantidad);
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

    public void eliminarVenta(int id){
        ventaRepository.deleteById(id);
    }
}
