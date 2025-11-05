package ui;

import model.Empleado;
import service.EmpleadoService;

import java.util.Scanner;

public class MenuEmpleados {
    private Scanner sc;
    private EmpleadoService empleadoService;

    public MenuEmpleados(EmpleadoService empleadoService){
        this.sc = new Scanner(System.in);
        this.empleadoService = empleadoService;
    }

    public String menu = """
        === MENU EMPLEADOS ===
        1. Agregar empleado
        2. Editar empleado
        3. Buscar empleado
        4. Listar empleado
        5. Eliminar empleado
        6. Volver
        """;


    public void mostrarMenuEmpleado() {
        boolean continuar = true;
        while (continuar) {
            System.out.println(this.menu);
            System.out.print("¿Qué quieres hacer?: ");
            int seleccion = sc.nextInt();sc.nextLine();
            if (0 < seleccion && seleccion < 7)
                switch (seleccion) {
                    case 1:
                        agregarEmpleado();
                        break;

                    case 2:
                        editarEmpleado();
                        break;

                    case 3:
                        buscarEmpleado();
                        break;

                    case 4:
                        listarEmpleados();
                        break;

                    case 5:
                        eliminarEmpleado();
                        break;

                    case 6:
                        System.out.println("Volviendo al menu principal...");
                        return;
                }
            else continuar = false;
        }
    }

    public void agregarEmpleado() {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese el email del empleado: ");
        String email = sc.nextLine();
        Empleado empleado = new Empleado(nombre, email);
        empleadoService.agregarEmpleado(empleado);
        System.out.println("Empleado agregado correctamente");
    }

    public void editarEmpleado(){
        System.out.print("Ingrese el ID del empleado: ");
        int id = sc.nextInt();sc.nextLine();
        System.out.println(empleadoService.buscarEmpleado(id));
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo email: ");
        String email = sc.nextLine();
        System.out.print("Nuevo puesto: ");
        String puesto = sc.nextLine();
        System.out.print("Nuevo salario: ");
        double salario = sc.nextInt();sc.nextLine();
        Empleado nuevoEmpleado = new Empleado(nombre, email, puesto, salario);
        empleadoService.editarEmpleado(id, nuevoEmpleado);
        System.out.println("Empleado editado correctamente.");
    }

    public void buscarEmpleado(){
        System.out.print("Ingrese el ID del empleado: ");
        int id = sc.nextInt();sc.nextLine();
        System.out.println(empleadoService.buscarEmpleado(id).toString());
    }

    public void listarEmpleados(){
        for(Empleado e : empleadoService.listarEmpleados()){
            System.out.println(e.toString());
        }
    }

    public void eliminarEmpleado(){
        System.out.print("Ingrese el ID del empleado: ");
        int id = sc.nextInt();sc.nextLine();
        empleadoService.eliminarEmpleado(id);
        System.out.println("Empleado eliminado correctamente");
    }
}
