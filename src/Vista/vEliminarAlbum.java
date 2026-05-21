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

public class vEliminarAlbum extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtTitulo;
    private JTextField txtGenero;
    private JTextField txtFecha;
    private JButton btnEliminar;

    public vEliminarAlbum(GestorControlador controlador) {
        setTitle("Administración - Eliminar Álbum");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 480);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("ELIMINAR ÁLBUM");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(178, 34, 34)); // Rojo para denotar peligro
        lblTitulo.setBounds(160, 20, 250, 30);
        contentPane.add(lblTitulo);

        // --- SECCIÓN DE BÚSQUEDA ---
        JLabel lblIdBuscar = new JLabel("ID del Álbum:");
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

        // --- SECCIÓN DE VISTA DE DATOS (SOLO LECTURA) ---
        JLabel lblTituloAlb = new JLabel("Título:");
        lblTituloAlb.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTituloAlb.setBounds(40, 150, 110, 25);
        contentPane.add(lblTituloAlb);

        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTitulo.setBounds(150, 150, 290, 25);
        txtTitulo.setEditable(false); // Solo lectura
        contentPane.add(txtTitulo);

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGenero.setBounds(40, 210, 110, 25);
        contentPane.add(lblGenero);

        txtGenero = new JTextField();
        txtGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtGenero.setBounds(150, 210, 290, 25);
        txtGenero.setEditable(false); // Solo lectura
        contentPane.add(txtGenero);

        JLabel lblFecha = new JLabel("Fecha Pub.:");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFecha.setBounds(40, 270, 110, 25);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtFecha.setBounds(150, 270, 290, 25);
        txtFecha.setEditable(false); // Solo lectura
        contentPane.add(txtFecha);

        // --- BOTONES DE ACCIÓN ---
        btnEliminar = new JButton("Eliminar Álbum");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnEliminar.setBackground(new Color(178, 34, 34)); // Rojo fuego
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBounds(80, 360, 160, 35);
        btnEliminar.setEnabled(false); // Inactivo hasta que la búsqueda sea exitosa
        contentPane.add(btnEliminar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(112, 128, 144)); // Gris pizarra
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 360, 160, 35);
        contentPane.add(btnVolver);

        // --- MANEJO DE EVENTOS ---

        // Evento Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(vEliminarAlbum.this, "Por favor, introduce el ID del álbum.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                controlador.setIdAlbumActual(id);
                String[] info = controlador.pedirInfoAlbum(); // Retorna [Titulo, Genero, Fecha]

                if (info != null) {
                    txtTitulo.setText(info[0]);
                    txtGenero.setText(info[1]);
                    txtFecha.setText(info[2]);
                    btnEliminar.setEnabled(true); // Activamos el botón si existe
                } else {
                    JOptionPane.showMessageDialog(vEliminarAlbum.this, "No se encontró ningún álbum con ese ID.", "No Encontrado", JOptionPane.ERROR_MESSAGE);
                    txtTitulo.setText("");
                    txtGenero.setText("");
                    txtFecha.setText("");
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
                    vEliminarAlbum.this,
                    "¿Estás seguro de que deseas eliminar este álbum?\nEsta acción borrará también todas las canciones que contiene.",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    // Llama al método eliminarAlbumBD que ya tienes configurado en tu GestorControlador
                    boolean exito = controlador.eliminarAlbumBD(id);

                    if (exito) {
                        JOptionPane.showMessageDialog(vEliminarAlbum.this, "El álbum ha sido eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        controlador.abrirGestionMusica();
                    } else {
                        JOptionPane.showMessageDialog(vEliminarAlbum.this, "Error al intentar eliminar el álbum de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
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