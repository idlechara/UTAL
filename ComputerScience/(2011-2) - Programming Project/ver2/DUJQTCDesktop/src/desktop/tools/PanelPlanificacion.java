/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop.tools;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author ve´ronica
 */
public class PanelPlanificacion extends JPanel implements ActionListener
{
    private JDateChooser fechaPlanificacion;
    private JComboBox<String> bloque;
    private JComboBox<String> salas;
    private boolean[] bloqueOcupado = {false,false,false,false,false,false,false,false,false,false,false};
    private JList<String> lista;
    private ArrayList<String> listData;
    private JButton botonQuitar;
    private JButton botonAgregar;
   

    public PanelPlanificacion() 
    {
        this.setLayout(new MigLayout("","[grow,fill]","[]"));
        this.createObjects();
    }

    private void createObjects() 
    {
        Cadenas c = new Cadenas();
        
        JPanel panel1 = new JPanel(new MigLayout("","[grow,fill]","[]"));
        JPanel panelref1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelref2 = new JPanel(new GridLayout(1, 1));
        JLabel label1 = new JLabel("Fecha planificacion hasta: ");
        this.fechaPlanificacion = new JDateChooser();
        this.fechaPlanificacion.setIcon(new ImageIcon ("src/desktop/resources/calendario.png"));
        panelref1.add(label1); panelref2.add(this.fechaPlanificacion);
        panel1.add(panelref1);panel1.add(panelref2);
        
        JPanel panel2 = new JPanel(new MigLayout("","[grow,fill]","[]"));
        JPanel panelref3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelref4 = new JPanel(new GridLayout(1, 1));
        JLabel label2 = new JLabel("Bloque: ");
        this.bloque = new JComboBox<String>(c.getBloques());
        panelref3.add(label2); panelref4.add(this.bloque);
        panel2.add(panelref3); panel2.add(panelref4);
        
        JPanel panel3 = new JPanel(new MigLayout("","[grow,fill]","[]"));
        JPanel panelref5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panelref6 = new JPanel(new GridLayout(1, 1));
        JLabel label3 = new JLabel("Sala:           ");
        this.salas = new JComboBox<String>(c.getSalas());
        panelref5.add(label3); panelref6.add(this.salas);
        panel3.add(panelref5); panel3.add(panelref6);
        
        JPanel panel4 = new JPanel(new BorderLayout());
        this.lista = new JList<>();
        this.listData = new ArrayList<String>();
        JScrollPane scroll = new JScrollPane(this.lista, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel panelBotones = new JPanel(new MigLayout());
        this.botonAgregar = new JButton(new ImageIcon("src/desktop/resources/add.png"));
        this.botonAgregar.addActionListener(this);
        this.botonQuitar = new JButton(new ImageIcon("src/desktop/resources/remove.png"));
        this.botonQuitar.addActionListener(this);
        panelBotones.add(this.botonAgregar,"wrap"); panelBotones.add(this.botonQuitar);
        panel4.add(scroll,BorderLayout.CENTER); panel4.add(panelBotones,BorderLayout.EAST);
        
        this.add(panel1,"wrap"); this.add(panel2,"wrap"); 
        this.add(panel3,"wrap"); this.add(panel4);
    }
    
    public String getSala()
    {
        return this.salas.getSelectedItem().toString();
    }
    
    public String getBloque()
    {
        return this.bloque.getSelectedItem().toString();
    }
    
    public String getFechaToString()
    {
        Date d = this.fechaPlanificacion.getDate();
        String dia = Integer.toString(d.getDate());String mes = Integer.toString(d.getMonth()+1);String año = Integer.toString(d.getYear()+1900);
        String date = dia + "/" + mes + "/" + año;
        System.out.println(date);
        return date;
    }
    
    public Date getFecha()
    {
        return this.fechaPlanificacion.getDate();
    }
    
    public void setFecha(Date d)
    {
        this.fechaPlanificacion.setDate(d);
    }
    
    public void cleanFields()
    {
        this.fechaPlanificacion.setDate(null);
        this.bloque.setSelectedIndex(0);
        this.salas.setSelectedIndex(0);
        this.listData.removeAll(listData);
        this.lista.setListData(listData.toArray(listData.toArray(new String[0])));
        this.lista.setSelectedIndex(-1);
    }
    
    public ArrayList<String> getListData()
    {
        return this.listData;
    }
    
    public void addData(JComboBox bloque, JComboBox sala) 
    {
        String s = "Bloque: " + bloque.getSelectedItem().toString() + "    -    " + "Sala: " + sala.getSelectedItem().toString() + "\n";
        this.listData.add(s);
        this.bloqueOcupado[bloque.getSelectedIndex()] = true;
        this.lista.setListData(listData.toArray(listData.toArray(new String[0])));
        this.lista.setSelectedIndex(this.listData.size() - 1);
        
    }
    
    public void removeData(int indice) 
    {
        this.listData.remove(indice);
        this.bloqueOcupado[indice] = false;
        this.lista.setListData(listData.toArray(listData.toArray(new String[0])));
        this.lista.setSelectedIndex(this.listData.size() - 1);
    }
    
    public void setData(ArrayList<String> a)
    {
        Iterator<String> i = a.iterator();
        ArrayList<String> reference = new ArrayList<>();
        while(i.hasNext())
        {
            String[] s = i.next().split("\n");
            for(String h: s)
            {
                char ch = h.charAt(8);
                int blo = ch-48;
                this.bloqueOcupado[blo-1] = true;
            }
            reference.addAll(Arrays.asList(s));
        }
        this.listData = reference;
        this.lista.setListData(listData.toArray(listData.toArray(new String[0])));
        this.lista.setSelectedIndex(this.listData.size() - 1);
    }
            
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.botonAgregar)
        {
            if(!this.bloqueOcupado[this.bloque.getSelectedIndex()])
            {
                this.addData(this.bloque, this.salas);
            }
        }
        
        if(e.getSource() == this.botonQuitar)
        {
            if(!this.listData.isEmpty())
            {
                this.removeData(this.lista.getSelectedIndex());
            }
        }
    }
}
