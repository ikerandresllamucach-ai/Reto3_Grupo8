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

public class vEliminarEpisodio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtTitulo;
    private JTextField txtDuracion;
    private JButton btnEliminar;

    public vEliminarEpisodio(GestorControlador controlador) {
        setTitle("Administración - Eliminar Episodio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 420);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTituloVentana = new JLabel("ELIMINAR EPISODIO");
        lblTituloVentana.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTituloVentana.setForeground(new Color(178, 34, 34));
        lblTituloVentana.setBounds(150, 20, 250, 30);
        contentPane.add(lblTituloVentana);

        // SECCIÓN BÚSQUEDA
        JLabel lblIdBuscar = new JLabel("ID del Episodio:");
        lblIdBuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIdBuscar.setBounds(40, 80, 120, 25);
        contentPane.add(lblIdBuscar);

        txtIdBuscar = new JTextField();
        txtIdBuscar.setBounds(170, 80, 140, 25);
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

        // VISTA DE DATOS (SOLO LECTURA)
        JLabel lblTituloEp = new JLabel("Título:");
        lblTituloEp.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTituloEp.setBounds(40, 150, 120, 25);
        contentPane.add(lblTituloEp);

        txtTitulo = new JTextField();
        txtTitulo.setEditable(false);
        txtTitulo.setBounds(180, 150, 260, 25);
        contentPane.add(txtTitulo);

        JLabel lblDuracion = new JLabel("Duración (seg):");
        lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDuracion.setBounds(40, 210, 130, 25);
        contentPane.add(lblDuracion);

        txtDuracion = new JTextField();
        txtDuracion.setEditable(false);
        txtDuracion.setBounds(180, 210, 260, 25);
        contentPane.add(txtDuracion);

        // BOTONES DE ACCIÓN
        btnEliminar = new JButton("Eliminar Episodio");
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

        // MANEJO DE EVENTOS
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(vEliminarEpisodio.this, "Por favor, introduce un ID.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                controlador.setIdEpisodioActual(id);
                String[] info = controlador.pedirInfoEpisodio();

                if (info != null) {
                    txtTitulo.setText(info[0]);
                    txtDuracion.setText(info[1]);
                    btnEliminar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(vEliminarEpisodio.this, "Episodio no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtTitulo.setText("");
                    txtDuracion.setText("");
                    btnEliminar.setEnabled(false);
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();

                int confirmar = JOptionPane.showConfirmDialog(
                    vEliminarEpisodio.this,
                    "¿Estás seguro de que deseas eliminar este episodio?",
                    "Confirmación de Borrado",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (confirmar == JOptionPane.YES_OPTION) {
                    boolean exito = controlador.eliminarEpisodioBD(id);
                    if (exito) {
                        JOptionPane.showMessageDialog(vEliminarEpisodio.this, "Episodio eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        controlador.abrirGestionPodcasts();
                    } else {
                        JOptionPane.showMessageDialog(vEliminarEpisodio.this, "Error al eliminar el episodio de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.abrirGestionPodcasts();
            }
        });
    }
}