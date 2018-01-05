package cl.starttoken.ModuloCheques.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBC {

	public static Connection coneccion() {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:cl:starttoken:ModuloCheques:db.db");
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
