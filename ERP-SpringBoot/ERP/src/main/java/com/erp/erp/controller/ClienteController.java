package com.erp.erp.controller;

import com.erp.erp.model.Cliente;
import com.erp.erp.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public Cliente agregarCliente(@RequestBody Cliente cliente) {
        return clienteService.agregarCliente(cliente);
    }

    @GetMapping("/{id}")
    public Cliente buscarClientePorId(@PathVariable int id) {
        return clienteService.buscarCliente(id);
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable int id) {
        clienteService.eliminarCliente(id);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable int id,@RequestBody Cliente cliente) {
        return clienteService.editarCliente(id, cliente);
    }

}
