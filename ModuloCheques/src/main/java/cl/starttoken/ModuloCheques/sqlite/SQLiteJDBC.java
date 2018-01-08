package cl.starttoken.ModuloCheques.sqlite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class SQLiteJDBC {

	public static Connection coneccion() {
		Connection c = null;
		String nombreArchivo= System.getProperty("user.dir")+"\\ModuloChequesDb\\db.db";
		File baseDatos = new File(nombreArchivo);
		boolean existeDb= true;
		if(!baseDatos.exists()) {
			try {
				baseDatos.createNewFile();
				existeDb = false;
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error al crear la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+baseDatos.getAbsolutePath());
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		
		if(!existeDb){
			crearTablas(c);
		}
		
		return c;
	}

	public static void crearTablas(Connection c) {
		Statement stmt = null;

		try {

			stmt = c.createStatement();
			String sql = "CREATE TABLE usuarios " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + " usuario           TEXT    NOT NULL, " + " contraseña        text    NOT NULL, " + " email				text 	null	)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	

	public static boolean closedConeccion(Connection c) {

		try {
			c.close();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}
}
