package cl.starttoken.ModuloCheques.sqlite.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cl.starttoken.ModuloCheques.sqlite.SQLiteJDBC;
import cl.starttoken.ModuloCheques.to.Usuario;

public class UsuarioDao {

	public static void insertUsuario(Usuario user) {
		try (Connection conexion = SQLiteJDBC.coneccion();
				PreparedStatement st = conexion
						.prepareStatement("insert into usuarios (usuario, contraseña,email) values (?,?,?)");) {

			st.setString(1, user.getUsuario());
			st.setString(2, user.getContraseña());
			st.setString(3, user.getCorreo());
			st.execute();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		System.out.println("insert successfully");
	}

	public static Usuario obtenerUsuario(String filtro) {
		ResultSet rs = null;
		Usuario usuario = null;
		try (Connection conexion = SQLiteJDBC.coneccion();
			 PreparedStatement st = conexion.prepareStatement("select * from usuarios where usuario = ?");) {
			st.setString(1, filtro);
			rs = st.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();

				usuario.setId(rs.getInt(1));
				System.out.print("ID: ");
				System.out.println(usuario.getId());

				usuario.setUsuario(rs.getString(2));
				System.out.print("Usuario: ");
				System.out.println(usuario.getUsuario());

				usuario.setContraseña(rs.getString(3));
				usuario.setCorreo(rs.getString(4));

				System.out.println("=======================");
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return usuario;
	}

}
