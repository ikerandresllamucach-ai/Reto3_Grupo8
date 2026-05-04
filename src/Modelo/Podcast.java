package Modelo;

public class Podcast extends Audio {
	private int Numero_Colaboradores;

	public Podcast(String id_Audio, String nombre_audio, int duracion_audio, String tipo_audio,
			int reproducciones_audio, int num_Colab_audio) {
		super(id_Audio, nombre_audio, duracion_audio, tipo_audio, reproducciones_audio);
		Numero_Colaboradores = num_Colab_audio;
	}
	
	public Podcast() {
		super();
		this.Numero_Colaboradores = 0;
	}

	public Podcast(Podcast c) {
		super(c);
		Numero_Colaboradores = c.Numero_Colaboradores;
	}

	public int getNumero_Colaboradores() {
		return Numero_Colaboradores;
	}

	public void setNumero_Colaboradores(int numero_Colaboradores) {
		Numero_Colaboradores = numero_Colaboradores;
	}
	
	
}
