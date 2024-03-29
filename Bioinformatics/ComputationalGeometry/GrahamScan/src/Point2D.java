import java.awt.Point;


public class Point2D extends Point implements Comparable<Point>{

	public Point2D() {
		// TODO Auto-generated constructor stub
	}

	public Point2D(Point p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public Point2D(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Point o) {
		if(this.y != o.y)	return this.y - o.y;
		else{
			return this.x - o.x;
		}
	}

	@Override
    public String toString() {
	return "[x=" + x + ",y=" + y + "]";
    }

}
