package Modelo;

public class Playlist {
	private int Id_list;
	private String Titulo;
	private String Fecha_Creacion;
	
	public Playlist(int id_list, String titulo, String fecha_Creacion) {
		Id_list = id_list;
		Titulo = titulo;
		Fecha_Creacion = fecha_Creacion;
	}

	public Playlist() {
		Id_list = 0;
		Titulo = null;
		Fecha_Creacion = null;
	}

	public int getId_list() {
		return Id_list;
	}

	public void setId_list(int id_list) {
		Id_list = id_list;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getFecha_Creacion() {
		return Fecha_Creacion;
	}

	public void setFecha_Creacion(String fecha_Creacion) {
		Fecha_Creacion = fecha_Creacion;
	}
	
	
}
