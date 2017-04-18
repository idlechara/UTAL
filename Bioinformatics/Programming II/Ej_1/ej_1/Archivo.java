package ej_1;
import java.io.*;

public class Archivo {
	BufferedReader lectorarchivo;
	Reader leer, file_r;
	public Archivo(int i){
		try {
			leer = new Reader();
			System.out.println( "Ingrese direccion del archivo "+ i+": ");
			file_r = new Reader(leer.readString());
		} 
		catch (FileNotFoundException e) {
			System.err.println("Tu archivo no existe");
			System.exit(1);
		} 
		catch (IOException e) {
			System.err.println("Nu puedo abrur el archivo ;___;");
			System.exit(1);
		}
	}
	public String sweep() throws IOException{
		StringBuffer resultado = new StringBuffer();
		char temp = (char)this.file_r.readChar();
		while(temp != (char)-1){
			resultado.append(temp);
			temp = (char)this.file_r.readChar();
		}
		System.out.println(resultado);
		return resultado.toString();
	}
}
