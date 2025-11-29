package co.edu.udec.domain.ports.out;

import co.edu.udec.domain.model.Venta;

import java.util.List;

public interface VentaRepository {
    int guardar(Venta venta);  // Retorna ID generado
    Venta obtenerPorId(int id);
    List<Venta> listar();
}
