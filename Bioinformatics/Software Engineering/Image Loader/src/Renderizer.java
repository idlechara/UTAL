import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLLightingFunc;

import com.jogamp.opengl.util.gl2.GLUT;

/**
 * clase de pruebas para las implementaciones de la salida
 * @author natsuko
 *
 */
public class Renderizer implements GLEventListener {

	Imagen2D imagen;
	
	@Override
	public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();			//se inicializa herramienta
        GLUT glut = new GLUT();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);	//se limpia la pantalla
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {

	}

	@Override
	public void init(GLAutoDrawable gLDrawable) {
        GL2 gl = gLDrawable.getGL().getGL2();
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);              // Hablilitamos un shading para que se vean mas bonitas las sombras
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // dejamos un fondo negro
        gl.glClearDepth(1.0f);                      // dejamos la profundidad del buffer
        gl.glEnable(GL.GL_DEPTH_TEST);							// habilitamos el testeo
        gl.glDepthFunc(GL.GL_LEQUAL);								// y luego de decimos cual es
        gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);	// y unos calculos de perspectiva
        //gl.glViewport(0, 0, 100, 100);
        
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {

	}

}
