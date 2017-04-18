package ej;

import java.io.*;

public class Registro {
	private int numero_cuenta;
	private String Nombre;
	private String Apellido_pat;
	private double Saldo;
	
	public Registro() throws NumberFormatException, IOException{
		BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Ingrese Nombre: ");
		this.Nombre = leer.readLine();
		System.out.print("Ingrese Apellido Paterno: ");
		this.Apellido_pat = leer.readLine();
		System.out.print("Ingrese N° Cuenta: ");
		this.numero_cuenta = Integer.parseInt(leer.readLine());
		System.out.print("Ingrese Saldo: ");
		this.Saldo = Double.parseDouble(leer.readLine());
	}
	
	public void mostrar(){
		System.out.println("Nombre: " + this.Nombre);
		System.out.println("Apellido Paterno: " + this.Apellido_pat);
		System.out.println("Numero Cuenta: " + this.numero_cuenta);
		System.out.println("Saldo: " + this.Saldo);
	}
}
