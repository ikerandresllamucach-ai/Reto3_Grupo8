package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controlador.GestorControlador;

public class vVistaEstadisticasDetalle extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTable tablaEstadisticas;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver, btnAstea, btnHilabetea, btnUrtea;
    private GestorControlador controlador;
    private String tipoEstadistica; 

    public vVistaEstadisticasDetalle(GestorControlador controlador, String tipoEstadistica, String tiempo) {
        this.controlador = controlador;
        this.tipoEstadistica = tipoEstadistica;
        
        setTitle(tipoEstadistica);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 450);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // BOTÓN VOLVER (Atzera)
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(20, 15, 90, 25);
        btnVolver.addActionListener(this);
        contentPane.add(btnVolver);

     
        // CONFIGURACIÓN DE LA TABLA
        String[] columnas = {"Pos", "Cantante / Podcaster", "Cancion / Podcast", "Oyentes"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        tablaEstadisticas = new JTable(modeloTabla);
        tablaEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 12));
        tablaEstadisticas.setRowHeight(22);
        
        tablaEstadisticas.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablaEstadisticas.getColumnModel().getColumn(1).setPreferredWidth(220);
        tablaEstadisticas.getColumnModel().getColumn(2).setPreferredWidth(220);
        tablaEstadisticas.getColumnModel().getColumn(3).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(tablaEstadisticas);
        scrollPane.setBounds(20, 60, 745, 330);
        contentPane.add(scrollPane);

 
        cargarDatosDesdeBD(tiempo);
    }

    private void cargarDatosDesdeBD(String filtro) {
        modeloTabla.setRowCount(0); 
        
        List<String[]> datosReales;

     
        if (tipoEstadistica.contains("Podcast")) {
            datosReales = controlador.obtenerEstadisticasPodcastsReales(filtro);
        } else {
            
            datosReales = controlador.obtenerEstadisticasCancionesReales(filtro);
        }

      
        if (datosReales != null) {
            for (String[] fila : datosReales) {
                modeloTabla.addRow(fila);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();

        if (origen == btnVolver) {
            controlador.abrirEstadisticas();
        }
    }
}