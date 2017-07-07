/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.tools;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ve´ronica
 */
public final class PanelFechaPostulacion extends JPanel
{
    private JPanel panelIniPos;
    private JDateChooser textoIniPos,textoFinPos;
    private JPanel panelFinPos;
    public PanelFechaPostulacion() 
    {
        this.setLayout(new GridLayout(1, 2));
        this.createObjects();
    }
    
    private void createObjects()
    {
        this.panelIniPos = new JPanel(new GridLayout(1, 1));
        this.textoIniPos = new JDateChooser();
        this.textoIniPos.setIcon(new ImageIcon ( "src/desktop/resources/calendario.png" ));
        this.panelIniPos.setBorder(BorderFactory.createTitledBorder("Inicio Postulación"));
        this.panelIniPos.add(this.textoIniPos);
        
       
        
        this.panelFinPos = new JPanel(new GridLayout(1, 1));
        this.textoFinPos = new JDateChooser();
        this.textoFinPos.setIcon(new ImageIcon ( "src/desktop/resources/calendario.png" ));
        this.panelFinPos.setBorder(BorderFactory.createTitledBorder("Fin Postulación"));
        this.panelFinPos.add(this.textoFinPos); 
        
        this.add(this.panelIniPos);this.add(this.panelFinPos);
    } 
    
    public String getDateIniPosToString()
    {
        Date d = this.textoIniPos.getDate();
        String dia = Integer.toString(d.getDate());String mes = Integer.toString(d.getMonth()+1);String año = Integer.toString(d.getYear()+1900);
        String dateIni = dia + "/" + mes + "/" + año;
        return dateIni;
    }
    
    public String getDateFinPosToString()
    {
        Date d = this.textoFinPos.getDate();
        String dia = Integer.toString(d.getDate());String mes = Integer.toString(d.getMonth()+1);String año = Integer.toString(d.getYear()+1900);
        String dateFin = dia + "/" + mes + "/" + año;
        return dateFin;
    }
    
    public void setDateIniPos(Date d)
    {
        this.textoIniPos.setDate(d);
    }
    
    public void setDateFinPos(Date d)
    {
        this.textoFinPos.setDate(d);
    }
    
    public boolean validateIniDateAndFinDate()
    {
        JCalendar c = new JCalendar();
        if (this.textoIniPos.getDate().after(c.getDate()) || this.textoFinPos.getDate().after(c.getDate()))
        {
            if(this.textoFinPos.getDate().after(this.textoIniPos.getDate()))
            {
                return true;
            }
        }
        return false;
    }
    
    public void cleanDates()
    {
        this.textoIniPos.setDate(null);
        this.textoFinPos.setDate(null);
    }
}
