package com.erp.erp.controller;

import com.erp.erp.model.Cliente;
import com.erp.erp.model.Empleado;
import com.erp.erp.model.Producto;
import com.erp.erp.service.ReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController  // Responde a peticiones REST (devuelve JSON)
@RequestMapping("/reportes")  // Prefijo común para todos los endpoints de este controlador
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    // =======================
    // ENDPOINTS DE REPORTES
    // =======================

    @GetMapping("/ventas/totales")
    public double obtenerVentasTotales() {
        return reporteService.generarReporteVentasTotales();
    }

    @GetMapping("/ingresos/totales")
    public double obtenerIngresosTotales() {
        return reporteService.generarReporteIngresosTotales();
    }

    @GetMapping("/ventas/por-empleado")
    public ResponseEntity<Map<String, Double>> obtenerVentasPorEmpleado() {
        Map<Empleado, Double> ventasPorEmpleado = reporteService.generarReporteVentasPorEmpleado();
        Map<String, Double> resultado = ventasPorEmpleado.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().getNombre(),
                        Map.Entry::getValue
                ));
        return ResponseEntity.ok(resultado);
    }


    @GetMapping("/ventas/por-cliente")
    public Map<Cliente, Double> obtenerVentasPorCliente() {
        return reporteService.generarReporteVentasPorCliente();
    }

    @GetMapping("/productos/mas-vendidos")
    public List<Map.Entry<Producto, Integer>> obtenerProductosMasVendidos() {
        return reporteService.generarReporteProductosMasVendidos();
    }

    @GetMapping("/ingresos/por-producto")
    public Map<Producto, Double> obtenerIngresosPorProducto() {
        return reporteService.generarReporteIngresosPorProducto();
    }

    @GetMapping("/general")
    public Map<String, Object> obtenerReporteGeneral() {
        return reporteService.generarReporteGeneral();
    }
}
