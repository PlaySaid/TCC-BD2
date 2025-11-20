package co.edu.udec.infrastructure.repository;

import co.edu.udec.domain.model.DetalleCompra;
import co.edu.udec.domain.ports.out.DetalleCompraRepository;
import co.edu.udec.infrastructure.config.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCompraRepositoryImpl implements DetalleCompraRepository {

    @Override
    public boolean guardar(DetalleCompra detalle) {
        String sql = "INSERT INTO detalle_compras (id_compra, id_producto, cantidad, costo_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detalle.getIdCompra());
            stmt.setInt(2, detalle.getIdProducto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setDouble(4, detalle.getCostoUnitario());
            stmt.setDouble(5, detalle.getSubtotal());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error guardar detalle compra: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<DetalleCompra> listarPorCompra(int idCompra) {
        List<DetalleCompra> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_compras WHERE id_compra=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCompra);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new DetalleCompra(
                        rs.getInt("id_detalle"),
                        rs.getInt("id_compra"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("costo_unitario"),
                        rs.getDouble("subtotal")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listar detalle compra: " + e.getMessage());
        }

        return lista;
    }
}
