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

public class vCrearPodcast extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtTematica;
    private JTextArea txtDescripcion;

    public vCrearPodcast(GestorControlador controlador) {
        setTitle("Administración - Registrar Nuevo Podcast");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 460);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("REGISTRAR PODCAST");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(140, 25, 250, 30);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(50, 90, 110, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNombre.setBounds(170, 90, 270, 25);
        contentPane.add(txtNombre);

        JLabel lblTematica = new JLabel("Temática:");
        lblTematica.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTematica.setBounds(50, 140, 110, 25);
        contentPane.add(lblTematica);

        txtTematica = new JTextField();
        txtTematica.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTematica.setBounds(170, 140, 270, 25);
        contentPane.add(txtTematica);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDescripcion.setBounds(50, 190, 110, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(txtDescripcion);
        scrollPane.setBounds(170, 190, 270, 100);
        contentPane.add(scrollPane);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(46, 139, 87));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(100, 340, 130, 35);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(178, 34, 34));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 340, 130, 35);
        contentPane.add(btnVolver);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                String tematica = txtTematica.getText().trim();
                String descripcion = txtDescripcion.getText().trim();

                if (nombre.isEmpty() || tematica.isEmpty() || descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(vCrearPodcast.this, "Por favor, complete todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean exito = controlador.registrarNuevoPodcast(nombre, tematica, descripcion);

                if (exito) {
                    JOptionPane.showMessageDialog(vCrearPodcast.this, "¡Podcast registrado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    controlador.abrirGestionPodcasts();
                } else {
                    JOptionPane.showMessageDialog(vCrearPodcast.this, "Error al guardar el podcast en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
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