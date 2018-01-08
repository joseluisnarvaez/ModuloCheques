package cl.starttoken.ModuloCheques.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cl.starttoken.ModuloCheques.principalJFm.WPrincipal;
import cl.starttoken.ModuloCheques.sqlite.daos.UsuarioDao;
import cl.starttoken.ModuloCheques.to.Usuario;

public class Login extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1263930061903610841L;
	private JLabel textoUsuario;
	private JTextField cajaUsuario;
	private JLabel textoPw;
	private JPasswordField cajaPw;
	private JButton boton;

	@Override
	public void actionPerformed(ActionEvent e) {
		validaUsuario();
	}

	public Login() {
		super(); // usamos el contructor de la clase padre JFrame
		configurarVentana(); // configuramos la ventana
		inicializarComponentes(); // inicializamos los atributos o componentes
	}

	private void configurarVentana() {
		this.setTitle("Login");
		this.setSize(310, 210);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void inicializarComponentes() {
		// creamos los componentes
		boton = new JButton();
		textoUsuario = new JLabel();
		textoPw = new JLabel();
		cajaUsuario = new JTextField();
		cajaPw = new JPasswordField();

		textoUsuario.setText("Usuario");
		textoUsuario.setBounds(50, 50, 100, 25);
		cajaUsuario.setText("user1");
		cajaUsuario.setEnabled(false);
		cajaUsuario.setBounds(150, 50, 100, 25); // colocamos posicion y tamanio
													// a la

		textoPw.setText("Contraseña");
		textoPw.setBounds(50, 75, 100, 25);
		// texto (x, y, ancho, alto)
		cajaPw.setBounds(150, 75, 100, 25); // colocamos posicion y tamanio a la
											// caja (x, y, ancho, alto)
		cajaPw.addKeyListener(this);
		boton.setText("Ingresar"); // colocamos un texto al boton
		boton.setBounds(50, 100, 200, 30); // colocamos posicion y tamanio al
											// boton (x, y, ancho, alto)
		boton.addActionListener(this); // hacemos que el boton tenga una accion
		// adicionamos los componentes a la ventana
		this.add(textoUsuario);
		this.add(cajaUsuario);
		this.add(textoPw);
		this.add(cajaPw);
		this.add(boton);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)  {
			validaUsuario();
		}

	}
	
	private void validaUsuario(){
		String contraseña = String.valueOf(cajaPw.getPassword());
		if(contraseña == null){
			JOptionPane.showMessageDialog(this, "Favor de escribir la contraseña.", "Contraseña", JOptionPane.WARNING_MESSAGE);
		}
		
		String usuario = cajaUsuario.getText();
		boolean encontrado = false;
		Usuario user = UsuarioDao.obtenerUsuario(usuario);

		if (user.getContraseña().equals(contraseña) && user.getUsuario().equals(usuario)) {
			encontrado = true;
		}

		if (!encontrado) {
			JOptionPane.showMessageDialog(this, "Contraseña Incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			WPrincipal ventana = new WPrincipal();
			ventana.setVisible(true);
			this.dispose();
		}
	}

}
