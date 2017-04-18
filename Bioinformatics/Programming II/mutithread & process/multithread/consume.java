package multithread;
/*
 * @Funcion: es un "thread", que consume lo que 
 * le ingresa el productor
 * para hacerlo mas gracioso y amigable, se ha implementado
 * el comportamiento de un perro! (inu)
 */
public class consume implements Runnable {
	/*
	 * @Tranfering: Solo sirve para transportar los datos y 
	 * para dar funciones.
	 */
	Transfering transporter;
	
	/*
	 * @consume
	 * primero se indica que se creo un hilo consumidor,
	 * luego le asigno a esta clase su vinculo con el
	 * mundo exterior, y al final arranco el hilo.
	 */
	consume(Transfering trans){
		System.out.println("Se ha creado un hilo consumidor!");
		this.transporter = trans;
		new Thread(this,"Consumer").start();
	}
	
	/*
	 * @run
	 * ejecuta de forma eterna la accion de recibir
	 */
	public void run(){
		try{
			while(true){
				this.transporter.get();
			}
		}
		catch (InterruptedException e){
			System.out.println("Se ha interrumpido el hilo que alimenta!");
		}
	}
}
