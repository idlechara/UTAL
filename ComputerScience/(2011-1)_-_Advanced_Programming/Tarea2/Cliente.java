import java.io.Serializable;


@SuppressWarnings("serial")
public class Cliente implements Serializable{
	private String rut;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String direccion;
	private String ciudad;
	private String email;
	
	public String getRut() {
		return rut;
	}
	public Cliente(String rut, String nombres, String apellidoPaterno,
			String apellidoMaterno, String direccion,
			String ciudad, String email) {
		this.rut = rut;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.email = email;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
