package ui;

import model.*;
import service.ReporteService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuReportes {
    private Scanner sc;
    private ReporteService reporteService;

    public MenuReportes(ReporteService reporteService) {
        this.sc = new Scanner(System.in);
        this.reporteService = reporteService;
    }

    private final String menu = """
            === MENU REPORTES ===
            1. Reporte de ventas totales
            2. Reporte de ventas por empleado
            3. Reporte de ventas por cliente
            4. Reporte de productos más vendidos
            5. Reporte de ingresos por producto
            6. Reporte general
            7. Volver
            """;

    public void mostrarMenuReportes() {
        boolean continuar = true;

        while (continuar) {
            System.out.println(menu);
            System.out.print("Selecciona una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> mostrarReporteVentasTotales();
                case 2 -> mostrarReporteVentasPorEmpleado();
                case 3 -> mostrarReporteVentasPorCliente();
                case 4 -> mostrarReporteProductosMasVendidos();
                case 5 -> mostrarReporteIngresosPorProducto();
                case 6 -> mostrarReporteGeneral();
                case 7 -> {
                    System.out.println("Volviendo al menú principal...");
                    return;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void mostrarReporteVentasTotales() {
        double total = reporteService.generarReporteVentasTotales();
        System.out.println("=== REPORTE DE VENTAS TOTALES ===");
        System.out.println("Total de ingresos: $" + total);
    }

    private void mostrarReporteVentasPorEmpleado() {
        System.out.println("=== REPORTE DE VENTAS POR EMPLEADO ===");
        Map<Empleado, Double> reporte = reporteService.generarReporteVentasPorEmpleado();

        if (reporte.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        reporte.forEach((empleado, total) ->
                System.out.println(empleado.getNombre() + " -> $" + total));
    }

    private void mostrarReporteVentasPorCliente() {
        System.out.println("=== REPORTE DE VENTAS POR CLIENTE ===");
        Map<Cliente, Double> reporte = reporteService.generarReporteVentasPorCliente();

        if (reporte.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        reporte.forEach((cliente, total) ->
                System.out.println(cliente.getNombre() + " -> $" + total));
    }

    private void mostrarReporteProductosMasVendidos() {
        System.out.println("=== REPORTE DE PRODUCTOS MÁS VENDIDOS ===");
        List<Map.Entry<Producto, Integer>> lista = reporteService.generarReporteProductosMasVendidos();

        if (lista.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        int pos = 1;
        for (Map.Entry<Producto, Integer> entry : lista) {
            System.out.println(pos++ + ". " + entry.getKey().getNombre() +
                    " -> Cantidad vendida: " + entry.getValue());
        }
    }

    private void mostrarReporteIngresosPorProducto() {
        System.out.println("=== REPORTE DE INGRESOS POR PRODUCTO ===");
        Map<Producto, Double> reporte = reporteService.generarReporteIngresosPorProducto();

        if (reporte.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        reporte.forEach((producto, total) ->
                System.out.println(producto.getNombre() + " -> Ingresos: $" + total));
    }

    private void mostrarReporteGeneral() {
        System.out.println("=== REPORTE GENERAL ===");
        Map<String, Object> reporte = reporteService.generarReporteGeneral();

        if (reporte.containsKey("mensaje")) {
            System.out.println(reporte.get("mensaje"));
            return;
        }

        System.out.println("Total de ventas: " + reporte.get("totalVentas"));
        System.out.println("Ingresos totales: $" + reporte.get("ingresosTotales"));
        System.out.println("Fecha última venta: " + reporte.get("fechaUltimaVenta"));
        System.out.println("Total de clientes únicos: " + reporte.get("totalClientes"));
        System.out.println("Total de productos vendidos: " + reporte.get("totalProductosVendidos"));

        Producto masVendido = (Producto) reporte.get("productoMasVendido");
        if (masVendido != null)
            System.out.println("Producto más vendido: " + masVendido.getNombre());

        Empleado top = (Empleado) reporte.get("empleadoTopVentas");
        if (top != null)
            System.out.println("Empleado con más ventas: " + top.getNombre());
    }
}

