package co.edu.udec.infrastructure.repository;

import co.edu.udec.domain.model.Compra;
import co.edu.udec.domain.ports.out.CompraRepository;
import co.edu.udec.infrastructure.config.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraRepositoryImpl implements CompraRepository {

    @Override
    public int guardar(Compra compra) {
        String sql = "INSERT INTO compras (fecha, id_proveedor, id_usuario, total) VALUES (?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, compra.getFecha());
            stmt.setInt(2, compra.getIdProveedor());
            stmt.setInt(3, compra.getIdUsuario());
            stmt.setDouble(4, compra.getTotal());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error guardar compra: " + e.getMessage());
        }

        return -1;
    }

    @Override
    public Compra obtenerPorId(int id) {
        String sql = "SELECT * FROM compras WHERE id_compra=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Compra(
                        rs.getInt("id_compra"),
                        rs.getTimestamp("fecha"),
                        rs.getInt("id_proveedor"),
                        rs.getInt("id_usuario"),
                        rs.getDouble("total")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error obtener compra: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Compra> listar() {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT * FROM compras";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Compra(
                        rs.getInt("id_compra"),
                        rs.getTimestamp("fecha"),
                        rs.getInt("id_proveedor"),
                        rs.getInt("id_usuario"),
                        rs.getDouble("total")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listar compras: " + e.getMessage());
        }

        return lista;
    }
}
