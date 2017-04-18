import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

import javax.imageio.ImageIO;

/**
 * Clase Imagen2D
 * 
 * Descricion:
 * Es nesesaria la implementacion de una clase de imagen en dos dimensiones,
 * para el correcto trazado de un slice.
 * 
 * esta imagen 2D, contiene todos los datos relacionados a un slide,
 * como lo son, el organo al cual se le saco la imagen, su ID, fecha y hora,
 * un filtro de extencion de archivo y la imagen en si.
 * 
 * esta clase hereda de forma directa de un Buffered image, ya que como tal, este ha de ser creado en
 * base a un archivo.
 * 
 * los constructores han sido modificados, de modo que reciba los datos que indica el diagrama de clases.
 * @author natsuko
 *
 */
public class Imagen2D extends BufferedImage{

	//Miembros de datos
	protected String organo = null;	//descricion del organo
	protected String hora = null;		//hora de toma del examen
	protected String fecha = null;	//fecha de hora del examen
	protected String id = null;		//identificador de imagen
	
	protected Graphics g = null;		//graficos de la imagen
	
	/**
	 * Constructor de una imagen2D,el cual recibe la informacion nesesaria para iniciar su almacenamiento
	 * 
	 * Primero inicia la superclase, de modo que el elemento de imagen predefinido, que iniciado,
	 * para asi colocar el mapa de bits en su lugar.
	 * 
	 * Luego se procede a tomar los datos relativos de la imagen.
	 * 
	 * @param organo organo al cual se le tomo la imagen
	 * @param hora hora a la que fue tomada la imagen
	 * @param fecha fecha a la que la imagen fue tomada
	 * @param source BufferedImage la cual contiene el raster con los datos de la misma
	 */
	public Imagen2D(String organo, String hora, String fecha, BufferedImage source) {
		super(source.getWidth(), source.getWidth(), source.getType());
		super.setData(source.getRaster());
		this.g = super.getGraphics();
		this.organo = organo;
		this.hora = hora;
		this.fecha = fecha;
	}

	public Imagen2D(Imagen2D source){
		super(source.getWidth(), source.getWidth(), source.getType());
		super.setData(source.getRaster());
		this.g = super.getGraphics();
		this.organo = source.organo;
		this.hora = source.hora;
		this.fecha = source.fecha;
	}

	public Imagen2D(BufferedImage source){
		super(source.getWidth(), source.getWidth(), source.getType());
		super.setData(source.getRaster());
		this.g = super.getGraphics();
	}
	
	/**
	 * Alguien ha usado el bote de pintura del paint?
	 * 
	 * no?
	 * 
	 * bueno, este metodo se encarga de poder elejir los pixeles de colores iguales
	 * adyacentes a un punto, y los pinta de un color de forma recursiva.
	 * 
	 * nap, la recursion en caso de ser una imagen demasiado grande, no rinde mucho.
	 * asi que mejor la dejo global.
	 * 
	 * en donde sea q encuentre una densidad igual, es por q se marca.XD
	 * 
	 * @param original color original que se busca
	 * @param ink color de la tincion
	 */
	public void colorizar(Color original, Color ink){
		this.g.setColor(ink);
		for(int j=0; j< super.getHeight(); j++){
			for(int i=0; i< super.getWidth(); i++){
				if(original.equals(new Color(super.getRGB(i, j))))
					this.g.fillRect(i, j, 1, 1);
			}
		}
	}
	
	/**
	 * ajusta el contraste?
	 * 
	 * la idea es multiplicar los colores por un factor determinado.
	 * 
	 * @param nivel el nivel de contraste, este debe de ser un valor positivo
	 */
	public void contraste(int nivel){
		//se declaran los temporales nesesarios
		Color contrastado = null;
		int r=0,g=0,b=0;
		
		for(int j=0; j< super.getHeight(); j++){
			for(int i=0; i< super.getWidth(); i++){
				//tomo el color original
				contrastado = new Color(super.getRGB(i, j));
				
				//lo descompongo en rgb
				r = contrastado.getRed() * nivel;
				g = contrastado.getGreen() * nivel;
				b = contrastado.getBlue() * nivel;

				//se verifica
				if(r>255) r = 255;
				if(g>255) g = 255;
				if(b>255) b = 255;
				
				//se pinta
				this.g.setColor(new Color(r,g,b));
				this.g.drawRect(i, j, 1, 1);
			}
		}
	}

	public String getOrgano(){
		return this.organo;
	}
	public String getFecha(){
		return this.fecha;
	}
	public String getHora(){
		return this.hora;
	}
	public String getId(){
		return this.id;
	}
}
