/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.Tabbs;

import desktop.panels.*;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ve´ronica
 */
public class MainTabbs extends JTabbedPane implements ChangeListener
{
    private PanelAlumnos panelAlumnos;
    private PanelExALumnos panelExAlumnos;
    private PanelCalendario panelCalendario;
    private PanelBorradores panelBorradores;
    private PanelPublicados panelPublicados;

    public MainTabbs() throws Exception 
    {
        this.createObjects();
    }
    
    private void createObjects()throws Exception 
    {
        this.panelAlumnos = new PanelAlumnos();
        this.panelExAlumnos = new PanelExALumnos();
        this.panelCalendario = new PanelCalendario();
        this.panelPublicados = new PanelPublicados();
        this.panelBorradores = new PanelBorradores();
        this.addChangeListener(this);
        this.addTab("Alumnos.",new ImageIcon("src/desktop/resources/estudiante.png"),this.panelAlumnos);
        this.addTab("Ex-Alumnos.",new ImageIcon("src/desktop/resources/ex estudiante.png"),this.panelExAlumnos);
        this.addTab("Calendario de eventos.",new ImageIcon("src/desktop/resources/calendario.png"),this.panelCalendario);
        this.addTab("Publicados.",new ImageIcon("src/desktop/resources/publicados.png"),this.panelPublicados);
        this.addTab("Borradores.",new ImageIcon("src/desktop/resources/borrador.png"),this.panelBorradores);
    }
    
    @Override
    public void stateChanged(ChangeEvent e) 
    {
        int indice = this.getSelectedIndex();
        if(indice == 3)
        {
            this.panelPublicados.refreshList();
        }
        if(indice == 4)
        {
            this.panelBorradores.refreshList();
        }
    }
}
