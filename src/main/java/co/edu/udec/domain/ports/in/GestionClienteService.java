package co.edu.udec.domain.ports.in;

import co.edu.udec.domain.model.Cliente;

import java.util.List;

public interface GestionClienteService {
    boolean crearCliente(Cliente cliente);
    boolean actualizarCliente(Cliente cliente);
    boolean eliminarCliente(int id);
    Cliente obtenerPorId(int id);
    List<Cliente> listarClientes();
}
