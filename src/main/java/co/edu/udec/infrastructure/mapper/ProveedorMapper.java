package co.edu.udec.infrastructure.mapper;

import co.edu.udec.domain.model.Proveedor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedorMapper {

    public static Proveedor map(ResultSet rs) throws SQLException {
        return new Proveedor(
                rs.getInt("id_proveedor"),
                rs.getString("nombre"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getString("direccion"),
                rs.getDate("fecha_registro")
        );
    }
}
