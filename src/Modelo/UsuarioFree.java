package Modelo;

public class UsuarioFree extends Usuario{
	
	public UsuarioFree(String id_Usuario, String nombre, String apellidos, String nombre_usuario, String contraseña,
			String fecha_Nacimiento, String fecha_Registro, String tipo) {
		super(id_Usuario, nombre, apellidos, nombre_usuario, contraseña, fecha_Nacimiento, fecha_Registro, tipo);

	}
	
	public UsuarioFree() {
		super();
	}

	
	public UsuarioFree(Usuario c) {
		super(c);
	
	}
	
	
}
