package ej_4;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ingrese un ruta para descargar el archivo: ");
		FileWriter salida = new FileWriter(read.readLine());
		BufferedWriter escribir = new BufferedWriter(salida);
		escribir.write("shile gana 4-1, pero no servira " +
				"de nada por que es un amistoso");
		escribir.flush();
	}
}
