package cl.starttoken.ModuloCheques.sqlite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBC {

	public static Connection coneccion() {
		Connection c = null;
		String nombreArchivo= System.getProperty("user.dir")+"\\ModuloChequesDb\\db.db";
		File baseDatos = new File(nombreArchivo);
		if(!baseDatos.exists()) {
			baseDatos.mkdir();	
		}
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+baseDatos.getAbsolutePath());
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
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
