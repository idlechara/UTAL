import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import org.w3c.dom.css.RGBColor;


public class MotionListener implements MouseMotionListener,MouseListener {

	//componentes de datos
	JLabel rojo,verde,azul;
	Imagen2D imagen;
	Color color;
	Canvas lienzo;
	Canvas lienzoligado;
	
	public MotionListener(JLabel rojo, JLabel verde, JLabel azul, Imagen2D image, Canvas lienzo, Canvas lienzoligado){
		this.rojo = rojo;
		this.verde = verde;
		this.azul = azul;
		this.imagen = image;
		this.lienzo = lienzo;
		this.lienzoligado = lienzoligado;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		//si esta en el rango de la imagen: imprime los valores RGB de esta
		if(e.getX() < this.imagen.getWidth() && e.getY() < this.imagen.getHeight()){
			color = new Color(this.imagen.getRGB(e.getX(), e.getY()));
			this.rojo.setText(Integer.toString(color.getRed()));
			this.verde.setText(Integer.toString(color.getGreen()));
			this.azul.setText(Integer.toString(color.getAlpha()));
		}
		//si esta en el valor de la imagen con margenes de 10: muestra el color promedio
		if(e.getX()+10 < this.imagen.getWidth()-20 && e.getY()+10 < this.imagen.getHeight()-20){
			int r = 0, g = 0, b = 0, a = 0;
			for(int i=e.getX()-10; i< e.getX()+10; i++){
				for(int j=e.getY()-10; j<e.getY()+10; j++){
					this.color = new Color(this.imagen.getRGB(i, j));
					r += this.color.getRed();
					g += this.color.getGreen();
					b += this.color.getBlue();
					a += this.color.getAlpha();
				}
			}
			r/=400;
			g/=400;
			b/=400;
			a/=400;
			this.color = new Color(r, g, b, a);
			System.out.println(color);
			Graphics gra = this.lienzo.getGraphics();
			gra.setColor(color);
			gra.fillRect(0, 0, 30, 30);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("salida" + new Color(imagen.getRGB(e.getX(), e.getY())));
		imagen.colorizar(new Color(imagen.getRGB(e.getX(), e.getY())), Color.GREEN);
		this.lienzoligado.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
