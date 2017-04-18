import java.util.ArrayList;
import java.util.Stack;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;


public class OGLCanvas implements GLEventListener {
	ArrayList<Point2D> listOfPoints = null;
	Stack<Point2D> pointStack = null;
	Point2D p = null;

	public void stepTwo(ArrayList<Point2D> listOfPoints, Stack<Point2D> pointStack, Point2D p) throws InterruptedException{
		this.listOfPoints = listOfPoints;
		this.pointStack = pointStack;
		this.p = p;
		Thread.sleep(100);
	}

	public void drawLine(Point2D p1, Point2D p2, GLAutoDrawable renderingPort) {

		GL2 gl = renderingPort.getGL().getGL2();
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(p1.x, p1.y);
		gl.glVertex2i(p2.x, p2.y);
		gl.glEnd();
	}

	public void drawPolyLine(ArrayList<Point2D> listOfPoints, GLAutoDrawable renderingPort) {


		GL2 gl = renderingPort.getGL().getGL2();
		if (listOfPoints!=null) {
			gl.glBegin(GL.GL_LINE_STRIP);
			for (Point2D point2d : listOfPoints) {
				gl.glVertex2i(point2d.x, point2d.y);
			}
			gl.glEnd();
		}
	}

	public void drawPolyLine(Stack<Point2D> listOfPoints, Point2D p, GLAutoDrawable renderingPort) {

		GL2 gl = renderingPort.getGL().getGL2();
		if (listOfPoints!=null) {
			gl.glBegin(GL.GL_LINE_STRIP);
			for (Point2D point2d : listOfPoints) {
				gl.glVertex2i(point2d.x, point2d.y);
			}
			gl.glVertex2i(p.x, p.y);
			gl.glEnd();
		}
	}

	public void drawDot(Point2D p, GLAutoDrawable renderingPort) {

		GL2 gl = renderingPort.getGL().getGL2();
		gl.glBegin(GL.GL_POINTS);
		gl.glVertex2i(p.x, p.y);
		gl.glEnd();
	}

	public void drawDotCloud(ArrayList<Point2D> listOfPoints, GLAutoDrawable renderingPort) {

		GL2 gl = renderingPort.getGL().getGL2();

		if (listOfPoints!=null) {
			gl.glBegin(GL.GL_POINTS);
			for (Point2D point2d : listOfPoints) {
				gl.glVertex2i(point2d.x, point2d.y);
			}
			gl.glEnd();
		}
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable renderingPort) {

		GL2 gl = renderingPort.getGL().getGL2();
		gl.glClearColor(1, 1, 1, 0);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glOrtho(-200, 200, -200, 200, -1, 1);
		gl.glViewport(-200, 200, -200, 200);
		gl.glColor3f(0, 0, 0);
		gl.glPointSize(3);

		System.out.println("ASDSA");
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(GLAutoDrawable arg0) {

		GL2 gl = arg0.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//		try {
//			this.drawStepTwo(arg0);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(100, 200);
		gl.glVertex2i(-100, 200);
		gl.glVertex2i(100, -200);
		gl.glEnd();
		gl.glFlush();
	}



	public void drawStepOne(GLAutoDrawable renderingPort) throws InterruptedException{

		GL2 gl = renderingPort.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		this.drawDotCloud(listOfPoints, renderingPort);
		gl.glFlush();
		Thread.sleep(100);
	}
	public void drawStepTwo(GLAutoDrawable renderingPort) throws InterruptedException{

		GL2 gl = renderingPort.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		this.drawDotCloud(listOfPoints, renderingPort);
		this.drawPolyLine(pointStack, p, renderingPort);
		gl.glFlush();
		Thread.sleep(100);
	}

}
