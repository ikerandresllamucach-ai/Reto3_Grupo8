package Modelo;

public class Cancion extends Audio{
	private String Artistas_Invitados ;

	public Cancion(String foto,String id_Audio, String nombre_audio, int duracion_audio, String tipo_audio,
			int reproducciones_audio, String cancion_Invitados, boolean reproduccion) {
		super(foto,id_Audio, nombre_audio, duracion_audio, tipo_audio, reproducciones_audio, reproduccion);
		Artistas_Invitados = cancion_Invitados;
	}
	
	public Cancion() {
		super();
		this.Artistas_Invitados = null;
	}
	
	public Cancion(Cancion c) {
		super(c);
		Artistas_Invitados = c.Artistas_Invitados;
	}
	
}
