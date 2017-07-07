/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Erik Andres Regla Torres
 */
public class Ayudantia extends JPanel{
    //contenidos propios de la instancia
    private JPanel nombreModuloPanel, nombreProfesorEncargadoPanel, numeroHorasOfrecidasPanel, requisitosPostulacionPanel, observacionesPanel;
    private JTextArea observacionesContent, requisitosPostulacionContent;
    private JTextField nombreProfesorEncargadoContent;
    private JComboBox<String> nombreModuloContent, numeroHorasOfrecidas;
    private final String moduleRoute= "d:\\modulos.txt";
    private final String timeRoute= "d:\\horas.txt";
    private JScrollPane scrollRequisitos;
    private final JScrollPane scrollObservaciones;
    
    public Ayudantia() {
        //set constraints
        this.setLayout(new MigLayout("","[grow][grow]",""));
        //this.setColoring();
        
        //initialize objects
        this.observacionesContent = new JTextArea(15, 30);
        this.requisitosPostulacionContent = new JTextArea(15, 30);
        this.nombreProfesorEncargadoContent = new JTextField();
        this.nombreModuloContent = new JComboBox<String>(this.retrieveModulos());
        this.numeroHorasOfrecidas = new JComboBox<String>(this.retrieveTime());
        
        
        this.nombreModuloPanel = initializeJpanel("Modulo", this.nombreModuloContent);
        this.add(this.nombreModuloPanel,"grow");
        
        this.numeroHorasOfrecidasPanel = initializeJpanel("Horas ofrecidas", this.numeroHorasOfrecidas);
        this.add(this.numeroHorasOfrecidasPanel,"grow, wrap");
        
        
        this.nombreProfesorEncargadoPanel = initializeJpanel("Profesor a cargo", this.nombreProfesorEncargadoContent);
        this.add(this.nombreProfesorEncargadoPanel,"grow");
        
        
        this.scrollRequisitos = new JScrollPane(this.requisitosPostulacionContent,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.requisitosPostulacionPanel = initializeJpanel("Requisitos postulacion", scrollRequisitos);
        this.add(this.requisitosPostulacionPanel,"grow, span 1 2, wrap");
        
        this.scrollObservaciones = new JScrollPane(this.observacionesContent,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.observacionesPanel = initializeJpanel("Observaciones", this.scrollObservaciones);
        this.add(this.observacionesPanel,"grow, span 1 2");
    }
    
    private JPanel initializeJpanel(String title, JComponent component){
        JPanel target = new JPanel();
        target.setLayout(new MigLayout("","[grow, fill]","[grow, fill]"));
        //target.setBackground(Color.WHITE);
        target.setMinimumSize(new Dimension(300, 30));
        //target.setMaximumSize(new Dimension(999,999));
        //target.setLayout(new FlowLayout());
        target.setBorder(BorderFactory.createTitledBorder(title));
        if(component != null)
        target.add(component);
        return target;
    }
    
    private String[] retrieveModulos(){
        try {
            String [] salida = new String[1];
            BufferedReader br = new BufferedReader(new FileReader(moduleRoute));
            ArrayList<String> output = new ArrayList<String>();
            String input = br.readLine();
            while(input != null){
                output.add(input);
                input = br.readLine();
            }
            
            return output.toArray(salida);
        } catch (Exception ex) {
            Logger.getLogger(Ayudantia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return null;
    }
    
    private String[] retrieveTime(){
        try {
            String [] salida = new String[1];
            BufferedReader br = new BufferedReader(new FileReader(timeRoute));
            ArrayList<String> output = new ArrayList<String>();
            String input = br.readLine();
            while(input != null){
                output.add(input);
                input = br.readLine();
            }
            
            return output.toArray(salida);
        } catch (Exception ex) {
            Logger.getLogger(Ayudantia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return null;
    }
    
    private void setColoring(){
        this.setBackground(Color.WHITE);
    }
}
