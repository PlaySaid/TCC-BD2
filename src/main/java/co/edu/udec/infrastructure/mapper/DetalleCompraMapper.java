package co.edu.udec.infrastructure.mapper;

import co.edu.udec.domain.model.DetalleCompra;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleCompraMapper {

    public static DetalleCompra map(ResultSet rs) throws SQLException {
        return new DetalleCompra(
                rs.getInt("id_detalle"),
                rs.getInt("id_compra"),
                rs.getInt("id_producto"),
                rs.getInt("cantidad"),
                rs.getDouble("costo_unitario"),
                rs.getDouble("subtotal")
        );
    }
}
