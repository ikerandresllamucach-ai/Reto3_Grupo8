package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import Controlador.GestorControlador;

public class gestionMusica extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private GestorControlador controlador;
    private JTabbedPane pestanas;
    private JButton btnVolver;
    private JButton btnCrearArtista, btnModificarArtista, btnEliminarArtista;
    private JButton btnCrearAlbum, btnModificarAlbum, btnEliminarAlbum;
    private JButton btnCrearCancion, btnModificarCancion, btnEliminarCancion;

    public gestionMusica(GestorControlador controlador) {
        this.controlador = controlador;
        
        setTitle("Panel de Administración - Gestión de Música");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 10));
        setContentPane(contentPane);

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(null);
        panelNorte.setPreferredSize(new java.awt.Dimension(600, 50));
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(10, 11, 89, 23);
        btnVolver.addActionListener(this);
        panelNorte.add(btnVolver);
        
        JLabel lblTitulo = new JLabel("Mantenimiento de Música");
        lblTitulo.setFont(new Font("Lucida Sans", Font.BOLD, 16));
        lblTitulo.setBounds(190, 11, 250, 23);
        panelNorte.add(lblTitulo);
        
        contentPane.add(panelNorte, BorderLayout.NORTH);

   
        pestanas = new JTabbedPane(JTabbedPane.TOP);
        pestanas.setFont(new Font("Lucida Sans", Font.PLAIN, 12));
        contentPane.add(pestanas, BorderLayout.CENTER);

   
        JPanel panelArtistas = new JPanel();
        panelArtistas.setLayout(null);
        
        btnCrearArtista = new JButton("Crear Artista");
        btnCrearArtista.setBounds(180, 50, 220, 40);
        btnCrearArtista.addActionListener(this);
        panelArtistas.add(btnCrearArtista);
        
        btnModificarArtista = new JButton("Modificar Artista");
        btnModificarArtista.setBounds(180, 120, 220, 40);
        btnModificarArtista.addActionListener(this);
        panelArtistas.add(btnModificarArtista);
        
        btnEliminarArtista = new JButton("Eliminar Artista");
        btnEliminarArtista.setBounds(180, 190, 220, 40);
        btnEliminarArtista.addActionListener(this);
        panelArtistas.add(btnEliminarArtista);
        
        pestanas.addTab("Artistas", panelArtistas);

     
        JPanel panelAlbumes = new JPanel();
        panelAlbumes.setLayout(null);
        
        btnCrearAlbum = new JButton("Crear Álbum");
        btnCrearAlbum.setBounds(180, 50, 220, 40);
        btnCrearAlbum.addActionListener(this);
        panelAlbumes.add(btnCrearAlbum);
        
        btnModificarAlbum = new JButton("Modificar Álbum");
        btnModificarAlbum.setBounds(180, 120, 220, 40);
        btnModificarAlbum.addActionListener(this);
        panelAlbumes.add(btnModificarAlbum);
        
        btnEliminarAlbum = new JButton("Eliminar Álbum");
        btnEliminarAlbum.setBounds(180, 190, 220, 40);
        btnEliminarAlbum.addActionListener(this);
        panelAlbumes.add(btnEliminarAlbum);
        
        pestanas.addTab("Álbumes", panelAlbumes);

        
        JPanel panelCanciones = new JPanel();
        panelCanciones.setLayout(null);
        
        btnCrearCancion = new JButton("Crear Canción");
        btnCrearCancion.setBounds(180, 50, 220, 40);
        btnCrearCancion.addActionListener(this);
        panelCanciones.add(btnCrearCancion);
        
        btnModificarCancion = new JButton("Modificar Canción");
        btnModificarCancion.setBounds(180, 120, 220, 40);
        btnModificarCancion.addActionListener(this);
        panelCanciones.add(btnModificarCancion);
        
        btnEliminarCancion = new JButton("Eliminar Canción");
        btnEliminarCancion.setBounds(180, 190, 220, 40);
        btnEliminarCancion.addActionListener(this);
        panelCanciones.add(btnEliminarCancion);
        
        pestanas.addTab("Canciones", panelCanciones);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            controlador.abrirMenuAdmin(); 
        }

        if (e.getSource() == btnCrearArtista) {
        	controlador.abrirCrearArtista();
        }
        if (e.getSource() == btnModificarArtista) {
        	controlador.abrirModificarArtista();
        }
        if (e.getSource() == btnEliminarArtista) {
        	controlador.abrirEliminarArtista();
        }
        
        if (e.getSource() == btnCrearAlbum) {
        	controlador.abrirCrearAlbum();
        }
        if (e.getSource() == btnModificarAlbum) {
        	controlador.abrirModificarAlbum();
        }
        if (e.getSource() == btnEliminarAlbum) {
        	controlador.abrirEliminarAlbum();
        }
        
   
        if (e.getSource() == btnCrearCancion) {
        	controlador.abrirCrearCancion();
        }
        
        if (e.getSource() == btnModificarCancion) {
        	controlador.abrirModificarCancion();
        }
        if (e.getSource() == btnEliminarCancion) {
        	controlador.abrirEliminarCancion();
        }
    }
}