package co.edu.udec.domain.ports.in;

import co.edu.udec.domain.model.Compra;
import co.edu.udec.domain.model.DetalleCompra;

import java.util.List;

public interface GestionCompraService {
    int crearCompra(Compra compra, List<DetalleCompra> detalles);
    Compra obtenerPorId(int id);
    List<Compra> listarCompras();

    double calcularTotalCompra(int idCompra);
}
