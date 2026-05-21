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

public class vCrearCancion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtDuracion;
    private JTextField txtIdAlbum;

    public vCrearCancion(GestorControlador controlador) {
        setTitle("Administración - Registrar Nueva Canción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 380);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("REGISTRAR CANCIÓN");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(140, 25, 250, 30);
        contentPane.add(lblTitulo);

        // Etiqueta y campo: Nombre de la canción
        JLabel lblNombreCancion = new JLabel("Título:");
        lblNombreCancion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombreCancion.setBounds(50, 90, 130, 25);
        contentPane.add(lblNombreCancion);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNombre.setBounds(190, 90, 250, 25);
        contentPane.add(txtNombre);
        txtNombre.setColumns(10);

        // Etiqueta y campo: Duración
        JLabel lblDuracion = new JLabel("Duración (seg):");
        lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDuracion.setBounds(50, 140, 130, 25);
        contentPane.add(lblDuracion);

        txtDuracion = new JTextField();
        txtDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDuracion.setBounds(190, 140, 250, 25);
        contentPane.add(txtDuracion);
        txtDuracion.setColumns(10);

        // Etiqueta y campo: ID del Álbum
        JLabel lblIdAlbum = new JLabel("ID del Álbum:");
        lblIdAlbum.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblIdAlbum.setBounds(50, 190, 130, 25);
        contentPane.add(lblIdAlbum);

        txtIdAlbum = new JTextField();
        txtIdAlbum.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtIdAlbum.setBounds(190, 190, 250, 25);
        contentPane.add(txtIdAlbum);
        txtIdAlbum.setColumns(10);

        // Botón: Guardar Datos
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(46, 139, 87)); 
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(100, 270, 130, 35);
        contentPane.add(btnGuardar);

        // Botón: Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(178, 34, 34));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 270, 130, 35);
        contentPane.add(btnVolver);

        // --- MANEJO DE EVENTOS ---

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                String duracionStr = txtDuracion.getText().trim();
                String idAlbum = txtIdAlbum.getText().trim();

                // Validación de campos vacíos
                if (nombre.isEmpty() || duracionStr.isEmpty() || idAlbum.isEmpty()) {
                    JOptionPane.showMessageDialog(vCrearCancion.this, 
                        "Por favor, complete todos los campos.", 
                        "Campos Incompletos", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    // Convertimos la duración a entero
                    int duracion = Integer.parseInt(duracionStr);

                    // Llamada al método registrarNuevaCancion del GestorControlador
                    boolean exito = controlador.registrarNuevaCancion(nombre, duracion, idAlbum);

                    if (exito) {
                        JOptionPane.showMessageDialog(vCrearCancion.this, 
                            "¡Canción registrada correctamente!", 
                            "Éxito", 
                            JOptionPane.INFORMATION_MESSAGE);
                        controlador.abrirGestionMusica();
                    } else {
                        JOptionPane.showMessageDialog(vCrearCancion.this, 
                            "Error al registrar la canción. Verifique que el ID del Álbum exista.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vCrearCancion.this, 
                        "La duración debe ser un número entero (segundos).", 
                        "Error de Formato", 
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