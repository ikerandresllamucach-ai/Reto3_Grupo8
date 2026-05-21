package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controlador.GestorControlador;

public class vPodcasters extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTable tablaPodcasters;
    private DefaultTableModel modeloTabla;
    private JButton btnAtras;
    private GestorControlador controlador;

    public vPodcasters(GestorControlador controlador) {
        this.controlador = controlador;
        
        setTitle("Podcasters - Zpotify");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // TÍTULO DE LA VENTANA
        JLabel lblTitulo = new JLabel("Podcasterrak / Saioak");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(150, 20, 280, 30);
        contentPane.add(lblTitulo);

        // BOTÓN ATRÁS (Atzera)
        btnAtras = new JButton("Atzera");
        btnAtras.setFont(new Font("Lucida Sans", Font.BOLD, 11));
        btnAtras.setBackground(new Color(214, 232, 245));
        btnAtras.setBounds(20, 20, 90, 30);
        btnAtras.addActionListener(this);
        contentPane.add(btnAtras);

        // CONFIGURACIÓN DE LA TABLA
        String[] columnas = {"ID", "Podcaster / Programa"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPodcasters = new JTable(modeloTabla);
        tablaPodcasters.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tablaPodcasters.setRowHeight(26);
        
        // Ocultamos la columna ID para que quede estético, pero mantenga el valor
        tablaPodcasters.getColumnModel().getColumn(0).setMinWidth(0);
        tablaPodcasters.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaPodcasters.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(tablaPodcasters);
        scrollPane.setBounds(20, 70, 490, 310);
        contentPane.add(scrollPane);

        // EVENTO DE SELECCIÓN (Doble clic sobre un Podcaster)
        tablaPodcasters.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { 
                    int filaSeleccionada = tablaPodcasters.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        String idPodcaster = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
                        
   
                        controlador.abrirPodcast(idPodcaster); 
                    }
                }
            }
        });

       
        cargarPodcastersBD();
    }

    private void cargarPodcastersBD() {
        modeloTabla.setRowCount(0); 
        
     
        List<String[]> lista = controlador.obtenerPodcastersReales(); 
        
        if (lista != null) {
            for (String[] podcaster : lista) {
                modeloTabla.addRow(podcaster); 
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAtras) {
            controlador.abrirMenuPrincipal(); 
        }
    }
}