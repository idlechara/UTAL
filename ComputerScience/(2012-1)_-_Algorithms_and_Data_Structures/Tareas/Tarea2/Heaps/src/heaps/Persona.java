/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heaps;

import heaps.Structures.PriorityContainer;
import java.util.Random;

/**
 *
 * @author Kynku
 */
public class Persona extends PriorityContainer{
    private int tiempoRestante;
    private int tiempoDeLlegada;
    private int edad;
    private static Random randomizer = new Random(System.currentTimeMillis());
    
    
    
    public Persona(int segundosLlegada) {
        this.tiempoDeLlegada = segundosLlegada;
        this.edad = 11 + randomizer.nextInt(90);
        this.priority = (segundosLlegada/60) - (2*edad);
        this.tiempoRestante = randomizer.nextInt(121)+20;
    }
    
    public int getEdad(){
        return this.edad;
    }
    
    public int getTiempoRestante(){
        return this.tiempoRestante;
    }
    
    public int getTiempoDeLlegada(){
        return tiempoDeLlegada;
    }
    
    public boolean stepTimeOut(){
        return (this.tiempoRestante-- <= 0)? true : false;
    }

    @Override
    public String toString() {
        return "Persona{" + "tiempoRestante=" + tiempoRestante + ", tiempoDeLlegada=" + tiempoDeLlegada + ", edad=" + edad + '}';
    }
    
    
}
