package co.edu.udec.domain.ports.out;

import co.edu.udec.domain.model.Usuario;

import java.util.List;

public interface UsuarioRepository {
    Usuario buscarPorUsuario(String usuario);
    Usuario obtenerPorId(int id);
    List<Usuario> listar();

    boolean guardar(Usuario usuario);
    boolean actualizar(Usuario usuario);
    boolean eliminar(int id);
}
