package ejercicio;

import java.awt.List;
import java.io.*;

public class Archivo {
	List lista = new List();
	
	public BufferedReader br;
	public BufferedWriter bw;
	
	public Archivo(String ruta){
		try{
			this.bw = new BufferedWriter(new FileWriter(ruta));
			this.br = new BufferedReader(new FileReader(ruta));
		}
		catch(IOException e){
			System.out.println("No se ha podido abrir/crear el archivo");
		}
	}
	
	public String leer(){
		StringBuffer buffer = new StringBuffer();
		char character = 0;
		try {
			character = (char)this.br.read();
		} catch (IOException e1) {
			System.out.println("No se puede leer el archivo");
		}
		while(character != -1){
			buffer.append(character);
			try {
				character = (char)this.br.read();
			} catch (IOException e) {
				System.out.println("No se puede leer el archivo");
			}
		}
		return (buffer.toString());
	}
	
	public void escribir(String cadena){
		try {
			bw.write(cadena);
			bw.flush();
		} catch (IOException e) {
			System.out.println("No se puede escribir el archivo");
		}
	}
}
