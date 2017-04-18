package clases;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
* Clase PantallaSobreJTetravex
* Su funcion es mostrar una pantalla informativa sobre los desarrolladores del juego
*/
public class PantallaSobreJTetravex extends JPanel
{
    private ImageIcon sobreJTetravex;
    
    /**
     * El constructor inicializa los atributos declarados en la clase PantallaSobreJTtetravex
     * @param sobreJTetravex del tipo ImageIcon inserta una imagen a la pantalla de sobreJTetravex
     */
    public PantallaSobreJTetravex()
    {
        this.sobreJTetravex = new ImageIcon(getClass().getResource("/imagenes/sobreJTetravex.jpg"));
    }
    
    /**
     * El metodo paint es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(sobreJTetravex.getImage(), 0, 0, 950, 508, this);
    }
}