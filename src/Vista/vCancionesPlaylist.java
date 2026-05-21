package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import Controlador.GestorControlador;

public class vCancionesPlaylist extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private GestorControlador controlador;
	private JButton btnVolver;
	private JButton btnPerfil;
	private JPanel panelContenedorCanciones;
	private JScrollPane scrollPane;
	
	public vCancionesPlaylist(GestorControlador controlador) {
		this.controlador = controlador;
		setBounds(700, 500, 580, 420);
		getContentPane().setLayout(null);
		setTitle("Canciones de la Playlist");
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(29, 24, 89, 23);
		btnVolver.addActionListener(this);
		getContentPane().add(btnVolver);
		
		btnPerfil = new JButton(controlador.getUsuarioActual());
		btnPerfil.setBounds(436, 24, 89, 23);
		btnPerfil.addActionListener(this);
		getContentPane().add(btnPerfil);
	
		panelContenedorCanciones = new JPanel();
		panelContenedorCanciones.setLayout(new BoxLayout(panelContenedorCanciones, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane(panelContenedorCanciones);
		scrollPane.setBounds(29, 75, 496, 275);
		getContentPane().add(scrollPane);
		
		cargarCancionesDinamicas();
	}

	private void cargarCancionesDinamicas() {
	
		panelContenedorCanciones.removeAll(); 
		List<String[]> cancionesDatos = controlador.pedirCancionesPlaylist();
		
		if (cancionesDatos != null && !cancionesDatos.isEmpty()) {
			for (String[] cancion : cancionesDatos) {
				String idCancion = cancion[0];
				String tituloCancion = cancion[1];
				
		
				JPanel filaCancion = new JPanel(new BorderLayout());
				filaCancion.setMaximumSize(new Dimension(470, 40));
				filaCancion.setPreferredSize(new Dimension(470, 40));
				filaCancion.setBorder(new EmptyBorder(5, 5, 5, 5));
				
			
				JButton btnNombreCancion = new JButton(tituloCancion);
				btnNombreCancion.setFont(new Font("Tahoma", Font.PLAIN, 13));
				btnNombreCancion.setHorizontalAlignment(JButton.LEFT);
				btnNombreCancion.setContentAreaFilled(false);
				btnNombreCancion.setBorderPainted(false);
				btnNombreCancion.setFocusPainted(false);
				
			
				btnNombreCancion.addActionListener(e -> {
					controlador.reproducirCancionDesdePlaylist(idCancion);
				});
				
				
				JButton btnBorrar = new JButton("-");
				btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnBorrar.setPreferredSize(new Dimension(45, 25));
				btnBorrar.setForeground(Color.RED);
				
			
				btnBorrar.addActionListener(e -> {
					int respuesta = JOptionPane.showConfirmDialog(this, 
							"¿Seguro que deseas quitar \"" + tituloCancion + "\" de esta playlist?", 
							"Quitar Canción", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						boolean exito = controlador.eliminarCancionDePlaylistActual(idCancion);
						if (exito) {
							JOptionPane.showMessageDialog(this, "Canción quitada.");
							cargarCancionesDinamicas();
						} else {
							JOptionPane.showMessageDialog(this, "Error al quitar la canción.");
						}
					}
				});
				
				filaCancion.add(btnNombreCancion, BorderLayout.CENTER);
				filaCancion.add(btnBorrar, BorderLayout.EAST);
				
				panelContenedorCanciones.add(filaCancion);
			}
		} else {
			JLabel lblVacio = new JLabel("Esta playlist no contiene canciones todavía.");
			lblVacio.setFont(new Font("Tahoma", Font.ITALIC, 13));
			lblVacio.setBorder(new EmptyBorder(15, 15, 0, 0));
			panelContenedorCanciones.add(lblVacio);
		}
		
		
		panelContenedorCanciones.revalidate();
		panelContenedorCanciones.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVolver) {
			controlador.abrirPlaylists();
		}
		if (e.getSource() == btnPerfil) {
			controlador.abrirPerfil();
		}
	}
}