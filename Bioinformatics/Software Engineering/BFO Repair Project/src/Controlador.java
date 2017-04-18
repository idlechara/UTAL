
public class Controlador {

	SistemaDeControl sis;
	Brazo brazo;
	Almacenamiento alamacenamiento;
	boolean exclude;
	public Controlador(SistemaDeControl sis, Almacenamiento almacenamiento) {
		this.sis = sis;
		this.brazo = new Brazo(this);
		this.alamacenamiento = almacenamiento;
	}

	public void iniciaReparacion(){
		boolean finalizado = false;
		this.sis.printLog("Controlador: Iniciando reparacion");
		int altura = sis.puntoInicial.y;
		this.sis.printLog("Controlador: Entrando al ciclo");
		while(finalizado==false){
			finalizado = brazo.mover(altura);
			altura+=2;
			this.sis.printLog("Controlador: Moviendo cabeza a la siguente posicion.");
		}
		this.sis.printLog("Controlador: Reparacion Finalizada.");
	}
	
	public void setExcludeMotion(boolean exclude){
		this.exclude = exclude;
		this.sis.printLog("Controlador: zona de exclusion: " + this.exclude);
	}

	public void registraPunto(int i, int altura) {
		this.alamacenamiento.registrarTrayectoria(i, altura);
		this.sis.interfaz.repintarLienzo();
	}

}
