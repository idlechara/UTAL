package app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Proceso2 implements ActionListener 
{
    // Variable utilizada para guardar una referencia al objeto boton
    JButton botonRef;
 
    // Constructor que guarda la referencia al objeto boton
    Proceso2(JButton boton)
    {
        this.botonRef = boton;
    }

    public void actionPerformed(ActionEvent e)
    {
        System.out.println("Se presiono el boton 1");
    }
}