package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;

public class ClienteDAO {

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = Conexion.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Cliente c = new Cliente(
					rs.getInt("id"), 
					rs.getString("dni"), 
					rs.getString("nombre"),
					rs.getString("telefono"),
                    rs.getString("direccion")
				);
				
				clientes.add(c);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return clientes;
    }

    public boolean addCliente(Cliente c) {
        String sql = "INSERT INTO clientes (dni, nombre, telefono, direccion) VALUES (?,?,?,?)";

        try (Connection conn = Conexion.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getDni());
			pstmt.setString(2, c.getNombre());
			pstmt.setString(3, c.getTelefono());
            pstmt.setString(4, c.getDireccion());
			
			return pstmt.executeUpdate() > 0;

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

        return false;
    }

    public boolean modificarTelefono(String dni, String telefono) {

        String sql = "UPDATE clientes SET telefono = ? WHERE dni = ?";

		try (Connection conn = Conexion.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, telefono);
			pstmt.setString(2, dni);

			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return false;
    }

    public boolean modificarDireccion(String dni, String direccion) {

        String sql = "UPDATE clientes SET direccion = ? WHERE dni = ?";

		try (Connection conn = Conexion.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, direccion);
			pstmt.setString(2, dni);

			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return false;
    }

    public boolean eliminarClienrte(String dni) { // un buen identificador del cliente, a modo de id, sería el DNI

        String sql = "DELETE FROM clientes WHERE dni = ?";

		try (Connection conn = Conexion.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dni);

			return pstmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return false;
    }
}
