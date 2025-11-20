package co.edu.udec.domain.ports.in;

import co.edu.udec.domain.model.Usuario;

import java.util.List;

public interface GestionUsuarioService {
    boolean crearUsuario(Usuario usuario);
    boolean actualizarUsuario(Usuario usuario);
    boolean eliminarUsuario(int id);
    Usuario obtenerPorId(int id);
    List<Usuario> listarUsuarios();
}
