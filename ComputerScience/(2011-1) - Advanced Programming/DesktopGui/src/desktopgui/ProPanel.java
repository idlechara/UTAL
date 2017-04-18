/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopgui;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Erik Andres Regla Torres
 */
public abstract class ProPanel extends JPanel {

    private final String moduleRoute = "d:\\modulos.txt";
    private final String timeRoute = "d:\\horas.txt";
    private JButton clean, save, publish;
    public static final int TIPO1 = 0, TIPO2 = 1;

    public ProPanel(int type) {
        this.setLayout(new MigLayout("", "[grow,fill]", ""));

        JPanel buttonPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        //buttonPanel.setBorder(BorderFactory.createTitledBorder("buttonPanel"));
        
        JPanel buttonPanel2 = new JPanel(new MigLayout("","",""));
        //buttonPanel2.setBorder(BorderFactory.createTitledBorder("buttonPanel2"));
        
        this.clean = new JButton("Limpiar");
        this.publish = new JButton("Publicar");
        if (type == TIPO1) {
            this.save = new JButton("Guardar borrador");
        }
        if (type == TIPO2) {
            this.save = new JButton("Cargar");
        }
        
        buttonPanel.add(this.clean);
        buttonPanel.add(this.save);
        buttonPanel.add(this.publish);
        
        
        buttonPanel2.add(buttonPanel, "align right");
        
        this.add(buttonPanel2, "south");
        
        this.clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1Behiavor();
            }
        });
        
        this.save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2Behiavor();
            }
        });
        
        this.publish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button3Behiavor();
            }
        });
        
    }

    public void addElementsWithScroll(JComponent target, String text, String constraints) {
        this.add(this.initializeJpanel(text, new JScrollPane(target, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER)), constraints);
    }

    public void addElementsWithoutScroll(JComponent target, String text, String constraints) {
        this.add(this.initializeJpanel(text, target), constraints);
    }

    private JPanel initializeJpanel(String title, JComponent component) {
        JPanel target = new JPanel();
        target.setLayout(new MigLayout("", "[grow, fill]", "[grow, fill]"));
        //target.setBackground(Color.WHITE);
        target.setMinimumSize(new Dimension(300, 30));
        //target.setMaximumSize(new Dimension(999,999));
        //target.setLayout(new FlowLayout());
        target.setBorder(BorderFactory.createTitledBorder(title));
        if (component != null) {
            target.add(component);
        }
        return target;
    }

    public String[] retrieveModulos() {
        try {
            String[] salida = new String[1];
            BufferedReader br = new BufferedReader(new FileReader(moduleRoute));
            ArrayList<String> output = new ArrayList<String>();
            String input = br.readLine();
            while (input != null) {
                output.add(input);
                input = br.readLine();
            }

            return output.toArray(salida);
        } catch (Exception ex) {
            Logger.getLogger(Ayudantia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String[] retrieveTime() {
        try {
            String[] salida = new String[1];
            BufferedReader br = new BufferedReader(new FileReader(timeRoute));
            ArrayList<String> output = new ArrayList<String>();
            String input = br.readLine();
            while (input != null) {
                output.add(input);
                input = br.readLine();
            }

            return output.toArray(salida);
        } catch (Exception ex) {
            Logger.getLogger(Ayudantia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    protected abstract void button1Behiavor();
    protected abstract void button2Behiavor();
    protected abstract void button3Behiavor();
}
