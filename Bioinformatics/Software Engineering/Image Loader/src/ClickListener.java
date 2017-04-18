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


public class ClickListener implements MouseMotionListener,MouseListener {

	//componentes de datos
	BufferedImage imagen;
	Color color;
	RenderizableCanvas lienzo;
	
	public ClickListener(BufferedImage image, RenderizableCanvas lienzo){
		this.imagen = image;
		this.lienzo = lienzo;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		Point p = new Point(e.getX(), e.getY());
//		this.lienzo.colorizar(p, new Color(imagen.getRGB(p.x, p.y)), Color.MAGENTA);
//		this.lienzo.repaint();
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
