/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.panels;

import desktop.subPanels.subPanelAlumnos.*;
import desktop.tools.Cadenas;
import desktop.tools.ReferencePanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author ve´ronica
 */
public class PanelAlumnos extends JPanel implements ActionListener
{
    private Cadenas cadenaAlumnos;
    private JPanel panelNorte;
    private JComboBox<String> selector;
    private JPanel panelBienvenida;

    public PanelAlumnos()
    {   this.setLayout(new BorderLayout());
        this.cadenaAlumnos = new Cadenas();
        this.createObjects();
    }
    
    private void createObjects()
    {
      this.panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
      this.selector = new JComboBox<>(this.cadenaAlumnos.getTipoMensajesAlumnos());
      this.selector.addActionListener(this);
      this.panelNorte.setBorder(BorderFactory.createRaisedBevelBorder());
      this.panelNorte.add(this.selector);
      
      this.add(this.panelNorte,BorderLayout.NORTH);
      PanelCambioHorario panelref = new PanelCambioHorario();
      this.addReferencePanel(panelref);
    }

    private void addReferencePanel(ReferencePanel p)
    {
        JScrollPane scrollPanelRef1 = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.removeAll();
        this.add(this.panelNorte,BorderLayout.NORTH);
        this.add(scrollPanelRef1,BorderLayout.CENTER);
        this.add(p.getButtonPanel(),BorderLayout.SOUTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
         if(e.getSource() == this.selector)
        {
            if(this.selector.getSelectedIndex() == 0)
            {
                PanelCambioHorario panelref = new PanelCambioHorario();
                this.addReferencePanel(panelref);
            }
            
            if(this.selector.getSelectedIndex() == 1)
            { 
                PanelSuspension panelref = new PanelSuspension();
                this.addReferencePanel(panelref);  
            }
            
            if(this.selector.getSelectedIndex() == 2)
            { 
                PanelAyudantes panelref = new PanelAyudantes();
                this.addReferencePanel(panelref);
            }
            
            if(this.selector.getSelectedIndex() == 3)
            {
                PanelEnvioPlanificacion panelref = new PanelEnvioPlanificacion();
                this.addReferencePanel(panelref);
            }
            
            if(this.selector.getSelectedIndex() == 4)
            {
                PanelOfertaLaboral panelref = new PanelOfertaLaboral();
                this.addReferencePanel(panelref);
            }
            
            if(this.selector.getSelectedIndex() == 5)
            {
                PanelAvisoGeneral panelref = new PanelAvisoGeneral();
                this.addReferencePanel(panelref);
            }
            
            this.repaint();
            this.updateUI();
        }
    }
}
