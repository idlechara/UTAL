/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package day_of_sagitarius;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kuroneko
 */
public class superDisparo implements ActionListener{

    Battle root;
    int i,j;

    superDisparo(Battle root, int i, int j){
        this.root = root;
        this.i = i;
        this.j = j;
    }

    public void actionPerformed(ActionEvent e) {
      //  this.root.shoot(this.i,this.j);
    }

}
