package clases;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Clase Ventana
 * Su funcion es crear la ventana donde se ejecuta el juego
 */
public class Ventana extends JFrame
{
    private BarraMenu barraMenu;
    private Pantalla pantalla;
    private BarraEstado barraEstado;
    private Registro registro;
    
    /**
     * El constructor inicializa los atributos declarados en la clase Ventana
     * @param barraMenu del tipo BarraMenu crea una barra en la ventana donde se almacenas las opciones del menu
     * @param pantalla del tipo Pantalla crea una nueva pantalla
     * @param barraEstado del tipo barraEstado crea una nueva barra de estado en la pantalla
     * @param registro del tipo Registro crea un nuevo registro de la partida
     */
    public Ventana()
    {
        this.setTitle("JTetravex");
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/ventanaIcono.png")).getImage());
        this.setSize(1024, 576);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.barraMenu = new BarraMenu();
        this.pantalla = new Pantalla();
        this.barraEstado = new BarraEstado();
        this.add(this.barraMenu, BorderLayout.PAGE_START);
        this.add(this.pantalla, BorderLayout.CENTER);
        this.add(this.barraEstado, BorderLayout.PAGE_END);
        
        this.registro = new Registro();
        this.registro = this.registro.abrir();
        
        if(this.registro == null)
        {
            this.registro = new Registro();
        }
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo barraMenu
     * @return barraMenu El metodo retorna el contenido almacenado en barraMenu
     */ 
    public BarraMenu getBarraMenu()
    {
        return this.barraMenu;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo pantalla
     * @return pantalla El metodo retorna el contenido almacenado en pantalla
     */ 
    public Pantalla getPantalla()
    {
        return this.pantalla;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo barraEstado
     * @return barraEstado El metodo retorna el contenido almacenado en barraEstado
     */ 
    public BarraEstado getBarraEstado()
    {
        return this.barraEstado;
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo registro
     * @return registro El metodo retorna el contenido almacenado en registro
     */ 
    public Registro getRegistro()
    {
        return this.registro;
    }
}