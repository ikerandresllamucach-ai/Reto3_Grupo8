package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import Controlador.GestorControlador;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class menuAdmin extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    private GestorControlador controlador;
    private JButton btnVolver;
    private JLabel txtTitulo;
    private JButton btnGestionMusica;
    private JButton btnGestionPodcast;
    private JButton btnEstadisticas;

    public menuAdmin(GestorControlador controlador) {
        this.controlador = controlador;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 506, 400);
        getContentPane().setLayout(null);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(21, 44, 89, 23);
        btnVolver.addActionListener(this);
        getContentPane().add(btnVolver);
        
        txtTitulo = new JLabel("Ventana de Administracion");
        txtTitulo.setFont(new Font("Lucida Sans", Font.BOLD, 15));
        txtTitulo.setBounds(143, 45, 229, 19);
        getContentPane().add(txtTitulo);
        
        btnGestionMusica = new JButton("Gestionar Musica");
        btnGestionMusica.setBounds(101, 128, 295, 28);
        btnGestionMusica.addActionListener(this); 
        getContentPane().add(btnGestionMusica);
        
        btnGestionPodcast = new JButton("Gestionar Podcast's");
        btnGestionPodcast.setBounds(101, 190, 295, 28);
        btnGestionPodcast.addActionListener(this); 
        getContentPane().add(btnGestionPodcast);
        
        btnEstadisticas = new JButton("Estadisticas");
        btnEstadisticas.setBounds(101, 256, 295, 28);
        btnEstadisticas.addActionListener(this);
        getContentPane().add(btnEstadisticas);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            controlador.iniciarLogin();
        }
        
        if (e.getSource() == btnGestionMusica) {
            controlador.abrirGestionMusica(); 
        }
        
        if (e.getSource() == btnGestionPodcast) {
            controlador.abrirGestionPodcasts(); 
        }
        
        if (e.getSource() == btnEstadisticas) {
            controlador.abrirEstadisticas();
        }
    }
}