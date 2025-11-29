package co.edu.udec.domain.ports.out;

import co.edu.udec.domain.model.DetalleCompra;

import java.util.List;

public interface DetalleCompraRepository {
    boolean guardar(DetalleCompra detalle);
    List<DetalleCompra> listarPorCompra(int idCompra);
}
