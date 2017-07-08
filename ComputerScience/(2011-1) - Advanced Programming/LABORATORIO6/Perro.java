import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class Perro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Perro  extends Actor
{
    /**
     * Act - do whatever the Perro wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Random asdf = new Random(System.currentTimeMillis());
    int accel = -asdf.nextInt(5)-1;
public     int puntaje = 0;
    
    public void act() 
    {
        MouseInfo info = Greenfoot.getMouseInfo();
        if(info != null){
        this.setLocation(info.getX(),info.getY());
        }
        
        if((int)((Mundo)(this.getWorld())).velocidad > -300){
            ((Mundo)(this.getWorld())).velocidad -= 0.5;
        }
         
        if((int)((Mundo)(this.getWorld())).velocidad <= -100){
            int WIDTH=400, HEIGHT = 300;
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(0, 0, 0, 160));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(255, 255, 255, 100));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(40);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString("Puntaje final -> " + puntaje, 60, 100);
        setImage(image);
            Greenfoot.stop();
            //System.exit(0);
        }
    }    
}
