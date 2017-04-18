package ej_3;

import java.io.*;

public class Registro {
	private String ap;
	private double cah$$;
	private int account;
	private String name;
	public Registro() throws NumberFormatException, IOException{
		Reader leer = new Reader();
		System.out.print("Nombre: ");
		this.name = leer.readString();
		System.out.print("Apellido Paterno: ");
		this.ap = leer.readString();
		System.out.print("N° Cuenta: ");
		this.account = leer.readInt();
		System.out.print("Saldo: ");
		this.cah$$ = leer.readDouble();
		System.out.println("Registro ingresado exitosamente!!");
	}
	
	public void mostrar(){
		System.out.println("Nombre: " + this.name);
		System.out.println("Apellido Paterno: " + this.ap);
		System.out.println("Numero Cuenta: " + this.account);
		System.out.println("Saldo: " + this.cah$$);
	}
}
