package co.edu.udec.infrastructure.repository;

import co.edu.udec.domain.model.Venta;
import co.edu.udec.domain.ports.out.VentaRepository;
import co.edu.udec.infrastructure.config.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaRepositoryImpl implements VentaRepository {

    @Override
    public int guardar(Venta venta) {
        String sql = "INSERT INTO ventas (fecha, id_usuario, id_cliente, total) VALUES (?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, venta.getFecha());
            stmt.setInt(2, venta.getIdUsuario());
            stmt.setObject(3, venta.getIdCliente());
            stmt.setDouble(4, venta.getTotal());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);

        } catch (SQLException e) {
            System.out.println("Error guardar venta: " + e.getMessage());
        }

        return -1;
    }

    @Override
    public Venta obtenerPorId(int id) {
        String sql = "SELECT * FROM ventas WHERE id_venta=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Venta(
                        rs.getInt("id_venta"),
                        rs.getTimestamp("fecha"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_cliente"),
                        rs.getDouble("total")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error obtener venta: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Venta> listar() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Venta(
                        rs.getInt("id_venta"),
                        rs.getTimestamp("fecha"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_cliente"),
                        rs.getDouble("total")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listar ventas: " + e.getMessage());
        }

        return lista;
    }
}
