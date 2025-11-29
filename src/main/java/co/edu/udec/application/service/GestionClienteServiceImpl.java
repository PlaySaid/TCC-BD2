package co.edu.udec.application.service;

import co.edu.udec.domain.model.Cliente;
import co.edu.udec.domain.ports.in.GestionClienteService;
import co.edu.udec.domain.ports.out.ClienteRepository;

import java.util.List;

public class GestionClienteServiceImpl implements GestionClienteService {

    private final ClienteRepository clienteRepository;

    public GestionClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public boolean crearCliente(Cliente cliente) {
        if (cliente == null || cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            return false;
        }
        return clienteRepository.guardar(cliente);
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() <= 0) return false;
        return clienteRepository.actualizar(cliente);
    }

    @Override
    public boolean eliminarCliente(int id) {
        if (id <= 0) return false;
        return clienteRepository.eliminar(id);
    }

    @Override
    public Cliente obtenerPorId(int id) {
        if (id <= 0) return null;
        return clienteRepository.obtenerPorId(id);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.listar();
    }
}
