import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Paint;
import java.awt.Point;

import javax.swing.JComponent;

/**
 * Ok, ok... no es wea mia, pero es que tenemos una aplicacion "especial",
 * por ende nececitamos algo "mas especial" para poder utilizarlo.
 * 
 * Esta es mi version propia de mi canvas, el cual tiene metodos para poder
 * mostrar imagenes con simples llamadas a funciones, de modo, que la parte de 
 * la interfaz grafica sea solo colocar el canvas e intereactuar con el usuario
 * 
 * @author natsuko
 *
 */
public class RenderingCanvas extends Canvas {
	
	//Miembros de datos
	private Imagen2D toRender = null;	//la imagen a mostrar, si es q esta presente
	private Graphics g = null;			//elemento grafico
	private String coordX = "X";		//indicador de la coordenada
	private String coordY = "Y";		//indicador de la coordenada
	private Point current = null; 		//indicador de la posicion
	
	public RenderingCanvas() {
		super();
		this.g = super.getGraphics();
	}
	
	public RenderingCanvas(GraphicsConfiguration config) {
		super(config);
	}
	
	/**
	 * carga una imagen al canvas, y luego se actualiza.
	 * @param source la imagen a cargar al canvas
	 */
	public void setImage(Imagen2D source){
		this.toRender = source;
		this.repaint();
	}
	
	/**
	 * aveces nececitaremos limpiar la imagen..XD
	 */
	public void clearImage(){
		this.g.setColor(Color.white);
		this.g.clearRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	/**
	 * aca es la cosa, aca se sobrelleva el paint, para poder hacer q el canvas imprima
	 * nuestras imagenes sin mayor dificultad! xD
	 */
	public void paint(Graphics g){
		super.paint(g);
		//pinta la imagen
		this.g.drawImage(toRender, 0, 0, null);
		
		//ahora pinta una flechita la que indica el sentido del vector
		g.setColor(Color.WHITE);
		g.drawLine(10, 10, 10, 100);
		g.drawLine(7, 100, 10, 110);
		g.drawLine(13, 100, 10, 110);
		g.drawLine(7, 100, 13, 100);
		g.drawString(this.coordX, 7, 123);
		
		//otra mas.. XD
		g.drawLine(10, 10, 100, 10);
		g.drawLine(100, 7, 100, 13);
		g.drawLine(110, 10, 100, 7);
		g.drawLine(110, 10, 100, 13);
		g.drawString(this.coordY, 115, 14);
		
		//ahora imprime las coordenadas dadas al canvas. para este tipo de cosas, es necesario proveerselas con
		//un listener adecuado.
		g.drawString("Current Coordinates: (" +this.current.x + "," + this.current.y + ")", 
				this.toRender.getWidth()-150, 
				this.toRender.getHeight()-30);
	}
}
