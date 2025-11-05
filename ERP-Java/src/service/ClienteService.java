package service;

import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private int nextId = 1;
    private List<Cliente> clientes;

    public ClienteService() {
        this.clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente c){
        c.setId(nextId++);
        clientes.add(c);
    }

    public Cliente buscarCliente(int id){
        return clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public List<Cliente> listarClientes(){
        return clientes;
    }

    public void eliminarCliente(int id){
        clientes.removeIf(c -> c.getId() == id);
    }

    public void editarCliente(int id, Cliente nuevosDatos){
        clientes.stream().filter(c -> c.getId() == id).forEach(c -> {
            c.setNombre(nuevosDatos.getNombre());
            c.setEmail(nuevosDatos.getEmail());
        });
    }
}
