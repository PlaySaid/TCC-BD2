package co.edu.udec.infrastructure.mapper;

import co.edu.udec.domain.model.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaMapper {

    public static Categoria map(ResultSet rs) throws SQLException {
        return new Categoria(
                rs.getInt("id_categoria"),
                rs.getString("nombre"),
                rs.getString("descripcion")
        );
    }
}
