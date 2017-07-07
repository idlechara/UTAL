/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.tools;

import com.toedter.calendar.JDateChooser;
import java.io.Serializable;
import java.util.Date;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Erik Andres Regla Torres
 */
public class CalendarCachedMessage implements Serializable {

    private String nombreEvento, topico, fecha, lugar, horario,
            archivo, observaciones;
    public int index;
    public boolean published;

    public CalendarCachedMessage(JTextField nombreEvento, JTextField topico,
            JDateChooser fecha, JTextField lugar, JTextField horario,
            PanelArchivo archivo, JTextArea observaciones) {
        this.archivo = archivo.getNameArchivo();
        this.fecha = fecha.getDate().getDate() + "-" + (fecha.getDate().getMonth() + 1) + "-" + (fecha.getDate().getYear() + 1900);
        this.horario = horario.getText();
        this.lugar = lugar.getText();
        this.nombreEvento = nombreEvento.getText();
        this.observaciones = observaciones.getText();
        this.topico = topico.getText();
    }

    public void rebuildCalendarCachedMessage(JTextField nombreEvento, JTextField topico,
            JDateChooser fecha, JTextField lugar, JTextField horario,
            PanelArchivo archivo, JTextArea observaciones) {
        archivo.setNameArchivo(this.archivo);
        int year=Integer.parseInt(this.fecha.split("-")[2]), 
                month=Integer.parseInt(this.fecha.split("-")[1]),
                date=Integer.parseInt(this.fecha.split("-")[0]);
                System.out.println(date+"/"+month+"/"+year);
                Date dat = new Date();
                dat.setMonth(month-1);
                dat.setYear(year-1900);
                dat.setDate(date);
                fecha.setDate(dat);
        horario.setText(this.horario);
        lugar.setText(this.lugar);
        nombreEvento.setText(this.nombreEvento);
        observaciones.setText(this.observaciones);
        topico.setText(this.topico);
    }

    public String getArchivo() {
        return archivo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHorario() {
        return horario;
    }

    public String getLugar() {
        return lugar;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getTopico() {
        return topico;
    }

    @Override
    public String toString() {
        return this.fecha + " " + this.topico + " " + this.horario;
    }
}
