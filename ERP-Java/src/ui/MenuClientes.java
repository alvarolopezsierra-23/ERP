package ui;

import model.Cliente;
import service.ClienteService;

import java.util.Scanner;

public class MenuClientes {
    private Scanner sc;
    private ClienteService clienteService;

    public MenuClientes(ClienteService clienteService) {
        this.sc = new Scanner(System.in);
        this.clienteService = clienteService;
    }

    public String menu = """
        === MENU CLIENTES ===
        1. Agregar clientes
        2. Buscar clientes
        3. Listar clientes
        4. Eliminar clientes
        5. Volver
        """;


    public void mostrarMenuCliente() {
        boolean continuar = true;
        while (continuar) {
            System.out.println(this.menu);
            System.out.print("¿Qué quieres hacer?: ");
            int seleccion = sc.nextInt();sc.nextLine();
            if (0 < seleccion && seleccion < 7)
                switch (seleccion) {
                    case 1:
                        agregarClientes();
                        break;

                    case 2:
                        buscarCliente();
                        break;

                    case 3:
                        listarClientes();
                        break;

                    case 4:
                        eliminarCliente();
                        break;

                    case 5:
                        System.out.println("Volviendo al menu principal...");
                        return;
                }
            else continuar = false;
        }
    }



    public void agregarClientes(){
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese el email del cliente: ");
        String email = sc.nextLine();
        Cliente cliente = new Cliente(nombre, email);
        clienteService.agregarCliente(cliente);
        System.out.println("Cliente agregado correctamente");
    }

    public void buscarCliente(){
        System.out.print("Ingrese el ID del cliente: ");
        int id = sc.nextInt();sc.nextLine();
        System.out.println(clienteService.buscarCliente(id).toString());
    }

    public void listarClientes(){
        for(Cliente c : clienteService.listarClientes()){
            System.out.println(c.toString());
        }
    }

    public void eliminarCliente(){
        System.out.print("Ingrese el ID del cliente: ");
        int id = sc.nextInt();sc.nextLine();
        clienteService.eliminarCliente(id);
        System.out.println("Cliente eliminado correctamente");
    }
}
