/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.subPanels.subPanelAlumnos;

import com.dataPack.Message;
import com.toedter.calendar.JCalendar;
import desktop.panels.PanelBorradores;
import desktop.tools.Cadenas;
import desktop.tools.GsonSerializer;
import desktop.tools.ReferencePanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ve´ronica
 */
public class PanelAyudantes extends ReferencePanel
{
    
    private JComboBox<String> modulo,horas;
    private JTextField profesor;
    private JTextArea requisitos,observaciones;
    private boolean isDraftMessage;
    
    
    
    public PanelAyudantes() 
    {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.isDraftMessage = false;
    }

    public PanelAyudantes(String profesor,String requisitos,String observaciones) {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.profesor.setText(profesor);
        this.requisitos.setText(requisitos);
        this.observaciones.setText(observaciones);
        this.isDraftMessage = true;
    }

    private void createObjects() 
    {
        Cadenas cadenaAyudantes = new Cadenas();
        
        this.modulo = new JComboBox<String>(cadenaAyudantes.getModulos());
        this.profesor = new JTextField(25);
        this.horas = new JComboBox<String>(cadenaAyudantes.getCantidadHoras());
        this.requisitos = new JTextArea(10,20);
        this.observaciones = new JTextArea(10,20);
        
        this.addElementsWithoutScroll(this.modulo, "Nombre y sección del módulo", "grow");
        this.addElementsWithoutScroll(this.profesor, "Profesor del módulo", "grow,wrap");
        this.addElementsWithoutScroll(this.horas, "Horas semanales", "grow,wrap");
        this.addElementsWithScroll(this.requisitos, "Requisitos", "grow");
        this.addElementsWithScroll(this.observaciones , "Observaciones", "grow");
    }
    
    private boolean isEmptyFields()
    {
        return (this.profesor.getText().equals("") || this.requisitos.getText().equals("") || this.observaciones.getText().equals(""));
    }

    @Override
    protected void button1Behiavor() 
    {
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de limpiar los campos?");
        if(seleccion == JOptionPane.OK_OPTION)
        {
            this.modulo.setSelectedIndex(0);
            this.profesor.setText("");
            this.horas.setSelectedIndex(0);
            this.requisitos.setText("");
            this.observaciones.setText("");
        }
    }

    @Override
    protected void button2Behiavor() 
    {
        if(!this.isEmptyFields())
        {
            JCalendar c = new JCalendar();
            int dia = c.getDate().getDate();
            int mes = c.getDate().getMonth()+1;
            int año = c.getDate().getYear()+1900;
            Message m = new Message(0, com.message.Message.SOLICITUD_AYUDANTES, dia, mes, año);
            m.add("Nombre Y Sección Del Módulo", this.modulo.getSelectedItem().toString());
            m.add("Profesor Del Módulo", this.profesor.getText());
            m.add("Horas Semanales", this.horas.getSelectedItem().toString());
            m.add("Requisitos", this.requisitos.getText());
            m.add("Observaciones", this.observaciones.getText());
            GsonSerializer.addDraftNotice(m);
            JOptionPane.showMessageDialog(null, "Mensaje guardado en borrador.", "Realizado!!!", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void button3Behiavor() 
    {
        if(!this.isEmptyFields())
        {
            JCalendar c = new JCalendar();
            int dia = c.getDate().getDate();
            int mes = c.getDate().getMonth()+1;
            int año = c.getDate().getYear()+1900;
            Message m = new Message(0, com.message.Message.SOLICITUD_AYUDANTES, dia, mes, año);
            m.add("Nombre Y Sección Del Módulo", this.modulo.getSelectedItem().toString());
            m.add("Profesor Del Módulo", this.profesor.getText());
            m.add("Horas Semanales", this.horas.getSelectedItem().toString());
            m.add("Requisitos", this.requisitos.getText());
            m.add("Observaciones", this.observaciones.getText());
            GsonSerializer.addNoticeToPublishedList(m);
            JOptionPane.showMessageDialog(null, "Mensaje publicado.", "Realizado!!!", JOptionPane.INFORMATION_MESSAGE);
            if(this.isDraftMessage)
            {
                try 
                {
                    PanelBorradores.removePublishedMessage();
                } 
                catch (Exception ex) 
                {
                    Logger.getLogger(PanelAyudantes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
