package multithread;

import java.io.IOException;

/*
 * @descripcion de esta clase: recibe ingresos por consola
 * (comida), y se la da a el objeto transporter.
 * 
 */
public class produce implements Runnable {
	/*
	 * Vinculo con el mundo exterior
	 */
	Transfering transporter;
	
	/*
	 * primero muestra la creacion de el hilo,
	 * luego le paso su vinculo con el mundo exterior,
	 * luego arranca el hilo.
	 */
	produce(Transfering trans){
		System.out.println("Se ha creado un hilo productor!");
		this.transporter = trans;
		new Thread(this,"Producer").start();
	}
	
	
	/*
	 * Es simple, corre eternamente la accion de colocar
	 */
	public void run(){
		try{
			while(true){
				this.transporter.put();
			}
		}
		catch (InterruptedException e){
			System.out.println("Se ha interrumpido el hilo que alimenta!");
		}
		catch (IOException e){
			System.out.println("Error en el ingreso!");
		}
	}
}
