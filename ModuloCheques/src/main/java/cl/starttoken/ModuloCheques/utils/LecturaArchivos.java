package cl.starttoken.ModuloCheques.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;

import cl.starttoken.ModuloCheques.to.Usuario;

public class LecturaArchivos {

	private static ArrayList<Usuario> listaUsuarios;

	public static void lecturaArchivo(String archivo, String llamada) throws IOException {
		String cadena;

		URL in = LecturaArchivos.class.getClass().getResource(archivo);
		FileReader f = new FileReader(in.getPath());
		BufferedReader b = new BufferedReader(f);
		if ("usuario".equals(llamada)) {
			listaUsuarios = new ArrayList<>();
		}
		while ((cadena = b.readLine()) != null) {
			switch (llamada) {
			case "usuario":
				listaUsuarios.add(parceoUsuario(cadena));
				break;
			default:
				break;
			}
		}
		b.close();
	}

	public static ArrayList<Usuario> getUsuarios() throws Exception {
		try {
			lecturaArchivo("/cl/starttoken/ModuloCheques/db/properties.txt", "usuario");
		} catch (IOException e) {
			throw new Exception(e);
		}
		return listaUsuarios;
	}

	private static Usuario parceoUsuario(String linea) {
		String data = decifrarTexto(linea);
		String[] datos = data.split(";");
		Usuario user = new Usuario();
		user.setUsuario(datos[0]);
		user.setContraseña(datos[1]);
		user.setCorreo(datos[2]);
		return user;
	}

	private static String decifrarTexto(String linea) {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedByteArray = decoder.decode(linea);
		return new String(decodedByteArray);
	}

}
