package co.edu.udec.domain.ports.out;

import co.edu.udec.domain.model.Producto;

import java.util.List;

public interface ProductoRepository {
    Producto obtenerPorId(int id);
    List<Producto> listar();

    boolean guardar(Producto producto);
    boolean actualizar(Producto producto);
    boolean eliminar(int id);

    // Importante para ventas y compras
    boolean actualizarStock(int idProducto, int nuevoStock);
}
