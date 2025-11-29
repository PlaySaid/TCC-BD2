package co.edu.udec.domain.ports.out;

import co.edu.udec.domain.model.Cliente;

import java.util.List;

public interface ClienteRepository {
    Cliente obtenerPorId(int id);
    List<Cliente> listar();

    boolean guardar(Cliente cliente);
    boolean actualizar(Cliente cliente);
    boolean eliminar(int id);
}
