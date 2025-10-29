package ui;

import service.*;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner sc;
    private ClienteService clienteService;
    private EmpleadoService empleadoService;
    private VentaService ventaService;
    private ProductoService productoService;

    private MenuClientes menuClientes;
    private MenuEmpleados menuEmpleados;
    private MenuProductos menuProductos;
    private MenuReportes menuReportes;
    private MenuVentas menuVentas;

    public MenuPrincipal() {
        this.sc = new Scanner(System.in);

        // Crear instancias compartidas de los servicios
        this.clienteService = new ClienteService();
        this.empleadoService = new EmpleadoService();
        this.ventaService = new VentaService();
        this.productoService = new ProductoService();

        // Pasarlas a los menús
        this.menuClientes = new MenuClientes(clienteService);
        this.menuEmpleados = new MenuEmpleados(empleadoService);
        this.menuProductos = new MenuProductos(productoService);
        //this.menuReportes = new MenuReportes(ventaService, productoService, empleadoService);
        this.menuVentas = new MenuVentas(clienteService, empleadoService, ventaService, productoService);
    }

    private String menu = """
        ===MENU PRINCIPAL===
        1. Menu clientes
        2. Menu empleados
        3. Menu productos
        4. Menu ventas
        5. Menu reportes
        6. Salir
        """;

    public void mostrarMenuPrincipal() {
        boolean continuar = true;
        while (continuar) {
            System.out.println(menu);
            System.out.print("¿A qué menú quieres acceder?: ");
            int seleccion = sc.nextInt();
            sc.nextLine();
            if (seleccion >= 1 && seleccion <= 5)
                ejecutarOpcion(seleccion);
            else
                continuar = false;
        }
        System.out.println("Cerrando programa...");
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> menuClientes.mostrarMenuCliente();
            case 2 -> menuEmpleados.mostrarMenuEmpleado();
            case 3 -> menuProductos.mostrarMenuProductos();
            case 4 -> menuVentas.mostrarMenuVentas();
            case 5 -> menuReportes.mostrarMenuReportes();
        }
    }
}
