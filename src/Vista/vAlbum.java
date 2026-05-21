package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import Controlador.GestorControlador;

public class vAlbum extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private GestorControlador controlador;
    private JButton btnVolver;
    private JPanel panelCanciones;
    private JPanel panelInfo;
    private JLabel txtTitulo;
    private JTextArea txtInfo;
    private JLabel imagen;

    private JList<String> listaCanciones;
    private DefaultListModel<String> modeloCanciones;
    private List<String> idsCanciones = new ArrayList<>();

    public vAlbum(GestorControlador controlador) {
        this.controlador = controlador;
        setBounds(700, 500, 580, 420);
        getContentPane().setLayout(null);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(26, 23, 89, 23);
        btnVolver.addActionListener(this);
        getContentPane().add(btnVolver);

        txtTitulo = new JLabel("Album: ");
        txtTitulo.setBounds(125, 28, 200, 14);
        getContentPane().add(txtTitulo);

        panelCanciones = new JPanel();
        panelCanciones.setLayout(null);
        panelCanciones.setBorder(new TitledBorder(
            new EtchedBorder(EtchedBorder.LOWERED, null, null),
            "Lista Canciones",
            TitledBorder.LEADING, TitledBorder.TOP,
            null, new Color(0, 0, 0)
        ));
        panelCanciones.setBounds(26, 60, 235, 300);
        getContentPane().add(panelCanciones);

        modeloCanciones = new DefaultListModel<>();
        listaCanciones = new JList<>(modeloCanciones);
        listaCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Al hacer clic en una canción abre la reproducción
        listaCanciones.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int idx = listaCanciones.getSelectedIndex();
                if (idx >= 0 && idx < idsCanciones.size()) {
                    controlador.abrirReproduccion(idsCanciones.get(idx));
                }
            }
        });

        JScrollPane scrollCanciones = new JScrollPane(listaCanciones);
        scrollCanciones.setBounds(10, 20, 215, 265);
        panelCanciones.add(scrollCanciones);

        panelInfo = new JPanel();
        panelInfo.setLayout(null);
        panelInfo.setBorder(new TitledBorder(
            new EtchedBorder(EtchedBorder.LOWERED, null, null),
            "Informacion del Album",
            TitledBorder.LEADING, TitledBorder.TOP,
            null, new Color(0, 0, 0)
        ));
        panelInfo.setBounds(280, 60, 260, 300);
        getContentPane().add(panelInfo);

        txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setLineWrap(true);
        txtInfo.setBounds(10, 20, 240, 80);
        panelInfo.add(txtInfo);

        imagen = new JLabel("");
        imagen.setBounds(22, 110, 216, 160);
        panelInfo.add(imagen);

        cargarDatos();
    }

    private void cargarDatos() {
        List<String[]> canciones = controlador.pedirCancionesConId();
        modeloCanciones.clear();
        idsCanciones.clear();

        if (canciones == null || canciones.isEmpty()) {
            modeloCanciones.addElement("No hay canciones disponibles");
        } else {
            for (String[] fila : canciones) {
                idsCanciones.add(fila[0]); // ID_Audio
                int dur = Integer.parseInt(fila[2]);
                modeloCanciones.addElement(fila[1] + " - "
                    + (dur / 60) + ":"
                    + String.format("%02d", dur % 60));
            }
        }

        // Info del álbum
        String[] infoAlbum = controlador.pedirInfoAlbum();
        if (infoAlbum != null) {
            txtTitulo.setText("Album: " + infoAlbum[0]);
            txtInfo.setText(
                "Género: " + infoAlbum[1] + "\n" +
                "Publicado: " + infoAlbum[2]
            );

            // Imagen
            try {
                java.net.URL url = vAlbum.class.getResource("/imagenes/albums/" + infoAlbum[0].trim() + ".png");
                if (url != null) {
                    java.awt.Image img = new ImageIcon(url).getImage()
                        .getScaledInstance(216, 160, java.awt.Image.SCALE_SMOOTH);
                    imagen.setIcon(new ImageIcon(img));
                } else {
                    System.err.println("No se encontró imagen: " + infoAlbum[0]);
                    imagen.setIcon(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            controlador.abrirInfoArtista(controlador.getIdArtistaActual());
        }
    }
}