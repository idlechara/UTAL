package clases;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase BarraEstado
 * Su funcion es crear y controlar los parametros que esten presente en la barra de estado de Tetravex
 */
public class BarraEstado extends JPanel
{
    private JLabel tamaño;
    private JLabel tiempo;
    private Cronometro crono;
    
    /**
    * El constructor inicializa las variables declaradas en la clase BarraEstado
    * @param tamaño del tipo JLabel crea una etiqueta en la barra de estado que almacena el tamaño del juego
    * @param tiempo del tipo JLabel crea una etiqueta en la barra de estado que almacena y controla el tiempo que se esta empleando en la partida
    * @param cono del tipo Cronometro registra el tiempo que transcurre durante la partida
    */
    public BarraEstado()
    {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        this.tamaño = new JLabel("Jugando con un tablero de 3x3");
        this.add(this.tamaño);
        this.crono = new Cronometro(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                ((Cronometro)ae.getSource()).actualizaLabel();
            }
        });
        this.crono.start();
        this.tiempo = this.crono.getLabel();
        this.add(this.tiempo);
    }
    
    /**
     * El metodo actualizar controla el tamaño y el tiempo que se encuentra en la barra de estado
     * @param tamaño del tipo int almacena el tamaño del tablero de la partida
     * @param resetear del tipo int resetea la partida actual
     * @param pausar del tipo int pausa la partida actual
     */
    public void actualizar(int tamaño, int resetear, int pausar)
    {
        this.remove(this.tamaño);
        this.tamaño.setText("Jugando con un tablero de " + tamaño + "x" + tamaño);
        this.add(this.tamaño);
        if(resetear == 1)
        {
            this.remove(this.tiempo);
            this.crono = new Cronometro(1000, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    ((Cronometro)ae.getSource()).actualizaLabel();
                }
            });
            this.crono.start();
        }
        if(pausar == 1)
        {
            this.crono.stop();
        }
        else if(pausar == 0)
        {
            this.crono.start();
        }
        this.tiempo = this.crono.getLabel();
        this.add(this.tiempo);
        this.repaint();
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo crono
     * @return crono El metodo retorna el valor que se encuentra almacenado en el atributo crono
     */
    public Cronometro getCrono()
    {
        return this.crono;
    }
}