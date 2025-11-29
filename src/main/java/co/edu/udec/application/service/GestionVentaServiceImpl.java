package co.edu.udec.application.service;

import co.edu.udec.domain.model.Venta;
import co.edu.udec.domain.model.DetalleVenta;
import co.edu.udec.domain.model.Producto;
import co.edu.udec.domain.ports.in.GestionVentaService;
import co.edu.udec.domain.ports.out.VentaRepository;
import co.edu.udec.domain.ports.out.DetalleVentaRepository;
import co.edu.udec.domain.ports.out.ProductoRepository;

import java.util.List;

public class GestionVentaServiceImpl implements GestionVentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepository productoRepository;

    public GestionVentaServiceImpl(
            VentaRepository ventaRepository,
            DetalleVentaRepository detalleVentaRepository,
            ProductoRepository productoRepository) {

        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public int crearVenta(Venta venta, List<DetalleVenta> detalles) {

        // 1️ Guardar la venta
        int idVenta = ventaRepository.guardar(venta);

        if (idVenta <= 0) {
            System.out.println("Error: no se pudo registrar la venta.");
            return -1;
        }

        // 2️ Guardar detalles y actualizar stock
        for (DetalleVenta detalle : detalles) {

            detalle.setIdVenta(idVenta);
            detalleVentaRepository.guardar(detalle);

            // 3️ Actualizar estado del inventario (restar)
            Producto producto = productoRepository.obtenerPorId(detalle.getIdProducto());

            if (producto != null) {
                int nuevoStock = producto.getStock() - detalle.getCantidad();

                // Evitar stock negativo por seguridad
                if (nuevoStock < 0) nuevoStock = 0;

                productoRepository.actualizarStock(producto.getId(), nuevoStock);
            }
        }

        System.out.println("Venta registrada correctamente con ID: " + idVenta);
        return idVenta;
    }

    @Override
    public Venta obtenerPorId(int id) {
        return ventaRepository.obtenerPorId(id);
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.listar();
    }

    @Override
    public double calcularTotalVenta(int idVenta) {
        List<DetalleVenta> detalles = detalleVentaRepository.listarPorVenta(idVenta);

        return detalles.stream()
                .mapToDouble(DetalleVenta::getSubtotal)
                .sum();
    }
}


