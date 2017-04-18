package basicSystem;

import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		//se crea un servidor
		ServerSocket SocketServidor = null; 
		
		try{
			SocketServidor = new ServerSocket(6789);
		}
		catch(IOException e){
			System.err.println("Se ha producido un " +
					"error al intentar abrir el socket");
			System.exit(1);
		}
		
		//se inicia un ciclo para escuchar clientes
		/*
		 * si se llega a escuchar uno
		 * -crea un nuevo hilo.
		 * -se actualiza la lista de activos
		 * -se inicia la conexion y repite
		 */
		ArrayList storage = new ArrayList();
		Socket newConnection;
		while(connected_users >=0){
			try{
				newConnection = SocketServidor.accept();
				new NewConversation(newConnection);
				newConnection = null;
			}
			
		}

	}

}
