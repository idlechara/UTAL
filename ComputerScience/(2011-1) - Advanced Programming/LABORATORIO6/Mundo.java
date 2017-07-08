import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Mundo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mundo  extends World
{

    /**
     * Constructor for objects of class Mundo.
     * 
     * 
     * 
     */

    public float velocidad = 50;
    public Perro gato;

    public Mundo()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        Random asdf = new Random(System.currentTimeMillis());
        for(int i=0; i<3; i++){

            addObject(new Balloon(), asdf.nextInt(600) , asdf.nextInt(400));
            addObject(new Balloon2(), asdf.nextInt(600) , asdf.nextInt(400));
        }
        gato = new Perro();
        addObject(gato, asdf.nextInt(600) , asdf.nextInt(400));
        this.

    }
}
