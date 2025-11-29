package co.edu.udec.domain.ports.in;

import co.edu.udec.domain.model.Categoria;

import java.util.List;

public interface GestionCategoriaService {
    boolean crearCategoria(Categoria categoria);
    boolean actualizarCategoria(Categoria categoria);
    boolean eliminarCategoria(int id);
    Categoria obtenerPorId(int id);
    List<Categoria> listarCategorias();
}
