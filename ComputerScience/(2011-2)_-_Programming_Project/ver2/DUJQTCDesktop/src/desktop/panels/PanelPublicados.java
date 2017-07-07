/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.panels;

import com.dataPack.Message;
import desktop.tools.GsonSerializer;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
public class PanelPublicados extends JPanel implements ListSelectionListener,ActionListener
{
    private JSplitPane panelCentro;
    private JList<String> listaPublicados;
    private JPanel panelEste;
    private JButton botonRefrescar;

    public PanelPublicados() throws Exception 
    {
        this.setLayout(new BorderLayout());
        this.createObjects();
    }
    
    private void createObjects()throws Exception
    {    
        this.panelCentro = new JSplitPane();
        this.listaPublicados = new JList<String>();
        this.listaPublicados.addListSelectionListener(this);
        this.panelCentro.setLeftComponent(this.listaPublicados);
        this.panelCentro.setRightComponent(new JPanel());
        this.panelCentro.setBorder(BorderFactory.createLoweredBevelBorder());
        this.panelCentro.setDividerLocation(300);
        this.listaPublicados.addListSelectionListener(this);
        
        this.panelEste = new JPanel(new MigLayout());
        this.botonRefrescar = new JButton("Refrescar");
        this.botonRefrescar.addActionListener(this);
        this.panelEste.setBorder(BorderFactory.createLoweredBevelBorder());
        this.panelEste.add(this.botonRefrescar,"wrap");
                
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
    
        private String typeMessagesToString(int tm)
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
        
        public void refreshList()
    {
        ArrayList<Message> listM = new ArrayList<Message>();
            try {
                listM = (ArrayList<Message>)GsonSerializer.deSerialize(GsonSerializer.publishDestination);
            } catch (Exception ex) {
                Logger.getLogger(PanelPublicados.class.getName()).log(Level.SEVERE, null, ex);
            }finally
            {
                this.listaPublicados.setListData(new String[0]);
                ArrayList<String> events = new ArrayList<String>();
                for (int i=0; i<listM.size(); i++) 
                {
                    events.add("Mensaje de tipo " + this.typeMessagesToString(listM.get(i).type));
                }

                if (events.size() >= 1) 
                {
                        System.out.println("EVENTOS CARGADOS!!" + events.toArray(new String[0])[0]);
                        this.listaPublicados.setListData(events.toArray(events.toArray(new String[0])));
                        this.listaPublicados.setSelectedIndex(0);
                }
            }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if(e.getSource() == this.listaPublicados)
        {                
            ArrayList<Message> listM = new ArrayList<Message>();
            try 
            {
                listM = (ArrayList<Message>)GsonSerializer.deSerialize(GsonSerializer.publishDestination);
            } 
            catch (Exception ex) {
                Logger.getLogger(PanelPublicados.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(listM.size()>0)
            {
                int indice = this.listaPublicados.getSelectedIndex();
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

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            if(e.getSource() == this.botonRefrescar)
            {
                this.refreshList();
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, "No existen mensajes en la lista de publicados.", "Error al mostrar un mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
   
}