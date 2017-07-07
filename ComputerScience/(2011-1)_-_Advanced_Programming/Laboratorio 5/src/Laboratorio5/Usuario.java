package Laboratorio5;

public class Usuario {
	String nombre;
	String apellidoMaterno;
	String apellidoPaterno;
	String eMail;
	String userName;
	String diaDeNacimiento;
	Object type;
	
	
	public Usuario(String nombre, String apellidoMaterno,
			String apellidoPaterno, String eMail, String userName,
			String diaDeNacimiento, Object type) {
		super();
		this.nombre = nombre;
		this.apellidoMaterno = apellidoMaterno;
		this.apellidoPaterno = apellidoPaterno;
		this.eMail = eMail;
		this.userName = userName;
		this.diaDeNacimiento = diaDeNacimiento;
		this.type = type;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public String geteMail() {
		return eMail;
	}
	public String getUserName() {
		return userName;
	}
	public String getDiaDeNacimiento() {
		return diaDeNacimiento;
	}
	
	public Object getType() {
		return type;
	}

	
	
}
