package Proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/**
     * 
     * Esta funcion lee un numero entero y valida dentro de un rango si es valido o no.
     * 
     * @param minRange minimo del rango de numeros para validar
     * @param maxRange maximo del rango de numero a valdar.
     * @version 1.1
     * @author Erik Regla
     * @return el valor dentro del rango esperado
     * @throws IOException 
     * @throws NumberFormatException
     */
    private int leerInt ( int minRange, int maxRange){
        boolean loop = true;
        int valido = -1;
        System.out.println("Ingrese su opcion:  ");
        while(loop){
            try{
                valido = Integer.parseInt(br.readLine());
                if(valido < minRange || valido> maxRange){
                    throw new NumberFormatException();
                }
                loop=false;

            } 
            catch(NumberFormatException e){
                System.err.println("Error de ingreso , Intente nuevamente ");

            }
            catch(IOException e){
                //kynku: modifificando salida (formato)
                System.err.print("Error de entrada y salida, Intente nuevamente... ");

            }

        }
        return valido;
    }
    
    private void dormicir(){
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void menuPrincipal(){
        while (true) {
            //menu principal
        	System.out.println("\f\n");
            System.out.println("\nMenu principal:");
            System.out.println("[1] Ingresar");
            System.out.println("[0] Salir");
            switch(leerInt(0, 1)){
            case 1:
            	menuIngresar();
            	break;
            case 0:
            	return;
            }
        }
    }

	private void menuIngresar() {
        while (true) {
            //menu ingreso
        	System.out.println("\f\n");
            System.out.println("\nLogin:");
            
            //TODO implementar sistema de login
            int type = login();
            
            switch(type){
            case 1:
            	menuAdmin();
            	break;
            case 2:
            	menuUsuarioAdmin();
            	break;
            case 3:
            	menuGuest();
            	break;
            case 0:
            	System.out.println("Ingreso invalido.");
            	dormicir();
            	return;
            }
        }
	}
	
	private void menuJuegos(){
        while (true) {
            //menu principal
        	System.out.println("\f\n");
            System.out.println("\nMenu juego:");
            System.out.println("[1] Añadir Juego");
            System.out.println("[2] Eliminar Juego");
            System.out.println("[3] Editar Juego");
            System.out.println("[0] Salir");
            switch(leerInt(0, 3)){
            case 1:
            	añadirJuego();
            	break;
            case 2:
            	eliminarJuego();
            	break;
            case 3:
            	editarjuego();
            	break;
            case 0:
            	return;
            }
        }
	}

	private void menuInformacion(){
        while (true) {
            //menu principal
        	System.out.println("\f\n");
            System.out.println("\nMenu informacion:");
            System.out.println("[1] Informacion juegos");
            System.out.println("[2] Informacion votaciones");
            System.out.println("[3] Informacion General");
            System.out.println("[0] Salir");
            switch(leerInt(0, 3)){
            case 1:
            	informacionJuegos();
            	break;
            case 2:
            	informacionVotaciones();
            	break;
            case 3:
            	informacionGeneral();
            	break;
            case 0:
            	return;
            }
        }
	}


	@SuppressWarnings("unused")
	private void menuUser(){
        while (true) {
            //menu principal
        	System.out.println("\f\n");
            System.out.println("\nMenu usuario:");
            System.out.println("[1] Ver juego");
            System.out.println("[2] Votar Juego");
            System.out.println("[3] Ver votacion juego");
            System.out.println("[0] Salir");
            switch(leerInt(0, 3)){
            case 1:
            	verJuego();
            	break;
            case 2:
            	votarJuego();
            	break;
            case 3:
            	verVotacionesJuegos();
            	break;
            case 0:
            	return;
            }
        }
	}

	private void menuAdmin ()
    {
    	 while (true)
    	 {
    		 System.out.println("\f\n");
             System.out.println("\nMenu Administrador:");
             System.out.println("[1] Menu Juegos");
             System.out.println("[2] Menu Votaciones");
             System.out.println("[3] Menu Usuarios");
             System.out.println("[4] Menu Informacion");
             System.out.println("[0] Salir");
             switch(leerInt(0, 4)){
             case 1:
             	menuJuegos();
             	break;
             case 2:
             	menuVotaciones();
             	break;
             case 3:
             	menuUsuarioAdmin();
             	break;
             case 4:
            	 menuInformacion();
            	 break;
             case 0:
             	return;
             }
    	 }  	 
    }
    
    
    private void menuVotaciones()
    {
    	 while (true)
    	 {
    		 System.out.println("\f\n");
             System.out.println("\nMenu Votaciones");
             System.out.println("[1] Eliminar Votaciones");
             System.out.println("[0] Salir");
             switch(leerInt(0, 1)){
             case 1:
             	eliminarVotaciones();
             	break;
             case 0:
             	return;
             }
    	 }
    }
	private void menuUsuarioAdmin()
    {
    	 while (true)
    	 {
    		 System.out.println("\f\n");
             System.out.println("\nMenu Usuarios");
             System.out.println("[1] Agregar Administrador");
             System.out.println("[2] Eliminar Usuarios");
             System.out.println("[0] Salir");
             switch(leerInt(0, 2)){
             case 1:
             	agregarAdministrador();
             	break;
             case 2:
             	eliminarUsuarios();
             	break;
             case 0:
             	return;
             }
    	 }
    }

	private void menuGuest()
    {
    	 while (true)
    	 {
    		 System.out.println("\f\n");
             System.out.println("\nMenu Administrador:");
             System.out.println("[1] Ver Juego");
             System.out.println("[2] Registrar Usuario ");
             System.out.println("[0] Salir");
             switch(leerInt(0, 2)){
             case 1:
             	verJuego();
             	break;
             case 2:
             	registrarUsuario();
             	break;
             case 0:
             	return;
             }
    	 }
    	
    }


    
    private void eliminarUsuarios() {
    	//TODO implementar eliminiarUsuarios
    	System.out.println("elmina un usuario! D:");
    	dormicir();
	}

	private void agregarAdministrador() {
    	//TODO implementar agregarAdministrador
    	System.out.println(" agrega administrador");
    	dormicir();
	}
	
    private void registrarUsuario() {
    	//TODO implementar registrarUsuario
    	System.out.println("registra un nuevo usuario");
    	dormicir();
	}

	private void eliminarVotaciones() {
    	//TODO implementar eliminarVotaciones
    	System.out.println("elmina votaciones! D:");
    	dormicir();
	}

	private void verVotacionesJuegos() {
		// TODO implementar verVotacionesJuegos
		System.out.println("muestra las votaciones de los juegos");
		dormicir();
	}

	private void votarJuego() {
		// TODO implementar votarJuego
		System.out.println("vota un juego");
		dormicir();
	}

	private void verJuego() {
		// TODO implementar informacionGeneral
		System.out.println("muestra la informacion general");
		dormicir();
	}

	private void informacionGeneral() {
		// TODO implementar informacionGeneral
		System.out.println("muestra la informacion general");
		dormicir();
	}
	
	private void informacionVotaciones() {
		// TODO implementar informacionVotaciones
		System.out.println("muestra la informacion de las votaciones");
		dormicir();
	}

	private void informacionJuegos() {
		// TODO Auto-generated method stub
		System.out.println("muestra la informacion de los juegos");
		dormicir();
		
	}

	private void editarjuego() {
		//TODO implementar editaJuego
		System.out.println("Editar juego?");
		dormicir();
	}

	private void eliminarJuego() {
		//TODO implementar eliminarJuego
		System.out.println("eliminar juego?");
		dormicir();
	}

	private void añadirJuego() {
		//TODO implementar añadirJuego
		System.out.println("Añadir juego?");
		dormicir();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////

	private int login() {
		// TODO Implementar login
		
		///SOLUCION TEMPORAL!
		System.out.println("\f\n");
        System.out.println("\nElija tipo de usuario:");
        System.out.println("[1] Administrador");
        System.out.println("[2] Usuario");
        System.out.println("[3] Guest");
        System.out.println("[0] Salir");
        
		return leerInt(0, 3);
	}
}
