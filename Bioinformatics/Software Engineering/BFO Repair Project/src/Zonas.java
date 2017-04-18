import java.awt.Canvas;
import java.awt.Polygon;

/**
 * Clase definida para encerrar zonas de trabajo
 * @author natsuko
 *
 */
public class Zonas {
	Polygon coordZT;
	Polygon coordEZ;
	SistemaDeControl sis;
	public Canvas canvas;
	
	public Zonas(SistemaDeControl sis){
		sis.printLog("Zonas -> Iniciando zonas");
		this.sis = sis;
		this.coordZT = new Polygon();
		this.coordEZ = new Polygon();
	}
	
	public void guardaZonaTrabajo(Polygon zone){
		this.coordZT = zone;
		this.sis.printLog("Zona de trabajo guardada en memoria.");
	}

	public void guardaZonaExclusion(Polygon zone){
		this.coordEZ = zone;
		this.sis.printLog("Zona de exclusion guardada en memoria.");
	}

	public int validarZonas(){
		if((this.coordZT == null)||(this.coordEZ == null)){
			if(this.coordZT == null){
				this.sis.printLog("Validando Zona trabajo: zona sin configurar.");
				return 2;
			}
			if(this.coordEZ == null){
				this.sis.printLog("Validando Zona Exclusion: zona sin configurar.");
				return 1;
			}
		}
		this.sis.printLog("Validando Zonas: Zona trabajo y Zona exclcusion configuradas.");
		return 0;
	}

	public void setCanvas(Canvas canvas) {
		this.setCanvas(canvas);
	}
	
	public void borrarZT(){
		this.coordZT = new Polygon();
	}
	
	public void borrarEZ(){
		this.coordEZ = new Polygon();
	}
}
