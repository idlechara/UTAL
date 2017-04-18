package app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Proceso1 implements WindowListener 
{
    // Variable utilizada para guardar una referencia al objeto Frame
    Frame ventanaRef;
 
    // Constructor que guarda la referencia al objeto Frame
    Proceso1(Frame vent)
    {
        this.ventanaRef = vent;
    }

    public void windowClosed( WindowEvent evt ) 
    {
        System.out.println( "Metodo windowClosed de Proceso1" );
    }
  
    public void windowIconified( WindowEvent evt ) 
    {
        System.out.println( "Metodo windowIconified de Proceso1" );  
    }
  
    public void windowOpened( WindowEvent evt ) 
    {
        System.out.println( "Metodo windowOpened de Proceso1" );
    }

    public void windowClosing( WindowEvent evt ) 
    {
        System.out.println( "Metodo windowClosing de Proceso1" );
        // Se oculta la ventana
        ventanaRef.setVisible( false ); 
    }

    public void windowDeiconified( WindowEvent evt ) 
    {
        System.out.println( "Metodo windowDeiconified Proceso1" );
    }

    public void windowActivated( WindowEvent evt ) 
    {
        System.out.println( "Metodo windowActivated de Proceso1" );
    }

    public void windowDeactivated( WindowEvent evt ) 
    {
        System.out.println( "Metodo windowDeactivated de Proceso1" );
    }
}