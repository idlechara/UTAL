package ej;

import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Ingrese n, para hacer un triangulo pascal!!: ");
		int parametro = Integer.parseInt(leer.readLine());
		Pascal pas = new Pascal(parametro);
		pas.llenarTriangulo();
	}

}
