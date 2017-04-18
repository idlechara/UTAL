import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;


public class Almacenamiento {

	Polygon puntos;
	
	public Almacenamiento(){
		this.puntos = new Polygon();
	}
	public void registrarTrayectoria(int i, int altura) {
		this.puntos.addPoint(i, altura);
	}
	
	public void guardarTrayectoria(String ruta) throws IOException{
		BufferedWriter salida = salida = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, false)));
		for(int i=0; i<puntos.npoints; i++){
			salida.write(this.puntos.xpoints[i] + "," + this.puntos.ypoints[i]);
		}
		salida.close();
		
	}

	public void borrarPuntos(){
		this.puntos = new Polygon();
	}
}
