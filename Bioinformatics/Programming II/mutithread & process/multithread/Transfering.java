package multithread;

import java.io.IOException;
/*
 * este es el vinculo... como dijo que mis opciones se
 * limitan a crear un intermediario, aca esta. decidi centralizar
 * tanto metodos como variables en una clase, para asi
 * ahorrarme el trabajo de escribir tanto.
 */
public class Transfering {
	
	//es el ingreso que le pasa put
	String input;
	//es la clase que nos permitira leer
	Read read;
	/*
	 * este booleano es el corazon de este codigo,
	 * determina "si hay comida o no".
	 */
	boolean is_entered;
	
	public Transfering(){
		/*
		 * nada del otro mundo..
		 * una simple inicializacion
		 */
		this.read = new Read();
		this.is_entered = false;
	}

	/*
	 * este es un metodo sincronizado, asi mientras un hilo este en
	 * esta clase, modificando atributos o lo que sea, no lo
	 * pueda modificar otra.
	 * 
	 * arroja un InterruptedException, ya que asi reclama
	 * si es que algo interrumpio el proseso.
	 */
	public synchronized void get() throws InterruptedException{
		/*
		 * primero, si es que no hay comida, el "perro" no tendira
		 * por que estar comiendo, asi que se hace una llamada a
		 * wait, que le ordena al thread actual que salga de esta clase
		 * hasta que algun otro thread haya llamado dentro de esta a
		 * notify(), indicandole que vuelva aca.
		 */
		if(this.is_entered == false){
			//System.out.println("El perro esta impaciente -espera- !");
			wait();
		}
		/*
		 * una vez mostrado que el perro comio, se regresa el booleano
		 * is_entered a falso, ya que ota vez no hay comida aca. luego
		 * se hace la llamada a notify(), para que asi el otro thread
		 * pueda ingresar aca y este se retire.
		 */
		System.out.println("Ñam ñam! -tu perro ha comido " + this.input + "-.");
		this.is_entered = false;
		notify();
	}

	/*
	 * este es un metodo sincronizado, asi mientras un hilo este en
	 * esta clase, modificando atributos o lo que sea, no lo
	 * pueda modificar otra.
	 * 
	 * arroja un InterruptedException, ya que asi reclama
	 * si es que algo interrumpio el proseso.
	 */
	public synchronized void put() throws InterruptedException, IOException{
		/*
		 * aca es otro el caso... si es que hay comida, no tendiras 
		 * por que darle mas al perro aun... ¬¬
		 * 
		 * por ende, si esto ocurre, le cedemos el control a otro thread
		 * para que coma.
		 */
		if(this.is_entered == true){
			//System.out.println("Tu perro aun esta comiendo... no le des mucha comida! ¬¬");
			wait();
		}
		/*
		 * ahora, una vez ingresada la comida,
		 * cambia el booloeano is_entered a true
		 * asi, le decimos al objeto que hay comida.
		 * 
		 * luego llamamos a notify(), para devolverle 
		 * el control a el thread 
		 * que lo cedio.
		 */
		System.out.println("Ingrese comida para el perro!: ");
		input = read.string();
		this.is_entered = true;
		notify();
	}
}
