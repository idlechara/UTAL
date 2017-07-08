package clases;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase Pantalla
 * Su funcion es crear las imagenes de la pantalla del juego
 */
public class Pantalla extends JPanel implements MouseMotionListener, MouseListener
{
    private ImageIcon textoNuevoJuego;
    private ImageIcon fondo;
    private ImageIcon piezaFondo1;
    private ImageIcon piezaFondo2;
    private ImageIcon piezaSuperior0;
    private ImageIcon piezaSuperior1;
    private ImageIcon piezaSuperior2;
    private ImageIcon piezaSuperior3;
    private ImageIcon piezaSuperior4;
    private ImageIcon piezaSuperior5;
    private ImageIcon piezaSuperior6;
    private ImageIcon piezaSuperior7;
    private ImageIcon piezaSuperior8;
    private ImageIcon piezaSuperior9;
    private ImageIcon piezaInferior0;
    private ImageIcon piezaInferior1;
    private ImageIcon piezaInferior2;
    private ImageIcon piezaInferior3;
    private ImageIcon piezaInferior4;
    private ImageIcon piezaInferior5;
    private ImageIcon piezaInferior6;
    private ImageIcon piezaInferior7;
    private ImageIcon piezaInferior8;
    private ImageIcon piezaInferior9;
    private ImageIcon piezaIzquierda0;
    private ImageIcon piezaIzquierda1;
    private ImageIcon piezaIzquierda2;
    private ImageIcon piezaIzquierda3;
    private ImageIcon piezaIzquierda4;
    private ImageIcon piezaIzquierda5;
    private ImageIcon piezaIzquierda6;
    private ImageIcon piezaIzquierda7;
    private ImageIcon piezaIzquierda8;
    private ImageIcon piezaIzquierda9;
    private ImageIcon piezaDerecha0;
    private ImageIcon piezaDerecha1;
    private ImageIcon piezaDerecha2;
    private ImageIcon piezaDerecha3;
    private ImageIcon piezaDerecha4;
    private ImageIcon piezaDerecha5;
    private ImageIcon piezaDerecha6;
    private ImageIcon piezaDerecha7;
    private ImageIcon piezaDerecha8;
    private ImageIcon piezaDerecha9;
    private ImageIcon fondoPausa;
    private ImageIcon textoPausa;
    private ImageIcon piezaSeleccionada;
    private Sistema sistema;
    private int click;
    private int x;
    private int y;
    
    /**
     * El constructor inicializa los atributos declarados en la clase Pantalla
     * @param textoNuevoJuego
     * @param fondo del tipo ImageIcon inserta el fondo en la pantalla del juego
     * @param piezaFondo1 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaFondo2 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaSuperior0 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaSuperior1 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaSuperior2 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaSuperior3 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaSuperior4 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaSuperior5 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaSuperior6 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaSuperior7 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaSuperior8 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaSuperior9 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaInferior0 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaInferior1 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaInferior2 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaInferior3 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaInferior4 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaInferior5 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaInferior6 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaInferior7 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaInferior8 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaInferior9 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaIzquierda0 del tipo ImageIcon inserta una pieza a la pantalla del juego 
     * @param piezaIzquierda1 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaIzquierda2 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaIzquierda3 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaIzquierda4 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaIzquierda5 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaIzquierda6 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaIzquierda7 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaIzquierda8 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaIzquierda9 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaDerecha0 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaDerecha1 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaDerecha2 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaDerecha3 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaDerecha4 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaDerecha5 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaDerecha6 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaDerecha7 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaDerecha8 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param piezaDerecha9 del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param fondoPausa del tipo ImageIcon inserta una pieza a la pantalla del juego
     * @param textoPausa del tipo ImageIcon muestra el dialogo de pausa en el juego
     * @param piezaSeleccionada del tipo ImageIcon muestra el dialogo de pausa en el juego
     * @param sistema del tipo Sistema es un objeto que inicializa los procesos que se muestran por pantalla
     * @param click del tipo int almacena el estado del click
     * @param x del tipo int almacena la posicion del click
     * @param y del tipo int almacena la posicion del click
     */
    public Pantalla()
    {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.textoNuevoJuego = new ImageIcon(getClass().getResource("/imagenes/textoNuevoJuego.png"));
        this.fondo = new ImageIcon(getClass().getResource("/imagenes/pantallaFondo.jpg"));
        this.piezaFondo1 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaFondo1.png"));
        this.piezaFondo2 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaFondo2.jpg"));
        this.piezaSuperior0 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSuperior0.png"));
        this.piezaSuperior1 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSuperior1.png"));
        this.piezaSuperior2 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSuperior2.png"));
        this.piezaSuperior3 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSuperior3.png"));
        this.piezaSuperior4 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSuperior4.png"));
        this.piezaSuperior5 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSuperior5.png"));
        this.piezaSuperior6 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSuperior6.png"));
        this.piezaSuperior7 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSuperior7.png"));
        this.piezaSuperior8 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSuperior8.png"));
        this.piezaSuperior9 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSuperior9.png"));
        this.piezaInferior0 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaInferior0.png"));
        this.piezaInferior1 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaInferior1.png"));
        this.piezaInferior2 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaInferior2.png"));
        this.piezaInferior3 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaInferior3.png"));
        this.piezaInferior4 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaInferior4.png"));
        this.piezaInferior5 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaInferior5.png"));
        this.piezaInferior6 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaInferior6.png"));
        this.piezaInferior7 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaInferior7.png"));
        this.piezaInferior8 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaInferior8.png"));
        this.piezaInferior9 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaInferior9.png"));
        this.piezaIzquierda0 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaIzquierda0.png"));
        this.piezaIzquierda1 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaIzquierda1.png"));
        this.piezaIzquierda2 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaIzquierda2.png"));
        this.piezaIzquierda3 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaIzquierda3.png"));
        this.piezaIzquierda4 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaIzquierda4.png"));
        this.piezaIzquierda5 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaIzquierda5.png"));
        this.piezaIzquierda6 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaIzquierda6.png"));
        this.piezaIzquierda7 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaIzquierda7.png"));
        this.piezaIzquierda8 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaIzquierda8.png"));
        this.piezaIzquierda9 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaIzquierda9.png"));
        this.piezaDerecha0 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaDerecha0.png"));
        this.piezaDerecha1 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaDerecha1.png"));
        this.piezaDerecha2 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaDerecha2.png"));
        this.piezaDerecha3 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaDerecha3.png"));
        this.piezaDerecha4 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaDerecha4.png"));
        this.piezaDerecha5 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaDerecha5.png"));
        this.piezaDerecha6 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaDerecha6.png"));
        this.piezaDerecha7 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaDerecha7.png"));
        this.piezaDerecha8 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaDerecha8.png"));
        this.piezaDerecha9 = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaDerecha9.png"));
        this.fondoPausa = new ImageIcon(getClass().getResource("/imagenes/pantallaFondoPausa.png"));
        this.textoPausa = new ImageIcon(getClass().getResource("/imagenes/pantallaTextoPausa.png"));
        this.piezaSeleccionada = new ImageIcon(getClass().getResource("/imagenes/pantallaPiezaSeleccionada.png"));
        this.sistema = new Sistema();
        this.click = 0;
        this.x = 0;
        this.y = 0;
        this.repaint();
    }
    /**
     * El metodo paint es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(fondo.getImage(), 0, 0, 1018, 508, this);
        //JUEGO...
        if(this.sistema.getTamaño() == 2)
        {
            for(int i = 0; i < this.sistema.getTamaño(); i++)
            {
                for(int j = 0; j < this.sistema.getTamaño(); j++)
                {
                    //CUADRO IZQUIERDO...
                    g.drawImage(piezaFondo1.getImage(), ((j*204)+50), ((i*204)+50), 204, 204, this);
                    if(this.sistema.getPiezaMatrizFinal(i, j) != null)
                    {
                        g.drawImage(piezaFondo2.getImage(), ((j*204)+50), ((i*204)+50), 204, 204, this);
                        //SUPERIOR...
                        if(sistema.getPiezaMatrizFinal(i, j).getSuperior() == 0)
                        {
                            g.drawImage(piezaSuperior0.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);                       
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getSuperior() == 2)
                        {
                            g.drawImage(piezaSuperior2.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);              
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getSuperior() == 3)
                        {
                            g.drawImage(piezaSuperior3.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getSuperior() == 4)
                        {
                            g.drawImage(piezaSuperior4.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getSuperior() == 5)
                        {
                            g.drawImage(piezaSuperior5.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getSuperior() == 6)
                        {
                            g.drawImage(piezaSuperior6.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);        
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getSuperior() == 7)
                        {
                            g.drawImage(piezaSuperior7.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getSuperior() == 8)
                        {
                            g.drawImage(piezaSuperior8.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getSuperior() == 9)
                        {
                            g.drawImage(piezaSuperior9.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        //INFERIOR...
                        if(sistema.getPiezaMatrizFinal(i, j).getInferior() == 0)
                        {
                            g.drawImage(piezaInferior0.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);                       
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getInferior() == 1)
                        {
                            g.drawImage(piezaInferior1.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getInferior() == 2)
                        {
                            g.drawImage(piezaInferior2.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);              
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getInferior() == 3)
                        {
                            g.drawImage(piezaInferior3.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getInferior() == 4)
                        {
                            g.drawImage(piezaInferior4.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getInferior() == 5)
                        {
                            g.drawImage(piezaInferior5.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getInferior() == 6)
                        {
                            g.drawImage(piezaInferior6.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);        
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getInferior() == 7)
                        {
                            g.drawImage(piezaInferior7.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getInferior() == 8)
                        {
                            g.drawImage(piezaInferior8.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getInferior() == 9)
                        {
                            g.drawImage(piezaInferior9.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        //IZQUIERDA...
                        if(sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 0)
                        {
                            g.drawImage(piezaIzquierda0.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);                       
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 1)
                        {
                            g.drawImage(piezaIzquierda1.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 2)
                        {
                            g.drawImage(piezaIzquierda2.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);              
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 3)
                        {
                            g.drawImage(piezaIzquierda3.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 4)
                        {
                            g.drawImage(piezaIzquierda4.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 5)
                        {
                            g.drawImage(piezaIzquierda5.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 6)
                        {
                            g.drawImage(piezaIzquierda6.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);        
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 7)
                        {
                            g.drawImage(piezaIzquierda7.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 8)
                        {
                            g.drawImage(piezaIzquierda8.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 9)
                        {
                            g.drawImage(piezaIzquierda9.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        //DERECHA...
                        if(sistema.getPiezaMatrizFinal(i, j).getDerecha() == 0)
                        {
                            g.drawImage(piezaDerecha0.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);                       
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getDerecha() == 1)
                        {
                            g.drawImage(piezaDerecha1.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getDerecha() == 2)
                        {
                            g.drawImage(piezaDerecha2.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);              
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getDerecha() == 3)
                        {
                            g.drawImage(piezaDerecha3.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getDerecha() == 4)
                        {
                            g.drawImage(piezaDerecha4.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getDerecha() == 5)
                        {
                            g.drawImage(piezaDerecha5.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getDerecha() == 6)
                        {
                            g.drawImage(piezaDerecha6.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);        
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getDerecha() == 7)
                        {
                            g.drawImage(piezaDerecha7.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getDerecha() == 8)
                        {
                            g.drawImage(piezaDerecha8.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizFinal(i, j).getDerecha() == 9)
                        {
                            g.drawImage(piezaDerecha9.getImage(), ((j*204)+55), ((i*204)+55), 194, 194, this);
                        }
                        if(this.sistema.getMatrizActual() == 1)
                        {
                            g.drawImage(piezaSeleccionada.getImage(), ((this.sistema.getJPieza()*204)+50), ((this.sistema.getIPieza()*204)+50), 204, 204, this);
                        }
                    }
                    //CUADRO DERECHO...
                    g.drawImage(piezaFondo1.getImage(), ((j*204)+558), ((i*204)+50), 204, 204, this);
                    if(this.sistema.getPiezaMatrizDesordenada(i, j) != null)
                    {
                        g.drawImage(piezaFondo2.getImage(), ((j*204)+558), ((i*204)+50), 204, 204, this);
                        //SUPERIOR...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 0)
                        {
                            g.drawImage(piezaSuperior0.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 2)
                        {
                            g.drawImage(piezaSuperior2.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 3)
                        {
                            g.drawImage(piezaSuperior3.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 4)
                        {
                            g.drawImage(piezaSuperior4.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 5)
                        {
                            g.drawImage(piezaSuperior5.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 6)
                        {
                            g.drawImage(piezaSuperior6.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 7)
                        {
                            g.drawImage(piezaSuperior7.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 8)
                        {
                            g.drawImage(piezaSuperior8.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 9)
                        {
                            g.drawImage(piezaSuperior9.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        //INFERIOR...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 0)
                        {
                            g.drawImage(piezaInferior0.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 1)
                        {
                            g.drawImage(piezaInferior1.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 2)
                        {
                            g.drawImage(piezaInferior2.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 3)
                        {
                            g.drawImage(piezaInferior3.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 4)
                        {
                            g.drawImage(piezaInferior4.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 5)
                        {
                            g.drawImage(piezaInferior5.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 6)
                        {
                            g.drawImage(piezaInferior6.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 7)
                        {
                            g.drawImage(piezaInferior7.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 8)
                        {
                            g.drawImage(piezaInferior8.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 9)
                        {
                            g.drawImage(piezaInferior9.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        //IZQUIERDA...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 0)
                        {
                            g.drawImage(piezaIzquierda0.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 1)
                        {
                            g.drawImage(piezaIzquierda1.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 2)
                        {
                            g.drawImage(piezaIzquierda2.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 3)
                        {
                            g.drawImage(piezaIzquierda3.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 4)
                        {
                            g.drawImage(piezaIzquierda4.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 5)
                        {
                            g.drawImage(piezaIzquierda5.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 6)
                        {
                            g.drawImage(piezaIzquierda6.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 7)
                        {
                            g.drawImage(piezaIzquierda7.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 8)
                        {
                            g.drawImage(piezaIzquierda8.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 9)
                        {
                            g.drawImage(piezaIzquierda9.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        //DERECHA...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 0)
                        {
                            g.drawImage(piezaDerecha0.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 1)
                        {
                            g.drawImage(piezaDerecha1.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 2)
                        {
                            g.drawImage(piezaDerecha2.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 3)
                        {
                            g.drawImage(piezaDerecha3.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 4)
                        {
                            g.drawImage(piezaDerecha4.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 5)
                        {
                            g.drawImage(piezaDerecha5.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 6)
                        {
                            g.drawImage(piezaDerecha6.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 7)
                        {
                            g.drawImage(piezaDerecha7.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 8)
                        {
                            g.drawImage(piezaDerecha8.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 9)
                        {
                            g.drawImage(piezaDerecha9.getImage(), ((j*204)+563), ((i*204)+55), 194, 194, this);
                        }
                        if(this.sistema.getMatrizActual() == 2)
                        {
                            g.drawImage(piezaSeleccionada.getImage(), ((this.sistema.getJPieza()*204)+558), ((this.sistema.getIPieza()*204)+50), 204, 204, this);
                        }
                    }
                }
            }
        }
        else if(this.sistema.getTamaño() == 3)
        {
            for(int i = 0; i < this.sistema.getTamaño(); i++)
            {
                for(int j = 0; j < this.sistema.getTamaño(); j++)
                {
                    //CUADRO IZQUIERDO...
                    g.drawImage(piezaFondo1.getImage(), ((j*136)+50), ((i*136)+50), 136, 136, this);
                    if(this.sistema.getPiezaMatrizFinal(i, j) != null)
                    {
                        g.drawImage(piezaFondo2.getImage(), ((j*136)+50), ((i*136)+50), 136, 136, this);
                        //SUPERIOR...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 0)
                        {
                            g.drawImage(piezaSuperior0.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 2)
                        {
                            g.drawImage(piezaSuperior2.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 3)
                        {
                            g.drawImage(piezaSuperior3.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 4)
                        {
                            g.drawImage(piezaSuperior4.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 5)
                        {
                            g.drawImage(piezaSuperior5.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 6)
                        {
                            g.drawImage(piezaSuperior6.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 7)
                        {
                            g.drawImage(piezaSuperior7.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 8)
                        {
                            g.drawImage(piezaSuperior8.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 9)
                        {
                            g.drawImage(piezaSuperior9.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        //INFERIOR...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 0)
                        {
                            g.drawImage(piezaInferior0.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 1)
                        {
                            g.drawImage(piezaInferior1.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 2)
                        {
                            g.drawImage(piezaInferior2.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 3)
                        {
                            g.drawImage(piezaInferior3.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 4)
                        {
                            g.drawImage(piezaInferior4.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 5)
                        {
                            g.drawImage(piezaInferior5.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 6)
                        {
                            g.drawImage(piezaInferior6.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 7)
                        {
                            g.drawImage(piezaInferior7.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 8)
                        {
                            g.drawImage(piezaInferior8.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 9)
                        {
                            g.drawImage(piezaInferior9.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        //IZQUIERDA...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 0)
                        {
                            g.drawImage(piezaIzquierda0.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 1)
                        {
                            g.drawImage(piezaIzquierda1.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 2)
                        {
                            g.drawImage(piezaIzquierda2.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 3)
                        {
                            g.drawImage(piezaIzquierda3.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 4)
                        {
                            g.drawImage(piezaIzquierda4.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 5)
                        {
                            g.drawImage(piezaIzquierda5.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 6)
                        {
                            g.drawImage(piezaIzquierda6.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 7)
                        {
                            g.drawImage(piezaIzquierda7.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 8)
                        {
                            g.drawImage(piezaIzquierda8.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 9)
                        {
                            g.drawImage(piezaIzquierda9.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        //DERECHA...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 0)
                        {
                            g.drawImage(piezaDerecha0.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 1)
                        {
                            g.drawImage(piezaDerecha1.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 2)
                        {
                            g.drawImage(piezaDerecha2.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 3)
                        {
                            g.drawImage(piezaDerecha3.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 4)
                        {
                            g.drawImage(piezaDerecha4.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 5)
                        {
                            g.drawImage(piezaDerecha5.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 6)
                        {
                            g.drawImage(piezaDerecha6.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 7)
                        {
                            g.drawImage(piezaDerecha7.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 8)
                        {
                            g.drawImage(piezaDerecha8.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 9)
                        {
                            g.drawImage(piezaDerecha9.getImage(), ((j*136)+55), ((i*136)+55), 126, 126, this);
                        }
                        if(this.sistema.getMatrizActual() == 1)
                        {
                            g.drawImage(piezaSeleccionada.getImage(), ((this.sistema.getJPieza()*136)+50), ((this.sistema.getIPieza()*136)+50), 136, 136, this);
                        }
                    }
                    //CUADRO DERECHO...
                    g.drawImage(piezaFondo1.getImage(), ((j*136)+558), ((i*136)+50), 136, 136, this);
                    if(this.sistema.getPiezaMatrizDesordenada(i, j) != null)
                    {
                        g.drawImage(piezaFondo2.getImage(), ((j*136)+558), ((i*136)+50), 136, 136, this);
                        //SUPERIOR...
                        if(this.sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 0)
                        {
                            g.drawImage(piezaSuperior0.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 2)
                        {
                            g.drawImage(piezaSuperior2.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 3)
                        {
                            g.drawImage(piezaSuperior3.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 4)
                        {
                            g.drawImage(piezaSuperior4.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 5)
                        {
                            g.drawImage(piezaSuperior5.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 6)
                        {
                            g.drawImage(piezaSuperior6.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 7)
                        {
                            g.drawImage(piezaSuperior7.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 8)
                        {
                            g.drawImage(piezaSuperior8.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(this.sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 9)
                        {
                            g.drawImage(piezaSuperior9.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        //INFERIOR...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 0)
                        {
                            g.drawImage(piezaInferior0.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 1)
                        {
                            g.drawImage(piezaInferior1.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 2)
                        {
                            g.drawImage(piezaInferior2.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 3)
                        {
                            g.drawImage(piezaInferior3.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 4)
                        {
                            g.drawImage(piezaInferior4.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 5)
                        {
                            g.drawImage(piezaInferior5.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 6)
                        {
                            g.drawImage(piezaInferior6.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 7)
                        {
                            g.drawImage(piezaInferior7.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 8)
                        {
                            g.drawImage(piezaInferior8.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 9)
                        {
                            g.drawImage(piezaInferior9.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        //IZQUIERDA...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 0)
                        {
                            g.drawImage(piezaIzquierda0.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 1)
                        {
                            g.drawImage(piezaIzquierda1.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 2)
                        {
                            g.drawImage(piezaIzquierda2.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 3)
                        {
                            g.drawImage(piezaIzquierda3.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 4)
                        {
                            g.drawImage(piezaIzquierda4.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 5)
                        {
                            g.drawImage(piezaIzquierda5.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 6)
                        {
                            g.drawImage(piezaIzquierda6.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 7)
                        {
                            g.drawImage(piezaIzquierda7.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 8)
                        {
                            g.drawImage(piezaIzquierda8.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 9)
                        {
                            g.drawImage(piezaIzquierda9.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        //DERECHA...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 0)
                        {
                            g.drawImage(piezaDerecha0.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 1)
                        {
                            g.drawImage(piezaDerecha1.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 2)
                        {
                            g.drawImage(piezaDerecha2.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 3)
                        {
                            g.drawImage(piezaDerecha3.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 4)
                        {
                            g.drawImage(piezaDerecha4.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 5)
                        {
                            g.drawImage(piezaDerecha5.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 6)
                        {
                            g.drawImage(piezaDerecha6.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 7)
                        {
                            g.drawImage(piezaDerecha7.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 8)
                        {
                            g.drawImage(piezaDerecha8.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 9)
                        {
                            g.drawImage(piezaDerecha9.getImage(), ((j*136)+563), ((i*136)+55), 126, 126, this);
                        }
                        if(this.sistema.getMatrizActual() == 2)
                        {
                            g.drawImage(piezaSeleccionada.getImage(), ((this.sistema.getJPieza()*136)+558), ((this.sistema.getIPieza()*136)+50), 136, 136, this);
                        }
                    }
                }
            }
        }
        else if(this.sistema.getTamaño() == 4)
        {
            for(int i = 0; i < this.sistema.getTamaño(); i++)
            {
                for(int j = 0; j < this.sistema.getTamaño(); j++)
                {
                    //CUADRO IZQUIERDO...
                    g.drawImage(piezaFondo1.getImage(), ((j*102)+50), ((i*102)+50), 102, 102, this);
                    if(this.sistema.getPiezaMatrizFinal(i, j) != null)
                    {
                        g.drawImage(piezaFondo2.getImage(), ((j*102)+50), ((i*102)+50), 102, 102, this);
                        //SUPERIOR...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 0)
                        {
                            g.drawImage(piezaSuperior0.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 2)
                        {
                            g.drawImage(piezaSuperior2.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 3)
                        {
                            g.drawImage(piezaSuperior3.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 4)
                        {
                            g.drawImage(piezaSuperior4.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 5)
                        {
                            g.drawImage(piezaSuperior5.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 6)
                        {
                            g.drawImage(piezaSuperior6.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 7)
                        {
                            g.drawImage(piezaSuperior7.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 8)
                        {
                            g.drawImage(piezaSuperior8.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 9)
                        {
                            g.drawImage(piezaSuperior9.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        //INFERIOR...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 0)
                        {
                            g.drawImage(piezaInferior0.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 1)
                        {
                            g.drawImage(piezaInferior1.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 2)
                        {
                            g.drawImage(piezaInferior2.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 3)
                        {
                            g.drawImage(piezaInferior3.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 4)
                        {
                            g.drawImage(piezaInferior4.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 5)
                        {
                            g.drawImage(piezaInferior5.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 6)
                        {
                            g.drawImage(piezaInferior6.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 7)
                        {
                            g.drawImage(piezaInferior7.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 8)
                        {
                            g.drawImage(piezaInferior8.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 9)
                        {
                            g.drawImage(piezaInferior9.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        //IZQUIERDA...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 0)
                        {
                            g.drawImage(piezaIzquierda0.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 1)
                        {
                            g.drawImage(piezaIzquierda1.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 2)
                        {
                            g.drawImage(piezaIzquierda2.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 3)
                        {
                            g.drawImage(piezaIzquierda3.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 4)
                        {
                            g.drawImage(piezaIzquierda4.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 5)
                        {
                            g.drawImage(piezaIzquierda5.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 6)
                        {
                            g.drawImage(piezaIzquierda6.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 7)
                        {
                            g.drawImage(piezaIzquierda7.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 8)
                        {
                            g.drawImage(piezaIzquierda8.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 9)
                        {
                            g.drawImage(piezaIzquierda9.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        //DERECHA...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 0)
                        {
                            g.drawImage(piezaDerecha0.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 1)
                        {
                            g.drawImage(piezaDerecha1.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 2)
                        {
                            g.drawImage(piezaDerecha2.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 3)
                        {
                            g.drawImage(piezaDerecha3.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 4)
                        {
                            g.drawImage(piezaDerecha4.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 5)
                        {
                            g.drawImage(piezaDerecha5.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 6)
                        {
                            g.drawImage(piezaDerecha6.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 7)
                        {
                            g.drawImage(piezaDerecha7.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 8)
                        {
                            g.drawImage(piezaDerecha8.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 9)
                        {
                            g.drawImage(piezaDerecha9.getImage(), ((j*102)+55), ((i*102)+55), 92, 92, this);
                        }
                        if(this.sistema.getMatrizActual() == 1)
                        {
                            g.drawImage(piezaSeleccionada.getImage(), ((this.sistema.getJPieza()*102)+50), ((this.sistema.getIPieza()*102)+50), 102, 102, this);
                        }
                    }
                    //CUADRO DERECHO...
                    g.drawImage(piezaFondo1.getImage(), ((j*102)+558), ((i*102)+50), 102, 102, this);
                    if(this.sistema.getPiezaMatrizDesordenada(i, j) != null)
                    {
                        g.drawImage(piezaFondo2.getImage(), ((j*102)+558), ((i*102)+50), 102, 102, this);
                        //SUPERIOR...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 0)
                        {
                            g.drawImage(piezaSuperior0.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 2)
                        {
                            g.drawImage(piezaSuperior2.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 3)
                        {
                            g.drawImage(piezaSuperior3.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 4)
                        {
                            g.drawImage(piezaSuperior4.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 5)
                        {
                            g.drawImage(piezaSuperior5.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 6)
                        {
                            g.drawImage(piezaSuperior6.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 7)
                        {
                            g.drawImage(piezaSuperior7.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 8)
                        {
                            g.drawImage(piezaSuperior8.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 9)
                        {
                            g.drawImage(piezaSuperior9.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        //INFERIOR...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 0)
                        {
                            g.drawImage(piezaInferior0.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 1)
                        {
                            g.drawImage(piezaInferior1.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 2)
                        {
                            g.drawImage(piezaInferior2.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 3)
                        {
                            g.drawImage(piezaInferior3.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 4)
                        {
                            g.drawImage(piezaInferior4.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 5)
                        {
                            g.drawImage(piezaInferior5.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 6)
                        {
                            g.drawImage(piezaInferior6.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 7)
                        {
                            g.drawImage(piezaInferior7.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 8)
                        {
                            g.drawImage(piezaInferior8.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 9)
                        {
                            g.drawImage(piezaInferior9.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        //IZQUIERDA...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 0)
                        {
                            g.drawImage(piezaIzquierda0.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 1)
                        {
                            g.drawImage(piezaIzquierda1.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 2)
                        {
                            g.drawImage(piezaIzquierda2.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 3)
                        {
                            g.drawImage(piezaIzquierda3.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 4)
                        {
                            g.drawImage(piezaIzquierda4.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 5)
                        {
                            g.drawImage(piezaIzquierda5.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 6)
                        {
                            g.drawImage(piezaIzquierda6.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 7)
                        {
                            g.drawImage(piezaIzquierda7.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 8)
                        {
                            g.drawImage(piezaIzquierda8.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 9)
                        {
                            g.drawImage(piezaIzquierda9.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        //DERECHA...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 0)
                        {
                            g.drawImage(piezaDerecha0.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 1)
                        {
                            g.drawImage(piezaDerecha1.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 2)
                        {
                            g.drawImage(piezaDerecha2.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 3)
                        {
                            g.drawImage(piezaDerecha3.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 4)
                        {
                            g.drawImage(piezaDerecha4.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 5)
                        {
                            g.drawImage(piezaDerecha5.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 6)
                        {
                            g.drawImage(piezaDerecha6.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 7)
                        {
                            g.drawImage(piezaDerecha7.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 8)
                        {
                            g.drawImage(piezaDerecha8.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 9)
                        {
                            g.drawImage(piezaDerecha9.getImage(), ((j*102)+563), ((i*102)+55), 92, 92, this);
                        }
                        if(this.sistema.getMatrizActual() == 2)
                        {
                            g.drawImage(piezaSeleccionada.getImage(), ((this.sistema.getJPieza()*102)+558), ((this.sistema.getIPieza()*102)+50), 102, 102, this);
                        }
                    }
                }
            }
        }
        else if(this.sistema.getTamaño() == 5)
        {
            for(int i = 0; i < this.sistema.getTamaño(); i++)
            {
                for(int j = 0; j < this.sistema.getTamaño(); j++)
                {
                    //CUADRO IZQUIERDO...
                    g.drawImage(piezaFondo1.getImage(), ((j*82)+50), ((i*82)+50), 82, 82, this);
                    if(this.sistema.getPiezaMatrizFinal(i, j) != null)
                    {
                        g.drawImage(piezaFondo2.getImage(), ((j*82)+50), ((i*82)+50), 82, 82, this);
                        //SUPERIOR...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 0)
                        {
                            g.drawImage(piezaSuperior0.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 2)
                        {
                            g.drawImage(piezaSuperior2.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 3)
                        {
                            g.drawImage(piezaSuperior3.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 4)
                        {
                            g.drawImage(piezaSuperior4.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 5)
                        {
                            g.drawImage(piezaSuperior5.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 6)
                        {
                            g.drawImage(piezaSuperior6.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 7)
                        {
                            g.drawImage(piezaSuperior7.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 8)
                        {
                            g.drawImage(piezaSuperior8.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 9)
                        {
                            g.drawImage(piezaSuperior9.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        //INFERIOR...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 0)
                        {
                            g.drawImage(piezaInferior0.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 1)
                        {
                            g.drawImage(piezaInferior1.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 2)
                        {
                            g.drawImage(piezaInferior2.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 3)
                        {
                            g.drawImage(piezaInferior3.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 4)
                        {
                            g.drawImage(piezaInferior4.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 5)
                        {
                            g.drawImage(piezaInferior5.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 6)
                        {
                            g.drawImage(piezaInferior6.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 7)
                        {
                            g.drawImage(piezaInferior7.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 8)
                        {
                            g.drawImage(piezaInferior8.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 9)
                        {
                            g.drawImage(piezaInferior9.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        //IZQUIERDA...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 0)
                        {
                            g.drawImage(piezaIzquierda0.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 1)
                        {
                            g.drawImage(piezaIzquierda1.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 2)
                        {
                            g.drawImage(piezaIzquierda2.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 3)
                        {
                            g.drawImage(piezaIzquierda3.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 4)
                        {
                            g.drawImage(piezaIzquierda4.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 5)
                        {
                            g.drawImage(piezaIzquierda5.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 6)
                        {
                            g.drawImage(piezaIzquierda6.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 7)
                        {
                            g.drawImage(piezaIzquierda7.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 8)
                        {
                            g.drawImage(piezaIzquierda8.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 9)
                        {
                            g.drawImage(piezaIzquierda9.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        //DERECHA...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 0)
                        {
                            g.drawImage(piezaDerecha0.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 1)
                        {
                            g.drawImage(piezaDerecha1.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 2)
                        {
                            g.drawImage(piezaDerecha2.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 3)
                        {
                            g.drawImage(piezaDerecha3.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 4)
                        {
                            g.drawImage(piezaDerecha4.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 5)
                        {
                            g.drawImage(piezaDerecha5.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 6)
                        {
                            g.drawImage(piezaDerecha6.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 7)
                        {
                            g.drawImage(piezaDerecha7.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 8)
                        {
                            g.drawImage(piezaDerecha8.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 9)
                        {
                            g.drawImage(piezaDerecha9.getImage(), ((j*82)+55), ((i*82)+55), 72, 72, this);
                        }
                        if(this.sistema.getMatrizActual() == 1)
                        {
                            g.drawImage(piezaSeleccionada.getImage(), ((this.sistema.getJPieza()*82)+50), ((this.sistema.getIPieza()*82)+50), 82, 82, this);
                        }
                    }
                    //CUADRO DERECHO...
                    g.drawImage(piezaFondo1.getImage(), ((j*82)+558), ((i*82)+50), 82, 82, this);
                    if(this.sistema.getPiezaMatrizDesordenada(i, j) != null)
                    {
                        g.drawImage(piezaFondo2.getImage(), ((j*82)+558), ((i*82)+50), 82, 82, this);
                        //SUPERIOR...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 0)
                        {
                            g.drawImage(piezaSuperior0.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 2)
                        {
                            g.drawImage(piezaSuperior2.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 3)
                        {
                            g.drawImage(piezaSuperior3.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 4)
                        {
                            g.drawImage(piezaSuperior4.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 5)
                        {
                            g.drawImage(piezaSuperior5.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 6)
                        {
                            g.drawImage(piezaSuperior6.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 7)
                        {
                            g.drawImage(piezaSuperior7.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 8)
                        {
                            g.drawImage(piezaSuperior8.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 9)
                        {
                            g.drawImage(piezaSuperior9.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        //INFERIOR...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 0)
                        {
                            g.drawImage(piezaInferior0.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 1)
                        {
                            g.drawImage(piezaInferior1.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 2)
                        {
                            g.drawImage(piezaInferior2.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 3)
                        {
                            g.drawImage(piezaInferior3.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 4)
                        {
                            g.drawImage(piezaInferior4.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 5)
                        {
                            g.drawImage(piezaInferior5.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 6)
                        {
                            g.drawImage(piezaInferior6.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 7)
                        {
                            g.drawImage(piezaInferior7.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 8)
                        {
                            g.drawImage(piezaInferior8.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 9)
                        {
                            g.drawImage(piezaInferior9.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        //IZQUIERDA...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 0)
                        {
                            g.drawImage(piezaIzquierda0.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 1)
                        {
                            g.drawImage(piezaIzquierda1.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 2)
                        {
                            g.drawImage(piezaIzquierda2.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 3)
                        {
                            g.drawImage(piezaIzquierda3.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 4)
                        {
                            g.drawImage(piezaIzquierda4.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 5)
                        {
                            g.drawImage(piezaIzquierda5.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 6)
                        {
                            g.drawImage(piezaIzquierda6.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 7)
                        {
                            g.drawImage(piezaIzquierda7.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 8)
                        {
                            g.drawImage(piezaIzquierda8.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 9)
                        {
                            g.drawImage(piezaIzquierda9.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        //DERECHA...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 0)
                        {
                            g.drawImage(piezaDerecha0.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 1)
                        {
                            g.drawImage(piezaDerecha1.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 2)
                        {
                            g.drawImage(piezaDerecha2.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 3)
                        {
                            g.drawImage(piezaDerecha3.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 4)
                        {
                            g.drawImage(piezaDerecha4.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 5)
                        {
                            g.drawImage(piezaDerecha5.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 6)
                        {
                            g.drawImage(piezaDerecha6.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 7)
                        {
                            g.drawImage(piezaDerecha7.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 8)
                        {
                            g.drawImage(piezaDerecha8.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 9)
                        {
                            g.drawImage(piezaDerecha9.getImage(), ((j*82)+563), ((i*82)+55), 72, 72, this);
                        }
                        if(this.sistema.getMatrizActual() == 2)
                        {
                            g.drawImage(piezaSeleccionada.getImage(), ((this.sistema.getJPieza()*82)+558), ((this.sistema.getIPieza()*82)+50), 82, 82, this);
                        }
                    }
                }
            }
        }
        else
        {
            for(int i = 0; i < this.sistema.getTamaño(); i++)
            {
                for(int j = 0; j < this.sistema.getTamaño(); j++)
                {
                    //CUADRO IZQUIERDO...
                    g.drawImage(piezaFondo1.getImage(), ((j*68)+50), ((i*68)+50), 68, 68, this);
                    if(this.sistema.getPiezaMatrizFinal(i, j) != null)
                    {
                        g.drawImage(piezaFondo2.getImage(), ((j*68)+50), ((i*68)+50), 68, 68, this);
                        //SUPERIOR...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 0)
                        {
                            g.drawImage(piezaSuperior0.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 2)
                        {
                            g.drawImage(piezaSuperior2.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 3)
                        {
                            g.drawImage(piezaSuperior3.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 4)
                        {
                            g.drawImage(piezaSuperior4.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 5)
                        {
                            g.drawImage(piezaSuperior5.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 6)
                        {
                            g.drawImage(piezaSuperior6.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 7)
                        {
                            g.drawImage(piezaSuperior7.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 8)
                        {
                            g.drawImage(piezaSuperior8.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getSuperior() == 9)
                        {
                            g.drawImage(piezaSuperior9.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        //INFERIOR...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 0)
                        {
                            g.drawImage(piezaInferior0.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 1)
                        {
                            g.drawImage(piezaInferior1.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 2)
                        {
                            g.drawImage(piezaInferior2.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 3)
                        {
                            g.drawImage(piezaInferior3.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 4)
                        {
                            g.drawImage(piezaInferior4.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 5)
                        {
                            g.drawImage(piezaInferior5.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 6)
                        {
                            g.drawImage(piezaInferior6.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 7)
                        {
                            g.drawImage(piezaInferior7.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 8)
                        {
                            g.drawImage(piezaInferior8.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getInferior() == 9)
                        {
                            g.drawImage(piezaInferior9.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        //IZQUIERDA...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 0)
                        {
                            g.drawImage(piezaDerecha0.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);                       
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 1)
                        {
                            g.drawImage(piezaDerecha1.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 2)
                        {
                            g.drawImage(piezaDerecha2.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);              
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 3)
                        {
                            g.drawImage(piezaDerecha3.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 4)
                        {
                            g.drawImage(piezaDerecha4.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 5)
                        {
                            g.drawImage(piezaDerecha5.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 6)
                        {
                            g.drawImage(piezaDerecha6.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 7)
                        {
                            g.drawImage(piezaDerecha7.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 8)
                        {
                            g.drawImage(piezaDerecha8.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getDerecha() == 9)
                        {
                            g.drawImage(piezaDerecha9.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        //DERECHA...
                        if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 0)
                        {
                            g.drawImage(piezaIzquierda0.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);                       
                        } 
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 1)
                        {
                            g.drawImage(piezaIzquierda1.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 2)
                        {
                            g.drawImage(piezaIzquierda2.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);              
                        }        
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 3)
                        {
                            g.drawImage(piezaIzquierda3.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 4)
                        {
                            g.drawImage(piezaIzquierda4.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 5)
                        {
                            g.drawImage(piezaIzquierda5.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 6)
                        {
                            g.drawImage(piezaIzquierda6.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);        
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 7)
                        {
                            g.drawImage(piezaIzquierda7.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 8)
                        {
                            g.drawImage(piezaIzquierda8.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        else if(this.sistema.getPiezaMatrizFinal(i, j).getIzquierda() == 9)
                        {
                            g.drawImage(piezaIzquierda9.getImage(), ((j*68)+55), ((i*68)+55), 58, 58, this);
                        }
                        if(this.sistema.getMatrizActual() == 1)
                        {
                            g.drawImage(piezaSeleccionada.getImage(), ((this.sistema.getJPieza()*68)+50), ((this.sistema.getIPieza()*68)+50), 68, 68, this);
                        }
                    }
                    //CUADRO DERECHO...
                    g.drawImage(piezaFondo1.getImage(), ((j*68)+558), ((i*68)+50), 68, 68, this);
                    if(this.sistema.getPiezaMatrizDesordenada(i, j) != null)
                    {
                        g.drawImage(piezaFondo2.getImage(), ((j*68)+558), ((i*68)+50), 68, 68, this);
                        //SUPERIOR...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 0)
                        {
                            g.drawImage(piezaSuperior0.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 1)
                        {
                            g.drawImage(piezaSuperior1.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 2)
                        {
                            g.drawImage(piezaSuperior2.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 3)
                        {
                            g.drawImage(piezaSuperior3.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 4)
                        {
                            g.drawImage(piezaSuperior4.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 5)
                        {
                            g.drawImage(piezaSuperior5.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 6)
                        {
                            g.drawImage(piezaSuperior6.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 7)
                        {
                            g.drawImage(piezaSuperior7.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 8)
                        {
                            g.drawImage(piezaSuperior8.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getSuperior() == 9)
                        {
                            g.drawImage(piezaSuperior9.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        //INFERIOR...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 0)
                        {
                            g.drawImage(piezaInferior0.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 1)
                        {
                            g.drawImage(piezaInferior1.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 2)
                        {
                            g.drawImage(piezaInferior2.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 3)
                        {
                            g.drawImage(piezaInferior3.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 4)
                        {
                            g.drawImage(piezaInferior4.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 5)
                        {
                            g.drawImage(piezaInferior5.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 6)
                        {
                            g.drawImage(piezaInferior6.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 7)
                        {
                            g.drawImage(piezaInferior7.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 8)
                        {
                            g.drawImage(piezaInferior8.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getInferior() == 9)
                        {
                            g.drawImage(piezaInferior9.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        //IZQUIERDA...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 0)
                        {
                            g.drawImage(piezaDerecha0.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);                       
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 1)
                        {
                            g.drawImage(piezaDerecha1.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 2)
                        {
                            g.drawImage(piezaDerecha2.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);              
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 3)
                        {
                            g.drawImage(piezaDerecha3.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 4)
                        {
                            g.drawImage(piezaDerecha4.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 5)
                        {
                            g.drawImage(piezaDerecha5.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 6)
                        {
                            g.drawImage(piezaDerecha6.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 7)
                        {
                            g.drawImage(piezaDerecha7.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 8)
                        {
                            g.drawImage(piezaDerecha8.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getDerecha() == 9)
                        {
                            g.drawImage(piezaDerecha9.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        //DERECHA...
                        if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 0)
                        {
                            g.drawImage(piezaIzquierda0.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);                       
                        } 
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 1)
                        {
                            g.drawImage(piezaIzquierda1.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 2)
                        {
                            g.drawImage(piezaIzquierda2.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);              
                        }        
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 3)
                        {
                            g.drawImage(piezaIzquierda3.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 4)
                        {
                            g.drawImage(piezaIzquierda4.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 5)
                        {
                            g.drawImage(piezaIzquierda5.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 6)
                        {
                            g.drawImage(piezaIzquierda6.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);        
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 7)
                        {
                            g.drawImage(piezaIzquierda7.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 8)
                        {
                            g.drawImage(piezaIzquierda8.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        else if(sistema.getPiezaMatrizDesordenada(i, j).getIzquierda() == 9)
                        {
                            g.drawImage(piezaIzquierda9.getImage(), ((j*68)+563), ((i*68)+55), 58, 58, this);
                        }
                        if(this.sistema.getMatrizActual() == 2)
                        {
                            g.drawImage(piezaSeleccionada.getImage(), ((this.sistema.getJPieza()*68)+558), ((this.sistema.getIPieza()*68)+50), 68, 68, this);
                        }
                    }
                }
            }
        }
        if(this.sistema.getPausa())
        {
            g.drawImage(fondoPausa.getImage(), 0, 0, 1018, 508, this);
            g.drawImage(textoPausa.getImage(), 0, 0, 1018, 508, this);
        }
        if(this.sistema.matrizCompleta() == true)
        {
            if(this.sistema.matrizCorrecta() == true && this.click == 3)
            {
                this.click = 3;
                g.drawImage(fondoPausa.getImage(), 0, 0, 1018, 508, this);
                g.drawImage(textoNuevoJuego.getImage(), 0, 0, 1018, 508, this);
            }
            else if(this.sistema.matrizCorrecta() != true && this.click != 3 && this.click != 4)
            {
                this.click = 4;
                ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño(), 0, 1);
                g.drawImage(fondoPausa.getImage(), 0, 0, 1018, 508, this);
                g.drawImage(textoNuevoJuego.getImage(), 0, 0, 1018, 508, this);
            }
            else if(this.sistema.matrizCorrecta() == true && this.click != 3 && this.click != 4)
            {
                this.finJuego();
                this.click = 4;
            }
        }
        this.repaint();
    }
    
    /**
     * El metodo mouseDragged es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
    }
    
    /**
     * El metodo mouseMoved es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
        this.x = e.getX();
        this.y = e.getY();
        this.repaint();
    }

    /**
     * El metodo mouseClicked es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    /**
     * El metodo mousePressed es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1 && this.sistema.getPausa() == false)
        {
            if(this.sistema.getTamaño() == 2)
            {
                //CUADRO IZQUIERDO...
                if(this.x >= 50 && this.x <= 458 && this.y >= 50 && this.y <= 458)
                {
                    if(this.x >= 50 && this.x < 254)
                    {
                        this.sistema.setJPieza(0);
                        this.click = 1;
                    }
                    else if(this.x >= 254 && this.x < 458)
                    {
                        this.sistema.setJPieza(1);
                        this.click = 1;
                    }
                    if(this.y >= 50 && this.y < 254)
                    {
                        this.sistema.setIPieza(0);
                        this.click = 2;
                    }
                    else if(this.y >= 254 && this.y < 458)
                    {
                        this.sistema.setIPieza(1);
                        this.click = 2;
                    }
                    this.sistema.setMatrizActual(1);
                    if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) != null && this.sistema.getClick() == 0)
                    {
                        this.sistema.setPiezaAuxiliar1();
                        this.sistema.setClick(1);
                    }
                    else if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 1)
                    {
                        this.sistema.setClick(2);
                    }
                }
                //CUADRO DERECHO...
                else if(this.x >= 558 && this.x <= 966 && this.y >= 50 && this.y <= 458)
                {
                    if(this.x >= 558 && this.x < 762)
                    {
                        this.sistema.setJPieza(0);
                        this.click = 1;
                    }
                    else if(this.x >= 762 && this.x < 966)
                    {
                        this.sistema.setJPieza(1);
                        this.click = 1;
                    }
                    if(this.y >= 50 && this.y < 254)
                    {
                        this.sistema.setIPieza(0);
                        this.click = 2;
                    }
                    else if(this.y >= 254 && this.y < 458)
                    {
                        this.sistema.setIPieza(1);
                        this.click = 2;
                    }
                    this.sistema.setMatrizActual(2);
                    if(this.sistema.getPiezaMatrizDesordenada(this.sistema.getIPieza(), this.sistema.getJPieza()) != null && this.sistema.getClick() == 0)
                    {
                        this.sistema.setPiezaAuxiliar2();
                        this.sistema.setClick(1);
                    }
                    else if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 1)
                    {
                        this.sistema.setClick(2);
                    }
                }
                else
                {
                    this.sistema.setPiezaAuxiliar();
                    this.sistema.setMatrizActual(0);
                    this.sistema.setIPieza(0);
                    this.sistema.setJPieza(0);
                    this.click = 0;
                    this.sistema.setClick(0);
                }
                if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 2)
                {
                    this.sistema.moverElemento();
                    this.click = 0;
                }
            }
            else if(this.sistema.getTamaño() == 3)
            {
                //CUADRO IZQUIERDO...
                if(this.x >= 50 && this.x <= 458 && this.y >= 50 && this.y <= 458)
                {
                    if(this.x >= 50 && this.x < 186)
                    {
                        this.sistema.setJPieza(0);
                        this.click = 1;
                    }
                    else if(this.x >= 186 && this.x < 322)
                    {
                        this.sistema.setJPieza(1);
                        this.click = 1;
                    }
                    else if(this.x >= 322 && this.x < 458)
                    {
                        this.sistema.setJPieza(2);
                        this.click = 1;
                    }
                    if(this.y >= 50 && this.y < 186)
                    {
                        this.sistema.setIPieza(0);
                        this.click = 2;
                    }
                    else if(this.y >= 186 && this.y < 322)
                    {
                        this.sistema.setIPieza(1);
                        this.click = 2;
                    }
                    else if(this.y >= 322 && this.y < 458)
                    {
                        this.sistema.setIPieza(2);
                        this.click = 2;
                    }
                    this.sistema.setMatrizActual(1);
                    if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) != null && this.sistema.getClick() == 0)
                    {
                        this.sistema.setPiezaAuxiliar1();
                        this.sistema.setClick(1);
                    }
                    else if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 1)
                    {
                        this.sistema.setClick(2);
                    }
                }
                //CUADRO DERECHO...
                else if(this.x >= 558 && this.x <= 966 && this.y >= 50 && this.y <= 458)
                {
                    if(this.x >= 558 && this.x < 694)
                    {
                        this.sistema.setJPieza(0);
                        this.click = 1;
                    }
                    else if(this.x >= 694 && this.x < 830)
                    {
                        this.sistema.setJPieza(1);
                        this.click = 1;
                    }
                    else if(this.x >= 830 && this.x < 966)
                    {
                        this.sistema.setJPieza(2);
                        this.click = 1;
                    }
                    if(this.y >= 50 && this.y < 186)
                    {
                        this.sistema.setIPieza(0);
                        this.click = 2;
                    }
                    else if(this.y >= 186 && this.y < 322)
                    {
                        this.sistema.setIPieza(1);
                        this.click = 2;
                    }
                    else if(this.y >= 322 && this.y < 458)
                    {
                        this.sistema.setIPieza(2);
                        this.click = 2;
                    }
                    this.sistema.setMatrizActual(2);
                    if(this.sistema.getPiezaMatrizDesordenada(this.sistema.getIPieza(), this.sistema.getJPieza()) != null && this.sistema.getClick() == 0)
                    {
                        this.sistema.setPiezaAuxiliar2();
                        this.sistema.setClick(1);
                    }
                    else if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 1)
                    {
                        this.sistema.setClick(2);
                    }
                }
                else
                {
                    this.sistema.setPiezaAuxiliar();
                    this.sistema.setMatrizActual(0);
                    this.sistema.setIPieza(0);
                    this.sistema.setJPieza(0);
                    this.click = 0;
                    this.sistema.setClick(0);
                }
                if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 2)
                {
                    this.sistema.moverElemento();
                    this.click = 0;
                }
            }
            else if(this.sistema.getTamaño() == 4)
            {
                //CUADRO IZQUIERDO...
                if(this.x >= 50 && this.x <= 458 && this.y >= 50 && this.y <= 458)
                {
                    if(this.x >= 50 && this.x < 152)
                    {
                        this.sistema.setJPieza(0);
                        this.click = 1;
                    }
                    else if(this.x >= 152 && this.x < 254)
                    {
                        this.sistema.setJPieza(1);
                        this.click = 1;
                    }
                    else if(this.x >= 254 && this.x < 356)
                    {
                        this.sistema.setJPieza(2);
                        this.click = 1;
                    }
                    else if(this.x >= 356 && this.x < 458)
                    {
                        this.sistema.setJPieza(3);
                        this.click = 1;
                    }
                    if(this.y >= 50 && this.y < 152)
                    {
                        this.sistema.setIPieza(0);
                        this.click = 2;
                    }
                    else if(this.y >= 152 && this.y < 254)
                    {
                        this.sistema.setIPieza(1);
                        this.click = 2;
                    }
                    else if(this.y >= 254 && this.y < 356)
                    {
                        this.sistema.setIPieza(2);
                        this.click = 2;
                    }
                    else if(this.y >= 356 && this.y < 458)
                    {
                        this.sistema.setIPieza(3);
                        this.click = 2;
                    }
                    this.sistema.setMatrizActual(1);
                    if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) != null && this.sistema.getClick() == 0)
                    {
                        this.sistema.setPiezaAuxiliar1();
                        this.sistema.setClick(1);
                    }
                    else if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 1)
                    {
                        this.sistema.setClick(2);
                    }
                }
                //CUADRO DERECHO...
                else if(this.x >= 558 && this.x <= 966 && this.y >= 50 && this.y <= 458)
                {
                    if(this.x >= 558 && this.x < 660)
                    {
                        this.sistema.setJPieza(0);
                        this.click = 1;
                    }
                    else if(this.x >= 660 && this.x < 762)
                    {
                        this.sistema.setJPieza(1);
                        this.click = 1;
                    }
                    else if(this.x >= 762 && this.x < 864)
                    {
                        this.sistema.setJPieza(2);
                        this.click = 1;
                    }
                    else if(this.x >= 864 && this.x < 966)
                    {
                        this.sistema.setJPieza(3);
                        this.click = 1;
                    }
                    if(this.y >= 50 && this.y < 152)
                    {
                        this.sistema.setIPieza(0);
                        this.click = 2;
                    }
                    else if(this.y >= 152 && this.y < 254)
                    {
                        this.sistema.setIPieza(1);
                        this.click = 2;
                    }
                    else if(this.y >= 254 && this.y < 356)
                    {
                        this.sistema.setIPieza(2);
                        this.click = 2;
                    }
                    else if(this.y >= 356 && this.y < 458)
                    {
                        this.sistema.setIPieza(3);
                        this.click = 2;
                    }
                    this.sistema.setMatrizActual(2);
                    if(this.sistema.getPiezaMatrizDesordenada(this.sistema.getIPieza(), this.sistema.getJPieza()) != null && this.sistema.getClick() == 0)
                    {
                        this.sistema.setPiezaAuxiliar2();
                        this.sistema.setClick(1);
                    }
                    else if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 1)
                    {
                        this.sistema.setClick(2);
                    }
                }
                else
                {
                    this.sistema.setPiezaAuxiliar();
                    this.sistema.setMatrizActual(0);
                    this.sistema.setIPieza(0);
                    this.sistema.setJPieza(0);
                    this.click = 0;
                    this.sistema.setClick(0);
                }
                if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 2)
                {
                    this.sistema.moverElemento();
                    this.click = 0;
                }
            }
            else if(this.sistema.getTamaño() == 5)
            {
                //CUADRO IZQUIERDO...
                if(this.x >= 50 && this.x <= 460 && this.y >= 50 && this.y <= 460)
                {
                    if(this.x >= 50 && this.x < 132)
                    {
                        this.sistema.setJPieza(0);
                        this.click = 1;
                    }
                    else if(this.x >= 132 && this.x < 214)
                    {
                        this.sistema.setJPieza(1);
                        this.click = 1;
                    }
                    else if(this.x >= 214 && this.x < 296)
                    {
                        this.sistema.setJPieza(2);
                        this.click = 1;
                    }
                    else if(this.x >= 296 && this.x < 378)
                    {
                        this.sistema.setJPieza(3);
                        this.click = 1;
                    }
                    else if(this.x >= 378 && this.x < 460)
                    {
                        this.sistema.setJPieza(4);
                        this.click = 1;
                    }
                    if(this.y >= 50 && this.y < 132)
                    {
                        this.sistema.setIPieza(0);
                        this.click = 2;
                    }
                    else if(this.y >= 132 && this.y < 214)
                    {
                        this.sistema.setIPieza(1);
                        this.click = 2;
                    }
                    else if(this.y >= 214 && this.y < 296)
                    {
                        this.sistema.setIPieza(2);
                        this.click = 2;
                    }
                    else if(this.y >= 296 && this.y < 378)
                    {
                        this.sistema.setIPieza(3);
                        this.click = 2;
                    }
                    else if(this.y >= 378 && this.y < 460)
                    {
                        this.sistema.setIPieza(4);
                        this.click = 2;
                    }
                    this.sistema.setMatrizActual(1);
                    if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) != null && this.sistema.getClick() == 0)
                    {
                        this.sistema.setPiezaAuxiliar1();
                        this.sistema.setClick(1);
                    }
                    else if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 1)
                    {
                        this.sistema.setClick(2);
                    }
                }
                //CUADRO DERECHO...
                else if(this.x >= 558 && this.x <= 968 && this.y >= 50 && this.y <= 460)
                {
                    if(this.x >= 558 && this.x < 640)
                    {
                        this.sistema.setJPieza(0);
                        this.click = 1;
                    }
                    else if(this.x >= 640 && this.x < 722)
                    {
                        this.sistema.setJPieza(1);
                        this.click = 1;
                    }
                    else if(this.x >= 722 && this.x < 804)
                    {
                        this.sistema.setJPieza(2);
                        this.click = 1;
                    }
                    else if(this.x >= 804 && this.x < 886)
                    {
                        this.sistema.setJPieza(3);
                        this.click = 1;
                    }
                    else if(this.x >= 886 && this.x < 968)
                    {
                        this.sistema.setJPieza(4);
                        this.click = 1;
                    }
                    if(this.y >= 50 && this.y < 132)
                    {
                        this.sistema.setIPieza(0);
                        this.click = 2;
                    }
                    else if(this.y >= 132 && this.y < 214)
                    {
                        this.sistema.setIPieza(1);
                        this.click = 2;
                    }
                    else if(this.y >= 214 && this.y < 296)
                    {
                        this.sistema.setIPieza(2);
                        this.click = 2;
                    }
                    else if(this.y >= 296 && this.y < 378)
                    {
                        this.sistema.setIPieza(3);
                        this.click = 2;
                    }
                    else if(this.y >= 378 && this.y < 460)
                    {
                        this.sistema.setIPieza(4);
                        this.click = 2;
                    }
                    this.sistema.setMatrizActual(2);
                    if(this.sistema.getPiezaMatrizDesordenada(this.sistema.getIPieza(), this.sistema.getJPieza()) != null && this.sistema.getClick() == 0)
                    {
                        this.sistema.setPiezaAuxiliar2();
                        this.sistema.setClick(1);
                    }
                    else if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 1)
                    {
                        this.sistema.setClick(2);
                    }
                }
                else
                {
                    this.sistema.setPiezaAuxiliar();
                    this.sistema.setMatrizActual(0);
                    this.sistema.setIPieza(0);
                    this.sistema.setJPieza(0);
                    this.click = 0;
                    this.sistema.setClick(0);
                }
                if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 2)
                {
                    this.sistema.moverElemento();
                    this.click = 0;
                }
            }
            else
            {
                //CUADRO IZQUIERDO...
                if(this.x >= 50 && this.x <= 458 && this.y >= 50 && this.y <= 458)
                {
                    if(this.x >= 50 && this.x < 118)
                    {
                        this.sistema.setJPieza(0);
                        this.click = 1;
                    }
                    else if(this.x >= 118 && this.x < 186)
                    {
                        this.sistema.setJPieza(1);
                        this.click = 1;
                    }
                    else if(this.x >= 186 && this.x < 254)
                    {
                        this.sistema.setJPieza(2);
                        this.click = 1;
                    }
                    else if(this.x >= 254 && this.x < 322)
                    {
                        this.sistema.setJPieza(3);
                        this.click = 1;
                    }
                    else if(this.x >= 322 && this.x < 390)
                    {
                        this.sistema.setJPieza(4);
                        this.click = 1;
                    }
                    else if(this.x >= 390 && this.x < 458)
                    {
                        this.sistema.setJPieza(5);
                        this.click = 1;
                    }
                    if(this.y >= 50 && this.y < 118)
                    {
                        this.sistema.setIPieza(0);
                        this.click = 2;
                    }
                    else if(this.y >= 118 && this.y < 186)
                    {
                        this.sistema.setIPieza(1);
                        this.click = 2;
                    }
                    else if(this.y >= 186 && this.y < 254)
                    {
                        this.sistema.setIPieza(2);
                        this.click = 2;
                    }
                    else if(this.y >= 254 && this.y < 322)
                    {
                        this.sistema.setIPieza(3);
                        this.click = 2;
                    }
                    else if(this.y >= 322 && this.y < 390)
                    {
                        this.sistema.setIPieza(4);
                        this.click = 2;
                    }
                    else if(this.y >= 390 && this.y < 458)
                    {
                        this.sistema.setIPieza(5);
                        this.click = 2;
                    }
                    this.sistema.setMatrizActual(1);
                    if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) != null && this.sistema.getClick() == 0)
                    {
                        this.sistema.setPiezaAuxiliar1();
                        this.sistema.setClick(1);
                    }
                    else if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 1)
                    {
                        this.sistema.setClick(2);
                    }
                }
                //CUADRO DERECHO...
                else if(this.x >= 558 && this.x <= 966 && this.y >= 50 && this.y <= 458)
                {
                    if(this.x >= 558 && this.x < 626)
                    {
                        this.sistema.setJPieza(0);
                        this.click = 1;
                    }
                    else if(this.x >= 626 && this.x < 694)
                    {
                        this.sistema.setJPieza(1);
                        this.click = 1;
                    }
                    else if(this.x >= 694 && this.x < 762)
                    {
                        this.sistema.setJPieza(2);
                        this.click = 1;
                    }
                    else if(this.x >= 762 && this.x < 830)
                    {
                        this.sistema.setJPieza(3);
                        this.click = 1;
                    }
                    else if(this.x >= 830 && this.x < 898)
                    {
                        this.sistema.setJPieza(4);
                        this.click = 1;
                    }
                    else if(this.x >= 898 && this.x < 966)
                    {
                        this.sistema.setJPieza(5);
                        this.click = 1;
                    }
                    if(this.y >= 50 && this.y < 118)
                    {
                        this.sistema.setIPieza(0);
                        this.click = 2;
                    }
                    else if(this.y >= 118 && this.y < 186)
                    {
                        this.sistema.setIPieza(1);
                        this.click = 2;
                    }
                    else if(this.y >= 186 && this.y < 254)
                    {
                        this.sistema.setIPieza(2);
                        this.click = 2;
                    }
                    else if(this.y >= 254 && this.y < 322)
                    {
                        this.sistema.setIPieza(3);
                        this.click = 2;
                    }
                    else if(this.y >= 322 && this.y < 390)
                    {
                        this.sistema.setIPieza(4);
                        this.click = 2;
                    }
                    else if(this.y >= 390 && this.y < 458)
                    {
                        this.sistema.setIPieza(5);
                        this.click = 2;
                    }
                    this.sistema.setMatrizActual(2);
                    if(this.sistema.getPiezaMatrizDesordenada(this.sistema.getIPieza(), this.sistema.getJPieza()) != null && this.sistema.getClick() == 0)
                    {
                        this.sistema.setPiezaAuxiliar2();
                        this.sistema.setClick(1);
                    }
                    else if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 1)
                    {
                        this.sistema.setClick(2);
                    }
                }
                else
                {
                    this.sistema.setPiezaAuxiliar();
                    this.sistema.setMatrizActual(0);
                    this.sistema.setIPieza(0);
                    this.sistema.setJPieza(0);
                    this.click = 0;
                    this.sistema.setClick(0);
                }
                if(this.sistema.getPiezaMatrizFinal(this.sistema.getIPieza(), this.sistema.getJPieza()) == null && this.sistema.getClick() == 2)
                {
                    this.sistema.moverElemento();
                    this.click = 0;
                }
            }
        }
        this.repaint();
    }
    
    /**
     * El metodo mouseReleased es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    /**
     * El metodo mouseEntered es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    /**
     * El metodo mouseExited es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
    }
    
    /**
     * Metodo que retorna la informacion que se encuentra contenida en el atributo sistema
     * @return sistema El metodo retorna el valor que se encuentra almacenado en el atributo sistema
     */
    public Sistema getSistema()
    {
        return this.sistema;
    }
    
    /**
     * Metodo que verifica si la partida de JTetravex ha finalizado
     */
    public void finJuego()
    {
        ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(this.sistema.getTamaño(), 0, 1);
        int hora = ((Ventana)this.getTopLevelAncestor()).getBarraEstado().getCrono().getHora();
        int minutos = ((Ventana)this.getTopLevelAncestor()).getBarraEstado().getCrono().getMinutos();
        int segundos = ((Ventana)this.getTopLevelAncestor()).getBarraEstado().getCrono().getSegundos();
        minutos = minutos + hora/60;
        segundos = segundos + minutos/60;
        mostrarGanador(segundos);
    }
    
    /**
     * Metodo que muestra por pantalla al ganador de la partida y pide registro de su nombre y puntaje
     */
    public void mostrarGanador(int segundos)
    {
        String nombre = (String)JOptionPane.showInputDialog(((Ventana)this.getTopLevelAncestor()), "Ha terminado el juego!!! \n Escribe tu nombre: \n", "Ganaste", JOptionPane.PLAIN_MESSAGE);
        if(nombre != null && nombre.length() > 0)
        {
            ((Ventana)this.getTopLevelAncestor()).getRegistro().insertarOrdenado(segundos, nombre);
            ((Ventana)this.getTopLevelAncestor()).getRegistro().guardar();
        }
    }
    
    /**
     * Metodo para sobreescribir la informacion almacenada en el atributo click
     * @param click del tipo int indica el estado del click
     */
    public void setClick()
    {
        this.click = 3;
    }
}