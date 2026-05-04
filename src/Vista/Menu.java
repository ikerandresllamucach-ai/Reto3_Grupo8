package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.GestorControlador;

public class Menu extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel ContentPane;
	private JButton btnAtras;
	private JButton btnPerfil;
	private JButton btnMusica;
	private JButton btnPodcast;
	private JButton btnPlaylist;
	
	private String nombreUsuario;
	private GestorControlador controlador;

	
	@SuppressWarnings("unused")
	public Menu(String nombreUsuario, GestorControlador controlador) {
		this.nombreUsuario = nombreUsuario;
		this.controlador = controlador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 400);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		
		JLabel usuario = new JLabel("Seleccione una Opcion:");
		usuario.setFont(new Font("Arial", Font.BOLD, 24));
		usuario.setBounds(106, 11, 289, 30);
		getContentPane().add(usuario);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Lucida Sans", Font.BOLD, 11));
		btnAtras.setBackground(new Color(214, 232, 245));
		btnAtras.setBounds(10, 320, 75, 30);
		btnAtras.addActionListener(this);
		ContentPane.add(btnAtras);
		
		btnPerfil = new JButton(nombreUsuario);
		btnPerfil.setFont(new Font("Lucida Sans", Font.BOLD, 11));
		btnPerfil.setBackground(new Color(214, 232, 245));
		btnPerfil.setBounds(405, 320, 75, 30);
		btnPerfil.addActionListener(this);
		ContentPane.add(btnPerfil);
		
		btnMusica = new JButton("Descubrir Musica");
		btnMusica.setFont(new Font("Lucida Sans", Font.BOLD, 11));
		btnMusica.setBackground(new Color(214, 232, 245));
		btnMusica.setBounds(119, 96, 256, 30);
		ContentPane.add(btnMusica);
		
		btnPodcast = new JButton("Descubrir Podcast");
		btnPodcast.setFont(new Font("Lucida Sans", Font.BOLD, 11));
		btnPodcast.setBackground(new Color(214, 232, 245));
		btnPodcast.setBounds(119, 137, 256, 30);
		ContentPane.add(btnPodcast);
		
		btnPlaylist = new JButton("Mis Playlist");
		btnPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnPlaylist.setFont(new Font("Lucida Sans", Font.BOLD, 11));
		btnPlaylist.setBackground(new Color(214, 232, 245));
		btnPlaylist.setBounds(119, 178, 256, 30);
		ContentPane.add(btnPlaylist);
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== btnAtras) {
			   controlador.abrirLogin();
		}
		
		
		if (e.getSource()== btnPerfil) {
			 controlador.abrirPerfil();
		}
	}
}