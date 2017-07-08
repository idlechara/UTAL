/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.subPanels.subPanelsCalendarios;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import desktop.tools.CalendarCachedMessage;
import desktop.tools.GsonSerializer;
import desktop.tools.PanelArchivo;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author ve´ronica
 */
public class FrameNuevoEvento extends JFrame implements ActionListener
{
    private int index = -1;
    private JTextField nombreEvento;
    private JTextField topico;
    private JDateChooser fecha;
    private JTextField lugar;
    private JTextField horario;
    private PanelArchivo archivo;
    private JTextArea observaciones;
    private JPanel panelBotones;
    private JButton publicarEvento;
    private JButton limpiarEvento;
    private JButton guardarEvento;
    private JButton cancelarNuevoEvento;

    public FrameNuevoEvento() 
    {
        super("Nuevo Evento");
        this.setLayout(new MigLayout("", "[grow,fill]", ""));
        this.createObjects();
    }

    
    
    private void createObjects() 
    {   
        this.nombreEvento = new JTextField(30);
        this.topico = new JTextField(30);
        this.fecha = new JDateChooser();
        this.fecha.setIcon(new ImageIcon ( "src/desktop/resources/calendario.png" ));
        this.lugar = new JTextField(30);
        this.horario = new JTextField(30);
        this.archivo = new PanelArchivo();
        this.observaciones = new JTextArea(6,15);
        
        this.panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.publicarEvento = new JButton("Publicar");   
        this.guardarEvento = new JButton("Guardar");
        this.limpiarEvento = new JButton("Limpiar");
        this.publicarEvento.addActionListener(this); this.guardarEvento.addActionListener(this);
        this.limpiarEvento.addActionListener(this);
        this.panelBotones.add(this.publicarEvento); this.panelBotones.add(this.guardarEvento); 
        this.panelBotones.add(this.limpiarEvento); 
        
        this.addElementsWithoutScroll(this.nombreEvento, "Nombre del evento", "grow");
        this.addElementsWithoutScroll(this.topico, "Tópico", "grow,wrap");
        this.addElementsWithoutScroll(this.fecha, "Fecha del evento", "grow");
        this.addElementsWithoutScroll(this.lugar, "Lugar del evento", "grow,wrap");
        this.addElementsWithoutScroll(this.horario, "Horario",null);
        this.addElementsWithoutScroll(this.archivo, "Subir Archivo", "wrap");
        this.addElementsWithScroll(this.observaciones, "Observaciones", "grow,span");
        this.add(this.panelBotones,"south");
    }
    
    
    private void addElementsWithScroll(JComponent target, String text, String constraints) {
        this.add(this.initializeJpanel(text, new JScrollPane(target, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER)), constraints);
    }

    private void addElementsWithoutScroll(JComponent target, String text, String constraints) {
        this.add(this.initializeJpanel(text, target), constraints);
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
    
    private boolean isEmptyFields()
    {
        return(this.nombreEvento.getText().equals("") || this.topico.getText().equals("") || this.lugar.getText().equals("") 
                || this.horario.getText().equals("") || this.observaciones.getText().equals(""));
    }

    private boolean validateDate()
    {
        JCalendar c = new JCalendar();
        if(this.fecha.getDate().after(c.getDate()))
        {
            return true;
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.publicarEvento)
        {
            try 
            {
                if(!this.isEmptyFields() && testFile())
                {
                    if(this.validateDate())
                    {
                        CalendarCachedMessage sto = new CalendarCachedMessage(nombreEvento, topico, fecha, lugar, horario, archivo, observaciones);
                        sto.published = true;
                        if(this.index!=-1)
                        GsonSerializer.remplaceDraftCalendarNotice(sto,index);
                        else GsonSerializer.addDraftCalendarNotice(sto);
                        GsonSerializer.addNoticeToPublishedList(GsonSerializer.addPublishedCalendarNotice(sto));
                        this.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Evento publicado.", "Realizado!!!", JOptionPane.INFORMATION_MESSAGE);
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
        
        if(e.getSource() == this.guardarEvento)
        {
            try 
            {
                if(!this.isEmptyFields() && testFile())
                {
                    if(this.validateDate())
                    {
                        
                        CalendarCachedMessage sto = new CalendarCachedMessage(nombreEvento, topico, fecha, lugar, horario, archivo, observaciones);
                        if(this.index!=-1)
                        GsonSerializer.remplaceDraftCalendarNotice(sto,index);
                        else GsonSerializer.addDraftCalendarNotice(sto);
                        this.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Evento guardado en borradores.", "Realizado!!!", JOptionPane.INFORMATION_MESSAGE);
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
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(e.getSource() == this.limpiarEvento)
        {
            int seleccion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de limpiar los campos?");
            if(seleccion == JOptionPane.OK_OPTION)
            {
                this.fecha.setDate(null);
                this.horario.setText("");
                this.lugar.setText("");
                this.nombreEvento.setText("");
                this.observaciones.setText("");
                this.topico.setText("");
            }
        }
    }

    public FrameNuevoEvento(CalendarCachedMessage cal){
        super("Nuevo Evento");
        this.setLayout(new MigLayout("", "[grow,fill]", ""));
        this.createObjects();
        cal.rebuildCalendarCachedMessage(nombreEvento, topico, fecha, lugar, horario, this.archivo, observaciones);
        this.index = cal.index;
        if(cal.published){
            this.publicarEvento.setEnabled(false);
            this.limpiarEvento.setEnabled(false);
            this.guardarEvento.setEnabled(false);
        }
    }
    
    public boolean testFile(){  
        try {
            FileReader fr = new FileReader(archivo.getDirectoryNameArchivo());
        } catch (FileNotFoundException ex) {
            archivo.cleanArchivo();
            return false;      
        }
        return true;
               
    }
    
}
