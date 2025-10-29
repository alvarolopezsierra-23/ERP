package com.erp.erp.controller;


import com.erp.erp.model.DetalleVenta;
import com.erp.erp.model.Producto;
import com.erp.erp.model.Venta;
import com.erp.erp.service.VentaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public void crearVenta(@RequestBody Venta venta) {
        ventaService.crearVenta(venta);
    }

    @GetMapping("/{id}")
    public Venta buscarVentaPorId(@PathVariable int id) {
        return ventaService.buscarVenta(id);
    }

    @GetMapping
    public List<Venta> listarVentas() {
        return ventaService.listarVentas();
    }

    @GetMapping("/total")
    public double calcularTotalVentas(){
        return ventaService.calcularTotalVentas();
    }

    @GetMapping("/{id}/detalles")
    public void agregarDetalle(@PathVariable int id, @RequestBody DetalleVenta detalle) {
        ventaService.agregarDetalle(id, detalle.getProducto(), detalle.getCantidad());
    }


    @DeleteMapping("{id}")
    public void eliminarDetalle(@PathVariable int id) {
        ventaService.eliminarVenta(id);
    }




}
