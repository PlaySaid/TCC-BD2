package co.edu.udec.infrastructure.mapper;

import co.edu.udec.domain.model.DetalleVenta;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleVentaMapper {

    public static DetalleVenta map(ResultSet rs) throws SQLException {
        return new DetalleVenta(
                rs.getInt("id_detalle"),
                rs.getInt("id_venta"),
                rs.getInt("id_producto"),
                rs.getInt("cantidad"),
                rs.getDouble("precio_unitario"),
                rs.getDouble("subtotal")
        );
    }
}
