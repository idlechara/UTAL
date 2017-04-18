/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chibbi;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

/**
 *
 * @author Erik Andres Regla Torres
 */
public class CircleChibbi extends Group {

    Circle frame;
    Text text;

    public CircleChibbi(double x, double y, String text, Group parent) {
        super();
        this.text = new Text(text);
        double textX = x - (this.text.getBoundsInLocal().getWidth() / 2);
        double textY = y - (this.text.getBoundsInLocal().getHeight() / 2);
        double radius = 0;
        
        if ((x - textX) > (y - textY)) radius = x - textX;
        else radius = y - textY;
        
        this.frame = new Circle(x-(radius*2)+10, y-(radius*2)-2, radius*4);
        
        this.text.setX(textX - this.text.getBoundsInLocal().getWidth() / 2);
        this.text.setY(textY - (this.text.getBoundsInLocal().getHeight() / 2));
        
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
