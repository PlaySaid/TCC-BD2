package co.edu.udec.domain.ports.in;

import co.edu.udec.domain.model.Proveedor;

import java.util.List;

public interface GestionProveedorService {
    boolean crearProveedor(Proveedor proveedor);
    boolean actualizarProveedor(Proveedor proveedor);
    boolean eliminarProveedor(int id);
    Proveedor obtenerPorId(int id);
    List<Proveedor> listarProveedores();
}
