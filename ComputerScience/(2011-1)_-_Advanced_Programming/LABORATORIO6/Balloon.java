import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Balloon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Balloon  extends Actor
{
    /**
     * Act - do whatever the Balloon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Random asdf = new Random(System.currentTimeMillis());
    int accel = asdf.nextInt(5)+1;
    
    public void act() 
    {
        this.setLocation(this.getX(), this.getY() + 1/ accel +
        (int)((Mundo)(this.getWorld())).velocidad );
        
        if(this.getY() == this.getWorld().getHeight()-1){
            this.setLocation(asdf.nextInt(800), 0);
            this.accel = +asdf.nextInt(10)+1;
        }
        
        else if(this.getY() == 0){
            this.setLocation(asdf.nextInt(800), this.getWorld().getHeight());
            this.accel = -asdf.nextInt(10)-1;
        }
        
        else if((this.intersects(((Mundo)(this.getWorld())).gato))&&
        ((Mundo)(this.getWorld())).velocidad > 0){
            if( ((Mundo)(this.getWorld())).velocidad < 30){
            ((Mundo)(this.getWorld())).velocidad += asdf.nextInt(20);
        }
            this.setLocation(asdf.nextInt(800), 0);
            this.accel = +asdf.nextInt(10)+1;
        }
    }    
}
