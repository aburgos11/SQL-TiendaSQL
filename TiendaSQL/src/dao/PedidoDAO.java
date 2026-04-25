package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Cliente;
import models.Pedido;
import models.Producto;

public class PedidoDAO {

    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente("", "", "", "");
                c.setId(rs.getInt("id_cliente"));

                Producto prod = new Producto("", 0, 0);
                prod.setId(rs.getInt("id_producto"));

                Pedido p = new Pedido(c, prod, rs.getInt("cantidad"), rs.getDate("fecha").toLocalDate());
                p.setId(rs.getInt("id"));

                pedidos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public boolean addPedido(Pedido p) {
        String sql = "INSERT INTO pedidos (id_cliente, id_producto, cantidad, fecha) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, p.getCliente().getId());
            pstmt.setInt(2, p.getProducto().getId());
            pstmt.setInt(3, p.getCantidad());
            pstmt.setDate(4, java.sql.Date.valueOf(p.getFecha()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarPedido(int id_pedido, int cantidad, LocalDate fecha) {
        String sql = "UPDATE pedidos SET cantidad = ?, fecha = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cantidad);
            pstmt.setDate(2, java.sql.Date.valueOf(fecha));
            pstmt.setInt(3, id_pedido);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean cancelarPedido(int id_pedido) {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_pedido);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
