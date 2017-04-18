/**
 * Clase principal, o main, la cual alberga unicamente al metodo principal.
 * 
 * @author Erik Andres Regla Torres
 * @version 0.1a
 * @since 29/10/2010
 * @see http://programmers-journey.blogspot.com/
 *
 */
public class Main {

	/**
	 * metodo principal... no hay mucho que decir la verdad.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//creamos nuestro objeto viejo mañoso!
		ViejoMañoso anciano = new ViejoMañoso("Mr. Burns");
		
		//ahora hacemos un ciclo que repita 10 veces las acciones
		for(int i=0; i<10; i++){
			anciano.gritar();
		}
		
		System.out.println("\nEjecucion Terminada.");
		System.exit(0);
	}

}
