package Vista;

import Controlador.GestorControlador;

public class GestorVista {

    private Login login;
    private Menu menu;
    private Registro registro;
    private infoArtista infoArtistaVentana;
    private vAlbum vAlbum;
    private Artistas artistas;
    private vReproduccion vRep;
    private Playlists playlists;
    private vCancionesPlaylist cancionesPlaylist;
    private menuAdmin menuAdmin;
    private vEstadisticas vEstadisticas;
    private gestionMusica gestionMusica;
    private gestionPodcast gestionPodcast;
    
    private vCrearArtista vCrearArt;
    private vModificarArtista vModifArt;
    private vEliminarArtista vElimArt;
    private vCrearAlbum vCrearAlb;
    private vModificarAlbum vModifAlb;
    private vEliminarAlbum vElimAlb;
    private vCrearCancion vCrearCan;
    private vModificarCancion vModifCan;
    private vEliminarCancion vElimCan;
    
    private vCrearPodcast vCrearPod;
    private vModificarPodcast vModifPod;
    private vEliminarPodcast vElimPod;
    private vCrearTemporada vCrearTemp;
    private vModificarTemporada vModifTemp;
    private vEliminarTemporada vElimTemp;
    private vCrearEpisodio vCrearEp;
    private vModificarEpisodio vModifEp;
    private vEliminarEpisodio vElimEp;
    private vVistaEstadisticasDetalle vVistaEstadisticasDetalle;
    private vPodcasters vPodcasters; 
    
    public void mostrarLogin(GestorControlador controlador) {
        cerrarTodo();
        login = new Login(controlador);
        login.setVisible(true);
    }

    public void mostrarMenu(GestorControlador controlador) {
        cerrarTodo();
        menu = new Menu(controlador);
        menu.setVisible(true);
    }

    public void mostrarPerfil(GestorControlador controlador) {
        cerrarTodo();
        registro = new Registro(controlador, true);
        registro.setVisible(true);
    }

    public void mostrarRegistro(GestorControlador controlador) {
        cerrarTodo();
        registro = new Registro(controlador, false);
        registro.setVisible(true);
    }

    public void mostrarArtistas(GestorControlador controlador) {
        cerrarTodo();
        artistas = new Artistas(controlador);
        artistas.setVisible(true);
    }

    public void mostrarInfoArtista(GestorControlador controlador) {
        cerrarTodo();
        String imagen = controlador.pedirImagenArtista();
        infoArtistaVentana = new infoArtista(controlador, imagen);
        infoArtistaVentana.setVisible(true);
    }

    public void mostrarValbum(GestorControlador controlador) {
        cerrarTodo();
        vAlbum = new vAlbum(controlador);
        vAlbum.setVisible(true);
    }

    public void mostrarReproduccion(GestorControlador controlador) {
        cerrarTodo();
        vRep = new vReproduccion(controlador);
        vRep.setVisible(true);
    }
    
    public void mostrarPlaylists(GestorControlador controlador) {
        cerrarTodo();
        playlists = new Playlists(controlador);
        playlists.setVisible(true);
    }
    
    public void mostrarCancionesPlaylist(GestorControlador controlador) {
        cerrarTodo();
        cancionesPlaylist = new vCancionesPlaylist(controlador);
        cancionesPlaylist.setVisible(true);
    }
    
    public void mostrarMenuAdmin(GestorControlador controlador) {
        cerrarTodo();
        menuAdmin = new menuAdmin(controlador);
        menuAdmin.setVisible(true);
    }
    
    public void mostrarError(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrarGestionMusica(GestorControlador controlador) {
        cerrarTodo();
        gestionMusica = new gestionMusica(controlador);
        gestionMusica.setVisible(true);
    }
    
    public void mostrarGestionPodcasts(GestorControlador controlador) {
        cerrarTodo();
        gestionPodcast = new gestionPodcast(controlador);
        gestionPodcast.setVisible(true);
    }

    public void mostrarEstadisticas(GestorControlador controlador) {
        cerrarTodo();
        vEstadisticas = new vEstadisticas(controlador);
        vEstadisticas.setVisible(true);
    }
    
    public void mostrarCrearArtista(GestorControlador controlador) {
        cerrarTodo();
        vCrearArt = new vCrearArtista(controlador);
        vCrearArt.setVisible(true);
    }

    public void mostrarModificarArtista(GestorControlador controlador) {
        cerrarTodo();
        vModifArt = new vModificarArtista(controlador);
        vModifArt.setVisible(true);
    }
    
    public void mostrarEliminarArtista(GestorControlador controlador) {
        cerrarTodo();
        vElimArt = new vEliminarArtista(controlador);
        vElimArt.setVisible(true);
    }

    public void mostrarCrearAlbum(GestorControlador controlador) {
        cerrarTodo();
        vCrearAlb = new vCrearAlbum(controlador);
        vCrearAlb.setVisible(true);
    }
    
    public void mostrarModificarAlbum(GestorControlador controlador) {
        cerrarTodo();
        vModifAlb = new vModificarAlbum(controlador);
        vModifAlb.setVisible(true);
    }
    
    public void mostrarEliminarAlbum(GestorControlador gestorControlador) {
        cerrarTodo();
        vElimAlb = new vEliminarAlbum(gestorControlador);
        vElimAlb.setVisible(true);
    }
    
    public void mostrarCrearCancion(GestorControlador gestorControlador) {
        cerrarTodo();
        vCrearCan = new vCrearCancion(gestorControlador);
        vCrearCan.setVisible(true);
    }
    
    public void mostrarModificarCancion(GestorControlador gestorControlador) {
        cerrarTodo();
        vModifCan = new vModificarCancion(gestorControlador);
        vModifCan.setVisible(true);
    }

    public void mostrarEliminarCancion(GestorControlador gestorControlador) {
        cerrarTodo();
        vElimCan = new vEliminarCancion(gestorControlador);
        vElimCan.setVisible(true);
    }
    
    public void mostrarCrearPodcast(GestorControlador controlador) {
        cerrarTodo();
        vCrearPod = new vCrearPodcast(controlador);
        vCrearPod.setVisible(true);
    }

    public void mostrarModificarPodcast(GestorControlador controlador) {
        cerrarTodo();
        vModifPod = new vModificarPodcast(controlador);
        vModifPod.setVisible(true);
    }

    public void mostrarEliminarPodcast(GestorControlador controlador) {
        cerrarTodo();
        vElimPod = new vEliminarPodcast(controlador);
        vElimPod.setVisible(true);
    }

    public void mostrarCrearTemporada(GestorControlador controlador) {
        cerrarTodo();
        vCrearTemp = new vCrearTemporada(controlador);
        vCrearTemp.setVisible(true);
    }

    public void mostrarModificarTemporada(GestorControlador controlador) {
        cerrarTodo();
        vModifTemp = new vModificarTemporada(controlador);
        vModifTemp.setVisible(true);
    }

    public void mostrarEliminarTemporada(GestorControlador controlador) {
        cerrarTodo();
        vElimTemp = new vEliminarTemporada(controlador);
        vElimTemp.setVisible(true);
    }

    public void mostrarCrearEpisodio(GestorControlador controlador) {
        cerrarTodo();
        vCrearEp = new vCrearEpisodio(controlador);
        vCrearEp.setVisible(true);
    }

    public void mostrarModificarEpisodio(GestorControlador controlador) {
        cerrarTodo();
        vModifEp = new vModificarEpisodio(controlador);
        vModifEp.setVisible(true);
    }

    public void mostrarEliminarEpisodio(GestorControlador controlador) {
        cerrarTodo();
        vElimEp = new vEliminarEpisodio(controlador);
        vElimEp.setVisible(true);
    }

    public void mostrarVistaEstadisticaDetalle(GestorControlador controlador) {
        mostrarVistaEstadisticaDetalle(controlador, "Top Canción Más Escuchadas", "Año");
    }
	
    public void mostrarVistaEstadisticaDetalle(GestorControlador controlador, String tipo, String tiempo) {
        cerrarTodo();
        vVistaEstadisticasDetalle = new vVistaEstadisticasDetalle(controlador, tipo, tiempo);
        vVistaEstadisticasDetalle.setVisible(true);
    }

    public void mostrarPodcasters(GestorControlador controlador) {
        cerrarTodo();
        vPodcasters = new vPodcasters(controlador);
        vPodcasters.setVisible(true);
    }
    
    private void cerrarTodo() {
        if (login != null)              { login.dispose();              login = null; }
        if (menu != null)               { menu.dispose();               menu = null; }
        if (registro != null)           { registro.dispose();           registro = null; }
        if (artistas != null)           { artistas.dispose();            artistas = null; }
        if (infoArtistaVentana != null) { infoArtistaVentana.dispose(); infoArtistaVentana = null; }
        if (vAlbum != null)             { vAlbum.dispose();             vAlbum = null; }
        if (vRep != null)               { vRep.dispose();               vRep = null; }
        if (playlists != null)          { playlists.dispose();          playlists = null; }
        if (cancionesPlaylist != null)  { cancionesPlaylist.dispose();  cancionesPlaylist = null; }
        if (menuAdmin != null)          { menuAdmin.dispose();          menuAdmin = null; }
        if (vEstadisticas != null)      { vEstadisticas.dispose();      vEstadisticas = null; }
        if (gestionPodcast != null)     { gestionPodcast.dispose();     gestionPodcast = null; }		
        if (gestionMusica != null)      { gestionMusica.dispose();      gestionMusica = null; }	
        
        if (vCrearArt != null)          { vCrearArt.dispose();          vCrearArt = null; }
        if (vModifArt != null)          { vModifArt.dispose();          vModifArt = null; }
        if (vElimArt != null)           { vElimArt.dispose();           vElimArt = null; }
        if (vCrearAlb != null)          { vCrearAlb.dispose();          vCrearAlb = null; }
        if (vModifAlb != null)          { vModifAlb.dispose();          vModifAlb = null; }
        if (vElimAlb != null)           { vElimAlb.dispose();           vElimAlb = null; }
        if (vCrearCan != null)          { vCrearCan.dispose();          vCrearCan = null; }
        if (vModifCan != null)          { vModifCan.dispose();          vModifCan = null; }
        if (vElimCan != null)           { vElimCan.dispose();           vElimCan = null; }
        
        if (vCrearPod != null)                  { vCrearPod.dispose();                  vCrearPod = null; }
        if (vModifPod != null)                  { vModifPod.dispose();                  vModifPod = null; }
        if (vElimPod != null)                   { vElimPod.dispose();                   vElimPod = null; }
        if (vCrearTemp != null)                 { vCrearTemp.dispose();                 vCrearTemp = null; }
        if (vModifTemp != null)                 { vModifTemp.dispose();                 vModifTemp = null; }
        if (vElimTemp != null)                  { vElimTemp.dispose();                  vElimTemp = null; }
        if (vCrearEp != null)                   { vCrearEp.dispose();                   vCrearEp = null; }
        if (vModifEp != null)                   { vModifEp.dispose();                   vModifEp = null; }
        if (vElimEp != null)                    { vElimEp.dispose();                    vElimEp = null; }
        if (vVistaEstadisticasDetalle != null)  { vVistaEstadisticasDetalle.dispose();  vVistaEstadisticasDetalle = null; }
        if (vPodcasters != null)                { vPodcasters.dispose();                vPodcasters = null; }
    }
}