package multithread;

public class Main {
	public static void main(String[] args) {
		/*
		 * El metodo de abajo es para probar otra cosa... xd
		 */
		//new NewThread();
		
		/*
		 * Lo que nos interesa es esto... 
		 * Primero creo un objeto, que es el que comparten en comun
		 * tanto el productor como el consumidor
		 * 
		 * Luego creo 2 hilos, asi me aseguro que la cosa 
		 * corra de forma paralela
		 */
		Transfering T = new Transfering();
		new produce(T);
		new consume(T);
	}

}
