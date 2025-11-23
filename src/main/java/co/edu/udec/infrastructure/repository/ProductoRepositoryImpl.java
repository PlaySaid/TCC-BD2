package co.edu.udec.infrastructure.repository;

import co.edu.udec.domain.model.Producto;
import co.edu.udec.domain.ports.out.ProductoRepository;
import co.edu.udec.infrastructure.config.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements ProductoRepository {

    @Override
    public Producto obtenerPorId(int id) {
        String sql = "SELECT * FROM productos WHERE id_producto=?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio_compra"),
                        rs.getDouble("precio_venta"),
                        rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error obtener producto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio_compra"),
                        rs.getDouble("precio_venta"),
                        rs.getInt("stock")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listar productos: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean guardar(Producto producto) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio_compra, precio_venta, stock, id_categoria) VALUES (?, ?, ?, ?, ?, 1)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio_compra());
            stmt.setDouble(4, producto.getPrecio_venta());
            stmt.setInt(5, producto.getStock());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error guardar producto: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizar(Producto producto) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio_compra=?, precio_venta=?, stock=? WHERE id_producto=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio_compra());
            stmt.setDouble(4, producto.getPrecio_venta());
            stmt.setInt(5, producto.getStock());
            stmt.setInt(6, producto.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar producto: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE id_producto=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar producto: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean actualizarStock(int idProducto, int nuevoStock) {
        String sql = "UPDATE productos SET stock=? WHERE id_producto=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nuevoStock);
            stmt.setInt(2, idProducto);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar stock: " + e.getMessage());
        }

        return false;
    }
}
