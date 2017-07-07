package Laboratorio5;

import java.util.List;

public class Solista extends Musica {

	String nombreArtistico;
	String nombreReal;
	String fechaNacimiento;

	public Solista(MusicalGenre genero, int startYear,
			String nombreArtistico, String nombreReal, String fechaNacimiento) {
		super(genero, startYear);
		this.nombreArtistico = nombreArtistico;
		this.nombreReal = nombreReal;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombreArtistico() {
		return nombreArtistico;
	}

	public String getNombreReal() {
		return nombreReal;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
}
