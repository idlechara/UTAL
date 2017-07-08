package Laboratorio5;

import java.util.ArrayList;

public class Juego {
	String nombre;
	int añoCreacion;
	Genre genero;
	String empresaDesarrolladora;
	String empresaDistribuidora;
	GameMode modoDeJuego;
	ArrayList<Platform> plataformasSoportadas;
	
	public Juego(String nombre, int añoCreacion, Genre genero,
			String empresaDesarrolladora, String empresaDistribuidora,
			GameMode modoDeJuego) {
		super();
		this.nombre = nombre;
		this.añoCreacion = añoCreacion;
		this.genero = genero;
		this.empresaDesarrolladora = empresaDesarrolladora;
		this.empresaDistribuidora = empresaDistribuidora;
		this.modoDeJuego = modoDeJuego;
	}

	public String getPlataformasSoportadas() {
		String output = "";
		for (Platform p: this.plataformasSoportadas) {
			output.concat(p.toString() + " ");
		}
		return output;
	}

	public void addPlataformaSoportada(Platform plataformaSoportada) {
		this.plataformasSoportadas.add(plataformaSoportada);
	}

	public String getNombre() {
		return nombre;
	}

	public int getAñoCreacion() {
		return añoCreacion;
	}

	public Genre getGenero() {
		return genero;
	}

	public String getEmpresaDesarrolladora() {
		return empresaDesarrolladora;
	}

	public String getEmpresaDistribuidora() {
		return empresaDistribuidora;
	}

	public GameMode getModoDeJuego() {
		return modoDeJuego;
	}
	
	
}
