import java.awt.Canvas;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jogamp.opengl.util.FPSAnimator;


public class VentanaTesting extends JFrame {

	public VentanaTesting() throws HeadlessException {
		super();
	}

	public VentanaTesting(GraphicsConfiguration gc) {
		super(gc);
	}
	public VentanaTesting(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public VentanaTesting(String title) throws HeadlessException {
		super(title);
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//canvas en el cual se visualizan las imagenes
		//despues es posible cambiarlo por un glCanvas
		RenderizableCanvas lienzo = new RenderizableCanvas();
		
		//imagen a renderizar
		BufferedImage imagen = null;
		
		//ventana de salida
		VentanaTesting ventana = new VentanaTesting("Salida");
		ventana.setLayout(null);
		
		//selectori de archivos
		FileDialog seleccionar = new FileDialog(ventana, "Elije imagen", FileDialog.LOAD);
		seleccionar.setVisible(true);
		

		Imagen2D[] imagenes = new Imagen2D[31];
		for(int i=1; i<32;i++){
			System.out.println(seleccionar.getDirectory()+"short"+i+".jpg");
			imagenes[i-1] = new Imagen2D("Short", "??", "??", ImageIO.read(new File(seleccionar.getDirectory()+"short"+i+".jpg")));
		}
		
		Imagen3D tridimensional = new Imagen3D(imagenes);
		
		/*
		//se intenta leer la imagen, de otra forma arroja un error y termina la ejecucion.
		try {
			imagen = ImageIO.read(new File(seleccionar.getDirectory()+seleccionar.getFile()));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Apertura de archivo fallada. Ejecucion Terminada.");
		}
		*/
		//se le indica que imagen debe precargar el camvas, esta es pasada
		//por referencia, para asi mantener la actualizacion.
		lienzo.setImage(tridimensional.slice(15, 0, null));
		
		//los jlabel para poder ver que concentraciones de colores existen.
		JLabel rojo = new JLabel("Rojo");
		rojo.setBounds(new Rectangle(660,10,100,20));
		JLabel verde = new JLabel("Verde");
		verde.setBounds(new Rectangle(660,30,100,20));
		JLabel azul = new JLabel("Azul");
		azul.setBounds(new Rectangle(660,50,100,20));
		
		//se crea el canvas en el cual se renderiza
		Canvas lienzoEjemplo = new Canvas();
		RenderizableCanvas lienzo2 = new RenderizableCanvas();
		lienzoEjemplo.setBounds(new Rectangle(700,100,30,30));
		lienzo2.setBounds(new Rectangle(700,300,100,100));
		
		//se añade el escucha de moviemeinto
		MotionListener listener = new MotionListener(rojo, verde, azul, imagenes[1], lienzoEjemplo, lienzo);
		lienzo.addMouseMotionListener(listener);
		lienzo.addMouseListener(listener);
		
		//lienzo.addMouseListener(new ClickListener(imagen, lienzo));
		
		
		
		//se setea la ventana y arranca.
		ventana.add(lienzo2);
		ventana.add(lienzo);
		ventana.add(rojo);
		ventana.add(verde);
		ventana.add(azul);
		ventana.add(lienzoEjemplo);
		ventana.setSize(1000, 850);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
