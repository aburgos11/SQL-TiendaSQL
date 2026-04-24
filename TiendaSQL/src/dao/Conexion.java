package dao;

import java.sql.*;

public class Conexion {
	
	private static final String URL = "jdbc:mysql://localhost:3306/tienda"; 
	private static final String USER = "user"; // vuestro usuario
	private static final String PASSWORD = "user"; // vuestra contraseña
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch(SQLException e) {
			System.err.println("No se ha conectado a la base de datos: \n"+e.getMessage());
		}
		return conn;
	}
}
