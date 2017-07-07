/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imgageviewer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author kynku
 */
public class ChallengePanel extends JPanel{
    private BufferedImage image;
    
    @Override
    public void paint( Graphics g )
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        //http://download.oracle.com/javase/tutorial/2d/advanced/quality.html
        RenderingHints rh = new RenderingHints( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED );
	g2d.setRenderingHints(rh);
        rh = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2d.setRenderingHints(rh);
        
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
        if(this.image != null){
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
    
    public void setImage(BufferedImage image){
        this.image = image;
        this.repaint();
    }
}
