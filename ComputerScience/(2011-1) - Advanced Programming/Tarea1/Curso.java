
public class Curso {

	private String nombre;
	private boolean asignado;
	
	public boolean isAsignado() {
		return asignado;
	}

	public void setAsignado(boolean asignado) {
		this.asignado = asignado;
	}

	public Curso() {
		System.out.println("\tIngrese nombre del curso");
		this.nombre = Main.leerCadena();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
