package co.edu.udec.domain.ports.out;

import co.edu.udec.domain.model.Compra;

import java.util.List;

public interface CompraRepository {
    int guardar(Compra compra); // retorna ID generado
    Compra obtenerPorId(int id);
    List<Compra> listar();
}
