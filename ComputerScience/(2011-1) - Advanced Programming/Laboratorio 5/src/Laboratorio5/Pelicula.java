package Laboratorio5;

public class Pelicula {
	String nombre;
	int año;
	MovieGenre genero;
	String director;
	String estudio;
	int presupuesto;
	
	public Pelicula(String nombre, int año, MovieGenre genero, String director,
			String estudio, int presupuesto) {
		super();
		this.nombre = nombre;
		this.año = año;
		this.genero = genero;
		this.director = director;
		this.estudio = estudio;
		this.presupuesto = presupuesto;
	}
	
	public String getNombre() {
		return nombre;
	}
	public int getAño() {
		return año;
	}
	public MovieGenre getGenero() {
		return genero;
	}
	public String getDirector() {
		return director;
	}
	public String getEstudio() {
		return estudio;
	}
	public int getPresupuesto() {
		return presupuesto;
	}
	
	
}
