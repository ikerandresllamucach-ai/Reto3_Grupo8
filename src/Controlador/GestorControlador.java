package Controlador;

import Modelo.GestorModelo;
import Modelo.GestorModelo.ObtenerUsuario;
import Modelo.Usuario;
import Vista.GestorVista;

public class GestorControlador {
	
	private GestorModelo modelo;
	private GestorVista gestorV;
	private GestorModelo.LoginModelo log;
	private GestorModelo.ObtenerUsuario datosUser;
	private GestorModelo.ActualizarUsuario updateUser;
	private String usuarioActual;
	
	public GestorControlador() {
		modelo = new GestorModelo();
		gestorV = new GestorVista();
		log = new GestorModelo.LoginModelo();
		datosUser = new ObtenerUsuario();
		updateUser =  new GestorModelo.ActualizarUsuario();

	}

	public void iniciarLogin() {
		modelo.ConexionBD();
		gestorV.mostrarLogin(this);		
	}
	
	public String inicioSesion(String usuario, String contraseña) {
		String tipo =  log.validarUsuario(usuario,contraseña);
		if (tipo != null) {
			this.usuarioActual = usuario;
		}
		return tipo;
	}
	
	public String getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(String usuario) {
		this.usuarioActual = usuario;
	}
	
	public Usuario pedirDatosPerfil() {
        
        Usuario u = datosUser.datosUsuario(usuarioActual);
        if (u != null) {
            this.usuarioActual = u.getNombre_usuario();
        }
        return u;
    }
	

	public boolean actualizarUsuario(String id, String nombre, String apellidos,
            String usuario, String pass, String idioma) {
		
		boolean exito = updateUser.actualizarUsuario(id, nombre, apellidos, usuario, pass, idioma);
		
		if (exito) {
			this.usuarioActual = usuario;
			}
		return exito;
	}
	
	 public void abrirMenu() {
	        gestorV.mostrarMenu(this,usuarioActual);
	    }

	    public void abrirPerfil() {
	        gestorV.mostrarPerfil(this);
	    }

	 
	    public void abrirLogin() {
	        gestorV.mostrarLogin(this);
	    }
}
