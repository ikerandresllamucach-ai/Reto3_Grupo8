package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestorModelo {
	
	public void ConexionBD() {
		System.out.println("Hola, bienvenido a nuestro . ");
		try {
			Connection conexion = Conexion.getConexion();       
			if (conexion != null) {
				System.out.println("¡Conexión a la base de datos exitosa!");	
				conexion.close();
			}
		} catch (SQLException e) {
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
	    public String comprobarAutenticacion(String usuario, String contrasena) {
	        String sql = "SELECT Tipo FROM cliente WHERE Usuario = ? AND Contraseña = ?";
	        try (Connection con = Conexion.getConexion();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, usuario);
	            pst.setString(2, contrasena);
	            try (ResultSet rs = pst.executeQuery()) {
	                if (rs.next()) {
	                    String tipoBD = rs.getString("Tipo");
	                    if (usuario.startsWith("ADM_")) {
	                        return "Admin"; 
	                    }
	                    return tipoBD; 
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error en la autenticación: " + e.getMessage());
	        }
	        return null;
	    }
	}
	
	public static class RegistrarUsuario {
		public boolean registrarUsuario(String nombre, String apellidos, String user, String pass, 
				String fNaci, String fReg, String tipo, String idioma, String fCaducidad) {
			String sqlCliente = "INSERT INTO Cliente (Nombre, Apellidos, Usuario, Contraseña, Fecha_Nacimiento, Fecha_Registro, Tipo, Idioma) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
				System.err.println("Error obtaining usuario: " + e.getMessage());
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
	
	public static class ObtenerArtista {
		public List<String[]> obtenerListaDetallada() {
			List<String[]> resultado = new ArrayList<>();
			String sql = "(SELECT a.ID_Artista, a.NombreArtistico, SUM(au.NReproducciones) as Total "
			           + "FROM artista a "
			           + "JOIN musico m ON a.ID_Artista = m.ID_Musico "
			           + "JOIN album al ON m.ID_Musico = al.ID_Musico "
			           + "JOIN cancion c ON al.ID_Album = c.ID_Album "
			           + "JOIN audio au ON c.ID_Cancion = au.ID_Audio "
			           + "GROUP BY a.ID_Artista, a.NombreArtistico) ";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql);
			     ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					resultado.add(new String[]{
						rs.getString("ID_Artista"),
						rs.getString("NombreArtistico"),
						String.format("%.0f", rs.getDouble("Total"))
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resultado;
		}
	}
	
	public static class ObtenerAlbumesArtista {
		public String getAlbumes(String idArtista) {
			StringBuilder resultado = new StringBuilder();
			String sql = "SELECT al.Titulo, al.Fecha_Publicacion, COUNT(c.ID_Cancion) as NumCanciones "
			           + "FROM album al "
			           + "JOIN cancion c ON al.ID_Album = c.ID_Album "
			           + "WHERE al.ID_Musico = ? "
			           + "GROUP BY al.ID_Album, al.Titulo, al.Fecha_Publicacion";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, idArtista);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						resultado.append(rs.getString("Titulo"))
						         .append(" - ")
						         .append(rs.getString("Fecha_Publicacion"))
						         .append(" - ")
						         .append(rs.getInt("NumCanciones"))
						         .append(" canciones\n");
					}
				}
			} catch (SQLException e) {
				System.err.println("Error obtaining álbumes: " + e.getMessage());
			}
			return resultado.toString();
		}

		public List<String[]> getAlbumesDetallados(String idArtista) {
			List<String[]> resultado = new ArrayList<>();
			String sql = "SELECT al.ID_Album, al.Titulo, al.Fecha_Publicacion, COUNT(c.ID_Cancion) as NumCanciones "
			           + "FROM album al "
			           + "JOIN cancion c ON al.ID_Album = c.ID_Album "
			           + "WHERE al.ID_Musico = ? "
			           + "GROUP BY al.ID_Album, al.Titulo, al.Fecha_Publicacion";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, idArtista);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						resultado.add(new String[]{
							rs.getString("ID_Album"),
							rs.getString("Titulo"),
							rs.getString("Fecha_Publicacion"),
							rs.getString("NumCanciones")
						});
					}
				}
			} catch (SQLException e) {
				System.err.println("Error obteniendo álbumes detallados: " + e.getMessage());
			}
			return resultado;
		}
	}

	public static class ObtenerInfoArtista {
		public Artista getInfo(String idArtista) {
			String sql = "SELECT a.NombreArtistico, a.Genero, a.Descripcion "
			           + "FROM artista a WHERE a.ID_Artista = ?";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, idArtista);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						return new Artista(
							idArtista,
							rs.getString("NombreArtistico"),
							rs.getString("Genero"),
							rs.getString("Descripcion")
						);
					}
				}
			} catch (SQLException e) {
				System.err.println("Error obteniendo info artista: " + e.getMessage());
			}
			return null;
		}
	}
	
	public static class ObtenerCancionesAlbum {
		public String getCanciones(String idAlbum) {
			StringBuilder resultado = new StringBuilder();
			String sql = "SELECT a.Nombre, a.NReproducciones, a.Duracion "
			           + "FROM audio a "
			           + "JOIN cancion c ON c.ID_Cancion = a.ID_Audio "
			           + "JOIN album al ON al.ID_Album = c.ID_Album "
			           + "WHERE al.ID_Album = ? "
			           + "ORDER BY a.Nombre";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, idAlbum);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						resultado.append(rs.getString("Nombre"))
						         .append(" - ")
						         .append(rs.getInt("NReproducciones"))
						         .append(" reprod - ")
						         .append(rs.getInt("Duracion"))
						         .append(" s\n");
					}
				}
			} catch (SQLException e) {
				System.err.println("Error obteniendo canciones: " + e.getMessage());
			}
			return resultado.toString();
		}
	}

	public static class ObtenerInfoAlbum {
		public String[] getInfo(String idAlbum) {
			String sql = "SELECT Titulo, Genero, Fecha_Publicacion FROM album WHERE ID_Album = ?";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, idAlbum);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						return new String[]{
							rs.getString("Titulo"),
							rs.getString("Genero"),
							rs.getString("Fecha_Publicacion")
						};
					}
				}
			} catch (SQLException e) {
				System.err.println("Error obteniendo info álbum: " + e.getMessage());
			}
			return null;
		}
	}
	
	public static class ObtenerInfoCancion {
		public String[] getInfo(String idCancion) {
			String sql = "SELECT au.Nombre, au.Duracion, a.NombreArtistico, al.Titulo "
			           + "FROM audio au "
			           + "JOIN cancion c ON c.ID_Cancion = au.ID_Audio "
			           + "JOIN album al ON al.ID_Album = c.ID_Album "
			           + "JOIN artista a ON a.ID_Artista = al.ID_Musico "
			           + "WHERE au.ID_Audio = ?";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, idCancion);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						return new String[]{
							rs.getString("Nombre"),           
							rs.getString("Nombre"),         
							rs.getString("Duracion"),       
							rs.getString("NombreArtistico"),  
							rs.getString("Titulo")           
						};
					}
				}
			} catch (SQLException e) {
				System.err.println("Error obteniendo info canción: " + e.getMessage());
			}
			return null;
		}
	}
	
	public static class ObtenerCancionesConId {
		public List<String[]> getCanciones(String idAlbum) {
			List<String[]> resultado = new ArrayList<>();
			String sql = "SELECT au.ID_Audio, au.Nombre, au.Duracion "
			           + "FROM audio au "
			           + "JOIN cancion c ON c.ID_Cancion = au.ID_Audio "
			           + "JOIN album al ON al.ID_Album = c.ID_Album "
			           + "WHERE al.ID_Album = ? "
			           + "ORDER BY au.Nombre";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, idAlbum);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						resultado.add(new String[]{
							rs.getString("ID_Audio"),
							rs.getString("Nombre"),
							rs.getString("Duracion")
						});
					}
				}
			} catch (SQLException e) {
				System.err.println("Error: " + e.getMessage());
			}
			return resultado;
		}
	}

	public static class AnadirFavorito {
		public boolean añadir(String idCliente, String idAudio) {
			String sql = "INSERT IGNORE INTO favoritos (ID_Cliente, ID_Audio) VALUES (?, ?)";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, idCliente);
				pst.setString(2, idAudio);
				return pst.executeUpdate() > 0;
			} catch (SQLException e) {
				System.err.println("Error añadiendo favorito: " + e.getMessage());
			}
			return false;
		}
	}
	
	public static class ObtenerPlaylists {
		public List<String[]> getPlaylists(String idCliente) {
			List<String[]> resultado = new ArrayList<>();
			String sql = "SELECT ID_List, Titulo FROM playlist WHERE ID_Cliente = ?";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, idCliente);
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						resultado.add(new String[]{
							rs.getString("ID_List"),
							rs.getString("Titulo")
						});
					}
				}
			} catch (SQLException e) {
				System.err.println("Error obteniendo playlists: " + e.getMessage());
			}
			return resultado;
		}
	}
	
	public static class AnadirAPlaylist {
		public boolean añadir(String idPlaylist, String idCancion) {
			String sql = "INSERT IGNORE INTO playlist_canciones (ID_List, ID_Cancion, Fecha_Playlist_Cancion) "
			           + "VALUES (?, ?, CURDATE())";
			try (Connection con = Conexion.getConexion();
			     PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, idPlaylist);
				pst.setString(2, idCancion);
				return pst.executeUpdate() > 0;
			} catch (SQLException e) {
				System.err.println("Error añadiendo a playlist: " + e.getMessage());
			}
			return false;
		}
	}
	
	public static class CrearPlaylist {
	    public boolean crear(String idCliente, String titulo) {
	        String sqlMaxId = "SELECT MAX(ID_List) FROM playlist";
	        String sqlInsert = "INSERT INTO playlist (ID_List, Titulo, Fecha_Creacion, ID_Cliente) VALUES (?, ?, CURDATE(), ?)";
	        try (Connection con = Conexion.getConexion()) {
	            int siguienteId = 100000001; 
	            try (PreparedStatement pstMax = con.prepareStatement(sqlMaxId);
	                 ResultSet rs = pstMax.executeQuery()) {
	                if (rs.next() && rs.getInt(1) != 0) {
	                    siguienteId = rs.getInt(1) + 1;
	                }
	            }
	            try (PreparedStatement pstInsert = con.prepareStatement(sqlInsert)) {
	                pstInsert.setInt(1, siguienteId);
	                pstInsert.setString(2, titulo);
	                pstInsert.setString(3, idCliente);
	                return pstInsert.executeUpdate() > 0;
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al crear playlist: " + e.getMessage());
	        }
	        return false;
	    }
	}
	
	public static class BorrarPlaylist {
	    public boolean borrar(String idPlaylist) {
	        String sql = "DELETE FROM playlist WHERE ID_List = ?";
	        try (Connection con = Conexion.getConexion();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, idPlaylist);
	            return pst.executeUpdate() > 0;
	        } catch (SQLException e) {
	            System.err.println("Error al borrar playlist: " + e.getMessage());
	        }
	        return false;
	    }
	}
	
	public static class ExportarPlaylistsModelo {
	    public List<String[]> obtenerCancionesDePlaylist(String idPlaylist) {
	        List<String[]> canciones = new ArrayList<>();
	        String sql = "SELECT ID_Cancion FROM playlist_canciones WHERE ID_List = ?";
	        try (Connection con = Conexion.getConexion();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, idPlaylist);
	            try (ResultSet rs = pst.executeQuery()) {
	                while (rs.next()) {
	                    canciones.add(new String[]{rs.getString("ID_Cancion")});
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al exportar canciones de playlist: " + e.getMessage());
	        }
	        return canciones;
	    }
	}

	public static class ImportarPlaylistsModelo {
	    public void limpiarYRestaurar(String idCliente, List<String> lineasFichero) {
	        String sqlDeleteIncluye = "DELETE FROM playlist_canciones WHERE ID_List IN (SELECT ID_List FROM playlist WHERE ID_Cliente = ?)";
	        String sqlDeletePlaylists = "DELETE FROM playlist WHERE ID_Cliente = ?";
	        String sqlMaxId = "SELECT MAX(ID_List) FROM playlist";
	        String sqlInsertPlaylist = "INSERT INTO playlist (ID_List, Titulo, Fecha_Creacion, ID_Cliente) VALUES (?, ?, CURDATE(), ?)";
	        String sqlInsertIncluye = "INSERT INTO playlist_canciones (ID_List, ID_Cancion, Fecha_Playlist_Cancion) VALUES (?, ?, CURDATE())";
	        try (Connection con = Conexion.getConexion()) {
	            con.setAutoCommit(false);
	            try {
	                try (PreparedStatement pstDelInc = con.prepareStatement(sqlDeleteIncluye)) {
	                    pstDelInc.setString(1, idCliente);
	                    pstDelInc.executeUpdate();
	                }
	                try (PreparedStatement pstDelPl = con.prepareStatement(sqlDeletePlaylists)) {
	                    pstDelPl.setString(1, idCliente);
	                    pstDelPl.executeUpdate();
	                }
	                int siguienteId = 100000001;
	                try (PreparedStatement pstMax = con.prepareStatement(sqlMaxId);
	                     ResultSet rs = pstMax.executeQuery()) {
	                    if (rs.next() && rs.getInt(1) != 0) {
	                        siguienteId = rs.getInt(1) + 1;
	                    }
	                }
	                int idPlaylistActual = siguienteId;
	                for (String linea : lineasFichero) {
	                    if (linea.startsWith("[PLAYLIST]")) {
	                        String titulo = linea.replace("[PLAYLIST]", "").trim();
	                        try (PreparedStatement pstInsPl = con.prepareStatement(sqlInsertPlaylist)) {
	                            pstInsPl.setInt(1, idPlaylistActual);
	                            pstInsPl.setString(2, titulo);
	                            pstInsPl.setString(3, idCliente);
	                            pstInsPl.executeUpdate();
	                        }
	                        idPlaylistActual++;
	                    } else if (!linea.trim().isEmpty()) {
	                        String idCancion = linea.trim();
	                        try (PreparedStatement pstInsInc = con.prepareStatement(sqlInsertIncluye)) {
	                            pstInsInc.setInt(1, idPlaylistActual - 1);
	                            pstInsInc.setString(2, idCancion);
	                            pstInsInc.executeUpdate();
	                        }
	                    }
	                }
	                con.commit();
	            } catch (SQLException ex) {
	                con.rollback();
	                throw ex;
	            }
	        } catch (SQLException e) {
	            System.err.println("Error en la importación de datos: " + e.getMessage());
	            throw new RuntimeException(e);
	        }
	    }
	}
	
	public static class ObtenerCancionesPlaylistModelo {
	    public List<String[]> getCanciones(String idPlaylist) {
	        List<String[]> canciones = new ArrayList<>();
	        String sql = "SELECT a.* FROM audio a " +
	                     "JOIN cancion c ON a.ID_Audio = c.ID_Cancion " +
	                     "JOIN playlist_canciones pc ON c.ID_Cancion = pc.ID_Cancion " +
	                     "WHERE pc.ID_List = ?";
	        try (Connection con = Conexion.getConexion();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, idPlaylist);
	            try (ResultSet rs = pst.executeQuery()) {
	                while (rs.next()) {
	                    canciones.add(new String[]{rs.getString(1), rs.getString(2)});
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al obtener canciones de la playlist: " + e.getMessage());
	        }
	        return canciones;
	    }
	}
	
	public static class BorrarCancionPlaylistModelo {
	    public boolean borrarCancion(String idPlaylist, String idCancion) {
	        String sql = "DELETE FROM playlist_canciones WHERE ID_List = ? AND ID_Cancion = ?";
	        try (Connection con = Conexion.getConexion();
	             PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, idPlaylist);
	            pst.setString(2, idCancion);
	            return pst.executeUpdate() > 0;
	        } catch (SQLException e) {
	            System.err.println("Error al eliminar canción de la playlist: " + e.getMessage());
	        }
	        return false;
	    }
	}

	public static class CrearArtista {
		public boolean crear(String nombre, String genero, String descripcion) {
			String sql = "INSERT INTO artista (NombreArtistico, Genero, Descripcion) VALUES (?, ?, ?)";
			try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, nombre);
				pst.setString(2, genero);
				pst.setString(3, descripcion);
				return pst.executeUpdate() > 0;
			} catch (SQLException e) { e.printStackTrace(); return false; }
		}
	}

	public static class ModificarArtista {
		public boolean actualizar(String id, String nombre, String genero, String descripcion) {
			String sql = "UPDATE artista SET NombreArtistico = ?, Genero = ?, Descripcion = ? WHERE ID_Artista = ?";
			try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, nombre);
				pst.setString(2, genero);
				pst.setString(3, descripcion);
				pst.setString(4, id);
				return pst.executeUpdate() > 0;
			} catch (SQLException e) { e.printStackTrace(); return false; }
		}
	}

	public static class BorrarArtista {
		public boolean eliminar(String id) {
			String sql = "DELETE FROM artista WHERE ID_Artista = ?";
			try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, id);
				return pst.executeUpdate() > 0;
			} catch (SQLException e) { e.printStackTrace(); return false; }
		}
	}

	public static class CrearAlbum {
		public boolean crear(String titulo, String genero, String fecha, String idMusico) {
			String sql = "INSERT INTO album (Titulo, Genero, Fecha_Publicacion, ID_Musico) VALUES (?, ?, ?, ?)";
			try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, titulo);
				pst.setString(2, genero);
				pst.setString(3, fecha);
				pst.setString(4, idMusico);
				return pst.executeUpdate() > 0;
			} catch (SQLException e) { e.printStackTrace(); return false; }
		}
	}

	public static class BorrarAlbum {
		public boolean eliminar(String id) {
			String sql = "DELETE FROM album WHERE ID_Album = ?";
			try (Connection con = Conexion.getConexion(); PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setString(1, id);
				return pst.executeUpdate() > 0;
			} catch (SQLException e) { e.printStackTrace(); return false; }
		}
	}

	public static class CrearCancion {
		public boolean crear(String nombre, int duracion, String idAlbum) {
			Connection con = null;
			String sqlAudio = "INSERT INTO audio (Nombre, Duracion, NReproducciones) VALUES (?, ?, 0)";
			String sqlCancion = "INSERT INTO cancion (ID_Cancion, ID_Album) VALUES (?, ?)";
			try {
				con = Conexion.getConexion();
				con.setAutoCommit(false);
				int idGenerado = 0;
				try (PreparedStatement pstA = con.prepareStatement(sqlAudio, Statement.RETURN_GENERATED_KEYS)) {
					pstA.setString(1, nombre);
					pstA.setInt(2, duracion);
					pstA.executeUpdate();
					try (ResultSet rs = pstA.getGeneratedKeys()) {
						if (rs.next()) idGenerado = rs.getInt(1);
					}
				}
				try (PreparedStatement pstC = con.prepareStatement(sqlCancion)) {
					pstC.setInt(1, idGenerado);
					pstC.setString(2, idAlbum);
					pstC.executeUpdate();
				}
				con.commit();
				return true;
			} catch (SQLException e) {
				try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
				e.printStackTrace();
				return false;
			} finally {
				try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
	}

	public static class BorrarCancion {
	    public boolean eliminar(String id) {
	        String sqlCancion = "DELETE FROM cancion WHERE ID_Cancion = ?";
	        String sqlAudio = "DELETE FROM audio WHERE ID_Audio = ?";
	        Connection con = null;
	        try {
	            con = Conexion.getConexion();
	            con.setAutoCommit(false);
	            try (PreparedStatement pstC = con.prepareStatement(sqlCancion)) {
	                pstC.setString(1, id);
	                pstC.executeUpdate();
	            }
	            try (PreparedStatement pstA = con.prepareStatement(sqlAudio)) {
	                pstA.setString(1, id);
	                pstA.executeUpdate();
	            }
	            con.commit();
	            return true;
	        } catch (SQLException e) {
	            try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
	            e.printStackTrace();
	            return false;
	        } finally {
	            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }
	    }
	}

	
	public static class ObtenerEstadisticas {
		public ArrayList<Object[]> obtenerTopBD(String tipoEstadistica, String filtroTiempo) {
			ArrayList<Object[]> lista = new ArrayList<>();
			String query = "";

			double factor;
			switch (filtroTiempo) {
				case "Semana": factor = 0.05; break;
				case "Mes":    factor = 0.22; break; 
				default:       factor = 1.0;  break; 
			}

			if (tipoEstadistica.equals("Top Canción Más Escuchadas")) {
				query = "SELECT art.NombreArtistico, au.Nombre, au.NReproducciones " +
						"FROM audio au " +
						"JOIN cancion c ON au.ID_Audio = c.ID_Cancion " +
						"JOIN album al ON c.ID_Album = al.ID_Album " +
						"JOIN musico m ON al.ID_Musico = m.ID_Musico " +
						"JOIN artista art ON m.ID_Musico = art.ID_Artista " +
						"WHERE au.Tipo = 'Cancion' " +
						"ORDER BY au.NReproducciones DESC LIMIT 10";
			} 
			else if (tipoEstadistica.equals("Top Podcast Más Escuchadas")) {
				query = "SELECT art.NombreArtistico, au.Nombre, au.NReproducciones " +
						"FROM audio au " +
						"JOIN podcast p ON au.ID_Audio = p.ID_Podcast " +
						"JOIN podcaster pod ON p.ID_Podcaster = pod.ID_Podcaster " +
						"JOIN artista art ON pod.ID_Podcaster = art.ID_Artista " +
						"WHERE au.Tipo = 'Podcast' " +
						"ORDER BY au.NReproducciones DESC LIMIT 10";
			} 
			else if (tipoEstadistica.equals("Top Más Escuchados")) {
				query = "SELECT art.NombreArtistico, au.Nombre, au.NReproducciones " +
						"FROM audio au " +
						"LEFT JOIN cancion c ON au.ID_Audio = c.ID_Cancion " +
						"LEFT JOIN album al ON c.ID_Album = al.ID_Album " +
						"LEFT JOIN podcast p ON au.ID_Audio = p.ID_Podcast " +
						"LEFT JOIN artista art ON (al.ID_Musico = art.ID_Artista OR p.ID_Podcaster = art.ID_Artista) " +
						"ORDER BY au.NReproducciones DESC LIMIT 10";
			} 
			else if (tipoEstadistica.equals("Top Playlist")) {
				query = "SELECT cl.Usuario, pl.Titulo " +
						"FROM playlist pl " +
						"JOIN cliente cl ON pl.ID_Cliente = cl.ID_Cliente " +
						"ORDER BY pl.Fecha_Creacion DESC LIMIT 10";
			}

			try (Connection con = Conexion.getConexion();
				 PreparedStatement pst = con.prepareStatement(query)) {
				
				try (ResultSet rs = pst.executeQuery()) {
					int posicion = 1;
					while (rs.next()) {
						if (tipoEstadistica.equals("Top Playlist")) {
							lista.add(new Object[]{
								String.valueOf(posicion++),
								rs.getString("Usuario"),
								rs.getString("Titulo"),
								"Activa"
							});
						} else {
							String creador = rs.getString("NombreArtistico") != null ? rs.getString("NombreArtistico") : "Desconocido";
							int reprosSimuladas = (int) (rs.getInt("NReproducciones") * factor);
							lista.add(new Object[]{
								String.valueOf(posicion++),
								creador,
								rs.getString("Nombre"),
								String.format("%,d", reprosSimuladas)
							});
						}
					}
				}
			} catch (SQLException e) {
				System.err.println("Error extrayendo estadísticas de BD: " + e.getMessage());
			}
			return lista;
		}
	}
}