package ej;

import java.io.*;
import java.util.*;

public class Main {	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
	BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
	Registro r1 = null , temp = null;
	ArrayList array = new ArrayList();
	int opcion = 0;
	
	while(true){
		System.out.println("Menu");
		System.out.println("1.- Agregar Registro");
		System.out.println("2.- Ver Registros");
		System.out.println("Ingrese Opcion: ");
		opcion = Integer.parseInt(leer.readLine());
		if(opcion == 1){
			r1 = new Registro();
			array.add(r1);
		}
		if(opcion == 2){
			for(int i=0; i<array.size(); i++){
				temp = (Registro) array.get(i);
				temp.mostrar();
			}
		}
		if((opcion !=2 ) && (opcion != 1)){
			System.out.println(" Ingrese una opcion valida...");
		}
	}
	}
}
