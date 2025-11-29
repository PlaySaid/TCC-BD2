package co.edu.udec.domain.ports.out;

import co.edu.udec.domain.model.Proveedor;

import java.util.List;

public interface ProveedorRepository {
    Proveedor obtenerPorId(int id);
    List<Proveedor> listar();

    boolean guardar(Proveedor proveedor);
    boolean actualizar(Proveedor proveedor);
    boolean eliminar(int id);
}
