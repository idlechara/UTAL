import java.awt.Canvas;
import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;


public class SistemaDeControl {
	PrintWriter log;
	Zonas zonas;
	Interfaz interfaz;
	Controlador controlador;
	Almacenamiento almacenamiento;
	Point puntoInicial;
	
	public SistemaDeControl(){
		try {
			log = new PrintWriter(new FileWriter("system.log"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Inicializacion de Archivo .log Fallada.\n" +
					"Ejecucion Terminada");
			System.exit(1);
		}
		almacenamiento = new Almacenamiento();
		puntoInicial = new Point(-10,-10);
		zonas = new Zonas(this);
		interfaz = new Interfaz(this);
		controlador = new Controlador(this, this.almacenamiento);
	}
	
	public void printLog(String string){
		this.log.println(Calendar.DAY_OF_MONTH + "/"+ Calendar.MONTH + "/" + Calendar.YEAR +
				" "+Calendar.HOUR+":"+Calendar.MINUTE + ":" + Calendar.SECOND + "; -> " + string);
		System.out.println(Calendar.DAY_OF_MONTH + "/"+ Calendar.MONTH + "/" + Calendar.YEAR +
				" "+Calendar.HOUR+":"+Calendar.MINUTE + ":" + Calendar.SECOND + "; -> " + string);
	}
	
	public void showError(String string){
		this.interfaz.showErrorMessage(string);
	}

	public void tipoReparacion(boolean b){
		this.controlador.setExcludeMotion(b);
	}
	
	public void setearZona(Canvas canvas){
		this.zonas.setCanvas(canvas);
		this.printLog("Canvas seteado");
	}
}
