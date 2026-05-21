package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Modelo.Artista;
import Modelo.GestorModelo;
import Modelo.GestorModelo.Conexion;
import Modelo.GestorModelo.ObtenerUsuario;
import Modelo.Usuario;
import Vista.GestorVista;

public class GestorControlador {

    private GestorModelo modelo;
    private GestorVista gestorV;
    private GestorModelo.LoginModelo log;
    private GestorModelo.ObtenerUsuario datosUser;
    private GestorModelo.ActualizarUsuario updateUser;
    private GestorModelo.ObtenerArtista datosArtista;
    private GestorModelo.ObtenerAlbumesArtista albumesArtista;
    private GestorModelo.ObtenerInfoArtista obtenerInfo;
    private GestorModelo.ObtenerCancionesAlbum cancionesAlbum;
    private GestorModelo.ObtenerInfoAlbum infoAlbum;
    private GestorModelo.ObtenerInfoCancion infoCancion;
    private GestorModelo.ObtenerCancionesConId cancionesConId;
    private GestorModelo.AnadirFavorito anadirFavorito;
    private GestorModelo.ObtenerPlaylists obtenerPlaylists;
    private GestorModelo.AnadirAPlaylist anadirAPlaylist;
    private GestorModelo.CrearPlaylist crearPlaylistModelo;
    private GestorModelo.BorrarPlaylist borrarPlaylistModelo;
    private GestorModelo.ExportarPlaylistsModelo exportarPlModelo;
    private GestorModelo.ImportarPlaylistsModelo importarPlModelo;
    private GestorModelo.ObtenerCancionesPlaylistModelo cancionesPlaylistModelo;
    private GestorModelo.BorrarCancionPlaylistModelo borrarCancionModelo;
    
    private GestorModelo.CrearArtista crearArtistaModelo;
    private GestorModelo.ModificarArtista modificarArtistaModelo;
    private GestorModelo.BorrarArtista borrarArtistaModelo;
    private GestorModelo.CrearAlbum crearAlbumModelo;
    private GestorModelo.BorrarAlbum borrarAlbumModelo;
    private GestorModelo.CrearCancion crearCancionModelo;
    private GestorModelo.BorrarCancion eliminarCancionBDModelo;

    private String idArtistaActual;
    private String idAlbumActual;
    private String idCancionActual;
    private String idPlaylistActual;
    private String usuarioActual;
    private String idUsuarioActual;
    private List<String[]> cancionesAlbumActual;
    private int indiceCancionActual;
    private String idPodcastActual;
    private String idTemporadaActual;
    private String idEpisodioActual;
    private String idPodcastSeleccionado;

    public GestorControlador() {
        modelo = new GestorModelo();
        gestorV = new GestorVista();
        log = new GestorModelo.LoginModelo();
        datosUser = new ObtenerUsuario();
        updateUser = new GestorModelo.ActualizarUsuario();
        datosArtista = new GestorModelo.ObtenerArtista();
        albumesArtista = new GestorModelo.ObtenerAlbumesArtista();
        obtenerInfo = new GestorModelo.ObtenerInfoArtista();
        cancionesAlbum = new GestorModelo.ObtenerCancionesAlbum();
        infoAlbum = new GestorModelo.ObtenerInfoAlbum();
        infoCancion = new GestorModelo.ObtenerInfoCancion();
        cancionesConId = new GestorModelo.ObtenerCancionesConId();
        anadirFavorito = new GestorModelo.AnadirFavorito();
        obtenerPlaylists = new GestorModelo.ObtenerPlaylists();
        anadirAPlaylist = new GestorModelo.AnadirAPlaylist();
        crearPlaylistModelo = new GestorModelo.CrearPlaylist();
        borrarPlaylistModelo = new GestorModelo.BorrarPlaylist();
        exportarPlModelo = new GestorModelo.ExportarPlaylistsModelo();
        importarPlModelo = new GestorModelo.ImportarPlaylistsModelo();
        cancionesPlaylistModelo = new GestorModelo.ObtenerCancionesPlaylistModelo();
        borrarCancionModelo = new GestorModelo.BorrarCancionPlaylistModelo();
        
        crearArtistaModelo = new GestorModelo.CrearArtista();
        modificarArtistaModelo = new GestorModelo.ModificarArtista();
        borrarArtistaModelo = new GestorModelo.BorrarArtista();
        crearAlbumModelo = new GestorModelo.CrearAlbum();
        borrarAlbumModelo = new GestorModelo.BorrarAlbum();
        crearCancionModelo = new GestorModelo.CrearCancion();
        eliminarCancionBDModelo = new GestorModelo.BorrarCancion();
    }

    public void iniciarLogin() {
        modelo.ConexionBD();
        gestorV.mostrarLogin(this);
    }

    public void procesarLogin(String usuario, String contrasena, String tipoSeleccionadoEnCombo) {
        String rolIdentificado = log.comprobarAutenticacion(usuario, contrasena);

        if (rolIdentificado == null) {
            javax.swing.JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error de Login", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if ("Administrador".equalsIgnoreCase(tipoSeleccionadoEnCombo) && "Admin".equals(rolIdentificado)) {
            this.usuarioActual = usuario;
            gestorV.mostrarMenuAdmin(this);
        } else if ("Cliente".equalsIgnoreCase(tipoSeleccionadoEnCombo) && 
                  ("Usuario Premium".equals(rolIdentificado) || "Usuario Free".equals(rolIdentificado))) {
            this.usuarioActual = usuario;
            pedirDatosPerfil();
            gestorV.mostrarMenu(this);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "El tipo de selección no coincide con los privilegios de tu cuenta.", "Error de Rol", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getUsuarioActual() { return usuarioActual; }
    public void setUsuarioActual(String usuario) { this.usuarioActual = usuario; }
    public String getIdArtistaActual() { return idArtistaActual; }
    public void setIdArtistaActual(String idArtista) { this.idArtistaActual = idArtista; }
    public String getIdAlbumActual() { return idAlbumActual; }
    public void setIdAlbumActual(String idAlbum) { this.idAlbumActual = idAlbum; }
    public String getIdCancionActual() { return idCancionActual; }
    public String getIdPlaylistActual() { return idPlaylistActual; }
    public void setIdPlaylistActual(String idPlaylist) { this.idPlaylistActual = idPlaylist; }
    public void setIdCancionActual(String idCancion) { this.idCancionActual = idCancion; }
    public String getIdPodcastActual() { return idPodcastActual; }
    public void setIdPodcastActual(String idPodcast) { this.idPodcastActual = idPodcast; }
    public String getIdTemporadaActual() { return idTemporadaActual; }
    public void setIdTemporadaActual(String idTemporada) { this.idTemporadaActual = idTemporada; }
    public String getIdEpisodioActual() { return idEpisodioActual; }
    public void setIdEpisodioActual(String idEpisodio) { this.idEpisodioActual = idEpisodio; }
    public String getIdPodcastSeleccionado() {
        return this.idPodcastSeleccionado;
    }
    
    public Usuario pedirDatosPerfil() {
        Usuario u = datosUser.datosUsuario(usuarioActual);
        if (u != null) {
            this.idUsuarioActual = u.getId_Usuario();
        }
        return u;
    }

    public boolean actualizarUsuario(String nombre, String apellidos, String usuario, String pass, String idioma) {
        boolean exito = updateUser.actualizarUsuario(idUsuarioActual, nombre, apellidos, usuario, pass, idioma);
        if (exito) {
            this.usuarioActual = usuario;
        }
        return exito;
    }

    public List<String[]> pedirListaArtistasDetallada() { return datosArtista.obtenerListaDetallada(); }
    public String pedirAlbumesArtista() { return albumesArtista.getAlbumes(idArtistaActual); }
    public Artista pedirInfoArtista() { return obtenerInfo.getInfo(idArtistaActual); }

    public String pedirImagenArtista() {
        Artista info = obtenerInfo.getInfo(idArtistaActual);
        if (info == null) return null;
        return info.getNombreArtistico() + ".png";
    }

    public String pedirCancionesAlbum() { return cancionesAlbum.getCanciones(idAlbumActual); }
    public String[] pedirInfoAlbum() { return infoAlbum.getInfo(idAlbumActual); }
    public List<String[]> pedirAlbumesArtistaDetallados() { return albumesArtista.getAlbumesDetallados(idArtistaActual); }
    public String[] pedirInfoCancion() { return infoCancion.getInfo(idCancionActual); }
    public boolean añadirFavorito() { return anadirFavorito.añadir(idUsuarioActual, idCancionActual); }

    public List<String[]> pedirPlaylists() {
        if (this.idUsuarioActual == null) {
            pedirDatosPerfil();
        }
        return obtenerPlaylists.getPlaylists(idUsuarioActual);
    }

    public boolean añadirAPlaylist(String idPlaylist) { return anadirAPlaylist.añadir(idPlaylist, idCancionActual); }
    public List<String[]> pedirCancionesConId() { return cancionesConId.getCanciones(idAlbumActual); }

    public String cancionAnterior() {
        if (cancionesAlbumActual == null || cancionesAlbumActual.isEmpty()) return null;
        if (indiceCancionActual > 0) {
            indiceCancionActual--;
            idCancionActual = cancionesAlbumActual.get(indiceCancionActual)[0];
        }
        return idCancionActual;
    }

    public String cancionSiguiente() {
        if (cancionesAlbumActual == null || cancionesAlbumActual.isEmpty()) return null;
        if (indiceCancionActual < cancionesAlbumActual.size() - 1) {
            indiceCancionActual++;
            idCancionActual = cancionesAlbumActual.get(indiceCancionActual)[0];
        }
        return idCancionActual;
    }

    public boolean crearPlaylist(String titulo) { return crearPlaylistModelo.crear(idUsuarioActual, titulo); }

    public boolean borrarPlaylist(String nombrePlaylist) {
        List<String[]> playlists = pedirPlaylists();
        String idBorrar = null;
        for (String[] pl : playlists) {
            if (pl[1].equals(nombrePlaylist)) {
                idBorrar = pl[0];
                break;
            }
        }
        if (idBorrar != null) {
            return borrarPlaylistModelo.borrar(idBorrar);
        }
        return false;
    }

    public boolean guardarFicheroCancion(File archivoDestino) {
        String[] info = pedirInfoCancion();
        if (info == null || info.length == 0) return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoDestino))) {
            writer.write("========================================"); writer.newLine();
            writer.write("        DETALLES DE LA CANCIÓN          "); writer.newLine();
            writer.write("========================================"); writer.newLine();
            writer.write("Título:      " + info[1]); writer.newLine();
            writer.write("Duración:    " + info[2] + " segundos"); writer.newLine();
            writer.write("Artista:     " + info[3]); writer.newLine();
            writer.write("Álbum:       " + info[4]); writer.newLine();
            writer.write("========================================"); writer.newLine();
            writer.write("Exportado el: " + new Date()); writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean exportarTodasLasPlaylists(File archivoDestino) {
        List<String[]> playlists = pedirPlaylists();
        if (playlists == null || playlists.isEmpty()) return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoDestino))) {
            for (String[] pl : playlists) {
                String idPlaylist = pl[0];
                String titulo = pl[1];
                writer.write("[PLAYLIST]" + titulo);
                writer.newLine();
                
                List<String[]> canciones = exportarPlModelo.obtenerCancionesDePlaylist(idPlaylist);
                for (String[] cancion : canciones) {
                    writer.write(cancion[0]);
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean importarPlaylistsDesdeFichero(File archivoOrigen) {
        if (this.idUsuarioActual == null) {
            pedirDatosPerfil();
        }
        List<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoOrigen))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
            importarPlModelo.limpiarYRestaurar(idUsuarioActual, lineas);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<String[]> pedirCancionesPlaylist() { return cancionesPlaylistModelo.getCanciones(idPlaylistActual); }
    
    public void abrirCancionesPlaylist(String idPlaylist) {
        this.idPlaylistActual = idPlaylist;
        gestorV.mostrarCancionesPlaylist(this); 
    }
    
    public boolean eliminarCancionDePlaylistActual(String idCancion) {
        return borrarCancionModelo.borrarCancion(idPlaylistActual, idCancion);
    }

    public void reproducirCancionDesdePlaylist(String idCancion) {
        this.idCancionActual = idCancion; 
        gestorV.mostrarReproduccion(this); 
    }
    
    public boolean registrarNuevoArtista(String nombre, String genero, String descripcion) { return crearArtistaModelo.crear(nombre, genero, descripcion); }
    public boolean actualizarArtistaExistente(String id, String nombre, String genero, String descripcion) { return modificarArtistaModelo.actualizar(id, nombre, genero, descripcion); }
    public boolean eliminarArtistaBD(String id) { return borrarArtistaModelo.eliminar(id); }
    public boolean registrarNuevoAlbum(String titulo, String genero, String fecha, String idMusico) { return crearAlbumModelo.crear(titulo, genero, fecha, idMusico); }
    public boolean eliminarAlbumBD(String id) { return borrarAlbumModelo.eliminar(id); }
    public boolean registrarNuevaCancion(String nombre, int duracion, String idAlbum) { return crearCancionModelo.crear(nombre, duracion, idAlbum); }
    public boolean eliminarCancionBD(String id) { return eliminarCancionBDModelo.eliminar(id); }
    
    public boolean actualizarAlbumExistente(String id, String titulo, String genero, String fecha) {
        String sql = "UPDATE album SET Titulo = ?, Genero = ?, Fecha_Lanzamiento = ? WHERE ID_Album = ?";
        java.sql.Connection con = null;
        try {
            con = Conexion.getConexion(); 
            try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, titulo);
                pst.setString(2, genero);
                pst.setString(3, fecha);
                pst.setString(4, id);
                return pst.executeUpdate() > 0; 
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (con != null) con.close(); } catch (java.sql.SQLException e) { e.printStackTrace(); }
        }
    }
    
    public boolean actualizarCancionExistente(String id, String titulo, int duracion) {
        String sql = "UPDATE cancion SET Titulo = ?, Duracion = ? WHERE ID_Cancion = ?";
        java.sql.Connection con = null;
        try {
            con = Conexion.getConexion(); 
            try (java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, titulo);
                pst.setInt(2, duracion);
                pst.setString(3, id);
                return pst.executeUpdate() > 0; 
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (con != null) con.close(); } catch (java.sql.SQLException e) { e.printStackTrace(); }
        }
    }
    
    public boolean registrarNuevoPodcast(String nombre, String tematica, String descripcion) {
        String sql = "INSERT INTO podcast (Nombre, Tematica, Descripcion) VALUES (?, ?, ?)";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setString(2, tematica);
            pst.setString(3, descripcion);
            return pst.executeUpdate() > 0;
        } catch (java.sql.SQLException e) { e.printStackTrace(); return false; }
    }

    public String[] pedirInfoPodcast() {
        String sql = "SELECT Nombre, Tematica, Descripcion FROM podcast WHERE ID_Podcast = ?";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, idPodcastActual);
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new String[]{ rs.getString("Nombre"), rs.getString("Tematica"), rs.getString("Descripcion") };
                }
            }
        } catch (java.sql.SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean actualizarPodcastExistente(String id, String nombre, String tematica, String descripcion) {
        String sql = "UPDATE podcast SET Nombre = ?, Tematica = ?, Descripcion = ? WHERE ID_Podcast = ?";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setString(2, tematica);
            pst.setString(3, descripcion);
            pst.setString(4, id);
            return pst.executeUpdate() > 0;
        } catch (java.sql.SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean eliminarPodcastBD(String id) {
        String sql = "DELETE FROM podcast WHERE ID_Podcast = ?";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        } catch (java.sql.SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean registrarNuevaTemporada(String numero, String fecha, String idPodcast) {
        String sql = "INSERT INTO temporada (Numero_Temporada, Fecha_Lanzamiento, ID_Podcast) VALUES (?, ?, ?)";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, numero);
            pst.setString(2, fecha);
            pst.setString(3, idPodcast);
            return pst.executeUpdate() > 0;
        } catch (java.sql.SQLException e) { e.printStackTrace(); return false; }
    }

    public String[] pedirInfoTemporada() {
        String sql = "SELECT Numero_Temporada, Fecha_Lanzamiento FROM temporada WHERE ID_Temporada = ?";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, idTemporadaActual);
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new String[]{ rs.getString("Numero_Temporada"), rs.getString("Fecha_Lanzamiento") };
                }
            }
        } catch (java.sql.SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean actualizarTemporadaExistente(String id, String numero, String fecha) {
        String sql = "UPDATE temporada SET Numero_Temporada = ?, Fecha_Lanzamiento = ? WHERE ID_Temporada = ?";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, numero);
            pst.setString(2, fecha);
            pst.setString(3, id);
            return pst.executeUpdate() > 0;
        } catch (java.sql.SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean eliminarTemporadaBD(String id) {
        String sql = "DELETE FROM temporada WHERE ID_Temporada = ?";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        } catch (java.sql.SQLException e) { e.printStackTrace(); return false; }
    }
    
    public String[] pedirInfoEpisodio() {
        String sql = "SELECT Titulo, Duracion FROM episodio WHERE ID_Episodio = ?";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, idEpisodioActual);
            try (java.sql.ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new String[]{ rs.getString("Titulo"), rs.getString("Duracion") };
                }
            }
        } catch (java.sql.SQLException e) { e.printStackTrace(); }
        return null;
    }
    
    public boolean actualizarEpisodioExistente(String id, String titulo, int duracion) {
        String sql = "UPDATE episodio SET Titulo = ?, Duracion = ? WHERE ID_Episodio = ?";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, titulo);
            pst.setInt(2, duracion);
            pst.setString(3, id);
            return pst.executeUpdate() > 0;
        } catch (java.sql.SQLException e) { 
            e.printStackTrace(); 
            return false; 
        }
    }
    
    public boolean eliminarEpisodioBD(String id) {
        String sql = "DELETE FROM episodio WHERE ID_Episodio = ?";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        } catch (java.sql.SQLException e) { 
            e.printStackTrace(); 
            return false; 
        }
    }
    
    public boolean registrarNuevoEpisodio(String titulo, int duracion, String idTemporada) {
        String sql = "INSERT INTO episodio (Titulo, Duracion, ID_Temporada) VALUES (?, ?, ?)";
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, titulo);
            pst.setInt(2, duracion);
            pst.setString(3, idTemporada);
            return pst.executeUpdate() > 0;
        } catch (java.sql.SQLException e) { 
            e.printStackTrace(); 
            return false; 
        }
    }

    // ==========================================
    //   MÉTODOS DE NAVEGACIÓN NUEVOS Y FIJADOS
    // ==========================================

    public void abrirMenuAdmin() {
        gestorV.mostrarMenuAdmin(this);
    }

    public void abrirEstadisticas() {
        gestorV.mostrarEstadisticas(this);
    }

    public void abrirEstadisticaDetalle() {
        gestorV.mostrarVistaEstadisticaDetalle(this, "Top Canción Más Escuchadas", "Año");
    }

    public void abrirEstadisticaDetalle(String tipo, String tiempo) {
        if (tipo == null) tipo = "Top Canción Más Escuchadas";
        if (tiempo == null) tiempo = "Año";
        gestorV.mostrarVistaEstadisticaDetalle(this, tipo, tiempo);
    }
    
 
    public List<String[]> obtenerEstadisticasCancionesReales(String filtroTiempo) {
        List<String[]> lista = new ArrayList<>();
        
        // Consulta directa a la tabla audio  por canciones
        String sql = "SELECT Nombre, NReproducciones AS Total FROM audio WHERE Tipo = 'Cancion' ORDER BY NReproducciones DESC LIMIT 10";
        
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql);
             java.sql.ResultSet rs = pst.executeQuery()) {
            
            int posicion = 1;
            while (rs.next()) {
                lista.add(new String[]{
                    String.valueOf(posicion++),
                    "Cantante", // Ponemos el tipo de creador genérico en euskera para cumplir con la columna
                    rs.getString("Nombre"),
                    rs.getString("Total")
                });
            }
        } catch (java.sql.SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public List<String[]> obtenerEstadisticasPodcastsReales(String filtroTiempo) {
        List<String[]> lista = new ArrayList<>();
        
        String sql = "SELECT Nombre, NReproducciones AS Total " +
                     "FROM audio " +
                     "WHERE Tipo = 'Podcast' " +
                     "ORDER BY NReproducciones DESC LIMIT 10";

        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql);
             java.sql.ResultSet rs = pst.executeQuery()) {
            
            int posicion = 1;
            while (rs.next()) {
                lista.add(new String[]{
                    String.valueOf(posicion++),
                    "Podcaster",             
                    rs.getString("Nombre"),  
                    rs.getString("Total")   
                });
            }
        } catch (java.sql.SQLException e) { 
            e.printStackTrace(); 
        }
        return lista;
    }

    public void abrirCrearPodcast() {
        gestorV.mostrarCrearPodcast(this);
    }

    public void abrirModificarPodcast() {
        gestorV.mostrarModificarPodcast(this);
    }

    public void abrirEliminarPodcast() {
        gestorV.mostrarEliminarPodcast(this);
    }

    public void abrirCrearTemporada() {
        gestorV.mostrarCrearTemporada(this);
    }

    public void abrirModificarTemporada() {
        gestorV.mostrarModificarTemporada(this);
    }

    public void abrirEliminarTemporada() {
        gestorV.mostrarEliminarTemporada(this);
    }

    public void abrirCrearEpisodio() {
        gestorV.mostrarCrearEpisodio(this);
    }

    public void abrirModificarEpisodio() {
        gestorV.mostrarModificarEpisodio(this);
    }

    public void abrirEliminarEpisodio() {
        gestorV.mostrarEliminarEpisodio(this);
    }
    
 // ==========================================
    //   MÉTODOS DE NAVEGACIÓN DE VISTAS FALTANTES
    // ==========================================

    public void abrirAlbum(String idAlbum) {
        this.idAlbumActual = idAlbum;
        gestorV.mostrarValbum(this);
    }

    public void abrirArtista(String idArtista) {
        this.idArtistaActual = idArtista;
        gestorV.mostrarInfoArtista(this);
    }

    public void abrirReproduccion(String idCancion) {
        this.idCancionActual = idCancion;
        gestorV.mostrarReproduccion(this);
    }

    public void abrirPlaylists() {
        gestorV.mostrarPlaylists(this);
    }

    public void abrirMenuPrincipal() {
        gestorV.mostrarMenu(this);
    }

    public void abrirPerfil() {
        gestorV.mostrarPerfil(this);
    }

    public void abrirArtistas() {
        gestorV.mostrarArtistas(this);
    }

    public void abrirGestionMusica() {
        gestorV.mostrarGestionMusica(this);
    }

    public void abrirGestionPodcasts() {
        gestorV.mostrarGestionPodcasts(this);
    }

    public void abrirCrearArtista() {
        gestorV.mostrarCrearArtista(this);
    }

    public void abrirModificarArtista() {
        gestorV.mostrarModificarArtista(this);
    }

    public void abrirEliminarArtista() {
        gestorV.mostrarEliminarArtista(this);
    }

    public void abrirCrearAlbum() {
        gestorV.mostrarCrearAlbum(this);
    }

    public void abrirModificarAlbum() {
        gestorV.mostrarModificarAlbum(this);
    }

    public void abrirEliminarAlbum() {
        gestorV.mostrarEliminarAlbum(this);
    }

    public void abrirCrearCancion() {
        gestorV.mostrarCrearCancion(this);
    }

    public void abrirModificarCancion() {
        gestorV.mostrarModificarCancion(this);
    }

    public void abrirEliminarCancion() {
        gestorV.mostrarEliminarCancion(this);
    }
    
    public void abrirInfoArtista(String idArtista) {
        this.setIdArtistaActual(idArtista);
        gestorV.mostrarInfoArtista(this);   
    }
    
    public void abrirRegistro() {
        gestorV.mostrarRegistro(this);
    }

	public void abrirPodcasters() {
		gestorV.mostrarPodcasters(this);
	}

	public List<String[]> obtenerPodcastersReales() {
        List<String[]> lista = new java.util.ArrayList<>();
        
        String sql = "SELECT ID_Audio, Nombre FROM audio WHERE Tipo = 'Podcast' ORDER BY Nombre ASC";
        
        try (java.sql.Connection con = Conexion.getConexion();
             java.sql.PreparedStatement pst = con.prepareStatement(sql);
             java.sql.ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("ID_Audio"),
                    rs.getString("Nombre")
                });
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

	public void abrirPodcast(String idPodcast) {
        this.idPodcastSeleccionado = idPodcast; 
      
    }
    
}