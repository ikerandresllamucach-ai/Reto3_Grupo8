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

public class vCrearAlbum extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTitulo;
    private JTextField txtGenero;
    private JTextField txtFecha;
    private JTextField txtIdArtista;

    public vCrearAlbum(GestorControlador controlador) {
        setTitle("Administración - Registrar Nuevo Álbum");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 420);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("REGISTRAR ÁLBUM");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(150, 25, 250, 30);
        contentPane.add(lblTitulo);

        // Etiqueta y campo: Título del Álbum
        JLabel lblTituloAlbum = new JLabel("Título del Álbum:");
        lblTituloAlbum.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTituloAlbum.setBounds(50, 90, 130, 25);
        contentPane.add(lblTituloAlbum);

        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTitulo.setBounds(190, 90, 250, 25);
        contentPane.add(txtTitulo);
        txtTitulo.setColumns(10);

        // Etiqueta y campo: Género
        JLabel lblGenero = new JLabel("Género Musical:");
        lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGenero.setBounds(50, 140, 130, 25);
        contentPane.add(lblGenero);

        txtGenero = new JTextField();
        txtGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtGenero.setBounds(190, 140, 250, 25);
        contentPane.add(txtGenero);
        txtGenero.setColumns(10);

        // Etiqueta y campo: Fecha de Lanzamiento
        JLabel lblFecha = new JLabel("Fecha (AAAA-MM-DD):");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFecha.setBounds(50, 190, 140, 25);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtFecha.setBounds(190, 190, 250, 25);
        contentPane.add(txtFecha);
        txtFecha.setColumns(10);

        // Etiqueta y campo: ID del Artista dueño del álbum
        JLabel lblIdArtista = new JLabel("ID del Artista:");
        lblIdArtista.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblIdArtista.setBounds(50, 240, 130, 25);
        contentPane.add(lblIdArtista);

        txtIdArtista = new JTextField();
        txtIdArtista.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtIdArtista.setBounds(190, 240, 250, 25);
        contentPane.add(txtIdArtista);
        txtIdArtista.setColumns(10);

        // Botón: Guardar Datos
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(46, 139, 87)); // Verde bosque
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(100, 310, 130, 35);
        contentPane.add(btnGuardar);

        // Botón: Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(178, 34, 34)); // Rojo fuego
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 310, 130, 35);
        contentPane.add(btnVolver);

        // --- MANEJO DE EVENTOS ---

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = txtTitulo.getText().trim();
                String genero = txtGenero.getText().trim();
                String fecha = txtFecha.getText().trim();
                String idMusico = txtIdArtista.getText().trim();

                // Validación simple de campos obligatorios
                if (titulo.isEmpty() || genero.isEmpty() || fecha.isEmpty() || idMusico.isEmpty()) {
                    JOptionPane.showMessageDialog(vCrearAlbum.this, 
                        "Por favor, complete todos los campos de texto.", 
                        "Campos Incompletos", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Llamada al controlador que creamos previamente (mapea con CrearAlbum en el Modelo)
                boolean exito = controlador.registrarNuevoAlbum(titulo, genero, fecha, idMusico);

                if (exito) {
                    JOptionPane.showMessageDialog(vCrearAlbum.this, 
                        "¡Álbum registrado con éxito!", 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE);
                    controlador.abrirGestionMusica();
                } else {
                    JOptionPane.showMessageDialog(vCrearAlbum.this, 
                        "Error al intentar registrar el álbum. Verifique que el ID del Artista exista.", 
                        "Error de Base de Datos", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.abrirGestionMusica();
            }
        });
    }
}