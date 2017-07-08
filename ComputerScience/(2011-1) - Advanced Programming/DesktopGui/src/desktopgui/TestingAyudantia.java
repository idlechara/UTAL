/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopgui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Erik Andres Regla Torres
 */
public class TestingAyudantia extends ProPanel{

    private JPanel nombreModuloPanel, nombreProfesorEncargadoPanel, numeroHorasOfrecidasPanel, requisitosPostulacionPanel, observacionesPanel;
    private JTextArea observacionesContent, requisitosPostulacionContent;
    private JTextField nombreProfesorEncargadoContent;
    private JComboBox<String> nombreModuloContent, numeroHorasOfrecidas;
    
    public TestingAyudantia() {
        super(ProPanel.TIPO1);
        
        //initialize objects
        this.observacionesContent = new JTextArea(15, 30);
        this.requisitosPostulacionContent = new JTextArea(15, 30);
        this.nombreProfesorEncargadoContent = new JTextField();
        this.nombreModuloContent = new JComboBox<String>(this.retrieveModulos());
        this.numeroHorasOfrecidas = new JComboBox<String>(this.retrieveTime());
        
        this.addElementsWithoutScroll(this.nombreModuloContent, "Modulo", "grow");
        this.addElementsWithoutScroll(this.numeroHorasOfrecidas, "horas ofrecidas", "grow, wrap");
        this.addElementsWithoutScroll(this.nombreProfesorEncargadoContent, "Profesor Encargado", "grow");
        this.addElementsWithScroll(this.requisitosPostulacionContent, "Requisitos", "grow, span 1 2, wrap");
        this.addElementsWithScroll(this.observacionesContent, "Observaciones", "span 1 2, grow");
    }

    @Override
    protected void button1Behiavor() {
        this.nombreProfesorEncargadoContent.setText("");
        this.requisitosPostulacionContent.setText("");
        this.observacionesContent.setText("");
    }

    @Override
    protected void button2Behiavor() {
        try {
            Message asdf = new Message(MessageType.STUDENT_WORK);
            try {
                asdf.saveToPersistence();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TestingAyudantia.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(TestingAyudantia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void button3Behiavor() {
        throw new UnsupportedOperationException("Not supported yet. : boton3");
    }
    
}
