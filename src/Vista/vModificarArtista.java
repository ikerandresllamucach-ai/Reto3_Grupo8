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
import Modelo.Artista;

public class vModificarArtista extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtNombre;
    private JTextField txtGenero;
    private JTextArea txtDescripcion;
    private JButton btnGuardar;

    public vModificarArtista(GestorControlador controlador) {
        setTitle("Administración - Modificar Artista Existente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 500);
        setLocationRelativeTo(null); // Centra la ventana
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("MODIFICAR ARTISTA");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(150, 20, 250, 30);
        contentPane.add(lblTitulo);

        // --- SECCIÓN DE BÚSQUEDA ---
        JLabel lblIdBuscar = new JLabel("ID del Artista:");
        lblIdBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIdBuscar.setBounds(40, 80, 110, 25);
        contentPane.add(lblIdBuscar);

        txtIdBuscar = new JTextField();
        txtIdBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtIdBuscar.setBounds(150, 80, 160, 25);
        contentPane.add(txtIdBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnBuscar.setBackground(new Color(70, 130, 180)); // Azul acero
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBounds(325, 80, 115, 25);
        contentPane.add(btnBuscar);

        // Línea divisoria visual simulada con un JLabel de fondo negro/gris
        JLabel lblSeparador = new JLabel();
        lblSeparador.setOpaque(true);
        lblSeparador.setBackground(Color.LIGHT_GRAY);
        lblSeparador.setBounds(40, 125, 420, 2);
        contentPane.add(lblSeparador);

        // --- SECCIÓN DE CAMPOS EDITABLES ---
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(40, 150, 110, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNombre.setBounds(150, 150, 290, 25);
        txtNombre.setEnabled(false); // Deshabilitado hasta que busque un artista
        contentPane.add(txtNombre);

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGenero.setBounds(40, 200, 110, 25);
        contentPane.add(lblGenero);

        txtGenero = new JTextField();
        txtGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtGenero.setBounds(150, 200, 290, 25);
        txtGenero.setEnabled(false);
        contentPane.add(txtGenero);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDescripcion.setBounds(40, 250, 110, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEnabled(false);
        
        JScrollPane scrollPane = new JScrollPane(txtDescripcion);
        scrollPane.setBounds(150, 250, 290, 100);
        contentPane.add(scrollPane);

        // --- BOTONES DE ACCIÓN COMPLETOS ---
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnGuardar.setBackground(new Color(46, 139, 87)); // Verde
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(80, 390, 160, 35);
        btnGuardar.setEnabled(false); // Inactivo hasta que carguen datos válidos
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(178, 34, 34)); // Rojo
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 390, 160, 35);
        contentPane.add(btnVolver);

        // --- MANEJO DE EVENTOS (LISTENERS) ---

        // Evento Buscar Artista
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idBuscar = txtIdBuscar.getText().trim();
                if (idBuscar.isEmpty()) {
                    JOptionPane.showMessageDialog(vModificarArtista.this, "Por favor, introduce el ID de un artista.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Guardamos el ID en el controlador para simular el estado actual
                controlador.setIdArtistaActual(idBuscar);
                // Solicitamos la información del modelo a través del controlador
                Artista art = controlador.pedirInfoArtista();

                if (art != null && art.getNombreArtistico() != null) {
                    // Rellenamos los datos del artista encontrado
                    txtNombre.setText(art.getNombreArtistico());
                    txtGenero.setText(art.getGenero());                    
                    txtDescripcion.setText(art.getDescripcion());
                    
                    // Habilitamos la edición y el botón de guardar cambios
                    txtNombre.setEnabled(true);
                    txtGenero.setEnabled(true);
                    txtDescripcion.setEnabled(true);
                    btnGuardar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(vModificarArtista.this, "No se encontró ningún artista con el ID especificado.", "Artista No Encontrado", JOptionPane.ERROR_MESSAGE);
                    // Limpiamos y deshabilitamos por seguridad si la búsqueda falla
                    txtNombre.setText("");
                    txtGenero.setText("");
                    txtDescripcion.setText("");
                    txtNombre.setEnabled(false);
                    txtGenero.setEnabled(false);
                    txtDescripcion.setEnabled(false);
                    btnGuardar.setEnabled(false);
                }
            }
        });

        // Evento Guardar Cambios Actualizados
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                String nombre = txtNombre.getText().trim();
                String genero = txtGenero.getText().trim();
                String descripcion = txtDescripcion.getText().trim();

                if (nombre.isEmpty() || genero.isEmpty() || descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(vModificarArtista.this, "Ningún campo editable puede quedar vacío.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Pasamos los datos al controlador para aplicar el UPDATE SQL
                boolean exito = controlador.actualizarArtistaExistente(id, nombre, genero, descripcion);

                if (exito) {
                    JOptionPane.showMessageDialog(vModificarArtista.this, "¡Los datos del artista se han actualizado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    controlador.abrirGestionMusica();
                } else {
                    JOptionPane.showMessageDialog(vModificarArtista.this, "Hubo un error al intentar actualizar el artista en la base de datos.", "Error de Actualización", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Evento Volver sin Guardar
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.abrirGestionMusica();
            }
        });
    }
}