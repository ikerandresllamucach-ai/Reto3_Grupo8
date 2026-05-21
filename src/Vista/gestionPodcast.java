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

public class gestionPodcast extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    
    private GestorControlador controlador;
    private JPanel contentPane;
    private JButton btnCrearPod, btnModifPod, btnElimPod;
    private JButton btnCrearTemp, btnModifTemp, btnElimTemp;
    private JButton btnCrearEp, btnModifEp, btnElimEp;
    private JButton btnVolver;

    public gestionPodcast(GestorControlador controlador) {
        this.controlador = controlador;
        
        setTitle("Panel de Administración - Gestión de Podcasts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 680, 500);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitulo = new JLabel("ADMINISTRACIÓN DE PODCASTS");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitulo.setBounds(160, 20, 400, 30);
        contentPane.add(lblTitulo);
        
     
        JLabel lblPod = new JLabel("1. Podcasts");
        lblPod.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPod.setBounds(40, 90, 150, 25);
        contentPane.add(lblPod);
        
        btnCrearPod = new JButton("Crear Podcast");
        btnCrearPod.setBounds(40, 130, 160, 40);
        btnCrearPod.addActionListener(this);
        contentPane.add(btnCrearPod);
        
        btnModifPod = new JButton("Modificar Podcast");
        btnModifPod.setBounds(40, 190, 160, 40);
        btnModifPod.addActionListener(this);
        contentPane.add(btnModifPod);
        
        btnElimPod = new JButton("Eliminar Podcast");
        btnElimPod.setBounds(40, 250, 160, 40);
        btnElimPod.addActionListener(this);
        contentPane.add(btnElimPod);
        
       
        JLabel lblTemp = new JLabel("2. Temporadas");
        lblTemp.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTemp.setBounds(250, 90, 150, 25);
        contentPane.add(lblTemp);
        
        btnCrearTemp = new JButton("Crear Temporada");
        btnCrearTemp.setBounds(250, 130, 160, 40);
        btnCrearTemp.addActionListener(this);
        contentPane.add(btnCrearTemp);
        
        btnModifTemp = new JButton("Modificar Temporada");
        btnModifTemp.setBounds(250, 190, 160, 40);
        btnModifTemp.addActionListener(this);
        contentPane.add(btnModifTemp);
        
        btnElimTemp = new JButton("Eliminar Temporada");
        btnElimTemp.setBounds(250, 250, 160, 40);
        btnElimTemp.addActionListener(this);
        contentPane.add(btnElimTemp);
        
       
        JLabel lblEp = new JLabel("3. Episodios");
        lblEp.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblEp.setBounds(460, 90, 150, 25);
        contentPane.add(lblEp);
        
        btnCrearEp = new JButton("Crear Episodio");
        btnCrearEp.setBounds(460, 130, 160, 40);
        btnCrearEp.addActionListener(this);
        contentPane.add(btnCrearEp);
        
        btnModifEp = new JButton("Modificar Episodio");
        btnModifEp.setBounds(460, 190, 160, 40);
        btnModifEp.addActionListener(this);
        contentPane.add(btnModifEp);
        
        btnElimEp = new JButton("Eliminar Episodio");
        btnElimEp.setBounds(460, 250, 160, 40);
        btnElimEp.addActionListener(this);
        contentPane.add(btnElimEp);
        
        
        btnVolver = new JButton("Volver al Menú Admin");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnVolver.setBackground(new Color(112, 128, 144));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(230, 370, 200, 45);
        btnVolver.addActionListener(this);
        contentPane.add(btnVolver);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();
        
     
        if (origen == btnCrearPod) {
            controlador.abrirCrearPodcast();
        } else if (origen == btnModifPod) {
            controlador.abrirModificarPodcast();
        } else if (origen == btnElimPod) {
            controlador.abrirEliminarPodcast();
        }
        
  
        else if (origen == btnCrearTemp) {
            controlador.abrirCrearTemporada();
        } else if (origen == btnModifTemp) {
            controlador.abrirModificarTemporada();
        } else if (origen == btnElimTemp) {
            controlador.abrirEliminarTemporada();
        }
        
    
        else if (origen == btnCrearEp) {
            controlador.abrirCrearEpisodio();
        } else if (origen == btnModifEp) {
            controlador.abrirModificarEpisodio();
        } else if (origen == btnElimEp) {
            controlador.abrirEliminarEpisodio();
        }
        
      
        else if (origen == btnVolver) {
            controlador.abrirMenuAdmin();
        }
    }
}