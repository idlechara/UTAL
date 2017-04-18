import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
/**
 * Clase Imagen 3D, la cual se encarga de almacenar las imagenes en 3d
 * 
 * La idea principal es que una imagen 3d esta compuesta por cuadraditos de una misma imagen
 * entonces, nosotros podemos decomponer la imagen en unos pixeles mas grandes.
 * 
 *  para eso primero hemos de pixelar la imagen, y obtener un arreglo de colores,
 *  los cuales seran traducidos como un espacio de colores, el cual dara forma a
 *  la imagen 3D.
 *  
 *  el metodo medir, no se como cresta funka solo recibiendo un entero... ? alguna idea?
 *  el metodo suavizar cuesta mucho, de por momento no lo rajamos.
 *  el metodo rotar... claro... como se rota?..XD alguien expliqueme.xD
 *  
 *  el metodo slice y visualizar ya estan en un metodo llamado obtieneImagen, el cual corta lo pedido
 *  y retorna la imagen 2D lista para su visualizacion, guardado, renderizado, o lo que 
 *  se te ocurra.XD
 *  
 *  MODIFICACIONES DE ULTIMA HORA!!!
 *  
 *  gracias a samu, que no llevo mejor forma de interrumpir mi comida mas que decirme que cambiara las cosas
 *  las cosas a cambiar seran:
 *  
 *  -Herencia de 2d -> 3d
 *  -visualizar retornara una imagen
 *  
 * @author natsuko
 *
 */
public class Imagen3D extends Imagen2D{

	//Miembros de datos
	private int width = 0;
	private int height = 0;	
	private int imageType = 0;	//tipo de imagen
	int slides = 0;	//numero de imagenes recibidas
	private int detailLevel = 0;	//nivel de detalle de la imagen
	private int metricUnit = 0;	//por implementar
	
	Imagen2D imagenRenderizable = null;
	
	Color[][][] colores= null;	//espacio de colores
	float medidaPorVoxel = 0;	//tamaño de cada voxel
	//estos indican la medida de cada trozo.x:D
	int stepX = 0, stepY = 0;
	
	public boolean isAvaliable(){
		if(colores!=null) return true;
		else return false;
	}
	
	/**
	 * Ahora, todos hemos tenido legos. La idea aca es la misma. Podemos usar una formula de
	 * aproximacion para decidir cual sera el color de la cajita:
	 * 
	 * 		Color_Final = suma(Colores_Adyacentes)/numero de adyacentes;
	 * 
	 * Esto es algo asi como calcular un promedio del color en cada region, basandonos en rgba.
	 * 
	 * Los datos son guardados en un vector tridimensional, el cual se encarga de almacenarlos,
	 * algo asi como un lego que solo tiene las posiciones. Ya que en si no es un dibujo, sino
	 * un espacio de colores.
	 * 
	 * @param source las imagenes a prosesar
	 * @throws ImagingOpException arrojada en caso de que haya una operacion no permitida, o hayan 
	 * muy pocas imagenes para realizar un correcto analisis.
	 */
	public Imagen3D(Imagen2D[] source){
		
		//se crea una instancia de esta imagen
		super(source[0]);
		this.detailLevel = 8;	//ahora, el nivel de detalle es la separacion entre cada imagen
								//en relacion a los pixeles q esta tiene, osea, si 1px, en la imagen real
								//y la imagen esa separada por 8 veces esa cantidad, el nivel es 8

		this.slides = source.length;						//la cantidad de imagenes
		this.width = source[0].getWidth()/detailLevel;		//la longitud del arreglo
		this.height = source[0].getHeight()/detailLevel;	//la longitud del arreglo
		this.imageType = source[0].getType();				//tipo de imagen a usar
		
		this.colores = new Color[this.width][this.height][this.slides];	//entonces creamos el espacio
		
		//y le damos sus caracteristicas propias
		super.fecha = source[0].getFecha();
		this.hora = source[0].getHora();
		this.id = source[0].getId();
		this.organo = getOrgano();
		
		//variables auxiliares para la construccion
		int r=0, g=0 ,b=0 ,a=0, numPixels = detailLevel*detailLevel;
		Color color;
		
		this.stepX = detailLevel;
		this.stepY = detailLevel;
		
		//iteracion que cambia la imagen en curso
		for(int image=0; image<this.slides; image++){
			System.out.println("Imagen en curso: "+ image);
			System.out.println(source[image]);
			//iteracion que cambia el cuadradito a pixelar
				for(int x=0; x<this.width; x++){
					for(int y=0; y<this.height; y++){
						//iteracion que toma un cuadrado de tamaño detail
						//y calcula su media de color
						for(int i=0; i< this.stepX; i++){
							for(int j=0; j<this.stepY; j++){
								color = new Color(source[image].getRGB(i+(x*stepX), j+(y*stepY)));
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
						System.out.println("X: " + x +"\n" +
								"Y: " + y + "\n" +
								"image: " + image);
						this.colores[x][y][image] = color;
						r=0;
						g=0;
						b=0;
						a=0;
					}
				}
		}
	}
	
	/**
	 * Bueno, alguie ha comido torta... no? otra vez ¬.¬"!
	 * 
	 * Ok, nosotros creamos una especie de cubo anteriormente, la cual nos permite
	 * simular una especie de espacio. La cosa es que cuando uno visualiza, uno tiene a
	 * ver de todas formas las cosas en 2D, asi que para ahorrarnos el trabajo,
	 * he creado un metodo el cual obtiene una imagen, desde una perspectiva ya dada, y 
	 * la exporta como una imagen de el mismo tamaño que las originales, para eso
	 * es que nececitaba los datos.
	 * 
	 * En resumen, estamos creando una imagen, a partir de un espacio de colores.
	 * 
	 * 
	 * @param altura altura a la que se realiza el corte/vision
	 * @param vertical indica si es vertical o de eje X
	 * @param type tipo de slice a prosesar, 0 para z (normal), 1 para Y, 2 para x
	 * @param area area de la imagen a recortar
	 * @return la imagen prosesada
	 */
	public Imagen2D slice(int altura, int type, Rectangle area){
		//se crea una imagen de dimensiones menores, pero ajustada al campo
		Imagen2D retorno = new Imagen2D(this.organo, this.hora, this.fecha, 
				new BufferedImage(this.width*detailLevel, this.height*detailLevel, this.imageType));
		//se obtiene la grafica, para limbpiar pantalla y volas
		Graphics g2 = retorno.getGraphics();
		g2.setColor(Color.WHITE);
		g2.clearRect(0, 0, this.width*detailLevel, this.height*detailLevel);
		System.out.println("slides: " +slides);

		//aca el tipo de barrido es determinado por el modo de vision de la imagen
		switch (type) {
		
		//caso 1: vision a travez del eje Z
		case 0:
			//g2.translate(this.height, this.width);
			for(int i=0; i<this.width; i++){
				for(int j=0; j<this.height; j++){
					g2.setColor(this.colores[i][j][altura]);
					g2.fillRect(i, j, 1, 1);
				}	
			}
			break;
		
		//caso 2: vision a travez del eje y
		case 1:
			//g2.translate(this.height, this.width);
			for(int i=0; i<this.width; i++){
				for(int j=0; j<this.slides; j++){
					g2.setColor(this.colores[i][altura][j]);
					g2.fillRect(i, j, 1, 1);
				}	
			}
			break;

		//caso 3: vision a travez del eje z
		case 2:
			//g2.translate(this.height, this.width);
			for(int i=0; i<this.width; i++){
				for(int j=0; j<this.slides; j++){
					g2.setColor(this.colores[altura][i][j]);
					g2.fillRect(i, j, 1, 1);
				}	
			}
			break;
		}

		this.imagenRenderizable=retorno;
		//this.tamaño(this.detailLevel);
		this.tamaño(this.detailLevel);
		//aca indica si es sacado un trozo de imagen
		if(area == null) return this.imagenRenderizable;
		else {
			retorno.setData(retorno.getSubimage(area.x, area.y, area.width, area.height).getRaster());
			return retorno;
		}
	}
	
	/**
	 * @deprecated
	 * jojoi!, la opcion visualizar.
	 * 
	 * se supone que la imagen actual que carga nuestro imagen3d es la ultima slice.
	 * bueno, entonces, tambien podemos mostrar una distinta.
	 * 
	 * cambia el rastrer (mapa de bits) por uno nuevo.
	 * 
	 * METODO INEMPLEMENTABLE
	 */
	public void visualizar(){
		//TODO borrar.
	}
	
	/**
	 * esta funcion multiplica los pixeles, dando una sensacion de zoom
	 * 
	 * @param tamaño el nivel de aumento
	 */
	public void tamaño(int tamaño){
		//espacio temporal donde dejar la imagen
		BufferedImage temp = new BufferedImage(this.imagenRenderizable.getWidth()*(int)tamaño, this.imagenRenderizable.getHeight()*(int)tamaño, this.getType());
		Graphics g2 = temp.getGraphics();
		
		//se procede a multiplicar los pixeles
		for(int i=0; i<this.imagenRenderizable.getWidth(); i++){
			for(int j=0; j<this.imagenRenderizable.getHeight(); j++){
				g2.setColor(new Color(this.imagenRenderizable.getRGB(i, j)));
				g2.fillRect(i*(int)tamaño, j*(int)tamaño, (int)tamaño, (int)tamaño);
			}
		}
		
		//se guarda en la cache de la imagen actualmente renderizable
		this.imagenRenderizable = new Imagen2D(temp);
	}
	
	/**
	 * metodo medir, el cual mide la distancia entre dos puntos, dados una medida
	 * del espacio tridimensional a trabajar.
	 * 
	 * para la correcta implementacion, es nesesario previamente tener correctamente 
	 * configurado el valor del nivel de detalle
	 * 
	 * @param origen punto 1
	 * @param destiny punto 2
	 * @return la distancia entre ambos puntos
	 */
	public float medir(Point origen, Point destiny){
		return detailLevel;
		//TODO completar algoritmo de medicion
	}
	
	/**
	 * W T F !
	 */
	public void rotar(){
		//TODO confirmar algoritmo!
	}


	/**
	 * ajusta el contraste?
	 * 
	 * la idea es multiplicar los colores por un factor determinado.
	 * 
	 * @param nivel el nivel de contraste, este debe de ser un valor positivo
	 */
	@Override
	public void contraste(int nivel){
		this.imagenRenderizable.contraste(nivel);
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
	@Override
	public void colorizar(Color original, Color ink){
		this.imagenRenderizable.colorizar(original, ink);
	}
	
	@Deprecated
	public String getOrgano(){
		return this.organo;
	}

	@Deprecated
	public String getFecha(){
		return this.fecha;
	}

	@Deprecated
	public String getHora(){
		return this.hora;
	}

	@Deprecated
	public String getId(){
		return this.id;
	}
}
