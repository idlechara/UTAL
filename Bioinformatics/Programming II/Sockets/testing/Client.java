package testing;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) throws IOException {
		Socket Socket_Conexion = null;
		BufferedReader entrada = null;
		PrintWriter salida = null;
		String localizacion = "localhost";
		int puerto = 1313;
		/*
		 * QUE ES ESTO!!!!!! @_@!!
		 * 
		 * nada del otro mundo!, primero se ha de 
		 * establecer una conxion con un programa servidor
		 * alojado en algun sitio. Para ello usamos un socket
		 * (traducido del ingles "Enchufe, Adaptador").
		 * Para inicializar un socket (red), hay que darle al constructor
		 * dos parametros: un lugar (dado por una ip, que es puesta como
		 * un tipo String), y un puerto (es el lugar por donde se 
		 * reciben-mandan datos, es puesto como un tipo int).
		 * 
		 * Aca se puede apreciar como lugar de conexion un tal
		 * "localhost", y no una ip, como se ha de esperar.
		 * 
		 * localhost es el nombre que la maquina se da a si misma.
		 * al referirnos a localhost, nos referimos a un servidor puesto
		 * dentro de esta misma maquina.
		 * 
		 *  y en el campo nro2, encontramos un numero!
		 *  
		 *  este es el puerto por el cual se espera que el
		 *  servidor "escuche" al cliente.
		 */
		try{
			//se intenta conectar al servidor...
			Socket_Conexion = new Socket(localizacion,puerto);
			
			/*
			 * aca se intenta abrir un canal para escribir en
			 * el pc apuntado por el socket. para ello
			 * usamos la funcion getOutputStream(), la cual
			 * devuelve un canal de salida. (estilo system.out)
			 */
			salida = new PrintWriter(Socket_Conexion.getOutputStream(), true);
			
			/*
			 * otra vez lo mismo, pero ahora con un canal de entrada
			 */
			entrada = new BufferedReader(new InputStreamReader(Socket_Conexion.getInputStream()));
		}
		catch(UnknownHostException e){
			/*
			 * esta excepcion es propia de las fallas
			 * que ocurren al momento de no encontrar un host
			 * (servidor). 
			 */
			System.err.println("Couldn't find the server " 
					+ localizacion);
		}
		catch(IOException e){
			System.err.println("Couldn't initialize/feedback a connection" +
					" on the port" + puerto);
		}
		
		//un lector comun y silvestre
		BufferedReader lector_usuario = new BufferedReader(new InputStreamReader(System.in));
		String cadena_cliente, cadena_servidor;
		
		/*
		 * aca la condicion para seguir/entrar en el ciclo
		 * es que el servidor envie una cadena. lo cual se 
		 * da desde el principio, ya que al conectar envia un mensaje
		 */
		while((cadena_servidor = entrada.readLine()) != null){
			System.out.println("Server: " + cadena_servidor);
			
			/*
			 * primero se verfica que no se haya salido del server
			 */
			if(cadena_servidor.equals("See Ya!")) break;
			
			/*
			 * luego se lee una linea
			 */
			cadena_cliente = lector_usuario.readLine();
			
			/*
			 * luego, como final se la enviamos,
			 * usando el canal creado prviamente.
			 */
			if(cadena_cliente != null){
				System.out.println("Client: " + cadena_cliente);
				salida.println(cadena_cliente);
			}
		}
		
		/*
		 * recordemos que hay que cerrar canales! :)
		 */
		
		entrada.close();
		salida.close();
		Socket_Conexion.close();
		lector_usuario.close();
	}

}
