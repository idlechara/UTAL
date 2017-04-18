/*
 * Dejamos las importaciones necesarias
 */
import java.io.*;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.*;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.gl2.GLUT;

import com.jogamp.opengl.util.*;


/**
 * Clase Reloj, la cual hereda de un escuchador de evenots de open gl, el cual renderizara un reloj 3d
 * @author Natsuko
 *
 */
public class Reloj implements GLEventListener {
	
	/*
	 * aca debiesesn ir los datos nesesarios
	 */

	final GLU glu = new GLU();
	final GLUT glut = new GLUT();
	
	public static void main(String[] args){

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
		Reloj scene = new Reloj();
		canvas.addGLEventListener(scene);
		
		//y nuestro animador amigable.. XD gracias APIDOC!
		FPSAnimator animator = new FPSAnimator(canvas, 60);
		animator.add(canvas);
		animator.start();
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		final GLU glu = new GLU();
		final GLUT glut = new GLUT();
		//gira la wea cada cierto tiempo..xD
		gl.glRotatef(1, 0, 1, 0);
		gl.glColor3f(255, 255, 255);
		renderizarPlaca(new Point3D(20,-15,0), 10, 70, 10, drawable);
		//renderizarPlaca(new Point3D(0,55,0), 50, 5, 10, drawable);
		//renderizarPlaca(new Point3D(0,-15,0), 60, 5, 20, drawable);

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		final GLU glu = new GLU();
		final GLUT glut = new GLUT();

        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl.glClearDepth(1.0f);												// Depth Buffer Setup
    	gl.glEnable(GL.GL_DEPTH_TEST);										// Enables Depth Testing
    	gl.glDepthFunc(GL.GL_LEQUAL);										// The Type Of Depth Test To Do

		// Enable smooth shading, which blends colors nicely, and smoothes out lighting.
		gl.glShadeModel(gl.GL_SMOOTH);
		// Set background color in RGBA. Alpha: 0 (transparent) 1 (opaque) 
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		//se carga el tipo de proyeccion--xD
		gl.glMatrixMode(GL2ES1.GL_PROJECTION);
		gl.glLoadIdentity();
		
		//el color del fondo (borrado), y las dimensiones
		gl.glOrtho(-100, 100, -100, 100, -100, 100);
		

		//se define desde donde se ve.
		gl.glMatrixMode(GL2ES1.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluLookAt(40, 40, -40, 0, 0, 0, 0, 25, 0);
		
        // Enable smooth shading.
        gl.glShadeModel(gl.GL_SMOOTH);

        // Define "clear" color.
        gl.glClearColor(0f, 0f, 0f, 0f);

        // We want a nice perspective.
        gl.glHint(gl.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
/*
        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {0, 30, 15, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.1f, 0.1f, 0.1f, 1f};
        float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 1f};
		
        // Set light parameters.
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_POSITION, lightPos, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_SPECULAR, lightColorSpecular, 0);

        // Enable lighting in GL.
        gl.glEnable(gl.GL_LIGHT1);
        gl.glEnable(gl.GL_LIGHTING);

        // Set material properties.
        float[] rgba = {1f, 1f, 1f};
        gl.glMaterialfv(GL.GL_FRONT, gl.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, gl.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, gl.GL_SHININESS, 0.5f);*/
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Metodo que rederiza una placa.
	 * @param puntoInicio coordenada de incio del dibujo
	 * @param y alto.. ¬¬ y
	 * @param x ancho de la placa x
	 * @param z largo de la placa z
	 */
	public void renderizarPlaca(Point3D puntoInicio, float x, float y, float z, GLAutoDrawable drawable){
		//inicializamos herramientas
		final GL2 gl = drawable.getGL().getGL2();
//		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		//seteamos nuestros puntos
		Point3D[][] placas = new Point3D[2][4];
		
		placas[0][0] = puntoInicio.translate(-(x/2), 0, z/2);
		placas[0][1] = puntoInicio.translate(x/2, 0, z/2);
		placas[0][3] = puntoInicio.translate(-(x/2), 0, -(z/2));
		placas[0][2] = puntoInicio.translate((x/2), 0, -(z/2));
		
		placas[1][0] = puntoInicio.translate(-(x/2), y, z/2);
		placas[1][1] = puntoInicio.translate(x/2, y, z/2);
		placas[1][3] = puntoInicio.translate(-(x/2), y, -(z/2));
		placas[1][2] = puntoInicio.translate((x/2), y, -(z/2));
		
		//procedemos a dibujar!
		//placa superior
		gl.glColor4f(255, 255, 255, 1);
		
		
		gl.glPushMatrix();
		
		gl.glBegin(gl.GL_QUADS);
		gl.glNormal3f(0,0 , -1);
		gl.glVertex3f(10, 10, -10);
		gl.glVertex3f(10, -10, -10);
		gl.glVertex3f(-10, -10, -10);
		gl.glVertex3f(-10, 10, -10);
		
		gl.glEnd();
		gl.glRotatef(90, 1, 0, 0);
		gl.glBegin(gl.GL_QUADS);

		gl.glVertex3f(10, 10, -10);
		gl.glVertex3f(10, -10, -10);
		gl.glVertex3f(-10, -10, -10);
		gl.glVertex3f(-10, 10, -10);
		
		gl.glEnd();
		gl.glRotatef(90, 1, 0, 0);
		gl.glBegin(gl.GL_QUADS);

		gl.glVertex3f(10, 10, -10);
		gl.glVertex3f(10, -10, -10);
		gl.glVertex3f(-10, -10, -10);
		gl.glVertex3f(-10, 10, -10);
		
		gl.glEnd();
		gl.glRotatef(90, 1, 0, 0);
		gl.glBegin(gl.GL_QUADS);

		gl.glVertex3f(10, 10, -10);
		gl.glVertex3f(10, -10, -10);
		gl.glVertex3f(-10, -10, -10);
		gl.glVertex3f(-10, 10, -10);
		
		gl.glEnd();
		gl.glRotatef(90, 0, 1, 0);
		gl.glBegin(gl.GL_QUADS);

		gl.glVertex3f(10, 10, -10);
		gl.glVertex3f(10, -10, -10);
		gl.glVertex3f(-10, -10, -10);
		gl.glVertex3f(-10, 10, -10);
		
		gl.glEnd();
		gl.glRotatef(180, 1, 0, 0);
		gl.glBegin(gl.GL_QUADS);

		gl.glVertex3f(10, 10, -10);
		gl.glVertex3f(10, -10, -10);
		gl.glVertex3f(-10, -10, -10);
		gl.glVertex3f(-10, 10, -10);
		
		gl.glEnd();
		gl.glPopMatrix();
	}
	
}

/**
 * Clase que sirve para guardar la informacion de puntos en el plano y sus respectivas operaciones
 * @author Natsuko
 *
 */
class Point3D{
	float x;
	float y;
	float z;
	
	/**
	 * Crea un punto 3d con las coordenadas dadas
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param z coordenada z
	 */
	public Point3D(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.print();
	}
	
	/**
	 * crea un punto 3d por defecto (0,0,0)
	 */
	public Point3D(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	/**
	 * transloca el punto.
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Point3D translate(float x, float y, float z){
		Point3D returner = new Point3D(this.x,this.y, this.z);
		returner.x+=x;
		returner.y+=y;
		returner.z+=z;
		return (returner);
	}

	public float[] getVertex(){
		float[] vertex = {this.x,this.y,this.z};
		return vertex;
	}
	
	public void draw(GLAutoDrawable drawable){
		final GL2 gl = drawable.getGL().getGL2();
		gl.glVertex3f(this.x,this.y,this.z);
	}
	
	public void print(){
		System.out.println("Vertex: x:" + x + " y:"+ y + " z:"+ z);
	}
}



/*
public void renderizarPlaca(Point3D puntoInicio, float x, float y, float z, GLAutoDrawable drawable){
	//inicializamos herramientas
	final GL2 gl = drawable.getGL().getGL2();
//	gl.glClear(GL.GL_COLOR_BUFFER_BIT);

	final GLU glu = new GLU();
	final GLUT glut = new GLUT();
	
	//seteamos nuestros puntos
	Point3D[][] placas = new Point3D[2][4];
	
	placas[0][0] = puntoInicio.translate(-(x/2), 0, z/2);
	placas[0][1] = puntoInicio.translate(x/2, 0, z/2);
	placas[0][3] = puntoInicio.translate(-(x/2), 0, -(z/2));
	placas[0][2] = puntoInicio.translate((x/2), 0, -(z/2));
	
	placas[1][0] = puntoInicio.translate(-(x/2), y, z/2);
	placas[1][1] = puntoInicio.translate(x/2, y, z/2);
	placas[1][3] = puntoInicio.translate(-(x/2), y, -(z/2));
	placas[1][2] = puntoInicio.translate((x/2), y, -(z/2));
	
	//procedemos a dibujar!
	//placa superior
	gl.glColor3f(0, 0, 0);
	gl.glBegin(gl.GL_QUADS);
	gl.glNormal3f(0, 10, 0);
	gl.glVertex3fv(placas[0][0].getVertex(), 0);
	gl.glNormal3f(0, 10, 0);
	gl.glVertex3fv(placas[0][1].getVertex(), 0);
	gl.glNormal3f(0, 10, 0);
	gl.glVertex3fv(placas[0][2].getVertex(), 0);
	gl.glNormal3f(0, 10, 0);
	gl.glVertex3fv(placas[0][3].getVertex(), 0);
	gl.glNormal3f(0, -10, 0);
	gl.glVertex3fv(placas[1][0].getVertex(), 0);
	gl.glNormal3f(0, -10, 0);
	gl.glVertex3fv(placas[1][1].getVertex(), 0);
	gl.glNormal3f(0, -10, 0);
	gl.glVertex3fv(placas[1][2].getVertex(), 0);
	gl.glNormal3f(0, -10, 0);
	gl.glVertex3fv(placas[1][3].getVertex(), 0);
	gl.glNormal3f(10, 0, 0);
	gl.glVertex3fv(placas[0][1].getVertex(), 0);
	gl.glNormal3f(10, 0, 0);
	gl.glVertex3fv(placas[0][2].getVertex(), 0);
	gl.glNormal3f(10, 0, 0);
	gl.glVertex3fv(placas[1][2].getVertex(), 0);
	gl.glNormal3f(10, 0, 0);
	gl.glVertex3fv(placas[1][1].getVertex(), 0);
	gl.glNormal3f(0, 0, 10);
	gl.glVertex3fv(placas[0][0].getVertex(), 0);
	gl.glNormal3f(0, 0, 10);
	gl.glVertex3fv(placas[0][1].getVertex(), 0);
	gl.glNormal3f(0, 0, 10);
	gl.glVertex3fv(placas[1][1].getVertex(), 0);
	gl.glNormal3f(0, 0, 10);
	gl.glVertex3fv(placas[1][0].getVertex(), 0);
	gl.glNormal3f(-10, 0, 0);
	gl.glVertex3fv(placas[0][0].getVertex(), 0);
	gl.glNormal3f(-10, 0, 0);
	gl.glVertex3fv(placas[0][3].getVertex(), 0);
	gl.glNormal3f(-10, 0, 0);
	gl.glVertex3fv(placas[1][3].getVertex(), 0);
	gl.glNormal3f(-10, 0, 0);
	gl.glVertex3fv(placas[1][0].getVertex(), 0);
	gl.glNormal3f(0, 0, 10);
	gl.glVertex3fv(placas[0][3].getVertex(), 0);
	gl.glNormal3f(0, 0, 10);
	gl.glVertex3fv(placas[0][2].getVertex(), 0);
	gl.glNormal3f(0, 0, 10);
	gl.glVertex3fv(placas[1][2].getVertex(), 0);
	gl.glNormal3f(0, 0, 10);
	gl.glVertex3fv(placas[1][3].getVertex(), 0);
	gl.glEnd();

}*/