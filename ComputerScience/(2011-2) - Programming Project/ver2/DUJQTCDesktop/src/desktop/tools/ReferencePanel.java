/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Camilo Andres Gálvez Vidal
 */
public abstract class ReferencePanel extends JPanel {

    private JButton limpar, guardarBorrador, publicar;
    public static final int TIPO1 = 0, TIPO2 = 1;
    private JPanel buttonPanel2;
    private JPanel mainPanel;

    public ReferencePanel(int type) {
        this.setLayout(new BorderLayout());
        this.mainPanel = new JPanel(new MigLayout("", "[grow,fill]", ""));
        
        JPanel buttonPanel = new JPanel(new GridLayout(0, 3, 5, 5));
        
        this.buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        this.limpar = new JButton("Limpiar");
        this.publicar = new JButton("Publicar");
        if (type == TIPO1) {
            this.guardarBorrador = new JButton("Guardar borrador");
        }
        if (type == TIPO2) {
            this.guardarBorrador = new JButton("Cargar");
        }
        
        buttonPanel.add(this.limpar);
        buttonPanel.add(this.guardarBorrador);
        buttonPanel.add(this.publicar);
        
        
        this.buttonPanel2.add(buttonPanel);
        
       
        this.add(this.mainPanel,BorderLayout.CENTER);
   
        
        
        this.limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1Behiavor();
            }
        });
        
        this.guardarBorrador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button2Behiavor();
            }
        });
        
        this.publicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button3Behiavor();
            }
        });
        
    }
    
    public void activeSouthPanel(boolean b)
    {
        if(b)
        {
            this.add(this.buttonPanel2,BorderLayout.SOUTH);
        }
    }

    public void addElementsWithScroll(JComponent target, String text, String constraints) {
        this.mainPanel.add(this.initializeJpanel(text, new JScrollPane(target, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER)), constraints);
    }

    public void addElementsWithoutScroll(JComponent target, String text, String constraints) {
        this.mainPanel.add(this.initializeJpanel(text, target), constraints);
    }

    private JPanel initializeJpanel(String title, JComponent component) {
        
            JPanel referencia = new JPanel();
            referencia.setLayout(new MigLayout("", "[grow, fill]", "[grow, fill]"));
            referencia.setMinimumSize(new Dimension(300, 30));
            referencia.setBorder(BorderFactory.createTitledBorder(title));
            if (component != null) {
                referencia.add(component);
            }
            return referencia;
    }
    
    public JPanel getButtonPanel()
    {
        this.buttonPanel2.setBorder(BorderFactory.createRaisedBevelBorder());
        return this.buttonPanel2;
    }
    
    protected abstract void button1Behiavor();
    protected abstract void button2Behiavor();
    protected abstract void button3Behiavor();
}
