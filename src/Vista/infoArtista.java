package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.util.List;

import Controlador.GestorControlador;
import Modelo.Artista;
import javax.swing.ImageIcon;

public class infoArtista extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private GestorControlador controlador;
    private JButton btnVolver;
    private JButton btnPerfil;
    private JLabel lblNombreArtista;
    private JPanel panelDiscos;
    private JPanel panelInfo;
    private JTextArea txtInfo;
    private JLabel imagen;

    private javax.swing.JList<String> listaAlbumes;
    private javax.swing.DefaultListModel<String> modeloAlbumes;
    private java.util.List<String> idsAlbumes = new java.util.ArrayList<>();

    public infoArtista(GestorControlador controlador, String imgArtista) {
        this.controlador = controlador;

        setBounds(700, 500, 700, 500);
        getContentPane().setLayout(null);

        btnVolver = new JButton("Atras");
        btnVolver.setBounds(45, 32, 89, 23);
        btnVolver.addActionListener(this);
        getContentPane().add(btnVolver);

        btnPerfil = new JButton(controlador.getUsuarioActual());
        btnPerfil.setBounds(551, 32, 89, 23);
        btnPerfil.addActionListener(this);
        getContentPane().add(btnPerfil);

        lblNombreArtista = new JLabel();
        lblNombreArtista.setFont(new Font("Arial", Font.BOLD, 13));
        lblNombreArtista.setBounds(163, 36, 200, 14);
        getContentPane().add(lblNombreArtista);

        panelDiscos = new JPanel();
        panelDiscos.setBorder(new TitledBorder(
            new EtchedBorder(EtchedBorder.LOWERED, null, null),
            "Lista Albumes",
            TitledBorder.LEADING, TitledBorder.TOP,
            null, new Color(0, 0, 0)
        ));
        panelDiscos.setBounds(45, 75, 281, 355);
        panelDiscos.setLayout(null);
        getContentPane().add(panelDiscos);

        modeloAlbumes = new javax.swing.DefaultListModel<>();
        listaAlbumes = new javax.swing.JList<>(modeloAlbumes);
        listaAlbumes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        listaAlbumes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int idx = listaAlbumes.getSelectedIndex();
                if (idx >= 0) {
                    String idAlbum = idsAlbumes.get(idx);
                    controlador.abrirAlbum(idAlbum);
                }
            }
        });

        javax.swing.JScrollPane scrollAlbumes = new javax.swing.JScrollPane(listaAlbumes);
        scrollAlbumes.setBounds(10, 20, 261, 320);
        panelDiscos.add(scrollAlbumes);

        panelInfo = new JPanel();
        panelInfo.setBorder(new TitledBorder(
            new EtchedBorder(EtchedBorder.LOWERED, null, null),
            "Informacion",
            TitledBorder.LEADING, TitledBorder.TOP,
            null, new Color(0, 0, 0)
        ));
        
        panelInfo.setBounds(349, 66, 297, 355);
        panelInfo.setLayout(null);
        getContentPane().add(panelInfo);

        txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setLineWrap(true);
        txtInfo.setWrapStyleWord(true);
        txtInfo.setFont(new Font("Arial", Font.PLAIN, 11));
        txtInfo.setBounds(10, 20, 277, 93);
        panelInfo.add(txtInfo);

        imagen = new JLabel("");
        imagen.setBounds(21, 124, 256, 220);

        try {
            URL url = infoArtista.class.getResource("/imagenes/" + imgArtista);
            if (url != null) {
                Image img = new ImageIcon(url).getImage()
                    .getScaledInstance(256, 220, Image.SCALE_SMOOTH);
                imagen.setIcon(new ImageIcon(img));
            } else {
                System.out.println("No se encontró la imagen: " + imgArtista);
            }
        } catch (Exception e) {
            System.out.println("Error cargando imagen: " + e.getMessage());
        }

        panelInfo.add(imagen);
        cargarDatos();
    }

    private void cargarDatos() {
     
        List<String[]> albumes = controlador.pedirAlbumesArtistaDetallados();
        modeloAlbumes.clear();
        idsAlbumes.clear();

        if (albumes == null || albumes.isEmpty()) {
            modeloAlbumes.addElement("No hay álbumes disponibles");
        } else {
            for (String[] fila : albumes) {
                idsAlbumes.add(fila[0]);
                modeloAlbumes.addElement(fila[1] + " - " + fila[2] + " - " + fila[3] + " canciones");
            }
        }

        Artista info = controlador.pedirInfoArtista();
        if (info != null) {
            lblNombreArtista.setText(info.getNombreArtistico());
            txtInfo.setText(
                "Genero: " + info.getGenero() + "\n\n" +
                "Descripcion:\n" + info.getDescripcion()
            );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            controlador.abrirArtistas();
        }
        if (e.getSource() == btnPerfil) {
            controlador.abrirPerfil();
        }
    }
}