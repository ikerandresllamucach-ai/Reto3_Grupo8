package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Controlador.GestorControlador;

public class vEliminarPodcast extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtNombre;
    private JTextField txtTematica;
    private JTextArea txtDescripcion;
    private JButton btnEliminar;

    public vEliminarPodcast(GestorControlador controlador) {
        setTitle("Administración - Eliminar Podcast");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 520);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("ELIMINAR PODCAST");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(178, 34, 34));
        lblTitulo.setBounds(150, 20, 250, 30);
        contentPane.add(lblTitulo);

        // BUSCADOR
        JLabel lblIdBuscar = new JLabel("ID del Podcast:");
        lblIdBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIdBuscar.setBounds(40, 80, 110, 25);
        contentPane.add(lblIdBuscar);

        txtIdBuscar = new JTextField();
        txtIdBuscar.setBounds(160, 80, 150, 25);
        contentPane.add(txtIdBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(70, 130, 180));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBounds(325, 80, 115, 25);
        contentPane.add(btnBuscar);

        JLabel lblSeparador = new JLabel();
        lblSeparador.setOpaque(true);
        lblSeparador.setBackground(Color.LIGHT_GRAY);
        lblSeparador.setBounds(40, 125, 420, 2);
        contentPane.add(lblSeparador);

        // VISTA DE DATOS (SOLO LECTURA)
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(40, 150, 110, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        txtNombre.setBounds(160, 150, 280, 25);
        contentPane.add(txtNombre);

        JLabel lblTematica = new JLabel("Temática:");
        lblTematica.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTematica.setBounds(40, 200, 110, 25);
        contentPane.add(lblTematica);

        txtTematica = new JTextField();
        txtTematica.setEditable(false);
        txtTematica.setBounds(160, 200, 280, 25);
        contentPane.add(txtTematica);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDescripcion.setBounds(40, 250, 110, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(txtDescripcion);
        scrollPane.setBounds(160, 250, 280, 100);
        contentPane.add(scrollPane);

        // ACCIONES
        btnEliminar = new JButton("Eliminar Podcast");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnEliminar.setBackground(new Color(178, 34, 34));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBounds(80, 390, 160, 35);
        btnEliminar.setEnabled(false);
        contentPane.add(btnEliminar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(112, 128, 144));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 390, 160, 35);
        contentPane.add(btnVolver);

        // EVENTOS
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(vEliminarPodcast.this, "Por favor, introduce un ID.");
                    return;
                }

                controlador.setIdPodcastActual(id);
                String[] info = controlador.pedirInfoPodcast();

                if (info != null) {
                    txtNombre.setText(info[0]);
                    txtTematica.setText(info[1]);
                    txtDescripcion.setText(info[2]);
                    btnEliminar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(vEliminarPodcast.this, "Podcast no encontrado.");
                    txtNombre.setText(""); txtTematica.setText(""); txtDescripcion.setText("");
                    btnEliminar.setEnabled(false);
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();

                int confirmar = JOptionPane.showConfirmDialog(
                    vEliminarPodcast.this,
                    "¿Estás seguro de eliminar este podcast?\nEsto borrará todas sus temporadas y episodios.",
                    "Confirmación de Borrado",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    boolean exito = controlador.eliminarPodcastBD(id);
                    if (exito) {
                        JOptionPane.showMessageDialog(vEliminarPodcast.this, "Podcast eliminado con éxito.");
                        controlador.abrirGestionPodcasts();
                    } else {
                        JOptionPane.showMessageDialog(vEliminarPodcast.this, "Error al eliminar de la base de datos.");
                    }
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.abrirGestionPodcasts();
            }
        });
    }
}