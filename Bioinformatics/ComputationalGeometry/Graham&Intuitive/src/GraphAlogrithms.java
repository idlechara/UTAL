import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphAlogrithms {

	public static ArrayList<Point2D> parseFile(File descriptor)
			throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(descriptor));
		int pointNumber = Integer.parseInt(br.readLine());
		ArrayList<Point2D> outputStack = new ArrayList<Point2D>();
		for (int points = 0; points < pointNumber; points++) {
			// TODO: Implementacion primaria.
			String in = br.readLine();
			outputStack.add(new Point2D(Integer.parseInt(in.split(" ")[0]),
					Integer.parseInt(in.split(" ")[1])));
		}
		return outputStack;
	}

	public static Point2D lowest = null;

	public static void recursiveQuickSort(List<Point2D> array, int firstIndex,
			int lastIndex) {
		int left = firstIndex, right = lastIndex;

		double pivotValue = getAngle(array.get((firstIndex + lastIndex) / 2));
		int pivotIndex = (firstIndex + lastIndex) / 2;

		boolean loop = true;
		while (loop) {
			while (getAngle(array.get(left)) < pivotValue)
				left++;
			while (getAngle(array.get(right)) > pivotValue)
				right--;

			if (left <= right) {
				Point2D aux = array.get(left);
				array.set(left, array.get(right));
				array.set(right, aux);
				left++;
				right--;
			}
			if (!(left <= right)) {
				loop = false;
			}
		}
		if (firstIndex < right)
			recursiveQuickSort(array, firstIndex, right);
		if (lastIndex > left)
			recursiveQuickSort(array, left, lastIndex);

	}

	public static double getAngle(Point2D p) {
		double angle = 0.0;

		double side1 = (p.x - lowest.x);
		double side2 = (p.y - lowest.y);
		if (side1 == 0) {
			return 90;
		}
		if (side2 == 0) {
			return 180;
		}

		angle = Math.atan(side2 / side1);

		if (p.x > lowest.x) {
			return Math.toDegrees(angle);
		} else {
			return 180 - Math.toDegrees(Math.abs(angle));
		}
	}

	public static Stack<Point2D> graham(List<Point2D> pointCloud,
			RenderingArea canvas) throws InterruptedException {
		// O(nlog n)
		Stack<Point2D> outputStack = new Stack<Point2D>();
		outputStack.push(lowest);
		outputStack.push(pointCloud.get(0));
		int count = 1;

		canvas.graph.drawLine(lowest.getCanvasCoords().x,
				lowest.getCanvasCoords().y, lowest.getCanvasCoords().x + 1,
				lowest.getCanvasCoords().y + 1);

		while (count < (pointCloud.size())) {
			Point2D pt1 = outputStack.get(outputStack.size() - 2);

			if (isLeft(pt1, (Point2D) outputStack.peek(), pointCloud.get(count)) > 0) {
				outputStack.push(pointCloud.get(count));
				count++;
				continue;
			}
			outputStack.pop();
		}

		for (Point2D point2d : outputStack) {

			canvas.graph.drawLine(point2d.getCanvasCoords().x,
					point2d.getCanvasCoords().y,
					point2d.getCanvasCoords().x + 1,
					point2d.getCanvasCoords().y + 1);
		}
		return outputStack;
	}

	public static double isLeft(Point2D p1, Point2D p2, Point2D p3) {
		return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY())
				- (p3.getX() - p1.getX()) * (p2.getY() - p1.getY());
	}
}
