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

public class vModificarCancion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtTitulo;
    private JTextField txtDuracion;
    private JButton btnGuardar;

    public vModificarCancion(GestorControlador controlador) {
        setTitle("Administración - Modificar Canción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 420);
        setLocationRelativeTo(null); 
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("MODIFICAR CANCIÓN");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(150, 20, 250, 30);
        contentPane.add(lblTitulo);

        // --- SECCIÓN DE BÚSQUEDA ---
        JLabel lblIdBuscar = new JLabel("ID de la Canción:");
        lblIdBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIdBuscar.setBounds(40, 80, 120, 25);
        contentPane.add(lblIdBuscar);

        txtIdBuscar = new JTextField();
        txtIdBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtIdBuscar.setBounds(170, 80, 140, 25);
        contentPane.add(txtIdBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnBuscar.setBackground(new Color(70, 130, 180)); 
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBounds(325, 80, 115, 25);
        contentPane.add(btnBuscar);

        // Línea divisoria
        JLabel lblSeparador = new JLabel();
        lblSeparador.setOpaque(true);
        lblSeparador.setBackground(Color.LIGHT_GRAY);
        lblSeparador.setBounds(40, 125, 420, 2);
        contentPane.add(lblSeparador);

        // --- SECCIÓN DE CAMPOS EDITABLES ---
        JLabel lblTituloCancion = new JLabel("Título:");
        lblTituloCancion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTituloCancion.setBounds(40, 150, 110, 25);
        contentPane.add(lblTituloCancion);

        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTitulo.setBounds(170, 150, 270, 25);
        txtTitulo.setEnabled(false);
        contentPane.add(txtTitulo);

        JLabel lblDuracion = new JLabel("Duración (seg):");
        lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDuracion.setBounds(40, 210, 110, 25);
        contentPane.add(lblDuracion);

        txtDuracion = new JTextField();
        txtDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDuracion.setBounds(170, 210, 270, 25);
        txtDuracion.setEnabled(false);
        contentPane.add(txtDuracion);

        // --- BOTONES DE ACCIÓN ---
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnGuardar.setBackground(new Color(46, 139, 87));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(80, 300, 160, 35);
        btnGuardar.setEnabled(false);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(178, 34, 34)); 
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 300, 160, 35);
        contentPane.add(btnVolver);

        // --- MANEJO DE EVENTOS ---

        // Buscar Canción
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(vModificarCancion.this, "Por favor, introduce un ID.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                controlador.setIdCancionActual(id);
                String[] info = controlador.pedirInfoCancion(); 

                if (info != null && info.length > 2) {
                    txtTitulo.setText(info[1]);
                    txtDuracion.setText(info[2]);
                    
                    txtTitulo.setEnabled(true);
                    txtDuracion.setEnabled(true);
                    btnGuardar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(vModificarCancion.this, "Canción no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtTitulo.setText("");
                    txtDuracion.setText("");
                    txtTitulo.setEnabled(false);
                    txtDuracion.setEnabled(false);
                    btnGuardar.setEnabled(false);
                }
            }
        });

        
        // Guardar Cambios
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                String titulo = txtTitulo.getText().trim();
                String duracionStr = txtDuracion.getText().trim();

                if (titulo.isEmpty() || duracionStr.isEmpty()) {
                    JOptionPane.showMessageDialog(vModificarCancion.this, "Los campos no pueden quedar vacíos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int duracion = Integer.parseInt(duracionStr);
                    
                  
                    boolean exito = controlador.actualizarCancionExistente(id, titulo, duracion);

                    if (exito) {
                        JOptionPane.showMessageDialog(vModificarCancion.this, "¡Canción actualizada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        controlador.abrirGestionMusica();
                    } else {
                        JOptionPane.showMessageDialog(vModificarCancion.this, "Error al intentar actualizar la canción.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vModificarCancion.this, "La duración debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
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