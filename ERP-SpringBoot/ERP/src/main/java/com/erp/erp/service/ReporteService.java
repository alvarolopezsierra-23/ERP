package com.erp.erp.service;

import com.erp.erp.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private final VentaService ventaService;
    private final ProductoService productoService;
    private final EmpleadoService empleadoService;
    private final ClienteService clienteService;

    public ReporteService(VentaService ventaService,
                          ProductoService productoService,
                          EmpleadoService empleadoService,
                          ClienteService clienteService) {
        this.ventaService = ventaService;
        this.productoService = productoService;
        this.empleadoService = empleadoService;
        this.clienteService = clienteService;
    }

    // Total de ventas
    public double generarReporteVentasTotales() {
        return ventaService.listarVentas().size();
    }

    // Total de ingresos
    public double generarReporteIngresosTotales() {
        return ventaService.listarVentas()
                .stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }

    // Ventas por empleado
    public Map<Empleado, Double> generarReporteVentasPorEmpleado() {
        return ventaService.listarVentas().stream()
                .collect(Collectors.groupingBy(
                        Venta::getEmpleado,
                        Collectors.summingDouble(Venta::getTotal)
                ));
    }

    // Ventas por cliente
    public Map<Cliente, Double> generarReporteVentasPorCliente() {
        return ventaService.listarVentas().stream()
                .collect(Collectors.groupingBy(
                        Venta::getCliente,
                        Collectors.summingDouble(Venta::getTotal)
                ));
    }

    // Productos más vendidos
    public List<Map.Entry<Producto, Integer>> generarReporteProductosMasVendidos() {
        Map<Producto, Integer> productosVendidos = new HashMap<>();
        ventaService.listarVentas().forEach(venta ->
                venta.getDetalles().forEach(detalle -> {
                    productosVendidos.merge(detalle.getProducto(), detalle.getCantidad(), Integer::sum);
                })
        );

        return productosVendidos.entrySet().stream()
                .sorted(Map.Entry.<Producto, Integer>comparingByValue().reversed())
                .toList();
    }

    // Ingresos por producto
    public Map<Producto, Double> generarReporteIngresosPorProducto() {
        Map<Producto, Double> ingresos = new HashMap<>();
        ventaService.listarVentas().forEach(venta ->
                venta.getDetalles().forEach(detalle ->
                        ingresos.merge(detalle.getProducto(), detalle.getSubtotal(), Double::sum)
                )
        );
        return ingresos;
    }

    // Reporte general
    public Map<String, Object> generarReporteGeneral() {
        Map<String, Object> reporte = new HashMap<>();
        List<Venta> ventas = ventaService.listarVentas();

        if (ventas.isEmpty()) {
            reporte.put("mensaje", "No hay ventas registradas.");
            return reporte;
        }

        reporte.put("totalVentas", ventas.size());
        reporte.put("ingresosTotales", ventas.stream().mapToDouble(Venta::getTotal).sum());
        reporte.put("fechaUltimaVenta", ventas.stream().map(Venta::getFecha).max(LocalDate::compareTo).orElse(null));
        reporte.put("totalClientes", ventas.stream().map(Venta::getCliente).distinct().count());
        reporte.put("totalProductosVendidos",
                ventas.stream()
                        .flatMap(v -> v.getDetalles().stream())
                        .mapToInt(DetalleVenta::getCantidad)
                        .sum()
        );

        // Producto más vendido
        Map<Producto, Integer> productosVendidos = new HashMap<>();
        for (Venta venta : ventas) {
            for (DetalleVenta detalle : venta.getDetalles()) {
                productosVendidos.merge(detalle.getProducto(), detalle.getCantidad(), Integer::sum);
            }
        }
        Producto productoMasVendido = productosVendidos.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        reporte.put("productoMasVendido", productoMasVendido);

        // Empleado top ventas
        Map<Empleado, Double> ventasPorEmpleado = ventas.stream()
                .collect(Collectors.groupingBy(Venta::getEmpleado, Collectors.summingDouble(Venta::getTotal)));
        Empleado empleadoTop = ventasPorEmpleado.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        reporte.put("empleadoTopVentas", empleadoTop);

        return reporte;
    }
}
