package clases;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
* Clase VentanaComoJugar
* Su funcion es crear una ventana que muestra la informacion de como jugar
*/
public class VentanaComoJugar extends JFrame
{
    private PantallaComoJugar pantallaComoJugar;
    
    /**
     * El constructor inicializa los atributos declarados en la clase VentanaComoJugar
     * @param pantallaComoJugar del tipo PantallaComoJugar crea una pantalla de como jugar
     */
    public VentanaComoJugar()
    {
        this.setTitle("Como Jugar");
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/ventanaIcono.png")).getImage());
        this.setSize(1000, 537);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pantallaComoJugar = new PantallaComoJugar();
        this.add(this.pantallaComoJugar, BorderLayout.CENTER);
    }
}