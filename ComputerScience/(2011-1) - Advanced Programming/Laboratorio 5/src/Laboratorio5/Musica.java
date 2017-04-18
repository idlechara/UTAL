package Laboratorio5;

import java.util.ArrayList;
import java.util.List;

public class Musica {
	MusicalGenre genero;
	ArrayList<String> albumList;
	int startYear;
	
	public Musica(MusicalGenre genero,int startYear) {
		super();
		this.genero = genero;
		this.albumList = new ArrayList<String>();
		this.startYear = startYear;
	}

	public void addAlbum(String album){
		this.albumList.add(album);
	}
	
	public MusicalGenre getGenero() {
		return genero;
	}

	public String getAlbumList() {
		String output = "";
		for (String s : this.albumList) {
			output.concat(s + ", ");
		}
		return output;
	}

	public int getStartYear() {
		return startYear;
	}
}
