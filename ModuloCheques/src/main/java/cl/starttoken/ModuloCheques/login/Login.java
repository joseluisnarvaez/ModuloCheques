package cl.starttoken.ModuloCheques.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import cl.starttoken.ModuloCheques.principalJFm.WPrincipal;
import cl.starttoken.ModuloCheques.to.Usuario;
import cl.starttoken.ModuloCheques.utils.LecturaArchivos;

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1263930061903610841L;
	private JLabel textoPw; // etiqueta o texto no editable
	private JPasswordField cajaPw; // caja de texto, para insertar datos
	private JButton boton;

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Usuario> usuarios = null;
		try {
			usuarios = LecturaArchivos.getUsuarios();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "No se a podido validar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String contraseña = String.valueOf(cajaPw.getPassword());
		boolean encontrado = false;
		for (Usuario user : usuarios) {
			if (user.getContraseña().equals(contraseña)) {
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			JOptionPane.showMessageDialog(this, "Contraseña Incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			WPrincipal ventana = new WPrincipal();
			ventana.setVisible(true);
			this.dispose();
		}

	}

	public Login() {
		super(); // usamos el contructor de la clase padre JFrame
		configurarVentana(); // configuramos la ventana
		inicializarComponentes(); // inicializamos los atributos o componentes
	}

	private void configurarVentana() {
		this.setTitle("Login"); // colocamos titulo a la ventana
		this.setSize(310, 210); // colocamos tamanio a la ventana (ancho, alto)
		this.setLocationRelativeTo(null); // centramos la ventana en la pantalla
		this.setLayout(null); // no usamos ningun layout, solo asi podremos dar
								// posiciones a los componentes
		this.setResizable(false); // hacemos que la ventana no sea
									// redimiensionable
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // hacemos que
																// cuando se
																// cierre la
																// ventana
																// termina todo
																// proceso
	}

	private void inicializarComponentes() {
		// creamos los componentes
		boton = new JButton();

		textoPw = new JLabel();
		cajaPw = new JPasswordField();

		// configuramos los componentes
		textoPw.setText("Contraseña"); // colocamos un texto a la etiqueta
		textoPw.setBounds(50, 50, 100, 25); // colocamos posicion y tamanio al
											// texto (x, y, ancho, alto)
		cajaPw.setBounds(150, 50, 100, 25); // colocamos posicion y tamanio a la
											// caja (x, y, ancho, alto)

		boton.setText("Ingresar"); // colocamos un texto al boton
		boton.setBounds(50, 100, 200, 30); // colocamos posicion y tamanio al
											// boton (x, y, ancho, alto)
		boton.addActionListener(this); // hacemos que el boton tenga una accion
										// y esa accion estara en esta clase
		// adicionamos los componentes a la ventana
		this.add(textoPw);
		this.add(cajaPw);
		this.add(boton);
	}

}
