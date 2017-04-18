package Tarea2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

/**
 *
 * @author Matias
 */
class JVentana extends JFrame {

    private Dimension d = new Dimension(1024, 440);
    private JPanel contenido;
    private JPanel esperando;
    private JLabel textoEsperando;
    private JPanel resultados;
    private JLabel labelN;
    private JLabel labelE;
    private JLabel labelS;
    private JLabel result;
    private JProgressBar progressBarN;
    private JProgressBar progressBarE;
    private int nor;
    private int totalNor;
    private int esp;
    private int sub;
    private Scanner entrada;

    public JVentana(int n, int e, int s, String t) {
        nor = n;
        totalNor = n * s - e;
        esp = e;
        sub = s;
        entrada = new Scanner(System.in);
        super.setTitle(t);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(d);
        this.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) screenSize.getWidth() / 2 - d.width / 2,
                (int) screenSize.getHeight() / 2 - d.height / 2);
        this.contenido = new JPanel(new BorderLayout());
        this.esperando = new JPanel();
        textoEsperando = new JLabel("Esperando resultados...");
        esperando.add(textoEsperando);
        this.add(esperando);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void updateGUI(int ti, int tie, BinaryHeap[] subterraneos, int tif, int tief, int sal) {
        resultados = new JPanel(new GridLayout(7, 1, 10, 2));
        resultados.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        resultados.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        //Total estacionamientos normales.
        this.labelN = new JLabel("Estacionamientos normales:  "+ti+"/" + totalNor);
        progressBarN = new JProgressBar(0, totalNor);
        progressBarN.setValue(ti);
        progressBarN.setString(""+ti);
        resultados.add(labelN);
        resultados.add(progressBarN);
        //Total estacionamientos especiales.
        this.labelE = new JLabel("Estacionamientos especiales:  "+tie+"/" + esp);
        progressBarE = new JProgressBar(0, esp);
        progressBarE.setValue(tie);
        progressBarE.setString(""+tie);
        resultados.add(labelE);
        resultados.add(progressBarE);
        //Total subterraneos usados.
        int nSubUsados = 0;
        JPanel subUsados = new JPanel(new GridLayout(1, subterraneos.length, 1, 2));
        JProgressBar[] progressBars = new JProgressBar[subterraneos.length];
        //Se calcula el primer subterraneo por separado.
        JProgressBar primero = new JProgressBar(0, nor-esp);;
        primero.setStringPainted(true);
        int i = 0;
        if( subterraneos[i] != null ){
            nSubUsados++;
            primero.setValue(subterraneos[i].rest());
            primero.setString("Sub: -"+(i+1)+" Total: "+subterraneos[i].rest());
        }else{
            primero.setValue(0);
            primero.setString("Sub: -"+(i+1)+" Total: 0");
        }
        i++;
        subUsados.add(primero);
        //Se procede con el resto.
        for( ;i < progressBars.length; i++ ){
            progressBars[i] = new JProgressBar(0, nor);
            progressBars[i].setStringPainted(true);
            if( subterraneos[i] != null ){
                if( !subterraneos[i].isFull() ){
                    nSubUsados++;
                }
                progressBars[i].setValue(subterraneos[i].rest());
                progressBars[i].setString("Sub: -"+(i+1)+" Total: "+subterraneos[i].rest());
            }else{
                progressBars[i].setValue(0);
                progressBars[i].setString("Sub: -"+(i+1)+" Total: 0");
            }
            subUsados.add(progressBars[i]);
        }
        this.labelS = new JLabel("Subterraneos usados:  "+nSubUsados+"/" + sub);
        resultados.add(labelS);
        JScrollPane jsp = new JScrollPane(subUsados);
        resultados.add(jsp);
//        resultados.add(subUsados);
        result = new JLabel("<html>Ingresos normales fallidos: " + tif + "<br>"
                + "Ingresos especiales derivados: " + tief + "<br>"
                + "Numero de salidas: "+ sal);
        resultados.add(result);
        //Actualizar UI
        JOptionPane.showMessageDialog(this, "¡Terminado! Enter para continuar...");
        this.remove(this.esperando);
        this.add(resultados, BorderLayout.CENTER);
        this.validate();
        this.repaint();
        this.pack();
    }
}
