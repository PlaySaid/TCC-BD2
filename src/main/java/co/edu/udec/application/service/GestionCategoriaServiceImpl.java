package co.edu.udec.application.service;

import co.edu.udec.domain.model.Categoria;
import co.edu.udec.domain.ports.in.GestionCategoriaService;
import co.edu.udec.domain.ports.out.CategoriaRepository;

import java.util.List;

public class GestionCategoriaServiceImpl implements GestionCategoriaService {

    private final CategoriaRepository categoriaRepository;

    public GestionCategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean crearCategoria(Categoria categoria) {
        if (categoria == null || categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
            return false;
        }
        return categoriaRepository.guardar(categoria);
    }

    @Override
    public boolean actualizarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getId() <= 0) {
            return false;
        }
        return categoriaRepository.actualizar(categoria);
    }

    @Override
    public boolean eliminarCategoria(int id) {
        if (id <= 0) return false;
        return categoriaRepository.eliminar(id);
    }

    @Override
    public Categoria obtenerPorId(int id) {
        if (id <= 0) return null;
        return categoriaRepository.obtenerPorId(id);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listar();
    }
}
