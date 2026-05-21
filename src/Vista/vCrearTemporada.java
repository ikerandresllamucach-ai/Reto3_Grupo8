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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Controlador.GestorControlador;

public class vCrearTemporada extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNumero;
    private JTextField txtFecha;
    private JTextField txtIdPodcast;

    public vCrearTemporada(GestorControlador controlador) {
        setTitle("Administración - Registrar Nueva Temporada");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 380);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("REGISTRAR TEMPORADA");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(120, 25, 270, 30);
        contentPane.add(lblTitulo);

        // Número de Temporada
        JLabel lblNumero = new JLabel("Nº Temporada:");
        lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNumero.setBounds(50, 90, 130, 25);
        contentPane.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNumero.setBounds(190, 90, 250, 25);
        contentPane.add(txtNumero);

        // Fecha de Lanzamiento
        JLabel lblFecha = new JLabel("Fecha (AAAA-MM-DD):");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFecha.setBounds(50, 140, 140, 25);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtFecha.setBounds(190, 140, 250, 25);
        contentPane.add(txtFecha);

        // ID del Podcast
        JLabel lblIdPodcast = new JLabel("ID del Podcast:");
        lblIdPodcast.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblIdPodcast.setBounds(50, 190, 130, 25);
        contentPane.add(lblIdPodcast);

        txtIdPodcast = new JTextField();
        txtIdPodcast.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtIdPodcast.setBounds(190, 190, 250, 25);
        contentPane.add(txtIdPodcast);

        // Botón Guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(46, 139, 87));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(100, 270, 130, 35);
        contentPane.add(btnGuardar);

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(178, 34, 34));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 270, 130, 35);
        contentPane.add(btnVolver);

        // EVENTOS
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numero = txtNumero.getText().trim();
                String fecha = txtFecha.getText().trim();
                String idPodcast = txtIdPodcast.getText().trim();

                if (numero.isEmpty() || fecha.isEmpty() || idPodcast.isEmpty()) {
                    JOptionPane.showMessageDialog(vCrearTemporada.this, "Por favor, complete todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean exito = controlador.registrarNuevaTemporada(numero, fecha, idPodcast);

                if (exito) {
                    JOptionPane.showMessageDialog(vCrearTemporada.this, "¡Temporada registrada correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    controlador.abrirGestionPodcasts();
                } else {
                    JOptionPane.showMessageDialog(vCrearTemporada.this, "Error al registrar. Verifique que el ID del Podcast existe.", "Error", JOptionPane.ERROR_MESSAGE);
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