import java.util.Comparator;



public class AngleComparer implements Comparator<Point2D> {

	@Override
	public int compare(Point2D a, Point2D b){
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        double angle = 0.0;
        if (dx == 0.0) {
            if(dy == 0.0)     angle = 0.0;
            else if(dy > 0.0) angle = Math.PI / 2.0;
            else              angle = (Math.PI * 3.0) / 2.0;
        }
        else if(dy == 0.0) {
            if(dx > 0.0)      angle = 0.0;
            else              angle = Math.PI;
        }
        else {
            if(dx < 0.0)      angle = Math.atan(dy/dx) + Math.PI;
            else if(dy < 0.0) angle = Math.atan(dy/dx) + (2*Math.PI);
            else              angle = Math.atan(dy/dx);
        }
        return (int)((angle*180)/Math.PI);
	}

}
