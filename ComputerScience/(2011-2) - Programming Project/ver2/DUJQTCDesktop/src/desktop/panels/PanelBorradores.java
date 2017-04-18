/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.panels;

import com.dataPack.Message;
import desktop.subPanels.subPanelAlumnos.*;
import desktop.subPanels.subPanelExAlumnos.PanelAvisoGeneralExAlumnos;
import desktop.subPanels.subPanelExAlumnos.PanelBecaEstudio;
import desktop.subPanels.subPanelExAlumnos.PanelOfertaLaboralExALumnos;
import desktop.subPanels.subPanelExAlumnos.PanelOfertaPostGrado;
import desktop.tools.GsonSerializer;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Camilo
 */
public class PanelBorradores extends JPanel implements ListSelectionListener,ActionListener
{
    private JSplitPane panelCentro;
    private static JList<String> listaBorradores;
    private JPanel panelEste;
    private JButton botonEditar;
    private JButton botonBorrar;
    private JButton botonRefrescar;
    
    

    public PanelBorradores() throws Exception
    {
        this.setLayout(new BorderLayout());
        this.createObjects();
    }
    
    private void createObjects()throws Exception
    {    
        this.panelCentro = new JSplitPane();
        PanelBorradores.listaBorradores = new JList<>();
        PanelBorradores.listaBorradores.addListSelectionListener(this);
        this.panelCentro.setLeftComponent(PanelBorradores.listaBorradores);
        this.panelCentro.setRightComponent(new JPanel());
        this.panelCentro.setBorder(BorderFactory.createLoweredBevelBorder());
        this.panelCentro.setDividerLocation(300);
        PanelBorradores.listaBorradores.addListSelectionListener(this);
        
        this.panelEste = new JPanel(new MigLayout());
        this.botonRefrescar = new JButton("Refrescar");
        this.botonRefrescar.addActionListener(this);
        this.botonEditar = new JButton("Editar       ");
        this.botonEditar.addActionListener(this);
        this.botonBorrar = new JButton("Borrar       ");
        this.botonBorrar.addActionListener(this);
        this.panelEste.setBorder(BorderFactory.createLoweredBevelBorder());
        this.panelEste.add(this.botonRefrescar,"wrap");
        this.panelEste.add(this.botonEditar,"wrap");
        this.panelEste.add(this.botonBorrar);
        
        this.add(this.panelCentro,BorderLayout.CENTER);
        this.add(this.panelEste,BorderLayout.EAST);
        
    }
    
    private JScrollPane loadPanelMessages(com.dataPack.Message m)
    {
        JTextArea panel = new JTextArea();
        String s = new String();
        for(int i=0;i<m.keys.size();i++)
        {
           s += m.keys.get(i)+": " + m.values.get(i) + "\n";
        }
        s += "Fecha de creación: " + (m.day+"/"+m.month+"/"+m.year) + "\n";
        panel.setText(s);
        panel.setEditable(false);
        JScrollPane scrollPanel = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPanel;
    }
    
    private static String typeMessagesToString(int tm)
    {
        String type = new String();
        
        if(tm == 0)
        {
            type = "Cambio de horario para cursos";
        }
        
        if(tm == 1)
        {
            type = "Suspensión de pruebas y/o clases";
        }
        
        if(tm == 2)
        {
            type = "Avisos de solicitud de ayudantes";
        }
        
        if(tm == 3)
        {
            type = "Envío archivo de planificación de cursos y horarios";
        }
        
        if(tm == 4)
        {
            type = "Ofertas Laborales (memorias/prácticas)";
        }
        
        if(tm == 5)
        {
            type = "Aviso general para alumnos";
        }
        
        if(tm == 6)
        {
            type = "Oferta laboral";
        }
        
        if(tm == 7)
        {
            type = "Oferta Post-grado";
        }
        
        if(tm == 8)
        {
            type = "Becas de estudio";
        }
        
        if(tm == 9)
        {
            type = "Aviso general para Ex-alumnos";
        }
        
        if(tm == 10)
        {
            type = "Calendario de eventos";
        }
        
        return type;
    }
    
    public static void refreshList()
    {
        ArrayList<Message> listM = new ArrayList<>();
        try 
        {
            listM = (ArrayList<Message>)GsonSerializer.deSerialize(GsonSerializer.draftDestination);
        } 
        catch (Exception ex) {
            Logger.getLogger(PanelBorradores.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            PanelBorradores.listaBorradores.setListData(new String[0]);
            ArrayList<String> events = new ArrayList<>();
            for (int i=0; i<listM.size(); i++) 
            {
                events.add("Mensaje de tipo " + PanelBorradores.typeMessagesToString(listM.get(i).type));
            }

            if (events.size() >= 1) 
            {
                    PanelBorradores.listaBorradores.setListData(events.toArray(events.toArray(new String[0])));
                    PanelBorradores.listaBorradores.setSelectedIndex(0);
            }
        }
    }

    public static void removePublishedMessage() throws Exception
    {
        int indice = PanelBorradores.listaBorradores.getSelectedIndex();
        ArrayList<Message> listM = new ArrayList<>();
        listM = (ArrayList<Message>)GsonSerializer.deSerialize(GsonSerializer.draftDestination);
        listM.remove(indice);
        GsonSerializer.serialize(listM,GsonSerializer.draftDestination);
        PanelBorradores.refreshList();
    }

 
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //try
        //{
            if(e.getSource() == this.botonRefrescar)
            {
                PanelBorradores.refreshList();
            }
            
            if(e.getSource() == this.botonEditar)
            {
                ArrayList<Message> listM = new ArrayList<Message>();
                try {
                    listM = (ArrayList<Message>)GsonSerializer.deSerialize(GsonSerializer.draftDestination);
                } catch (Exception ex) {
                    Logger.getLogger(PanelBorradores.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(listM.size());
                int indice = PanelBorradores.listaBorradores.getSelectedIndex();
                Message m = listM.get(indice);
                JFrame newFrame = new JFrame("Editar " + PanelBorradores.typeMessagesToString(m.type));
                newFrame.setLayout(new BorderLayout());
                newFrame.setSize(1080, 600);
                newFrame.setLocationRelativeTo(null);
                newFrame.setIconImage(new ImageIcon("src/desktop/resources/borrador.png").getImage());

                if(m.type == com.message.Message.CAMBIO_HORARIO)
                {   
                    int year1 = Integer.parseInt(m.values.get(2).split("/")[2]);
                    int month1 = Integer.parseInt(m.values.get(2).split("/")[1]);
                    int day1 = Integer.parseInt(m.values.get(2).split("/")[0]);
                    Date fi = new Date();
                    fi.setYear(year1-1900);
                    fi.setMonth(month1-1);
                    fi.setDate(day1);
                    int year2 = Integer.parseInt(m.values.get(4).split("/")[2]);
                    int month2 = Integer.parseInt(m.values.get(4).split("/")[1]);
                    int day2 = Integer.parseInt(m.values.get(4).split("/")[0]);
                    Date ff = new Date();
                    ff.setYear(year2-1900);
                    ff.setMonth(month2-1);
                    ff.setDate(day2);
                    ArrayList<String> a1 = new ArrayList<>();
                    a1.add(m.values.get(3));
                    ArrayList<String> a2 = new ArrayList<>();
                    a2.add(m.values.get(5));
                    PanelCambioHorario panel = new PanelCambioHorario(m.values.get(1),fi,ff,a1,a2,m.values.get(6));
                    JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.add(scroll,BorderLayout.CENTER);
                    newFrame.add(panel.getButtonPanel(),BorderLayout.SOUTH);
                    newFrame.setVisible(true);
                }
                
                if(m.type == com.message.Message.SUSPENSION_CLASES)
                {
                    PanelSuspension panel = new PanelSuspension(m.values.get(1), m.values.get(6));
                    JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.add(scroll);
                    newFrame.add(panel.getButtonPanel(),BorderLayout.SOUTH);
                    newFrame.setVisible(true);
                }
                
                if(m.type == com.message.Message.SOLICITUD_AYUDANTES)
                {
                    PanelAyudantes panel = new PanelAyudantes(m.values.get(1), m.values.get(3), m.values.get(4));
                    JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.add(scroll);
                    newFrame.add(panel.getButtonPanel(),BorderLayout.SOUTH);
                    newFrame.setVisible(true);
                }
                
                if(m.type == com.message.Message.PLANIFICACION)
                {
                    File f = new File(m.values.get(0));
                    PanelEnvioPlanificacion panel = new PanelEnvioPlanificacion(m.values.get(0),f,m.values.get(1));
                    JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.add(scroll);
                    newFrame.add(panel.getButtonPanel(),BorderLayout.SOUTH);
                    newFrame.setVisible(true);
                }
                
                if(m.type == com.message.Message.OFERTA_LABORAL_PRE)
                {
                    int year1 = Integer.parseInt(m.values.get(7).split("/")[2]);
                    int month1 = Integer.parseInt(m.values.get(7).split("/")[1]);
                    int day1 = Integer.parseInt(m.values.get(7).split("/")[0]);
                    Date fi = new Date();
                    fi.setYear(year1-1900);
                    fi.setMonth(month1-1);
                    fi.setDate(day1);
                    int year2 = Integer.parseInt(m.values.get(8).split("/")[2]);
                    int month2 = Integer.parseInt(m.values.get(8).split("/")[1]);
                    int day2 = Integer.parseInt(m.values.get(8).split("/")[0]);
                    Date ff = new Date();
                    ff.setYear(year2-1900);
                    ff.setMonth(month2-1);
                    ff.setDate(day2);                    
                    File f = new File(m.values.get(9));
                    PanelOfertaLaboral panel = new PanelOfertaLaboral(m.values.get(0), m.values.get(1), m.values.get(2), m.values.get(3),m.values.get(6),fi,ff,m.values.get(9),f,m.values.get(10),m.values.get(11));
                    JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.add(scroll);
                    newFrame.add(panel.getButtonPanel(),BorderLayout.SOUTH);
                    newFrame.setVisible(true);
                }
                
                if(m.type == com.message.Message.AVISO_PRE)
                {
                    File f = new File(m.values.get(1));
                    PanelAvisoGeneral panel = new PanelAvisoGeneral(m.values.get(0),m.values.get(1),f,m.values.get(2));
                    JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.add(scroll);
                    newFrame.add(panel.getButtonPanel(),BorderLayout.SOUTH);
                    newFrame.setVisible(true);
                }
                
                if(m.type == com.message.Message.OFERTA_LABORAL_POST)
                {
                    int year1 = Integer.parseInt(m.values.get(5).split("/")[2]);
                    int month1 = Integer.parseInt(m.values.get(5).split("/")[1]);
                    int day1 = Integer.parseInt(m.values.get(5).split("/")[0]);
                    Date fi = new Date();
                    fi.setYear(year1-1900);
                    fi.setMonth(month1-1);
                    fi.setDate(day1);
                    int year2 = Integer.parseInt(m.values.get(6).split("/")[2]);
                    int month2 = Integer.parseInt(m.values.get(6).split("/")[1]);
                    int day2 = Integer.parseInt(m.values.get(6).split("/")[0]);
                    Date ff = new Date();
                    ff.setYear(year2-1900);
                    ff.setMonth(month2-1);
                    ff.setDate(day2);                    
                    File f = new File(m.values.get(7));
                    PanelOfertaLaboralExALumnos panel = new PanelOfertaLaboralExALumnos(m.values.get(0), m.values.get(1), m.values.get(2), m.values.get(3), m.values.get(4),fi,ff,m.values.get(7),f,m.values.get(8), m.values.get(9));
                    JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.add(scroll);
                    newFrame.add(panel.getButtonPanel(),BorderLayout.SOUTH);
                    newFrame.setVisible(true);
                }
                
                if(m.type == com.message.Message.OFERTA_POSTGRADO)
                {
                    int year1 = Integer.parseInt(m.values.get(3).split("/")[2]);
                    int month1 = Integer.parseInt(m.values.get(3).split("/")[1]);
                    int day1 = Integer.parseInt(m.values.get(3).split("/")[0]);
                    Date fi = new Date();
                    fi.setYear(year1-1900);
                    fi.setMonth(month1-1);
                    fi.setDate(day1);
                    int year2 = Integer.parseInt(m.values.get(4).split("/")[2]);
                    int month2 = Integer.parseInt(m.values.get(4).split("/")[1]);
                    int day2 = Integer.parseInt(m.values.get(4).split("/")[0]);
                    Date ff = new Date();
                    ff.setYear(year2-1900);
                    ff.setMonth(month2-1);
                    ff.setDate(day2); 
                    File f = new File(m.values.get(5));
                    PanelOfertaPostGrado panel = new PanelOfertaPostGrado(m.values.get(0), m.values.get(2),fi,ff,m.values.get(6),f,m.values.get(6), m.values.get(7));
                    JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.add(scroll);
                    newFrame.add(panel.getButtonPanel(),BorderLayout.SOUTH);
                    newFrame.setVisible(true);
                }

                if(m.type == com.message.Message.BECAS_ESTUDIO)
                {
                    int year1 = Integer.parseInt(m.values.get(5).split("/")[2]);
                    int month1 = Integer.parseInt(m.values.get(5).split("/")[1]);
                    int day1 = Integer.parseInt(m.values.get(5).split("/")[0]);
                    Date fi = new Date();
                    fi.setYear(year1-1900);
                    fi.setMonth(month1-1);
                    fi.setDate(day1);
                    int year2 = Integer.parseInt(m.values.get(6).split("/")[2]);
                    int month2 = Integer.parseInt(m.values.get(6).split("/")[1]);
                    int day2 = Integer.parseInt(m.values.get(6).split("/")[0]);
                    Date ff = new Date();
                    ff.setYear(year2-1900);
                    ff.setMonth(month2-1);
                    ff.setDate(day2); 
                    int year3 = Integer.parseInt(m.values.get(7).split("/")[2]);
                    int month3 = Integer.parseInt(m.values.get(7).split("/")[1]);
                    int day3 = Integer.parseInt(m.values.get(7).split("/")[0]);
                    Date fp = new Date();
                    fp.setYear(year3-1900);
                    fp.setMonth(month3-1);
                    fp.setDate(day3);
                    File f = new File(m.values.get(8));
                    PanelBecaEstudio panel = new PanelBecaEstudio(m.values.get(0), m.values.get(1), m.values.get(2), m.values.get(3),fi,ff,fp,m.values.get(8),f,m.values.get(9), m.values.get(10), m.values.get(11));
                    JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.add(scroll);
                    newFrame.add(panel.getButtonPanel(),BorderLayout.SOUTH);
                    newFrame.setVisible(true);
                }
                
                if(m.type == com.message.Message.AVISOS_GENERALES)
                {
                    File f = new File(m.values.get(1));
                    PanelAvisoGeneralExAlumnos panel = new PanelAvisoGeneralExAlumnos(m.values.get(0),m.values.get(1),f,m.values.get(2));
                    JScrollPane scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    newFrame.add(scroll);
                    newFrame.add(panel.getButtonPanel(),BorderLayout.SOUTH);
                    newFrame.setVisible(true);
                }
            }    
        //}catch(Exception ex)
        //{
        //    JOptionPane.showMessageDialog(this, "No existen mensajes en la lista de borradores.", "Error al editar un mensaje", JOptionPane.ERROR_MESSAGE);
        //}
        
        if(e.getSource() == this.botonBorrar)
        {
            ArrayList<Message> listM = new ArrayList<>();
            int indice = PanelBorradores.listaBorradores.getSelectedIndex();
            try 
            {
                listM = (ArrayList<Message>)GsonSerializer.deSerialize(GsonSerializer.draftDestination);
                if(!listM.isEmpty())
                {
                    int seleccion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de borrar el mensaje?");
                    if(seleccion == JOptionPane.OK_OPTION)
                    {
                        listM.remove(indice);
                        GsonSerializer.serialize(listM,GsonSerializer.draftDestination);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "No existen mensajes en la lista de borradores.", "Error al borrar un mensaje", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (Exception ex) 
            {
                JOptionPane.showMessageDialog(this, "No existen mensajes en la lista de borradores.", "Error al borrar un mensaje", JOptionPane.ERROR_MESSAGE);
            }
            PanelBorradores.listaBorradores.setSelectedIndex(-1);
            PanelBorradores.refreshList();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if(e.getSource() == PanelBorradores.listaBorradores)
        {                
            ArrayList<Message> listM = new ArrayList<>();
            try 
            {
                listM = (ArrayList<Message>)GsonSerializer.deSerialize(GsonSerializer.draftDestination);
            } 
            catch (Exception ex) {
                Logger.getLogger(PanelBorradores.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(listM.size()>0)
            {
                int indice = PanelBorradores.listaBorradores.getSelectedIndex();
                if(indice >= 0)
                {
                    this.panelCentro.setRightComponent(this.loadPanelMessages(listM.get(indice)));
                    this.panelCentro.setDividerLocation(300);
                }
                else
                {
                    this.panelCentro.setRightComponent(new JPanel());
                    this.panelCentro.setDividerLocation(300);
                }
            }
            else
            {
                this.panelCentro.setRightComponent(new JPanel());
                this.panelCentro.setDividerLocation(300);
            }
        }
    }
}
