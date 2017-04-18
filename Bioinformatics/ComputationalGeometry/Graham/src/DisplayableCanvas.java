import java.awt.BufferCapabilities;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


@SuppressWarnings("serial")
public class DisplayableCanvas extends Canvas {

	//miembros de datos
	Graphics graficos = this.getGraphics();
	int pointWidth = 0;
	int pointHeight = 0;
	final int MAGNIF = 2;
	final int SIZE = 200+20;
	
	//seteador de canvas
	public void setCanvas() {
		this.setBackground(Color.WHITE);
		this.setBounds(new Rectangle(400*MAGNIF +50,400*MAGNIF+50));
	}
	
	//cargador de graficos
	public void loadGraphics(){
		this.graficos = super.getGraphics();
	}

	//dibuja un punto
	public void drawPoint(PointType point){
		this.graficos.setColor(Color.black);
		this.graficos.fillOval((point.x+SIZE)*MAGNIF, (SIZE-point.y)*MAGNIF, pointWidth, pointHeight);
		this.graficos.drawString(point.toString(), (point.x+SIZE+pointWidth)*MAGNIF,  (SIZE-point.y-pointWidth)*MAGNIF+15);
	}
	
	//dibuja un punto con un color y numero.
	public void drawPoint(PointType point, int number, Color color){
		this.graficos.setColor(color);
		this.graficos.fillOval((point.x+SIZE)*MAGNIF, (SIZE-point.y)*MAGNIF, pointWidth, pointHeight);
		this.graficos.drawString(point.toString(), (point.x+SIZE+pointWidth)*MAGNIF,  (SIZE-point.y-pointWidth)*MAGNIF+15);
	}

	//dibuja puntos
	public void drawPoints(PointType[] points){
		for (PointType pointType : points) {
			this.drawPoint(pointType);
		}
	}

	//dibuja una linea
	public void drawLine(PointType a, PointType b){
			this.graficos.drawLine((SIZE+a.x)*MAGNIF, (SIZE-a.y)*MAGNIF, (SIZE+b.x)*MAGNIF, (SIZE-b.y)*MAGNIF);
	}

	//setea el tama�ode un punto
	public void setPointSize(int size){
			this.pointHeight = size;
			this.pointWidth = size;
	}
	
	//limpia el lienzo
	public void clearCanvas(){
		this.graficos.setColor(Color.WHITE);
		this.graficos.fillRect(0, 0, 400*MAGNIF, 400*MAGNIF);
	}
}
