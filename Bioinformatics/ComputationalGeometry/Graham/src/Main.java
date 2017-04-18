import java.awt.Color;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import javax.swing.JFrame;


public class Main {


	public static void main(String[] args){
		try {
			//la espera..xd
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Presione [ENTER] para iniciar...");
			br.readLine();
			br.close();
			
			PointType[] pointArray;	//arreglo de puntos		
			//Main?

			//Seleccion de archivos
			//Seleccion de archivos
			//Seleccion de archivos
			System.out.println("Iniciando sistema de ConvexHull...");
			long tiempoInicio = System.currentTimeMillis();
			//descriptor de archivos y variable de ciclo
			File descriptorEntrada = new File("convex.in");
			//si es que la ruta es invalida
			if (descriptorEntrada.isFile() == false) {
				throw new FileNotFoundException();
			}
			BufferedReader reader = new BufferedReader(new FileReader(descriptorEntrada));
			//comienza el parsing de los puntos
			//TODO CATCH SENTENCE
			int numeroPuntos = Integer.parseInt(reader.readLine());
			String lectura = null;
			pointArray = new PointType[numeroPuntos];
			int mayor = 0, mayorValue = 201;
			int x, y;
			//ciclo lectura primario
			for (int i = 0; i < numeroPuntos; i++) {
				//lectura
				lectura = reader.readLine();
				String[] puntos = lectura.split(" ");
				//conversion
				x = Integer.parseInt(puntos[0]);
				y = Integer.parseInt(puntos[1]);

				//si esta fuera de rango...
				if (x > 200 || x < -200 || y > 200 || y < -200) {
					throw new NumberFormatException();
				}

				pointArray[i] = new PointType(x, y);

				//verifica cual es el que esta mas abajo
				if (y < mayorValue) {
					mayorValue = y;
					mayor = i;
					continue;
				}
				if (y == mayorValue && pointArray[mayor].getX() < x) {
					mayorValue = y;
					mayor = i;
				}
			}
			reader.close();
			//fin ciclo lectura
			PointType p = pointArray[mayor];

			//inicio de ordenamiento por pendiente
			//TODO ordenamiento por pendiente
			quicksort(pointArray, 0, pointArray.length - 1, p);
			//fin ciclo
			
			//se crea la escena
			JFrame marco = new JFrame("Output Window");
			DisplayableCanvas canvas = new DisplayableCanvas();
			canvas.setCanvas();
			marco.add(canvas);
			marco.pack();
			marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			marco.setVisible(true);
			//canvas.repaint();
			canvas.loadGraphics();
			canvas.clearCanvas();
			canvas.setPointSize(5);
			Thread.sleep(100);
			canvas.drawPoints(pointArray);
			int i = 0;
			for (PointType pointType : pointArray) {
				canvas.drawPoint(pointType, i, Color.black);
				i++;
				Thread.sleep(500);
				///////////////////////////////////////////////////////////
				/* ACA SE PUEDE PONER UNA PAUSA PARA VER EL DESARROLLO ! */
				///////////////////////////////////////////////////////////
				
			}
			
			//inicia algorritmo de graham
			
			Stack<PointType> pila = new Stack<PointType>();

			//alocacion primaria: ojo, que quicksort no funka muuuuuy bien, asi q hice este peque�o arreglin.xd
			pila.push(pointArray[1]);
			pila.push(pointArray[0]);
			PointType a,b;
			int index = 1;
			i=2;
			while(i<pointArray.length){
				//dibuja el estado de la pila
				canvas.clearCanvas();
				canvas.drawPoints(pointArray);
				PointType temp = pila.get(0);
				for (PointType pointType : pila) {
					canvas.drawLine(temp, pointType);
					temp = pointType;
				}
				///////////////////////////////////////////////////////////
				/* ACA SE PUEDE PONER UNA PAUSA PARA VER EL DESARROLLO ! */
				///////////////////////////////////////////////////////////
				Thread.sleep(500);
				

				//realoca los valores
				a = pila.get(index-1);
				b = pila.get(index);

				//verifica la situacion del punto
				if(esContrareloj(a, b, pointArray[i])){
					pila.push(pointArray[i]);
					i++;
				}
				else{
					pila.pop();
				}
				index = pila.size()-1;
			}
			//fin algortimo graham
			
			//imprime resultados y cierra poligono
			PointType temp = pila.get(0);
			for (PointType pointType : pila) {
				canvas.drawLine(temp, pointType);
				temp = pointType;
			}
			canvas.drawLine(temp, pila.get(0));
			
			//se imprimen resultados
			BufferedWriter writter = new BufferedWriter(new FileWriter("salida.log"));
			
			for (PointType pointType : pila) {
				writter.write(pointType.x + " " + pointType.y);
				writter.newLine();
			}
			writter.write("Tiempo Ejecucion: " + (System.currentTimeMillis() - tiempoInicio) + " milsecs.");
			writter.newLine();
			writter.flush();
			writter.close();
			
			System.out.println("Ejecucion terminada. Cierre la salida para terminar.");
		} 
		//manejo de errores
		catch (FileNotFoundException e) {
			System.err.println("Ruta no encontrada o invalida... ejecucion terminada.");
			System.exit(1);
		} catch (NumberFormatException e) {
			System.err.println("Error en formato de archivo... ejecucion terminada.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error de E/S... ejecucion terminada.");
			System.exit(1);
		} catch (NullPointerException e) {
			System.err.println("Error de apertura de fichero... ejecucion terminada.");
			e.printStackTrace();
			System.exit(1);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	
	}
	
	//un quicksort... pero esta medio fallado... asi que antes se hace un arreglin.. pero sirve para lo que tiene q servir
	//usa como criterio de ordenamiento, la pendiente.
	static void quicksort(PointType[] vector, int primero, int ultimo, PointType p){
		int i=primero, j=ultimo;
		PointType pivote=vector[(primero + ultimo) / 2];
		PointType auxiliar;
	
		do{
			while( areaTriangulo(p, pivote, vector[i])< 0) i++;                  
			while(areaTriangulo(p, pivote, vector[j])> 0) j--;
	
			if (i <= j){
				auxiliar = vector[j];
				vector[j] = vector[i];
				vector[i] = auxiliar;
				i++;
				j--;
			}
	
		} while (i<=j);
	
		if(primero<j) quicksort(vector,primero, j, p);
		if(ultimo>i) quicksort(vector,i, ultimo, p);
	}

	//validador de que si un punto esta a la derecha de un vector, osea si el 
	//triangulo formado tiene pendiente positiva
	static boolean esContrareloj(PointType a, PointType b, PointType c){
		return areaTriangulo(a, b, c) >= 0;
	}

	// regresa el area de un triangulo
	static float areaTriangulo(PointType a, PointType b, PointType c){
		return (b.x - a.x) * (c.y - a.y)- (c.x - a.x) * (b.y - a.y);
	}
	
	
}
