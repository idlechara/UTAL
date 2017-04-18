/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package day_of_sagitarius;

import java.io.Serializable;

/**
 *
 * @author kuroneko
 */
public class Jugador implements Serializable{
    int[][] grid;
    String Name;
   // int[] hp;
    int type;

    public Jugador(int[][]grid,String Name,int type){
        this.Name = Name;
        this.grid  = grid;
       // this.hp = hp;
        this.type = type;
    }
    void setInfo(int[][] grid, String Name, int type){
        this.Name = Name;
        this.grid  = grid;
       // this.hp = hp;
        this.type = type;
    }


}
