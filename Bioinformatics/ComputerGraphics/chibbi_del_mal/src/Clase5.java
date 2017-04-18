/**
 * El presente código tiene como objeto el presentar de manera práctica la forma
 * en que se realizan transformaciones afines en OpenGL, lo que se enmarca 
 * dentro de la revisión de contenidos para viewing 2D del curso de Computación 
 * Gráfica.
 * 
 * @author scsrsao
 * @version 1.0
 */

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.*;
import javax.media.opengl.glu.GLU;

public class Clase5 extends JFrame{

	/* esta es el area sobre la cual voy a dibujar usando openGL */
	protected GLCanvas glcanvas;

	/* el objeto que escucha a eventos de OpenGL y responde de manera acorde */
	private clase5EventListener glHandler;

	/**
	 * constructor
	 * @throws IOException
	 */
	public Clase5() throws IOException{
		/* constructor de la super clase, JFrame */
		super();
		/* el título del frame */
		this.setTitle("Clase 5 - Transformaciones afines");
		/* terminar la ejecucion una vez que cierro la ventana */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * main
	 * @param args
	 * @throws IOException
	 */
	static public void main(String []args)throws IOException{
		/* inicializacion de un objeto de la clase clase2 */
		Clase5 c5 = new Clase5();
		/* inicializar componentes de interfaz de usuario */
		c5.initGUI();
		/* hacer visible el frame */
		c5.setVisible(true);
	}

	/**
	 * initGUI
	 * Inicializa componentes de interfaz gráfica del programa
	 */
	public void initGUI(){
		/* inicializacion del handler para eventos de OpenGL */
		glHandler = new clase5EventListener(); 

		/* container del JFrame, aqui van todos los elementos de interfaz de
		 * usuario */
		Container container = super.getContentPane();
		
		/* Inicializo el profile de OpenGL a utilizar */
		GLProfile.initSingleton();
		GLProfile glp = GLProfile.getDefault();

		/* inicializar el panel de dibujo y sus capacidades */
		GLCapabilities glcaps = new GLCapabilities(glp);
		glcanvas = new GLCanvas(glcaps);
		glcanvas.addGLEventListener(glHandler);

		/* agregar el panel de dibujo al frame */
		container.add(glcanvas, BorderLayout.CENTER);
		
		/* fijar el tamaño del frame en una ventana de 450x300 pixeles */
		this.setSize(480, 480);
	}
	
	/**
	 * Método que dibuja una línea especial
	 * @param	gld	el objeto grafico sobre el que voy a dibujar (canvas)
	 */
	public void dibujarLinea(GLAutoDrawable gld){
		final GL2 gl = gld.getGL().getGL2();
		
		/* Selecciono la primitiva polilinea */
		gl.glBegin(GL.GL_LINE_STRIP);
			gl.glVertex2f(0, 0.1f);
			gl.glVertex2f(2, 0.1f);
			gl.glVertex2f(3, 1.5f);
			gl.glVertex2f(4, 1.5f);
			gl.glVertex2f(3, 0.1f);
			gl.glVertex2f(5, 0.1f);
			gl.glVertex2f(6, 1);
			gl.glVertex2f(7, 1);
			gl.glVertex2f(6, 0.1f);
			gl.glVertex2f(8, 0.1f);
			gl.glVertex2f(8.5f, 0.5f);
			gl.glVertex2f(9, 0.5f);
			gl.glVertex2f(8.7f, 0.1f);
			gl.glVertex2f(9.8f, 0.1f);
			gl.glVertex2f(10, 0);
		gl.glEnd();
	}


	/**
	 * Método que dibuja un auto
	 * @param	gld	el objeto grafico sobre el que voy a dibujar (canvas)
	 */
	public void dibujarAuto(GLAutoDrawable gld, float x, float y){
		final GL2 gl = gld.getGL().getGL2();
		
		/* Selecciono la primitiva polilinea */
		gl.glBegin(GL.GL_LINE_STRIP);
			gl.glVertex2f(2+x, 2+y);
			gl.glVertex2f(2+x, 3+y);
			gl.glVertex2f(3+x, 3+y);
			gl.glVertex2f(4+x, 4+y);
			gl.glVertex2f(7+x, 4+y);
			gl.glVertex2f(7+x, 3+y);
			gl.glVertex2f(8+x, 3+y);
			gl.glVertex2f(8+x, 2+y);
			gl.glVertex2f(2+x, 2+y);
//			gl.glVertex2f(6, 0.1f);
//			gl.glVertex2f(8, 0.1f);
//			gl.glVertex2f(8.5f, 0.5f);
//			gl.glVertex2f(9, 0.5f);
//			gl.glVertex2f(8.7f, 0.1f);
//			gl.glVertex2f(9.8f, 0.1f);
//			gl.glVertex2f(10, 0);
		gl.glEnd();
	}

	/**
	 * Método que dibuja una rueda
	 * @param	gld	el objeto grafico sobre el que voy a dibujar (canvas)
	 */
	public void dibujarRueda(GLAutoDrawable gld, float x, float y){
		final GL2 gl = gld.getGL().getGL2();
		
		/* Selecciono la primitiva polilinea */
		gl.glBegin(GL.GL_POLYGON);
			gl.glVertex2f(2, 2);
			gl.glVertex2f(2, 3);
			gl.glVertex2f(3, 3);
			gl.glVertex2f(4, 4);
			gl.glVertex2f(7, 4);
			gl.glVertex2f(7, 3);
			gl.glVertex2f(8, 3);
			gl.glVertex2f(8, 2);
			gl.glVertex2f(2, 2);
//			gl.glVertex2f(6, 0.1f);
//			gl.glVertex2f(8, 0.1f);
//			gl.glVertex2f(8.5f, 0.5f);
//			gl.glVertex2f(9, 0.5f);
//			gl.glVertex2f(8.7f, 0.1f);
//			gl.glVertex2f(9.8f, 0.1f);
//			gl.glVertex2f(10, 0);
		gl.glEnd();
	}
	
	/**
	 * Clase privada para el manejo del panel
	 * @author scsrsao
	 *
	 */
	private class clase5EventListener implements GLEventListener{

		/**
		 * Inicializacion del aspecto de OpenGL
		 */
		public void init (GLAutoDrawable gld){
			final GL2 gl = gld.getGL().getGL2();
			
			/* color de fondo */
			gl.glClearColor(1, 1, 1, 0);
			
			/* determino el tipo de proyeccion */
			gl.glMatrixMode(GL2ES1.GL_PROJECTION);
			gl.glLoadIdentity();
			
			/* definimos las dimensiones de la ventana del mundo */
			gl.glOrtho(-11, 11, -11, 11, -1, 1);
			
			/* definimos cuanto del frame utilizaremos para el pintado */
			gl.glViewport(0, 0, 480,480);
		}

		/**
		 * Pintado de la escena en OpenGL
		 */
		public void display(GLAutoDrawable gld){
			final GL2 gl = gld.getGL().getGL2();
			
			/* determino el color del fondo */
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);
			
			/* voy a dibujar utilizando negro */
			gl.glColor3f(0, 0, 0);
			
			/* dibujo mi linea especial */
			//dibujarLinea(gld);
			gl.glRotatef(60, 0, 0, 1);
			dibujarAuto(gld,0,0);
			
//			/* realizo una rotacion respecto del eje Z y vuelvo a dibujar */
//			gl.glRotatef(60, 0, 0, 1);
//			dibujarLinea(gld);
//			
//			/* realizo una rotacion respecto del eje Z y vuelvo a dibujar */
//			gl.glRotatef(60, 0, 0, 1);
//			dibujarLinea(gld);
//			
//			/* realizo una rotacion respecto del eje Z y vuelvo a dibujar */
//			gl.glRotatef(60, 0, 0, 1);
//			dibujarLinea(gld);
//			
//			/* realizo una rotacion respecto del eje Z y vuelvo a dibujar */
//			gl.glRotatef(60, 0, 0, 1);
//			dibujarLinea(gld);
//			
//			/* realizo una rotacion respecto del eje Z y vuelvo a dibujar */
//			gl.glRotatef(60, 0, 0, 1);
//			dibujarLinea(gld);
//
//			/* realizo una reflejo respecto del eje X y vuelvo a dibujar */
//			gl.glScalef(-1, 1, 1);
//			dibujarLinea(gld);
//			
//			/* realizo una rotacion respecto del eje Z y vuelvo a dibujar */
//			gl.glRotatef(60, 0, 0, 1);
//			dibujarLinea(gld);
//			
//			/* realizo una rotacion respecto del eje Z y vuelvo a dibujar */
//			gl.glRotatef(60, 0, 0, 1);
//			dibujarLinea(gld);
//			
//			/* realizo una rotacion respecto del eje Z y vuelvo a dibujar */
//			gl.glRotatef(60, 0, 0, 1);
//			dibujarLinea(gld);
//			
//			/* realizo una rotacion respecto del eje Z y vuelvo a dibujar */
//			gl.glRotatef(60, 0, 0, 1);
//			dibujarLinea(gld);
//			
//			/* realizo una rotacion respecto del eje Z y vuelvo a dibujar */
//			gl.glRotatef(60, 0, 0, 1);
//			dibujarLinea(gld);
		}

		/**
		 * Método llamado cuando el GLDrawable (GLCanvas o GLJPanel) ha cambiado
		 * de tamaño. Por el momento no lo necesitamos (aunque podria ser 
		 * utilizado de manera eventual en el futuro).
		 */
		public void reshape(
				GLAutoDrawable drawable, 
				int x, 
				int y, 
				int w, 
				int h
		) {	}

		/**
		 * Método llamado cuando la profundidad del display cambia durante la
		 * ejecución. Actualmente esto no sucede demasiado, a menos que el 
		 * programador lo realice.
		 */
		public void displayChanged(
				GLAutoDrawable drawable, 
				boolean modeChanged, 
				boolean deviceChanged
		) { }
		
		public void dispose(GLAutoDrawable arg0) {
			
		}

	}

}