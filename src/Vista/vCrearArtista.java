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

public class vCrearArtista extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtGenero;
    private JTextArea txtDescripcion;

    public vCrearArtista(GestorControlador controlador) {
        setTitle("Administración - Registrar Nuevo Artista");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 450);
        setLocationRelativeTo(null); 
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("REGISTRAR ARTISTA");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(140, 25, 250, 30);
        contentPane.add(lblTitulo);

        // Etiqueta y campo: Nombre Artístico
        JLabel lblNombre = new JLabel("Nombre Artístico:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(50, 90, 120, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNombre.setBounds(180, 90, 260, 25);
        contentPane.add(txtNombre);
        txtNombre.setColumns(10);

        // Etiqueta y campo: Género Musical
        JLabel lblGenero = new JLabel("Género Principal:");
        lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGenero.setBounds(50, 140, 120, 25);
        contentPane.add(lblGenero);

        txtGenero = new JTextField();
        txtGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtGenero.setBounds(180, 140, 260, 25);
        contentPane.add(txtGenero);
        txtGenero.setColumns(10);

        // Etiqueta y área de texto: Descripción/Biografía
        
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDescripcion.setBounds(50, 190, 120, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(txtDescripcion);
        scrollPane.setBounds(180, 190, 260, 100);
        contentPane.add(scrollPane);

        // Botón: Guardar Datos
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(46, 139, 87)); // Verde bosque
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(100, 330, 130, 35);
        contentPane.add(btnGuardar);

        // Botón: Cancelar / Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(178, 34, 34)); 
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(270, 330, 130, 35);
        contentPane.add(btnVolver);

        // --- MANEJO DE EVENTOS (LISTENERS) ---

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                String genero = txtGenero.getText().trim();
                String descripcion = txtDescripcion.getText().trim();

              
                if (nombre.isEmpty() || genero.isEmpty() || descripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(vCrearArtista.this, 
                        "Por favor, rellene todos los campos antes de guardar.", 
                        "Campos Incompletos", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

          
                boolean exito = controlador.registrarNuevoArtista(nombre, genero, descripcion);

                if (exito) {
                    JOptionPane.showMessageDialog(vCrearArtista.this, 
                        "¡Artista registrado correctamente en la base de datos!", 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                
                    controlador.abrirGestionMusica();
                } else {
                    JOptionPane.showMessageDialog(vCrearArtista.this, 
                        "Error crítico al intentar guardar el artista.", 
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