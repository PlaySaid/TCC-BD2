package co.edu.udec.infrastructure.controller;

import co.edu.udec.domain.model.Usuario;
import co.edu.udec.domain.ports.in.GestionUsuarioService;
import co.edu.udec.domain.ports.in.LoginService;

import java.sql.Date;
import java.util.List;

public class UsuarioController {

    private final GestionUsuarioService service;
    private final LoginService loginService;

    public UsuarioController(GestionUsuarioService service, LoginService loginService) {
        this.service = service;
        this.loginService = loginService;
    }

    public boolean crear(String nombre, String usuario, String password,
                         int idRol, Date fechaCreacion, boolean activo) {

        return service.crearUsuario(new Usuario(
                0, nombre, usuario, password, idRol, fechaCreacion, activo
        ));
    }

    public Usuario login(String usuario, String password) {
        return loginService.login(usuario, password);
    }

    public boolean actualizar(Usuario u) {
        return service.actualizarUsuario(u);
    }

    public boolean eliminar(int id) {
        return service.eliminarUsuario(id);
    }

    public Usuario obtener(int id) {
        return service.obtenerPorId(id);
    }

    public List<Usuario> listar() {
        return service.listarUsuarios();
    }
}
