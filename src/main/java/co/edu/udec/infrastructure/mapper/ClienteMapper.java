package co.edu.udec.infrastructure.mapper;

import co.edu.udec.domain.model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteMapper {

    public static Cliente map(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id_cliente"),
                rs.getString("nombre"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getString("direccion"),
                rs.getDate("fecha_registro")
        );
    }
}
