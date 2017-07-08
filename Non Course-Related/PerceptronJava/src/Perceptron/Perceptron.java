package Perceptron;

import java.util.ArrayList;
import java.util.Random;

/**
 * Tipo enumerado que permite conocer que tipo de funcion sera usada para
 * prosesar la salida (respuesta, o potencial de accion)
 * 
 * @author kynku
 *
 */
enum ActivationFunctions {
	SIGMOID,THRESHOLD,H_TANGENT;
}//fin de la clase enumerada ActivationFunctions

/**
 * Clase perceptron, la cual guarda todos los metodos nesesarios para la manipulacion de este,
 * ademas de guardar la informacion relativa al aprendizaje.
 * 
 * En este caso, fue hecha una implementacion generica, de modo que este mismo modelo pueda ser
 * usado en mas de una situacion. Por ejemplo, desde detectar colores, hasta la imagen... aunque
 * esta ultima no funciona del todo bien.. u.u
 * 
 * En la implementacion de esta clase no fue tomada en cuenta el manejo de errores, ya que de
 * por momento no corresponde.
 * 
 * @author kynku
 *
 */
public class Perceptron {
	private float[] inputVector;
	private float[] weightsVector;
	private ActivationFunctions activationFunction;
	
	/**
	 * Contructor por defecto de la clase perceptron. Inicializa los pesos de las aristas
	 * de forma aleatoria, con valores entre -+0.5, inicializa el vector de entradas
	 * con 0 (aunque este paso no es del todo nesesario), y indica la funcion a usar para
	 * normalizar, ademas de fijar el numero de entradas a utilizar.
	 * 
	 * @param inputNumber el numero de entradas que recibira el perceptron
	 * @param activationFunction la funcion de activacion a usar
	 */
	public Perceptron(int inputNumber, ActivationFunctions activationFunction) {
		Random randomizer = new Random(System.currentTimeMillis());

		this.inputVector = new float[inputNumber];
		this.weightsVector = new float[inputNumber];
		
		for (int i = 0; i < inputNumber; i++) {
			this.weightsVector[i] = randomizer.nextFloat() - 0.5f;
			this.inputVector[i] = 0;
		}
		
		this.activationFunction = activationFunction;
	}//fin de constructor
	
	/**
	 * Ingresa una entrada en una posicion definida al vector de entradas.
	 * 
	 * @param position la posicion a la cual agregar el dato
	 * @param value el valor a agregar
	 */
	public void inputAtPosition(int position, float value){
		this.inputVector[position] = value;
	}//fin de metodo inputAtPosition
	
	/**
	 * Calcula la respuesta de el perceptron. Realiza la suma ponderada, y
	 *  luego normaliza la salida de esta.
	 *  
	 * @return la respuesta (potencial de accion) del perceptron
	 */
	public float calculateResponse(){
		float response = 0.0f;

		for(int i=0; i<this.inputVector.length; i++)
		{
			response += this.inputVector[i] * this.weightsVector[i];
		}
		
		/*
		 * NOTA:
		 * 
		 * averiguando por algunos textos, vi que existian 3 funciones que eran utilizadas 
		 * normalmente para prosesar la salida. La primera, la funcion umbral, entrega un 1 si es 
		 * que existe alguna respuesta de parte del perceptron y 0 si es que no existe.
		 * 
		 * Ahora hay casos en que este tipo de respuesta puede no ser la mas adecuada, hay
		 * que "suavizarla" un poco mas, en este caso es usada la funcion sigmoide. En otros, se puede
		 * usar la tangente hiperbolica.
		 * 
		 */
		switch (activationFunction) {
		case THRESHOLD:
			if(response >= 0)
				response = 0;
			else
				response = 1;
			break;
			
		case SIGMOID:
			response = (float) (1.0f / (1.0f + Math.exp(-response)));
			break;
			
		case H_TANGENT:
			response = (float) ((Math.exp(2*response)-1)/(Math.exp(2*response)+1));
		}
		
		return response;
	}//fin del metodo inputAtPosition
	
	/**
	 * Ajusta el vector de pesos, de modo que se pueda lograr el aprendizaje.
	 * 
	 * @param teachingStep el nivel de aprendizaje de la neurona por cada paso
	 * @param output la salida previa de la red. Es nesesaria para poder ajustar los pesos correctamente
	 * @param target valor que indica la validez de un ejemplo. Se utiliza 1 para indicar verdadero. 
	 * (la razon de esto esta en que 0 no aporta nada, por ende al realizar la diferencia, esta
	 * entrega un valor negativo. si es mayor que esta, en este caso un 1 debido a que ese es el
	 * limite de la funcion utilizada, entonces el valor entregado puede ser positivo...)
	 */
	public void adjustWeights(float teachingStep, float output, float target){
		//TODO codigo
		for (int i = 0; i < this.inputVector.length; i++) {
			this.weightsVector[i] = this.weightsVector[i] + teachingStep*(target-output) + this.inputVector[i];
		}
	}//fin del metodo adjustWeights
	
	/**
	 * Realiza una "consulta" a la neurona.
	 * 
	 * @param input vector de entrada
	 * @return respuesta de la neurona
	 */
	public float recall(float[] input){
		for (int i = 0; i < input.length; i++) {
			this.inputVector[i] = input[i];
		}
		
		return calculateResponse();
	}//fin del metodo recall
	
	/**
	 * Alogritmo de entrenamiento de la neurona, pero a diferencia de la original, esta vez
	 * viene incluida en la clase.
	 * 
	 * este metodo va mas detallado de lo normal, debido al hecho que fue escrito sin ser probado...
	 * 
	 * @param inputs los conjuntos de ejemplos para la neurona
	 * @param inputSize el tamaño de los conjuntos de ejemplo
	 */
	public void train(ArrayList<float[]> inputs, int inputSize){
		/*
		 *  el mse (mean square error), ahce referencia al margen de error de la misma neurona.
		 *  al momento de probarla, se comprobo que si la presicion es muy amplia, se obitiene mayor generalidad
		 *  al momento de revisar ejemplos, pero conlleva a que no siempre es capaz de detectar
		 *  los ejemplos mostrados. por el contrario si la presicion (o margen de error) es mas
		 *  acotado, esta tendera a ser mas deterministica, pero puede conllevar a que lo sea tanto que
		 *  termine solo reconociendo los ejemplos mostrados.
		 *  
		 *  el teaching step, hace referencia a el nivel de aprendizaje producido por cada
		 *  vez que se le enseña algo.
		 */
		
		//se inicializan valores
		int inputCounter = 0;
		float meanSquareError = 0.0f, mseLimit = 0.001f, errorValue = 0.0f;
		float output = 0.0f;
		final float teachingStep = 0.01f;
	
		//entonces..
		do{
			//se reinicializan los contadores
			meanSquareError = 0.0f;
			errorValue = 0.0f;
			inputCounter = 0;
			
			//ciclo que recorre cada conjunto de ejemplos
			for (int i = 0; i < inputs.size(); i++) {
				//ciclo que ingresa los valores a la neurona
				for (int j = 0; j < inputSize-1 ; j++) {
					this.inputAtPosition(j, inputs.get(i)[inputCounter++]);
				}//fin del ciclo que ingresa los valores a la neurona
				
				//se calcula respuesta de la neurona, y se evalua el margen de error,
				//contrastando el valor esperado con el valor real
				output = this.calculateResponse();
				errorValue += Math.abs(inputs.get(i)[inputCounter] - output);
				
				//se ajustan los pesos para este ejemplo y avanza al siguente
				this.adjustWeights(teachingStep, output, inputs.get(i)[inputCounter]);
				inputCounter++;
			}//fin del ciclo que recorre ejemplos

			//se calcula el error de esta iteracion 
			meanSquareError = errorValue / inputs.size();
		}
		while(Math.abs(meanSquareError- mseLimit) > 0.0001f);
	}//fin del metodo train
}//fin de clase Perceptron
