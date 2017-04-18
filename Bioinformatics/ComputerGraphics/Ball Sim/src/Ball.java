import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.TextureIO;

/**
 * 
 */

/**
 * @author natsuko
 *
 */
public class Ball implements GLEventListener {

	/* (non-Javadoc)
	 * @see javax.media.opengl.GLEventListener#display(javax.media.opengl.GLAutoDrawable)
	 */
	@Override
	public void display(GLAutoDrawable gLDrawable) {
		final GL2 gl = gLDrawable.getGL().getGL2();			//se inicializa herramienta
		GLUT glut = new GLUT();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);	//se limpia la pantalla
		gl.glLoadIdentity();								//se carga la matriz identidad, osea, resetamos la matriz
		
		//traza primera transposcion:
		gl.glTranslated(0, 0, -5);
		
		//dibujo triangulo beta:gl.glDisable(GL.GL_TEXTURE_2D);	//se desactivan las texturas
    	float[] rgba = {1f, 1f, 1f};	//se setea el color del material
    	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, rgba, 0);	//se setean las caracteristicas del material
    	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_SPECULAR, rgba, 0);
    	gl.glMaterialf(GL.GL_FRONT, GLLightingFunc.GL_SHININESS, 50f);
    	
		gl.glBegin(gl.GL_TRIANGLES);
		gl.glVertex3f(1, 1, 0);
		gl.glVertex3f(1, 0, 1);
		gl.glVertex3f(0, 0, 0);
		gl.glEnd();


	}

	/* (non-Javadoc)
	 * @see javax.media.opengl.GLEventListener#dispose(javax.media.opengl.GLAutoDrawable)
	 */
	@Override
	public void dispose(GLAutoDrawable glDrawable) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.media.opengl.GLEventListener#init(javax.media.opengl.GLAutoDrawable)
	 */
	@Override
	public void init(GLAutoDrawable gLDrawable) {
		// TODO Auto-generated method stub

		GL2 gl = gLDrawable.getGL().getGL2();
		GLU glu = new GLU();
		
		gl.glShadeModel(GLLightingFunc.GL_SMOOTH);              // Hablilitamos un shading para que se vean mas bonitas las sombras
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // dejamos un fondo negro
		gl.glClearDepth(1.0f);                      // dejamos la profundidad del buffer
		gl.glEnable(GL.GL_DEPTH_TEST);							// habilitamos el testeo
		gl.glDepthFunc(GL.GL_LEQUAL);								// y luego de decimos cual es
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);	// y unos calculos de perspectiva
		
		gl.glMatrixMode(GL2ES1.GL_PROJECTION); //cargamos el modo de la matriz
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, 1.0, 1.0, 30.0);	//cargamos nuestra perspectiva
		gl.glMatrixMode(GL2ES1.GL_MODELVIEW);
		gl.glLoadIdentity();

		//definimos las luces a usar
		float[] luz_de_ambiente = {0.2f, 0.2f, 0.2f, 1.0f};
		float[] luz_difusa = {1f, 1f, 0.2f, 1.0f};
		float[] posicion_luz = {10f, 5f, 0f, 1.0f};

		//seteamos las luces
		gl.glLightfv(GLLightingFunc.GL_LIGHT1, GLLightingFunc.GL_AMBIENT, luz_de_ambiente, 0);
		gl.glLightfv(GLLightingFunc.GL_LIGHT1, GLLightingFunc.GL_DIFFUSE, luz_difusa, 0);
		gl.glLightfv(GLLightingFunc.GL_LIGHT1, GLLightingFunc.GL_POSITION, posicion_luz, 0);
		gl.glEnable(GLLightingFunc.GL_LIGHT1);
		gl.glEnable(GLLightingFunc.GL_LIGHTING);
	}

	/* (non-Javadoc)
	 * @see javax.media.opengl.GLEventListener#reshape(javax.media.opengl.GLAutoDrawable, int, int, int, int)
	 */
	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3,
			int arg4) {       
		
		//TODO escribir codigo de redimensionado
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//iniciamos gl
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		caps.setDoubleBuffered(true);
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
		Ball scene = new Ball();
		canvas.addGLEventListener(scene);

		//y nuestro animador amigable.. XD gracias APIDOC!
		FPSAnimator animator = new FPSAnimator(canvas, 60);
		animator.add(canvas);
		animator.start();
		

	}

}
