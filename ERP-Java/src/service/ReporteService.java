package service;

import model.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteService {
    private VentaService ventaService;
    private ProductoService productoService;
    private EmpleadoService empleadoService;
    private ClienteService clienteService;

    public ReporteService(VentaService vs, ProductoService ps, EmpleadoService es, ClienteService cs) {
        this.ventaService = vs;
        this.productoService = ps;
        this.empleadoService = es;
        this.clienteService = cs;
    }

    public double generarReporteVentasTotales(){
        return ventaService.calcularTotalVentas();
    }

    public Map<Empleado, Double> generarReporteVentasPorEmpleado(){
        Map<Empleado, Double> reporte = new HashMap<>();

        for(Venta venta : ventaService.listarVentas()){
            Empleado empleado = venta.getEmpleado();
            double totalVenta = venta.getTotal();

            if(reporte.containsKey(empleado)){
                double totalActual = reporte.get(empleado);
                reporte.put(empleado, totalActual+totalVenta);
            } else {
                reporte.put(empleado, totalVenta);
            }
        }
        return reporte;
    }

    public Map<Cliente, Double> generarReporteVentasPorCliente(){
        Map<Cliente, Double> reporte = new HashMap<>();

        for(Venta venta : ventaService.listarVentas()){
            Cliente cliente = venta.getCliente();
            double totalVenta = venta.getTotal();

            if(reporte.containsKey(cliente)){
                double totalActual = reporte.get(cliente);
                reporte.put(cliente, totalActual+totalVenta);
            } else {
                reporte.put(cliente, totalVenta);
            }
        }
        return reporte;
    }

    public List<Map.Entry<Producto, Integer>> generarReporteProductosMasVendidos(){
        Map<Producto, Integer> reporte = new HashMap<>();
        for(Venta venta : ventaService.listarVentas()){
            for(DetalleVenta detalle : venta.getDetalles()) {
                Producto producto = detalle.getProducto();
                int cantidadVendida = detalle.getCantidad();
                if (reporte.containsKey(producto)) {
                    int cantidadActual = reporte.get(producto);
                    reporte.put(producto, cantidadActual + cantidadVendida);
                } else {
                    reporte.put(producto, cantidadVendida);
                }
            }
        }

        //Creamos lista para poder devolverlo ya ordenado
        List<Map.Entry<Producto, Integer>> lista = new ArrayList<>(reporte.entrySet());
        lista.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return lista;
    }

    public Map<Producto, Double> generarReporteIngresosPorProducto(){
        Map<Producto, Double> reporte = new HashMap<>();
        for(Venta venta : ventaService.listarVentas()){
            for(DetalleVenta detalle : venta.getDetalles()){
                Producto producto = detalle.getProducto();
                double totalVenta = detalle.getSubtotal();
                if(reporte.containsKey(producto)){
                    double totalActual = reporte.get(producto);
                    reporte.put(producto, totalActual+totalVenta);
                } else {
                    reporte.put(producto, totalVenta);
                }
            }
        }
        return reporte;
    }

    public Map<String, Object> generarReporteGeneral() {
        Map<String, Object> reporte = new HashMap<>();
        List<Venta> ventas = ventaService.listarVentas();
        if (ventas == null || ventas.isEmpty()) {
            reporte.put("mensaje", "⚠️ No hay ventas registradas.");
            return reporte;
        }

        reporte.put("totalVentas", ventas.size());
        reporte.put("ingresosTotales", ventas.stream().mapToDouble(Venta::getTotal).sum());
        reporte.put("fechaUltimaVenta", ventas.stream().map(Venta::getFecha).max(LocalDate::compareTo).orElse(null));

        reporte.put("totalClientes", (int) ventas.stream()
                .map(Venta::getCliente)
                .distinct()
                .count());

        reporte.put("totalProductosVendidos", ventas.stream()
                .flatMap(v -> v.getDetalles().stream())
                .mapToInt(DetalleVenta::getCantidad)
                .sum());

        Map<Producto, Integer> productosVendidos = new HashMap<>();
        for (Venta venta : ventas) {
            for (DetalleVenta detalle : venta.getDetalles()) {
                Producto p = detalle.getProducto();
                productosVendidos.put(p, productosVendidos.getOrDefault(p, 0) + detalle.getCantidad());
            }
        }

        Producto productoMasVendido = productosVendidos.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        reporte.put("productoMasVendido", productoMasVendido);

        Map<Empleado, Double> ventasPorEmpleado = new HashMap<>();
        for (Venta venta : ventas) {
            Empleado empleado = venta.getEmpleado();
            double total = venta.getTotal();
            ventasPorEmpleado.put(empleado, ventasPorEmpleado.getOrDefault(empleado, 0.0) + total);
        }
        Empleado empleadoTop = ventasPorEmpleado.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        reporte.put("empleadoTopVentas", empleadoTop);

        return reporte;
    }

}
