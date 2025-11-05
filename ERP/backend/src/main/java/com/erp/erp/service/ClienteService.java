package com.erp.erp.service;

import com.erp.erp.model.Cliente;
import com.erp.erp.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente agregarCliente(Cliente c) {
        return clienteRepository.save(c);
    }

    public Cliente buscarCliente(int id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente con id " + id + " no encontrado"));
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public void eliminarCliente(int id) {
        clienteRepository.deleteById(id);
    }

    public Cliente editarCliente(int id, Cliente nuevosDatos) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente con id " + id + " no encontrado"));

        cliente.setNombre(nuevosDatos.getNombre());
        cliente.setEmail(nuevosDatos.getEmail());
        // añade otros campos si los hay
        return clienteRepository.save(cliente);
    }
}
