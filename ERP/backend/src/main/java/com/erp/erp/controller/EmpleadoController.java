package com.erp.erp.controller;

import com.erp.erp.model.Empleado;
import com.erp.erp.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        return empleadoService.agregarEmpleado(empleado);
    }

    @GetMapping("/{id}")
    public Empleado buscarEmpleadoPorId(@PathVariable int id){
        return empleadoService.buscarEmpleado(id);
    }

    @GetMapping
    public List<Empleado> listarEmpleados(){
        return  empleadoService.listarEmpleados();
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable int id){
        empleadoService.eliminarEmpleado(id);
    }

    @PutMapping("/{id}")
    public Empleado editarEmpleado(@PathVariable int id, @RequestBody Empleado empleado){
        return empleadoService.editarEmpleado(id, empleado);
    }
}
