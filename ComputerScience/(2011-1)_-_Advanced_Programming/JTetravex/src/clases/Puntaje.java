package clases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
* Clase Puntaje
* Su funcion es controlar y almacenar los puntajes del juego JTetravex
*/
public class Puntaje extends JDialog
{
    private Ventana ventana;
    private JPanel jPanel;
    private String[][] informacion;
    private int j;
    private final JTable jTable;
    
    /**
     * El constructor inicializa los atributos declarados en la clase Puntaje
     * @param ventana del tipo Ventana crea una ventana que muestra los puntajes
     * @param jPanel del tipo JPanel es un panel que almacena los puntajes
     * @param informacion del tipo String almacena la informacion del jugador y su puntaje
     * @param j del tipo int variable que almacena un atributo
     * @param jTable del tipo JTable almacena la informacion de las partidas
     * 
     */
    public Puntaje(Ventana ventana)
    {
        super(ventana, true);
        this.ventana = ventana;
        this.setTitle("Puntajes");
        this.getContentPane().setLayout(new FlowLayout());
        jPanel = new JPanel(new BorderLayout());
        this.setResizable(false);

        String[] columna = {"Nombre","Puntaje"};

        informacion = new String[][]{{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}};
        
        jTable = new JTable(informacion, columna);
        jTable.setEnabled(false);
        jTable.getTableHeader().setReorderingAllowed(false);
        jPanel.add(new JScrollPane(jTable));
        this.getContentPane().add(jPanel);
        this.setSize(471, 156);
        
        for(int i = 0; i < 5 && i < this.ventana.getRegistro().getPuntajes().size(); i++)
        {
            informacion[i][0] = this.ventana.getRegistro().getNombres().get(i);
            int horas = 0;
            int minutos = 0;
            int segundos = this.ventana.getRegistro().getPuntajes().get(i);
            if(segundos/60 >= 1)
            {
                minutos = segundos/60;
                segundos = segundos%60;
                if(minutos/60 >= 1)
                {
                    horas = minutos/60;
                    minutos = minutos%60;
                }
            }
            informacion[i][1] = horas + ":" + minutos + ":" + segundos;
        }
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getSize().width) / 2 , (screenSize.height - this.getSize().height) / 2 - 20);
        
        this.setVisible(true);
    }
}