import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;


public class GraphAlogrithms {
	
	public static ArrayList<Point2D> parseFile(File descriptor) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(descriptor));
		int pointNumber = Integer.parseInt(br.readLine());
		ArrayList<Point2D> outputStack = new ArrayList<Point2D>();
		for(int i=0; i<pointNumber; i++){
			//TODO: Implementacion primaria.
			String in = br.readLine();
			outputStack.add(new Point2D(Integer.parseInt(in.split(" ")[0]),Integer.parseInt(in.split(" ")[1])));
		}
		return outputStack;
	}
	

    public static Stack<Point2D> graham(ArrayList<Point2D> pointCloud, OGLCanvas output) throws InterruptedException {
        //O(nlog n)
    	Stack<Point2D> outputStack = new Stack<Point2D>();
    	outputStack.push(pointCloud.get(0));
    	outputStack.push(pointCloud.get(1));
        int i = 2;
        //output.stepOne(pointCloud);
        while(i<(pointCloud.size())) {
        	output.stepTwo(pointCloud, outputStack, pointCloud.get(i));
        	//output.drawDotCloud(pointCloud);
        	Point2D pt1 = outputStack.get(outputStack.size()-2);
        	
            if(isLeft((Point2D) outputStack.peek(), pt1, pointCloud.get(i))>0) {
            	outputStack.push(pointCloud.get(i));
                i++;
                continue;
            }
            outputStack.pop();
        }
        return outputStack;
    }


    private static double isLeft(Point2D p1, Point2D p2, Point2D p3) {
        return (p2.getX() - p1.getX())*(p3.getY() - p1.getY()) -
                (p3.getX() - p1.getX())*(p2.getY() - p1.getY());
    }
}
