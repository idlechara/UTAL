import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;

public class RenderingArea extends Canvas {
	Graphics graph = super.getGraphics();

	public void createRenderingArea() {
		super.setBounds(new Rectangle(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void clearCanvas() {
		this.graph.clearRect(0, 0, 400, 400);
	}
}
