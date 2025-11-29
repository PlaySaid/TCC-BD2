package co.edu.udec.infrastructure.controller;

import co.edu.udec.domain.model.Usuario;
import co.edu.udec.domain.ports.in.LoginService;

public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Método encargado de manejar el login desde la capa UI
     * (Consola, Web, Swing, Lo que sea)
     */
    public Usuario login(String usuario, String password) {
        Usuario encontrado = loginService.login(usuario, password);

        if (encontrado == null) {
            System.out.println("❌ Error: Usuario o contraseña incorrectos.");
            return null;
        }

        System.out.println("🔓 Sesión iniciada correctamente.");
        return encontrado;
    }
}



