package Modelo;

public class Podcaster extends Artista{
	
	public Podcaster(String id_art, String nombre_art, String genero_art, String descripcion_art) {
		super(id_art, nombre_art, genero_art, descripcion_art);
	}
	
	public Podcaster() {
		super();
	}

	public Podcaster(Podcaster c) {
		super(c);
	}
	
	
}
