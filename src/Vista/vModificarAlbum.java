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

public class vModificarAlbum extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtTitulo;
    private JTextField txtGenero;
    private JTextField txtFecha;
    private JButton btnGuardar;

    public vModificarAlbum(GestorControlador controlador) {
        setTitle("Administración - Modificar Álbum Existente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 480);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("MODIFICAR ÁLBUM");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
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
        JLabel lblTituloAlb = new JLabel("Título:");
        lblTituloAlb.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTituloAlb.setBounds(40, 150, 110, 25);
        contentPane.add(lblTituloAlb);

        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTitulo.setBounds(150, 150, 290, 25);
        txtTitulo.setEnabled(false);
        contentPane.add(txtTitulo);

        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGenero.setBounds(40, 210, 110, 25);
        contentPane.add(lblGenero);

        txtGenero = new JTextField();
        txtGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtGenero.setBounds(150, 210, 290, 25);
        txtGenero.setEnabled(false);
        contentPane.add(txtGenero);

        JLabel lblFecha = new JLabel("Fecha Pub.:");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFecha.setBounds(40, 270, 110, 25);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtFecha.setBounds(150, 270, 290, 25);
        txtFecha.setEnabled(false);
        contentPane.add(txtFecha);

        // --- BOTONES DE ACCIÓN ---
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnGuardar.setBackground(new Color(46, 139, 87));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(80, 360, 160, 35);
        btnGuardar.setEnabled(false);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(178, 34, 34));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 360, 160, 35);
        contentPane.add(btnVolver);

        // --- MANEJO DE EVENTOS ---

        // Buscar Álbum
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(vModificarAlbum.this, "Introduzca un ID.");
                    return;
                }

                controlador.setIdAlbumActual(id);
                String[] info = controlador.pedirInfoAlbum(); // Retorna [Titulo, Genero, Fecha]

                if (info != null) {
                    txtTitulo.setText(info[0]);
                    txtGenero.setText(info[1]);
                    txtFecha.setText(info[2]);
                    
                    txtTitulo.setEnabled(true);
                    txtGenero.setEnabled(true);
                    txtFecha.setEnabled(true);
                    btnGuardar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(vModificarAlbum.this, "Álbum no encontrado.");
                    txtTitulo.setEnabled(false);
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
                String genero = txtGenero.getText().trim();
                String fecha = txtFecha.getText().trim();

                boolean exito = controlador.actualizarAlbumExistente(id, titulo, genero, fecha);

                if (exito) {
                    JOptionPane.showMessageDialog(vModificarAlbum.this, "Álbum actualizado.");
                    controlador.abrirGestionMusica();
                } else {
                    JOptionPane.showMessageDialog(vModificarAlbum.this, "Error al actualizar.");
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