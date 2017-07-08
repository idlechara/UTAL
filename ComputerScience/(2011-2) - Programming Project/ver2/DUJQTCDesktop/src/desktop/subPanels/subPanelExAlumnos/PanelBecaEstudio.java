/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.subPanels.subPanelExAlumnos;

import com.dataPack.Message;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import desktop.panels.PanelBorradores;
import desktop.tools.*;
import java.io.File;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author ve´ronica
 */
public class PanelBecaEstudio extends ReferencePanel
{
  
    private JTextField nombreBeca,organismoPatroncinante,destino,montoBeca;
    private JDateChooser pubResultados; 
    private PanelFechaPostulacion panelPos;
    private JTextArea objetivoBeca,requisitos,observaciones;
    private PanelArchivo archivo;
    private JComboBox<String> duracionBeca;
    private boolean isDraftMessage;
    
    public PanelBecaEstudio() 
    {   
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.isDraftMessage = false;
    }

    public PanelBecaEstudio(String nombreBeca, String organismoPatroncinante, String destino, String montoBeca,Date di,Date df,Date dp,String nombreArchivo, File archivo,String objetivoBeca, String  requisitos, String observaciones) 
    {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.nombreBeca.setText(nombreBeca);
        this.organismoPatroncinante.setText(organismoPatroncinante);
        this.destino.setText(destino);
        this.montoBeca.setText(montoBeca);
        this.panelPos.setDateIniPos(di);
        this.panelPos.setDateFinPos(df);
        this.pubResultados.setDate(dp);
        this.archivo.setNameArchivo(nombreArchivo);
        this.archivo.setArchivo(archivo);
        this.objetivoBeca.setText(objetivoBeca);
        this.requisitos.setText(requisitos);
        this.observaciones.setText(observaciones);
        this.isDraftMessage = true;
    }

    private void createObjects() 
    {
        Cadenas cadenaBecaEstudio = new Cadenas();
        
        this.nombreBeca = new JTextField(30);
        this.organismoPatroncinante = new JTextField(30);
        this.destino = new JTextField(30);
        this.duracionBeca = new JComboBox<String>(cadenaBecaEstudio.getCantidadMeses());
        this.montoBeca = new JTextField(30);
        this.pubResultados = new JDateChooser();
        this.pubResultados.setIcon(new ImageIcon ( "src/desktop/resources/calendario.png" ));
        this.panelPos = new PanelFechaPostulacion();
        this.objetivoBeca = new JTextArea(10,20);
        this.requisitos = new JTextArea(10,20);
        this.observaciones = new JTextArea(10,20);
        this.archivo = new PanelArchivo();
        
        
        
        this.addElementsWithoutScroll(this.nombreBeca, "Nombre de la beca", "grow");
        this.addElementsWithoutScroll(this.organismoPatroncinante, "Organismo patrocinante", "grow,wrap");
        this.addElementsWithoutScroll(this.destino, "Destino", "grow");
        this.addElementsWithoutScroll(this.montoBeca, "Monto de la beca", "grow,wrap");
        this.addElementsWithoutScroll(this.duracionBeca, "Duración de la beca", "grow");
        this.addElementsWithoutScroll(this.pubResultados, "Publicacion de los resultados", "grow,wrap");
        this.addElementsWithoutScroll(this.panelPos, "Fechas de postulacion", "grow");
        this.addElementsWithoutScroll(this.archivo, "Subir airchivo", "grow,wrap");
        this.addElementsWithScroll(this.objetivoBeca, "Objetivo de la beca", "grow");
        this.addElementsWithScroll(this.requisitos, "Requisitos", "grow,wrap");
        this.addElementsWithScroll(this.observaciones, "Observaciones", "grow");
    }
    
    private boolean isEmptyFields()
    {
        return (this.nombreBeca.getText().equals("") || this.organismoPatroncinante.getText().equals("") || this.destino.getText().equals("") || this.montoBeca.getText().equals("") ||
                this.requisitos.getText().equals("") || this.observaciones.getText().equals(""));
    }

    @Override
    protected void button1Behiavor() 
    {
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de limpiar los campos?");
        if(seleccion == JOptionPane.OK_OPTION)
        {
            this.nombreBeca.setText("");
            this.organismoPatroncinante.setText("");
            this.destino.setText("");
            this.montoBeca.setText("");
            this.duracionBeca.setSelectedIndex(0);
            this.pubResultados = null;
            this.panelPos.cleanDates();
            this.archivo.cleanArchivo();
            this.objetivoBeca.setText("");
            this.requisitos.setText("");
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
                    JCalendar cr = new JCalendar();
                    if(this.panelPos.validateIniDateAndFinDate() || this.pubResultados.getDate().after(cr.getDate()))
                    {
                        JCalendar c = new JCalendar();
                        int dia = c.getDate().getDate();
                        int mes = c.getDate().getMonth()+1;
                        int año = c.getDate().getYear()+1900;
                        Message m = new Message(0, com.message.Message.BECAS_ESTUDIO, dia, mes, año);
                        m.add("Nombre De La Beca", this.nombreBeca.getText());
                        m.add("Organismo Patrocinante", this.organismoPatroncinante.getText());
                        m.add("Destino", this.destino.getText());
                        m.add("Monto De La Beca", this.montoBeca.getText());
                        m.add("Duración De La Beca", this.duracionBeca.getSelectedItem().toString());
                        m.add("Fecha Inicio Postulaciones", this.panelPos.getDateIniPosToString());
                        m.add("Fecha Fin Postulaciones", this.panelPos.getDateFinPosToString());
                        Date d = this.pubResultados.getDate();
                        String dia1 = Integer.toString(d.getDate());String mes1 = Integer.toString(d.getMonth()+1);String año1 = Integer.toString(d.getYear()+1900);
                        m.add("Publicación De Los Resultados", dia1 + "/" + mes1 + "/" + año1);
                        m.add("Archivo Adjunto", this.archivo.getNameArchivo());
                        m.add("Objetivo De La Beca", this.objetivoBeca.getText());
                        m.add("Requisitos", this.requisitos.getText());
                        m.add("Observaciones", this.observaciones.getText());
                        GsonSerializer.addDraftNotice(m);
                        JOptionPane.showMessageDialog(this, "Borrador guardado con éxito!!.", "Borrador guardado", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Ingrese una fecha correcta.", "Error al ingresar la fecha", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No existe el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    @Override
    protected void button3Behiavor() 
    {
        try{
            if(!this.isEmptyFields())
            {
                if(archivo.testFile())
                {
                    JCalendar cr = new JCalendar();
                    if(this.panelPos.validateIniDateAndFinDate() || this.pubResultados.getDate().after(cr.getDate()))
                    {
                        JCalendar c = new JCalendar();
                        int dia = c.getDate().getDate();
                        int mes = c.getDate().getMonth()+1;
                        int año = c.getDate().getYear()+1900;
                        Message m = new Message(0, com.message.Message.BECAS_ESTUDIO, dia, mes, año);
                        m.add("Nombre De La Beca", this.nombreBeca.getText());
                        m.add("Organismo Patrocinante", this.organismoPatroncinante.getText());
                        m.add("Destino", this.destino.getText());
                        m.add("Monto De La Beca", this.montoBeca.getText());
                        m.add("Duración De La Beca", this.duracionBeca.getSelectedItem().toString());
                        Date d = this.pubResultados.getDate();
                        String dia1 = Integer.toString(d.getDate());String mes1 = Integer.toString(d.getMonth()+1);String año1 = Integer.toString(d.getYear()+1900);
                        m.add("Inicio Postulaciones", this.panelPos.getDateIniPosToString());
                        m.add("Fin Postulaciones", this.panelPos.getDateFinPosToString());
                        m.add("Publicación De Los Resultados", dia1 + "/" + mes1 + "/" + año1);
                        m.add("Archivo Adjunto",this.archivo.getNameArchivo());
                        m.add("Objetivo De La Beca", this.objetivoBeca.getText());
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
                        JOptionPane.showMessageDialog(this, "Ingrese una fecha correcta.", "Error al ingresar la fecha", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No existe el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
