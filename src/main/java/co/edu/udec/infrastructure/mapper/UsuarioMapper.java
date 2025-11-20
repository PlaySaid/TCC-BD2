package co.edu.udec.infrastructure.mapper;

import co.edu.udec.domain.model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper {

    public static Usuario map(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombre"),
                rs.getString("usuario"),
                rs.getString("password"),
                rs.getInt("id_rol"),
                rs.getDate("fecha_creacion"),
                rs.getBoolean("activo")
        );
    }
}
