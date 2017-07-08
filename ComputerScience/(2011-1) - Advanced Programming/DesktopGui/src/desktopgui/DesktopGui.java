/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopgui;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JWindow;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Erik Andres Regla Torres
 */
public class DesktopGui {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame asdf = new JFrame("Testing");
        asdf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        asdf.add(new TestingAyudantia());
        asdf.pack();
        asdf.setVisible(true);
    }
}
