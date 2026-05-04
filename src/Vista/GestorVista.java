package Vista;

import Controlador.GestorControlador;


public class GestorVista {

    private Login login;
    private Menu menu;
    private Registro registro;

    public void mostrarLogin(GestorControlador controlador) {
        cerrarTodo();
        login = new Login(controlador);
        login.setVisible(true);
    }

    public void mostrarMenu(GestorControlador controlador, String nombreUsuario	) {
        cerrarTodo();
        menu = new Menu(nombreUsuario, controlador);
        menu.setVisible(true);
    }

    public void mostrarPerfil(GestorControlador controlador) {
        cerrarTodo();
        registro = new Registro(controlador);
        registro.setVisible(true);
    }


    private void cerrarTodo() {
        if (login != null)    { login.dispose();    login = null; }
        if (menu != null)     { menu.dispose();     menu = null; }
        if (registro != null) { registro.dispose(); registro = null; }
    }
}