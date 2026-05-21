package Vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controlador.GestorControlador;
import Modelo.GestorModelo;
import Modelo.Usuario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Registro extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JPanel ContentPane;
	private JButton btnVolver;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtUsuario;
	private JTextField txtContraseña;
	private JTextField txtConfirmar;
	private JButton btnGuardar;
	private JTextField selectIdioma;
	private JComboBox<String> idioma;
	private JTextField fechaNaci;
	private JTextField fecRegistro;
	private JTextField cadPremium;

	private GestorControlador controlador;
	private boolean esPerfil;


	public Registro(GestorControlador controlador, boolean esPerfil) {
		this.controlador = controlador;
		this.esPerfil = esPerfil;
		construirUI();
		if (esPerfil) {
			cargarDatos();
		}
	}

	private void construirUI() {
		getContentPane().setLayout(null);
		setBounds(700, 500, 700, 500);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(19, 23, 89, 23);
		btnVolver.addActionListener(this);
		getContentPane().add(btnVolver);

		JLabel lNombre = new JLabel("Nombre:");
		lNombre.setBounds(115, 63, 153, 14);
		getContentPane().add(lNombre);

		JLabel lApellidos = new JLabel("Apellidos:");
		lApellidos.setBounds(394, 66, 75, 14);
		getContentPane().add(lApellidos);

		JLabel lUsuario = new JLabel("Usuario:");
		lUsuario.setBounds(115, 97, 153, 14);
		getContentPane().add(lUsuario);

		JLabel lContraseña = new JLabel("Contraseña:");
		lContraseña.setBounds(115, 132, 153, 14);
		getContentPane().add(lContraseña);

		JLabel lConfirmar = new JLabel("Repetir Contraseña:");
		lConfirmar.setBounds(115, 166, 153, 14);
		getContentPane().add(lConfirmar);

		JLabel lFechaNaci = new JLabel("Fecha Nacimiento:");
		lFechaNaci.setBounds(115, 237, 153, 14);
		getContentPane().add(lFechaNaci);

		JLabel lFechaReg = new JLabel("Fecha Registro:");
		lFechaReg.setBounds(115, 202, 153, 14);
		getContentPane().add(lFechaReg);

		JLabel lCaducidad = new JLabel("Caducidad Premium:");
		lCaducidad.setBounds(115, 272, 153, 14);
		getContentPane().add(lCaducidad);

		JLabel lIdioma = new JLabel("Idioma:");
		lIdioma.setBounds(116, 307, 152, 14);
		getContentPane().add(lIdioma);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(182, 373, 117, 29);
		btnGuardar.addActionListener(this);
		getContentPane().add(btnGuardar);

		JButton comprarPremium = new JButton("Comprar Premium");
		comprarPremium.setBounds(381, 373, 139, 29);
		getContentPane().add(comprarPremium);

		txtNombre = new JTextField();
		txtNombre.setBounds(212, 63, 153, 20);
		txtNombre.setColumns(10);
		getContentPane().add(txtNombre);

		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(463, 63, 139, 20);
		getContentPane().add(txtApellidos);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(213, 97, 152, 20);
		getContentPane().add(txtUsuario);

		txtContraseña = new JTextField();
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(212, 132, 153, 20);
		getContentPane().add(txtContraseña);

		txtConfirmar = new JTextField();
		txtConfirmar.setColumns(10);
		txtConfirmar.setBounds(239, 166, 126, 20);
		getContentPane().add(txtConfirmar);

		fechaNaci = new JTextField("DD/MM/AAAA");
		fechaNaci.setForeground(Color.GRAY);
		fechaNaci.setColumns(10);
		fechaNaci.setBounds(217, 202, 148, 20);
		getContentPane().add(fechaNaci);

		fecRegistro = new JTextField("DD/MM/AAAA");
		fecRegistro.setForeground(Color.GRAY);
		fecRegistro.setColumns(10);
		fecRegistro.setBounds(228, 237, 137, 20);
		getContentPane().add(fecRegistro);

		cadPremium = new JTextField("DD/MM/AAAA");
		cadPremium.setForeground(Color.GRAY);
		cadPremium.setColumns(10);
		cadPremium.setBounds(246, 272, 119, 20);
		getContentPane().add(cadPremium);

		selectIdioma = new JTextField();
		idioma = new JComboBox<String>();
		idioma.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		idioma.setForeground(new Color(21, 21, 21));
		idioma.setBackground(new Color(255, 255, 255));
		idioma.addItem("Español");
		idioma.addItem("Euskera");
		idioma.addItem("Inglés");
		idioma.setBounds(182, 307, 167, 21);
		idioma.setSelectedIndex(0);
		idioma.addActionListener(this);
		getContentPane().add(idioma);
	}

	private void cargarDatos() {
		Usuario u = controlador.pedirDatosPerfil();
		if (u != null) {
			txtNombre.setText(u.getNombre());
			txtApellidos.setText(u.getApellidos());
			txtUsuario.setText(u.getNombre_usuario());
			txtContraseña.setText(u.getContraseña());
			txtConfirmar.setText(u.getContraseña());
			fecRegistro.setText(u.getFecha_Registro());
			fechaNaci.setText(u.getFecha_Nacimiento());
		
			fechaNaci.setEditable(false);
			fecRegistro.setEditable(false);
			cadPremium.setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == idioma) {
			String seleccion = (String) idioma.getSelectedItem();
			selectIdioma.setText(seleccion);
		}

		if (e.getSource() == btnVolver) {
			if (esPerfil) {
				controlador.abrirMenuPrincipal();
			} else {
				controlador.iniciarLogin();
			}
		}

		if (e.getSource() == btnGuardar) {
			String nombre    = txtNombre.getText();
			String apellidos = txtApellidos.getText();
			String usuario   = txtUsuario.getText();
			String pass      = txtContraseña.getText();
			String confirmar = txtConfirmar.getText();

			if (!pass.equals(confirmar)) {
				javax.swing.JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
				return;
			}

			String idio = "ES";
			if (idioma.getSelectedItem().equals("Inglés"))  idio = "EN";
			if (idioma.getSelectedItem().equals("Euskera")) idio = "EU";

			if (esPerfil) {
				
				boolean exito = controlador.actualizarUsuario(controlador.getUsuarioActual(),nombre, apellidos, usuario, pass);
				if (exito) {
					javax.swing.JOptionPane.showMessageDialog(this, "Perfil actualizado correctamente");
					controlador.abrirMenuPrincipal();
				} else {
					javax.swing.JOptionPane.showMessageDialog(this, "Error al actualizar");
				}
			} else {
		
				String naci      = fechaNaci.getText().trim();
				String registro  = fecRegistro.getText().trim();
				String caducidad = cadPremium.getText().trim();
				String naciMySQL = convertirFecha(naci);
				String regMySQL  = convertirFecha(registro);
				String cadMySQL  = caducidad.equals("DD/MM/AAAA") ? null : convertirFecha(caducidad);
				String tipoUsuario = (caducidad.isEmpty() || caducidad.equals("DD/MM/AAAA"))
						? "Usuario Free" : "Usuario Premium";

				GestorModelo.RegistrarUsuario regDB = new GestorModelo.RegistrarUsuario();
				boolean exito = regDB.registrarUsuario(nombre, apellidos, usuario, pass,
						naciMySQL, regMySQL, tipoUsuario, idio, cadMySQL);
				if (exito) {
					javax.swing.JOptionPane.showMessageDialog(this, "Registro completado como " + tipoUsuario);
					controlador.iniciarLogin();
				} else {
					javax.swing.JOptionPane.showMessageDialog(this, "Error al registrar. Revisa los datos.");
				}
			}
		}
	}

	public String convertirFecha(String fechaUI) {
		String[] partes = fechaUI.split("/");
		if (partes.length == 3) {
			return partes[2].trim() + "-" + partes[1].trim() + "-" + partes[0].trim();
		}
		return fechaUI;
	}
}