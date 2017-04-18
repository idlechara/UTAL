import java.awt.Point;

//clase de encapsulamiento
@SuppressWarnings("serial")
public class PointType extends Point{
	
	//constructor
	public PointType(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y);
	}
	
	@Override
	public String toString() {
		return "("+ x +","+ y +")";
	}
}
