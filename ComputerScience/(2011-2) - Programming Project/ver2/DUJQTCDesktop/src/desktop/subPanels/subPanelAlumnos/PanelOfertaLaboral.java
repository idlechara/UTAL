/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.subPanels.subPanelAlumnos;

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
public class PanelOfertaLaboral extends ReferencePanel
{
    private JTextField tituloTrabajo,profesor,lugarTrabajo,contactoTrabajo,montoTrabajo;
    private JComboBox<String> horasSemanales,duracionMeses;
    private PanelFechaPostulacion panelPos;
    private JTextArea requisitos,observaciones;
    private PanelArchivo archivo;
    private boolean isDraftMessage;

    public PanelOfertaLaboral() 
    {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.isDraftMessage = false;
    }

    public PanelOfertaLaboral(String tituloTrabajo, String profesor, String lugarTrabajo, String contactoTrabajo, String montoTrabajo, Date di, Date df, String nombreArchivo, File archivo, String requisitos, String observaciones) 
    {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.tituloTrabajo.setText(tituloTrabajo);
        this.profesor.setText(profesor);
        this.lugarTrabajo.setText(lugarTrabajo);
        this.contactoTrabajo.setText(contactoTrabajo);
        this.montoTrabajo.setText(montoTrabajo);
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
        Cadenas cadenaOfertaLaboral = new Cadenas();
        
        this.tituloTrabajo = new JTextField(30);
        this.profesor = new JTextField(30);
        this.lugarTrabajo = new JTextField(30);
        this.contactoTrabajo = new JTextField(30);
        this.horasSemanales = new JComboBox<String>(cadenaOfertaLaboral.getCantidadHoras());
        this.duracionMeses = new JComboBox<String>(cadenaOfertaLaboral.getCantidadMeses());
        this.panelPos = new PanelFechaPostulacion();
        this.montoTrabajo = new JTextField(23);
        this.archivo = new PanelArchivo();
        this.requisitos = new JTextArea(10,20);
        this.observaciones = new JTextArea(10,20);
        
        this.addElementsWithoutScroll(this.tituloTrabajo, "Título del trabajo", "grow");
        this.addElementsWithoutScroll(this.profesor, "Profesor encargado", "grow,wrap");
        this.addElementsWithoutScroll(this.lugarTrabajo, "Lugar de trabajo", "grow");
        this.addElementsWithoutScroll(this.contactoTrabajo, "Nombre del contacto", "grow,wrap");
        this.addElementsWithoutScroll(this.horasSemanales, "Horas semanales", "grow");
        this.addElementsWithoutScroll( this.duracionMeses, "Duración del trabajo","grow,wrap");
        this.addElementsWithoutScroll(this.montoTrabajo, "Monto a pagar","grow,wrap");
        this.addElementsWithoutScroll(this.panelPos, "Fechas de postulacion", "grow");
        this.addElementsWithoutScroll(this.archivo, "Subir Archivo", "grow,wrap");
        this.addElementsWithScroll(this.requisitos, "Requisitos", "span 1 2,grow");
        this.addElementsWithScroll(this.observaciones, "Observaciones", "span 1 2,grow");
    }
    
    private boolean isEmptyFields()
    {
        return (this.tituloTrabajo.getText().equals("") || this.profesor.getText().equals("") || this.lugarTrabajo.getText().equals("") || this.contactoTrabajo.getText().equals("") ||
                this.montoTrabajo.getText().equals("") || this.requisitos.getText().equals("") || this.observaciones.getText().equals(""));
    }

    @Override
    protected void button1Behiavor() 
    {
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de limpiar los campos?");
        if(seleccion == JOptionPane.OK_OPTION)
        {
            this.tituloTrabajo.setText("");
            this.profesor.setText("");
            this.lugarTrabajo.setText("");
            this.contactoTrabajo.setText("");
            this.horasSemanales.setSelectedIndex(0);
            this.duracionMeses.setSelectedIndex(0);
            this.montoTrabajo.setText("");
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
                        Message m = new Message(0, com.message.Message.OFERTA_LABORAL_PRE, dia, mes, año);
                        m.add("Título Del Trabajo", this.tituloTrabajo.getText());
                        m.add("Profesor Encargado", this.profesor.getText());
                        m.add("Lugar De Trabajo", this.lugarTrabajo.getText());
                        m.add("Nombre Del Contacto", this.contactoTrabajo.getText());
                        m.add("Horas Semanales", this.horasSemanales.getSelectedItem().toString());
                        m.add("Duración Del Trabajo", this.duracionMeses.getSelectedItem().toString());
                        m.add("Monto A Pagar", this.montoTrabajo.getText());
                        m.add("Fecha Inicio Postulaciones", this.panelPos.getDateIniPosToString());
                        m.add("Fecha Fin Postulaciones", this.panelPos.getDateFinPosToString());
                        m.add("Archivo Adjunto", this.archivo.getNameArchivo());
                        m.add("Requisitos", this.requisitos.getText());
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
                    JOptionPane.showMessageDialog(null, "No existe el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
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
                        Message m = new Message(0, com.message.Message.OFERTA_LABORAL_PRE, dia, mes, año);
                        m.add("Título Del Trabajo", this.tituloTrabajo.getText());
                        m.add("Profesor Encargado", this.profesor.getText());
                        m.add("Lugar De Trabajo", this.lugarTrabajo.getText());
                        m.add("Nombre Del Contacto", this.contactoTrabajo.getText());
                        m.add("Horas Semanales", this.horasSemanales.getSelectedItem().toString());
                        m.add("Duración Del Trabajo", this.duracionMeses.getSelectedItem().toString());
                        m.add("Monto A Pagar", this.montoTrabajo.getText());
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
            JOptionPane.showMessageDialog(null,"Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } 
}
