package co.edu.udec.application.service;

import co.edu.udec.domain.model.Producto;
import co.edu.udec.domain.ports.in.GestionProductoService;
import co.edu.udec.domain.ports.out.ProductoRepository;

import java.util.List;

public class GestionProductoServiceImpl implements GestionProductoService {

    private final ProductoRepository productoRepository;

    public GestionProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public boolean crearProducto(Producto producto) {
        if (producto == null || producto.getNombre() == null || producto.getNombre().isEmpty()) {
            return false;
        }
        return productoRepository.guardar(producto);
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        if (producto == null || producto.getId() <= 0) return false;
        return productoRepository.actualizar(producto);
    }

    @Override
    public boolean eliminarProducto(int id) {
        if (id <= 0) return false;
        return productoRepository.eliminar(id);
    }

    @Override
    public Producto obtenerPorId(int id) {
        if (id <= 0) return null;
        return productoRepository.obtenerPorId(id);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.listar();
    }

    @Override
    public boolean actualizarStock(int idProducto, int nuevoStock) {
        if (idProducto <= 0) return false;
        return productoRepository.actualizarStock(idProducto, nuevoStock);
    }
}
