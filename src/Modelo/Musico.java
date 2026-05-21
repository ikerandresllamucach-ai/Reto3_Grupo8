package Modelo;

public class Musico extends Artista{
	private String Caracteristica;

	public Musico(String id_art, String nombre_art, String genero_art, String descripcion_art, String caracteristica) {
		super(id_art, nombre_art, genero_art, descripcion_art);
		this.Caracteristica = caracteristica;
	}

	public Musico() {
		super();
		this.Caracteristica = null;
	}

	public Musico(Musico c) {
		super(c);
		this.Caracteristica = c.Caracteristica;
	}

	public String getCaracteristica() {
		return Caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		Caracteristica = caracteristica;
	}


	
	



	
	
	
}
