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
import desktop.tools.PanelFechaPostulacion;
import desktop.tools.ReferencePanel;
import java.io.File;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ve´ronica
 */
public class PanelOfertaLaboralExALumnos extends ReferencePanel
{
    private JTextField nombreEmpresa,posicionOfertada,destinoPosicion,metodoSeleccion,salarioTrabajo;
    private PanelFechaPostulacion panelPos;
    private PanelArchivo archivo;
    private JTextArea requisitos,observaciones;
    private boolean isDraftMessage;

    public PanelOfertaLaboralExALumnos() 
    {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.isDraftMessage = false;
    }

    public PanelOfertaLaboralExALumnos(String nombreEmpresa, String posicionOfertada, String destinoPosicion, String metodoSeleccion, String salarioTrabajo, Date di, Date df, String nombreArchivo, File archivo,String requisitos, String observaciones) 
    {
        super(ReferencePanel.TIPO1);
        this.createObjects();
        this.nombreEmpresa.setText(nombreEmpresa);
        this.posicionOfertada.setText(posicionOfertada);
        this.destinoPosicion.setText(destinoPosicion);
        this.metodoSeleccion.setText(metodoSeleccion);
        this.salarioTrabajo.setText(salarioTrabajo);
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
        this.nombreEmpresa = new JTextField(30);
        this.posicionOfertada = new JTextField(30);
        this.destinoPosicion = new JTextField(30);
        this.metodoSeleccion = new JTextField(30);
        this.panelPos = new PanelFechaPostulacion();
        this.salarioTrabajo = new JTextField(23);
        this.requisitos = new JTextArea(10,20);
        this.archivo = new PanelArchivo();
        this.observaciones = new JTextArea(10,20);
        
        this.addElementsWithoutScroll(this.nombreEmpresa, "Nombre de la empresa", "grow");
        this.addElementsWithoutScroll(this.posicionOfertada, "Nombre de la posición ofertada", "grow,wrap");
        this.addElementsWithoutScroll(this.destinoPosicion, "Destino de la posición", "grow");
        this.addElementsWithoutScroll(this.metodoSeleccion, "Método de selección", "grow,wrap");
        this.addElementsWithoutScroll(this.salarioTrabajo, "Salario Ofrecido", "grow,wrap");
        this.addElementsWithoutScroll(this.panelPos, "Fechas de postulacióm", "grow");
        this.addElementsWithoutScroll(this.archivo, "Subir archivo", "grow,wrap");
        this.addElementsWithScroll(this.requisitos, "Requisitos", "grow");
        this.addElementsWithScroll(this.observaciones, "Observaciones", "grow");
        
    }
    
    private boolean isEmptyFields()
    {
        return (this.nombreEmpresa.getText().equals("") || this.posicionOfertada.getText().equals("") || this.destinoPosicion.getText().equals("") || this.metodoSeleccion.getText().equals("") ||
                this.salarioTrabajo.getText().equals("") || this.requisitos.getText().equals("") || this.observaciones.getText().equals(""));
    }

    @Override
    protected void button1Behiavor() 
    {
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de limpiar los campos?");
        if(seleccion == JOptionPane.OK_OPTION)
        {
            this.nombreEmpresa.setText("");
            this.posicionOfertada.setText("");
            this.destinoPosicion.setText("");
            this.metodoSeleccion.setText("");
            this.salarioTrabajo.setText("");
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
                        Message m = new Message(0,com.message.Message.OFERTA_LABORAL_POST, dia, mes, año);
                        m.add("Nombre De La Empresa", this.nombreEmpresa.getText());
                        m.add("Nombre De La Posición Ofertada", this.posicionOfertada.getText());
                        m.add("Destino De La Posición", this.destinoPosicion.getText());
                        m.add("Método De La Selección", this.metodoSeleccion.getText());
                        m.add("Salario Ofrecido", this.salarioTrabajo.getText());
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
                        Message m = new Message(0,com.message.Message.OFERTA_LABORAL_POST, dia, mes, año);
                        m.add("Nombre De La Empresa", this.nombreEmpresa.getText());
                        m.add("Nombre De La Posición Ofertada", this.posicionOfertada.getText());
                        m.add("Destino De La Posición", this.destinoPosicion.getText());
                        m.add("Método De La Selección", this.metodoSeleccion.getText());
                        m.add("Salario Ofrecido", this.salarioTrabajo.getText());
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
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
