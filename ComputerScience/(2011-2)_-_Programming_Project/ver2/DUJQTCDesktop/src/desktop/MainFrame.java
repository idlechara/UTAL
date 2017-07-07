/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import desktop.Tabbs.MainTabbs;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author ve´ronica
 */
public class MainFrame extends JFrame 
{
    private MainTabbs panel1;

    public MainFrame() throws Exception 
    {
        super("DUJQTC - Desktop");
        this.setLayout(new BorderLayout());
        this.setIconImage(new ImageIcon("src/desktop/resources/desktop.png").getImage());
        this.createObjects();
    }
    
    private void createObjects() throws Exception
    {
        this.panel1 = new MainTabbs();
        this.add(this.panel1,BorderLayout.CENTER);
    }
}
