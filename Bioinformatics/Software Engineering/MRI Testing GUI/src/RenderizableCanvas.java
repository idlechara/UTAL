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
	}
}
