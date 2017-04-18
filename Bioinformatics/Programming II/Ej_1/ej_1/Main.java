package ej_1;

import java.io.*;

public class Main {
	public static void main(String[] args) {
		Archivo file_1 = new Archivo(1);
		Archivo file_2 = new Archivo(2);
		String temp_1, temp_2;
		boolean isthere;
		char[] te_1, te_2;
		int dif1 = 0, dif2 = 0;
		try {
			temp_1 = file_1.sweep();
			temp_2 = file_2.sweep();
			
			te_1 = temp_1.toCharArray();
			te_2 = temp_2.toCharArray();

			for(int i=0; i < temp_1.length(); i++){
				isthere = false;
				for(int j=0; j < temp_2.length(); j++){
					if(te_1[i]==te_2[j]){
						isthere = true;
					}
				}
				if(isthere == false){
					dif1++;
				}
			}
			for(int i=0; i < temp_2.length(); i++){
				isthere = false;
				for(int j=0; j < temp_1.length(); j++){
					if(te_2[i]==te_1[j]){
						isthere = true;
					}
				}
				if(isthere == false){
					dif2++;
				}
			}
			System.out.println("La diferencia de caracteres es : " + (dif2+dif1));
		} catch (IOException e) {
			System.err.println("Error en la lectura.");
		}
	}

}
