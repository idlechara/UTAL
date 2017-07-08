package Laboratorio5;

import java.util.List;

public class Banda extends Musica {
	String nombre;
	List<String> integrantes;
	
	public Banda(MusicalGenre genero, int startYear,
			String nombre) {
		super(genero, startYear);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIntegrantes() {
		String output = "";
		for (String s : this.integrantes) {
			output.concat(s + " ");
		}
		return output;
	}
	
	public void addIntegrante(String integrante){
		this.integrantes.add(integrante);
	}
	
	
}
