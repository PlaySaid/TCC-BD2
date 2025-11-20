package co.edu.udec.infrastructure.controller;

import co.edu.udec.domain.model.Producto;
import co.edu.udec.domain.ports.in.GestionProductoService;

import java.util.List;

public class ProductoController {

    private final GestionProductoService service;

    public ProductoController(GestionProductoService service) {
        this.service = service;
    }

    public boolean crear(String nombre, String descripcion,
                         double precioCompra, double precioVenta, int stock) {

        return service.crearProducto(new Producto(
                0, nombre, descripcion, precioCompra, precioVenta, stock
        ));
    }

    public boolean actualizar(int id, String nombre, String descripcion,
                              double precioCompra, double precioVenta, int stock) {

        return service.actualizarProducto(new Producto(
                id, nombre, descripcion, precioCompra, precioVenta, stock
        ));
    }

    public boolean eliminar(int id) {
        return service.eliminarProducto(id);
    }

    public Producto obtener(int id) {
        return service.obtenerPorId(id);
    }

    public List<Producto> listar() {
        return service.listarProductos();
    }

    public boolean actualizarStock(int idProducto, int nuevoStock) {
        return service.actualizarStock(idProducto, nuevoStock);
    }
}
