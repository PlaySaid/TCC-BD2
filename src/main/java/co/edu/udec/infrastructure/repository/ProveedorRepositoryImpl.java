package co.edu.udec.infrastructure.repository;

import co.edu.udec.domain.model.Proveedor;
import co.edu.udec.domain.ports.out.ProveedorRepository;
import co.edu.udec.infrastructure.config.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorRepositoryImpl implements ProveedorRepository {

    @Override
    public Proveedor obtenerPorId(int id) {
        String sql = "SELECT * FROM proveedores WHERE id_proveedor=?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_registro")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error obtener proveedor: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        rs.getDate("fecha_registro")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listar proveedores: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean guardar(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombre, telefono, email, direccion, fecha_registro) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getTelefono());
            stmt.setString(3, proveedor.getEmail());
            stmt.setString(4, proveedor.getDireccion());
            stmt.setDate(5, proveedor.getFechaRegistro());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error guardar proveedor: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizar(Proveedor proveedor) {
        String sql = "UPDATE proveedores SET nombre=?, telefono=?, email=?, direccion=? WHERE id_proveedor=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getTelefono());
            stmt.setString(3, proveedor.getEmail());
            stmt.setString(4, proveedor.getDireccion());
            stmt.setInt(5, proveedor.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar proveedor: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar proveedor: " + e.getMessage());
        }
        return false;
    }
}
