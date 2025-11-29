package co.edu.udec.domain.ports.in;

import co.edu.udec.domain.model.Producto;

import java.util.List;

public interface GestionProductoService {
    boolean crearProducto(Producto producto);
    boolean actualizarProducto(Producto producto);
    boolean eliminarProducto(int id);
    Producto obtenerPorId(int id);
    List<Producto> listarProductos();

    // ---- Funcion importante para ventas y compras ---- //
    boolean actualizarStock(int idProducto, int nuevoStock);
}
