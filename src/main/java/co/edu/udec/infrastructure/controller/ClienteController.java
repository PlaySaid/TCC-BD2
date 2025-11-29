package co.edu.udec.infrastructure.controller;

import co.edu.udec.domain.model.Cliente;
import co.edu.udec.domain.ports.in.GestionClienteService;

import java.sql.Date;
import java.util.List;

public class ClienteController {

    private final GestionClienteService service;

    public ClienteController(GestionClienteService service) {
        this.service = service;
    }

    public boolean crear(String nombre, String telefono, String email,
                         String direccion, Date fechaRegistro) {

        return service.crearCliente(new Cliente(
                0, nombre, telefono, email, direccion, fechaRegistro
        ));
    }

    public boolean actualizar(int id, String nombre, String telefono,
                              String email, String direccion) {

        return service.actualizarCliente(new Cliente(
                id, nombre, telefono, email, direccion, null
        ));
    }

    public boolean eliminar(int id) {
        return service.eliminarCliente(id);
    }

    public Cliente obtener(int id) {
        return service.obtenerPorId(id);
    }

    public List<Cliente> listar() {
        return service.listarClientes();
    }
}
