import java.awt.Color;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		// TODO Auto-generated method stub
		if (args.length != 1)
			System.exit(1);
		System.out.print("Presione Intro para iniciar ejecucion.");
		System.in.read();

		System.out.println("Abriendo archivo " + args[0] + ".");
		File descriptor = new File(args[0]);

		JFrame outputWindow = new JFrame("GLX output");
		RenderingArea canvas = new RenderingArea();
		outputWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.createRenderingArea();
		outputWindow.add(canvas);
		outputWindow.pack();
		outputWindow.setVisible(true);
		canvas.graph = canvas.getGraphics();
		canvas.graph.setColor(Color.BLACK);

		ArrayList<Point2D> pointList = GraphAlogrithms.parseFile(descriptor);

		Thread.sleep(50);

		for (Point2D point2d : pointList) {
			canvas.graph.drawLine(point2d.getCanvasCoords().x - 1,
					point2d.getCanvasCoords().y - 1,
					point2d.getCanvasCoords().x + 1,
					point2d.getCanvasCoords().y + 1);
			canvas.graph.drawLine(point2d.getCanvasCoords().x - 1,
					point2d.getCanvasCoords().y + 1,
					point2d.getCanvasCoords().x + 1,
					point2d.getCanvasCoords().y - 1);
		}

		long start = System.currentTimeMillis();

		Collections.sort(pointList);
		GraphAlogrithms.lowest = pointList.get(0);

		List<Point2D> sublistOfPoints = pointList.subList(1, pointList.size());

		GraphAlogrithms.recursiveQuickSort(sublistOfPoints, 0,
				sublistOfPoints.size() - 1);

		Stack<Point2D> salida = GraphAlogrithms.graham(sublistOfPoints, canvas);

		Point2D temp = salida.get(0);

		canvas.graph.setColor(Color.GREEN);
		for (Point2D point2d : salida) {
			Thread.sleep(50);
			canvas.graph.drawLine(point2d.getCanvasCoords().x,
					point2d.getCanvasCoords().y, temp.getCanvasCoords().x,
					temp.getCanvasCoords().y);
			temp = point2d;
		}
		Thread.sleep(50);
		canvas.graph.drawLine(salida.get(0).getCanvasCoords().x, salida.get(0)
				.getCanvasCoords().y, temp.getCanvasCoords().x, temp
				.getCanvasCoords().y);

		FileWriter writter = new FileWriter("Salida.log");
		writter.write("Graham output\n");
		for (Point2D point2d : salida) {
			writter.write(point2d.toString() + "\n");
		}
		writter.write((System.currentTimeMillis() - start) + "milsecs\n");
		writter.write("////////////////\n");
		writter.write("Intuitive output\n");

		long start2 = System.currentTimeMillis();

		boolean[] extreme = new boolean[pointList.size()];

		for (int count = 0; count < pointList.size(); count++) {
			for (int count2 = 0; count2 < pointList.size(); count2++) {
				boolean fail = false;
				if (count != count2) {
					for (int point = 0; (point < pointList.size())
							&& (fail != true); point++) {

						if (count != point) {
							if (0 > GraphAlogrithms
									.isLeft(pointList.get(count),
											pointList.get(count2),
											pointList.get(point))) {
								fail = true;
							}
						}
					}
					if (fail == false) {
						extreme[count] = true;
						extreme[count2] = true;
					}
				}
			}
		}

		int index = 0;
		ArrayList<Point2D> print = new ArrayList<Point2D>();
		for (boolean b : extreme) {
			if (b == true) {
				print.add(pointList.get(index));
				writter.write(pointList.get(index) + "\n");
			}
			index++;
		}

		canvas.graph.setColor(Color.red);
		temp = print.get(0);
		for (Point2D point2d : print) {
			Thread.sleep(50);
			canvas.graph.drawLine(point2d.getCanvasCoords().x,
					point2d.getCanvasCoords().y, temp.getCanvasCoords().x,
					temp.getCanvasCoords().y);
			temp = point2d;
		}
		Thread.sleep(50);
		canvas.graph.drawLine(print.get(0).getCanvasCoords().x, print.get(0)
				.getCanvasCoords().y, temp.getCanvasCoords().x, temp
				.getCanvasCoords().y);

		writter.write((System.currentTimeMillis() - start2) + "milsecs\n");

		boolean equals = true;

		for (int test = 0; test < extreme.length; test++) {
			if (extreme[test] == true) {
				if (!salida.contains(pointList.get(test))) {
					equals = false;
					break;
				}
			}
		}

		writter.write("/////////////////\n");
		writter.write("summary:\n");
		writter.write("Graham = Intuitive? : " + equals);

		writter.flush();
	}
}
