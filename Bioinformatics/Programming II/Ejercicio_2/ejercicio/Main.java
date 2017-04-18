package ejercicio;

public class Main {
	public static void main(String[] args) {
		Archivo entrada = new Archivo("cancion.txt");
		Archivo salida = new Archivo("salida1.txt");
		String palabras = entrada.leer();
		int numero = 0;
		char[] array = palabras.toCharArray();
		for(int i=0; i< array.length; i++){
			if((Character.isSpace(array[i]) == true)&&(Character.isSpace(array[i-1]) == false));numero++;
		}
		System.out.println("La contidad de palabras es: " + numero);
	}

}
