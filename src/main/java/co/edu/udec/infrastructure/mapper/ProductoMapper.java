package co.edu.udec.infrastructure.mapper;

import co.edu.udec.domain.model.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoMapper {

    public static Producto map(ResultSet rs) throws SQLException {
        return new Producto(
                rs.getInt("id_producto"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio_compra"),
                rs.getDouble("precio_venta"),
                rs.getInt("stock")
        );
    }
}
