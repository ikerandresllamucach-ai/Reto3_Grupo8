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

public class vCrearEpisodio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTituloEp;
    private JTextField txtDuracion;
    private JTextField txtIdTemporada;

    public vCrearEpisodio(GestorControlador controlador) {
        setTitle("Administración - Registrar Nuevo Episodio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 380);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("REGISTRAR EPISODIO");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(140, 25, 250, 30);
        contentPane.add(lblTitulo);

        // Título del Episodio
        JLabel lblTituloEp = new JLabel("Título:");
        lblTituloEp.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTituloEp.setBounds(50, 90, 130, 25);
        contentPane.add(lblTituloEp);

        txtTituloEp = new JTextField();
        txtTituloEp.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTituloEp.setBounds(190, 90, 250, 25);
        contentPane.add(txtTituloEp);

        // Duración (en segundos)
        JLabel lblDuracion = new JLabel("Duración (seg):");
        lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDuracion.setBounds(50, 140, 130, 25);
        contentPane.add(lblDuracion);

        txtDuracion = new JTextField();
        txtDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDuracion.setBounds(190, 140, 250, 25);
        contentPane.add(txtDuracion);

        // ID de la Temporada vinculada
        JLabel lblIdTemporada = new JLabel("ID Temporada:");
        lblIdTemporada.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblIdTemporada.setBounds(50, 190, 130, 25);
        contentPane.add(lblIdTemporada);

        txtIdTemporada = new JTextField();
        txtIdTemporada.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtIdTemporada.setBounds(190, 190, 250, 25);
        contentPane.add(txtIdTemporada);

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
                String titulo = txtTituloEp.getText().trim();
                String duracionStr = txtDuracion.getText().trim();
                String idTemporada = txtIdTemporada.getText().trim();

                if (titulo.isEmpty() || duracionStr.isEmpty() || idTemporada.isEmpty()) {
                    JOptionPane.showMessageDialog(vCrearEpisodio.this, "Por favor, complete todos los campos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int duracion = Integer.parseInt(duracionStr);
                    boolean exito = controlador.registrarNuevoEpisodio(titulo, duracion, idTemporada);

                    if (exito) {
                        JOptionPane.showMessageDialog(vCrearEpisodio.this, "¡Episodio registrado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        controlador.abrirGestionPodcasts();
                    } else {
                        JOptionPane.showMessageDialog(vCrearEpisodio.this, "Error al registrar el episodio. Verifique que el ID de Temporada exista.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vCrearEpisodio.this, "La duración debe ser un número entero (segundos).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
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