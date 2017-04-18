import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class RenderizableCanvas extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6167488256463858097L;
	
	//imagen a trabajar
	BufferedImage imagen = null;
	
	public RenderizableCanvas(BufferedImage imagen){
		super();
		this.imagen = imagen;
	}

	public RenderizableCanvas() {
		super();
		super.setBounds(new Rectangle(10, 10, 640, 480));
	}

	public RenderizableCanvas(GraphicsConfiguration config) {
		super(config);
	}
	
	public void setImage(BufferedImage imagen){
		this.imagen = imagen;
		this.repaint();
	}
	public void paint(Graphics g){
		super.paint(g);
		
		//aca va el codigo personalizado
		
		g.drawImage(this.imagen, 0, 0, null);
		g.setColor(Color.WHITE);
		g.drawLine(10, 10, 10, 100);
		g.drawLine(7, 100, 10, 110);
		g.drawLine(13, 100, 10, 110);
		g.drawLine(7, 100, 13, 100);
		g.drawString("X", 7, 123);
		
		g.drawLine(10, 10, 100, 10);
		g.drawLine(100, 7, 100, 13);
		g.drawLine(110, 10, 100, 7);
		g.drawLine(110, 10, 100, 13);
		g.drawString("Y", 115, 14);
		
	}

	public void colorizar(Point p, Color original, Color ink){
		Color color = new Color(this.imagen.getRGB(p.x, p.y));
		if(color.equals(original)){
			Graphics g = imagen.getGraphics();
			g.setColor(ink);
			g.fillRect(p.x, p.y, 1, 1);
			colorizar(new Point(p.x-1, p.y), original, ink);
			colorizar(new Point(p.x, p.y-1), original, ink);
			colorizar(new Point(p.x+1, p.y), original, ink);
			colorizar(new Point(p.x, p.y+1), original, ink);
		}
		this.repaint();
		
	}
	/*
	
	algo anda mal aca... pero permite seleccionar areas contiguas!!
	public void colorizar(Point p, Color original, Color ink){
		Color color = new Color(this.imagen.getRGB(p.x, p.y));
		if(color.equals(ink)) return;
		if(color.equals(original)){
			Graphics g = imagen.getGraphics();
			g.setColor(ink);
			g.fillRect(p.x, p.y, 1, 1);
			colorizar(new Point(p.x+1, p.y), original, ink);
			colorizar(new Point(p.x, p.y+1), original, ink);
			colorizar(new Point(p.x-1, p.y), original, ink);
			colorizar(new Point(p.x, p.y-1), original, ink);
		}
		this.repaint();
		
	}*/

}
