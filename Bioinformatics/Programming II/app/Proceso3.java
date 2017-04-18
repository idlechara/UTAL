package app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Proceso3 implements ActionListener 
{
    // Variable utilizada para guardar una referencia al objeto boton2
    JButton botonRef;
 
    // Constructor que guarda la referencia al objeto boton2
    Proceso3(JButton boton)
    {
        this.botonRef = boton;
    }

    public void actionPerformed(ActionEvent e) 
    {
        //Muestra el texto del comando asociado a esta acción (en es
        System.out.println(((JButton)e.getSource()).getActionCommand());
    }
}