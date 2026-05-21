package Vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controlador.GestorControlador;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Login extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L; 
	
	
	private JPanel ContentPane;
	private JTextField txtUsuario;
	private JTextField txtContraseña;
	private JTextField txtSeleccion;
	private JComboBox <String> tipoUsuario;
	private JButton btnLogin;
	private JButton btnRegistrar;
	
	private GestorControlador controlador;
	
	public Login(GestorControlador controlador) {
		this.controlador = controlador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 400);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		
		
		JLabel usuario = new JLabel("Usuario");
		usuario.setFont(new Font("Arial", Font.BOLD, 11));
		usuario.setBounds(98, 84, 80, 21);
		getContentPane().add(usuario);
		
		
		JLabel contraseña = new JLabel("Contraseña");
		contraseña.setFont(new Font("Arial", Font.BOLD, 11));
		contraseña.setBounds(98, 137, 80, 21);
		ContentPane.add(contraseña);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(211,84,167,20);
		ContentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContraseña = new JTextField();
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(211, 137, 167, 20);
		ContentPane.add(txtContraseña);
		
		txtSeleccion = new JTextField(); 
		tipoUsuario = new JComboBox<String>();
		tipoUsuario.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		tipoUsuario.setForeground(new Color(21, 21, 21));
		tipoUsuario.setBackground(new Color(255, 255, 255));
		tipoUsuario.setSize(167, 21);
		tipoUsuario.setLocation(211, 180);
		tipoUsuario.addItem("Cliente");
		tipoUsuario.addItem("Administrador");
		tipoUsuario.setSelectedIndex(0);
		tipoUsuario.addActionListener(this);
		ContentPane.add(tipoUsuario);
		tipoUsuario.setVisible(true);
		
		
		btnLogin = new JButton("Iniciar Sesion");
		btnLogin.setFont(new Font("Lucida Sans", Font.BOLD, 11));
		btnLogin.setBackground(new Color(214, 232, 245));
		btnLogin.setBounds(98, 252, 124, 31);
		btnLogin.addActionListener(this);
		ContentPane.add(btnLogin);
		
		btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setFont(new Font("Lucida Sans", Font.BOLD, 11));
		btnRegistrar.setBackground(new Color(214, 232, 245));
		btnRegistrar.setBounds(266, 252, 112, 31);
		btnRegistrar.addActionListener(this);
		ContentPane.add(btnRegistrar);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == tipoUsuario) {
	        String seleccion = (String) this.tipoUsuario.getSelectedItem();
	        this.txtSeleccion.setText(seleccion);
	    }

	    if (e.getSource() == btnLogin) {
	        String user = txtUsuario.getText().trim();
	        String pass = txtContraseña.getText().trim();
	             
	        String seleccionCombo = (String) tipoUsuario.getSelectedItem();

	        if (user.isEmpty() || pass.isEmpty()) {
	            javax.swing.JOptionPane.showMessageDialog(this, "Rellena todos los campos");
	            return;
	        }
  
	        controlador.procesarLogin(user, pass, seleccionCombo);
	    }
	    
	    if (e.getSource() == btnRegistrar) {  
	        controlador.abrirRegistro();
	    }
	}
	}