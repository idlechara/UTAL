package testing;

import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) throws IOException {
		/*
		 * ServerSocket. establece un puerto para recibir
		 * datos, no es mas que un canal cualquiera como
		 * System.in
		 */
		ServerSocket Socket_Server = null;
		
		try{
			/*
			 * Aca se intenta asignar al servidor un puerto
			 * el cual es entregado a travez de un valor 
			 * entero al constructor.
			 * 
			 *  se obvia la direccion ip a la cual han de
			 *  llegar los datagramas, ya que el encargado de
			 *  buscar al servidor es el cliente 
			 */
			Socket_Server = new ServerSocket(1313);
		}
		catch(IOException e){
			System.err.println("Couldn't create the server " +
					"acess port");
			System.exit(1);
		}
		
		/*
		 * Por que un socket para el cliente?
		 * 
		 * Si bien un socket (el del servidor) da una puerta de 
		 * acceso es nesesario aceptar las conexiones de ese puerto.
		 * 
		 */
		Socket Socket_Cliente = null;
		
		try{
			/*
			 * y ahora.. accept?
			 * 
			 * accept es un metodo predefinido para las
			 * clases SocketServer, el cual lo que hace
			 * es esperar hasta que alguien "toque" en el puerto,
			 * y cuando eso ocurre, devuelve un socket hacia quien 
			 * hizo la peticion.
			 */
			Socket_Cliente = Socket_Server.accept();
		}
		catch(IOException e){
			System.err.println("Couldn't connect to annyone.");
			System.exit(1);
		}
		
		/*
		 * Ya el neko se esta mandando kgas...
		 * 
		 * nop. ahora hay que crear una salida, o sea, que lo que
		 * nosotros enviemos desde aca, sea tranferida a travez del socket
		 * hacia el otro programa.
		 * 
		 * por eso hacemos una llamada a Socket_Cliente.getOutputStream(),
		 * para que nos devuelva un canal de salida.
		 * 
		 * para la entrada desde el cliente es lo mismo
		 */
		PrintWriter salida = new PrintWriter(Socket_Cliente.getOutputStream(),true);
		BufferedReader entrada = new BufferedReader(new InputStreamReader(Socket_Cliente.getInputStream()));
		
		String cadena_entrada, cadena_salida;
		cadena_salida = new String("Hi, you are connected to the NEKO_SERVER");
		
		/*
		 * te acuerdas de el printwritter??, el del socket?
		 * 
		 * bueno, aca esta de regreso. cada vez que lo llamemos
		 * y le digamos println(), imprimira en la entrada de datos 
		 * del cliente la palabra que le digamos. (en este
		 * caso, la cadena que le demos)
		 * 
		 */
		
		salida.println(salida);
		
		/*
		 * wii, ya vamos terminado (creo -_-")
		 * como podras recordar, cuando leemos desde la consola
		 * usamos readline(). aca readline es lo mismo, esta congelado
		 * hasta que le demos <enter>, pero en este caso, el enter
		 * es el println() o print() que recibe. 
		 */
		while((cadena_entrada = entrada.readLine()) != null){
			/*
			 * ahora, parseint(), tiene la gracia que es capaz
			 * de detectar si nosotros no le 
			 * damos un entero. nos colaremos de eso, para
			 * verificar que lo que le entregemos sea mayor que 0.
			 */
			if(Integer.parseInt(cadena_entrada) > 0){
				/*
				 * esto no es mas que otra forma de decir 
				 * que la cadena dentro del metodo (es una char-array)
				 * sea convertida en un string.. (super creativo, no?).
				 */
				cadena_salida = String.valueOf("You have done well... p" +
						"ut some more numbers...!");
				/*
				 * ahora esta cadena, la enviamos hacia nuestro cliente!
				 */
				salida.println(cadena_salida);
			}
			else break;
		}
		
		salida.println("See ya!");
		/*
		 * como en c hay que cerrar los descriptores
		 * aca hay que cerrar los canalesy los sockets, sino
		 * va a quedar la KGA!
		 */
		salida.close();
		entrada.close();
		Socket_Cliente.close();
		Socket_Server.close();
	}

}
