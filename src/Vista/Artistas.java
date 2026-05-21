package Vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controlador.GestorControlador;

public class Artistas extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JButton btnVolver;
    private JButton btnPerfil;
    private JLabel txtTitulo;
    private JList<String> listaArtistas;
    private DefaultListModel<String> modeloLista;

   
    private List<String> idsArtistas = new ArrayList<>();

    private GestorControlador controlador;

    public Artistas(GestorControlador controlador) {
        this.controlador = controlador;

        getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
        setBounds(700, 500, 700, 500);
        getContentPane().setLayout(null);

        btnVolver = new JButton("Atras");
        btnVolver.setBounds(38, 30, 89, 23);
        btnVolver.addActionListener(this);
        getContentPane().add(btnVolver);

        btnPerfil = new JButton(controlador.getUsuarioActual());
        btnPerfil.setBounds(554, 30, 89, 23);
        btnPerfil.addActionListener(this);
        getContentPane().add(btnPerfil);

        txtTitulo = new JLabel("Lista de Artistas (Numero de Reproducciones)");
        txtTitulo.setFont(new Font("Lucida Sans", Font.BOLD, 12));
        txtTitulo.setBounds(197, 78, 308, 14);
        getContentPane().add(txtTitulo);

        modeloLista = new DefaultListModel<>();
        listaArtistas = new JList<>(modeloLista);
        listaArtistas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(listaArtistas);
        scroll.setBounds(171, 102, 358, 268);
        getContentPane().add(scroll);

     
        listaArtistas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int idx = listaArtistas.getSelectedIndex();
                    if (idx >= 0) {
                        String idArtista = idsArtistas.get(idx);
                        String nombreFoto = idArtista + ".png"; 
                        controlador.abrirInfoArtista(idArtista);
                    }
                }
            }
        });

        cargarDatos();
    }

    public void cargarDatos() {
        List<String[]> lista = controlador.pedirListaArtistasDetallada();
        modeloLista.clear();
        idsArtistas.clear();

        if (lista == null || lista.isEmpty()) {
            modeloLista.addElement("No hay artistas disponibles");
        } else {
            for (String[] fila : lista) {
                idsArtistas.add(fila[0]);
                modeloLista.addElement(fila[1] + " - " + fila[2] + " reproducciones"); 
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVolver) {
            controlador.abrirMenuPrincipal();
        }
        if (e.getSource() == btnPerfil) {
            controlador.abrirPerfil();
        }
    }
}