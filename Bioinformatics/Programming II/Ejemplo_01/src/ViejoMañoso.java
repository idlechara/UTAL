/**
 * Explicacion:
 * 
 * La sentencia import, es una directiva que le indica al precompilador que debe incluir las "librerias" o codigos, descritos en la ruta ahi definida.
 * podemos usar comodines ("*"),para poder indicar que desamos importar todas las clases alojadas en el jar.
 */
import java.util.*;

/**
 * Declaracion de una clase ejemplo.
 * 
 * nada mas que decir. en honor a mi graaaan viejo..xd
 * 
 * @author Erik Andres Regla Torres
 * @version 0.1a
 * @since 29/10/2010
 * @see http://programmers-journey.blogspot.com/
 */
public class ViejoMañoso {
	/*
	 * Aca declararemos todos los miembros de datos que nececitaremos para nuestra clase.
	 */
	public String nombre;
	public int nivel;
	public long hp;
	public long mp;
	public long exp;
	
	/**
	 * Constructor de la clase viejo mañoso. Como se podran dar cuenta
	 * el constructor no tiene retorno. Esto se debe a que se obia que el tipo es 
	 * de la misma clase.
	 * 
	 * @param nombre es el nombre que le daremos a nuestro viejo mañoso.
	 */
	public ViejoMañoso(String nombre){
		/*
		 * hasta ahora todo bien no?, estamos diciendo que cuando construyamos el objeto, este
		 * tome los siguentes valores en estos parametros. nada del otro mundo.
		 */
		nivel = 1;
		hp = 25;
		mp = 10;
		exp = 0;
		
		/*
		 * WTF!!!! QUE MIERDA ES ESTO!? THIS???
		 * bueh, si se fijan, como parametro recibimos un String llamado 'nombre'...
		 * pero dentro de nuestra clase tambien hay un String del mismo nombre.
		 * 
		 * 'this.', es la forma con la que java diferencia entre el argumento o valores propios
		 * con los inherentes a la clase.
		 * 
		 * 'this.nombre' hace referencia al Miembro de dato nombre que existe en la clase, mientras
		 * que 'nombre' hace referencia al parametro. por motivos de claridad del codigo, se recomienda
		 * siempre utilizar 'this' cuando se referencie a un mienbro de la clase.
		 */
		this.nombre = nombre;
		
		/*
		 * ahora llamamos una funcion del mismo metodo. notese el uso de 'this'
		 */
		this.imprimeStatus();
	}
	
	/**
	 * metodo que hace que el viejo impima por pantalla "DESCONCHETUMADRIZATE!' o 'CHUCHETUMARE!' 
	 * al azar, en factor de un numero aleatorio.
	 */
	public void gritar(){
		//creamos un objeto aleotrizador
		Random aleatorizador = new Random();
		
		//obligamos al resultado a ser 0 o 1... xD
		int resultado = aleatorizador.nextInt()%2;
		
		/*
		 * si el resultado es 0, imprime un mensaje, sube la exp, y baja el mp. si es 1, imprime un mensaje, baja el hp.
		 */

		System.out.print("El viejo mañoso ha gritado: ");
		
		if(resultado==0){
			System.out.println("DESCONCHETUMADRIZATE!");
			this.exp++;
			this.mp--;
		}
		else{
			System.out.println("CHUCHETUMARE!");
			this.hp--;
		}
		
		/*
		 * ahora comprobamos si se subio de nivel, y luego imprimimos
		 * 
		 * porsiaca, cada 5 puntos de exp, se sube un lvl..xD
		 */
		if((this.exp%5)==0){
			this.nivel++;
			System.out.println(this.nombre + " ha subido un nivel!, ahora es Lvl. " + this.nivel);
		}
		
		this.imprimeStatus();
	}
	
	/**
	 * Metodo que se encarga de mostrar por pantalla el status del viejo mañoso.
	 */
	public void imprimeStatus(){
		/*
		 * aca para concatenar cadenas no usamos funciones... usamos el '+'
		 * java automaticamente convierte los tipos de datos en cadenas de caracteres, en
		 * el caso de los tipos primitivos
		 */
		System.out.println("**************************");
		System.out.println("Lvl. " + this.nivel + " " + this.nombre);
		System.out.println("HP actual: " + this.hp);
		System.out.println("MP actual: " + this.mp);
		System.out.println("EXP adquirida: " + this.exp);
		System.out.println("**************************");
	}

	/**
	 * el main... el main se puede alojar bien en su propia clase, o en alguna clase cualquiera mientras sea publica.
	 * la cosa es q debe existir para la ejecucion del programa. en este caso, creare una nueva clase, a fin de hacerlo mas
	 * ilustrador y para que no se enreden.
	 */
}