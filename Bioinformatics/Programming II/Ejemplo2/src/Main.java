import java.io.*;

/**
 * Clase de ejemplo de lectura, usando flujos de entrada y salida.
 * @author natsuko
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * primero declaramos el isr, el cual nos permitira capturar el flujo,
		 * y un br en el cual guardaremos este.
		 */
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		/*
		 * para ser mas elegantes, usaremos un printstream, el cual dirigira el flujo hacia el canal de salida.XD
		 */
		PrintStream ps = new PrintStream(System.out);
		
		String entrada = null;
		int numero_a=0, numero_b=0;

		ps.println("Ingrese x: ");
		entrada = br.readLine();
		numero_a = Integer.parseInt(entrada);
		
		ps.println("Ingrese y: ");
		entrada = br.readLine();
		numero_b = Integer.parseInt(entrada);
		
		ps.println(numero_a + " + " + numero_b + " = " + (numero_a+numero_b));
		
		/*
		 * cerramos los canales
		 */
		br.close();
		isr.close();
		
		ps.println("Ejecucion Terminada con estatus 0.");
		ps.close();
		
		System.exit(0);
	}
}
