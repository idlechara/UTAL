import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.*;
import javax.media.opengl.glu.GLU;

class clase2 extends JFrame{

	/* esta es el area sobre la cual voy a dibujar usando openGL */
	protected GLCanvas glcanvas;

	/* el objeto que escucha a eventos de OpenGL y responde de manera acorde */
	private clase2EventListener glHandler;

	/**
	 * constructor
	 * @throws IOException
	 */
	public clase2() throws IOException{
		/* constructor de la super clase, JFrame */
		super();
		/* el título del frame */
		this.setTitle("Introduccion a Jogl");
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
		clase2 c2 = new clase2();
		/* inicializar componentes de interfaz de usuario */
		c2.initGUI();
		/* hacer visible el frame */
		c2.setVisible(true);
		c2.
	}

	public void initGUI(){
		/* inicializacion del handler para eventos de OpenGL */
		glHandler = new clase2EventListener(); 

		/* container del JFrame, aqui van todos los elementos de interfaz de
		 * usuario */
		Container container = super.getContentPane();
		
		GLProfile.initSingleton();
		GLProfile glp = GLProfile.getDefault();

		/* inicializar el panel de dibujo */
		GLCapabilities glcaps = new GLCapabilities(glp);
		glcanvas = new GLCanvas(glcaps);
		//glcanvas = new GLCanvas();
		glcanvas.addGLEventListener(glHandler);

		/* agregar el panel de dibujo al frame */
		container.add(glcanvas, BorderLayout.CENTER);
		/* fijar el tamaño del frame en una ventana de 300x300 pixeles */
		setSize(300, 300);
		
	}
	
	

	private class clase2EventListener implements GLEventListener{

		// Inicializacion de los graficos de OpenGL que GLCanvas usara, tales como el 
		// color de fondo, color de los objetos que se dibujaran, luces que se 
		// manejaran, etc. 
		public void init (GLAutoDrawable gld){
			final GL2 gl = gld.getGL().getGL2();
			GLU glu = new GLU();
			
			/* color a utilizar de fondo en el area de dibujo */
			gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
			
			/* proyecciones... de esto nos preocuparemos un poco mas adelante */
			gl.glMatrixMode(GL2ES1.GL_PROJECTION);
			gl.glLoadIdentity();
			
			/* definimos el sistema coordenado a utilizar */
			glu.gluOrtho2D(-5.0, 5.0, -5.0, 5.0);
			/* definimos cuanto del frame utilizaremos para el pintado */
			gl.glViewport(0, 0, 300, 300);
		}

		// es llamado por drawable para iniciar el renderizado de opengl a peticion del cliente. Dentro de este metodo 
		// se incluiran los graficos que glcanvas dibujara y sera llamado cada vez que se le solicite o bien cuanto 
		// todos los gleventlisteners hayan sido notificados de que ocurrio algun evento.
		public void display(GLAutoDrawable gld){
			final GL2 gl = gld.getGL().getGL2();
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);
			
			/* Inicializamos el array que representan
	    	 * las coordenadas de los vértices del circulo*/
			double punto[][] = new double[360][2];
			for (int i = 0; i<360; i++)
	    	{
	    		punto[i][0] = 0.0f;
	    		punto[i][1] = 0.0f;
	    	}

			/* dibujo el eje coordenado utilizando lineas rojas */
			gl.glColor3f(1,0,0);
			gl.glBegin(GL2.GL_LINE);
			gl.glVertex2f(-5.0f, 0.0f);
			gl.glVertex2f( 5.0f, 0.0f);						
			gl.glVertex2f(0.0f, 5.0f);
			gl.glVertex2f(0.0f, -5.0f);
			gl.glEnd();

			/* dibujo un triangulo blanco */
			// 0 = negro; 1 = blanco
			gl.glColor3f(0, 0, 0);
			gl.glBegin(GL.GL_TRIANGLES);
			gl.glVertex2f( -2.5f, 3.0f);
			gl.glVertex2f( -4.0f,-3.0f);
			gl.glVertex2f( -1.0f,-3.0f);
			gl.glEnd();				

			/* dibujo un rectangulo blanco */
			gl.glBegin(GL2.GL_QUADS);         
			gl.glVertex2f(0.5f, -2.0f);	
			gl.glVertex2f(2.0f, -2.0f);	
			gl.glVertex2f(2.0f, 2.0f);	
			gl.glVertex2f(0.5f, 2.0f);	
			gl.glEnd();
			
			/*dibujo un cuadrado*/
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2f(4.0f, 4.0f);	
			gl.glVertex2f(4.0f, 4.5f);	
			gl.glVertex2f(3.5f, 4.5f);
			gl.glVertex2f(3.5f, 4.0f);	
			gl.glEnd();
			
			/*dibujo un circulo verde*/
			gl.glColor3f(0,1,0);
			gl.glBegin(GL2.GL_POLYGON);
			// Inicializamos el primer vértice. Coordenada x (primer vértice)
			punto[0][0] = 3.0;
			
			// Coordenada y (primer vértice)
			punto[0][1] = 0.0;
			
			// Dibujamos el primer vértice
			gl.glVertex2d(punto[0][0], punto[0][1]);
			
			/* Dibujamos los 359 vértices restantes haciendo rotar
			 * el anterior 1º, utilizaremos el equivalente en
			 * radianes ya que las funciones sin y cos del
			 * paquete Math de Java trabaja con estos últimos */
			for (int i = 1; i<360; i++) {
				
				// Coordenada x' = x*cos(1º) - y*sin(1º)
				punto[i][0] = punto[i-1][0]*Math.cos(Math.toRadians(1)) - punto[i-1][1]*Math.sin(Math.toRadians(1));
			
				// Coordenada y' = x*sin(1º) + y*cos(1º)
				punto[i][1] = punto[i-1][0]*Math.sin(Math.toRadians(1)) + punto[i-1][1]*Math.cos(Math.toRadians(1));
				
				// Dibujamos el nuevo vértice obtenido
				gl.glVertex2d(punto[i][0], punto[i][1]);
			}
			gl.glEnd();
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
		) {
//			drawable.repaint();
		}

		/**
		 * Método llamado cuando la profundidad del display cambia durante la
		 * ejecución. Actualmente esto no sucede demasiado, a menos que el 
		 * programador lo realice.
		 */
		// visualizador (pantalla) asociado con GLAutoDrawable.  Los dos parametros 
		// indican el tipo de cambio ocurrido; o sea cambio de calidad de color o 
		// cambio en el visualizador.
		
		public void displayChanged(
				GLAutoDrawable drawable, 
				boolean modeChanged, 
				boolean deviceChanged
		) {}

		@Override
		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}

	}
}