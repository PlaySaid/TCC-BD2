package co.edu.udec.infrastructure.repository;

import co.edu.udec.domain.model.Usuario;
import co.edu.udec.domain.ports.out.UsuarioRepository;
import co.edu.udec.infrastructure.config.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Override
    public Usuario buscarPorUsuario(String user) {
        String sql = "SELECT * FROM usuarios WHERE usuario=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getInt("id_rol"),
                        rs.getDate("fecha_creacion"),
                        rs.getBoolean("activo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error buscar usuario: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Usuario obtenerPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getInt("id_rol"),
                        rs.getDate("fecha_creacion"),
                        rs.getBoolean("activo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error obtener usuario: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = MySQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getInt("id_rol"),
                        rs.getDate("fecha_creacion"),
                        rs.getBoolean("activo")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error listar usuarios: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean guardar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, usuario, password, id_rol, fecha_creacion, activo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getPassword());
            stmt.setInt(4, usuario.getIdRol());
            stmt.setDate(5, usuario.getFechaCreacion());
            stmt.setBoolean(6, usuario.isActivo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error guardar usuario: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre=?, usuario=?, password=?, id_rol=?, activo=? WHERE id_usuario=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getPassword());
            stmt.setInt(4, usuario.getIdRol());
            stmt.setBoolean(5, usuario.isActivo());
            stmt.setInt(6, usuario.getIdUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar usuario: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario=?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar usuario: " + e.getMessage());
        }

        return false;
    }
}
