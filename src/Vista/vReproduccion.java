package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import Controlador.GestorControlador;

public class vReproduccion extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private GestorControlador controlador;
	private JButton btnVolver;
	private JButton btnPerfil;
	private JButton btnMenu;
	private JButton btnCancionAnterior;
	private JButton btnPlay;
	private JButton btnSiguienteCancion;
	private JButton btnFavorito;
	private JPanel panelInfoCanciones;
	private JLabel lblTextoCancion; 
	private JLabel imagen; 
	private JPopupMenu popupMenu;
	private JMenuItem itemAñadirPlaylist;
	private JMenuItem itemExportarFichero;
	private Timer reproductorTimer;
	private int tiempoActualSegundos = 0;
	private int duracionTotalSegundos = 0;

	public vReproduccion(GestorControlador controlador) {
		this.controlador = controlador;
		setBounds(700, 500, 580, 420);
		getContentPane().setLayout(null);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(29, 24, 89, 23);
		btnVolver.addActionListener(this);
		getContentPane().add(btnVolver);
		
		btnPerfil = new JButton(controlador.getUsuarioActual());
		btnPerfil.setBounds(436, 24, 89, 23);
		btnPerfil.addActionListener(this);
		getContentPane().add(btnPerfil);
		
		btnMenu = new JButton("Menu");
		btnMenu.setBounds(84, 240, 89, 23);
		btnMenu.addActionListener(this);
		getContentPane().add(btnMenu);
		
		popupMenu = new JPopupMenu();
		
		itemAñadirPlaylist = new JMenuItem("Añadir a la playlist");
		itemAñadirPlaylist.addActionListener(this);
		popupMenu.add(itemAñadirPlaylist);
		
		itemExportarFichero = new JMenuItem("Exportar a un fichero");
		itemExportarFichero.addActionListener(this);
		popupMenu.add(itemExportarFichero);

		btnCancionAnterior = new JButton("<");
		btnCancionAnterior.setBounds(183, 240, 48, 23);
		btnCancionAnterior.addActionListener(this);
		getContentPane().add(btnCancionAnterior);
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(245, 240, 77, 23);
		btnPlay.addActionListener(this);
		getContentPane().add(btnPlay);
		
		btnSiguienteCancion = new JButton(">");
		btnSiguienteCancion.setBounds(334, 240, 48, 23);
		btnSiguienteCancion.addActionListener(this);
		getContentPane().add(btnSiguienteCancion);
		
		btnFavorito = new JButton("Favorito");
		btnFavorito.setBounds(393, 240, 89, 23);
		btnFavorito.addActionListener(this);
		getContentPane().add(btnFavorito);
		
		panelInfoCanciones = new JPanel();
		panelInfoCanciones.setLayout(null);
		panelInfoCanciones.setBorder(new TitledBorder(
			            new EtchedBorder(EtchedBorder.LOWERED, null, null),
			            "Información de Canción",
			            TitledBorder.LEADING, TitledBorder.TOP,
			            null, new Color(0, 0, 0)
			        ));
		panelInfoCanciones.setBounds(86, 284, 394, 81);
		getContentPane().add(panelInfoCanciones);

		lblTextoCancion = new JLabel("");
		lblTextoCancion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextoCancion.setFont(new Font("Tahoma", Font.BOLD, 12)); 
		lblTextoCancion.setBounds(10, 25, 374, 35);
		panelInfoCanciones.add(lblTextoCancion);
		
		imagen = new JLabel("");
		imagen.setBounds(175, 65, 220, 160);
		getContentPane().add(imagen);

		reproductorTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tiempoActualSegundos < duracionTotalSegundos) {
					tiempoActualSegundos++;
					actualizarCancionVisual();
				} else {
					reproductorTimer.stop();
					btnPlay.setText("Play");
				}
			}
		});
		
		cargarDatos();
	}

	private void cargarDatos() {
		String[] infoAlbum = controlador.pedirInfoAlbum();
		if (infoAlbum != null && infoAlbum[0] != null) {
			try {
				java.net.URL url = vReproduccion.class.getResource("/imagenes/albums/" + infoAlbum[0].trim() + ".png");
				if (url != null) {
					java.awt.Image img = new ImageIcon(url).getImage().getScaledInstance(220, 160, java.awt.Image.SCALE_SMOOTH);
					imagen.setIcon(new ImageIcon(img));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resetearYActualizarCancion();
	}

	private void resetearYActualizarCancion() {
		reproductorTimer.stop();
		btnPlay.setText("Play");
		tiempoActualSegundos = 0;

		String[] infoCancion = controlador.pedirInfoCancion();
		if (infoCancion != null && infoCancion.length > 2) {
			try {
				duracionTotalSegundos = Integer.parseInt(infoCancion[2]);
			} catch (NumberFormatException e) {
				duracionTotalSegundos = 180; 
			}
		} else {
			duracionTotalSegundos = 180;
		}
		actualizarCancionVisual();
	}

	private void actualizarCancionVisual() {
		String[] infoCancion = controlador.pedirInfoCancion();
		String titulo = (infoCancion != null && infoCancion.length > 1) ? infoCancion[1] : "Canción";
		String album = (infoCancion != null && infoCancion.length > 4) ? infoCancion[4] : "Álbum Desconocido";

		String tiempoFormateado = String.format("%d:%02d / %d:%02d", 
				tiempoActualSegundos / 60, tiempoActualSegundos % 60,
				duracionTotalSegundos / 60, duracionTotalSegundos % 60);

		lblTextoCancion.setText(titulo + "   |   " + album + "   |   " + tiempoFormateado);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVolver) {
			reproductorTimer.stop();
	        controlador.abrirAlbum(controlador.getIdAlbumActual());
	    }
	    if (e.getSource() == btnPerfil) {
	    	reproductorTimer.stop();
	        controlador.abrirPerfil();
	    }
	    if (e.getSource() == btnMenu) {
	    	popupMenu.show(btnMenu, 0, btnMenu.getHeight());
	    }
	    
	    if (e.getSource() == itemAñadirPlaylist) {
	        List<String[]> playlists = controlador.pedirPlaylists();
	        if (playlists == null || playlists.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "No tienes ninguna playlist creada.", "Información", JOptionPane.INFORMATION_MESSAGE);
	            return;
	        }
	        
	        String[] nombresPlaylists = new String[playlists.size()];
	        for (int i = 0; i < playlists.size(); i++) {
	            nombresPlaylists[i] = playlists.get(i)[1];
	        }
	        
	        String seleccion = (String) JOptionPane.showInputDialog(this, "Selecciona a qué playlist deseas añadir la canción:", "Añadir a Playlist", JOptionPane.QUESTION_MESSAGE, null, nombresPlaylists, nombresPlaylists[0]);
	        
	        if (seleccion != null) {
	            String idPlaylistSeleccionada = null;
	            for (String[] pl : playlists) {
	                if (pl[1].equals(seleccion)) {
	                    idPlaylistSeleccionada = pl[0];
	                    break;
	                }
	            }
	            
	            if (idPlaylistSeleccionada != null) {
	                boolean exito = controlador.añadirAPlaylist(idPlaylistSeleccionada);
	                if (exito) {
	                    JOptionPane.showMessageDialog(this, "Canción añadida con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(this, "La canción ya está en esta playlist o hubo un fallo en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }
	    }
	    
	    
	    if (e.getSource() == itemExportarFichero) {
	        String[] info = controlador.pedirInfoCancion();
	        if (info != null) {
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.setDialogTitle("Exportar Información de Canción");
	            String nombreSugerido = "Cancion_" + info[1].replaceAll("[^a-zA-Z0-9]", "_") + ".txt";
	            fileChooser.setSelectedFile(new File(nombreSugerido));
	            int seleccion = fileChooser.showSaveDialog(this);
	            if (seleccion == JFileChooser.APPROVE_OPTION) {
	                boolean exito = controlador.guardarFicheroCancion(fileChooser.getSelectedFile());
	                if (exito) {
	                    JOptionPane.showMessageDialog(this, "Archivo exportado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(this, "Error al escribir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }
	    }
	    if (e.getSource() == btnFavorito) {
	    	boolean exito = controlador.añadirFavorito();
	    	if (exito) {
	    		JOptionPane.showMessageDialog(this, "Canción guardada en favoritos correctamente.", "Favoritos", JOptionPane.INFORMATION_MESSAGE);
	    	} else {
	    		JOptionPane.showMessageDialog(this, "Esta canción ya se encuentra en tus favoritos o hubo un problema.", "Favoritos", JOptionPane.WARNING_MESSAGE);
	    	}
	    }
	    if (e.getSource() == btnCancionAnterior) {
	    	String idNueva = controlador.cancionAnterior();
	    	if (idNueva != null) {
	    		resetearYActualizarCancion();
	    	} else {
	    		JOptionPane.showMessageDialog(this, "No hay más canciones anteriores en este álbum.", "Reproductor", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    }
	    if (e.getSource() == btnSiguienteCancion) {
	    	String idNueva = controlador.cancionSiguiente();
	    	if (idNueva != null) {
	    		resetearYActualizarCancion();
	    	} else {
	    		JOptionPane.showMessageDialog(this, "Has llegado al final de las canciones de este álbum.", "Reproductor", JOptionPane.INFORMATION_MESSAGE);
	    	}
	    }
	    if (e.getSource() == btnPlay) {
	    	if (btnPlay.getText().equals("Play")) {
	    		btnPlay.setText("Pause");
	    		reproductorTimer.start();
	    	} else {
	    		btnPlay.setText("Play");
	    		reproductorTimer.stop();
	    		actualizarCancionVisual(); 
	    	}
	    }
	}
}