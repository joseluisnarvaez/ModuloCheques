package cl.starttoken.ModuloCheques.to;

public class Usuario {
	
	private int id;
	private String correo;
	private String usuario;
	private String contraseña;

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [correo=");
		builder.append(correo);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", contraseña=");
		builder.append(contraseña);
		builder.append("]");
		return builder.toString();
	}
	


}
