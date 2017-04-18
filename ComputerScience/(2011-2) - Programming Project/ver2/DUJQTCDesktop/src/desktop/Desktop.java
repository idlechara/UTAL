/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author ve´ronica
 */
public class Desktop {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        MainFrame frame = new MainFrame();
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setSize(1024,728);
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
