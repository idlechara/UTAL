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
import desktop.tools.PanelPlanificacion;
import desktop.tools.ReferencePanel;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ve´ronica
 */
public class PanelCambioHorario extends ReferencePanel
{
    private JComboBox<String> modulo;
    private JTextField profesor;
    private PanelPlanificacion panelPlanificacionNueva,panelPlanificacionOriginal;
    private JTextArea observaciones;
    private boolean isDraftMessage;
    
    public PanelCambioHorario() 
    {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.isDraftMessage = false;
    }

    public PanelCambioHorario(String profesor,Date fi, Date ff,ArrayList<String> d1,ArrayList<String> d2, String observaciones) 
    {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.profesor.setText(profesor);
        this.panelPlanificacionOriginal.setFecha(fi);
        this.panelPlanificacionNueva.setFecha(ff);
        this.panelPlanificacionOriginal.setData(d1);
        this.panelPlanificacionNueva.setData(d2);
        this.observaciones.setText(observaciones);
        this.isDraftMessage = true;
    }
    
    
    
    private void createObjects()
    {
        Cadenas cadenaCambioHorario = new Cadenas();
        
        
        this.modulo = new JComboBox<String>(cadenaCambioHorario.getModulos());
        this.profesor = new JTextField(25); 
        this.panelPlanificacionOriginal = new PanelPlanificacion();
        this.panelPlanificacionNueva = new PanelPlanificacion();
        this.observaciones = new JTextArea(10,20);
        
        
        this.addElementsWithoutScroll(this.modulo,"Nombre y sección del módulo","grow");
        this.addElementsWithoutScroll(this.profesor,"Profesor del módulo","grow,wrap");
        this.addElementsWithoutScroll(this.panelPlanificacionOriginal, "Planificación original", "grow");
        this.addElementsWithoutScroll(this.panelPlanificacionNueva, "Planificación nueva", "grow,wrap");
        this.addElementsWithScroll(this.observaciones, "Observaciones", "grow");
    }
    
    private boolean isEmptyFields()
    {
        return(this.profesor.getText().equals("") || this.observaciones.getText().equals("") || this.panelPlanificacionOriginal.getListData().isEmpty()
                || this.panelPlanificacionNueva.getListData().isEmpty());
    }
    
    private boolean validateDates()
    {
        JCalendar c = new JCalendar();
        if (this.panelPlanificacionOriginal.getFecha().after(c.getDate()) || this.panelPlanificacionNueva.getFecha().after(c.getDate()))
        {
            if(this.panelPlanificacionNueva.getFecha().after(this.panelPlanificacionOriginal.getFecha()))
            {
                return true;
            }
        }
        return false;

    }

    @Override
    protected void button1Behiavor() 
    {
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de limpiar los campos?");
        if(seleccion == JOptionPane.OK_OPTION)
        {
            this.modulo.setSelectedIndex(0);
            this.profesor.setText("");
            this.observaciones.setText("");
            this.panelPlanificacionOriginal.cleanFields();
            this.panelPlanificacionNueva.cleanFields();
        }
    }

    @Override
    protected void button2Behiavor() 
    {
        try
        {
            if(!this.isEmptyFields())
            {
                if(this.validateDates())
                {
                    JCalendar c = new JCalendar();
                    int dia = c.getDate().getDate();
                    int mes = c.getDate().getMonth()+1;
                    int año = c.getDate().getYear()+1900;
                    Message m = new Message(0,com.message.Message.CAMBIO_HORARIO, dia, mes, año);
                    m.add("Nombre Y Sección Del Módulo", this.modulo.getSelectedItem().toString());
                    m.add("Nombre Del Profesor", this.profesor.getText());
                    m.add("Fecha De La Planificación Original", this.panelPlanificacionOriginal.getFechaToString());
                    String s1 = new String();
                    for(int i = 0;i<this.panelPlanificacionOriginal.getListData().size();i++)
                    {
                        s1 += this.panelPlanificacionOriginal.getListData().get(i);
                    }
                    m.add("Sala(s) Y Bloque(s) De La Planificación Original",s1);
                    m.add("Fecha De La Nueva Planificación ", this.panelPlanificacionNueva.getFechaToString());
                    String s2 = new String();
                    for(int i = 0;i<this.panelPlanificacionNueva.getListData().size();i++)
                    {
                        s2 += this.panelPlanificacionNueva.getListData().get(i);
                    }
                    m.add("Sala(s) Y Bloque(s) De La Nueva Planificación",s2);
                    m.add("Observaciones", this.observaciones.getText());
                    GsonSerializer.addDraftNotice(m);
                    JOptionPane.showMessageDialog(null, "Mensaje guardado en borrador.", "Realizado!!!", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Ingrese una fecha correcta.", "Error al ingresar la fecha", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception ex)
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
                if(this.validateDates())
                {
                    JCalendar c = new JCalendar();
                    int dia = c.getDate().getDate();
                    int mes = c.getDate().getMonth()+1;
                    int año = c.getDate().getYear()+1900;
                    Message m = new Message(0,com.message.Message.CAMBIO_HORARIO, dia, mes, año);
                    m.add("Nombre Y Sección Del Módulo", this.modulo.getSelectedItem().toString());
                    m.add("Nombre Del Profesor", this.profesor.getText());
                    m.add("Fecha De La Planificación Original", this.panelPlanificacionOriginal.getFechaToString());
                    String s1 = new String();
                    for(int i = 0;i<this.panelPlanificacionOriginal.getListData().size();i++)
                    {
                        s1 += this.panelPlanificacionOriginal.getListData().get(i);
                    }
                    m.add("Sala(s) Y Bloque(s) De La Planificación Original",s1);
                    m.add("Fecha De La Nueva Planificación ", this.panelPlanificacionNueva.getFechaToString());
                    String s2 = new String();
                    for(int i = 0;i<this.panelPlanificacionNueva.getListData().size();i++)
                    {
                        s2 += this.panelPlanificacionNueva.getListData().get(i);
                    }
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
                    JOptionPane.showMessageDialog(null, "Ingrese una fecha correcta.", "Error al ingresar la fecha", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 }