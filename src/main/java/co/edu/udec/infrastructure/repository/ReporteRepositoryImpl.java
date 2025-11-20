package co.edu.udec.infrastructure.repository;

import co.edu.udec.infrastructure.config.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteRepositoryImpl {

    // Productos con bajo stock
    public List<String> productosBajoStock() {
        List<String> resultado = new ArrayList<>();
        String sql = """
                SELECT nombre, stock
                FROM productos
                WHERE stock <= 5
                ORDER BY stock ASC
                """;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                resultado.add(
                        rs.getString("nombre") + " - Stock: " + rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error reporte bajo stock: " + e.getMessage());
        }

        return resultado;
    }

    // Valor total del inventario
    public double valorInventario() {
        String sql = "SELECT SUM(stock * precio_compra) AS total FROM productos";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) return rs.getDouble("total");

        } catch (SQLException e) {
            System.out.println("Error reporte valor inventario: " + e.getMessage());
        }
        return 0;
    }

    // Ventas por mes
    public List<String> ventasPorMes() {
        List<String> resultado = new ArrayList<>();
        String sql = """
                SELECT 
                    MONTH(fecha) AS mes, 
                    SUM(total) AS total
                FROM ventas
                GROUP BY MONTH(fecha)
                ORDER BY mes
                """;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                resultado.add(
                        "Mes: " + rs.getInt("mes") +
                                " - Ventas: $" + rs.getDouble("total")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error ventas por mes: " + e.getMessage());
        }

        return resultado;
    }

    // Productos más vendidos
    public List<String> productosMasVendidos() {
        List<String> resultado = new ArrayList<>();
        String sql = """
                SELECT p.nombre, SUM(dv.cantidad) AS total_vendido
                FROM detalle_ventas dv
                JOIN productos p ON dv.id_producto = p.id_producto
                GROUP BY dv.id_producto
                ORDER BY total_vendido DESC
                LIMIT 5;
                """;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                resultado.add(
                        rs.getString("nombre") +
                                " - Vendidos: " + rs.getInt("total_vendido")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error productos más vendidos: " + e.getMessage());
        }

        return resultado;
    }
}
