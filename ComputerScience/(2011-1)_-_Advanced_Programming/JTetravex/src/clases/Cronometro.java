
package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Clase Cronometro
 * Su funcion es controlar el tiempo quese emplea durante el juego
 */
public class Cronometro extends Timer
{
    private JLabel tiempo;
    private int hora, minutos, segundos;
    
    /**
     * El constructor inicializa las variables declaradas en la clase Cronometro
     * @param tiempo del tipo JLabel almacena el tiempo de la partida
     * @param hora del tipo int almacena las horas que transcurren en la partida
     * @param minutos del tipo int almacena los minutos que transcurren en la partida
     * @param segundos del tipo int almacena los segundos que transcurren en la partida
     */
    public Cronometro(int tiempo, ActionListener ae)
    {
        super(tiempo,ae);
        this.hora=0;
        this.minutos=0;
        this.segundos=0;
        this.tiempo = new JLabel();
        this.tiempo.setText("Tiempo: 00:00:00");    
    }
    
    /**
     * El metodo actualizarLabel actualiza el tiempo que transucrre durante el juego
     */
    public void actualizaLabel()
    {
        this.segundos++;
        if(segundos%60==0)
        {
            this.minutos++;
            this.segundos=0;
            if(this.minutos%60==0)
            {
                this.hora++;
                this.minutos=0;
            }
        }
        String s, m, h;
        if(this.segundos<10)
            s="0"+this.segundos;
        else
            s=""+this.segundos;
        if(this.minutos<10)
            m="0"+this.minutos;
        else
            m=""+this.minutos;
        if(this.hora<10)
            h="0"+this.hora;
        else
            h=""+this.hora;
        this.tiempo.setText("Tiempo: "+h+":"+m+":"+s);
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo tiempo
     * @return tiempo El metodo retorna el valor que se encuentra almacenado en el atributo tiempo
     */
    public JLabel getLabel()
    {
        return this.tiempo;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo hora
     * @return hora El metodo retorna el valor que se encuentra almacenado en el atributo hora
     */
    public int getHora()
    {
        return this.hora;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo minutos
     * @return minutos El metodo retorna el valor que se encuentra almacenado en el atributo minutos
     */
    public int getMinutos()
    {
        return this.minutos;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo segundos
     * @return segundos El metodo retorna el valor que se encuentra almacenado en el atributo segundos
     */
    public int getSegundos()
    {
        return this.segundos;
    }
}