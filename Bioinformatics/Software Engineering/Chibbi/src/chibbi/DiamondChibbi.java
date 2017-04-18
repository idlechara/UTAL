/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chibbi;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import javafx.event.EventHandler;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Erik Andres Regla Torres
 */
public class DiamondChibbi extends Group {

    Polygon frame;
    Text text, yes, no;
    
    public void setYesListener(EventHandler ev){
        this.yes.setOnMouseClicked(ev);
    }
    public void setNoListener(EventHandler ev){
        this.no.setOnMouseClicked(ev);
    }

    public DiamondChibbi(double x, double y, String text, Group parent) {
        super();
        this.text = new Text(text);
        double originX = x;
        double originY = y;
        x = x - (this.text.getBoundsInLocal().getWidth() / 2);
        y = y - (this.text.getBoundsInLocal().getHeight() / 2);
        this.text.setX(x);
        this.text.setY(y+8);

        double deltaX = 2 * (originX - x);
        double deltaY = 2 * (originY - y);

        this.frame = new Polygon();
        this.frame.getPoints().addAll(new Double[]{
                    originX - deltaX, originY,
                    originX, originY - deltaY,
                    originX + deltaX, originY,
                    originX, originY + deltaY
                });

        this.yes = new Text(originX+8 + deltaX, originY,"si");
        this.no = new Text(originX, originY + deltaY+9,"no");
        
        this.frame.setStrokeType(StrokeType.OUTSIDE);
        this.frame.setStroke(Color.BLUEVIOLET);
        this.frame.setStrokeWidth(3);
        this.frame.setFill(Color.LIGHTSTEELBLUE);

        this.getChildren().add(this.frame);
        this.getChildren().add(this.text);
        this.getChildren().add(this.yes);
        this.getChildren().add(this.no);
        this.setOpacity(0.3);
        parent.getChildren().add(this);
    }
    
    public void enableItem(){
        this.setOpacity(1);
        this.on=true;
    }
    boolean on;
    boolean choice;
    boolean yesNo;
}
