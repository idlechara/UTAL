package nekow;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import javax.swing.*;

public class Vigilante implements KeyListener{
	volatile TextArea text;
	volatile JFrame frame;
	volatile JLabel nekow;
	
	public Vigilante(TextArea a, JFrame jua, JLabel nekow){
		this.text = a;
		this.frame = jua;
		this.nekow = nekow;
	}
	public void keyTyped(KeyEvent e) {
		this.nekow = new JLabel(this.text.getText());
		this.frame.repaint();
	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
