package Modelo;

public class UsuarioPremiun extends Usuario{
	private String Fecha_Caducidad;

	public UsuarioPremiun(String id_Usuario, String nombre, String apellidos, String nombre_usuario, String contraseña,
			String fecha_Nacimiento, String fecha_Registro, String tipo, String fecha_Caducidad) {
		super(id_Usuario, nombre, apellidos, nombre_usuario, contraseña, fecha_Nacimiento, fecha_Registro, tipo);
		Fecha_Caducidad = fecha_Caducidad;
	}
	
	public UsuarioPremiun() {
		super();
		this.Fecha_Caducidad = null;
	}
	
	public UsuarioPremiun(UsuarioPremiun c) {
		super(c);
		Fecha_Caducidad = c.Fecha_Caducidad;
	}

	public String getFecha_Caducidad() {
		return Fecha_Caducidad;
	}

	public void setFecha_Caducidad(String fecha_Caducidad) {
		Fecha_Caducidad = fecha_Caducidad;
	}
	
	
}
