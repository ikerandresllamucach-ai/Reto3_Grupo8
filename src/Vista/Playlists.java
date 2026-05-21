package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import Controlador.GestorControlador;

public class Playlists extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JButton btnAtras;
	private JButton btnPerfil;
	private JList<String> listPlaylists;
	private DefaultListModel<String> listModel;
	private List<String[]> listaPlaylistsDatos;
	private GestorControlador controlador;
	private JButton btnCrearPlaylist;
	private JButton btnBorrarPlaylist;
	private JButton btnImportar;
	private JButton btnExportar;
	
	public Playlists(GestorControlador controlador) {
		this.controlador = controlador;
		getContentPane().setLayout(null);
		setBounds(100, 100, 506, 400);
		
		btnAtras = new JButton("Volver");
		btnAtras.setBounds(28, 27, 89, 23);
		btnAtras.addActionListener(this);
		getContentPane().add(btnAtras);
		
		btnPerfil = new JButton(controlador.getUsuarioActual());
		btnPerfil.setBounds(368, 27, 89, 23);
		btnPerfil.addActionListener(this);
		getContentPane().add(btnPerfil);
	     
		listModel = new DefaultListModel<>();
		listPlaylists = new JList<>(listModel);
		listPlaylists.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane(listPlaylists);
		scrollPane.setBounds(28, 80, 262, 250);
		getContentPane().add(scrollPane);
		
		listPlaylists.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = listPlaylists.getSelectedIndex();
					if (index != -1 && listaPlaylistsDatos != null) {
						String idPlaylistSel = listaPlaylistsDatos.get(index)[0];
						controlador.abrirCancionesPlaylist(idPlaylistSel);
					}
				}
			}
		});
	     
		btnCrearPlaylist = new JButton("Crear Playlist");
		btnCrearPlaylist.setBounds(329, 95, 128, 23);
		btnCrearPlaylist.addActionListener(this);
		getContentPane().add(btnCrearPlaylist);
	     
		btnBorrarPlaylist = new JButton("Borrar Playlist");
		btnBorrarPlaylist.setBounds(329, 154, 128, 23);
		btnBorrarPlaylist.addActionListener(this);
		getContentPane().add(btnBorrarPlaylist);
	     
		btnImportar = new JButton("Importar");
		btnImportar.setBounds(329, 214, 128, 23);
		btnImportar.addActionListener(this);
		getContentPane().add(btnImportar);
	     
		btnExportar = new JButton("Exportar");
		btnExportar.setBounds(329, 274, 128, 23);
		btnExportar.addActionListener(this);
		getContentPane().add(btnExportar);

		cargarPlaylists();
	}
	
	private void cargarPlaylists() {
		listModel.clear();
		listaPlaylistsDatos = controlador.pedirPlaylists();
		
		if (listaPlaylistsDatos != null && !listaPlaylistsDatos.isEmpty()) {
			for (String[] pl : listaPlaylistsDatos) {
				listModel.addElement(pl[1]);
			}
		} else {
			listModel.addElement("No tienes ninguna playlist creada.");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAtras) {
			controlador.abrirMenuPrincipal();
		}
		if (e.getSource() == btnPerfil) {
			controlador.abrirPerfil();
		}
		if (e.getSource() == btnCrearPlaylist) {
			String titulo = JOptionPane.showInputDialog(this, "Introduce el nombre de la nueva playlist:", "Crear Playlist", JOptionPane.QUESTION_MESSAGE);
			if (titulo != null && !titulo.trim().isEmpty()) {
				boolean creado = controlador.crearPlaylist(titulo.trim());
				if (creado) {
					JOptionPane.showMessageDialog(this, "Playlist creada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
					cargarPlaylists();
				} else {
					JOptionPane.showMessageDialog(this, "Error al crear la playlist.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if (e.getSource() == btnBorrarPlaylist) {
			List<String[]> lista = controlador.pedirPlaylists();
			if (lista == null || lista.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No tienes ninguna playlist para borrar.", "Información", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String[] nombres = new String[lista.size()];
			for (int i = 0; i < lista.size(); i++) {
				nombres[i] = lista.get(i)[1];
			}
			String seleccion = (String) JOptionPane.showInputDialog(this, "Selecciona la playlist que deseas eliminar:", "Borrar Playlist", JOptionPane.QUESTION_MESSAGE, null, nombres, nombres[0]);
			if (seleccion != null) {
				int confirmar = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres borrar la playlist '" + seleccion + "'?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
				if (confirmar == JOptionPane.YES_OPTION) {
					boolean borrado = controlador.borrarPlaylist(seleccion);
					if (borrado) {
						JOptionPane.showMessageDialog(this, "Playlist eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						cargarPlaylists();
					} else {
						JOptionPane.showMessageDialog(this, "No se pudo eliminar la playlist.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
}