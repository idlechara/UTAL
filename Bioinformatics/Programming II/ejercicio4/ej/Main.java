package ej;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		FileWriter salida = new FileWriter("/home/kmy/gol.txt");
		BufferedWriter escribir = new BufferedWriter(salida);
		escribir.write("chile gana 3-2!!!");
		escribir.flush();
	}
}
