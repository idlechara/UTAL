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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ve´ronica
 */
public class PanelSuspension extends ReferencePanel
{
    private JComboBox<String> modulo,tipoSuspension;
    private JTextField profesor;
    private PanelPlanificacion panelSuspension;
    private JTextArea observaciones;
    private boolean isDraftMessage;

    public PanelSuspension() 
    {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.isDraftMessage = false;
    }

    public PanelSuspension(String profesor, String observaciones) {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.profesor.setText(profesor);
        this.observaciones.setText(observaciones);
        this.isDraftMessage = true;
    }
    
    private boolean isEmptyFields()
    {
        return(this.profesor.getText().equals("") || this.observaciones.getText().equals("") || this.panelSuspension.getListData().isEmpty());
    }
    
    
    private void createObjects()
    {
        Cadenas cadenaSuspension = new Cadenas();
        
        this.modulo = new JComboBox<String>(cadenaSuspension.getModulos());
        this.profesor = new JTextField(30);
        this.tipoSuspension = new JComboBox<String>(cadenaSuspension.getTipoSuspension());
        this.panelSuspension = new PanelPlanificacion();
        this.observaciones = new JTextArea(10,20);
        
        this.addElementsWithoutScroll(this.modulo, "Nombre y sección del módulo", "grow");
        this.addElementsWithoutScroll(this.profesor, "Profesor del módulo", "grow,wrap");
        this.addElementsWithoutScroll(this.tipoSuspension, "Tipo de suspensión", "grow,wrap");
        this.addElementsWithoutScroll(this.panelSuspension, "Suspensión", "grow");
        this.addElementsWithScroll(this.observaciones, "Observaciones", "grow");
    }

    @Override
    protected void button1Behiavor() 
    {
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de limpiar los campos?");
        if(seleccion == JOptionPane.OK_OPTION)
        {
            this.modulo.setSelectedIndex(0);
            this.profesor.setText("");
            this.tipoSuspension.setSelectedIndex(0);
            this.observaciones.setText("");
        }
    }

    @Override
    protected void button2Behiavor() 
    {
        try
        {
            if(!this.isEmptyFields())
            {
                JCalendar cr = new JCalendar();
                if(this.panelSuspension.getFecha().after(cr.getDate()))
                {
                    JCalendar c = new JCalendar();
                    int dia = c.getDate().getDate();
                    int mes = c.getDate().getMonth()+1;
                    int año = c.getDate().getYear()+1900;
                    Message m = new Message(0,com.message.Message.SUSPENSION_CLASES,dia,mes,año);
                    m.add("Nombre Y Sección Del Módulo", this.modulo.getSelectedItem().toString());
                    m.add("Profesor Del Módulo", this.profesor.getText());
                    m.add("Tipo De Suspensión", this.tipoSuspension.getSelectedItem().toString());
                    m.add("Fecha De La Suspensión", this.panelSuspension.getFechaToString());
                    String s = new String();
                    for(int i = 0;i<this.panelSuspension.getListData().size();i++)
                    {
                        s += this.panelSuspension.getListData().get(i);
                    }
                    m.add("Sala(s) Y Bloque(s) De La Suspensión",s);
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
                JCalendar cr = new JCalendar();
                if(this.panelSuspension.getFecha().after(cr.getDate()))
                {
                    JCalendar c = new JCalendar();
                    int dia = c.getDate().getDate();
                    int mes = c.getDate().getMonth()+1;
                    int año = c.getDate().getYear()+1900;
                    Message m = new Message(0,com.message.Message.SUSPENSION_CLASES,dia,mes,año);
                    m.add("Nombre Y Sección Del Módulo", this.modulo.getSelectedItem().toString());
                    m.add("Profesor Del Módulo", this.profesor.getText());
                    m.add("Tipo De Suspensión", this.tipoSuspension.getSelectedItem().toString());
                    m.add("Fecha De La Suspensión", this.panelSuspension.getFechaToString());
                    String s = new String();
                    for(int i = 0;i<this.panelSuspension.getListData().size();i++)
                    {
                        s += this.panelSuspension.getListData().get(i);
                    }
                    m.add("Sala(s) Y Bloque(s) De La Suspensión",s);                    
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
