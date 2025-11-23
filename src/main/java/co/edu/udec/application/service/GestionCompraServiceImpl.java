package co.edu.udec.application.service;

import co.edu.udec.domain.model.Compra;
import co.edu.udec.domain.model.DetalleCompra;
import co.edu.udec.domain.model.Producto;
import co.edu.udec.domain.ports.in.GestionCompraService;
import co.edu.udec.domain.ports.out.CompraRepository;
import co.edu.udec.domain.ports.out.DetalleCompraRepository;
import co.edu.udec.domain.ports.out.ProductoRepository;

import java.util.List;

public class GestionCompraServiceImpl implements GestionCompraService {

    private final CompraRepository compraRepository;
    private final DetalleCompraRepository detalleCompraRepository;
    private final ProductoRepository productoRepository;

    public GestionCompraServiceImpl(
            CompraRepository compraRepository,
            DetalleCompraRepository detalleCompraRepository,
            ProductoRepository productoRepository) {

        this.compraRepository = compraRepository;
        this.detalleCompraRepository = detalleCompraRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public int crearCompra(Compra compra, List<DetalleCompra> detalles) {

        // 1️ Guardar compra en BD
        int idCompra = compraRepository.guardar(compra);

        if (idCompra <= 0) {
            System.out.println("❌ Error: no se pudo registrar la compra.");
            return -1;
        }

        // 2️    Guardar detalles de compra
        for (DetalleCompra detalle : detalles) {

            detalle.setIdCompra(idCompra);
            detalleCompraRepository.guardar(detalle);

            // 3️⃣ Actualizar stock
            Producto producto = productoRepository.obtenerPorId(detalle.getIdProducto());

            if (producto != null) {
                int nuevoStock = producto.getStock() + detalle.getCantidad();
                productoRepository.actualizarStock(producto.getId(), nuevoStock);
            }
        }

        System.out.println("✅ Compra registrada correctamente con ID: " + idCompra);
        return idCompra;
    }

    @Override
    public Compra obtenerPorId(int id) {
        return compraRepository.obtenerPorId(id);
    }

    @Override
    public List<Compra> listarCompras() {
        return compraRepository.listar();
    }

    @Override
    public double calcularTotalCompra(int idCompra) {

        List<DetalleCompra> detalles = detalleCompraRepository.listarPorCompra(idCompra);

        return detalles.stream()
                .mapToDouble(DetalleCompra::getSubtotal)
                .sum();
    }
}


