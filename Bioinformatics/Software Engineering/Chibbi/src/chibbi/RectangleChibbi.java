/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chibbi;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

/**
 *
 * @author Erik Andres Regla Torres
 */
public class RectangleChibbi extends Group{
    Rectangle frame;
    Text text;
    
    public RectangleChibbi(double x, double y, String text, Group parent) {
        super();
        this.text = new Text(text);
        x = x-(this.text.getBoundsInLocal().getWidth()/2);
        y = y-(this.text.getBoundsInLocal().getHeight()/2);
        double rectangleHeight = this.text.getBoundsInLocal().getHeight()+20;
        double rectangleWidith = this.text.getBoundsInLocal().getWidth()+40;
        this.frame = new Rectangle(x-20, y-20, rectangleWidith, rectangleHeight);
        this.text.setX(x);
        this.text.setY(y);
        
        this.frame.setStrokeType(StrokeType.OUTSIDE);
        this.frame.setStroke(Color.BLUEVIOLET);
        this.frame.setStrokeWidth(3);
        this.frame.setFill(Color.LIGHTSTEELBLUE);
        
        this.getChildren().add(this.frame);
        this.getChildren().add(this.text);
        this.setOpacity(0.3);
        parent.getChildren().add(this);
    }
    
    public void enableItem(){
        this.setOpacity(1);
        this.on=true;
    }
    boolean on;
}
