/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.panels;

import com.toedter.calendar.JCalendar;
import desktop.subPanels.subPanelsCalendarios.FrameNuevoEvento;
import desktop.tools.CalendarCachedMessage;
import desktop.tools.GsonSerializer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ve´ronica
 */
public class PanelCalendario extends JPanel implements ActionListener, PropertyChangeListener, ListSelectionListener {

    private JPanel panelBotones, panelCalendario;
    private JButton botonAgregar, botonEditar, botonBorrar;
    private JCalendar calendario;
    private JList<String> listaEventos;

    public PanelCalendario() {
        this.setLayout(new BorderLayout());
        this.createObjects();
    }

    private void createObjects() {
        this.panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.botonAgregar = new JButton("Agregar evento");
        this.botonAgregar.addActionListener(this);
        this.botonEditar = new JButton("Editar evento");
        this.botonEditar.addActionListener(this);
        this.botonBorrar = new JButton("Borrar evento");
        this.botonBorrar.addActionListener(this);
        this.botonEditar.setEnabled(false);
        this.botonBorrar.setEnabled(false);
        this.panelBotones.add(this.botonAgregar);
        this.panelBotones.add(this.botonEditar);
        this.panelBotones.add(this.botonBorrar);

        this.panelCalendario = new JPanel(new GridLayout(2, 2, 10, 10));
        this.calendario = new JCalendar();
        this.calendario.setBorder(BorderFactory.createLoweredBevelBorder());
        this.listaEventos = new JList<String>();
        this.listaEventos.setBorder(BorderFactory.createLoweredBevelBorder());
        this.panelCalendario.add(this.calendario);
        this.panelCalendario.add(this.listaEventos);
        this.panelCalendario.add(new JPanel());
        this.panelCalendario.add(new JPanel());
        this.calendario.addPropertyChangeListener(this);
        this.listaEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.listaEventos.addListSelectionListener(this);


        this.add(this.panelBotones, BorderLayout.NORTH);
        this.add(this.panelCalendario, BorderLayout.CENTER);
        this.add(new JPanel(), BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonAgregar) {
            FrameNuevoEvento newEvent = new FrameNuevoEvento();
            newEvent.setVisible(true);
            newEvent.setSize(700, 560);
            newEvent.setLocationRelativeTo(null);
            newEvent.setIconImage(new ImageIcon("src/desktop/resources/calendario.png").getImage());
            newEvent.setResizable(false);
        }

        if (e.getSource() == this.botonEditar) {
            ArrayList<CalendarCachedMessage> cal = new ArrayList<CalendarCachedMessage>();
            try {
                cal = (ArrayList<CalendarCachedMessage>) GsonSerializer.deSerialize(GsonSerializer.calendarDestination);
            } catch (Exception ex) {
            } finally {
                String fecha = this.calendario.getDate().getDate() + "-" + (this.calendario.getDate().getMonth() + 1) + "-" + (this.calendario.getDate().getYear() + 1900);
                int counter = this.listaEventos.getSelectedIndex();
                for (CalendarCachedMessage c : cal) {
                    if (c.getFecha().equals(fecha)) {
                        if (counter == 0) {
                            c.index = cal.indexOf(c);
                            FrameNuevoEvento newEvent = new FrameNuevoEvento(c);
                            newEvent.setVisible(true);
                            newEvent.setSize(700, 560);
                            newEvent.setLocationRelativeTo(null);
                            newEvent.setIconImage(new ImageIcon("src/desktop/resources/calendario.png").getImage());
                            newEvent.setResizable(false);
                            break;
                        }
                        else{
                            counter--;
                        }
                    }
                }
            }
        }

        if (e.getSource() == this.botonBorrar) {
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (e.getSource() == this.calendario) {
            String fecha = this.calendario.getDate().getDate() + "-" + (this.calendario.getDate().getMonth() + 1) + "-" + (this.calendario.getDate().getYear() + 1900);
            System.out.println("fecha:" + fecha);
            ArrayList<CalendarCachedMessage> cal = new ArrayList<CalendarCachedMessage>();
            try {
                cal = (ArrayList<CalendarCachedMessage>) GsonSerializer.deSerialize(GsonSerializer.calendarDestination);
            } catch (Exception ex) {
                        System.out.println("hola, vengo a flotar!");
            } finally {
                this.botonEditar.setEnabled(false);
                this.listaEventos.setListData((new String[0]));
                ArrayList<String> events = new ArrayList<String>();
                for (CalendarCachedMessage c : cal) {
                    System.out.println(c.getFecha());
                    if (c.getFecha().equals(fecha)) {
                        System.out.println("EVENTO!");
                        c.index = cal.indexOf(c);
                        events.add(c.toString());
                    }
                }

                if (events.size() >= 1) {
                    System.out.println("EVENTOS CARGADOS!!" + events.toArray(new String[0])[0]);
                    this.listaEventos.setListData(events.toArray(events.toArray(new String[0])));
                    this.repaint();
                    this.listaEventos.setSelectedIndex(0);
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (this.listaEventos.getLastVisibleIndex() >= 0) {
            this.botonEditar.setEnabled(true);
        }
    }
}
