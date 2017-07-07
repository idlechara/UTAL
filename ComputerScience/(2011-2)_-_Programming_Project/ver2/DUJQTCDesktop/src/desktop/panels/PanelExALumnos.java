/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.panels;

import desktop.subPanels.subPanelExAlumnos.PanelAvisoGeneralExAlumnos;
import desktop.subPanels.subPanelExAlumnos.PanelBecaEstudio;
import desktop.subPanels.subPanelExAlumnos.PanelOfertaLaboralExALumnos;
import desktop.subPanels.subPanelExAlumnos.PanelOfertaPostGrado;
import desktop.tools.Cadenas;
import desktop.tools.ReferencePanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author ve´ronica
 */
public class PanelExALumnos extends JPanel implements ActionListener
{
    private Cadenas cadenaExAlumnos;
    private JPanel panelNorte;
    private JComboBox<String> selector;
    

    public PanelExALumnos() 
    {
        this.setLayout(new BorderLayout());
        this.cadenaExAlumnos = new Cadenas();
        this.createObjects();
    }

    private void createObjects()
    {
      this.panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
      this.selector = new JComboBox<>(this.cadenaExAlumnos.getTipoMensajesExAlumnos());
      this.selector.addActionListener(this);
      this.panelNorte.setBorder(BorderFactory.createRaisedBevelBorder());
      this.panelNorte.add(this.selector);
      
      this.add(this.panelNorte,BorderLayout.NORTH);
      PanelOfertaLaboralExALumnos panelref = new PanelOfertaLaboralExALumnos();
      this.addReferencePanel(panelref);
    }

    private void addReferencePanel(ReferencePanel p)
    {
        JScrollPane scrollPanelRef = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.removeAll();
        this.add(this.panelNorte,BorderLayout.NORTH);
        this.add(scrollPanelRef,BorderLayout.CENTER);
        this.add(p.getButtonPanel(),BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.selector)
        {
            if(this.selector.getSelectedIndex() == 0)
            {
                PanelOfertaLaboralExALumnos panelref = new PanelOfertaLaboralExALumnos();
                this.addReferencePanel(panelref);
            }
            
            if(this.selector.getSelectedIndex() == 1)
            {
                PanelOfertaPostGrado panelref = new PanelOfertaPostGrado();
                this.addReferencePanel(panelref);
            }
            
            if(this.selector.getSelectedIndex() == 2)
            {
                PanelBecaEstudio panelref = new PanelBecaEstudio();
                this.addReferencePanel(panelref);
            }
            
            if(this.selector.getSelectedIndex() == 3)
            {
                PanelAvisoGeneralExAlumnos panelref = new PanelAvisoGeneralExAlumnos();
                this.addReferencePanel(panelref);
            }
            
            this.repaint();
            this.updateUI();
        }
    }
}
