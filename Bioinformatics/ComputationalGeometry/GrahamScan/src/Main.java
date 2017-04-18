import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import org.ietf.jgss.Oid;

import com.jogamp.opengl.impl.windows.wgl.GPU_DEVICE;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;


public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		if(args.length != 1) System.exit(1);
		System.out.print("Presione Intro para iniciar ejecucion.");
		System.in.read();
		System.out.println("Abriendo archivo "+args[0]+".");
		File descriptor = new File(args[0]);
		ArrayList<Point2D> pointList = GraphAlogrithms.parseFile(descriptor);
		Collections.sort(pointList);	
		Collections.sort(pointList, new AngleComparer());
		
		JFrame outputWindow = new JFrame("GLX output");
		outputWindow.setResizable(false);
		outputWindow.setAlwaysOnTop(true);
		outputWindow.setVisible(true);
		outputWindow.setSize(400,400);

		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);

		
		outputWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		OGLCanvas scene = new OGLCanvas();
		outputWindow.add(canvas);
		canvas.addGLEventListener(scene);
		FPSAnimator anim = new FPSAnimator(canvas,60);
		anim.add(canvas);
		anim.start();
//		Stack<Point2D> salida = GraphAlogrithms.graham(pointList, scene);
//		
//		FileWriter writter = new FileWriter("Salida.log");
//		for (Point2D point2d : salida) {
//			writter.write(point2d.toString() + "\n");
//		}
	}

}
