package co.edu.udec.domain.ports.out;

import co.edu.udec.domain.model.DetalleVenta;

import java.util.List;

public interface DetalleVentaRepository {
    boolean guardar(DetalleVenta detalle);
    List<DetalleVenta> listarPorVenta(int idVenta);
}
