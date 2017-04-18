import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
		BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, false)));
		for(int i=0; i<puntos.npoints; i++){
			salida.write(this.puntos.xpoints[i] + "," + this.puntos.ypoints[i]);
			salida.newLine();
		}
		salida.flush();
		salida.close();
		
	}	
	
	public void guardarZTE(String ruta, Polygon puntos) throws IOException{
		BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, false)));
		for(int i=0; i<puntos.npoints; i++){
			salida.write(puntos.xpoints[i] + "," +puntos.ypoints[i]);
			salida.newLine();
		}
		salida.flush();
		salida.close();
		
	}	
	
	public Polygon abrirZTE(String ruta) throws IOException{
		Polygon retorno = new Polygon();
		BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)));
		String lectura = entrada.readLine();
		String[] fragmentos;
		while(lectura!=null){
			System.out.println(lectura);
			fragmentos = lectura.split(",");
			retorno.addPoint(Integer.parseInt(fragmentos[0]), Integer.parseInt(fragmentos[1]));
			lectura = entrada.readLine();
		}
		entrada.close();
		return retorno;
	}
	
	public void abrirTrayectoria(String ruta) throws IOException{
		this.puntos = abrirZTE(ruta);
	}

	public void borrarPuntos(){
		this.puntos = new Polygon();
	}
}
