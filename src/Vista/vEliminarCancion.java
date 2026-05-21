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

public class vEliminarCancion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtTitulo;
    private JTextField txtDuracion;
    private JButton btnEliminar;

    public vEliminarCancion(GestorControlador controlador) {
        setTitle("Administración - Eliminar Canción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 420);
        setLocationRelativeTo(null); // Centra la ventana
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("ELIMINAR CANCIÓN");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(178, 34, 34)); // Rojo para advertencia
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

        // --- SECCIÓN DE DATOS (SOLO LECTURA) ---
        JLabel lblTituloCancion = new JLabel("Título:");
        lblTituloCancion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTituloCancion.setBounds(40, 150, 110, 25);
        contentPane.add(lblTituloCancion);

        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTitulo.setBounds(170, 150, 270, 25);
        txtTitulo.setEditable(false); // No editable
        contentPane.add(txtTitulo);

        JLabel lblDuracion = new JLabel("Duración (seg):");
        lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDuracion.setBounds(40, 210, 110, 25);
        contentPane.add(lblDuracion);

        txtDuracion = new JTextField();
        txtDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDuracion.setBounds(170, 210, 270, 25);
        txtDuracion.setEditable(false); // No editable
        contentPane.add(txtDuracion);

        // --- BOTONES DE ACCIÓN ---
        btnEliminar = new JButton("Eliminar Canción");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnEliminar.setBackground(new Color(178, 34, 34)); 
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBounds(80, 300, 160, 35);
        btnEliminar.setEnabled(false); 
        contentPane.add(btnEliminar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(112, 128, 144)); 
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 300, 160, 35);
        contentPane.add(btnVolver);

        
        // --- MANEJO DE EVENTOS ---

        // Evento Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(vEliminarCancion.this, "Por favor, introduce el ID de la canción.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                controlador.setIdCancionActual(id);
                String[] info = controlador.pedirInfoCancion(); 

                if (info != null && info.length > 2) {
                    txtTitulo.setText(info[1]);
                    txtDuracion.setText(info[2]);
                    btnEliminar.setEnabled(true); 
                } else {
                    JOptionPane.showMessageDialog(vEliminarCancion.this, "No se encontró ninguna canción con ese ID.", "No Encontrada", JOptionPane.ERROR_MESSAGE);
                    txtTitulo.setText("");
                    txtDuracion.setText("");
                    btnEliminar.setEnabled(false);
                }
            }
        });

        
        // Evento Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();

                int confirmar = JOptionPane.showConfirmDialog(
                    vEliminarCancion.this,
                    "¿Estás completamente seguro de que deseas eliminar esta canción?\nEsta acción es irreversible.",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    // Llamamos al método eliminarCancionBD del controlador (asegúrate de tenerlo implementado)
                    boolean exito = controlador.eliminarCancionBD(id);

                    if (exito) {
                        JOptionPane.showMessageDialog(vEliminarCancion.this, "La canción ha sido eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        controlador.abrirGestionMusica();
                    } else {
                        JOptionPane.showMessageDialog(vEliminarCancion.this, "Error al intentar borrar la canción de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
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