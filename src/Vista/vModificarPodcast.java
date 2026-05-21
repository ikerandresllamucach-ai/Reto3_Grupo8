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

public class vModificarPodcast extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtNombre;
    private JTextField txtTematica;
    private JTextArea txtDescripcion;
    private JButton btnGuardar;

    public vModificarPodcast(GestorControlador controlador) {
        setTitle("Administración - Modificar Podcast");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 520);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("MODIFICAR PODCAST");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(150, 20, 250, 30);
        contentPane.add(lblTitulo);

        // BUSCADOR
        JLabel lblIdBuscar = new JLabel("ID del Podcast:");
        lblIdBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIdBuscar.setBounds(40, 80, 110, 25);
        contentPane.add(lblIdBuscar);

        txtIdBuscar = new JTextField();
        txtIdBuscar.setBounds(160, 80, 150, 25);
        contentPane.add(txtIdBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(70, 130, 180));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBounds(325, 80, 115, 25);
        contentPane.add(btnBuscar);

        JLabel lblSeparador = new JLabel();
        lblSeparador.setOpaque(true);
        lblSeparador.setBackground(Color.LIGHT_GRAY);
        lblSeparador.setBounds(40, 125, 420, 2);
        contentPane.add(lblSeparador);

        // FORMULARIO EDITABLE
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(40, 150, 110, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setEnabled(false);
        txtNombre.setBounds(160, 150, 280, 25);
        contentPane.add(txtNombre);

        JLabel lblTematica = new JLabel("Temática:");
        lblTematica.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTematica.setBounds(40, 200, 110, 25);
        contentPane.add(lblTematica);

        txtTematica = new JTextField();
        txtTematica.setEnabled(false);
        txtTematica.setBounds(160, 200, 280, 25);
        contentPane.add(txtTematica);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDescripcion.setBounds(40, 250, 110, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEnabled(false);
        
        JScrollPane scrollPane = new JScrollPane(txtDescripcion);
        scrollPane.setBounds(160, 250, 280, 100);
        contentPane.add(scrollPane);

  
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnGuardar.setBackground(new Color(46, 139, 87));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(80, 390, 160, 35);
        btnGuardar.setEnabled(false);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBackground(new Color(178, 34, 34));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(260, 390, 160, 35);
        contentPane.add(btnVolver);

      
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(vModificarPodcast.this, "Introduzca un ID.");
                    return;
                }

                controlador.setIdPodcastActual(id);
                String[] info = controlador.pedirInfoPodcast();

                if (info != null) {
                    txtNombre.setText(info[0]);
                    txtTematica.setText(info[1]);
                    txtDescripcion.setText(info[2]);
                    
                    txtNombre.setEnabled(true);
                    txtTematica.setEnabled(true);
                    txtDescripcion.setEnabled(true);
                    btnGuardar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(vModificarPodcast.this, "Podcast no encontrado.");
                    btnGuardar.setEnabled(false);
                }
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                String nombre = txtNombre.getText().trim();
                String tematica = txtTematica.getText().trim();
                String descripcion = txtDescripcion.getText().trim();

                boolean exito = controlador.actualizarPodcastExistente(id, nombre, tematica, descripcion);

                if (exito) {
                    JOptionPane.showMessageDialog(vModificarPodcast.this, "Podcast actualizado.");
                    controlador.abrirGestionPodcasts();
                } else {
                    JOptionPane.showMessageDialog(vModificarPodcast.this, "Error al actualizar.");
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                controlador.abrirGestionPodcasts();
            }
        });
    }
}