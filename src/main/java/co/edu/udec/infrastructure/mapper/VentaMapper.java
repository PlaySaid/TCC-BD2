package co.edu.udec.infrastructure.mapper;

import co.edu.udec.domain.model.Venta;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentaMapper {

    public static Venta map(ResultSet rs) throws SQLException {
        return new Venta(
                rs.getInt("id_venta"),
                rs.getTimestamp("fecha"),
                rs.getInt("id_usuario"),
                rs.getInt("id_cliente"),
                rs.getDouble("total")
        );
    }
}
