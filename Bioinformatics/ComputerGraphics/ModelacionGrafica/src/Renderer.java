import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;
import java.util.Calendar;
import java.util.Stack;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;

import javax.media.opengl.awt.*;
import javax.swing.JFrame;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.*;

import com.jogamp.opengl.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase renderer la cual renderiza un reloj de pendulo en 3D
 * 
 * Nota: Nececita tener instalalas las librerias de JOGL. se han de añadir los sgtes jar:
 * 		 newt.all, jogl.all, glueten-rt, y nativewindow.all
 * Y se ha de setear la native libray para nativewindow en la carpeta lib de donde se extrajo JOGL.
 * 
 * descargar JOGL desde aca. XD:: www.jogamp.org/deployment/autobuilds/
 * 
 * 
 * una demostracion mas que se puede hacer de todo en java, aunq me demore una tarde..xD
 * 
 * sorry si no esta optimizado el uso de recursos, pero es que = estaba aburrido, y no encontraba
 * que hacer.XD
 * 
 * @author natsuko
 *
 */
class Renderer implements GLEventListener {
	private GLU glu = new GLU();		//nececitamos la herramienta de glu
	private long tiempoInicio, tiempoFin;	//tiempos para calcular la ejecucion
	PointType[] pointArray;	//arreglo de puntos
	Stack<PointType> pilaPuntos;
	int i= 2;

	public Renderer(long tiempoInicio, PointType[] pointArray) {
		// TODO Auto-generated constructor stub
		super();
		this.pointArray = pointArray;
		this.tiempoInicio = tiempoInicio;
	}

	public void display(GLAutoDrawable gLDrawable) {
		final GL2 gl = gLDrawable.getGL().getGL2();			//se inicializa herramienta
		GLUT glut = new GLUT();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);	//se limpia la pantalla
		gl.glLoadIdentity();								//se carga la matriz identidad, osea, resetamos la matriz
		
		dibujarPuntosBase(gLDrawable);

		if(i<pointArray.length){
			if(esContrareloj(pilaPuntos.get(i-2), pilaPuntos.get(i-1), pointArray[i])){
				pilaPuntos.push(pointArray[i]);
				i++;
			}
			else{
				pilaPuntos.pop();
			}
		}
		
		gl.glBegin(GL.GL_LINE_STRIP);
			for (PointType punto : pilaPuntos) {
				gl.glVertex2d(punto.x, punto.y);
			}
		gl.glEnd();
		//TODO algortmo de muestra
	}

	public void dibujarPuntosBase(GLAutoDrawable gLDrawable){
		final GL2 gl = gLDrawable.getGL().getGL2();
		gl.glBegin(GL.GL_POINTS);
		for (PointType punto : this.pointArray) {
			gl.glVertex2d(punto.x, punto.y);
		}
		gl.glEnd();
	}

	public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) {
	}

	/**
	 * aca van los metodos que se han de llamar al inicio, algo asi como el seteo previo
	 */
	@Override
	public void init(GLAutoDrawable drawable) {

		pilaPuntos.add(pointArray[0]);
		pilaPuntos.add(pointArray[1]);
		
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(1, 1, 1, 0);
		//TODO: parametrizar el seteo
		gl.glOrtho(-200, 200, -200, 200, -1, 1);
	}

	boolean esContrareloj(PointType a, PointType b, PointType c){
		int valor = ((b.x - a.x) * (c.y - a.y)) - ((c.x - a.x) * (b.y - a.y));
		return valor >= 0;
	}
	/**
	 * por si a alguien se le ocurre redimensionar la pantalla... bueno, aca esta la solucion
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GL2 gl = drawable.getGL().getGL2();

		//vemos los parametro de la altura, si es que esta es valida
		if (height <= 0)
			height = 1;
		final float h = (float) width / (float) height;

		//procedemos a remiensionar
		gl.glViewport(0, 0, width, height);		//seteamos el viewport
		gl.glMatrixMode(GL2ES1.GL_PROJECTION); //cargamos el modo de la matriz
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, h, 1.0, 30.0);	//cargamos nuestra perspectiva
		gl.glMatrixMode(GL2ES1.GL_MODELVIEW);
		gl.glLoadIdentity();
	}


	public static void main(String[] args) {


		try {
			PointType[] pointArray;	//arreglo de puntos		
			//Main?

			//Seleccion de archivos
			System.out.println("Iniciando sistema de ConvexHull...");
			long tiempoInicio = System.currentTimeMillis();
			//descriptor de archivos y variable de ciclo
			File descriptorEntrada = null;
			//si es que la ruta es invalida
			if (descriptorEntrada.isFile() == false) {
				throw new FileNotFoundException();
			}
			BufferedReader reader;
			reader = new BufferedReader(new FileReader(descriptorEntrada));
			//comienza el parsing de los puntos
			//TODO CATCH SENTENCE
			int numeroPuntos = Integer.parseInt(reader.readLine());
			String lectura = null;
			pointArray = new PointType[numeroPuntos];
			int mayor = -1, mayorValue = 201;
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

				//verifica cual es el que esta mas abajo
				if (y <= mayorValue) {
					mayorValue = y;
					mayor = i;
				}
				if (y == mayorValue && pointArray[mayor].getX() < x) {
					mayorValue = y;
					mayor = i;
				}
				pointArray[i] = new PointType(x, y);
			}
			//fin ciclo lectura
			PointType p = pointArray[mayor];
			//inicio de ordenamiento por pendiente
			//TODO ordenamiento por pendiente
			for (PointType point : pointArray) {
				point.pendiente = (point.y - p.y) / (point.x - p.y);
			}
			quicksort(pointArray, 0, pointArray.length - 1);
			//fin ciclo

			//iniciamos gl
			GLProfile glp = GLProfile.getDefault();
			GLCapabilities caps = new GLCapabilities(glp);
			GLCanvas canvas = new GLCanvas(caps);

			//montamos nuestra primera pantalla
			Frame frame = new Frame("Salida GLX");
			frame.setSize(600, 600);
			frame.add(canvas);
			frame.setVisible(true);
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

			//le damos forma, ponemos los eventos de openGL
			Renderer scene = new Renderer(tiempoInicio, pointArray);
			canvas.addGLEventListener(scene);

			//y nuestro animador amigable.. XD gracias APIDOC!
			Animator animator = new FPSAnimator(canvas, 60);
			animator.add(canvas);
			animator.start();

		} 
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
			System.exit(1);
		}
		catch (Exception e) {
			System.exit(1);
		}
	}


	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	static void quicksort(PointType[] vector, int primero, int ultimo){
		int i=primero, j=ultimo;
		float pivote=vector[(primero + ultimo) / 2].pendiente;
		float auxiliar;

		do{
			while(vector[i].pendiente < pivote) i++;                  
			while(vector[j].pendiente > pivote) j--;

			if (i <= j){
				auxiliar = vector[j].pendiente;
				vector[j].pendiente = vector[i].pendiente;
				vector[i].pendiente = auxiliar;
				i++;
				j--;
			}

		} while (i<=j);

		if(primero<j) quicksort(vector,primero, j);
		if(ultimo>i) quicksort(vector,i, ultimo);
	}

}

class PointType extends Point{
	public PointType(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x, y);
		pendiente= 0;
	}

	public float pendiente;
}