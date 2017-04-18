
public class Cabeza {

	Brazo brazo;
	Zonas zonas;
	boolean sentido, iniciado=true;
	int i=0, direccion;boolean siguente, siguienteAbajo,siguienteLateral;
	
	public Cabeza(Brazo brazo) {
		brazo.controlador.sis.printLog("Cabeza -> Iniciando.");
		this.sentido = true;
		this.brazo = brazo;
		this.zonas = this.brazo.controlador.sis.zonas;
	}


	public boolean mover(int altura) {
		//si es la primera vez, tonces se inicializa
		if(iniciado==true){
			this.i = this.brazo.controlador.sis.puntoInicial.x;
			iniciado = false;
		}
		do{	

			this.brazo.registraPunto(i, altura);
			
			//verificar q el brazo este en posicion, ver si es valido
			if(sentido){
				siguente = (zonas.coordEZ.contains(i+2, altura+2)== false)&&(zonas.coordZT.contains(i+2, altura+2));
			}
			else{
				siguente = (zonas.coordEZ.contains(i-2, altura+2)== false)&&(zonas.coordZT.contains(i-2, altura+2));
			}

			//verificar si existe siguiente directamente abajo
			siguienteAbajo = (zonas.coordEZ.contains(i, altura+2)== false)&&(zonas.coordZT.contains(i, altura+2));

			//verificar si existe siguiente directamente al lado
			if(sentido){
				siguienteLateral = (zonas.coordEZ.contains(i+2, altura)== false)&&(zonas.coordZT.contains(i+2, altura));
			}
			else{
				siguienteLateral = (zonas.coordEZ.contains(i-2, altura)== false)&&(zonas.coordZT.contains(i-2, altura));
			}

			System.out.println("-------\n" + siguente + " " + siguienteAbajo + " " + siguienteLateral + " " +"\n sentido: \n" + sentido);
			//si no se puede hacer mas.
			if((!siguienteAbajo)&&(!siguente)&&(!siguienteLateral)){
				System.out.println("opcion 1");
				return true;
			}

			//si no se puede avanzar, pero si bajar
			if((!siguienteLateral)&&(siguienteAbajo)){
				if(sentido)sentido=false;
				else sentido=true;
				System.out.println("opcion 2");
				return false;
			}
			if(!siguente&&siguienteAbajo){
				if(sentido)sentido=false;
				else sentido=true;
				System.out.println("opcion 3/2");
				return false;
			}
			if(!siguienteLateral&&!siguente){
				if(sentido)sentido=false;
				else sentido=true;
				System.out.println("opcion 4");
				return false;
			}

			if(sentido) i+=2;
			else i-=2;
			System.out.println("bypass");

		}while(true);
	}

}
