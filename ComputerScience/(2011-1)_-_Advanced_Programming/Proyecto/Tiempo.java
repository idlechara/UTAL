import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase Tiempo
 * 
 * Debido a la necesidad de poder tener un control acerca de los
 * bloques de disponibilidad, se impuso esta clase, de modo que
 * se pudiera trabajar de forma mas comoda el manejo del tiempo. 
 * Basicamente todas las funciones son hechas sobre una matriz tridimensional
 * la cual guarda la informacion de que si un bloque esta ocupado o no.
 * 
 * Respecto a lo del manejo de la matriz, debido a que todas las operaciones reccorren
 * en el peor caso la matriz completa, esta tendria un tiempo de ejecucion
 * promedio n³... aunque si pensamos que no es una matriz derechamente cuadrada,
 * y por el tipo de dimensiones que posee, podriamos pensar que las funciones 
 * se pueden ejecutar en un tiempo T(n)=5(n²)
 * 
 * @author Erik Andres Regla Torres
 * @version 0.1
 * @since 0.1
 *
 */
public class Tiempo {

	//miembros de datos
	private boolean[][][] bloquesDisponibles;	//la matriz en la que se guardaran los datos

	//miembros de clase, son nesesarios debido a que hay q tener un control respecto a las cantidades
	/**
	 * Constante que indica la cantidad de semanas a prosesar.
	 * 
	 * @author Erik Andres Regla Torres
	 * @since 0.1
	 */
	static private final int SEMANAS = 18;
	/**
	 * Constante que indica la cantidad de dias que contiene una semana
	 * 
	 * @author Erik Andres Regla Torres
	 * @since 0.1
	 */
	static private final int DIAS = 5;
	/**
	 * Constante que indica la cantidad de bloques que contiene un dia.
	 * 
	 * @author Erik Andres Regla Torres
	 * @since 0.1
	 */
	static private final int BLOQUES = 11; 
	
	/**
	 * Constructor de la clase Tiempo
	 * 
	 * Inicializa la matriz en valores falsos, indicando que no hay ningun tiempo ocupado.
	 * 
	 * El sistema para incicializar utiliza los valores de las constantes definidas con anterioridad,
	 * usando un ciclo anidado tres veces, para recorrer completamente la matriz y poder incicializar
	 * los valores
	 * 
	 * @author Erik Andres Regla Torres
	 * @since 0.1
	 */
	public Tiempo() {
		this.bloquesDisponibles = new boolean[Tiempo.SEMANAS][Tiempo.DIAS][Tiempo.BLOQUES];		//crea los bloques
		for(int i=0; i<Tiempo.SEMANAS; i++){													//ahora recorre la matriz, para
			for(int j=0; j<Tiempo.DIAS; j++){													//luego incializar valores
				for(int k=0; k<Tiempo.BLOQUES; k++){
					this.bloquesDisponibles[i][j][k] = false;
				}
			}
		}
	}
	
	/**
	 * Getter de el miembro bloquesDisponibles. 
	 * 
	 * La razon por la cual no se le puso getBloquesDisponibles, es por que
	 * cuando se llama, lo que se hace es llamar derechamente a la matriz para
	 * trabajar con ella, no es que la queramos usar en alguna otra
	 * instancia.
	 * 
	 * @return
	 * 
	 * @author Erik Andres Regla Torres
	 * @since 0.1
	 * 
	 */
	private boolean[][][] getMatrix(){
		return this.bloquesDisponibles;
	}
	
	//TODO isDisponible, reservarSemana, reservarSemestre.... xD
	
	/**
	 * Verificador de matriz. Se encarga de indicar si la matriz actual, tiene alguna
	 * similitud con la entregada como parametro, lo cual se traduce en el saber
	 * si existe tope horario entre el entregado y el presente aca.
	 * 
	 * El metodo de operacion es igual al descrito previamente; se recorre la matriz
	 * completamente, regresando de forma inmediata un valor falso y saliendo del
	 * metodo en caso de encontrar una similitud. Caso contrario, si no haya ninguna
	 * ha de terminar las iteraciones y retornar un verdadero.
	 * 
	 * @param matriz El tiempo con el que se ha de comparar la matriz o tiempo actual
	 * 
	 * @return Un booleano que indica si la matriz es compatible, en otras palabras  si no hay tope horario
	 * 
	 * @author Erik Andres Regla Torres
	 * @since 0.1
	 *  
	 */
	@Deprecated
	public boolean isDisponible(Tiempo matriz){
		for(int i=0; i<Tiempo.SEMANAS; i++){													//ahora recorre la matriz, para
			for(int j=0; j<Tiempo.DIAS; j++){													//lverificar los valores
				for(int k=0; k<Tiempo.BLOQUES; k++){
					//ahora si es que encuentra un bloque ocupado.....
					if((this.bloquesDisponibles[i][j][k])&&
							(matriz.getMatrix()[i][j][k])){
						return false;
					}
				}
			}
		}
		//si fue capaz de llegar aca, es por que no hubo problemas, entonces...
		return true;
	}
	
	/**
	 * Metodo isEmpty, el cual verifica que esta matriz tenga algun espacio
	 * ocupado.
	 * 
	 * Para realizar esta comprobacion se recurre a lo tipico; se recorre la matriz
	 * completa y si hay algun bloque ocupado, entonces quiere decir que no
	 * esta ocupada
	 * 
	 * @return true si es que esta libre
	 */
	public boolean isEmpty(){
		for(int i=0; i<Tiempo.SEMANAS; i++){					
			for(int j=0; j<Tiempo.DIAS; j++){	
				for(int k=0; k<Tiempo.BLOQUES; k++){
					//ahora si es que encuentra un bloque ocupado.....
					if(this.bloquesDisponibles[i][j][k]){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Metodo reservar dia, (aunque mas que reservar dia, parecer reservar semana ._.)
	 * el cual realiza una verificacion sobre la matriz y paralelamente
	 * la escribe, usando un bloque de memoria temporal para evitar 'accidentes', 
	 * de modo que las modificaciones quedan almacenadas ahi, 
	 * y despues la reemplazamos en la original y la borramos
	 * 
	 * El metodo de operacion es bastante simple, y similar a las implementaciones anteriores,
	 * recorriendo la matriz completamente hasta que algo ocurra.
	 * 
	 * @param semana Entero que describe la semana en la cual estamos trabajando
	 * @param diaBloque HashMap<Integer , ArrayList <>>
	 * @return La indicacion que si la operacion pudo ser efectuada exitosamente
	 * 
	 * @author Erik Andres Regla Torres
	 * @since 0.1
	 */
	@SuppressWarnings("rawtypes")
	public boolean reservarDia(int semana, HashMap<Integer, ArrayList> diaBloque){
		//creamos la matriz de respaldo
		boolean[][][] matrix = new boolean[Tiempo.SEMANAS][Tiempo.DIAS][Tiempo.BLOQUES];
		
		Integer valor = null;
		
		for(int i=0; i<Tiempo.DIAS; i++){
			//si es que contiene este valor dentro de el hashmap en forma de
			//key, entonces...
			valor = new Integer(i);
			if(diaBloque.containsKey(valor)){
				//para cada valor guardado en el HashMap
				for(Object obj: diaBloque.get(valor)){
					valor = (Integer) obj;
					//si es que es no valida...
					if(!matrix[semana][i][valor.intValue()]){
						return false;
					}
					//caso analogo, en el que el valor no este ocupado...
					else{
						matrix[semana][i][valor.intValue()] = true;
					}
				}
			}
		}
		this.bloquesDisponibles = matrix;
		return true;
	}

	/**
	 * Metodo reservarSemeste().  ReHashMap<Integer, ArrayList> diaBloquealiza una verificacion de la matriz, al tiempo que guarda 
	 * para el bloque completo los datos del dia, usando un bloque de memoria
	 * temporal para escribir los datos ahi, y luego exportarlos a la matriz original.
	 * 
	 * Tiene un uso bastante similiar al del reservarDia(),
	 * pero con la diferencia que como en este caso el bloque representa un semestre completo,
	 * no nececitamos el paramtro de la semana a reservar, por lo tanto, aplicamos la iteracion
	 * a la matriz completa, pero bajo los mismos parametros.
	 * 
	 * @param diaBloque HashMap<Integer , ArrayList <>>
	 * @return La indicacion que si la operacion pudo ser efectuada exitosamente
	 * 
	 * @author Erik Andres Regla Torres
	 * @since 0.1
	 */
	@SuppressWarnings("rawtypes")
	public boolean reservarSemestre(HashMap<Integer, ArrayList> diaBloque){

		//creamos la matriz de respaldo
		boolean[][][] matrix = new boolean[Tiempo.SEMANAS][Tiempo.DIAS][Tiempo.BLOQUES];
		
		Integer valor = null;
		
		//a diferencia del metodo anterior... este recorre por todas las semanas.
		for (int i = 0; i < Tiempo.SEMANAS; i++) {
			for (int j = 0; j < Tiempo.DIAS; j++) {
				//si es que contiene este valor dentro de el hashmap en forma de
				//key, entonces...
				valor = new Integer(j);
				if (diaBloque.containsKey(valor)) {
					//para cada valor guardado en el HashMap
					for (Object obj : diaBloque.get(valor)) {
						valor = (Integer) obj;
						//si es que es no valida...
						if (!matrix[i][j][valor.intValue()]) {
							return false;
						}
						//caso analogo, en el que el valor no este ocupado...
						else {
							matrix[i][j][valor.intValue()] = true;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Metodo deReservar dia, el cual, inicializa las posiciones de la matriz
	 * de modo que quedan marcadas con un valor el cual indica que estan disponibles,
	 * para una semana dada.
	 * 
	 * @param semana Entero que describe la semana en la cual estamos trabajando
	 * @param diaBloque HashMap<Integer , ArrayList <>>
	 * @return La indicacion que si la operacion pudo ser efectuada exitosamente
	 * 
	 * @author Erik Andres Regla Torres
	 * @since 0.1
	 */
	@SuppressWarnings("rawtypes")
	public boolean deReservarDia(int semana, HashMap<Integer, ArrayList> diaBloque){
		//creamos la matriz de respaldo
		boolean[][][] matrix = this.bloquesDisponibles;
		
		Integer valor = null;
		
		for(int i=0; i<Tiempo.DIAS; i++){
			//si es que contiene este valor dentro de el hashmap en forma de
			//key, entonces...
			valor = new Integer(i);
			if(diaBloque.containsKey(valor)){
				//para cada valor guardado en el HashMap
				for(Object obj: diaBloque.get(valor)){
					valor = (Integer) obj;
					//incializa el valor.
					matrix[semana][i][valor] = false;
				}
			}
		}
		this.bloquesDisponibles = matrix;
		return true;
	}

	/**
	 * Metodo deReservar Semana, el cual, inicializa las posiciones de la matriz
	 * de modo que quedan marcadas con un valor el cual indica que estan disponibles,
	 * pero ya que una matriz guarda la informacion de un semestre, entonces 
	 * reinicializa todos los valores comprendidos en el semestre, que concuerden con los
	 * entregados.
	 * 
	 * @return La indicacion que si la operacion pudo ser efectuada exitosamente
	 * 
	 * @author Erik Andres Regla Torres
	 * @since 0.1
	 */
	@SuppressWarnings("rawtypes")
	public boolean deReservarSemestre(HashMap<Integer, ArrayList> diaBloque){

		//creamos la matriz de respaldo
		boolean[][][] matrix = new boolean[Tiempo.SEMANAS][Tiempo.DIAS][Tiempo.BLOQUES];
		
		Integer valor = null;
		
		//a diferencia del metodo anterior... este recorre por todas las semanas.
		for (int i = 0; i < Tiempo.SEMANAS; i++) {
			for (int j = 0; j < Tiempo.DIAS; j++) {
				//si es que contiene este valor dentro de el hashmap en forma de
				//key, entonces...
				valor = new Integer(j);
				if (diaBloque.containsKey(valor)) {
					//para cada valor guardado en el HashMap
					for (Object obj : diaBloque.get(valor)) {
						valor = (Integer) obj;
						matrix[i][j][valor] = false;
					}
				}
			}
		}
		return true;
	}

}