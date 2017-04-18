package ej_2;

import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Ingrese n: ");
		int n = Integer.parseInt(leer.readLine());
		Pascal triangulow = new Pascal(n);
		triangulow.llenarTriangulo();
	}

}
