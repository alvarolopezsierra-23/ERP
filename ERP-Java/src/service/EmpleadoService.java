package service;

import model.Empleado;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {
    private int nextId = 1;
    private List<Empleado> empleados;

    public EmpleadoService() {
        empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado e){
        e.setId(nextId++);
        empleados.add(e);
    }

    public void eliminarEmpleado(int id){
        empleados.removeIf(e -> e.getId() == id);
    }

    public Empleado buscarEmpleado(int id){
        return empleados.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public List<Empleado> listarEmpleados(){
        return empleados;
    }

    public void editarEmpleado(int id, Empleado nuevosDatos){
        empleados.stream().filter(e -> e.getId() == id).forEach(e -> {
            e.setNombre(nuevosDatos.getNombre());
            e.setEmail(nuevosDatos.getEmail());
            e.setPuesto(nuevosDatos.getPuesto());
            e.setSalario(nuevosDatos.getSalario());
        });
    }

}
