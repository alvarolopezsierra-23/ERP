package com.erp.erp.controller;


import com.erp.erp.model.DetalleVenta;
import com.erp.erp.model.Producto;
import com.erp.erp.model.Venta;
import com.erp.erp.repository.VentaRepository;
import com.erp.erp.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final VentaRepository ventaRepository;

    public VentaController(VentaService ventaService, VentaRepository ventaRepository) {
        this.ventaService = ventaService;
        this.ventaRepository = ventaRepository;
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

    @PostMapping("/{id}/detalles")
    public void agregarDetalle(@PathVariable int id, @RequestBody DetalleVenta detalle) {
        ventaService.agregarDetalle(id, detalle.getProducto(), detalle.getCantidad());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable int id, @RequestBody Venta ventaActualizada) {
        Optional<Venta> ventaExistenteOpt = ventaRepository.findById(id);

        if (ventaExistenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Venta ventaExistente = ventaExistenteOpt.get();

        // Actualizar campos básicos
        ventaExistente.setCliente(ventaActualizada.getCliente());
        ventaExistente.setEmpleado(ventaActualizada.getEmpleado());
        ventaExistente.setFecha(ventaActualizada.getFecha());
        ventaExistente.setTotal(ventaActualizada.getTotal());

        // Actualizar detalles si vienen incluidos
        if (ventaActualizada.getDetalles() != null && !ventaActualizada.getDetalles().isEmpty()) {
            ventaExistente.getDetalles().clear();
            ventaExistente.getDetalles().addAll(ventaActualizada.getDetalles());
        }

        Venta ventaGuardada = ventaRepository.save(ventaExistente);
        return ResponseEntity.ok(ventaGuardada);
    }

    @DeleteMapping("{id}")
    public void eliminarDetalle(@PathVariable int id) {
        ventaService.eliminarVenta(id);
    }




}
