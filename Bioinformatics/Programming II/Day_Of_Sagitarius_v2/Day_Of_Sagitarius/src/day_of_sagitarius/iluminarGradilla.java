/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package day_of_sagitarius;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author kuroneko
 */
class iluminarGradilla implements MouseListener{
    int i, j;
    newGame root;

    public iluminarGradilla(newGame root, int i, int j) {
        this.i= i;
        this.j = j;
        this.root = root;
    }

    public void mouseClicked(MouseEvent e) {
        this.root.cargar(i, j);
    }

    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
        this.root.iluminarBotones(this.i, this.j);
    }

    public void mouseExited(MouseEvent e) {
        this.root.apagarBotones(this.i, this.j);
    }


}