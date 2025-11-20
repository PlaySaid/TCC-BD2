package co.edu.udec.domain.ports.out;

import co.edu.udec.domain.model.Categoria;

import java.util.List;

public interface CategoriaRepository {
    Categoria obtenerPorId(int id);
    List<Categoria> listar();

    boolean guardar(Categoria categoria);
    boolean actualizar(Categoria categoria);
    boolean eliminar(int id);
}
