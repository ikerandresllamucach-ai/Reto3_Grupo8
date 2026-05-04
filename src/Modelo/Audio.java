package Modelo;

public class Audio {
	
	protected String Id_Audio;
	protected String Nombre;
	protected int Duracion;
	protected String Tipo;
	protected int Numero_Reproducciones;
	
	public Audio(String id_Audio, String nombre_audio, int duracion_audio, String tipo_audio, int reproducciones_audio) {
		
		Id_Audio = id_Audio;
		Nombre = nombre_audio;
		Duracion = duracion_audio;
		Tipo = tipo_audio;
		Numero_Reproducciones = reproducciones_audio;
	}

	public Audio() {
		Id_Audio = null;
		Nombre = null;
		Duracion = 0;
		Tipo = null;
		Numero_Reproducciones = 0;
	}
	
	public Audio(Audio c) {
		Id_Audio = c.Id_Audio;
		Nombre = c.Nombre;
		Duracion = c.Duracion;
		Tipo = c.Tipo;
		Numero_Reproducciones = c.Numero_Reproducciones;
	}

	public String getId_Audio() {
		return Id_Audio;
	}

	public void setId_Audio(String id_Audio) {
		Id_Audio = id_Audio;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getDuracion() {
		return Duracion;
	}

	public void setDuracion(int duracion) {
		Duracion = duracion;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public int getNumero_Reproducciones() {
		return Numero_Reproducciones;
	}

	public void setNumero_Reproducciones(int numero_Reproducciones) {
		Numero_Reproducciones = numero_Reproducciones;
	}
	
	
	
}
