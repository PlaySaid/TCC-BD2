package co.edu.udec.domain.ports.in;

import co.edu.udec.domain.model.DetalleVenta;
import co.edu.udec.domain.model.Venta;

import java.util.List;

public interface GestionVentaService {
    int crearVenta(Venta venta, List<DetalleVenta> detalles);
    Venta obtenerPorId(int id);
    List<Venta> listarVentas();

    // ---- Reportes o consultas especiales ---- //
    double calcularTotalVenta(int idVenta);
}
