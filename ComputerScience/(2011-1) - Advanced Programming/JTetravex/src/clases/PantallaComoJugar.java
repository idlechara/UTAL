package clases;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
* Clase PantallaComoJugar
* Su funcion es mostrar una pantalla informativa que enseña como jugar JTetravex
*/
public class PantallaComoJugar extends JPanel
{
    private ImageIcon comoJugar;
    
    /**
     * El constructor inicializa los atributos declarados en la clase PantallaComoJugar
     * @param comoJugar del tipo ImageIcon inserta una imagen a la pantalla de comoJugar
     */
    public PantallaComoJugar()
    {
        this.comoJugar = new ImageIcon(getClass().getResource("/imagenes/comoJugar.jpg"));
    }
    
    /**
     * El metodo paint es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(comoJugar.getImage(), 0, 0, 1018, 508, this);
    }
}