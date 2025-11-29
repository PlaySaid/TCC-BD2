package co.edu.udec.infrastructure.controller;

import co.edu.udec.domain.model.Categoria;
import co.edu.udec.domain.ports.in.GestionCategoriaService;

import java.util.List;

public class CategoriaController {

    private final GestionCategoriaService service;

    public CategoriaController(GestionCategoriaService service) {
        this.service = service;
    }

    public void crearCategoria(String nombre, String descripcion) {
        Categoria c = new Categoria(0, nombre, descripcion);
        service.crearCategoria(c);
    }

    public void actualizarCategoria(int id, String nombre, String descripcion) {
        Categoria c = new Categoria(id, nombre, descripcion);
        service.actualizarCategoria(c);
    }

    public void eliminarCategoria(int id) {
        service.eliminarCategoria(id);
    }

    public Categoria obtener(int id) {
        return service.obtenerPorId(id);
    }

    public List<Categoria> listar() {
        return service.listarCategorias();
    }
}
