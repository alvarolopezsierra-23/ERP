package com.erp.erp.service;

import com.erp.erp.model.Empleado;
import com.erp.erp.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado agregarEmpleado(Empleado e){
        return empleadoRepository.save(e);
    }

    public void eliminarEmpleado(int id){
        empleadoRepository.deleteById(id);
    }

    public Empleado buscarEmpleado(int id){
        return empleadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Empleado con id " + id + " no encontrado"));
    }

    public List<Empleado> listarEmpleados(){
        return empleadoRepository.findAll();
    }

    public Empleado editarEmpleado(int id, Empleado nuevosDatos){
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Empleado con id " + id + " no encontrado"));
        if(empleado != null){
            empleado.setNombre(nuevosDatos.getNombre());
            empleado.setEmail(nuevosDatos.getEmail());
            empleado.setPuesto(nuevosDatos.getPuesto());
            empleado.setSalario(nuevosDatos.getSalario());
            return empleadoRepository.save(empleado);
        }
        return null;
    }

}
