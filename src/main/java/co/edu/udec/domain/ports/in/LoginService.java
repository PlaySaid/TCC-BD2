package co.edu.udec.domain.ports.in;

import co.edu.udec.domain.model.Usuario;

public interface LoginService {
    Usuario login(String usuario, String password);
}
