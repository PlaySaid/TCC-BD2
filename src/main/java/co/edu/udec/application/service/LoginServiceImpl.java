package co.edu.udec.application.service;

import co.edu.udec.domain.model.Usuario;
import co.edu.udec.domain.ports.in.LoginService;
import co.edu.udec.domain.ports.out.UsuarioRepository;

public class LoginServiceImpl implements LoginService {

    private final UsuarioRepository usuarioRepository;

    public LoginServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario login(String usuario, String password) {
        // 1 Buscar usuario por el nombre
        Usuario encontrado = usuarioRepository.buscarPorUsuario(usuario);

        // 2 Validar si existe
        if (encontrado == null) {
            System.out.println("Usuario no encontrado");
            return null;
        }

        // 3️ Validar contraseña
        if (!encontrado.getPassword().equals(password)) {
            System.out.println("Contraseña incorrecta");
            return null;
        }

        // 4 Return si todo ok
        System.out.println("Login exitoso: " + encontrado.getNombre());
        return encontrado;
    }
}