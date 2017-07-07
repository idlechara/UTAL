/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanatesting;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import sun.util.calendar.CalendarUtils;
import sun.util.calendar.LocalGregorianCalendar.Date;
import sun.util.resources.CalendarData;

/**
 *
 * @author kinya
 */
public class JVentana extends JFrame{

    public JVentana() throws HeadlessException {
        LayoutManager lay = new FlowLayout();
        this.setLayout(lay);
        String[] data= {"uno", "dos", "tres", "cuatro", "cinco", "seis"};
        this.add(new JList(data));
        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        
        ScrollPane sc = new ScrollPane();
        sc.setSize(new Dimension(300, 300));
        sc.add(area, ScrollPane.SCROLLBARS_AS_NEEDED);
        this.add(sc);
        //this.add(field);
        this.pack();
        this.setVisible(true);
    }
    
}
