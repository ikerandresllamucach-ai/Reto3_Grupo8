package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Controlador.GestorControlador;

public class vEstadisticas extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private GestorControlador controlador;
	private JButton btnVolver;
	private JLabel txtTitulo;
	private JButton btnTopCanciones;
	private JButton btnTopPodcast;
	private JButton btnMasEscuchados;
	private JButton btnTopPlaylist;

	public vEstadisticas(GestorControlador controlador) {
		this.controlador = controlador;
		setTitle("Estadísticas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 400);
		setLocationRelativeTo(null); 
		getContentPane().setLayout(null);
		
		btnVolver = new JButton("Atras"); 
		btnVolver.setBounds(63, 59, 89, 23);
		btnVolver.addActionListener(this);
		getContentPane().add(btnVolver);
		
		txtTitulo = new JLabel("ESTADISTICAS");
		txtTitulo.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		txtTitulo.setBounds(186, 55, 200, 25);
		getContentPane().add(txtTitulo);
		
		btnTopCanciones = new JButton("Top Canciones Escuchadas");
		btnTopCanciones.setBounds(126, 115, 240, 29);
		btnTopCanciones.addActionListener(this);
		getContentPane().add(btnTopCanciones);
		
		btnTopPodcast = new JButton("Top Podcast Escuchadas");
		btnTopPodcast.setBounds(126, 172, 240, 29);
		btnTopPodcast.addActionListener(this); 
		getContentPane().add(btnTopPodcast);
		
		btnMasEscuchados = new JButton("Top Escuchados");
		btnMasEscuchados.setBounds(126, 231, 240, 29);
		btnMasEscuchados.addActionListener(this); 
		getContentPane().add(btnMasEscuchados);
		
		btnTopPlaylist = new JButton("Top PlayList");
		btnTopPlaylist.setBounds(126, 288, 240, 29);
		btnTopPlaylist.addActionListener(this);
		getContentPane().add(btnTopPlaylist);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object origen = e.getSource();
		
		if (origen == btnVolver) {
			controlador.abrirMenuAdmin();
		} 
		
		else if (origen == btnTopCanciones) {
			controlador.abrirEstadisticaDetalle();
		} else if (origen == btnTopPodcast) {
			controlador.abrirEstadisticaDetalle();
		} else if (origen == btnMasEscuchados) {
		
		} else if (origen == btnTopPlaylist) {
			
		}
	}
}