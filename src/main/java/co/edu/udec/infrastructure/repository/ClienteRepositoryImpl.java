package co.edu.udec.infrastructure.repository;

import co.edu.udec.domain.model.Cliente;
import co.edu.udec.domain.ports.out.ClienteRepository;
import co.edu.udec.infrastructure.config.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {

    @Override
    public Cliente obtenerPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id_cliente=?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_registro")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error obtener cliente: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_registro")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listar clientes: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean guardar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, telefono, email, direccion, fecha_registro) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getDireccion());
            stmt.setDate(5, cliente.getFecha_registro());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error guardar cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre=?, telefono=?, email=?, direccion=? WHERE id_cliente=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getDireccion());
            stmt.setInt(5, cliente.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id_cliente=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar cliente: " + e.getMessage());
        }
        return false;
    }
}
