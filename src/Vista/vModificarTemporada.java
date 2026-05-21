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

public class vModificarTemporada extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtIdBuscar;
    private JTextField txtNumero;
    private JTextField txtFecha;
    private JButton btnGuardar;

    public vModificarTemporada(GestorControlador controlador) {
        setTitle("Administración - Modificar Temporada");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 420);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("MODIFICAR TEMPORADA");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(140, 20, 260, 30);
        contentPane.add(lblTitulo);

        // SECCIÓN BÚSQUEDA
        JLabel lblIdBuscar = new JLabel("ID de Temporada:");
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
        JLabel lblNumero = new JLabel("Nº Temporada:");
        lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNumero.setBounds(40, 150, 120, 25);
        contentPane.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setEnabled(false);
        txtNumero.setBounds(180, 150, 260, 25);
        contentPane.add(txtNumero);

        JLabel lblFecha = new JLabel("Fecha (AAAA-MM-DD):");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFecha.setBounds(40, 210, 140, 25);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setEnabled(false);
        txtFecha.setBounds(180, 210, 260, 25);
        contentPane.add(txtFecha);

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
                    JOptionPane.showMessageDialog(vModificarTemporada.this, "Por favor, introduce un ID.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                controlador.setIdTemporadaActual(id);
                String[] info = controlador.pedirInfoTemporada(); 

                if (info != null) {
                    txtNumero.setText(info[0]);
                    txtFecha.setText(info[1]);
                    
                    txtNumero.setEnabled(true);
                    txtFecha.setEnabled(true);
                    btnGuardar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(vModificarTemporada.this, "Temporada no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtNumero.setText("");
                    txtFecha.setText("");
                    txtNumero.setEnabled(false);
                    txtFecha.setEnabled(false);
                    btnGuardar.setEnabled(false);
                }
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = txtIdBuscar.getText().trim();
                String numero = txtNumero.getText().trim();
                String fecha = txtFecha.getText().trim();

                if (numero.isEmpty() || fecha.isEmpty()) {
                    JOptionPane.showMessageDialog(vModificarTemporada.this, "Los campos no pueden quedar vacíos.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean exito = controlador.actualizarTemporadaExistente(id, numero, fecha);

                if (exito) {
                    JOptionPane.showMessageDialog(vModificarTemporada.this, "¡Temporada actualizada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    controlador.abrirGestionPodcasts();
                } else {
                    JOptionPane.showMessageDialog(vModificarTemporada.this, "Error al actualizar la temporada.", "Error", JOptionPane.ERROR_MESSAGE);
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