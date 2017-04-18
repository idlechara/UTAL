package ej;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		String temporal, temporal_2;
		boolean existe;
		archivos file_a = new archivos(1);
		archivos file_b = new archivos(2);
		char[] arreglo_1, arreglo_2;
		int diferencias;
			temporal = file_a.extraer_string();
			temporal_2 = file_b.extraer_string();
			
			arreglo_1 = temporal.toCharArray();
			arreglo_2 = temporal_2.toCharArray();
			
			diferencias = 0;
			for(int i=0; i < temporal.length(); i++){
				existe = false;
				for(int j=0; j < temporal_2.length(); j++){
					if(arreglo_1[i]==arreglo_2[j]){
						existe = true;
					}
				}
				if(existe == false){
					diferencias++;
				}
			}
			for(int i=0; i < temporal_2.length(); i++){
				existe = false;
				for(int j=0; j < temporal.length(); j++){
					if(arreglo_2[i]==arreglo_1[j]){
						existe = true;
					}
				}
				if(existe == false){
					diferencias++;
				}
			}
			System.out.println("La diferencia es : " + diferencias);
	}

}
