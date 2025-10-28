package ui;

import model.Producto;
import service.ProductoService;

import java.util.Scanner;

public class MenuProductos {
    private Scanner sc;
    private ProductoService productoService;

    public MenuProductos(ProductoService productoService) {
        this.sc = new Scanner(System.in);
        this.productoService = productoService;
    }

    public String menu = """
        === MENU PRODUCTOS ===
        1. Agregar producto
        2. Buscar producto
        3. Listar productos
        4. Actualizar stock
        5. Eliminar producto
        6. Volver
        """;


    public void mostrarMenuProductos() {
        boolean continuar = true;
        while (continuar) {
            System.out.println(this.menu);
            System.out.print("¿Qué quieres hacer?: ");
            int seleccion = sc.nextInt();sc.nextLine();
            if (0 < seleccion && seleccion < 7)
                switch (seleccion) {
                    case 1:
                        agregarProductos();
                        break;

                    case 2:
                        buscarProducto();
                        break;

                    case 3:
                        listarProductos();
                        break;

                    case 4:
                        actualizarStock();
                        break;

                    case 5:
                        eliminarProducto();
                        break;

                    case 6:
                        System.out.println("Volviendo al menu principal...");
                        return;
                }
            else continuar = false;
        }
    }

    public void agregarProductos(){
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = sc.nextInt();sc.nextLine();
        System.out.print("Ingrese el stock actual del producto: ");
        int stock = sc.nextInt();sc.nextLine();
        Producto producto = new Producto(nombre, precio, stock);
        productoService.agregarProducto(producto);
        System.out.println("Producto agregado correctamente");
    }

    public void buscarProducto(){
        System.out.print("Ingrese el ID del producto: ");
        int id = sc.nextInt();sc.nextLine();
        System.out.println(productoService.buscarProducto(id).toString());
    }

    public void listarProductos(){
        for(Producto p : productoService.listarProductos()){
            System.out.println(p.toString());
        }
    }

    public void actualizarStock(){
        System.out.print("Indique el Id del producto: ");
        int id = sc.nextInt();sc.nextLine();
        System.out.print("Indique el nuevo stock: ");
        int stock = sc.nextInt();sc.nextLine();
        productoService.actualizarStock(id, stock);
    }

    public void eliminarProducto(){
        System.out.print("Ingrese el ID del producto: ");
        int id = sc.nextInt();sc.nextLine();
        productoService.eliminarProducto(id);
        System.out.println("Producto eliminado correctamente");
    }
}
