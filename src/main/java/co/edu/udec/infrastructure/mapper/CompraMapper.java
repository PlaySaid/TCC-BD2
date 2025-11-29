package co.edu.udec.infrastructure.mapper;

import co.edu.udec.domain.model.Compra;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompraMapper {

    public static Compra map(ResultSet rs) throws SQLException {
        return new Compra(
                rs.getInt("id_compra"),
                rs.getTimestamp("fecha"),
                rs.getInt("id_proveedor"),
                rs.getInt("id_usuario"),
                rs.getDouble("total")
        );
    }
}
