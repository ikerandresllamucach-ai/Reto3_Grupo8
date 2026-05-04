package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorModelo {
	
	public void ConexionBD() {
		 
        System.out.println("Hola, bienvenido a nuestro . ");
        
        	try  {
        		Connection conexion = Conexion.getConexion(); 
            
        			if (conexion != null) {
            	
        				System.out.println("¡Conexión a la base de datos exitosa!");	
        				conexion.close();
        			}
        	} 
        	
        	catch (SQLException e) {
        	System.out.println("Error al conectar a la base de datos");
        	e.printStackTrace();
        	
        	}
}
	
	public static class Conexion {
		private static final String URL = "jdbc:mysql://localhost:3306/zpotify_reto3?useSSL=false&serverTimezone=UTC";
		private static final String USER = "root";
		private static final String PASS = "1234";

		public static Connection getConexion() throws SQLException {
			return DriverManager.getConnection(URL, USER, PASS);
		}
	}
	
	public static class LoginModelo {	
		public String validarUsuario(String usuario, String contraseña) {
			String sql = "SELECT Tipo FROM Cliente WHERE Usuario = ? AND Contraseña = ?";
			
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
	                
				pst.setString(1, usuario);
				pst.setString(2, contraseña);
	                
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						return rs.getString("Tipo");
					}
				}     
			} catch (SQLException e) {
				System.err.println("Error en login: " + e.getMessage());
			}		
			return null;
		}
	}
	
	public static class RegistrarUsuario {
		public boolean registrarUsuario(String nombre, String apellidos, String user, String pass, 
				String fNaci, String fReg, String tipo, String idioma, String fCaducidad) {
			
			String sqlCliente = "INSERT INTO Cliente (Nombre, Apellidos, Usuario, Contraseña, Fecha_Nacimiento, Fecha_Registro, Tipo, Idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			String sqlPremium = "INSERT INTO Premium (ID_Cliente, Fecha_Caducidad) VALUES (?, ?)";
	        
			Connection con = null;
			
			try {
				con = Conexion.getConexion();
				con.setAutoCommit(false); 

				PreparedStatement pstCliente = con.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
				pstCliente.setString(1, nombre);
				pstCliente.setString(2, apellidos);
				pstCliente.setString(3, user);
				pstCliente.setString(4, pass);
				pstCliente.setString(5, fNaci);
				pstCliente.setString(6, fReg);
				pstCliente.setString(7, tipo);
				pstCliente.setString(8, idioma);
				pstCliente.executeUpdate();

				

				con.commit();
				return true;

			} catch (SQLException e) {
				try {
					if (con != null) con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				System.err.println("Error en registro: " + e.getMessage());
			} finally {
				try {
					if (con != null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
	}
	
	public static class ObtenerUsuario {
	    public Usuario datosUsuario(String nombreUsuario) {
	        String sql = "SELECT * FROM Cliente WHERE Usuario = ?";
	        
	        try (Connection con = Conexion.getConexion();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            
	            pst.setString(1, nombreUsuario);
	            
	            try (ResultSet rs = pst.executeQuery()) {
	                if (rs.next()) {
	                    return new Usuario(
	                        rs.getString("ID_Cliente"),
	                        rs.getString("Nombre"),
	                        rs.getString("Apellidos"),
	                        rs.getString("Usuario"),
	                        rs.getString("Contraseña"),
	                        rs.getString("Fecha_Nacimiento"),
	                        rs.getString("Fecha_Registro"),
	                        rs.getString("Tipo")
	                    );
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error obteniendo usuario: " + e.getMessage());
	        }
	        return null;
	    }
	}
	
	
	public static class ActualizarUsuario {
	    public boolean actualizarUsuario(String id, String nombre, String apellidos, 
	                                      String usuario, String pass, String idioma) {
	        String sql = "UPDATE Cliente SET Nombre=?, Apellidos=?, Usuario=?, Contraseña=?, Idioma=? WHERE ID_Cliente=?";
	        
	        try (Connection con = Conexion.getConexion();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            
	            pst.setString(1, nombre);
	            pst.setString(2, apellidos);
	            pst.setString(3, usuario);
	            pst.setString(4, pass);
	            pst.setString(5, idioma);
	            pst.setString(6, id);
	            
	            return pst.executeUpdate() > 0;
	            
	        } catch (SQLException e) {
	            System.err.println("Error actualizando usuario: " + e.getMessage());
	        }
	        return false;
	    }
	}
	
}