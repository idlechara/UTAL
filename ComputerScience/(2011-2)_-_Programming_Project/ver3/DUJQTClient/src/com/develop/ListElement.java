package com.develop;

/**
 * Clase contenedora para poder describir elementos de una lista usando 
 * una cadena de titulo y otra de descipcion.
 * 
 * @author Kinya
 *
 */
public class ListElement {
	/**
	 * Cadenas de titulo y subtitulo de la publicacion.
	 */
	private String title, subtitle;

	/**
	 * Construye un elemento para el anuncio.
	 * 
	 * @param title titulo del anuncio
	 * @param subtitle subtitulo del anuncio
	 */
	public ListElement(String title, String subtitle) {
		super();
		this.title = title;
		this.subtitle = subtitle;
	}

	/**
	 * retorna el titulo almacenado
	 * @return el titulo de la publicacion
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * retorna el subtitulo almacenado
	 * @return el subtitulo de la publicacion
	 */
	public String getSubtitle() {
		return subtitle;
	}
}
