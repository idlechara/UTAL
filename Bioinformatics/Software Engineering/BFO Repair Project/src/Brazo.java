import java.awt.Point;


public class Brazo {

	Controlador controlador;
	Cabeza cabeza;
	Zonas zonas;
	int altura;
	boolean finalizado;
	public Brazo(Controlador controlador) {
		this.controlador = controlador;
		this.controlador.sis.printLog("Brazo -> Iniciando.");
		this.cabeza = new Cabeza(this);
		this.zonas = this.controlador.sis.zonas;
	}

	public boolean mover(int altura) {
		this.controlador.sis.printLog("Brazo: Mover()");
		return this.cabeza.mover(altura);
	}

	public void registraPunto(int i, int altura){
		this.controlador.registraPunto(i,altura);
		this.controlador.sis.printLog("Brazo -> Registrando punto.");
	}

}
