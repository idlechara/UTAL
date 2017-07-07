package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Clase BarraMenu
 * Su funcion es crear los menu y las opciones  del juego
 */
public class BarraMenu extends JMenuBar implements ActionListener
{
    private JMenu juego;
    private JMenuItem nuevoJuego;
    private JMenuItem pausar;
    private JMenuItem reanudar;
    private JMenuItem consejo;
    private JMenuItem resolver;
    private JMenuItem puntajes;
    private JMenuItem salir;
    private JMenu ver;
    private JMenu mover;
    private JMenu tamaño;
    private JMenuItem dos;//    2x2.
    private JMenuItem tres;//   3x3.
    private JMenuItem cuatro;// 4x4.
    private JMenuItem cinco;//  5x5.
    private JMenuItem seis;//   6x6.
    private JMenu ayuda;
    private JMenuItem comoJugar;
    private JMenuItem sobreJTetravex;
    
    /**
     * El constructor inicializa los atributos declarados en la clase BarraMenu
     * @param juego del tipo JMenu crea un nuevo menu en el juego
     * @param nuevoJuego del tipo JMenuItem crea un item en el menu juego
     * @param pausar del tipo JMenuItem crea un item en el menu juego
     * @param reanudar del tipo JMenuItem crea un item en el menu juego
     * @param consejo del tipo JMenuItem crea un item en el menu juego
     * @param resolver del tipo JMenuItem crea un item en el menu juego
     * @param puntajes del tipo JMenuItem crea un item en el menu juego
     * @param salir del tipo JMenuItem crea un item en el menu juego
     * @param ver del tipo JMenu crea un nuevo menu en el juego
     * @param mover del tipo JMenu crea un nuevo menu en el juego
     * @param tamaño del tipo JMenu crea un nuevo menu en el juego
     * @param dos del tipo JMenuItem crea un item en el menu tamaño
     * @param tres del tipo JMenuItem crea un item en el menu tamaño
     * @param cuatro del tipo JMenuItem crea un item en el menu tamaño
     * @param cinco del tipo JMenuItem crea un item en el menu tamaño
     * @param seis del tipo JMenuItem crea un item en el menu tamaño
     * @param ayuda del tipo JMenu crea un nuevo menu en el juego
     * @param comoJugar del tipo JMenuItem crea un item en el menu ayuda
     * @param sobreJTetravex del tipo JMenuItem crea un item en el menu ayuda
     */
    public BarraMenu()
    {
        //  Juego.
        this.juego = new JMenu("Juego");
        this.nuevoJuego = new JMenuItem("Nuevo Juego");
        this.nuevoJuego.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        this.nuevoJuego.setMnemonic('N');
        this.nuevoJuego.addActionListener(this);
        this.juego.add(nuevoJuego);
        this.pausar = new JMenuItem("Pausar");
        this.pausar.addActionListener(this);
        this.juego.add(pausar);
        this.reanudar = new JMenuItem("Reanudar");
        this.reanudar.addActionListener(this);
        this.juego.add(reanudar);
        this.juego.addSeparator();
        this.consejo = new JMenuItem("Consejo");
        this.consejo.addActionListener(this);
        this.juego.add(consejo);
        this.resolver = new JMenuItem("Resolver");
        this.resolver.addActionListener(this);
        this.juego.add(resolver);
        this.juego.addSeparator();
        this.puntajes = new JMenuItem("Puntajes");
        this.puntajes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        this.puntajes.setMnemonic('P');
        this.puntajes.addActionListener(this);
        this.juego.add(puntajes);
        this.juego.addSeparator();
        this.salir = new JMenuItem("Salir");
        this.salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        this.salir.setMnemonic('S');
        this.salir.addActionListener(this);
        this.juego.add(salir);
        this.add(juego);
        //  Ver.
        this.ver = new JMenu("Ver");
        this.add(ver);
        //  Mover.
        this.mover = new JMenu("Mover");
        this.add(mover);
        //  Tamaño.
        this.tamaño = new JMenu("Tamaño");
        this.dos = new JMenuItem("2x2");
        this.dos.addActionListener(this);
        this.tamaño.add(dos);
        this.tres = new JMenuItem("3x3");
        this.tres.addActionListener(this);
        this.tamaño.add(tres);
        this.cuatro = new JMenuItem("4x4");
        this.cuatro.addActionListener(this);
        this.tamaño.add(cuatro);
        this.cinco = new JMenuItem("5x5");
        this.cinco.addActionListener(this);
        this.tamaño.add(cinco);
        this.seis = new JMenuItem("6x6");
        this.seis.addActionListener(this);
        this.tamaño.add(seis);
        this.add(tamaño);
        //  Ayuda.
        this.ayuda = new JMenu("Ayuda");
        this.comoJugar = new JMenuItem("Como Jugar");
        this.comoJugar.addActionListener(this);
        this.ayuda.add(comoJugar);
        this.sobreJTetravex = new JMenuItem("Sobre JTetravex");
        this.sobreJTetravex.addActionListener(this);
        this.ayuda.add(sobreJTetravex);
        this.add(ayuda);
    }

    /**
     * El metodo actionPerformed es un metodo override por lo cual es sobreescrito 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //  Juego.
        if(e.getSource() == this.nuevoJuego)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().nuevoJuego(((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño());
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño(), 1, 0);
        }
        else if(e.getSource() == this.pausar && ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getPausa() == false)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().setPausa();
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño(), 0, 1);
        }
        else if(e.getSource() == this.reanudar && ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getPausa() == true)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().setPausa();
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño(), 0, 0);
        }
        else if(e.getSource() == this.consejo)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().hint();
        }
        else if(e.getSource() == this.resolver)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().mostrarMatriz();
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño(), 0, 1);
            ((Ventana)this.getTopLevelAncestor()).getPantalla().setClick();
        }
        else if(e.getSource() == this.puntajes)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().setPausa2();
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño(), 0, 1);
            new Puntaje(((Ventana)this.getTopLevelAncestor()));
        }
        else if(e.getSource() == this.salir)
        {
            ((Ventana)this.getTopLevelAncestor()).dispose();
        }
        //  Ver.
        //  Mover.
        //  Tamaño.
        else if(e.getSource() == this.dos && ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño() != 2)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().nuevoJuego(2);
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(2, 1, 0);
        }
        else if(e.getSource() == this.tres && ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño() != 3)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().nuevoJuego(3);
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(3, 1, 0);
        }
        else if(e.getSource() == this.cuatro && ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño() != 4)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().nuevoJuego(4);
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(4, 1, 0);
        }
        else if(e.getSource() == this.cinco && ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño() != 5)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().nuevoJuego(5);
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(5, 1, 0);
        }
        else if(e.getSource() == this.seis && ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño() != 6)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().nuevoJuego(6);
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(6, 1, 0);
        }
        //  Ayuda.
        else if(e.getSource() == this.comoJugar)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().setPausa2();
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño(), 0, 1);
            VentanaComoJugar comoJugar = new VentanaComoJugar();
            comoJugar.setVisible(true);
        }
        else if(e.getSource() == this.sobreJTetravex)
        {
            ((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().setPausa2();
            ((Ventana)this.getTopLevelAncestor()).getBarraEstado().actualizar(((Ventana)this.getTopLevelAncestor()).getPantalla().getSistema().getTamaño(), 0, 1);
            VentanaSobreJTetravex sobreJTetravex = new VentanaSobreJTetravex();
            sobreJTetravex.setVisible(true);
        }
    }
}