package clases;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
* Clase VentanaSobreJTetravex
* Su funcion es crear una ventana que muestra la informacion de sobre JTetravex
*/
public class VentanaSobreJTetravex extends JFrame
{
    private PantallaSobreJTetravex pantallaSobreJTetravex;
    
    /**
     * El constructor inicializa los atributos declarados en la clase VentanaSobreJTetravex
     * @param pantallaSobreJTetravex del tipo PantallaSobreJTetravex crea una pantalla de sobre JTetravex
     */
    public VentanaSobreJTetravex()
    {
        this.setTitle("Sobre JTetravex");
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/ventanaIcono.png")).getImage());
        this.setSize(950, 537);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pantallaSobreJTetravex = new PantallaSobreJTetravex();
        this.add(this.pantallaSobreJTetravex, BorderLayout.CENTER);
    }
}