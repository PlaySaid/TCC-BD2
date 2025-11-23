package co.edu.udec.application.service;

import co.edu.udec.domain.model.Usuario;
import co.edu.udec.domain.ports.in.GestionUsuarioService;
import co.edu.udec.domain.ports.out.UsuarioRepository;

import java.util.List;

public class GestionUsuarioServiceImpl implements GestionUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public GestionUsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean crearUsuario(Usuario usuario) {
        if (usuario == null || usuario.getUsuario() == null || usuario.getUsuario().isEmpty()) {
            return false;
        }
        // Nota: aquí podrías agregar validación de unicidad llamando a buscarPorUsuario
        return usuarioRepository.guardar(usuario);
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getIdUsuario() <= 0) return false;
        return usuarioRepository.actualizar(usuario);
    }

    @Override
    public boolean eliminarUsuario(int id) {
        if (id <= 0) return false;
        return usuarioRepository.eliminar(id);
    }

    @Override
    public Usuario obtenerPorId(int id) {
        if (id <= 0) return null;
        return usuarioRepository.obtenerPorId(id);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listar();
    }
}
