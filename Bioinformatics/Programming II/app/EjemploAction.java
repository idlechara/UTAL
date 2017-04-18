package app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class EjemploAction
{
    JFrame ventana;
    JButton boton;
    JButton boton2;
    JButton boton3;
    
    public EjemploAction()
    {
      ventana = new JFrame("Ventana");
      boton = new JButton("Boton 1");
      boton2 = new JButton("2");
      
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ventana.getContentPane().setLayout(new GridLayout(2,1));
      
      ventana.getContentPane().add(boton);
      ventana.getContentPane().add(boton2);
      
      // Se instancian tres objetos receptores que procesaran los
      // eventos de la ventana y los botones
      Proceso1 ventanaProceso1 = new Proceso1(ventana);
      Proceso2 botonProceso2 = new Proceso2(boton);
      Proceso3 boton2Proceso3 = new Proceso3(boton2);
      
      // Se registran los tres objetos receptores para que sean 
      // notificados de los eventos que generan la ventana, y los botones 
      //que son los objetos origen de los eventos
      ventana.addWindowListener(ventanaProceso1);
      boton.addActionListener(botonProceso2);
      boton2.addActionListener(boton2Proceso3);
      
      //Se muestra la ventana
      ventana.pack();
      ventana.setVisible(true);
    }
}






