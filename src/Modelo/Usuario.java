package Modelo;

public class Usuario {
	protected String Id_Usuario;
	protected String Nombre;
	protected String Apellidos;
	protected String Nombre_usuario;
	protected String Contraseña;
	protected String Fecha_Nacimiento;
	protected String Fecha_Registro;
	protected String Tipo;
	
	public Usuario(String id_Usuario, String nombre, String apellidos, String nombre_usuario, String contraseña,
			String fecha_Nacimiento, String fecha_Registro, String tipo) {
		Id_Usuario = id_Usuario;
		Nombre = nombre;
		Apellidos = apellidos;
		Nombre_usuario = nombre_usuario;
		Contraseña = contraseña;
		Fecha_Nacimiento = fecha_Nacimiento;
		Fecha_Registro = fecha_Registro;
		Tipo = tipo;
	}

	public Usuario() {
		this.Id_Usuario = null;
		this.Nombre = null;
		this.Apellidos = null;
		this.Nombre_usuario = null;
		this.Contraseña = null;
		this.Fecha_Nacimiento = null;
		this.Fecha_Registro = null;
		this.Tipo = null;
	}
	
	public Usuario(Usuario c) {
		Id_Usuario = c.Id_Usuario;
		Nombre = c.Nombre;
		Apellidos = c.Apellidos;
		Nombre_usuario = c.Nombre_usuario;
		Contraseña = c.Contraseña;
		Fecha_Nacimiento = c.Fecha_Nacimiento;
		Fecha_Registro = c.Fecha_Registro;
		Tipo = c.Tipo;
	}

	public String getId_Usuario() {
		return Id_Usuario;
	}

	public void setId_Usuario(String id_Usuario) {
		Id_Usuario = id_Usuario;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getNombre_usuario() {
		return Nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		Nombre_usuario = nombre_usuario;
	}

	public String getContraseña() {
		return Contraseña;
	}

	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}

	public String getFecha_Nacimiento() {
		return Fecha_Nacimiento;
	}

	public void setFecha_Nacimiento(String fecha_Nacimiento) {
		Fecha_Nacimiento = fecha_Nacimiento;
	}

	public String getFecha_Registro() {
		return Fecha_Registro;
	}

	public void setFecha_Registro(String fecha_Registro) {
		Fecha_Registro = fecha_Registro;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
}
