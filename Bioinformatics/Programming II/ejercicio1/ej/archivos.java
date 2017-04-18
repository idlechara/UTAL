package ej;
import java.io.*;

public class archivos {
	BufferedReader lectorarchivo;
	FileReader fuente;
	
	public archivos(int i){
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		System.out.println( "Ingrese la ruta del archivo "+ i+": ");
		try {
			this.fuente = new FileReader(teclado.readLine());
			this.lectorarchivo = new BufferedReader(this.fuente);
		} catch (FileNotFoundException e) {
			System.err.println("No se encontro el archivo");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("No se pudo abrir el archivo");
			System.exit(1);
		}
	}
	public String extraer_string() throws IOException{
		StringBuffer temporal = new StringBuffer();
		char temp = (char)this.lectorarchivo.read();
		while(temp != (char)-1){
			temporal.append(temp);
			temp = (char)this.lectorarchivo.read();
		}
		System.out.println(temporal);
		return temporal.toString();
	}
}
