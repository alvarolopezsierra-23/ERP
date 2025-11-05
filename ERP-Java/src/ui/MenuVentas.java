package ui;

import model.*;
import service.ClienteService;
import service.ProductoService;
import service.VentaService;
import service.EmpleadoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuVentas {
    private Scanner sc;
    private ClienteService clienteService;
    private EmpleadoService empleadoService;
    private VentaService ventaService;
    private ProductoService productoService;

    public MenuVentas(ClienteService c, EmpleadoService e, VentaService v, ProductoService p) {
        this.clienteService = c;
        this.empleadoService = e;
        this.ventaService = v;
        this.productoService = p;
        this.sc = new Scanner(System.in);
    }

    private final String menu = """
            === MENU VENTAS ===
            1. Registrar venta
            2. Agregar detalles a una venta existente
            3. Buscar venta
            4. Listar ventas
            5. Volver al menú principal
            """;

    public void mostrarMenuVentas() {
        boolean continuar = true;
        while (continuar) {
            System.out.println(menu);
            System.out.print("¿Qué quieres hacer?: ");
            int seleccion = sc.nextInt();
            sc.nextLine();

            switch (seleccion) {
                case 1 -> registrarVenta();
                case 2 -> agregarDetalle();
                case 3 -> buscarVenta();
                case 4 -> listarVentas();
                case 5 -> {
                    System.out.println("Volviendo al menú principal...");
                    continuar = false;
                }
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    public void registrarVenta() {
        System.out.print("Ingrese ID del cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Cliente cliente = clienteService.buscarCliente(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("Ingrese ID del empleado: ");
        int idEmpleado = sc.nextInt();
        sc.nextLine();

        Empleado empleado = empleadoService.buscarEmpleado(idEmpleado);
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        List<DetalleVenta> detalles = new ArrayList<>();

        String continuar = "";
        do {
            System.out.print("Ingrese ID del producto: ");
            int idProducto = sc.nextInt();
            sc.nextLine();

            Producto producto = productoService.buscarProducto(idProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            if (cantidad > producto.getStock()) {
                System.out.println("No hay suficiente stock disponible.");
                continue;
            }

            DetalleVenta detalle = new DetalleVenta(producto, cantidad);
            detalles.add(detalle);
            producto.restarStock(cantidad);

            System.out.print("¿Agregar otro producto? (s/n): ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        Venta venta = new Venta(cliente, empleado, detalles);
        ventaService.crearVenta(venta);

        System.out.println("Venta registrada con éxito. Total: " + venta.getTotal());
    }

    public void agregarDetalle() {
        System.out.print("Ingrese el ID de la venta: ");
        int idVenta = sc.nextInt();
        sc.nextLine();

        Venta venta = ventaService.buscarVenta(idVenta);
        if (venta == null) {
            System.out.println("Venta no encontrada.");
            return;
        }

        String continuar = "";
        do {
            System.out.print("Ingrese el ID del producto: ");
            int idProducto = sc.nextInt();
            sc.nextLine();

            Producto producto = productoService.buscarProducto(idProducto);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Ingrese la cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            if (cantidad > producto.getStock()) {
                System.out.println("No hay suficiente stock disponible.");
                continue;
            }

            ventaService.agregarDetalle(venta, producto, cantidad);
            producto.restarStock(cantidad);

            System.out.println("Producto agregado correctamente.");
            System.out.println("Subtotal del último detalle: " + venta.getDetalles()
                    .get(venta.getDetalles().size() - 1).getSubtotal());

            System.out.print("¿Deseas agregar otro producto? (s/n): ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));
    }

    public void buscarVenta() {
        System.out.print("Ingrese ID de la venta: ");
        int idVenta = sc.nextInt();
        sc.nextLine();

        Venta venta = ventaService.buscarVenta(idVenta);
        if (venta == null) {
            System.out.println("Venta no encontrada.");
            return;
        }

        System.out.println(venta);
    }

    public void listarVentas() {
        List<Venta> ventas = ventaService.listarVentas();
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        for (Venta v : ventas) {
            System.out.println(v);
            System.out.println("--------------------------");
        }
    }
}
