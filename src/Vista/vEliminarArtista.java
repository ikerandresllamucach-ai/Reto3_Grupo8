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

public class vEliminarArtista extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtNombre;
    private JTextField txtGenero;
    private JTextArea txtDescripcion;
    private JButton btnEliminar;

    public vEliminarArtista(GestorControlador controlador) {
        setTitle("Administración - Eliminar Artista");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("ELIMINAR ARTISTA");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(178, 34, 34)); // Rojo oscuro para denotar peligro/borrado
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

        // Línea divisoria
        JLabel lblSeparador = new JLabel();
        lblSeparador.setOpaque(true);
        lblSeparador.setBackground(Color.LIGHT_GRAY);
        lblSeparador.setBounds(40, 125, 420, 2);
        contentPane.add(lblSeparador);

        // --- SECCIÓN DE VISTA DE DATOS (NO EDITABLES) ---
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(40, 150, 110, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNombre.setBounds(150, 150, 290, 25);
        txtNombre.setEditable(false); // Solo lectura
        contentPane.add(txtNombre);

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGenero.setBounds(40, 200, 110, 25);
        contentPane.add(lblGenero);

        txtGenero = new JTextField();
        txtGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtGenero.setBounds(150, 200, 290, 25);
        txtGenero.setEditable(false); // Solo lectura
        contentPane.add(txtGenero);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDescripcion.setBounds(40, 250, 110, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false); // Solo lectura
        
        JScrollPane scrollPane = new JScrollPane(txtDescripcion);
        scrollPane.setBounds(150, 250, 290, 100);
        contentPane.add(scrollPane);

        // --- BOTONES DE ACCIÓN ---
        btnEliminar = new JButton("Eliminar Artista");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnEliminar.setBackground(new Color(178, 34, 34)); // Rojo fuego
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBounds(80, 390, 160, 35);
        btnEliminar.setEnabled(false); // Inactivo hasta que se busque un artista válido
        contentPane.add(btnEliminar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(112, 128, 144)); // Gris pizarra
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
                    JOptionPane.showMessageDialog(vEliminarArtista.this, "Por favor, introduce el ID del artista.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                controlador.setIdArtistaActual(idBuscar);
                Artista art = controlador.pedirInfoArtista();

                if (art != null && art.getNombreArtistico() != null) {
                    txtNombre.setText(art.getNombreArtistico());
                    
                    // Nota: Si en tu modelo 'Artista' el método es getGenero() en vez de getGeneroMusical(), cámbialo aquí.
                    txtGenero.setText(art.getGenero());                    
                    txtDescripcion.setText(art.getDescripcion());
                    
                    // Activamos el botón de eliminación al confirmar la existencia
                    btnEliminar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(vEliminarArtista.this, "No se encontró ningún artista con el ID especificado.", "Artista No Encontrado", JOptionPane.ERROR_MESSAGE);
                    txtNombre.setText("");
                    txtGenero.setText("");
                    txtDescripcion.setText("");
                    btnEliminar.setEnabled(false);
                }
            }
        });

        // Evento Eliminar Artista
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();

                // Diálogo de confirmación de seguridad crítico antes del borrado en cascada
                int confirmar = JOptionPane.showConfirmDialog(
                    vEliminarArtista.this,
                    "¿Estás completamente seguro de que deseas eliminar a este artista?\nEsta acción eliminará también todos sus álbumes y canciones asociadas.",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    // Llamamos a la función del controlador que ejecuta el DELETE SQL
                    boolean exito = controlador.eliminarArtistaBD(id);

                    if (exito) {
                        JOptionPane.showMessageDialog(vEliminarArtista.this, "El artista ha sido eliminado correctamente de la base de datos.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        controlador.abrirGestionMusica();
                    } else {
                        JOptionPane.showMessageDialog(vEliminarArtista.this, "Error al intentar borrar el artista de la base de datos.", "Error de Borrado", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Evento Volver
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.abrirGestionMusica();
            }
        });
    }
}