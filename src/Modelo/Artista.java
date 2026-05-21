package Modelo;

public class Artista {
	
	protected String Id_Artista;
	protected String NombreArtistico;
	protected String Genero;
	protected String Descripcion;
	
	
	
	public Artista() {
		Id_Artista = null;
		NombreArtistico = null;
		Genero = null;
		Descripcion = null;
	}



	public Artista(String id_art, String nombre_art, String genero_art, String descripcion_art) {
		Id_Artista = id_art;
		NombreArtistico = nombre_art;
		Genero = genero_art;
		Descripcion = descripcion_art;
	}



	public Artista(Artista c) {
		Id_Artista = c.Id_Artista;
		NombreArtistico = c.NombreArtistico;
		Genero = c.Genero;
		Descripcion = c.Descripcion;
	}



	public String getId_Artista() {
		return Id_Artista;
	}



	public void setId_Artista(String id_Artista) {
		Id_Artista = id_Artista;
	}



	public String getNombreArtistico() {
		return NombreArtistico;
	}



	public void setNombreArtistico(String nombreArtistico) {
		NombreArtistico = nombreArtistico;
	}



	public String getGenero() {
		return Genero;
	}



	public void setGenero(String genero) {
		Genero = genero;
	}



	public String getDescripcion() {
		return Descripcion;
	}



	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}



	
	
	
	

}
