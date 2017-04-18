/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.subPanels.subPanelExAlumnos;

import com.dataPack.Message;
import com.toedter.calendar.JCalendar;
import desktop.panels.PanelBorradores;
import desktop.tools.GsonSerializer;
import desktop.tools.PanelArchivo;
import desktop.tools.ReferencePanel;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ve´ronica
 */
public class PanelAvisoGeneralExAlumnos extends ReferencePanel 
{
    private JTextField titulo;
    private PanelArchivo archivo;
    private JTextArea observaciones;
    private boolean isDraftMessage;

    public PanelAvisoGeneralExAlumnos() 
    {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.isDraftMessage = false;
    }

    public PanelAvisoGeneralExAlumnos(String titulo,String nombreArchivo,File archivo, String observaciones) {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.titulo.setText(titulo);
        this.archivo.setNameArchivo(nombreArchivo);
        this.archivo.setArchivo(archivo);
        this.observaciones.setText(observaciones);
        this.isDraftMessage = true;
    }

    private void createObjects() 
    {
        this.titulo = new JTextField();
        this.archivo = new PanelArchivo();
        this.observaciones = new JTextArea(10,20);
        
        this.addElementsWithoutScroll(this.titulo, "Titulo", "grow,wrap");
        this.addElementsWithoutScroll(this.archivo, "Subir Archivo", "grow,wrap");
        this.addElementsWithScroll(this.observaciones, "Observaciones", "grow,wrap");
        
    }
    
    private boolean isEmptyFields()
    {
        return(this.titulo.getText().equals("") || this.observaciones.getText().equals(""));
    }
    

    @Override
    protected void button1Behiavor() 
    {
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de limpiar los campos?");
        if(seleccion == JOptionPane.OK_OPTION)
        {
            this.titulo.setText("");
            this.archivo.cleanArchivo();
            this.observaciones.setText("");
        }
    }

    @Override
    protected void button2Behiavor() 
    {
        try{
            if(!this.isEmptyFields())
            {
                if(archivo.testFile())
                {
                    JCalendar c = new JCalendar();
                    int dia = c.getDate().getDate();
                    int mes = c.getDate().getMonth()+1;
                    int año = c.getDate().getYear()+1900;
                    Message m = new Message(0,com.message.Message.AVISOS_GENERALES, dia, mes, año);
                    m.add("Título", this.titulo.getText());
                    m.add("Archivo Adjunto", this.archivo.getNameArchivo());
                    m.add("Observaciones", this.observaciones.getText());
                    GsonSerializer.addDraftNotice(m);
                    JOptionPane.showMessageDialog(this, "Borrador guardado con éxito!!.", "Borrador guardado", JOptionPane.INFORMATION_MESSAGE);
                 }
                else
                {
                    JOptionPane.showMessageDialog(null, "No existe el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }   
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void button3Behiavor() 
    {
        try
        {
            if(!this.isEmptyFields())
            {
                if(archivo.testFile())
                {
                    JCalendar c = new JCalendar();
                    int dia = c.getDate().getDate();
                    int mes = c.getDate().getMonth()+1;
                    int año = c.getDate().getYear()+1900;
                    Message m = new Message(0, com.message.Message.AVISOS_GENERALES, dia, mes, año);
                    m.add("Título", this.titulo.getText());
                    m.add("Archivo Adjunto", this.archivo.getNameArchivo());
                    m.add("Observaciones", this.observaciones.getText());
                    GsonSerializer.addNoticeToPublishedList(m);
                    JOptionPane.showMessageDialog(null, "Mensaje publicado.", "Realizado!!!", JOptionPane.INFORMATION_MESSAGE);
                    if(this.isDraftMessage)
                    {
                        PanelBorradores.removePublishedMessage();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No existe el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
