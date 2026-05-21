package Modelo;

public class Album {
	private String Id_Album;
	private String Titulo;
	private String Fecha_Publicacion;
	private String Genero;
	
	public Album(String id_album, String titulo_album, String publicacion_album, String genero_album) {
		Id_Album = id_album;
		Titulo = titulo_album;
		Fecha_Publicacion = publicacion_album;
		Genero = genero_album;
	}
	
	public Album() {
		Id_Album = null;
		Titulo = null;
		Fecha_Publicacion = null;
		Genero = null;
	}	
	
	public Album(Album c) {
		Id_Album = c.Id_Album;
		Titulo = c.Titulo;
		Fecha_Publicacion = c.Fecha_Publicacion;
		Genero = c.Genero;
	}

	public String getId_Album() {
		return Id_Album;
	}

	public void setId_Album(String id_Album) {
		Id_Album = id_Album;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getFecha_Publicacion() {
		return Fecha_Publicacion;
	}

	public void setFecha_Publicacion(String fecha_Publicacion) {
		Fecha_Publicacion = fecha_Publicacion;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}
	
	
}
