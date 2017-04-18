import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;

/**
 * un listener que es usado para escuchar las acciones del boton
 * @author natsuko
 *
 */
class SuperListenerXd implements ActionListener{
	AutoMovilXd scene;
	
	//constructor
	public SuperListenerXd(AutoMovilXd scene){
		this.scene = scene;
	}
	
	@Override
	//el listener en si
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.scene.luces==false) this.scene.luces = true;
		else this.scene.luces = false;
		
	}
	
}

public class AutoMovilXd implements GLEventListener {

	//ponemos la variables que nececitamos para mantener el control de nuestro auto
	boolean luces = true;
	private double theta = 0;
	private double angulo_etapa = 360;
	private double bache_etapa = -11;
	private double transpocion_etapa = 0;
	private int etapa=0;
	private boolean bache = true;
	
	//metodo main, que ociososamente solo inicializa componentes
	public static void main(String[] args) {
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
		
		//montamos la segunda
		Frame frame2 = new Frame("Controles");
		Button boton = new Button("Luces?");
		frame2.add(boton);
		frame2.setVisible(true);
		frame2.pack();
		frame2.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//le damos forma, ponemos los eventos de openGL
		AutoMovilXd scene = new AutoMovilXd();
		canvas.addGLEventListener(scene);
		
		//añadimos un escuchador para los eventos de mismo opengl
		boton.addActionListener(new SuperListenerXd(scene));
		
		//y nuestro animador amigable.. XD gracias APIDOC!
		Animator animator = new FPSAnimator(canvas, 60);
		animator.add(canvas);
		animator.start();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		computar();
		renderAuto(drawable, (float)this.angulo_etapa);
		renderBache(drawable);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}
	
	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(1, 1, 1, 0);
		gl.glOrtho(-10, 30, -10, 30, -1, 1);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	}

	private void computar() {
		//operador de la rueda :)
		theta += 0.1;
		if(theta==1) theta = 0.1;
		
		//aca se ve por las etapas... si es que hay un bacheeeeee
		if(bache_etapa > 3){
			if((etapa>=0)&&(etapa<=45)){
				this.angulo_etapa--;
				this.transpocion_etapa = this.transpocion_etapa + 0.25;
			}
			if((etapa>=46)&&(etapa<=90)){
				this.angulo_etapa++;
				this.transpocion_etapa = this.transpocion_etapa + 0.1;
			}
			if((etapa>=91)&&(etapa<=135)){
				this.angulo_etapa++;
				this.transpocion_etapa = this.transpocion_etapa - 0.25;
			}
			if((etapa>=136)&&(etapa<180)){
				this.angulo_etapa--;
				this.transpocion_etapa = this.transpocion_etapa - 0.1;
			}
			etapa++;
		}
		//con esto generamos el bucle, y cerramos.
		if(etapa==360){
			etapa = 0;
			bache=false;
		}
		
		//ahora vemos que onda el bache qlio!
		this.bache_etapa = this.bache_etapa+0.2;
		if(bache == false){
			this.bache_etapa = -10;
			bache = true;
		}
	}
     

	private void renderWheel(GLAutoDrawable drawable, float cx, float cy, float radius) {
		GL2 gl = drawable.getGL().getGL2();

		double increment = (2*Math.PI/10);
		
		gl.glColor4f(0, 0, 0, 1);
		
		for(double angle = 0; angle < 2*Math.PI; angle+=increment){
			//dibujamos los rayos
			gl.glBegin(gl.GL_LINE_STRIP);
			gl.glVertex2d(cx, cy);
			gl.glVertex2d(cx + Math.cos(angle+theta)* radius, cy + Math.sin(angle+theta)*radius);
			gl.glEnd();

			gl.glBegin(gl.GL_LINE_STRIP);
			
			gl.glVertex2d(cx + Math.cos(angle)* radius, cy + Math.sin(angle)*radius);
			gl.glVertex2d(cx + Math.cos(angle + increment)*radius, cy + Math.sin(angle + increment)*radius);
			gl.glEnd();
		}
	}    

	private void renderBache(GLAutoDrawable drawable) {
		//tomamos el elemento donde renderizamos.
		GL2 gl = drawable.getGL().getGL2();
		//iniciamos el dibujo de la polilinea
		gl.glColor3f(0, 0, 0);
		gl.glBegin(gl.GL_POLYGON);
		gl.glVertex2d(this.bache_etapa,1);
		gl.glVertex2d(this.bache_etapa-2,4);
		gl.glVertex2d(this.bache_etapa-4,4);
		gl.glVertex2d(this.bache_etapa-8,1);
		gl.glVertex2d(this.bache_etapa,1);
		gl.glEnd();
		
	}
	
	private void renderAuto(GLAutoDrawable drawable, float carAngle) {
		//tomamos el elemento donde renderizamos.
		GL2 gl = drawable.getGL().getGL2();
		//iniciamos el dibujo de la polilinea

		//giramos, de acuerdo al bache, si hay

		gl.glTranslated(0, this.transpocion_etapa, 0);
		gl.glRotatef(carAngle, 0, 0, 1);
		

		//usamos un vector de 3dimensiones para definir el color, negro!!!!!!!
		gl.glColor3f(0, 0, 0);

		gl.glBegin(gl.GL_POLYGON);
		//comeinza el dibujo:
		gl.glVertex2d(3,2);
		gl.glVertex2d(3,5);
		gl.glVertex2d(6,5);
		gl.glVertex2d(7,6);
		gl.glVertex2d(10,8);
		gl.glVertex2d(15,8);
		gl.glVertex2d(17,6);
		gl.glVertex2d(20,6);
		gl.glVertex2d(20,8);
		gl.glVertex2d(19,8);
		gl.glVertex2d(19,9);
		gl.glVertex2d(22,9);
		gl.glVertex2d(22,8);
		gl.glVertex2d(21,8);
		gl.glVertex2d(21,2);
		gl.glVertex2d(20,2);
		gl.glVertex2d(19,3);
		gl.glVertex2d(17,3);
		gl.glVertex2d(16,2);
		gl.glVertex2d(9,2);
		gl.glVertex2d(8,3);
		gl.glVertex2d(6,3);
		gl.glVertex2d(5,2);
		gl.glVertex2d(3,2);
		gl.glEnd();
		renderWheel(drawable, 7, 2, 1);
		renderWheel(drawable, 18, 2, 1);
		
		//si estan las luces...
		if(this.luces){
			gl.glBegin(GL.GL_TRIANGLES);
			gl.glColor3f(255, 255, 0);
			gl.glVertex2d(4,4);
			gl.glColor3f(255, 255, 255);
			gl.glVertex2d(-4,6);
			gl.glColor3f(255, 255, 255);
			gl.glVertex2d(-4,1);
			gl.glEnd();
		}
		
		//regresamos las cosas donde estaban ;)
		gl.glRotatef(-carAngle, 0, 0, 1);
		gl.glTranslated(0, -this.transpocion_etapa, 0);
	}
}