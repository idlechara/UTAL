import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImagingOpException;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * clase image space, la cual se encarga de crear espacios de imagenes
 * @author natsuko
 *
 */
public class ImageSpace {

	//miembros de datos
	ImageData[] space = null;

	/**
	 * 
	 * Constructor de clase: construye un espacio de tamaño
	 * inicial 1.
	 * 
	 * @param source el archivo fuente de la imagen
	 * @throws IOException execpcion  a ser capturada, indica que fallo el proseso de apertura
	 */
	public ImageSpace(File source) throws IOException{
		this.space = new ImageData[1];
		this.space[0] = new ImageData(ImageIO.read(source));
	}
	
	/**
	 * 
	 * Constructor de clase: construye un espacio de tamaño
	 * inicial 1.
	 * 
	 * @param source el arreglo de archivos a cargar
	 * @throws IOException execpcion  a ser capturada, indica que fallo el proseso de apertura
	 */
	public ImageSpace(File[] source) throws IOException{
		this.space = new ImageData[source.length];
		for(int i=0; i<source.length;i++){
			this.space[i] = new ImageData(ImageIO.read(source[i]));
		}
	}
	
	/**
	 * clase privada ImageData. la cual es una instancia de una imagen,
	 * pero, la cual nos da las herramientas internas nesesarias para su manipulacion
	 * @author natsuko
	 *
	 */
	public class ImageData extends BufferedImage{
		//miembros de datos
		
		Graphics g = null;
		
		public ImageData(ColorModel cm, WritableRaster raster,
				boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
			super(cm, raster, isRasterPremultiplied, properties);
		}

		public ImageData(int width, int height, int imageType,
				IndexColorModel cm) {
			super(width, height, imageType, cm);
		}

		public ImageData(int width, int height, int imageType) {
			super(width, height, imageType);
		}
		
		public ImageData(BufferedImage image){
			super(image.getWidth(), image.getHeight(), image.getType());
			super.setData(image.getRaster());
		}
		/**
		 * metodo para comprobar y inicializar graficos
		 */
		public void setGraphics(){
			if(this.g==null)
			this.g = super.getGraphics();
		}
		
		/**
		 * borra la imagen contenida actualmente
		 */
		public void erase(){
			this.setGraphics();
			this.g.setColor(Color.WHITE);
			this.g.clearRect(0, 0, super.getWidth(), super.getHeight());
		}
		
		/**
		 * retorna una imagen pixelada, basada en la imagen actual
		 * 
		 * opera bajo la formula:
		 * 
		 * 	GL = Sum(colores)/numero_de_pixeles
		 * 
		 * @return
		 */
		public BufferedImage pixelate(int detail){
			ImageData retorno = new ImageData(this.getWidth(), this.getHeight(), this.getType());
			Color color = null;
			retorno.erase();
			
			int r=0, g=0 ,b=0 ,a=0, numPixels = detail*detail;

			//iteracion que cambia el cuadradito a pixelar
			for(int x=0; x<retorno.getWidth()-detail; x+=detail){
				for(int y=0; y<retorno.getHeight()-detail; y+=detail){
					//iteracion que toma un cuadrado de tamaño detail
					//y calcula su media de color
					for(int i=0; i< detail; i++){
						for(int j=0; j<detail; j++){
							color = new Color(super.getRGB(i+x, j+y));
							r += color.getRed();
							g += color.getGreen();
							b += color.getBlue();
							a += color.getAlpha();
						}
					}
					r/= numPixels;
					g/= numPixels;
					b/= numPixels;
					a/= numPixels;
					color = new Color(r, g, b);
					retorno.g.setColor(color);
					retorno.g.fillRect(x, y, detail, detail);
					r=0;
					g=0;
					b=0;
					a=0;
				}
			}
			return retorno;
		}
	}
	
}
