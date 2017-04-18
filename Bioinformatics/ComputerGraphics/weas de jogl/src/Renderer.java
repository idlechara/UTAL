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
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.awt.*;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.*;

import com.jogamp.opengl.util.*;

import java.io.File;
import java.io.IOException;

/**
 * Clase renderer la cual renderiza un reloj de pendulo en 3D
 * @author natsuko
 *
 */
class Renderer implements GLEventListener {
	//nececitamos indicadores:
    float range_y = 0.0f;				//el rango nos indica cuanto hemos rotado en el eje nuestro reloj
    float range_x = 0.0f;				
    int rotation_y = 0;					//nos indica cuanto hemos de rotar aun nuestro rejoj
    int rotation_x = 0;
    private GLU glu = new GLU();		//nececitamos la herramienta de glu
    float angle = 260;					//angulo de inicio del pendulo
    boolean rotation_value = true;		//indica el sentido de la rotacion
    float speed_factor = 0.7f;				//es el factor de la velocidad del movimiento del pendulo
    float speed_smoothness = 0.018f;		//indica que tan suave es la aceleracion de este
    float angle_rad = 0, lenght = 2.5f;	//valores auxiliares para calcular la posicion repsecto a un punto
    Calendar cal = Calendar.getInstance();	//calendario... obiamente para extraer los datos de la hora actual
    int minutos = cal.get(Calendar.MINUTE), 
    horas = cal.get(Calendar.HOUR), 
    segundos = cal.get(Calendar.SECOND);
    Texture madera, piedra;				//descriptores para las texturas
    
    /**
     * Funcion que es la responsable de renderizar cada cuadro.
     */
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();			//se inicializa herramienta
        GLUT glut = new GLUT();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);	//se limpia la pantalla
        gl.glLoadIdentity();								//se carga la matriz identidad, osea, resetamos la matriz

        gl.glTranslatef(0f, -2f, -10.0f);		//desplasamos todo 6 casillas atras, para lo de la perspectiva

        if(rotation_y > 0){	//esto nos sirve para hace rla rotacion en el eje Y
        	range_y++;
        	rotation_y--;
        }
        if(rotation_y < 0){	//esto nos sirve para hace rla rotacion en el eje Y
        	range_y--;
        	rotation_y++;
        }
        if(rotation_x > 0){	//esto nos sirve para hace rla rotacion en el eje x
        	range_x++;
        	rotation_x--;
        }
        if(rotation_x < 0){	//esto nos sirve para hace rla rotacion en el eje x
        	range_x--;
        	rotation_x++;
        }

        gl.glRotatef(range_y,1f,0f,0f);			//el efecto de rotacion
        gl.glRotatef(range_x,0f,1f,0f);			//el efecto de rotacion
        
        this.setMaterial(gLDrawable, 1);
       
        gl.glPushMatrix();
        gl.glTranslated(0,-1,0);
        this.drawRectangle(gLDrawable, 2, 8);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        this.drawTriangle(gLDrawable, 2, 4, true);
        gl.glRotatef(180, 0, 1, 0);
        this.drawTriangle(gLDrawable, 2, 4, false);
        gl.glPopMatrix();
         
        gl.glPushMatrix();
        gl.glTranslated(4,-1,-4);
        gl.glRotatef(90, 0, 1,0);
        this.drawRectangle(gLDrawable, 2, 8);
        
        gl.glPushMatrix();
        gl.glTranslated(0,0,-8);
        this.drawRectangle(gLDrawable, 2, 8);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslated(0, -1, -8);
        this.drawRectangle(gLDrawable, 2, 8);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslated(0, 0, -8);
        this.drawTriangle(gLDrawable, 2, 4, true);
        gl.glRotatef(180, 0, 1, 0);
        this.drawTriangle(gLDrawable, 2, 4, false);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslated(0, 1, -4);
        gl.glRotatef(90, 0, 1, 0);
        gl.glRotatef(45, 1, 0, 0);
        this.drawRectangle(gLDrawable, 2, 8);
        gl.glPopMatrix();
        
        
        
        //ahora dibujamos, una vez con nuestro "escritorio-medio-canvas-medio-neko" seteado
        //base
        setMaterial(gLDrawable, 1);				//indicamos que material deseamos usar
        drawPseudoCubic(gLDrawable, 5, 0.5f, 4);
        
        //pilar1
        setMaterial(gLDrawable, 2);
        gl.glPushMatrix();						//sacamos un respaldo de la matriz
        gl.glTranslated(-2, 2.5, 0);			//trasladamos la figura
        drawPseudoCubic(gLDrawable, 0.5f, 5f, 0.5f);	//dibujamos el pseudocubo
        gl.glPopMatrix();						//cargamos el respaldo de esta

        //pilar2
        gl.glPushMatrix();
        gl.glTranslated(2, 2.5, 0);
        drawPseudoCubic(gLDrawable, 0.5f, 5f, 0.5f);
        gl.glPopMatrix();
        
        //marco
        setMaterial(gLDrawable, 1);
        gl.glPushMatrix();
        gl.glTranslated(0, 5, 0);
        drawPseudoCubic(gLDrawable, 3.49f, 3f, 0.5f);
        gl.glPopMatrix();
        
        //cosa de la hora
        setMaterial(gLDrawable, 3);
        gl.glPushMatrix();
        gl.glTranslated(0, 5, 0.1);
        glut.glutSolidCylinder(1,0.2 , 20, 20);
        gl.glPopMatrix();
        
        //eje de la hora
        setMaterial(gLDrawable, 0);
        gl.glPushMatrix();
        gl.glTranslated(0, 5, 0.2);
        glut.glutSolidCylinder(0.05, 0.3, 20, 20);
        gl.glPopMatrix();
        
        //aca debiese ir el pendulo
        setMaterial(gLDrawable, 2);
        gl.glPushMatrix();
        angle_rad = (float)((Math.PI/180)*angle);	//primero vemos los angulos
        gl.glTranslatef(0, 3.6f, 0);				//tralasladamos para ponernos en posicion
        gl.glTranslatef(
        		(float)(Math.cos(angle_rad)* (lenght/2)),		//esto nos trasladara al "centro" de la figura 
        		(float)(Math.sin(angle_rad)* (lenght/2)), 0);
        gl.glRotatef(angle, 0, 0, 1);				//rotamos a modo de quedar afin con el dibujo
        drawPseudoCubic(gLDrawable, lenght, 0.3f, 0.3f);	//dibujamos la barra
        gl.glTranslatef(1f, 0, 0);		//nos trasladamos nuevamente, pero como ya se hizo una rotacion, es como si
        setMaterial(gLDrawable, 0);		//simplemente avanzaramos, para luego colocar la masa del pendulo
        glut.glutSolidSphere(0.5, 10, 10);
        gl.glPopMatrix();

        //manilla de la hora
        setMaterial(gLDrawable, 4);
        gl.glPushMatrix();
        angle_rad = -(float)(((Math.PI/6)*horas)-(Math.PI/2));
        gl.glTranslated(0, 5, 0.4);
        gl.glTranslatef((float)(Math.cos(angle_rad)/4), (float)(Math.sin(angle_rad)/4), 0);
        gl.glRotatef(-((360/12)*horas)-90, 0, 0, 1);
        gl.glRotatef(180, 0, 0, 0);
        drawPseudoCubic(gLDrawable, 0.5f, 0.02f, 0.02f);
        gl.glPopMatrix();

        //manilla de los minutos
        setMaterial(gLDrawable, 5);
        gl.glPushMatrix();
        angle_rad = -(float)(((Math.PI/30)*minutos)-(Math.PI/2));
        gl.glTranslated(0, 5, 0.45);
        gl.glTranslatef((float)((Math.cos(angle_rad)*0.65)/2), (float)((Math.sin(angle_rad)*0.65)/2), 0);
        gl.glRotatef(-((360/60)*minutos)-90, 0, 0, 1);
        gl.glRotatef(180, 0, 0, 0);
        drawPseudoCubic(gLDrawable, 0.65f, 0.02f, 0.02f);
        gl.glPopMatrix();
        

        //manilla de los segundos
        setMaterial(gLDrawable, 6);
        gl.glPushMatrix();
        angle_rad = -(float)(((Math.PI/30)*segundos)-(Math.PI/2));
        gl.glTranslated(0, 5, 0.5);
        gl.glTranslatef((float)((Math.cos(angle_rad)*0.7)/2), (float)((Math.sin(angle_rad)*0.7)/2), 0);
        gl.glRotatef(-((360/60)*segundos)-90, 0, 0, 1);
        gl.glRotatef(180, 0, 0, 0);
        drawPseudoCubic(gLDrawable, 0.7f, 0.02f, 0.02f);
        gl.glPopMatrix();
        
        //cosas de la hora
        setMaterial(gLDrawable, 2);
        for(int stage=0; stage <12;stage++){
            gl.glPushMatrix();
        	gl.glTranslated(0, 5, 0.35);
        	angle_rad = -(float)(((Math.PI/6)*stage)-(Math.PI/2));
        	gl.glTranslatef((float)((Math.cos(angle_rad)*0.7)*1.1), (float)((Math.sin(angle_rad)*0.7)*1.1), 0);
        	gl.glRotatef(-((360/12)*stage)-90, 0, 0, 1);
        	gl.glRotatef(180, 0, 0, 0);
        	drawPseudoCubic(gLDrawable, 0.1f, 0.05f, 0.05f);
            gl.glPopMatrix();
        }
        
        gl.glFlush();		//descargamos el buffer en pantalla
        
        //son computados los datos nesesarios para las simulaciones de movimiento
        if(rotation_value)angle+= speed_factor;
        else angle -= speed_factor;
        if(angle > 300) rotation_value = false;
        if(angle < 240) rotation_value = true;
        if((angle > 270)&&(rotation_value)) speed_factor -= speed_smoothness;
        if((angle < 270)&&(rotation_value)) speed_factor += speed_smoothness;
        if((angle > 270)&&(rotation_value==false)) speed_factor += speed_smoothness;
        if((angle < 270)&&(rotation_value==false)) speed_factor -= speed_smoothness;
        //y tambien las del calculo de la hora
        cal = Calendar.getInstance();
        minutos = cal.get(Calendar.MINUTE);
        horas = cal.get(Calendar.HOUR);
        segundos = cal.get(Calendar.SECOND);
        
    }

    
    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    /**
     * aca van los metodos que se han de llamar al inicio, algo asi como el seteo previo
     */
    public void init(GLAutoDrawable gLDrawable) {
        GL2 gl = gLDrawable.getGL().getGL2();
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);              // Hablilitamos un shading para que se vean mas bonitas las sombras
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // dejamos un fondo negro
        gl.glClearDepth(1.0f);                      // dejamos la profundidad del buffer
//        gl.glEnable(GL.GL_DEPTH_TEST);							// habilitamos el testeo
//        gl.glDepthFunc(GL.GL_LEQUAL);								// y luego de decimos cual es
        gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);	// y unos calculos de perspectiva
        
        // luego intetentamos cargar las texturas
        try {
            madera = TextureIO.newTexture(Renderer.class.getResource("/Resources/madera.jpg"), true,"jpg");
            piedra = TextureIO.newTexture(Renderer.class.getResource("/Resources/Piedra.jpg"), true,"jpg");
        }//sino.. PLR nomas..xD
        catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }

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

    /**
     * Esta funcion se encarga de generar un cuerpo del tipo cubo, la 
     * gracia y lo que lo hace interesante es el hecho de que parte desde (0,0,0)
     * @param x dimension de crecimiento en x
     * @param y dimension de crecimiento en y
     * @param z dimension de crecimiento en z
     */
   
    public void drawPseudoCubic(GLAutoDrawable drawable,float x, float y, float z){

        final GL2 gl = drawable.getGL().getGL2();
        
        //definimos los vertices de nuestra figura
    	float[] vertice_1 = {x/2, y/2, z/2};
    	float[] vertice_2 = {-x/2, y/2, z/2};
    	float[] vertice_3 = {-x/2, y/2, -z/2};
    	float[] vertice_4 = {x/2, y/2, -z/2};
    	
    	float[] vertice_5 = {x/2, -y/2, z/2};
    	float[] vertice_6 = {-x/2, -y/2, z/2};
    	float[] vertice_7 = {-x/2, -y/2, -z/2};
    	float[] vertice_8 = {x/2, -y/2, -z/2};

    	gl.glPushMatrix();
    	gl.glBegin(GL2.GL_QUADS);	//comenzamos a dibujar nuestros cuadrados
    	//cara superior
    	gl.glNormal3fv(vertice_1, 0);	//seteamos la normal, de acuerdo a la direccion de nuestra cara.
        gl.glTexCoord2f(0.0f, 0.0f);	//le indicamos que parte de la textura cargar, en caso de que existiese
    	gl.glVertex3fv(vertice_1, 0);	//dibujamos un vertice
    	gl.glNormal3fv(vertice_2, 0);
        gl.glTexCoord2f(1.0f, 0.0f);
    	gl.glVertex3fv(vertice_2, 0);
    	gl.glNormal3fv(vertice_3, 0);
        gl.glTexCoord2f(1.0f, 1.0f);
    	gl.glVertex3fv(vertice_3, 0);
    	gl.glNormal3fv(vertice_4, 0);
        gl.glTexCoord2f(0.0f, 1.0f);
    	gl.glVertex3fv(vertice_4, 0);

    	//cara inferior
    	gl.glNormal3fv(vertice_5, 0);
        gl.glTexCoord2f(0.0f, 0.0f);
    	gl.glVertex3fv(vertice_5, 0);
    	gl.glNormal3fv(vertice_6, 0);
        gl.glTexCoord2f(1.0f, 0.0f);
    	gl.glVertex3fv(vertice_6, 0);
    	gl.glNormal3fv(vertice_7, 0);
        gl.glTexCoord2f(1.0f, 1.0f);
    	gl.glVertex3fv(vertice_7, 0);
    	gl.glNormal3fv(vertice_8, 0);
        gl.glTexCoord2f(0.0f, 1.0f);
    	gl.glVertex3fv(vertice_8, 0);

    	//cara delantera
    	gl.glNormal3fv(vertice_1, 0);
        gl.glTexCoord2f(0.0f, 0.0f);
    	gl.glVertex3fv(vertice_1, 0);
    	gl.glNormal3fv(vertice_2, 0);
        gl.glTexCoord2f(1.0f, 0.0f);
    	gl.glVertex3fv(vertice_2, 0);
    	gl.glNormal3fv(vertice_6, 0);
        gl.glTexCoord2f(1.0f, 1.0f);
    	gl.glVertex3fv(vertice_6, 0);
    	gl.glNormal3fv(vertice_5, 0);
        gl.glTexCoord2f(0.0f, 1.0f);
    	gl.glVertex3fv(vertice_5, 0);

    	//cara frontal
    	gl.glNormal3fv(vertice_3, 0);
        gl.glTexCoord2f(0.0f, 0.0f);
    	gl.glVertex3fv(vertice_3, 0);
    	gl.glNormal3fv(vertice_4, 0);
        gl.glTexCoord2f(1.0f, 0.0f);
    	gl.glVertex3fv(vertice_4, 0);
    	gl.glNormal3fv(vertice_8, 0);
        gl.glTexCoord2f(1.0f, 1.0f);
    	gl.glVertex3fv(vertice_8, 0);
    	gl.glNormal3fv(vertice_7, 0);
        gl.glTexCoord2f(0.0f, 1.0f);
    	gl.glVertex3fv(vertice_7, 0);

    	//cara izda
    	gl.glNormal3fv(vertice_1, 0);
        gl.glTexCoord2f(0.0f, 0.0f);
    	gl.glVertex3fv(vertice_1, 0);
    	gl.glNormal3fv(vertice_4, 0);
        gl.glTexCoord2f(1.0f, 0.0f);
    	gl.glVertex3fv(vertice_4, 0);
    	gl.glNormal3fv(vertice_8, 0);
        gl.glTexCoord2f(1.0f, 1.0f);
    	gl.glVertex3fv(vertice_8, 0);
    	gl.glNormal3fv(vertice_5, 0);
        gl.glTexCoord2f(0.0f, 1.0f);
    	gl.glVertex3fv(vertice_5, 0);

    	//cara dcha
    	gl.glNormal3fv(vertice_2, 0);
        gl.glTexCoord2f(0.0f, 0.0f);
    	gl.glVertex3fv(vertice_2, 0);
    	gl.glNormal3fv(vertice_3, 0);
        gl.glTexCoord2f(1.0f, 0.0f);
    	gl.glVertex3fv(vertice_3, 0);
    	gl.glNormal3fv(vertice_7, 0);
        gl.glTexCoord2f(1.0f, 1.0f);
    	gl.glVertex3fv(vertice_7, 0);
    	gl.glNormal3fv(vertice_6, 0);
        gl.glTexCoord2f(0.0f, 1.0f);
    	gl.glVertex3fv(vertice_6, 0);
    	
    	gl.glEnd();
    	gl.glPopMatrix();
    	
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


	@Override
	public void dispose(GLAutoDrawable arg0) {
	}
	
	/**
	 * Claro, de modo de que esto sea mas facil, la funcion que setea los materiales,
	 * fue aislada del resto
	 * @param drawable el elemento autodibujable
	 * @param type	cual material se va a usar
	 */
	public void setMaterial(GLAutoDrawable drawable, int type){
        final GL2 gl = drawable.getGL().getGL2();
        // Set material properties.

        if(type==0){
        	gl.glDisable(GL.GL_TEXTURE_2D);	//se desactivan las texturas
        	float[] rgba = {1f, 1f, 1f};	//se setea el color del material
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, rgba, 0);	//se setean las caracteristicas del material
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_SPECULAR, rgba, 0);
        	gl.glMaterialf(GL.GL_FRONT, GLLightingFunc.GL_SHININESS, 50f);
        }

        if(type==1){
        	gl.glEnable(GL.GL_TEXTURE_2D);	//habilitamos las texturas
        	madera.bind();					//unimos una textura a una "forma"
        	float[] rgba = {1f, 1f, 1f};
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, rgba, 0);
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_SPECULAR, rgba, 0);
        	gl.glMaterialf(GL.GL_FRONT, GLLightingFunc.GL_SHININESS, 0.5f);
        }

        if(type==2){
        	gl.glEnable(GL.GL_TEXTURE_2D);
        	piedra.bind();
        	float[] rgba = {1f, 1f, 1f};
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, rgba, 0);
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_SPECULAR, rgba, 0);
        	gl.glMaterialf(GL.GL_FRONT, GLLightingFunc.GL_SHININESS, 0.5f);
        }

        if(type==3){
        	gl.glDisable(GL.GL_TEXTURE_2D);
        	float[] rgba = {0.2f, 0.2f, 1f};
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, rgba, 1);
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_SPECULAR, rgba, 0);
        	gl.glMaterialf(GL.GL_FRONT, GLLightingFunc.GL_SHININESS, 0.5f);
        }
        if(type==4){
        	gl.glDisable(GL.GL_TEXTURE_2D);
        	float[] rgba = {0f, 0f, 1f};
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, rgba, 0);
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_SPECULAR, rgba, 0);
        	gl.glMaterialf(GL.GL_FRONT, GLLightingFunc.GL_SHININESS, 50f);
        }
        if(type==5){
        	gl.glDisable(GL.GL_TEXTURE_2D);
        	float[] rgba = {1, 0, 0};
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, rgba, 0);
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_SPECULAR, rgba, 0);
        	gl.glMaterialf(GL.GL_FRONT, GLLightingFunc.GL_SHININESS, 50f);
        }
        if(type==6){
        	gl.glDisable(GL.GL_TEXTURE_2D);
        	float[] rgba = {0f, 1, 0};
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_AMBIENT, rgba, 0);
        	gl.glMaterialfv(GL.GL_FRONT, GLLightingFunc.GL_SPECULAR, rgba, 0);
        	gl.glMaterialf(GL.GL_FRONT, GLLightingFunc.GL_SHININESS, 50f);
        }
	}

    public static void main(String[] args) {
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
		
		//montamos nuestra segunda pantalla
		TextArea text = new TextArea("Tarea nro. 3 Computacion Grafica:\n"+
				"\n Las flechas direccionales son\n" +
				"las encargadas de cambiar los \n" +
				"algulos de la camara.\n" +
				"\n" +
				"Detalle Freak: El reloj DA la hora.\n" +
				"\n" +
				"\n" +
				"Para terminar la ejecucion, \n" +
				"cerrar la ventana del GLX", 25,30);
		Frame frame2 = new Frame("Ayuda");
		frame2.add(text);
		frame2.pack();
		frame2.setVisible(true);
		
		//le damos forma, ponemos los eventos de openGL
		Renderer scene = new Renderer();
		InputHandler manejador = new InputHandler(scene);
		canvas.addGLEventListener(scene);
		canvas.addKeyListener(manejador);
		
		//y nuestro animador amigable.. XD gracias APIDOC!
		FPSAnimator animator = new FPSAnimator(canvas, 60);
		animator.add(canvas);
		animator.start();
    }
    
    /**
     * Metodo que dibuja un triangulo con base en el origen
     * @param height altura del triangulo
     * @param width base del triangulo
     */
	public void drawTriangle(GLAutoDrawable drawable, float height, float width, boolean way){

        final GL2 gl = drawable.getGL().getGL2();
        
        gl.glBegin(gl.GL_TRIANGLES);
        if(way) gl.glNormal3f(0, 0, 1);
        else gl.glNormal3f(0, 0, -1);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(width, 0, 0);
        gl.glVertex3f(0, height, 0);
        gl.glEnd();
	}
	public void drawRectangle(GLAutoDrawable drawable, float h, float w){

        final GL2 gl = drawable.getGL().getGL2();
		gl.glBegin(gl.GL_QUADS);
		gl.glNormal3f(0,0,1);
		gl.glVertex3f(-w/2, h/2, 0);
        gl.glVertex3f(w/2, h/2, 0);
        gl.glVertex3f(w/2, -h/2, 0);
        gl.glVertex3f(-w/2, -h/2, 0);
        gl.glEnd();
	}
}

/**
 * Clase que tiene los metodos para controlar los eventos de la pantalla
 * @author natsuko
 *
 */
class InputHandler implements java.awt.event.KeyListener{
	//nececitamos tener acceso al renderizador
    private Renderer renderer;

    //constructor
    public InputHandler(Renderer renderer) {
        this.renderer = renderer;
    }

    /**
     * esta funcion se encarga de pŕosesar cada evento que ocurre, mientras
     * este relacionado al teclado y a las teclas q deseemos
     * @param e	el evento a recibir
     * @param pressed	si fue presionada una tecla
     */
    private void processKeyEvent(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
        case java.awt.event.KeyEvent.VK_UP:
        	renderer.rotation_y += 10;
            break;
        case java.awt.event.KeyEvent.VK_DOWN:
        	renderer.rotation_y -= 10;
            break;
        case java.awt.event.KeyEvent.VK_LEFT:
        	renderer.rotation_x += 10;   
            break;
        case java.awt.event.KeyEvent.VK_RIGHT:
        	renderer.rotation_x -= 10;
            break;
        }
    }
	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
        processKeyEvent(e);
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
	}
	
}