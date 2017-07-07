/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.subPanels.subPanelExAlumnos;

import com.dataPack.Message;
import com.toedter.calendar.JCalendar;
import desktop.panels.PanelBorradores;
import desktop.tools.*;
import java.io.File;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ve´ronica
 */
public class PanelOfertaPostGrado extends ReferencePanel
{
    private JTextField planPostGrado,institucionOfertante;
    private JComboBox<String> tipoPostGrado;
    private PanelFechaPostulacion panelPos;
    private JTextArea requisitos,observaciones;
    private PanelArchivo archivo;
    private boolean isDraftMessage;

    public PanelOfertaPostGrado() 
    {
        super(ReferencePanel.TIPO1); 
        this.createObjects();
        this.isDraftMessage = false;
    }

    public PanelOfertaPostGrado(String planPostGrado, String institucionOfertante,Date di, Date df, String nombreArchivo, File archivo,String requisitos, String observaciones) 
    {
        super(ReferencePanel.TIPO1); 
        this.createObjects();
        this.planPostGrado.setText(planPostGrado);
        this.institucionOfertante.setText(institucionOfertante);
        this.panelPos.setDateIniPos(di);
        this.panelPos.setDateIniPos(df);
        this.archivo.setNameArchivo(nombreArchivo);
        this.archivo.setArchivo(archivo);
        this.requisitos.setText(requisitos);
        this.observaciones.setText(observaciones);
        this.isDraftMessage = true;
    }
    
    

    private void createObjects() 
    {
        Cadenas cadenaOfertaPostGrado = new Cadenas();
        
        this.planPostGrado = new JTextField(30);
        this.tipoPostGrado = new JComboBox<String>(cadenaOfertaPostGrado.getTipoPostGrado());
        this.panelPos = new PanelFechaPostulacion();
        this.institucionOfertante = new JTextField(30);
        this.requisitos = new JTextArea(10,20);
        this.archivo = new PanelArchivo();
        this.observaciones = new JTextArea(10,20);
        
        this.addElementsWithoutScroll(this.planPostGrado, "Nombre plan post-grado", "grow");
        this.addElementsWithoutScroll(this.tipoPostGrado, "tipo de post-grado", "grow,wrap");
        this.addElementsWithoutScroll(this.institucionOfertante, "Nombre de la institución ofertante", "grow,wrap");
        this.addElementsWithoutScroll(this.panelPos, "Fechas de postulación", "grow");
        this.addElementsWithoutScroll(this.archivo, "Subir Archivo", "grow,wrap");
        this.addElementsWithScroll(this.requisitos, "Requisitos", "grow");
        this.addElementsWithScroll(this.observaciones, "Observaciones", "grow,wrap");
    }
    
    private boolean isEmptyFields()
    {
        return (this.planPostGrado.getText().equals("") || this.institucionOfertante.getText().equals("") 
                || this.requisitos.getText().equals("") || this.observaciones.getText().equals(""));
    }

    @Override
    protected void button1Behiavor() 
    {
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de limpiar los campos?");
        if(seleccion == JOptionPane.OK_OPTION)
        {
            this.planPostGrado.setText("");
            this.tipoPostGrado.setSelectedIndex(0);
            this.institucionOfertante.setText("");
            this.panelPos.cleanDates();
            this.archivo.cleanArchivo();
            this.requisitos.setText("");
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
                if(archivo.testFile())
                {
                    if(this.panelPos.validateIniDateAndFinDate())
                    {
                        JCalendar c = new JCalendar();
                        int dia = c.getDate().getDate();
                        int mes = c.getDate().getMonth()+1;
                        int año = c.getDate().getYear()+1900;
                        Message m = new Message(0, com.message.Message.OFERTA_POSTGRADO, dia, mes, año);
                        m.add("Nombre Plan Post-Grado", this.planPostGrado.getText());
                        m.add("Tipo Del Post-Grado", this.tipoPostGrado.getSelectedItem().toString());
                        m.add("Nombre De La Institución Ofertante", this.institucionOfertante.getText());
                        m.add("Fecha Inicio Postulaciones", this.panelPos.getDateIniPosToString());
                        m.add("Fecha Fin Postulaciones", this.panelPos.getDateFinPosToString());
                        m.add("Archivo Adjunto", this.archivo.getNameArchivo());
                        m.add("Requisitos", this.requisitos.getText());
                        m.add("Observaciones", this.observaciones.getText());
                        GsonSerializer.addDraftNotice(m);
                        JOptionPane.showMessageDialog(this, "Borrador guardado con éxito!!.", "Borrador guardado", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Elija una fecha correcta.", "Error", JOptionPane.ERROR_MESSAGE);
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
        }catch(Exception e)
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
                    if(this.panelPos.validateIniDateAndFinDate())
                    {
                        JCalendar c = new JCalendar();
                        int dia = c.getDate().getDate();
                        int mes = c.getDate().getMonth()+1;
                        int año = c.getDate().getYear()+1900;
                        Message m = new Message(0, com.message.Message.OFERTA_POSTGRADO, dia, mes, año);
                        m.add("Nombre Plan Post-Grado", this.planPostGrado.getText());
                        m.add("Tipo Del Post-Grado", this.tipoPostGrado.getSelectedItem().toString());
                        m.add("Nombre De La Institución Ofertante", this.institucionOfertante.getText());
                        m.add("Fecha Inicio Postulaciones", this.panelPos.getDateIniPosToString());
                        m.add("Fecha Fin Postulaciones", this.panelPos.getDateFinPosToString());
                        m.add("Archivo Adjunto", this.archivo.getNameArchivo());
                        m.add("Requisitos", this.requisitos.getText());
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
                    JOptionPane.showMessageDialog(null, "No existe el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
