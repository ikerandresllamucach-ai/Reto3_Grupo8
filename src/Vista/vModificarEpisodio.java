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

public class vModificarEpisodio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtTitulo;
    private JTextField txtDuracion;
    private JButton btnGuardar;

    public vModificarEpisodio(GestorControlador controlador) {
        setTitle("Administración - Modificar Episodio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 420);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTituloVentana = new JLabel("MODIFICAR EPISODIO");
        lblTituloVentana.setFont(new Font("Tahoma", Font.BOLD, 20));
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

        // CAMPOS EDITABLES
        JLabel lblTituloEp = new JLabel("Título:");
        lblTituloEp.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTituloEp.setBounds(40, 150, 120, 25);
        contentPane.add(lblTituloEp);

        txtTitulo = new JTextField();
        txtTitulo.setEnabled(false);
        txtTitulo.setBounds(180, 150, 260, 25);
        contentPane.add(txtTitulo);

        // Duración
        JLabel lblDuracion = new JLabel("Duración (seg):");
        lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDuracion.setBounds(40, 210, 130, 25);
        contentPane.add(lblDuracion);

        txtDuracion = new JTextField();
        txtDuracion.setEnabled(false);
        txtDuracion.setBounds(180, 210, 260, 25);
        contentPane.add(txtDuracion);

        // BOTONES DE ACCIÓN
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

        // MANEJO DE EVENTOS
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(vModificarEpisodio.this, "Por favor, introduce un ID.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                controlador.setIdEpisodioActual(id);
                String[] info = controlador.pedirInfoEpisodio(); 

                if (info != null) {
                    txtTitulo.setText(info[0]);
                    txtDuracion.setText(info[1]);
                    
                    txtTitulo.setEnabled(true);
                    txtDuracion.setEnabled(true);
                    btnGuardar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(vModificarEpisodio.this, "Episodio no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtTitulo.setText("");
                    txtDuracion.setText("");
                    txtTitulo.setEnabled(false);
                    txtDuracion.setEnabled(false);
                    btnGuardar.setEnabled(false);
                }
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                String titulo = txtTitulo.getText().trim();
                String duracionStr = txtDuracion.getText().trim();

                if (titulo.isEmpty() || duracionStr.isEmpty()) {
                    JOptionPane.showMessageDialog(vModificarEpisodio.this, "Los campos no pueden quedar vacíos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int duracion = Integer.parseInt(duracionStr);
                    boolean exito = controlador.actualizarEpisodioExistente(id, titulo, duracion);

                    if (exito) {
                        JOptionPane.showMessageDialog(vModificarEpisodio.this, "¡Episodio actualizado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        controlador.abrirGestionPodcasts();
                    } else {
                        JOptionPane.showMessageDialog(vModificarEpisodio.this, "Error al actualizar el episodio.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vModificarEpisodio.this, "La duración debe ser un número entero válido (segundos).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
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