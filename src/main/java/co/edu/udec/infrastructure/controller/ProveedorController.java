package co.edu.udec.infrastructure.controller;

import co.edu.udec.domain.model.Proveedor;
import co.edu.udec.domain.ports.in.GestionProveedorService;

import java.sql.Date;
import java.util.List;

public class ProveedorController {

    private final GestionProveedorService service;

    public ProveedorController(GestionProveedorService service) {
        this.service = service;
    }

    public boolean crear(String nombre, String telefono, String email,
                         String direccion, Date fechaRegistro) {

        return service.crearProveedor(new Proveedor(
                0, nombre, telefono, email, direccion, fechaRegistro
        ));
    }

    public boolean actualizar(int id, String nombre, String telefono,
                              String email, String direccion) {

        return service.actualizarProveedor(new Proveedor(
                id, nombre, telefono, email, direccion, null
        ));
    }

    public boolean eliminar(int id) {
        return service.eliminarProveedor(id);
    }

    public Proveedor obtener(int id) {
        return service.obtenerPorId(id);
    }

    public List<Proveedor> listar() {
        return service.listarProveedores();
    }
}
