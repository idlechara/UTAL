package ej_3;

import java.io.*;
import java.util.*;

public class Main {	
	public static void main(String[] args) throws NumberFormatException, IOException{
	Reader leer = new Reader();
	Registro temporal = null;
	ArrayList storage = new ArrayList();
	int opcion = 0;
	
	while(true){
		System.out.println("\tMenu");
		System.out.println("\t\t1.- Agregar Registro Nuevo");
		System.out.println("\t\t2.- Ver Todos Los Registros");
		System.out.println("\tIngrese Opcion: ");
		opcion = leer.readInt();
		if(opcion == 1){
			temporal = new Registro();
			storage.add(temporal);
		}
		if(opcion == 2){
			for(int i=0; i<storage.size(); i++){
				temporal = (Registro) storage.get(i);
				temporal.mostrar();
			}
		}
		if((opcion >2 ) || (opcion < 1)){
			System.out.println(" Ingrese una opcion valida...");
		}
	}
	}
}
